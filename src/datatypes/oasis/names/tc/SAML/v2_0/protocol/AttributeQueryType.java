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

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class AttributeQueryType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AttributeQueryType extends oasis.names.tc.SAML.v2_0.protocol.SubjectQueryAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _attributeList.
     */
    private java.util.List<oasis.names.tc.SAML.v2_0.assertion.Attribute> _attributeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AttributeQueryType() {
        super();
        this._attributeList = new java.util.ArrayList<oasis.names.tc.SAML.v2_0.assertion.Attribute>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAttribute(
            final oasis.names.tc.SAML.v2_0.assertion.Attribute vAttribute)
    throws java.lang.IndexOutOfBoundsException {
        this._attributeList.add(vAttribute);
    }

    /**
     * 
     * 
     * @param index
     * @param vAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAttribute(
            final int index,
            final oasis.names.tc.SAML.v2_0.assertion.Attribute vAttribute)
    throws java.lang.IndexOutOfBoundsException {
        this._attributeList.add(index, vAttribute);
    }

    /**
     * Method enumerateAttribute.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends oasis.names.tc.SAML.v2_0.assertion.Attribute> enumerateAttribute(
    ) {
        return java.util.Collections.enumeration(this._attributeList);
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

        if (obj instanceof AttributeQueryType) {

            AttributeQueryType temp = (AttributeQueryType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._attributeList != null) {
                if (temp._attributeList == null) return false;
                if (this._attributeList != temp._attributeList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._attributeList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._attributeList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._attributeList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._attributeList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._attributeList.equals(temp._attributeList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._attributeList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._attributeList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._attributeList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._attributeList);
                    }
                }
            } else if (temp._attributeList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getAttribute.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * oasis.names.tc.SAML.v2_0.assertion.Attribute at the given
     * index
     */
    public oasis.names.tc.SAML.v2_0.assertion.Attribute getAttribute(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._attributeList.size()) {
            throw new IndexOutOfBoundsException("getAttribute: Index value '" + index + "' not in range [0.." + (this._attributeList.size() - 1) + "]");
        }

        return (oasis.names.tc.SAML.v2_0.assertion.Attribute) _attributeList.get(index);
    }

    /**
     * Method getAttribute.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public oasis.names.tc.SAML.v2_0.assertion.Attribute[] getAttribute(
    ) {
        oasis.names.tc.SAML.v2_0.assertion.Attribute[] array = new oasis.names.tc.SAML.v2_0.assertion.Attribute[0];
        return (oasis.names.tc.SAML.v2_0.assertion.Attribute[]) this._attributeList.toArray(array);
    }

    /**
     * Method getAttributeCount.
     * 
     * @return the size of this collection
     */
    public int getAttributeCount(
    ) {
        return this._attributeList.size();
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
        if (_attributeList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_attributeList)) {
           result = 37 * result + _attributeList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_attributeList);
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
     * Method iterateAttribute.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends oasis.names.tc.SAML.v2_0.assertion.Attribute> iterateAttribute(
    ) {
        return this._attributeList.iterator();
    }

    /**
     */
    public void removeAllAttribute(
    ) {
        this._attributeList.clear();
    }

    /**
     * Method removeAttribute.
     * 
     * @param vAttribute
     * @return true if the object was removed from the collection.
     */
    public boolean removeAttribute(
            final oasis.names.tc.SAML.v2_0.assertion.Attribute vAttribute) {
        boolean removed = _attributeList.remove(vAttribute);
        return removed;
    }

    /**
     * Method removeAttributeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public oasis.names.tc.SAML.v2_0.assertion.Attribute removeAttributeAt(
            final int index) {
        java.lang.Object obj = this._attributeList.remove(index);
        return (oasis.names.tc.SAML.v2_0.assertion.Attribute) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAttribute
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAttribute(
            final int index,
            final oasis.names.tc.SAML.v2_0.assertion.Attribute vAttribute)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._attributeList.size()) {
            throw new IndexOutOfBoundsException("setAttribute: Index value '" + index + "' not in range [0.." + (this._attributeList.size() - 1) + "]");
        }

        this._attributeList.set(index, vAttribute);
    }

    /**
     * 
     * 
     * @param vAttributeArray
     */
    public void setAttribute(
            final oasis.names.tc.SAML.v2_0.assertion.Attribute[] vAttributeArray) {
        //-- copy array
        _attributeList.clear();

        for (int i = 0; i < vAttributeArray.length; i++) {
                this._attributeList.add(vAttributeArray[i]);
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
