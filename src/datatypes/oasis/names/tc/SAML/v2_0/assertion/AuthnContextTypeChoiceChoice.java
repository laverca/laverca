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

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class AuthnContextTypeChoiceChoice.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AuthnContextTypeChoiceChoice implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _authnContextDecl.
     */
    private java.lang.Object _authnContextDecl;

    /**
     * Field _authnContextDeclRef.
     */
    private java.lang.String _authnContextDeclRef;


      //----------------/
     //- Constructors -/
    //----------------/

    public AuthnContextTypeChoiceChoice() {
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

        if (obj instanceof AuthnContextTypeChoiceChoice) {

            AuthnContextTypeChoiceChoice temp = (AuthnContextTypeChoiceChoice)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._authnContextDecl != null) {
                if (temp._authnContextDecl == null) return false;
                if (this._authnContextDecl != temp._authnContextDecl) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnContextDecl);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnContextDecl);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextDecl); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextDecl); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnContextDecl.equals(temp._authnContextDecl)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextDecl);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextDecl);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextDecl);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextDecl);
                    }
                }
            } else if (temp._authnContextDecl != null)
                return false;
            if (this._authnContextDeclRef != null) {
                if (temp._authnContextDeclRef == null) return false;
                if (this._authnContextDeclRef != temp._authnContextDeclRef) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnContextDeclRef);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnContextDeclRef);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextDeclRef); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextDeclRef); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnContextDeclRef.equals(temp._authnContextDeclRef)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextDeclRef);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextDeclRef);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextDeclRef);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextDeclRef);
                    }
                }
            } else if (temp._authnContextDeclRef != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'authnContextDecl'.
     * 
     * @return the value of field 'AuthnContextDecl'.
     */
    public java.lang.Object getAuthnContextDecl(
    ) {
        return this._authnContextDecl;
    }

    /**
     * Returns the value of field 'authnContextDeclRef'.
     * 
     * @return the value of field 'AuthnContextDeclRef'.
     */
    public java.lang.String getAuthnContextDeclRef(
    ) {
        return this._authnContextDeclRef;
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
        if (_authnContextDecl != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnContextDecl)) {
           result = 37 * result + _authnContextDecl.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnContextDecl);
        }
        if (_authnContextDeclRef != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnContextDeclRef)) {
           result = 37 * result + _authnContextDeclRef.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnContextDeclRef);
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
     * Sets the value of field 'authnContextDecl'.
     * 
     * @param authnContextDecl the value of field 'authnContextDecl'
     */
    public void setAuthnContextDecl(
            final java.lang.Object authnContextDecl) {
        this._authnContextDecl = authnContextDecl;
    }

    /**
     * Sets the value of field 'authnContextDeclRef'.
     * 
     * @param authnContextDeclRef the value of field
     * 'authnContextDeclRef'.
     */
    public void setAuthnContextDeclRef(
            final java.lang.String authnContextDeclRef) {
        this._authnContextDeclRef = authnContextDeclRef;
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
     * oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceChoice
     */
    public static oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceChoice unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceChoice) org.exolab.castor.xml.Unmarshaller.unmarshal(oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoiceChoice.class, reader);
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
