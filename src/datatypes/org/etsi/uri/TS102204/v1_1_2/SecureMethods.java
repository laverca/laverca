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
 * Class SecureMethods.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class SecureMethods implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _MSS_Signature.
     */
    private boolean _MSS_Signature;

    /**
     * keeps track of state for field: _MSS_Signature
     */
    private boolean _has_MSS_Signature;

    /**
     * Field _MSS_Registration.
     */
    private boolean _MSS_Registration;

    /**
     * keeps track of state for field: _MSS_Registration
     */
    private boolean _has_MSS_Registration;

    /**
     * Field _MSS_Notification.
     */
    private boolean _MSS_Notification;

    /**
     * keeps track of state for field: _MSS_Notification
     */
    private boolean _has_MSS_Notification;

    /**
     * Field _MSS_ProfileQuery.
     */
    private boolean _MSS_ProfileQuery;

    /**
     * keeps track of state for field: _MSS_ProfileQuery
     */
    private boolean _has_MSS_ProfileQuery;

    /**
     * Field _MSS_Receipt.
     */
    private boolean _MSS_Receipt;

    /**
     * keeps track of state for field: _MSS_Receipt
     */
    private boolean _has_MSS_Receipt;

    /**
     * Field _MSS_Status.
     */
    private boolean _MSS_Status;

    /**
     * keeps track of state for field: _MSS_Status
     */
    private boolean _has_MSS_Status;


      //----------------/
     //- Constructors -/
    //----------------/

    public SecureMethods() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteMSS_Notification(
    ) {
        this._has_MSS_Notification= false;
    }

    /**
     */
    public void deleteMSS_ProfileQuery(
    ) {
        this._has_MSS_ProfileQuery= false;
    }

    /**
     */
    public void deleteMSS_Receipt(
    ) {
        this._has_MSS_Receipt= false;
    }

    /**
     */
    public void deleteMSS_Registration(
    ) {
        this._has_MSS_Registration= false;
    }

    /**
     */
    public void deleteMSS_Signature(
    ) {
        this._has_MSS_Signature= false;
    }

    /**
     */
    public void deleteMSS_Status(
    ) {
        this._has_MSS_Status= false;
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

        if (obj instanceof SecureMethods) {

            SecureMethods temp = (SecureMethods)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._MSS_Signature != temp._MSS_Signature)
                return false;
            if (this._has_MSS_Signature != temp._has_MSS_Signature)
                return false;
            if (this._MSS_Registration != temp._MSS_Registration)
                return false;
            if (this._has_MSS_Registration != temp._has_MSS_Registration)
                return false;
            if (this._MSS_Notification != temp._MSS_Notification)
                return false;
            if (this._has_MSS_Notification != temp._has_MSS_Notification)
                return false;
            if (this._MSS_ProfileQuery != temp._MSS_ProfileQuery)
                return false;
            if (this._has_MSS_ProfileQuery != temp._has_MSS_ProfileQuery)
                return false;
            if (this._MSS_Receipt != temp._MSS_Receipt)
                return false;
            if (this._has_MSS_Receipt != temp._has_MSS_Receipt)
                return false;
            if (this._MSS_Status != temp._MSS_Status)
                return false;
            if (this._has_MSS_Status != temp._has_MSS_Status)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'MSS_Notification'.
     * 
     * @return the value of field 'MSS_Notification'.
     */
    public boolean getMSS_Notification(
    ) {
        return this._MSS_Notification;
    }

    /**
     * Returns the value of field 'MSS_ProfileQuery'.
     * 
     * @return the value of field 'MSS_ProfileQuery'.
     */
    public boolean getMSS_ProfileQuery(
    ) {
        return this._MSS_ProfileQuery;
    }

    /**
     * Returns the value of field 'MSS_Receipt'.
     * 
     * @return the value of field 'MSS_Receipt'.
     */
    public boolean getMSS_Receipt(
    ) {
        return this._MSS_Receipt;
    }

    /**
     * Returns the value of field 'MSS_Registration'.
     * 
     * @return the value of field 'MSS_Registration'.
     */
    public boolean getMSS_Registration(
    ) {
        return this._MSS_Registration;
    }

    /**
     * Returns the value of field 'MSS_Signature'.
     * 
     * @return the value of field 'MSS_Signature'.
     */
    public boolean getMSS_Signature(
    ) {
        return this._MSS_Signature;
    }

    /**
     * Returns the value of field 'MSS_Status'.
     * 
     * @return the value of field 'MSS_Status'.
     */
    public boolean getMSS_Status(
    ) {
        return this._MSS_Status;
    }

    /**
     * Method hasMSS_Notification.
     * 
     * @return true if at least one MSS_Notification has been added
     */
    public boolean hasMSS_Notification(
    ) {
        return this._has_MSS_Notification;
    }

    /**
     * Method hasMSS_ProfileQuery.
     * 
     * @return true if at least one MSS_ProfileQuery has been added
     */
    public boolean hasMSS_ProfileQuery(
    ) {
        return this._has_MSS_ProfileQuery;
    }

    /**
     * Method hasMSS_Receipt.
     * 
     * @return true if at least one MSS_Receipt has been added
     */
    public boolean hasMSS_Receipt(
    ) {
        return this._has_MSS_Receipt;
    }

    /**
     * Method hasMSS_Registration.
     * 
     * @return true if at least one MSS_Registration has been added
     */
    public boolean hasMSS_Registration(
    ) {
        return this._has_MSS_Registration;
    }

    /**
     * Method hasMSS_Signature.
     * 
     * @return true if at least one MSS_Signature has been added
     */
    public boolean hasMSS_Signature(
    ) {
        return this._has_MSS_Signature;
    }

    /**
     * Method hasMSS_Status.
     * 
     * @return true if at least one MSS_Status has been added
     */
    public boolean hasMSS_Status(
    ) {
        return this._has_MSS_Status;
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
        result = 37 * result + (_MSS_Signature?0:1);
        result = 37 * result + (_MSS_Registration?0:1);
        result = 37 * result + (_MSS_Notification?0:1);
        result = 37 * result + (_MSS_ProfileQuery?0:1);
        result = 37 * result + (_MSS_Receipt?0:1);
        result = 37 * result + (_MSS_Status?0:1);

        return result;
    }

    /**
     * Returns the value of field 'MSS_Notification'.
     * 
     * @return the value of field 'MSS_Notification'.
     */
    public boolean isMSS_Notification(
    ) {
        return this._MSS_Notification;
    }

    /**
     * Returns the value of field 'MSS_ProfileQuery'.
     * 
     * @return the value of field 'MSS_ProfileQuery'.
     */
    public boolean isMSS_ProfileQuery(
    ) {
        return this._MSS_ProfileQuery;
    }

    /**
     * Returns the value of field 'MSS_Receipt'.
     * 
     * @return the value of field 'MSS_Receipt'.
     */
    public boolean isMSS_Receipt(
    ) {
        return this._MSS_Receipt;
    }

    /**
     * Returns the value of field 'MSS_Registration'.
     * 
     * @return the value of field 'MSS_Registration'.
     */
    public boolean isMSS_Registration(
    ) {
        return this._MSS_Registration;
    }

    /**
     * Returns the value of field 'MSS_Signature'.
     * 
     * @return the value of field 'MSS_Signature'.
     */
    public boolean isMSS_Signature(
    ) {
        return this._MSS_Signature;
    }

    /**
     * Returns the value of field 'MSS_Status'.
     * 
     * @return the value of field 'MSS_Status'.
     */
    public boolean isMSS_Status(
    ) {
        return this._MSS_Status;
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
     * Sets the value of field 'MSS_Notification'.
     * 
     * @param MSS_Notification the value of field 'MSS_Notification'
     */
    public void setMSS_Notification(
            final boolean MSS_Notification) {
        this._MSS_Notification = MSS_Notification;
        this._has_MSS_Notification = true;
    }

    /**
     * Sets the value of field 'MSS_ProfileQuery'.
     * 
     * @param MSS_ProfileQuery the value of field 'MSS_ProfileQuery'
     */
    public void setMSS_ProfileQuery(
            final boolean MSS_ProfileQuery) {
        this._MSS_ProfileQuery = MSS_ProfileQuery;
        this._has_MSS_ProfileQuery = true;
    }

    /**
     * Sets the value of field 'MSS_Receipt'.
     * 
     * @param MSS_Receipt the value of field 'MSS_Receipt'.
     */
    public void setMSS_Receipt(
            final boolean MSS_Receipt) {
        this._MSS_Receipt = MSS_Receipt;
        this._has_MSS_Receipt = true;
    }

    /**
     * Sets the value of field 'MSS_Registration'.
     * 
     * @param MSS_Registration the value of field 'MSS_Registration'
     */
    public void setMSS_Registration(
            final boolean MSS_Registration) {
        this._MSS_Registration = MSS_Registration;
        this._has_MSS_Registration = true;
    }

    /**
     * Sets the value of field 'MSS_Signature'.
     * 
     * @param MSS_Signature the value of field 'MSS_Signature'.
     */
    public void setMSS_Signature(
            final boolean MSS_Signature) {
        this._MSS_Signature = MSS_Signature;
        this._has_MSS_Signature = true;
    }

    /**
     * Sets the value of field 'MSS_Status'.
     * 
     * @param MSS_Status the value of field 'MSS_Status'.
     */
    public void setMSS_Status(
            final boolean MSS_Status) {
        this._MSS_Status = MSS_Status;
        this._has_MSS_Status = true;
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
     * org.etsi.uri.TS102204.v1_1_2.SecureMethods
     */
    public static org.etsi.uri.TS102204.v1_1_2.SecureMethods unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.etsi.uri.TS102204.v1_1_2.SecureMethods) org.exolab.castor.xml.Unmarshaller.unmarshal(org.etsi.uri.TS102204.v1_1_2.SecureMethods.class, reader);
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
