/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class SubjectConfirmationDataType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class SubjectConfirmationDataType implements java.io.Serializable {


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
     * Field _recipient.
     */
    private java.lang.String _recipient;

    /**
     * Field _inResponseTo.
     */
    private java.lang.String _inResponseTo;

    /**
     * Field _address.
     */
    private java.lang.String _address;

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _anyObject.
     */
    private java.util.List<java.lang.Object> _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public SubjectConfirmationDataType() {
        super();
        setContent("");
        this._anyObject = new java.util.ArrayList<java.lang.Object>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAnyObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAnyObject(
            final java.lang.Object vAnyObject)
    throws java.lang.IndexOutOfBoundsException {
        this._anyObject.add(vAnyObject);
    }

    /**
     * 
     * 
     * @param index
     * @param vAnyObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAnyObject(
            final int index,
            final java.lang.Object vAnyObject)
    throws java.lang.IndexOutOfBoundsException {
        this._anyObject.add(index, vAnyObject);
    }

    /**
     * Method enumerateAnyObject.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.Object> enumerateAnyObject(
    ) {
        return java.util.Collections.enumeration(this._anyObject);
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

        if (obj instanceof SubjectConfirmationDataType) {

            SubjectConfirmationDataType temp = (SubjectConfirmationDataType)obj;
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
            if (this._recipient != null) {
                if (temp._recipient == null) return false;
                if (this._recipient != temp._recipient) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._recipient);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._recipient);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recipient); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recipient); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._recipient.equals(temp._recipient)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recipient);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recipient);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._recipient);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._recipient);
                    }
                }
            } else if (temp._recipient != null)
                return false;
            if (this._inResponseTo != null) {
                if (temp._inResponseTo == null) return false;
                if (this._inResponseTo != temp._inResponseTo) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._inResponseTo);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._inResponseTo);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._inResponseTo); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._inResponseTo); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._inResponseTo.equals(temp._inResponseTo)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._inResponseTo);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._inResponseTo);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._inResponseTo);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._inResponseTo);
                    }
                }
            } else if (temp._inResponseTo != null)
                return false;
            if (this._address != null) {
                if (temp._address == null) return false;
                if (this._address != temp._address) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._address);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._address);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._address); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._address); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._address.equals(temp._address)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._address);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._address);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._address);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._address);
                    }
                }
            } else if (temp._address != null)
                return false;
            if (this._content != null) {
                if (temp._content == null) return false;
                if (this._content != temp._content) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._content);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._content);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._content.equals(temp._content)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content);
                    }
                }
            } else if (temp._content != null)
                return false;
            if (this._anyObject != null) {
                if (temp._anyObject == null) return false;
                if (this._anyObject != temp._anyObject) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._anyObject);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._anyObject);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._anyObject.equals(temp._anyObject)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject);
                    }
                }
            } else if (temp._anyObject != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'address'.
     * 
     * @return the value of field 'Address'.
     */
    public java.lang.String getAddress(
    ) {
        return this._address;
    }

    /**
     * Method getAnyObject.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.Object at the given index
     */
    public java.lang.Object getAnyObject(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._anyObject.size()) {
            throw new IndexOutOfBoundsException("getAnyObject: Index value '" + index + "' not in range [0.." + (this._anyObject.size() - 1) + "]");
        }

        return (java.lang.Object) _anyObject.get(index);
    }

    /**
     * Method getAnyObject.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.Object[] getAnyObject(
    ) {
        java.lang.Object[] array = new java.lang.Object[0];
        return (java.lang.Object[]) this._anyObject.toArray(array);
    }

    /**
     * Method getAnyObjectCount.
     * 
     * @return the size of this collection
     */
    public int getAnyObjectCount(
    ) {
        return this._anyObject.size();
    }

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return the value of field 'Content'.
     */
    public java.lang.String getContent(
    ) {
        return this._content;
    }

    /**
     * Returns the value of field 'inResponseTo'.
     * 
     * @return the value of field 'InResponseTo'.
     */
    public java.lang.String getInResponseTo(
    ) {
        return this._inResponseTo;
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
     * Returns the value of field 'recipient'.
     * 
     * @return the value of field 'Recipient'.
     */
    public java.lang.String getRecipient(
    ) {
        return this._recipient;
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
        if (_recipient != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_recipient)) {
           result = 37 * result + _recipient.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_recipient);
        }
        if (_inResponseTo != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_inResponseTo)) {
           result = 37 * result + _inResponseTo.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_inResponseTo);
        }
        if (_address != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_address)) {
           result = 37 * result + _address.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_address);
        }
        if (_content != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_content)) {
           result = 37 * result + _content.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_content);
        }
        if (_anyObject != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_anyObject)) {
           result = 37 * result + _anyObject.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_anyObject);
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
     * Method iterateAnyObject.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.Object> iterateAnyObject(
    ) {
        return this._anyObject.iterator();
    }

    /**
     */
    public void removeAllAnyObject(
    ) {
        this._anyObject.clear();
    }

    /**
     * Method removeAnyObject.
     * 
     * @param vAnyObject
     * @return true if the object was removed from the collection.
     */
    public boolean removeAnyObject(
            final java.lang.Object vAnyObject) {
        boolean removed = _anyObject.remove(vAnyObject);
        return removed;
    }

    /**
     * Method removeAnyObjectAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.Object removeAnyObjectAt(
            final int index) {
        java.lang.Object obj = this._anyObject.remove(index);
        return (java.lang.Object) obj;
    }

    /**
     * Sets the value of field 'address'.
     * 
     * @param address the value of field 'address'.
     */
    public void setAddress(
            final java.lang.String address) {
        this._address = address;
    }

    /**
     * 
     * 
     * @param index
     * @param vAnyObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAnyObject(
            final int index,
            final java.lang.Object vAnyObject)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._anyObject.size()) {
            throw new IndexOutOfBoundsException("setAnyObject: Index value '" + index + "' not in range [0.." + (this._anyObject.size() - 1) + "]");
        }

        this._anyObject.set(index, vAnyObject);
    }

    /**
     * 
     * 
     * @param vAnyObjectArray
     */
    public void setAnyObject(
            final java.lang.Object[] vAnyObjectArray) {
        //-- copy array
        _anyObject.clear();

        for (int i = 0; i < vAnyObjectArray.length; i++) {
                this._anyObject.add(vAnyObjectArray[i]);
        }
    }

    /**
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(
            final java.lang.String content) {
        this._content = content;
    }

    /**
     * Sets the value of field 'inResponseTo'.
     * 
     * @param inResponseTo the value of field 'inResponseTo'.
     */
    public void setInResponseTo(
            final java.lang.String inResponseTo) {
        this._inResponseTo = inResponseTo;
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
     * Sets the value of field 'recipient'.
     * 
     * @param recipient the value of field 'recipient'.
     */
    public void setRecipient(
            final java.lang.String recipient) {
        this._recipient = recipient;
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
