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
 * Class AttributeType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AttributeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _nameFormat.
     */
    private java.lang.String _nameFormat;

    /**
     * Field _friendlyName.
     */
    private java.lang.String _friendlyName;

    /**
     * Field _attributeValueList.
     */
    private java.util.List<java.lang.Object> _attributeValueList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AttributeType() {
        super();
        this._attributeValueList = new java.util.ArrayList<java.lang.Object>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAttributeValue
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAttributeValue(
            final java.lang.Object vAttributeValue)
    throws java.lang.IndexOutOfBoundsException {
        this._attributeValueList.add(vAttributeValue);
    }

    /**
     * 
     * 
     * @param index
     * @param vAttributeValue
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAttributeValue(
            final int index,
            final java.lang.Object vAttributeValue)
    throws java.lang.IndexOutOfBoundsException {
        this._attributeValueList.add(index, vAttributeValue);
    }

    /**
     * Method enumerateAttributeValue.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.Object> enumerateAttributeValue(
    ) {
        return java.util.Collections.enumeration(this._attributeValueList);
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

        if (obj instanceof AttributeType) {

            AttributeType temp = (AttributeType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._name != null) {
                if (temp._name == null) return false;
                if (this._name != temp._name) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._name);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._name);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._name.equals(temp._name)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._name);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._name);
                    }
                }
            } else if (temp._name != null)
                return false;
            if (this._nameFormat != null) {
                if (temp._nameFormat == null) return false;
                if (this._nameFormat != temp._nameFormat) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._nameFormat);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._nameFormat);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameFormat); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameFormat); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._nameFormat.equals(temp._nameFormat)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameFormat);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameFormat);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameFormat);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameFormat);
                    }
                }
            } else if (temp._nameFormat != null)
                return false;
            if (this._friendlyName != null) {
                if (temp._friendlyName == null) return false;
                if (this._friendlyName != temp._friendlyName) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._friendlyName);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._friendlyName);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._friendlyName); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._friendlyName); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._friendlyName.equals(temp._friendlyName)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._friendlyName);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._friendlyName);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._friendlyName);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._friendlyName);
                    }
                }
            } else if (temp._friendlyName != null)
                return false;
            if (this._attributeValueList != null) {
                if (temp._attributeValueList == null) return false;
                if (this._attributeValueList != temp._attributeValueList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._attributeValueList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._attributeValueList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._attributeValueList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._attributeValueList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._attributeValueList.equals(temp._attributeValueList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._attributeValueList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._attributeValueList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._attributeValueList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._attributeValueList);
                    }
                }
            } else if (temp._attributeValueList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getAttributeValue.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.Object at the given index
     */
    public java.lang.Object getAttributeValue(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._attributeValueList.size()) {
            throw new IndexOutOfBoundsException("getAttributeValue: Index value '" + index + "' not in range [0.." + (this._attributeValueList.size() - 1) + "]");
        }

        return (java.lang.Object) _attributeValueList.get(index);
    }

    /**
     * Method getAttributeValue.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.Object[] getAttributeValue(
    ) {
        java.lang.Object[] array = new java.lang.Object[0];
        return (java.lang.Object[]) this._attributeValueList.toArray(array);
    }

    /**
     * Method getAttributeValueCount.
     * 
     * @return the size of this collection
     */
    public int getAttributeValueCount(
    ) {
        return this._attributeValueList.size();
    }

    /**
     * Returns the value of field 'friendlyName'.
     * 
     * @return the value of field 'FriendlyName'.
     */
    public java.lang.String getFriendlyName(
    ) {
        return this._friendlyName;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'nameFormat'.
     * 
     * @return the value of field 'NameFormat'.
     */
    public java.lang.String getNameFormat(
    ) {
        return this._nameFormat;
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
        if (_name != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_name)) {
           result = 37 * result + _name.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_name);
        }
        if (_nameFormat != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_nameFormat)) {
           result = 37 * result + _nameFormat.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_nameFormat);
        }
        if (_friendlyName != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_friendlyName)) {
           result = 37 * result + _friendlyName.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_friendlyName);
        }
        if (_attributeValueList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_attributeValueList)) {
           result = 37 * result + _attributeValueList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_attributeValueList);
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
     * Method iterateAttributeValue.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.Object> iterateAttributeValue(
    ) {
        return this._attributeValueList.iterator();
    }

    /**
     */
    public void removeAllAttributeValue(
    ) {
        this._attributeValueList.clear();
    }

    /**
     * Method removeAttributeValue.
     * 
     * @param vAttributeValue
     * @return true if the object was removed from the collection.
     */
    public boolean removeAttributeValue(
            final java.lang.Object vAttributeValue) {
        boolean removed = _attributeValueList.remove(vAttributeValue);
        return removed;
    }

    /**
     * Method removeAttributeValueAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.Object removeAttributeValueAt(
            final int index) {
        java.lang.Object obj = this._attributeValueList.remove(index);
        return (java.lang.Object) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAttributeValue
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAttributeValue(
            final int index,
            final java.lang.Object vAttributeValue)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._attributeValueList.size()) {
            throw new IndexOutOfBoundsException("setAttributeValue: Index value '" + index + "' not in range [0.." + (this._attributeValueList.size() - 1) + "]");
        }

        this._attributeValueList.set(index, vAttributeValue);
    }

    /**
     * 
     * 
     * @param vAttributeValueArray
     */
    public void setAttributeValue(
            final java.lang.Object[] vAttributeValueArray) {
        //-- copy array
        _attributeValueList.clear();

        for (int i = 0; i < vAttributeValueArray.length; i++) {
                this._attributeValueList.add(vAttributeValueArray[i]);
        }
    }

    /**
     * Sets the value of field 'friendlyName'.
     * 
     * @param friendlyName the value of field 'friendlyName'.
     */
    public void setFriendlyName(
            final java.lang.String friendlyName) {
        this._friendlyName = friendlyName;
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'nameFormat'.
     * 
     * @param nameFormat the value of field 'nameFormat'.
     */
    public void setNameFormat(
            final java.lang.String nameFormat) {
        this._nameFormat = nameFormat;
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
