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
import org.etsi.uri.TS102204.v1_1_2.MSS_ReceiptReq;
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
	
	private static final int INITIAL_WAIT    = 20 * 1000;      // Initial wait 20 s   as per MSS FiCom Implementation Guideline, section 5.1
	private static final int SUBSEQUENT_WAIT = 5  * 1000;      // Subsequent wait 5 s as per MSS FiCom Implementation Guideline, section 5.1
	private static final int TIMEOUT         = 5  * 1000 * 60; // Timeout 5 min       as per MSS FiCom Implementation Guideline, section 6.4
	
    private static final Log log = LogFactory.getLog(FiComClient.class);

    private EtsiClient etsiClient;
    private ExecutorService threadExecutor; 

    public FiComClient( String apId,             // AP settings
                        String apPwd, 
                        String msspSignatureUrl, // AE connection settings
                        String msspStatusUrl,
                        String msspReceiptUrl
    )
    throws IllegalArgumentException
    {
        this.etsiClient = new EtsiClient(apId, 
                                         apPwd, 
                                         msspSignatureUrl, 
                                         msspStatusUrl, 
                                         msspReceiptUrl);

        this.threadExecutor = Executors.newCachedThreadPool();
    }

    /**
     * Convenience method for sending an authentication request
     * 
     * @param apTransId AP Transaction ID
     * @param authnChallenge
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request.
     * @see #call(String, DTBS, String, Service, Service, List, String, String, FiComResponseHandler)
     */
    public FiComRequest authenticate(final String apTransId,
                                     final byte[] authnChallenge,
                                     final String phoneNumber,
                                     final Service noSpamService,
                                     final Service eventIdService,
                                     final List<Service> additionalServices,
                                     final FiComResponseHandler handler) 
    throws IOException
    {

        return call(apTransId, 
                    new DTBS(authnChallenge, DTBS.ENCODING_BASE64, DTBS.MIME_STREAM),
                    phoneNumber,
                    noSpamService,
                    eventIdService,
                    additionalServices,
                    FiComSignatureProfiles.AUTHENTICATION,
                    FiComMSS_Formats.PKCS7,
                    handler);

    }
    
    /**
     * Convenience method for sending an anonymous authentication request
     * 
     * @param apTransId AP Transaction ID
     * @param authnChallenge
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request. 
     * @see #call(String, DTBS, String, Service, Service, List, String, String, FiComResponseHandler)
     */
    public FiComRequest authenticateAnon(final String apTransId,
                                         final byte[] authnChallenge,
                                         final String phoneNumber,
                                         final Service noSpamService,
                                         final Service eventIDService,
                                         final List<Service> additionalServices,
                                         final FiComResponseHandler handler) 
    throws IOException
    {

        return call(apTransId, 
                    new DTBS(authnChallenge, DTBS.ENCODING_BASE64, DTBS.MIME_STREAM),
                    phoneNumber,
                    noSpamService,
                    eventIDService,
                    additionalServices,
                    FiComSignatureProfiles.ANONYMOUS,
                    FiComMSS_Formats.PKCS7,
                    handler);

    }

    /**
     * Convenience method for sending a text sig request
     * 
     * @param apTransId AP Transaction ID
     * @param textToBeSigned
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request.
     * @see #call(String, DTBS, String, Service, Service, List, String, String, FiComResponseHandler)
     */
    public FiComRequest signText(final String apTransId,
                                 final String textToBeSigned,
                                 final String phoneNumber,
                                 final Service noSpamService,
                                 final Service eventIDService,
                                 final List<Service> additionalServices,
                                 final FiComResponseHandler handler) 
    throws IOException
    {

        return call(apTransId, 
                    new DTBS(textToBeSigned, DTBS.ENCODING_UTF8, DTBS.MIME_TEXTPLAIN),
                    phoneNumber,
                    noSpamService,
                    eventIDService,
                    additionalServices,
                    FiComSignatureProfiles.SIGNATURE,
                    FiComMSS_Formats.PKCS7,
                    handler);

    }

    /**
     * Convenience method for sending a data sign request
     * 
     * @param apTransId AP Transaction ID
     * @param digestToBeSigned
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request.
     * @see #call(String, DTBS, String, Service, Service, List, String, String, FiComResponseHandler)
     */
    public FiComRequest signData(final String apTransId,
                                 final byte [] digestToBeSigned,
                                 final String phoneNumber,
                                 final Service noSpamService,
                                 final Service eventIDService,
                                 final List<Service> additionalServices,
                                 final FiComResponseHandler handler) 
    throws IOException
    {

        return call(apTransId, 
                    new DTBS(digestToBeSigned, DTBS.ENCODING_BASE64, DTBS.MIME_SHA1),
                    phoneNumber,
                    noSpamService,
                    eventIDService,
                    additionalServices,
                    FiComSignatureProfiles.DIGESTIVE,
                    FiComMSS_Formats.PKCS7,
                    handler);

    }

    /**
     * Convenience method for sending a consent request
     * 
     * @param apTransId AP Transaction ID
     * @param textToBeConsentedTo
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request.
     * @see #call(String, DTBS, String, Service, Service, List, String, String, FiComResponseHandler)
     */
    public FiComRequest consent(final String apTransId,
                                 final String textToBeConsentedTo,
                                 final String phoneNumber,
                                 final Service noSpamService,
                                 final Service eventIDService,
                                 final List<Service> additionalServices,
                                 final FiComResponseHandler handler) 
    throws IOException
    {

        return call(apTransId, 
                    new DTBS(textToBeConsentedTo, DTBS.ENCODING_UTF8, DTBS.MIME_TEXTPLAIN),
                    phoneNumber,
                    noSpamService,
                    eventIDService,
                    additionalServices,
                    FiComSignatureProfiles.CONSENT,
                    FiComMSS_Formats.PKCS7,
                    handler);

    }
    
    /**
     * Sends a request and waits for the response.
     * Polls the server with status requests while waiting.
     * 
     * All responses are sent to the given response handler.
     * 
     * @param apTransId AP Transaction ID
     * @param dtbs Data to be signed
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param signatureProfile Signature profile to use
     * @param mssFormat MSS Format to use.
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request.
     */
    public FiComRequest call(final String apTransId,
                             final DTBS dtbs,
                             final String phoneNumber,
                             final Service noSpamService,
                             final Service eventIDService,
                             final List<Service> additionalServices,
                             final String signatureProfile,
                             final String mssFormat,
                             final FiComResponseHandler handler) 
    throws IOException
    {
        
        if(handler == null) {
            throw new IOException("Null response handler not allowed.");
        }

        final FiComRequest fiReq = new FiComRequest();

        String msisdn = phoneNumber; //consider using some kind of normalizer
        String dataToBeDisplayed = null;
        MessagingModeType messagingMode = MessagingModeType.ASYNCHCLIENTSERVER;

        final MSS_SignatureReq sigReq = etsiClient.createSignatureRequest(apTransId, 
                                                                    msisdn, 
                                                                    dtbs, 
                                                                    dataToBeDisplayed, 
                                                                    signatureProfile, 
                                                                    mssFormat, 
                                                                    messagingMode);
        fiReq.sigReq = sigReq;

        sigReq.getAdditionalServices().addService(noSpamService);
        sigReq.getAdditionalServices().addService(eventIDService);
        if(additionalServices != null) {
            for(Service s : additionalServices) {
                if(s != null)
                    sigReq.getAdditionalServices().addService(s);
            }
        }

        MSS_SignatureResp _sigResp = null;
        try {
            log.debug("Sending sigReq");
            _sigResp = etsiClient.send(sigReq);
            log.debug("Got resp");
            fiReq.sigResp = _sigResp;
        }
        catch(AxisFault af) {
            log.error("Got SOAP fault", af);
            //throw af;
            handler.onError(fiReq, af);
        }
        catch(IOException ioe) {
            log.error("Got IOException ", ioe);
            throw ioe;
            //handler.onError(fiReq, ioe);
        }
        
        final MSS_SignatureResp fSigResp = _sigResp;
        FutureTask<FiComResponse> ft = 
            new FutureTask<FiComResponse>(new Callable<FiComResponse>() {
                @SuppressWarnings("finally")
                @Override
				public FiComResponse call() throws Exception {
                	
                    long timeout = TIMEOUT;
                    long currentTimeMillis = System.currentTimeMillis();
                    // note that the transaction generally times out at the server at 180 s 
                    long deadline = currentTimeMillis + timeout;
                    
                    FiComResponse fiResp = null;
                    ProgressUpdate prgUpdate = new ProgressUpdate(timeout, currentTimeMillis);

                    MSS_StatusResp statResp = null;
                    int waitPeriod = INITIAL_WAIT;
                    long now = System.currentTimeMillis();
                    LOOP: while(true) {    	
                    	Thread.sleep(waitPeriod - (System.currentTimeMillis()-now));
                        now = System.currentTimeMillis();
                        waitPeriod = SUBSEQUENT_WAIT; 
                        
                        if (now > deadline) {
                        	log.trace("Timed out");
                            try {
                                handler.onError(fiReq, new FiComException("timed out"));
                            }
                            finally {
                                break LOOP;
                            }
                        }
                        MSS_StatusReq  statReq = null;
                        try {
                            statReq = etsiClient.createStatusRequest(fSigResp, apTransId);
                        } catch (Throwable t){
                            log.trace("Failed creating status request", t);
                            try {
                                handler.onError(fiReq, t);
                            } 
                            finally {
                                break LOOP;
                            }
                        }
                        try {
                            log.trace("Sending statReq");
                            statResp = etsiClient.send(statReq);
                            log.trace("Got statResp");

                            boolean done = isDone(statResp);
                            long statusCode = parseStatus(statResp.getStatus());

                            if(FiComStatusCodes.OUTSTANDING_TRANSACTION.getValue() == statusCode) {
                                log.trace("Got an outstanding statResp. Continuing to wait for a final answer.");
                                handler.onOutstandingProgress(fiReq, prgUpdate);
                                continue LOOP;
                            }
                            else if(done) {
                                log.info("Got a final Status Response. Ending the wait.");
                                fiResp = new FiComResponse(sigReq,
                                                           fSigResp,
                                                           statResp);
                                try {
                                    handler.onResponse(fiReq, fiResp);
                                }
                                finally {
                                    break LOOP;                              
                                }
                            }
                            else {
                                log.warn("Got an abnormal Status Response. (" + statusCode  + ") Ending the wait.");
                                FiComException fe = new FiComException("abnormal status code " + statusCode);
                                try {
                                    handler.onError(fiReq, fe);
                                }
                                finally {
                                    break LOOP;
                                }
                            }

                        }
                        catch(AxisFault af) {
                            log.trace("Got soap fault", af);
                            //throw af;
                            try {
                                handler.onError(fiReq, af);
                            }
                            finally {
                                break LOOP;
                            }
                        }
                        catch(IOException ioe) {
                            log.trace("Got IOException", ioe);
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

        log.debug("Starting calling");
        this.threadExecutor.execute(ft);

        return fiReq;

    }
    
    /**
     * Sends a receipt request.
     * @param fiResp The receipt request to send
     * @param message 
     */
    public void sendReceiptReq(FiComResponse fiResp, String message) {
        
        if(fiResp == null)
            throw new IllegalArgumentException("Null fiResp object. Can't send receipt.");
        
        log.debug("Sending receipt");
    	MSS_ReceiptReq receiptReq = etsiClient.createReceiptRequest(fiResp.originalSigResp, 
    	                                                            fiResp.originalSigReq.getAP_Info().getAP_TransID(), 
    	                                                            message);
        receiptReq.setMobileUser(fiResp.originalSigReq.getMobileUser());

        try {
			etsiClient.send(receiptReq);
		} catch (IOException e) {
			log.debug("Could not send receipt", e);
		}
    }

    private static final long NO_STATUS = -1; // -1 is not used by ETSI or by FiCom
    private long parseStatus(Status status) {
        if(status == null)
            return NO_STATUS;
        StatusCode sc = status.getStatusCode();
        if(sc == null)
            return NO_STATUS;
        return sc.getValue();
    }

    /**
     * Checks whether the given StatusResp contains an MSS_Signature
     * @param statResp
     * @return true if signature was found, false otherwise
     */
    private static boolean isDone(MSS_StatusResp statResp) {
        if(statResp == null)
            return false;
        MSS_Signature sig = statResp.getMSS_Signature();
        return sig != null;
    }

    /**
     * Shuts down all started threads.
     */
    public void shutdown() {
        try {
            this.threadExecutor.shutdown();
        } catch (Throwable t) {
            log.trace("FiComClient failed to shut down properly", t);
        }
    }

}
