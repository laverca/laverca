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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fi.laverca.etsi.EtsiAdditionalServices;
import fi.laverca.jaxb.mss.AdditionalServiceType;
import fi.laverca.jaxb.mss.MssURIType;
import fi.laverca.jaxb.mss.StatusDetailType;
import fi.laverca.jaxb.mssfi.NoSpamCode;
import fi.laverca.jaxb.mssfi.ServiceResponses;
import fi.laverca.jaxb.mssfi.ServiceResponses.ServiceResponse;
import fi.laverca.jaxb.saml2a.Attribute;
import fi.laverca.jaxb.saml2p.AttributeQuery;
import fi.laverca.util.Saml2Util;

/**
 * FiCom specific AdditionalServices
 * <p>As per MSS FiCom Implementation Guideline v2.1
 */
public class FiComAdditionalServices {
    
    private static final Log log = LogFactory.getLog(FiComAdditionalServices.class);

    public static final String FICOMNS        = "http://mss.ficom.fi/TS102204/v1.0.0#";
    
    public static final String NO_SPAM_URI    = "http://mss.ficom.fi/TS102204/v1.0.0#noSpam";
    public static final String EVENT_ID_URI   = "http://mss.ficom.fi/TS102204/v1.0.0#eventId";
    public static final String USER_LANG_URI  = "http://mss.ficom.fi/TS102204/v1.0.0#userLang";
    public static final String PERSON_ID_URI  = "http://mss.ficom.fi/TS102204/v1.0.0#personIdentity";
    public static final String VALIDATE_URI   = "http://mss.ficom.fi/TS102204/v1.0.0#validate";

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
        
        final AdditionalServiceType s = EtsiAdditionalServices.createService(NO_SPAM_URI);

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
        final AdditionalServiceType s = EtsiAdditionalServices.createService(EVENT_ID_URI);
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
        final AdditionalServiceType s = EtsiAdditionalServices.createService(USER_LANG_URI);
        final JAXBElement<String> u = FiComClient.ficomFact.createUserLang(userLang);
        s.getSessionIDsAndEventIDsAndNoSpamCodes().add(u);
        return s;
    }
    
    /**
     * Creates an AdditionalService for FiCom validation service
     * @return FiCom Validation AdditionalService
     */
    public static AdditionalServiceType createValidateService() {
        return EtsiAdditionalServices.createService(VALIDATE_URI);
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
     * @param attributeName List of attribute names to put in the PersonId Service
     * @return FiCom PersonID AdditionalService
     */    
    public static AdditionalServiceType createPersonIdService(final String ... attributeName) {
        final List<String> attrNames = new ArrayList<String>();
        if (attributeName != null) {
            for (final String s : attributeName) {
                if (s != null) {
                    attrNames.add(s);
                }
            }
        }
        return createPersonIdService(attrNames);
    }

    /** 
     * Create an AdditionalService for SAML2 AttributeQuery
     * @param uri AdditionalService URI
     * @param attributeQuery SAML2 attribute query, as per FiCom 2.0.
     * @return Created AdditionalService
     */
    public static AdditionalServiceType createAttributeQueryService(final String         uri, 
                                                                    final AttributeQuery attributeQuery) {
        final AdditionalServiceType s = EtsiAdditionalServices.createService(uri);
        s.getSessionIDsAndEventIDsAndNoSpamCodes().add(attributeQuery);
        return s;
    }

    /**
     * FiCom PersonID AdditionalService attribute
     *
     */
    public static class PersonIdAttribute {
        
        private Attribute samlAttribute;
        
        public PersonIdAttribute(final Attribute samlAttribute) {
            if (samlAttribute == null) {
                throw new IllegalArgumentException("null attribute not allowed.");
            }
            this.samlAttribute = samlAttribute;
        }
        
        /**
         * Get the attribute name
         * @return Name of the attribute
         */
        public String getName() {
            return this.samlAttribute.getName();
        }
        
        /**
         * Get the attribute value as String
         * @return Value of the attribute
         */
        public String getStringValue() {
            try {
                final List<Object> avl = this.samlAttribute.getAttributeValues();
                final Object o = avl.get(0);
                if (PERSON_ID_ADDRESS.equals(this.samlAttribute.getName())){
                    fi.laverca.jaxb.mssfi.PostalAddress pa;
                    try {
                        pa = (fi.laverca.jaxb.mssfi.PostalAddress)o;
                    } catch (Throwable t){
                        return null;
                    }
                    return postalAddressToString(pa);       
                }
                /*
                else {
                    
                    org.exolab.castor.types.AnyNode an = (org.exolab.castor.types.AnyNode)o;
                    return an.getStringValue();
                }
                */
                log.debug("Failed getting String value from " + this.samlAttribute.getName() + ".");
                return null;
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
         * <p>There is no standard way to print a postal address. 
         * <br>Laverca uses comma separated values.
         * 
         * @param pa PostalAddress to be printed
         * @return Postal address CSV or null if the given PostalAddress was null.
         */
        private String postalAddressToString(final fi.laverca.jaxb.mssfi.PostalAddress pa){
            if (pa == null) {
                return null;
            }
            return pa.getName()          + ", " +
                   pa.getStreetAddress() + ", " +
                   pa.getPostalCode()    + ", " +
                   pa.getTown()          + ", " +
                   pa.getCountry();
        }
    }

    /**
     * Read ServiceResponses from a StatusDetail element
     * @param sd StatusDetail
     * @return ServiceResponses or null
     * @throws JAXBException 
     */
    public static ServiceResponses readServiceResponses(final StatusDetailType sd)
        throws JAXBException
    {
        if (sd == null) {
            return null;
        }
        
        final List<Object> le = sd.getAniesAndServiceResponsesAndReceiptRequestExtensions();
        ServiceResponses ret = null;
        for (final Object o : le) {
            if (!(o instanceof ServiceResponses)) {
                continue;
            }
            // Check that there is only one...
            if (ret != null) {
                log.warn("mss:StatusDetail contains multiple ServiceResponses instances. Returning the first.");
                break;
            }
            ret = (ServiceResponses) o;
        }
        return ret;
    }

    /**
     * Read a specific ServiceResponse from a StatusDetail element
     *
     * @param sd StatusDetail
     * @param serviceUri URI of the ServiceResponse
     * @return single ServiceResponse or null
     * @throws JAXBException 
     */
    public static ServiceResponse readServiceResponse(final StatusDetailType sd, 
                                                      final String       serviceUri)
        throws JAXBException
    {
        if (sd == null) {
            return null;
        }
        final ServiceResponses sr = readServiceResponses(sd);
        // Note that this assumes only one service response for a given URI
        // It is an ok assumption in FiCom 2.0.
        for (final ServiceResponse sResp : sr.getServiceResponses()) {
            final MssURIType d = sResp.getDescription();
            final String   dUri = d.getMssURI();
            if (serviceUri.equals(dUri)) {
                return sResp;
            }
        }
        return null;
    }
    
}
