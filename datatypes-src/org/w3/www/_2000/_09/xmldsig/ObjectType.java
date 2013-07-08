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

package org.w3.www._2000._09.xmldsig;

/**
 * Class ObjectType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ObjectType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private java.lang.String _id;

    /**
     * Field _mimeType.
     */
    private java.lang.String _mimeType;

    /**
     * Field _encoding.
     */
    private java.lang.String _encoding;

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _items.
     */
    private java.util.List<org.w3.www._2000._09.xmldsig.ObjectTypeItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public ObjectType() {
        super();
        setContent("");
        this._items = new java.util.ArrayList<org.w3.www._2000._09.xmldsig.ObjectTypeItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vObjectTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addObjectTypeItem(
            final org.w3.www._2000._09.xmldsig.ObjectTypeItem vObjectTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vObjectTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vObjectTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addObjectTypeItem(
            final int index,
            final org.w3.www._2000._09.xmldsig.ObjectTypeItem vObjectTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vObjectTypeItem);
    }

    /**
     * Method enumerateObjectTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2000._09.xmldsig.ObjectTypeItem> enumerateObjectTypeItem(
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

        if (obj instanceof ObjectType) {

            ObjectType temp = (ObjectType)obj;
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
     * Returns the value of field 'encoding'.
     * 
     * @return the value of field 'Encoding'.
     */
    public java.lang.String getEncoding(
    ) {
        return this._encoding;
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
     * Returns the value of field 'mimeType'.
     * 
     * @return the value of field 'MimeType'.
     */
    public java.lang.String getMimeType(
    ) {
        return this._mimeType;
    }

    /**
     * Method getObjectTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.w3.www._2000._09.xmldsig.ObjectTypeItem at the given inde
     */
    public org.w3.www._2000._09.xmldsig.ObjectTypeItem getObjectTypeItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getObjectTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (org.w3.www._2000._09.xmldsig.ObjectTypeItem) _items.get(index);
    }

    /**
     * Method getObjectTypeItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2000._09.xmldsig.ObjectTypeItem[] getObjectTypeItem(
    ) {
        org.w3.www._2000._09.xmldsig.ObjectTypeItem[] array = new org.w3.www._2000._09.xmldsig.ObjectTypeItem[0];
        return (org.w3.www._2000._09.xmldsig.ObjectTypeItem[]) this._items.toArray(array);
    }

    /**
     * Method getObjectTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getObjectTypeItemCount(
    ) {
        return this._items.size();
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
     * Method iterateObjectTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2000._09.xmldsig.ObjectTypeItem> iterateObjectTypeItem(
    ) {
        return this._items.iterator();
    }

    /**
     */
    public void removeAllObjectTypeItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeObjectTypeItem.
     * 
     * @param vObjectTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeObjectTypeItem(
            final org.w3.www._2000._09.xmldsig.ObjectTypeItem vObjectTypeItem) {
        boolean removed = _items.remove(vObjectTypeItem);
        return removed;
    }

    /**
     * Method removeObjectTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2000._09.xmldsig.ObjectTypeItem removeObjectTypeItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (org.w3.www._2000._09.xmldsig.ObjectTypeItem) obj;
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
     * Sets the value of field 'encoding'.
     * 
     * @param encoding the value of field 'encoding'.
     */
    public void setEncoding(
            final java.lang.String encoding) {
        this._encoding = encoding;
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
     * Sets the value of field 'mimeType'.
     * 
     * @param mimeType the value of field 'mimeType'.
     */
    public void setMimeType(
            final java.lang.String mimeType) {
        this._mimeType = mimeType;
    }

    /**
     * 
     * 
     * @param index
     * @param vObjectTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setObjectTypeItem(
            final int index,
            final org.w3.www._2000._09.xmldsig.ObjectTypeItem vObjectTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setObjectTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vObjectTypeItem);
    }

    /**
     * 
     * 
     * @param vObjectTypeItemArray
     */
    public void setObjectTypeItem(
            final org.w3.www._2000._09.xmldsig.ObjectTypeItem[] vObjectTypeItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vObjectTypeItemArray.length; i++) {
                this._items.add(vObjectTypeItemArray[i]);
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
