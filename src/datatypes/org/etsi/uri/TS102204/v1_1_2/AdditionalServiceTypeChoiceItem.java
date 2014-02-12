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

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class AdditionalServiceTypeChoiceItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AdditionalServiceTypeChoiceItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _sessionID.
     */
    private java.lang.String _sessionID;

    /**
     * Field _eventID.
     */
    private java.lang.String _eventID;

    /**
     * Field _noSpamCode.
     */
    private fi.ficom.mss.TS102204.v1_0_0.NoSpamCode _noSpamCode;

    /**
     * Field _userLang.
     */
    private java.lang.String _userLang;

    /**
     * Field _attributeQuery.
     */
    private oasis.names.tc.SAML.v2_0.protocol.AttributeQuery _attributeQuery;

    /**
     * Field _anyObject.
     */
    private java.lang.Object _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public AdditionalServiceTypeChoiceItem() {
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
     * Returns the value of field 'attributeQuery'.
     * 
     * @return the value of field 'AttributeQuery'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.AttributeQuery getAttributeQuery(
    ) {
        return this._attributeQuery;
    }

    /**
     * Returns the value of field 'eventID'.
     * 
     * @return the value of field 'EventID'.
     */
    public java.lang.String getEventID(
    ) {
        return this._eventID;
    }

    /**
     * Returns the value of field 'noSpamCode'.
     * 
     * @return the value of field 'NoSpamCode'.
     */
    public fi.ficom.mss.TS102204.v1_0_0.NoSpamCode getNoSpamCode(
    ) {
        return this._noSpamCode;
    }

    /**
     * Returns the value of field 'sessionID'.
     * 
     * @return the value of field 'SessionID'.
     */
    public java.lang.String getSessionID(
    ) {
        return this._sessionID;
    }

    /**
     * Returns the value of field 'userLang'.
     * 
     * @return the value of field 'UserLang'.
     */
    public java.lang.String getUserLang(
    ) {
        return this._userLang;
    }

    /**
     * Sets the value of field 'anyObject'.
     * 
     * @param anyObject the value of field 'anyObject'.
     */
    public void setAnyObject(
            final java.lang.Object anyObject) {
        this._anyObject = anyObject;
    }

    /**
     * Sets the value of field 'attributeQuery'.
     * 
     * @param attributeQuery the value of field 'attributeQuery'.
     */
    public void setAttributeQuery(
            final oasis.names.tc.SAML.v2_0.protocol.AttributeQuery attributeQuery) {
        this._attributeQuery = attributeQuery;
    }

    /**
     * Sets the value of field 'eventID'.
     * 
     * @param eventID the value of field 'eventID'.
     */
    public void setEventID(
            final java.lang.String eventID) {
        this._eventID = eventID;
    }

    /**
     * Sets the value of field 'noSpamCode'.
     * 
     * @param noSpamCode the value of field 'noSpamCode'.
     */
    public void setNoSpamCode(
            final fi.ficom.mss.TS102204.v1_0_0.NoSpamCode noSpamCode) {
        this._noSpamCode = noSpamCode;
    }

    /**
     * Sets the value of field 'sessionID'.
     * 
     * @param sessionID the value of field 'sessionID'.
     */
    public void setSessionID(
            final java.lang.String sessionID) {
        this._sessionID = sessionID;
    }

    /**
     * Sets the value of field 'userLang'.
     * 
     * @param userLang the value of field 'userLang'.
     */
    public void setUserLang(
            final java.lang.String userLang) {
        this._userLang = userLang;
    }

}
