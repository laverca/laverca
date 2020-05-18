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

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A custom singleton implementation of SSL TrustManager.
 * <p>
 * This checks that the server certificate seen on remote system is one of the expected ones.
 * If no expected ones are defined, no error happens.
 */
public class LavercaSSLTrustManager implements X509TrustManager {

    private static final Log log = LogFactory.getLog(LavercaSSLTrustManager.class);

    /** There can be a chain of trust managers in thread-local context! */
    private final ThreadLocal<X509TrustManager> nextTrustManager = new ThreadLocal<>();

    /**
     * If the "expectedServerCerts" list is defined and non-empty,
     * the remote server certificate
     */
    private final ThreadLocal<List<byte[]>> expectedServerCerts = new ThreadLocal<>();

    private static final LavercaSSLTrustManager self = new LavercaSSLTrustManager();

    public static LavercaSSLTrustManager getInstance() {
        return LavercaSSLTrustManager.self;
    }

    private LavercaSSLTrustManager() {
        // Nothing here.
    }

    /**
     * Set a collection (or null) of server certificates expected at the remote system.
     * If the collection is not null and non-empty the server's certificate must
     * be present in the collection, otherwise the connection is rejected.
     * <p>
     * This sets data into ThreadLocal storage, and therefore must always be called
     * just prior the service function calls to make sure the correct data is present.
     * 
     * @param ss List of server certificates
     */
    public void setExpectedServerCerts(final List<byte[]> ss) {
        this.expectedServerCerts.set(ss);
    }

    public List<byte[]> getExpectedServerCerts() {
        return this.expectedServerCerts.get();
    }


    /**
     * Set new "next trust manager" to be called, return previous value.
     * This is applied only on server certificates, and happens in JRE
     * context executing SSL socket under multiple layers of other libraries.
     * 
     * @param tm TrustManager
     * @return Old TrustManager
     */
    public X509TrustManager setNextTrustManager(final X509TrustManager tm) {
        X509TrustManager oldTm = this.nextTrustManager.get();
        this.nextTrustManager.set(tm);
        return oldTm;
    }


    /**
     * For API symmetry, on TLS server check incoming client certificate.
     * Really pass it on to next checker in the chain.
     * 
     * @param chain    Certificate Chain (X509Certificate)
     * @param derChain Certificate Chain (byte[]) (not used)
     * @param authType TrustManager authentication type
     * @throws CertificateException if client is not trusted
     */
    public void checkClientTrusted( final List<X509Certificate> chain,
                                    final List<byte[]> derChain,
                                    final String authType )
        throws CertificateException
    {
        log.trace("checkClientTrusted(3)");

        final X509TrustManager tm = this.nextTrustManager.get();
        if (tm != null) {
            tm.checkClientTrusted(((X509Certificate[])chain.toArray()), authType);
        }
    }

    /*
     * For API symmetry, on TLS server check incoming client certificate.
     * Really pass it on to next checker in the chain.
     */
    // from javax.net.ssl.X509TrustManager
    @Override
    public void checkClientTrusted( final X509Certificate[] chain,
                                    final String authType )
        throws CertificateException
    {
        log.trace("checkClientTrusted(2)");

        if ((chain == null) || (chain.length == 0)) {
            throw new CertificateException("No certificates passed in!");
        }

        if (log.isDebugEnabled()) {
            for (int i = 0; i < chain.length; ++i) {
                if (i > 0) {
                    if (log.isTraceEnabled()) {
                        log.trace("  chain["+i+"] = "+chain[i]);
                    }
                } else {
                    log.debug("  chain["+i+"] = "+chain[i]);
                }
            }
        }

        final X509TrustManager tm = this.nextTrustManager.get();
        if (tm != null) {
            tm.checkClientTrusted(chain, authType);
        }
    }

    /*
     * On client connections, check that the server certificate is valid
     * (by calling next of the chained trust managers), and then check
     * that the observed server certificate is in our internal expected
     * certificate's list.
     */
    // from javax.net.ssl.X509TrustManager
    @Override
    public void checkServerTrusted( final X509Certificate[] chain,
                                    final String authType )
        throws CertificateException
    {
        log.trace("checkServerTrusted(2)");

        final X509TrustManager nextTm = this.nextTrustManager.get();
        if (nextTm != null) {
            nextTm.checkServerTrusted(chain, authType);
        }

        if (chain == null || chain.length == 0) {
            log.debug("No certificates received from server, no verification!");
            throw new CertificateException("No certificates received from server! Did it reject our connection?");
        }

        if (log.isDebugEnabled()) {
            for (int i = 0; i < chain.length; ++i) {
                if (i > 0) {
                    if (log.isTraceEnabled()) {
                        log.trace("  chain["+i+"] = "+chain[i]);
                    }
                } else {
                    log.debug("  chain["+i+"] = "+chain[i]);
                }
            }
        }

        checkExpectedServerCerts(chain[0]);
    }


    /**
     * On client connections, check that the server certificate is valid
     * (by calling next of the chained trust managers), and then check
     * that the observed server certificate is in our internal expected
     * certificate's list.
     * 
     * @param x509cert X.509 Certificate
     * @throws CertificateException if expected server certs do not match
     */
    public void checkExpectedServerCerts(final X509Certificate x509cert)
        throws CertificateException
    {
        final List<byte[]> escs = this.expectedServerCerts.get();
        if (escs == null) {
            // Nothing to verify against
            log.debug("Server certificate match list is not defined.");
            return;
        }
        if (escs.size() == 0) {
            // Nothing to verify against
            log.debug("Server certificate match list is empty.");
            return;
        }

        final byte[] cert = x509cert.getEncoded(); // may throw CertificateEncodingException

        for (final byte[] esc : escs) {
            if (Arrays.equals(esc, cert)) {
                log.debug("Match on expected server certificate");
                return;
            }
        }

        log.warn("Received remote server certificate not found in expected certificates.");

        throw new CertificateException("No server cert match among expected certificates.");
    }


    /** -- java 1.6 --
     * Given the partial or complete certificate chain provided by the
     * peer, check its identity and build a certificate path to a trusted
     * root, return if it can be validated and is trusted for client SSL
     * authentication based on the authentication type.
     * <p>
     * The authentication type is determined by the actual certificate
     * used. For instance, if RSAPublicKey is used, the authType
     * should be "RSA". Checking is case-sensitive.
     * <p>
     * The algorithm parameter specifies the client identification protocol
     * to use. If the algorithm and the peer hostname are available, the
     * peer hostname is checked against the peer's identity presented in
     * the X509 certificate, in order to prevent masquerade attacks.
     *
     * @param chain the peer certificate chain
     * @param authType the authentication type based on the client certificate
     * @param hostname the peer hostname
     * @param algorithm the identification algorithm
     * @throws IllegalArgumentException if null or zero-length chain
     *         is passed in for the chain parameter or if null or zero-length
     *         string is passed in for the  authType parameter
     * @throws CertificateException if the certificate chain is not trusted
     *         by this TrustManager.
     */
    //@Override
    public void checkClientTrusted(final X509Certificate[] chain,
                                   final String authType,
                                   final String hostname,
                                   final String algorithm)
        throws CertificateException
    {
        log.trace("checkClientTrusted(1)");
        this.checkClientTrusted( chain, authType );
    }


    /**  -- java 1.6 --
     * Given the partial or complete certificate chain provided by the
     * peer, check its identity and build a certificate path to a trusted
     * root, return if it can be validated and is trusted for server SSL
     * authentication based on the authentication type.
     * <p>
     * The authentication type is the key exchange algorithm portion
     * of the cipher suites represented as a String, such as "RSA",
     * "DHE_DSS". Checking is case-sensitive.
     * <p>
     * The algorithm parameter specifies the server identification protocol
     * to use. If the algorithm and the peer hostname are available, the
     * peer hostname is checked against the peer's identity presented in
     * the X509 certificate, in order to prevent masquerade attacks.
     *
     * @param chain the peer certificate chain
     * @param authType the key exchange algorithm used
     * @param hostname the peer hostname
     * @param algorithm the identification algorithm
     * @throws IllegalArgumentException if null or zero-length chain
     *         is passed in for the chain parameter or if null or zero-length
     *         string is passed in for the  authType parameter
     * @throws CertificateException if the certificate chain is not trusted
     *         by this TrustManager.
     */
    //@Override
    public void checkServerTrusted(final X509Certificate[] chain,
                                   final String authType,
                                   final String hostname,
                                   final String algorithm)
        throws CertificateException
    {
        log.trace("checkServerTrusted(1) ");
        this.checkServerTrusted( chain, authType );
    }


    // This does not matter. We use the Validator for everything.
    // We could ask the Validator for this list, but Validator does
    // not include a public listing of accepted issuers, so we don't
    // do it here, either.
    @Override
    public X509Certificate[] getAcceptedIssuers()
    {
        return new X509Certificate[0];
    }
}
