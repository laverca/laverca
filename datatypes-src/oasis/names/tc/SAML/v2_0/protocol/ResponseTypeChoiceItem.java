/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class ResponseTypeChoiceItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ResponseTypeChoiceItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _assertion.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Assertion _assertion;

    /**
     * Field _encryptedAssertion.
     */
    private oasis.names.tc.SAML.v2_0.assertion.EncryptedAssertion _encryptedAssertion;


      //----------------/
     //- Constructors -/
    //----------------/

    public ResponseTypeChoiceItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'assertion'.
     * 
     * @return the value of field 'Assertion'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.Assertion getAssertion(
    ) {
        return this._assertion;
    }

    /**
     * Returns the value of field 'encryptedAssertion'.
     * 
     * @return the value of field 'EncryptedAssertion'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.EncryptedAssertion getEncryptedAssertion(
    ) {
        return this._encryptedAssertion;
    }

    /**
     * Sets the value of field 'assertion'.
     * 
     * @param assertion the value of field 'assertion'.
     */
    public void setAssertion(
            final oasis.names.tc.SAML.v2_0.assertion.Assertion assertion) {
        this._assertion = assertion;
    }

    /**
     * Sets the value of field 'encryptedAssertion'.
     * 
     * @param encryptedAssertion the value of field
     * 'encryptedAssertion'.
     */
    public void setEncryptedAssertion(
            final oasis.names.tc.SAML.v2_0.assertion.EncryptedAssertion encryptedAssertion) {
        this._encryptedAssertion = encryptedAssertion;
    }

}
