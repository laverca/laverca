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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fi.laverca.jaxb.saml2a.Attribute;

/**
 * FiCom PersonID AdditionalService attribute
 *
 */
public class PersonIdAttribute {
    
    private static final Log log = LogFactory.getLog(PersonIdAttribute.class);
    
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
            if (FiComAdditionalServices.PERSON_ID_ADDRESS.equals(this.samlAttribute.getName())){
                fi.laverca.jaxb.mssfi.PostalAddress pa;
                try {
                    pa = (fi.laverca.jaxb.mssfi.PostalAddress)o;
                } catch (Throwable t){
                    return null;
                }
                return postalAddressToString(pa);       
            }

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
