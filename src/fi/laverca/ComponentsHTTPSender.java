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
*/


package fi.laverca;

import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.net.ssl.SSLContext;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;

import org.apache.axis.AxisFault;
import org.apache.axis.Constants;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.components.logger.LogFactory;
import org.apache.axis.components.net.CommonsHTTPClientProperties;
import org.apache.axis.components.net.CommonsHTTPClientPropertiesFactory;
import org.apache.axis.components.net.TransportClientProperties;
import org.apache.axis.components.net.TransportClientPropertiesFactory;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.soap.SOAPConstants;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.axis.utils.JavaUtils;
import org.apache.axis.utils.Messages;
import org.apache.axis.utils.NetworkUtils;
import org.apache.commons.logging.Log;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author Eemeli Miettinen (eemeli@methics.fi)
 */
public class ComponentsHTTPSender extends BasicHandler {
    
    /** Field log           */
    protected static Log log =
        LogFactory.getLog(ComponentsHTTPSender.class.getName());
    
    protected PoolingClientConnectionManager connectionManager;
    protected CommonsHTTPClientProperties clientProperties;
    boolean httpChunkStream = true; //Use HTTP chunking or not.

    public ComponentsHTTPSender() {
        initialize();
    }

    protected void initialize() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        
        // Use the system properties for initializing the SSL
        registry.register(new Scheme("https", 443, SSLSocketFactory.getSystemSocketFactory()));

        
        this.clientProperties = CommonsHTTPClientPropertiesFactory.create();      
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager(registry, this.clientProperties.getDefaultConnectionTimeout(), TimeUnit.MILLISECONDS);
        
        cm.setDefaultMaxPerRoute(clientProperties.getMaximumConnectionsPerHost());
        cm.setMaxTotal(clientProperties.getMaximumTotalConnections());

        this.connectionManager = cm;
    }
    
    /**
     * invoke creates a socket connection, sends the request SOAP message and then
     * reads the response SOAP message back from the SOAP server
     *
     * @param msgContext the messsage context
     *
     * @throws AxisFault
     */
    public void invoke(MessageContext msgContext) throws AxisFault {
        HttpPost post = null;
        if (log.isDebugEnabled()) {
            log.debug(Messages.getMessage("enter00",
                                          "CommonsHTTPSender::invoke"));
        }
        try {
            URL targetURL =
                new URL(msgContext.getStrProp(MessageContext.TRANS_URL));
            
            DefaultHttpClient httpClient = new DefaultHttpClient(this.connectionManager);           
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, this.clientProperties.getConnectionPoolTimeout());
            
            HttpHost hostConfiguration = 
            getHostConfiguration(httpClient, msgContext, targetURL);
        

            Message reqMessage = msgContext.getRequestMessage();
            post = new HttpPost(targetURL.toString());

            // set false as default, addContetInfo can overwrite
            post.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE,
                                                   false);
            
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
                    post.getParams().setParameter(httpVersion, HttpVersion.HTTP_1_0);
                }
            }
            
            HttpContext localContext = new BasicHttpContext();
            
            
            
            HttpResponse response = httpClient.execute(hostConfiguration, post, localContext);
            int returnCode = response.getStatusLine().getStatusCode();
            
            String contentType = 
                getHeader(post, HTTPConstants.HEADER_CONTENT_TYPE);
            String contentLocation = 
                getHeader(post, HTTPConstants.HEADER_CONTENT_LOCATION);
            String contentLength = 
                getHeader(post, HTTPConstants.HEADER_CONTENT_LENGTH);

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
                    fault.setFaultDetailString(
                         Messages.getMessage("return01",
                                             "" + returnCode,
                                             response.getStatusLine().getReasonPhrase())); //TODO: response.getResponseBodyAsString()));
                    fault.addFaultDetail(Constants.QNAME_FAULTDETAIL_HTTPERRORCODE,
                                         Integer.toString(returnCode));
                    throw fault;
                } finally {
                    post.releaseConnection(); // release connection back to pool.
                }
            }
            
            // wrap the response body stream so that close() also releases
            // the connection back to the pool.
            InputStream releaseConnectionOnCloseStream = 
                createConnectionReleasingInputStream(response);

            Header contentEncoding = 
            	response.getFirstHeader(HTTPConstants.HEADER_CONTENT_ENCODING);
            if (contentEncoding != null) {
            	if (contentEncoding.getValue().
            			equalsIgnoreCase(HTTPConstants.COMPRESSION_GZIP)) {
            		releaseConnectionOnCloseStream = 
            			new GZIPInputStream(releaseConnectionOnCloseStream);
            	} else {
                    AxisFault fault = new AxisFault("HTTP",
                            "unsupported content-encoding of '" 
                    		+ contentEncoding.getValue()
                            + "' found", null, null);
                    throw fault;
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

            // always release the connection back to the pool if 
            // it was one way invocation
            if (msgContext.isPropertyTrue("axis.one.way")) {
                post.releaseConnection();
            }
            
        } catch (Exception e) {
            log.debug(e);
            throw AxisFault.makeFault(e);
        }
        
        if (log.isDebugEnabled()) {
            log.debug(Messages.getMessage("exit00",
                                          "CommonsHTTPSender::invoke"));
        }
    }
    
    protected HttpHost getHostConfiguration(DefaultHttpClient client, 
                                                     MessageContext context,
                                                     URL targetURL) {
        TransportClientProperties tcp = 
            TransportClientPropertiesFactory.create(targetURL.getProtocol()); // http or https
        int port = targetURL.getPort();
        boolean hostInNonProxyList =
            isHostInNonProxyList(targetURL.getHost(), tcp.getNonProxyHosts());
        
        HttpHost config = new HttpHost(targetURL.getHost(), port, targetURL.getProtocol());
        
        if (port == -1) {
        	if(targetURL.getProtocol().equalsIgnoreCase("https")) {
        		port = 443;		// default port for https being 443
        	} else { // it must be http
        		port = 80;		// default port for http being 80
        	}
        }
        
        if(hostInNonProxyList){
            config = new HttpHost(targetURL.getHost(), port, targetURL.getProtocol());
        } else {
            if (tcp.getProxyHost().length() == 0 ||
                tcp.getProxyPort().length() == 0) {
                config = new HttpHost(targetURL.getHost(), port, targetURL.getProtocol());
            } else {
                if (tcp.getProxyUser().length() != 0) {
                    Credentials proxyCred = 
                        new UsernamePasswordCredentials(tcp.getProxyUser(),
                                                        tcp.getProxyPassword());
                    // if the username is in the form "user\domain" 
                    // then use NTCredentials instead.
                    int domainIndex = tcp.getProxyUser().indexOf("\\");
                    if (domainIndex > 0) {
                        String domain = tcp.getProxyUser().substring(0, domainIndex);
                        if (tcp.getProxyUser().length() > domainIndex + 1) {
                            String user = tcp.getProxyUser().substring(domainIndex + 1);
                            proxyCred = new NTCredentials(user,
                                            tcp.getProxyPassword(),
                                            tcp.getProxyHost(), domain);
                        }
                    }
                    client.getCredentialsProvider().setCredentials(AuthScope.ANY, proxyCred);
                }
                int proxyPort = new Integer(tcp.getProxyPort()).intValue();
                config = new HttpHost(tcp.getProxyHost(), proxyPort);
                //config.setProxy(tcp.getProxyHost(), proxyPort);
            }
        }

        return config;
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
                                DefaultHttpClient httpClient, 
                                MessageContext msgContext, 
                                URL tmpURL)
        throws Exception {
        
        // optionally set a timeout for the request
        if (msgContext.getTimeout() != 0) {
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, msgContext.getTimeout());
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, msgContext.getTimeout());

        }
        
        // Get SOAPAction, default to ""
        String action = msgContext.useSOAPAction()
            ? msgContext.getSOAPActionURI()
            : "";
        
        if (action == null) {
            action = "";
        }

        Message msg = msgContext.getRequestMessage();
        if (msg != null){
            method.setHeader(new BasicHeader(HTTPConstants.HEADER_CONTENT_TYPE,
                                               msg.getContentType(msgContext.getSOAPConstants())));
        }

        method.setHeader(new BasicHeader(HTTPConstants.HEADER_SOAP_ACTION, "\"" + action + "\""));
        
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
            httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, proxyCred);
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
                    method.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE,
                                                           true);
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
    
    /**
     * Check if the specified host is in the list of non proxy hosts.
     *
     * @param host host name
     * @param nonProxyHosts string containing the list of non proxy hosts
     *
     * @return true/false
     */
    protected boolean isHostInNonProxyList(String host, String nonProxyHosts) {
        
        if ((nonProxyHosts == null) || (host == null)) {
            return false;
        }
        
        /*
         * The http.nonProxyHosts system property is a list enclosed in
         * double quotes with items separated by a vertical bar.
         */
        StringTokenizer tokenizer = new StringTokenizer(nonProxyHosts, "|\"");
        
        while (tokenizer.hasMoreTokens()) {
            String pattern = tokenizer.nextToken();
            
            if (log.isDebugEnabled()) {
                log.debug(Messages.getMessage("match00",
                new String[]{"HTTPSender",
                host,
                pattern}));
            }
            if (match(pattern, host, false)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Matches a string against a pattern. The pattern contains two special
     * characters:
     * '*' which means zero or more characters,
     *
     * @param pattern the (non-null) pattern to match against
     * @param str     the (non-null) string that must be matched against the
     *                pattern
     * @param isCaseSensitive
     *
     * @return <code>true</code> when the string matches against the pattern,
     *         <code>false</code> otherwise.
     */
    protected static boolean match(String pattern, String str,
                                   boolean isCaseSensitive) {
        
        char[] patArr = pattern.toCharArray();
        char[] strArr = str.toCharArray();
        int patIdxStart = 0;
        int patIdxEnd = patArr.length - 1;
        int strIdxStart = 0;
        int strIdxEnd = strArr.length - 1;
        char ch;
        boolean containsStar = false;
        
        for (int i = 0; i < patArr.length; i++) {
            if (patArr[i] == '*') {
                containsStar = true;
                break;
            }
        }
        if (!containsStar) {
            
            // No '*'s, so we make a shortcut
            if (patIdxEnd != strIdxEnd) {
                return false;        // Pattern and string do not have the same size
            }
            for (int i = 0; i <= patIdxEnd; i++) {
                ch = patArr[i];
                if (isCaseSensitive && (ch != strArr[i])) {
                    return false;    // Character mismatch
                }
                if (!isCaseSensitive
                && (Character.toUpperCase(ch)
                != Character.toUpperCase(strArr[i]))) {
                    return false;    // Character mismatch
                }
            }
            return true;             // String matches against pattern
        }
        if (patIdxEnd == 0) {
            return true;    // Pattern contains only '*', which matches anything
        }
        
        // Process characters before first star
        while ((ch = patArr[patIdxStart]) != '*'
        && (strIdxStart <= strIdxEnd)) {
            if (isCaseSensitive && (ch != strArr[strIdxStart])) {
                return false;    // Character mismatch
            }
            if (!isCaseSensitive
            && (Character.toUpperCase(ch)
            != Character.toUpperCase(strArr[strIdxStart]))) {
                return false;    // Character mismatch
            }
            patIdxStart++;
            strIdxStart++;
        }
        if (strIdxStart > strIdxEnd) {
            
            // All characters in the string are used. Check if only '*'s are
            // left in the pattern. If so, we succeeded. Otherwise failure.
            for (int i = patIdxStart; i <= patIdxEnd; i++) {
                if (patArr[i] != '*') {
                    return false;
                }
            }
            return true;
        }
        
        // Process characters after last star
        while ((ch = patArr[patIdxEnd]) != '*' && (strIdxStart <= strIdxEnd)) {
            if (isCaseSensitive && (ch != strArr[strIdxEnd])) {
                return false;    // Character mismatch
            }
            if (!isCaseSensitive
            && (Character.toUpperCase(ch)
            != Character.toUpperCase(strArr[strIdxEnd]))) {
                return false;    // Character mismatch
            }
            patIdxEnd--;
            strIdxEnd--;
        }
        if (strIdxStart > strIdxEnd) {
            
            // All characters in the string are used. Check if only '*'s are
            // left in the pattern. If so, we succeeded. Otherwise failure.
            for (int i = patIdxStart; i <= patIdxEnd; i++) {
                if (patArr[i] != '*') {
                    return false;
                }
            }
            return true;
        }
        
        // process pattern between stars. padIdxStart and patIdxEnd point
        // always to a '*'.
        while ((patIdxStart != patIdxEnd) && (strIdxStart <= strIdxEnd)) {
            int patIdxTmp = -1;
            
            for (int i = patIdxStart + 1; i <= patIdxEnd; i++) {
                if (patArr[i] == '*') {
                    patIdxTmp = i;
                    break;
                }
            }
            if (patIdxTmp == patIdxStart + 1) {
                
                // Two stars next to each other, skip the first one.
                patIdxStart++;
                continue;
            }
            
            // Find the pattern between padIdxStart & padIdxTmp in str between
            // strIdxStart & strIdxEnd
            int patLength = (patIdxTmp - patIdxStart - 1);
            int strLength = (strIdxEnd - strIdxStart + 1);
            int foundIdx = -1;
            
            strLoop:
                for (int i = 0; i <= strLength - patLength; i++) {
                    for (int j = 0; j < patLength; j++) {
                        ch = patArr[patIdxStart + j + 1];
                        if (isCaseSensitive
                        && (ch != strArr[strIdxStart + i + j])) {
                            continue strLoop;
                        }
                        if (!isCaseSensitive && (Character
                        .toUpperCase(ch) != Character
                        .toUpperCase(strArr[strIdxStart + i + j]))) {
                            continue strLoop;
                        }
                    }
                    foundIdx = strIdxStart + i;
                    break;
                }
                if (foundIdx == -1) {
                    return false;
                }
                patIdxStart = patIdxTmp;
                strIdxStart = foundIdx + patLength;
        }
        
        // All characters in the string are used. Check if only '*'s are left
        // in the pattern. If so, we succeeded. Otherwise failure.
        for (int i = patIdxStart; i <= patIdxEnd; i++) {
            if (patArr[i] != '*') {
                return false;
            }
        }
        return true;
    }

    private static String getHeader(HttpPost method, String headerName) {
        Header header = method.getFirstHeader(headerName);
        return (header == null) ? null : header.getValue().trim();
    }

    private InputStream createConnectionReleasingInputStream(final HttpResponse response) throws IOException {
        return new FilterInputStream(response.getEntity().getContent()) {
                public void close() throws IOException {
                    try {
                        super.close();
                    } finally {
//                        response.releaseConnection();
                    }
                }
            };
    }

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

        public boolean isRepeatable() {
            return true;
        }

        protected boolean isContentLengthNeeded() {
        	return this.method.getProtocolVersion() == HttpVersion.HTTP_1_0 || !httpChunkStream;
        }
        
        public long getContentLength() {
            if (isContentLengthNeeded()) {
                try {
                    return message.getContentLength();
                } catch (Exception e) {
                }
            } 
            return -1; /* -1 for chunked */
        }

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

