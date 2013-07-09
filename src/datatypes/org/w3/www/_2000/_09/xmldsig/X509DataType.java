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
 * Class X509DataType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class X509DataType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items.
     */
    private java.util.List<org.w3.www._2000._09.xmldsig.X509DataTypeItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public X509DataType() {
        super();
        this._items = new java.util.ArrayList<org.w3.www._2000._09.xmldsig.X509DataTypeItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vX509DataTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addX509DataTypeItem(
            final org.w3.www._2000._09.xmldsig.X509DataTypeItem vX509DataTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vX509DataTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vX509DataTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addX509DataTypeItem(
            final int index,
            final org.w3.www._2000._09.xmldsig.X509DataTypeItem vX509DataTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vX509DataTypeItem);
    }

    /**
     * Method enumerateX509DataTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2000._09.xmldsig.X509DataTypeItem> enumerateX509DataTypeItem(
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

        if (obj instanceof X509DataType) {

            X509DataType temp = (X509DataType)obj;
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
     * Method getX509DataTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.w3.www._2000._09.xmldsig.X509DataTypeItem at the given
     * index
     */
    public org.w3.www._2000._09.xmldsig.X509DataTypeItem getX509DataTypeItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getX509DataTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (org.w3.www._2000._09.xmldsig.X509DataTypeItem) _items.get(index);
    }

    /**
     * Method getX509DataTypeItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2000._09.xmldsig.X509DataTypeItem[] getX509DataTypeItem(
    ) {
        org.w3.www._2000._09.xmldsig.X509DataTypeItem[] array = new org.w3.www._2000._09.xmldsig.X509DataTypeItem[0];
        return (org.w3.www._2000._09.xmldsig.X509DataTypeItem[]) this._items.toArray(array);
    }

    /**
     * Method getX509DataTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getX509DataTypeItemCount(
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
     * Method iterateX509DataTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2000._09.xmldsig.X509DataTypeItem> iterateX509DataTypeItem(
    ) {
        return this._items.iterator();
    }

    /**
     */
    public void removeAllX509DataTypeItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeX509DataTypeItem.
     * 
     * @param vX509DataTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeX509DataTypeItem(
            final org.w3.www._2000._09.xmldsig.X509DataTypeItem vX509DataTypeItem) {
        boolean removed = _items.remove(vX509DataTypeItem);
        return removed;
    }

    /**
     * Method removeX509DataTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2000._09.xmldsig.X509DataTypeItem removeX509DataTypeItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (org.w3.www._2000._09.xmldsig.X509DataTypeItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vX509DataTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setX509DataTypeItem(
            final int index,
            final org.w3.www._2000._09.xmldsig.X509DataTypeItem vX509DataTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setX509DataTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vX509DataTypeItem);
    }

    /**
     * 
     * 
     * @param vX509DataTypeItemArray
     */
    public void setX509DataTypeItem(
            final org.w3.www._2000._09.xmldsig.X509DataTypeItem[] vX509DataTypeItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vX509DataTypeItemArray.length; i++) {
                this._items.add(vX509DataTypeItemArray[i]);
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
