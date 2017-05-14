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

package fi.laverca.examples;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class for getting the sample configuration properties
 */
public class ExampleConf {

    private static final Log log = LogFactory.getLog(ExampleConf.class);
    
    public static final String CONF_LOCATION       = "conf/examples.conf";
    
    public static final String TRUSTSTORE_FILE     = "ssl.truststore.file";
    public static final String TRUSTSTORE_PASSWORD = "ssl.truststore.password";
    public static final String TRUSTSTORE_TYPE     = "ssl.truststore.type";
    
    public static final String KEYSTORE_FILE       = "ssl.keystore.file";
    public static final String KEYSTORE_PASSWORD   = "ssl.keystore.password";
    public static final String KEYSTORE_TYPE       = "ssl.keystore.type";
    
    public static final String AP_ID               = "ap.id";
    public static final String AP_PASSWORD         = "ap.password";
    
    public static final String SIGNATURE_URL       = "mssp.signature.url";
    public static final String STATUS_URL          = "mssp.status.url";
    public static final String RECEIPT_URL         = "mssp.receipt.url";
    
    private static ExampleConf instance;
    
    public static ExampleConf getInstance() {
        if (instance == null) {
            instance = new ExampleConf();
        }
        return instance;
    }
    
    private Properties properties;
    
    private ExampleConf() {
        File f = new File(CONF_LOCATION);
        this.properties = new Properties();
        FileInputStream is;
        try {
            is = new FileInputStream(f);
            this.properties.load(is);
            is.close();
        } catch (Exception e) {
            log.fatal("Could not load properties");
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Get the properties object of this configuration
     * @return Java Properties containing the configuration
     */
    public static Properties getProperties(){
        return getInstance().properties;
    }

    public String getTruststore() {
        return this.properties.getProperty(TRUSTSTORE_FILE);
    }
    
    public String getTruststorePwd() {
        return this.properties.getProperty(TRUSTSTORE_PASSWORD);
    }
    
    public String getTruststoreType() {
        return this.properties.getProperty(TRUSTSTORE_TYPE, "JKS");
    }
    
    public String getKeystore() {
        return this.properties.getProperty(KEYSTORE_FILE);
    }
    
    public String getKeystorePwd() {
        return this.properties.getProperty(KEYSTORE_PASSWORD);
    }
    
    public String getKeystoreType() {
        return this.properties.getProperty(KEYSTORE_TYPE, "JKS");
    }
    
    public String getApId() {
        return this.properties.getProperty(AP_ID);
    }
    
    public String getApPwd() {
        return this.properties.getProperty(AP_PASSWORD);
    }
    
    public String getSignatureUrl() {
        return this.properties.getProperty(SIGNATURE_URL);
    }
    
    public String getStatusUrl() {
        return this.properties.getProperty(STATUS_URL);
    }
    
    public String getReceiptUrl() {
        return this.properties.getProperty(RECEIPT_URL);
    }

}
