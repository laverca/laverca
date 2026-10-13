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
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import fi.laverca.mss.MssClient;
import fi.laverca.mss.MssConf;
import fi.laverca.mss.ws.LavercaAxis2Engine;

/**
 * Connection pooling: connections reused, also with a client certificate, and rebuilt when a setter changes them.
 */
class PoolTest {

    @TempDir
    Path dir;

    @Test
    void plainConnectionsReused() throws Exception {
        try (SoapServer s = SoapServer.http()) {
            final MssClient c = Calls.client(s.url());
            for (int i = 0; i < 50; i++) {
                c.send(Calls.sig(c));
            }
            assertEquals(1, s.clientPorts.size(), "connections for 50 calls");
            assertEquals(0, LavercaAxis2Engine.getConfigurationContext().getAxisConfiguration().getServices().size(),
                         "anonymous services left in the engine");
        }
    }

    @Test
    void clientCertificateConnectionsReused() throws Exception {
        final TestPki.Cert ca = TestPki.ca("pool-ca");
        try (SoapServer s = SoapServer.https(TestPki.serverContext(TestPki.cert("server", ca)), true)) {
            final MssConf conf = Calls.conf(s.url());
            conf.setKeystore(TestPki.keystore(this.dir.resolve("client.p12"), TestPki.cert("client", ca)).toString(), TestPki.PASSWORD, "PKCS12");
            final MssClient c = new MssClient(conf);
            for (int i = 0; i < 20; i++) {
                c.send(Calls.sig(c));
            }
            assertEquals(1, s.clientPorts.size(), "connections for 20 calls");
            assertEquals(List.of("CN=client"), s.clientCerts.stream().distinct().toList());
        }
    }

    @Test
    void setterRebuildsThePool() throws Exception {
        final TestPki.Cert ca = TestPki.ca("pool-ca");
        try (SoapServer s = SoapServer.https(TestPki.serverContext(TestPki.cert("server", ca)), true)) {
            final MssConf conf1 = Calls.conf(s.url());
            conf1.setKeystore(TestPki.keystore(this.dir.resolve("c1.p12"), TestPki.cert("client1", ca)).toString(), TestPki.PASSWORD, "PKCS12");
            final MssConf conf2 = Calls.conf(s.url());
            conf2.setKeystore(TestPki.keystore(this.dir.resolve("c2.p12"), TestPki.cert("client2", ca)).toString(), TestPki.PASSWORD, "PKCS12");
            final MssClient c = new MssClient(conf1);
            c.send(Calls.sig(c));
            c.setSSLSocketFactory(conf2.createSSLFactory());
            c.send(Calls.sig(c));
            assertEquals(List.of("CN=client1", "CN=client2"), s.clientCerts);
            assertEquals(2, s.clientPorts.size(), "a new connection after the setter");
        }
    }

    @Test
    void callInProgressSurvivesARebuild() throws Exception {
        try (SoapServer s = SoapServer.http()) {
            final MssClient c = Calls.client(s.url());
            s.answer(new SoapServer.Answer(200, SoapServer.env(SoapServer.SIGRESP, null), 1500));
            final CompletableFuture<Throwable> slow = CompletableFuture.supplyAsync(() -> {
                try {
                    c.send(Calls.sig(c));
                    return null;
                } catch (Throwable t) {
                    return t;
                }
            });
            Thread.sleep(500);
            c.setSocketTimeout(20000);
            assertNull(slow.get(), "the call in progress");
            s.answer(SoapServer.Answer.ok(SoapServer.env(SoapServer.SIGRESP, null)));
            c.send(Calls.sig(c));
        }
    }
}
