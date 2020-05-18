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
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import eu.europa.esig.dss.detailedreport.DetailedReport;
import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.SignaturePackaging;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.FileDocument;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.model.x509.CertificateToken;
import eu.europa.esig.dss.simplereport.SimpleReport;
import eu.europa.esig.dss.spi.x509.CertificateSource;
import eu.europa.esig.dss.spi.x509.CommonTrustedCertificateSource;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.validation.SignedDocumentValidator;
import eu.europa.esig.dss.validation.reports.Reports;
import eu.europa.esig.dss.xades.XAdESSignatureParameters;
import eu.europa.esig.dss.xades.signature.XAdESService;
import fi.laverca.CmsSignature;
import fi.laverca.MSS_Formats;
import fi.laverca.SignatureProfiles;
import fi.laverca.etsi.EtsiClient;
import fi.laverca.etsi.EtsiRequest;
import fi.laverca.etsi.EtsiResponse;
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
 * <li>Constructs {@link DTBS} from the input documents
 * <li>Sends a SignatureRequest to the MSSP
 * <li>Constructs a signed XML object
 * <li>Prints the XML
 * </ol>
 *
 */
public class XAdES {
    
    private static final DigestAlgorithm    DIGEST_ALG    = DigestAlgorithm.SHA256;
    private static final SignatureAlgorithm SIG_ALG       = SignatureAlgorithm.RSA_SHA256;
    private static final String             DTBS_MIMETYPE = DTBS.MIME_SHA256;
    
    // MSS SignatureProfile
    private static final String MSS_SIG_PROF = SignatureProfiles.ALAUDA_SIGNING;
           
    private String msisdn;
    
    private String fileToSign;
    private DSSDocument doc;
            
    private XAdESService             service;
    private XAdESSignatureParameters parameters;
    
    private MssConf    conf;
    private EtsiClient client;
    
    public static void main(final String[] args) {
        new XAdES(args).run();
        
    }
    
    public XAdES(final String[] args) {
        
        this.msisdn = "+35847001001";
        if (args.length == 1) {
            this.fileToSign = args[0];
        } else if (args.length > 1) {
            this.msisdn     = args[0];
            this.fileToSign = args[1];
        } else {
            System.err.println("Usage: [msisdn] filename");
            return;
        }

        this.doc        = new FileDocument(this.fileToSign);
        
        this.service    = new XAdESService(new CommonCertificateVerifier());
        this.parameters = new XAdESSignatureParameters();
        this.parameters.setSignatureLevel(SignatureLevel.XAdES_BASELINE_B);
        this.parameters.setSignaturePackaging(SignaturePackaging.ENVELOPING);
        this.parameters.setDigestAlgorithm(DIGEST_ALG);
        
        this.conf   = MssConf.fromPropertyFile("conf/examples.conf");
        this.client = new EtsiClient(this.conf);
    } 
    
    /**
     * Run the example
     */
    public void run() {
        
        // Create AP_TransID to use. This can be any unique String. 
        String apTransId = "A" + System.currentTimeMillis();
      
        // 1. Get Signing Certificate
        X509CertificateChain chain = null;
        try {
            chain = this.getCertChain(this.msisdn, apTransId);
        } catch (IOException ioe) {
            System.out.println("Failed to get certs from MSSP.");
            ioe.printStackTrace();
            this.client.shutdown();
            return;
        }
        
        // 2. Fill certs to SignatureParameters
        final X509Certificate       signingCert = chain.getSigningCert();        
        final List<CertificateToken> tokenChain = chain.stream()
                                                       .map(CertificateToken::new)
                                                       .collect(Collectors.toList());

        this.parameters.setCertificateChain(tokenChain);
        this.parameters.setSigningCertificate(new CertificateToken(signingCert));
        
        
        // 3. Send the actual XAdES signature request
        try {            
            final ToBeSigned dataToSign   = this.service.getDataToSign(this.doc, this.parameters);
            final byte[] dataToSignBytes  = dataToSign.getBytes();
            final byte[] dataToSignDigest = digest(dataToSignBytes);
            
            DTBS dtbs = new DTBS(dataToSignDigest, DTBS.ENCODING_BASE64, DTBS_MIMETYPE);
            
            // Data to be displayed
            String dtbd = dtbs.toString();
            
            EtsiRequest req = this.client.createRequest(apTransId,               // AP Transaction ID
                                                        this.msisdn,             // MSISDN
                                                        dtbs,                    // Data to be signed
                                                        dtbd,                    // Data to be displayed
                                                        null,                    // Additional services
                                                        MSS_SIG_PROF,            // Signature profile
                                                        MSS_Formats.KIURU_PKCS1, // MSS Format
                                                        MessagingModeType.ASYNCH_CLIENT_SERVER);
    
            // 4. Send Signature request
            EtsiResponse resp = this.client.send(req);

            System.out.println("Got a response");
            System.out.println("  StatusCode   : " + resp.getStatusCode());
            System.out.println("  StatusMessage: " + resp.getStatusMessage());
            System.out.println("  Signature    : " + resp.getSignature().getBase64Signature());
            
            DSSDocument signedDocument = null;
            try {
                // 5. Attach signature to PDF
                SignatureValue signatureValue = new SignatureValue(SIG_ALG, resp.getSignature().getRawSignature());
                signedDocument = this.service.signDocument(this.doc, this.parameters, signatureValue);
                
                // Print
                try (InputStream in = signedDocument.openStream()) {
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
            boolean isValid = this.validate(this.doc, signedDocument);
            System.out.println("XAdES signature is " + (isValid ? "valid" : "invalid"));

        } catch (NoSuchAlgorithmException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            System.out.println("Got an Exception:");
            e.printStackTrace();
        }
        
        // Kill the thread pool - otherwise this example would wait 60 seconds for the thread to die
        this.client.shutdown();
    }

    /**
     * Get the certificate chain.
     * <p>First tries to get the chain with ProfileQuery extension.
     * If unsuccessful, results to sending a dummy signature request to the MSSP to get the chain.
     * 
     * @param _msisdn    Target MSISDN
     * @param apTransId AP transaction ID
     * @return Certificate chain (may be empty in which case the signing will fail)
     * @throws IOException
     */
    private X509CertificateChain getCertChain(final String _msisdn, final String apTransId) 
        throws IOException 
    {
        
        // Send a ProfileQueryReq. Depending on the MSSP configuration, the response may contain the needed certs. 
        ProfileQueryResponse profileResp = this.client.sendProfileQuery(_msisdn, apTransId);
        X509CertificateChain certChain   = profileResp.getCertificate(MSS_SIG_PROF);
        
        // If ProfileQueryResponse does not contain certs, get the certs from a Signature. 
        if (certChain.isEmpty()) {
            certChain = getCertsWithSign(_msisdn, apTransId);
        }
        
        return certChain;
    }
    
    /**
     * Get the certificate chain by sending a dummy signature request to the MSSP for the user to sign.
     * 
     * @param _msisdn    Target MSISDN
     * @param apTransId AP transaction ID
     * @return Certificate chain (may be empty in which case the signing will fail)
     * @throws IOException
     */
    private X509CertificateChain getCertsWithSign(final String _msisdn, final String apTransId) 
        throws IOException
    {
        DTBS   dtbs = new DTBS("CAdES dummy signature to pick certificates.");
        String dtbd = dtbs.toString();
      
        EtsiRequest req = this.client.createRequest(apTransId,         // AP Transaction ID
                                                    this.msisdn,       // MSISDN
                                                    dtbs,              // Data to be signed
                                                    dtbd,              // Data to be displayed
                                                    null,              // Additional services
                                                    MSS_SIG_PROF,      // Signature profile
                                                    MSS_Formats.PKCS7, // MSS Format
                                                    MessagingModeType.ASYNCH_CLIENT_SERVER);
        
        EtsiResponse resp = this.client.send(req);
        return ((CmsSignature)resp.getSignature()).getCertificateChain();
    }
    
    /**
     * Digest the given data with {@code DIGEST_ALG}
     * 
     * @param data Data to digest
     * @return digest
     * @throws NoSuchAlgorithmException
     */
    private byte[] digest(final byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(DIGEST_ALG.getJavaName());
        md.reset();
        md.update(data);
        return md.digest();
    }
    

    /**
     * Validate the XAdES signature
     * 
     * @param originalDoc Original document
     * @param signedDoc   Signature document
     * @return true if the signature is valid
     */
    private boolean validate(final DSSDocument originalDoc, final DSSDocument signedDoc) {
 
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
            this.printReport(reports.getDetailedReport());                
        }
        
        return simpleReport.isSignatureValid(id);
    }
    
    /**
     * Create a verifier with a trusted cert source. 
     * @return Certificate verifier
     */
    private CommonCertificateVerifier createVerifier() {
        CommonCertificateVerifier verifier = new CommonCertificateVerifier();
        CertificateSource trusted = new CommonTrustedCertificateSource();
        trusted.addCertificate(this.parameters.getCertificateChain().get(0));
        verifier.setTrustedCertSource(trusted);
        return verifier;
    }
    
    /**
     * Print a detailed validation report
     * @param details validation detail report
     */
    private void printReport(final DetailedReport details) {
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
