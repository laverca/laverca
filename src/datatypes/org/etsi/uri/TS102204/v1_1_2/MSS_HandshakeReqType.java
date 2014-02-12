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
 * Class MSS_HandshakeReqType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MSS_HandshakeReqType extends org.etsi.uri.TS102204.v1_1_2.MessageAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _secureMethods.
     */
    private org.etsi.uri.TS102204.v1_1_2.SecureMethods _secureMethods;

    /**
     * Field _certificates.
     */
    private org.etsi.uri.TS102204.v1_1_2.Certificates _certificates;

    /**
     * Field _rootCAs.
     */
    private org.etsi.uri.TS102204.v1_1_2.RootCAs _rootCAs;

    /**
     * Field _signatureAlgList.
     */
    private org.etsi.uri.TS102204.v1_1_2.SignatureAlgList _signatureAlgList;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSS_HandshakeReqType() {
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

        if (obj instanceof MSS_HandshakeReqType) {

            MSS_HandshakeReqType temp = (MSS_HandshakeReqType)obj;
            boolean thcycle;
            boolean tmcycle;
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
            if (this._certificates != null) {
                if (temp._certificates == null) return false;
                if (this._certificates != temp._certificates) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._certificates);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._certificates);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificates); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificates); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._certificates.equals(temp._certificates)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificates);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificates);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificates);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificates);
                    }
                }
            } else if (temp._certificates != null)
                return false;
            if (this._rootCAs != null) {
                if (temp._rootCAs == null) return false;
                if (this._rootCAs != temp._rootCAs) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._rootCAs);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._rootCAs);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rootCAs); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rootCAs); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._rootCAs.equals(temp._rootCAs)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rootCAs);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rootCAs);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rootCAs);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rootCAs);
                    }
                }
            } else if (temp._rootCAs != null)
                return false;
            if (this._signatureAlgList != null) {
                if (temp._signatureAlgList == null) return false;
                if (this._signatureAlgList != temp._signatureAlgList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signatureAlgList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signatureAlgList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureAlgList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureAlgList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signatureAlgList.equals(temp._signatureAlgList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureAlgList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureAlgList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureAlgList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureAlgList);
                    }
                }
            } else if (temp._signatureAlgList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'certificates'.
     * 
     * @return the value of field 'Certificates'.
     */
    public org.etsi.uri.TS102204.v1_1_2.Certificates getCertificates(
    ) {
        return this._certificates;
    }

    /**
     * Returns the value of field 'rootCAs'.
     * 
     * @return the value of field 'RootCAs'.
     */
    public org.etsi.uri.TS102204.v1_1_2.RootCAs getRootCAs(
    ) {
        return this._rootCAs;
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
     * Returns the value of field 'signatureAlgList'.
     * 
     * @return the value of field 'SignatureAlgList'.
     */
    public org.etsi.uri.TS102204.v1_1_2.SignatureAlgList getSignatureAlgList(
    ) {
        return this._signatureAlgList;
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
        if (_secureMethods != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_secureMethods)) {
           result = 37 * result + _secureMethods.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_secureMethods);
        }
        if (_certificates != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_certificates)) {
           result = 37 * result + _certificates.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_certificates);
        }
        if (_rootCAs != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_rootCAs)) {
           result = 37 * result + _rootCAs.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_rootCAs);
        }
        if (_signatureAlgList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signatureAlgList)) {
           result = 37 * result + _signatureAlgList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signatureAlgList);
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
     * Sets the value of field 'certificates'.
     * 
     * @param certificates the value of field 'certificates'.
     */
    public void setCertificates(
            final org.etsi.uri.TS102204.v1_1_2.Certificates certificates) {
        this._certificates = certificates;
    }

    /**
     * Sets the value of field 'rootCAs'.
     * 
     * @param rootCAs the value of field 'rootCAs'.
     */
    public void setRootCAs(
            final org.etsi.uri.TS102204.v1_1_2.RootCAs rootCAs) {
        this._rootCAs = rootCAs;
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
     * Sets the value of field 'signatureAlgList'.
     * 
     * @param signatureAlgList the value of field 'signatureAlgList'
     */
    public void setSignatureAlgList(
            final org.etsi.uri.TS102204.v1_1_2.SignatureAlgList signatureAlgList) {
        this._signatureAlgList = signatureAlgList;
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
