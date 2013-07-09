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

package fi.ficom.mss.TS102204.v1_0_0;

/**
 * Class PostalAddress.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class PostalAddress implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _streetAddress.
     */
    private java.lang.String _streetAddress;

    /**
     * Field _postalCode.
     */
    private java.lang.String _postalCode;

    /**
     * Field _town.
     */
    private java.lang.String _town;

    /**
     * Field _country.
     */
    private java.lang.String _country;


      //----------------/
     //- Constructors -/
    //----------------/

    public PostalAddress() {
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

        if (obj instanceof PostalAddress) {

            PostalAddress temp = (PostalAddress)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._name != null) {
                if (temp._name == null) return false;
                if (this._name != temp._name) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._name);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._name);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._name.equals(temp._name)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name);
                    }
                }
            } else if (temp._name != null)
                return false;
            if (this._streetAddress != null) {
                if (temp._streetAddress == null) return false;
                if (this._streetAddress != temp._streetAddress) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._streetAddress);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._streetAddress);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._streetAddress); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._streetAddress); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._streetAddress.equals(temp._streetAddress)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._streetAddress);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._streetAddress);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._streetAddress);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._streetAddress);
                    }
                }
            } else if (temp._streetAddress != null)
                return false;
            if (this._postalCode != null) {
                if (temp._postalCode == null) return false;
                if (this._postalCode != temp._postalCode) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._postalCode);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._postalCode);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._postalCode); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._postalCode); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._postalCode.equals(temp._postalCode)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._postalCode);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._postalCode);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._postalCode);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._postalCode);
                    }
                }
            } else if (temp._postalCode != null)
                return false;
            if (this._town != null) {
                if (temp._town == null) return false;
                if (this._town != temp._town) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._town);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._town);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._town); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._town); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._town.equals(temp._town)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._town);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._town);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._town);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._town);
                    }
                }
            } else if (temp._town != null)
                return false;
            if (this._country != null) {
                if (temp._country == null) return false;
                if (this._country != temp._country) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._country);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._country);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._country); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._country); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._country.equals(temp._country)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._country);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._country);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._country);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._country);
                    }
                }
            } else if (temp._country != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'country'.
     * 
     * @return the value of field 'Country'.
     */
    public java.lang.String getCountry(
    ) {
        return this._country;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'postalCode'.
     * 
     * @return the value of field 'PostalCode'.
     */
    public java.lang.String getPostalCode(
    ) {
        return this._postalCode;
    }

    /**
     * Returns the value of field 'streetAddress'.
     * 
     * @return the value of field 'StreetAddress'.
     */
    public java.lang.String getStreetAddress(
    ) {
        return this._streetAddress;
    }

    /**
     * Returns the value of field 'town'.
     * 
     * @return the value of field 'Town'.
     */
    public java.lang.String getTown(
    ) {
        return this._town;
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
        if (_name != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_name)) {
           result = 37 * result + _name.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_name);
        }
        if (_streetAddress != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_streetAddress)) {
           result = 37 * result + _streetAddress.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_streetAddress);
        }
        if (_postalCode != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_postalCode)) {
           result = 37 * result + _postalCode.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_postalCode);
        }
        if (_town != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_town)) {
           result = 37 * result + _town.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_town);
        }
        if (_country != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_country)) {
           result = 37 * result + _country.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_country);
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
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'country'.
     * 
     * @param country the value of field 'country'.
     */
    public void setCountry(
            final java.lang.String country) {
        this._country = country;
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'postalCode'.
     * 
     * @param postalCode the value of field 'postalCode'.
     */
    public void setPostalCode(
            final java.lang.String postalCode) {
        this._postalCode = postalCode;
    }

    /**
     * Sets the value of field 'streetAddress'.
     * 
     * @param streetAddress the value of field 'streetAddress'.
     */
    public void setStreetAddress(
            final java.lang.String streetAddress) {
        this._streetAddress = streetAddress;
    }

    /**
     * Sets the value of field 'town'.
     * 
     * @param town the value of field 'town'.
     */
    public void setTown(
            final java.lang.String town) {
        this._town = town;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * fi.ficom.mss.TS102204.v1_0_0.PostalAddress
     */
    public static fi.ficom.mss.TS102204.v1_0_0.PostalAddress unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (fi.ficom.mss.TS102204.v1_0_0.PostalAddress) org.exolab.castor.xml.Unmarshaller.unmarshal(fi.ficom.mss.TS102204.v1_0_0.PostalAddress.class, reader);
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
