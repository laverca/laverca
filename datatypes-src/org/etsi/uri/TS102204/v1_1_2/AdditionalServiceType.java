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

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class AdditionalServiceType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AdditionalServiceType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _description.
     */
    private org.etsi.uri.TS102204.v1_1_2.Description _description;

    /**
     * Field _entity.
     */
    private org.etsi.uri.TS102204.v1_1_2.Entity _entity;

    /**
     * Field _additionalServiceTypeChoice.
     */
    private org.etsi.uri.TS102204.v1_1_2.AdditionalServiceTypeChoice _additionalServiceTypeChoice;


      //----------------/
     //- Constructors -/
    //----------------/

    public AdditionalServiceType() {
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

        if (obj instanceof AdditionalServiceType) {

            AdditionalServiceType temp = (AdditionalServiceType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._description != null) {
                if (temp._description == null) return false;
                if (this._description != temp._description) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._description);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._description);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._description); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._description); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._description.equals(temp._description)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._description);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._description);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._description);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._description);
                    }
                }
            } else if (temp._description != null)
                return false;
            if (this._entity != null) {
                if (temp._entity == null) return false;
                if (this._entity != temp._entity) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._entity);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._entity);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._entity); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._entity); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._entity.equals(temp._entity)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._entity);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._entity);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._entity);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._entity);
                    }
                }
            } else if (temp._entity != null)
                return false;
            if (this._additionalServiceTypeChoice != null) {
                if (temp._additionalServiceTypeChoice == null) return false;
                if (this._additionalServiceTypeChoice != temp._additionalServiceTypeChoice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._additionalServiceTypeChoice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._additionalServiceTypeChoice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._additionalServiceTypeChoice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._additionalServiceTypeChoice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._additionalServiceTypeChoice.equals(temp._additionalServiceTypeChoice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._additionalServiceTypeChoice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._additionalServiceTypeChoice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._additionalServiceTypeChoice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._additionalServiceTypeChoice);
                    }
                }
            } else if (temp._additionalServiceTypeChoice != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'additionalServiceTypeChoice'.
     * 
     * @return the value of field 'AdditionalServiceTypeChoice'.
     */
    public org.etsi.uri.TS102204.v1_1_2.AdditionalServiceTypeChoice getAdditionalServiceTypeChoice(
    ) {
        return this._additionalServiceTypeChoice;
    }

    /**
     * Returns the value of field 'description'.
     * 
     * @return the value of field 'Description'.
     */
    public org.etsi.uri.TS102204.v1_1_2.Description getDescription(
    ) {
        return this._description;
    }

    /**
     * Returns the value of field 'entity'.
     * 
     * @return the value of field 'Entity'.
     */
    public org.etsi.uri.TS102204.v1_1_2.Entity getEntity(
    ) {
        return this._entity;
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
        if (_description != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_description)) {
           result = 37 * result + _description.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_description);
        }
        if (_entity != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_entity)) {
           result = 37 * result + _entity.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_entity);
        }
        if (_additionalServiceTypeChoice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_additionalServiceTypeChoice)) {
           result = 37 * result + _additionalServiceTypeChoice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_additionalServiceTypeChoice);
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
     * Sets the value of field 'additionalServiceTypeChoice'.
     * 
     * @param additionalServiceTypeChoice the value of field
     * 'additionalServiceTypeChoice'.
     */
    public void setAdditionalServiceTypeChoice(
            final org.etsi.uri.TS102204.v1_1_2.AdditionalServiceTypeChoice additionalServiceTypeChoice) {
        this._additionalServiceTypeChoice = additionalServiceTypeChoice;
    }

    /**
     * Sets the value of field 'description'.
     * 
     * @param description the value of field 'description'.
     */
    public void setDescription(
            final org.etsi.uri.TS102204.v1_1_2.Description description) {
        this._description = description;
    }

    /**
     * Sets the value of field 'entity'.
     * 
     * @param entity the value of field 'entity'.
     */
    public void setEntity(
            final org.etsi.uri.TS102204.v1_1_2.Entity entity) {
        this._entity = entity;
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
