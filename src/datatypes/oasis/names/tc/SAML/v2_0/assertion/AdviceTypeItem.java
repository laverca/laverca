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
 * Class AdviceTypeItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AdviceTypeItem implements java.io.Serializable {


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

    /**
     * Field _anyObject.
     */
    private java.lang.Object _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public AdviceTypeItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'anyObject'.
     * 
     * @return the value of field 'AnyObject'.
     */
    public java.lang.Object getAnyObject(
    ) {
        return this._anyObject;
    }

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
     * Sets the value of field 'anyObject'.
     * 
     * @param anyObject the value of field 'anyObject'.
     */
    public void setAnyObject(
            final java.lang.Object anyObject) {
        this._anyObject = anyObject;
        this._choiceValue = anyObject;
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
