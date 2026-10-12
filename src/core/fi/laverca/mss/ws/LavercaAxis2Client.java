/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2026 Laverca Project
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMDocument;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMNode;
import org.apache.axiom.om.OMXMLStreamReaderConfiguration;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axiom.soap.SOAPFaultText;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.description.TransportOutDescription;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fi.laverca.LavercaSoapFaultException;
import fi.laverca.util.JMarshallerFactory;
import fi.laverca.util.LavercaContext;
import fi.laverca.util.LavercaHttpClient;

/**
 * Sends one ETSI TS 102 204 operation over Axis2, in the wire format of Laverca 3's Axis1 stubs: SOAP 1.2, an RPC/literal
 * wrapper element in no namespace named after the operation (e.g. {@code MSS_Signature}) around the JAXB-marshalled
 * request, SOAPAction {@code "#<operation>"}, and each namespace declared where it is used.
 * <p>
 * Failures come out as {@link LavercaSoapFaultException}; no Axis2 type leaves this class.
 */
public final class LavercaAxis2Client {

    private static final String RPC_RESULT_NS        = "http://www.w3.org/2003/05/soap-rpc";
    private static final String RPC_RESULT_LOCALNAME = "result";

    private LavercaAxis2Client() {
        // Static only
    }

    /**
     * Sends one MSS operation and returns its response.
     *
     * @param <T>           the response type
     * @param operationName RPC wrapper element name and SOAPAction fragment, e.g. "MSS_Signature"
     * @param reqBody       JAXB request object, e.g. an MSSSignatureReq
     * @param respClass     JAXB response class
     * @param url           target URL
     * @param httpClient    the client whose connection pool to send on
     * @param timeoutMillis call timeout in milliseconds, 0 for the default of 10 minutes
     * @param soapHeader    JAXB object to send as a SOAP header, e.g. a WS-Security Security; null for none
     * @param context       call context for extra HTTP headers and the raw messages; may be null
     * @return the response, or null if the response had no payload
     * @throws LavercaSoapFaultException if the MSSP answered with a SOAP fault, or the call failed
     * @throws IOException if the request can't be built or the response can't be read
     */
    public static <T> T send(final String operationName,
                             final Object reqBody,
                             final Class<T> respClass,
                             final URL url,
                             final LavercaHttpClient httpClient,
                             final long timeoutMillis,
                             final Object soapHeader,
                             final LavercaContext context)
        throws IOException
    {
        if (url == null) {
            throw new IOException("No URL for " + operationName);
        }

        final OMFactory f = OMAbstractFactory.getOMFactory();
        final OMElement wrapper = f.createOMElement(operationName, null);
        final OMElement headerEl;
        try {
            wrapper.addChild(marshal(reqBody));
            headerEl = (soapHeader != null) ? marshal(soapHeader) : null;
        } catch (JAXBException e) {
            throw new IOException("Unable to marshal " + operationName + " request: " + e.getMessage(), e);
        }

        ServiceClient sc = null;
        try {
            sc = new ServiceClient(LavercaAxis2Engine.getConfigurationContext(), null);
            final Options opts = LavercaAxis2Engine.newOptions();
            opts.setTo(new EndpointReference(url.toString()));
            opts.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
            opts.setAction("#" + operationName);
            if (timeoutMillis > 0) {
                opts.setTimeOutInMilliSeconds(timeoutMillis);
            }
            if (context != null) {
                opts.setProperty(LavercaAxis2HttpSender.LAVERCA_CONTEXT, context);
            }
            final TransportOutDescription tod = new TransportOutDescription("http");
            tod.setSender(new LavercaAxis2HttpSender(httpClient));
            opts.setTransportOut(tod);
            sc.setOptions(opts);
            if (headerEl != null) {
                sc.addHeader(headerEl);
            }

            final OMElement respWrapper = sc.sendReceive(wrapper);
            if (respWrapper == null) {
                return null;
            }
            final OMElement payload = payloadOf(respWrapper, respClass);
            if (payload == null) {
                return null;
            }
            return unmarshal(respClass, payload);

        } catch (AxisFault af) {
            throw toLavercaFault(af);
        } catch (JAXBException e) {
            throw new IOException("Unable to unmarshal " + operationName + " response: " + e.getMessage(), e);
        } finally {
            if (sc != null) {
                try {
                    // Removes the call's anonymous service from the shared engine
                    sc.cleanup();
                } catch (AxisFault e) {
                    // Ignore
                }
            }
        }
    }

    /**
     * The response payload: the wrapper's first child other than an rpc:result marker, or the wrapper itself if it
     * already is the response element.
     *
     * @param respWrapper first element of the response Body
     * @param respClass   JAXB response class, whose simple name the response element has with an underscore
     * @return the payload, or null if there is none
     */
    private static OMElement payloadOf(final OMElement respWrapper, final Class<?> respClass) {
        for (OMNode n = respWrapper.getFirstOMChild(); n != null; n = n.getNextOMSibling()) {
            if (n instanceof OMElement el) {
                final OMNamespace ns = el.getNamespace();
                final String nsUri = (ns != null) ? ns.getNamespaceURI() : null;
                if (!(RPC_RESULT_NS.equals(nsUri) && RPC_RESULT_LOCALNAME.equals(el.getLocalName()))) {
                    return el;
                }
            }
        }
        // Document style answer: the Body holds the response element itself, e.g. MSS_SignatureResp
        final String respName = respClass.getSimpleName().replaceFirst("^MSS", "MSS_");
        return respName.equals(respWrapper.getLocalName()) ? respWrapper : null;
    }

    /**
     * Converts an Axis2 call failure to a LavercaSoapFaultException: a received SOAP fault keeps its code, subcodes,
     * reason, role, node and details; a failed connection is the sender's local fault; anything else a local
     * Receiver fault.
     *
     * @param af the failure
     * @return the fault to throw
     */
    static LavercaSoapFaultException toLavercaFault(final AxisFault af) {
        for (Throwable t = af; t != null; t = (t.getCause() == t) ? null : t.getCause()) {
            if (t instanceof LavercaSoapFaultException lsf) {
                return lsf;
            }
        }
        if (af.getFaultCodeElement() != null) {
            final List<Element> details = new ArrayList<>();
            if (af.getDetails() != null) {
                for (final OMElement det : af.getDetails()) {
                    try {
                        details.add(toDOM(det).getDocumentElement());
                    } catch (Exception e) {
                        // Skip a detail that can't be converted
                    }
                }
            }
            final String role = (af.getFaultRoleElement() != null) ? af.getFaultRoleElement().getRoleValue() : null;
            final String node = (af.getFaultNodeElement() != null) ? af.getFaultNodeElement().getFaultNodeValue() : null;
            return new LavercaSoapFaultException(af.getFaultCode(), af.getFaultSubCodes(), faultReason(af),
                                                 role, node, details, true, null);
        }
        final Throwable cause = (af.getCause() != null) ? af.getCause() : af;
        return new LavercaSoapFaultException(LavercaSoapFaultException.SOAP12_RECEIVER, null, cause.getMessage(),
                                             null, null, null, false, cause);
    }

    /**
     * First non-empty fault reason text, whatever its xml:lang; else the fault message.
     *
     * @param af the received fault
     * @return the reason text
     */
    private static String faultReason(final AxisFault af) {
        if (af.getFaultReasonElement() != null) {
            for (final SOAPFaultText text : af.getFaultReasonElement().getAllSoapTexts()) {
                if (text.getText() != null && !text.getText().isEmpty()) {
                    return text.getText();
                }
            }
        }
        return af.getMessage();
    }

    /**
     * Axiom element to a namespace-aware DOM document, with the namespaces it inherits declared on its root.
     *
     * @param el the element
     * @return the DOM document
     * @throws Exception if the transformation fails
     */
    private static Document toDOM(final OMElement el) throws Exception {
        final DOMResult result = new DOMResult();
        TransformerFactory.newInstance().newTransformer().transform(el.getSAXSource(true), result);
        return (Document) result.getNode();
    }

    /**
     * Marshals a JAXB object into an Axiom element straight from JAXB's SAX events, with each namespace declared
     * where used, as Axis1 wrote it.
     *
     * @param obj a JAXB root element object, or a JAXBElement
     * @return the element
     * @throws JAXBException if marshalling fails
     */
    static OMElement marshal(final Object obj) throws JAXBException {
        final OMDocument doc = OMAbstractFactory.getOMFactory().createOMDocument();
        JMarshallerFactory.marshalWhereUsed(obj, doc.getSAXResult().getHandler());
        return doc.getOMDocumentElement();
    }

    /**
     * Unmarshals an Axiom element into the given JAXB type, whatever the element's name.
     *
     * @param <T>   the result type
     * @param clazz the JAXB class
     * @param el    the element
     * @return the object
     * @throws JAXBException if the element can't be unmarshalled
     */
    static <T> T unmarshal(final Class<T> clazz, final OMElement el) throws JAXBException {
        final OMXMLStreamReaderConfiguration conf = new OMXMLStreamReaderConfiguration();
        conf.setPreserveNamespaceContext(true);
        final XMLStreamReader r = el.getXMLStreamReader(true, conf);
        try {
            final Unmarshaller u = JMarshallerFactory.createUnmarshaller(clazz);
            return u.unmarshal(r, clazz).getValue();
        } finally {
            try {
                r.close();
            } catch (XMLStreamException e) {
                // Ignore
            }
        }
    }
}
