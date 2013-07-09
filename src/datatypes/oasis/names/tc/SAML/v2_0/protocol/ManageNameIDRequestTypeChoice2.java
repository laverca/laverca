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
 * Class ManageNameIDRequestTypeChoice2.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ManageNameIDRequestTypeChoice2 implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _newID.
     */
    private java.lang.String _newID;

    /**
     * Field _newEncryptedID.
     */
    private oasis.names.tc.SAML.v2_0.protocol.NewEncryptedID _newEncryptedID;

    /**
     * Field _terminate.
     */
    private oasis.names.tc.SAML.v2_0.protocol.Terminate _terminate;


      //----------------/
     //- Constructors -/
    //----------------/

    public ManageNameIDRequestTypeChoice2() {
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

        if (obj instanceof ManageNameIDRequestTypeChoice2) {

            ManageNameIDRequestTypeChoice2 temp = (ManageNameIDRequestTypeChoice2)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._newID != null) {
                if (temp._newID == null) return false;
                if (this._newID != temp._newID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._newID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._newID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._newID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._newID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._newID.equals(temp._newID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._newID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._newID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._newID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._newID);
                    }
                }
            } else if (temp._newID != null)
                return false;
            if (this._newEncryptedID != null) {
                if (temp._newEncryptedID == null) return false;
                if (this._newEncryptedID != temp._newEncryptedID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._newEncryptedID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._newEncryptedID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._newEncryptedID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._newEncryptedID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._newEncryptedID.equals(temp._newEncryptedID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._newEncryptedID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._newEncryptedID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._newEncryptedID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._newEncryptedID);
                    }
                }
            } else if (temp._newEncryptedID != null)
                return false;
            if (this._terminate != null) {
                if (temp._terminate == null) return false;
                if (this._terminate != temp._terminate) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._terminate);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._terminate);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._terminate); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._terminate); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._terminate.equals(temp._terminate)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._terminate);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._terminate);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._terminate);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._terminate);
                    }
                }
            } else if (temp._terminate != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'newEncryptedID'.
     * 
     * @return the value of field 'NewEncryptedID'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.NewEncryptedID getNewEncryptedID(
    ) {
        return this._newEncryptedID;
    }

    /**
     * Returns the value of field 'newID'.
     * 
     * @return the value of field 'NewID'.
     */
    public java.lang.String getNewID(
    ) {
        return this._newID;
    }

    /**
     * Returns the value of field 'terminate'.
     * 
     * @return the value of field 'Terminate'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.Terminate getTerminate(
    ) {
        return this._terminate;
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
        if (_newID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_newID)) {
           result = 37 * result + _newID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_newID);
        }
        if (_newEncryptedID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_newEncryptedID)) {
           result = 37 * result + _newEncryptedID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_newEncryptedID);
        }
        if (_terminate != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_terminate)) {
           result = 37 * result + _terminate.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_terminate);
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
     * Sets the value of field 'newEncryptedID'.
     * 
     * @param newEncryptedID the value of field 'newEncryptedID'.
     */
    public void setNewEncryptedID(
            final oasis.names.tc.SAML.v2_0.protocol.NewEncryptedID newEncryptedID) {
        this._newEncryptedID = newEncryptedID;
    }

    /**
     * Sets the value of field 'newID'.
     * 
     * @param newID the value of field 'newID'.
     */
    public void setNewID(
            final java.lang.String newID) {
        this._newID = newID;
    }

    /**
     * Sets the value of field 'terminate'.
     * 
     * @param terminate the value of field 'terminate'.
     */
    public void setTerminate(
            final oasis.names.tc.SAML.v2_0.protocol.Terminate terminate) {
        this._terminate = terminate;
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
     * oasis.names.tc.SAML.v2_0.protocol.ManageNameIDRequestTypeChoice2
     */
    public static oasis.names.tc.SAML.v2_0.protocol.ManageNameIDRequestTypeChoice2 unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (oasis.names.tc.SAML.v2_0.protocol.ManageNameIDRequestTypeChoice2) org.exolab.castor.xml.Unmarshaller.unmarshal(oasis.names.tc.SAML.v2_0.protocol.ManageNameIDRequestTypeChoice2.class, reader);
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
