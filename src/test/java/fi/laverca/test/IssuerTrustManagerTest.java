package fi.laverca.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.net.ssl.X509TrustManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import fi.laverca.util.IssuerTrustManager;
import fi.laverca.util.LavercaSSLTrustManager;

/**
 * The global trust managers kept for applications' own SSLContexts (MET-844).
 */
class IssuerTrustManagerTest {

    private static final String MSSP_A = "http://mssp-a.example";
    private static final String MSSP_B = "http://mssp-b.example";

    @AfterEach
    void clean() {
        IssuerTrustManager.getInstance().clearExpectedMsspURIs();
        IssuerTrustManager.getInstance().setIssuerCerts(null);
        IssuerTrustManager.getInstance().setNextTrustManager(null);
        LavercaSSLTrustManager.getInstance().setNextTrustManager(null);
    }

    @Test
    void earlierCallsMsspUriNotAcceptedAfterClear() throws Exception {
        final TestPki.Cert ca = TestPki.ca("Test CA");
        final TestPki.Cert serverA = TestPki.issue("mssp-a", ca, TestPki.daysFromNow(-1), TestPki.daysFromNow(30), MSSP_A, false);
        final IssuerTrustManager itm = IssuerTrustManager.getInstance();
        itm.setIssuerCerts(List.of(ca.cert));

        // A call to MSSP A on this thread
        itm.addExpectedMsspURI(MSSP_A);
        itm.checkServerTrusted(serverA.chain(), "EC");
        itm.clearExpectedMsspURIs();

        // The next call on the same thread goes to MSSP B: MSSP A's certificate is refused
        itm.addExpectedMsspURI(MSSP_B);
        assertThrows(CertificateException.class, () -> itm.checkServerTrusted(serverA.chain(), "EC"));
    }

    @Test
    void checkClientTrustedListPassesTheChain() throws Exception {
        final TestPki.Cert c = TestPki.cert("client", null);
        final AtomicReference<X509Certificate[]> seen = new AtomicReference<>();
        final X509TrustManager next = new X509TrustManager() {
            @Override
            public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
                seen.set(chain);
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
                // Not used
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        IssuerTrustManager.getInstance().setNextTrustManager(next);
        IssuerTrustManager.getInstance().checkClientTrusted(List.of(c.cert), null, "EC");
        assertArrayEquals(new X509Certificate[] {c.cert}, seen.get());

        seen.set(null);
        LavercaSSLTrustManager.getInstance().setNextTrustManager(next);
        LavercaSSLTrustManager.getInstance().checkClientTrusted(List.of(c.cert), null, "EC");
        assertArrayEquals(new X509Certificate[] {c.cert}, seen.get());
    }
}
