/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2013 Laverca Project
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
 * Class ConditionsTypeItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ConditionsTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _condition.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Condition _condition;

    /**
     * Field _audienceRestriction.
     */
    private oasis.names.tc.SAML.v2_0.assertion.AudienceRestriction _audienceRestriction;

    /**
     * Field _oneTimeUse.
     */
    private oasis.names.tc.SAML.v2_0.assertion.OneTimeUse _oneTimeUse;

    /**
     * Field _proxyRestriction.
     */
    private oasis.names.tc.SAML.v2_0.assertion.ProxyRestriction _proxyRestriction;


      //----------------/
     //- Constructors -/
    //----------------/

    public ConditionsTypeItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'audienceRestriction'.
     * 
     * @return the value of field 'AudienceRestriction'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.AudienceRestriction getAudienceRestriction(
    ) {
        return this._audienceRestriction;
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
     * Returns the value of field 'condition'.
     * 
     * @return the value of field 'Condition'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.Condition getCondition(
    ) {
        return this._condition;
    }

    /**
     * Returns the value of field 'oneTimeUse'.
     * 
     * @return the value of field 'OneTimeUse'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.OneTimeUse getOneTimeUse(
    ) {
        return this._oneTimeUse;
    }

    /**
     * Returns the value of field 'proxyRestriction'.
     * 
     * @return the value of field 'ProxyRestriction'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.ProxyRestriction getProxyRestriction(
    ) {
        return this._proxyRestriction;
    }

    /**
     * Sets the value of field 'audienceRestriction'.
     * 
     * @param audienceRestriction the value of field
     * 'audienceRestriction'.
     */
    public void setAudienceRestriction(
            final oasis.names.tc.SAML.v2_0.assertion.AudienceRestriction audienceRestriction) {
        this._audienceRestriction = audienceRestriction;
        this._choiceValue = audienceRestriction;
    }

    /**
     * Sets the value of field 'condition'.
     * 
     * @param condition the value of field 'condition'.
     */
    public void setCondition(
            final oasis.names.tc.SAML.v2_0.assertion.Condition condition) {
        this._condition = condition;
        this._choiceValue = condition;
    }

    /**
     * Sets the value of field 'oneTimeUse'.
     * 
     * @param oneTimeUse the value of field 'oneTimeUse'.
     */
    public void setOneTimeUse(
            final oasis.names.tc.SAML.v2_0.assertion.OneTimeUse oneTimeUse) {
        this._oneTimeUse = oneTimeUse;
        this._choiceValue = oneTimeUse;
    }

    /**
     * Sets the value of field 'proxyRestriction'.
     * 
     * @param proxyRestriction the value of field 'proxyRestriction'
     */
    public void setProxyRestriction(
            final oasis.names.tc.SAML.v2_0.assertion.ProxyRestriction proxyRestriction) {
        this._proxyRestriction = proxyRestriction;
        this._choiceValue = proxyRestriction;
    }

}
