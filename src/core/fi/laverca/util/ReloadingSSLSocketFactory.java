/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2026 Laverca Project
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
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Collections;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * TLS socket factory whose client key comes from a {@link ReloadingKeyManager} and whose server check from a
 * {@link ReloadingTrustManager}, or from the JVM's default PKIX check without a truststore.
 * MssClient calls {@link #refresh()} before each call and rebuilds its connection pool when the key or the trusted certificates change.
 */
public class ReloadingSSLSocketFactory extends SSLSocketFactory {

    private final SSLContext            context;
    private final SSLSocketFactory      delegate;
    private final ReloadingKeyManager   keyManager;
    private final ReloadingTrustManager trustManager;

    /** The key and trust generation whose TLS sessions the context's session cache holds. */
    private long sessionGeneration;

    /**
     * Creates the SSLContext and its socket factory.
     *
     * @param protocol the SSLContext protocol, e.g. TLSv1.3
     * @param keyManager the client key; null to send no client certificate
     * @param trustManager the server check; null for the JVM's default PKIX check
     * @return the socket factory
     * @throws GeneralSecurityException if the SSLContext cannot be created
     */
    public static ReloadingSSLSocketFactory create(final String protocol,
                                                   final ReloadingKeyManager keyManager,
                                                   final ReloadingTrustManager trustManager)
        throws GeneralSecurityException
    {
        final SSLContext ctx = SSLContext.getInstance(protocol);
        final KeyManager[] kms = (keyManager != null) ? new KeyManager[] {keyManager} : null;
        final TrustManager tm = (trustManager != null) ? trustManager : defaultTrustManager();
        ctx.init(kms, new TrustManager[] {tm}, null);
        return new ReloadingSSLSocketFactory(ctx, keyManager, trustManager);
    }

    /**
     * Gets the JVM's default PKIX trust manager, which checks the server against the JVM's CA certificates.
     *
     * @return the trust manager
     * @throws GeneralSecurityException if there is none
     */
    public static X509TrustManager defaultTrustManager() throws GeneralSecurityException {
        final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init((KeyStore) null);
        for (final TrustManager tm : tmf.getTrustManagers()) {
            if (tm instanceof X509TrustManager x) {
                return x;
            }
        }
        throw new GeneralSecurityException("No default X509TrustManager");
    }

    /**
     * Wraps the socket factory of an SSLContext initialized with the given managers.
     *
     * @param context the SSLContext
     * @param keyManager the key manager the SSLContext uses; null for none
     * @param trustManager the trust manager the SSLContext uses; null if it is not a reloading one
     */
    public ReloadingSSLSocketFactory(final SSLContext context, final ReloadingKeyManager keyManager, final ReloadingTrustManager trustManager) {
        this.context           = context;
        this.delegate          = context.getSocketFactory();
        this.keyManager        = keyManager;
        this.trustManager      = trustManager;
        this.sessionGeneration = this.generation();
    }

    private long generation() {
        return ((this.keyManager != null) ? this.keyManager.getGeneration() : 0)
             + ((this.trustManager != null) ? this.trustManager.getGeneration() : 0);
    }

    /**
     * Refreshes the key and trust managers. When either changed, also drops the cached TLS sessions:
     * a resumed session would skip the new client certificate and the new server check.
     *
     * @return a counter that changes whenever the client key or the trusted certificates change
     */
    public synchronized long refresh() {
        if (this.keyManager != null) {
            this.keyManager.refresh();
        }
        if (this.trustManager != null) {
            this.trustManager.refresh();
        }
        final long generation = this.generation();
        if (generation != this.sessionGeneration) {
            final SSLSessionContext sessions = this.context.getClientSessionContext();
            for (final byte[] id : Collections.list(sessions.getIds())) {
                final SSLSession session = sessions.getSession(id);
                if (session != null) {
                    session.invalidate();
                }
            }
            this.sessionGeneration = generation;
        }
        return generation;
    }

    /**
     * Gets the key manager that chooses the client key.
     *
     * @return the key manager, or null if no client certificate is sent
     */
    public ReloadingKeyManager getKeyManager() {
        return this.keyManager;
    }

    /**
     * Gets the trust manager that checks the server.
     *
     * @return the trust manager, or null if the JVM's default PKIX check is used
     */
    public ReloadingTrustManager getTrustManager() {
        return this.trustManager;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    @Override
    public Socket createSocket() throws IOException {
        return this.delegate.createSocket();
    }

    @Override
    public Socket createSocket(final Socket s, final String host, final int port, final boolean autoClose) throws IOException {
        return this.delegate.createSocket(s, host, port, autoClose);
    }

    @Override
    public Socket createSocket(final String host, final int port) throws IOException {
        return this.delegate.createSocket(host, port);
    }

    @Override
    public Socket createSocket(final String host, final int port, final InetAddress localHost, final int localPort) throws IOException {
        return this.delegate.createSocket(host, port, localHost, localPort);
    }

    @Override
    public Socket createSocket(final InetAddress host, final int port) throws IOException {
        return this.delegate.createSocket(host, port);
    }

    @Override
    public Socket createSocket(final InetAddress address, final int port, final InetAddress localAddress, final int localPort) throws IOException {
        return this.delegate.createSocket(address, port, localAddress, localPort);
    }
}
