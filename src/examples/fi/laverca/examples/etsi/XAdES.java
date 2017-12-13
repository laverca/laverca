/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2017 Laverca Project
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

package fi.laverca.examples.etsi;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.axis.AxisFault;

import eu.europa.esig.dss.DSSDocument;
import eu.europa.esig.dss.DigestAlgorithm;
import eu.europa.esig.dss.FileDocument;
import eu.europa.esig.dss.SignatureAlgorithm;
import eu.europa.esig.dss.SignatureLevel;
import eu.europa.esig.dss.SignaturePackaging;
import eu.europa.esig.dss.SignatureValue;
import eu.europa.esig.dss.ToBeSigned;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.validation.SignedDocumentValidator;
import eu.europa.esig.dss.validation.reports.DetailedReport;
import eu.europa.esig.dss.validation.reports.Reports;
import eu.europa.esig.dss.validation.reports.SimpleReport;
import eu.europa.esig.dss.x509.CertificateSource;
import eu.europa.esig.dss.x509.CertificateToken;
import eu.europa.esig.dss.x509.CommonTrustedCertificateSource;
import eu.europa.esig.dss.xades.XAdESSignatureParameters;
import eu.europa.esig.dss.xades.signature.XAdESService;
import fi.laverca.CmsSignature;
import fi.laverca.MSS_Formats;
import fi.laverca.ProgressUpdate;
import fi.laverca.SignatureProfiles;
import fi.laverca.etsi.EtsiClient;
import fi.laverca.etsi.EtsiRequest;
import fi.laverca.etsi.EtsiResponse;
import fi.laverca.etsi.EtsiResponseHandler;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.mss.MssConf;
import fi.laverca.mss.ProfileQueryResponse;
import fi.laverca.util.DTBS;
import fi.laverca.util.X509CertificateChain;

/**
 * XAdES signing example
 * 
 * <p>Performs the following steps
 * <ol>
 * <li>Gets the Signing Certificate either with a ProfileQuery, or a dummy SignatureRequest
 * <li>Constructs {@link XAdESSignatureParameters} 
 * <li>Sends the actual SignatureRequest with {@link DTBS} constructed from the {@link ToBeSigned} digest
 * </ol>
 *
 */
public class XAdES {
    
    // Signature and digest algorithms that this example uses. 
    // Note that these must match with what the MSS SignatureProfile uses.
    private static final DigestAlgorithm    DIGEST_ALG = DigestAlgorithm.SHA1;
    private static final SignatureAlgorithm SIG_ALG    = SignatureAlgorithm.RSA_SHA1;
    
    // MSS SignatureProfile
    private static final String MSS_SIG_PROF = SignatureProfiles.ALAUDA_AUTHENTICATION;
            
    public static void main(final String[] args) {

        String msisdn     = "+35847001001";
        String fileToSign = null;
        if (args.length == 1) {
            fileToSign = args[0];
        } else if (args.length > 1) {
            msisdn     = args[0];
            fileToSign = args[1];
        } else {
            System.err.println("Usage: [msisdn] filename");
            return;
        }

        final DSSDocument doc = new FileDocument(fileToSign);

        // Init XAdES service, parameters, and handler
        final XAdESService             service    = new XAdESService(new CommonCertificateVerifier());
        final XAdESSignatureParameters parameters = new XAdESSignatureParameters();
        parameters.setSignatureLevel(SignatureLevel.XAdES_BASELINE_B);
        parameters.setSignaturePackaging(SignaturePackaging.ENVELOPING);
        parameters.setDigestAlgorithm(DIGEST_ALG);
        
        final XAdESResponseHandler handler = new XAdESResponseHandler(parameters, service, SIG_ALG, doc);
        
        // Load config
        MssConf conf = MssConf.fromPropertyFile("conf/examples.conf");
        
        // Create client. This will also create a SSL Socket Factory for the client. 
        EtsiClient client = new EtsiClient(conf);

        
        // Create AP_TransID to use. This can be any unique String. 
        String apTransId = "A" + System.currentTimeMillis();
      
        // 1.  Get Signing Certificate.
        // This could be for example done on service login.
        // Note: This does not work if the authentication and signature use different key/cert (e.g. FiCom)
        X509CertificateChain certChain = null;
        try {
            certChain = getCertChain(client, msisdn, apTransId, handler);
        } catch (IOException ioe) {
            System.out.println("Failed to get certs from MSSP.");
            ioe.printStackTrace();
            return;
        }
        
        // 2. Fill certs to XAdESSignatureParameters
        final X509Certificate signingCert = certChain.getSigningCert();        
        
        final List<CertificateToken> certTokenChain = new ArrayList<>();
        for (final X509Certificate c : certChain) {
            certTokenChain.add(new CertificateToken(c));
        }
        
        parameters.setCertificateChain(certTokenChain);
        parameters.setSigningCertificate(new CertificateToken(signingCert));
        
        // 3. Send the actual XAdES signature request
        try {            
            final ToBeSigned dataToSign   = service.getDataToSign(doc, parameters);
            final byte[] dataToSignBytes  = dataToSign.getBytes();
            final byte[] dataToSignDigest = digest(dataToSignBytes);
            
            DTBS dtbs = new DTBS(dataToSignDigest, DTBS.ENCODING_BASE64, DTBS.MIME_STREAM);
            
            // Data to be displayed
            String dtbd = dtbs.toString();
            
            EtsiRequest req = client.createRequest(
                                       apTransId,         // AP Transaction ID
                                       msisdn,            // MSISDN
                                       dtbs,              // Data to be signed
                                       dtbd,              // Data to be displayed
                                       null,              // Additional services
                                       MSS_SIG_PROF,      // Signature profile
                                       MSS_Formats.KIURU_PKCS1, // MSS Format
                                       MessagingModeType.ASYNCH_CLIENT_SERVER);
    
            // Send second SigReq
            sendEtsiReq(client, req, handler);

        } catch (NoSuchAlgorithmException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            System.out.println("Got an Exception:");
            e.printStackTrace();
        }
        
        // Kill the thread pool - otherwise this example would wait 60 seconds for the thread to die
        client.shutdown();
    }
    
    
    private static X509CertificateChain getCertChain(EtsiClient client, String msisdn, String apTransId, XAdESResponseHandler handler) 
            throws IOException {
        
        // Send a ProfileQueryReq. Depending on the MSSP config, the response may contain the needed certs. 
        ProfileQueryResponse profileResp = client.sendProfileQuery(msisdn, apTransId);
        X509CertificateChain certChain = profileResp.getCertificates();
        
        // If ProfileQueryResponse does not contain certs, get the certs from a Signature. 
        if (certChain.isEmpty()) {
            certChain = getCertsWithSign(client, msisdn, apTransId, handler);
        }
        
        return certChain;
    }
    
    private static X509CertificateChain getCertsWithSign(EtsiClient client, String msisdn, String apTransId, XAdESResponseHandler handler) {
        DTBS   dtbs = new DTBS("XAdES dummy signature to pick certificates.");
        String dtbd = dtbs.toString();
      
        EtsiRequest req = client.createRequest(apTransId,         // AP Transaction ID
                                               msisdn,            // MSISDN
                                               dtbs,              // Data to be signed
                                               dtbd,              // Data to be displayed
                                               null,              // Additional services
                                               MSS_SIG_PROF,      // Signature profile
                                               MSS_Formats.PKCS7, // MSS Format
                                               MessagingModeType.ASYNCH_CLIENT_SERVER);
        
        EtsiResponse resp = sendEtsiReq(client, req, handler);
        if (resp != null) {
            X509CertificateChain certChain = new  X509CertificateChain();
            for (X509Certificate cert : ((CmsSignature)resp.getSignature()).getCertificates()) {
                certChain.add(cert);
            }
            return certChain;            
        } 
        return new X509CertificateChain();
        
    }
    
    private static EtsiResponse sendEtsiReq(EtsiClient client, EtsiRequest req, XAdESResponseHandler handler) {
        try {
            client.call(req, handler);
            EtsiResponse resp = req.waitForResponse();
            return resp;
        } catch (AxisFault af) {
            System.out.println("Got a SOAP fault:");
            af.printStackTrace();
        } catch (Exception e) {
            System.out.println("Got an Exception:");
            e.printStackTrace();
        }
        return null;
    }
    
    private static byte[] digest(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(DIGEST_ALG.getJavaName());
        md.reset();
        md.update(data);
        return md.digest();
    }

    protected static class XAdESResponseHandler implements EtsiResponseHandler {
    
        private final XAdESSignatureParameters parameters;
        private final XAdESService service;
        private final SignatureAlgorithm sigAlg;
        private final DSSDocument doc;
        
        private DSSDocument signedDocument;


        /**
         * Create an asynchronous response handler that handles both signature responses (#1 and #2)
         * 
         * @param parameters XAdES Signature Parameters - only used for XAdES signing
         * @param service    XAdES Service              - only used for XAdES signing
         * @param doc        XAdES Document             - only used for XAdES signing
         */
        public XAdESResponseHandler(final XAdESSignatureParameters parameters,
                                    final XAdESService             service,
                                    final SignatureAlgorithm       sigAlg,
                                    final DSSDocument              doc) {
            this.parameters = parameters;
            this.service    = service;
            this.sigAlg     = sigAlg;
            this.doc        = doc;
        }

        @Override
        public void onResponse(final EtsiRequest req, final EtsiResponse resp) {
            System.out.println("Got a response");
            System.out.println("  StatusCode   : " + resp.getStatusCode());
            System.out.println("  StatusMessage: " + resp.getStatusMessage());
            System.out.println("  Signature    : " + resp.getSignature().getBase64Signature());
            
            // If we have the Signing cert, do XAdES sign
            if (this.parameters.getSigningCertificate() != null) {
                
                try {
                    // Sign
                    this.signedDocument = this.xadesSign(resp.getSignature().getRawSignature());
                    
                    // Print
                    try (InputStream in = this.signedDocument.openStream()) {
                        PrintStream out = System.out;
    
                        int size = 0;
                        byte[] buffer = new byte[1024];
                        while ((size = in.read(buffer)) != -1) {
                            out.write(buffer, 0, size);
                        }
                        out.flush();
                        out.println();
                    }
                } catch (Throwable e) {
                    System.out.println("XAdES sign failed:");
                    e.printStackTrace();
                }
                
                // Validate
                boolean isValid = this.xadesValidate(doc, this.signedDocument);
                System.out.println("XAdES signature is " + (isValid ? "valid" : "invalid"));
            }
        }

        @Override
        public void onError(final EtsiRequest req, final Throwable t) {
            System.out.println("Got an error:");
            t.printStackTrace();
        }

        @Override
        public void onOutstandingProgress(final EtsiRequest req, final ProgressUpdate update) {
            System.out.println("Got a progress update");
        }
        
        /**
         * Run XAdES sign on the given document.
         * Prints the output to stdout.
         * 
         * @param signature  SignatureValue as byte[]
         */
        private DSSDocument xadesSign(final byte[] signature) {
            final SignatureValue signatureValue = new SignatureValue(this.sigAlg, signature);
            return this.service.signDocument(this.doc, this.parameters, signatureValue);
        }

        /**
         * Validate the XAdES signature
         * 
         * @param originalDoc Original document
         * @param signedDoc   Signature document
         * @return true if the signature is valid
         */
        private boolean xadesValidate(final DSSDocument originalDoc, final DSSDocument signedDoc) {
     
            SignedDocumentValidator validator = SignedDocumentValidator.fromDocument(signedDoc);
     
            validator.setCertificateVerifier(this.createVerifier());
            
            Reports reports = validator.validateDocument();
            
            SimpleReport simpleReport = reports.getSimpleReport();
            String  id = simpleReport.getFirstSignatureId();
            System.out.println("Validation:");
            System.out.println("  Info    : " + simpleReport.getInfo(id));
            System.out.println("  Errors  : " + simpleReport.getErrors(id));
            System.out.println("  Warnings: " + simpleReport.getWarnings(id));
            
            if (!simpleReport.isSignatureValid(id)) {
                this.printDetailedReport(reports.getDetailedReport());                
            }
            
            return simpleReport.isSignatureValid(id);
        }
        
        /**
         * Create a verifier with a trusted cert source. 
         * @return
         */
        private CommonCertificateVerifier createVerifier() {
            CommonCertificateVerifier verifier = new CommonCertificateVerifier();
            CertificateSource trusted = new CommonTrustedCertificateSource();
            trusted.addCertificate(this.parameters.getCertificateChain().get(0));
            verifier.setTrustedCertSource(trusted);
            return verifier;
        }
        
        private void printDetailedReport(DetailedReport details) {
            try {
                System.out.println("\nDetailed report:");
                JAXBContext context = JAXBContext.newInstance(details.getJAXBModel().getClass());
                final Marshaller marshaller = context.createMarshaller();
                final StringWriter stringWriter = new StringWriter();
               // Marshal the javaObject and write the XML to the stringWriter
                marshaller.marshal(details.getJAXBModel(), stringWriter);
         
                // Print out the contents of the stringWriter
                System.out.println(stringWriter.toString() + "\n");
            } catch (JAXBException e) {
                System.err.println("Failed to print detailed report. ");
                e.printStackTrace();
            }
        }
        
    }

}
