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
 * Class MSS_HandshakeRespType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MSS_HandshakeRespType extends org.etsi.uri.TS102204.v1_1_2.MessageAbstractType 
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
     * Field _secureMethods.
     */
    private org.etsi.uri.TS102204.v1_1_2.SecureMethods _secureMethods;

    /**
     * Field _matchingMSSPCertificates.
     */
    private org.etsi.uri.TS102204.v1_1_2.MatchingMSSPCertificates _matchingMSSPCertificates;

    /**
     * Field _matchingAPCertificates.
     */
    private org.etsi.uri.TS102204.v1_1_2.MatchingAPCertificates _matchingAPCertificates;

    /**
     * Field _matchingSigAlgList.
     */
    private org.etsi.uri.TS102204.v1_1_2.MatchingSigAlgList _matchingSigAlgList;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSS_HandshakeRespType() {
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

        if (obj instanceof MSS_HandshakeRespType) {

            MSS_HandshakeRespType temp = (MSS_HandshakeRespType)obj;
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
            if (this._secureMethods != null) {
                if (temp._secureMethods == null) return false;
                if (this._secureMethods != temp._secureMethods) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._secureMethods);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._secureMethods);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._secureMethods); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._secureMethods); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._secureMethods.equals(temp._secureMethods)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._secureMethods);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._secureMethods);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._secureMethods);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._secureMethods);
                    }
                }
            } else if (temp._secureMethods != null)
                return false;
            if (this._matchingMSSPCertificates != null) {
                if (temp._matchingMSSPCertificates == null) return false;
                if (this._matchingMSSPCertificates != temp._matchingMSSPCertificates) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._matchingMSSPCertificates);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._matchingMSSPCertificates);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._matchingMSSPCertificates); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._matchingMSSPCertificates); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._matchingMSSPCertificates.equals(temp._matchingMSSPCertificates)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._matchingMSSPCertificates);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._matchingMSSPCertificates);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._matchingMSSPCertificates);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._matchingMSSPCertificates);
                    }
                }
            } else if (temp._matchingMSSPCertificates != null)
                return false;
            if (this._matchingAPCertificates != null) {
                if (temp._matchingAPCertificates == null) return false;
                if (this._matchingAPCertificates != temp._matchingAPCertificates) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._matchingAPCertificates);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._matchingAPCertificates);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._matchingAPCertificates); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._matchingAPCertificates); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._matchingAPCertificates.equals(temp._matchingAPCertificates)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._matchingAPCertificates);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._matchingAPCertificates);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._matchingAPCertificates);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._matchingAPCertificates);
                    }
                }
            } else if (temp._matchingAPCertificates != null)
                return false;
            if (this._matchingSigAlgList != null) {
                if (temp._matchingSigAlgList == null) return false;
                if (this._matchingSigAlgList != temp._matchingSigAlgList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._matchingSigAlgList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._matchingSigAlgList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._matchingSigAlgList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._matchingSigAlgList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._matchingSigAlgList.equals(temp._matchingSigAlgList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._matchingSigAlgList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._matchingSigAlgList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._matchingSigAlgList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._matchingSigAlgList);
                    }
                }
            } else if (temp._matchingSigAlgList != null)
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
     * Returns the value of field 'matchingAPCertificates'.
     * 
     * @return the value of field 'MatchingAPCertificates'.
     */
    public org.etsi.uri.TS102204.v1_1_2.MatchingAPCertificates getMatchingAPCertificates(
    ) {
        return this._matchingAPCertificates;
    }

    /**
     * Returns the value of field 'matchingMSSPCertificates'.
     * 
     * @return the value of field 'MatchingMSSPCertificates'.
     */
    public org.etsi.uri.TS102204.v1_1_2.MatchingMSSPCertificates getMatchingMSSPCertificates(
    ) {
        return this._matchingMSSPCertificates;
    }

    /**
     * Returns the value of field 'matchingSigAlgList'.
     * 
     * @return the value of field 'MatchingSigAlgList'.
     */
    public org.etsi.uri.TS102204.v1_1_2.MatchingSigAlgList getMatchingSigAlgList(
    ) {
        return this._matchingSigAlgList;
    }

    /**
     * Returns the value of field 'secureMethods'.
     * 
     * @return the value of field 'SecureMethods'.
     */
    public org.etsi.uri.TS102204.v1_1_2.SecureMethods getSecureMethods(
    ) {
        return this._secureMethods;
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
        if (_secureMethods != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_secureMethods)) {
           result = 37 * result + _secureMethods.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_secureMethods);
        }
        if (_matchingMSSPCertificates != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_matchingMSSPCertificates)) {
           result = 37 * result + _matchingMSSPCertificates.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_matchingMSSPCertificates);
        }
        if (_matchingAPCertificates != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_matchingAPCertificates)) {
           result = 37 * result + _matchingAPCertificates.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_matchingAPCertificates);
        }
        if (_matchingSigAlgList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_matchingSigAlgList)) {
           result = 37 * result + _matchingSigAlgList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_matchingSigAlgList);
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
     * Sets the value of field 'matchingAPCertificates'.
     * 
     * @param matchingAPCertificates the value of field
     * 'matchingAPCertificates'.
     */
    public void setMatchingAPCertificates(
            final org.etsi.uri.TS102204.v1_1_2.MatchingAPCertificates matchingAPCertificates) {
        this._matchingAPCertificates = matchingAPCertificates;
    }

    /**
     * Sets the value of field 'matchingMSSPCertificates'.
     * 
     * @param matchingMSSPCertificates the value of field
     * 'matchingMSSPCertificates'.
     */
    public void setMatchingMSSPCertificates(
            final org.etsi.uri.TS102204.v1_1_2.MatchingMSSPCertificates matchingMSSPCertificates) {
        this._matchingMSSPCertificates = matchingMSSPCertificates;
    }

    /**
     * Sets the value of field 'matchingSigAlgList'.
     * 
     * @param matchingSigAlgList the value of field
     * 'matchingSigAlgList'.
     */
    public void setMatchingSigAlgList(
            final org.etsi.uri.TS102204.v1_1_2.MatchingSigAlgList matchingSigAlgList) {
        this._matchingSigAlgList = matchingSigAlgList;
    }

    /**
     * Sets the value of field 'secureMethods'.
     * 
     * @param secureMethods the value of field 'secureMethods'.
     */
    public void setSecureMethods(
            final org.etsi.uri.TS102204.v1_1_2.SecureMethods secureMethods) {
        this._secureMethods = secureMethods;
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
