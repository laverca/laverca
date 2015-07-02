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
 * Class StatusResponseType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class StatusResponseType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _ID.
     */
    private java.lang.String _ID;

    /**
     * Field _inResponseTo.
     */
    private java.lang.String _inResponseTo;

    /**
     * Field _version.
     */
    private java.lang.String _version;

    /**
     * Field _issueInstant.
     */
    private java.util.Date _issueInstant;

    /**
     * Field _destination.
     */
    private java.lang.String _destination;

    /**
     * Field _consent.
     */
    private java.lang.String _consent;

    /**
     * Field _issuer.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Issuer _issuer;

    /**
     * Field _signature.
     */
    private org.w3.www._2000._09.xmldsig.Signature _signature;

    /**
     * Field _extensions.
     */
    private oasis.names.tc.SAML.v2_0.protocol.Extensions _extensions;

    /**
     * Field _status.
     */
    private oasis.names.tc.SAML.v2_0.protocol.Status _status;


      //----------------/
     //- Constructors -/
    //----------------/

    public StatusResponseType() {
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

        if (obj instanceof StatusResponseType) {

            StatusResponseType temp = (StatusResponseType)obj;
            boolean thcycle;
            boolean tmcycle;
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
            if (this._inResponseTo != null) {
                if (temp._inResponseTo == null) return false;
                if (this._inResponseTo != temp._inResponseTo) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._inResponseTo);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._inResponseTo);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._inResponseTo); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._inResponseTo); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._inResponseTo.equals(temp._inResponseTo)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._inResponseTo);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._inResponseTo);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._inResponseTo);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._inResponseTo);
                    }
                }
            } else if (temp._inResponseTo != null)
                return false;
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
            if (this._destination != null) {
                if (temp._destination == null) return false;
                if (this._destination != temp._destination) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._destination);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._destination);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._destination); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._destination); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._destination.equals(temp._destination)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._destination);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._destination);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._destination);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._destination);
                    }
                }
            } else if (temp._destination != null)
                return false;
            if (this._consent != null) {
                if (temp._consent == null) return false;
                if (this._consent != temp._consent) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._consent);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._consent);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._consent); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._consent); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._consent.equals(temp._consent)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._consent);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._consent);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._consent);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._consent);
                    }
                }
            } else if (temp._consent != null)
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
            if (this._extensions != null) {
                if (temp._extensions == null) return false;
                if (this._extensions != temp._extensions) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._extensions);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._extensions);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._extensions); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._extensions); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._extensions.equals(temp._extensions)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._extensions);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._extensions);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._extensions);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._extensions);
                    }
                }
            } else if (temp._extensions != null)
                return false;
            if (this._status != null) {
                if (temp._status == null) return false;
                if (this._status != temp._status) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._status);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._status);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._status.equals(temp._status)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
                    }
                }
            } else if (temp._status != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'consent'.
     * 
     * @return the value of field 'Consent'.
     */
    public java.lang.String getConsent(
    ) {
        return this._consent;
    }

    /**
     * Returns the value of field 'destination'.
     * 
     * @return the value of field 'Destination'.
     */
    public java.lang.String getDestination(
    ) {
        return this._destination;
    }

    /**
     * Returns the value of field 'extensions'.
     * 
     * @return the value of field 'Extensions'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.Extensions getExtensions(
    ) {
        return this._extensions;
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
     * Returns the value of field 'inResponseTo'.
     * 
     * @return the value of field 'InResponseTo'.
     */
    public java.lang.String getInResponseTo(
    ) {
        return this._inResponseTo;
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
     * Returns the value of field 'status'.
     * 
     * @return the value of field 'Status'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.Status getStatus(
    ) {
        return this._status;
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
        if (_ID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_ID)) {
           result = 37 * result + _ID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_ID);
        }
        if (_inResponseTo != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_inResponseTo)) {
           result = 37 * result + _inResponseTo.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_inResponseTo);
        }
        if (_version != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_version)) {
           result = 37 * result + _version.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_version);
        }
        if (_issueInstant != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_issueInstant)) {
           result = 37 * result + _issueInstant.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_issueInstant);
        }
        if (_destination != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_destination)) {
           result = 37 * result + _destination.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_destination);
        }
        if (_consent != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_consent)) {
           result = 37 * result + _consent.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_consent);
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
        if (_extensions != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_extensions)) {
           result = 37 * result + _extensions.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_extensions);
        }
        if (_status != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_status)) {
           result = 37 * result + _status.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_status);
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
     * Sets the value of field 'consent'.
     * 
     * @param consent the value of field 'consent'.
     */
    public void setConsent(
            final java.lang.String consent) {
        this._consent = consent;
    }

    /**
     * Sets the value of field 'destination'.
     * 
     * @param destination the value of field 'destination'.
     */
    public void setDestination(
            final java.lang.String destination) {
        this._destination = destination;
    }

    /**
     * Sets the value of field 'extensions'.
     * 
     * @param extensions the value of field 'extensions'.
     */
    public void setExtensions(
            final oasis.names.tc.SAML.v2_0.protocol.Extensions extensions) {
        this._extensions = extensions;
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
     * Sets the value of field 'inResponseTo'.
     * 
     * @param inResponseTo the value of field 'inResponseTo'.
     */
    public void setInResponseTo(
            final java.lang.String inResponseTo) {
        this._inResponseTo = inResponseTo;
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
     * Sets the value of field 'status'.
     * 
     * @param status the value of field 'status'.
     */
    public void setStatus(
            final oasis.names.tc.SAML.v2_0.protocol.Status status) {
        this._status = status;
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
