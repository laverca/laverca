/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2017 Laverca Project
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

package fi.laverca.mss;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import fi.laverca.util.IssuerTrustManager;
import fi.laverca.util.LavercaSSLTrustManager;

/**
 * Class for reading and passing around the Laverca MSS configuration
 */
public class MssConf {

    public static final String TRUSTSTORE_FILE = "ssl.truststore.file";
    public static final String TRUSTSTORE_PWD  = "ssl.truststore.password";
    public static final String TRUSTSTORE_TYPE = "ssl.truststore.type";
    
    public static final String KEYSTORE_FILE   = "ssl.keystore.file";
    public static final String KEYSTORE_PWD    = "ssl.keystore.password";
    public static final String KEYSTORE_TYPE   = "ssl.keystore.type";
    
    public static final String AP_ID           = "ap.id";
    public static final String AP_PWD          = "ap.password";
    public static final String MSSP_URI        = "mssp.uri";
    public static final String CHECK_ISSUER    = "check.issuer";
    
    public static final String SIGNATURE_URL       = "mssp.signature.url";
    public static final String STATUS_URL          = "mssp.status.url";
    public static final String RECEIPT_URL         = "mssp.receipt.url";
    public static final String PROFILE_URL         = "mssp.profile.url";
    public static final String REGISTRATION_URL    = "mssp.registration.url";
    public static final String HANDSHAKE_URL       = "mssp.handshake.url";
    
    private String apId;             // AP settings
    private String apPwd; 
    private String msspSignatureUrl; // AE connection settings
    private String msspStatusUrl;
    private String msspReceiptUrl;
    private String msspRegistrationUrl;
    private String msspProfileUrl;
    private String msspHandshakeUrl;
    
    private String keystoreFile;
    private String keystorePwd;
    private String keystoreType;
    
    private String truststoreFile;
    private String truststorePwd;
    private String truststoreType;
    
    private boolean  checkIssuer = false;
    private List<String> msspUri = new ArrayList<>();

    public MssConf() {
        // Empty constructor
    }
    
    public MssConf(final String apId,             // AP settings
                   final String apPwd, 
                   final String msspSignatureUrl, // AE connection settings
                   final String msspStatusUrl,
                   final String msspReceiptUrl,
                   final String msspRegistrationUrl,
                   final String msspProfileUrl,
                   final String msspHandshakeUrl) 
    {
        this.apId             = apId;
        this.apPwd            = apPwd;
        this.msspSignatureUrl = msspSignatureUrl;
        this.msspStatusUrl    = msspStatusUrl;
        this.msspReceiptUrl   = msspReceiptUrl;
        this.msspRegistrationUrl = msspRegistrationUrl;
        this.msspProfileUrl   = msspProfileUrl;
        this.msspHandshakeUrl = msspHandshakeUrl;
    }
    
    /**
     * Construct an MSS Configuration object from Java properties
     * @param p Properties
     * @return new MssConf
     */
    public static MssConf fromProperties(final Properties p) {
        
        final MssConf conf = new MssConf();
        
        conf.setApId(p.getProperty(AP_ID));
        conf.setApPwd(p.getProperty(AP_PWD));
        conf.setMsspUri(p.getProperty(MSSP_URI));

        conf.setSignatureUrl(p.getProperty(SIGNATURE_URL));
        conf.setStatusUrl(p.getProperty(STATUS_URL));
        conf.setReceiptUrl(p.getProperty(RECEIPT_URL));
        conf.setRegistrationUrl(p.getProperty(REGISTRATION_URL));
        conf.setProfileUrl(p.getProperty(PROFILE_URL));
        conf.setHandshakeUrl(p.getProperty(HANDSHAKE_URL));

        conf.setTruststore(p.getProperty(TRUSTSTORE_FILE),
                           p.getProperty(TRUSTSTORE_PWD),
                           p.getProperty(TRUSTSTORE_TYPE));
        
        conf.setKeystore(p.getProperty(KEYSTORE_FILE),
                         p.getProperty(KEYSTORE_PWD),
                         p.getProperty(KEYSTORE_TYPE));
        conf.setCheckIssuer(Boolean.getBoolean(p.getProperty(CHECK_ISSUER)));
        return conf;
    }
    
    /**
     * Construct an MSS Configuration object from the given property file
     * @param fileName Java properties file
     * @return new MssConf
     */
    public static MssConf fromPropertyFile(final String fileName) {
        File f = new File(fileName);
        Properties p = new Properties();
        try (FileInputStream is = new FileInputStream(f)) {
            p.load(is);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return MssConf.fromProperties(p);
    }


    /**
     * Get the properties object of this configuration
     * @return Java Properties containing the configuration
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put(AP_ID,  this.getApId());
        p.put(AP_PWD, this.getApPwd());
        
        p.put(SIGNATURE_URL,    this.getSignatureUrl());
        p.put(STATUS_URL,       this.getStatusUrl());
        p.put(RECEIPT_URL,      this.getReceiptUrl());
        p.put(REGISTRATION_URL, this.getRegistrationUrl());
        p.put(PROFILE_URL,      this.getProfileUrl());
        p.put(HANDSHAKE_URL,    this.getHandshakeUrl());
        
        p.put(TRUSTSTORE_FILE, this.getTruststore());
        p.put(TRUSTSTORE_PWD,  this.getTruststorePwd());
        p.put(TRUSTSTORE_TYPE, this.getTruststoreType());
        
        p.put(KEYSTORE_FILE, this.getKeystore());
        p.put(KEYSTORE_PWD,  this.getKeystorePwd());
        p.put(KEYSTORE_TYPE, this.getKeystoreType());
        p.put(MSSP_URI,      String.join(",", this.getMsspUri()));

        return p;
    }

    public String getProfileUrl() {
        return this.msspProfileUrl;
    }

    public String getRegistrationUrl() {
        return this.msspRegistrationUrl;
    }

    public String getHandshakeUrl() {
        return this.msspHandshakeUrl;
    }
    
    /**
     * Create a new SSLSocketFactory from this configuration
     * @return SSLSocketFactory
     * @throws IOException if the keystore or truststore cannot be read
     * @throws GeneralSecurityException if there is any security exception related to accessing the keystore or truststore
     */
    public SSLSocketFactory createSSLFactory() throws GeneralSecurityException, IOException {
        
        final String ksType = this.getKeystoreType();
        final String ksFile = this.getKeystore();
        final String ksPwd  = this.getKeystorePwd();
        final String tsType = this.getTruststoreType();
        final String tsFile = this.getTruststore();
        final String tsPwd  = this.getTruststorePwd();
        
        KeyStore    ks = KeyStore.getInstance(ksType);
        KeyStore    ts = null;

        try (InputStream kis = new FileInputStream(ksFile)) {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            
            ks.load(kis, ksPwd.toCharArray());
            kmf.init(ks, ksPwd.toCharArray());

            final SSLContext ctx = SSLContext.getInstance(MssClient.getTLSContextName());
            
            if (tsFile != null) {
                ts = KeyStore.getInstance(tsType);
                try (InputStream tis = new FileInputStream(tsFile)) {
                    ts.load(tis, tsPwd.toCharArray());
                    List<byte[]> certs = new ArrayList<>();
                    List<X509Certificate> _certs = new ArrayList<>();
                    
                    for (Enumeration<String> aliases = ts.aliases(); aliases.hasMoreElements();) {
                        String alias = aliases.nextElement();
                        if (ts.isKeyEntry(alias)) {
                            X509Certificate cert = (X509Certificate)ts.getCertificate(alias);
                            if (cert != null) {
                                certs.add(cert.getEncoded());
                                _certs.add(cert);
                            }
                        } else if (ts.isCertificateEntry(alias)) {
                            X509Certificate cert = (X509Certificate)ts.getCertificate(alias);
                            if (cert != null) {
                                certs.add(cert.getEncoded());
                                _certs.add(cert);
                            }
                        }
                    }
    
                    // Note: Following is GLOBAL setting to all MssClient instances!
                    // Only one form of MssClient constructor handles the cert list pickup correctly for future client calls
                    LavercaSSLTrustManager.getInstance().setExpectedServerCerts(certs);
                    IssuerTrustManager.getInstance().setIssuerCerts(_certs);
                }
            }
            
            // Use issuer trust manager
            if (this.isCheckIssuer()) {
                TrustManager[] tms = new TrustManager[] {IssuerTrustManager.getInstance()};
                ctx.init(kmf.getKeyManagers(), tms, null);
            } else {
                TrustManager[] tms = new TrustManager[] {LavercaSSLTrustManager.getInstance()};
                ctx.init(kmf.getKeyManagers(), tms, null);
            }
            
            return ctx.getSocketFactory();
        }
    }

    public String getTruststore() {
        return this.truststoreFile;
    }
    
    public String getTruststorePwd() {
        return this.truststorePwd;
    }

    /**
     * Get the configured truststore type (PKCS12 or JKS)
     * @return truststore type (default is JKS)
     */
    public String getTruststoreType() {
        if (this.truststoreType == null) {
            if (this.truststoreFile != null) {
                return this.truststoreFile.endsWith(".pfx") || this.truststoreFile.endsWith(".p12") ? "PKCS12" : "JKS"; 
            } else {
                return "JKS";
            }
        }
        return this.truststoreType;
    }
    
    public String getKeystore() {
        return this.keystoreFile;
    }
    
    public String getKeystorePwd() {
        return this.keystorePwd;
    }
    
    /**
     * Get the configured keystore type (PKCS12 or JKS)
     * @return keystore type (default is JKS)
     */
    public String getKeystoreType() {
        if (this.keystoreType == null) {
            if (this.keystoreFile != null) {
                return this.keystoreFile.endsWith(".pfx") || this.keystoreFile.endsWith(".p12") ? "PKCS12" : "JKS"; 
            } else {
                return "JKS";
            }
        }
        return this.keystoreType;
    }
    
    public String getApId() {
        return this.apId;
    }
    
    public String getApPwd() {
        return this.apPwd;
    }
    
    public String getSignatureUrl() {
        return this.msspSignatureUrl;
    }
    
    public String getStatusUrl() {
        return this.msspStatusUrl;
    }
    
    public String getReceiptUrl() {
        return this.msspReceiptUrl;
    }

    public void setApId(String apId) {
        this.apId = apId;
    }

    public void setApPwd(String apPwd) {
        this.apPwd = apPwd;
    }

    public void setSignatureUrl(String msspSignatureUrl) {
        this.msspSignatureUrl = msspSignatureUrl;
    }

    public void setStatusUrl(String msspStatusUrl) {
        this.msspStatusUrl = msspStatusUrl;
    }

    public void setReceiptUrl(String msspReceiptUrl) {
        this.msspReceiptUrl = msspReceiptUrl;
    }

    public void setRegistrationUrl(String msspRegistrationUrl) {
        this.msspRegistrationUrl = msspRegistrationUrl;
    }

    public void setProfileUrl(String msspProfileUrl) {
        this.msspProfileUrl = msspProfileUrl;
    }

    public void setHandshakeUrl(String msspHandshakeUrl) {
        this.msspHandshakeUrl = msspHandshakeUrl;
    }
    
    /**
     * Set the list of known MSSP URIs
     * @param msspUri MSSP URI as comma separated String
     */
    public void setMsspUri(final String msspUri) {
        if (msspUri == null) return;
        this.msspUri = Arrays.asList(msspUri.split(","));
    }

    /**
     * Set the keystore
     * @param file Keystore file
     * @param pwd  Keystore password
     * @param type Keystore type (jks or pkcs12)
     */
    public void setKeystore(final String file, final String pwd, final String type) {
        this.keystoreFile = file;
        this.keystorePwd  = pwd;
        this.keystoreType = type;
    }
    
    /**
     * Set the truststore
     * @param file Truststore file
     * @param pwd  Truststore password
     * @param type Truststore type (jks or pkcs12)
     */
    public void setTruststore(final String file, final String pwd, final String type) {
        this.truststoreFile = file;
        this.truststorePwd  = pwd;
        this.truststoreType = type;
    }
    
    /**
     * Get a list of known MSSP URIs
     * <p>This is primarily used for certificate SAN validation. 
     * @return MSSP URIs
     */
    public List<String> getMsspUri() {
        return this.msspUri;
    }

    /**
     * Should we check server certificate issuer instead of explicit certificate?
     * @param checkIssuer true to match issuer to truststore
     */
    public void setCheckIssuer(boolean checkIssuer) {
        this.checkIssuer = true;
    }

    /**
     * @return Should we check server certificate issuer instead of explicit certificate?
     */
    public boolean isCheckIssuer() {
        return this.checkIssuer;
    }
    
}
