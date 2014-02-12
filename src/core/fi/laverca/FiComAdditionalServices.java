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

package fi.laverca;

import java.util.ArrayList;
import java.util.List;
import oasis.names.tc.SAML.v2_0.assertion.Attribute;
import oasis.names.tc.SAML.v2_0.protocol.AttributeQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.AdditionalServiceTypeChoice;
import org.etsi.uri.TS102204.v1_1_2.AdditionalServiceTypeChoiceItem;
import org.etsi.uri.TS102204.v1_1_2.Service;
import org.etsi.uri.TS102204.v1_1_2.StatusDetail;
import org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem;
import fi.ficom.mss.TS102204.v1_0_0.Description;
import fi.ficom.mss.TS102204.v1_0_0.NoSpamCode;
import fi.ficom.mss.TS102204.v1_0_0.ServiceResponse;
import fi.ficom.mss.TS102204.v1_0_0.ServiceResponses;

/**
 * As per FiCom Implementation Guideline v2.0
 */
public class FiComAdditionalServices {
    private static final Log log = LogFactory.getLog(FiComAdditionalServices.class);

    //TODO REVIEW
    public static final String NO_SPAM_URI    = "http://mss.ficom.fi/TS102204/v1.0.0#noSpam";
    public static final String EVENT_ID_URI   = "http://mss.ficom.fi/TS102204/v1.0.0#eventId";
    public static final String USER_LANG_URI  = "http://mss.ficom.fi/TS102204/v1.0.0#userLang";
    public static final String PERSON_ID_URI  = "http://mss.ficom.fi/TS102204/v1.0.0#personIdentity";
    public static final String VALIDATE_URI   = "http://mss.ficom.fi/TS102204/v1.0.0#validate"; //http://uri.etsi.org/TS102204/v1.1.2#validate

    //personid attributes
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
     * Create an AdditionalService for FiCom noSpam codes as per FiCom 2.0
     * @param String noSpamCodeValue the value of the nospam code
     * @param boolean verifyValue
     */
    public static Service createNoSpamService(String noSpamCodeValue, boolean verifyValue) {
        Service s = EtsiAdditionalServices.createService(NO_SPAM_URI);

        NoSpamCode noSpamObject = new NoSpamCode();
        noSpamObject.setContent(noSpamCodeValue);

        AdditionalServiceTypeChoice astc = new AdditionalServiceTypeChoice();
        AdditionalServiceTypeChoiceItem astci = new AdditionalServiceTypeChoiceItem();
        astc.addAdditionalServiceTypeChoiceItem(astci);
        astci.setNoSpamCode(noSpamObject);
        s.setAdditionalServiceTypeChoice(astc);

        if (verifyValue == false) {
            noSpamObject.setVerify("no");
        }

        return s;
    }

    /**
     * Creates an AdditionalService for FiCom eventId
     * @param String eventId
     */
    public static Service createEventIdService(String eventId) {
        Service s = EtsiAdditionalServices.createService(EVENT_ID_URI);

        AdditionalServiceTypeChoice astc = new AdditionalServiceTypeChoice();
        AdditionalServiceTypeChoiceItem astci = new AdditionalServiceTypeChoiceItem();
        astc.addAdditionalServiceTypeChoiceItem(astci);
        astci.setEventID(eventId);
        s.setAdditionalServiceTypeChoice(astc);
        return s;
    }

    public static Service createUserLangService(String userLang) {
        Service s = EtsiAdditionalServices.createService(USER_LANG_URI);

        AdditionalServiceTypeChoice astc = new AdditionalServiceTypeChoice();
        AdditionalServiceTypeChoiceItem astci = new AdditionalServiceTypeChoiceItem();
        astc.addAdditionalServiceTypeChoiceItem(astci);
        astci.setUserLang(userLang);
        s.setAdditionalServiceTypeChoice(astc);
        return s;
    }
    
    /**
     * Helper method for creating a Validation Service.
     * @return Created Service
     */
    public static Service createValidateService() {
    	Service s = EtsiAdditionalServices.createService(VALIDATE_URI);
        return s;
    }

    /**
     * Creates a new PersonId Service from the given attribute names.
     * @param attributeNames List of attribute names to put in the PersonId Service
     * @return Created Service
     */
    public static Service createPersonIdService(List<String> attributeNames) {
        AttributeQuery aq = Saml2Util.createAttributeQuery(null, null, attributeNames);
        return createAttributeQueryService(PERSON_ID_URI, aq);
    }
    

    /**
     * Creates a new PersonId Service from one attribute name.
     * @param attributeName Attribtue name to put in the PersonId Service
     * @return Created Service
     */    
    public static Service createPersonIdService(String attributeName) {
        List<String> attrNames = new ArrayList<String>();
        attrNames.add(attributeName);
        return createPersonIdService(attrNames);
    }

    /** 
     * Create an AdditionalService for SAML2 AttributeQuery
     * @param attributeQuery SAML2 attribute query, as per FiCom 2.0.
     * @return Created Service
     */
    public static Service createAttributeQueryService(String uri, AttributeQuery attributeQuery) {
        Service s = EtsiAdditionalServices.createService(PERSON_ID_URI);

        AdditionalServiceTypeChoice astc = new AdditionalServiceTypeChoice();
        AdditionalServiceTypeChoiceItem astci = new AdditionalServiceTypeChoiceItem();
        astc.addAdditionalServiceTypeChoiceItem(astci);
        astci.setAttributeQuery(attributeQuery);
        s.setAdditionalServiceTypeChoice(astc);
        return s;
    }

    public static class PersonIdAttribute {
        Attribute samlAttribute;
        
        PersonIdAttribute(Attribute samlAttribute) {
            if(samlAttribute == null) {
                throw new IllegalArgumentException("null attribute not allowed.");
            }
            this.samlAttribute = samlAttribute;
        }
        
        public String getName() {
            return this.samlAttribute.getName();
        }
        
        public String getStringValue() {
            try {
                if (PERSON_ID_ADDRESS.equals(this.samlAttribute.getName())){
                    Object o = this.samlAttribute.getAttributeValue(0);
                    fi.ficom.mss.TS102204.v1_0_0.PostalAddress pa;
                    try {
                        pa = (fi.ficom.mss.TS102204.v1_0_0.PostalAddress)o;
                    } catch (Throwable t){
                        return null;
                    }
                    return postalAddressToString(pa);       
                } else {
                    Object o = this.samlAttribute.getAttributeValue(0);
                    org.exolab.castor.types.AnyNode an = (org.exolab.castor.types.AnyNode)o;
                    return an.getStringValue();
                }
            } catch (IndexOutOfBoundsException ioe) {
                log.debug("Failed getting String value from " + this.samlAttribute.getName() + ".");
                return null;
            } catch(Throwable t) {
                log.warn("Failed getting String value from " + this.samlAttribute.getName() + "." , t);
                return null;
            }
        }
        
        /**
         * Pretty print postal address 
         * There is no standard way to print postal address. Laverca uses comma separated values.
         * @param pa PostalAddress class to be printed
         * @return Pretty print of PostalAddress or null if the given PostalAddress was null.
         */
        private String postalAddressToString(fi.ficom.mss.TS102204.v1_0_0.PostalAddress pa){
            if (pa == null) {
                return null;
            }
            return pa.getName() + ", " + 
                   pa.getStreetAddress() + ", " + 
                   pa.getPostalCode() + " " +
                   pa.getTown() + ", " +
                   pa.getCountry();
        }
    }

    public static ServiceResponses readServiceResponses(StatusDetail sd) {
        if(sd == null) {
            return null;
        }
        ServiceResponses sr = null;
        for(StatusDetailTypeItem item : sd.getStatusDetailTypeItem()) {
            if(item.getServiceResponses() != null) {
                if(sr != null) {
                    log.warn("mss:StatusDetail contains multiple ServiceResponses instances. Returning the first.");
                    break;
                }
                sr = item.getServiceResponses();
            }
        }
        return sr;
    }

    public static ServiceResponse readServiceResponse(StatusDetail sd, String serviceUri) {
        if(sd == null) {
            return null;
        }
        ServiceResponses sr = readServiceResponses(sd);
        // note that this assumes only one service response for a given uri
        // it's an ok assumption in FiCom 2.0.
        for(ServiceResponse sResp : sr.getServiceResponse()) {
            Description d = sResp.getDescription();
            String dUri = d.getMssURI();
            if(serviceUri.equals(dUri))
                return sResp;
        }
        return null;
    }
}