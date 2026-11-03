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
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.net.ssl.X509TrustManager;

/**
 * A custom singleton implementation of SSL TrustManager.
 * <p>
 * This checks that the server certificate seen on remote system issued by one of the trusted certificates.
 */
public class IssuerTrustManager implements X509TrustManager {

    // RFC 5280: SAN type 6 is for uniformResourceIdentifier
    private static final int SAN_TYPE_URI = 6;
    
    /** There can be a chain of trust managers in thread-local context! */
    private final ThreadLocal<X509TrustManager> nextTrustManager = new ThreadLocal<>();

    /**
     * If the "expectedIssuerCerts" list is defined and non-empty,
     * the remote server certificate
     */
    private final ThreadLocal<List<X509Certificate>> expectedIssuerCerts = new ThreadLocal<>();
    private final ThreadLocal<Set<String>>           expectedServerSAN   = new ThreadLocal<Set<String>>() {
        @Override 
        public Set<String> initialValue() {
            return new HashSet<String>();
        }
    };

    private static final IssuerTrustManager self = new IssuerTrustManager();

    public static IssuerTrustManager getInstance() {
        return IssuerTrustManager.self;
    }

    private IssuerTrustManager() {
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
    public void setIssuerCerts(final List<X509Certificate> ss) {
        this.expectedIssuerCerts.set(ss);
    }
    
    /**
     * Add MSSP URI as expected
     * @param msspUri MSSP URI
     */
    public void addExpectedMsspURI(final String ... msspUri) {
        for (String s : msspUri) {
            this.expectedServerSAN.get().add(s);
        }
    }

    /**
     * Add MSSP URI as expected
     * @param msspUri MSSP URI
     */
    public void addExpectedMsspURI(final List<String> msspUri) {
        for (String s : msspUri) {
            this.expectedServerSAN.get().add(s);
        }
    }
    
    public List<X509Certificate> getIssuerCerts() {
        return this.expectedIssuerCerts.get();
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
    public void checkClientTrusted(final List<X509Certificate> chain,
                                   final List<byte[]> derChain,
                                   final String authType)
        throws CertificateException
    {
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
    public void checkClientTrusted(final X509Certificate[] chain,
                                   final String authType)
        throws CertificateException
    {
        if ((chain == null) || (chain.length == 0)) {
            throw new CertificateException("No certificates passed in!");
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
    public void checkServerTrusted(final X509Certificate[] chain,
                                   final String authType)
        throws CertificateException
    {

        final X509TrustManager nextTm = this.nextTrustManager.get();
        if (nextTm != null) {
            nextTm.checkServerTrusted(chain, authType);
        }

        if (chain == null || chain.length == 0) {
            throw new CertificateException("No certificates received from server! Did it reject our connection?");
        }
        this.checkExpectedServerCerts(chain[0]);
    }


    /**
     * On client connections, check that the server certificate is valid
     * (by calling next of the chained trust managers), and then check
     * that the observed server certificate is in our internal expected
     * certificate's list.
     * 
     * @param cert X.509 Certificate
     * @throws CertificateException if expected server certs do not match
     */
    public void checkExpectedServerCerts(final X509Certificate cert)
        throws CertificateException
    {
        final List<X509Certificate> expectedIssuerCerts = this.expectedIssuerCerts.get();
        if (expectedIssuerCerts == null) {
            // Nothing to verify against
            return;
        }
        if (expectedIssuerCerts.size() == 0) {
            // Nothing to verify against
            return;
        }
        
        X509Certificate issuerCert = this.findIssuerCert(cert);
        if (issuerCert == null) {
            throw new CertificateException("No matching certificate found!");
        }
        
        // Found a cert with good issuer
        if (this.expectedServerSAN.get().isEmpty()) {
            // No configured MSSP URI to check from SAN - we are OK
            return;
        }
        
        // Check that server cert SAN matches one of the expected MSSP URI values
        try {
            Collection<List<?>> sanEntries = cert.getSubjectAlternativeNames();
            if (sanEntries == null) {
                throw new CertificateException("No server cert match among expected certificates.");
            }
            for (List<?> sanEntry : sanEntries) {
                Integer type = (Integer) sanEntry.get(0);
                if (type != null && type == SAN_TYPE_URI) {
                    String sanValue = (String) sanEntry.get(1);
                    if (this.expectedServerSAN.get().contains(sanValue)) {
                        // GOOD!
                        return;
                    }
                }
            }
        } catch (CertificateParsingException e) {
            throw new CertificateException("No issuer/SAN match among expected certificates: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new CertificateException("Failed to parse SAN: " + e.getMessage(), e);
        }
        throw new CertificateException("No issuer/SAN match among expected certificates.");
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
    
    /**
     * Find the issuer cert from expected issuer certs
     * @param cert server cert
     * @return issuer cert if found, null if not
     */
    private X509Certificate findIssuerCert(final X509Certificate cert) {
        List<X509Certificate> issuerCerts = this.expectedIssuerCerts.get();
        if (cert == null || issuerCerts == null || issuerCerts.isEmpty()) {
            return null;
        }
        return issuerCerts.stream()
                          .filter(issuer -> issuer.getSubjectX500Principal().equals(cert.getIssuerX500Principal()))
                          .filter(issuer -> isSignedBy(cert, issuer))
                          .findFirst()
                          .orElse(null);
    }
    
    private boolean isSignedBy(X509Certificate cert, X509Certificate issuer) {
        try {
            cert.verify(issuer.getPublicKey());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
