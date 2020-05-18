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

package fi.laverca.swisscom;

import java.io.IOException;
import java.util.List;

import fi.laverca.ClientBase;
import fi.laverca.MSS_Formats;
import fi.laverca.ResponseHandler;
import fi.laverca.SignatureProfiles;
import fi.laverca.jaxb.mss.AdditionalServiceType;
import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.mss.MSSStatusResp;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.jaxb.mssfi.ObjectFactory;
import fi.laverca.mss.MssConf;
import fi.laverca.util.DTBS;

/**  
 * An asynchronous client for Swisscom -style signature requests.
 */
public class SwisscomClient extends ClientBase<SwisscomRequest, SwisscomResponse> {
    
    public static final ObjectFactory factory = new ObjectFactory();

    public SwisscomClient(final String apId,             // AP settings
                          final String apPwd, 
                          final String msspSignatureUrl, // AE connection settings
                          final String msspStatusUrl,
                          final String msspReceiptUrl)
        throws IllegalArgumentException
    {
        super(apId, apPwd,  msspSignatureUrl, msspStatusUrl, msspReceiptUrl);
    }
    
    public SwisscomClient(MssConf conf) {
        super(conf);
    }

    /**
     * Create a SwisscomRequest instance
     * 
     * @param apTransId AP Transaction ID
     * @param dtbs Data to be signed
     * @param msisdn MSISDN (phone number in international format) of the target user
     * @param userLangService Additional Service for selecting user language
     * @param additionalServices List of additionalservices to add to the request
     * @param signatureProfile Signature profile to use
     * @param mssFormat MSS Format to use.
     * @return created SwisscomRequest
     */
    public SwisscomRequest createRequest(final String apTransId, 
                                         final DTBS dtbs,
                                         final String msisdn,
                                         final AdditionalServiceType userLangService,
                                         final List<AdditionalServiceType> additionalServices, 
                                         final String signatureProfile,
                                         final String mssFormat) {
        
        final SwisscomRequest req = new SwisscomRequest();

        String dataToBeDisplayed = null;
        MessagingModeType messagingMode = MessagingModeType.ASYNCH_CLIENT_SERVER;

        final MSSSignatureReq sigReq = this.mssClient.createSignatureRequest(apTransId, 
                                                                             msisdn, 
                                                                             dtbs, 
                                                                             dataToBeDisplayed, 
                                                                             signatureProfile, 
                                                                             mssFormat, 
                                                                             messagingMode);
        req.sigReq = sigReq;

        final List<AdditionalServiceType> as = sigReq.getAdditionalServices().getServices();
        if (userLangService != null) {
            as.add(userLangService);
        }
        
        if (additionalServices != null) {
            for (final AdditionalServiceType s : additionalServices) {
                if (s != null) {
                    as.add(s);
                }
            }
        }
        
        return req;
    }
    
    /**
     * Convenience method for sending an authentication request
     * 
     * @param apTransId AP Transaction ID
     * @param dtbs Data to be signed
     * @param msisdn MSISDN (phone number in international format) of the target user
     * @param userLangService Additional Service for selecting user language
     * @param additionalServices List of additionalservices to add to the request
     * @param handler SwisscomResponseHandler for receiving asynch responses.
     * @return Sent request.
     * @throws IOException if handler is null or if an IOException was caught when sending the request.
     * @see ClientBase#call(fi.laverca.mss.MssRequest, ResponseHandler)
     */
    public SwisscomRequest authenticate(final String apTransId,
                                        final byte[] dtbs,
                                        final String msisdn,
                                        final AdditionalServiceType userLangService,
                                        final List<AdditionalServiceType> additionalServices,
                                        final SwisscomResponseHandler handler) 
        throws IOException
    {
        SwisscomRequest req = this.createRequest(apTransId, 
                                                 new DTBS(dtbs, DTBS.ENCODING_BASE64, DTBS.MIME_STREAM),
                                                 msisdn,
                                                 userLangService,
                                                 additionalServices,
                                                 SignatureProfiles.SWISSCOM_AUTHENTICATION,
                                                 MSS_Formats.PKCS7);
        
        return this.send(req, handler);
    }


    @Override
    protected SwisscomResponse createResp(final MSSSignatureReq sigReq,
                                          final MSSSignatureResp sigResp, 
                                          final MSSStatusResp statResp) {
        return new SwisscomResponse(sigReq, sigResp, statResp);
    }

}
