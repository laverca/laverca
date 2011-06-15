package fi.laverca.samples;

import java.io.IOException;

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

    /**
     * @param args
     */
    public static void main(String[] args) {
       
        //String apId  = "http://my.ap.id";
        //String apPwd = "my-ap-pwd";

        //TODO: Use your AP identity
        String apId  = "http://ap1.methics.fi";
        String apPwd = "ap1pass";
        
        String aeMsspIdUri = "http://ae1.telco.fi";
        
        //TODO: Use your own addresses
        String msspSignatureUrl    = "http://pavo.methics.fi:50080/soap/services/MSS_SignaturePort";
        String msspStatusUrl       = "http://pavo.methics.fi:50080/soap/services/MSS_StatusQueryPort";
        String msspReceiptUrl      = "http://pavo.methics.fi:50080/soap/services/MSS_ReceiptPort";
        String msspRegistrationUrl = "http://nevermind";
        String msspProfileUrl      = "http://nevermind";
        String msspHandshakeUrl    = "http://nevermind";
        
        EtsiClient etsiClient = new EtsiClient(apId, 
                                               apPwd, 
                                               aeMsspIdUri, 
                                               msspSignatureUrl, 
                                               msspStatusUrl, 
                                               msspReceiptUrl, 
                                               msspRegistrationUrl, 
                                               msspProfileUrl, 
                                               msspHandshakeUrl);

        String apTransId = "A"+System.currentTimeMillis();
        String msisdn = "+35847001001";
        DTBS dtbs = new DTBS("sign this", "UTF-8");
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
