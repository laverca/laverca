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
import fi.laverca.FiComAdditionalServices;
import fi.laverca.FiComAdditionalServices.PersonIdAttribute;
import fi.laverca.FiComClient;
import fi.laverca.FiComRequest;
import fi.laverca.FiComResponse;
import fi.laverca.FiComResponseHandler;
import fi.laverca.JvmSsl;
import fi.laverca.ProgressUpdate;

/**
 * Sample for simple authentication. Takes parameters.
 */
public class PersonIdCaller {
	
    private static final Log log = LogFactory.getLog(PersonIdCaller.class);
    private static final String CONFIG_LOCATION = "fi/laverca/samples/configuration.xml";

    /**
     * @param args
     */
    public static void main(String[] args) {
    	        
        Properties properties = ExampleConf.getProperties();
        
        log.info("setting up ssl");
        JvmSsl.setSSL(properties.getProperty(ExampleConf.TRUSTSTORE_FILE),
                properties.getProperty(ExampleConf.TRUSTSTORE_PASSWORD),
                properties.getProperty(ExampleConf.KEYSTORE_FILE),
                properties.getProperty(ExampleConf.KEYSTORE_PASSWORD),
                properties.getProperty(ExampleConf.KEYSTORE_TYPE));
        
        String apId  = properties.getProperty(ExampleConf.AP_ID);
        String apPwd = properties.getProperty(ExampleConf.AP_PASSWORD);

        String msspSignatureUrl    = properties.getProperty(ExampleConf.SIGNATURE_URL);
        String msspStatusUrl       = properties.getProperty(ExampleConf.STATUS_URL);
        String msspReceiptUrl      = properties.getProperty(ExampleConf.RECEIPT_URL);

        log.info("creating FiComClient");
        FiComClient fiComClient = new FiComClient(apId, 
                                                  apPwd, 
                                                  msspSignatureUrl, 
                                                  msspStatusUrl, 
                                                  msspReceiptUrl); 
        
        Long currentTimeMillis = System.currentTimeMillis();
        String apTransId = "A"+currentTimeMillis;
        final String eventId = "A"+ currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
        
        byte[] authnChallenge = new DTBS(apTransId, DTBS.ENCODING_UTF8).toBytes();
        
        Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);
        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        LinkedList<Service> additionalServices = new LinkedList<Service>();
        LinkedList<String> attributeNames = new LinkedList<String>();
        
        boolean performCall = true;
        String phoneNumber = "+35847001001";
        
    	for (int i = 0; i < args.length; i++) {
    		if (args[i].equals("--help") || args[i].equals("-help")) {
    			System.out.println("\n-n <number>	Default +35847001001" +
    					"\n-validuntil	add attribute PERSON_ID_VALIDUNTIL" +
    					"\n-address	add attribute PERSON_ID_ADDRESS" +
    					"\n-age		add attribute PERSON_ID_AGE" +
    					"\n-subject	add attribute PERSON_ID_SUBJECT" +
    					"\n-surname	add attribute PERSON_ID_SURNAME" +
    					"\n-givenname	add attribute PERSON_ID_GIVENNAME" +
    					"\n-hetu		add attribute PERSON_ID_HETU" +
    					"\n-satu		add attribute PERSON_ID_SATU");
    			performCall = false;
    		} else if (args[i].equals("-n")) {
    			phoneNumber = args[i+1];
    		} else if (args[i].equals("-validuntil")) {
    			attributeNames.add(FiComAdditionalServices.PERSON_ID_VALIDUNTIL);
    		} else if (args[i].equals("-address")) {
    			attributeNames.add(FiComAdditionalServices.PERSON_ID_ADDRESS);
    		} else if (args[i].equals("-age")) {
    			 attributeNames.add(FiComAdditionalServices.PERSON_ID_AGE);
    		} else if (args[i].equals("-subject")) {
    			attributeNames.add(FiComAdditionalServices.PERSON_ID_SUBJECT);
    		} else if (args[i].equals("-surname")) {
    			attributeNames.add(FiComAdditionalServices.PERSON_ID_SURNAME);
    		} else if (args[i].equals("-givenname")) {
    			attributeNames.add(FiComAdditionalServices.PERSON_ID_GIVENNAME);
    		} else if (args[i].equals("-hetu")) {
    			attributeNames.add(FiComAdditionalServices.PERSON_ID_HETU);
    		} else if (args[i].equals("-satu")) {
    			attributeNames.add(FiComAdditionalServices.PERSON_ID_SATU);
    		}
    	}
        
        Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        additionalServices.add(personIdService);
        
        if (performCall) {
        	try {
        		log.info("calling authenticate");
        		fiComClient.authenticate(apTransId, 
        				authnChallenge, 
        				phoneNumber, 
        				noSpamService,
        				eventIdService,
        				additionalServices, 
        				new FiComResponseHandler() {
        			@Override
        			public void onResponse(FiComRequest req, FiComResponse resp) {
        				log.info("got resp");
        				log.info(resp.getPkcs7Signature().getSignerCn());
        				for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
        					log.info(a.getName().substring(a.getName().indexOf('#')+1) + " " + a.getStringValue());
        				}
        			}

        			@Override
        			public void onError(FiComRequest req, Throwable throwable) {
        				log.info("got error", throwable);
        			}

        			@Override
        			public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate) {

        			}
        		});
        	}
        	catch (IOException e) {
        		log.info("error establishing connection", e);
        	}
        }

        fiComClient.shutdown();
        
    }

}
