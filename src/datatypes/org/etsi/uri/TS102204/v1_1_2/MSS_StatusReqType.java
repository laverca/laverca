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
 * Class MSS_StatusReqType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MSS_StatusReqType extends org.etsi.uri.TS102204.v1_1_2.MessageAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _MSSP_TransID.
     */
    private java.lang.String _MSSP_TransID;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSS_StatusReqType() {
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

        if (obj instanceof MSS_StatusReqType) {

            MSS_StatusReqType temp = (MSS_StatusReqType)obj;
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
