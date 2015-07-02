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
 * Class KeyReferenceType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class KeyReferenceType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _certificateURLList.
     */
    private java.util.List<java.lang.String> _certificateURLList;

    /**
     * Field _certificateIssuerDNList.
     */
    private java.util.List<java.lang.String> _certificateIssuerDNList;

    /**
     * Field _hashOfUsersPublicKeyList.
     */
    private java.util.List<org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey> _hashOfUsersPublicKeyList;

    /**
     * Field _hashOfCAPublicKeyList.
     */
    private java.util.List<org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey> _hashOfCAPublicKeyList;

    /**
     * Field _anyObject.
     */
    private java.lang.Object _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public KeyReferenceType() {
        super();
        this._certificateURLList = new java.util.ArrayList<java.lang.String>();
        this._certificateIssuerDNList = new java.util.ArrayList<java.lang.String>();
        this._hashOfUsersPublicKeyList = new java.util.ArrayList<org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey>();
        this._hashOfCAPublicKeyList = new java.util.ArrayList<org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCertificateIssuerDN
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCertificateIssuerDN(
            final java.lang.String vCertificateIssuerDN)
    throws java.lang.IndexOutOfBoundsException {
        this._certificateIssuerDNList.add(vCertificateIssuerDN);
    }

    /**
     * 
     * 
     * @param index
     * @param vCertificateIssuerDN
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCertificateIssuerDN(
            final int index,
            final java.lang.String vCertificateIssuerDN)
    throws java.lang.IndexOutOfBoundsException {
        this._certificateIssuerDNList.add(index, vCertificateIssuerDN);
    }

    /**
     * 
     * 
     * @param vCertificateURL
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCertificateURL(
            final java.lang.String vCertificateURL)
    throws java.lang.IndexOutOfBoundsException {
        this._certificateURLList.add(vCertificateURL);
    }

    /**
     * 
     * 
     * @param index
     * @param vCertificateURL
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCertificateURL(
            final int index,
            final java.lang.String vCertificateURL)
    throws java.lang.IndexOutOfBoundsException {
        this._certificateURLList.add(index, vCertificateURL);
    }

    /**
     * 
     * 
     * @param vHashOfCAPublicKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addHashOfCAPublicKey(
            final org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey vHashOfCAPublicKey)
    throws java.lang.IndexOutOfBoundsException {
        this._hashOfCAPublicKeyList.add(vHashOfCAPublicKey);
    }

    /**
     * 
     * 
     * @param index
     * @param vHashOfCAPublicKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addHashOfCAPublicKey(
            final int index,
            final org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey vHashOfCAPublicKey)
    throws java.lang.IndexOutOfBoundsException {
        this._hashOfCAPublicKeyList.add(index, vHashOfCAPublicKey);
    }

    /**
     * 
     * 
     * @param vHashOfUsersPublicKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addHashOfUsersPublicKey(
            final org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey vHashOfUsersPublicKey)
    throws java.lang.IndexOutOfBoundsException {
        this._hashOfUsersPublicKeyList.add(vHashOfUsersPublicKey);
    }

    /**
     * 
     * 
     * @param index
     * @param vHashOfUsersPublicKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addHashOfUsersPublicKey(
            final int index,
            final org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey vHashOfUsersPublicKey)
    throws java.lang.IndexOutOfBoundsException {
        this._hashOfUsersPublicKeyList.add(index, vHashOfUsersPublicKey);
    }

    /**
     * Method enumerateCertificateIssuerDN.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateCertificateIssuerDN(
    ) {
        return java.util.Collections.enumeration(this._certificateIssuerDNList);
    }

    /**
     * Method enumerateCertificateURL.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateCertificateURL(
    ) {
        return java.util.Collections.enumeration(this._certificateURLList);
    }

    /**
     * Method enumerateHashOfCAPublicKey.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey> enumerateHashOfCAPublicKey(
    ) {
        return java.util.Collections.enumeration(this._hashOfCAPublicKeyList);
    }

    /**
     * Method enumerateHashOfUsersPublicKey.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey> enumerateHashOfUsersPublicKey(
    ) {
        return java.util.Collections.enumeration(this._hashOfUsersPublicKeyList);
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

        if (obj instanceof KeyReferenceType) {

            KeyReferenceType temp = (KeyReferenceType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._certificateURLList != null) {
                if (temp._certificateURLList == null) return false;
                if (this._certificateURLList != temp._certificateURLList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._certificateURLList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._certificateURLList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateURLList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateURLList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._certificateURLList.equals(temp._certificateURLList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateURLList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateURLList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateURLList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateURLList);
                    }
                }
            } else if (temp._certificateURLList != null)
                return false;
            if (this._certificateIssuerDNList != null) {
                if (temp._certificateIssuerDNList == null) return false;
                if (this._certificateIssuerDNList != temp._certificateIssuerDNList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._certificateIssuerDNList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._certificateIssuerDNList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateIssuerDNList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateIssuerDNList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._certificateIssuerDNList.equals(temp._certificateIssuerDNList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateIssuerDNList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateIssuerDNList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._certificateIssuerDNList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._certificateIssuerDNList);
                    }
                }
            } else if (temp._certificateIssuerDNList != null)
                return false;
            if (this._hashOfUsersPublicKeyList != null) {
                if (temp._hashOfUsersPublicKeyList == null) return false;
                if (this._hashOfUsersPublicKeyList != temp._hashOfUsersPublicKeyList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._hashOfUsersPublicKeyList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._hashOfUsersPublicKeyList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._hashOfUsersPublicKeyList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._hashOfUsersPublicKeyList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._hashOfUsersPublicKeyList.equals(temp._hashOfUsersPublicKeyList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._hashOfUsersPublicKeyList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._hashOfUsersPublicKeyList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._hashOfUsersPublicKeyList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._hashOfUsersPublicKeyList);
                    }
                }
            } else if (temp._hashOfUsersPublicKeyList != null)
                return false;
            if (this._hashOfCAPublicKeyList != null) {
                if (temp._hashOfCAPublicKeyList == null) return false;
                if (this._hashOfCAPublicKeyList != temp._hashOfCAPublicKeyList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._hashOfCAPublicKeyList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._hashOfCAPublicKeyList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._hashOfCAPublicKeyList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._hashOfCAPublicKeyList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._hashOfCAPublicKeyList.equals(temp._hashOfCAPublicKeyList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._hashOfCAPublicKeyList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._hashOfCAPublicKeyList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._hashOfCAPublicKeyList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._hashOfCAPublicKeyList);
                    }
                }
            } else if (temp._hashOfCAPublicKeyList != null)
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
     * Returns the value of field 'anyObject'.
     * 
     * @return the value of field 'AnyObject'.
     */
    public java.lang.Object getAnyObject(
    ) {
        return this._anyObject;
    }

    /**
     * Method getCertificateIssuerDN.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getCertificateIssuerDN(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._certificateIssuerDNList.size()) {
            throw new IndexOutOfBoundsException("getCertificateIssuerDN: Index value '" + index + "' not in range [0.." + (this._certificateIssuerDNList.size() - 1) + "]");
        }

        return (java.lang.String) _certificateIssuerDNList.get(index);
    }

    /**
     * Method getCertificateIssuerDN.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getCertificateIssuerDN(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._certificateIssuerDNList.toArray(array);
    }

    /**
     * Method getCertificateIssuerDNCount.
     * 
     * @return the size of this collection
     */
    public int getCertificateIssuerDNCount(
    ) {
        return this._certificateIssuerDNList.size();
    }

    /**
     * Method getCertificateURL.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getCertificateURL(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._certificateURLList.size()) {
            throw new IndexOutOfBoundsException("getCertificateURL: Index value '" + index + "' not in range [0.." + (this._certificateURLList.size() - 1) + "]");
        }

        return (java.lang.String) _certificateURLList.get(index);
    }

    /**
     * Method getCertificateURL.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getCertificateURL(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._certificateURLList.toArray(array);
    }

    /**
     * Method getCertificateURLCount.
     * 
     * @return the size of this collection
     */
    public int getCertificateURLCount(
    ) {
        return this._certificateURLList.size();
    }

    /**
     * Method getHashOfCAPublicKey.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey at the given
     * index
     */
    public org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey getHashOfCAPublicKey(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._hashOfCAPublicKeyList.size()) {
            throw new IndexOutOfBoundsException("getHashOfCAPublicKey: Index value '" + index + "' not in range [0.." + (this._hashOfCAPublicKeyList.size() - 1) + "]");
        }

        return (org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey) _hashOfCAPublicKeyList.get(index);
    }

    /**
     * Method getHashOfCAPublicKey.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey[] getHashOfCAPublicKey(
    ) {
        org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey[] array = new org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey[0];
        return (org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey[]) this._hashOfCAPublicKeyList.toArray(array);
    }

    /**
     * Method getHashOfCAPublicKeyCount.
     * 
     * @return the size of this collection
     */
    public int getHashOfCAPublicKeyCount(
    ) {
        return this._hashOfCAPublicKeyList.size();
    }

    /**
     * Method getHashOfUsersPublicKey.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey at the
     * given index
     */
    public org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey getHashOfUsersPublicKey(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._hashOfUsersPublicKeyList.size()) {
            throw new IndexOutOfBoundsException("getHashOfUsersPublicKey: Index value '" + index + "' not in range [0.." + (this._hashOfUsersPublicKeyList.size() - 1) + "]");
        }

        return (org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey) _hashOfUsersPublicKeyList.get(index);
    }

    /**
     * Method getHashOfUsersPublicKey.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey[] getHashOfUsersPublicKey(
    ) {
        org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey[] array = new org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey[0];
        return (org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey[]) this._hashOfUsersPublicKeyList.toArray(array);
    }

    /**
     * Method getHashOfUsersPublicKeyCount.
     * 
     * @return the size of this collection
     */
    public int getHashOfUsersPublicKeyCount(
    ) {
        return this._hashOfUsersPublicKeyList.size();
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
        if (_certificateURLList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_certificateURLList)) {
           result = 37 * result + _certificateURLList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_certificateURLList);
        }
        if (_certificateIssuerDNList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_certificateIssuerDNList)) {
           result = 37 * result + _certificateIssuerDNList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_certificateIssuerDNList);
        }
        if (_hashOfUsersPublicKeyList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_hashOfUsersPublicKeyList)) {
           result = 37 * result + _hashOfUsersPublicKeyList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_hashOfUsersPublicKeyList);
        }
        if (_hashOfCAPublicKeyList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_hashOfCAPublicKeyList)) {
           result = 37 * result + _hashOfCAPublicKeyList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_hashOfCAPublicKeyList);
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
     * Method iterateCertificateIssuerDN.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateCertificateIssuerDN(
    ) {
        return this._certificateIssuerDNList.iterator();
    }

    /**
     * Method iterateCertificateURL.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateCertificateURL(
    ) {
        return this._certificateURLList.iterator();
    }

    /**
     * Method iterateHashOfCAPublicKey.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey> iterateHashOfCAPublicKey(
    ) {
        return this._hashOfCAPublicKeyList.iterator();
    }

    /**
     * Method iterateHashOfUsersPublicKey.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey> iterateHashOfUsersPublicKey(
    ) {
        return this._hashOfUsersPublicKeyList.iterator();
    }

    /**
     */
    public void removeAllCertificateIssuerDN(
    ) {
        this._certificateIssuerDNList.clear();
    }

    /**
     */
    public void removeAllCertificateURL(
    ) {
        this._certificateURLList.clear();
    }

    /**
     */
    public void removeAllHashOfCAPublicKey(
    ) {
        this._hashOfCAPublicKeyList.clear();
    }

    /**
     */
    public void removeAllHashOfUsersPublicKey(
    ) {
        this._hashOfUsersPublicKeyList.clear();
    }

    /**
     * Method removeCertificateIssuerDN.
     * 
     * @param vCertificateIssuerDN
     * @return true if the object was removed from the collection.
     */
    public boolean removeCertificateIssuerDN(
            final java.lang.String vCertificateIssuerDN) {
        boolean removed = _certificateIssuerDNList.remove(vCertificateIssuerDN);
        return removed;
    }

    /**
     * Method removeCertificateIssuerDNAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeCertificateIssuerDNAt(
            final int index) {
        java.lang.Object obj = this._certificateIssuerDNList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Method removeCertificateURL.
     * 
     * @param vCertificateURL
     * @return true if the object was removed from the collection.
     */
    public boolean removeCertificateURL(
            final java.lang.String vCertificateURL) {
        boolean removed = _certificateURLList.remove(vCertificateURL);
        return removed;
    }

    /**
     * Method removeCertificateURLAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeCertificateURLAt(
            final int index) {
        java.lang.Object obj = this._certificateURLList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Method removeHashOfCAPublicKey.
     * 
     * @param vHashOfCAPublicKey
     * @return true if the object was removed from the collection.
     */
    public boolean removeHashOfCAPublicKey(
            final org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey vHashOfCAPublicKey) {
        boolean removed = _hashOfCAPublicKeyList.remove(vHashOfCAPublicKey);
        return removed;
    }

    /**
     * Method removeHashOfCAPublicKeyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey removeHashOfCAPublicKeyAt(
            final int index) {
        java.lang.Object obj = this._hashOfCAPublicKeyList.remove(index);
        return (org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey) obj;
    }

    /**
     * Method removeHashOfUsersPublicKey.
     * 
     * @param vHashOfUsersPublicKey
     * @return true if the object was removed from the collection.
     */
    public boolean removeHashOfUsersPublicKey(
            final org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey vHashOfUsersPublicKey) {
        boolean removed = _hashOfUsersPublicKeyList.remove(vHashOfUsersPublicKey);
        return removed;
    }

    /**
     * Method removeHashOfUsersPublicKeyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey removeHashOfUsersPublicKeyAt(
            final int index) {
        java.lang.Object obj = this._hashOfUsersPublicKeyList.remove(index);
        return (org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey) obj;
    }

    /**
     * Sets the value of field 'anyObject'.
     * 
     * @param anyObject the value of field 'anyObject'.
     */
    public void setAnyObject(
            final java.lang.Object anyObject) {
        this._anyObject = anyObject;
    }

    /**
     * 
     * 
     * @param index
     * @param vCertificateIssuerDN
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCertificateIssuerDN(
            final int index,
            final java.lang.String vCertificateIssuerDN)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._certificateIssuerDNList.size()) {
            throw new IndexOutOfBoundsException("setCertificateIssuerDN: Index value '" + index + "' not in range [0.." + (this._certificateIssuerDNList.size() - 1) + "]");
        }

        this._certificateIssuerDNList.set(index, vCertificateIssuerDN);
    }

    /**
     * 
     * 
     * @param vCertificateIssuerDNArray
     */
    public void setCertificateIssuerDN(
            final java.lang.String[] vCertificateIssuerDNArray) {
        //-- copy array
        _certificateIssuerDNList.clear();

        for (int i = 0; i < vCertificateIssuerDNArray.length; i++) {
                this._certificateIssuerDNList.add(vCertificateIssuerDNArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vCertificateURL
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCertificateURL(
            final int index,
            final java.lang.String vCertificateURL)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._certificateURLList.size()) {
            throw new IndexOutOfBoundsException("setCertificateURL: Index value '" + index + "' not in range [0.." + (this._certificateURLList.size() - 1) + "]");
        }

        this._certificateURLList.set(index, vCertificateURL);
    }

    /**
     * 
     * 
     * @param vCertificateURLArray
     */
    public void setCertificateURL(
            final java.lang.String[] vCertificateURLArray) {
        //-- copy array
        _certificateURLList.clear();

        for (int i = 0; i < vCertificateURLArray.length; i++) {
                this._certificateURLList.add(vCertificateURLArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vHashOfCAPublicKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setHashOfCAPublicKey(
            final int index,
            final org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey vHashOfCAPublicKey)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._hashOfCAPublicKeyList.size()) {
            throw new IndexOutOfBoundsException("setHashOfCAPublicKey: Index value '" + index + "' not in range [0.." + (this._hashOfCAPublicKeyList.size() - 1) + "]");
        }

        this._hashOfCAPublicKeyList.set(index, vHashOfCAPublicKey);
    }

    /**
     * 
     * 
     * @param vHashOfCAPublicKeyArray
     */
    public void setHashOfCAPublicKey(
            final org.etsi.uri.TS102204.v1_1_2.HashOfCAPublicKey[] vHashOfCAPublicKeyArray) {
        //-- copy array
        _hashOfCAPublicKeyList.clear();

        for (int i = 0; i < vHashOfCAPublicKeyArray.length; i++) {
                this._hashOfCAPublicKeyList.add(vHashOfCAPublicKeyArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vHashOfUsersPublicKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setHashOfUsersPublicKey(
            final int index,
            final org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey vHashOfUsersPublicKey)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._hashOfUsersPublicKeyList.size()) {
            throw new IndexOutOfBoundsException("setHashOfUsersPublicKey: Index value '" + index + "' not in range [0.." + (this._hashOfUsersPublicKeyList.size() - 1) + "]");
        }

        this._hashOfUsersPublicKeyList.set(index, vHashOfUsersPublicKey);
    }

    /**
     * 
     * 
     * @param vHashOfUsersPublicKeyArray
     */
    public void setHashOfUsersPublicKey(
            final org.etsi.uri.TS102204.v1_1_2.HashOfUsersPublicKey[] vHashOfUsersPublicKeyArray) {
        //-- copy array
        _hashOfUsersPublicKeyList.clear();

        for (int i = 0; i < vHashOfUsersPublicKeyArray.length; i++) {
                this._hashOfUsersPublicKeyList.add(vHashOfUsersPublicKeyArray[i]);
        }
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
