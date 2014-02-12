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
 * Class AuthnQueryType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AuthnQueryType extends oasis.names.tc.SAML.v2_0.protocol.SubjectQueryAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _sessionIndex.
     */
    private java.lang.String _sessionIndex;

    /**
     * Field _requestedAuthnContext.
     */
    private oasis.names.tc.SAML.v2_0.protocol.RequestedAuthnContext _requestedAuthnContext;


      //----------------/
     //- Constructors -/
    //----------------/

    public AuthnQueryType() {
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

        if (obj instanceof AuthnQueryType) {

            AuthnQueryType temp = (AuthnQueryType)obj;
            boolean thcycle;
            boolean tmcycle;
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
            if (this._requestedAuthnContext != null) {
                if (temp._requestedAuthnContext == null) return false;
                if (this._requestedAuthnContext != temp._requestedAuthnContext) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._requestedAuthnContext);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._requestedAuthnContext);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._requestedAuthnContext); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._requestedAuthnContext); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._requestedAuthnContext.equals(temp._requestedAuthnContext)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._requestedAuthnContext);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._requestedAuthnContext);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._requestedAuthnContext);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._requestedAuthnContext);
                    }
                }
            } else if (temp._requestedAuthnContext != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'requestedAuthnContext'.
     * 
     * @return the value of field 'RequestedAuthnContext'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.RequestedAuthnContext getRequestedAuthnContext(
    ) {
        return this._requestedAuthnContext;
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
        if (_sessionIndex != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_sessionIndex)) {
           result = 37 * result + _sessionIndex.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_sessionIndex);
        }
        if (_requestedAuthnContext != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_requestedAuthnContext)) {
           result = 37 * result + _requestedAuthnContext.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_requestedAuthnContext);
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
     * Sets the value of field 'requestedAuthnContext'.
     * 
     * @param requestedAuthnContext the value of field
     * 'requestedAuthnContext'.
     */
    public void setRequestedAuthnContext(
            final oasis.names.tc.SAML.v2_0.protocol.RequestedAuthnContext requestedAuthnContext) {
        this._requestedAuthnContext = requestedAuthnContext;
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
