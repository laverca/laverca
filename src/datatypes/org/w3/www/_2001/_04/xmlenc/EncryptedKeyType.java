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
 * Class EncryptedKeyType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class EncryptedKeyType extends org.w3.www._2001._04.xmlenc.EncryptedType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _recipient.
     */
    private java.lang.String _recipient;

    /**
     * Field _referenceList.
     */
    private org.w3.www._2001._04.xmlenc.ReferenceList _referenceList;

    /**
     * Field _carriedKeyName.
     */
    private java.lang.String _carriedKeyName;


      //----------------/
     //- Constructors -/
    //----------------/

    public EncryptedKeyType() {
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

        if (obj instanceof EncryptedKeyType) {

            EncryptedKeyType temp = (EncryptedKeyType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._recipient != null) {
                if (temp._recipient == null) return false;
                if (this._recipient != temp._recipient) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._recipient);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._recipient);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recipient); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recipient); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._recipient.equals(temp._recipient)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recipient);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recipient);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recipient);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recipient);
                    }
                }
            } else if (temp._recipient != null)
                return false;
            if (this._referenceList != null) {
                if (temp._referenceList == null) return false;
                if (this._referenceList != temp._referenceList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._referenceList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._referenceList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._referenceList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._referenceList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._referenceList.equals(temp._referenceList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._referenceList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._referenceList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._referenceList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._referenceList);
                    }
                }
            } else if (temp._referenceList != null)
                return false;
            if (this._carriedKeyName != null) {
                if (temp._carriedKeyName == null) return false;
                if (this._carriedKeyName != temp._carriedKeyName) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._carriedKeyName);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._carriedKeyName);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._carriedKeyName); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._carriedKeyName); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._carriedKeyName.equals(temp._carriedKeyName)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._carriedKeyName);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._carriedKeyName);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._carriedKeyName);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._carriedKeyName);
                    }
                }
            } else if (temp._carriedKeyName != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'carriedKeyName'.
     * 
     * @return the value of field 'CarriedKeyName'.
     */
    public java.lang.String getCarriedKeyName(
    ) {
        return this._carriedKeyName;
    }

    /**
     * Returns the value of field 'recipient'.
     * 
     * @return the value of field 'Recipient'.
     */
    public java.lang.String getRecipient(
    ) {
        return this._recipient;
    }

    /**
     * Returns the value of field 'referenceList'.
     * 
     * @return the value of field 'ReferenceList'.
     */
    public org.w3.www._2001._04.xmlenc.ReferenceList getReferenceList(
    ) {
        return this._referenceList;
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
        if (_recipient != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_recipient)) {
           result = 37 * result + _recipient.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_recipient);
        }
        if (_referenceList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_referenceList)) {
           result = 37 * result + _referenceList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_referenceList);
        }
        if (_carriedKeyName != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_carriedKeyName)) {
           result = 37 * result + _carriedKeyName.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_carriedKeyName);
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
     * Sets the value of field 'carriedKeyName'.
     * 
     * @param carriedKeyName the value of field 'carriedKeyName'.
     */
    public void setCarriedKeyName(
            final java.lang.String carriedKeyName) {
        this._carriedKeyName = carriedKeyName;
    }

    /**
     * Sets the value of field 'recipient'.
     * 
     * @param recipient the value of field 'recipient'.
     */
    public void setRecipient(
            final java.lang.String recipient) {
        this._recipient = recipient;
    }

    /**
     * Sets the value of field 'referenceList'.
     * 
     * @param referenceList the value of field 'referenceList'.
     */
    public void setReferenceList(
            final org.w3.www._2001._04.xmlenc.ReferenceList referenceList) {
        this._referenceList = referenceList;
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
