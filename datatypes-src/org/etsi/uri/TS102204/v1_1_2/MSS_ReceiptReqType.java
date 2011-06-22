/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class MSS_ReceiptReqType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MSS_ReceiptReqType extends org.etsi.uri.TS102204.v1_1_2.MessageAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _MSSP_TransID.
     */
    private java.lang.String _MSSP_TransID;

    /**
     * Field _mobileUser.
     */
    private org.etsi.uri.TS102204.v1_1_2.MobileUser _mobileUser;

    /**
     * Field _status.
     */
    private org.etsi.uri.TS102204.v1_1_2.Status _status;

    /**
     * Field _message.
     */
    private org.etsi.uri.TS102204.v1_1_2.Message _message;

    /**
     * Field _signedReceipt.
     */
    private org.etsi.uri.TS102204.v1_1_2.SignedReceipt _signedReceipt;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSS_ReceiptReqType() {
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

        if (obj instanceof MSS_ReceiptReqType) {

            MSS_ReceiptReqType temp = (MSS_ReceiptReqType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._MSSP_TransID != null) {
                if (temp._MSSP_TransID == null) return false;
                if (this._MSSP_TransID != temp._MSSP_TransID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._MSSP_TransID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._MSSP_TransID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSSP_TransID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSSP_TransID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._MSSP_TransID.equals(temp._MSSP_TransID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSSP_TransID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSSP_TransID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSSP_TransID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSSP_TransID);
                    }
                }
            } else if (temp._MSSP_TransID != null)
                return false;
            if (this._mobileUser != null) {
                if (temp._mobileUser == null) return false;
                if (this._mobileUser != temp._mobileUser) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._mobileUser);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._mobileUser);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mobileUser); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mobileUser); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._mobileUser.equals(temp._mobileUser)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mobileUser);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mobileUser);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mobileUser);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mobileUser);
                    }
                }
            } else if (temp._mobileUser != null)
                return false;
            if (this._status != null) {
                if (temp._status == null) return false;
                if (this._status != temp._status) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._status);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._status);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._status.equals(temp._status)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
                    }
                }
            } else if (temp._status != null)
                return false;
            if (this._message != null) {
                if (temp._message == null) return false;
                if (this._message != temp._message) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._message);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._message);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._message); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._message); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._message.equals(temp._message)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._message);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._message);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._message);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._message);
                    }
                }
            } else if (temp._message != null)
                return false;
            if (this._signedReceipt != null) {
                if (temp._signedReceipt == null) return false;
                if (this._signedReceipt != temp._signedReceipt) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signedReceipt);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signedReceipt);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signedReceipt); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signedReceipt); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signedReceipt.equals(temp._signedReceipt)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signedReceipt);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signedReceipt);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signedReceipt);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signedReceipt);
                    }
                }
            } else if (temp._signedReceipt != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'MSSP_TransID'.
     * 
     * @return the value of field 'MSSP_TransID'.
     */
    public java.lang.String getMSSP_TransID(
    ) {
        return this._MSSP_TransID;
    }

    /**
     * Returns the value of field 'message'.
     * 
     * @return the value of field 'Message'.
     */
    public org.etsi.uri.TS102204.v1_1_2.Message getMessage(
    ) {
        return this._message;
    }

    /**
     * Returns the value of field 'mobileUser'.
     * 
     * @return the value of field 'MobileUser'.
     */
    public org.etsi.uri.TS102204.v1_1_2.MobileUser getMobileUser(
    ) {
        return this._mobileUser;
    }

    /**
     * Returns the value of field 'signedReceipt'.
     * 
     * @return the value of field 'SignedReceipt'.
     */
    public org.etsi.uri.TS102204.v1_1_2.SignedReceipt getSignedReceipt(
    ) {
        return this._signedReceipt;
    }

    /**
     * Returns the value of field 'status'.
     * 
     * @return the value of field 'Status'.
     */
    public org.etsi.uri.TS102204.v1_1_2.Status getStatus(
    ) {
        return this._status;
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
        if (_MSSP_TransID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_MSSP_TransID)) {
           result = 37 * result + _MSSP_TransID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_MSSP_TransID);
        }
        if (_mobileUser != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_mobileUser)) {
           result = 37 * result + _mobileUser.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_mobileUser);
        }
        if (_status != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_status)) {
           result = 37 * result + _status.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_status);
        }
        if (_message != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_message)) {
           result = 37 * result + _message.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_message);
        }
        if (_signedReceipt != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signedReceipt)) {
           result = 37 * result + _signedReceipt.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signedReceipt);
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
     * Sets the value of field 'MSSP_TransID'.
     * 
     * @param MSSP_TransID the value of field 'MSSP_TransID'.
     */
    public void setMSSP_TransID(
            final java.lang.String MSSP_TransID) {
        this._MSSP_TransID = MSSP_TransID;
    }

    /**
     * Sets the value of field 'message'.
     * 
     * @param message the value of field 'message'.
     */
    public void setMessage(
            final org.etsi.uri.TS102204.v1_1_2.Message message) {
        this._message = message;
    }

    /**
     * Sets the value of field 'mobileUser'.
     * 
     * @param mobileUser the value of field 'mobileUser'.
     */
    public void setMobileUser(
            final org.etsi.uri.TS102204.v1_1_2.MobileUser mobileUser) {
        this._mobileUser = mobileUser;
    }

    /**
     * Sets the value of field 'signedReceipt'.
     * 
     * @param signedReceipt the value of field 'signedReceipt'.
     */
    public void setSignedReceipt(
            final org.etsi.uri.TS102204.v1_1_2.SignedReceipt signedReceipt) {
        this._signedReceipt = signedReceipt;
    }

    /**
     * Sets the value of field 'status'.
     * 
     * @param status the value of field 'status'.
     */
    public void setStatus(
            final org.etsi.uri.TS102204.v1_1_2.Status status) {
        this._status = status;
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
