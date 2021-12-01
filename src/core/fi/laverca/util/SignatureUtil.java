/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2021 Laverca Project
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
package fi.laverca.util;

import fi.laverca.CmsSignature;
import fi.laverca.MSS_Formats;
import fi.laverca.MobileConnectSignature;
import fi.laverca.Pkcs1;
import fi.laverca.Signature;
import fi.laverca.SignatureProfiles;
import fi.laverca.jaxb.mss.SignatureType;
import fi.laverca.jaxb.mssfi.PKCS1;
import fi.laverca.mss.MssResponse;

public class SignatureUtil {

    /**
     * Parse a signature from the MSS response
     * @param resp    Response
     * @param soapSig SOAP SignatureType
     * @return Signature object (or null)
     */
    public static Signature parseSignature(MssResponse resp, SignatureType soapSig) {
        
        if (soapSig == null) {
            return null;
        }
        Signature signature = null;

        try {
            if (soapSig.getPKCS1() != null) {
                return new Pkcs1(soapSig.getPKCS1());
            }
            if (resp.getMssFormat() != null) {
                switch (resp.getMssFormat()) {
                    case MSS_Formats.KIURU_PKCS1:
                        PKCS1 p1 = new PKCS1();
                        p1.setSignatureValue(soapSig.getBase64Signature());
                        signature = new Pkcs1(p1);
                        break;
                    case MSS_Formats.PKCS7:
                    case MSS_Formats.CMS:
                    default:
                        signature = new CmsSignature(soapSig.getBase64Signature());
                        break;
                }
            } else {
                if (resp.getSignatureProfile() != null) {
                    switch (resp.getSignatureProfile()) {
                        case SignatureProfiles.MOBILECONNECT_LOA2:
                        case SignatureProfiles.MOBILECONNECT_LOA3:
                            signature = new MobileConnectSignature(soapSig.getBase64Signature());
                            break;
                        default:
                            try {
                                signature = new CmsSignature(soapSig.getBase64Signature());
                            } catch (IllegalArgumentException e) {
                                // Not CMS - assume it's MobileConnect
                                signature = new MobileConnectSignature(soapSig.getBase64Signature());
                            }
                            break;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return signature;
    }

}
