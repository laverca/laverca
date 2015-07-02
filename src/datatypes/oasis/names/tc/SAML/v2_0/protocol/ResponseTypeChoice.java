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
 * Class ResponseTypeChoice.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ResponseTypeChoice implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items.
     */
    private java.util.List<oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public ResponseTypeChoice() {
        super();
        this._items = new java.util.ArrayList<oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vResponseTypeChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addResponseTypeChoiceItem(
            final oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem vResponseTypeChoiceItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vResponseTypeChoiceItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vResponseTypeChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addResponseTypeChoiceItem(
            final int index,
            final oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem vResponseTypeChoiceItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vResponseTypeChoiceItem);
    }

    /**
     * Method enumerateResponseTypeChoiceItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem> enumerateResponseTypeChoiceItem(
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

        if (obj instanceof ResponseTypeChoice) {

            ResponseTypeChoice temp = (ResponseTypeChoice)obj;
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
     * Method getResponseTypeChoiceItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem at
     * the given index
     */
    public oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem getResponseTypeChoiceItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getResponseTypeChoiceItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem) _items.get(index);
    }

    /**
     * Method getResponseTypeChoiceItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem[] getResponseTypeChoiceItem(
    ) {
        oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem[] array = new oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem[0];
        return (oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem[]) this._items.toArray(array);
    }

    /**
     * Method getResponseTypeChoiceItemCount.
     * 
     * @return the size of this collection
     */
    public int getResponseTypeChoiceItemCount(
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
     * Method iterateResponseTypeChoiceItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem> iterateResponseTypeChoiceItem(
    ) {
        return this._items.iterator();
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     */
    public void removeAllResponseTypeChoiceItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeResponseTypeChoiceItem.
     * 
     * @param vResponseTypeChoiceItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeResponseTypeChoiceItem(
            final oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem vResponseTypeChoiceItem) {
        boolean removed = _items.remove(vResponseTypeChoiceItem);
        return removed;
    }

    /**
     * Method removeResponseTypeChoiceItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem removeResponseTypeChoiceItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vResponseTypeChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setResponseTypeChoiceItem(
            final int index,
            final oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem vResponseTypeChoiceItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setResponseTypeChoiceItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vResponseTypeChoiceItem);
    }

    /**
     * 
     * 
     * @param vResponseTypeChoiceItemArray
     */
    public void setResponseTypeChoiceItem(
            final oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoiceItem[] vResponseTypeChoiceItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vResponseTypeChoiceItemArray.length; i++) {
                this._items.add(vResponseTypeChoiceItemArray[i]);
        }
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoice
     */
    public static oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoice unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoice) org.exolab.castor.xml.Unmarshaller.unmarshal(oasis.names.tc.SAML.v2_0.protocol.ResponseTypeChoice.class, reader);
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
