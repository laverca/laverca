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

package fi.laverca.mss.ws;

import javax.xml.namespace.QName;

import org.apache.axis.AxisEngine;
import org.apache.axis.client.Call;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.soap.SOAPConstants;

import fi.laverca.jaxb.mss.MSSMessageSignature;
import fi.laverca.jaxb.mss.MSSRegistrationReq;
import fi.laverca.jaxb.mss.MSSRegistrationResp;
import fi.laverca.jaxb.wsssecext.Security;
import fi.laverca.util.AbstractSoapBindingStub;
import fi.laverca.util.JMarshallerFactory;

public class MSS_RegistrationBindingStub extends AbstractSoapBindingStub
    implements MSS_RegistrationType
{
    static OperationDesc [] _operations;

    static {
        _operations = new OperationDesc[1];

        // Register prefix at Axis.
        JMarshallerFactory.registerPrefix("mss", NS204);
        final String NSWSSEC = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
        
        final QName reqQN     = new QName(NS204, "MSS_RegistrationReq");
        final QName respQN    = new QName(NS204, "MSS_RegistrationResp");

        final QName securityQN   = new QName(NSWSSEC, "Security");
        
        OperationDesc oper;
        ParameterDesc [] params;
        params = new ParameterDesc [] {
            // Final fallback at Axis uses parameters in supplied order if it does not recognize them otherwise.
            // Place body element(s) into start of the parameter set, headers follow.
            new ParameterDesc(reqQN,
                              ParameterDesc.IN,
                              reqQN,
                              MSSRegistrationReq.class,
                              false, false),
            new ParameterDesc(securityQN,
                              ParameterDesc.IN,
                              securityQN,
                              Security.class,
                              true, false),
        };
        params[1].setOmittable(true);
        oper = new OperationDesc("MSS_Registration",
                                 new QName("", "MSS_Registration"), // Element QName
                                 params,
                                 respQN,
                                 respQN,
                                 MSSRegistrationResp.class,
                                 Style.RPC,
                                 Use.LITERAL);

        //
        // NOTE: Because of Castor serialization and deserialization
        //       only top level types needs to be mapped.
        //       The castor marshalling takes care of the rest.
        //

        // 1. javaType
        // 2. xmlType
        // 3. serClass
        // 4. dserClass
        // 5. encodingStyleURI

        oper.registerType(MSSRegistrationReq.class,   reqQN, sf, df, null);
        oper.registerType(MSSRegistrationResp.class, respQN, sf, df, null);
        oper.registerType(Security.class,        securityQN, sf, df, null);
        oper.registerType(MSSMessageSignature.class, MESSAGESIGNATURE_HEADER, sf, df, null);

        MSS_RegistrationBindingStub._operations[0] = oper;
    }

    public MSS_RegistrationBindingStub() {
        this(null);
    }

    public MSS_RegistrationBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) {
        this(service);
        super.cachedEndpoint = endpointURL;
    }

    public MSS_RegistrationBindingStub(javax.xml.rpc.Service service) {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
    }

    @Override
    public MSSRegistrationResp MSS_Registration(final MSSRegistrationReq req, final Security security) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        Call _call1 = this.createCall(SOAPConstants.SOAP12_CONSTANTS,
                                      null,
                                      _operations[0]);
        _call1.setProperty(Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call1.setProperty(AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call1.setSOAPActionURI("#MSS_Registration");

        this.setRequestHeaders(_call1);
        
        Object _resp = _call1.invoke(new Object[] {req, security});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            return (MSSRegistrationResp) _resp;
        }
    }
}
