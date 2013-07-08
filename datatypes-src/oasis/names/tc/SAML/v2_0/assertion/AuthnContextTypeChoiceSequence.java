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
 * Class AuthnContextTypeChoiceSequence.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AuthnContextTypeChoiceSequence implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _authnContextClassRef.
     */
    private java.lang.String _authnContextClassRef;

    /**
     * Field _authnContextTypeChoiceSequenceChoice.
     */
    private oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceSequenceChoice _authnContextTypeChoiceSequenceChoice;


      //----------------/
     //- Constructors -/
    //----------------/

    public AuthnContextTypeChoiceSequence() {
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

        if (obj instanceof AuthnContextTypeChoiceSequence) {

            AuthnContextTypeChoiceSequence temp = (AuthnContextTypeChoiceSequence)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._authnContextClassRef != null) {
                if (temp._authnContextClassRef == null) return false;
                if (this._authnContextClassRef != temp._authnContextClassRef) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnContextClassRef);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnContextClassRef);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextClassRef); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextClassRef); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnContextClassRef.equals(temp._authnContextClassRef)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextClassRef);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextClassRef);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextClassRef);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextClassRef);
                    }
                }
            } else if (temp._authnContextClassRef != null)
                return false;
            if (this._authnContextTypeChoiceSequenceChoice != null) {
                if (temp._authnContextTypeChoiceSequenceChoice == null) return false;
                if (this._authnContextTypeChoiceSequenceChoice != temp._authnContextTypeChoiceSequenceChoice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnContextTypeChoiceSequenceChoice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnContextTypeChoiceSequenceChoice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoiceSequenceChoice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoiceSequenceChoice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnContextTypeChoiceSequenceChoice.equals(temp._authnContextTypeChoiceSequenceChoice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoiceSequenceChoice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoiceSequenceChoice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoiceSequenceChoice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoiceSequenceChoice);
                    }
                }
            } else if (temp._authnContextTypeChoiceSequenceChoice != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'authnContextClassRef'.
     * 
     * @return the value of field 'AuthnContextClassRef'.
     */
    public java.lang.String getAuthnContextClassRef(
    ) {
        return this._authnContextClassRef;
    }

    /**
     * Returns the value of field
     * 'authnContextTypeChoiceSequenceChoice'.
     * 
     * @return the value of field
     * 'AuthnContextTypeChoiceSequenceChoice'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceSequenceChoice getAuthnContextTypeChoiceSequenceChoice(
    ) {
        return this._authnContextTypeChoiceSequenceChoice;
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
        if (_authnContextClassRef != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnContextClassRef)) {
           result = 37 * result + _authnContextClassRef.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnContextClassRef);
        }
        if (_authnContextTypeChoiceSequenceChoice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnContextTypeChoiceSequenceChoice)) {
           result = 37 * result + _authnContextTypeChoiceSequenceChoice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnContextTypeChoiceSequenceChoice);
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
     * Sets the value of field 'authnContextClassRef'.
     * 
     * @param authnContextClassRef the value of field
     * 'authnContextClassRef'.
     */
    public void setAuthnContextClassRef(
            final java.lang.String authnContextClassRef) {
        this._authnContextClassRef = authnContextClassRef;
    }

    /**
     * Sets the value of field
     * 'authnContextTypeChoiceSequenceChoice'.
     * 
     * @param authnContextTypeChoiceSequenceChoice the value of
     * field 'authnContextTypeChoiceSequenceChoice'.
     */
    public void setAuthnContextTypeChoiceSequenceChoice(
            final oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceSequenceChoice authnContextTypeChoiceSequenceChoice) {
        this._authnContextTypeChoiceSequenceChoice = authnContextTypeChoiceSequenceChoice;
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
     * oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceSequence
     */
    public static oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceSequence unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceSequence) org.exolab.castor.xml.Unmarshaller.unmarshal(oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceSequence.class, reader);
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
