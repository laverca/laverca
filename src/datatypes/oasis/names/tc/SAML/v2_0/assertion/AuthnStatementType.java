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
 * Class AuthnStatementType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AuthnStatementType extends oasis.names.tc.SAML.v2_0.assertion.StatementAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _authnInstant.
     */
    private java.util.Date _authnInstant;

    /**
     * Field _sessionIndex.
     */
    private java.lang.String _sessionIndex;

    /**
     * Field _sessionNotOnOrAfter.
     */
    private java.util.Date _sessionNotOnOrAfter;

    /**
     * Field _subjectLocality.
     */
    private oasis.names.tc.SAML.v2_0.assertion.SubjectLocality _subjectLocality;

    /**
     * Field _authnContext.
     */
    private oasis.names.tc.SAML.v2_0.assertion.AuthnContext _authnContext;


      //----------------/
     //- Constructors -/
    //----------------/

    public AuthnStatementType() {
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

        if (super.equals(obj)==false)
            return false;

        if (obj instanceof AuthnStatementType) {

            AuthnStatementType temp = (AuthnStatementType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._authnInstant != null) {
                if (temp._authnInstant == null) return false;
                if (this._authnInstant != temp._authnInstant) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnInstant);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnInstant);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnInstant); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnInstant); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnInstant.equals(temp._authnInstant)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnInstant);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnInstant);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnInstant);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnInstant);
                    }
                }
            } else if (temp._authnInstant != null)
                return false;
            if (this._sessionIndex != null) {
                if (temp._sessionIndex == null) return false;
                if (this._sessionIndex != temp._sessionIndex) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._sessionIndex);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._sessionIndex);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionIndex); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionIndex); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._sessionIndex.equals(temp._sessionIndex)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionIndex);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionIndex);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionIndex);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionIndex);
                    }
                }
            } else if (temp._sessionIndex != null)
                return false;
            if (this._sessionNotOnOrAfter != null) {
                if (temp._sessionNotOnOrAfter == null) return false;
                if (this._sessionNotOnOrAfter != temp._sessionNotOnOrAfter) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._sessionNotOnOrAfter);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._sessionNotOnOrAfter);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionNotOnOrAfter); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionNotOnOrAfter); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._sessionNotOnOrAfter.equals(temp._sessionNotOnOrAfter)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionNotOnOrAfter);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionNotOnOrAfter);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionNotOnOrAfter);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionNotOnOrAfter);
                    }
                }
            } else if (temp._sessionNotOnOrAfter != null)
                return false;
            if (this._subjectLocality != null) {
                if (temp._subjectLocality == null) return false;
                if (this._subjectLocality != temp._subjectLocality) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._subjectLocality);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._subjectLocality);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectLocality); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectLocality); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._subjectLocality.equals(temp._subjectLocality)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectLocality);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectLocality);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subjectLocality);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subjectLocality);
                    }
                }
            } else if (temp._subjectLocality != null)
                return false;
            if (this._authnContext != null) {
                if (temp._authnContext == null) return false;
                if (this._authnContext != temp._authnContext) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._authnContext);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._authnContext);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContext); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContext); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._authnContext.equals(temp._authnContext)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContext);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContext);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._authnContext);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._authnContext);
                    }
                }
            } else if (temp._authnContext != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'authnContext'.
     * 
     * @return the value of field 'AuthnContext'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.AuthnContext getAuthnContext(
    ) {
        return this._authnContext;
    }

    /**
     * Returns the value of field 'authnInstant'.
     * 
     * @return the value of field 'AuthnInstant'.
     */
    public java.util.Date getAuthnInstant(
    ) {
        return this._authnInstant;
    }

    /**
     * Returns the value of field 'sessionIndex'.
     * 
     * @return the value of field 'SessionIndex'.
     */
    public java.lang.String getSessionIndex(
    ) {
        return this._sessionIndex;
    }

    /**
     * Returns the value of field 'sessionNotOnOrAfter'.
     * 
     * @return the value of field 'SessionNotOnOrAfter'.
     */
    public java.util.Date getSessionNotOnOrAfter(
    ) {
        return this._sessionNotOnOrAfter;
    }

    /**
     * Returns the value of field 'subjectLocality'.
     * 
     * @return the value of field 'SubjectLocality'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.SubjectLocality getSubjectLocality(
    ) {
        return this._subjectLocality;
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
        if (_authnInstant != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnInstant)) {
           result = 37 * result + _authnInstant.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnInstant);
        }
        if (_sessionIndex != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_sessionIndex)) {
           result = 37 * result + _sessionIndex.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_sessionIndex);
        }
        if (_sessionNotOnOrAfter != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_sessionNotOnOrAfter)) {
           result = 37 * result + _sessionNotOnOrAfter.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_sessionNotOnOrAfter);
        }
        if (_subjectLocality != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_subjectLocality)) {
           result = 37 * result + _subjectLocality.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_subjectLocality);
        }
        if (_authnContext != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_authnContext)) {
           result = 37 * result + _authnContext.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_authnContext);
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
     * Sets the value of field 'authnContext'.
     * 
     * @param authnContext the value of field 'authnContext'.
     */
    public void setAuthnContext(
            final oasis.names.tc.SAML.v2_0.assertion.AuthnContext authnContext) {
        this._authnContext = authnContext;
    }

    /**
     * Sets the value of field 'authnInstant'.
     * 
     * @param authnInstant the value of field 'authnInstant'.
     */
    public void setAuthnInstant(
            final java.util.Date authnInstant) {
        this._authnInstant = authnInstant;
    }

    /**
     * Sets the value of field 'sessionIndex'.
     * 
     * @param sessionIndex the value of field 'sessionIndex'.
     */
    public void setSessionIndex(
            final java.lang.String sessionIndex) {
        this._sessionIndex = sessionIndex;
    }

    /**
     * Sets the value of field 'sessionNotOnOrAfter'.
     * 
     * @param sessionNotOnOrAfter the value of field
     * 'sessionNotOnOrAfter'.
     */
    public void setSessionNotOnOrAfter(
            final java.util.Date sessionNotOnOrAfter) {
        this._sessionNotOnOrAfter = sessionNotOnOrAfter;
    }

    /**
     * Sets the value of field 'subjectLocality'.
     * 
     * @param subjectLocality the value of field 'subjectLocality'.
     */
    public void setSubjectLocality(
            final oasis.names.tc.SAML.v2_0.assertion.SubjectLocality subjectLocality) {
        this._subjectLocality = subjectLocality;
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
