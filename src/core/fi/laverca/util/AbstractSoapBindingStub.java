//
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

package fi.laverca.util;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.encoding.DeserializerFactory;
import org.apache.axis.encoding.SerializerFactory;
import org.apache.axis.soap.SOAPConstants;

import fi.laverca.ErrorCodes;

/**
 * This extends non-thread-safe Stub, therefore this is not thread-safe either!
 */
public abstract class AbstractSoapBindingStub extends Stub {

    // MSS specific constants used in stubs.
    public static final String NS204 = ErrorCodes.ETSI_204_NS_URI;
    public static final QName MESSAGESIGNATURE_HEADER = new QName(NS204, "MSS_MessageSignature", "mss");

    public static final Class<? extends SerializerFactory>   sf = JaxbSerializerFactory.class;
    public static final Class<? extends DeserializerFactory> df = JaxbDeserializerFactory.class;


    protected boolean sendSOAPAction;
    protected String  soapActionURI;

    public Call getCall()
        throws RemoteException
    {
        try {
            final Call c = super._getCall();
            if (c != null) return c;
            return this._createCall();
        }
        catch (final Throwable _t) {
            throw new AxisFault("Failure trying to get the Call object", _t);
        }
    }

    /**
     * Get client's MessageContext.
     * Creates the Call client if it does not yet exist.
     * <p>
     * Note: The MessageContext will be cleared deep inside
     *       Axis during the client call, so anything set into
     *       its context is not stable before the call.
     * 
     * @return the MessageContext object
     * @throws ServiceException if creating the Call fails
     */
    public MessageContext getMessageContext()
        throws ServiceException
    {
        if (this._getCall() == null) {
            // Creates _call datasets, including a new MessageContext
            // (which will be CLEARED deep inside Axis during client call...)
            this._createCall();
        }
        
        return this._getCall().getMessageContext();
    }

    /**
     * Set property value into Axis Stub instance cachedProperties map,
     * which gets propagated to actual call context.
     *
     * @param name  Property name
     * @param value Property value
     * @return the previous value of the specified name, or null if it did not exist
     * @throws NullPointerException if name or value is null
     */
    public Object setProperty(final String name, final Object value) throws NullPointerException
    {
        return super.cachedProperties.put(name, value);
    }


    /**
     * Methics custom bit to control sending SOAPAction HTTP header.
     *
     * @param send true if SOAPAction is send
     */
    public void setSendSOAPAction(final boolean send) {
        this.sendSOAPAction = send;
    }

    /**
     * Stub implementations must supply this value.
     * 
     * @param uri SOAP Action URI
     */
    public void setSOAPActionUri(final String uri) {
        this.soapActionURI = uri;
    }

    // Overrides a public method at Stub.
    @Override
    public org.apache.axis.client.Call _createCall()
        throws javax.xml.rpc.ServiceException
    {
        // Hand modification calling createCall from Stub(super) instead
        // of the original super.service.createCall
        if (super._call == null)
            super._call = super._createCall();

        if (this.sendSOAPAction) {
            super._call.setUseSOAPAction(true);
            super._call.setSOAPActionURI(this.soapActionURI);
        } else {
            super._call.setUseSOAPAction(false);
        }
        return super._call;
    }

    protected Call createCall()
            throws RemoteException
    {
        return this.createCall((String)null);
    }

    protected Call createCall(final String encodingStyle)
            throws RemoteException
    {
        try {
            // Make sure super._call is initialized
            this._createCall();
            // Set each subfield
            if (super.maintainSessionSet) {
                super._call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                super._call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                super._call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                super._call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                super._call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                super._call.setPortName(super.cachedPortName);
            }

            final Enumeration<Object> keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                final String key = (String) keys.nextElement();
                super._call.setProperty(key, super.cachedProperties.get(key));
            }
            return super._call;
        }
        catch (final Throwable _t) {
            throw new AxisFault("Failure trying to get the Call object", _t);
        }
    }

    /**
     * Create a call with common parameter preparation.
     *
     * @param soapConstants Preferably SOAP12Constants
     * @param encodingStyleURI null or Constants.URI_SOAP12_ENC, or Constants.URI_SOAP12_NOENC
     * @param operationDesc OperationDesc
     * @return Prepared Call object.
     * @throws RemoteException for a number of initialization failures
     */
    public Call createCall( final SOAPConstants soapConstants,
                            final String encodingStyleURI,
                            final OperationDesc operationDesc )
        throws RemoteException
    {
        final org.apache.axis.client.Call _call1 = this.createCall(encodingStyleURI);
        _call1.setSOAPVersion(soapConstants);
        // Could this be safe not to be filled?
        _call1.setEncodingStyle(encodingStyleURI);

        // OperationDesc fills local type registry as a side function
        _call1.setOperation(operationDesc);

        // Set the operation QName (OperationDesc has top-level ElementQName)
        _call1.setOperationName(operationDesc.getElementQName());

        return _call1;
    }
}
