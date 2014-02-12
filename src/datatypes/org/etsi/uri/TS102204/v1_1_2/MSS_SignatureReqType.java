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
 * Class MSS_SignatureReqType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MSS_SignatureReqType extends org.etsi.uri.TS102204.v1_1_2.MessageAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _validityDate.
     */
    private java.util.Date _validityDate;

    /**
     * Field _timeOut.
     */
    private long _timeOut;

    /**
     * keeps track of state for field: _timeOut
     */
    private boolean _has_timeOut;

    /**
     * Field _messagingMode.
     */
    private org.etsi.uri.TS102204.v1_1_2.types.MessagingModeType _messagingMode;

    /**
     * Field _mobileUser.
     */
    private org.etsi.uri.TS102204.v1_1_2.MobileUser _mobileUser;

    /**
     * Field _dataToBeSigned.
     */
    private org.etsi.uri.TS102204.v1_1_2.DataToBeSigned _dataToBeSigned;

    /**
     * Field _dataToBeDisplayed.
     */
    private org.etsi.uri.TS102204.v1_1_2.DataToBeDisplayed _dataToBeDisplayed;

    /**
     * Field _signatureProfile.
     */
    private org.etsi.uri.TS102204.v1_1_2.SignatureProfile _signatureProfile;

    /**
     * Field _additionalServices.
     */
    private org.etsi.uri.TS102204.v1_1_2.AdditionalServices _additionalServices;

    /**
     * Field _MSS_Format.
     */
    private org.etsi.uri.TS102204.v1_1_2.MSS_Format _MSS_Format;

    /**
     * Field _keyReference.
     */
    private org.etsi.uri.TS102204.v1_1_2.KeyReference _keyReference;

    /**
     * Field _signatureProfileComparison.
     */
    private org.etsi.uri.TS102204.v1_1_2.types.SignatureProfileComparisonType _signatureProfileComparison;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSS_SignatureReqType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteTimeOut(
    ) {
        this._has_timeOut= false;
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

        if (super.equals(obj)==false)
            return false;

        if (obj instanceof MSS_SignatureReqType) {

            MSS_SignatureReqType temp = (MSS_SignatureReqType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._validityDate != null) {
                if (temp._validityDate == null) return false;
                if (this._validityDate != temp._validityDate) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._validityDate);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._validityDate);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._validityDate); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._validityDate); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._validityDate.equals(temp._validityDate)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._validityDate);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._validityDate);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._validityDate);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._validityDate);
                    }
                }
            } else if (temp._validityDate != null)
                return false;
            if (this._timeOut != temp._timeOut)
                return false;
            if (this._has_timeOut != temp._has_timeOut)
                return false;
            if (this._messagingMode != null) {
                if (temp._messagingMode == null) return false;
                if (this._messagingMode != temp._messagingMode) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._messagingMode);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._messagingMode);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messagingMode); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messagingMode); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._messagingMode.equals(temp._messagingMode)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messagingMode);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messagingMode);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messagingMode);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messagingMode);
                    }
                }
            } else if (temp._messagingMode != null)
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
            if (this._dataToBeSigned != null) {
                if (temp._dataToBeSigned == null) return false;
                if (this._dataToBeSigned != temp._dataToBeSigned) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._dataToBeSigned);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._dataToBeSigned);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dataToBeSigned); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dataToBeSigned); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._dataToBeSigned.equals(temp._dataToBeSigned)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dataToBeSigned);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dataToBeSigned);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dataToBeSigned);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dataToBeSigned);
                    }
                }
            } else if (temp._dataToBeSigned != null)
                return false;
            if (this._dataToBeDisplayed != null) {
                if (temp._dataToBeDisplayed == null) return false;
                if (this._dataToBeDisplayed != temp._dataToBeDisplayed) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._dataToBeDisplayed);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._dataToBeDisplayed);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dataToBeDisplayed); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dataToBeDisplayed); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._dataToBeDisplayed.equals(temp._dataToBeDisplayed)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dataToBeDisplayed);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dataToBeDisplayed);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dataToBeDisplayed);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dataToBeDisplayed);
                    }
                }
            } else if (temp._dataToBeDisplayed != null)
                return false;
            if (this._signatureProfile != null) {
                if (temp._signatureProfile == null) return false;
                if (this._signatureProfile != temp._signatureProfile) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signatureProfile);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signatureProfile);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureProfile); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureProfile); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signatureProfile.equals(temp._signatureProfile)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureProfile);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureProfile);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureProfile);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureProfile);
                    }
                }
            } else if (temp._signatureProfile != null)
                return false;
            if (this._additionalServices != null) {
                if (temp._additionalServices == null) return false;
                if (this._additionalServices != temp._additionalServices) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._additionalServices);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._additionalServices);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._additionalServices); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._additionalServices); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._additionalServices.equals(temp._additionalServices)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._additionalServices);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._additionalServices);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._additionalServices);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._additionalServices);
                    }
                }
            } else if (temp._additionalServices != null)
                return false;
            if (this._MSS_Format != null) {
                if (temp._MSS_Format == null) return false;
                if (this._MSS_Format != temp._MSS_Format) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._MSS_Format);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._MSS_Format);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSS_Format); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSS_Format); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._MSS_Format.equals(temp._MSS_Format)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSS_Format);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSS_Format);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSS_Format);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSS_Format);
                    }
                }
            } else if (temp._MSS_Format != null)
                return false;
            if (this._keyReference != null) {
                if (temp._keyReference == null) return false;
                if (this._keyReference != temp._keyReference) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._keyReference);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._keyReference);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._keyReference); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._keyReference); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._keyReference.equals(temp._keyReference)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._keyReference);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._keyReference);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._keyReference);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._keyReference);
                    }
                }
            } else if (temp._keyReference != null)
                return false;
            if (this._signatureProfileComparison != null) {
                if (temp._signatureProfileComparison == null) return false;
                if (this._signatureProfileComparison != temp._signatureProfileComparison) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signatureProfileComparison);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signatureProfileComparison);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureProfileComparison); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureProfileComparison); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signatureProfileComparison.equals(temp._signatureProfileComparison)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureProfileComparison);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureProfileComparison);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureProfileComparison);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureProfileComparison);
                    }
                }
            } else if (temp._signatureProfileComparison != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'additionalServices'.
     * 
     * @return the value of field 'AdditionalServices'.
     */
    public org.etsi.uri.TS102204.v1_1_2.AdditionalServices getAdditionalServices(
    ) {
        return this._additionalServices;
    }

    /**
     * Returns the value of field 'dataToBeDisplayed'.
     * 
     * @return the value of field 'DataToBeDisplayed'.
     */
    public org.etsi.uri.TS102204.v1_1_2.DataToBeDisplayed getDataToBeDisplayed(
    ) {
        return this._dataToBeDisplayed;
    }

    /**
     * Returns the value of field 'dataToBeSigned'.
     * 
     * @return the value of field 'DataToBeSigned'.
     */
    public org.etsi.uri.TS102204.v1_1_2.DataToBeSigned getDataToBeSigned(
    ) {
        return this._dataToBeSigned;
    }

    /**
     * Returns the value of field 'keyReference'.
     * 
     * @return the value of field 'KeyReference'.
     */
    public org.etsi.uri.TS102204.v1_1_2.KeyReference getKeyReference(
    ) {
        return this._keyReference;
    }

    /**
     * Returns the value of field 'MSS_Format'.
     * 
     * @return the value of field 'MSS_Format'.
     */
    public org.etsi.uri.TS102204.v1_1_2.MSS_Format getMSS_Format(
    ) {
        return this._MSS_Format;
    }

    /**
     * Returns the value of field 'messagingMode'.
     * 
     * @return the value of field 'MessagingMode'.
     */
    public org.etsi.uri.TS102204.v1_1_2.types.MessagingModeType getMessagingMode(
    ) {
        return this._messagingMode;
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
     * Returns the value of field 'signatureProfile'.
     * 
     * @return the value of field 'SignatureProfile'.
     */
    public org.etsi.uri.TS102204.v1_1_2.SignatureProfile getSignatureProfile(
    ) {
        return this._signatureProfile;
    }

    /**
     * Returns the value of field 'signatureProfileComparison'.
     * 
     * @return the value of field 'SignatureProfileComparison'.
     */
    public org.etsi.uri.TS102204.v1_1_2.types.SignatureProfileComparisonType getSignatureProfileComparison(
    ) {
        return this._signatureProfileComparison;
    }

    /**
     * Returns the value of field 'timeOut'.
     * 
     * @return the value of field 'TimeOut'.
     */
    public long getTimeOut(
    ) {
        return this._timeOut;
    }

    /**
     * Returns the value of field 'validityDate'.
     * 
     * @return the value of field 'ValidityDate'.
     */
    public java.util.Date getValidityDate(
    ) {
        return this._validityDate;
    }

    /**
     * Method hasTimeOut.
     * 
     * @return true if at least one TimeOut has been added
     */
    public boolean hasTimeOut(
    ) {
        return this._has_timeOut;
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
        if (_validityDate != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_validityDate)) {
           result = 37 * result + _validityDate.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_validityDate);
        }
        result = 37 * result + (int)(_timeOut^(_timeOut>>>32));
        if (_messagingMode != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_messagingMode)) {
           result = 37 * result + _messagingMode.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_messagingMode);
        }
        if (_mobileUser != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_mobileUser)) {
           result = 37 * result + _mobileUser.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_mobileUser);
        }
        if (_dataToBeSigned != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_dataToBeSigned)) {
           result = 37 * result + _dataToBeSigned.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_dataToBeSigned);
        }
        if (_dataToBeDisplayed != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_dataToBeDisplayed)) {
           result = 37 * result + _dataToBeDisplayed.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_dataToBeDisplayed);
        }
        if (_signatureProfile != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signatureProfile)) {
           result = 37 * result + _signatureProfile.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signatureProfile);
        }
        if (_additionalServices != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_additionalServices)) {
           result = 37 * result + _additionalServices.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_additionalServices);
        }
        if (_MSS_Format != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_MSS_Format)) {
           result = 37 * result + _MSS_Format.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_MSS_Format);
        }
        if (_keyReference != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_keyReference)) {
           result = 37 * result + _keyReference.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_keyReference);
        }
        if (_signatureProfileComparison != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signatureProfileComparison)) {
           result = 37 * result + _signatureProfileComparison.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signatureProfileComparison);
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
     * Sets the value of field 'additionalServices'.
     * 
     * @param additionalServices the value of field
     * 'additionalServices'.
     */
    public void setAdditionalServices(
            final org.etsi.uri.TS102204.v1_1_2.AdditionalServices additionalServices) {
        this._additionalServices = additionalServices;
    }

    /**
     * Sets the value of field 'dataToBeDisplayed'.
     * 
     * @param dataToBeDisplayed the value of field
     * 'dataToBeDisplayed'.
     */
    public void setDataToBeDisplayed(
            final org.etsi.uri.TS102204.v1_1_2.DataToBeDisplayed dataToBeDisplayed) {
        this._dataToBeDisplayed = dataToBeDisplayed;
    }

    /**
     * Sets the value of field 'dataToBeSigned'.
     * 
     * @param dataToBeSigned the value of field 'dataToBeSigned'.
     */
    public void setDataToBeSigned(
            final org.etsi.uri.TS102204.v1_1_2.DataToBeSigned dataToBeSigned) {
        this._dataToBeSigned = dataToBeSigned;
    }

    /**
     * Sets the value of field 'keyReference'.
     * 
     * @param keyReference the value of field 'keyReference'.
     */
    public void setKeyReference(
            final org.etsi.uri.TS102204.v1_1_2.KeyReference keyReference) {
        this._keyReference = keyReference;
    }

    /**
     * Sets the value of field 'MSS_Format'.
     * 
     * @param MSS_Format the value of field 'MSS_Format'.
     */
    public void setMSS_Format(
            final org.etsi.uri.TS102204.v1_1_2.MSS_Format MSS_Format) {
        this._MSS_Format = MSS_Format;
    }

    /**
     * Sets the value of field 'messagingMode'.
     * 
     * @param messagingMode the value of field 'messagingMode'.
     */
    public void setMessagingMode(
            final org.etsi.uri.TS102204.v1_1_2.types.MessagingModeType messagingMode) {
        this._messagingMode = messagingMode;
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
     * Sets the value of field 'signatureProfile'.
     * 
     * @param signatureProfile the value of field 'signatureProfile'
     */
    public void setSignatureProfile(
            final org.etsi.uri.TS102204.v1_1_2.SignatureProfile signatureProfile) {
        this._signatureProfile = signatureProfile;
    }

    /**
     * Sets the value of field 'signatureProfileComparison'.
     * 
     * @param signatureProfileComparison the value of field
     * 'signatureProfileComparison'.
     */
    public void setSignatureProfileComparison(
            final org.etsi.uri.TS102204.v1_1_2.types.SignatureProfileComparisonType signatureProfileComparison) {
        this._signatureProfileComparison = signatureProfileComparison;
    }

    /**
     * Sets the value of field 'timeOut'.
     * 
     * @param timeOut the value of field 'timeOut'.
     */
    public void setTimeOut(
            final long timeOut) {
        this._timeOut = timeOut;
        this._has_timeOut = true;
    }

    /**
     * Sets the value of field 'validityDate'.
     * 
     * @param validityDate the value of field 'validityDate'.
     */
    public void setValidityDate(
            final java.util.Date validityDate) {
        this._validityDate = validityDate;
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
