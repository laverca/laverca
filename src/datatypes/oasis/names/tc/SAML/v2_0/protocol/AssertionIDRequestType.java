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

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class AssertionIDRequestType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AssertionIDRequestType extends oasis.names.tc.SAML.v2_0.protocol.RequestAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _assertionIDRefList.
     */
    private java.util.List<java.lang.String> _assertionIDRefList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AssertionIDRequestType() {
        super();
        this._assertionIDRefList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAssertionIDRef
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAssertionIDRef(
            final java.lang.String vAssertionIDRef)
    throws java.lang.IndexOutOfBoundsException {
        this._assertionIDRefList.add(vAssertionIDRef);
    }

    /**
     * 
     * 
     * @param index
     * @param vAssertionIDRef
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAssertionIDRef(
            final int index,
            final java.lang.String vAssertionIDRef)
    throws java.lang.IndexOutOfBoundsException {
        this._assertionIDRefList.add(index, vAssertionIDRef);
    }

    /**
     * Method enumerateAssertionIDRef.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateAssertionIDRef(
    ) {
        return java.util.Collections.enumeration(this._assertionIDRefList);
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

        if (obj instanceof AssertionIDRequestType) {

            AssertionIDRequestType temp = (AssertionIDRequestType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._assertionIDRefList != null) {
                if (temp._assertionIDRefList == null) return false;
                if (this._assertionIDRefList != temp._assertionIDRefList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._assertionIDRefList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._assertionIDRefList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._assertionIDRefList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._assertionIDRefList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._assertionIDRefList.equals(temp._assertionIDRefList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._assertionIDRefList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._assertionIDRefList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._assertionIDRefList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._assertionIDRefList);
                    }
                }
            } else if (temp._assertionIDRefList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getAssertionIDRef.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getAssertionIDRef(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._assertionIDRefList.size()) {
            throw new IndexOutOfBoundsException("getAssertionIDRef: Index value '" + index + "' not in range [0.." + (this._assertionIDRefList.size() - 1) + "]");
        }

        return (java.lang.String) _assertionIDRefList.get(index);
    }

    /**
     * Method getAssertionIDRef.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getAssertionIDRef(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._assertionIDRefList.toArray(array);
    }

    /**
     * Method getAssertionIDRefCount.
     * 
     * @return the size of this collection
     */
    public int getAssertionIDRefCount(
    ) {
        return this._assertionIDRefList.size();
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
        if (_assertionIDRefList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_assertionIDRefList)) {
           result = 37 * result + _assertionIDRefList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_assertionIDRefList);
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
     * Method iterateAssertionIDRef.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateAssertionIDRef(
    ) {
        return this._assertionIDRefList.iterator();
    }

    /**
     */
    public void removeAllAssertionIDRef(
    ) {
        this._assertionIDRefList.clear();
    }

    /**
     * Method removeAssertionIDRef.
     * 
     * @param vAssertionIDRef
     * @return true if the object was removed from the collection.
     */
    public boolean removeAssertionIDRef(
            final java.lang.String vAssertionIDRef) {
        boolean removed = _assertionIDRefList.remove(vAssertionIDRef);
        return removed;
    }

    /**
     * Method removeAssertionIDRefAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeAssertionIDRefAt(
            final int index) {
        java.lang.Object obj = this._assertionIDRefList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAssertionIDRef
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAssertionIDRef(
            final int index,
            final java.lang.String vAssertionIDRef)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._assertionIDRefList.size()) {
            throw new IndexOutOfBoundsException("setAssertionIDRef: Index value '" + index + "' not in range [0.." + (this._assertionIDRefList.size() - 1) + "]");
        }

        this._assertionIDRefList.set(index, vAssertionIDRef);
    }

    /**
     * 
     * 
     * @param vAssertionIDRefArray
     */
    public void setAssertionIDRef(
            final java.lang.String[] vAssertionIDRefArray) {
        //-- copy array
        _assertionIDRefList.clear();

        for (int i = 0; i < vAssertionIDRefArray.length; i++) {
                this._assertionIDRefList.add(vAssertionIDRefArray[i]);
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
