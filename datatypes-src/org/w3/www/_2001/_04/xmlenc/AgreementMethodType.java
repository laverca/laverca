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

package org.w3.www._2001._04.xmlenc;

/**
 * Class AgreementMethodType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AgreementMethodType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _algorithm.
     */
    private java.lang.String _algorithm;

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _KANonce.
     */
    private byte[] _KANonce;

    /**
     * Field _anyObject.
     */
    private java.util.List<java.lang.Object> _anyObject;

    /**
     * Field _originatorKeyInfo.
     */
    private org.w3.www._2001._04.xmlenc.OriginatorKeyInfo _originatorKeyInfo;

    /**
     * Field _recipientKeyInfo.
     */
    private org.w3.www._2001._04.xmlenc.RecipientKeyInfo _recipientKeyInfo;


      //----------------/
     //- Constructors -/
    //----------------/

    public AgreementMethodType() {
        super();
        setContent("");
        this._anyObject = new java.util.ArrayList<java.lang.Object>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAnyObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAnyObject(
            final java.lang.Object vAnyObject)
    throws java.lang.IndexOutOfBoundsException {
        this._anyObject.add(vAnyObject);
    }

    /**
     * 
     * 
     * @param index
     * @param vAnyObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAnyObject(
            final int index,
            final java.lang.Object vAnyObject)
    throws java.lang.IndexOutOfBoundsException {
        this._anyObject.add(index, vAnyObject);
    }

    /**
     * Method enumerateAnyObject.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.Object> enumerateAnyObject(
    ) {
        return java.util.Collections.enumeration(this._anyObject);
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

        if (obj instanceof AgreementMethodType) {

            AgreementMethodType temp = (AgreementMethodType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._algorithm != null) {
                if (temp._algorithm == null) return false;
                if (this._algorithm != temp._algorithm) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._algorithm);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._algorithm);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._algorithm); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._algorithm); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._algorithm.equals(temp._algorithm)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._algorithm);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._algorithm);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._algorithm);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._algorithm);
                    }
                }
            } else if (temp._algorithm != null)
                return false;
            if (this._content != null) {
                if (temp._content == null) return false;
                if (this._content != temp._content) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._content);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._content);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._content.equals(temp._content)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content);
                    }
                }
            } else if (temp._content != null)
                return false;
            if (this._KANonce != null) {
                if (temp._KANonce == null) return false;
                if (this._KANonce != temp._KANonce) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._KANonce);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._KANonce);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._KANonce); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._KANonce); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._KANonce, temp._KANonce)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._KANonce);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._KANonce);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._KANonce);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._KANonce);
                    }
                }
            } else if (temp._KANonce != null)
                return false;
            if (this._anyObject != null) {
                if (temp._anyObject == null) return false;
                if (this._anyObject != temp._anyObject) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._anyObject);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._anyObject);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._anyObject.equals(temp._anyObject)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject);
                    }
                }
            } else if (temp._anyObject != null)
                return false;
            if (this._originatorKeyInfo != null) {
                if (temp._originatorKeyInfo == null) return false;
                if (this._originatorKeyInfo != temp._originatorKeyInfo) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._originatorKeyInfo);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._originatorKeyInfo);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._originatorKeyInfo); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._originatorKeyInfo); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._originatorKeyInfo.equals(temp._originatorKeyInfo)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._originatorKeyInfo);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._originatorKeyInfo);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._originatorKeyInfo);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._originatorKeyInfo);
                    }
                }
            } else if (temp._originatorKeyInfo != null)
                return false;
            if (this._recipientKeyInfo != null) {
                if (temp._recipientKeyInfo == null) return false;
                if (this._recipientKeyInfo != temp._recipientKeyInfo) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._recipientKeyInfo);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._recipientKeyInfo);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recipientKeyInfo); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recipientKeyInfo); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._recipientKeyInfo.equals(temp._recipientKeyInfo)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recipientKeyInfo);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recipientKeyInfo);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recipientKeyInfo);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recipientKeyInfo);
                    }
                }
            } else if (temp._recipientKeyInfo != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'algorithm'.
     * 
     * @return the value of field 'Algorithm'.
     */
    public java.lang.String getAlgorithm(
    ) {
        return this._algorithm;
    }

    /**
     * Method getAnyObject.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.Object at the given index
     */
    public java.lang.Object getAnyObject(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._anyObject.size()) {
            throw new IndexOutOfBoundsException("getAnyObject: Index value '" + index + "' not in range [0.." + (this._anyObject.size() - 1) + "]");
        }

        return (java.lang.Object) _anyObject.get(index);
    }

    /**
     * Method getAnyObject.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.Object[] getAnyObject(
    ) {
        java.lang.Object[] array = new java.lang.Object[0];
        return (java.lang.Object[]) this._anyObject.toArray(array);
    }

    /**
     * Method getAnyObjectCount.
     * 
     * @return the size of this collection
     */
    public int getAnyObjectCount(
    ) {
        return this._anyObject.size();
    }

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return the value of field 'Content'.
     */
    public java.lang.String getContent(
    ) {
        return this._content;
    }

    /**
     * Returns the value of field 'KANonce'.
     * 
     * @return the value of field 'KANonce'.
     */
    public byte[] getKANonce(
    ) {
        return this._KANonce;
    }

    /**
     * Returns the value of field 'originatorKeyInfo'.
     * 
     * @return the value of field 'OriginatorKeyInfo'.
     */
    public org.w3.www._2001._04.xmlenc.OriginatorKeyInfo getOriginatorKeyInfo(
    ) {
        return this._originatorKeyInfo;
    }

    /**
     * Returns the value of field 'recipientKeyInfo'.
     * 
     * @return the value of field 'RecipientKeyInfo'.
     */
    public org.w3.www._2001._04.xmlenc.RecipientKeyInfo getRecipientKeyInfo(
    ) {
        return this._recipientKeyInfo;
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
        if (_algorithm != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_algorithm)) {
           result = 37 * result + _algorithm.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_algorithm);
        }
        if (_content != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_content)) {
           result = 37 * result + _content.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_content);
        }
        if (_KANonce != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_KANonce)) {
           result = 37 * result + _KANonce.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_KANonce);
        }
        if (_anyObject != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_anyObject)) {
           result = 37 * result + _anyObject.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_anyObject);
        }
        if (_originatorKeyInfo != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_originatorKeyInfo)) {
           result = 37 * result + _originatorKeyInfo.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_originatorKeyInfo);
        }
        if (_recipientKeyInfo != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_recipientKeyInfo)) {
           result = 37 * result + _recipientKeyInfo.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_recipientKeyInfo);
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
     * Method iterateAnyObject.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.Object> iterateAnyObject(
    ) {
        return this._anyObject.iterator();
    }

    /**
     */
    public void removeAllAnyObject(
    ) {
        this._anyObject.clear();
    }

    /**
     * Method removeAnyObject.
     * 
     * @param vAnyObject
     * @return true if the object was removed from the collection.
     */
    public boolean removeAnyObject(
            final java.lang.Object vAnyObject) {
        boolean removed = _anyObject.remove(vAnyObject);
        return removed;
    }

    /**
     * Method removeAnyObjectAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.Object removeAnyObjectAt(
            final int index) {
        java.lang.Object obj = this._anyObject.remove(index);
        return (java.lang.Object) obj;
    }

    /**
     * Sets the value of field 'algorithm'.
     * 
     * @param algorithm the value of field 'algorithm'.
     */
    public void setAlgorithm(
            final java.lang.String algorithm) {
        this._algorithm = algorithm;
    }

    /**
     * 
     * 
     * @param index
     * @param vAnyObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAnyObject(
            final int index,
            final java.lang.Object vAnyObject)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._anyObject.size()) {
            throw new IndexOutOfBoundsException("setAnyObject: Index value '" + index + "' not in range [0.." + (this._anyObject.size() - 1) + "]");
        }

        this._anyObject.set(index, vAnyObject);
    }

    /**
     * 
     * 
     * @param vAnyObjectArray
     */
    public void setAnyObject(
            final java.lang.Object[] vAnyObjectArray) {
        //-- copy array
        _anyObject.clear();

        for (int i = 0; i < vAnyObjectArray.length; i++) {
                this._anyObject.add(vAnyObjectArray[i]);
        }
    }

    /**
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(
            final java.lang.String content) {
        this._content = content;
    }

    /**
     * Sets the value of field 'KANonce'.
     * 
     * @param KANonce the value of field 'KANonce'.
     */
    public void setKANonce(
            final byte[] KANonce) {
        this._KANonce = KANonce;
    }

    /**
     * Sets the value of field 'originatorKeyInfo'.
     * 
     * @param originatorKeyInfo the value of field
     * 'originatorKeyInfo'.
     */
    public void setOriginatorKeyInfo(
            final org.w3.www._2001._04.xmlenc.OriginatorKeyInfo originatorKeyInfo) {
        this._originatorKeyInfo = originatorKeyInfo;
    }

    /**
     * Sets the value of field 'recipientKeyInfo'.
     * 
     * @param recipientKeyInfo the value of field 'recipientKeyInfo'
     */
    public void setRecipientKeyInfo(
            final org.w3.www._2001._04.xmlenc.RecipientKeyInfo recipientKeyInfo) {
        this._recipientKeyInfo = recipientKeyInfo;
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
