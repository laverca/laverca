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

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class ConditionsType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ConditionsType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _notBefore.
     */
    private java.util.Date _notBefore;

    /**
     * Field _notOnOrAfter.
     */
    private java.util.Date _notOnOrAfter;

    /**
     * Field _items.
     */
    private java.util.List<oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public ConditionsType() {
        super();
        this._items = new java.util.ArrayList<oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vConditionsTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addConditionsTypeItem(
            final oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem vConditionsTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vConditionsTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vConditionsTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addConditionsTypeItem(
            final int index,
            final oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem vConditionsTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vConditionsTypeItem);
    }

    /**
     * Method enumerateConditionsTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem> enumerateConditionsTypeItem(
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

        if (obj instanceof ConditionsType) {

            ConditionsType temp = (ConditionsType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._notBefore != null) {
                if (temp._notBefore == null) return false;
                if (this._notBefore != temp._notBefore) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._notBefore);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._notBefore);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._notBefore); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._notBefore); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._notBefore.equals(temp._notBefore)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._notBefore);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._notBefore);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._notBefore);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._notBefore);
                    }
                }
            } else if (temp._notBefore != null)
                return false;
            if (this._notOnOrAfter != null) {
                if (temp._notOnOrAfter == null) return false;
                if (this._notOnOrAfter != temp._notOnOrAfter) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._notOnOrAfter);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._notOnOrAfter);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._notOnOrAfter); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._notOnOrAfter); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._notOnOrAfter.equals(temp._notOnOrAfter)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._notOnOrAfter);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._notOnOrAfter);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._notOnOrAfter);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._notOnOrAfter);
                    }
                }
            } else if (temp._notOnOrAfter != null)
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
     * Method getConditionsTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem at the
     * given index
     */
    public oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem getConditionsTypeItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getConditionsTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem) _items.get(index);
    }

    /**
     * Method getConditionsTypeItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem[] getConditionsTypeItem(
    ) {
        oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem[] array = new oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem[0];
        return (oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem[]) this._items.toArray(array);
    }

    /**
     * Method getConditionsTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getConditionsTypeItemCount(
    ) {
        return this._items.size();
    }

    /**
     * Returns the value of field 'notBefore'.
     * 
     * @return the value of field 'NotBefore'.
     */
    public java.util.Date getNotBefore(
    ) {
        return this._notBefore;
    }

    /**
     * Returns the value of field 'notOnOrAfter'.
     * 
     * @return the value of field 'NotOnOrAfter'.
     */
    public java.util.Date getNotOnOrAfter(
    ) {
        return this._notOnOrAfter;
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
        if (_notBefore != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_notBefore)) {
           result = 37 * result + _notBefore.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_notBefore);
        }
        if (_notOnOrAfter != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_notOnOrAfter)) {
           result = 37 * result + _notOnOrAfter.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_notOnOrAfter);
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
     * Method iterateConditionsTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem> iterateConditionsTypeItem(
    ) {
        return this._items.iterator();
    }

    /**
     */
    public void removeAllConditionsTypeItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeConditionsTypeItem.
     * 
     * @param vConditionsTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeConditionsTypeItem(
            final oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem vConditionsTypeItem) {
        boolean removed = _items.remove(vConditionsTypeItem);
        return removed;
    }

    /**
     * Method removeConditionsTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem removeConditionsTypeItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vConditionsTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setConditionsTypeItem(
            final int index,
            final oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem vConditionsTypeItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setConditionsTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vConditionsTypeItem);
    }

    /**
     * 
     * 
     * @param vConditionsTypeItemArray
     */
    public void setConditionsTypeItem(
            final oasis.names.tc.SAML.v2_0.assertion.ConditionsTypeItem[] vConditionsTypeItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vConditionsTypeItemArray.length; i++) {
                this._items.add(vConditionsTypeItemArray[i]);
        }
    }

    /**
     * Sets the value of field 'notBefore'.
     * 
     * @param notBefore the value of field 'notBefore'.
     */
    public void setNotBefore(
            final java.util.Date notBefore) {
        this._notBefore = notBefore;
    }

    /**
     * Sets the value of field 'notOnOrAfter'.
     * 
     * @param notOnOrAfter the value of field 'notOnOrAfter'.
     */
    public void setNotOnOrAfter(
            final java.util.Date notOnOrAfter) {
        this._notOnOrAfter = notOnOrAfter;
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
