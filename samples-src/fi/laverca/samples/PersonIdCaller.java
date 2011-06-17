package fi.laverca.samples;

import java.io.IOException;
import java.util.LinkedList;

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

public class PersonIdCaller {
    private static final Log log = LogFactory.getLog(PersonIdCaller.class);

    /**
     * @param args
     */
    public static void main(String[] args) {

        log.info("setting up ssl");
        JvmSsl.setSSL("etc/laverca-truststore",
                      "changeit",
                      "etc/laverca-keystore",
                      "changeit",
                      "JKS");

        String apId  = "http://laverca-eval.fi";
        String apPwd = "pfkpfk";

        String aeMsspIdUri = "http://dev-ae.mssp.dna.fi";
        //TODO: TeliaSonera
        //TODO: Elisa

        String msspSignatureUrl    = "https://dev-ae.mssp.dna.fi/soap/services/MSS_SignaturePort";
        String msspStatusUrl       = "https://dev-ae.mssp.dna.fi/soap/services/MSS_StatusQueryPort";
        String msspReceiptUrl      = "https://dev-ae.mssp.dna.fi/soap/services/MSS_ReceiptPort";

        log.info("creating FiComClient");
        FiComClient fiComClient = new FiComClient(apId, 
                                                  apPwd, 
                                                  aeMsspIdUri, 
                                                  msspSignatureUrl, 
                                                  msspStatusUrl, 
                                                  msspReceiptUrl); 
        
        Long currentTimeMillis = System.currentTimeMillis();
        String apTransId = "A"+currentTimeMillis;
        final String eventId = "A"+ currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
        
        String phoneNumber = "+35847001001";
        byte[] authnChallenge = new DTBS(apTransId, "UTF-8").toBytes();
        
        Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);
        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        LinkedList<Service> additionalServices = new LinkedList<Service>();
        LinkedList<String> attributeNames = new LinkedList<String>();
        attributeNames.add(FiComAdditionalServices.PERSON_ID_HETU);
        //personIdAttrs.add(FiComAdditionalServices.PERSON_ID_ADDRESS);
        Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        additionalServices.add(personIdService);
        
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
                                                     log.info(a.getName() + " " + a.getStringValue());
                                                 }
                                             }

                                             @Override
                                             public void onError(FiComRequest req, Throwable throwable) {
                                                 log.info("got error", throwable);
                                             }

											@Override
											public void onOutstandingProgress(
													FiComRequest req,
													ProgressUpdate prgUpdate) {
												// TODO Auto-generated method stub
												
											}
                                         });
        }
        catch (IOException e) {
            log.info("error establishing connection", e);
        }

        fiComClient.shutdown();
        
    }

}
