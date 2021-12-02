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

package fi.laverca.mss;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fi.laverca.BatchSignature;
import fi.laverca.CmsSignature;
import fi.laverca.Pkcs1;
import fi.laverca.Signature;
import fi.laverca.StatusCodes;
import fi.laverca.etsi.EtsiResponse;
import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.mss.MSSStatusResp;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.jaxb.mss.SignatureType;
import fi.laverca.jaxb.mss.StatusDetailType;
import fi.laverca.jaxb.mss.StatusType;
import fi.laverca.jaxb.mssfi.ServiceResponses;
import fi.laverca.jaxb.mssfi.ServiceResponses.ServiceResponse;
import fi.laverca.util.SignatureUtil;
import fi.methics.ts102204.ext.v1_0.AdditionalSignatureResponse;

/**
 * Represents the final response to a signature request. Signature requests can be either synchronous or asynchronous. 
 * 
 * <p>
 * In synchronous mode, the only response is a signature response, which <b>contains</b> the signature (unless the signature is unsuccessful),
 * and status details.   
 * 
 * <p>
 * In asynchronous mode, the first response is a signature response that does <b>not</b> contain a signature. After receiving the signature response,
 * Laverca sends periodical status requests. The final status response will contain a signature (if successful), and status details. 
 * 
 * <p>
 * The signature and status details in synchronous signature response and asynchronous status response are identical. 
 * 
 */
public abstract class MssResponse {

    private static final Log log = LogFactory.getLog(EtsiResponse.class);
    
    private final MSSSignatureReq  originalSigReq;
    private final MSSSignatureResp originalSigResp;
    private final MSSStatusResp    finalStatusResp;
    
    protected final StatusType    status;
    protected final SignatureType signature;
    
    public MssResponse(final MSSSignatureReq  originalSigReq,
                       final MSSSignatureResp originalSigResp,
                       final MSSStatusResp    finalStatusResp) { 
        this.originalSigReq  = originalSigReq;
        this.originalSigResp = originalSigResp;
        this.finalStatusResp = finalStatusResp;
        
        this.originalSigResp.getMSSSignature();
        
        if (this.finalStatusResp != null) {
            this.status    = this.finalStatusResp.getStatus();
            this.signature = this.finalStatusResp.getMSSSignature();
        } else {
            this.status    = this.originalSigResp.getStatus();
            this.signature = this.originalSigResp.getMSSSignature();
        }
        
    }
    
    /** 
     * Get the MSS signature
     * 
     * @return signature or null if the response contains no signature.
     * @throws IllegalStateException if the signature parsing fails
     */
    public Signature getSignature() throws IllegalStateException {
        return SignatureUtil.parseSignature(this, this.signature);
    }
    
    /**
     * Check if the response has signature available
     * 
     * @return true if signature is available
     * @throws IllegalStateException if the signature parsing fails
     */
    public boolean hasSignature() throws IllegalStateException {
        return this.getSignature() != null;
    }
    
    /**
     * Return batch signatures
     * @return batch signatures
     * @see {@link #isBatchSignature()}
     */
    public List<BatchSignature> getBatchSignatures() {
        List<BatchSignature> resp = new ArrayList<>();
        if (this.signature != null) {
            resp.add(new BatchSignature(this.getSignature(), this.originalSigReq));
        }
        for (AdditionalServiceResponse as : this.getAdditionalServiceResponses()) {
            if (as.getDescription() == null) continue;
            if (as.getDescription().equals(AdditionalServices.BATCH_SIGNATURE_URI)) {
                if (as.getServiceResponse() == null) continue;
                if (as.getServiceResponse().getAdditionalSignatureResponses() == null) continue;
                for (AdditionalSignatureResponse ar : as.getServiceResponse().getAdditionalSignatureResponses()) {
                    resp.add(new BatchSignature(ar, this));
                }
            }
        }
        return resp;
    }

    /** 
     * Get the PKCS7 signature from this response
     * @return PKCS7 signature or null if the signature is not in this format.
     * @see {@link #getSignature()}
     */
    public CmsSignature getPkcs7Signature() {
        try {
            return new CmsSignature(this.signature.getBase64Signature());
        } catch (IllegalArgumentException iae) {
            log.debug("Response is not PKCS7", iae);
            return null;
        }
    }
    
    /** 
     * Get the PKCS1 signature from this response
     * @return PKCS1 signature or null if the signature is not in this format.
     * @see {@link #getSignature()}
     */
    public Pkcs1 getPkcs1Signature() {
        try {
            return new Pkcs1(this.signature.getPKCS1());
        } catch(IllegalArgumentException iae) {
            log.debug("Response is not PKCS#1", iae);
            return null;
        }
    }

    /**
     * Get the raw XML datatype of the MSS_SignatureReq
     * @return {@link MSSSignatureReq}
     */
    public MSSSignatureReq getMSS_SignatureReq() {
        return this.originalSigReq;
    }

    /**
     * Get the raw XML datatype of the MSS_SignatureResp
     * @return {@link MSSSignatureResp}
     */
    public MSSSignatureResp getMSS_SignatureResp() {
        return this.originalSigResp;
    }
    
    /** 
     * Get the raw XML datatype of the last MSS_StatusResp message.
     * <p>This is null if the request is synchronous.
     * @return {@link MSSStatusResp}
     */
    public MSSStatusResp getMSS_StatusResp() {
        return this.finalStatusResp;
    }

    /**
     * Get the latest status code of the transaction
     * @return latest status code or -1 if not available
     */
    public long getStatusCode() {
        if (this.status != null && this.status.getStatusCode() != null && this.status.getStatusCode().getValue() != null) {
            return this.status.getStatusCode().getValue().longValue();
        }
        return -1L;
    }
    
    /**
     * Get the latest status message of the transaction
     * @return latest status code or null if not available
     */
    public String getStatusMessage() {
        return this.status != null ? this.status.getStatusMessage() : null;
    }
    
    /**
     * Get the requested MSS_Format as String
     * @return requested MSS_Format as String. May be null.
     */
    public String getMssFormat() {
        if (this.originalSigReq != null &&
            this.originalSigReq.getMSSFormat() != null) {
            
            return this.originalSigReq.getMSSFormat().getMssURI();
        }
        return null;
    }
    
    /**
     * Get the requested SignatureProfile as String
     * @return requested SignatureProfile as String. May be null.
     */
    public String getSignatureProfile() {
        if (this.originalSigReq != null &&
            this.originalSigReq.getSignatureProfile() != null) {
            
            return this.originalSigReq.getSignatureProfile().getMssURI();
        }
        return null;
    }
    
    /**
     * Get a list of AdditionalService responses.
     * @return List (may be empty) containing all {@link AdditionalServiceResponse}s found in the response
     */
    public List<AdditionalServiceResponse> getAdditionalServiceResponses() {
        List<AdditionalServiceResponse> resps = new ArrayList<>();
        
        if (this.getStatusDetail() != null) {
            ServiceResponses sresps = AdditionalServices.readServiceResponses(this.getStatusDetail());
            for (ServiceResponse sr : sresps.getServiceResponses()) {
                resps.add(new AdditionalServiceResponse(sr));
            }
        }
        
        return resps;
    }
    
    /**
     * Get the raw XML datatype {@link StatusDetailType} of the latest response. Works in both synchronous and asynchronous messaging modes. 
     * @return Status detail type of the signature. Can be null. 
     */
    public StatusDetailType getStatusDetail() {
        return this.status != null ? this.status.getStatusDetail() : null;
    }
    
    /**
     * Check if this response contains given AdditionalService URI
     * @param uri URI
     * @return true if response for given AdditionalService is present
     * @see {@link AdditionalServices}
     */
    public boolean hasAdditionalServiceResponse(String uri) {
        for (AdditionalServiceResponse resp : this.getAdditionalServiceResponses()) {
            if (resp == null) continue;
            if (resp.getDescription() == null) continue;
            if (resp.getDescription().equals(uri)) return true;
        }
        return false;
    }
    
    /**
     * Was this transaction synchronous?
     * @return true if MessagingMode was "synch"
     */
    public boolean isSynchronous() {
        if (this.originalSigReq == null) return false;
        return MessagingModeType.SYNCH == this.originalSigReq.getMessagingMode();
    }
    
    /**
     * Is this a successful MSS response (500 SIGNATURE or 502 VALID_SIGNATURE)
     * @return true for successful response
     */
    public boolean isSuccess() {
        return StatusCodes.SIGNATURE.equals(this.getStatusCode()) || StatusCodes.VALID_SIGNATURE.equals(this.getStatusCode()) ;
    }
    
    /**
     * Check if this response is a batch signature response
     * @return true if batch signature response is present
     * @see {@link getBatchSignatures()}
     */
    public boolean isBatchSignature() {
        return this.hasAdditionalServiceResponse(AdditionalServices.BATCH_SIGNATURE_URI);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("StatusCode=").append(this.getStatusCode());
        sb.append(", StatusMessage=").append(this.getStatusMessage());
        sb.append(", HasSignature=").append(this.hasSignature());
        sb.append("]");
        return sb.toString();
    }
    
}
