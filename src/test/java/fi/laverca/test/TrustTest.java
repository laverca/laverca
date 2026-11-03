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

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import fi.laverca.mss.MssClient;
import fi.laverca.mss.MssConf;

/**
 * The server certificate check: none without a truststore, an exact match, or the issuer and mssp.uri SAN check.
 */
class TrustTest {

    @TempDir
    static Path dir;

    private static TestPki.Cert ca;
    private static TestPki.Cert server;
    private static SoapServer s;
    private static Path tsExact;
    private static Path tsOther;
    private static Path tsCa;

    @BeforeAll
    static void start() throws Exception {
        ca = TestPki.ca("ca");
        server = TestPki.issue("server", ca, TestPki.daysFromNow(-1), TestPki.daysFromNow(30), "http://mssp.test", false);
        s = SoapServer.https(TestPki.serverContext(server), false);
        tsExact = TestPki.truststore(dir.resolve("exact.p12"), server.cert);
        tsOther = TestPki.truststore(dir.resolve("other.p12"), TestPki.cert("other", ca).cert);
        tsCa = TestPki.truststore(dir.resolve("ca.p12"), ca.cert);
    }

    @AfterAll
    static void stop() {
        s.close();
    }

    private static MssClient client(final Path ts, final String checkIssuer, final String msspUri) {
        return client(s.url(), ts, checkIssuer, msspUri);
    }

    private static MssClient client(final String url, final Path ts, final String checkIssuer, final String msspUri) {
        final Properties p = new Properties();
        p.setProperty(MssConf.AP_ID, "ap");
        p.setProperty(MssConf.AP_PWD, "pwd");
        for (final String k : new String[] {MssConf.SIGNATURE_URL, MssConf.STATUS_URL, MssConf.RECEIPT_URL,
                                            MssConf.REGISTRATION_URL, MssConf.PROFILE_URL, MssConf.HANDSHAKE_URL}) {
            p.setProperty(k, url);
        }
        if (ts != null) {
            p.setProperty(MssConf.TRUSTSTORE_FILE, ts.toString());
            p.setProperty(MssConf.TRUSTSTORE_PWD, TestPki.PASSWORD);
            p.setProperty(MssConf.TRUSTSTORE_TYPE, "PKCS12");
        }
        if (checkIssuer != null) {
            p.setProperty(MssConf.CHECK_ISSUER, checkIssuer);
        }
        if (msspUri != null) {
            p.setProperty(MssConf.MSSP_URI, msspUri);
        }
        p.setProperty(MssConf.TRUSTSTORE_RELOAD_INTERVAL, "1");
        return new MssClient(MssConf.fromProperties(p));
    }

    private static void accepted(final MssClient c) throws Exception {
        c.send(Calls.sig(c));
    }

    private static void rejected(final MssClient c) {
        assertThrows(Exception.class, () -> c.send(Calls.sig(c)));
    }

    @Test
    void withoutTruststoreAnyCertificate() throws Exception {
        accepted(client(null, "false", null));
        accepted(client(null, "true", "http://other.test"));
    }

    @Test
    void exactMatch() throws Exception {
        accepted(client(tsExact, "false", null));
        rejected(client(tsOther, "false", null));
        rejected(client(tsCa, "false", null));
    }

    @Test
    void truststoreReload() throws Exception {
        final Path ts = TestPki.truststore(dir.resolve("live.p12"), TestPki.cert("other", ca).cert);
        final MssClient c = client(ts, "false", null);
        rejected(c);
        TestPki.truststore(ts, server.cert);
        Thread.sleep(1500);
        accepted(c);
        accepted(c);
        TestPki.truststore(ts, TestPki.cert("other", ca).cert);
        Thread.sleep(1500);
        rejected(c);
    }

    @Test
    void issuerAndSan() throws Exception {
        accepted(client(tsCa, "true", null));
        accepted(client(tsCa, "true", "http://mssp.test"));
        rejected(client(tsCa, "true", "http://other.test"));
        accepted(client(tsCa, null, "http://mssp.test"));
        rejected(client(tsCa, null, "http://other.test"));
        rejected(client(tsOther, "true", "http://mssp.test"));
    }

    @Test
    void issuerCheckValidityAndCa() throws Exception {
        final TestPki.Cert expired = TestPki.issue("server", ca, TestPki.daysFromNow(-10), TestPki.daysFromNow(-1), "http://mssp.test", false);
        final TestPki.Cert leaf = TestPki.cert("leaf", ca);
        final TestPki.Cert byLeaf = TestPki.issue("server", leaf, TestPki.daysFromNow(-1), TestPki.daysFromNow(30), "http://mssp.test", false);
        final TestPki.Cert self = TestPki.cert("self", null);
        try (SoapServer e = SoapServer.https(TestPki.serverContext(expired), false)) {
            rejected(client(e.url(), tsCa, "true", null));
            accepted(client(e.url(), TestPki.truststore(dir.resolve("expired.p12"), expired.cert), "true", null));
        }
        try (SoapServer l = SoapServer.https(TestPki.serverContext(byLeaf), false)) {
            rejected(client(l.url(), TestPki.truststore(dir.resolve("leaf.p12"), leaf.cert), "true", null));
        }
        try (SoapServer p = SoapServer.https(TestPki.serverContext(self), false)) {
            accepted(client(p.url(), TestPki.truststore(dir.resolve("self.p12"), self.cert), "true", null));
        }
    }

    @Test
    void issuerCheckOnAnotherThread() throws Exception {
        final MssClient good = client(tsCa, "true", "http://mssp.test");
        final MssClient bad = client(tsCa, "true", "http://other.test");
        final ExecutorService ex = Executors.newSingleThreadExecutor();
        try {
            ex.submit(() -> { accepted(good); return null; }).get();
            ex.submit(() -> { rejected(bad); return null; }).get();
        } finally {
            ex.shutdown();
        }
    }

    @Test
    void staticFactories() throws Exception {
        final MssClient any = Calls.client(s.url());
        any.setSSLSocketFactory(MssClient.createSSLFactory(null, null, null));
        accepted(any);
        final MssClient exact = Calls.client(s.url());
        exact.setSSLSocketFactory(MssClient.createSSLFactory(null, null, null, tsExact.toString(), TestPki.PASSWORD, "PKCS12"));
        accepted(exact);
        final MssClient other = Calls.client(s.url());
        other.setSSLSocketFactory(MssClient.createSSLFactory(null, null, null, tsOther.toString(), TestPki.PASSWORD, "PKCS12"));
        rejected(other);
    }
}
