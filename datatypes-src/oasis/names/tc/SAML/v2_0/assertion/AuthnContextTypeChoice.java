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

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class AuthnContextTypeChoice.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AuthnContextTypeChoice implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _authnContextTypeChoiceSequence.
     */
    private oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceSequence _authnContextTypeChoiceSequence;

    /**
     * Field _authnContextTypeChoiceChoice.
     */
    private oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceChoice _authnContextTypeChoiceChoice;


      //----------------/
     //- Constructors -/
    //----------------/

    public AuthnContextTypeChoice() {
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

        if (obj instanceof AuthnContextTypeChoice) {

            AuthnContextTypeChoice temp = (AuthnContextTypeChoice)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._authnContextTypeChoiceSequence != null) {
                if (temp._authnContextTypeChoiceSequence == null) return false;
                if (this._authnContextTypeChoiceSequence != temp._authnContextTypeChoiceSequence) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnContextTypeChoiceSequence);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnContextTypeChoiceSequence);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoiceSequence); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoiceSequence); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnContextTypeChoiceSequence.equals(temp._authnContextTypeChoiceSequence)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoiceSequence);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoiceSequence);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoiceSequence);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoiceSequence);
                    }
                }
            } else if (temp._authnContextTypeChoiceSequence != null)
                return false;
            if (this._authnContextTypeChoiceChoice != null) {
                if (temp._authnContextTypeChoiceChoice == null) return false;
                if (this._authnContextTypeChoiceChoice != temp._authnContextTypeChoiceChoice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnContextTypeChoiceChoice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnContextTypeChoiceChoice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoiceChoice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoiceChoice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnContextTypeChoiceChoice.equals(temp._authnContextTypeChoiceChoice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoiceChoice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoiceChoice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoiceChoice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoiceChoice);
                    }
                }
            } else if (temp._authnContextTypeChoiceChoice != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'authnContextTypeChoiceChoice'.
     * 
     * @return the value of field 'AuthnContextTypeChoiceChoice'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceChoice getAuthnContextTypeChoiceChoice(
    ) {
        return this._authnContextTypeChoiceChoice;
    }

    /**
     * Returns the value of field 'authnContextTypeChoiceSequence'.
     * 
     * @return the value of field 'AuthnContextTypeChoiceSequence'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceSequence getAuthnContextTypeChoiceSequence(
    ) {
        return this._authnContextTypeChoiceSequence;
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
        if (_authnContextTypeChoiceSequence != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnContextTypeChoiceSequence)) {
           result = 37 * result + _authnContextTypeChoiceSequence.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnContextTypeChoiceSequence);
        }
        if (_authnContextTypeChoiceChoice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnContextTypeChoiceChoice)) {
           result = 37 * result + _authnContextTypeChoiceChoice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnContextTypeChoiceChoice);
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
     * Sets the value of field 'authnContextTypeChoiceChoice'.
     * 
     * @param authnContextTypeChoiceChoice the value of field
     * 'authnContextTypeChoiceChoice'.
     */
    public void setAuthnContextTypeChoiceChoice(
            final oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceChoice authnContextTypeChoiceChoice) {
        this._authnContextTypeChoiceChoice = authnContextTypeChoiceChoice;
    }

    /**
     * Sets the value of field 'authnContextTypeChoiceSequence'.
     * 
     * @param authnContextTypeChoiceSequence the value of field
     * 'authnContextTypeChoiceSequence'.
     */
    public void setAuthnContextTypeChoiceSequence(
            final oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceSequence authnContextTypeChoiceSequence) {
        this._authnContextTypeChoiceSequence = authnContextTypeChoiceSequence;
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
     * oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoice
     */
    public static oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoice unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoice) org.exolab.castor.xml.Unmarshaller.unmarshal(oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoice.class, reader);
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
