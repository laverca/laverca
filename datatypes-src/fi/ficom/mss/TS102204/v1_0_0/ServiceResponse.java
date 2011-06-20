/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package fi.ficom.mss.TS102204.v1_0_0;

/**
 * Class ServiceResponse.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ServiceResponse implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _description.
     */
    private fi.ficom.mss.TS102204.v1_0_0.Description _description;

    /**
     * Field _entity.
     */
    private fi.ficom.mss.TS102204.v1_0_0.Entity _entity;

    /**
     * Field _status.
     */
    private fi.ficom.mss.TS102204.v1_0_0.Status _status;

    /**
     * Field _response.
     */
    private oasis.names.tc.SAML.v2_0.protocol.Response _response;

    /**
     * Field _anyObject.
     */
    private java.util.List<java.lang.Object> _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public ServiceResponse() {
        super();
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

        if (obj instanceof ServiceResponse) {

            ServiceResponse temp = (ServiceResponse)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._description != null) {
                if (temp._description == null) return false;
                if (this._description != temp._description) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._description);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._description);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._description); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._description); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._description.equals(temp._description)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._description);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._description);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._description);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._description);
                    }
                }
            } else if (temp._description != null)
                return false;
            if (this._entity != null) {
                if (temp._entity == null) return false;
                if (this._entity != temp._entity) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._entity);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._entity);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._entity); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._entity); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._entity.equals(temp._entity)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._entity);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._entity);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._entity);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._entity);
                    }
                }
            } else if (temp._entity != null)
                return false;
            if (this._status != null) {
                if (temp._status == null) return false;
                if (this._status != temp._status) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._status);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._status);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._status.equals(temp._status)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
                    }
                }
            } else if (temp._status != null)
                return false;
            if (this._response != null) {
                if (temp._response == null) return false;
                if (this._response != temp._response) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._response);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._response);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._response); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._response); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._response.equals(temp._response)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._response);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._response);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._response);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._response);
                    }
                }
            } else if (temp._response != null)
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
     * Returns the value of field 'description'.
     * 
     * @return the value of field 'Description'.
     */
    public fi.ficom.mss.TS102204.v1_0_0.Description getDescription(
    ) {
        return this._description;
    }

    /**
     * Returns the value of field 'entity'.
     * 
     * @return the value of field 'Entity'.
     */
    public fi.ficom.mss.TS102204.v1_0_0.Entity getEntity(
    ) {
        return this._entity;
    }

    /**
     * Returns the value of field 'response'.
     * 
     * @return the value of field 'Response'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.Response getResponse(
    ) {
        return this._response;
    }

    /**
     * Returns the value of field 'status'.
     * 
     * @return the value of field 'Status'.
     */
    public fi.ficom.mss.TS102204.v1_0_0.Status getStatus(
    ) {
        return this._status;
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
        if (_description != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_description)) {
           result = 37 * result + _description.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_description);
        }
        if (_entity != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_entity)) {
           result = 37 * result + _entity.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_entity);
        }
        if (_status != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_status)) {
           result = 37 * result + _status.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_status);
        }
        if (_response != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_response)) {
           result = 37 * result + _response.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_response);
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
     * Sets the value of field 'description'.
     * 
     * @param description the value of field 'description'.
     */
    public void setDescription(
            final fi.ficom.mss.TS102204.v1_0_0.Description description) {
        this._description = description;
    }

    /**
     * Sets the value of field 'entity'.
     * 
     * @param entity the value of field 'entity'.
     */
    public void setEntity(
            final fi.ficom.mss.TS102204.v1_0_0.Entity entity) {
        this._entity = entity;
    }

    /**
     * Sets the value of field 'response'.
     * 
     * @param response the value of field 'response'.
     */
    public void setResponse(
            final oasis.names.tc.SAML.v2_0.protocol.Response response) {
        this._response = response;
    }

    /**
     * Sets the value of field 'status'.
     * 
     * @param status the value of field 'status'.
     */
    public void setStatus(
            final fi.ficom.mss.TS102204.v1_0_0.Status status) {
        this._status = status;
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
     * fi.ficom.mss.TS102204.v1_0_0.ServiceResponse
     */
    public static fi.ficom.mss.TS102204.v1_0_0.ServiceResponse unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (fi.ficom.mss.TS102204.v1_0_0.ServiceResponse) org.exolab.castor.xml.Unmarshaller.unmarshal(fi.ficom.mss.TS102204.v1_0_0.ServiceResponse.class, reader);
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
