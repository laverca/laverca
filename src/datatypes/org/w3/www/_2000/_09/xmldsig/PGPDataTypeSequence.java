/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2014 Laverca Project
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

package org.w3.www._2000._09.xmldsig;

/**
 * Class PGPDataTypeSequence.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class PGPDataTypeSequence implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _PGPKeyID.
     */
    private byte[] _PGPKeyID;

    /**
     * Field _PGPKeyPacket.
     */
    private byte[] _PGPKeyPacket;

    /**
     * Field _anyObject.
     */
    private java.util.List<java.lang.Object> _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public PGPDataTypeSequence() {
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

        if (obj instanceof PGPDataTypeSequence) {

            PGPDataTypeSequence temp = (PGPDataTypeSequence)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._PGPKeyID != null) {
                if (temp._PGPKeyID == null) return false;
                if (this._PGPKeyID != temp._PGPKeyID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._PGPKeyID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._PGPKeyID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPKeyID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPKeyID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._PGPKeyID, temp._PGPKeyID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPKeyID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPKeyID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPKeyID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPKeyID);
                    }
                }
            } else if (temp._PGPKeyID != null)
                return false;
            if (this._PGPKeyPacket != null) {
                if (temp._PGPKeyPacket == null) return false;
                if (this._PGPKeyPacket != temp._PGPKeyPacket) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._PGPKeyPacket);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._PGPKeyPacket);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPKeyPacket); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPKeyPacket); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._PGPKeyPacket, temp._PGPKeyPacket)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPKeyPacket);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPKeyPacket);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._PGPKeyPacket);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._PGPKeyPacket);
                    }
                }
            } else if (temp._PGPKeyPacket != null)
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
     * Returns the value of field 'PGPKeyID'.
     * 
     * @return the value of field 'PGPKeyID'.
     */
    public byte[] getPGPKeyID(
    ) {
        return this._PGPKeyID;
    }

    /**
     * Returns the value of field 'PGPKeyPacket'.
     * 
     * @return the value of field 'PGPKeyPacket'.
     */
    public byte[] getPGPKeyPacket(
    ) {
        return this._PGPKeyPacket;
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
        if (_PGPKeyID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_PGPKeyID)) {
           result = 37 * result + _PGPKeyID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_PGPKeyID);
        }
        if (_PGPKeyPacket != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_PGPKeyPacket)) {
           result = 37 * result + _PGPKeyPacket.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_PGPKeyPacket);
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
     * Sets the value of field 'PGPKeyID'.
     * 
     * @param PGPKeyID the value of field 'PGPKeyID'.
     */
    public void setPGPKeyID(
            final byte[] PGPKeyID) {
        this._PGPKeyID = PGPKeyID;
    }

    /**
     * Sets the value of field 'PGPKeyPacket'.
     * 
     * @param PGPKeyPacket the value of field 'PGPKeyPacket'.
     */
    public void setPGPKeyPacket(
            final byte[] PGPKeyPacket) {
        this._PGPKeyPacket = PGPKeyPacket;
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
     * org.w3.www._2000._09.xmldsig.PGPDataTypeSequence
     */
    public static org.w3.www._2000._09.xmldsig.PGPDataTypeSequence unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.w3.www._2000._09.xmldsig.PGPDataTypeSequence) org.exolab.castor.xml.Unmarshaller.unmarshal(org.w3.www._2000._09.xmldsig.PGPDataTypeSequence.class, reader);
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
