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

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class MSS_StatusRespType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MSS_StatusRespType extends org.etsi.uri.TS102204.v1_1_2.MessageAbstractType 
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
     * Field _MSS_Signature.
     */
    private org.etsi.uri.TS102204.v1_1_2.MSS_Signature _MSS_Signature;

    /**
     * Field _status.
     */
    private org.etsi.uri.TS102204.v1_1_2.Status _status;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSS_StatusRespType() {
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

        if (obj instanceof MSS_StatusRespType) {

            MSS_StatusRespType temp = (MSS_StatusRespType)obj;
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
            if (this._MSS_Signature != null) {
                if (temp._MSS_Signature == null) return false;
                if (this._MSS_Signature != temp._MSS_Signature) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._MSS_Signature);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._MSS_Signature);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSS_Signature); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSS_Signature); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._MSS_Signature.equals(temp._MSS_Signature)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSS_Signature);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSS_Signature);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSS_Signature);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSS_Signature);
                    }
                }
            } else if (temp._MSS_Signature != null)
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
     * Returns the value of field 'MSS_Signature'.
     * 
     * @return the value of field 'MSS_Signature'.
     */
    public org.etsi.uri.TS102204.v1_1_2.MSS_Signature getMSS_Signature(
    ) {
        return this._MSS_Signature;
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
        if (_MSS_Signature != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_MSS_Signature)) {
           result = 37 * result + _MSS_Signature.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_MSS_Signature);
        }
        if (_status != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_status)) {
           result = 37 * result + _status.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_status);
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
     * Sets the value of field 'MSS_Signature'.
     * 
     * @param MSS_Signature the value of field 'MSS_Signature'.
     */
    public void setMSS_Signature(
            final org.etsi.uri.TS102204.v1_1_2.MSS_Signature MSS_Signature) {
        this._MSS_Signature = MSS_Signature;
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
