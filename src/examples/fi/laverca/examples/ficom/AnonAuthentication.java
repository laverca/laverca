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

package fi.laverca.examples.ficom;

import java.io.IOException;
import java.util.Collections;

import javax.net.ssl.SSLSocketFactory;

import fi.laverca.ProgressUpdate;
import fi.laverca.ficom.FiComAdditionalServices;
import fi.laverca.ficom.FiComClient;
import fi.laverca.ficom.FiComRequest;
import fi.laverca.ficom.FiComResponse;
import fi.laverca.ficom.FiComResponseHandler;
import fi.laverca.ficom.PersonIdAttribute;
import fi.laverca.jaxb.mss.AdditionalServiceType;
import fi.laverca.mss.MssClient;
import fi.laverca.mss.MssConf;
import fi.laverca.util.DTBS;

/**
 * Example FiCom application for demonstrating anonymous authentication.
 * Requests the user's gender.
 * 
 * <p>Uses the following FiCom AdditionalServices:
 * <ul>
 * <li>EventID
 * <li>NoSpamCode
 * <li>PersonID
 * </ul>
 * 
 */
public class AnonAuthentication {    


    public static void main(final String[] args) {
        
        String msisdn = "+35847001001";
        if (args.length > 0) {
            msisdn = args[0];
        }
        
        MssConf conf = MssConf.fromPropertyFile("conf/examples.conf");
                
        // Setup SSL
        SSLSocketFactory ssf = null;
        try {
            System.out.println("Setting up ssl");
            ssf = MssClient.createSSLFactory(conf.getKeystore(),
                                             conf.getKeystorePwd(),
                                             conf.getKeystoreType(),
                                             conf.getTruststore(),
                                             conf.getTruststorePwd(),
                                             conf.getTruststoreType());
        } catch (Exception e) {
            System.out.println("TLS initialization problem: " + e.getMessage());
            return;
        }

        System.out.println("Creating FiComClient");
        FiComClient client = new FiComClient(conf.getApId(), 
                                             conf.getApPwd(), 
                                             conf.getSignatureUrl(), 
                                             conf.getStatusUrl(), 
                                             conf.getReceiptUrl());
        
        client.setSSLSocketFactory(ssf);
        
        // Generate IDs
        final String millis    = ""  + System.currentTimeMillis();
        final String apTransId = "A" + millis;
        final String eventId   = "A" + millis.substring(millis.length()-4);

        byte[] authnChallenge = new DTBS(apTransId, DTBS.ENCODING_UTF8).toBytes();

        // Initialize additional services
        AdditionalServiceType eventIdService  = FiComAdditionalServices.createEventIdService(eventId);
        AdditionalServiceType noSpamService   = FiComAdditionalServices.createNoSpamService("A12", false);
        AdditionalServiceType personIdService = FiComAdditionalServices.createPersonIdService(FiComAdditionalServices.PERSON_ID_GENDER);
        
        // Initialize response handler
        FiComResponseHandler handler = new FiComResponseHandler() {
            @Override
            public void onResponse(final FiComRequest req, final FiComResponse resp) {
                System.out.println("Got a response:");
                System.out.println("  StatusCode   : " + resp.getStatusCode());
                System.out.println("  StatusMessage: " + resp.getStatusMessage());
                
                if (resp.getPersonIdAttributes() != null) {
                    System.out.println("PersonID attributes:");
                    for (PersonIdAttribute a : resp.getPersonIdAttributes()) {
                        System.out.println("  " + a.getName() + " " + a.getStringValue());
                    }
                } else {
                    System.out.println("No Person ID Attributes received");
                }
                
            }

            @Override
            public void onError(final FiComRequest req, final Throwable t) {
                System.out.println("Got an error");
                t.printStackTrace();
            }

            @Override
            public void onOutstandingProgress(final FiComRequest req, final ProgressUpdate update) {
                System.out.println("Got a progress update");
            }
        };
        
        // Attempt to authenticate
        try {
            System.out.println("Authenticating");
            client.authenticateAnon(apTransId, 
                                    authnChallenge, 
                                    msisdn, 
                                    noSpamService, 
                                    eventIdService,
                                    Collections.singletonList(personIdService), 
                                    handler);

        } catch (IOException ioe) {
            System.out.println("Error establishing connection:");
            ioe.printStackTrace();
        }
        
        // Kill the thread pool - otherwise this example would wait 60 seconds for the thread to die
        client.shutdown();
    }
    
}
