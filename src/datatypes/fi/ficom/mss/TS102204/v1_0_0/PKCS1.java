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

package fi.ficom.mss.TS102204.v1_0_0;

/**
 * Class PKCS1.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class PKCS1 implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _signatureValue.
     */
    private byte[] _signatureValue;

    /**
     * Field _x509Certificate.
     */
    private byte[] _x509Certificate;


      //----------------/
     //- Constructors -/
    //----------------/

    public PKCS1() {
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

        if (obj instanceof PKCS1) {

            PKCS1 temp = (PKCS1)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._signatureValue != null) {
                if (temp._signatureValue == null) return false;
                if (this._signatureValue != temp._signatureValue) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signatureValue);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signatureValue);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureValue); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureValue); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._signatureValue, temp._signatureValue)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureValue);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureValue);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureValue);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureValue);
                    }
                }
            } else if (temp._signatureValue != null)
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
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'signatureValue'.
     * 
     * @return the value of field 'SignatureValue'.
     */
    public byte[] getSignatureValue(
    ) {
        return this._signatureValue;
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
        if (_signatureValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signatureValue)) {
           result = 37 * result + _signatureValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signatureValue);
        }
        if (_x509Certificate != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_x509Certificate)) {
           result = 37 * result + _x509Certificate.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_x509Certificate);
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
     * Sets the value of field 'signatureValue'.
     * 
     * @param signatureValue the value of field 'signatureValue'.
     */
    public void setSignatureValue(
            final byte[] signatureValue) {
        this._signatureValue = signatureValue;
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
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled fi.ficom.mss.TS102204.v1_0_0.PKCS1
     */
    public static fi.ficom.mss.TS102204.v1_0_0.PKCS1 unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (fi.ficom.mss.TS102204.v1_0_0.PKCS1) org.exolab.castor.xml.Unmarshaller.unmarshal(fi.ficom.mss.TS102204.v1_0_0.PKCS1.class, reader);
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
