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
 * Class TransformType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class TransformType implements java.io.Serializable {


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
     * Field _items.
     */
    private java.util.List<org.w3.www._2000._09.xmldsig.TransformTypeItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public TransformType() {
        super();
        setContent("");
        this._items = new java.util.ArrayList<org.w3.www._2000._09.xmldsig.TransformTypeItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vTransformTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTransformTypeItem(
            final org.w3.www._2000._09.xmldsig.TransformTypeItem vTransformTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vTransformTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vTransformTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTransformTypeItem(
            final int index,
            final org.w3.www._2000._09.xmldsig.TransformTypeItem vTransformTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vTransformTypeItem);
    }

    /**
     * Method enumerateTransformTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2000._09.xmldsig.TransformTypeItem> enumerateTransformTypeItem(
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

        if (obj instanceof TransformType) {

            TransformType temp = (TransformType)obj;
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
     * Returns the value of field 'algorithm'.
     * 
     * @return the value of field 'Algorithm'.
     */
    public java.lang.String getAlgorithm(
    ) {
        return this._algorithm;
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
     * Method getTransformTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.w3.www._2000._09.xmldsig.TransformTypeItem at the given
     * index
     */
    public org.w3.www._2000._09.xmldsig.TransformTypeItem getTransformTypeItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getTransformTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (org.w3.www._2000._09.xmldsig.TransformTypeItem) _items.get(index);
    }

    /**
     * Method getTransformTypeItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2000._09.xmldsig.TransformTypeItem[] getTransformTypeItem(
    ) {
        org.w3.www._2000._09.xmldsig.TransformTypeItem[] array = new org.w3.www._2000._09.xmldsig.TransformTypeItem[0];
        return (org.w3.www._2000._09.xmldsig.TransformTypeItem[]) this._items.toArray(array);
    }

    /**
     * Method getTransformTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getTransformTypeItemCount(
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
     * Method iterateTransformTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2000._09.xmldsig.TransformTypeItem> iterateTransformTypeItem(
    ) {
        return this._items.iterator();
    }

    /**
     */
    public void removeAllTransformTypeItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeTransformTypeItem.
     * 
     * @param vTransformTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeTransformTypeItem(
            final org.w3.www._2000._09.xmldsig.TransformTypeItem vTransformTypeItem) {
        boolean removed = _items.remove(vTransformTypeItem);
        return removed;
    }

    /**
     * Method removeTransformTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2000._09.xmldsig.TransformTypeItem removeTransformTypeItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (org.w3.www._2000._09.xmldsig.TransformTypeItem) obj;
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
     * @param vTransformTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTransformTypeItem(
            final int index,
            final org.w3.www._2000._09.xmldsig.TransformTypeItem vTransformTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setTransformTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vTransformTypeItem);
    }

    /**
     * 
     * 
     * @param vTransformTypeItemArray
     */
    public void setTransformTypeItem(
            final org.w3.www._2000._09.xmldsig.TransformTypeItem[] vTransformTypeItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vTransformTypeItemArray.length; i++) {
                this._items.add(vTransformTypeItemArray[i]);
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
