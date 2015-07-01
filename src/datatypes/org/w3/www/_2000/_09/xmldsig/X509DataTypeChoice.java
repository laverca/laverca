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

/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig;

/**
 * Class X509DataTypeChoice.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class X509DataTypeChoice implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _x509IssuerSerial.
     */
    private org.w3.www._2000._09.xmldsig.X509IssuerSerial _x509IssuerSerial;

    /**
     * Field _x509SKI.
     */
    private byte[] _x509SKI;

    /**
     * Field _x509SubjectName.
     */
    private java.lang.String _x509SubjectName;

    /**
     * Field _x509Certificate.
     */
    private byte[] _x509Certificate;

    /**
     * Field _x509CRL.
     */
    private byte[] _x509CRL;

    /**
     * Field _anyObject.
     */
    private java.lang.Object _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public X509DataTypeChoice() {
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

        if (obj instanceof X509DataTypeChoice) {

            X509DataTypeChoice temp = (X509DataTypeChoice)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._x509IssuerSerial != null) {
                if (temp._x509IssuerSerial == null) return false;
                if (this._x509IssuerSerial != temp._x509IssuerSerial) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._x509IssuerSerial);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._x509IssuerSerial);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509IssuerSerial); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509IssuerSerial); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._x509IssuerSerial.equals(temp._x509IssuerSerial)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509IssuerSerial);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509IssuerSerial);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509IssuerSerial);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509IssuerSerial);
                    }
                }
            } else if (temp._x509IssuerSerial != null)
                return false;
            if (this._x509SKI != null) {
                if (temp._x509SKI == null) return false;
                if (this._x509SKI != temp._x509SKI) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._x509SKI);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._x509SKI);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509SKI); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509SKI); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._x509SKI, temp._x509SKI)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509SKI);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509SKI);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509SKI);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509SKI);
                    }
                }
            } else if (temp._x509SKI != null)
                return false;
            if (this._x509SubjectName != null) {
                if (temp._x509SubjectName == null) return false;
                if (this._x509SubjectName != temp._x509SubjectName) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._x509SubjectName);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._x509SubjectName);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509SubjectName); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509SubjectName); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._x509SubjectName.equals(temp._x509SubjectName)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509SubjectName);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509SubjectName);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509SubjectName);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509SubjectName);
                    }
                }
            } else if (temp._x509SubjectName != null)
                return false;
            if (this._x509Certificate != null) {
                if (temp._x509Certificate == null) return false;
                if (this._x509Certificate != temp._x509Certificate) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._x509Certificate);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._x509Certificate);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509Certificate); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509Certificate); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._x509Certificate, temp._x509Certificate)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509Certificate);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509Certificate);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509Certificate);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509Certificate);
                    }
                }
            } else if (temp._x509Certificate != null)
                return false;
            if (this._x509CRL != null) {
                if (temp._x509CRL == null) return false;
                if (this._x509CRL != temp._x509CRL) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._x509CRL);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._x509CRL);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509CRL); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509CRL); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._x509CRL, temp._x509CRL)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509CRL);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509CRL);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509CRL);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509CRL);
                    }
                }
            } else if (temp._x509CRL != null)
                return false;
            if (this._anyObject != null) {
                if (temp._anyObject == null) return false;
                if (this._anyObject != temp._anyObject) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._anyObject);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._anyObject);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._anyObject.equals(temp._anyObject)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject);
                    }
                }
            } else if (temp._anyObject != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'anyObject'.
     * 
     * @return the value of field 'AnyObject'.
     */
    public java.lang.Object getAnyObject(
    ) {
        return this._anyObject;
    }

    /**
     * Returns the value of field 'x509CRL'.
     * 
     * @return the value of field 'X509CRL'.
     */
    public byte[] getX509CRL(
    ) {
        return this._x509CRL;
    }

    /**
     * Returns the value of field 'x509Certificate'.
     * 
     * @return the value of field 'X509Certificate'.
     */
    public byte[] getX509Certificate(
    ) {
        return this._x509Certificate;
    }

    /**
     * Returns the value of field 'x509IssuerSerial'.
     * 
     * @return the value of field 'X509IssuerSerial'.
     */
    public org.w3.www._2000._09.xmldsig.X509IssuerSerial getX509IssuerSerial(
    ) {
        return this._x509IssuerSerial;
    }

    /**
     * Returns the value of field 'x509SKI'.
     * 
     * @return the value of field 'X509SKI'.
     */
    public byte[] getX509SKI(
    ) {
        return this._x509SKI;
    }

    /**
     * Returns the value of field 'x509SubjectName'.
     * 
     * @return the value of field 'X509SubjectName'.
     */
    public java.lang.String getX509SubjectName(
    ) {
        return this._x509SubjectName;
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
        if (_x509IssuerSerial != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_x509IssuerSerial)) {
           result = 37 * result + _x509IssuerSerial.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_x509IssuerSerial);
        }
        if (_x509SKI != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_x509SKI)) {
           result = 37 * result + _x509SKI.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_x509SKI);
        }
        if (_x509SubjectName != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_x509SubjectName)) {
           result = 37 * result + _x509SubjectName.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_x509SubjectName);
        }
        if (_x509Certificate != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_x509Certificate)) {
           result = 37 * result + _x509Certificate.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_x509Certificate);
        }
        if (_x509CRL != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_x509CRL)) {
           result = 37 * result + _x509CRL.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_x509CRL);
        }
        if (_anyObject != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_anyObject)) {
           result = 37 * result + _anyObject.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_anyObject);
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
     * Sets the value of field 'anyObject'.
     * 
     * @param anyObject the value of field 'anyObject'.
     */
    public void setAnyObject(
            final java.lang.Object anyObject) {
        this._anyObject = anyObject;
    }

    /**
     * Sets the value of field 'x509CRL'.
     * 
     * @param x509CRL the value of field 'x509CRL'.
     */
    public void setX509CRL(
            final byte[] x509CRL) {
        this._x509CRL = x509CRL;
    }

    /**
     * Sets the value of field 'x509Certificate'.
     * 
     * @param x509Certificate the value of field 'x509Certificate'.
     */
    public void setX509Certificate(
            final byte[] x509Certificate) {
        this._x509Certificate = x509Certificate;
    }

    /**
     * Sets the value of field 'x509IssuerSerial'.
     * 
     * @param x509IssuerSerial the value of field 'x509IssuerSerial'
     */
    public void setX509IssuerSerial(
            final org.w3.www._2000._09.xmldsig.X509IssuerSerial x509IssuerSerial) {
        this._x509IssuerSerial = x509IssuerSerial;
    }

    /**
     * Sets the value of field 'x509SKI'.
     * 
     * @param x509SKI the value of field 'x509SKI'.
     */
    public void setX509SKI(
            final byte[] x509SKI) {
        this._x509SKI = x509SKI;
    }

    /**
     * Sets the value of field 'x509SubjectName'.
     * 
     * @param x509SubjectName the value of field 'x509SubjectName'.
     */
    public void setX509SubjectName(
            final java.lang.String x509SubjectName) {
        this._x509SubjectName = x509SubjectName;
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
     * org.w3.www._2000._09.xmldsig.X509DataTypeChoice
     */
    public static org.w3.www._2000._09.xmldsig.X509DataTypeChoice unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.w3.www._2000._09.xmldsig.X509DataTypeChoice) org.exolab.castor.xml.Unmarshaller.unmarshal(org.w3.www._2000._09.xmldsig.X509DataTypeChoice.class, reader);
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
