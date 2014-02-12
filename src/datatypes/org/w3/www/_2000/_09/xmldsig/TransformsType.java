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
 * Class TransformsType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class TransformsType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _transformList.
     */
    private java.util.List<org.w3.www._2000._09.xmldsig.Transform> _transformList;


      //----------------/
     //- Constructors -/
    //----------------/

    public TransformsType() {
        super();
        this._transformList = new java.util.ArrayList<org.w3.www._2000._09.xmldsig.Transform>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vTransform
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTransform(
            final org.w3.www._2000._09.xmldsig.Transform vTransform)
    throws java.lang.IndexOutOfBoundsException {
        this._transformList.add(vTransform);
    }

    /**
     * 
     * 
     * @param index
     * @param vTransform
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTransform(
            final int index,
            final org.w3.www._2000._09.xmldsig.Transform vTransform)
    throws java.lang.IndexOutOfBoundsException {
        this._transformList.add(index, vTransform);
    }

    /**
     * Method enumerateTransform.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2000._09.xmldsig.Transform> enumerateTransform(
    ) {
        return java.util.Collections.enumeration(this._transformList);
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

        if (obj instanceof TransformsType) {

            TransformsType temp = (TransformsType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._transformList != null) {
                if (temp._transformList == null) return false;
                if (this._transformList != temp._transformList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._transformList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._transformList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transformList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transformList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._transformList.equals(temp._transformList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transformList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transformList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transformList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transformList);
                    }
                }
            } else if (temp._transformList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getTransform.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.w3.www._2000._09.xmldsig.Transform at the given index
     */
    public org.w3.www._2000._09.xmldsig.Transform getTransform(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._transformList.size()) {
            throw new IndexOutOfBoundsException("getTransform: Index value '" + index + "' not in range [0.." + (this._transformList.size() - 1) + "]");
        }

        return (org.w3.www._2000._09.xmldsig.Transform) _transformList.get(index);
    }

    /**
     * Method getTransform.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2000._09.xmldsig.Transform[] getTransform(
    ) {
        org.w3.www._2000._09.xmldsig.Transform[] array = new org.w3.www._2000._09.xmldsig.Transform[0];
        return (org.w3.www._2000._09.xmldsig.Transform[]) this._transformList.toArray(array);
    }

    /**
     * Method getTransformCount.
     * 
     * @return the size of this collection
     */
    public int getTransformCount(
    ) {
        return this._transformList.size();
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
        if (_transformList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_transformList)) {
           result = 37 * result + _transformList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_transformList);
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
     * Method iterateTransform.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2000._09.xmldsig.Transform> iterateTransform(
    ) {
        return this._transformList.iterator();
    }

    /**
     */
    public void removeAllTransform(
    ) {
        this._transformList.clear();
    }

    /**
     * Method removeTransform.
     * 
     * @param vTransform
     * @return true if the object was removed from the collection.
     */
    public boolean removeTransform(
            final org.w3.www._2000._09.xmldsig.Transform vTransform) {
        boolean removed = _transformList.remove(vTransform);
        return removed;
    }

    /**
     * Method removeTransformAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2000._09.xmldsig.Transform removeTransformAt(
            final int index) {
        java.lang.Object obj = this._transformList.remove(index);
        return (org.w3.www._2000._09.xmldsig.Transform) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vTransform
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTransform(
            final int index,
            final org.w3.www._2000._09.xmldsig.Transform vTransform)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._transformList.size()) {
            throw new IndexOutOfBoundsException("setTransform: Index value '" + index + "' not in range [0.." + (this._transformList.size() - 1) + "]");
        }

        this._transformList.set(index, vTransform);
    }

    /**
     * 
     * 
     * @param vTransformArray
     */
    public void setTransform(
            final org.w3.www._2000._09.xmldsig.Transform[] vTransformArray) {
        //-- copy array
        _transformList.clear();

        for (int i = 0; i < vTransformArray.length; i++) {
                this._transformList.add(vTransformArray[i]);
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
