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

package org.w3.www._2001._04.xmlenc;

/**
 * Class EncryptionPropertiesType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class EncryptionPropertiesType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private java.lang.String _id;

    /**
     * Field _encryptionPropertyList.
     */
    private java.util.List<org.w3.www._2001._04.xmlenc.EncryptionProperty> _encryptionPropertyList;


      //----------------/
     //- Constructors -/
    //----------------/

    public EncryptionPropertiesType() {
        super();
        this._encryptionPropertyList = new java.util.ArrayList<org.w3.www._2001._04.xmlenc.EncryptionProperty>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vEncryptionProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addEncryptionProperty(
            final org.w3.www._2001._04.xmlenc.EncryptionProperty vEncryptionProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._encryptionPropertyList.add(vEncryptionProperty);
    }

    /**
     * 
     * 
     * @param index
     * @param vEncryptionProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addEncryptionProperty(
            final int index,
            final org.w3.www._2001._04.xmlenc.EncryptionProperty vEncryptionProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._encryptionPropertyList.add(index, vEncryptionProperty);
    }

    /**
     * Method enumerateEncryptionProperty.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2001._04.xmlenc.EncryptionProperty> enumerateEncryptionProperty(
    ) {
        return java.util.Collections.enumeration(this._encryptionPropertyList);
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

        if (obj instanceof EncryptionPropertiesType) {

            EncryptionPropertiesType temp = (EncryptionPropertiesType)obj;
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
            if (this._encryptionPropertyList != null) {
                if (temp._encryptionPropertyList == null) return false;
                if (this._encryptionPropertyList != temp._encryptionPropertyList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._encryptionPropertyList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._encryptionPropertyList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptionPropertyList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptionPropertyList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._encryptionPropertyList.equals(temp._encryptionPropertyList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptionPropertyList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptionPropertyList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encryptionPropertyList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encryptionPropertyList);
                    }
                }
            } else if (temp._encryptionPropertyList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getEncryptionProperty.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.w3.www._2001._04.xmlenc.EncryptionProperty at the given
     * index
     */
    public org.w3.www._2001._04.xmlenc.EncryptionProperty getEncryptionProperty(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._encryptionPropertyList.size()) {
            throw new IndexOutOfBoundsException("getEncryptionProperty: Index value '" + index + "' not in range [0.." + (this._encryptionPropertyList.size() - 1) + "]");
        }

        return (org.w3.www._2001._04.xmlenc.EncryptionProperty) _encryptionPropertyList.get(index);
    }

    /**
     * Method getEncryptionProperty.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2001._04.xmlenc.EncryptionProperty[] getEncryptionProperty(
    ) {
        org.w3.www._2001._04.xmlenc.EncryptionProperty[] array = new org.w3.www._2001._04.xmlenc.EncryptionProperty[0];
        return (org.w3.www._2001._04.xmlenc.EncryptionProperty[]) this._encryptionPropertyList.toArray(array);
    }

    /**
     * Method getEncryptionPropertyCount.
     * 
     * @return the size of this collection
     */
    public int getEncryptionPropertyCount(
    ) {
        return this._encryptionPropertyList.size();
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
        if (_encryptionPropertyList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_encryptionPropertyList)) {
           result = 37 * result + _encryptionPropertyList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_encryptionPropertyList);
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
     * Method iterateEncryptionProperty.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2001._04.xmlenc.EncryptionProperty> iterateEncryptionProperty(
    ) {
        return this._encryptionPropertyList.iterator();
    }

    /**
     */
    public void removeAllEncryptionProperty(
    ) {
        this._encryptionPropertyList.clear();
    }

    /**
     * Method removeEncryptionProperty.
     * 
     * @param vEncryptionProperty
     * @return true if the object was removed from the collection.
     */
    public boolean removeEncryptionProperty(
            final org.w3.www._2001._04.xmlenc.EncryptionProperty vEncryptionProperty) {
        boolean removed = _encryptionPropertyList.remove(vEncryptionProperty);
        return removed;
    }

    /**
     * Method removeEncryptionPropertyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2001._04.xmlenc.EncryptionProperty removeEncryptionPropertyAt(
            final int index) {
        java.lang.Object obj = this._encryptionPropertyList.remove(index);
        return (org.w3.www._2001._04.xmlenc.EncryptionProperty) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vEncryptionProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setEncryptionProperty(
            final int index,
            final org.w3.www._2001._04.xmlenc.EncryptionProperty vEncryptionProperty)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._encryptionPropertyList.size()) {
            throw new IndexOutOfBoundsException("setEncryptionProperty: Index value '" + index + "' not in range [0.." + (this._encryptionPropertyList.size() - 1) + "]");
        }

        this._encryptionPropertyList.set(index, vEncryptionProperty);
    }

    /**
     * 
     * 
     * @param vEncryptionPropertyArray
     */
    public void setEncryptionProperty(
            final org.w3.www._2001._04.xmlenc.EncryptionProperty[] vEncryptionPropertyArray) {
        //-- copy array
        _encryptionPropertyList.clear();

        for (int i = 0; i < vEncryptionPropertyArray.length; i++) {
                this._encryptionPropertyList.add(vEncryptionPropertyArray[i]);
        }
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
