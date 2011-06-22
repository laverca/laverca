/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class RequestedAuthnContextType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class RequestedAuthnContextType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _comparison.
     */
    private oasis.names.tc.SAML.v2_0.protocol.types.AuthnContextComparisonType _comparison;

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _authnContextClassRefList.
     */
    private java.util.List<java.lang.String> _authnContextClassRefList;

    /**
     * Field _authnContextDeclRefList.
     */
    private java.util.List<java.lang.String> _authnContextDeclRefList;


      //----------------/
     //- Constructors -/
    //----------------/

    public RequestedAuthnContextType() {
        super();
        this._authnContextClassRefList = new java.util.ArrayList<java.lang.String>();
        this._authnContextDeclRefList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAuthnContextClassRef
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAuthnContextClassRef(
            final java.lang.String vAuthnContextClassRef)
    throws java.lang.IndexOutOfBoundsException {
        this._authnContextClassRefList.add(vAuthnContextClassRef);
    }

    /**
     * 
     * 
     * @param index
     * @param vAuthnContextClassRef
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAuthnContextClassRef(
            final int index,
            final java.lang.String vAuthnContextClassRef)
    throws java.lang.IndexOutOfBoundsException {
        this._authnContextClassRefList.add(index, vAuthnContextClassRef);
    }

    /**
     * 
     * 
     * @param vAuthnContextDeclRef
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAuthnContextDeclRef(
            final java.lang.String vAuthnContextDeclRef)
    throws java.lang.IndexOutOfBoundsException {
        this._authnContextDeclRefList.add(vAuthnContextDeclRef);
    }

    /**
     * 
     * 
     * @param index
     * @param vAuthnContextDeclRef
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAuthnContextDeclRef(
            final int index,
            final java.lang.String vAuthnContextDeclRef)
    throws java.lang.IndexOutOfBoundsException {
        this._authnContextDeclRefList.add(index, vAuthnContextDeclRef);
    }

    /**
     * Method enumerateAuthnContextClassRef.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateAuthnContextClassRef(
    ) {
        return java.util.Collections.enumeration(this._authnContextClassRefList);
    }

    /**
     * Method enumerateAuthnContextDeclRef.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateAuthnContextDeclRef(
    ) {
        return java.util.Collections.enumeration(this._authnContextDeclRefList);
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

        if (obj instanceof RequestedAuthnContextType) {

            RequestedAuthnContextType temp = (RequestedAuthnContextType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._comparison != null) {
                if (temp._comparison == null) return false;
                if (this._comparison != temp._comparison) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._comparison);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._comparison);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._comparison); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._comparison); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._comparison.equals(temp._comparison)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._comparison);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._comparison);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._comparison);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._comparison);
                    }
                }
            } else if (temp._comparison != null)
                return false;
            if (this._choiceValue != null) {
                if (temp._choiceValue == null) return false;
                if (this._choiceValue != temp._choiceValue) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._choiceValue);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._choiceValue);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._choiceValue.equals(temp._choiceValue)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue);
                    }
                }
            } else if (temp._choiceValue != null)
                return false;
            if (this._authnContextClassRefList != null) {
                if (temp._authnContextClassRefList == null) return false;
                if (this._authnContextClassRefList != temp._authnContextClassRefList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnContextClassRefList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnContextClassRefList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextClassRefList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextClassRefList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnContextClassRefList.equals(temp._authnContextClassRefList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextClassRefList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextClassRefList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextClassRefList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextClassRefList);
                    }
                }
            } else if (temp._authnContextClassRefList != null)
                return false;
            if (this._authnContextDeclRefList != null) {
                if (temp._authnContextDeclRefList == null) return false;
                if (this._authnContextDeclRefList != temp._authnContextDeclRefList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnContextDeclRefList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnContextDeclRefList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextDeclRefList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextDeclRefList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnContextDeclRefList.equals(temp._authnContextDeclRefList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextDeclRefList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextDeclRefList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextDeclRefList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextDeclRefList);
                    }
                }
            } else if (temp._authnContextDeclRefList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getAuthnContextClassRef.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getAuthnContextClassRef(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._authnContextClassRefList.size()) {
            throw new IndexOutOfBoundsException("getAuthnContextClassRef: Index value '" + index + "' not in range [0.." + (this._authnContextClassRefList.size() - 1) + "]");
        }

        return (java.lang.String) _authnContextClassRefList.get(index);
    }

    /**
     * Method getAuthnContextClassRef.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getAuthnContextClassRef(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._authnContextClassRefList.toArray(array);
    }

    /**
     * Method getAuthnContextClassRefCount.
     * 
     * @return the size of this collection
     */
    public int getAuthnContextClassRefCount(
    ) {
        return this._authnContextClassRefList.size();
    }

    /**
     * Method getAuthnContextDeclRef.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getAuthnContextDeclRef(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._authnContextDeclRefList.size()) {
            throw new IndexOutOfBoundsException("getAuthnContextDeclRef: Index value '" + index + "' not in range [0.." + (this._authnContextDeclRefList.size() - 1) + "]");
        }

        return (java.lang.String) _authnContextDeclRefList.get(index);
    }

    /**
     * Method getAuthnContextDeclRef.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getAuthnContextDeclRef(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._authnContextDeclRefList.toArray(array);
    }

    /**
     * Method getAuthnContextDeclRefCount.
     * 
     * @return the size of this collection
     */
    public int getAuthnContextDeclRefCount(
    ) {
        return this._authnContextDeclRefList.size();
    }

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return the value of field 'ChoiceValue'.
     */
    public java.lang.Object getChoiceValue(
    ) {
        return this._choiceValue;
    }

    /**
     * Returns the value of field 'comparison'.
     * 
     * @return the value of field 'Comparison'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.types.AuthnContextComparisonType getComparison(
    ) {
        return this._comparison;
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
        if (_comparison != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_comparison)) {
           result = 37 * result + _comparison.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_comparison);
        }
        if (_choiceValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_choiceValue)) {
           result = 37 * result + _choiceValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_choiceValue);
        }
        if (_authnContextClassRefList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnContextClassRefList)) {
           result = 37 * result + _authnContextClassRefList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnContextClassRefList);
        }
        if (_authnContextDeclRefList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnContextDeclRefList)) {
           result = 37 * result + _authnContextDeclRefList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnContextDeclRefList);
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
     * Method iterateAuthnContextClassRef.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateAuthnContextClassRef(
    ) {
        return this._authnContextClassRefList.iterator();
    }

    /**
     * Method iterateAuthnContextDeclRef.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateAuthnContextDeclRef(
    ) {
        return this._authnContextDeclRefList.iterator();
    }

    /**
     */
    public void removeAllAuthnContextClassRef(
    ) {
        this._authnContextClassRefList.clear();
    }

    /**
     */
    public void removeAllAuthnContextDeclRef(
    ) {
        this._authnContextDeclRefList.clear();
    }

    /**
     * Method removeAuthnContextClassRef.
     * 
     * @param vAuthnContextClassRef
     * @return true if the object was removed from the collection.
     */
    public boolean removeAuthnContextClassRef(
            final java.lang.String vAuthnContextClassRef) {
        boolean removed = _authnContextClassRefList.remove(vAuthnContextClassRef);
        return removed;
    }

    /**
     * Method removeAuthnContextClassRefAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeAuthnContextClassRefAt(
            final int index) {
        java.lang.Object obj = this._authnContextClassRefList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Method removeAuthnContextDeclRef.
     * 
     * @param vAuthnContextDeclRef
     * @return true if the object was removed from the collection.
     */
    public boolean removeAuthnContextDeclRef(
            final java.lang.String vAuthnContextDeclRef) {
        boolean removed = _authnContextDeclRefList.remove(vAuthnContextDeclRef);
        return removed;
    }

    /**
     * Method removeAuthnContextDeclRefAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeAuthnContextDeclRefAt(
            final int index) {
        java.lang.Object obj = this._authnContextDeclRefList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAuthnContextClassRef
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAuthnContextClassRef(
            final int index,
            final java.lang.String vAuthnContextClassRef)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._authnContextClassRefList.size()) {
            throw new IndexOutOfBoundsException("setAuthnContextClassRef: Index value '" + index + "' not in range [0.." + (this._authnContextClassRefList.size() - 1) + "]");
        }

        this._authnContextClassRefList.set(index, vAuthnContextClassRef);
    }

    /**
     * 
     * 
     * @param vAuthnContextClassRefArray
     */
    public void setAuthnContextClassRef(
            final java.lang.String[] vAuthnContextClassRefArray) {
        //-- copy array
        _authnContextClassRefList.clear();

        for (int i = 0; i < vAuthnContextClassRefArray.length; i++) {
                this._authnContextClassRefList.add(vAuthnContextClassRefArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vAuthnContextDeclRef
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAuthnContextDeclRef(
            final int index,
            final java.lang.String vAuthnContextDeclRef)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._authnContextDeclRefList.size()) {
            throw new IndexOutOfBoundsException("setAuthnContextDeclRef: Index value '" + index + "' not in range [0.." + (this._authnContextDeclRefList.size() - 1) + "]");
        }

        this._authnContextDeclRefList.set(index, vAuthnContextDeclRef);
    }

    /**
     * 
     * 
     * @param vAuthnContextDeclRefArray
     */
    public void setAuthnContextDeclRef(
            final java.lang.String[] vAuthnContextDeclRefArray) {
        //-- copy array
        _authnContextDeclRefList.clear();

        for (int i = 0; i < vAuthnContextDeclRefArray.length; i++) {
                this._authnContextDeclRefList.add(vAuthnContextDeclRefArray[i]);
        }
    }

    /**
     * Sets the value of field 'comparison'.
     * 
     * @param comparison the value of field 'comparison'.
     */
    public void setComparison(
            final oasis.names.tc.SAML.v2_0.protocol.types.AuthnContextComparisonType comparison) {
        this._comparison = comparison;
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
