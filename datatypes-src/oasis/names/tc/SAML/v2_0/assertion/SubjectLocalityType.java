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

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class SubjectLocalityType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class SubjectLocalityType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _address.
     */
    private java.lang.String _address;

    /**
     * Field _DNSName.
     */
    private java.lang.String _DNSName;


      //----------------/
     //- Constructors -/
    //----------------/

    public SubjectLocalityType() {
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

        if (obj instanceof SubjectLocalityType) {

            SubjectLocalityType temp = (SubjectLocalityType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._address != null) {
                if (temp._address == null) return false;
                if (this._address != temp._address) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._address);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._address);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._address); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._address); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._address.equals(temp._address)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._address);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._address);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._address);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._address);
                    }
                }
            } else if (temp._address != null)
                return false;
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
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'address'.
     * 
     * @return the value of field 'Address'.
     */
    public java.lang.String getAddress(
    ) {
        return this._address;
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
        if (_address != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_address)) {
           result = 37 * result + _address.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_address);
        }
        if (_DNSName != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_DNSName)) {
           result = 37 * result + _DNSName.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_DNSName);
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
     * Sets the value of field 'address'.
     * 
     * @param address the value of field 'address'.
     */
    public void setAddress(
            final java.lang.String address) {
        this._address = address;
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
