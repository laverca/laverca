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
import java.util.ArrayList;
import java.util.List;

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
import fi.laverca.util.XmlDsigUtil;
import fi.laverca.util.XmlDsigUtil.ValidationException;

/**
 * Example FiCom application for demonstrating authentication.
 * 
 * <ul>
 * <li>Requests all supported PersonID attributes from the user
 * <li>Requests validation
 * </ul>
 * 
 * <p>Uses the following FiCom AdditionalServices:
 * <ul>
 * <li>EventID
 * <li>NoSpamCode
 * <li>PersonID
 * <li>Validate
 * </ul>
 * 
 */
public class Authentication {

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

        // Initialize services
        AdditionalServiceType eventIdService = FiComAdditionalServices.createEventIdService(eventId);
        AdditionalServiceType noSpamService  = FiComAdditionalServices.createNoSpamService("A12", false);
        List<AdditionalServiceType> additionalServices = createAdditionalServices();

        // Create response handler
        FiComResponseHandler handler = new FiComResponseHandler() {
            
            @Override
            public void onResponse(final FiComRequest req, final FiComResponse resp) {
                System.out.println("Got response");
                System.out.println("  StatusCode   : " + resp.getStatusCode());
                System.out.println("  StatusMessage: " + resp.getStatusMessage());
                
                if (resp.getPersonIdAttributes() != null) {
                    System.out.println("PersonID attributes:");
                    for (PersonIdAttribute a : resp.getPersonIdAttributes()) {
                        System.out.println("  " + a.getName() + " " + a.getStringValue());
                    }
                    
                    // Validate XML Signature
                    try {
                        XmlDsigUtil.validate(resp);
                    } catch (ValidationException e) {
                        System.out.println("Invalid XML signature!");
                    } catch (Exception e) {
                        System.out.println("Unable to validate the XML signature:");
                        e.printStackTrace();
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
                System.out.println("Got progress update");
            }
        };
        
        try {
            System.out.println("Authenticating");
            client.authenticate(apTransId, 
                                authnChallenge, 
                                msisdn, 
                                noSpamService, 
                                eventIdService,
                                additionalServices, 
                                handler);

        } catch (IOException ioe) {
            System.out.println("Error establishing connection:");
            ioe.printStackTrace();
        }

        // Kill the thread pool - otherwise this example would wait 60 seconds for the thread to die
        client.shutdown();
    }

    /**
     * Create AdditionalServices for:
     * <ul>
     * <li>PersonID
     * <li>Validate
     * </ul>
     * @return list of AdditionalServices
     */
    private static List<AdditionalServiceType> createAdditionalServices() {

        List<AdditionalServiceType> additionalServices = new ArrayList<>();
        List<String> attributeNames = new ArrayList<>(); 
 
        attributeNames.add(FiComAdditionalServices.PERSON_ID_VALIDUNTIL);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_ADDRESS);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_AGE);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_SUBJECT);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_SURNAME);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_GIVENNAME);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_HETU);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_SATU);
        
        AdditionalServiceType personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        AdditionalServiceType validateService = FiComAdditionalServices.createValidateService();
        additionalServices.add(personIdService);
        additionalServices.add(validateService);

        return additionalServices;
    }

}

