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

package org.w3.www._2000._09.xmldsig;

/**
 * Class PGPDataType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class PGPDataType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _PGPDataTypeSequence.
     */
    private org.w3.www._2000._09.xmldsig.PGPDataTypeSequence _PGPDataTypeSequence;

    /**
     * Field _PGPDataTypeSequence2.
     */
    private org.w3.www._2000._09.xmldsig.PGPDataTypeSequence2 _PGPDataTypeSequence2;


      //----------------/
     //- Constructors -/
    //----------------/

    public PGPDataType() {
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

        if (obj instanceof PGPDataType) {

            PGPDataType temp = (PGPDataType)obj;
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
            if (this._PGPDataTypeSequence != null) {
                if (temp._PGPDataTypeSequence == null) return false;
                if (this._PGPDataTypeSequence != temp._PGPDataTypeSequence) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._PGPDataTypeSequence);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._PGPDataTypeSequence);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPDataTypeSequence); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPDataTypeSequence); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._PGPDataTypeSequence.equals(temp._PGPDataTypeSequence)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPDataTypeSequence);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPDataTypeSequence);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPDataTypeSequence);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPDataTypeSequence);
                    }
                }
            } else if (temp._PGPDataTypeSequence != null)
                return false;
            if (this._PGPDataTypeSequence2 != null) {
                if (temp._PGPDataTypeSequence2 == null) return false;
                if (this._PGPDataTypeSequence2 != temp._PGPDataTypeSequence2) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._PGPDataTypeSequence2);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._PGPDataTypeSequence2);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPDataTypeSequence2); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPDataTypeSequence2); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._PGPDataTypeSequence2.equals(temp._PGPDataTypeSequence2)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPDataTypeSequence2);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPDataTypeSequence2);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPDataTypeSequence2);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPDataTypeSequence2);
                    }
                }
            } else if (temp._PGPDataTypeSequence2 != null)
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
     * Returns the value of field 'PGPDataTypeSequence'.
     * 
     * @return the value of field 'PGPDataTypeSequence'.
     */
    public org.w3.www._2000._09.xmldsig.PGPDataTypeSequence getPGPDataTypeSequence(
    ) {
        return this._PGPDataTypeSequence;
    }

    /**
     * Returns the value of field 'PGPDataTypeSequence2'.
     * 
     * @return the value of field 'PGPDataTypeSequence2'.
     */
    public org.w3.www._2000._09.xmldsig.PGPDataTypeSequence2 getPGPDataTypeSequence2(
    ) {
        return this._PGPDataTypeSequence2;
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
        if (_PGPDataTypeSequence != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_PGPDataTypeSequence)) {
           result = 37 * result + _PGPDataTypeSequence.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_PGPDataTypeSequence);
        }
        if (_PGPDataTypeSequence2 != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_PGPDataTypeSequence2)) {
           result = 37 * result + _PGPDataTypeSequence2.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_PGPDataTypeSequence2);
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
     * Sets the value of field 'PGPDataTypeSequence'.
     * 
     * @param PGPDataTypeSequence the value of field
     * 'PGPDataTypeSequence'.
     */
    public void setPGPDataTypeSequence(
            final org.w3.www._2000._09.xmldsig.PGPDataTypeSequence PGPDataTypeSequence) {
        this._PGPDataTypeSequence = PGPDataTypeSequence;
        this._choiceValue = PGPDataTypeSequence;
    }

    /**
     * Sets the value of field 'PGPDataTypeSequence2'.
     * 
     * @param PGPDataTypeSequence2 the value of field
     * 'PGPDataTypeSequence2'.
     */
    public void setPGPDataTypeSequence2(
            final org.w3.www._2000._09.xmldsig.PGPDataTypeSequence2 PGPDataTypeSequence2) {
        this._PGPDataTypeSequence2 = PGPDataTypeSequence2;
        this._choiceValue = PGPDataTypeSequence2;
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
