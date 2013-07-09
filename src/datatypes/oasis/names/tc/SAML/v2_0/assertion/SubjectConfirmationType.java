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
 * Class SubjectConfirmationType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class SubjectConfirmationType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _method.
     */
    private java.lang.String _method;

    /**
     * Field _subjectConfirmationTypeChoice.
     */
    private oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmationTypeChoice _subjectConfirmationTypeChoice;

    /**
     * Field _subjectConfirmationData.
     */
    private oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmationData _subjectConfirmationData;


      //----------------/
     //- Constructors -/
    //----------------/

    public SubjectConfirmationType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

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

        if (obj instanceof SubjectConfirmationType) {

            SubjectConfirmationType temp = (SubjectConfirmationType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._method != null) {
                if (temp._method == null) return false;
                if (this._method != temp._method) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._method);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._method);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._method); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._method); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._method.equals(temp._method)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._method);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._method);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._method);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._method);
                    }
                }
            } else if (temp._method != null)
                return false;
            if (this._subjectConfirmationTypeChoice != null) {
                if (temp._subjectConfirmationTypeChoice == null) return false;
                if (this._subjectConfirmationTypeChoice != temp._subjectConfirmationTypeChoice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._subjectConfirmationTypeChoice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._subjectConfirmationTypeChoice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectConfirmationTypeChoice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectConfirmationTypeChoice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._subjectConfirmationTypeChoice.equals(temp._subjectConfirmationTypeChoice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectConfirmationTypeChoice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectConfirmationTypeChoice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectConfirmationTypeChoice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectConfirmationTypeChoice);
                    }
                }
            } else if (temp._subjectConfirmationTypeChoice != null)
                return false;
            if (this._subjectConfirmationData != null) {
                if (temp._subjectConfirmationData == null) return false;
                if (this._subjectConfirmationData != temp._subjectConfirmationData) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._subjectConfirmationData);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._subjectConfirmationData);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectConfirmationData); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectConfirmationData); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._subjectConfirmationData.equals(temp._subjectConfirmationData)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectConfirmationData);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectConfirmationData);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectConfirmationData);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectConfirmationData);
                    }
                }
            } else if (temp._subjectConfirmationData != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'method'.
     * 
     * @return the value of field 'Method'.
     */
    public java.lang.String getMethod(
    ) {
        return this._method;
    }

    /**
     * Returns the value of field 'subjectConfirmationData'.
     * 
     * @return the value of field 'SubjectConfirmationData'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmationData getSubjectConfirmationData(
    ) {
        return this._subjectConfirmationData;
    }

    /**
     * Returns the value of field 'subjectConfirmationTypeChoice'.
     * 
     * @return the value of field 'SubjectConfirmationTypeChoice'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmationTypeChoice getSubjectConfirmationTypeChoice(
    ) {
        return this._subjectConfirmationTypeChoice;
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
        if (_method != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_method)) {
           result = 37 * result + _method.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_method);
        }
        if (_subjectConfirmationTypeChoice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_subjectConfirmationTypeChoice)) {
           result = 37 * result + _subjectConfirmationTypeChoice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_subjectConfirmationTypeChoice);
        }
        if (_subjectConfirmationData != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_subjectConfirmationData)) {
           result = 37 * result + _subjectConfirmationData.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_subjectConfirmationData);
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
     * Sets the value of field 'method'.
     * 
     * @param method the value of field 'method'.
     */
    public void setMethod(
            final java.lang.String method) {
        this._method = method;
    }

    /**
     * Sets the value of field 'subjectConfirmationData'.
     * 
     * @param subjectConfirmationData the value of field
     * 'subjectConfirmationData'.
     */
    public void setSubjectConfirmationData(
            final oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmationData subjectConfirmationData) {
        this._subjectConfirmationData = subjectConfirmationData;
    }

    /**
     * Sets the value of field 'subjectConfirmationTypeChoice'.
     * 
     * @param subjectConfirmationTypeChoice the value of field
     * 'subjectConfirmationTypeChoice'.
     */
    public void setSubjectConfirmationTypeChoice(
            final oasis.names.tc.SAML.v2_0.assertion.SubjectConfirmationTypeChoice subjectConfirmationTypeChoice) {
        this._subjectConfirmationTypeChoice = subjectConfirmationTypeChoice;
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
