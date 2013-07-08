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

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class MessageAbstractType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MessageAbstractType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _majorVersion.
     */
    private long _majorVersion;

    /**
     * keeps track of state for field: _majorVersion
     */
    private boolean _has_majorVersion;

    /**
     * Field _minorVersion.
     */
    private long _minorVersion;

    /**
     * keeps track of state for field: _minorVersion
     */
    private boolean _has_minorVersion;

    /**
     * Field _AP_Info.
     */
    private org.etsi.uri.TS102204.v1_1_2.AP_Info _AP_Info;

    /**
     * Field _MSSP_Info.
     */
    private org.etsi.uri.TS102204.v1_1_2.MSSP_Info _MSSP_Info;


      //----------------/
     //- Constructors -/
    //----------------/

    public MessageAbstractType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteMajorVersion(
    ) {
        this._has_majorVersion= false;
    }

    /**
     */
    public void deleteMinorVersion(
    ) {
        this._has_minorVersion= false;
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

        if (obj instanceof MessageAbstractType) {

            MessageAbstractType temp = (MessageAbstractType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._majorVersion != temp._majorVersion)
                return false;
            if (this._has_majorVersion != temp._has_majorVersion)
                return false;
            if (this._minorVersion != temp._minorVersion)
                return false;
            if (this._has_minorVersion != temp._has_minorVersion)
                return false;
            if (this._AP_Info != null) {
                if (temp._AP_Info == null) return false;
                if (this._AP_Info != temp._AP_Info) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._AP_Info);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._AP_Info);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_Info); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_Info); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._AP_Info.equals(temp._AP_Info)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_Info);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_Info);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_Info);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_Info);
                    }
                }
            } else if (temp._AP_Info != null)
                return false;
            if (this._MSSP_Info != null) {
                if (temp._MSSP_Info == null) return false;
                if (this._MSSP_Info != temp._MSSP_Info) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._MSSP_Info);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._MSSP_Info);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSSP_Info); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSSP_Info); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._MSSP_Info.equals(temp._MSSP_Info)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSSP_Info);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSSP_Info);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSSP_Info);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSSP_Info);
                    }
                }
            } else if (temp._MSSP_Info != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'AP_Info'.
     * 
     * @return the value of field 'AP_Info'.
     */
    public org.etsi.uri.TS102204.v1_1_2.AP_Info getAP_Info(
    ) {
        return this._AP_Info;
    }

    /**
     * Returns the value of field 'MSSP_Info'.
     * 
     * @return the value of field 'MSSP_Info'.
     */
    public org.etsi.uri.TS102204.v1_1_2.MSSP_Info getMSSP_Info(
    ) {
        return this._MSSP_Info;
    }

    /**
     * Returns the value of field 'majorVersion'.
     * 
     * @return the value of field 'MajorVersion'.
     */
    public long getMajorVersion(
    ) {
        return this._majorVersion;
    }

    /**
     * Returns the value of field 'minorVersion'.
     * 
     * @return the value of field 'MinorVersion'.
     */
    public long getMinorVersion(
    ) {
        return this._minorVersion;
    }

    /**
     * Method hasMajorVersion.
     * 
     * @return true if at least one MajorVersion has been added
     */
    public boolean hasMajorVersion(
    ) {
        return this._has_majorVersion;
    }

    /**
     * Method hasMinorVersion.
     * 
     * @return true if at least one MinorVersion has been added
     */
    public boolean hasMinorVersion(
    ) {
        return this._has_minorVersion;
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
        result = 37 * result + (int)(_majorVersion^(_majorVersion>>>32));
        result = 37 * result + (int)(_minorVersion^(_minorVersion>>>32));
        if (_AP_Info != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_AP_Info)) {
           result = 37 * result + _AP_Info.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_AP_Info);
        }
        if (_MSSP_Info != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_MSSP_Info)) {
           result = 37 * result + _MSSP_Info.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_MSSP_Info);
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
     * Sets the value of field 'AP_Info'.
     * 
     * @param AP_Info the value of field 'AP_Info'.
     */
    public void setAP_Info(
            final org.etsi.uri.TS102204.v1_1_2.AP_Info AP_Info) {
        this._AP_Info = AP_Info;
    }

    /**
     * Sets the value of field 'MSSP_Info'.
     * 
     * @param MSSP_Info the value of field 'MSSP_Info'.
     */
    public void setMSSP_Info(
            final org.etsi.uri.TS102204.v1_1_2.MSSP_Info MSSP_Info) {
        this._MSSP_Info = MSSP_Info;
    }

    /**
     * Sets the value of field 'majorVersion'.
     * 
     * @param majorVersion the value of field 'majorVersion'.
     */
    public void setMajorVersion(
            final long majorVersion) {
        this._majorVersion = majorVersion;
        this._has_majorVersion = true;
    }

    /**
     * Sets the value of field 'minorVersion'.
     * 
     * @param minorVersion the value of field 'minorVersion'.
     */
    public void setMinorVersion(
            final long minorVersion) {
        this._minorVersion = minorVersion;
        this._has_minorVersion = true;
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
