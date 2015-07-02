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

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class EncryptedElementType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class EncryptedElementType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _encryptedData.
     */
    private org.w3.www._2001._04.xmlenc.EncryptedData _encryptedData;

    /**
     * Field _encryptedKeyList.
     */
    private java.util.List<org.w3.www._2001._04.xmlenc.EncryptedKey> _encryptedKeyList;


      //----------------/
     //- Constructors -/
    //----------------/

    public EncryptedElementType() {
        super();
        this._encryptedKeyList = new java.util.ArrayList<org.w3.www._2001._04.xmlenc.EncryptedKey>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vEncryptedKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addEncryptedKey(
            final org.w3.www._2001._04.xmlenc.EncryptedKey vEncryptedKey)
    throws java.lang.IndexOutOfBoundsException {
        this._encryptedKeyList.add(vEncryptedKey);
    }

    /**
     * 
     * 
     * @param index
     * @param vEncryptedKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addEncryptedKey(
            final int index,
            final org.w3.www._2001._04.xmlenc.EncryptedKey vEncryptedKey)
    throws java.lang.IndexOutOfBoundsException {
        this._encryptedKeyList.add(index, vEncryptedKey);
    }

    /**
     * Method enumerateEncryptedKey.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2001._04.xmlenc.EncryptedKey> enumerateEncryptedKey(
    ) {
        return java.util.Collections.enumeration(this._encryptedKeyList);
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

        if (obj instanceof EncryptedElementType) {

            EncryptedElementType temp = (EncryptedElementType)obj;
            boolean thcycle;
            boolean tmcycle;
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
            if (this._encryptedKeyList != null) {
                if (temp._encryptedKeyList == null) return false;
                if (this._encryptedKeyList != temp._encryptedKeyList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._encryptedKeyList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._encryptedKeyList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptedKeyList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptedKeyList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._encryptedKeyList.equals(temp._encryptedKeyList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptedKeyList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptedKeyList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptedKeyList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptedKeyList);
                    }
                }
            } else if (temp._encryptedKeyList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'encryptedData'.
     * 
     * @return the value of field 'EncryptedData'.
     */
    public org.w3.www._2001._04.xmlenc.EncryptedData getEncryptedData(
    ) {
        return this._encryptedData;
    }

    /**
     * Method getEncryptedKey.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.w3.www._2001._04.xmlenc.EncryptedKey at the given index
     */
    public org.w3.www._2001._04.xmlenc.EncryptedKey getEncryptedKey(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._encryptedKeyList.size()) {
            throw new IndexOutOfBoundsException("getEncryptedKey: Index value '" + index + "' not in range [0.." + (this._encryptedKeyList.size() - 1) + "]");
        }

        return (org.w3.www._2001._04.xmlenc.EncryptedKey) _encryptedKeyList.get(index);
    }

    /**
     * Method getEncryptedKey.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2001._04.xmlenc.EncryptedKey[] getEncryptedKey(
    ) {
        org.w3.www._2001._04.xmlenc.EncryptedKey[] array = new org.w3.www._2001._04.xmlenc.EncryptedKey[0];
        return (org.w3.www._2001._04.xmlenc.EncryptedKey[]) this._encryptedKeyList.toArray(array);
    }

    /**
     * Method getEncryptedKeyCount.
     * 
     * @return the size of this collection
     */
    public int getEncryptedKeyCount(
    ) {
        return this._encryptedKeyList.size();
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
        if (_encryptedData != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_encryptedData)) {
           result = 37 * result + _encryptedData.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_encryptedData);
        }
        if (_encryptedKeyList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_encryptedKeyList)) {
           result = 37 * result + _encryptedKeyList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_encryptedKeyList);
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
     * Method iterateEncryptedKey.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2001._04.xmlenc.EncryptedKey> iterateEncryptedKey(
    ) {
        return this._encryptedKeyList.iterator();
    }

    /**
     */
    public void removeAllEncryptedKey(
    ) {
        this._encryptedKeyList.clear();
    }

    /**
     * Method removeEncryptedKey.
     * 
     * @param vEncryptedKey
     * @return true if the object was removed from the collection.
     */
    public boolean removeEncryptedKey(
            final org.w3.www._2001._04.xmlenc.EncryptedKey vEncryptedKey) {
        boolean removed = _encryptedKeyList.remove(vEncryptedKey);
        return removed;
    }

    /**
     * Method removeEncryptedKeyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2001._04.xmlenc.EncryptedKey removeEncryptedKeyAt(
            final int index) {
        java.lang.Object obj = this._encryptedKeyList.remove(index);
        return (org.w3.www._2001._04.xmlenc.EncryptedKey) obj;
    }

    /**
     * Sets the value of field 'encryptedData'.
     * 
     * @param encryptedData the value of field 'encryptedData'.
     */
    public void setEncryptedData(
            final org.w3.www._2001._04.xmlenc.EncryptedData encryptedData) {
        this._encryptedData = encryptedData;
    }

    /**
     * 
     * 
     * @param index
     * @param vEncryptedKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setEncryptedKey(
            final int index,
            final org.w3.www._2001._04.xmlenc.EncryptedKey vEncryptedKey)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._encryptedKeyList.size()) {
            throw new IndexOutOfBoundsException("setEncryptedKey: Index value '" + index + "' not in range [0.." + (this._encryptedKeyList.size() - 1) + "]");
        }

        this._encryptedKeyList.set(index, vEncryptedKey);
    }

    /**
     * 
     * 
     * @param vEncryptedKeyArray
     */
    public void setEncryptedKey(
            final org.w3.www._2001._04.xmlenc.EncryptedKey[] vEncryptedKeyArray) {
        //-- copy array
        _encryptedKeyList.clear();

        for (int i = 0; i < vEncryptedKeyArray.length; i++) {
                this._encryptedKeyList.add(vEncryptedKeyArray[i]);
        }
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
