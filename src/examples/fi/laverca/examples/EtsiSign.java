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

package fi.laverca.examples;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import javax.net.ssl.SSLSocketFactory;

import org.apache.axis.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fi.laverca.MSS_Formats;
import fi.laverca.SignatureProfiles;
import fi.laverca.examples.util.ExampleConf;
import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.mss.MssClient;
import fi.laverca.util.DTBS;
import fi.laverca.util.LavercaSSLTrustManager;

/**
 * Simple signature request example
 * 
 * <ul>
 * <li>Uses the generic MssClient
 * <li>Sends a SignatureRequest with DTBS "sign this"
 * <li>Uses synchronous messaging mode
 * <li>No AdditionalServices
 * </ul>
 *
 */
public class EtsiSign {
    
    private static final Log log = LogFactory.getLog(EtsiSign.class);
    
    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) {
       
        // Load properties
        Properties properties = ExampleConf.getProperties();
        
        // Setup SSL
        SSLSocketFactory ssf = null;
        
        try {
            
            log.info("Setting up ssl");
            String ksFile = properties.getProperty(ExampleConf.KEYSTORE_FILE);
            String ksPwd  = properties.getProperty(ExampleConf.KEYSTORE_PASSWORD);
            String ksType = properties.getProperty(ExampleConf.KEYSTORE_TYPE, "JKS");
            ssf = MssClient.createSSLFactory(ksFile, ksPwd, ksType);

        } catch (Exception e) {
            log.fatal("Keystore problem: " + e.getMessage());
            return;        
        }

        String apId  = properties.getProperty(ExampleConf.AP_ID);
        String apPwd = properties.getProperty(ExampleConf.AP_PASSWORD);
        
        // Setup service URLs
        String msspSignatureUrl    = properties.getProperty(ExampleConf.SIGNATURE_URL);
        String msspStatusUrl       = properties.getProperty(ExampleConf.STATUS_URL);
        String msspReceiptUrl      = properties.getProperty(ExampleConf.RECEIPT_URL);
        
        // Create client
        MssClient etsiClient = new MssClient(apId, 
                                             apPwd, 
                                             msspSignatureUrl, 
                                             msspStatusUrl, 
                                             msspReceiptUrl);

        // Set SSL socket factory
        etsiClient.setSSLSocketFactory(ssf);
        
        // Set empty trust list
        // TODO: Read the truststore configuration instead!
        LavercaSSLTrustManager.getInstance().setExpectedServerCerts(Collections.<byte[]> emptyList());
        
        String apTransId = "A" + System.currentTimeMillis();
        String msisdn    = "+35847001001";
        
        // Create DataToBeSigned
        DTBS dtbs = new DTBS("sign this", DTBS.ENCODING_UTF8);
        
        MSSSignatureReq sigReq = etsiClient.createSignatureRequest(apTransId, // AP Transaction ID
                                                                   msisdn,    // MSISDN
                                                                   dtbs,      // Data to be signed
                                                                   null,      // Data to be displayed
                                                                   SignatureProfiles.FICOM_SIGNATURE, // Signature profile
                                                                   MSS_Formats.PKCS7,                 // MSS Format
                                                                   MessagingModeType.SYNCH);          // Messaging Mode
                                                                    
        
        MSSSignatureResp sigResp = null;
        
        try {
            sigResp = etsiClient.send(sigReq);
        } catch(AxisFault af) {
            log.error("Got SOAP fault", af);
            return;
        } catch(IOException ioe) {
            log.error("Got IOException ", ioe);
            return;
        }
        
        log.info("Got response");
        log.info("  StatusCode   : " + sigResp.getStatus().getStatusCode().getValue());
        log.info("  StatusMessage: " + sigResp.getStatus().getStatusMessage());
    }
    
}
