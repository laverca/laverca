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

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import eu.europa.esig.dss.asic.cades.ASiCWithCAdESSignatureParameters;
import eu.europa.esig.dss.asic.cades.signature.ASiCWithCAdESService;
import eu.europa.esig.dss.enumerations.ASiCContainerType;
import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.SignaturePackaging;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.FileDocument;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.model.x509.CertificateToken;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
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
 * ASiC signing example
 * 
 * <p>Performs the following steps
 * <ol>
 * <li>Gets the Signing Certificate either with a ProfileQuery, or a dummy SignatureRequest
 * <li>Constructs {@link DTBS} from the input documents
 * <li>Sends a SignatureRequest to the MSSP
 * <li>Constructs an ASiC file
 * </ol>
 *
 */
public class ASiC {
    
    private static final DigestAlgorithm    DIGEST_ALG    = DigestAlgorithm.SHA256;
    private static final SignatureAlgorithm SIG_ALG       = SignatureAlgorithm.RSA_SHA256;
    private static final String             DTBS_MIMETYPE = DTBS.MIME_SHA256;
    private static final String             RESULT_FILE   = "signed.asics";
    
    // MSS SignatureProfile
    private static final String MSS_SIG_PROF = SignatureProfiles.ALAUDA_SIGNING;
    
    private String msisdn;
    private List<String> filesToSign;
    private List<DSSDocument>   docs;
    
    // ASiC service
    private ASiCWithCAdESService             service;
    private ASiCWithCAdESSignatureParameters parameters;
    
    // MSS
    private MssConf    conf;
    private EtsiClient client;
    
    public static void main(final String[] args) {
        new ASiC(args).run();
    }
    
    /**
     * Create an ASiC example instance.
     * @param args command arguments to parse
     */
    public ASiC(final String[] args) {

        if (args.length < 2) {
            System.err.println("Usage: msisdn filename1 filename2 ...");
            return;
        }
        // Read args
        this.msisdn      = args[0];
        this.filesToSign = Arrays.stream(args).skip(1).collect(Collectors.toList());
        this.docs        = this.filesToSign.stream().map(FileDocument::new).collect(Collectors.toList());
        
        // Init ASiC service, parameters, and handler
        this.service    = new ASiCWithCAdESService(new CommonCertificateVerifier());
        this.parameters = new ASiCWithCAdESSignatureParameters();
        this.parameters.setSignatureLevel(SignatureLevel.CAdES_BASELINE_B);
        this.parameters.setSignaturePackaging(SignaturePackaging.ENVELOPING);
        this.parameters.setDigestAlgorithm(DIGEST_ALG);
        this.parameters.aSiC().setContainerType(ASiCContainerType.ASiC_S);
        this.parameters.aSiC().setZipComment(true);
        this.parameters.bLevel().setSigningDate(new Date());
        
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
        
        // 3. Send the ASiC signature request
        try {            
            final ToBeSigned dataToSign   = this.service.getDataToSign(this.docs, this.parameters);
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
                // 5. Attach signature to ASiC package
                SignatureValue signatureValue = new SignatureValue(SIG_ALG, resp.getSignature().getRawSignature());
                signedDocument = this.service.signDocument(this.docs, this.parameters, signatureValue);
            } catch (Throwable e) {
                System.out.println("ASiC sign failed:");
                e.printStackTrace();
            }
            
            try {
                // 6. Save signed file
                if (signedDocument != null) {
                    signedDocument.save(RESULT_FILE);
                    System.out.println("Saved signed document to " + new File(RESULT_FILE).getAbsolutePath());
                }

            } catch (IOException e) {
                System.out.println("Failed to save signed document:");
                e.printStackTrace();
            }

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
                                                    _msisdn,           // MSISDN
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

}
