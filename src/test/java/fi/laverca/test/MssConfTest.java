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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import fi.laverca.mss.MssConf;

/**
 * MssConf properties: defaults, parsing and the getProperties() round trip.
 */
class MssConfTest {

    private static Properties props(final String... kv) {
        final Properties p = new Properties();
        for (int i = 0; i < kv.length; i += 2) {
            p.setProperty(kv[i], kv[i + 1]);
        }
        return p;
    }

    @Test
    void checkIssuer() {
        assertTrue(MssConf.fromProperties(props()).isCheckIssuer(), "unset means true");
        assertTrue(MssConf.fromProperties(props(MssConf.CHECK_ISSUER, "true")).isCheckIssuer());
        assertFalse(MssConf.fromProperties(props(MssConf.CHECK_ISSUER, "false")).isCheckIssuer());
    }

    @Test
    void msspUriList() {
        assertEquals(List.of("http://a", "http://b"), MssConf.fromProperties(props(MssConf.MSSP_URI, "http://a,http://b")).getMsspUri());
        assertEquals(List.of(), MssConf.fromProperties(props()).getMsspUri());
    }

    @Test
    void reloadIntervals() {
        final MssConf c = MssConf.fromProperties(props(MssConf.KEYSTORE_RELOAD_INTERVAL, " 15 ", MssConf.TRUSTSTORE_RELOAD_INTERVAL, "60"));
        assertEquals(15, c.getKeystoreReloadInterval());
        assertEquals(60, c.getTruststoreReloadInterval());
    }

    @Test
    void emptyConfiguration() {
        final Properties p = new MssConf().getProperties();
        assertEquals("false", p.getProperty(MssConf.CHECK_ISSUER), "a constructed MssConf has the issuer check off");
        assertFalse(p.containsKey(MssConf.AP_ID));
    }

    @Test
    void roundTrip() {
        final MssConf c = new MssConf();
        c.setApId("http://ap");
        c.setCheckIssuer(false);
        c.setTruststore("/tmp/ts.p12", "pw", "PKCS12");
        c.setKeystore("/tmp/ks.p12", "kpw", "PKCS12");
        c.setKeystoreReloadInterval(20);
        c.setMsspUri("http://mssp.test");
        final MssConf back = MssConf.fromProperties(c.getProperties());
        assertEquals(c.getProperties(), back.getProperties());
        assertEquals("http://ap", back.getApId());
        assertFalse(back.isCheckIssuer());
        assertEquals("/tmp/ts.p12", back.getTruststore());
        assertEquals(20, back.getKeystoreReloadInterval());
        assertEquals(List.of("http://mssp.test"), back.getMsspUri());
    }
}
