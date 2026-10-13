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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsExchange;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

/**
 * A loopback MSSP for the tests: answers each POST with the current {@link Answer}, and records the request,
 * the client's TCP ports (one per connection) and, over TLS, the client certificate's subject.
 */
public final class SoapServer implements AutoCloseable {

    public static final String NS = "http://uri.etsi.org/TS102204/v1.1.2#";

    /** A canned answer: HTTP status, body (empty for none) and an optional delay. */
    public record Answer(int status, String body, long delayMillis) {
        public static Answer ok(final String body) {
            return new Answer(200, body, 0);
        }
    }

    public static final String SIGRESP = "<MSS_SignatureResponse><mss:MSS_SignatureResp MajorVersion=\"1\" MinorVersion=\"1\" MSSP_TransID=\"T1\" xmlns:mss=\"" + NS + "\">"
        + "<mss:AP_Info AP_ID=\"ap\" AP_TransID=\"A1\" AP_PWD=\"x\" Instant=\"2026-10-12T10:00:00.000Z\"/>"
        + "<mss:MSSP_Info Instant=\"2026-10-12T10:00:00.000Z\"><mss:MSSP_ID><mss:URI>http://mssp</mss:URI></mss:MSSP_ID></mss:MSSP_Info>"
        + "<mss:MobileUser><mss:MSISDN>+35847001001</mss:MSISDN></mss:MobileUser>"
        + "<mss:MSS_Signature><mss:Base64Signature>AAAA</mss:Base64Signature></mss:MSS_Signature>"
        + "<mss:SignatureProfile><mss:mssURI>http://p</mss:mssURI></mss:SignatureProfile>"
        + "<mss:Status><mss:StatusCode Value=\"500\"/><mss:StatusMessage>SIGNATURE</mss:StatusMessage></mss:Status></mss:MSS_SignatureResp></MSS_SignatureResponse>";

    public static final String FAULT = "<soapenv:Fault><soapenv:Code><soapenv:Value>soapenv:Receiver</soapenv:Value>"
        + "<soapenv:Subcode><soapenv:Value xmlns:mss=\"" + NS + "\">mss:_105</soapenv:Value>"
        + "<soapenv:Subcode><soapenv:Value xmlns:mss=\"" + NS + "\">mss:_1052</soapenv:Value></soapenv:Subcode></soapenv:Subcode></soapenv:Code>"
        + "<soapenv:Reason><soapenv:Text xml:lang=\"en\">UNKNOWN_CLIENT</soapenv:Text></soapenv:Reason><soapenv:Role>http://role</soapenv:Role>"
        + "<soapenv:Detail><ficom:detail xmlns:ficom=\"http://mss.ficom.fi/TS102204/v1.0.0#\">User not found</ficom:detail></soapenv:Detail></soapenv:Fault>";

    public static final String REGRESP = "<MSS_RegistrationResponse><mss:MSS_RegistrationResp MajorVersion=\"1\" MinorVersion=\"1\" xmlns:mss=\"" + NS + "\">"
        + "<mss:AP_Info AP_ID=\"ap\" AP_TransID=\"A1\" AP_PWD=\"x\" Instant=\"2026-10-12T10:00:00.000Z\"/>"
        + "<mss:MSSP_Info Instant=\"2026-10-12T10:00:00.000Z\"><mss:MSSP_ID/></mss:MSSP_Info>"
        + "<mss:Status><mss:StatusCode Value=\"100\"/></mss:Status></mss:MSS_RegistrationResp></MSS_RegistrationResponse>";

    /** @return a SOAP 1.2 envelope around the body, with an optional header block */
    public static String env(final String body, final String header) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://www.w3.org/2003/05/soap-envelope\">"
            + ((header == null) ? "" : "<soapenv:Header>" + header + "</soapenv:Header>")
            + "<soapenv:Body>" + body + "</soapenv:Body></soapenv:Envelope>";
    }

    private final HttpServer server;
    private final ExecutorService executor;
    private volatile Answer answer = Answer.ok(env(SIGRESP, null));

    public final Set<Integer> clientPorts = Collections.synchronizedSet(new LinkedHashSet<>());
    public final List<String> clientCerts = new CopyOnWriteArrayList<>();
    public volatile String lastBody;
    public volatile String lastContentType;
    public volatile String lastHeader;
    public volatile int requests;

    private SoapServer(final HttpServer server) {
        this.server = server;
        this.executor = Executors.newFixedThreadPool(8, r -> {
            final Thread t = new Thread(r, "soap-server");
            t.setDaemon(true);
            return t;
        });
        this.server.setExecutor(this.executor);
        this.server.createContext("/", this::handle);
        this.server.start();
    }

    /** @return a plain HTTP server */
    public static SoapServer http() throws IOException {
        return new SoapServer(HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0));
    }

    /**
     * Creates an HTTPS server.
     *
     * @param ctx the server's TLS context (see {@link TestPki#serverContext})
     * @param needClientAuth whether a client certificate is required (else only requested)
     * @return the server
     * @throws IOException if the server cannot be started
     */
    public static SoapServer https(final SSLContext ctx, final boolean needClientAuth) throws IOException {
        final HttpsServer s = HttpsServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        s.setHttpsConfigurator(new HttpsConfigurator(ctx) {
            @Override
            public void configure(final HttpsParameters p) {
                final SSLParameters sp = getSSLContext().getDefaultSSLParameters();
                if (needClientAuth) {
                    sp.setNeedClientAuth(true);
                } else {
                    sp.setWantClientAuth(true);
                }
                p.setSSLParameters(sp);
            }
        });
        return new SoapServer(s);
    }

    /** @return the server's URL */
    public String url() {
        final String scheme = (this.server instanceof HttpsServer) ? "https" : "http";
        return scheme + "://127.0.0.1:" + this.server.getAddress().getPort() + "/soap/services/MSS_SignaturePort";
    }

    /** Sets the answer to the next requests. */
    public void answer(final Answer a) {
        this.answer = a;
    }

    /** Forgets the recorded client ports and certificates. */
    public void reset() {
        this.clientPorts.clear();
        this.clientCerts.clear();
    }

    private void handle(final HttpExchange ex) throws IOException {
        this.requests++;
        this.clientPorts.add(ex.getRemoteAddress().getPort());
        if (ex instanceof HttpsExchange hx) {
            String peer;
            try {
                peer = hx.getSSLSession().getPeerPrincipal().getName();
            } catch (Exception e) {
                peer = "none";
            }
            this.clientCerts.add(peer);
        }
        this.lastBody = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        this.lastContentType = ex.getRequestHeaders().getFirst("Content-Type");
        this.lastHeader = ex.getRequestHeaders().getFirst("X-Test");
        final Answer a = this.answer;
        if (a.delayMillis() > 0) {
            try {
                Thread.sleep(a.delayMillis());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        final byte[] b = a.body().getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", "application/soap+xml; charset=utf-8");
        ex.sendResponseHeaders(a.status(), (b.length == 0) ? -1 : b.length);
        if (b.length > 0) {
            ex.getResponseBody().write(b);
        }
        ex.close();
    }

    @Override
    public void close() {
        this.server.stop(0);
        this.executor.shutdownNow();
    }
}
