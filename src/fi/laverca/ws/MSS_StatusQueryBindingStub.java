package fi.laverca.ws;

import javax.xml.namespace.QName;

import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.ser.castor.CastorDeserializerFactory;
import org.apache.axis.encoding.ser.castor.CastorSerializerFactory;

public class MSS_StatusQueryBindingStub extends org.apache.axis.client.Stub
implements MSS_StatusQueryType
{
    private java.util.Vector<Class> cachedSerClasses = new java.util.Vector<Class>();
    private java.util.Vector<QName> cachedSerQNames = new java.util.Vector<QName>();
    private java.util.Vector<Object> cachedSerFactories = new java.util.Vector<Object>();
    private java.util.Vector<Object> cachedDeserFactories = new java.util.Vector<Object>();

    static OperationDesc [] _operations;

    static {
        _operations = new OperationDesc[1];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        OperationDesc oper;
        oper = new OperationDesc();
        oper.setName("MSS_StatusQuery");
        oper.addParameter(new QName("http://uri.etsi.org/TS102204/v1.1.2#", "MSS_StatusReq"),
                          new QName("http://uri.etsi.org/TS102204/v1.1.2#", "MSS_StatusReqType"),
                          org.etsi.uri.TS102204.v1_1_2.MSS_StatusReq.class,
                          ParameterDesc.IN, false, false);
        oper.setReturnType(new QName("http://uri.etsi.org/TS102204/v1.1.2#", "MSS_StatusRespType"));
        oper.setReturnClass(org.etsi.uri.TS102204.v1_1_2.MSS_StatusRespType.class);
        oper.setReturnQName(new QName("http://uri.etsi.org/TS102204/v1.1.2#", "MSS_StatusResp"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

    }

    public MSS_StatusQueryBindingStub() throws org.apache.axis.AxisFault {
        this(null);
    }

    public MSS_StatusQueryBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        this(service);
        super.cachedEndpoint = endpointURL;
    }

    public MSS_StatusQueryBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }

        Class cls;
        QName qName;

        Class beansf = CastorSerializerFactory.class;
        Class beandf = CastorDeserializerFactory.class;

        //
        // NOTE: Because of Castor serialization and deserialization only top level types needs to be mapped.
        //       The castor marshalling takes care of the rest.
        //
        qName = new QName("http://uri.etsi.org/TS102204/v1.1.2#", "MSS_MessageSignature");
        cachedSerQNames.add(qName);
        cls = org.etsi.uri.TS102204.v1_1_2.MSS_MessageSignature.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new QName("http://uri.etsi.org/TS102204/v1.1.2#", "MSS_StatusReqType");
        cachedSerQNames.add(qName);
        cls = org.etsi.uri.TS102204.v1_1_2.MSS_StatusReq.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new QName("http://uri.etsi.org/TS102204/v1.1.2#", "MSS_StatusRespType");
        cachedSerQNames.add(qName);
        cls = org.etsi.uri.TS102204.v1_1_2.MSS_StatusResp.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);	   
    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            /* Hand modification calling createCall from Stub(super) instead 
	       of the original super.service.createCall */
            if (super._call == null)
                super._call = super._createCall();

            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        Class cls = (Class) cachedSerClasses.get(i);
                        QName qName =
                            (QName) cachedSerQNames.get(i);
                        Class sf = (Class)
                        cachedSerFactories.get(i);
                        Class df = (Class)
                        cachedDeserFactories.get(i);
                        _call.registerTypeMapping(cls, qName, sf, df, false);
                    }
                }
            }
            return _call;
        }
        catch (Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public org.etsi.uri.TS102204.v1_1_2.MSS_StatusRespType MSS_StatusQuery(org.etsi.uri.TS102204.v1_1_2.MSS_StatusReqType MSS_StatusReq) throws java.rmi.RemoteException {

        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new QName("", "MSS_StatusQuery"));

        setRequestHeaders(_call);
        setAttachments(_call);
        Object _resp = _call.invoke(new Object[] {MSS_StatusReq});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.etsi.uri.TS102204.v1_1_2.MSS_StatusRespType) _resp;
            } catch (Exception _exception) {
                return (org.etsi.uri.TS102204.v1_1_2.MSS_StatusRespType) org.apache.axis.utils.JavaUtils.convert(_resp, org.etsi.uri.TS102204.v1_1_2.MSS_StatusRespType.class);
            }
        }
    }
}
