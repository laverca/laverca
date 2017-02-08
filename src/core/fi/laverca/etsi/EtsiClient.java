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

package fi.laverca.etsi;

import java.util.List;

import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusResp;
import org.etsi.uri.TS102204.v1_1_2.Service;
import org.etsi.uri.TS102204.v1_1_2.types.MessagingModeType;

import fi.laverca.ClientHelper;
import fi.laverca.util.DTBS;

/**  
 * An asynchronous client for FiCom -style signature requests.
 */
public class EtsiClient extends ClientHelper<EtsiRequest, EtsiResponse> {

    public EtsiClient(final String apId,             // AP settings
                      final String apPwd, 
                      final String msspSignatureUrl, // AE connection settings
                      final String msspStatusUrl,
                      final String msspReceiptUrl)
        throws IllegalArgumentException
    {
        super(apId, apPwd, msspSignatureUrl, msspStatusUrl, msspReceiptUrl);
    }

    /**
     * Create an EtsiRequest instance
     * @param apTransId AP Transaction ID
     * @param dtbd Data to be displayed
     * @param dtbs Data to be signed
     * @param msisdn MSISDN of the target user
     * @param additionalServices List of FiCom additionalservices to add to the request
     * @param signatureProfile Signature profile to use
     * @param mssFormat MSS Format to use.
     * @param messagingMode Messaging mode to use
     * @return Created request
     */
    public EtsiRequest createRequest(final String apTransId, 
                                     final String msisdn,
                                     final DTBS   dtbs,
                                     final String dtbd,
                                     final List<Service> additionalServices, 
                                     final String signatureProfile,
                                     final String mssFormat,
                                     final MessagingModeType messagingMode) {
        
        final EtsiRequest req = new EtsiRequest();

        final MSS_SignatureReq sigReq = this.mssClient.createSignatureRequest(apTransId, 
                                                                              msisdn, 
                                                                              dtbs,
                                                                              dtbd, 
                                                                              signatureProfile, 
                                                                              mssFormat, 
                                                                              messagingMode);
        req.sigReq = sigReq;

        if(additionalServices != null) {
            for(Service s : additionalServices) {
                if(s != null) {
                    sigReq.getAdditionalServices().addService(s);
                }
            }
        }
        
        return req;
    }

    @Override
    protected EtsiResponse createResp(final MSS_SignatureReq sigReq,
                                      final MSS_SignatureResp sigResp, 
                                      final MSS_StatusResp statResp) {
        return new EtsiResponse(sigReq, sigResp, statResp);
    }

}
