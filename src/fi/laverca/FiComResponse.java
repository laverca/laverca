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

import fi.ficom.mss.TS102204.v1_0_0.ServiceResponse;

/**
 * Asynchronous MSS_StatusResp callback.
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
            FiComPkcs7 p7 = new FiComPkcs7(this.getSignature());
            return p7;
        }
        catch(IllegalArgumentException iae) {
            log.debug("not a pkcs7?", iae);
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
            log.error("", t);
            return null;
        }
    }

    /** The final MSS_StatusResp message */
    public MSS_StatusResp getMSS_StatusResp() {
        return this.statusResp;
    }

    byte[] getSignature() {
        if(this.statusResp == null)
            throw new RuntimeException("illegal state. Null statusResp.");

        if(this.statusResp.getMSS_Signature() == null)
            throw new RuntimeException("illegal state. Null statusResp.MSS_Signature");

        return this.statusResp.getMSS_Signature().getBase64Signature();
    }

    /*
    boolean isValid() {
        long statusCode = this.statusResp.getStatus().getStatusCode().getValue();

        return 502 == statusCode; //TODO magic number. Consider putting the status codes in a file
        // TODO: read the AE validation status from a status detail element
    }
    */

}
