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

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class MeshMemberType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MeshMemberType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _DNSName.
     */
    private java.lang.String _DNSName;

    /**
     * Field _IPAddress.
     */
    private java.lang.String _IPAddress;

    /**
     * Field _URI.
     */
    private java.lang.String _URI;

    /**
     * Field _identifierString.
     */
    private java.lang.String _identifierString;


      //----------------/
     //- Constructors -/
    //----------------/

    public MeshMemberType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Overrides the java.lang.Object.equals method.
     * 
     * @param obj
     * @return true if the objects are equal.
     */
    @Override()
    public boolean equals(
            final java.lang.Object obj) {
        if ( this == obj )
            return true;

        if (obj instanceof MeshMemberType) {

            MeshMemberType temp = (MeshMemberType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._DNSName != null) {
                if (temp._DNSName == null) return false;
                if (this._DNSName != temp._DNSName) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._DNSName);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._DNSName);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DNSName); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DNSName); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._DNSName.equals(temp._DNSName)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DNSName);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DNSName);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DNSName);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DNSName);
                    }
                }
            } else if (temp._DNSName != null)
                return false;
            if (this._IPAddress != null) {
                if (temp._IPAddress == null) return false;
                if (this._IPAddress != temp._IPAddress) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._IPAddress);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._IPAddress);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._IPAddress); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._IPAddress); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._IPAddress.equals(temp._IPAddress)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._IPAddress);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._IPAddress);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._IPAddress);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._IPAddress);
                    }
                }
            } else if (temp._IPAddress != null)
                return false;
            if (this._URI != null) {
                if (temp._URI == null) return false;
                if (this._URI != temp._URI) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._URI);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._URI);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._URI); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._URI); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._URI.equals(temp._URI)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._URI);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._URI);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._URI);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._URI);
                    }
                }
            } else if (temp._URI != null)
                return false;
            if (this._identifierString != null) {
                if (temp._identifierString == null) return false;
                if (this._identifierString != temp._identifierString) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._identifierString);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._identifierString);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._identifierString); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._identifierString); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._identifierString.equals(temp._identifierString)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._identifierString);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._identifierString);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._identifierString);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._identifierString);
                    }
                }
            } else if (temp._identifierString != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'DNSName'.
     * 
     * @return the value of field 'DNSName'.
     */
    public java.lang.String getDNSName(
    ) {
        return this._DNSName;
    }

    /**
     * Returns the value of field 'IPAddress'.
     * 
     * @return the value of field 'IPAddress'.
     */
    public java.lang.String getIPAddress(
    ) {
        return this._IPAddress;
    }

    /**
     * Returns the value of field 'identifierString'.
     * 
     * @return the value of field 'IdentifierString'.
     */
    public java.lang.String getIdentifierString(
    ) {
        return this._identifierString;
    }

    /**
     * Returns the value of field 'URI'.
     * 
     * @return the value of field 'URI'.
     */
    public java.lang.String getURI(
    ) {
        return this._URI;
    }

    /**
     * Overrides the java.lang.Object.hashCode method.
     * <p>
     * The following steps came from <b>Effective Java Programming
     * Language Guide</b> by Joshua Bloch, Chapter 3
     * 
     * @return a hash code value for the object.
     */
    public int hashCode(
    ) {
        int result = 17;

        long tmp;
        if (_DNSName != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_DNSName)) {
           result = 37 * result + _DNSName.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_DNSName);
        }
        if (_IPAddress != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_IPAddress)) {
           result = 37 * result + _IPAddress.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_IPAddress);
        }
        if (_URI != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_URI)) {
           result = 37 * result + _URI.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_URI);
        }
        if (_identifierString != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_identifierString)) {
           result = 37 * result + _identifierString.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_identifierString);
        }

        return result;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * Sets the value of field 'DNSName'.
     * 
     * @param DNSName the value of field 'DNSName'.
     */
    public void setDNSName(
            final java.lang.String DNSName) {
        this._DNSName = DNSName;
    }

    /**
     * Sets the value of field 'IPAddress'.
     * 
     * @param IPAddress the value of field 'IPAddress'.
     */
    public void setIPAddress(
            final java.lang.String IPAddress) {
        this._IPAddress = IPAddress;
    }

    /**
     * Sets the value of field 'identifierString'.
     * 
     * @param identifierString the value of field 'identifierString'
     */
    public void setIdentifierString(
            final java.lang.String identifierString) {
        this._identifierString = identifierString;
    }

    /**
     * Sets the value of field 'URI'.
     * 
     * @param URI the value of field 'URI'.
     */
    public void setURI(
            final java.lang.String URI) {
        this._URI = URI;
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
