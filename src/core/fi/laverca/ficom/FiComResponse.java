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

import java.util.ArrayList;
import java.util.List;

import oasis.names.tc.SAML.v2_0.assertion.Assertion;
import oasis.names.tc.SAML.v2_0.assertion.Attribute;
import oasis.names.tc.SAML.v2_0.assertion.AttributeStatement;
import oasis.names.tc.SAML.v2_0.protocol.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusResp;
import org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem;

import fi.ficom.mss.TS102204.v1_0_0.ServiceResponse;
import fi.ficom.mss.TS102204.v1_0_0.Status;
import fi.laverca.Saml2Util;

/**
 * Asynchronous MSS_StatusResp callback response.
 */
public class FiComResponse {
    private static final Log log = LogFactory.getLog(FiComResponse.class);

    MSS_SignatureReq  originalSigReq;
    MSS_SignatureResp originalSigResp;
    MSS_StatusResp    finalStatusResp;

    FiComResponse( final MSS_SignatureReq  originalSigReq,
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
    public FiComPkcs7 getPkcs7Signature() {
        try {
        	if(this.finalStatusResp == null)
                throw new RuntimeException("Illegal state. Null statusResp.");

            if(this.finalStatusResp.getMSS_Signature() == null)
                throw new RuntimeException("Illegal state. Null statusResp.MSS_Signature");
            
            FiComPkcs7 p7 = new FiComPkcs7(this.finalStatusResp.getMSS_Signature().getBase64Signature());
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
    public FiComPkcs1 getPkcs1Signature() {
        try {
        	if(this.finalStatusResp == null)
                throw new RuntimeException("illegal state. Null statusResp.");

            if(this.finalStatusResp.getMSS_Signature() == null)
                throw new RuntimeException("illegal state. Null statusResp.MSS_Signature");
            
            FiComPkcs1 p1 = new FiComPkcs1(this.finalStatusResp.getMSS_Signature().getPKCS1());
            return p1;
        } catch(IllegalArgumentException iae) {
            log.debug("not a pkcs1?", iae);
            return null;
        }
    }

    /** 
     * Get available PersonID attributes from this response
     * 
     * @return A list of attributes as per the PersonIdentity additional service. 
     *         <br>null if the MSS_Status response does not include a PersonID response.
     */
    public List<FiComAdditionalServices.PersonIdAttribute> getPersonIdAttributes() {
        try {
        	ServiceResponse sResp   = FiComAdditionalServices.readServiceResponse(this.finalStatusResp.getStatus().getStatusDetail(), FiComAdditionalServices.PERSON_ID_URI);

            Response      samlpResp = sResp.getResponse();
            Assertion     assertion = Saml2Util.parseFromResponse(samlpResp);
            AttributeStatement stmt = Saml2Util.parseFromAssertion(assertion);
            List<Attribute>   attrs = Saml2Util.parseFromAttributeStatement(stmt);

            List<FiComAdditionalServices.PersonIdAttribute> fiComAttrs = 
                new ArrayList<FiComAdditionalServices.PersonIdAttribute>(); 

            for(Attribute samlAttribute : attrs) {
                fiComAttrs.add(new FiComAdditionalServices.PersonIdAttribute(samlAttribute));
            }
                
            return fiComAttrs;
        } catch (NullPointerException e){
            log.error("Failed to fetch PersonID attributes");
        	return null;
        } catch(Throwable t) {
            log.error("Failed to fetch PersonID attributes: " + t.getMessage());
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
     * 
     * @return AE validation status, null if no AE validation was done
     */
    public Status getAeValidationStatus() {
    	try{
			Status validationStatus = null;
			for (StatusDetailTypeItem statusDetailTypeItem : this.finalStatusResp.getStatus().getStatusDetail().getStatusDetailTypeItem()) {
				for(ServiceResponse serviceResponse : statusDetailTypeItem.getServiceResponses().getServiceResponse()) {
					if (serviceResponse.getDescription().getMssURI().equals(FiComAdditionalServices.VALIDATE_URI)) {
						validationStatus = serviceResponse.getStatus();
						break;
					}
				}
			}
			return validationStatus;
    	} catch (NullPointerException e){
            log.error("Failed to fetch PersonID attributes");
            return null;    	
        } catch (Throwable t) {
            log.error("Failed to fetch PersonID attributes: " + t.getMessage());
            return null;
    	}
    }

    /**
     * Check if this response has a valid signature
     * 
     * @return true if HMSSP and AE validations return VALID_SIGNATURE
     */
    public boolean isValid() {
    	try {
    		long    statusCode = this.finalStatusResp.getStatus().getStatusCode().getValue();
	    	boolean aeStatusOk; 
	
	    	try {
	    		aeStatusOk = getAeValidationStatus().getStatusCode().getValue() == 
	    			FiComStatusCodes.VALID_SIGNATURE.getValue();
	    	} catch (NullPointerException e) {
	
	    		if (getAeValidationStatus() == null) {
	    			aeStatusOk = true;
	    		} else {
	    			aeStatusOk = false;
	    		}
	    	}
	
	    	return statusCode == FiComStatusCodes.VALID_SIGNATURE.getValue() && aeStatusOk; 
    	} catch (Throwable t) {
    		log.error("Failed to check response validity: " + t.getMessage());
    		return false;
    	}
    }

}
