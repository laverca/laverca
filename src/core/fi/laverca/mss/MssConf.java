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
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class for reading and passing around the Laverca MSS configuration
 */
public class MssConf {

    private static final Log log = LogFactory.getLog(MssConf.class);
    
    public static final String TRUSTSTORE_FILE = "ssl.truststore.file";
    public static final String TRUSTSTORE_PWD  = "ssl.truststore.password";
    public static final String TRUSTSTORE_TYPE = "ssl.truststore.type";
    
    public static final String KEYSTORE_FILE   = "ssl.keystore.file";
    public static final String KEYSTORE_PWD    = "ssl.keystore.password";
    public static final String KEYSTORE_TYPE   = "ssl.keystore.type";
    
    public static final String AP_ID           = "ap.id";
    public static final String AP_PWD          = "ap.password";
    
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
    
    public static MssConf fromProperties(final Properties p) {
        
        final MssConf conf = new MssConf();
        
        conf.setApId(p.getProperty(AP_ID));
        conf.setApPwd(p.getProperty(AP_PWD));
        
        conf.setSignatureUrl(p.getProperty(SIGNATURE_URL));
        conf.setStatusUrl(p.getProperty(STATUS_URL));
        conf.setReceiptUrl(p.getProperty(RECEIPT_URL));
        conf.setRegistrationUrl(p.getProperty(REGISTRATION_URL));
        conf.setProfileUrl(p.getProperty(PROFILE_URL));
        conf.setHandshakeUrl(p.getProperty(HANDSHAKE_URL));

        conf.setTruststore(p.getProperty(TRUSTSTORE_FILE),
                           p.getProperty(TRUSTSTORE_PWD),
                           p.getProperty(TRUSTSTORE_TYPE, "JKS"));
        
        conf.setKeystore(p.getProperty(KEYSTORE_FILE),
                         p.getProperty(KEYSTORE_PWD),
                         p.getProperty(KEYSTORE_TYPE, "JKS"));        
        return conf;
    }
    
    public static MssConf fromPropertyFile(final String fileName) {
        File f = new File(fileName);
        Properties p = new Properties();
        FileInputStream is;
        try {
            is = new FileInputStream(f);
            p.load(is);
            is.close();
        } catch (Exception e) {
            log.fatal("Could not load properties");
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

    public String getTruststore() {
        return this.truststoreFile;
    }
    
    public String getTruststorePwd() {
        return this.truststorePwd;
    }
    
    public String getTruststoreType() {
        return (this.truststoreType != null ? this.truststoreType : "JKS");
    }
    
    public String getKeystore() {
        return this.keystoreFile;
    }
    
    public String getKeystorePwd() {
        return this.keystorePwd;
    }
    
    public String getKeystoreType() {
        return (this.keystoreType != null ? this.keystoreType : "JKS");
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
    
    public void setKeystore(final String file, final String pwd, final String type) {
        this.keystoreFile = file;
        this.keystorePwd  = pwd;
        this.keystoreType = type;
    }
    
    public void setTruststore(final String file, final String pwd, final String type) {
        this.truststoreFile = file;
        this.truststorePwd  = pwd;
        this.truststoreType = type;
    }
    
}
