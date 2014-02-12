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

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class StatusCodeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class StatusCodeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _value.
     */
    private long _value;

    /**
     * keeps track of state for field: _value
     */
    private boolean _has_value;

    /**
     * Field _statusCode.
     */
    private org.etsi.uri.TS102204.v1_1_2.StatusCode _statusCode;


      //----------------/
     //- Constructors -/
    //----------------/

    public StatusCodeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteValue(
    ) {
        this._has_value= false;
    }

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

        if (obj instanceof StatusCodeType) {

            StatusCodeType temp = (StatusCodeType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._value != temp._value)
                return false;
            if (this._has_value != temp._has_value)
                return false;
            if (this._statusCode != null) {
                if (temp._statusCode == null) return false;
                if (this._statusCode != temp._statusCode) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._statusCode);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._statusCode);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusCode); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusCode); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._statusCode.equals(temp._statusCode)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusCode);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusCode);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusCode);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusCode);
                    }
                }
            } else if (temp._statusCode != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'statusCode'.
     * 
     * @return the value of field 'StatusCode'.
     */
    public org.etsi.uri.TS102204.v1_1_2.StatusCode getStatusCode(
    ) {
        return this._statusCode;
    }

    /**
     * Returns the value of field 'value'.
     * 
     * @return the value of field 'Value'.
     */
    public long getValue(
    ) {
        return this._value;
    }

    /**
     * Method hasValue.
     * 
     * @return true if at least one Value has been added
     */
    public boolean hasValue(
    ) {
        return this._has_value;
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
        result = 37 * result + (int)(_value^(_value>>>32));
        if (_statusCode != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_statusCode)) {
           result = 37 * result + _statusCode.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_statusCode);
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
     * Sets the value of field 'statusCode'.
     * 
     * @param statusCode the value of field 'statusCode'.
     */
    public void setStatusCode(
            final org.etsi.uri.TS102204.v1_1_2.StatusCode statusCode) {
        this._statusCode = statusCode;
    }

    /**
     * Sets the value of field 'value'.
     * 
     * @param value the value of field 'value'.
     */
    public void setValue(
            final long value) {
        this._value = value;
        this._has_value = true;
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
