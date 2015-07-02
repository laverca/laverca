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

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class SignatureType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class SignatureType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _XMLSignature.
     */
    private org.etsi.uri.TS102204.v1_1_2.XMLSignature _XMLSignature;

    /**
     * Field _base64Signature.
     */
    private byte[] _base64Signature;

    /**
     * Field _PKCS1.
     */
    private fi.ficom.mss.TS102204.v1_0_0.PKCS1 _PKCS1;

    /**
     * Field _anyObject.
     */
    private java.lang.Object _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public SignatureType() {
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

        if (obj instanceof SignatureType) {

            SignatureType temp = (SignatureType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._choiceValue != null) {
                if (temp._choiceValue == null) return false;
                if (this._choiceValue != temp._choiceValue) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._choiceValue);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._choiceValue);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._choiceValue.equals(temp._choiceValue)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue);
                    }
                }
            } else if (temp._choiceValue != null)
                return false;
            if (this._XMLSignature != null) {
                if (temp._XMLSignature == null) return false;
                if (this._XMLSignature != temp._XMLSignature) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._XMLSignature);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._XMLSignature);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._XMLSignature); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._XMLSignature); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._XMLSignature.equals(temp._XMLSignature)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._XMLSignature);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._XMLSignature);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._XMLSignature);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._XMLSignature);
                    }
                }
            } else if (temp._XMLSignature != null)
                return false;
            if (this._base64Signature != null) {
                if (temp._base64Signature == null) return false;
                if (this._base64Signature != temp._base64Signature) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._base64Signature);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._base64Signature);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._base64Signature); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._base64Signature); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._base64Signature, temp._base64Signature)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._base64Signature);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._base64Signature);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._base64Signature);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._base64Signature);
                    }
                }
            } else if (temp._base64Signature != null)
                return false;
            if (this._PKCS1 != null) {
                if (temp._PKCS1 == null) return false;
                if (this._PKCS1 != temp._PKCS1) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._PKCS1);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._PKCS1);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PKCS1); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PKCS1); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._PKCS1.equals(temp._PKCS1)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PKCS1);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PKCS1);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PKCS1);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PKCS1);
                    }
                }
            } else if (temp._PKCS1 != null)
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
     * Returns the value of field 'base64Signature'.
     * 
     * @return the value of field 'Base64Signature'.
     */
    public byte[] getBase64Signature(
    ) {
        return this._base64Signature;
    }

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return the value of field 'ChoiceValue'.
     */
    public java.lang.Object getChoiceValue(
    ) {
        return this._choiceValue;
    }

    /**
     * Returns the value of field 'PKCS1'.
     * 
     * @return the value of field 'PKCS1'.
     */
    public fi.ficom.mss.TS102204.v1_0_0.PKCS1 getPKCS1(
    ) {
        return this._PKCS1;
    }

    /**
     * Returns the value of field 'XMLSignature'.
     * 
     * @return the value of field 'XMLSignature'.
     */
    public org.etsi.uri.TS102204.v1_1_2.XMLSignature getXMLSignature(
    ) {
        return this._XMLSignature;
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
        if (_choiceValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_choiceValue)) {
           result = 37 * result + _choiceValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_choiceValue);
        }
        if (_XMLSignature != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_XMLSignature)) {
           result = 37 * result + _XMLSignature.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_XMLSignature);
        }
        if (_base64Signature != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_base64Signature)) {
           result = 37 * result + _base64Signature.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_base64Signature);
        }
        if (_PKCS1 != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_PKCS1)) {
           result = 37 * result + _PKCS1.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_PKCS1);
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
     * Sets the value of field 'anyObject'.
     * 
     * @param anyObject the value of field 'anyObject'.
     */
    public void setAnyObject(
            final java.lang.Object anyObject) {
        this._anyObject = anyObject;
        this._choiceValue = anyObject;
    }

    /**
     * Sets the value of field 'base64Signature'.
     * 
     * @param base64Signature the value of field 'base64Signature'.
     */
    public void setBase64Signature(
            final byte[] base64Signature) {
        this._base64Signature = base64Signature;
        this._choiceValue = base64Signature;
    }

    /**
     * Sets the value of field 'PKCS1'.
     * 
     * @param PKCS1 the value of field 'PKCS1'.
     */
    public void setPKCS1(
            final fi.ficom.mss.TS102204.v1_0_0.PKCS1 PKCS1) {
        this._PKCS1 = PKCS1;
        this._choiceValue = PKCS1;
    }

    /**
     * Sets the value of field 'XMLSignature'.
     * 
     * @param XMLSignature the value of field 'XMLSignature'.
     */
    public void setXMLSignature(
            final org.etsi.uri.TS102204.v1_1_2.XMLSignature XMLSignature) {
        this._XMLSignature = XMLSignature;
        this._choiceValue = XMLSignature;
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
