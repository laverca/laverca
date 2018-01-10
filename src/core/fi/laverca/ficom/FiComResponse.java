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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fi.laverca.StatusCodes;
import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.mss.MSSStatusResp;
import fi.laverca.jaxb.mss.StatusDetailType;
import fi.laverca.jaxb.mss.StatusType;
import fi.laverca.jaxb.mssfi.ServiceResponses.ServiceResponse;
import fi.laverca.jaxb.saml2a.Assertion;
import fi.laverca.jaxb.saml2a.Attribute;
import fi.laverca.jaxb.saml2a.AttributeStatement;
import fi.laverca.jaxb.saml2p.Response;
import fi.laverca.mss.MssResponse;
import fi.laverca.util.Saml2Util;

/**
 * Asynchronous MSS_StatusResp callback response.
 */
public class FiComResponse extends MssResponse {
    
    private static final Log log = LogFactory.getLog(FiComResponse.class);

    public FiComResponse( final MSSSignatureReq  originalSigReq,
                          final MSSSignatureResp originalSigResp,
                          final MSSStatusResp    finalStatusResp) { 
        super(originalSigReq, originalSigResp, finalStatusResp);
    }
    
    /** 
     * Get available PersonID attributes from this response
     * 
     * @return A list of attributes as per the PersonIdentity additional service. 
     *         <br>null if the MSS_Status response does not include a PersonID response.
     */
    public List<PersonIdAttribute> getPersonIdAttributes() {
        try {
            final StatusDetailType   sd = this.getStatusDetail();
            final ServiceResponse sResp = FiComAdditionalServices.readServiceResponse(sd, FiComAdditionalServices.PERSON_ID_URI);

            final Response      samlpResp = sResp.getResponse();
            final Assertion     assertion = Saml2Util.parseFromResponse(samlpResp);
            final AttributeStatement stmt = Saml2Util.parseFromAssertion(assertion);
            final List<Attribute>   attrs = Saml2Util.parseFromAttributeStatement(stmt);

            final List<PersonIdAttribute> fiComAttrs = new ArrayList<PersonIdAttribute>(); 
            
            for (final Attribute samlAttribute : attrs) {
                fiComAttrs.add(new PersonIdAttribute(samlAttribute));
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
     * 
     * @return AE validation status, null if no AE validation was done or if an error occurred
     */
    public StatusType getAeValidationStatus() {
        try {
            StatusType validationStatus = null;
            for (final Object o1 : this.getStatusDetail().getAniesAndServiceResponsesAndReceiptRequestExtensions()) {
                if (!(o1 instanceof StatusType)) {
                    // Not interesting
                    continue;
                }
                final StatusType s1 = (StatusType)o1;
                if (s1.getStatusDetail() == null) {
                    // null -> not interesting
                    continue;
                }
                for (final Object o2 : s1.getStatusDetail().getAniesAndServiceResponsesAndReceiptRequestExtensions()) {
                    if (!(o2 instanceof ServiceResponse)) {
                        continue;
                    }
                    final ServiceResponse s2 = (ServiceResponse)o2;
                    if (FiComAdditionalServices.VALIDATE_URI.equals(s2.getDescription().getMssURI())) {
                        validationStatus = s2.getStatus();
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
            long    statusCode = this.getStatusCode();
            boolean aeStatusOk; 
    
            try {
                aeStatusOk = (this.getAeValidationStatus().getStatusCode().getValue() == StatusCodes.VALID_SIGNATURE.getValue());
            } catch (NullPointerException e) {
                aeStatusOk = (this.getAeValidationStatus() == null);
            }
    
            return statusCode == StatusCodes.VALID_SIGNATURE.getValue() && aeStatusOk; 
        } catch (Throwable t) {
            log.error("Failed to check response validity: " + t.getMessage());
            return false;
        }
    }
    
}
