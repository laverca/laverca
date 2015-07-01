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

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class IDPListType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class IDPListType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _IDPEntryList.
     */
    private java.util.List<oasis.names.tc.SAML.v2_0.protocol.IDPEntry> _IDPEntryList;

    /**
     * Field _getComplete.
     */
    private java.lang.String _getComplete;


      //----------------/
     //- Constructors -/
    //----------------/

    public IDPListType() {
        super();
        this._IDPEntryList = new java.util.ArrayList<oasis.names.tc.SAML.v2_0.protocol.IDPEntry>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vIDPEntry
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addIDPEntry(
            final oasis.names.tc.SAML.v2_0.protocol.IDPEntry vIDPEntry)
    throws java.lang.IndexOutOfBoundsException {
        this._IDPEntryList.add(vIDPEntry);
    }

    /**
     * 
     * 
     * @param index
     * @param vIDPEntry
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addIDPEntry(
            final int index,
            final oasis.names.tc.SAML.v2_0.protocol.IDPEntry vIDPEntry)
    throws java.lang.IndexOutOfBoundsException {
        this._IDPEntryList.add(index, vIDPEntry);
    }

    /**
     * Method enumerateIDPEntry.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends oasis.names.tc.SAML.v2_0.protocol.IDPEntry> enumerateIDPEntry(
    ) {
        return java.util.Collections.enumeration(this._IDPEntryList);
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

        if (obj instanceof IDPListType) {

            IDPListType temp = (IDPListType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._IDPEntryList != null) {
                if (temp._IDPEntryList == null) return false;
                if (this._IDPEntryList != temp._IDPEntryList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._IDPEntryList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._IDPEntryList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._IDPEntryList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._IDPEntryList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._IDPEntryList.equals(temp._IDPEntryList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._IDPEntryList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._IDPEntryList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._IDPEntryList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._IDPEntryList);
                    }
                }
            } else if (temp._IDPEntryList != null)
                return false;
            if (this._getComplete != null) {
                if (temp._getComplete == null) return false;
                if (this._getComplete != temp._getComplete) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._getComplete);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._getComplete);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._getComplete); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._getComplete); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._getComplete.equals(temp._getComplete)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._getComplete);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._getComplete);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._getComplete);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._getComplete);
                    }
                }
            } else if (temp._getComplete != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'getComplete'.
     * 
     * @return the value of field 'GetComplete'.
     */
    public java.lang.String getGetComplete(
    ) {
        return this._getComplete;
    }

    /**
     * Method getIDPEntry.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * oasis.names.tc.SAML.v2_0.protocol.IDPEntry at the given index
     */
    public oasis.names.tc.SAML.v2_0.protocol.IDPEntry getIDPEntry(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._IDPEntryList.size()) {
            throw new IndexOutOfBoundsException("getIDPEntry: Index value '" + index + "' not in range [0.." + (this._IDPEntryList.size() - 1) + "]");
        }

        return (oasis.names.tc.SAML.v2_0.protocol.IDPEntry) _IDPEntryList.get(index);
    }

    /**
     * Method getIDPEntry.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public oasis.names.tc.SAML.v2_0.protocol.IDPEntry[] getIDPEntry(
    ) {
        oasis.names.tc.SAML.v2_0.protocol.IDPEntry[] array = new oasis.names.tc.SAML.v2_0.protocol.IDPEntry[0];
        return (oasis.names.tc.SAML.v2_0.protocol.IDPEntry[]) this._IDPEntryList.toArray(array);
    }

    /**
     * Method getIDPEntryCount.
     * 
     * @return the size of this collection
     */
    public int getIDPEntryCount(
    ) {
        return this._IDPEntryList.size();
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
        if (_IDPEntryList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_IDPEntryList)) {
           result = 37 * result + _IDPEntryList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_IDPEntryList);
        }
        if (_getComplete != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_getComplete)) {
           result = 37 * result + _getComplete.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_getComplete);
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
     * Method iterateIDPEntry.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends oasis.names.tc.SAML.v2_0.protocol.IDPEntry> iterateIDPEntry(
    ) {
        return this._IDPEntryList.iterator();
    }

    /**
     */
    public void removeAllIDPEntry(
    ) {
        this._IDPEntryList.clear();
    }

    /**
     * Method removeIDPEntry.
     * 
     * @param vIDPEntry
     * @return true if the object was removed from the collection.
     */
    public boolean removeIDPEntry(
            final oasis.names.tc.SAML.v2_0.protocol.IDPEntry vIDPEntry) {
        boolean removed = _IDPEntryList.remove(vIDPEntry);
        return removed;
    }

    /**
     * Method removeIDPEntryAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public oasis.names.tc.SAML.v2_0.protocol.IDPEntry removeIDPEntryAt(
            final int index) {
        java.lang.Object obj = this._IDPEntryList.remove(index);
        return (oasis.names.tc.SAML.v2_0.protocol.IDPEntry) obj;
    }

    /**
     * Sets the value of field 'getComplete'.
     * 
     * @param getComplete the value of field 'getComplete'.
     */
    public void setGetComplete(
            final java.lang.String getComplete) {
        this._getComplete = getComplete;
    }

    /**
     * 
     * 
     * @param index
     * @param vIDPEntry
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setIDPEntry(
            final int index,
            final oasis.names.tc.SAML.v2_0.protocol.IDPEntry vIDPEntry)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._IDPEntryList.size()) {
            throw new IndexOutOfBoundsException("setIDPEntry: Index value '" + index + "' not in range [0.." + (this._IDPEntryList.size() - 1) + "]");
        }

        this._IDPEntryList.set(index, vIDPEntry);
    }

    /**
     * 
     * 
     * @param vIDPEntryArray
     */
    public void setIDPEntry(
            final oasis.names.tc.SAML.v2_0.protocol.IDPEntry[] vIDPEntryArray) {
        //-- copy array
        _IDPEntryList.clear();

        for (int i = 0; i < vIDPEntryArray.length; i++) {
                this._IDPEntryList.add(vIDPEntryArray[i]);
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
