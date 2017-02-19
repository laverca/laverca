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

import fi.laverca.jaxb.mss.MSSHandshakeReq;
import fi.laverca.jaxb.mss.MSSHandshakeResp;
import fi.laverca.jaxb.mss.MSSMessageSignature;
import fi.laverca.util.AbstractSoapBindingStub;
import fi.laverca.util.JMarshallerFactory;

public class MSS_HandshakeBindingStub extends AbstractSoapBindingStub
    implements MSS_HandshakePortType
{
    public static OperationDesc [] _operations;

    static {
        _operations = new OperationDesc[1];

        // Register prefix at Axis.
        JMarshallerFactory.registerPrefix("mss", NS204);

        final QName reqQN  = new QName(NS204, "MSS_HandshakeReq");
        final QName respQN = new QName(NS204, "MSS_HandshakeResp");

        final ParameterDesc param = new ParameterDesc(reqQN,
                                                      ParameterDesc.IN,
                                                      reqQN,
                                                      MSSHandshakeReq.class,
                                                      false, false);
        final OperationDesc oper = new OperationDesc("MSS_Handshake",
                                                     new QName("", "MSS_Handshake"),
                                                     new ParameterDesc[] { param },
                                                     respQN,
                                                     respQN,
                                                     MSSHandshakeResp.class,
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

        oper.registerType( MSSHandshakeReq.class,  reqQN,  sf, df, null );
        oper.registerType( MSSHandshakeResp.class, respQN, sf, df, null );
        oper.registerType( MSSMessageSignature.class,
                          MESSAGESIGNATURE_HEADER, sf, df, null);

        MSS_HandshakeBindingStub._operations[0] = oper;
    }

    public MSS_HandshakeBindingStub() {
        this(null);
    }

    public MSS_HandshakeBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) {
        this(service);
        super.cachedEndpoint = endpointURL;
    }

    public MSS_HandshakeBindingStub(javax.xml.rpc.Service service) {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
    }

    @Override
    public MSSHandshakeResp MSS_Handshake(MSSHandshakeReq MSS_HandshakeReq) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        Call _call1 = this.createCall(SOAPConstants.SOAP12_CONSTANTS,
                                      null,
                                      _operations[0]);
        _call1.setProperty(Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call1.setProperty(AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call1.setSOAPActionURI("#MSS_Handshake");

        this.setRequestHeaders(_call1);
        Object _resp = _call1.invoke(new Object[] {MSS_HandshakeReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            return (MSSHandshakeResp) _resp;
        }
    }
}
