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
