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
 */


package fi.laverca.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import org.apache.axis.components.logger.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * Facade class for data used in setting up HttpClient 4.x clients + client constructor.
 * <p>
 * There are several tool methods that could be static, but are chosen to be class instance
 * dependent because the experience is that updates to the HttpClient library need more and
 * more of these facade methods to do the business without distributed code changes all over
 * the places.
 */

public class LavercaHttpClient {

    private static final Log log = LogFactory.getLog(LavercaHttpClient.class);

    private final PoolingHttpClientConnectionManager  connectionManager;
    /** What authentication scheme? Currently only BasicAuth: */
    private final UsernamePasswordCredentials         targetAuthCredentials;
    /** What authentication scheme? Currently only BasicAuth: */
    private final AuthScheme                          targetAuthScheme;
    private final HttpHost                            proxyHost;
    private final AuthScope                           proxyAuthScope;
    private final UsernamePasswordCredentials         proxyAuthCredentials;
    private final CloseableHttpClient                 client;
    private final CredentialsProvider                 credentialsProvider;
    private final AuthCache                           authCache;
    private final RequestConfig.Builder               requestConfigBuilder;
    private final HttpClientContext                   httpClientContext;

    private final String    poolUrl;
    private final String    username;
    private final String    password;


    /**
     * Use this method when you know the exact target URL.
     *
     * @param poolUrl target URL. 
     * @param newPoolSize connection pool size. 10 is good.
     * @param newConnTimeout connection timeout in millis. 30000 is 30 sec.
     * @param newSoTimeout protocol timeout in millis. 300000 is 300 sec / 5 minutes.
     * @param newUsername if set, use this to authenticate to the remote service. Use null to not authenticate.
     * @param newPassword see user.
     * @param proxySettings Optional proxy parameters, null for none.
     * @param ssf SSL Socket factory with specific client certificate and key, null for use of system wide default.
     *
     */
    public LavercaHttpClient( final String poolUrl,
                              final int newPoolSize,
                              final int newConnTimeout,
                              final int newSoTimeout,
                              final String newUsername,
                              final String newPassword,
                              final ProxySettings proxySettings,
                              final SSLSocketFactory ssf )
    {
        if (log.isTraceEnabled()) {
            log.trace("Creating HttpClient instance...");
            log.trace("  URL " + poolUrl);
        }

        this.poolUrl  = poolUrl;
        this.username = newUsername;
        this.password = newPassword;
        
        // Multiple threads (workers) share the HTTP connection pool
        final RegistryBuilder<ConnectionSocketFactory> rb = RegistryBuilder.create();
        rb.register("http", PlainConnectionSocketFactory.getSocketFactory());
        
        // Use the system properties for initializing the HTTPS

        final HostnameVerifier hnv = org.apache.http.conn.ssl.NoopHostnameVerifier.INSTANCE;
        rb.register("https", new SSLConnectionSocketFactory(ssf, hnv));

        // HttpClient keeps outbound client connections in a pool waiting for re-use.
        // Lifetime of that pool in seconds is:
        final int connPoolTTL = 300;
        
        this.connectionManager = new PoolingHttpClientConnectionManager(rb.build(), null, null, null, connPoolTTL, TimeUnit.SECONDS);
        this.connectionManager.setMaxTotal(newPoolSize);
        this.connectionManager.setDefaultMaxPerRoute(newPoolSize);


        final HttpClientBuilder hcb = HttpClientBuilder.create();
        hcb.setConnectionManager(this.connectionManager);

        // A single instance of connection manager servers a single
        // target and thus the values are equal.

        this.requestConfigBuilder = RequestConfig.custom();
        this.requestConfigBuilder.setExpectContinueEnabled(true);

        if (newConnTimeout > 0) {
            this.requestConfigBuilder.setConnectTimeout(newConnTimeout);
        }

        if (newSoTimeout > 0) {
            this.requestConfigBuilder.setSocketTimeout(newSoTimeout);
        }
        
        final ConnectionReuseStrategy     connReuse = new DefaultConnectionReuseStrategy();
        final ConnectionKeepAliveStrategy keepaliveStrategy = new DefaultConnectionKeepAliveStrategy(); 

        hcb.setConnectionReuseStrategy(connReuse);
        hcb.setKeepAliveStrategy(keepaliveStrategy);

        if (proxySettings != null) {

            if (proxySettings.proxyHostName != null && proxySettings.proxyHostName.length() > 0) {
                if (log.isTraceEnabled()) {
                    log.trace("Using a proxy. "+proxySettings.proxyHostName+ " : " + proxySettings.proxyPort);
                }
                this.proxyHost = new HttpHost(proxySettings.proxyHostName, proxySettings.proxyPort);

                if (proxySettings.proxyUsername != null && proxySettings.proxyUsername.length() > 0) {
                    if (log.isTraceEnabled()) {
                        log.trace("Setting up basic proxy authentication. "+proxySettings.proxyUsername+ " : " + proxySettings.proxyPassword);
                    }
                
                    this.proxyAuthScope = new AuthScope( proxySettings.proxyHostName, proxySettings.proxyPort );
                    this.proxyAuthCredentials = new UsernamePasswordCredentials( proxySettings.proxyUsername, proxySettings.proxyPassword );

                } else {
                    this.proxyAuthScope = null;
                    this.proxyAuthCredentials = null;
                    log.trace("Not defining proxy authentication.");
                }
            } else {
                this.proxyHost = null;
                this.proxyAuthScope = null;
                this.proxyAuthCredentials = null;
                log.trace("Not defining a proxy.");
            }
        } else {
            this.proxyHost = null;
            this.proxyAuthScope = null;
            this.proxyAuthCredentials = null;
            log.trace("Not defining a proxy.");
        }

        if (newUsername != null && newUsername.length() > 0) {
            if (log.isTraceEnabled()) {
                log.trace("Setting up basic server authentication. "+newUsername+ " : " + newPassword);
            }
            this.targetAuthCredentials = new UsernamePasswordCredentials(newUsername, newPassword);
            this.targetAuthScheme = new BasicScheme();
        } else {
            this.targetAuthCredentials = null;
            this.targetAuthScheme = null;
        }

        final boolean needCredentialsProvider = 
                (this.targetAuthCredentials != null || this.proxyAuthCredentials != null);
        if (needCredentialsProvider) {
            this.credentialsProvider = new BasicCredentialsProvider();
        } else {
            this.credentialsProvider = null;
        }

        // Credential setting is done at HttpClientContext of 4.4.1

        if (this.targetAuthCredentials != null) {
            this.credentialsProvider.setCredentials(AuthScope.ANY, this.targetAuthCredentials);
        }
        if (this.proxyHost != null) {
            final DefaultProxyRoutePlanner rp = new DefaultProxyRoutePlanner(this.proxyHost);
            hcb.setRoutePlanner(rp);
            if (this.proxyAuthCredentials != null) {
                this.credentialsProvider.setCredentials(this.proxyAuthScope,
                                                        this.proxyAuthCredentials);
            }
        }

        // Pre-emptive authentication needs to know the target URL
        // It can be done with AuthCache mechanism, but requires
        // setting with HttpPost at getHttpGet()/getHttpPost() methods.
        this.authCache = new BasicAuthCache();

        // Construct the HttpClient
        this.client = hcb.build();

        // Note: Examples in HttpComponents tutorials (at least until 4.5.4) let
        //       it be understood that HttpClientContext is per request basis.
        //       Documentation does not say anything either way, but what really
        //       needs to be done is to use single shared context in between all
        //       call sessions wanting to use a shared pool.
        //
        //       This means that e.g. AUTH_CACHE is same among all calls.
        //       It works in this case, but is not an universal thing.
        
        this.httpClientContext = HttpClientContext.create();
        if (this.credentialsProvider  != null) this.httpClientContext.setCredentialsProvider(this.credentialsProvider);
        if (this.requestConfigBuilder != null) this.httpClientContext.setRequestConfig(this.requestConfigBuilder.build());
        this.httpClientContext.setAttribute(HttpClientContext.AUTH_CACHE, this.authCache);

        log.trace("done creating.");
    }

    public String getURL() {
        return this.poolUrl;
    }
    
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    
    /**
     * Convenience method for executing HttpClient operations with
     * externally supplied HttpContext
     * @param req HttpGet/HttpPost request object with URI inside it
     * @param ctx HttpContext
     * @return CloseableHttpResponse data
     * @throws ClientProtocolException if an error in the HTTP protocol occurs.
     * @throws IOException If the execution fails
     */
    public CloseableHttpResponse execute( final HttpUriRequest req, final HttpContext ctx )
        throws ClientProtocolException, IOException
    {
        if (req instanceof HttpPost) {
            log.info("Sending POST to " + req.getURI());
        }
        if (req instanceof HttpGet) {
            log.info("Sending GET to " + req.getURI());
        }
        return this.client.execute(req, ctx);
    }

    /**
     * Convenience method for executing HttpClient operations with
     * internally constructed HttpContext.
     * 
     * @param req HttpGet/HttpPost request object with URI inside it
     * @return CloseableHttpResponse data
     * @throws ClientProtocolException if an error in the HTTP protocol occurs.
     * @throws IOException If the execution fails
     */
    public CloseableHttpResponse execute( final HttpUriRequest req )
        throws ClientProtocolException, IOException
    {
        final HttpContext ctx = this.buildContext(); 
        return this.execute(req, ctx);
    }
    
    /**
     * Get initialized HttpContext.
     * This is shared in between all call invocations of the 
     * 
     * @return initialized HttpContext
     */
    public HttpClientContext buildContext() {
        return this.httpClientContext;
    }
    
    /**
     * Get Response body as InputStream
     * 
     * @param resp HTTP Response data
     * @return an InputStream for reading the body
     * @throws IOException Bad input parameters
     */
    public InputStream getResponseBodyAsStream( final CloseableHttpResponse resp )
        throws IOException
    {
        final HttpEntity ent = resp.getEntity();
        if (ent == null)
            throw new IOException();
        return ent.getContent();
    }

    /**
     * Get Response body as a String presuming the input to be UTF-8 unless response specifies charset.
     * 
     * @param resp HTTP Response data
     * @return A String containing the response body.
     * @throws IOException Bad input parameters
     */
    public String getResponseBodyAsString( final CloseableHttpResponse resp )
        throws IOException
    {
        final HttpEntity ent = resp.getEntity();
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


    /**
     * Get Response body as a byte[].
     * 
     * @param resp HTTP Response data
     * @return A byte[] containing the response
     * @throws IOException Bad input parameters
     */
    public byte[] getResponseBodyAsBytes( final CloseableHttpResponse resp )
        throws IOException
    {
        final HttpEntity ent = resp.getEntity();
        if (ent == null)
            throw new IOException();
        return EntityUtils.toByteArray(ent);
    }

    /**
     * Convenience method building and initializing HttpGet
     * 
     * @param _url Target URL, note: this is <b>not</b> the same one that class constructor used. 
     * @return result
     */
    public HttpGet buildHttpGet( final String _url ) {
        final HttpGet ret = new HttpGet(_url);

        // AuthCredentials, if any
        if (this.targetAuthCredentials != null) {
            try {
                URI uri = new URI(_url);
                final HttpHost hh = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
                this.authCache.put(hh, this.targetAuthScheme);
                final AuthScope as = new AuthScope(hh.getHostName(), hh.getPort(), hh.getSchemeName());
                this.credentialsProvider.setCredentials(as, this.targetAuthCredentials);
            } catch (URISyntaxException e) {
                log.debug("Unable to set auth credentials. URISyntaxException for URL: " + _url);
                log.trace("URISyntaxException", e);
            }
        }
        
        return ret;
    }

    /**
     * Convenience method to build HTTP GET with ProtocolVersion HTTP1.0.
     * <p>
     * This one is used when it is explicitly wanted that connection is not kept alive in pool.
     * 
     * @param _url Target URL, note: this is <b>not</b> the same one that class constructor used. 
     * @return result
     */
    public HttpGet buildHttpGet1( final String _url ) {
        final HttpGet ret = this.buildHttpGet(_url);
        // Note: Setting protocol version has the side-effect that
        //       the connection cannot be reused, but it also removes CLOSE_WAIT problems.
        ret.setProtocolVersion(HttpVersion.HTTP_1_0);

        return ret;
    }


    /**
     * Convenience method building and initializing HttpPost
     * 
     * @param _url Target URL, note: this is <b>not</b> the same one that class constructor used. 
     * @return result
     */
    public HttpPost buildHttpPost( final String _url ) {
        final HttpPost ret = new HttpPost(_url);

        // AuthCredentials, if any
        if (this.targetAuthCredentials != null) {
            try {
                URI uri = new URI(_url);
                final HttpHost hh = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
                this.authCache.put(hh, this.targetAuthScheme);
                final AuthScope as = new AuthScope(hh.getHostName(), hh.getPort(), hh.getSchemeName());
                this.credentialsProvider.setCredentials(as, this.targetAuthCredentials);
            } catch (URISyntaxException e) {
                log.debug("Unable to set auth credentials. URISyntaxException for URL: " + _url);
                log.trace("URISyntaxException", e);
            }
        }
        
        return ret;
    }

    /**
     * Convenience method to build HTTP POST with ProtocolVersion HTTP1.0.
     * <p>
     * This one is used when it is explicitly wanted that connection is not kept alive in pool.
     * 
     * @param u Target URL, note: this is <b>not</b> the same one that class constructor used. 
     * @return result
     */
    public HttpPost buildHttpPost1( final String u ) {
        final HttpPost ret = this.buildHttpPost(u);
        // Note: Setting protocol version has the side-effect that
        //       the connection cannot be reused, but it also removes CLOSE_WAIT problems.
        ret.setProtocolVersion(HttpVersion.HTTP_1_0);
        
        return ret;
    }

    /**
     * Helper to close HttpGet and its response 
     * @param get  Thing to be closed 
     * @param resp Thing to be closed
     */
    public void closeQuietly( final HttpGet get, final CloseableHttpResponse resp ) {
        // Response needs to be closed
        HttpClientUtils.closeQuietly(resp);
        // Closing the request is not an absolute requirement
        if (get != null)
            get.releaseConnection();
    }

    /**
     * Helper to close HttpPost and its response
     * @param post Thing to be closed 
     * @param resp Thing to be closed
     */
    public void closeQuietly( final HttpPost post, final CloseableHttpResponse resp ) {
        // Response needs to be closed
        HttpClientUtils.closeQuietly(resp);
        // Closing the request is not an absolute requirement
        if (post != null)
            post.releaseConnection();
    }

    public PoolingHttpClientConnectionManager getConnectionManager() {
        return this.connectionManager;
    }
    
    @Override
    public String toString() {
        return "[URL=" + this.poolUrl + "]";
    }
}
