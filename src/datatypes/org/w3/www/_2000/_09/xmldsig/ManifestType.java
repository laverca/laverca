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

package org.w3.www._2000._09.xmldsig;

/**
 * Class ManifestType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ManifestType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private java.lang.String _id;

    /**
     * Field _referenceList.
     */
    private java.util.List<org.w3.www._2000._09.xmldsig.Reference> _referenceList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ManifestType() {
        super();
        this._referenceList = new java.util.ArrayList<org.w3.www._2000._09.xmldsig.Reference>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vReference
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addReference(
            final org.w3.www._2000._09.xmldsig.Reference vReference)
    throws java.lang.IndexOutOfBoundsException {
        this._referenceList.add(vReference);
    }

    /**
     * 
     * 
     * @param index
     * @param vReference
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addReference(
            final int index,
            final org.w3.www._2000._09.xmldsig.Reference vReference)
    throws java.lang.IndexOutOfBoundsException {
        this._referenceList.add(index, vReference);
    }

    /**
     * Method enumerateReference.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2000._09.xmldsig.Reference> enumerateReference(
    ) {
        return java.util.Collections.enumeration(this._referenceList);
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

        if (obj instanceof ManifestType) {

            ManifestType temp = (ManifestType)obj;
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
            if (this._referenceList != null) {
                if (temp._referenceList == null) return false;
                if (this._referenceList != temp._referenceList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._referenceList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._referenceList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._referenceList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._referenceList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._referenceList.equals(temp._referenceList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._referenceList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._referenceList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._referenceList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._referenceList);
                    }
                }
            } else if (temp._referenceList != null)
                return false;
            return true;
        }
        return false;
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
     * Method getReference.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.w3.www._2000._09.xmldsig.Reference at the given index
     */
    public org.w3.www._2000._09.xmldsig.Reference getReference(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._referenceList.size()) {
            throw new IndexOutOfBoundsException("getReference: Index value '" + index + "' not in range [0.." + (this._referenceList.size() - 1) + "]");
        }

        return (org.w3.www._2000._09.xmldsig.Reference) _referenceList.get(index);
    }

    /**
     * Method getReference.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2000._09.xmldsig.Reference[] getReference(
    ) {
        org.w3.www._2000._09.xmldsig.Reference[] array = new org.w3.www._2000._09.xmldsig.Reference[0];
        return (org.w3.www._2000._09.xmldsig.Reference[]) this._referenceList.toArray(array);
    }

    /**
     * Method getReferenceCount.
     * 
     * @return the size of this collection
     */
    public int getReferenceCount(
    ) {
        return this._referenceList.size();
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
        if (_referenceList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_referenceList)) {
           result = 37 * result + _referenceList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_referenceList);
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
     * Method iterateReference.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2000._09.xmldsig.Reference> iterateReference(
    ) {
        return this._referenceList.iterator();
    }

    /**
     */
    public void removeAllReference(
    ) {
        this._referenceList.clear();
    }

    /**
     * Method removeReference.
     * 
     * @param vReference
     * @return true if the object was removed from the collection.
     */
    public boolean removeReference(
            final org.w3.www._2000._09.xmldsig.Reference vReference) {
        boolean removed = _referenceList.remove(vReference);
        return removed;
    }

    /**
     * Method removeReferenceAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2000._09.xmldsig.Reference removeReferenceAt(
            final int index) {
        java.lang.Object obj = this._referenceList.remove(index);
        return (org.w3.www._2000._09.xmldsig.Reference) obj;
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
     * @param index
     * @param vReference
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setReference(
            final int index,
            final org.w3.www._2000._09.xmldsig.Reference vReference)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._referenceList.size()) {
            throw new IndexOutOfBoundsException("setReference: Index value '" + index + "' not in range [0.." + (this._referenceList.size() - 1) + "]");
        }

        this._referenceList.set(index, vReference);
    }

    /**
     * 
     * 
     * @param vReferenceArray
     */
    public void setReference(
            final org.w3.www._2000._09.xmldsig.Reference[] vReferenceArray) {
        //-- copy array
        _referenceList.clear();

        for (int i = 0; i < vReferenceArray.length; i++) {
                this._referenceList.add(vReferenceArray[i]);
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
