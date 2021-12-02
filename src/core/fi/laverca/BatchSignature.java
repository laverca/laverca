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
package fi.laverca;

import java.security.cert.X509Certificate;

import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.StatusType;
import fi.laverca.mss.MssException;
import fi.laverca.mss.MssResponse;
import fi.laverca.util.SignatureUtil;
import fi.methics.ts102204.ext.v1_0.AdditionalSignatureResponse;

public class BatchSignature implements Signature {

    private Signature     sig;
    private String     docref;
    private StatusType status;
    
    /**
     * Build a BatchSignature from the "regular" response
     * @param sig    Signature
     * @param sigreq Signature request
     */
    public BatchSignature(Signature sig, MSSSignatureReq sigreq) {
        this.sig = sig;
        if (sigreq != null && sigreq.getDataToBeDisplayed() != null) {
            this.docref = sigreq.getDataToBeDisplayed().getValue();
        }
    }
    
    /**
     * Build a BatchSignature from a BatchSignature AdditionalService response element
     * @param as      BatchSignature AdditionalService response element
     * @param sigresp MSS Signature Response
     */
    public BatchSignature(AdditionalSignatureResponse as, MssResponse sigresp) {
        this.sig    = SignatureUtil.parseSignature(sigresp, as.getMSSSignature());
        this.docref = as.getDocumentRef();
        this.status = as.getStatus();
    }

    @Override
    public byte[] getRawSignature() {
        if (this.sig == null) return null;
        return this.sig.getRawSignature();
    }

    @Override
    public String getBase64Signature() {
        if (this.sig == null) return null;
        return this.sig.getBase64Signature();
    }

    @Override
    public String getSignerCn() {
        if (this.sig == null) return null;
        return this.sig.getSignerCn();
    }

    @Override
    public X509Certificate getSignerCert() throws MssException {
        if (this.sig == null) return null;
        return this.sig.getSignerCert();
    }
    
    /**
     * Return document/hash reference "docref"
     * @return reference or null
     */
    public String getDocRef() {
        return this.docref;
    }
    
    /**
     * Check if the signature is complete
     * @return true for complete signature, false for outstanding one (504)
     */
    public boolean isComplete() {
        if (this.status == null) return true;
        if (this.status.getStatusCode() == null) return false;
        return !StatusCodes.OUTSTANDING_TRANSACTION.equals(this.status.getStatusCode().getValue());
    }
    
    /**
     * Get BatchSignature response status code
     * @return StatusCode (or -1 if not available)
     */
    public int getStatusCode() {
        if (this.status == null) return -1;
        if (this.status.getStatusCode() == null) return -1;
        if (this.status.getStatusCode().getValue() == null) return -1;
        return this.status.getStatusCode().getValue().intValue();
    }

    /**
     * Get BatchSignature response status message
     * @return StatusMessage (or null if not available)
     */
    public String getStatusMessage() {
        if (this.status == null) return null;
        return this.status.getStatusMessage();
    }
}
