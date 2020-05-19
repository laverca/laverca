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
import java.util.List;
import java.util.stream.Collectors;

import org.bouncycastle.cms.CMSSignedData;

import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.SignaturePackaging;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.FileDocument;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.model.x509.CertificateToken;
import eu.europa.esig.dss.pades.PAdESSignatureParameters;
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
 * PAdES signing example
 * 
 * <p>Performs the following steps
 * <ol>
 * <li>Gets the Signing Certificate either with a ProfileQuery, or a dummy SignatureRequest
 * <li>Constructs {@link DTBS} from the input documents
 * <li>Sends a SignatureRequest to the MSSP
 * <li>Constructs a signed PDF file
 * <li>Stores the signed PDF with "signed" appended to its name (e.g. foo.pdf -> foo.signed.pdf)
 * </ol>
 */
public class PAdES {

    private static final DigestAlgorithm    DIGEST_ALG    = DigestAlgorithm.SHA256;
    private static final SignatureAlgorithm SIG_ALG       = SignatureAlgorithm.RSA_SHA256;
    private static final String             DTBS_MIMETYPE = DTBS.MIME_SHA256;
    
    // MSS SignatureProfile
    private static final String MSS_SIG_PROF = SignatureProfiles.ALAUDA_SIGNING;
    
    private String msisdn;
    
    private String fileToSign;
    private String signedFile;
    private DSSDocument doc;
    private MessagingModeType msgMode = MessagingModeType.ASYNCH_CLIENT_SERVER;
            
    private MssConf    conf;
    private EtsiClient client;

    // Select the operation mode.
    // - true: Makes a CMS external hash signature, and merges the CMS signature
    //         Does require that MSSP making the signature does produce a PAdES_BASELINE_B
    //         (CAdES-BES) profile conformant SignedAttributes collection.
    // - false: Does query the remote MSSP server for end-user certificates, prepares
    //          the signature material, and then digests it for final signing device
    //          processing ("PKCS1" signature).
    // 

    private static boolean useCMSmode = false;

    
    public static void main(final String[] args) {
        new PAdES(args).run();
    }
    
    /**
     * Create a PAdES example instance.
     * @param args command arguments to parse
     */
    public PAdES(final String[] args) {

        this.msisdn = "+35847001001";
        
        for (String arg : args) {
            if (arg.equals("-cms")) {
                useCMSmode = true;
                continue;
            }
            if (arg.equals("-sync")) {
                this.msgMode = MessagingModeType.SYNCH;
                continue;
            }
            // MSISDN pickup
            char c = arg.length() > 0 ? arg.charAt(0) : 0;
            if (c == '+') {
                c = arg.length() > 1 ? arg.charAt(1) : 0;
                if (Character.isDigit(c)) {
                    this.msisdn = arg;
                    continue;
                }
            }
            if (Character.isDigit(c)) {
                this.msisdn = arg;
                continue;
            }
            
            final File f = new File(arg);
            if (f.exists() && f.canRead()) {
                this.fileToSign = arg;
                continue;
            }
            System.err.println("Usage: [-cms] [msisdn] filename");
            System.exit(64);
        }
        if (this.fileToSign == null) {
            System.err.println("Usage: [-cms] [msisdn] filename");
            System.exit(64);
        }
        
        // Check at least that the file extension is PDF
        if (!this.fileToSign.endsWith(".pdf")) {
            System.err.println("Supplied file is not a PDF");
            return;
        }
        this.signedFile = this.fileToSign.replace(".pdf", ".signed.pdf");
        this.doc        = new FileDocument(this.fileToSign);
        
        this.conf   = MssConf.fromPropertyFile("conf/examples.conf");
        this.client = new EtsiClient(this.conf);
    }
    
    /**
     * Run the example
     */
    public void run() {
        
        // Create AP_TransID to use. This can be any String unique within this AP.
        String apTransId = "A" + System.currentTimeMillis();
      
        try {
            
            // 1. Prepare the PAdES signature request

            final LavercaPAdESService service    = new LavercaPAdESService(new CommonCertificateVerifier());
            
            final DigestAlgorithm digAlg = DIGEST_ALG;

            final PAdESSignatureParameters parameters = new PAdESSignatureParameters();
            parameters.setSignatureLevel(SignatureLevel.PAdES_BASELINE_B);
            parameters.setSignaturePackaging(SignaturePackaging.ENVELOPED);
            parameters.setDigestAlgorithm(digAlg);
            // this.parameters.setLocation(locationString);


            if (!useCMSmode) {
                // 2. Get Signing Certificate
                X509CertificateChain chain = null;
                try {
                    chain = this.getCertChain(this.msisdn, apTransId);
                } catch (IOException ioe) {
                    System.out.println("Failed to get certs from MSSP.");
                    ioe.printStackTrace();
                    this.client.shutdown();
                    return;
                }
    
                // 3. Fill certs to SignatureParameters
                final X509Certificate       signingCert = chain.getSigningCert();        
                final List<CertificateToken> tokenChain = chain.stream()
                                                               .map(CertificateToken::new)
                                                               .collect(Collectors.toList());
                
                parameters.setCertificateChain(tokenChain);
                parameters.setSigningCertificate(new CertificateToken(signingCert));
            }

            // 4. Prepare signature request
            
            DTBS dtbs;
            
            String mssFormat;
            
            if (useCMSmode) {
                final byte[] messageDigest = service.computeDocumentDigest(this.doc, parameters);
                dtbs = new DTBS(messageDigest, DTBS.ENCODING_BASE64, DTBS_MIMETYPE);
                mssFormat = MSS_Formats.CMS;
            } else {
                final ToBeSigned dataToSign   = service.getDataToSign(this.doc, parameters);
                final byte[] dataToSignBytes  = dataToSign.getBytes();
                final byte[] dataToSignDigest = digest(dataToSignBytes);
                
                dtbs = new DTBS(dataToSignDigest, DTBS.ENCODING_BASE64, DTBS_MIMETYPE);
                mssFormat = MSS_Formats.KIURU_PKCS1;
            }
            
            // Data to be displayed
            String dtbd = dtbs.toString();

            // 5. Send signature request
            
            EtsiRequest req = this.client.createRequest(apTransId,               // AP Transaction ID
                                                        this.msisdn,             // MSISDN
                                                        dtbs,                    // Data to be signed
                                                        dtbd,                    // Data to be displayed
                                                        null,                    // Additional services
                                                        MSS_SIG_PROF,            // Signature profile
                                                        mssFormat,               // MSS Format
                                                        this.msgMode);
    
            // 4. Send Signature request
            EtsiResponse resp = this.client.send(req);

            System.out.println("Got a response");
            System.out.println("  StatusCode   : " + resp.getStatusCode());
            System.out.println("  StatusMessage: " + resp.getStatusMessage());
            System.out.println("  Signature    : " + resp.getSignature().getBase64Signature());

            DSSDocument signedDocument = null;
            try {
                
                if (useCMSmode) {
                    // Pick CMS signature part, the WPKI signer produces CMS signature
                    final byte[] cmsSigBytes = resp.getSignature().getRawSignature();
                    final CMSSignedData cmsSignedData = new CMSSignedData(cmsSigBytes);

                    // 5. Attach signature to PDF, using customized processing method
                    signedDocument = service.signDocument(this.doc, parameters, cmsSignedData);

                    
                } else {
                    // Pick raw PKCS#1 signature
                    final byte[] sigBytes = resp.getSignature().getRawSignature();

                    // 5. Attach signature to PDF
                    final SignatureValue signatureValue = new SignatureValue(SIG_ALG, sigBytes);
                    signedDocument = service.signDocument(this.doc, parameters, signatureValue);
                }

            } catch (Throwable e) {
                System.out.println("PAdES sign failed:");
                e.printStackTrace();
            }
            
            try {
                // 6. Save signed file
                if (signedDocument != null) {
                    signedDocument.save(this.signedFile);
                    System.out.println("Saved signed document to " + new File(this.signedFile).getAbsolutePath());
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
     * If unsuccessful, results to sending a dummy signature requestto the MSSP to get the chain.
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
            certChain = this.getCertsWithSign(_msisdn, apTransId);
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
}
