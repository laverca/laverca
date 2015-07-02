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

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class RootCAs.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class RootCAs implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _DNList.
     */
    private java.util.List<java.lang.String> _DNList;


      //----------------/
     //- Constructors -/
    //----------------/

    public RootCAs() {
        super();
        this._DNList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vDN
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDN(
            final java.lang.String vDN)
    throws java.lang.IndexOutOfBoundsException {
        this._DNList.add(vDN);
    }

    /**
     * 
     * 
     * @param index
     * @param vDN
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDN(
            final int index,
            final java.lang.String vDN)
    throws java.lang.IndexOutOfBoundsException {
        this._DNList.add(index, vDN);
    }

    /**
     * Method enumerateDN.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateDN(
    ) {
        return java.util.Collections.enumeration(this._DNList);
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

        if (obj instanceof RootCAs) {

            RootCAs temp = (RootCAs)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._DNList != null) {
                if (temp._DNList == null) return false;
                if (this._DNList != temp._DNList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._DNList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._DNList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DNList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DNList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._DNList.equals(temp._DNList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DNList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DNList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DNList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DNList);
                    }
                }
            } else if (temp._DNList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getDN.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getDN(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._DNList.size()) {
            throw new IndexOutOfBoundsException("getDN: Index value '" + index + "' not in range [0.." + (this._DNList.size() - 1) + "]");
        }

        return (java.lang.String) _DNList.get(index);
    }

    /**
     * Method getDN.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getDN(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._DNList.toArray(array);
    }

    /**
     * Method getDNCount.
     * 
     * @return the size of this collection
     */
    public int getDNCount(
    ) {
        return this._DNList.size();
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
        if (_DNList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_DNList)) {
           result = 37 * result + _DNList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_DNList);
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
     * Method iterateDN.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateDN(
    ) {
        return this._DNList.iterator();
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
    public void removeAllDN(
    ) {
        this._DNList.clear();
    }

    /**
     * Method removeDN.
     * 
     * @param vDN
     * @return true if the object was removed from the collection.
     */
    public boolean removeDN(
            final java.lang.String vDN) {
        boolean removed = _DNList.remove(vDN);
        return removed;
    }

    /**
     * Method removeDNAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeDNAt(
            final int index) {
        java.lang.Object obj = this._DNList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vDN
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDN(
            final int index,
            final java.lang.String vDN)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._DNList.size()) {
            throw new IndexOutOfBoundsException("setDN: Index value '" + index + "' not in range [0.." + (this._DNList.size() - 1) + "]");
        }

        this._DNList.set(index, vDN);
    }

    /**
     * 
     * 
     * @param vDNArray
     */
    public void setDN(
            final java.lang.String[] vDNArray) {
        //-- copy array
        _DNList.clear();

        for (int i = 0; i < vDNArray.length; i++) {
                this._DNList.add(vDNArray[i]);
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
     * @return the unmarshaled org.etsi.uri.TS102204.v1_1_2.RootCAs
     */
    public static org.etsi.uri.TS102204.v1_1_2.RootCAs unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.etsi.uri.TS102204.v1_1_2.RootCAs) org.exolab.castor.xml.Unmarshaller.unmarshal(org.etsi.uri.TS102204.v1_1_2.RootCAs.class, reader);
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
