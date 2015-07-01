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
 * Class NameIDMappingResponseType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class NameIDMappingResponseType extends oasis.names.tc.SAML.v2_0.protocol.StatusResponseType 
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
     * Field _nameID.
     */
    private oasis.names.tc.SAML.v2_0.assertion.NameID _nameID;

    /**
     * Field _encryptedID.
     */
    private oasis.names.tc.SAML.v2_0.assertion.EncryptedID _encryptedID;


      //----------------/
     //- Constructors -/
    //----------------/

    public NameIDMappingResponseType() {
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

        if (obj instanceof NameIDMappingResponseType) {

            NameIDMappingResponseType temp = (NameIDMappingResponseType)obj;
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
            if (this._nameID != null) {
                if (temp._nameID == null) return false;
                if (this._nameID != temp._nameID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._nameID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._nameID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._nameID.equals(temp._nameID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameID);
                    }
                }
            } else if (temp._nameID != null)
                return false;
            if (this._encryptedID != null) {
                if (temp._encryptedID == null) return false;
                if (this._encryptedID != temp._encryptedID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._encryptedID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._encryptedID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptedID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptedID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._encryptedID.equals(temp._encryptedID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptedID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptedID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptedID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptedID);
                    }
                }
            } else if (temp._encryptedID != null)
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
     * Returns the value of field 'encryptedID'.
     * 
     * @return the value of field 'EncryptedID'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.EncryptedID getEncryptedID(
    ) {
        return this._encryptedID;
    }

    /**
     * Returns the value of field 'nameID'.
     * 
     * @return the value of field 'NameID'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.NameID getNameID(
    ) {
        return this._nameID;
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
        if (_nameID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_nameID)) {
           result = 37 * result + _nameID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_nameID);
        }
        if (_encryptedID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_encryptedID)) {
           result = 37 * result + _encryptedID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_encryptedID);
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
     * Sets the value of field 'encryptedID'.
     * 
     * @param encryptedID the value of field 'encryptedID'.
     */
    public void setEncryptedID(
            final oasis.names.tc.SAML.v2_0.assertion.EncryptedID encryptedID) {
        this._encryptedID = encryptedID;
        this._choiceValue = encryptedID;
    }

    /**
     * Sets the value of field 'nameID'.
     * 
     * @param nameID the value of field 'nameID'.
     */
    public void setNameID(
            final oasis.names.tc.SAML.v2_0.assertion.NameID nameID) {
        this._nameID = nameID;
        this._choiceValue = nameID;
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
