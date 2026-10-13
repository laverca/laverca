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

package fi.laverca.test;

import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

/**
 * Certificates, keystores and TLS contexts for the tests, generated at run time (EC P-256).
 */
public final class TestPki {

    /** Password of every keystore the tests write. */
    public static final String PASSWORD = "secret";

    private static final AtomicLong SERIAL = new AtomicLong(System.currentTimeMillis());

    /** A certificate with its private key and the issuer chain above it. */
    public static final class Cert {
        public final X509Certificate cert;
        public final PrivateKey key;
        public final List<X509Certificate> issuers;

        Cert(final X509Certificate cert, final PrivateKey key, final List<X509Certificate> issuers) {
            this.cert = cert;
            this.key = key;
            this.issuers = issuers;
        }

        /** @return the certificate and its issuers, leaf first */
        public X509Certificate[] chain() {
            final List<X509Certificate> l = new ArrayList<>();
            l.add(this.cert);
            l.addAll(this.issuers);
            return l.toArray(new X509Certificate[0]);
        }
    }

    private TestPki() {
        // Static only
    }

    /** @return a CA valid from a day ago for 30 days */
    public static Cert ca(final String cn) throws Exception {
        return issue(cn, null, daysFromNow(-1), daysFromNow(30), null, true);
    }

    /** @return a certificate valid from a day ago for 30 days, issued by issuer (null: self-signed) */
    public static Cert cert(final String cn, final Cert issuer) throws Exception {
        return issue(cn, issuer, daysFromNow(-1), daysFromNow(30), null, false);
    }

    /**
     * Issues a certificate.
     *
     * @param cn the subject's CN
     * @param issuer the issuing CA; null for a self-signed certificate
     * @param notBefore validity start
     * @param notAfter validity end
     * @param sanUri a URI SubjectAltName, or null
     * @param isCa whether the certificate is a CA
     * @return the certificate with its key
     * @throws Exception on any failure
     */
    public static Cert issue(final String cn, final Cert issuer, final Date notBefore, final Date notAfter,
                             final String sanUri, final boolean isCa)
        throws Exception
    {
        final KeyPairGenerator g = KeyPairGenerator.getInstance("EC");
        g.initialize(new ECGenParameterSpec("secp256r1"));
        final KeyPair kp = g.generateKeyPair();
        final X500Name subject = new X500Name("CN=" + cn);
        final X500Name issuerName = (issuer != null) ? new X500Name(issuer.cert.getSubjectX500Principal().getName()) : subject;
        final X509v3CertificateBuilder b = new JcaX509v3CertificateBuilder(issuerName, BigInteger.valueOf(SERIAL.incrementAndGet()),
                                                                          notBefore, notAfter, subject, kp.getPublic());
        b.addExtension(Extension.basicConstraints, true, new BasicConstraints(isCa));
        if (sanUri != null) {
            b.addExtension(Extension.subjectAlternativeName, false,
                           new GeneralNames(new GeneralName(GeneralName.uniformResourceIdentifier, sanUri)));
        }
        final PrivateKey signer = (issuer != null) ? issuer.key : kp.getPrivate();
        final X509Certificate c = new JcaX509CertificateConverter().getCertificate(
            b.build(new JcaContentSignerBuilder("SHA256withECDSA").build(signer)));
        final List<X509Certificate> chain = new ArrayList<>();
        if (issuer != null) {
            chain.add(issuer.cert);
            chain.addAll(issuer.issuers);
        }
        return new Cert(c, kp.getPrivate(), chain);
    }

    /** @return the date the given number of seconds from now */
    public static Date secondsFromNow(final long seconds) {
        return new Date(System.currentTimeMillis() + seconds * 1000);
    }

    /** @return the date the given number of days from now */
    public static Date daysFromNow(final long days) {
        return secondsFromNow(days * 24 * 3600);
    }

    /** Writes a PKCS12 keystore with the given keys (alias = CN order given) to the file, replacing it. */
    public static Path keystore(final Path file, final Cert... keys) throws Exception {
        final KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(null, null);
        int i = 0;
        for (final Cert c : keys) {
            ks.setKeyEntry("key" + (i++), c.key, PASSWORD.toCharArray(), c.chain());
        }
        return store(ks, file);
    }

    /** Writes a PKCS12 truststore with the given certificates to the file, replacing it. */
    public static Path truststore(final Path file, final X509Certificate... certs) throws Exception {
        final KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(null, null);
        int i = 0;
        for (final X509Certificate c : certs) {
            ks.setCertificateEntry("cert" + (i++), c);
        }
        return store(ks, file);
    }

    private static Path store(final KeyStore ks, final Path file) throws Exception {
        final Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        try (OutputStream out = Files.newOutputStream(tmp)) {
            ks.store(out, PASSWORD.toCharArray());
        }
        return Files.move(tmp, file, java.nio.file.StandardCopyOption.REPLACE_EXISTING, java.nio.file.StandardCopyOption.ATOMIC_MOVE);
    }

    /**
     * A server-side TLS context: the server certificate, any client certificate accepted, and the given issuers
     * named in the CertificateRequest.
     *
     * @param server the server's certificate and key
     * @param namedIssuers the issuers to name; empty for none
     * @return the context
     * @throws Exception on any failure
     */
    public static SSLContext serverContext(final Cert server, final X509Certificate... namedIssuers) throws Exception {
        final KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(null, null);
        ks.setKeyEntry("server", server.key, PASSWORD.toCharArray(), server.chain());
        final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, PASSWORD.toCharArray());
        final TrustManager acceptAnyClient = new X509TrustManager() {
            @Override public void checkClientTrusted(final X509Certificate[] c, final String t) { /* any */ }
            @Override public void checkServerTrusted(final X509Certificate[] c, final String t) { /* unused */ }
            @Override public X509Certificate[] getAcceptedIssuers() { return namedIssuers; }
        };
        final SSLContext ctx = SSLContext.getInstance("TLSv1.3");
        ctx.init(kmf.getKeyManagers(), new TrustManager[] {acceptAnyClient}, null);
        return ctx;
    }
}
