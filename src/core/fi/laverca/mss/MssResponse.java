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

package fi.laverca.mss;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusResp;

import fi.laverca.Pkcs1;
import fi.laverca.Pkcs7;
import fi.laverca.etsi.EtsiResponse;

public abstract class MssResponse {

    private static final Log log = LogFactory.getLog(EtsiResponse.class);

    public MSS_SignatureReq  originalSigReq;
    public MSS_SignatureResp originalSigResp;
    public MSS_StatusResp    finalStatusResp;

    public MssResponse(final MSS_SignatureReq  originalSigReq,
                       final MSS_SignatureResp originalSigResp,
                       final MSS_StatusResp    finalStatusResp) { 
        this.originalSigReq  = originalSigReq;
        this.originalSigResp = originalSigResp;
        this.finalStatusResp = finalStatusResp;
    }

    /** 
     * Get the PKCS7 signature from this response
     * @return PKCS7 signature or null if the signature is not in this format. 
     */
    public Pkcs7 getPkcs7Signature() {
        try {
            if(this.finalStatusResp == null)
                throw new RuntimeException("Illegal state. Null statusResp.");

            if(this.finalStatusResp.getMSS_Signature() == null)
                throw new RuntimeException("Illegal state. Null statusResp.MSS_Signature");
            
            Pkcs7 p7 = new Pkcs7(this.finalStatusResp.getMSS_Signature().getBase64Signature());
            return p7;
        } catch(IllegalArgumentException iae) {
            log.debug("not a pkcs7?", iae);
            return null;
        }

    }
    
    /** 
     * Get the PKCS1 signature from this response
     * @return PKCS1 signature or null if the signature is not in this format. 
     */    
    public Pkcs1 getPkcs1Signature() {
        try {
            if(this.finalStatusResp == null)
                throw new RuntimeException("illegal state. Null statusResp.");

            if(this.finalStatusResp.getMSS_Signature() == null)
                throw new RuntimeException("illegal state. Null statusResp.MSS_Signature");
            
            Pkcs1 p1 = new Pkcs1(this.finalStatusResp.getMSS_Signature().getPKCS1());
            return p1;
        } catch(IllegalArgumentException iae) {
            log.debug("not a pkcs1?", iae);
            return null;
        }
    }

    /** 
     * Get the last MSS_StatusResp message
     * @return MSS_StatusResp
     */
    public MSS_StatusResp getMSS_StatusResp() {
        return this.finalStatusResp;
    }

    /**
     * Get the latest status code of the transaction
     * @return latest status code or -1 if not available
     */
    public long getStatusCode() {
        
        try {
            if (this.finalStatusResp != null) {
                return this.finalStatusResp.getStatus().getStatusCode().getValue();
            }
            if (this.originalSigResp != null) {
                return this.originalSigResp.getStatus().getStatusCode().getValue();
            }
        } catch (NullPointerException npe) {
            // Got a faulty response... ignore
        }
        return -1;
    }
    
    /**
     * Get the latest status message of the transaction
     * @return latest status code or null if not available
     */
    public String getStatusMessage() {
        try {
            if (this.finalStatusResp != null) {
                return this.finalStatusResp.getStatus().getStatusMessage();
            }
            if (this.originalSigResp != null) {
                return this.originalSigResp.getStatus().getStatusMessage();
            }
        } catch (NullPointerException npe) {
            // Got a faulty response... ignore
        }
        return null;
    }
    
}
