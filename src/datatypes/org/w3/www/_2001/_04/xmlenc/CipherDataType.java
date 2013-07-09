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

package org.w3.www._2001._04.xmlenc;

/**
 * Class CipherDataType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class CipherDataType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _cipherValue.
     */
    private byte[] _cipherValue;

    /**
     * Field _cipherReference.
     */
    private org.w3.www._2001._04.xmlenc.CipherReference _cipherReference;


      //----------------/
     //- Constructors -/
    //----------------/

    public CipherDataType() {
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

        if (obj instanceof CipherDataType) {

            CipherDataType temp = (CipherDataType)obj;
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
            if (this._cipherValue != null) {
                if (temp._cipherValue == null) return false;
                if (this._cipherValue != temp._cipherValue) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._cipherValue);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._cipherValue);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cipherValue); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cipherValue); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._cipherValue, temp._cipherValue)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cipherValue);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cipherValue);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cipherValue);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cipherValue);
                    }
                }
            } else if (temp._cipherValue != null)
                return false;
            if (this._cipherReference != null) {
                if (temp._cipherReference == null) return false;
                if (this._cipherReference != temp._cipherReference) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._cipherReference);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._cipherReference);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cipherReference); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cipherReference); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._cipherReference.equals(temp._cipherReference)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cipherReference);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cipherReference);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cipherReference);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cipherReference);
                    }
                }
            } else if (temp._cipherReference != null)
                return false;
            return true;
        }
        return false;
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
     * Returns the value of field 'cipherReference'.
     * 
     * @return the value of field 'CipherReference'.
     */
    public org.w3.www._2001._04.xmlenc.CipherReference getCipherReference(
    ) {
        return this._cipherReference;
    }

    /**
     * Returns the value of field 'cipherValue'.
     * 
     * @return the value of field 'CipherValue'.
     */
    public byte[] getCipherValue(
    ) {
        return this._cipherValue;
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
        if (_cipherValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_cipherValue)) {
           result = 37 * result + _cipherValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_cipherValue);
        }
        if (_cipherReference != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_cipherReference)) {
           result = 37 * result + _cipherReference.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_cipherReference);
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
     * Sets the value of field 'cipherReference'.
     * 
     * @param cipherReference the value of field 'cipherReference'.
     */
    public void setCipherReference(
            final org.w3.www._2001._04.xmlenc.CipherReference cipherReference) {
        this._cipherReference = cipherReference;
        this._choiceValue = cipherReference;
    }

    /**
     * Sets the value of field 'cipherValue'.
     * 
     * @param cipherValue the value of field 'cipherValue'.
     */
    public void setCipherValue(
            final byte[] cipherValue) {
        this._cipherValue = cipherValue;
        this._choiceValue = cipherValue;
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
