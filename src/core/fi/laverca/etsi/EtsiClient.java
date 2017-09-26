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

import fi.laverca.ClientBase;
import fi.laverca.jaxb.mss.AdditionalServiceType;
import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.mss.MSSStatusResp;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.mss.MssConf;
import fi.laverca.util.DTBS;

/**  
 * An asynchronous client for standard ETSI TS 102 204 signature requests.
 */
public class EtsiClient extends ClientBase<EtsiRequest, EtsiResponse> {

    /**
     * Initialize an EtsiClient
     * 
     * @param apId Your identifier; MessageAbstractType/AP_Info/AP_ID. Not null.
     * @param apPwd Your password; MessageAbstractType/AP_Info/AP_PWD. Not null.
     * @param msspSignatureUrl    Connection URL to the AE for signature requests. 
     * @param msspStatusUrl       Connection URL to the AE for status query requests. 
     * @param msspReceiptUrl      Connection URL to the AE for receipt requests. 
     * 
     * @throws IllegalArgumentException if AP ID or AP PWD is missing or invalid.
     */
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
     * Initialize an EtsiClient
     *
     * @param apId Your identifier; MessageAbstractType/AP_Info/AP_ID. Not null.
     * @param apPwd Your password; MessageAbstractType/AP_Info/AP_PWD. Not null.
     * @param msspSignatureUrl    Connection URL to the AE for signature requests. 
     * @param msspStatusUrl       Connection URL to the AE for status query requests. 
     * @param msspReceiptUrl      Connection URL to the AE for receipt requests. 
     * @param msspRegistrationUrl Connection URL to the AE for registration requests. 
     * @param msspProfileUrl      Connection URL to the AE for profile query requests. 
     * @param msspHandshakeUrl    Connection URL to the AE for handshake requests.
     * 
     * @throws IllegalArgumentException if AP ID or AP PWD is missing or invalid.
     */
    public EtsiClient(final String apId,             // AP settings
                      final String apPwd, 
                      final String msspSignatureUrl, // AE connection settings
                      final String msspStatusUrl,
                      final String msspReceiptUrl,
                      final String msspRegistrationUrl,
                      final String msspProfileUrl,
                      final String msspHandshakeUrl)
        throws IllegalArgumentException
    {
        super(apId, apPwd, msspSignatureUrl, msspStatusUrl, msspReceiptUrl, msspRegistrationUrl, msspProfileUrl, msspHandshakeUrl);
    }
    
    public EtsiClient(MssConf conf) {
        super(conf);
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
                                     final List<AdditionalServiceType> additionalServices, 
                                     final String signatureProfile,
                                     final String mssFormat,
                                     final MessagingModeType messagingMode) {
        
        final EtsiRequest req = new EtsiRequest();

        final MSSSignatureReq sigReq = this.mssClient.createSignatureRequest(apTransId, 
                                                                             msisdn, 
                                                                             dtbs,
                                                                             dtbd, 
                                                                             signatureProfile, 
                                                                             mssFormat, 
                                                                             messagingMode);
        req.sigReq = sigReq;
        
        final List<AdditionalServiceType> as = sigReq.getAdditionalServices().getServices();

        if (additionalServices != null) {
            for (AdditionalServiceType s : additionalServices) {
                if (s != null) {
                    as.add(s);
                }
            }
        }
        
        return req;
    }

    @Override
    protected EtsiResponse createResp(final MSSSignatureReq sigReq,
                                      final MSSSignatureResp sigResp, 
                                      final MSSStatusResp statResp) {
        return new EtsiResponse(sigReq, sigResp, statResp);
    }

}
