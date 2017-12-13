/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2015 Laverca Project
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

package fi.laverca.ficom;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBElement;

import fi.laverca.jaxb.mss.AdditionalServiceType;
import fi.laverca.jaxb.mssfi.NoSpamCode;
import fi.laverca.jaxb.saml2p.AttributeQuery;
import fi.laverca.mss.AdditionalServices;
import fi.laverca.util.Saml2Util;

/**
 * FiCom specific AdditionalServices
 * <p>As per MSS FiCom Implementation Guideline v2.1
 */
public class FiComAdditionalServices extends AdditionalServices {
    
    public static final String FICOMNS        = "http://mss.ficom.fi/TS102204/v1.0.0#";
    
    public static final String NO_SPAM_URI    = "http://mss.ficom.fi/TS102204/v1.0.0#noSpam";
    public static final String EVENT_ID_URI   = "http://mss.ficom.fi/TS102204/v1.0.0#eventId";
    public static final String USER_LANG_URI  = "http://mss.ficom.fi/TS102204/v1.0.0#userLang";
    public static final String PERSON_ID_URI  = "http://mss.ficom.fi/TS102204/v1.0.0#personIdentity";
    public static final String VALIDATE_URI   = "http://mss.ficom.fi/TS102204/v1.0.0#validate";
    public static final String DISPLAY_NAME_URI = "http://mss.ficom.fi/TS102204/v1.0.0#displayName";

    // PersonID attributes
    public static final String PERSON_ID_HETU       = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#hetu";
    public static final String PERSON_ID_SATU       = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#satu";
    public static final String PERSON_ID_ADDRESS    = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#address";
    public static final String PERSON_ID_AGE        = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#age";
    public static final String PERSON_ID_AGECLASS   = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#ageClass";
    public static final String PERSON_ID_EMAIL      = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#email";
    public static final String PERSON_ID_GENDER     = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#gender";
    public static final String PERSON_ID_GIVENNAME  = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#givenName";
    public static final String PERSON_ID_SURNAME    = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#surName";
    public static final String PERSON_ID_SUBJECT    = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#subject";
    public static final String PERSON_ID_VALIDUNTIL = "http://mss.ficom.fi/TS102204/v1.0.0/PersonID#validUntil";

    /** 
     * Creates an AdditionalService for FiCom NoSpam service
     * 
     * @param noSpamCodeValue the value of the nospam code
     * @param verifyValue Set NoSpamCode verify flag on / off
     * @return FiCom NoSpam AdditionalService
     */
    public static AdditionalServiceType createNoSpamService(final String  noSpamCodeValue, 
                                                            final boolean verifyValue) {
        
        final AdditionalServiceType s = createService(NO_SPAM_URI);

        final NoSpamCode noSpamObject = new NoSpamCode();
        noSpamObject.setValue(noSpamCodeValue);

        if (verifyValue == false) {
            noSpamObject.setVerify("no");
        }

        s.getSessionIDsAndEventIDsAndNoSpamCodes().add(noSpamObject);
        
        return s;
    }

    /**
     * Creates an AdditionalService for FiCom EventID service
     * @param eventId Event ID as a String
     * @return FiCom EventID AdditionalService
     */
    public static AdditionalServiceType createEventIdService(final String eventId) {
        final AdditionalServiceType s = createService(EVENT_ID_URI);
        final JAXBElement<String> e = FiComClient.ficomFact.createEventID(eventId);
        s.getSessionIDsAndEventIDsAndNoSpamCodes().add(e);
        return s;
    }

    /**
     * Creates an AdditionalService for FiCom user language service
     * @param userLang User language value as per MSS FiCom Implentation guideline v2.1
     * @return FiCom UserLang AdditionalService
     */
    public static AdditionalServiceType createUserLangService(final String userLang) {
        final AdditionalServiceType s = createService(USER_LANG_URI);
        final JAXBElement<String> u = FiComClient.ficomFact.createUserLang(userLang);
        s.getSessionIDsAndEventIDsAndNoSpamCodes().add(u);
        return s;
    }
    
    /**
     * Creates an AdditionalService for FiCom validation service
     * @return FiCom Validation AdditionalService
     */
    public static AdditionalServiceType createValidateService() {
        return createService(VALIDATE_URI);
    }

    /**
     * Creates a new PersonId Service from the given attribute names.
     * @param attributeNames List of attribute names to put in the PersonId Service
     * @return FiCom PersonID AdditionalService
     */
    public static AdditionalServiceType createPersonIdService(final List<String> attributeNames) {
        final AttributeQuery aq = Saml2Util.createAttributeQuery(null, null, attributeNames);
        return createAttributeQueryService(PERSON_ID_URI, aq);
    }
    

    /**
     * Creates a new PersonId Service from the given attribute names.
     * @param attributeNames List of attribute names to put in the PersonId Service
     * @return FiCom PersonID AdditionalService
     */    
    public static AdditionalServiceType createPersonIdService(final String ... attributeNames) {
        return createPersonIdService(Arrays.asList(attributeNames));
    }

    /** 
     * Create an AdditionalService for SAML2 AttributeQuery
     * @param uri AdditionalService URI
     * @param attributeQuery SAML2 attribute query, as per FiCom 2.0.
     * @return Created AdditionalService
     */
    public static AdditionalServiceType createAttributeQueryService(final String         uri, 
                                                                    final AttributeQuery attributeQuery) {
        final AdditionalServiceType s = createService(uri);
        s.getSessionIDsAndEventIDsAndNoSpamCodes().add(attributeQuery);
        return s;
    }
    
    /**
     * Create an AdditionalService for FiCom AP DisplayName service
     * <p><b>Note:</b> This AdditionalService usually requires special authorization on the MSSP side.
     * @param displayName Name to show on the user's screen instead of the AP_ID
     * @return FiCom DisplayName AdditionalService
     */
    public static AdditionalServiceType createDisplayNameService(final String displayName) {
        final AdditionalServiceType s = createService(DISPLAY_NAME_URI);
        final JAXBElement<String> u = FiComClient.ficomFact.createDisplayName(displayName);
        s.getSessionIDsAndEventIDsAndNoSpamCodes().add(u);
        return s;
    }
    
}
