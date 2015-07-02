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
 * Class Certificates.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Certificates implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _certificateList.
     */
    private java.util.List<byte[]> _certificateList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Certificates() {
        super();
        this._certificateList = new java.util.ArrayList<byte[]>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCertificate
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCertificate(
            final byte[] vCertificate)
    throws java.lang.IndexOutOfBoundsException {
        this._certificateList.add(vCertificate);
    }

    /**
     * 
     * 
     * @param index
     * @param vCertificate
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCertificate(
            final int index,
            final byte[] vCertificate)
    throws java.lang.IndexOutOfBoundsException {
        this._certificateList.add(index, vCertificate);
    }

    /**
     * Method enumerateCertificate.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends byte[]> enumerateCertificate(
    ) {
        return java.util.Collections.enumeration(this._certificateList);
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

        if (obj instanceof Certificates) {

            Certificates temp = (Certificates)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._certificateList != null) {
                if (temp._certificateList == null) return false;
                if (this._certificateList != temp._certificateList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._certificateList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._certificateList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._certificateList.equals(temp._certificateList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateList);
                    }
                }
            } else if (temp._certificateList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getCertificate.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the byte[] at the given index
     */
    public byte[] getCertificate(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._certificateList.size()) {
            throw new IndexOutOfBoundsException("getCertificate: Index value '" + index + "' not in range [0.." + (this._certificateList.size() - 1) + "]");
        }

        return (byte[]) _certificateList.get(index);
    }

    /**
     * Method getCertificate.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public byte[][] getCertificate(
    ) {
        byte[][] array = new byte[0][];
        return (byte[][]) this._certificateList.toArray(array);
    }

    /**
     * Method getCertificateCount.
     * 
     * @return the size of this collection
     */
    public int getCertificateCount(
    ) {
        return this._certificateList.size();
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
        if (_certificateList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_certificateList)) {
           result = 37 * result + _certificateList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_certificateList);
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
     * Method iterateCertificate.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends byte[]> iterateCertificate(
    ) {
        return this._certificateList.iterator();
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
    public void removeAllCertificate(
    ) {
        this._certificateList.clear();
    }

    /**
     * Method removeCertificate.
     * 
     * @param vCertificate
     * @return true if the object was removed from the collection.
     */
    public boolean removeCertificate(
            final byte[] vCertificate) {
        boolean removed = _certificateList.remove(vCertificate);
        return removed;
    }

    /**
     * Method removeCertificateAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public byte[] removeCertificateAt(
            final int index) {
        java.lang.Object obj = this._certificateList.remove(index);
        return (byte[]) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vCertificate
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCertificate(
            final int index,
            final byte[] vCertificate)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._certificateList.size()) {
            throw new IndexOutOfBoundsException("setCertificate: Index value '" + index + "' not in range [0.." + (this._certificateList.size() - 1) + "]");
        }

        this._certificateList.set(index, vCertificate);
    }

    /**
     * 
     * 
     * @param vCertificateArray
     */
    public void setCertificate(
            final byte[][] vCertificateArray) {
        //-- copy array
        _certificateList.clear();

        for (int i = 0; i < vCertificateArray.length; i++) {
                this._certificateList.add(vCertificateArray[i]);
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
     * org.etsi.uri.TS102204.v1_1_2.Certificates
     */
    public static org.etsi.uri.TS102204.v1_1_2.Certificates unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.etsi.uri.TS102204.v1_1_2.Certificates) org.exolab.castor.xml.Unmarshaller.unmarshal(org.etsi.uri.TS102204.v1_1_2.Certificates.class, reader);
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
