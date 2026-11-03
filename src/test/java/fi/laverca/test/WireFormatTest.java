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

package fi.laverca.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.HandlerDescription;
import org.apache.axis2.engine.Phase;
import org.apache.axis2.handlers.AbstractHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fi.laverca.ErrorCodes;
import fi.laverca.LavercaSoapFaultException;
import fi.laverca.jaxb.mss.MSSRegistrationReq;
import fi.laverca.jaxb.mss.MSSRegistrationResp;
import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.wsssecext.Security;
import fi.laverca.mss.MssClient;
import fi.laverca.mss.ws.LavercaAxis2Engine;
import fi.laverca.util.LavercaContext;

/**
 * The SOAP wire format: requests, answers, SOAP faults and transport failures, as Laverca 3 handled them.
 */
class WireFormatTest {

    private SoapServer server;
    private MssClient client;

    @BeforeEach
    void start() throws Exception {
        this.server = SoapServer.http();
        this.client = Calls.client(this.server.url());
    }

    @AfterEach
    void stop() {
        this.server.close();
    }

    @Test
    void signatureRequestAndAnswer() throws Exception {
        final LavercaContext ctx = new LavercaContext();
        ctx.putHeader("X-Test", "yes");
        final MSSSignatureResp resp = this.client.send(Calls.sig(this.client), ctx);
        assertEquals("T1", resp.getMSSPTransID());
        assertEquals(500L, resp.getStatus().getStatusCode().getValue());
        assertTrue(this.server.lastContentType.startsWith("application/soap+xml"), this.server.lastContentType);
        assertTrue(this.server.lastBody.contains("MSS_SignatureReq"), "request body");
        assertEquals("yes", this.server.lastHeader);
        assertEquals(200, ctx.getHttpStatusCode());
        assertEquals(this.server.lastBody, ctx.getRawXmlRequest());
        assertTrue(ctx.getRawXmlResponse().contains("MSS_SignatureResp"));
    }

    @Test
    void documentStyleAnswer() throws Exception {
        final String bare = SoapServer.SIGRESP.replace("<MSS_SignatureResponse>", "").replace("</MSS_SignatureResponse>", "");
        this.server.answer(SoapServer.Answer.ok(SoapServer.env(bare, null)));
        final MSSSignatureResp resp = this.client.send(Calls.sig(this.client));
        assertEquals("T1", resp.getMSSPTransID());
        assertEquals(500L, resp.getStatus().getStatusCode().getValue());
    }

    @Test
    void inFlowRunsOncePerAnswer() throws Exception {
        final AtomicInteger runs = new AtomicInteger();
        final AbstractHandler counter = new AbstractHandler() {
            @Override
            public InvocationResponse invoke(final MessageContext mc) {
                runs.incrementAndGet();
                return InvocationResponse.CONTINUE;
            }
        };
        counter.init(new HandlerDescription("test-counter"));
        final Phase phase = LavercaAxis2Engine.getConfigurationContext().getAxisConfiguration().getInFlowPhases().get(0);
        phase.addHandler(counter);
        try {
            this.client.send(Calls.sig(this.client));
            assertEquals(1, runs.get(), "in-flow handler runs");
        } finally {
            phase.removeHandler(counter.getHandlerDesc());
        }
    }

    @Test
    void redirectNotFollowed() throws Exception {
        try (SoapServer other = SoapServer.http()) {
            for (final int status : new int[] {301, 302, 307, 308}) {
                this.server.answer(SoapServer.Answer.redirect(status, other.url()));
                assertThrows(LavercaSoapFaultException.class, () -> this.client.send(Calls.sig(this.client)), "HTTP " + status);
            }
            assertEquals(0, other.requests, "nothing sent to the redirect target");
        }
    }

    @Test
    void mustUnderstandHeaderInAnswer() throws Exception {
        this.server.answer(SoapServer.Answer.ok(SoapServer.env(SoapServer.SIGRESP,
            "<mss:MSS_MessageSignature xmlns:mss=\"" + SoapServer.NS + "\" soapenv:mustUnderstand=\"true\">x</mss:MSS_MessageSignature>")));
        assertEquals("T1", this.client.send(Calls.sig(this.client)).getMSSPTransID());
    }

    @Test
    void soapFault() {
        this.server.answer(new SoapServer.Answer(500, SoapServer.env(SoapServer.FAULT, null), 0));
        final LavercaSoapFaultException f = assertThrows(LavercaSoapFaultException.class, () -> this.client.send(Calls.sig(this.client)));
        assertTrue(f.isReceived());
        assertEquals("Receiver", f.getFaultCode().getLocalPart());
        assertEquals("_105", f.getFaultSubCodes().get(0).getLocalPart());
        assertEquals("_1052", f.getFaultSubCodes().get(1).getLocalPart());
        assertEquals("UNKNOWN_CLIENT", f.getFaultReason());
        assertEquals("http://role", f.getFaultRole());
        assertTrue(ErrorCodes.UNKNOWN_CLIENT.is(f));
        assertFalse(ErrorCodes.WRONG_PARAM.is(f));
    }

    @Test
    void emptyAnswer() {
        this.server.answer(new SoapServer.Answer(503, "", 0));
        final LavercaSoapFaultException f = assertThrows(LavercaSoapFaultException.class, () -> this.client.send(Calls.sig(this.client)));
        assertTrue(ErrorCodes.UNABLE_TO_PROVIDE_SERVICES.is(f));
        assertTrue(f.getFaultDetailString().contains("503"), f.getFaultDetailString());
    }

    @Test
    void htmlAnswer() {
        this.server.answer(new SoapServer.Answer(500, "<html><body>Internal error</body></html>", 0));
        final LavercaSoapFaultException f = assertThrows(LavercaSoapFaultException.class, () -> this.client.send(Calls.sig(this.client)));
        assertTrue(ErrorCodes.UNABLE_TO_PROVIDE_SERVICES.is(f));
    }

    @Test
    void registrationWithSecurityHeader() throws Exception {
        this.server.answer(SoapServer.Answer.ok(SoapServer.env(SoapServer.REGRESP, null)));
        final MSSRegistrationReq rr = this.client.createRegistrationReq("A2");
        rr.getMobileUser().setMSISDN("+35847001001");
        final MSSRegistrationResp resp = this.client.send(rr, null, new Security());
        assertEquals(100L, resp.getStatus().getStatusCode().getValue());
        assertTrue(this.server.lastBody.contains("Security"), "WSSE header in the request");
    }

    @Test
    void readTimeout() {
        final MssClient c = Calls.client(this.server.url());
        c.setSocketTimeout(1000);
        this.server.answer(new SoapServer.Answer(200, SoapServer.env(SoapServer.SIGRESP, null), 3000));
        final MSSSignatureReq slow = Calls.sig(c);
        slow.setTimeOut(null);
        final long t0 = System.currentTimeMillis();
        final LavercaSoapFaultException f = assertThrows(LavercaSoapFaultException.class, () -> c.send(slow));
        final long took = System.currentTimeMillis() - t0;
        assertTrue(took >= 900 && took < 2900, "timed out after " + took + " ms");
        assertTrue(ErrorCodes.UNABLE_TO_PROVIDE_SERVICES.is(f));
    }

    @Test
    void connectionRefused() {
        final String url = this.server.url();
        this.server.close();
        final MssClient c = Calls.client(url);
        final LavercaSoapFaultException f = assertThrows(LavercaSoapFaultException.class, () -> c.send(Calls.sig(c)));
        assertTrue(ErrorCodes.UNABLE_TO_PROVIDE_SERVICES.is(f));
    }

    @Test
    void ncNames() {
        assertTrue(MssClient.isNCName("A1"));
        assertTrue(MssClient.isNCName("_x-1.2"));
        assertFalse(MssClient.isNCName("1abc"));
        assertFalse(MssClient.isNCName("a:b"));
        assertFalse(MssClient.isNCName(""));
    }
}
