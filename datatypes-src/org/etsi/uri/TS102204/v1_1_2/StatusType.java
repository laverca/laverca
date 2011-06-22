/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class StatusType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class StatusType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _statusCode.
     */
    private org.etsi.uri.TS102204.v1_1_2.StatusCode _statusCode;

    /**
     * Field _statusMessage.
     */
    private java.lang.String _statusMessage;

    /**
     * Field _statusDetail.
     */
    private org.etsi.uri.TS102204.v1_1_2.StatusDetail _statusDetail;


      //----------------/
     //- Constructors -/
    //----------------/

    public StatusType() {
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

        if (obj instanceof StatusType) {

            StatusType temp = (StatusType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._statusCode != null) {
                if (temp._statusCode == null) return false;
                if (this._statusCode != temp._statusCode) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._statusCode);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._statusCode);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusCode); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusCode); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._statusCode.equals(temp._statusCode)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusCode);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusCode);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusCode);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusCode);
                    }
                }
            } else if (temp._statusCode != null)
                return false;
            if (this._statusMessage != null) {
                if (temp._statusMessage == null) return false;
                if (this._statusMessage != temp._statusMessage) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._statusMessage);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._statusMessage);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusMessage); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusMessage); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._statusMessage.equals(temp._statusMessage)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusMessage);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusMessage);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusMessage);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusMessage);
                    }
                }
            } else if (temp._statusMessage != null)
                return false;
            if (this._statusDetail != null) {
                if (temp._statusDetail == null) return false;
                if (this._statusDetail != temp._statusDetail) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._statusDetail);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._statusDetail);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusDetail); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusDetail); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._statusDetail.equals(temp._statusDetail)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusDetail);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusDetail);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._statusDetail);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._statusDetail);
                    }
                }
            } else if (temp._statusDetail != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'statusCode'.
     * 
     * @return the value of field 'StatusCode'.
     */
    public org.etsi.uri.TS102204.v1_1_2.StatusCode getStatusCode(
    ) {
        return this._statusCode;
    }

    /**
     * Returns the value of field 'statusDetail'.
     * 
     * @return the value of field 'StatusDetail'.
     */
    public org.etsi.uri.TS102204.v1_1_2.StatusDetail getStatusDetail(
    ) {
        return this._statusDetail;
    }

    /**
     * Returns the value of field 'statusMessage'.
     * 
     * @return the value of field 'StatusMessage'.
     */
    public java.lang.String getStatusMessage(
    ) {
        return this._statusMessage;
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
        if (_statusCode != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_statusCode)) {
           result = 37 * result + _statusCode.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_statusCode);
        }
        if (_statusMessage != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_statusMessage)) {
           result = 37 * result + _statusMessage.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_statusMessage);
        }
        if (_statusDetail != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_statusDetail)) {
           result = 37 * result + _statusDetail.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_statusDetail);
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
     * Sets the value of field 'statusCode'.
     * 
     * @param statusCode the value of field 'statusCode'.
     */
    public void setStatusCode(
            final org.etsi.uri.TS102204.v1_1_2.StatusCode statusCode) {
        this._statusCode = statusCode;
    }

    /**
     * Sets the value of field 'statusDetail'.
     * 
     * @param statusDetail the value of field 'statusDetail'.
     */
    public void setStatusDetail(
            final org.etsi.uri.TS102204.v1_1_2.StatusDetail statusDetail) {
        this._statusDetail = statusDetail;
    }

    /**
     * Sets the value of field 'statusMessage'.
     * 
     * @param statusMessage the value of field 'statusMessage'.
     */
    public void setStatusMessage(
            final java.lang.String statusMessage) {
        this._statusMessage = statusMessage;
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
