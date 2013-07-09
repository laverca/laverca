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

package oasis.names.tc.SAML.v2_0.protocol;

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
    private java.lang.String _value;

    /**
     * Field _statusCode.
     */
    private oasis.names.tc.SAML.v2_0.protocol.StatusCode _statusCode;


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
            if (this._value != null) {
                if (temp._value == null) return false;
                if (this._value != temp._value) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._value);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._value);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._value); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._value); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._value.equals(temp._value)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._value);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._value);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._value);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._value);
                    }
                }
            } else if (temp._value != null)
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
    public oasis.names.tc.SAML.v2_0.protocol.StatusCode getStatusCode(
    ) {
        return this._statusCode;
    }

    /**
     * Returns the value of field 'value'.
     * 
     * @return the value of field 'Value'.
     */
    public java.lang.String getValue(
    ) {
        return this._value;
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
        if (_value != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_value)) {
           result = 37 * result + _value.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_value);
        }
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
            final oasis.names.tc.SAML.v2_0.protocol.StatusCode statusCode) {
        this._statusCode = statusCode;
    }

    /**
     * Sets the value of field 'value'.
     * 
     * @param value the value of field 'value'.
     */
    public void setValue(
            final java.lang.String value) {
        this._value = value;
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
