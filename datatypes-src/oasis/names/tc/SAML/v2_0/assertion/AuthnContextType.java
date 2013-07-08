/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2013 Laverca Project
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

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class AuthnContextType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AuthnContextType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _authnContextTypeChoice.
     */
    private oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoice _authnContextTypeChoice;

    /**
     * Field _authenticatingAuthorityList.
     */
    private java.util.List<java.lang.String> _authenticatingAuthorityList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AuthnContextType() {
        super();
        this._authenticatingAuthorityList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAuthenticatingAuthority
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAuthenticatingAuthority(
            final java.lang.String vAuthenticatingAuthority)
    throws java.lang.IndexOutOfBoundsException {
        this._authenticatingAuthorityList.add(vAuthenticatingAuthority);
    }

    /**
     * 
     * 
     * @param index
     * @param vAuthenticatingAuthority
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAuthenticatingAuthority(
            final int index,
            final java.lang.String vAuthenticatingAuthority)
    throws java.lang.IndexOutOfBoundsException {
        this._authenticatingAuthorityList.add(index, vAuthenticatingAuthority);
    }

    /**
     * Method enumerateAuthenticatingAuthority.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateAuthenticatingAuthority(
    ) {
        return java.util.Collections.enumeration(this._authenticatingAuthorityList);
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

        if (obj instanceof AuthnContextType) {

            AuthnContextType temp = (AuthnContextType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._authnContextTypeChoice != null) {
                if (temp._authnContextTypeChoice == null) return false;
                if (this._authnContextTypeChoice != temp._authnContextTypeChoice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnContextTypeChoice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnContextTypeChoice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnContextTypeChoice.equals(temp._authnContextTypeChoice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContextTypeChoice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContextTypeChoice);
                    }
                }
            } else if (temp._authnContextTypeChoice != null)
                return false;
            if (this._authenticatingAuthorityList != null) {
                if (temp._authenticatingAuthorityList == null) return false;
                if (this._authenticatingAuthorityList != temp._authenticatingAuthorityList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authenticatingAuthorityList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authenticatingAuthorityList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authenticatingAuthorityList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authenticatingAuthorityList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authenticatingAuthorityList.equals(temp._authenticatingAuthorityList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authenticatingAuthorityList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authenticatingAuthorityList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authenticatingAuthorityList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authenticatingAuthorityList);
                    }
                }
            } else if (temp._authenticatingAuthorityList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getAuthenticatingAuthority.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getAuthenticatingAuthority(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._authenticatingAuthorityList.size()) {
            throw new IndexOutOfBoundsException("getAuthenticatingAuthority: Index value '" + index + "' not in range [0.." + (this._authenticatingAuthorityList.size() - 1) + "]");
        }

        return (java.lang.String) _authenticatingAuthorityList.get(index);
    }

    /**
     * Method getAuthenticatingAuthority.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getAuthenticatingAuthority(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._authenticatingAuthorityList.toArray(array);
    }

    /**
     * Method getAuthenticatingAuthorityCount.
     * 
     * @return the size of this collection
     */
    public int getAuthenticatingAuthorityCount(
    ) {
        return this._authenticatingAuthorityList.size();
    }

    /**
     * Returns the value of field 'authnContextTypeChoice'.
     * 
     * @return the value of field 'AuthnContextTypeChoice'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoice getAuthnContextTypeChoice(
    ) {
        return this._authnContextTypeChoice;
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
        if (_authnContextTypeChoice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnContextTypeChoice)) {
           result = 37 * result + _authnContextTypeChoice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnContextTypeChoice);
        }
        if (_authenticatingAuthorityList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authenticatingAuthorityList)) {
           result = 37 * result + _authenticatingAuthorityList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authenticatingAuthorityList);
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
     * Method iterateAuthenticatingAuthority.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateAuthenticatingAuthority(
    ) {
        return this._authenticatingAuthorityList.iterator();
    }

    /**
     */
    public void removeAllAuthenticatingAuthority(
    ) {
        this._authenticatingAuthorityList.clear();
    }

    /**
     * Method removeAuthenticatingAuthority.
     * 
     * @param vAuthenticatingAuthority
     * @return true if the object was removed from the collection.
     */
    public boolean removeAuthenticatingAuthority(
            final java.lang.String vAuthenticatingAuthority) {
        boolean removed = _authenticatingAuthorityList.remove(vAuthenticatingAuthority);
        return removed;
    }

    /**
     * Method removeAuthenticatingAuthorityAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeAuthenticatingAuthorityAt(
            final int index) {
        java.lang.Object obj = this._authenticatingAuthorityList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAuthenticatingAuthority
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAuthenticatingAuthority(
            final int index,
            final java.lang.String vAuthenticatingAuthority)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._authenticatingAuthorityList.size()) {
            throw new IndexOutOfBoundsException("setAuthenticatingAuthority: Index value '" + index + "' not in range [0.." + (this._authenticatingAuthorityList.size() - 1) + "]");
        }

        this._authenticatingAuthorityList.set(index, vAuthenticatingAuthority);
    }

    /**
     * 
     * 
     * @param vAuthenticatingAuthorityArray
     */
    public void setAuthenticatingAuthority(
            final java.lang.String[] vAuthenticatingAuthorityArray) {
        //-- copy array
        _authenticatingAuthorityList.clear();

        for (int i = 0; i < vAuthenticatingAuthorityArray.length; i++) {
                this._authenticatingAuthorityList.add(vAuthenticatingAuthorityArray[i]);
        }
    }

    /**
     * Sets the value of field 'authnContextTypeChoice'.
     * 
     * @param authnContextTypeChoice the value of field
     * 'authnContextTypeChoice'.
     */
    public void setAuthnContextTypeChoice(
            final oasis.names.tc.SAML.v2_0.assertion.AuthnContextTypeChoice authnContextTypeChoice) {
        this._authnContextTypeChoice = authnContextTypeChoice;
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
