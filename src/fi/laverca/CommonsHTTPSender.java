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
 * 
 * Modified by Eemeli Miettinen to use Commons HTTPComponents in Laverca
 *
 * Again modified by Matti Aarnio to fit in Kiuru MSSP core services.
 */

package fi.laverca;

import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;

import org.apache.axis.AxisFault;
import org.apache.axis.Constants;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.soap.SOAPConstants;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.axis.utils.JavaUtils;
import org.apache.axis.utils.Messages;
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
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


/**
 * A replacement of the default Axis Commons HTTP sender that makes it
 * possible to share a connection manager among RoamingClient instances.
 *
 * @author Eemeli Miettinen (eemeli@methics.fi)
 * @author Matti Aarnio (matti@methics.fi)
 */
public class CommonsHTTPSender extends BasicHandler {
    
    private static Log log = LogFactory.getLog(CommonsHTTPSender.class);
    
    private static int connectionTimeout = 30000; // At most 30 seconds to wait for a socket connection to form
    
    private static final ThreadLocal<HttpClient> settings = new ThreadLocal<HttpClient>();
    
    /**
     * 
     * @param cm
     * @param clientProperties
     * @param sslSocketFactory if null then using default (new SSLProtocolSocketFactory();).
     * @param proxySettings
     */
    public static void initThreadLocals( HttpClient hc )
    {
        log.debug("initThreadLocals()");
        log.debug("  sslSocketFactory = "+hc);
        
        settings.set(hc);
    }
    
    boolean httpChunkStream = true; //Use HTTP chunking or not.
    
    public CommonsHTTPSender() {
    }
    
    /**
     * invoke creates a socket connection, sends the request SOAP message and then
     * reads the response SOAP message back from the SOAP server
     *
     * @param msgContext the messsage context
     *
     * @throws AxisFault
     */
    @Override
    public void invoke(MessageContext msgContext)
            throws AxisFault
            {
        // Sometimes there could be use for HttpGet in SOAP. Maybe.
        // We don't support it at the moment, but see AXIS 1.4:
        /*
            boolean posting = true;
            
            // If we're SOAP 1.2, allow the web method to be set from the
            // MessageContext.
            if (msgContext.getSOAPConstants() == SOAPConstants.SOAP12_CONSTANTS) {
                String webMethod = msgContext.getStrProp(SOAP12Constants.PROP_WEBMETHOD);
                if (webMethod != null) {
                    posting = webMethod.equals(HTTPConstants.HEADER_POST);
                }
            }

            HttpBaseMethod method = null;
            if (posting) {
              method = new HttpPost(targetURL.toString());
            } else {
              method = new HttpGet(targetURL.toString());
            }
            ..etc..
         */
        
        HttpPost post = null;
        HttpResponse response = null;
        if (log.isDebugEnabled()) {
            log.debug(Messages.getMessage("enter00",
                    "CommonsHTTPSender::invoke"));
        }
        try {
            
            HttpClient httpClient = settings.get();
            
            URL targetURL =
                    new URL(msgContext.getStrProp(MessageContext.TRANS_URL));
            
            
            Message reqMessage = msgContext.getRequestMessage();
            post = new HttpPost(targetURL.toString());
            
            // set false as default, addContentInfo can overwrite
            HttpParams params = post.getParams();
            HttpProtocolParams.setUseExpectContinue(params, false);
            
            addContextInfo(post, httpClient, msgContext, targetURL);
            
            MessageRequestEntity requestEntity = null;
            if (msgContext.isPropertyTrue(HTTPConstants.MC_GZIP_REQUEST)) {
                requestEntity = new GzipMessageRequestEntity(post, reqMessage, httpChunkStream);
            } else {
                requestEntity = new MessageRequestEntity(post, reqMessage, httpChunkStream);
            }
            post.setEntity(requestEntity);
            
            String httpVersion =
                    msgContext.getStrProp(MessageContext.HTTP_TRANSPORT_VERSION);
            if (httpVersion != null) {
                if (httpVersion.equals(HTTPConstants.HEADER_PROTOCOL_V10)) {
                    params.setParameter(httpVersion, HttpVersion.HTTP_1_0);
                }
            }
            
            HttpContext localContext = new BasicHttpContext();
            
            response = httpClient.execute(post, localContext);
            int returnCode = response.getStatusLine().getStatusCode();
            
            String contentType     = getHeader(post, HTTPConstants.HEADER_CONTENT_TYPE);
            String contentLocation = getHeader(post, HTTPConstants.HEADER_CONTENT_LOCATION);
            String contentLength   = getHeader(post, HTTPConstants.HEADER_CONTENT_LENGTH);
            
            if ((returnCode > 199) && (returnCode < 300)) {
                
                // SOAP return is OK - so fall through
            } else if (msgContext.getSOAPConstants() ==
                    SOAPConstants.SOAP12_CONSTANTS) {
                // For now, if we're SOAP 1.2, fall through, since the range of
                // valid result codes is much greater
            } else if ((contentType != null) && !contentType.equals("text/html")
                    && ((returnCode > 499) && (returnCode < 600))) {
                
                // SOAP Fault should be in here - so fall through
            } else {
                String statusMessage = response.getStatusLine().getReasonPhrase();
                AxisFault fault = new AxisFault("HTTP",
                        "(" + returnCode + ")"
                                + statusMessage, null,
                                null);
                try {
                    String body = getResponseBodyAsString(response);
                    fault.setFaultDetailString(Messages.getMessage("return01",
                            "" + returnCode,
                            body));
                    fault.addFaultDetail(Constants.QNAME_FAULTDETAIL_HTTPERRORCODE,
                            Integer.toString(returnCode));
                    throw fault;
                } finally {
                    HttpClientUtils.closeQuietly(response);
                    post.releaseConnection();
                }
            }
            
            // After this phase, the response and post are NOT to be closed/released
            // in this code path! See comments further below at "AXIS closure processing rules"
            
            // Wrap the response body stream so that close() also releases
            // the connection back to the pool.
            InputStream releaseConnectionOnCloseStream =
                    createConnectionReleasingInputStream(post, response);
            
            Header contentEncoding =
                    response.getFirstHeader(HTTPConstants.HEADER_CONTENT_ENCODING);
            if (contentEncoding != null) {
                if (contentEncoding.getValue().
                        equalsIgnoreCase(HTTPConstants.COMPRESSION_GZIP)) {
                    releaseConnectionOnCloseStream =
                            new GZIPInputStream(releaseConnectionOnCloseStream);
                } else {
                    try {
                        releaseConnectionOnCloseStream.close();
                    } catch (Throwable t) {
                        // ignore
                    }
                    throw new AxisFault("HTTP",
                            "unsupported content-encoding of '"
                                    + contentEncoding.getValue()
                                    + "' found", null, null);
                }
            }
            Message outMsg = new Message(releaseConnectionOnCloseStream,
                    false, contentType, contentLocation);
            // Transfer HTTP headers of HTTP message to MIME headers of SOAP message
            Header[] responseHeaders = post.getAllHeaders();
            MimeHeaders responseMimeHeaders = outMsg.getMimeHeaders();
            for (int i = 0; i < responseHeaders.length; i++) {
                Header responseHeader = responseHeaders[i];
                responseMimeHeaders.addHeader(responseHeader.getName(),
                        responseHeader.getValue());
            }
            outMsg.setMessageType(Message.RESPONSE);
            msgContext.setResponseMessage(outMsg);
            if (log.isDebugEnabled()) {
                if (null == contentLength) {
                    log.debug("\n"
                            + Messages.getMessage("no00", "Content-Length"));
                }
                log.debug("\n" + Messages.getMessage("xmlRecd00"));
                log.debug("-----------------------------------------------");
                log.debug(outMsg.getSOAPPartAsString());
            }
            
        } catch (Exception e) {
            log.debug(e);
            throw AxisFault.makeFault(e);
            
        } finally {
            
            // AXIS closure processing rules..
            //
            // 1: Always release the connection back to the pool
            //    IF it was ONE WAY invocation
            
            if (msgContext.isPropertyTrue("axis.one.way")) {
                HttpClientUtils.closeQuietly(response);
                if (post != null) {
                    post.releaseConnection();
                }
            } else {
                log.debug("A HTTP POST which did NOT plan to release the HTTP connection back to the pool");
            }
            
            // 2: Otherwise the Axis machinery will process call
            //    close() on the releaseConnectionOnCloseStream.
        }
        
        if (log.isDebugEnabled()) {
            log.debug(Messages.getMessage("exit00",
                    "CommonsHTTPSender::invoke"));
        }
            }
    
    
    /**
     * Extracts info from message context.
     *
     * @param method Post method
     * @param httpClient The client used for posting
     * @param msgContext the message context
     * @param tmpURL the url to post to.
     *
     * @throws Exception
     */
    private void addContextInfo(HttpPost method,
            HttpClient httpClient,
            MessageContext msgContext,
            URL tmpURL)
                    throws Exception {
        
        HttpParams params = method.getParams();
        
        if (msgContext.getTimeout() != 0) {
            // optionally set a timeout for response waits
            HttpConnectionParams.setSoTimeout(params, msgContext.getTimeout());
        }
        
        // Always set the 30 second timeout on establishing the connection
        HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
        
        
        Message msg = msgContext.getRequestMessage();
        if (msg != null){
            method.setHeader(HTTPConstants.HEADER_CONTENT_TYPE,
                    msg.getContentType(msgContext.getSOAPConstants()));
        }
        
        if (msgContext.useSOAPAction()) {
            // define SOAPAction header
            String action = msgContext.getSOAPActionURI();
            if (action != null && !"".equals(action))
                method.setHeader(HTTPConstants.HEADER_SOAP_ACTION, "\"" + action + "\"");
        }
        
        String userID = msgContext.getUsername();
        String passwd = msgContext.getPassword();
        
        // if UserID is not part of the context, but is in the URL, use
        // the one in the URL.
        if ((userID == null) && (tmpURL.getUserInfo() != null)) {
            String info = tmpURL.getUserInfo();
            int sep = info.indexOf(':');
            
            if ((sep >= 0) && (sep + 1 < info.length())) {
                userID = info.substring(0, sep);
                passwd = info.substring(sep + 1);
            } else {
                userID = info;
            }
        }
        if (userID != null) {
            Credentials proxyCred =
                    new UsernamePasswordCredentials(userID,
                            passwd);
            // if the username is in the form "user\domain"
            // then use NTCredentials instead.
            int domainIndex = userID.indexOf("\\");
            if (domainIndex > 0) {
                String domain = userID.substring(0, domainIndex);
                if (userID.length() > domainIndex + 1) {
                    String user = userID.substring(domainIndex + 1);
                    proxyCred = new NTCredentials(user,
                            passwd,
                            NetworkUtils.getLocalHostname(), domain);
                }
            }
            ((DefaultHttpClient)httpClient).getCredentialsProvider().setCredentials(AuthScope.ANY, proxyCred);
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
        MimeHeaders mimeHeaders = msg.getMimeHeaders();
        if (mimeHeaders != null) {
            for (Iterator i = mimeHeaders.getAllHeaders(); i.hasNext(); ) {
                MimeHeader mimeHeader = (MimeHeader) i.next();
                //HEADER_CONTENT_TYPE and HEADER_SOAP_ACTION are already set.
                //Let's not duplicate them.
                String headerName = mimeHeader.getName();
                if (headerName.equals(HTTPConstants.HEADER_CONTENT_TYPE)
                        || headerName.equals(HTTPConstants.HEADER_SOAP_ACTION)) {
                    continue;
                }
                method.addHeader(mimeHeader.getName(),
                        mimeHeader.getValue());
            }
        }
        
        // process user defined headers for information.
        Hashtable userHeaderTable =
                (Hashtable) msgContext.getProperty(HTTPConstants.REQUEST_HEADERS);
        
        if (userHeaderTable != null) {
            for (Iterator e = userHeaderTable.entrySet().iterator();
                    e.hasNext();) {
                Map.Entry me = (Map.Entry) e.next();
                Object keyObj = me.getKey();
                
                if (null == keyObj) {
                    continue;
                }
                String key = keyObj.toString().trim();
                String value = me.getValue().toString().trim();
                
                if (key.equalsIgnoreCase(HTTPConstants.HEADER_EXPECT) &&
                        value.equalsIgnoreCase(HTTPConstants.HEADER_EXPECT_100_Continue)) {
                    HttpProtocolParams.setUseExpectContinue(params, true);
                } else if (key.equalsIgnoreCase(HTTPConstants.HEADER_TRANSFER_ENCODING_CHUNKED)) {
                    String val = me.getValue().toString();
                    if (null != val)  {
                        httpChunkStream = JavaUtils.isTrue(val);
                    }
                } else {
                    method.addHeader(key, value);
                }
            }
        }
    }
    
    private static String getResponseBodyAsString( final HttpResponse resp )
        throws IOException
    {
        HttpEntity ent = resp.getEntity();
        if (ent == null)
            throw new IOException();
        try {
            return EntityUtils.toString(ent, "UTF-8");
        } catch (IOException e) {
            throw e;
        } catch (Exception e) { // org.apache.http.ParseException
            throw new IOException(e);
        }
    }
    
    private static String getHeader(HttpPost method, String headerName) {
        Header header = method.getFirstHeader(headerName);
        return (header == null) ? null : header.getValue().trim();
    }
    
    private InputStream createConnectionReleasingInputStream( final HttpPost post,
            final HttpResponse response )
                    throws IOException
                    {
        return new FilterInputStream(response.getEntity().getContent()) {
            @Override
            public void close() throws IOException {
                if (log.isDebugEnabled())
                    log.debug("Close http response, and release post method connection");
                try {
                    super.close();
                } finally {
                    HttpClientUtils.closeQuietly(response);
                    post.releaseConnection();
                }
            }
        };
                    }
    
    @SuppressWarnings("deprecation")
    private static class MessageRequestEntity implements HttpEntity {
        
        private HttpPost method;
        private Message message;
        boolean httpChunkStream = true; //Use HTTP chunking or not.
        
        public MessageRequestEntity(HttpPost method, Message message) {
            this.message = message;
            this.method = method;
        }
        
        public MessageRequestEntity(HttpPost method, Message message, boolean httpChunkStream) {
            this.message = message;
            this.method = method;
            this.httpChunkStream = httpChunkStream;
        }
        
        @Override
        public boolean isRepeatable() {
            return true;
        }
        
        protected boolean isContentLengthNeeded() {
            return this.method.getProtocolVersion() == HttpVersion.HTTP_1_0 || !httpChunkStream;
        }
        
        @Override
        public long getContentLength() {
            if (isContentLengthNeeded()) {
                try {
                    return message.getContentLength();
                } catch (Exception e) {
                }
            }
            return -1; /* -1 for chunked */
        }
        
        @Override
        public Header getContentType() {
            return null; // a separate header is added
        }
        
        @Override
        public void consumeContent() throws IOException {
            EntityUtils.consume(method.getEntity());
        }
        
        @Override
        public InputStream getContent() throws IOException,
        IllegalStateException {
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
        public void writeTo(OutputStream out) throws IOException {
            try {
                this.message.writeTo(out);
            } catch (SOAPException e) {
                throw new IOException(e.getMessage());
            }
        }
        
    }
    
    private static class GzipMessageRequestEntity extends MessageRequestEntity {
        
        public GzipMessageRequestEntity(HttpPost method, Message message) {
            super(method, message);
        }
        
        public GzipMessageRequestEntity(HttpPost method, Message message, boolean httpChunkStream) {
            super(method, message, httpChunkStream);
        }
        
        public void writeRequest(OutputStream out) throws IOException {
            if (cachedStream != null) {
                cachedStream.writeTo(out);
            } else {
                GZIPOutputStream gzStream = new GZIPOutputStream(out);
                super.writeTo(gzStream);
                gzStream.finish();
            }
        }
        
        @Override
        public long getContentLength() {
            if(isContentLengthNeeded()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    writeRequest(baos);
                    cachedStream = baos;
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
