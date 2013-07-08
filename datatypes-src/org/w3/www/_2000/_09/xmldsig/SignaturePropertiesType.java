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
 * Class SignaturePropertiesType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class SignaturePropertiesType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private java.lang.String _id;

    /**
     * Field _signaturePropertyList.
     */
    private java.util.List<org.w3.www._2000._09.xmldsig.SignatureProperty> _signaturePropertyList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SignaturePropertiesType() {
        super();
        this._signaturePropertyList = new java.util.ArrayList<org.w3.www._2000._09.xmldsig.SignatureProperty>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSignatureProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSignatureProperty(
            final org.w3.www._2000._09.xmldsig.SignatureProperty vSignatureProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._signaturePropertyList.add(vSignatureProperty);
    }

    /**
     * 
     * 
     * @param index
     * @param vSignatureProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSignatureProperty(
            final int index,
            final org.w3.www._2000._09.xmldsig.SignatureProperty vSignatureProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._signaturePropertyList.add(index, vSignatureProperty);
    }

    /**
     * Method enumerateSignatureProperty.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.w3.www._2000._09.xmldsig.SignatureProperty> enumerateSignatureProperty(
    ) {
        return java.util.Collections.enumeration(this._signaturePropertyList);
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

        if (obj instanceof SignaturePropertiesType) {

            SignaturePropertiesType temp = (SignaturePropertiesType)obj;
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
            if (this._signaturePropertyList != null) {
                if (temp._signaturePropertyList == null) return false;
                if (this._signaturePropertyList != temp._signaturePropertyList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signaturePropertyList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signaturePropertyList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signaturePropertyList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signaturePropertyList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signaturePropertyList.equals(temp._signaturePropertyList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signaturePropertyList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signaturePropertyList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signaturePropertyList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signaturePropertyList);
                    }
                }
            } else if (temp._signaturePropertyList != null)
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
     * Method getSignatureProperty.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.w3.www._2000._09.xmldsig.SignatureProperty at the given
     * index
     */
    public org.w3.www._2000._09.xmldsig.SignatureProperty getSignatureProperty(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._signaturePropertyList.size()) {
            throw new IndexOutOfBoundsException("getSignatureProperty: Index value '" + index + "' not in range [0.." + (this._signaturePropertyList.size() - 1) + "]");
        }

        return (org.w3.www._2000._09.xmldsig.SignatureProperty) _signaturePropertyList.get(index);
    }

    /**
     * Method getSignatureProperty.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.w3.www._2000._09.xmldsig.SignatureProperty[] getSignatureProperty(
    ) {
        org.w3.www._2000._09.xmldsig.SignatureProperty[] array = new org.w3.www._2000._09.xmldsig.SignatureProperty[0];
        return (org.w3.www._2000._09.xmldsig.SignatureProperty[]) this._signaturePropertyList.toArray(array);
    }

    /**
     * Method getSignaturePropertyCount.
     * 
     * @return the size of this collection
     */
    public int getSignaturePropertyCount(
    ) {
        return this._signaturePropertyList.size();
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
        if (_signaturePropertyList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signaturePropertyList)) {
           result = 37 * result + _signaturePropertyList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signaturePropertyList);
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
     * Method iterateSignatureProperty.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.w3.www._2000._09.xmldsig.SignatureProperty> iterateSignatureProperty(
    ) {
        return this._signaturePropertyList.iterator();
    }

    /**
     */
    public void removeAllSignatureProperty(
    ) {
        this._signaturePropertyList.clear();
    }

    /**
     * Method removeSignatureProperty.
     * 
     * @param vSignatureProperty
     * @return true if the object was removed from the collection.
     */
    public boolean removeSignatureProperty(
            final org.w3.www._2000._09.xmldsig.SignatureProperty vSignatureProperty) {
        boolean removed = _signaturePropertyList.remove(vSignatureProperty);
        return removed;
    }

    /**
     * Method removeSignaturePropertyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.w3.www._2000._09.xmldsig.SignatureProperty removeSignaturePropertyAt(
            final int index) {
        java.lang.Object obj = this._signaturePropertyList.remove(index);
        return (org.w3.www._2000._09.xmldsig.SignatureProperty) obj;
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
     * @param vSignatureProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSignatureProperty(
            final int index,
            final org.w3.www._2000._09.xmldsig.SignatureProperty vSignatureProperty)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._signaturePropertyList.size()) {
            throw new IndexOutOfBoundsException("setSignatureProperty: Index value '" + index + "' not in range [0.." + (this._signaturePropertyList.size() - 1) + "]");
        }

        this._signaturePropertyList.set(index, vSignatureProperty);
    }

    /**
     * 
     * 
     * @param vSignaturePropertyArray
     */
    public void setSignatureProperty(
            final org.w3.www._2000._09.xmldsig.SignatureProperty[] vSignaturePropertyArray) {
        //-- copy array
        _signaturePropertyList.clear();

        for (int i = 0; i < vSignaturePropertyArray.length; i++) {
                this._signaturePropertyList.add(vSignaturePropertyArray[i]);
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
