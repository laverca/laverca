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

package fi.laverca.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import fi.laverca.jaxb.saml2a.Assertion;
import fi.laverca.jaxb.saml2a.AttributeStatement;
import fi.laverca.jaxb.saml2a.Attribute;
import fi.laverca.jaxb.saml2a.NameIDType;
import fi.laverca.jaxb.saml2a.ObjectFactory;
import fi.laverca.jaxb.saml2a.StatementAbstractType;
import fi.laverca.jaxb.saml2a.Subject;
import fi.laverca.jaxb.saml2p.AttributeQuery;
import fi.laverca.jaxb.saml2p.RequestAbstractType;
import fi.laverca.jaxb.saml2p.Response;

/**
 * A collection of helper methods for commonplace SAML2 tasks.
 */
public class Saml2Util {
    
    private static ObjectFactory objFactA = new ObjectFactory();
    private static fi.laverca.jaxb.saml2p.ObjectFactory objFactB = new fi.laverca.jaxb.saml2p.ObjectFactory(); 
    
    /**
     * Fill required SAML2 request fields: 
     * <ul>
     * <li>ID
     * <li>Version
     * <li>IssueInstant
     * </ul>
     * See saml-core-2.0-os.pdf, section 3.2.1.
     * 
     * @param req Request to fill
     */
    public static void fillRequiredFields(final RequestAbstractType req) {
        Calendar d = new GregorianCalendar();
        String id = "id-"+d;
        req.setID(id); 
        req.setVersion("2.0");
        req.setIssueInstant(d);
    }

    /**
     * Create a SAML2 Subject element
     * 
     * @param nameIdContent Content for the NameID element
     * @param sPProvidedID  SP Provider ID
     * @return SAML2 Subject
     */
    public static Subject createSubject(final String nameIdContent, 
                                        final String sPProvidedID) {
        
        final NameIDType nameId = objFactA.createNameIDType();
        nameId.setValue(nameIdContent);
        nameId.setSPProvidedID(sPProvidedID);
        
        final Subject subject = objFactA.createSubject();
        subject.setNameID(nameId);
        return subject;
    }

    /**
     * Create a SAML2 AttributeQuery element
     * 
     * @param nameIdContent Content for the NameID element
     * @param sPProvidedID  SP Provider ID
     * @param attributeNames List of attribute names to add to the query
     * @return new SAML2 AttributeQuery
     */
    public static AttributeQuery createAttributeQuery(final String nameIdContent, 
                                                      final String sPProvidedID, 
                                                      final List<String> attributeNames) {
        
        final AttributeQuery attributeQuery = objFactB.createAttributeQuery();
        fillRequiredFields(attributeQuery);
        
        final Subject subject = createSubject(nameIdContent, sPProvidedID);
        attributeQuery.setSubject(subject);
        
        final List<Attribute> al = attributeQuery.getAttributes();
        
        if (attributeNames != null) {
            for (final String attributeName : attributeNames) {
                final Attribute a = objFactA.createAttribute();
                a.setName(attributeName);
                al.add(a);
            }
        }
        
        return attributeQuery;
    }
    
    /** 
     * Read the first Assertion from inside a Response. 
     * 
     * @param response SAML2 response
     * @return The first Assertion element or null
     */
    public static Assertion parseFromResponse(final Response response) {
        if (response == null) return null;
        final List<Object> aList = response.getAssertionsAndEncryptedAssertions();
        if (aList == null) return null;

        for (final Object a : aList) {
            if (a instanceof Assertion) {
                return (Assertion)a;
            }
        }

        return null;
    }

    /** 
     * Read the first AttributeStatement from inside an Assertion. 
     * 
     * @param assertion The SAML2 Assertion
     * @return The first AttributeStatement or null
     */
    public static AttributeStatement parseFromAssertion(final Assertion assertion) {
        if (assertion == null) return null;
        final List<StatementAbstractType> sList = assertion.getStatementsAndAuthnStatementsAndAuthzDecisionStatements();
        for (final StatementAbstractType s : sList) {
            if (s instanceof AttributeStatement) {
                return (AttributeStatement)s;
            }
        }

        return null;
    }

    /** 
     * Read the Attributes from inside an AttributeStatement. 
     * 
     * @param as Attribute statement
     * @return List of attributes (may be empty)
     */
    public static List<Attribute> parseFromAttributeStatement(final AttributeStatement as) {
        List<Attribute> attributes = new ArrayList<>();

        if (as == null) return attributes;
        final List<Object> aList = as.getAttributesAndEncryptedAttributes();
        for (final Object a : aList) {
            if (a instanceof Attribute) {
                attributes.add((Attribute)a);
            }
        }
        return attributes;
    }
}
