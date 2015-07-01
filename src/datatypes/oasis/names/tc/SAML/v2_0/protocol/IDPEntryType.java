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
 * Class IDPEntryType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class IDPEntryType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _providerID.
     */
    private java.lang.String _providerID;

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _loc.
     */
    private java.lang.String _loc;


      //----------------/
     //- Constructors -/
    //----------------/

    public IDPEntryType() {
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

        if (obj instanceof IDPEntryType) {

            IDPEntryType temp = (IDPEntryType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._providerID != null) {
                if (temp._providerID == null) return false;
                if (this._providerID != temp._providerID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._providerID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._providerID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._providerID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._providerID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._providerID.equals(temp._providerID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._providerID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._providerID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._providerID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._providerID);
                    }
                }
            } else if (temp._providerID != null)
                return false;
            if (this._name != null) {
                if (temp._name == null) return false;
                if (this._name != temp._name) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._name);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._name);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._name.equals(temp._name)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name);
                    }
                }
            } else if (temp._name != null)
                return false;
            if (this._loc != null) {
                if (temp._loc == null) return false;
                if (this._loc != temp._loc) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._loc);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._loc);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._loc); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._loc); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._loc.equals(temp._loc)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._loc);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._loc);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._loc);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._loc);
                    }
                }
            } else if (temp._loc != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'loc'.
     * 
     * @return the value of field 'Loc'.
     */
    public java.lang.String getLoc(
    ) {
        return this._loc;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'providerID'.
     * 
     * @return the value of field 'ProviderID'.
     */
    public java.lang.String getProviderID(
    ) {
        return this._providerID;
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
        if (_providerID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_providerID)) {
           result = 37 * result + _providerID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_providerID);
        }
        if (_name != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_name)) {
           result = 37 * result + _name.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_name);
        }
        if (_loc != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_loc)) {
           result = 37 * result + _loc.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_loc);
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
     * Sets the value of field 'loc'.
     * 
     * @param loc the value of field 'loc'.
     */
    public void setLoc(
            final java.lang.String loc) {
        this._loc = loc;
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'providerID'.
     * 
     * @param providerID the value of field 'providerID'.
     */
    public void setProviderID(
            final java.lang.String providerID) {
        this._providerID = providerID;
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
