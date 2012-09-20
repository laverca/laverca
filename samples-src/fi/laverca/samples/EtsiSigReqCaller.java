package fi.laverca.samples;

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
       
        Properties properties = SampleConf.getProperties();
        
        String apId  = properties.getProperty(SampleConf.AP_ID);
        String apPwd = properties.getProperty(SampleConf.AP_PASSWORD);
        
        String msspSignatureUrl    = properties.getProperty(SampleConf.SIGNATURE_URL);
        String msspStatusUrl       = properties.getProperty(SampleConf.STATUS_URL);
        String msspReceiptUrl      = properties.getProperty(SampleConf.RECEIPT_URL);
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
