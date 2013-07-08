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

package org.w3.www._2000._09.xmldsig;

/**
 * Class X509DataTypeItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class X509DataTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _x509DataTypeChoice.
     */
    private org.w3.www._2000._09.xmldsig.X509DataTypeChoice _x509DataTypeChoice;


      //----------------/
     //- Constructors -/
    //----------------/

    public X509DataTypeItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'x509DataTypeChoice'.
     * 
     * @return the value of field 'X509DataTypeChoice'.
     */
    public org.w3.www._2000._09.xmldsig.X509DataTypeChoice getX509DataTypeChoice(
    ) {
        return this._x509DataTypeChoice;
    }

    /**
     * Sets the value of field 'x509DataTypeChoice'.
     * 
     * @param x509DataTypeChoice the value of field
     * 'x509DataTypeChoice'.
     */
    public void setX509DataTypeChoice(
            final org.w3.www._2000._09.xmldsig.X509DataTypeChoice x509DataTypeChoice) {
        this._x509DataTypeChoice = x509DataTypeChoice;
    }

}
