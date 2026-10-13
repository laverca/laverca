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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Server certificate check against a truststore file that is reloaded when the file changes.
 * <p>
 * By default the server certificate must be one of the truststore's certificates. With the issuer check, it must be
 * signed by one of them, and when MSSP URIs are given, carry one of them as a URI SubjectAltName.
 * A truststore without certificates accepts no server.
 * <p>
 * Each instance holds its own settings, so the check is the same on every thread.
 * {@link #refresh()} re-reads the file at most once per check interval; {@link #getGeneration()} changes with the certificates.
 */
public class ReloadingTrustManager extends X509ExtendedTrustManager {

    private static final Log log = LogFactory.getLog(ReloadingTrustManager.class);

    /** Default truststore check interval in seconds. */
    public static final int DEFAULT_CHECK_INTERVAL = 30;

    private static final int SAN_TYPE_URI = 6;

    private final Path        file;
    private final char[]      password;
    private final String      type;
    private final long        intervalMillis;
    private final boolean     checkIssuer;
    private final Set<String> msspUris;

    private byte[] digest;
    private long   nextCheck;

    /** The truststore's certificates. */
    private volatile List<X509Certificate> certs;
    private volatile long                  generation;

    /**
     * Loads the truststore.
     *
     * @param file truststore file
     * @param password truststore password; null for none
     * @param type truststore type, e.g. PKCS12 or JKS
     * @param intervalSeconds how often the file is checked for changes, in seconds; 0 to load it only once
     * @param checkIssuer true to accept a server certificate signed by a truststore certificate, false to require the certificate itself
     * @param msspUris with the issuer check, URIs one of which the server certificate must carry as a SubjectAltName; empty or null for no SAN check
     * @throws IOException if the truststore cannot be read
     * @throws GeneralSecurityException if the truststore cannot be loaded
     */
    public ReloadingTrustManager(final String file,
                                 final String password,
                                 final String type,
                                 final int intervalSeconds,
                                 final boolean checkIssuer,
                                 final Collection<String> msspUris)
        throws IOException, GeneralSecurityException
    {
        this.file           = Path.of(file);
        this.password       = (password != null) ? password.toCharArray() : null;
        this.type           = type;
        this.intervalMillis = Math.max(intervalSeconds, 0) * 1000L;
        this.checkIssuer    = checkIssuer;
        final Set<String> uris = new LinkedHashSet<>();
        if (msspUris != null) {
            for (final String u : msspUris) {
                if (u != null && !u.isBlank()) {
                    uris.add(u.trim());
                }
            }
        }
        this.msspUris = Collections.unmodifiableSet(uris);

        final byte[] content = Files.readAllBytes(this.file);
        this.certs     = this.load(content);
        this.digest    = sha256(content);
        this.nextCheck = System.currentTimeMillis() + this.intervalMillis;
        log.info("Truststore " + this.file + ": " + this.certs.size() + " certificate(s), "
                 + (checkIssuer ? "issuer check" + (this.msspUris.isEmpty() ? "" : " with SAN " + this.msspUris) : "exact certificate match"));
    }

    /**
     * Re-reads the truststore file if the check interval has passed.
     * A file that cannot be loaded, e.g. one being written, leaves the current certificates in use until the next check.
     */
    public synchronized void refresh() {
        final long now = System.currentTimeMillis();
        if (this.intervalMillis == 0 || now < this.nextCheck) {
            return;
        }
        this.nextCheck = now + this.intervalMillis;
        try {
            final byte[] content = Files.readAllBytes(this.file);
            final byte[] newDigest = sha256(content);
            if (!Arrays.equals(newDigest, this.digest)) {
                this.certs  = this.load(content);
                this.digest = newDigest;
                this.generation++;
                log.info("Reloaded truststore " + this.file + ": " + this.certs.size() + " certificate(s)");
            }
        } catch (IOException | GeneralSecurityException e) {
            log.warn("Keeping the current trusted certificates: cannot load " + this.file + ": " + e);
        }
    }

    /**
     * Gets a counter that changes whenever the trusted certificates change.
     *
     * @return the generation of the trusted certificates
     */
    public long getGeneration() {
        return this.generation;
    }

    /**
     * Gets the trusted certificates.
     *
     * @return the certificates, unmodifiable
     */
    public List<X509Certificate> getCertificates() {
        return this.certs;
    }

    /**
     * Loads the certificates of a truststore: certificate entries and the certificates of key entries.
     *
     * @param content the truststore file's content
     * @return the certificates
     * @throws IOException if the truststore cannot be parsed
     * @throws GeneralSecurityException if the truststore cannot be loaded
     */
    private List<X509Certificate> load(final byte[] content) throws IOException, GeneralSecurityException {
        final KeyStore ts = KeyStore.getInstance(this.type);
        ts.load(new ByteArrayInputStream(content), this.password);
        final List<X509Certificate> list = new ArrayList<>();
        for (final Enumeration<String> aliases = ts.aliases(); aliases.hasMoreElements();) {
            final Certificate c = ts.getCertificate(aliases.nextElement());
            if (c instanceof X509Certificate x) {
                list.add(x);
            }
        }
        if (list.isEmpty()) {
            log.warn("Truststore " + this.file + " has no certificates: no server is accepted");
        }
        return Collections.unmodifiableList(list);
    }

    private static byte[] sha256(final byte[] content) throws GeneralSecurityException {
        return MessageDigest.getInstance("SHA-256").digest(content);
    }

    /**
     * Checks the server certificate against the trusted certificates.
     *
     * @param chain the server's certificate chain
     * @throws CertificateException if the server is not trusted
     */
    private void check(final X509Certificate[] chain) throws CertificateException {
        if (chain == null || chain.length == 0) {
            throw new CertificateException("No certificates received from server");
        }
        final X509Certificate server = chain[0];
        final List<X509Certificate> trusted = this.certs;
        if (!this.checkIssuer) {
            for (final X509Certificate t : trusted) {
                if (t.equals(server)) {
                    return;
                }
            }
            throw new CertificateException("Server certificate " + server.getSubjectX500Principal() + " is not in the truststore");
        }

        // A certificate pinned in the truststore needs no issuer; one trusted through its issuer must be valid now and its issuer a CA
        boolean signed = trusted.contains(server);
        if (!signed) {
            for (final X509Certificate t : trusted) {
                if (t.getSubjectX500Principal().equals(server.getIssuerX500Principal()) && isCa(t) && isSignedBy(server, t)) {
                    signed = true;
                    break;
                }
            }
            if (!signed) {
                throw new CertificateException("Server certificate " + server.getSubjectX500Principal() + " is not signed by a truststore CA certificate");
            }
            server.checkValidity();
        }
        if (this.msspUris.isEmpty()) {
            return;
        }
        try {
            final Collection<List<?>> sans = server.getSubjectAlternativeNames();
            if (sans != null) {
                for (final List<?> san : sans) {
                    if (Integer.valueOf(SAN_TYPE_URI).equals(san.get(0)) && this.msspUris.contains(String.valueOf(san.get(1)))) {
                        return;
                    }
                }
            }
        } catch (CertificateParsingException e) {
            throw new CertificateException("Cannot read the server certificate's SubjectAltNames: " + e.getMessage(), e);
        }
        throw new CertificateException("Server certificate carries none of the MSSP URIs " + this.msspUris);
    }

    /**
     * Tells whether a truststore certificate may issue certificates: a CA in its basic constraints, or an X.509 v1
     * certificate, which has no extensions.
     *
     * @param cert the truststore certificate
     * @return true if it may issue certificates
     */
    private static boolean isCa(final X509Certificate cert) {
        return cert.getBasicConstraints() >= 0 || cert.getVersion() < 3;
    }

    private static boolean isSignedBy(final X509Certificate cert, final X509Certificate issuer) {
        try {
            cert.verify(issuer.getPublicKey());
            return true;
        } catch (GeneralSecurityException e) {
            return false;
        }
    }

    @Override
    public void checkServerTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
        this.check(chain);
    }

    @Override
    public void checkServerTrusted(final X509Certificate[] chain, final String authType, final Socket socket) throws CertificateException {
        this.check(chain);
    }

    @Override
    public void checkServerTrusted(final X509Certificate[] chain, final String authType, final SSLEngine engine) throws CertificateException {
        this.check(chain);
    }

    @Override
    public void checkClientTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
        throw new CertificateException("Client certificates are not accepted: this trust manager is for TLS clients");
    }

    @Override
    public void checkClientTrusted(final X509Certificate[] chain, final String authType, final Socket socket) throws CertificateException {
        this.checkClientTrusted(chain, authType);
    }

    @Override
    public void checkClientTrusted(final X509Certificate[] chain, final String authType, final SSLEngine engine) throws CertificateException {
        this.checkClientTrusted(chain, authType);
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
