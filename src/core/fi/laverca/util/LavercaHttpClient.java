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

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.auth.BasicAuthCache;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.auth.BasicScheme;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.client5.http.ssl.TlsSocketStrategy;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpVersion;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

/**
 * Facade class for data used in setting up HttpClient 5.x clients + client constructor.
 * <p>
 * One instance holds one connection pool. Its connections are TLS-wrapped by the given {@link SSLSocketFactory},
 * so they carry its client certificate and Laverca's trust managers, and are reused across requests: the client
 * keeps no per-connection user state, which would otherwise keep client-certificate connections out of reuse.
 * <p>
 * There are several tool methods that could be static, but are chosen to be class instance
 * dependent because the experience is that updates to the HttpClient library need more and
 * more of these facade methods to do the business without distributed code changes all over
 * the places.
 */
public class LavercaHttpClient implements Closeable {

    /**
     * HttpClient 5's default retry strategy without its resend of 429 and 503 answers, which HttpClient 4 never did:
     * that would resend a POST a second after the answer. I/O failures are retried as by default (never a sent POST).
     */
    public static final HttpRequestRetryStrategy NO_RESPONSE_RETRY = new DefaultHttpRequestRetryStrategy() {
        @Override
        public boolean retryRequest(final HttpResponse response, final int execCount, final HttpContext context) {
            return false;
        }
    };

    /** Default connect timeout in milliseconds, used when none is given. */
    public static final int DEFAULT_CONNECT_TIMEOUT = 30000;

    /** How long a request waits for a free pooled connection, in milliseconds. */
    public static final int POOL_LEASE_TIMEOUT = 10000;

    /** Lifetime of a pooled connection in seconds. */
    private static final int CONNECTION_TTL = 300;

    private final PoolingHttpClientConnectionManager connectionManager;
    private final CloseableHttpClient                client;

    /** Target credentials, sent preemptively with Basic authentication; null for none. */
    private final UsernamePasswordCredentials targetAuthCredentials;
    private final HttpHost                    proxyHost;
    private final AuthScope                   proxyAuthScope;
    private final UsernamePasswordCredentials proxyAuthCredentials;

    private final int    connTimeout;
    private final int    soTimeout;
    private final String poolUrl;
    private final String username;
    private final String password;

    /**
     * Creates the client and its connection pool.
     *
     * @param poolUrl target URL, or a name for the pool
     * @param newPoolSize connection pool size. 10 is good.
     * @param newConnTimeout connection timeout in millis, 0 for the default of 30 seconds.
     * @param newSoTimeout socket timeout in millis, 0 for none (the call's own timeout still applies).
     * @param newUsername if set, use this to authenticate to the remote service with HTTP Basic authentication. Use null to not authenticate.
     * @param newPassword see user.
     * @param proxySettings Optional proxy parameters, null for none.
     * @param ssf SSL Socket factory with specific client certificate and key, null for use of system wide default.
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
        this.poolUrl     = poolUrl;
        this.username    = newUsername;
        this.password    = newPassword;
        this.connTimeout = (newConnTimeout > 0) ? newConnTimeout : DEFAULT_CONNECT_TIMEOUT;
        this.soTimeout   = Math.max(newSoTimeout, 0);

        final SSLSocketFactory tlsFactory = (ssf != null) ? ssf : (SSLSocketFactory) SSLSocketFactory.getDefault();

        final ConnectionConfig.Builder ccb = ConnectionConfig.custom()
            .setConnectTimeout(Timeout.ofMilliseconds(this.connTimeout))
            .setTimeToLive(TimeValue.ofSeconds(CONNECTION_TTL))
            .setValidateAfterInactivity(TimeValue.ofSeconds(2));
        if (this.soTimeout > 0) {
            ccb.setSocketTimeout(Timeout.ofMilliseconds(this.soTimeout));
        }

        // Multiple threads (workers) share the HTTP connection pool
        this.connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
            .setTlsSocketStrategy(new SocketFactoryTlsStrategy(tlsFactory))
            .setMaxConnTotal(newPoolSize)
            .setMaxConnPerRoute(newPoolSize)
            .setDefaultConnectionConfig(ccb.build())
            .build();

        if (proxySettings != null && proxySettings.proxyHostName != null && proxySettings.proxyHostName.length() > 0) {
            this.proxyHost = new HttpHost(proxySettings.proxyHostName, proxySettings.proxyPort);
            if (proxySettings.proxyUsername != null && proxySettings.proxyUsername.length() > 0) {
                this.proxyAuthScope       = new AuthScope(this.proxyHost);
                this.proxyAuthCredentials = credentials(proxySettings.proxyUsername, proxySettings.proxyPassword);
            } else {
                this.proxyAuthScope       = null;
                this.proxyAuthCredentials = null;
            }
        } else {
            this.proxyHost            = null;
            this.proxyAuthScope       = null;
            this.proxyAuthCredentials = null;
        }

        if (newUsername != null && newUsername.length() > 0) {
            this.targetAuthCredentials = credentials(newUsername, newPassword);
        } else {
            this.targetAuthCredentials = null;
        }

        final HttpClientBuilder hcb = HttpClients.custom();
        hcb.setConnectionManager(this.connectionManager);
        hcb.setRetryStrategy(NO_RESPONSE_RETRY);
        // One client identity per pool: a client-certificate tag on pooled connections would keep requests from reusing them
        hcb.disableConnectionState();
        if (this.proxyHost != null) {
            hcb.setRoutePlanner(new DefaultProxyRoutePlanner(this.proxyHost));
        }
        this.client = hcb.build();
    }

    private static UsernamePasswordCredentials credentials(final String user, final String pwd) {
        return new UsernamePasswordCredentials(user, (pwd != null) ? pwd.toCharArray() : new char[0]);
    }

    /**
     * Gets the target URL or pool name given to the constructor.
     *
     * @return the URL or pool name
     */
    public String getURL() {
        return this.poolUrl;
    }

    /**
     * Gets the HTTP Basic authentication user name.
     *
     * @return the user name, or null for none
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the HTTP Basic authentication password.
     *
     * @return the password, or null for none
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the connect timeout.
     *
     * @return connect timeout in milliseconds
     */
    public int getConnectTimeout() {
        return this.connTimeout;
    }

    /**
     * Gets the socket timeout.
     *
     * @return socket timeout in milliseconds, 0 for none
     */
    public int getSocketTimeout() {
        return this.soTimeout;
    }

    /**
     * Gets the underlying HttpClient, which sends on this instance's connection pool.
     *
     * @return the HttpClient
     */
    public CloseableHttpClient getHttpClient() {
        return this.client;
    }

    /**
     * Executes a request with a context from {@link #buildContext(String)}; the handler reads the response
     * before its connection goes back to the pool.
     *
     * @param <T> the handler's result type
     * @param req HttpGet/HttpPost request object with URI inside it
     * @param handler reads the response
     * @return the handler's result
     * @throws IOException If the execution fails
     */
    public <T> T execute(final ClassicHttpRequest req, final HttpClientResponseHandler<? extends T> handler)
        throws IOException
    {
        String uri;
        try {
            uri = req.getUri().toString();
        } catch (URISyntaxException e) {
            uri = null;
        }
        return this.execute(req, this.buildContext(uri), handler);
    }

    /**
     * Executes a request with the given context; the handler reads the response before its connection goes
     * back to the pool.
     *
     * @param <T> the handler's result type
     * @param req HttpGet/HttpPost request object with URI inside it
     * @param ctx context, e.g. from {@link #buildContext(String)}
     * @param handler reads the response
     * @return the handler's result
     * @throws IOException If the execution fails
     */
    public <T> T execute(final ClassicHttpRequest req,
                         final HttpClientContext ctx,
                         final HttpClientResponseHandler<? extends T> handler)
        throws IOException
    {
        return this.client.execute(req, ctx, handler);
    }

    /**
     * Builds a new per-request context with the client's credentials, sent preemptively to the target.
     * Credentials in the URL's user info are used when the client has none of its own.
     *
     * @param url target URL; may be null
     * @return the context
     */
    public HttpClientContext buildContext(final String url) {
        final HttpClientContext ctx = HttpClientContext.create();

        BasicCredentialsProvider credsProvider = null;
        if (this.proxyAuthCredentials != null) {
            credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(this.proxyAuthScope, this.proxyAuthCredentials);
        }

        final URI uri = parseUri(url);
        UsernamePasswordCredentials target = this.targetAuthCredentials;
        if (target == null && uri != null && uri.getRawUserInfo() != null) {
            target = userInfoCredentials(uri.getRawUserInfo());
        }
        if (target != null && uri != null && uri.getHost() != null) {
            final HttpHost host = new HttpHost(uri.getScheme(), uri.getHost(), uri.getPort());
            if (credsProvider == null) {
                credsProvider = new BasicCredentialsProvider();
            }
            credsProvider.setCredentials(new AuthScope(host), target);
            final BasicScheme basic = new BasicScheme();
            basic.initPreemptive(target);
            final BasicAuthCache authCache = new BasicAuthCache();
            authCache.put(host, basic);
            ctx.setAuthCache(authCache);
        }
        if (credsProvider != null) {
            ctx.setCredentialsProvider(credsProvider);
        }
        return ctx;
    }

    private static URI parseUri(final String url) {
        if (url == null) {
            return null;
        }
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private static UsernamePasswordCredentials userInfoCredentials(final String rawUserInfo) {
        final int sep = rawUserInfo.indexOf(':');
        final String user = (sep >= 0) ? rawUserInfo.substring(0, sep) : rawUserInfo;
        final String pwd  = (sep >= 0) ? rawUserInfo.substring(sep + 1) : "";
        return credentials(URLDecoder.decode(user, StandardCharsets.UTF_8), URLDecoder.decode(pwd, StandardCharsets.UTF_8));
    }

    /**
     * The URL without its user info, which HttpClient 5 refuses in a request URI.
     *
     * @param url the URL
     * @return the URL without user info
     */
    private static String withoutUserInfo(final String url) {
        final URI uri = parseUri(url);
        if (uri == null || uri.getRawUserInfo() == null) {
            return url;
        }
        try {
            return new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment()).toString();
        } catch (URISyntaxException e) {
            return url;
        }
    }

    /**
     * Get Response body as InputStream
     * 
     * @param resp HTTP Response data
     * @return an InputStream for reading the body
     * @throws IOException if the response has no body
     */
    public InputStream getResponseBodyAsStream(final ClassicHttpResponse resp)
        throws IOException
    {
        final HttpEntity ent = resp.getEntity();
        if (ent == null) {
            throw new IOException("No response body");
        }
        return ent.getContent();
    }

    /**
     * Get Response body as a String presuming the input to be UTF-8 unless response specifies charset.
     * 
     * @param resp HTTP Response data
     * @return A String containing the response body.
     * @throws IOException if the response has no body or can't be read
     */
    public String getResponseBodyAsString(final ClassicHttpResponse resp)
        throws IOException
    {
        final HttpEntity ent = resp.getEntity();
        if (ent == null) {
            throw new IOException("No response body");
        }
        try {
            return EntityUtils.toString(ent, StandardCharsets.UTF_8);
        } catch (ParseException e) {
            throw new IOException(e);
        }
    }

    /**
     * Get Response body as a byte[].
     * 
     * @param resp HTTP Response data
     * @return A byte[] containing the response
     * @throws IOException if the response has no body or can't be read
     */
    public byte[] getResponseBodyAsBytes(final ClassicHttpResponse resp)
        throws IOException
    {
        final HttpEntity ent = resp.getEntity();
        if (ent == null) {
            throw new IOException("No response body");
        }
        return EntityUtils.toByteArray(ent);
    }

    /**
     * Convenience method building an HttpGet. Execute it with a context from {@link #buildContext(String)}
     * of the same URL for the client's credentials.
     * 
     * @param _url Target URL, note: this is <b>not</b> the same one that class constructor used. 
     * @return result
     */
    public HttpGet buildHttpGet(final String _url) {
        return new HttpGet(withoutUserInfo(_url));
    }

    /**
     * Convenience method to build HTTP GET with ProtocolVersion HTTP1.0.
     * <p>
     * This one is used when it is explicitly wanted that connection is not kept alive in pool.
     * 
     * @param _url Target URL, note: this is <b>not</b> the same one that class constructor used. 
     * @return result
     */
    public HttpGet buildHttpGet1(final String _url) {
        final HttpGet ret = this.buildHttpGet(_url);
        ret.setVersion(HttpVersion.HTTP_1_0);
        return ret;
    }

    /**
     * Convenience method building an HttpPost. Execute it with a context from {@link #buildContext(String)}
     * of the same URL for the client's credentials.
     * 
     * @param _url Target URL, note: this is <b>not</b> the same one that class constructor used. 
     * @return result
     */
    public HttpPost buildHttpPost(final String _url) {
        return new HttpPost(withoutUserInfo(_url));
    }

    /**
     * Convenience method to build HTTP POST with ProtocolVersion HTTP1.0.
     * <p>
     * This one is used when it is explicitly wanted that connection is not kept alive in pool.
     * 
     * @param u Target URL, note: this is <b>not</b> the same one that class constructor used. 
     * @return result
     */
    public HttpPost buildHttpPost1(final String u) {
        final HttpPost ret = this.buildHttpPost(u);
        ret.setVersion(HttpVersion.HTTP_1_0);
        return ret;
    }

    /**
     * Gets the connection pool.
     *
     * @return the connection manager
     */
    public PoolingHttpClientConnectionManager getConnectionManager() {
        return this.connectionManager;
    }

    /**
     * Closes the client and its connection pool.
     *
     * @throws IOException if closing fails
     */
    @Override
    public void close() throws IOException {
        this.client.close();
    }

    /**
     * Describes the client by its URL or pool name.
     *
     * @return the description
     */
    @Override
    public String toString() {
        return "[URL=" + this.poolUrl + "]";
    }

    /**
     * TLS for pooled connections from a plain {@link SSLSocketFactory}: its key and trust managers decide the
     * client certificate and the server's acceptance. The host name is not verified, as in Laverca 3; the trust
     * managers check the server certificate.
     */
    private static final class SocketFactoryTlsStrategy implements TlsSocketStrategy {

        private final SSLSocketFactory ssf;

        SocketFactoryTlsStrategy(final SSLSocketFactory ssf) {
            this.ssf = ssf;
        }

        /**
         * Wraps a connected socket in TLS and completes the handshake.
         *
         * @param socket     the connected plain socket
         * @param target     the target host name
         * @param port       the target port
         * @param attachment unused
         * @param context    the HTTP context
         * @return the TLS socket
         * @throws IOException if the handshake fails
         */
        @Override
        public SSLSocket upgrade(final Socket socket,
                                 final String target,
                                 final int port,
                                 final Object attachment,
                                 final HttpContext context)
            throws IOException
        {
            final SSLSocket ssl = (SSLSocket) this.ssf.createSocket(socket, target, port, true);
            ssl.startHandshake();
            return ssl;
        }
    }
}
