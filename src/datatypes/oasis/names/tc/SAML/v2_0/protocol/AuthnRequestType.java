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

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class AuthnRequestType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AuthnRequestType extends oasis.names.tc.SAML.v2_0.protocol.RequestAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _forceAuthn.
     */
    private boolean _forceAuthn;

    /**
     * keeps track of state for field: _forceAuthn
     */
    private boolean _has_forceAuthn;

    /**
     * Field _isPassive.
     */
    private boolean _isPassive;

    /**
     * keeps track of state for field: _isPassive
     */
    private boolean _has_isPassive;

    /**
     * Field _protocolBinding.
     */
    private java.lang.String _protocolBinding;

    /**
     * Field _assertionConsumerServiceIndex.
     */
    private int _assertionConsumerServiceIndex;

    /**
     * keeps track of state for field: _assertionConsumerServiceInde
     */
    private boolean _has_assertionConsumerServiceIndex;

    /**
     * Field _assertionConsumerServiceURL.
     */
    private java.lang.String _assertionConsumerServiceURL;

    /**
     * Field _attributeConsumingServiceIndex.
     */
    private int _attributeConsumingServiceIndex;

    /**
     * keeps track of state for field:
     * _attributeConsumingServiceIndex
     */
    private boolean _has_attributeConsumingServiceIndex;

    /**
     * Field _providerName.
     */
    private java.lang.String _providerName;

    /**
     * Field _subject.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Subject _subject;

    /**
     * Field _nameIDPolicy.
     */
    private oasis.names.tc.SAML.v2_0.protocol.NameIDPolicy _nameIDPolicy;

    /**
     * Field _conditions.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Conditions _conditions;

    /**
     * Field _requestedAuthnContext.
     */
    private oasis.names.tc.SAML.v2_0.protocol.RequestedAuthnContext _requestedAuthnContext;

    /**
     * Field _scoping.
     */
    private oasis.names.tc.SAML.v2_0.protocol.Scoping _scoping;


      //----------------/
     //- Constructors -/
    //----------------/

    public AuthnRequestType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteAssertionConsumerServiceIndex(
    ) {
        this._has_assertionConsumerServiceIndex= false;
    }

    /**
     */
    public void deleteAttributeConsumingServiceIndex(
    ) {
        this._has_attributeConsumingServiceIndex= false;
    }

    /**
     */
    public void deleteForceAuthn(
    ) {
        this._has_forceAuthn= false;
    }

    /**
     */
    public void deleteIsPassive(
    ) {
        this._has_isPassive= false;
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

        if (obj instanceof AuthnRequestType) {

            AuthnRequestType temp = (AuthnRequestType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._forceAuthn != temp._forceAuthn)
                return false;
            if (this._has_forceAuthn != temp._has_forceAuthn)
                return false;
            if (this._isPassive != temp._isPassive)
                return false;
            if (this._has_isPassive != temp._has_isPassive)
                return false;
            if (this._protocolBinding != null) {
                if (temp._protocolBinding == null) return false;
                if (this._protocolBinding != temp._protocolBinding) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._protocolBinding);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._protocolBinding);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._protocolBinding); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._protocolBinding); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._protocolBinding.equals(temp._protocolBinding)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._protocolBinding);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._protocolBinding);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._protocolBinding);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._protocolBinding);
                    }
                }
            } else if (temp._protocolBinding != null)
                return false;
            if (this._assertionConsumerServiceIndex != temp._assertionConsumerServiceIndex)
                return false;
            if (this._has_assertionConsumerServiceIndex != temp._has_assertionConsumerServiceIndex)
                return false;
            if (this._assertionConsumerServiceURL != null) {
                if (temp._assertionConsumerServiceURL == null) return false;
                if (this._assertionConsumerServiceURL != temp._assertionConsumerServiceURL) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._assertionConsumerServiceURL);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._assertionConsumerServiceURL);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._assertionConsumerServiceURL); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._assertionConsumerServiceURL); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._assertionConsumerServiceURL.equals(temp._assertionConsumerServiceURL)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._assertionConsumerServiceURL);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._assertionConsumerServiceURL);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._assertionConsumerServiceURL);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._assertionConsumerServiceURL);
                    }
                }
            } else if (temp._assertionConsumerServiceURL != null)
                return false;
            if (this._attributeConsumingServiceIndex != temp._attributeConsumingServiceIndex)
                return false;
            if (this._has_attributeConsumingServiceIndex != temp._has_attributeConsumingServiceIndex)
                return false;
            if (this._providerName != null) {
                if (temp._providerName == null) return false;
                if (this._providerName != temp._providerName) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._providerName);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._providerName);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._providerName); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._providerName); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._providerName.equals(temp._providerName)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._providerName);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._providerName);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._providerName);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._providerName);
                    }
                }
            } else if (temp._providerName != null)
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
            if (this._nameIDPolicy != null) {
                if (temp._nameIDPolicy == null) return false;
                if (this._nameIDPolicy != temp._nameIDPolicy) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._nameIDPolicy);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._nameIDPolicy);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameIDPolicy); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameIDPolicy); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._nameIDPolicy.equals(temp._nameIDPolicy)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameIDPolicy);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameIDPolicy);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameIDPolicy);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameIDPolicy);
                    }
                }
            } else if (temp._nameIDPolicy != null)
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
            if (this._scoping != null) {
                if (temp._scoping == null) return false;
                if (this._scoping != temp._scoping) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._scoping);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._scoping);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._scoping); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._scoping); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._scoping.equals(temp._scoping)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._scoping);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._scoping);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._scoping);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._scoping);
                    }
                }
            } else if (temp._scoping != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'assertionConsumerServiceIndex'.
     * 
     * @return the value of field 'AssertionConsumerServiceIndex'.
     */
    public int getAssertionConsumerServiceIndex(
    ) {
        return this._assertionConsumerServiceIndex;
    }

    /**
     * Returns the value of field 'assertionConsumerServiceURL'.
     * 
     * @return the value of field 'AssertionConsumerServiceURL'.
     */
    public java.lang.String getAssertionConsumerServiceURL(
    ) {
        return this._assertionConsumerServiceURL;
    }

    /**
     * Returns the value of field 'attributeConsumingServiceIndex'.
     * 
     * @return the value of field 'AttributeConsumingServiceIndex'.
     */
    public int getAttributeConsumingServiceIndex(
    ) {
        return this._attributeConsumingServiceIndex;
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
     * Returns the value of field 'forceAuthn'.
     * 
     * @return the value of field 'ForceAuthn'.
     */
    public boolean getForceAuthn(
    ) {
        return this._forceAuthn;
    }

    /**
     * Returns the value of field 'isPassive'.
     * 
     * @return the value of field 'IsPassive'.
     */
    public boolean getIsPassive(
    ) {
        return this._isPassive;
    }

    /**
     * Returns the value of field 'nameIDPolicy'.
     * 
     * @return the value of field 'NameIDPolicy'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.NameIDPolicy getNameIDPolicy(
    ) {
        return this._nameIDPolicy;
    }

    /**
     * Returns the value of field 'protocolBinding'.
     * 
     * @return the value of field 'ProtocolBinding'.
     */
    public java.lang.String getProtocolBinding(
    ) {
        return this._protocolBinding;
    }

    /**
     * Returns the value of field 'providerName'.
     * 
     * @return the value of field 'ProviderName'.
     */
    public java.lang.String getProviderName(
    ) {
        return this._providerName;
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
     * Returns the value of field 'scoping'.
     * 
     * @return the value of field 'Scoping'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.Scoping getScoping(
    ) {
        return this._scoping;
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
     * Method hasAssertionConsumerServiceIndex.
     * 
     * @return true if at least one AssertionConsumerServiceIndex
     * has been added
     */
    public boolean hasAssertionConsumerServiceIndex(
    ) {
        return this._has_assertionConsumerServiceIndex;
    }

    /**
     * Method hasAttributeConsumingServiceIndex.
     * 
     * @return true if at least one AttributeConsumingServiceIndex
     * has been added
     */
    public boolean hasAttributeConsumingServiceIndex(
    ) {
        return this._has_attributeConsumingServiceIndex;
    }

    /**
     * Method hasForceAuthn.
     * 
     * @return true if at least one ForceAuthn has been added
     */
    public boolean hasForceAuthn(
    ) {
        return this._has_forceAuthn;
    }

    /**
     * Method hasIsPassive.
     * 
     * @return true if at least one IsPassive has been added
     */
    public boolean hasIsPassive(
    ) {
        return this._has_isPassive;
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
        result = 37 * result + (_forceAuthn?0:1);
        result = 37 * result + (_isPassive?0:1);
        if (_protocolBinding != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_protocolBinding)) {
           result = 37 * result + _protocolBinding.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_protocolBinding);
        }
        result = 37 * result + _assertionConsumerServiceIndex;
        if (_assertionConsumerServiceURL != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_assertionConsumerServiceURL)) {
           result = 37 * result + _assertionConsumerServiceURL.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_assertionConsumerServiceURL);
        }
        result = 37 * result + _attributeConsumingServiceIndex;
        if (_providerName != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_providerName)) {
           result = 37 * result + _providerName.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_providerName);
        }
        if (_subject != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_subject)) {
           result = 37 * result + _subject.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_subject);
        }
        if (_nameIDPolicy != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_nameIDPolicy)) {
           result = 37 * result + _nameIDPolicy.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_nameIDPolicy);
        }
        if (_conditions != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_conditions)) {
           result = 37 * result + _conditions.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_conditions);
        }
        if (_requestedAuthnContext != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_requestedAuthnContext)) {
           result = 37 * result + _requestedAuthnContext.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_requestedAuthnContext);
        }
        if (_scoping != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_scoping)) {
           result = 37 * result + _scoping.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_scoping);
        }

        return result;
    }

    /**
     * Returns the value of field 'forceAuthn'.
     * 
     * @return the value of field 'ForceAuthn'.
     */
    public boolean isForceAuthn(
    ) {
        return this._forceAuthn;
    }

    /**
     * Returns the value of field 'isPassive'.
     * 
     * @return the value of field 'IsPassive'.
     */
    public boolean isIsPassive(
    ) {
        return this._isPassive;
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
     * Sets the value of field 'assertionConsumerServiceIndex'.
     * 
     * @param assertionConsumerServiceIndex the value of field
     * 'assertionConsumerServiceIndex'.
     */
    public void setAssertionConsumerServiceIndex(
            final int assertionConsumerServiceIndex) {
        this._assertionConsumerServiceIndex = assertionConsumerServiceIndex;
        this._has_assertionConsumerServiceIndex = true;
    }

    /**
     * Sets the value of field 'assertionConsumerServiceURL'.
     * 
     * @param assertionConsumerServiceURL the value of field
     * 'assertionConsumerServiceURL'.
     */
    public void setAssertionConsumerServiceURL(
            final java.lang.String assertionConsumerServiceURL) {
        this._assertionConsumerServiceURL = assertionConsumerServiceURL;
    }

    /**
     * Sets the value of field 'attributeConsumingServiceIndex'.
     * 
     * @param attributeConsumingServiceIndex the value of field
     * 'attributeConsumingServiceIndex'.
     */
    public void setAttributeConsumingServiceIndex(
            final int attributeConsumingServiceIndex) {
        this._attributeConsumingServiceIndex = attributeConsumingServiceIndex;
        this._has_attributeConsumingServiceIndex = true;
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
     * Sets the value of field 'forceAuthn'.
     * 
     * @param forceAuthn the value of field 'forceAuthn'.
     */
    public void setForceAuthn(
            final boolean forceAuthn) {
        this._forceAuthn = forceAuthn;
        this._has_forceAuthn = true;
    }

    /**
     * Sets the value of field 'isPassive'.
     * 
     * @param isPassive the value of field 'isPassive'.
     */
    public void setIsPassive(
            final boolean isPassive) {
        this._isPassive = isPassive;
        this._has_isPassive = true;
    }

    /**
     * Sets the value of field 'nameIDPolicy'.
     * 
     * @param nameIDPolicy the value of field 'nameIDPolicy'.
     */
    public void setNameIDPolicy(
            final oasis.names.tc.SAML.v2_0.protocol.NameIDPolicy nameIDPolicy) {
        this._nameIDPolicy = nameIDPolicy;
    }

    /**
     * Sets the value of field 'protocolBinding'.
     * 
     * @param protocolBinding the value of field 'protocolBinding'.
     */
    public void setProtocolBinding(
            final java.lang.String protocolBinding) {
        this._protocolBinding = protocolBinding;
    }

    /**
     * Sets the value of field 'providerName'.
     * 
     * @param providerName the value of field 'providerName'.
     */
    public void setProviderName(
            final java.lang.String providerName) {
        this._providerName = providerName;
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
     * Sets the value of field 'scoping'.
     * 
     * @param scoping the value of field 'scoping'.
     */
    public void setScoping(
            final oasis.names.tc.SAML.v2_0.protocol.Scoping scoping) {
        this._scoping = scoping;
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
