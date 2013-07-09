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
 * Class NameIDMappingRequestType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class NameIDMappingRequestType extends oasis.names.tc.SAML.v2_0.protocol.RequestAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nameIDMappingRequestTypeChoice.
     */
    private oasis.names.tc.SAML.v2_0.protocol.NameIDMappingRequestTypeChoice _nameIDMappingRequestTypeChoice;

    /**
     * Field _nameIDPolicy.
     */
    private oasis.names.tc.SAML.v2_0.protocol.NameIDPolicy _nameIDPolicy;


      //----------------/
     //- Constructors -/
    //----------------/

    public NameIDMappingRequestType() {
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

        if (obj instanceof NameIDMappingRequestType) {

            NameIDMappingRequestType temp = (NameIDMappingRequestType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._nameIDMappingRequestTypeChoice != null) {
                if (temp._nameIDMappingRequestTypeChoice == null) return false;
                if (this._nameIDMappingRequestTypeChoice != temp._nameIDMappingRequestTypeChoice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._nameIDMappingRequestTypeChoice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._nameIDMappingRequestTypeChoice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameIDMappingRequestTypeChoice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameIDMappingRequestTypeChoice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._nameIDMappingRequestTypeChoice.equals(temp._nameIDMappingRequestTypeChoice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameIDMappingRequestTypeChoice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameIDMappingRequestTypeChoice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameIDMappingRequestTypeChoice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameIDMappingRequestTypeChoice);
                    }
                }
            } else if (temp._nameIDMappingRequestTypeChoice != null)
                return false;
            if (this._nameIDPolicy != null) {
                if (temp._nameIDPolicy == null) return false;
                if (this._nameIDPolicy != temp._nameIDPolicy) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._nameIDPolicy);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._nameIDPolicy);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameIDPolicy); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameIDPolicy); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._nameIDPolicy.equals(temp._nameIDPolicy)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameIDPolicy);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameIDPolicy);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameIDPolicy);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameIDPolicy);
                    }
                }
            } else if (temp._nameIDPolicy != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'nameIDMappingRequestTypeChoice'.
     * 
     * @return the value of field 'NameIDMappingRequestTypeChoice'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.NameIDMappingRequestTypeChoice getNameIDMappingRequestTypeChoice(
    ) {
        return this._nameIDMappingRequestTypeChoice;
    }

    /**
     * Returns the value of field 'nameIDPolicy'.
     * 
     * @return the value of field 'NameIDPolicy'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.NameIDPolicy getNameIDPolicy(
    ) {
        return this._nameIDPolicy;
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
        if (_nameIDMappingRequestTypeChoice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_nameIDMappingRequestTypeChoice)) {
           result = 37 * result + _nameIDMappingRequestTypeChoice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_nameIDMappingRequestTypeChoice);
        }
        if (_nameIDPolicy != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_nameIDPolicy)) {
           result = 37 * result + _nameIDPolicy.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_nameIDPolicy);
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
     * Sets the value of field 'nameIDMappingRequestTypeChoice'.
     * 
     * @param nameIDMappingRequestTypeChoice the value of field
     * 'nameIDMappingRequestTypeChoice'.
     */
    public void setNameIDMappingRequestTypeChoice(
            final oasis.names.tc.SAML.v2_0.protocol.NameIDMappingRequestTypeChoice nameIDMappingRequestTypeChoice) {
        this._nameIDMappingRequestTypeChoice = nameIDMappingRequestTypeChoice;
    }

    /**
     * Sets the value of field 'nameIDPolicy'.
     * 
     * @param nameIDPolicy the value of field 'nameIDPolicy'.
     */
    public void setNameIDPolicy(
            final oasis.names.tc.SAML.v2_0.protocol.NameIDPolicy nameIDPolicy) {
        this._nameIDPolicy = nameIDPolicy;
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
