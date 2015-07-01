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

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class ResponseType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ResponseType extends oasis.names.tc.SAML.v2_0.protocol.StatusResponseType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _responseTypeChoice.
     */
    private oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoice _responseTypeChoice;


      //----------------/
     //- Constructors -/
    //----------------/

    public ResponseType() {
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

        if (super.equals(obj)==false)
            return false;

        if (obj instanceof ResponseType) {

            ResponseType temp = (ResponseType)obj;
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
            if (this._responseTypeChoice != null) {
                if (temp._responseTypeChoice == null) return false;
                if (this._responseTypeChoice != temp._responseTypeChoice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._responseTypeChoice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._responseTypeChoice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._responseTypeChoice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._responseTypeChoice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._responseTypeChoice.equals(temp._responseTypeChoice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._responseTypeChoice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._responseTypeChoice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._responseTypeChoice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._responseTypeChoice);
                    }
                }
            } else if (temp._responseTypeChoice != null)
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
     * Returns the value of field 'responseTypeChoice'.
     * 
     * @return the value of field 'ResponseTypeChoice'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoice getResponseTypeChoice(
    ) {
        return this._responseTypeChoice;
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
        int result = super.hashCode();

        long tmp;
        if (_choiceValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_choiceValue)) {
           result = 37 * result + _choiceValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_choiceValue);
        }
        if (_responseTypeChoice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_responseTypeChoice)) {
           result = 37 * result + _responseTypeChoice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_responseTypeChoice);
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
     * Sets the value of field 'responseTypeChoice'.
     * 
     * @param responseTypeChoice the value of field
     * 'responseTypeChoice'.
     */
    public void setResponseTypeChoice(
            final oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoice responseTypeChoice) {
        this._responseTypeChoice = responseTypeChoice;
        this._choiceValue = responseTypeChoice;
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
