/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class SignatureAlgList.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class SignatureAlgList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _algorithmList.
     */
    private java.util.List<org.etsi.uri.TS102204.v1_1_2.Algorithm> _algorithmList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SignatureAlgList() {
        super();
        this._algorithmList = new java.util.ArrayList<org.etsi.uri.TS102204.v1_1_2.Algorithm>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAlgorithm
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAlgorithm(
            final org.etsi.uri.TS102204.v1_1_2.Algorithm vAlgorithm)
    throws java.lang.IndexOutOfBoundsException {
        this._algorithmList.add(vAlgorithm);
    }

    /**
     * 
     * 
     * @param index
     * @param vAlgorithm
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAlgorithm(
            final int index,
            final org.etsi.uri.TS102204.v1_1_2.Algorithm vAlgorithm)
    throws java.lang.IndexOutOfBoundsException {
        this._algorithmList.add(index, vAlgorithm);
    }

    /**
     * Method enumerateAlgorithm.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.etsi.uri.TS102204.v1_1_2.Algorithm> enumerateAlgorithm(
    ) {
        return java.util.Collections.enumeration(this._algorithmList);
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

        if (obj instanceof SignatureAlgList) {

            SignatureAlgList temp = (SignatureAlgList)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._algorithmList != null) {
                if (temp._algorithmList == null) return false;
                if (this._algorithmList != temp._algorithmList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._algorithmList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._algorithmList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._algorithmList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._algorithmList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._algorithmList.equals(temp._algorithmList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._algorithmList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._algorithmList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._algorithmList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._algorithmList);
                    }
                }
            } else if (temp._algorithmList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getAlgorithm.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.etsi.uri.TS102204.v1_1_2.Algorithm at the given index
     */
    public org.etsi.uri.TS102204.v1_1_2.Algorithm getAlgorithm(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._algorithmList.size()) {
            throw new IndexOutOfBoundsException("getAlgorithm: Index value '" + index + "' not in range [0.." + (this._algorithmList.size() - 1) + "]");
        }

        return (org.etsi.uri.TS102204.v1_1_2.Algorithm) _algorithmList.get(index);
    }

    /**
     * Method getAlgorithm.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.etsi.uri.TS102204.v1_1_2.Algorithm[] getAlgorithm(
    ) {
        org.etsi.uri.TS102204.v1_1_2.Algorithm[] array = new org.etsi.uri.TS102204.v1_1_2.Algorithm[0];
        return (org.etsi.uri.TS102204.v1_1_2.Algorithm[]) this._algorithmList.toArray(array);
    }

    /**
     * Method getAlgorithmCount.
     * 
     * @return the size of this collection
     */
    public int getAlgorithmCount(
    ) {
        return this._algorithmList.size();
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
        if (_algorithmList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_algorithmList)) {
           result = 37 * result + _algorithmList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_algorithmList);
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
     * Method iterateAlgorithm.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.etsi.uri.TS102204.v1_1_2.Algorithm> iterateAlgorithm(
    ) {
        return this._algorithmList.iterator();
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
     * Method removeAlgorithm.
     * 
     * @param vAlgorithm
     * @return true if the object was removed from the collection.
     */
    public boolean removeAlgorithm(
            final org.etsi.uri.TS102204.v1_1_2.Algorithm vAlgorithm) {
        boolean removed = _algorithmList.remove(vAlgorithm);
        return removed;
    }

    /**
     * Method removeAlgorithmAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.etsi.uri.TS102204.v1_1_2.Algorithm removeAlgorithmAt(
            final int index) {
        java.lang.Object obj = this._algorithmList.remove(index);
        return (org.etsi.uri.TS102204.v1_1_2.Algorithm) obj;
    }

    /**
     */
    public void removeAllAlgorithm(
    ) {
        this._algorithmList.clear();
    }

    /**
     * 
     * 
     * @param index
     * @param vAlgorithm
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAlgorithm(
            final int index,
            final org.etsi.uri.TS102204.v1_1_2.Algorithm vAlgorithm)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._algorithmList.size()) {
            throw new IndexOutOfBoundsException("setAlgorithm: Index value '" + index + "' not in range [0.." + (this._algorithmList.size() - 1) + "]");
        }

        this._algorithmList.set(index, vAlgorithm);
    }

    /**
     * 
     * 
     * @param vAlgorithmArray
     */
    public void setAlgorithm(
            final org.etsi.uri.TS102204.v1_1_2.Algorithm[] vAlgorithmArray) {
        //-- copy array
        _algorithmList.clear();

        for (int i = 0; i < vAlgorithmArray.length; i++) {
                this._algorithmList.add(vAlgorithmArray[i]);
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
     * org.etsi.uri.TS102204.v1_1_2.SignatureAlgList
     */
    public static org.etsi.uri.TS102204.v1_1_2.SignatureAlgList unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.etsi.uri.TS102204.v1_1_2.SignatureAlgList) org.exolab.castor.xml.Unmarshaller.unmarshal(org.etsi.uri.TS102204.v1_1_2.SignatureAlgList.class, reader);
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
