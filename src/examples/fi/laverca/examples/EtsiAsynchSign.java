package fi.laverca.examples;
import java.io.IOException;
import java.util.Properties;

import org.apache.axis.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.types.MessagingModeType;

import fi.laverca.MSS_Formats;
import fi.laverca.ProgressUpdate;
import fi.laverca.SignatureProfiles;
import fi.laverca.etsi.EtsiClient;
import fi.laverca.etsi.EtsiRequest;
import fi.laverca.etsi.EtsiResponse;
import fi.laverca.etsi.EtsiResponseHandler;
import fi.laverca.examples.util.ExampleConf;
import fi.laverca.util.DTBS;
import fi.laverca.util.JvmSsl;


/**
 * Simple signature request example
 * 
 * <ul>
 * <li>Uses EtsiClient instead of FiComClient
 * <li>Sends a SignatureRequest with DTBS "sign this"
 * <li>Uses synchronous messaging mode
 * <li>No AdditionalServices
 * </ul>
 *
 */
public class EtsiAsynchSign {
    
    private static final Log log = LogFactory.getLog(EtsiSign.class);

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) {
       
        // Load properties
        Properties properties = ExampleConf.getProperties();
        
        // Setup SSL
        log.info("Setting up ssl");
        JvmSsl.setSSL(properties.getProperty(ExampleConf.TRUSTSTORE_FILE),
                      properties.getProperty(ExampleConf.TRUSTSTORE_PASSWORD),
                      properties.getProperty(ExampleConf.KEYSTORE_FILE),
                      properties.getProperty(ExampleConf.KEYSTORE_PASSWORD),
                      properties.getProperty(ExampleConf.KEYSTORE_TYPE));
        
        String apId  = properties.getProperty(ExampleConf.AP_ID);
        String apPwd = properties.getProperty(ExampleConf.AP_PASSWORD);
        
        // Setup service URLs
        String msspSignatureUrl    = properties.getProperty(ExampleConf.SIGNATURE_URL);
        String msspStatusUrl       = properties.getProperty(ExampleConf.STATUS_URL);
        String msspReceiptUrl      = properties.getProperty(ExampleConf.RECEIPT_URL);
        
        // Create client
        EtsiClient etsiClient = new EtsiClient(apId, 
                                               apPwd, 
                                               msspSignatureUrl, 
                                               msspStatusUrl, 
                                               msspReceiptUrl);

        String apTransId = "A" + System.currentTimeMillis();
        String msisdn    = "+35847001001";
        
        // Create DataToBeSigned
        DTBS dtbs = new DTBS("sign this", DTBS.ENCODING_UTF8);
                        
        EtsiRequest req = etsiClient.createRequest(apTransId, // AP Transaction ID
                                                   msisdn,    // MSISDN
                                                   dtbs,      // Data to be signed
                                                   dtbs.toString(),                   // Data to be displayed
                                                   null,                              // Additional services
                                                   SignatureProfiles.FICOM_SIGNATURE, // Signature profile
                                                   MSS_Formats.PKCS7,                 // MSS Format
                                                   MessagingModeType.ASYNCHCLIENTSERVER);
                                                                            
        // Create response handler
        EtsiResponseHandler handler = new EtsiResponseHandler() {
            
            @Override
            public void onResponse(EtsiRequest req, EtsiResponse resp) {
                log.info("Got response");
                log.info("  StatusCode   : " + resp.getStatusCode());
                log.info("  StatusMessage: " + resp.getStatusMessage());
            }

            @Override
            public void onError(EtsiRequest req, Throwable throwable) {
                log.info("Got an error");
                log.error("", throwable);
            }

            @Override
            public void onOutstandingProgress(EtsiRequest req, ProgressUpdate prgUpdate) {
                log.info("Got progress update");
            }
        };
        
        try {
            etsiClient.call(req, handler);
        } catch(AxisFault af) {
            log.error("Got SOAP fault", af);
            return;
        } catch(IOException ioe) {
            log.error("Got IOException ", ioe);
            return;
        }
        
    }

}