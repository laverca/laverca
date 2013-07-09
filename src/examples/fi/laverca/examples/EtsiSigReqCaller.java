/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2013 Laverca Project
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
import fi.laverca.EtsiClient;
import fi.laverca.FiComMSS_Formats;
import fi.laverca.FiComSignatureProfiles;

public class EtsiSigReqCaller {
    
    private static final Log log = LogFactory.getLog(EtsiSigReqCaller.class);

    public static void main(String[] args) {
       
        Properties properties = ExampleConf.getProperties();
        
        String apId  = properties.getProperty(ExampleConf.AP_ID);
        String apPwd = properties.getProperty(ExampleConf.AP_PASSWORD);
        
        String msspSignatureUrl    = properties.getProperty(ExampleConf.SIGNATURE_URL);
        String msspStatusUrl       = properties.getProperty(ExampleConf.STATUS_URL);
        String msspReceiptUrl      = properties.getProperty(ExampleConf.RECEIPT_URL);
        String msspRegistrationUrl = "http://nevermind";
        String msspProfileUrl      = "http://nevermind";
        String msspHandshakeUrl    = "http://nevermind";
        
        EtsiClient etsiClient = new EtsiClient(apId, 
                                               apPwd, 
                                               msspSignatureUrl, 
                                               msspStatusUrl, 
                                               msspReceiptUrl, 
                                               msspRegistrationUrl, 
                                               msspProfileUrl, 
                                               msspHandshakeUrl);

        String apTransId = "A"+System.currentTimeMillis();
        String msisdn = "+35847001001";
        DTBS dtbs = new DTBS("sign this", DTBS.ENCODING_UTF8);
        String dataToBeDisplayed = null;
        String signatureProfile = FiComSignatureProfiles.SIGNATURE;
        String mss_format = FiComMSS_Formats.PKCS7;
        MessagingModeType messagingMode = MessagingModeType.SYNCH;
        
        MSS_SignatureReq sigReq = etsiClient.createSignatureRequest(apTransId, 
                                                                    msisdn, 
                                                                    dtbs, 
                                                                    dataToBeDisplayed, 
                                                                    signatureProfile, 
                                                                    mss_format, 
                                                                    messagingMode);

        MSS_SignatureResp sigResp = null;
        try {
            sigResp = etsiClient.send(sigReq);
        }
        catch(AxisFault af) {
            log.error("got soap fault", af);
            return;
        }
        catch(IOException ioe) {
            log.error("got IOException ", ioe);
            return;
        }
        
        log.info("got resp");
        log.info(" statuscode " + sigResp.getStatus().getStatusCode().getValue());
        log.info(" signature  " + sigResp.getMSS_Signature());
    }

}
