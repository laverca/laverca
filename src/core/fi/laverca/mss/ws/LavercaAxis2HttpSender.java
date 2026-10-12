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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMXMLBuilderFactory;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPHeader;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.NamedValue;
import org.apache.axis2.context.OperationContext;
import org.apache.axis2.description.HandlerDescription;
import org.apache.axis2.description.Parameter;
import org.apache.axis2.description.TransportOutDescription;
import org.apache.axis2.engine.AxisEngine;
import org.apache.axis2.engine.Handler;
import org.apache.axis2.kernel.TransportSender;
import org.apache.axis2.kernel.http.HTTPConstants;
import org.apache.axis2.wsdl.WSDLConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.ByteArrayEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.Timeout;

import fi.laverca.LavercaSoapFaultException;
import fi.laverca.util.LavercaContext;
import fi.laverca.util.LavercaHttpClient;

/**
 * Axis2 {@link TransportSender} that sends SOAP calls with HttpClient 5 on a {@link LavercaHttpClient}'s
 * connection pool, the counterpart of Laverca 3's Axis1 ComponentsHTTPSender.
 * <p>
 * Adds the {@link LavercaContext}'s extra HTTP headers, records the raw request and response and the HTTP status
 * into it, and turns a call that gets no SOAP answer into the local "Unable to provide services" fault
 * ({@link LavercaSoapFaultException#unableToProvideServices}). SOAP messages are logged at TRACE level only.
 */
public class LavercaAxis2HttpSender implements TransportSender {

    private static final Log log = LogFactory.getLog(LavercaAxis2HttpSender.class);

    /** Options/MessageContext property of the call's {@link LavercaContext}. */
    public static final String LAVERCA_CONTEXT = "laverca.context";

    private static final String CONTENTTYPE_SOAP12 = "application/soap+xml; charset=utf-8";
    private static final String CONTENTTYPE_SOAP11 = "text/xml; charset=utf-8";

    /**
     * The JDK's own StAX parser, whatever the classpath registers: e.g. Woodstox, bundled in some libraries, rejects
     * attributes over 512 KB, such as a CRL in an MReg answer. No DTDs or external entities are read (XXE).
     */
    private static final XMLInputFactory SOAP_PARSER = XMLInputFactory.newDefaultFactory();
    static {
        SOAP_PARSER.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
        SOAP_PARSER.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
    }

    private final LavercaHttpClient httpClient;

    private HandlerDescription handlerDesc;

    /**
     * Creates a sender on the given client's connection pool.
     *
     * @param httpClient the client to send with
     */
    public LavercaAxis2HttpSender(final LavercaHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Initializes the sender; nothing to do, the client is ready.
     *
     * @param cfgCtx the configuration context
     * @param transportOut the transport description
     */
    @Override
    public void init(final ConfigurationContext cfgCtx, final TransportOutDescription transportOut) {
        // The HttpClient is already set up
    }

    /**
     * Stops the sender; the client and its pool stay open, as they belong to the MssClient.
     */
    @Override
    public void stop() {
        // The LavercaHttpClient is not ours to close
    }

    /**
     * Releases per-call resources; there are none.
     *
     * @param msgContext the message context
     */
    @Override
    public void cleanup(final MessageContext msgContext) {
        // Nothing held per call
    }

    /**
     * Sets the handler description.
     *
     * @param handlerDesc the handler description
     */
    @Override
    public void init(final HandlerDescription handlerDesc) {
        this.handlerDesc = handlerDesc;
    }

    /**
     * Gets the handler description.
     *
     * @return the handler description, or null
     */
    @Override
    public HandlerDescription getHandlerDesc() {
        return this.handlerDesc;
    }

    /**
     * Gets the handler name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return "LavercaAxis2HttpSender";
    }

    /**
     * Gets a parameter of the handler description.
     *
     * @param name parameter name
     * @return the parameter, or null
     */
    @Override
    public Parameter getParameter(final String name) {
        return (this.handlerDesc == null) ? null : this.handlerDesc.getParameter(name);
    }

    /**
     * Called when the message flow completes; nothing to do.
     *
     * @param msgContext the message context
     */
    @Override
    public void flowComplete(final MessageContext msgContext) {
        // No-op
    }

    /**
     * Sends the request envelope and hands the response envelope back to the waiting ServiceClient.
     *
     * @param msgContext the outgoing message context
     * @return {@link Handler.InvocationResponse#CONTINUE}
     * @throws AxisFault if the call fails; a failed connection carries a {@link LavercaSoapFaultException} as cause
     */
    @Override
    public Handler.InvocationResponse invoke(final MessageContext msgContext) throws AxisFault {
        final String targetUrl = msgContext.getTo().getAddress();
        final Object ctxObj = msgContext.getProperty(LAVERCA_CONTEXT);
        final LavercaContext lavercaContext = (ctxObj instanceof LavercaContext) ? (LavercaContext) ctxObj : null;

        final SOAPEnvelope outEnv = msgContext.getEnvelope();
        // ServiceClient always builds an envelope with an empty Header; Axis1 sent none
        final SOAPHeader outHeader = outEnv.getHeader();
        if (outHeader != null && outHeader.getFirstElement() == null) {
            outHeader.detach();
        }
        final String requestXml = outEnv.toString();
        if (lavercaContext != null) {
            lavercaContext.put(LavercaContext.RAW_REQUEST_XML, requestXml);
        }
        if (log.isDebugEnabled()) {
            log.debug("Calling " + targetUrl);
        }
        if (log.isTraceEnabled()) {
            log.trace("Outbound envelope:\n" + requestXml);
        }

        final HttpPost post = this.httpClient.buildHttpPost(targetUrl);
        post.setHeader("Content-Type", msgContext.isSOAP11() ? CONTENTTYPE_SOAP11 : CONTENTTYPE_SOAP12);
        final String soapAction = msgContext.getSoapAction();
        if (soapAction != null && !soapAction.isEmpty()) {
            post.setHeader("SOAPAction", "\"" + soapAction + "\"");
        }
        final boolean expectContinue = addExtraHeaders(msgContext, lavercaContext, post);

        final RequestConfig.Builder rcb = RequestConfig.custom()
            .setConnectionRequestTimeout(Timeout.ofMilliseconds(LavercaHttpClient.POOL_LEASE_TIMEOUT))
            .setExpectContinueEnabled(expectContinue);
        final long timeout = msgContext.getOptions().getTimeOutInMilliSeconds();
        if (timeout > 0) {
            rcb.setResponseTimeout(Timeout.ofMilliseconds(timeout));
        }
        post.setConfig(rcb.build());
        post.setEntity(new ByteArrayEntity(requestXml.getBytes(StandardCharsets.UTF_8), null));

        final HttpAnswer answer;
        try {
            answer = this.httpClient.execute(post, this.httpClient.buildContext(targetUrl), HttpAnswer::read);
        } catch (IOException e) {
            throw AxisFault.makeFault(connectionFault(e));
        }

        if (lavercaContext != null) {
            lavercaContext.put(LavercaContext.HTTP_STATUS_CODE,      Integer.valueOf(answer.status));
            lavercaContext.put(LavercaContext.HTTP_STATUS_MESSAGE,   answer.reason);
            lavercaContext.put(LavercaContext.HTTP_CONTENT_TYPE,     answer.contentType);
            lavercaContext.put(LavercaContext.HTTP_RESPONSE_HEADERS, answer.headers);
            lavercaContext.put(LavercaContext.RAW_RESPONSE_XML,      new String(answer.body, StandardCharsets.UTF_8));
        }
        if (log.isDebugEnabled()) {
            log.debug("HTTP status " + answer.status + " from " + targetUrl);
        }
        if (log.isTraceEnabled()) {
            log.trace("Inbound envelope:\n" + new String(answer.body, StandardCharsets.UTF_8));
        }

        final String statusMessage = "HTTP response: " + answer.status + " " + answer.reason;
        if (answer.body.length == 0) {
            throw AxisFault.makeFault(LavercaSoapFaultException.unableToProvideServices(statusMessage, answer.status, null));
        }

        final SOAPEnvelope inEnv;
        try {
            final XMLStreamReader reader = SOAP_PARSER.createXMLStreamReader(new ByteArrayInputStream(answer.body));
            inEnv = OMXMLBuilderFactory.createStAXSOAPModelBuilder(reader).getSOAPEnvelope();
            inEnv.build();
        } catch (Exception e) {
            // Not a SOAP message, e.g. an HTML error page
            throw AxisFault.makeFault(LavercaSoapFaultException.unableToProvideServices(statusMessage, answer.status, e));
        }

        // As Laverca 3's HeaderHandler: response headers count as processed, so mustUnderstand ones don't fail the call
        final SOAPHeader inHeader = inEnv.getHeader();
        if (inHeader != null) {
            for (final Iterator<SOAPHeaderBlock> it = inHeader.examineAllHeaderBlocks(); it.hasNext(); ) {
                it.next().setProcessed();
            }
        }

        deliverResponse(msgContext, inEnv);
        return Handler.InvocationResponse.CONTINUE;
    }

    /**
     * The local fault for a failed connection, as Laverca 3 made it.
     *
     * @param e the failure
     * @return the fault
     */
    private static LavercaSoapFaultException connectionFault(final IOException e) {
        final String msg;
        if (e instanceof ConnectException) {
            msg = "Connection refused: " + e.getMessage();
        } else if (e instanceof InterruptedIOException) {
            msg = "Communication timed out: " + e.getMessage();
        } else {
            msg = "Connection failed: " + e.getMessage();
        }
        return LavercaSoapFaultException.unableToProvideServices(msg, -1, e);
    }

    /**
     * Adds the LavercaContext's extra headers and those of the HTTPConstants.HTTP_HEADERS call option (a List of
     * NamedValue or a Map of name to value). "Expect: 100-continue" is not added but turns on expect-continue.
     *
     * @param msgContext     the outgoing message context
     * @param lavercaContext the call's context, or null
     * @param post           the HTTP request to add the headers to
     * @return true to enable expect-continue
     */
    private static boolean addExtraHeaders(final MessageContext msgContext,
                                           final LavercaContext lavercaContext,
                                           final HttpPost post)
    {
        final List<NamedValue> headers = new ArrayList<>();
        if (lavercaContext != null) {
            for (final Map.Entry<String, String> e : lavercaContext.getHeaders().entrySet()) {
                headers.add(new NamedValue(e.getKey(), e.getValue()));
            }
        }
        final Object headersObj = msgContext.getProperty(HTTPConstants.HTTP_HEADERS);
        if (headersObj instanceof List) {
            for (final Object o : (List<?>) headersObj) {
                if (o instanceof NamedValue) {
                    headers.add((NamedValue) o);
                }
            }
        } else if (headersObj instanceof Map) {
            for (final Map.Entry<?, ?> e : ((Map<?, ?>) headersObj).entrySet()) {
                headers.add(new NamedValue(String.valueOf(e.getKey()), String.valueOf(e.getValue())));
            }
        }
        boolean expectContinue = false;
        for (final NamedValue h : headers) {
            if (HTTPConstants.HEADER_EXPECT.equalsIgnoreCase(h.getName())
                && HTTPConstants.HEADER_EXPECT_100_Continue.equalsIgnoreCase(h.getValue())) {
                expectContinue = true;
            } else {
                post.addHeader(h.getName(), h.getValue());
            }
        }
        return expectContinue;
    }

    /**
     * Hands the parsed response envelope back to the blocking ServiceClient.sendReceive() caller: builds the IN
     * MessageContext of the operation and runs it through the engine's in flow.
     *
     * @param outMsgContext the outgoing message context
     * @param inEnv         the response envelope
     * @throws AxisFault if the in flow fails
     */
    private static void deliverResponse(final MessageContext outMsgContext, final SOAPEnvelope inEnv)
        throws AxisFault
    {
        final OperationContext opCtx = outMsgContext.getOperationContext();
        MessageContext responseMC = opCtx.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
        if (responseMC == null) {
            responseMC = new MessageContext();
            responseMC.setServerSide(false);
            responseMC.setConfigurationContext(outMsgContext.getConfigurationContext());
            responseMC.setTransportIn(outMsgContext.getTransportIn());
            responseMC.setTransportOut(outMsgContext.getTransportOut());
            responseMC.setAxisMessage(outMsgContext.getAxisOperation().getMessage(WSDLConstants.MESSAGE_LABEL_IN_VALUE));
            responseMC.setOperationContext(opCtx);
            responseMC.setServiceContext(outMsgContext.getServiceContext());
            responseMC.setAxisService(outMsgContext.getAxisService());
            opCtx.addMessageContext(responseMC);
        }
        responseMC.setEnvelope(inEnv);
        AxisEngine.receive(responseMC);
    }

    /**
     * What the HTTP answer held, read before its connection goes back to the pool.
     */
    private static final class HttpAnswer {

        final int                 status;
        final String              reason;
        final String              contentType;
        final Map<String, String> headers;
        final byte[]              body;

        private HttpAnswer(final int status, final String reason, final String contentType,
                           final Map<String, String> headers, final byte[] body)
        {
            this.status      = status;
            this.reason      = reason;
            this.contentType = contentType;
            this.headers     = headers;
            this.body        = body;
        }

        /**
         * Reads an HTTP response; HttpClient has already decompressed a gzip or deflate body.
         *
         * @param resp the response
         * @return the answer
         * @throws IOException if reading the body fails
         */
        static HttpAnswer read(final ClassicHttpResponse resp) throws IOException {
            final Map<String, String> headers = new LinkedHashMap<>();
            for (final Header h : resp.getHeaders()) {
                headers.put(h.getName(), h.getValue());
            }
            final HttpEntity ent = resp.getEntity();
            final byte[] body = (ent != null) ? EntityUtils.toByteArray(ent) : new byte[0];
            final Header ct = resp.getFirstHeader("Content-Type");
            return new HttpAnswer(resp.getCode(), resp.getReasonPhrase(), (ct != null) ? ct.getValue() : null, headers, body);
        }
    }
}
