/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class AssertionTypeChoice.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AssertionTypeChoice implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items.
     */
    private java.util.List<oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public AssertionTypeChoice() {
        super();
        this._items = new java.util.ArrayList<oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAssertionTypeChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAssertionTypeChoiceItem(
            final oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem vAssertionTypeChoiceItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vAssertionTypeChoiceItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vAssertionTypeChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAssertionTypeChoiceItem(
            final int index,
            final oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem vAssertionTypeChoiceItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vAssertionTypeChoiceItem);
    }

    /**
     * Method enumerateAssertionTypeChoiceItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem> enumerateAssertionTypeChoiceItem(
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

        if (obj instanceof AssertionTypeChoice) {

            AssertionTypeChoice temp = (AssertionTypeChoice)obj;
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
     * Method getAssertionTypeChoiceItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem
     * at the given index
     */
    public oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem getAssertionTypeChoiceItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getAssertionTypeChoiceItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem) _items.get(index);
    }

    /**
     * Method getAssertionTypeChoiceItem.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem[] getAssertionTypeChoiceItem(
    ) {
        oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem[] array = new oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem[0];
        return (oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem[]) this._items.toArray(array);
    }

    /**
     * Method getAssertionTypeChoiceItemCount.
     * 
     * @return the size of this collection
     */
    public int getAssertionTypeChoiceItemCount(
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
     * Method iterateAssertionTypeChoiceItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem> iterateAssertionTypeChoiceItem(
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
    public void removeAllAssertionTypeChoiceItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeAssertionTypeChoiceItem.
     * 
     * @param vAssertionTypeChoiceItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeAssertionTypeChoiceItem(
            final oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem vAssertionTypeChoiceItem) {
        boolean removed = _items.remove(vAssertionTypeChoiceItem);
        return removed;
    }

    /**
     * Method removeAssertionTypeChoiceItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem removeAssertionTypeChoiceItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAssertionTypeChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAssertionTypeChoiceItem(
            final int index,
            final oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem vAssertionTypeChoiceItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setAssertionTypeChoiceItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vAssertionTypeChoiceItem);
    }

    /**
     * 
     * 
     * @param vAssertionTypeChoiceItemArray
     */
    public void setAssertionTypeChoiceItem(
            final oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoiceItem[] vAssertionTypeChoiceItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vAssertionTypeChoiceItemArray.length; i++) {
                this._items.add(vAssertionTypeChoiceItemArray[i]);
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
     * oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoice
     */
    public static oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoice unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoice) org.exolab.castor.xml.Unmarshaller.unmarshal(oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoice.class, reader);
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
