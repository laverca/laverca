/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2014 Laverca Project
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
import java.util.LinkedList;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.laverca.DTBS;
import fi.laverca.JvmSsl;
import fi.laverca.ProgressUpdate;
import fi.laverca.examples.gui.AuthnGUI;
import fi.laverca.examples.util.ExampleConf;
import fi.laverca.ficom.FiComAdditionalServices;
import fi.laverca.ficom.FiComClient;
import fi.laverca.ficom.FiComRequest;
import fi.laverca.ficom.FiComResponse;
import fi.laverca.ficom.FiComResponseHandler;

/**
 * Example application for demonstrating anonymous authentication.
 * 
 * <ul>
 * <li>Creates a swing UI 
 * <li>Requests the user's gender
 * </ul>
 * 
 * <p>Uses the following FiCom AdditionalServices:
 * <ul>
 * <li>EventID
 * <li>NoSpamCode
 * <li>PersonID
 * </ul>
 * 
 */
public class AnonAuthentication extends AuthnGUI {

    private static final Log log = LogFactory.getLog(AnonAuthentication.class);
    
    
    private FiComClient  client;
    private FiComRequest req;
    private Properties   conf;

    /**
     * Initializes this example
     * <ul>
     * <li>Reads configuration
     * <li>Creates a new FiComClient
     * <li>Sets up SSL
     * </ul>
     */
    private void init() {
        
        this.conf = ExampleConf.getProperties();
        
        log.info("Setting up ssl");
        JvmSsl.setSSL(this.conf.getProperty(ExampleConf.TRUSTSTORE_FILE),
                      this.conf.getProperty(ExampleConf.TRUSTSTORE_PASSWORD),
                      this.conf.getProperty(ExampleConf.KEYSTORE_FILE),
                      this.conf.getProperty(ExampleConf.KEYSTORE_PASSWORD),
                      this.conf.getProperty(ExampleConf.KEYSTORE_TYPE));
        
        final String apId  = this.conf.getProperty(ExampleConf.AP_ID);
        final String apPwd = this.conf.getProperty(ExampleConf.AP_PASSWORD);

        final String msspSignatureUrl = this.conf.getProperty(ExampleConf.SIGNATURE_URL);
        final String msspStatusUrl    = this.conf.getProperty(ExampleConf.STATUS_URL);
        final String msspReceiptUrl   = this.conf.getProperty(ExampleConf.RECEIPT_URL);

        log.info("Creating FiComClient");
        this.client = new FiComClient(apId, 
                                      apPwd, 
                                      msspSignatureUrl, 
                                      msspStatusUrl, 
                                      msspReceiptUrl);
    }
    
    
    /**
     * Authenticates to the MSSP with the FiCom anonymous profile
     * 
     * @param phoneNumber MSISDN of the user
     */
    @Override
    public void authenticate(final String phoneNumber) {
        
        // Generate IDs
        final Long currentTimeMillis = System.currentTimeMillis();
        final String apTransId = "A" + currentTimeMillis;
        final String eventId   = "A" + currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
        
        byte[] authnChallenge = new DTBS(apTransId, DTBS.ENCODING_UTF8).toBytes();

        // Add additional services
        LinkedList<Service> additionalServices = new LinkedList<Service>();        
        
        Service eventIdService  = FiComAdditionalServices.createEventIdService(eventId);
        Service noSpamService   = FiComAdditionalServices.createNoSpamService(FIXED_NOSPAMCODE, false);
        Service personIdService = FiComAdditionalServices.createPersonIdService(FiComAdditionalServices.PERSON_ID_GENDER);

        additionalServices.add(personIdService);
        
        // Create response handler
        FiComResponseHandler handler = new FiComResponseHandler() {
            @Override
            public void onResponse(FiComRequest req, FiComResponse resp) {
                log.info("Got response");
                displayResponse(eventId, resp, null);
            }

            @Override
            public void onError(FiComRequest req, Throwable throwable) {
                log.info("Got error");
                displayResponse(eventId, null, throwable);
            }

            @Override
            public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate) {
                log.info("Got progress update");
            }
        };
        
        // Attempt to authenticate
        try {
            log.info("Authenticating");
            this.req = client.authenticateAnon(apTransId, 
                                               authnChallenge, 
                                               phoneNumber, 
                                               noSpamService, 
                                               eventIdService,
                                               additionalServices, 
                                               handler);
            
            // Display Event ID
            this.displayResponse(eventId, null, null);
        } catch (IOException ioe) {
            this.displayResponse(eventId, null, ioe);
            log.info("Error establishing connection: " + ioe.getMessage());
        }

    }
    
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        AnonAuthentication main = new AnonAuthentication();
        main.init();
        main.initGUI(main.client, 
                     main.req, 
                     "Anon Authentication");
    }

}
