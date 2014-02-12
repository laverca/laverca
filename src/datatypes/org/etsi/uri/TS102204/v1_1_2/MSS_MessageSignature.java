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
 * Class MSS_MessageSignature.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class MSS_MessageSignature implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _role.
     */
    private java.lang.String _role;

    /**
     * Field _mustUnderstand.
     */
    private boolean _mustUnderstand;

    /**
     * keeps track of state for field: _mustUnderstand
     */
    private boolean _has_mustUnderstand;

    /**
     * Field _relay.
     */
    private boolean _relay;

    /**
     * keeps track of state for field: _relay
     */
    private boolean _has_relay;

    /**
     * Field _processed.
     */
    private boolean _processed;

    /**
     * keeps track of state for field: _processed
     */
    private boolean _has_processed;

    /**
     * Field _actor.
     */
    private java.lang.String _actor;

    /**
     * Field _signature.
     */
    private org.w3.www._2000._09.xmldsig.Signature _signature;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSS_MessageSignature() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteMustUnderstand(
    ) {
        this._has_mustUnderstand= false;
    }

    /**
     */
    public void deleteProcessed(
    ) {
        this._has_processed= false;
    }

    /**
     */
    public void deleteRelay(
    ) {
        this._has_relay= false;
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

        if (obj instanceof MSS_MessageSignature) {

            MSS_MessageSignature temp = (MSS_MessageSignature)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._role != null) {
                if (temp._role == null) return false;
                if (this._role != temp._role) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._role);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._role);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._role); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._role); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._role.equals(temp._role)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._role);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._role);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._role);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._role);
                    }
                }
            } else if (temp._role != null)
                return false;
            if (this._mustUnderstand != temp._mustUnderstand)
                return false;
            if (this._has_mustUnderstand != temp._has_mustUnderstand)
                return false;
            if (this._relay != temp._relay)
                return false;
            if (this._has_relay != temp._has_relay)
                return false;
            if (this._processed != temp._processed)
                return false;
            if (this._has_processed != temp._has_processed)
                return false;
            if (this._actor != null) {
                if (temp._actor == null) return false;
                if (this._actor != temp._actor) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._actor);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._actor);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._actor); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._actor); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._actor.equals(temp._actor)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._actor);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._actor);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._actor);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._actor);
                    }
                }
            } else if (temp._actor != null)
                return false;
            if (this._signature != null) {
                if (temp._signature == null) return false;
                if (this._signature != temp._signature) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signature);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signature);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signature); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signature); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signature.equals(temp._signature)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signature);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signature);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signature);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signature);
                    }
                }
            } else if (temp._signature != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'actor'.
     * 
     * @return the value of field 'Actor'.
     */
    public java.lang.String getActor(
    ) {
        return this._actor;
    }

    /**
     * Returns the value of field 'mustUnderstand'.
     * 
     * @return the value of field 'MustUnderstand'.
     */
    public boolean getMustUnderstand(
    ) {
        return this._mustUnderstand;
    }

    /**
     * Returns the value of field 'processed'.
     * 
     * @return the value of field 'Processed'.
     */
    public boolean getProcessed(
    ) {
        return this._processed;
    }

    /**
     * Returns the value of field 'relay'.
     * 
     * @return the value of field 'Relay'.
     */
    public boolean getRelay(
    ) {
        return this._relay;
    }

    /**
     * Returns the value of field 'role'.
     * 
     * @return the value of field 'Role'.
     */
    public java.lang.String getRole(
    ) {
        return this._role;
    }

    /**
     * Returns the value of field 'signature'.
     * 
     * @return the value of field 'Signature'.
     */
    public org.w3.www._2000._09.xmldsig.Signature getSignature(
    ) {
        return this._signature;
    }

    /**
     * Method hasMustUnderstand.
     * 
     * @return true if at least one MustUnderstand has been added
     */
    public boolean hasMustUnderstand(
    ) {
        return this._has_mustUnderstand;
    }

    /**
     * Method hasProcessed.
     * 
     * @return true if at least one Processed has been added
     */
    public boolean hasProcessed(
    ) {
        return this._has_processed;
    }

    /**
     * Method hasRelay.
     * 
     * @return true if at least one Relay has been added
     */
    public boolean hasRelay(
    ) {
        return this._has_relay;
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
        if (_role != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_role)) {
           result = 37 * result + _role.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_role);
        }
        result = 37 * result + (_mustUnderstand?0:1);
        result = 37 * result + (_relay?0:1);
        result = 37 * result + (_processed?0:1);
        if (_actor != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_actor)) {
           result = 37 * result + _actor.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_actor);
        }
        if (_signature != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signature)) {
           result = 37 * result + _signature.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signature);
        }

        return result;
    }

    /**
     * Returns the value of field 'mustUnderstand'.
     * 
     * @return the value of field 'MustUnderstand'.
     */
    public boolean isMustUnderstand(
    ) {
        return this._mustUnderstand;
    }

    /**
     * Returns the value of field 'processed'.
     * 
     * @return the value of field 'Processed'.
     */
    public boolean isProcessed(
    ) {
        return this._processed;
    }

    /**
     * Returns the value of field 'relay'.
     * 
     * @return the value of field 'Relay'.
     */
    public boolean isRelay(
    ) {
        return this._relay;
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
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'actor'.
     * 
     * @param actor the value of field 'actor'.
     */
    public void setActor(
            final java.lang.String actor) {
        this._actor = actor;
    }

    /**
     * Sets the value of field 'mustUnderstand'.
     * 
     * @param mustUnderstand the value of field 'mustUnderstand'.
     */
    public void setMustUnderstand(
            final boolean mustUnderstand) {
        this._mustUnderstand = mustUnderstand;
        this._has_mustUnderstand = true;
    }

    /**
     * Sets the value of field 'processed'.
     * 
     * @param processed the value of field 'processed'.
     */
    public void setProcessed(
            final boolean processed) {
        this._processed = processed;
        this._has_processed = true;
    }

    /**
     * Sets the value of field 'relay'.
     * 
     * @param relay the value of field 'relay'.
     */
    public void setRelay(
            final boolean relay) {
        this._relay = relay;
        this._has_relay = true;
    }

    /**
     * Sets the value of field 'role'.
     * 
     * @param role the value of field 'role'.
     */
    public void setRole(
            final java.lang.String role) {
        this._role = role;
    }

    /**
     * Sets the value of field 'signature'.
     * 
     * @param signature the value of field 'signature'.
     */
    public void setSignature(
            final org.w3.www._2000._09.xmldsig.Signature signature) {
        this._signature = signature;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * org.etsi.uri.TS102204.v1_1_2.MSS_MessageSignature
     */
    public static org.etsi.uri.TS102204.v1_1_2.MSS_MessageSignature unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.etsi.uri.TS102204.v1_1_2.MSS_MessageSignature) org.exolab.castor.xml.Unmarshaller.unmarshal(org.etsi.uri.TS102204.v1_1_2.MSS_MessageSignature.class, reader);
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
