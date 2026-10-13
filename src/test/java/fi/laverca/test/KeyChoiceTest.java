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

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.cert.X509Certificate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import fi.laverca.mss.MssClient;
import fi.laverca.mss.MssConf;

/**
 * The client key: the oldest unexpired key of a reloading keystore, among the keys matching the issuers the server
 * names when it names any.
 */
class KeyChoiceTest {

    @TempDir
    Path dir;

    /** Sends one request and returns the client certificate the server saw. */
    private static String call(final SoapServer s, final MssClient c) throws Exception {
        s.reset();
        c.send(Calls.sig(c));
        return s.clientCerts.get(0);
    }

    private MssClient client(final SoapServer s, final Path keystore) {
        final MssConf conf = Calls.conf(s.url());
        conf.setKeystore(keystore.toString(), TestPki.PASSWORD, "PKCS12");
        conf.setKeystoreReloadInterval(1);
        return new MssClient(conf);
    }

    @Test
    void rotationOnExpiryAndReload() throws Exception {
        final TestPki.Cert ca = TestPki.ca("ca");
        final TestPki.Cert a = TestPki.issue("keyA", ca, TestPki.daysFromNow(-1), TestPki.secondsFromNow(3), null, false);
        final TestPki.Cert b = TestPki.issue("keyB", ca, TestPki.secondsFromNow(-3600), TestPki.daysFromNow(30), null, false);
        final TestPki.Cert older = TestPki.issue("keyC", ca, TestPki.daysFromNow(-2), TestPki.daysFromNow(30), null, false);
        final Path ks = TestPki.keystore(this.dir.resolve("client.p12"), a, b);
        try (SoapServer s = SoapServer.https(TestPki.serverContext(TestPki.cert("server", ca)), true)) {
            final MssClient c = this.client(s, ks);
            assertEquals("CN=keyA", call(s, c), "oldest unexpired");
            Thread.sleep(a.cert.getNotAfter().getTime() - System.currentTimeMillis() + 500);
            assertEquals("CN=keyB", call(s, c), "after A expired, without a file change");

            TestPki.keystore(ks, a, b, older);
            Thread.sleep(1500);
            assertEquals("CN=keyC", call(s, c), "an older key added to the file");

            Files.writeString(ks, "not a keystore");
            Thread.sleep(1500);
            assertEquals("CN=keyC", call(s, c), "a broken file keeps the current keys");
        }
    }

    @Test
    void lastExpiredKeyStaysInUse() throws Exception {
        final TestPki.Cert ca = TestPki.ca("ca");
        final TestPki.Cert f = TestPki.issue("keyF", ca, TestPki.daysFromNow(-1), TestPki.secondsFromNow(2), null, false);
        final TestPki.Cert g = TestPki.issue("keyG", ca, TestPki.daysFromNow(-10), TestPki.daysFromNow(-9), null, false);
        final Path ks = TestPki.keystore(this.dir.resolve("client.p12"), f, g);
        try (SoapServer s = SoapServer.https(TestPki.serverContext(TestPki.cert("server", ca)), true)) {
            final MssClient c = this.client(s, ks);
            assertEquals("CN=keyF", call(s, c));
            Thread.sleep(f.cert.getNotAfter().getTime() - System.currentTimeMillis() + 500);
            assertEquals("CN=keyF", call(s, c), "all expired: the one that expired last");
        }
    }

    @Test
    void keystoreWithoutKeys() throws Exception {
        final TestPki.Cert ca = TestPki.ca("ca");
        final Path ks = TestPki.truststore(this.dir.resolve("client.p12"), ca.cert);
        try (SoapServer s = SoapServer.https(TestPki.serverContext(TestPki.cert("server", ca)), false)) {
            final MssClient c = this.client(s, ks);
            assertEquals("none", call(s, c), "no client certificate");
            TestPki.keystore(ks, TestPki.cert("client", ca));
            Thread.sleep(1500);
            assertEquals("CN=client", call(s, c), "a key added to the file");
        }
    }

    @Test
    void matchingKeyReplacedWhileTheDefaultStays() throws Exception {
        final TestPki.Cert caX = TestPki.ca("caX");
        final TestPki.Cert caY = TestPki.ca("caY");
        final TestPki.Cert dflt = TestPki.issue("keyS", caX, TestPki.daysFromNow(-3), TestPki.daysFromNow(30), null, false);
        final TestPki.Cert m1 = TestPki.issue("keyM1", caY, TestPki.daysFromNow(-1), TestPki.daysFromNow(30), null, false);
        final TestPki.Cert m2 = TestPki.issue("keyM2", caY, TestPki.daysFromNow(-1), TestPki.daysFromNow(30), null, false);
        final Path ks = TestPki.keystore(this.dir.resolve("client.p12"), dflt, m1);
        try (SoapServer s = SoapServer.https(TestPki.serverContext(TestPki.cert("server", caX), caY.cert), true)) {
            final MssClient c = this.client(s, ks);
            assertEquals("CN=keyM1", call(s, c));
            TestPki.keystore(ks, dflt, m2);
            Thread.sleep(1500);
            assertEquals("CN=keyM2", call(s, c), "the replaced key, also on a pooled connection");
        }
    }

    @Test
    void keyMatchingTheRequestedIssuers() throws Exception {
        final TestPki.Cert caX = TestPki.ca("caX");
        final TestPki.Cert caY = TestPki.ca("caY");
        final TestPki.Cert caZ = TestPki.ca("caZ");
        final TestPki.Cert a = TestPki.issue("keyA", caY, TestPki.daysFromNow(-2), TestPki.daysFromNow(30), null, false);
        final TestPki.Cert b = TestPki.issue("keyB", caX, TestPki.secondsFromNow(-3600), TestPki.daysFromNow(30), null, false);
        final Path ks = TestPki.keystore(this.dir.resolve("client.p12"), a, b);
        final TestPki.Cert server = TestPki.cert("server", caX);
        for (final Object[] c : new Object[][] {{caX.cert, "CN=keyB"}, {caY.cert, "CN=keyA"}, {caZ.cert, "CN=keyA"}, {null, "CN=keyA"}}) {
            final X509Certificate[] named = (c[0] == null) ? new X509Certificate[0] : new X509Certificate[] {(X509Certificate) c[0]};
            try (SoapServer s = SoapServer.https(TestPki.serverContext(server, named), true)) {
                assertEquals(c[1], call(s, this.client(s, ks)), "server names " + ((c[0] == null) ? "no issuers" : ((X509Certificate) c[0]).getSubjectX500Principal()));
            }
        }
    }
}
