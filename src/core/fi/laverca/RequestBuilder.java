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

package fi.laverca;

import java.util.GregorianCalendar;

import fi.laverca.jaxb.mss.DataType;
import fi.laverca.jaxb.mss.MSSProfileReq;
import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MessageAbstractType;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.jaxb.mss.MobileUserType;
import fi.laverca.jaxb.mss.MssURIType;
import fi.laverca.jaxb.mss.ObjectFactory;
import fi.laverca.mss.MssClient;
import fi.laverca.mss.ProfileQueryRequest;
import fi.laverca.mss.SignatureRequest;
import fi.laverca.util.DTBS;

public class RequestBuilder {

    private String msisdn;
    private String apTransId;
    private String apId;
    private String apPwd;

    private static final ObjectFactory MSS_OBJECT_FACTORY = new ObjectFactory();
    
    public RequestBuilder(final String msisdn,
                          final String apId,
                          final String apPwd,
                          final String apTransId) {
        this.msisdn    = msisdn;
        this.apTransId = apTransId;
        this.apId      = apId;
        this.apPwd     = apPwd;
        
        if (this.msisdn    == null) throw new IllegalArgumentException("Invalid MSISDN (null)");
        if (this.apTransId == null) throw new IllegalArgumentException("Invalid AP_TransID (null)");
    }
    
    public ProfileQueryRequest buildProfileQuery() {
        
        MSSProfileReq req = MSS_OBJECT_FACTORY.createMSSProfileReq();
        
        this.initializeRequestMessage(req, apTransId);
        
        MobileUserType mu = MSS_OBJECT_FACTORY.createMobileUserType();
        mu.setMSISDN(msisdn);
        req.setMobileUser(mu);        
        
        return new ProfileQueryRequest(req);
    }
    
    public SignatureRequest buildSignatureRequest(final DTBS   dtbs,
                                                  final String dataToBeDisplayed,
                                                  final String signatureProfile,
                                                  final String mss_format,
                                                  final MessagingModeType messagingMode) {

        if (dtbs             == null) throw new IllegalArgumentException("Invalid DTBS (null)");
        if (signatureProfile == null) throw new IllegalArgumentException("Invalid SignatureProfile (null)");
        if (messagingMode    == null) throw new IllegalArgumentException("Invalid MessagingMode (null)");

        final MSSSignatureReq req = MSS_OBJECT_FACTORY.createMSSSignatureReq();
        this.initializeRequestMessage(req, apTransId);

        final MobileUserType muObject = MSS_OBJECT_FACTORY.createMobileUserType();
        muObject.setMSISDN(msisdn);
        req.setMobileUser(muObject);

        final DataType dsObject = dtbs.toDataToBeSigned();
        req.setDataToBeSigned(dsObject);

        if (dataToBeDisplayed != null) {
            final DataType ddObject = MSS_OBJECT_FACTORY.createDataType();
            ddObject.setValue(dataToBeDisplayed);
            req.setDataToBeDisplayed(ddObject);
        }

        final MssURIType spObject = MSS_OBJECT_FACTORY.createMssURIType();
        spObject.setMssURI(signatureProfile);
        req.setSignatureProfile(spObject);

        if (mss_format != null) {
            MssURIType mfObject = MSS_OBJECT_FACTORY.createMssURIType();
            mfObject.setMssURI(mss_format);
            req.setMSSFormat(mfObject);
        }

        req.setMessagingMode(messagingMode);
        req.setAdditionalServices(MssClient.mssObjFactory.createMSSSignatureReqAdditionalServices());

        return new SignatureRequest(req);
    }
    
    
    /**
     * Fills Minorversion, Majorversion, AP_Info and MSS_Info to the given message.
     * @param mat Message to fill
     * @param apTransId AP Transaction ID
     */
    private void initializeRequestMessage(final MessageAbstractType mat, final String apTransId) {
        
        if (mat == null) throw new IllegalArgumentException("Invalid request (null)");
    
        // Set the interface versions. 1 for both, as per ETSI TS 102 204.
        mat.setMajorVersion(Long.valueOf(1));
        mat.setMinorVersion(Long.valueOf(1));

        if (apTransId != null) {
            // Create the AP_Info.
            final MessageAbstractType.APInfo aiObject = MSS_OBJECT_FACTORY.createMessageAbstractTypeAPInfo();
            aiObject.setAPID(this.apId);
            aiObject.setAPPWD(this.apPwd);
            aiObject.setAPTransID(apTransId);
            aiObject.setInstant(new GregorianCalendar());
            mat.setAPInfo(aiObject);
        }
        
        final MessageAbstractType.MSSPInfo miObject = MSS_OBJECT_FACTORY.createMessageAbstractTypeMSSPInfo();
        miObject.setMSSPID(MSS_OBJECT_FACTORY.createMeshMemberType()); 

        mat.setMSSPInfo(miObject);
    }
    
}
