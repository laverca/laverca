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

package org.etsi.uri.TS102204.v1_1_2.types;

/**
 * Enumeration SignatureProfileComparisonType.
 * 
 * @version $Revision$ $Date$
 */
public enum SignatureProfileComparisonType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant EXACT
     */
    EXACT("exact"),
    /**
     * Constant MINIMUM
     */
    MINIMUM("minimum"),
    /**
     * Constant BETTER
     */
    BETTER("better");

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field value.
     */
    private final java.lang.String value;

    /**
     * Field enumConstants.
     */
    private static final java.util.Map<java.lang.String, SignatureProfileComparisonType> enumConstants = new java.util.HashMap<java.lang.String, SignatureProfileComparisonType>();


    static {
        for (SignatureProfileComparisonType c: SignatureProfileComparisonType.values()) {
            SignatureProfileComparisonType.enumConstants.put(c.value, c);
        }

    };


      //----------------/
     //- Constructors -/
    //----------------/

    private SignatureProfileComparisonType(final java.lang.String value) {
        this.value = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static org.etsi.uri.TS102204.v1_1_2.types.SignatureProfileComparisonType fromValue(
            final java.lang.String value) {
        SignatureProfileComparisonType c = SignatureProfileComparisonType.enumConstants.get(value);
        if (c != null) {
            return c;
        }
        throw new IllegalArgumentException(value);
    }

    /**
     * 
     * 
     * @param value
     */
    public void setValue(
            final java.lang.String value) {
    }

    /**
     * Method toString.
     * 
     * @return the value of this constant
     */
    public java.lang.String toString(
    ) {
        return this.value;
    }

    /**
     * Method value.
     * 
     * @return the value of this constant
     */
    public java.lang.String value(
    ) {
        return this.value;
    }

}
