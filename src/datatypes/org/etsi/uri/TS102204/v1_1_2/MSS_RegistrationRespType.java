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
 * Class MSS_RegistrationRespType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MSS_RegistrationRespType extends org.etsi.uri.TS102204.v1_1_2.MessageAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _status.
     */
    private org.etsi.uri.TS102204.v1_1_2.Status _status;

    /**
     * Field _encryptedData.
     */
    private org.etsi.uri.TS102204.v1_1_2.EncryptedData _encryptedData;

    /**
     * Field _certificateURI.
     */
    private java.lang.String _certificateURI;

    /**
     * Field _x509Certificate.
     */
    private byte[] _x509Certificate;

    /**
     * Field _publicKey.
     */
    private byte[] _publicKey;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSS_RegistrationRespType() {
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

        if (obj instanceof MSS_RegistrationRespType) {

            MSS_RegistrationRespType temp = (MSS_RegistrationRespType)obj;
            boolean thcycle;
            boolean tmcycle;
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
            if (this._encryptedData != null) {
                if (temp._encryptedData == null) return false;
                if (this._encryptedData != temp._encryptedData) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._encryptedData);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._encryptedData);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptedData); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptedData); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._encryptedData.equals(temp._encryptedData)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptedData);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptedData);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptedData);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptedData);
                    }
                }
            } else if (temp._encryptedData != null)
                return false;
            if (this._certificateURI != null) {
                if (temp._certificateURI == null) return false;
                if (this._certificateURI != temp._certificateURI) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._certificateURI);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._certificateURI);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateURI); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateURI); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._certificateURI.equals(temp._certificateURI)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateURI);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateURI);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateURI);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateURI);
                    }
                }
            } else if (temp._certificateURI != null)
                return false;
            if (this._x509Certificate != null) {
                if (temp._x509Certificate == null) return false;
                if (this._x509Certificate != temp._x509Certificate) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._x509Certificate);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._x509Certificate);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509Certificate); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509Certificate); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._x509Certificate, temp._x509Certificate)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509Certificate);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509Certificate);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509Certificate);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509Certificate);
                    }
                }
            } else if (temp._x509Certificate != null)
                return false;
            if (this._publicKey != null) {
                if (temp._publicKey == null) return false;
                if (this._publicKey != temp._publicKey) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._publicKey);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._publicKey);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._publicKey); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._publicKey); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._publicKey, temp._publicKey)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._publicKey);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._publicKey);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._publicKey);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._publicKey);
                    }
                }
            } else if (temp._publicKey != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'certificateURI'.
     * 
     * @return the value of field 'CertificateURI'.
     */
    public java.lang.String getCertificateURI(
    ) {
        return this._certificateURI;
    }

    /**
     * Returns the value of field 'encryptedData'.
     * 
     * @return the value of field 'EncryptedData'.
     */
    public org.etsi.uri.TS102204.v1_1_2.EncryptedData getEncryptedData(
    ) {
        return this._encryptedData;
    }

    /**
     * Returns the value of field 'publicKey'.
     * 
     * @return the value of field 'PublicKey'.
     */
    public byte[] getPublicKey(
    ) {
        return this._publicKey;
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
     * Returns the value of field 'x509Certificate'.
     * 
     * @return the value of field 'X509Certificate'.
     */
    public byte[] getX509Certificate(
    ) {
        return this._x509Certificate;
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
        if (_status != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_status)) {
           result = 37 * result + _status.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_status);
        }
        if (_encryptedData != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_encryptedData)) {
           result = 37 * result + _encryptedData.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_encryptedData);
        }
        if (_certificateURI != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_certificateURI)) {
           result = 37 * result + _certificateURI.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_certificateURI);
        }
        if (_x509Certificate != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_x509Certificate)) {
           result = 37 * result + _x509Certificate.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_x509Certificate);
        }
        if (_publicKey != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_publicKey)) {
           result = 37 * result + _publicKey.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_publicKey);
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
     * Sets the value of field 'certificateURI'.
     * 
     * @param certificateURI the value of field 'certificateURI'.
     */
    public void setCertificateURI(
            final java.lang.String certificateURI) {
        this._certificateURI = certificateURI;
    }

    /**
     * Sets the value of field 'encryptedData'.
     * 
     * @param encryptedData the value of field 'encryptedData'.
     */
    public void setEncryptedData(
            final org.etsi.uri.TS102204.v1_1_2.EncryptedData encryptedData) {
        this._encryptedData = encryptedData;
    }

    /**
     * Sets the value of field 'publicKey'.
     * 
     * @param publicKey the value of field 'publicKey'.
     */
    public void setPublicKey(
            final byte[] publicKey) {
        this._publicKey = publicKey;
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
     * Sets the value of field 'x509Certificate'.
     * 
     * @param x509Certificate the value of field 'x509Certificate'.
     */
    public void setX509Certificate(
            final byte[] x509Certificate) {
        this._x509Certificate = x509Certificate;
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
