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
import java.util.Properties;

import org.apache.axis.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;
import org.etsi.uri.TS102204.v1_1_2.types.MessagingModeType;

import fi.laverca.DTBS;
import fi.laverca.JvmSsl;
import fi.laverca.etsi.EtsiClient;
import fi.laverca.examples.util.ExampleConf;
import fi.laverca.ficom.FiComMSS_Formats;
import fi.laverca.ficom.FiComSignatureProfiles;

/**
 * Simple signature request example
 * 
 * <ul>
 * <li>Uses EtsiClient instead of FiComClient
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
        log.info("Setting up ssl");
        JvmSsl.setSSL(properties.getProperty(ExampleConf.TRUSTSTORE_FILE),
                      properties.getProperty(ExampleConf.TRUSTSTORE_PASSWORD),
                      properties.getProperty(ExampleConf.KEYSTORE_FILE),
                      properties.getProperty(ExampleConf.KEYSTORE_PASSWORD),
                      properties.getProperty(ExampleConf.KEYSTORE_TYPE));
        
        String apId  = properties.getProperty(ExampleConf.AP_ID);
        String apPwd = properties.getProperty(ExampleConf.AP_PASSWORD);
        
        // Setup service URLs
        String msspSignatureUrl    = properties.getProperty(ExampleConf.SIGNATURE_URL);
        String msspStatusUrl       = properties.getProperty(ExampleConf.STATUS_URL);
        String msspReceiptUrl      = properties.getProperty(ExampleConf.RECEIPT_URL);
        
        // Create client
        EtsiClient etsiClient = new EtsiClient(apId, 
                                               apPwd, 
                                               msspSignatureUrl, 
                                               msspStatusUrl, 
                                               msspReceiptUrl);

        String apTransId = "A" + System.currentTimeMillis();
        String msisdn    = "+35847001001";
        
        // Create DataToBeSigned
        DTBS dtbs = new DTBS("sign this", DTBS.ENCODING_UTF8);
                        
        MSS_SignatureReq sigReq = etsiClient.createSignatureRequest(apTransId, // AP Transaction ID
                                                                    msisdn,    // MSISDN
                                                                    dtbs,      // Data to be signed
                                                                    null,      // Data to be displayed
                                                                    FiComSignatureProfiles.SIGNATURE, // Signature profile
                                                                    FiComMSS_Formats.PKCS7,  // MSS Format
                                                                    MessagingModeType.SYNCH  // Messaging Mode
                                                                    );
        
        MSS_SignatureResp sigResp = null;
        
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
