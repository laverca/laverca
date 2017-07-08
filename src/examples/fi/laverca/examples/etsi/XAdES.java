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

import java.io.InputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLSocketFactory;

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
import eu.europa.esig.dss.validation.reports.SimpleReport;
import eu.europa.esig.dss.x509.CertificateToken;
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
import fi.laverca.examples.ExampleConf;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.mss.MssClient;
import fi.laverca.util.DTBS;

/**
 * XAdES signing example
 * 
 * <ul>
 * <li>Uses standard EtsiClient
 * <li>First sends a SignatureRequest with DTBS "XAdES Example Login"
 * <li>Then sends a second SignatureRequest to sign the document
 * <li>Uses asynch-client-server messaging mode
 * <li>No AdditionalServices
 * </ul>
 *
 */
public class XAdES {
    
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
            this.service = service;
            this.sigAlg = sigAlg;
            this.doc = doc;
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
                    this.signedDocument = xadesSign(resp.getSignature().getRawSignature());
                    
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
                boolean isValid = xadesValidate(doc, this.signedDocument);
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
        private static boolean xadesValidate(final DSSDocument originalDoc, final DSSDocument signedDoc) {
     
            SignedDocumentValidator validator = SignedDocumentValidator.fromDocument(signedDoc);
     
            CommonCertificateVerifier verifier = new CommonCertificateVerifier();
            validator.setCertificateVerifier(verifier);
     
            SimpleReport simpleReport = validator.validateDocument().getSimpleReport();
            String  id = simpleReport.getFirstSignatureId();
            System.out.println("Validation:");
            System.out.println("  Info    : " + simpleReport.getInfo(id));
            System.out.println("  Errors  : " + simpleReport.getErrors(id));
            System.out.println("  Warnings: " + simpleReport.getWarnings(id));
            
            return simpleReport.isSignatureValid(id);
        }
        
    }
        
    /**
     * The main method
     */
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

        final MessageDigest md;

        final String mssSigProf = SignatureProfiles.ALAUDA_SIGNATURE;
        
        // Setting signature algorithm bits.
        // Note that these must match with what the MSS SignatureProfile uses.
        // (It would be folly to claim RSA-SHA256 and sign ECDSA-SHA512..)
        
        final DigestAlgorithm digestAlg = DigestAlgorithm.SHA256;
        final SignatureAlgorithm sigAlg = SignatureAlgorithm.RSA_SHA256;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            System.err.println("Failed to instantiate MessageDigest " + digestAlg.getName());
            return;
        }

        final DSSDocument      doc = new FileDocument(fileToSign);

        final XAdESService service = new XAdESService(new CommonCertificateVerifier());
        
        final XAdESSignatureParameters parameters = new XAdESSignatureParameters();
        parameters.setSignatureLevel(SignatureLevel.XAdES_BASELINE_B);
        parameters.setSignaturePackaging(SignaturePackaging.ENVELOPING);
        parameters.setDigestAlgorithm(digestAlg);
        final XAdESResponseHandler handler = new XAdESResponseHandler(parameters, service, sigAlg, doc);
        
        // Load config
        ExampleConf conf = ExampleConf.getInstance();
        
        // Setup SSL
        SSLSocketFactory ssf = null;
        try {
            System.out.println("Setting up ssl");
            ssf = MssClient.createSSLFactory(conf.getKeystore(),
                                             conf.getKeystorePwd(),
                                             conf.getKeystoreType(),
                                             conf.getTruststore(),
                                             conf.getTruststorePwd(),
                                             conf.getTruststoreType());
        } catch (Exception e) {
            System.out.println("TLS initialization problem: " + e.getMessage());
            return;
        }

        // Create client
        EtsiClient client = new EtsiClient(conf.getApId(), 
                                           conf.getApPwd(), 
                                           conf.getSignatureUrl(), 
                                           conf.getStatusUrl(), 
                                           conf.getReceiptUrl());

        client.setSSLSocketFactory(ssf);

        
        // 1.  Get Signing Certificate with a simple Signature request.
        // This could be for example done on service login.
        // Note: This does not work if the authentication and signature use different key/cert (e.g. FiCom)
        
        DTBS        dtbs = new DTBS("XAdES dummy signature to pick certificates.");
        String      dtbd = dtbs.toString();
        String apTransId = "A" + System.currentTimeMillis();
      
        EtsiRequest req = client.createRequest(apTransId,         // AP Transaction ID
                                               msisdn,            // MSISDN
                                               dtbs,              // Data to be signed
                                               dtbd,              // Data to be displayed
                                               null,              // Additional services
                                               mssSigProf,        // Signature profile
                                               MSS_Formats.PKCS7, // MSS Format
                                               MessagingModeType.ASYNCH_CLIENT_SERVER);
        
        try {
            // Send first SigReq
            client.call(req, handler);

            // Get the signing cert from the response
            EtsiResponse resp = req.waitForResponse();
            final X509Certificate signingCert = resp.getSignature().getSignerCert();
            
            // 2. Send the actual XAdES signature request

            // Fill certs to XAdESSignatureParameters
            final CertificateToken      signerCert = new CertificateToken(signingCert);
            final List<CertificateToken> certChain = new ArrayList<>();
            
            for (final X509Certificate c : ((CmsSignature)resp.getSignature()).getCertificates()) {
                certChain.add(new CertificateToken(c));
            }
            
            parameters.setCertificateChain(certChain);
            parameters.setSigningCertificate(signerCert);
            
            final ToBeSigned dataToSign = service.getDataToSign(doc, parameters);
            final byte[] dataToSignBytes = dataToSign.getBytes();
            md.reset();
            md.update(dataToSignBytes);
            final byte[] dataToSignDigest = md.digest();
            
            dtbs      = new DTBS(dataToSignDigest, DTBS.ENCODING_BASE64, DTBS.MIME_STREAM);
            apTransId = "A" + System.currentTimeMillis();

            // Data to be displayed
            dtbd = dtbs.toString();
            
            req = client.createRequest(apTransId,         // AP Transaction ID
                                       msisdn,            // MSISDN
                                       dtbs,              // Data to be signed
                                       dtbd,              // Data to be displayed
                                       null,              // Additional services
                                       mssSigProf,        // Signature profile
                                       MSS_Formats.PKCS7, // MSS Format
                                       MessagingModeType.ASYNCH_CLIENT_SERVER);
    
            
            // Send second SigReq
            client.call(req, handler);

        } catch (AxisFault af) {
            System.out.println("Got a SOAP fault:");
            af.printStackTrace();
        } catch (Exception ioe) {
            System.out.println("Got an Exception:");
            ioe.printStackTrace();
        }
        
        // Kill the thread pool - otherwise this example would wait 60 seconds for the thread to die
        client.shutdown();
    }

}
