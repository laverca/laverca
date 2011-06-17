package fi.laverca;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.axis.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.MSS_Signature;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusResp;
import org.etsi.uri.TS102204.v1_1_2.Service;
import org.etsi.uri.TS102204.v1_1_2.Status;
import org.etsi.uri.TS102204.v1_1_2.StatusCode;
import org.etsi.uri.TS102204.v1_1_2.types.MessagingModeType;

/**  
 * An asynchronous client for FiCom -style signature requests.
 */
public class FiComClient {
    private static final Log log = LogFactory.getLog(FiComClient.class);

    EtsiClient etsiClient;
    ExecutorService threadExecutor; 

    public FiComClient( String apId,             // AP settings
                        String apPwd, 
                        String aeMsspIdUri,      // MSSP_ID.URI of the AE
                        String msspSignatureUrl, // AE connection settings
                        String msspStatusUrl,
                        String msspReceiptUrl
    )
    throws IllegalArgumentException
    {
        String msspRegistrationUrl = "http://localhost";
        String msspProfileUrl      = "http://localhost";
        String msspHandshakeUrl    = "http://localhost";
        this.etsiClient = new EtsiClient(apId, 
                                         apPwd, 
                                         aeMsspIdUri, 
                                         msspSignatureUrl, 
                                         msspStatusUrl, 
                                         msspReceiptUrl, 
                                         msspRegistrationUrl, 
                                         msspProfileUrl, 
                                         msspHandshakeUrl);

        this.threadExecutor = Executors.newCachedThreadPool();
    }

    public FiComRequest authenticate(final String apTransId,
                                     final byte[] authnChallenge,
                                     final String phoneNumber,
                                     final Service noSpamService,
                                     final List<Service> additionalServices,
                                     final FiComResponseHandler handler) 
    throws IOException
    {

        return call(apTransId, 
                    new DTBS(authnChallenge),
                    phoneNumber,
                    noSpamService,
                    additionalServices,
                    FiComSignatureProfiles.AUTHENTICATION,
                    FiComMSS_Formats.PKCS7,
                    handler);

    }

    public FiComRequest authenticateAnon(final String apTransId,
                                         final byte[] authnChallenge,
                                         final String phoneNumber,
                                         final Service noSpamService,
                                         final List<Service> additionalServices,
                                         final FiComResponseHandler handler) 
    throws IOException
    {

        return call(apTransId, 
                    new DTBS(authnChallenge),
                    phoneNumber,
                    noSpamService,
                    additionalServices,
                    FiComSignatureProfiles.ANONYMOUS,
                    FiComMSS_Formats.PKCS7,
                    handler);

    }

    public FiComRequest signText(final String apTransId,
                                 final String textToBeSigned,
                                 final String phoneNumber,
                                 final Service noSpamService,
                                 final List<Service> additionalServices,
                                 final FiComResponseHandler handler) 
    throws IOException
    {

        return call(apTransId, 
                    new DTBS(textToBeSigned, "UTF-8"),
                    phoneNumber,
                    noSpamService,
                    additionalServices,
                    FiComSignatureProfiles.SIGNATURE,
                    FiComMSS_Formats.PKCS7,
                    handler);

    }

    public FiComRequest signData(final String apTransId,
                                 final byte [] digestToBeSigned,
                                 final String phoneNumber,
                                 final Service noSpamService,
                                 final List<Service> additionalServices,
                                 final FiComResponseHandler handler) 
    throws IOException
    {

        return call(apTransId, 
                    new DTBS(digestToBeSigned, DTBS.MIME_SHA1),
                    phoneNumber,
                    noSpamService,
                    additionalServices,
                    FiComSignatureProfiles.DIGESTIVE,
                    FiComMSS_Formats.PKCS7,
                    handler);

    }

    public FiComRequest consent(final String apTransId,
                                 final String textToBeConsentedTo,
                                 final String phoneNumber,
                                 final Service noSpamService,
                                 final List<Service> additionalServices,
                                 final FiComResponseHandler handler) 
    throws IOException
    {

        return call(apTransId, 
                    new DTBS(textToBeConsentedTo, "UTF-8"),
                    phoneNumber,
                    noSpamService,
                    additionalServices,
                    FiComSignatureProfiles.CONSENT,
                    FiComMSS_Formats.PKCS7,
                    handler);

    }


    public FiComRequest call(final String apTransId,
                             final DTBS dtbs,
                             final String phoneNumber,
                             final Service noSpamService,
                             final List<Service> additionalServices,
                             final String signatureProfile,
                             final String mssFormat,
                             final FiComResponseHandler handler) 
    throws IOException
    {

        if(handler == null) {
            throw new IOException("null response handler not allowed.");
        }

        final FiComRequest fiReq = new FiComRequest();

        String msisdn = phoneNumber; //consider using some kind of normalizer
        String dataToBeDisplayed = null;
        String mss_format = mssFormat;
        MessagingModeType messagingMode = MessagingModeType.ASYNCHCLIENTSERVER;

        MSS_SignatureReq sigReq = etsiClient.createSignatureRequest(apTransId, 
                                                                    msisdn, 
                                                                    dtbs, 
                                                                    dataToBeDisplayed, 
                                                                    signatureProfile, 
                                                                    mss_format, 
                                                                    messagingMode);
        fiReq.sigReq = sigReq;

        sigReq.getAdditionalServices().addService(noSpamService);
        if(additionalServices != null) {
            for(Service s : additionalServices) {
                if(s != null)
                    sigReq.getAdditionalServices().addService(s);
            }
        }

        MSS_SignatureResp sigResp = null;
        try {
            log.debug("sending sigReq");
            sigResp = etsiClient.send(sigReq);
            log.debug("got resp");
            fiReq.sigResp = sigResp;
        }
        catch(AxisFault af) {
            log.error("got soap fault", af);
            throw af;
            //handler.onError(fiReq, af);
        }
        catch(IOException ioe) {
            log.error("got IOException ", ioe);
            throw ioe;
            //handler.onError(fiReq, ioe);
        }

        final MSS_SignatureResp fSigResp = sigResp;
        FutureTask<FiComResponse> ft = 
            new FutureTask<FiComResponse>(new Callable<FiComResponse>() {
                public FiComResponse call() throws Exception {
                	
                    long timeout = 5*60*1000; // 5 min = 300 s = 300 000 millis
                    // note that the transaction generally times out at the server at 180 s 
                    long deadline = System.currentTimeMillis() + timeout;
                    
                    FiComResponse fiResp = null;
                    ProgressUpdate prgUpdate = new ProgressUpdate(timeout);

                    MSS_StatusResp statResp = null;
                    int waitPeriod = 20 * 1000; // initial wait 20 s as per FiCom, section 5.1
                    LOOP: while(true) {
                        Thread.sleep(waitPeriod);
                        waitPeriod = 5 * 1000; // subsequent wait 5 s as per FiCom, section 5.1
                        long now = System.currentTimeMillis();
                        if (now > deadline) {
                            try {
                                handler.onError(fiReq, new FiComException("timed out"));
                            }
                            finally {
                                break LOOP;
                            }
                        }

                        MSS_StatusReq  statReq = etsiClient.createStatusRequest(fSigResp, apTransId);
                        try {
                            log.debug("sending statReq");
                            statResp = etsiClient.send(statReq);
                            log.debug("got statResp");

                            boolean done = isDone(statResp);
                            long statusCode = parseStatus(statResp.getStatus());

                            if(done) {
                                log.debug("got a final statResp. Ending the wait.");
                                fiResp = new FiComResponse(statResp);
                                try {
                                    handler.onResponse(fiReq, fiResp);
                                }
                                finally {
                                    break LOOP;
                                }
                            }
                            else if(504 == statusCode) { // OUTSTANDING_TRANSACTION
                                log.debug("got an outstanding statResp. Continuing to wait for a final answer.");
                                handler.onOutstandingProgress(fiReq, prgUpdate);
                                continue LOOP;
                            }
                            else {
                                log.debug("got an abnormal statResp. Ending the wait.");
                                //throw new FiComException("abnormal status code");
                                FiComException fe = new FiComException("abnormal status code");
                                try {
                                    handler.onError(fiReq, fe);
                                }
                                finally {
                                    break LOOP;
                                }
                            }

                        }
                        catch(AxisFault af) {
                            log.error("got soap fault", af);
                            //throw af;
                            try {
                                handler.onError(fiReq, af);
                            }
                            finally {
                                break LOOP;
                            }
                        }
                        catch(IOException ioe) {
                            log.error("got IOException ", ioe);
                            //throw ioe;
                            try {
                                handler.onError(fiReq, ioe);
                            }
                            finally {
                                break LOOP;
                            }
                        }
                    }

                    return fiResp;
                }
            });
        fiReq.ft = ft;

        log.debug("starting calling");
        this.threadExecutor.execute(ft);

        return fiReq;

    }

    static final long NO_STATUS = -1; // -1 is not used by ETSI or by FiCom
    long parseStatus(Status status) {
        if(status == null)
            return NO_STATUS;
        StatusCode sc = status.getStatusCode();
        if(sc == null)
            return NO_STATUS;
        return sc.getValue();
    }

    static boolean isDone(MSS_StatusResp statResp) {
        if(statResp == null)
            return false;
        MSS_Signature sig = statResp.getMSS_Signature();
        return sig != null;
    }

    public void shutdown() {
        this.threadExecutor.shutdown();
    }

}
