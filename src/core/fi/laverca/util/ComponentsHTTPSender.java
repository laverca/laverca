/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2015 Laverca Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Modified by Eemeli Miettinen to use Commons HTTPComponents in Laverca
 *
 * Again modified by Matti Aarnio to fit in Kiuru MSSP core services.
 */

package fi.laverca.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.xml.namespace.QName;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;

import org.apache.axis.AxisFault;
import org.apache.axis.Constants;
import org.apache.axis.HTTPConstants;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.SocketInputStream;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.utils.JavaUtils;
import org.apache.axis.utils.NetworkUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.util.EntityUtils;

import fi.laverca.ErrorCodes;



/**
 * A replacement of the default Axis Commons HTTP sender that makes it
 * possible to share a connection manager among RoamingClient instances.
 */
@SuppressWarnings({"serial"})
public class ComponentsHTTPSender extends BasicHandler {
    
    private static Log log = LogFactory.getLog(ComponentsHTTPSender.class);

    private static int connectionTimeout = 30000; // At most 30 seconds to wait for a socket connection to form

    boolean httpChunkStream = true; // Use HTTP chunking or not.

    public static final String FAULTFACTORY_INSTANCE = "laverca.FaultFactory.instance";
    public static final String HTTPCLIENT_INSTANCE   = "laverca.HttpClient.instance";
    public static final String SERVERCERTS           = "laverca.ServerCerts";

    public static final String CONTENTTYPE_APPLICATION_SOAPXML = "application/soap+xml"; // SOAP 1.2
    public static final String CONTENTTYPE_TEXT_XML            = "text/xml";             // SOAP 1.1

    public static final String ERROR_IN_HTTPSENDER = "laverca.errorin.httpsender";

    public ComponentsHTTPSender() {
        // Empty constructor
    }

    /**
     * invoke creates a socket connection, sends the request SOAP message and then
     * reads the response SOAP message back from the SOAP server
     *
     * @param msgContext the MessageContext object
     *
     * @throws AxisFault if the call fails
     */
    @Override
    public void invoke(MessageContext msgContext)
        throws AxisFault
    {
        final boolean debug = log.isDebugEnabled();

        // Know if the call is to a ONE_WAY end-point
        final boolean oneWay = (msgContext.getProperty("axis.one.way") != null);


        // Make sure we do NOT have PREVIOUS response message hanging around.
        msgContext.setResponseMessage(null);
        // Set default StatusCode to -1
        msgContext.setProperty(HTTPConstants.MC_HTTP_STATUS_CODE, new Integer(-1));

        HttpPost     post     = null;
        HttpResponse response = null;
        
        final LavercaHttpClient httpClient = (LavercaHttpClient) msgContext.getProperty(ComponentsHTTPSender.HTTPCLIENT_INSTANCE);
        final String remoteURL = msgContext.getStrProp(MessageContext.TRANS_URL);

        if (httpClient == null) {
            final String msg = "Code bug: Calling convention error. Got NULL HttpClient object.";
            final NullPointerException npe = new NullPointerException(msg);
            log.fatal(msg, npe);
            throw npe;
        }

        long deadline = 0L; // when it does time out?
        try {

            final URL targetURL = new URL(remoteURL);

            @SuppressWarnings("unchecked")
            final List<byte[]> serverCerts =
                (List<byte[]>)msgContext.getProperty(ComponentsHTTPSender.SERVERCERTS);
            LavercaSSLTrustManager.getInstance().setExpectedServerCerts(serverCerts);

            final Message reqMessage = msgContext.getRequestMessage();
            post = httpClient.buildHttpPost(remoteURL);

            // set false as default, addContentInfo can overwrite

            final HttpClientContext httpContext = httpClient.buildContext();
            final RequestConfig.Builder rcb = RequestConfig.custom();
            // As default no expectancy on continue..
            rcb.setExpectContinueEnabled(false);

            this.addContextInfo(post, httpClient, httpContext, rcb, msgContext, targetURL);
            if (msgContext.getTimeout() != 0) {
                deadline = System.currentTimeMillis() + msgContext.getTimeout();
            }

            final MessageRequestEntity requestEntity;
            if (msgContext.isPropertyTrue(HTTPConstants.MC_GZIP_REQUEST)) {
                log.debug("Creating GzipMessageRequestEntity");
                requestEntity = new GzipMessageRequestEntity(post, reqMessage, this.httpChunkStream);
            } else {
                log.debug("Creating plain MessageRequestEntity");
                requestEntity = new MessageRequestEntity(post, reqMessage, this.httpChunkStream);
            }
            post.setEntity(requestEntity);

            final String httpVersion = msgContext.getStrProp(MessageContext.HTTP_TRANSPORT_VERSION);
            if ((httpVersion != null) && httpVersion.equals(HTTPConstants.HEADER_PROTOCOL_V10)) {
                post.setProtocolVersion(HttpVersion.HTTP_1_0);
            }

            if (debug) {
                try {
                    String reqXML = reqMessage.toString();
                    log.debug("Sending XML to: "+remoteURL);
                    log.debug("-----------------------------------------------\n" +
                              reqXML);
                    log.debug("-----------------------------------------------");
                    log.debug("..executing HTTP POST operation..");
                } catch (Throwable t) {
                    log.debug("Got a throwable in trace code dumping request XML:", t);
                }
            }

            httpContext.setRequestConfig(rcb.build());
            response = httpClient.execute(post, httpContext);

            log.trace("..done executing HTTP POST operation, result is now at hand.");

            // Clear the expected server certificate set from this thread
            // (Also fault producers are doing this clearing.)
            LavercaSSLTrustManager.getInstance().setExpectedServerCerts(null);

            final int statusCode = response.getStatusLine().getStatusCode();
            // Deliver it to caller
            msgContext.setProperty(HTTPConstants.MC_HTTP_STATUS_CODE, new Integer(statusCode));
            msgContext.setProperty(HTTPConstants.MC_HTTP_STATUS_MESSAGE, response.getStatusLine().getReasonPhrase());

            // Pick information out of response
            final String contentType     = this.getHeader(response, HTTPConstants.HEADER_CONTENT_TYPE);
            final String contentLocation = this.getHeader(response, HTTPConstants.HEADER_CONTENT_LOCATION);
            final String contentLength   = this.getHeader(response, HTTPConstants.HEADER_CONTENT_LENGTH);

            msgContext.setProperty(HTTPConstants.HEADER_CONTENT_TYPE, contentType);

            
            if (debug) {
                log.debug("HTTP status code: "+statusCode+", contentType: "+contentType);
            }

            // Wrap the response body stream so that close() also releases
            // the connection back to the pool.
            final ReleasingFilterInputStream inputStream;

            final Header contentEncoding = response.getFirstHeader(HTTPConstants.HEADER_CONTENT_ENCODING);

            if (contentEncoding != null) {
                if (contentEncoding.getValue().equalsIgnoreCase(HTTPConstants.COMPRESSION_GZIP)) {
                    log.debug(" .. wrapping with GZIPInputStream()");
                    // Wrap the stream with GZIPInputStream ...
                    final GZIPInputStream gis = new GZIPInputStream(response.getEntity().getContent());
                    inputStream = new ReleasingFilterInputStream(httpClient, post, response, gis);
                } else {
                    final String msg = ("Unsupported Content-Encoding of '"+ contentEncoding.getValue() + "' found");
                    log.debug(msg);
                    httpClient.closeQuietly(post, response);
                    throw this.createFault(msgContext, AxisFault.soap12sender, msg);
                }
            } else {
                inputStream = new ReleasingFilterInputStream(httpClient, post, response, response.getEntity().getContent());
            }

            // Try reading one byte from stream
            boolean isEmpty = true;
            try {
                int c = inputStream.read();
                if (c >= 0) {
                    // Got something, put it back
                    inputStream.unread(c);
                    isEmpty = false;
                } else {
                    // Void end-point? Something else wrong?
                }
            } catch (Throwable t) {
                // All kinds of exceptions
            }

            if (isEmpty && !oneWay) {
                final String statusMessage = "HTTP response: "+statusCode+" "+response.getStatusLine().getReasonPhrase();
                final AxisFault fault = this.createFault( msgContext,
                                                          AxisFault.soap12receiver,
                                                          statusMessage);

                fault.addFaultDetail(Constants.QNAME_FAULTDETAIL_HTTPERRORCODE, Integer.toString(statusCode));
                try {
                    throw fault;
                } finally {
                    httpClient.closeQuietly(post, response);
                    inputStream.close();
                }
            }
            
            // After this phase, the response and post are NOT to be closed/released
            // in this code path! See comments further below at "AXIS closure processing rules"

            final Message outMsg = new Message(inputStream,
                                               false, contentType, contentLocation);
            // Transfer HTTP headers of HTTP message to MIME headers of SOAP message
            final Header[] responseHeaders = post.getAllHeaders();
            final MimeHeaders responseMimeHeaders = outMsg.getMimeHeaders();
            for (final Header responseHeader : responseHeaders) {
                responseMimeHeaders.addHeader(responseHeader.getName(),
                                              responseHeader.getValue());
            }
            outMsg.setMessageType(Message.RESPONSE);
            msgContext.setResponseMessage(outMsg);
            
            try {
                // Our caller needs SOAPEnvelope soon, and right now the mode is INPUTSTREAM.
                // Read the data now as SOAPEnvelope for our debug printout purposes.
                outMsg.getSOAPEnvelope();
                final String receivedXML = outMsg.toString();

                if (debug) {
                    if (null == contentLength) {
                        log.debug("No Content-Length header in the response");
                    } else {
                        log.debug("Content-Length: "+contentLength);
                    }
                    if (receivedXML != null && !receivedXML.isEmpty()) {
                        log.debug("Received XML:");
                        log.debug("-----------------------------------------------\n" +
                                  receivedXML);
                        log.debug("-----------------------------------------------");
                    }
                }
                
            } catch (AxisFault af) {
                throw this.makeFault(af, deadline, remoteURL, msgContext);
            }

        } catch (final org.apache.http.conn.ConnectTimeoutException e) {
            if (debug) {
                log.debug("Connection(Pool)TimeoutException: " + e.getMessage());
                log.trace(e);
            }

            httpClient.closeQuietly(post, response);

            throw this.makeFault(e, deadline, remoteURL, msgContext);

        } catch (final org.apache.http.conn.HttpHostConnectException e) {
            if (debug) {
                log.debug("HttpHostConnectException: " + e.getMessage());
                log.trace(e);
            }

            httpClient.closeQuietly(post, response);

            throw this.makeFault(e, deadline, remoteURL, msgContext);

        } catch (final java.net.SocketException e) {
            if (debug) {
                log.debug("Connection(Pool)SocketException: " + e.getMessage());
                log.trace(e);
            }

            httpClient.closeQuietly(post, response);

            throw this.makeFault(e, deadline, remoteURL, msgContext);

        } catch (final java.io.InterruptedIOException e) {
            if (debug) {
                log.debug("InterruptedIOException: " + e.getMessage());
                log.trace("",e);
            }

            httpClient.closeQuietly(post, response);

            throw this.makeFault(e, deadline, remoteURL, msgContext);

        } catch (final AxisFault e) {
            if (debug) {
                log.debug("AxisFault: " + e.getMessage());
                log.trace("",e);
            }

            httpClient.closeQuietly(post, response);

            msgContext.setProperty(ERROR_IN_HTTPSENDER, Boolean.TRUE);

            // Clear the expected server certificate set from this thread
            LavercaSSLTrustManager.getInstance().setExpectedServerCerts(null);

            throw e;

        } catch (final IOException e) {
            if (debug) {
                log.debug("IOException: " + e.getMessage());
                log.trace("",e);
            }

            httpClient.closeQuietly(post, response);

            throw this.makeFault(e, deadline, remoteURL, msgContext);

        } catch (final Exception e) {
            if (debug) {
                log.debug("Catch-all of Exception: " + e.getMessage());
                log.trace("",e);
            }

            httpClient.closeQuietly(post, response);

            throw this.makeFault(e, deadline, remoteURL, msgContext);

        } catch (final Throwable t) {
            if (debug) {
                log.debug("Catch-all of Throwable: " + t.getMessage());
                log.trace("",t);
            }

            // Includes memory overflow..  cleanup initially just to be sure.
            httpClient.closeQuietly(post, response);

            final Exception e = new Exception(t.getMessage(), t);
            throw this.makeFault(e, deadline, remoteURL, msgContext);
        }
    }

    /**
     * Create a connection problem fault with a code and a detail
     *
     * @param msgContext MessageContext object
     * @param code   Fault Code
     * @param detail Fault Detail
     * @return Created Fault
     */
    private AxisFault createFault(final MessageContext msgContext, final QName code, final String detail) {
        final QName[] subcodes = {new QName(ErrorCodes.ETSI_207_NS_URI, "_780", "msrs")};

        final AxisFault af = new AxisFault(code,
                                           subcodes,
                                           "Unable to provide services.",
                                           null,   // role
                                           null,   // node
                                           null);  // details

        af.addFaultDetailString(detail);
        return af;
    }

    /**
     * Create an connection problem AxisFault from an Exception
     *
     * @param e Exception
     * @param deadline Not used
     * @param remoteURL Remote URL
     * @param msgContext MessageContext object
     * @return AxisFault
     */
    private AxisFault makeFault(final Exception e,
                                final long deadline,
                                final String remoteURL,
                                final MessageContext msgContext)
    {
        msgContext.setProperty(ERROR_IN_HTTPSENDER, Boolean.TRUE);

        // Clear the expected server certificate set from this thread
        LavercaSSLTrustManager.getInstance().setExpectedServerCerts(null);
        return this.makeFault(msgContext, e, remoteURL);
    }

    /**
     * Create an connection problem AxisFault from an Exception
     *
     * @param msgContext MessageContext object
     * @param e Exception
     * @param remoteURL Remote URL
     * @return AxisFault
     */
    private AxisFault makeFault(final MessageContext msgContext,
                                final Exception e,
                                final String remoteURL)
    {
        AxisFault af = null;

        if (e instanceof org.apache.http.conn.HttpHostConnectException) {
            af = this.createFault( msgContext,
                                   AxisFault.soap12receiver,
                                   "Connection refused");
        } else if (e instanceof InterruptedIOException) {
            af = this.createFault( msgContext,
                                   AxisFault.soap12receiver,
                                   "Communication timed out");
        } else if (e instanceof java.io.IOException) {
            af = this.createFault( msgContext,
                                   AxisFault.soap12receiver,
                                   "Connection failed");
        }

        if (af == null) {
           // Default maker..
           af = AxisFault.makeFault(e);
        }

        return af;
    }

    /**
     * Extracts info from message context.
     *
     * @param method Post method
     * @param httpClient The client used for posting
     * @param httpContext
     * @param rcb
     * @param msgContext the message context
     * @param tmpURL the url to post to.
     * @throws AxisFault
     */
    private void addContextInfo( final HttpPost method,
                                 final LavercaHttpClient httpClient,
                                 final HttpClientContext httpContext,
                                 final RequestConfig.Builder rcb,
                                 final MessageContext msgContext,
                                 final URL tmpURL )
        throws AxisFault
    {
        if (msgContext.getTimeout() != 0) {
            // optionally set a timeout for response waits
            rcb.setSocketTimeout(msgContext.getTimeout());
        }

        // Always set the 30 second timeout on establishing the connection
        rcb
            .setConnectionRequestTimeout(10)
            .setConnectTimeout(ComponentsHTTPSender.connectionTimeout);

        final Message msg = msgContext.getRequestMessage();
        if (msg != null){
            method.setHeader(HTTPConstants.HEADER_CONTENT_TYPE,
                             msg.getContentType(msgContext.getSOAPConstants()));
        }

        if (msgContext.useSOAPAction()) {
            // define SOAPAction header
            final String action = msgContext.getSOAPActionURI();
            if (action != null && !"".equals(action))
                method.setHeader(HTTPConstants.HEADER_SOAP_ACTION, "\"" + action + "\"");
        }

        String userID = msgContext.getUsername();
        String passwd = msgContext.getPassword();

        // if UserID is not part of the context, but is in the URL, use
        // the one in the URL.
        if ((userID == null) && (tmpURL.getUserInfo() != null)) {
            final String info = tmpURL.getUserInfo();
            final int sep     = info.indexOf(':');

            if ((sep >= 0) && (sep + 1 < info.length())) {
                userID = info.substring(0, sep);
                passwd = info.substring(sep + 1);
            } else {
                userID = info;
            }
        }
        if (userID != null) {

            Credentials proxyCred =
                new UsernamePasswordCredentials(userID,passwd);

            // if the username is in the form "user\domain"
            // then use NTCredentials instead.
            final int domainIndex = userID.indexOf("\\");
            if (domainIndex > 0) {
                final String domain = userID.substring(0, domainIndex);
                if (userID.length() > domainIndex + 1) {
                    final String user = userID.substring(domainIndex + 1);
                    proxyCred = new NTCredentials(user,
                                                  passwd,
                                                  NetworkUtils.getLocalHostname(), domain);
                }
            }
            final BasicCredentialsProvider bcp = new BasicCredentialsProvider();
            bcp.setCredentials(AuthScope.ANY, proxyCred);
            httpContext.setCredentialsProvider(bcp);
        }

        // add compression headers if needed
        if (msgContext.isPropertyTrue(HTTPConstants.MC_ACCEPT_GZIP)) {
            method.addHeader(HTTPConstants.HEADER_ACCEPT_ENCODING,
                             HTTPConstants.COMPRESSION_GZIP);
        }
        if (msgContext.isPropertyTrue(HTTPConstants.MC_GZIP_REQUEST)) {
            method.addHeader(HTTPConstants.HEADER_CONTENT_ENCODING,
                             HTTPConstants.COMPRESSION_GZIP);
        }

        // Transfer MIME headers of SOAPMessage to HTTP headers.
        final MimeHeaders mimeHeaders = msg != null ? msg.getMimeHeaders() : null;
        if (mimeHeaders != null) {
            for (@SuppressWarnings("unchecked") final Iterator<MimeHeader> i = mimeHeaders.getAllHeaders(); i.hasNext(); ) {
                final MimeHeader mimeHeader = i.next();
                //HEADER_CONTENT_TYPE and HEADER_SOAP_ACTION are already set.
                //Let's not duplicate them.
                final String headerName = mimeHeader.getName();
                if (headerName.equals(HTTPConstants.HEADER_CONTENT_TYPE)
                    || headerName.equals(HTTPConstants.HEADER_SOAP_ACTION)) {
                    continue;
                }
                method.addHeader(mimeHeader.getName(),
                                 mimeHeader.getValue());
            }
        }

        // process user defined headers for information.
        final MimeHeaders userHeaders =
            (MimeHeaders) msgContext.getProperty(HTTPConstants.REQUEST_HEADERS);

        if (userHeaders != null) {
            for (@SuppressWarnings("unchecked") final Iterator<MimeHeader> i = userHeaders.getAllHeaders(); i.hasNext(); ) {
                final MimeHeader mimeHeader = i.next();
                final String key   = mimeHeader.getName();
                final String value = mimeHeader.getValue();

                if (HTTPConstants.HEADER_EXPECT.equalsIgnoreCase(key) &&
                    HTTPConstants.HEADER_EXPECT_100_Continue.equalsIgnoreCase(value)) {
                    rcb.setExpectContinueEnabled(true);
                } else if (HTTPConstants.HEADER_TRANSFER_ENCODING_CHUNKED.equalsIgnoreCase(key)) {
                    if (null != value)  {
                        this.httpChunkStream = JavaUtils.isTrue(value);
                    }
                } else {
                    method.addHeader(key, value);
                }
            }
        }
    }

    /**
     * Get named response header
     *
     * @param resp HttpResponse
     * @param headerName name of the header to pick
     * @return
     */
    private String getHeader(final HttpResponse resp, final String headerName) {
        final Header header = resp.getFirstHeader(headerName);
        return (header == null) ? null : header.getValue().trim();
    }

    /**
     * An input stream that closes associated HTTP Client data.
     * This should do it even when the connection object gets dropped
     * due to out of memory condition.
     * <p>
     * SocketInputStream is marker for AXIS SOAPPart that
     * the stream is coming from a socket, and needs to be
     * closed at appropriate time.
     */
    private static class ReleasingFilterInputStream extends SocketInputStream {
        private HttpPost        post;
        private HttpResponse    resp;
        private LavercaHttpClient httpClient;

        /**
         *
         * @param httpClient
         * @param post
         * @param resp
         * @param is
         */
        public ReleasingFilterInputStream( final LavercaHttpClient httpClient,
                                           final HttpPost post,
                                           final HttpResponse resp,
                                           final InputStream is )
        {
            super(is, null);
            this.httpClient = httpClient;
            this.post       = post;
            this.resp       = resp;
        }

        @Override
        public synchronized void close() throws IOException {

            if (log.isTraceEnabled())
                log.trace("Close http response, and release post method connection");

            this.httpClient.closeQuietly(this.post, this.resp);

            super.close();
            log.trace(" .. super.close() done.");
        }
    }

    private static class MessageRequestEntity implements HttpEntity {

        private HttpPost method;
        private Message message;
        boolean httpChunkStream = true; //Use HTTP chunking or not.

        public MessageRequestEntity(final HttpPost method,
                                    final Message message) {
            this.message = message;
            this.method = method;
        }

        public MessageRequestEntity(final HttpPost method,
                                    final Message message,
                                    final boolean httpChunkStream) {
            this.message = message;
            this.method = method;
            this.httpChunkStream = httpChunkStream;
        }

        @Override
        public boolean isRepeatable() {
            return true;
        }

        protected boolean isContentLengthNeeded() {
            return this.method.getProtocolVersion() == HttpVersion.HTTP_1_0 || !this.httpChunkStream;
        }

        @Override
        public long getContentLength() {
            if (this.isContentLengthNeeded()) {
                try {
                    return this.message.getContentLength();
                } catch (Exception e) {
                    // ignored
                }
            }
            return -1; /* -1 for chunked */
        }

        @Override
        public Header getContentType() {
            return null; // a separate header is added
        }

        @Deprecated
        @Override
        public void consumeContent() throws IOException {
            EntityUtils.consume(this.method.getEntity());
        }

        @Override
        public InputStream getContent()
            throws IOException, IllegalStateException
        {
            return null;
        }

        @Override
        public Header getContentEncoding() {
            return null;
        }

        @Override
        public boolean isChunked() {
            return true;
        }

        @Override
        public boolean isStreaming() {
            return false;
        }

        @Override
        public void writeTo(final OutputStream out) throws IOException {
            try {
                this.message.writeTo(out);
            } catch (final Exception e) {
                throw new IOException(e.getMessage(), e);
            }
        }

    }

    private static class GzipMessageRequestEntity extends MessageRequestEntity {

        public GzipMessageRequestEntity(final HttpPost method,
                                        final Message message) {
            super(method, message);
        }

        public GzipMessageRequestEntity(final HttpPost method,
                                        final Message message,
                                        final boolean httpChunkStream) {
            super(method, message, httpChunkStream);
        }

        public void writeRequest(final OutputStream out) throws IOException {
            if (this.cachedStream != null) {
                this.cachedStream.writeTo(out);
            } else {
                final GZIPOutputStream gzStream = new GZIPOutputStream(out);
                super.writeTo(gzStream);
                gzStream.finish();
            }
        }

        @Override
        public long getContentLength() {
            if (this.isContentLengthNeeded()) {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    this.writeRequest(baos);
                    this.cachedStream = baos;
                    return baos.size();
                } catch (IOException e) {
                    // fall through to doing chunked.
                }
            }
            return -1; // do chunked
        }

        private ByteArrayOutputStream cachedStream;
    }

}
