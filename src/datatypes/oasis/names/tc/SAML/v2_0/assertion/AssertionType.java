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
 * Class AssertionType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AssertionType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _version.
     */
    private java.lang.String _version;

    /**
     * Field _ID.
     */
    private java.lang.String _ID;

    /**
     * Field _issueInstant.
     */
    private java.util.Date _issueInstant;

    /**
     * Field _issuer.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Issuer _issuer;

    /**
     * Field _signature.
     */
    private org.w3.www._2000._09.xmldsig.Signature _signature;

    /**
     * Field _subject.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Subject _subject;

    /**
     * Field _conditions.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Conditions _conditions;

    /**
     * Field _advice.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Advice _advice;

    /**
     * Field _assertionTypeChoice.
     */
    private oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoice _assertionTypeChoice;


      //----------------/
     //- Constructors -/
    //----------------/

    public AssertionType() {
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

        if (obj instanceof AssertionType) {

            AssertionType temp = (AssertionType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._version != null) {
                if (temp._version == null) return false;
                if (this._version != temp._version) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._version);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._version);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._version); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._version); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._version.equals(temp._version)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._version);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._version);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._version);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._version);
                    }
                }
            } else if (temp._version != null)
                return false;
            if (this._ID != null) {
                if (temp._ID == null) return false;
                if (this._ID != temp._ID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._ID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._ID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._ID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._ID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._ID.equals(temp._ID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._ID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._ID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._ID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._ID);
                    }
                }
            } else if (temp._ID != null)
                return false;
            if (this._issueInstant != null) {
                if (temp._issueInstant == null) return false;
                if (this._issueInstant != temp._issueInstant) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._issueInstant);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._issueInstant);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._issueInstant); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._issueInstant); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._issueInstant.equals(temp._issueInstant)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._issueInstant);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._issueInstant);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._issueInstant);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._issueInstant);
                    }
                }
            } else if (temp._issueInstant != null)
                return false;
            if (this._issuer != null) {
                if (temp._issuer == null) return false;
                if (this._issuer != temp._issuer) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._issuer);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._issuer);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._issuer); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._issuer); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._issuer.equals(temp._issuer)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._issuer);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._issuer);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._issuer);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._issuer);
                    }
                }
            } else if (temp._issuer != null)
                return false;
            if (this._signature != null) {
                if (temp._signature == null) return false;
                if (this._signature != temp._signature) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signature);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signature);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signature); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signature); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signature.equals(temp._signature)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signature);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signature);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signature);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signature);
                    }
                }
            } else if (temp._signature != null)
                return false;
            if (this._subject != null) {
                if (temp._subject == null) return false;
                if (this._subject != temp._subject) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._subject);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._subject);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subject); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subject); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._subject.equals(temp._subject)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subject);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subject);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subject);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subject);
                    }
                }
            } else if (temp._subject != null)
                return false;
            if (this._conditions != null) {
                if (temp._conditions == null) return false;
                if (this._conditions != temp._conditions) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._conditions);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._conditions);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._conditions); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._conditions); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._conditions.equals(temp._conditions)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._conditions);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._conditions);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._conditions);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._conditions);
                    }
                }
            } else if (temp._conditions != null)
                return false;
            if (this._advice != null) {
                if (temp._advice == null) return false;
                if (this._advice != temp._advice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._advice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._advice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._advice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._advice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._advice.equals(temp._advice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._advice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._advice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._advice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._advice);
                    }
                }
            } else if (temp._advice != null)
                return false;
            if (this._assertionTypeChoice != null) {
                if (temp._assertionTypeChoice == null) return false;
                if (this._assertionTypeChoice != temp._assertionTypeChoice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._assertionTypeChoice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._assertionTypeChoice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._assertionTypeChoice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._assertionTypeChoice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._assertionTypeChoice.equals(temp._assertionTypeChoice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._assertionTypeChoice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._assertionTypeChoice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._assertionTypeChoice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._assertionTypeChoice);
                    }
                }
            } else if (temp._assertionTypeChoice != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'advice'.
     * 
     * @return the value of field 'Advice'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.Advice getAdvice(
    ) {
        return this._advice;
    }

    /**
     * Returns the value of field 'assertionTypeChoice'.
     * 
     * @return the value of field 'AssertionTypeChoice'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoice getAssertionTypeChoice(
    ) {
        return this._assertionTypeChoice;
    }

    /**
     * Returns the value of field 'conditions'.
     * 
     * @return the value of field 'Conditions'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.Conditions getConditions(
    ) {
        return this._conditions;
    }

    /**
     * Returns the value of field 'ID'.
     * 
     * @return the value of field 'ID'.
     */
    public java.lang.String getID(
    ) {
        return this._ID;
    }

    /**
     * Returns the value of field 'issueInstant'.
     * 
     * @return the value of field 'IssueInstant'.
     */
    public java.util.Date getIssueInstant(
    ) {
        return this._issueInstant;
    }

    /**
     * Returns the value of field 'issuer'.
     * 
     * @return the value of field 'Issuer'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.Issuer getIssuer(
    ) {
        return this._issuer;
    }

    /**
     * Returns the value of field 'signature'.
     * 
     * @return the value of field 'Signature'.
     */
    public org.w3.www._2000._09.xmldsig.Signature getSignature(
    ) {
        return this._signature;
    }

    /**
     * Returns the value of field 'subject'.
     * 
     * @return the value of field 'Subject'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.Subject getSubject(
    ) {
        return this._subject;
    }

    /**
     * Returns the value of field 'version'.
     * 
     * @return the value of field 'Version'.
     */
    public java.lang.String getVersion(
    ) {
        return this._version;
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
        if (_version != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_version)) {
           result = 37 * result + _version.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_version);
        }
        if (_ID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_ID)) {
           result = 37 * result + _ID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_ID);
        }
        if (_issueInstant != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_issueInstant)) {
           result = 37 * result + _issueInstant.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_issueInstant);
        }
        if (_issuer != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_issuer)) {
           result = 37 * result + _issuer.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_issuer);
        }
        if (_signature != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signature)) {
           result = 37 * result + _signature.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signature);
        }
        if (_subject != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_subject)) {
           result = 37 * result + _subject.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_subject);
        }
        if (_conditions != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_conditions)) {
           result = 37 * result + _conditions.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_conditions);
        }
        if (_advice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_advice)) {
           result = 37 * result + _advice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_advice);
        }
        if (_assertionTypeChoice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_assertionTypeChoice)) {
           result = 37 * result + _assertionTypeChoice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_assertionTypeChoice);
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
     * Sets the value of field 'advice'.
     * 
     * @param advice the value of field 'advice'.
     */
    public void setAdvice(
            final oasis.names.tc.SAML.v2_0.assertion.Advice advice) {
        this._advice = advice;
    }

    /**
     * Sets the value of field 'assertionTypeChoice'.
     * 
     * @param assertionTypeChoice the value of field
     * 'assertionTypeChoice'.
     */
    public void setAssertionTypeChoice(
            final oasis.names.tc.SAML.v2_0.assertion.AssertionTypeChoice assertionTypeChoice) {
        this._assertionTypeChoice = assertionTypeChoice;
    }

    /**
     * Sets the value of field 'conditions'.
     * 
     * @param conditions the value of field 'conditions'.
     */
    public void setConditions(
            final oasis.names.tc.SAML.v2_0.assertion.Conditions conditions) {
        this._conditions = conditions;
    }

    /**
     * Sets the value of field 'ID'.
     * 
     * @param ID the value of field 'ID'.
     */
    public void setID(
            final java.lang.String ID) {
        this._ID = ID;
    }

    /**
     * Sets the value of field 'issueInstant'.
     * 
     * @param issueInstant the value of field 'issueInstant'.
     */
    public void setIssueInstant(
            final java.util.Date issueInstant) {
        this._issueInstant = issueInstant;
    }

    /**
     * Sets the value of field 'issuer'.
     * 
     * @param issuer the value of field 'issuer'.
     */
    public void setIssuer(
            final oasis.names.tc.SAML.v2_0.assertion.Issuer issuer) {
        this._issuer = issuer;
    }

    /**
     * Sets the value of field 'signature'.
     * 
     * @param signature the value of field 'signature'.
     */
    public void setSignature(
            final org.w3.www._2000._09.xmldsig.Signature signature) {
        this._signature = signature;
    }

    /**
     * Sets the value of field 'subject'.
     * 
     * @param subject the value of field 'subject'.
     */
    public void setSubject(
            final oasis.names.tc.SAML.v2_0.assertion.Subject subject) {
        this._subject = subject;
    }

    /**
     * Sets the value of field 'version'.
     * 
     * @param version the value of field 'version'.
     */
    public void setVersion(
            final java.lang.String version) {
        this._version = version;
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
