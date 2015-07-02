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
 * Class EncryptionPropertyType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class EncryptionPropertyType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _target.
     */
    private java.lang.String _target;

    /**
     * Field _id.
     */
    private java.lang.String _id;

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _items.
     */
    private java.util.List<org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public EncryptionPropertyType() {
        super();
        setContent("");
        this._items = new java.util.ArrayList<org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vEncryptionPropertyTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addEncryptionPropertyTypeItem(
            final org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem vEncryptionPropertyTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vEncryptionPropertyTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vEncryptionPropertyTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addEncryptionPropertyTypeItem(
            final int index,
            final org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem vEncryptionPropertyTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vEncryptionPropertyTypeItem);
    }

    /**
     * Method enumerateEncryptionPropertyTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem> enumerateEncryptionPropertyTypeItem(
    ) {
        return java.util.Collections.enumeration(this._items);
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

        if (obj instanceof EncryptionPropertyType) {

            EncryptionPropertyType temp = (EncryptionPropertyType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._target != null) {
                if (temp._target == null) return false;
                if (this._target != temp._target) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._target);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._target);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._target); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._target); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._target.equals(temp._target)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._target);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._target);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._target);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._target);
                    }
                }
            } else if (temp._target != null)
                return false;
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
            if (this._items != null) {
                if (temp._items == null) return false;
                if (this._items != temp._items) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._items);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._items);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._items); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._items); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._items.equals(temp._items)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._items);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._items);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._items);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._items);
                    }
                }
            } else if (temp._items != null)
                return false;
            return true;
        }
        return false;
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
     * Method getEncryptionPropertyTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem at
     * the given index
     */
    public org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem getEncryptionPropertyTypeItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getEncryptionPropertyTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem) _items.get(index);
    }

    /**
     * Method getEncryptionPropertyTypeItem.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem[] getEncryptionPropertyTypeItem(
    ) {
        org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem[] array = new org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem[0];
        return (org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem[]) this._items.toArray(array);
    }

    /**
     * Method getEncryptionPropertyTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getEncryptionPropertyTypeItemCount(
    ) {
        return this._items.size();
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
     * Returns the value of field 'target'.
     * 
     * @return the value of field 'Target'.
     */
    public java.lang.String getTarget(
    ) {
        return this._target;
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
        if (_target != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_target)) {
           result = 37 * result + _target.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_target);
        }
        if (_id != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_id)) {
           result = 37 * result + _id.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_id);
        }
        if (_content != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_content)) {
           result = 37 * result + _content.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_content);
        }
        if (_items != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_items)) {
           result = 37 * result + _items.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_items);
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
     * Method iterateEncryptionPropertyTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem> iterateEncryptionPropertyTypeItem(
    ) {
        return this._items.iterator();
    }

    /**
     */
    public void removeAllEncryptionPropertyTypeItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeEncryptionPropertyTypeItem.
     * 
     * @param vEncryptionPropertyTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeEncryptionPropertyTypeItem(
            final org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem vEncryptionPropertyTypeItem) {
        boolean removed = _items.remove(vEncryptionPropertyTypeItem);
        return removed;
    }

    /**
     * Method removeEncryptionPropertyTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem removeEncryptionPropertyTypeItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem) obj;
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
     * 
     * 
     * @param index
     * @param vEncryptionPropertyTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setEncryptionPropertyTypeItem(
            final int index,
            final org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem vEncryptionPropertyTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setEncryptionPropertyTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vEncryptionPropertyTypeItem);
    }

    /**
     * 
     * 
     * @param vEncryptionPropertyTypeItemArray
     */
    public void setEncryptionPropertyTypeItem(
            final org.w3.www._2001._04.xmlenc.EncryptionPropertyTypeItem[] vEncryptionPropertyTypeItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vEncryptionPropertyTypeItemArray.length; i++) {
                this._items.add(vEncryptionPropertyTypeItemArray[i]);
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
     * Sets the value of field 'target'.
     * 
     * @param target the value of field 'target'.
     */
    public void setTarget(
            final java.lang.String target) {
        this._target = target;
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
