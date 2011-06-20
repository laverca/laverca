/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig;

/**
 * Class SignedInfoType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class SignedInfoType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private java.lang.String _id;

    /**
     * Field _canonicalizationMethod.
     */
    private org.w3.www._2000._09.xmldsig.CanonicalizationMethod _canonicalizationMethod;

    /**
     * Field _signatureMethod.
     */
    private org.w3.www._2000._09.xmldsig.SignatureMethod _signatureMethod;

    /**
     * Field _referenceList.
     */
    private java.util.List<org.w3.www._2000._09.xmldsig.Reference> _referenceList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SignedInfoType() {
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

        if (obj instanceof SignedInfoType) {

            SignedInfoType temp = (SignedInfoType)obj;
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
            if (this._canonicalizationMethod != null) {
                if (temp._canonicalizationMethod == null) return false;
                if (this._canonicalizationMethod != temp._canonicalizationMethod) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._canonicalizationMethod);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._canonicalizationMethod);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._canonicalizationMethod); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._canonicalizationMethod); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._canonicalizationMethod.equals(temp._canonicalizationMethod)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._canonicalizationMethod);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._canonicalizationMethod);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._canonicalizationMethod);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._canonicalizationMethod);
                    }
                }
            } else if (temp._canonicalizationMethod != null)
                return false;
            if (this._signatureMethod != null) {
                if (temp._signatureMethod == null) return false;
                if (this._signatureMethod != temp._signatureMethod) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signatureMethod);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signatureMethod);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureMethod); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureMethod); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signatureMethod.equals(temp._signatureMethod)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureMethod);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureMethod);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureMethod);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureMethod);
                    }
                }
            } else if (temp._signatureMethod != null)
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
     * Returns the value of field 'canonicalizationMethod'.
     * 
     * @return the value of field 'CanonicalizationMethod'.
     */
    public org.w3.www._2000._09.xmldsig.CanonicalizationMethod getCanonicalizationMethod(
    ) {
        return this._canonicalizationMethod;
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
     * Returns the value of field 'signatureMethod'.
     * 
     * @return the value of field 'SignatureMethod'.
     */
    public org.w3.www._2000._09.xmldsig.SignatureMethod getSignatureMethod(
    ) {
        return this._signatureMethod;
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
        if (_canonicalizationMethod != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_canonicalizationMethod)) {
           result = 37 * result + _canonicalizationMethod.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_canonicalizationMethod);
        }
        if (_signatureMethod != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signatureMethod)) {
           result = 37 * result + _signatureMethod.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signatureMethod);
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
     * Sets the value of field 'canonicalizationMethod'.
     * 
     * @param canonicalizationMethod the value of field
     * 'canonicalizationMethod'.
     */
    public void setCanonicalizationMethod(
            final org.w3.www._2000._09.xmldsig.CanonicalizationMethod canonicalizationMethod) {
        this._canonicalizationMethod = canonicalizationMethod;
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
     * Sets the value of field 'signatureMethod'.
     * 
     * @param signatureMethod the value of field 'signatureMethod'.
     */
    public void setSignatureMethod(
            final org.w3.www._2000._09.xmldsig.SignatureMethod signatureMethod) {
        this._signatureMethod = signatureMethod;
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
