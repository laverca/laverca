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

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class ScopingType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ScopingType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _proxyCount.
     */
    private long _proxyCount;

    /**
     * keeps track of state for field: _proxyCount
     */
    private boolean _has_proxyCount;

    /**
     * Field _IDPList.
     */
    private oasis.names.tc.SAML.v2_0.protocol.IDPList _IDPList;

    /**
     * Field _requesterIDList.
     */
    private java.util.List<java.lang.String> _requesterIDList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ScopingType() {
        super();
        this._requesterIDList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vRequesterID
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRequesterID(
            final java.lang.String vRequesterID)
    throws java.lang.IndexOutOfBoundsException {
        this._requesterIDList.add(vRequesterID);
    }

    /**
     * 
     * 
     * @param index
     * @param vRequesterID
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRequesterID(
            final int index,
            final java.lang.String vRequesterID)
    throws java.lang.IndexOutOfBoundsException {
        this._requesterIDList.add(index, vRequesterID);
    }

    /**
     */
    public void deleteProxyCount(
    ) {
        this._has_proxyCount= false;
    }

    /**
     * Method enumerateRequesterID.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateRequesterID(
    ) {
        return java.util.Collections.enumeration(this._requesterIDList);
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

        if (obj instanceof ScopingType) {

            ScopingType temp = (ScopingType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._proxyCount != temp._proxyCount)
                return false;
            if (this._has_proxyCount != temp._has_proxyCount)
                return false;
            if (this._IDPList != null) {
                if (temp._IDPList == null) return false;
                if (this._IDPList != temp._IDPList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._IDPList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._IDPList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._IDPList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._IDPList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._IDPList.equals(temp._IDPList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._IDPList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._IDPList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._IDPList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._IDPList);
                    }
                }
            } else if (temp._IDPList != null)
                return false;
            if (this._requesterIDList != null) {
                if (temp._requesterIDList == null) return false;
                if (this._requesterIDList != temp._requesterIDList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._requesterIDList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._requesterIDList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._requesterIDList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._requesterIDList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._requesterIDList.equals(temp._requesterIDList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._requesterIDList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._requesterIDList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._requesterIDList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._requesterIDList);
                    }
                }
            } else if (temp._requesterIDList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'IDPList'.
     * 
     * @return the value of field 'IDPList'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.IDPList getIDPList(
    ) {
        return this._IDPList;
    }

    /**
     * Returns the value of field 'proxyCount'.
     * 
     * @return the value of field 'ProxyCount'.
     */
    public long getProxyCount(
    ) {
        return this._proxyCount;
    }

    /**
     * Method getRequesterID.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getRequesterID(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._requesterIDList.size()) {
            throw new IndexOutOfBoundsException("getRequesterID: Index value '" + index + "' not in range [0.." + (this._requesterIDList.size() - 1) + "]");
        }

        return (java.lang.String) _requesterIDList.get(index);
    }

    /**
     * Method getRequesterID.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getRequesterID(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._requesterIDList.toArray(array);
    }

    /**
     * Method getRequesterIDCount.
     * 
     * @return the size of this collection
     */
    public int getRequesterIDCount(
    ) {
        return this._requesterIDList.size();
    }

    /**
     * Method hasProxyCount.
     * 
     * @return true if at least one ProxyCount has been added
     */
    public boolean hasProxyCount(
    ) {
        return this._has_proxyCount;
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
        result = 37 * result + (int)(_proxyCount^(_proxyCount>>>32));
        if (_IDPList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_IDPList)) {
           result = 37 * result + _IDPList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_IDPList);
        }
        if (_requesterIDList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_requesterIDList)) {
           result = 37 * result + _requesterIDList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_requesterIDList);
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
     * Method iterateRequesterID.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateRequesterID(
    ) {
        return this._requesterIDList.iterator();
    }

    /**
     */
    public void removeAllRequesterID(
    ) {
        this._requesterIDList.clear();
    }

    /**
     * Method removeRequesterID.
     * 
     * @param vRequesterID
     * @return true if the object was removed from the collection.
     */
    public boolean removeRequesterID(
            final java.lang.String vRequesterID) {
        boolean removed = _requesterIDList.remove(vRequesterID);
        return removed;
    }

    /**
     * Method removeRequesterIDAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeRequesterIDAt(
            final int index) {
        java.lang.Object obj = this._requesterIDList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Sets the value of field 'IDPList'.
     * 
     * @param IDPList the value of field 'IDPList'.
     */
    public void setIDPList(
            final oasis.names.tc.SAML.v2_0.protocol.IDPList IDPList) {
        this._IDPList = IDPList;
    }

    /**
     * Sets the value of field 'proxyCount'.
     * 
     * @param proxyCount the value of field 'proxyCount'.
     */
    public void setProxyCount(
            final long proxyCount) {
        this._proxyCount = proxyCount;
        this._has_proxyCount = true;
    }

    /**
     * 
     * 
     * @param index
     * @param vRequesterID
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setRequesterID(
            final int index,
            final java.lang.String vRequesterID)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._requesterIDList.size()) {
            throw new IndexOutOfBoundsException("setRequesterID: Index value '" + index + "' not in range [0.." + (this._requesterIDList.size() - 1) + "]");
        }

        this._requesterIDList.set(index, vRequesterID);
    }

    /**
     * 
     * 
     * @param vRequesterIDArray
     */
    public void setRequesterID(
            final java.lang.String[] vRequesterIDArray) {
        //-- copy array
        _requesterIDList.clear();

        for (int i = 0; i < vRequesterIDArray.length; i++) {
                this._requesterIDList.add(vRequesterIDArray[i]);
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
