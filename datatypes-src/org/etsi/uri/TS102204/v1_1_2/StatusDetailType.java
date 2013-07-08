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

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class StatusDetailType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class StatusDetailType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items.
     */
    private java.util.List<org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public StatusDetailType() {
        super();
        this._items = new java.util.ArrayList<org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vStatusDetailTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addStatusDetailTypeItem(
            final org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem vStatusDetailTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vStatusDetailTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vStatusDetailTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addStatusDetailTypeItem(
            final int index,
            final org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem vStatusDetailTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vStatusDetailTypeItem);
    }

    /**
     * Method enumerateStatusDetailTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem> enumerateStatusDetailTypeItem(
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

        if (obj instanceof StatusDetailType) {

            StatusDetailType temp = (StatusDetailType)obj;
            boolean thcycle;
            boolean tmcycle;
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
     * Method getStatusDetailTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem at the
     * given index
     */
    public org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem getStatusDetailTypeItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getStatusDetailTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem) _items.get(index);
    }

    /**
     * Method getStatusDetailTypeItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem[] getStatusDetailTypeItem(
    ) {
        org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem[] array = new org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem[0];
        return (org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem[]) this._items.toArray(array);
    }

    /**
     * Method getStatusDetailTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getStatusDetailTypeItemCount(
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
     * Method iterateStatusDetailTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem> iterateStatusDetailTypeItem(
    ) {
        return this._items.iterator();
    }

    /**
     */
    public void removeAllStatusDetailTypeItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeStatusDetailTypeItem.
     * 
     * @param vStatusDetailTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeStatusDetailTypeItem(
            final org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem vStatusDetailTypeItem) {
        boolean removed = _items.remove(vStatusDetailTypeItem);
        return removed;
    }

    /**
     * Method removeStatusDetailTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem removeStatusDetailTypeItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vStatusDetailTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setStatusDetailTypeItem(
            final int index,
            final org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem vStatusDetailTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setStatusDetailTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vStatusDetailTypeItem);
    }

    /**
     * 
     * 
     * @param vStatusDetailTypeItemArray
     */
    public void setStatusDetailTypeItem(
            final org.etsi.uri.TS102204.v1_1_2.StatusDetailTypeItem[] vStatusDetailTypeItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vStatusDetailTypeItemArray.length; i++) {
                this._items.add(vStatusDetailTypeItemArray[i]);
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
