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
import java.util.Collections;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;

/**
 * TLS socket factory whose client key comes from a {@link ReloadingKeyManager}.
 * MssClient calls {@link #refresh()} before each call and rebuilds its connection pool when the client key changes.
 */
public class ReloadingSSLSocketFactory extends SSLSocketFactory {

    private final SSLContext          context;
    private final SSLSocketFactory    delegate;
    private final ReloadingKeyManager keyManager;

    /** The key generation whose TLS sessions the context's session cache holds. */
    private long sessionGeneration;

    /**
     * Wraps the socket factory of an SSLContext initialized with the given key manager.
     *
     * @param context the SSLContext
     * @param keyManager the key manager the SSLContext uses
     */
    public ReloadingSSLSocketFactory(final SSLContext context, final ReloadingKeyManager keyManager) {
        this.context           = context;
        this.delegate          = context.getSocketFactory();
        this.keyManager        = keyManager;
        this.sessionGeneration = keyManager.getGeneration();
    }

    /**
     * Refreshes the key manager. When the client key changed, also drops the cached TLS sessions:
     * a resumed session would keep authenticating with the old client certificate.
     *
     * @return the generation of the client key in use
     */
    public synchronized long refresh() {
        this.keyManager.refresh();
        final long generation = this.keyManager.getGeneration();
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
     * @return the key manager
     */
    public ReloadingKeyManager getKeyManager() {
        return this.keyManager;
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
