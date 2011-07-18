package fi.laverca;

import java.util.LinkedList;
import java.util.List;

import oasis.names.tc.SAML.v2_0.assertion.Assertion;
import oasis.names.tc.SAML.v2_0.assertion.Attribute;
import oasis.names.tc.SAML.v2_0.assertion.AttributeStatement;
import oasis.names.tc.SAML.v2_0.protocol.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusResp;
import org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem;

import fi.ficom.mss.TS102204.v1_0_0.ServiceResponse;
import fi.ficom.mss.TS102204.v1_0_0.Status;

/**
 * Asynchronous MSS_StatusResp callback.
 * 
 * @author Asko Saura (asko@methics.fi)
 * @author Jan Mikael Lindlöf (mikael.lindlof@nbl.fi)
 * @author Eemeli Miettinen (eemeli.miettinen@methics.fi) 
 */
public class FiComResponse {
    private static final Log log = LogFactory.getLog(FiComResponse.class);

    MSS_StatusResp statusResp;

    FiComResponse(MSS_StatusResp statResp) {
        this.statusResp = statResp;
    }

    /** @return null if the signature is not in this format. */
    public FiComPkcs7 getPkcs7Signature() {
        try {
        	if(this.statusResp == null)
                throw new RuntimeException("illegal state. Null statusResp.");

            if(this.statusResp.getMSS_Signature() == null)
                throw new RuntimeException("illegal state. Null statusResp.MSS_Signature");
            
            FiComPkcs7 p7 = new FiComPkcs7(this.statusResp.getMSS_Signature().getBase64Signature());
            return p7;
        }
        catch(IllegalArgumentException iae) {
            log.debug("not a pkcs7?", iae);
            return null;
        }

    }
    
    /** @return null if the signature is not in this format. */
    public FiComPkcs1 getPkcs1Signature() {
        try {
        	if(this.statusResp == null)
                throw new RuntimeException("illegal state. Null statusResp.");

            if(this.statusResp.getMSS_Signature() == null)
                throw new RuntimeException("illegal state. Null statusResp.MSS_Signature");
            
            FiComPkcs1 p1 = new FiComPkcs1(this.statusResp.getMSS_Signature().getPKCS1());
            return p1;
        }
        catch(IllegalArgumentException iae) {
            log.debug("not a pkcs1?", iae);
            return null;
        }

    }

    /** @return a list of attributes as per the PersonIdentity additional service. 
     * @return null if the MSS_Status response does not include a PersonID response.
     */
    public List<FiComAdditionalServices.PersonIdAttribute> getPersonIdAttributes() {
        try {
            ServiceResponse sResp = FiComAdditionalServices.readServiceResponse(this.statusResp.getStatus().getStatusDetail(), FiComAdditionalServices.PERSON_ID_URI);

            Response samlpResp = sResp.getResponse();
            Assertion assertion = Saml2Util.parseFromResponse(samlpResp);
            AttributeStatement stmt = Saml2Util.parseFromAssertion(assertion);
            List<Attribute> attrs = Saml2Util.parseFromAttributeStatement(stmt);

            LinkedList<FiComAdditionalServices.PersonIdAttribute> fiComAttrs = 
                new LinkedList<FiComAdditionalServices.PersonIdAttribute>(); 

            for(Attribute samlAttribute : attrs) 
                fiComAttrs.add(new FiComAdditionalServices.PersonIdAttribute(samlAttribute));

            return fiComAttrs;
        }
        catch(Throwable t) {
            return null;
        }
    }

    /** The final MSS_StatusResp message */
    public MSS_StatusResp getMSS_StatusResp() {
        return this.statusResp;
    }
    
    /**
     * 
     * @return AE validation status, null if no AE validation was done
     */
    public Status getAeValidationStatus() {
    	Status validationStatus = null;
    	for (StatusDetailTypeItem statusDetailTypeItem : this.statusResp.getStatus().getStatusDetail().getStatusDetailTypeItem()) {
			for(ServiceResponse serviceResponse : statusDetailTypeItem.getServiceResponses().getServiceResponse()) {
				if (serviceResponse.getDescription().getMssURI().equals(FiComAdditionalServices.VALIDATE_URI)) {
					validationStatus = serviceResponse.getStatus();
					break;
				}
			}
		}
    	return validationStatus;
    }

    /**
     * 
     * @return true if HMSSP and AE validations return VALID_SIGNATURE
     */
    
    public boolean isValid() {
    	long statusCode = this.statusResp.getStatus().getStatusCode().getValue();
    	boolean aeStatusOk; 

    	try {
    		aeStatusOk = getAeValidationStatus().getStatusCode().getValue() == 
    			FiComStatusCodes.VALID_SIGNATURE.getValue();
    	} catch (NullPointerException e) {

    		if (getAeValidationStatus() != null) 
    			aeStatusOk = true;
    		else 
    			aeStatusOk = false;
    	}

    	return statusCode == FiComStatusCodes.VALID_SIGNATURE.getValue() && aeStatusOk; 
    }

}
