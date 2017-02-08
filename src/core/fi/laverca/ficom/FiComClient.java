/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2015 Laverca Project
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

package fi.laverca.ficom;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusResp;
import org.etsi.uri.TS102204.v1_1_2.Service;
import org.etsi.uri.TS102204.v1_1_2.types.MessagingModeType;

import fi.laverca.ClientHelper;
import fi.laverca.MSS_Formats;
import fi.laverca.ResponseHandler;
import fi.laverca.SignatureProfiles;
import fi.laverca.util.DTBS;

/**  
 * An asynchronous client for FiCom -style signature requests.
 */
public class FiComClient extends ClientHelper<FiComRequest, FiComResponse> {

    public FiComClient( String apId,             // AP settings
                        String apPwd, 
                        String msspSignatureUrl, // AE connection settings
                        String msspStatusUrl,
                        String msspReceiptUrl)
    throws IllegalArgumentException
    {
        super(apId, 
              apPwd, 
              msspSignatureUrl, 
              msspStatusUrl, 
              msspReceiptUrl);
        
        this.initialWait    = 20 * 1000;      // Initial wait 20 s   as per MSS FiCom Implementation Guideline, section 5.1
        this.subsequentWait = 5  * 1000;      // Subsequent wait 5 s as per MSS FiCom Implementation Guideline, section 5.1
        this.timeout        = 5  * 1000 * 60; // Timeout 5 min       as per MSS FiCom Implementation Guideline, section 6.4

        this.threadExecutor = Executors.newCachedThreadPool();
    }

    /**
     * Create an EtsiRequest instance
     * @param apTransId AP Transaction ID
     * @param dtbs Data to be signed
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param signatureProfile Signature profile to use
     * @param mssFormat MSS Format to use.
     * @return created FiComRequest
     */
    public FiComRequest createRequest(final String apTransId, 
                                      final DTBS dtbs,
                                      final String phoneNumber,
                                      final Service noSpamService,
                                      final Service eventIDService,
                                      final List<Service> additionalServices, 
                                      final String signatureProfile,
                                      final String mssFormat) {
        
        final FiComRequest req = new FiComRequest();

        String msisdn = phoneNumber; //consider using some kind of normalizer
        String dataToBeDisplayed = null;
        MessagingModeType messagingMode = MessagingModeType.ASYNCHCLIENTSERVER;

        final MSS_SignatureReq sigReq = this.mssClient.createSignatureRequest(apTransId, 
                                                                              msisdn, 
                                                                              dtbs, 
                                                                              dataToBeDisplayed, 
                                                                              signatureProfile, 
                                                                              mssFormat, 
                                                                              messagingMode);
        req.sigReq = sigReq;

        sigReq.getAdditionalServices().addService(noSpamService);
        sigReq.getAdditionalServices().addService(eventIDService);
        
        if(additionalServices != null) {
            for(Service s : additionalServices) {
                if(s != null) {
                    sigReq.getAdditionalServices().addService(s);
                }
            }
        }
        
        return req;
    }
    
    /**
     * Convenience method for sending an authentication request
     * 
     * @param apTransId AP Transaction ID
     * @param authnChallenge Data to be signed
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIdService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request.
     * @see ClientHelper#call(fi.laverca.mss.MssRequest, ResponseHandler)
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
        FiComRequest req = this.createRequest(apTransId, 
                                              new DTBS(authnChallenge, DTBS.ENCODING_BASE64, DTBS.MIME_STREAM),
                                              phoneNumber,
                                              noSpamService,
                                              eventIdService,
                                              additionalServices,
                                              SignatureProfiles.FICOM_AUTHENTICATION,
                                              MSS_Formats.PKCS7);
        
        return this.call(req, handler);
    }
    
    /**
     * Convenience method for sending an anonymous authentication request
     * 
     * @param apTransId AP Transaction ID
     * @param authnChallenge Data to be signed
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request. 
     * @see ClientHelper#call(fi.laverca.mss.MssRequest, ResponseHandler)
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

        FiComRequest req = this.createRequest(apTransId, 
                                              new DTBS(authnChallenge, DTBS.ENCODING_BASE64, DTBS.MIME_STREAM),
                                              phoneNumber,
                                              noSpamService,
                                              eventIDService,
                                              additionalServices,
                                              SignatureProfiles.FICOM_ANONYMOUS,
                                              MSS_Formats.PKCS7);
        
        return this.call(req, handler);
    }

    /**
     * Convenience method for sending a text sig request
     * 
     * @param apTransId AP Transaction ID
     * @param textToBeSigned Data to be signed
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request.
     * @see ClientHelper#call(fi.laverca.mss.MssRequest, ResponseHandler)
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

        FiComRequest req = this.createRequest(apTransId, 
                                              new DTBS(textToBeSigned, DTBS.ENCODING_UTF8, DTBS.MIME_TEXTPLAIN),
                                              phoneNumber,
                                              noSpamService,
                                              eventIDService,
                                              additionalServices,
                                              SignatureProfiles.FICOM_SIGNATURE,
                                              MSS_Formats.PKCS7);
        
        return this.call(req, handler);

    }

    /**
     * Convenience method for sending a data sign request
     * 
     * @param apTransId AP Transaction ID
     * @param digestToBeSigned Data to be signed
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request.
     * @see ClientHelper#call(fi.laverca.mss.MssRequest, ResponseHandler)
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

        FiComRequest req = this.createRequest(apTransId, 
                                              new DTBS(digestToBeSigned, DTBS.ENCODING_BASE64, DTBS.MIME_SHA1),
                                              phoneNumber,
                                              noSpamService,
                                              eventIDService,
                                              additionalServices,
                                              SignatureProfiles.FICOM_DIGESTIVE,
                                              MSS_Formats.PKCS7);
        
        return this.call(req, handler);
    }

    /**
     * Convenience method for sending a consent request
     * 
     * @param apTransId AP Transaction ID
     * @param textToBeConsentedTo Data to be signed
     * @param phoneNumber MSISDN of the target user
     * @param noSpamService Service for sending nospam code
     * @param eventIDService Service containing the wanted EventId for the request
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request.
     * @see ClientHelper#call(fi.laverca.mss.MssRequest, ResponseHandler)
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

        FiComRequest req = this.createRequest(apTransId, 
                                              new DTBS(textToBeConsentedTo, DTBS.ENCODING_UTF8, DTBS.MIME_TEXTPLAIN),
                                              phoneNumber,
                                              noSpamService,
                                              eventIDService,
                                              additionalServices,
                                              SignatureProfiles.FICOM_CONSENT,
                                              MSS_Formats.PKCS7);
        
        return this.call(req, handler);

    }

    @Override
    protected FiComResponse createResp(final MSS_SignatureReq sigReq,
                                       final MSS_SignatureResp sigResp, 
                                       final MSS_StatusResp statResp) {
        return new FiComResponse(sigReq, sigResp, statResp);
    }

}
