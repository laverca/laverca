package fi.laverca.samples;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
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
 * 
 * @author Jan Mikael Lindlöf (mikael.lindlof@nbl.fi)
 * @author Eemeli Miettinen (eemeli.miettinen@methics.fi)
 */
public class PersonIdCaller {
	
    private static final Log log = LogFactory.getLog(PersonIdCaller.class);
    private static final String CONFIG_LOCATION = "fi/laverca/samples/configuration.xml";

    /**
     * @param args
     */
    public static void main(String[] args) {
    	
    	XMLConfiguration config = null;
		try {
		    config = new XMLConfiguration(CONFIG_LOCATION);
		} catch(ConfigurationException e) {
		    log.info("configuration file not found", e);
		}
		
		log.info("setting up ssl");
		JvmSsl.setSSL(config.getString("ssl.trustStore"),
				config.getString("ssl.trustStorePassword"),
				config.getString("ssl.keyStore"),
				config.getString("ssl.keyStorePassword"),
				config.getString("ssl.keyStoreType"));
		
		String apId  = config.getString("ap.apId");
        String apPwd = config.getString("ap.apPwd");

        String msspSignatureUrl    = config.getString("mssp.msspSignatureUrl");
        String msspStatusUrl       = config.getString("mssp.msspStatusUrl");
        String msspReceiptUrl      = config.getString("mssp.msspReceiptUrl");

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
