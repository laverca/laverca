package fi.laverca.samples;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
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
