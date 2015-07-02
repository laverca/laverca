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

package fi.laverca;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import oasis.names.tc.SAML.v2_0.assertion.Assertion;
import oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem;
import oasis.names.tc.SAML.v2_0.assertion.Attribute;
import oasis.names.tc.SAML.v2_0.assertion.AttributeStatement;
import oasis.names.tc.SAML.v2_0.assertion.AttributeStatementTypeItem;
import oasis.names.tc.SAML.v2_0.assertion.NameID;
import oasis.names.tc.SAML.v2_0.assertion.Subject;
import oasis.names.tc.SAML.v2_0.assertion.SubjectTypeSequence;
import oasis.names.tc.SAML.v2_0.assertion.SubjectTypeSequenceChoice;
import oasis.names.tc.SAML.v2_0.protocol.AttributeQuery;
import oasis.names.tc.SAML.v2_0.protocol.RequestAbstractType;
import oasis.names.tc.SAML.v2_0.protocol.Response;
import oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A collection of helper methods for commonplace SAML2 tasks.
 */
public class Saml2Util {
    
    private static final Log log = LogFactory.getLog(Saml2Util.class);

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
        Date d = new Date();
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
     * @return
     */
    public static Subject createSubject(final String nameIdContent, 
                                        final String sPProvidedID) {
        Subject subject = new Subject();
        SubjectTypeSequence sts = new SubjectTypeSequence();
        SubjectTypeSequenceChoice stsc = new SubjectTypeSequenceChoice();
        sts.setSubjectTypeSequenceChoice(stsc);
        subject.setSubjectTypeSequence(sts);
        
        NameID nameId = new NameID();
        nameId.setContent(nameIdContent);
        nameId.setSPProvidedID(sPProvidedID);
        stsc.setNameID(nameId);

        return subject;
    }

    /**
     * Create a SAML2 AttributeQuery element
     * 
     * @param nameIdContent
     * @param sPProvidedID
     * @param attributeNames
     * @return
     */
    public static AttributeQuery createAttributeQuery(final String nameIdContent, 
                                                      final String sPProvidedID, 
                                                      final List<String> attributeNames) {
        
        AttributeQuery attributeQuery = new AttributeQuery();
        fillRequiredFields(attributeQuery);
        
        Subject subject = createSubject(nameIdContent, sPProvidedID);
        attributeQuery.setSubject(subject);
        
        if (attributeNames != null) {
            for (String attributeName : attributeNames) {
                Attribute a = new Attribute();
                a.setName(attributeName);
                attributeQuery.addAttribute(a);
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
        try {
            for(ResponseTypeChoiceItem item : response.getResponseTypeChoice().getResponseTypeChoiceItem()) {
                if(item.getAssertion() != null) {
                    return item.getAssertion();
                }
            }
        } catch(Throwable t) {
            log.error("", t);
        }
        
        return null;
    }

    /** 
     * Read the first AttributeStatement from inside an Assertion. 
     * 
     * @param assertion
     * @return The first AttributeStatement or null
     */
    public static AttributeStatement parseFromAssertion(final Assertion assertion) {
        try {
            for(AssertionTypeChoiceItem item : assertion.getAssertionTypeChoice().getAssertionTypeChoiceItem()) {
                if(item.getAttributeStatement() != null)
                    return item.getAttributeStatement();
            }
        } catch(Throwable t) {
            log.error("",t);
        }
        
        return null;
    }

    /** 
     * Read the Attributes from inside an AttributeStatement. 
     * 
     * @param as Attribute statement
     * @return List of attributes
     */
    public static List<Attribute> parseFromAttributeStatement(AttributeStatement as) {
        try {
            List<Attribute>         attributes = new LinkedList<Attribute>();
            AttributeStatementTypeItem[] items = as.getAttributeStatementTypeItem();
            for (AttributeStatementTypeItem item : items) {
                Attribute attribute = item.getAttribute();
                attributes.add(attribute);
            }
            return attributes;
        } catch (Throwable t) {
            log.error("", t);
        }
        
        return null;
    }

}
