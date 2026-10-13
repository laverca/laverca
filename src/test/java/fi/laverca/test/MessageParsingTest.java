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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import fi.laverca.CmsSignature;
import fi.laverca.etsi.EtsiResponse;
import fi.laverca.ficom.FiComAdditionalServices;
import fi.laverca.ficom.FiComClient;
import fi.laverca.ficom.PersonIdAttribute;
import fi.laverca.jaxb.mss.MSSProfileResp;
import fi.laverca.jaxb.mss.MSSRegistrationReq;
import fi.laverca.jaxb.mss.MSSRegistrationResp;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.mss.ObjectFactory;
import fi.laverca.jaxb.mss.StatusType;
import fi.laverca.jaxb.saml2a.Attribute;
import fi.laverca.mss.AdditionalServices;
import fi.laverca.mss.MssException;
import fi.laverca.mss.MssResponse;
import fi.laverca.mss.ProfileQueryResponse;
import fi.laverca.registration.MregResponse;
import fi.laverca.util.FiComCharsetLimiter;
import fi.laverca.util.X509CertificateChain;

/**
 * Responses without their optional parts, and the helpers that read them (MET-778 review 2, items 8-15).
 */
class MessageParsingTest {

    private static final ObjectFactory MSS = new ObjectFactory();

    /** @return a status with an empty StatusDetail */
    private static StatusType statusWithEmptyDetail() {
        final StatusType st = MSS.createStatusType();
        st.setStatusDetail(MSS.createStatusDetailType());
        return st;
    }

    @Test
    void statusDetailWithoutServiceResponses() {
        final MSSSignatureResp resp = MSS.createMSSSignatureResp();
        resp.setStatus(statusWithEmptyDetail());
        final MssResponse r = new EtsiResponse(Calls.sig(Calls.client("http://127.0.0.1:1/soap")), resp, null);
        assertTrue(r.getAdditionalServiceResponses().isEmpty());
        assertFalse(r.isBatchSignature());
        assertTrue(r.isBatchSignatureComplete());
        assertNull(AdditionalServices.readServiceResponse(resp.getStatus().getStatusDetail(), "http://any"));
    }

    @Test
    void mregAnswerWithoutOutput() throws Exception {
        try (SoapServer s = SoapServer.http()) {
            s.answer(SoapServer.Answer.ok(SoapServer.env(SoapServer.REGRESP, null)));
            final FiComClient c = new FiComClient("ap", "pwd", s.url(), s.url(), s.url(), s.url(), s.url(), s.url());
            final MSSRegistrationReq req = c.getMssClient().createRegistrationReq("A1");
            final MregResponse r = new MregResponse(c.getMssClient().send(req));
            assertTrue(r.isSuccessful(), "an async answer, status 100");
            assertNull(r.getOperationOutput());
            assertNull(r.getStatusDetail());
            assertEquals(-1, r.getStatusCode());
        }
    }

    @Test
    void ficomRegistrationUrl() throws Exception {
        try (SoapServer receipt = SoapServer.http(); SoapServer registration = SoapServer.http()) {
            registration.answer(SoapServer.Answer.ok(SoapServer.env(SoapServer.REGRESP, null)));
            final String url = receipt.url();
            final FiComClient c = new FiComClient("ap", "pwd", url, url, url, registration.url(), url, url);
            final MSSRegistrationResp resp = c.getMssClient().send(c.getMssClient().createRegistrationReq("A1"));
            assertEquals(1, registration.requests, "registration request at the registration URL");
            assertEquals(0, receipt.requests);
            assertEquals(100L, resp.getStatus().getStatusCode().getValue());
        }
    }

    @Test
    void profileWithoutExtension() {
        final MSSProfileResp resp = MSS.createMSSProfileResp();
        resp.setStatus(statusWithEmptyDetail());
        final ProfileQueryResponse r = new ProfileQueryResponse(resp);
        assertTrue(r.getAllCertificates().isEmpty());
        assertTrue(r.getNonRepudiationCertificate().isEmpty());
        assertTrue(r.getDigitalSignatureCertificate().isEmpty());
        assertTrue(r.getCertificate("http://p").isEmpty());
    }

    @Test
    void certificateWithoutKeyUsage() throws Exception {
        final X509CertificateChain chain = new X509CertificateChain();
        chain.add(TestPki.cert("user", TestPki.ca("ca")).cert);
        assertNull(chain.getSigningCert().getKeyUsage(), "test certificate has no KeyUsage");
        assertFalse(chain.isNonRepudiation());
        assertFalse(chain.isDigitalSignature());
    }

    @Test
    void personIdValues() throws Exception {
        final Attribute hetu = new fi.laverca.jaxb.saml2a.ObjectFactory().createAttribute();
        hetu.setName(FiComAdditionalServices.PERSON_ID_HETU);
        hetu.getAttributeValues().add("010101-123N");
        assertEquals("010101-123N", new PersonIdAttribute(hetu).getStringValue());

        final Element e = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument().createElement("AttributeValue");
        e.setTextContent("Matti");
        final Attribute name = new fi.laverca.jaxb.saml2a.ObjectFactory().createAttribute();
        name.setName("http://mss.ficom.fi/TS102204/v1.0.0/PersonID#givenName");
        name.getAttributeValues().add(e);
        assertEquals("Matti", new PersonIdAttribute(name).getStringValue());
    }

    /** @return a CMS SignedData over "data" by the signer, with the given certificates */
    private static byte[] cms(final TestPki.Cert signer, final X509Certificate... certs) throws Exception {
        final CMSSignedDataGenerator g = new CMSSignedDataGenerator();
        g.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().build())
            .build(new JcaContentSignerBuilder("SHA256withECDSA").build(signer.key), signer.cert));
        if (certs.length > 0) {
            g.addCertificates(new JcaCertStore(List.of(certs)));
        }
        return g.generate(new CMSProcessableByteArray("data".getBytes(StandardCharsets.UTF_8)), false).getEncoded();
    }

    @Test
    void cmsWithoutCertificates() throws Exception {
        final TestPki.Cert signer = TestPki.cert("signer", TestPki.ca("ca"));
        final CmsSignature sig = new CmsSignature(cms(signer));
        assertTrue(sig.getCertificates().isEmpty());
        assertThrows(MssException.class, sig::getSignerCert);
        assertTrue(sig.getCertificateChain().isEmpty());
        assertThrows(IllegalArgumentException.class, () -> new CmsSignature(new byte[0]));
    }

    @Test
    void cmsChainStartsWithTheSigner() throws Exception {
        final TestPki.Cert ca = TestPki.ca("ca");
        final TestPki.Cert signer = TestPki.cert("signer", ca);
        for (final X509Certificate[] order : new X509Certificate[][] {{ca.cert, signer.cert}, {signer.cert, ca.cert}}) {
            final X509CertificateChain chain = new CmsSignature(cms(signer, order)).getCertificateChain();
            assertEquals(signer.cert, chain.getSigningCert());
            assertEquals(2, chain.size());
        }
    }

    @Test
    void gsmConversionReplacesOtherCharacters() {
        assertArrayEquals(new byte[] {0x04, 'a', '?', 'b'}, FiComCharsetLimiter.toGSM0338("ašb"));
        assertArrayEquals(new byte[] {0x04, 'a', '?', '?', 'b'}, FiComCharsetLimiter.toGSM0338("a😀b"));
    }
}
