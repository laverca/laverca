package fi.laverca.examples.etsi;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;

import eu.europa.esig.dss.cades.signature.CAdESLevelBaselineB;
import eu.europa.esig.dss.cades.signature.CAdESLevelBaselineT;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.DSSException;
import eu.europa.esig.dss.model.x509.CertificateToken;
import eu.europa.esig.dss.pades.PAdESSignatureParameters;
import eu.europa.esig.dss.pades.signature.PAdESService;
import eu.europa.esig.dss.pades.validation.PAdESSignature;
import eu.europa.esig.dss.pades.validation.PDFDocumentValidator;
import eu.europa.esig.dss.pdf.DSSDictionaryCallback;
import eu.europa.esig.dss.pdf.PDFSignatureService;
import eu.europa.esig.dss.pdf.PDFTimestampService;
import eu.europa.esig.dss.pdf.PdfObjFactory;
import eu.europa.esig.dss.signature.SignatureExtension;
import eu.europa.esig.dss.signature.SigningOperation;
import eu.europa.esig.dss.spi.DSSASN1Utils;
import eu.europa.esig.dss.spi.x509.tsp.TSPSource;
import eu.europa.esig.dss.utils.Utils;
import eu.europa.esig.dss.validation.AdvancedSignature;
import eu.europa.esig.dss.validation.CMSOCSPSource;
import eu.europa.esig.dss.validation.CertificateVerifier;
import eu.europa.esig.dss.validation.DefaultAdvancedSignature;
import eu.europa.esig.dss.validation.ValidationContext;
import eu.europa.esig.dss.validation.timestamp.TimestampToken;

/**
 * This is modification of the eSIG-DSS PAdESService receiving completed
 * CMS signature from external signer, and just inserts it to PDF.
 * 
 * The PAdESLevelBaselineXX classes are copied direct from original
 * because they are package local in there, and
 * the getExtensionProfile(SignatureLevel) method at PAdESService is private..
 */

public class LavercaPAdESService extends PAdESService {
    
    private static final long serialVersionUID = -7768731422942631381L;


    public LavercaPAdESService(CertificateVerifier certificateVerifier) {
        super(certificateVerifier);
    }
    
    
    // This method is protected in original implementation.
    @Override
    public byte[] computeDocumentDigest(final DSSDocument toSignDocument, final PAdESSignatureParameters parameters) {
        final PDFSignatureService pdfSignatureService = PdfObjFactory.newPAdESSignatureService();
        return pdfSignatureService.digest(toSignDocument, parameters, parameters.getDigestAlgorithm());
    }

    /**
     * Custom version of the signDocument() with a CMS signature value for a detached signature 
     *  
     * @param toSignDocument
     * @param parameters
     * @param cmsSignedData a CMS signature value
     * @return Signed document container
     * @throws CMSException
     */
    public DSSDocument signDocument(final DSSDocument toSignDocument,
                                    final PAdESSignatureParameters parameters,
                                    final CMSSignedData cmsSignedData)
        throws CMSException
    {
        // Fill certs to SignatureParameters
        try {
            final JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
            final Store<X509CertificateHolder> certStore = cmsSignedData.getCertificates();
            final SignerInformationStore         signers = cmsSignedData.getSignerInfos();
            for (final SignerInformation signer : signers.getSigners()) {
                @SuppressWarnings("unchecked")
                final Selector<X509CertificateHolder>              sid = signer.getSID();
                final Collection<X509CertificateHolder> certCollection = certStore.getMatches(sid);
                for (X509CertificateHolder certH : certCollection) {
                    try {
                        CertificateToken certT = new CertificateToken(certConverter.getCertificate(certH));
                        parameters.setSigningCertificate(certT);
                    } catch (Exception e) {
                        System.err.println("Exception: "+e.getMessage());
                        e.printStackTrace(System.err);
                    }
                }
            }
    
            // All certificates - match against "null"
            for (X509CertificateHolder certH : certStore.getMatches(null)) {
                CertificateToken certT = new CertificateToken(certConverter.getCertificate(certH));
                parameters.setCertificateChain(certT);
            }
        } catch (CertificateException e) {
            throw new CMSException(e.getMessage(), e);
        }

        // Proceed with signature building
        assertSigningDateInCertificateValidityRange(parameters);

        final SignatureLevel signatureLevel = parameters.getSignatureLevel();
        final byte[] encodedData = generateCMSSignedData(toSignDocument, parameters, cmsSignedData);
        
        final PDFSignatureService pdfSignatureService = PdfObjFactory.newPAdESSignatureService();
        DSSDocument signature = pdfSignatureService.sign(toSignDocument, encodedData, parameters, parameters.getDigestAlgorithm());

        
        // Note: Following code, and all depencies on it can be removed if the caller
        //       will always use PAdES_BASELINE_B ...
        if ((signatureLevel != SignatureLevel.PAdES_BASELINE_B) &&
            (signatureLevel != SignatureLevel.PAdES_BASELINE_T)) {
            final SignatureExtension<PAdESSignatureParameters> extension = getExtensionProfile(signatureLevel);
            if (extension != null) {
                signature = extension.extendSignatures(signature, parameters);
            }
        }
        
        parameters.reinitDeterministicId();
        signature.setName(getFinalFileName(toSignDocument, SigningOperation.SIGN, parameters.getSignatureLevel()));
        return signature;
    }


    protected byte[] generateCMSSignedData(final DSSDocument toSignDocument,
                                           final PAdESSignatureParameters parameters,
                                           final CMSSignedData cmsSignedData)
        throws CMSException
    {
        final SignatureLevel signatureLevel = parameters.getSignatureLevel();
        CMSSignedData data = cmsSignedData;

        if (signatureLevel != SignatureLevel.PAdES_BASELINE_B) {
            // use an embedded timestamp
            CAdESLevelBaselineT cadesLevelBaselineT = new CAdESLevelBaselineT(this.tspSource, false);
            data = cadesLevelBaselineT.extendCMSSignatures(data, parameters);
        }

        return DSSASN1Utils.getDEREncoded(data);
    }

    // Because this is private in original version, we need to copy this and package locals it refers to...
    private SignatureExtension<PAdESSignatureParameters> getExtensionProfile(SignatureLevel signatureLevel) {
        switch (signatureLevel) {
        case PAdES_BASELINE_B:
            return null;
        case PAdES_BASELINE_T:
            return new PAdESLevelBaselineT(super.tspSource);
        case PAdES_BASELINE_LT:
            return new PAdESLevelBaselineLT(super.tspSource, this.certificateVerifier);
        case PAdES_BASELINE_LTA:
            return new PAdESLevelBaselineLTA(super.tspSource, this.certificateVerifier);
        default:
            throw new IllegalArgumentException("Signature format '" + signatureLevel + "' not supported");
        }
    }
}



/**
 * PAdES Baseline T signature
 */
class PAdESLevelBaselineT implements SignatureExtension<PAdESSignatureParameters> {

    private final TSPSource tspSource;

    public PAdESLevelBaselineT(TSPSource tspSource) {
        this.tspSource = tspSource;
    }

    @Override
    public DSSDocument extendSignatures(final DSSDocument document, final PAdESSignatureParameters params) throws DSSException {
        // Will add a DocumentTimeStamp. signature-timestamp (CMS) is impossible to add while extending
        final PDFTimestampService timestampService = PdfObjFactory.newTimestampSignatureService();
        return timestampService.timestamp(document, params, this.tspSource);
    }

}

/**
 * PAdES Baseline LT signature
 */
class PAdESLevelBaselineLT implements SignatureExtension<PAdESSignatureParameters> {

    private final CertificateVerifier certificateVerifier;
    private final TSPSource tspSource;

    PAdESLevelBaselineLT(final TSPSource tspSource, final CertificateVerifier certificateVerifier) {
        this.certificateVerifier = certificateVerifier;
        this.tspSource = tspSource;
    }

    /**
     * @param document
     * @param parameters
     * @return modified document
     * @throws IOException
     */
    @Override
    public DSSDocument extendSignatures(DSSDocument document, final PAdESSignatureParameters parameters) throws DSSException {

        // check if needed to extends with PAdESLevelBaselineT
        PDFDocumentValidator pdfDocumentValidator = new PDFDocumentValidator(document);
        pdfDocumentValidator.setCertificateVerifier(this.certificateVerifier);

        List<AdvancedSignature> signatures = pdfDocumentValidator.getSignatures();
        for (final AdvancedSignature signature : signatures) {
            if (isRequireDocumentTimestamp(signature)) {
                final PAdESLevelBaselineT padesLevelBaselineT = new PAdESLevelBaselineT(this.tspSource);
                document = padesLevelBaselineT.extendSignatures(document, parameters);

                pdfDocumentValidator = new PDFDocumentValidator(document);
                pdfDocumentValidator.setCertificateVerifier(this.certificateVerifier);
                break;
            }
        }

        signatures = pdfDocumentValidator.getSignatures();

        // create DSS dictionary (order is important to know the original object
        // streams)
        List<DSSDictionaryCallback> callbacks = new ArrayList<>();
        for (final AdvancedSignature signature : signatures) {
            if (signature instanceof PAdESSignature) {
                callbacks.add(validate((PAdESSignature) signature));
            }
        }

        final PDFSignatureService signatureService = PdfObjFactory.newPAdESSignatureService();
        return signatureService.addDssDictionary(document, callbacks);

    }

    private boolean isRequireDocumentTimestamp(AdvancedSignature signature) {
        List<TimestampToken> signatureTimestamps = signature.getSignatureTimestamps();
        List<TimestampToken> archiveTimestamps = signature.getArchiveTimestamps();
        return Utils.isCollectionEmpty(signatureTimestamps) && Utils.isCollectionEmpty(archiveTimestamps);
    }

    protected DSSDictionaryCallback validate(PAdESSignature signature) {

        ValidationContext validationContext = signature.getSignatureValidationContext(this.certificateVerifier);

        DefaultAdvancedSignature.RevocationDataForInclusion revocationsForInclusionInProfileLT = signature.getRevocationDataForInclusion(validationContext);

        DSSDictionaryCallback validationCallback = new DSSDictionaryCallback();
        validationCallback.setSignature(signature);
        validationCallback.setCrls(revocationsForInclusionInProfileLT.crlTokens);
        validationCallback.setOcsps(revocationsForInclusionInProfileLT.ocspTokens);

        Set<CertificateToken> certificatesForInclusion = signature.getCertificatesForInclusion(validationContext);
        certificatesForInclusion.addAll(signature.getCertificateListWithinSignatureAndTimestamps());
        // DSS dictionary includes current certs + discovered with AIA,...
        validationCallback.setCertificates(certificatesForInclusion);

        return validationCallback;
    }

}

/**
 * PAdES Baseline LTA signature
 */
class PAdESLevelBaselineLTA implements SignatureExtension<PAdESSignatureParameters> {

    private final PAdESLevelBaselineLT padesLevelBaselineLT;
    private final PAdESLevelBaselineT padesLevelBaselineT;
    private final CertificateVerifier certificateVerifier;

    public PAdESLevelBaselineLTA(TSPSource tspSource, CertificateVerifier certificateVerifier) {
        this.padesLevelBaselineLT = new PAdESLevelBaselineLT(tspSource, certificateVerifier);
        this.padesLevelBaselineT = new PAdESLevelBaselineT(tspSource);
        this.certificateVerifier = certificateVerifier;
    }

    @Override
    public DSSDocument extendSignatures(DSSDocument document, PAdESSignatureParameters parameters) throws DSSException {

        // check if needed to extends with PAdESLevelBaselineLT
        final PDFDocumentValidator pdfDocumentValidator = new PDFDocumentValidator(document);
        pdfDocumentValidator.setCertificateVerifier(this.certificateVerifier);

        document = this.padesLevelBaselineLT.extendSignatures(document, parameters);

        // Will add a Document TimeStamp (not CMS)
        return this.padesLevelBaselineT.extendSignatures(document, parameters);
    }
}


/**
 * PAdES Baseline B signature
 *
 */
class PAdESLevelBaselineB {

    AttributeTable getSignedAttributes(Map<?,?> params, CAdESLevelBaselineB cadesProfile, PAdESSignatureParameters parameters, byte[] messageDigest) {

        AttributeTable signedAttributes = cadesProfile.getSignedAttributes(parameters);

        if (signedAttributes.get(CMSAttributes.contentType) == null) {
            ASN1ObjectIdentifier contentType = (ASN1ObjectIdentifier) params.get(CMSAttributeTableGenerator.CONTENT_TYPE);
            // contentType will be null if we're trying to generate a counter signature.
            if (contentType != null) {
                signedAttributes = signedAttributes.add(CMSAttributes.contentType, contentType);
            }
        }

        if (signedAttributes.get(CMSAttributes.messageDigest) == null) {
            signedAttributes = signedAttributes.add(CMSAttributes.messageDigest, new DEROctetString(messageDigest));
        }

        return signedAttributes;
    }

    AttributeTable getUnsignedAttributes() {
        // Unsigned attributes can't be an empty set (RFC 5652 5.3.)
        return null;
    }

}
