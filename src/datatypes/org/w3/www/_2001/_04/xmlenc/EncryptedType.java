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

package org.w3.www._2001._04.xmlenc;

/**
 * Class EncryptedType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class EncryptedType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private java.lang.String _id;

    /**
     * Field _type.
     */
    private java.lang.String _type;

    /**
     * Field _mimeType.
     */
    private java.lang.String _mimeType;

    /**
     * Field _encoding.
     */
    private java.lang.String _encoding;

    /**
     * Field _encryptionMethod.
     */
    private org.w3.www._2001._04.xmlenc.EncryptionMethod _encryptionMethod;

    /**
     * Field _keyInfo.
     */
    private org.w3.www._2000._09.xmldsig.KeyInfo _keyInfo;

    /**
     * Field _cipherData.
     */
    private org.w3.www._2001._04.xmlenc.CipherData _cipherData;

    /**
     * Field _encryptionProperties.
     */
    private org.w3.www._2001._04.xmlenc.EncryptionProperties _encryptionProperties;


      //----------------/
     //- Constructors -/
    //----------------/

    public EncryptedType() {
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

        if (obj instanceof EncryptedType) {

            EncryptedType temp = (EncryptedType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._id != null) {
                if (temp._id == null) return false;
                if (this._id != temp._id) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._id);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._id);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._id); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._id); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._id.equals(temp._id)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._id);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._id);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._id);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._id);
                    }
                }
            } else if (temp._id != null)
                return false;
            if (this._type != null) {
                if (temp._type == null) return false;
                if (this._type != temp._type) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._type);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._type);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._type); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._type); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._type.equals(temp._type)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._type);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._type);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._type);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._type);
                    }
                }
            } else if (temp._type != null)
                return false;
            if (this._mimeType != null) {
                if (temp._mimeType == null) return false;
                if (this._mimeType != temp._mimeType) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._mimeType);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._mimeType);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mimeType); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mimeType); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._mimeType.equals(temp._mimeType)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mimeType);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mimeType);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mimeType);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mimeType);
                    }
                }
            } else if (temp._mimeType != null)
                return false;
            if (this._encoding != null) {
                if (temp._encoding == null) return false;
                if (this._encoding != temp._encoding) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._encoding);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._encoding);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encoding); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encoding); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._encoding.equals(temp._encoding)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encoding);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encoding);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encoding);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encoding);
                    }
                }
            } else if (temp._encoding != null)
                return false;
            if (this._encryptionMethod != null) {
                if (temp._encryptionMethod == null) return false;
                if (this._encryptionMethod != temp._encryptionMethod) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._encryptionMethod);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._encryptionMethod);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptionMethod); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptionMethod); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._encryptionMethod.equals(temp._encryptionMethod)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptionMethod);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptionMethod);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptionMethod);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptionMethod);
                    }
                }
            } else if (temp._encryptionMethod != null)
                return false;
            if (this._keyInfo != null) {
                if (temp._keyInfo == null) return false;
                if (this._keyInfo != temp._keyInfo) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._keyInfo);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._keyInfo);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._keyInfo); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._keyInfo); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._keyInfo.equals(temp._keyInfo)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._keyInfo);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._keyInfo);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._keyInfo);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._keyInfo);
                    }
                }
            } else if (temp._keyInfo != null)
                return false;
            if (this._cipherData != null) {
                if (temp._cipherData == null) return false;
                if (this._cipherData != temp._cipherData) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._cipherData);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._cipherData);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cipherData); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cipherData); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._cipherData.equals(temp._cipherData)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cipherData);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cipherData);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._cipherData);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._cipherData);
                    }
                }
            } else if (temp._cipherData != null)
                return false;
            if (this._encryptionProperties != null) {
                if (temp._encryptionProperties == null) return false;
                if (this._encryptionProperties != temp._encryptionProperties) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._encryptionProperties);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._encryptionProperties);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptionProperties); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptionProperties); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._encryptionProperties.equals(temp._encryptionProperties)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptionProperties);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptionProperties);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptionProperties);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptionProperties);
                    }
                }
            } else if (temp._encryptionProperties != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'cipherData'.
     * 
     * @return the value of field 'CipherData'.
     */
    public org.w3.www._2001._04.xmlenc.CipherData getCipherData(
    ) {
        return this._cipherData;
    }

    /**
     * Returns the value of field 'encoding'.
     * 
     * @return the value of field 'Encoding'.
     */
    public java.lang.String getEncoding(
    ) {
        return this._encoding;
    }

    /**
     * Returns the value of field 'encryptionMethod'.
     * 
     * @return the value of field 'EncryptionMethod'.
     */
    public org.w3.www._2001._04.xmlenc.EncryptionMethod getEncryptionMethod(
    ) {
        return this._encryptionMethod;
    }

    /**
     * Returns the value of field 'encryptionProperties'.
     * 
     * @return the value of field 'EncryptionProperties'.
     */
    public org.w3.www._2001._04.xmlenc.EncryptionProperties getEncryptionProperties(
    ) {
        return this._encryptionProperties;
    }

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public java.lang.String getId(
    ) {
        return this._id;
    }

    /**
     * Returns the value of field 'keyInfo'.
     * 
     * @return the value of field 'KeyInfo'.
     */
    public org.w3.www._2000._09.xmldsig.KeyInfo getKeyInfo(
    ) {
        return this._keyInfo;
    }

    /**
     * Returns the value of field 'mimeType'.
     * 
     * @return the value of field 'MimeType'.
     */
    public java.lang.String getMimeType(
    ) {
        return this._mimeType;
    }

    /**
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'Type'.
     */
    public java.lang.String getType(
    ) {
        return this._type;
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
        if (_id != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_id)) {
           result = 37 * result + _id.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_id);
        }
        if (_type != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_type)) {
           result = 37 * result + _type.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_type);
        }
        if (_mimeType != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_mimeType)) {
           result = 37 * result + _mimeType.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_mimeType);
        }
        if (_encoding != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_encoding)) {
           result = 37 * result + _encoding.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_encoding);
        }
        if (_encryptionMethod != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_encryptionMethod)) {
           result = 37 * result + _encryptionMethod.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_encryptionMethod);
        }
        if (_keyInfo != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_keyInfo)) {
           result = 37 * result + _keyInfo.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_keyInfo);
        }
        if (_cipherData != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_cipherData)) {
           result = 37 * result + _cipherData.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_cipherData);
        }
        if (_encryptionProperties != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_encryptionProperties)) {
           result = 37 * result + _encryptionProperties.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_encryptionProperties);
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
     * Sets the value of field 'cipherData'.
     * 
     * @param cipherData the value of field 'cipherData'.
     */
    public void setCipherData(
            final org.w3.www._2001._04.xmlenc.CipherData cipherData) {
        this._cipherData = cipherData;
    }

    /**
     * Sets the value of field 'encoding'.
     * 
     * @param encoding the value of field 'encoding'.
     */
    public void setEncoding(
            final java.lang.String encoding) {
        this._encoding = encoding;
    }

    /**
     * Sets the value of field 'encryptionMethod'.
     * 
     * @param encryptionMethod the value of field 'encryptionMethod'
     */
    public void setEncryptionMethod(
            final org.w3.www._2001._04.xmlenc.EncryptionMethod encryptionMethod) {
        this._encryptionMethod = encryptionMethod;
    }

    /**
     * Sets the value of field 'encryptionProperties'.
     * 
     * @param encryptionProperties the value of field
     * 'encryptionProperties'.
     */
    public void setEncryptionProperties(
            final org.w3.www._2001._04.xmlenc.EncryptionProperties encryptionProperties) {
        this._encryptionProperties = encryptionProperties;
    }

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(
            final java.lang.String id) {
        this._id = id;
    }

    /**
     * Sets the value of field 'keyInfo'.
     * 
     * @param keyInfo the value of field 'keyInfo'.
     */
    public void setKeyInfo(
            final org.w3.www._2000._09.xmldsig.KeyInfo keyInfo) {
        this._keyInfo = keyInfo;
    }

    /**
     * Sets the value of field 'mimeType'.
     * 
     * @param mimeType the value of field 'mimeType'.
     */
    public void setMimeType(
            final java.lang.String mimeType) {
        this._mimeType = mimeType;
    }

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(
            final java.lang.String type) {
        this._type = type;
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
