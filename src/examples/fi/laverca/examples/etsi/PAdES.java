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
import java.util.ArrayList;
import java.util.List;

import eu.europa.esig.dss.DSSDocument;
import eu.europa.esig.dss.DigestAlgorithm;
import eu.europa.esig.dss.FileDocument;
import eu.europa.esig.dss.SignatureAlgorithm;
import eu.europa.esig.dss.SignatureLevel;
import eu.europa.esig.dss.SignaturePackaging;
import eu.europa.esig.dss.SignatureValue;
import eu.europa.esig.dss.ToBeSigned;
import eu.europa.esig.dss.pades.PAdESSignatureParameters;
import eu.europa.esig.dss.pades.signature.PAdESService;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.x509.CertificateToken;
import fi.laverca.MSS_Formats;
import fi.laverca.SignatureProfiles;
import fi.laverca.etsi.EtsiClient;
import fi.laverca.etsi.EtsiRequest;
import fi.laverca.etsi.EtsiResponse;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.mss.MssConf;
import fi.laverca.util.DTBS;
import fi.laverca.util.X509CertificateChain;

/**
 * PAdES signing example
 * 
 * <p>Performs the following steps
 * <ol>
 * <li>Gets the Signing Certificate with a ProfileQuery
 * <li>Constructs {@link PAdESSignatureParameters} 
 * <li>Sends the actual SignatureRequest with {@link DTBS} constructed from the {@link ToBeSigned} digest
 * <li>Stores the signed PDF with "signed" appended to its name (e.g. foo.pdf -> foo.signed.pdf)
 * </ol>
 */
public class PAdES {

    private static final DigestAlgorithm    DIGEST_ALG    = DigestAlgorithm.SHA256;
    private static final SignatureAlgorithm SIG_ALG       = SignatureAlgorithm.RSA_SHA256;
    private static final String             DTBS_MIMETYPE = DTBS.MIME_SHA256;
    
    // MSS SignatureProfile
    private static final String MSS_SIG_PROF = SignatureProfiles.ALAUDA_SIGNING;
    
    private static String fileToSign;
    private static String signedFile;
            
    public static void main(final String[] args) {

        String msisdn = "+35847001001";
        if (args.length == 1) {
            fileToSign = args[0];
        } else if (args.length > 1) {
            msisdn     = args[0];
            fileToSign = args[1];
        } else {
            System.err.println("Usage: [msisdn] filename");
            return;
        }
        
        // Check at least that the file extension is PDF
        if (!fileToSign.endsWith(".pdf")) {
            System.err.println("Supplied file is not a PDF");
            return;
        }
        signedFile = fileToSign.replace(".pdf", ".signed.pdf");
        
        final DSSDocument doc = new FileDocument(fileToSign);
        
        final PAdESService             service    = new PAdESService(new CommonCertificateVerifier());
        final PAdESSignatureParameters parameters = new PAdESSignatureParameters();
        parameters.setSignatureLevel(SignatureLevel.PAdES_BASELINE_B);
        parameters.setSignaturePackaging(SignaturePackaging.ENVELOPED);
        parameters.setDigestAlgorithm(DIGEST_ALG);
        
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
            certChain = client.sendProfileQuery(msisdn, apTransId).getCertificates();
            if (certChain == null) {
                System.out.println("Got no client certificates from MSSP");
                return;
            }
        } catch (IOException ioe) {
            System.out.println("Failed to get client certificates from MSSP");
            ioe.printStackTrace();
            return;
        }
        
        // 2. Fill certs to PAdESSignatureParameters
        final X509Certificate signingCert = certChain.getSigningCert();        
        
        final List<CertificateToken> certTokenChain = new ArrayList<>();
        for (final X509Certificate c : certChain) {
            certTokenChain.add(new CertificateToken(c));
        }
        
        parameters.setCertificateChain(certTokenChain);
        parameters.setSigningCertificate(new CertificateToken(signingCert));
        
        // 3. Send the actual PAdES signature request
        try {
            
            // Construct DTBS
            final ToBeSigned dataToSign   = service.getDataToSign(doc, parameters);
            final byte[] dataToSignBytes  = dataToSign.getBytes();
            
            // Get digest of dataToSignBytes
            MessageDigest digest = MessageDigest.getInstance(DIGEST_ALG.getJavaName());
            digest.reset();
            digest.update(dataToSignBytes);

            DTBS dtbs = new DTBS(digest.digest(), DTBS.ENCODING_BASE64, DTBS_MIMETYPE);
            
            // Data to be displayed
            String dtbd = dtbs.toString();
            
            EtsiRequest req = client.createRequest(apTransId,         // AP Transaction ID
                                                   msisdn,            // MSISDN
                                                   dtbs,              // Data to be signed
                                                   dtbd,              // Data to be displayed
                                                   null,              // Additional services
                                                   MSS_SIG_PROF,      // Signature profile
                                                   MSS_Formats.KIURU_PKCS1, // MSS Format
                                                   MessagingModeType.ASYNCH_CLIENT_SERVER);
    
            // Send SigReq
            EtsiResponse resp = client.send(req);

            System.out.println("Got a response");
            System.out.println("  StatusCode   : " + resp.getStatusCode());
            System.out.println("  StatusMessage: " + resp.getStatusMessage());
            System.out.println("  Signature    : " + resp.getSignature().getBase64Signature());
            
            DSSDocument signedDocument = null;
            
            try {
                // Sign
                SignatureValue signatureValue = new SignatureValue(SIG_ALG, resp.getSignature().getRawSignature());
                signedDocument = service.signDocument(doc, parameters, signatureValue);

            } catch (Throwable e) {
                System.out.println("PAdES sign failed:");
                e.printStackTrace();
            }
            
            try {
                // 4. Store signed file
                if (signedDocument != null) {
                    signedDocument.save(signedFile);
                    System.out.println("Saved signed document to " + new File(signedFile).getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("Failed to save signed document:");
                e.printStackTrace();
            }
            

        } catch (NoSuchAlgorithmException ae) {
            ae.printStackTrace();
        } catch (IOException ioe) {
            System.err.println("Signing failed");
            ioe.printStackTrace();
        }
        
        // Kill the thread pool - otherwise this example would wait 60 seconds for the thread to die
        client.shutdown();
    }
    
}
