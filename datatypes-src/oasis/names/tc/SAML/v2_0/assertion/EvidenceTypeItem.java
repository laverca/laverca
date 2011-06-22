/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class EvidenceTypeItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class EvidenceTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _assertionIDRef.
     */
    private java.lang.String _assertionIDRef;

    /**
     * Field _assertionURIRef.
     */
    private java.lang.String _assertionURIRef;

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

    public EvidenceTypeItem() {
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
     * Returns the value of field 'assertionIDRef'.
     * 
     * @return the value of field 'AssertionIDRef'.
     */
    public java.lang.String getAssertionIDRef(
    ) {
        return this._assertionIDRef;
    }

    /**
     * Returns the value of field 'assertionURIRef'.
     * 
     * @return the value of field 'AssertionURIRef'.
     */
    public java.lang.String getAssertionURIRef(
    ) {
        return this._assertionURIRef;
    }

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return the value of field 'ChoiceValue'.
     */
    public java.lang.Object getChoiceValue(
    ) {
        return this._choiceValue;
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
        this._choiceValue = assertion;
    }

    /**
     * Sets the value of field 'assertionIDRef'.
     * 
     * @param assertionIDRef the value of field 'assertionIDRef'.
     */
    public void setAssertionIDRef(
            final java.lang.String assertionIDRef) {
        this._assertionIDRef = assertionIDRef;
        this._choiceValue = assertionIDRef;
    }

    /**
     * Sets the value of field 'assertionURIRef'.
     * 
     * @param assertionURIRef the value of field 'assertionURIRef'.
     */
    public void setAssertionURIRef(
            final java.lang.String assertionURIRef) {
        this._assertionURIRef = assertionURIRef;
        this._choiceValue = assertionURIRef;
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
        this._choiceValue = encryptedAssertion;
    }

}
