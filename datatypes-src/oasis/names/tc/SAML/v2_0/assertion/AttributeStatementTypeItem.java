/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class AttributeStatementTypeItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AttributeStatementTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _attribute.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Attribute _attribute;

    /**
     * Field _encryptedAttribute.
     */
    private oasis.names.tc.SAML.v2_0.assertion.EncryptedAttribute _encryptedAttribute;


      //----------------/
     //- Constructors -/
    //----------------/

    public AttributeStatementTypeItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'attribute'.
     * 
     * @return the value of field 'Attribute'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.Attribute getAttribute(
    ) {
        return this._attribute;
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
     * Returns the value of field 'encryptedAttribute'.
     * 
     * @return the value of field 'EncryptedAttribute'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.EncryptedAttribute getEncryptedAttribute(
    ) {
        return this._encryptedAttribute;
    }

    /**
     * Sets the value of field 'attribute'.
     * 
     * @param attribute the value of field 'attribute'.
     */
    public void setAttribute(
            final oasis.names.tc.SAML.v2_0.assertion.Attribute attribute) {
        this._attribute = attribute;
        this._choiceValue = attribute;
    }

    /**
     * Sets the value of field 'encryptedAttribute'.
     * 
     * @param encryptedAttribute the value of field
     * 'encryptedAttribute'.
     */
    public void setEncryptedAttribute(
            final oasis.names.tc.SAML.v2_0.assertion.EncryptedAttribute encryptedAttribute) {
        this._encryptedAttribute = encryptedAttribute;
        this._choiceValue = encryptedAttribute;
    }

}
