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

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class KeyInfoConfirmationDataType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class KeyInfoConfirmationDataType extends oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmationDataType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _keyInfoList.
     */
    private java.util.List<org.w3.www._2000._09.xmldsig.KeyInfo> _keyInfoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public KeyInfoConfirmationDataType() {
        super();
        this._keyInfoList = new java.util.ArrayList<org.w3.www._2000._09.xmldsig.KeyInfo>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vKeyInfo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addKeyInfo(
            final org.w3.www._2000._09.xmldsig.KeyInfo vKeyInfo)
    throws java.lang.IndexOutOfBoundsException {
        this._keyInfoList.add(vKeyInfo);
    }

    /**
     * 
     * 
     * @param index
     * @param vKeyInfo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addKeyInfo(
            final int index,
            final org.w3.www._2000._09.xmldsig.KeyInfo vKeyInfo)
    throws java.lang.IndexOutOfBoundsException {
        this._keyInfoList.add(index, vKeyInfo);
    }

    /**
     * Method enumerateKeyInfo.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2000._09.xmldsig.KeyInfo> enumerateKeyInfo(
    ) {
        return java.util.Collections.enumeration(this._keyInfoList);
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

        if (obj instanceof KeyInfoConfirmationDataType) {

            KeyInfoConfirmationDataType temp = (KeyInfoConfirmationDataType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._keyInfoList != null) {
                if (temp._keyInfoList == null) return false;
                if (this._keyInfoList != temp._keyInfoList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._keyInfoList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._keyInfoList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._keyInfoList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._keyInfoList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._keyInfoList.equals(temp._keyInfoList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._keyInfoList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._keyInfoList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._keyInfoList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._keyInfoList);
                    }
                }
            } else if (temp._keyInfoList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getKeyInfo.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.w3.www._2000._09.xmldsig.KeyInfo at the given index
     */
    public org.w3.www._2000._09.xmldsig.KeyInfo getKeyInfo(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._keyInfoList.size()) {
            throw new IndexOutOfBoundsException("getKeyInfo: Index value '" + index + "' not in range [0.." + (this._keyInfoList.size() - 1) + "]");
        }

        return (org.w3.www._2000._09.xmldsig.KeyInfo) _keyInfoList.get(index);
    }

    /**
     * Method getKeyInfo.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2000._09.xmldsig.KeyInfo[] getKeyInfo(
    ) {
        org.w3.www._2000._09.xmldsig.KeyInfo[] array = new org.w3.www._2000._09.xmldsig.KeyInfo[0];
        return (org.w3.www._2000._09.xmldsig.KeyInfo[]) this._keyInfoList.toArray(array);
    }

    /**
     * Method getKeyInfoCount.
     * 
     * @return the size of this collection
     */
    public int getKeyInfoCount(
    ) {
        return this._keyInfoList.size();
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
        if (_keyInfoList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_keyInfoList)) {
           result = 37 * result + _keyInfoList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_keyInfoList);
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
     * Method iterateKeyInfo.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2000._09.xmldsig.KeyInfo> iterateKeyInfo(
    ) {
        return this._keyInfoList.iterator();
    }

    /**
     */
    public void removeAllKeyInfo(
    ) {
        this._keyInfoList.clear();
    }

    /**
     * Method removeKeyInfo.
     * 
     * @param vKeyInfo
     * @return true if the object was removed from the collection.
     */
    public boolean removeKeyInfo(
            final org.w3.www._2000._09.xmldsig.KeyInfo vKeyInfo) {
        boolean removed = _keyInfoList.remove(vKeyInfo);
        return removed;
    }

    /**
     * Method removeKeyInfoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2000._09.xmldsig.KeyInfo removeKeyInfoAt(
            final int index) {
        java.lang.Object obj = this._keyInfoList.remove(index);
        return (org.w3.www._2000._09.xmldsig.KeyInfo) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vKeyInfo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setKeyInfo(
            final int index,
            final org.w3.www._2000._09.xmldsig.KeyInfo vKeyInfo)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._keyInfoList.size()) {
            throw new IndexOutOfBoundsException("setKeyInfo: Index value '" + index + "' not in range [0.." + (this._keyInfoList.size() - 1) + "]");
        }

        this._keyInfoList.set(index, vKeyInfo);
    }

    /**
     * 
     * 
     * @param vKeyInfoArray
     */
    public void setKeyInfo(
            final org.w3.www._2000._09.xmldsig.KeyInfo[] vKeyInfoArray) {
        //-- copy array
        _keyInfoList.clear();

        for (int i = 0; i < vKeyInfoArray.length; i++) {
                this._keyInfoList.add(vKeyInfoArray[i]);
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
