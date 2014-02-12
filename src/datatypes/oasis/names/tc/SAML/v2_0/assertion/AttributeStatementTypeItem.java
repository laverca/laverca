/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2014 Laverca Project
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
