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

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class SubjectType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class SubjectType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _subjectTypeSequence.
     */
    private oasis.names.tc.SAML.v2_0.assertion.SubjectTypeSequence _subjectTypeSequence;

    /**
     * Field _subjectConfirmationList.
     */
    private java.util.List<oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation> _subjectConfirmationList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SubjectType() {
        super();
        this._subjectConfirmationList = new java.util.ArrayList<oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSubjectConfirmation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSubjectConfirmation(
            final oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation vSubjectConfirmation)
    throws java.lang.IndexOutOfBoundsException {
        this._subjectConfirmationList.add(vSubjectConfirmation);
    }

    /**
     * 
     * 
     * @param index
     * @param vSubjectConfirmation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSubjectConfirmation(
            final int index,
            final oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation vSubjectConfirmation)
    throws java.lang.IndexOutOfBoundsException {
        this._subjectConfirmationList.add(index, vSubjectConfirmation);
    }

    /**
     * Method enumerateSubjectConfirmation.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation> enumerateSubjectConfirmation(
    ) {
        return java.util.Collections.enumeration(this._subjectConfirmationList);
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

        if (obj instanceof SubjectType) {

            SubjectType temp = (SubjectType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._choiceValue != null) {
                if (temp._choiceValue == null) return false;
                if (this._choiceValue != temp._choiceValue) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._choiceValue);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._choiceValue);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._choiceValue.equals(temp._choiceValue)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue);
                    }
                }
            } else if (temp._choiceValue != null)
                return false;
            if (this._subjectTypeSequence != null) {
                if (temp._subjectTypeSequence == null) return false;
                if (this._subjectTypeSequence != temp._subjectTypeSequence) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._subjectTypeSequence);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._subjectTypeSequence);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectTypeSequence); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectTypeSequence); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._subjectTypeSequence.equals(temp._subjectTypeSequence)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectTypeSequence);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectTypeSequence);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectTypeSequence);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectTypeSequence);
                    }
                }
            } else if (temp._subjectTypeSequence != null)
                return false;
            if (this._subjectConfirmationList != null) {
                if (temp._subjectConfirmationList == null) return false;
                if (this._subjectConfirmationList != temp._subjectConfirmationList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._subjectConfirmationList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._subjectConfirmationList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectConfirmationList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectConfirmationList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._subjectConfirmationList.equals(temp._subjectConfirmationList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectConfirmationList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectConfirmationList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectConfirmationList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectConfirmationList);
                    }
                }
            } else if (temp._subjectConfirmationList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return the value of field 'ChoiceValue'.
     */
    public java.lang.Object getChoiceValue(
    ) {
        return this._choiceValue;
    }

    /**
     * Method getSubjectConfirmation.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation at
     * the given index
     */
    public oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation getSubjectConfirmation(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._subjectConfirmationList.size()) {
            throw new IndexOutOfBoundsException("getSubjectConfirmation: Index value '" + index + "' not in range [0.." + (this._subjectConfirmationList.size() - 1) + "]");
        }

        return (oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation) _subjectConfirmationList.get(index);
    }

    /**
     * Method getSubjectConfirmation.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation[] getSubjectConfirmation(
    ) {
        oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation[] array = new oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation[0];
        return (oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation[]) this._subjectConfirmationList.toArray(array);
    }

    /**
     * Method getSubjectConfirmationCount.
     * 
     * @return the size of this collection
     */
    public int getSubjectConfirmationCount(
    ) {
        return this._subjectConfirmationList.size();
    }

    /**
     * Returns the value of field 'subjectTypeSequence'.
     * 
     * @return the value of field 'SubjectTypeSequence'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.SubjectTypeSequence getSubjectTypeSequence(
    ) {
        return this._subjectTypeSequence;
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
        if (_choiceValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_choiceValue)) {
           result = 37 * result + _choiceValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_choiceValue);
        }
        if (_subjectTypeSequence != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_subjectTypeSequence)) {
           result = 37 * result + _subjectTypeSequence.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_subjectTypeSequence);
        }
        if (_subjectConfirmationList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_subjectConfirmationList)) {
           result = 37 * result + _subjectConfirmationList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_subjectConfirmationList);
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
     * Method iterateSubjectConfirmation.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation> iterateSubjectConfirmation(
    ) {
        return this._subjectConfirmationList.iterator();
    }

    /**
     */
    public void removeAllSubjectConfirmation(
    ) {
        this._subjectConfirmationList.clear();
    }

    /**
     * Method removeSubjectConfirmation.
     * 
     * @param vSubjectConfirmation
     * @return true if the object was removed from the collection.
     */
    public boolean removeSubjectConfirmation(
            final oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation vSubjectConfirmation) {
        boolean removed = _subjectConfirmationList.remove(vSubjectConfirmation);
        return removed;
    }

    /**
     * Method removeSubjectConfirmationAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation removeSubjectConfirmationAt(
            final int index) {
        java.lang.Object obj = this._subjectConfirmationList.remove(index);
        return (oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vSubjectConfirmation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSubjectConfirmation(
            final int index,
            final oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation vSubjectConfirmation)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._subjectConfirmationList.size()) {
            throw new IndexOutOfBoundsException("setSubjectConfirmation: Index value '" + index + "' not in range [0.." + (this._subjectConfirmationList.size() - 1) + "]");
        }

        this._subjectConfirmationList.set(index, vSubjectConfirmation);
    }

    /**
     * 
     * 
     * @param vSubjectConfirmationArray
     */
    public void setSubjectConfirmation(
            final oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmation[] vSubjectConfirmationArray) {
        //-- copy array
        _subjectConfirmationList.clear();

        for (int i = 0; i < vSubjectConfirmationArray.length; i++) {
                this._subjectConfirmationList.add(vSubjectConfirmationArray[i]);
        }
    }

    /**
     * Sets the value of field 'subjectTypeSequence'.
     * 
     * @param subjectTypeSequence the value of field
     * 'subjectTypeSequence'.
     */
    public void setSubjectTypeSequence(
            final oasis.names.tc.SAML.v2_0.assertion.SubjectTypeSequence subjectTypeSequence) {
        this._subjectTypeSequence = subjectTypeSequence;
        this._choiceValue = subjectTypeSequence;
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
