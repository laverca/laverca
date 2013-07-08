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
 * Class AudienceRestrictionType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AudienceRestrictionType extends oasis.names.tc.SAML.v2_0.assertion.ConditionAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _audienceList.
     */
    private java.util.List<java.lang.String> _audienceList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AudienceRestrictionType() {
        super();
        this._audienceList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAudience
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAudience(
            final java.lang.String vAudience)
    throws java.lang.IndexOutOfBoundsException {
        this._audienceList.add(vAudience);
    }

    /**
     * 
     * 
     * @param index
     * @param vAudience
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAudience(
            final int index,
            final java.lang.String vAudience)
    throws java.lang.IndexOutOfBoundsException {
        this._audienceList.add(index, vAudience);
    }

    /**
     * Method enumerateAudience.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateAudience(
    ) {
        return java.util.Collections.enumeration(this._audienceList);
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

        if (super.equals(obj)==false)
            return false;

        if (obj instanceof AudienceRestrictionType) {

            AudienceRestrictionType temp = (AudienceRestrictionType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._audienceList != null) {
                if (temp._audienceList == null) return false;
                if (this._audienceList != temp._audienceList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._audienceList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._audienceList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._audienceList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._audienceList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._audienceList.equals(temp._audienceList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._audienceList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._audienceList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._audienceList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._audienceList);
                    }
                }
            } else if (temp._audienceList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getAudience.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getAudience(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._audienceList.size()) {
            throw new IndexOutOfBoundsException("getAudience: Index value '" + index + "' not in range [0.." + (this._audienceList.size() - 1) + "]");
        }

        return (java.lang.String) _audienceList.get(index);
    }

    /**
     * Method getAudience.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getAudience(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._audienceList.toArray(array);
    }

    /**
     * Method getAudienceCount.
     * 
     * @return the size of this collection
     */
    public int getAudienceCount(
    ) {
        return this._audienceList.size();
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
        int result = super.hashCode();

        long tmp;
        if (_audienceList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_audienceList)) {
           result = 37 * result + _audienceList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_audienceList);
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
     * Method iterateAudience.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateAudience(
    ) {
        return this._audienceList.iterator();
    }

    /**
     */
    public void removeAllAudience(
    ) {
        this._audienceList.clear();
    }

    /**
     * Method removeAudience.
     * 
     * @param vAudience
     * @return true if the object was removed from the collection.
     */
    public boolean removeAudience(
            final java.lang.String vAudience) {
        boolean removed = _audienceList.remove(vAudience);
        return removed;
    }

    /**
     * Method removeAudienceAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeAudienceAt(
            final int index) {
        java.lang.Object obj = this._audienceList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAudience
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAudience(
            final int index,
            final java.lang.String vAudience)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._audienceList.size()) {
            throw new IndexOutOfBoundsException("setAudience: Index value '" + index + "' not in range [0.." + (this._audienceList.size() - 1) + "]");
        }

        this._audienceList.set(index, vAudience);
    }

    /**
     * 
     * 
     * @param vAudienceArray
     */
    public void setAudience(
            final java.lang.String[] vAudienceArray) {
        //-- copy array
        _audienceList.clear();

        for (int i = 0; i < vAudienceArray.length; i++) {
                this._audienceList.add(vAudienceArray[i]);
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
