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

import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.mss.MssClient;
import fi.laverca.mss.MssConf;
import fi.laverca.util.DTBS;

/**
 * Shared request and client builders for the tests.
 */
final class Calls {

    private Calls() {
        // Static only
    }

    /** @return a client with the same URL for every service */
    static MssClient client(final String url) {
        return new MssClient("ap", "pwd", url, url, url, url, url, url);
    }

    /** @return a configuration with the same URL for every service */
    static MssConf conf(final String url) {
        return new MssConf("ap", "pwd", url, url, url, url, url, url);
    }

    /** @return a synchronous signature request */
    static MSSSignatureReq sig(final MssClient c) {
        final MSSSignatureReq r = c.createSignatureRequest("A1", "+35847001001", new DTBS("sign this", "UTF-8"), "Hello",
                                                          "http://p", "http://uri.etsi.org/TS102204/v1.1.2#PKCS7", MessagingModeType.SYNCH);
        r.setTimeOut(Long.valueOf(60));
        return r;
    }
}
