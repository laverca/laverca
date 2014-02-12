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

package org.w3.www._2000._09.xmldsig;

/**
 * Class SignatureType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class SignatureType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private java.lang.String _id;

    /**
     * Field _signedInfo.
     */
    private org.w3.www._2000._09.xmldsig.SignedInfo _signedInfo;

    /**
     * Field _signatureValue.
     */
    private org.w3.www._2000._09.xmldsig.SignatureValue _signatureValue;

    /**
     * Field _keyInfo.
     */
    private org.w3.www._2000._09.xmldsig.KeyInfo _keyInfo;

    /**
     * Field _objectList.
     */
    private java.util.List<org.w3.www._2000._09.xmldsig.Object> _objectList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SignatureType() {
        super();
        this._objectList = new java.util.ArrayList<org.w3.www._2000._09.xmldsig.Object>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addObject(
            final org.w3.www._2000._09.xmldsig.Object vObject)
    throws java.lang.IndexOutOfBoundsException {
        this._objectList.add(vObject);
    }

    /**
     * 
     * 
     * @param index
     * @param vObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addObject(
            final int index,
            final org.w3.www._2000._09.xmldsig.Object vObject)
    throws java.lang.IndexOutOfBoundsException {
        this._objectList.add(index, vObject);
    }

    /**
     * Method enumerateObject.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2000._09.xmldsig.Object> enumerateObject(
    ) {
        return java.util.Collections.enumeration(this._objectList);
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

        if (obj instanceof SignatureType) {

            SignatureType temp = (SignatureType)obj;
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
            if (this._signedInfo != null) {
                if (temp._signedInfo == null) return false;
                if (this._signedInfo != temp._signedInfo) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signedInfo);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signedInfo);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signedInfo); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signedInfo); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signedInfo.equals(temp._signedInfo)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signedInfo);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signedInfo);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signedInfo);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signedInfo);
                    }
                }
            } else if (temp._signedInfo != null)
                return false;
            if (this._signatureValue != null) {
                if (temp._signatureValue == null) return false;
                if (this._signatureValue != temp._signatureValue) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signatureValue);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signatureValue);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureValue); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureValue); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signatureValue.equals(temp._signatureValue)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureValue);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureValue);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureValue);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureValue);
                    }
                }
            } else if (temp._signatureValue != null)
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
            if (this._objectList != null) {
                if (temp._objectList == null) return false;
                if (this._objectList != temp._objectList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._objectList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._objectList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._objectList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._objectList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._objectList.equals(temp._objectList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._objectList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._objectList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._objectList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._objectList);
                    }
                }
            } else if (temp._objectList != null)
                return false;
            return true;
        }
        return false;
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
     * Method getObject.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the org.w3.www._2000._09.xmldsig.Object
     * at the given index
     */
    public org.w3.www._2000._09.xmldsig.Object getObject(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._objectList.size()) {
            throw new IndexOutOfBoundsException("getObject: Index value '" + index + "' not in range [0.." + (this._objectList.size() - 1) + "]");
        }

        return (org.w3.www._2000._09.xmldsig.Object) _objectList.get(index);
    }

    /**
     * Method getObject.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2000._09.xmldsig.Object[] getObject(
    ) {
        org.w3.www._2000._09.xmldsig.Object[] array = new org.w3.www._2000._09.xmldsig.Object[0];
        return (org.w3.www._2000._09.xmldsig.Object[]) this._objectList.toArray(array);
    }

    /**
     * Method getObjectCount.
     * 
     * @return the size of this collection
     */
    public int getObjectCount(
    ) {
        return this._objectList.size();
    }

    /**
     * Returns the value of field 'signatureValue'.
     * 
     * @return the value of field 'SignatureValue'.
     */
    public org.w3.www._2000._09.xmldsig.SignatureValue getSignatureValue(
    ) {
        return this._signatureValue;
    }

    /**
     * Returns the value of field 'signedInfo'.
     * 
     * @return the value of field 'SignedInfo'.
     */
    public org.w3.www._2000._09.xmldsig.SignedInfo getSignedInfo(
    ) {
        return this._signedInfo;
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
        if (_signedInfo != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signedInfo)) {
           result = 37 * result + _signedInfo.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signedInfo);
        }
        if (_signatureValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signatureValue)) {
           result = 37 * result + _signatureValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signatureValue);
        }
        if (_keyInfo != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_keyInfo)) {
           result = 37 * result + _keyInfo.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_keyInfo);
        }
        if (_objectList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_objectList)) {
           result = 37 * result + _objectList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_objectList);
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
     * Method iterateObject.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2000._09.xmldsig.Object> iterateObject(
    ) {
        return this._objectList.iterator();
    }

    /**
     */
    public void removeAllObject(
    ) {
        this._objectList.clear();
    }

    /**
     * Method removeObject.
     * 
     * @param vObject
     * @return true if the object was removed from the collection.
     */
    public boolean removeObject(
            final org.w3.www._2000._09.xmldsig.Object vObject) {
        boolean removed = _objectList.remove(vObject);
        return removed;
    }

    /**
     * Method removeObjectAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2000._09.xmldsig.Object removeObjectAt(
            final int index) {
        java.lang.Object obj = this._objectList.remove(index);
        return (org.w3.www._2000._09.xmldsig.Object) obj;
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
     * 
     * 
     * @param index
     * @param vObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setObject(
            final int index,
            final org.w3.www._2000._09.xmldsig.Object vObject)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._objectList.size()) {
            throw new IndexOutOfBoundsException("setObject: Index value '" + index + "' not in range [0.." + (this._objectList.size() - 1) + "]");
        }

        this._objectList.set(index, vObject);
    }

    /**
     * 
     * 
     * @param vObjectArray
     */
    public void setObject(
            final org.w3.www._2000._09.xmldsig.Object[] vObjectArray) {
        //-- copy array
        _objectList.clear();

        for (int i = 0; i < vObjectArray.length; i++) {
                this._objectList.add(vObjectArray[i]);
        }
    }

    /**
     * Sets the value of field 'signatureValue'.
     * 
     * @param signatureValue the value of field 'signatureValue'.
     */
    public void setSignatureValue(
            final org.w3.www._2000._09.xmldsig.SignatureValue signatureValue) {
        this._signatureValue = signatureValue;
    }

    /**
     * Sets the value of field 'signedInfo'.
     * 
     * @param signedInfo the value of field 'signedInfo'.
     */
    public void setSignedInfo(
            final org.w3.www._2000._09.xmldsig.SignedInfo signedInfo) {
        this._signedInfo = signedInfo;
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
