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

package fi.laverca.samples;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.laverca.DTBS;
import fi.laverca.FiComAdditionalServices;
import fi.laverca.FiComClient;
import fi.laverca.FiComRequest;
import fi.laverca.FiComResponse;
import fi.laverca.FiComResponseHandler;
import fi.laverca.JvmSsl;
import fi.laverca.ProgressUpdate;

public class FiComSigReqCaller {
	
    private static final Log log = LogFactory.getLog(FiComSigReqCaller.class);
    private static final String CONFIG_LOCATION = "fi/laverca/samples/configuration.xml";

    /**
     * @param args
     */
    public static void main(String[] args) {

        
        Properties properties = SampleConf.getProperties();
        
        log.info("setting up ssl");
        JvmSsl.setSSL(properties.getProperty(SampleConf.TRUSTSTORE_FILE),
                properties.getProperty(SampleConf.TRUSTSTORE_PASSWORD),
                properties.getProperty(SampleConf.KEYSTORE_FILE),
                properties.getProperty(SampleConf.KEYSTORE_PASSWORD),
                properties.getProperty(SampleConf.KEYSTORE_TYPE));
        
        String apId  = properties.getProperty(SampleConf.AP_ID);
        String apPwd = properties.getProperty(SampleConf.AP_PASSWORD);

        String msspSignatureUrl    = properties.getProperty(SampleConf.SIGNATURE_URL);
        String msspStatusUrl       = properties.getProperty(SampleConf.STATUS_URL);
        String msspReceiptUrl      = properties.getProperty(SampleConf.RECEIPT_URL);

        log.info("creating FiComClient");
        FiComClient fiComClient = new FiComClient(apId, 
                                                  apPwd, 
                                                  msspSignatureUrl, 
                                                  msspStatusUrl, 
                                                  msspReceiptUrl); 

        Long currentTimeMillis = System.currentTimeMillis();
        String apTransId = "A"+currentTimeMillis;
        final String eventId = "A"+ currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
        
        String phoneNumber = "+35847001001";
        byte[] authnChallenge = new DTBS(apTransId, DTBS.ENCODING_UTF8).toBytes();

        Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);
        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        
        try {
            log.info("calling authenticate");
                fiComClient.authenticate(apTransId, 
                                         authnChallenge, 
                                         phoneNumber, 
                                         noSpamService,
                                         eventIdService,
                                         null, // additionalServices, 
                                         new FiComResponseHandler() {
                                             @Override
                                             public void onResponse(FiComRequest req, FiComResponse resp) {
                                                 log.info("got resp");
                                                 log.info(resp.getPkcs7Signature().getSignerCn());
                                             }

                                             @Override
                                             public void onError(FiComRequest req, Throwable throwable) {
                                                 log.info("got error", throwable);
                                             }

											@Override
											public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate) {
												log.debug("got progress update");
											}
                                         });
        }
        catch (IOException e) {
            log.info("error establishing connection", e);
        }

        fiComClient.shutdown();
        
    }

}
