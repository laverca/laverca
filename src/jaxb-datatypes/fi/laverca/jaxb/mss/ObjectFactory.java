
package fi.laverca.jaxb.mss;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fi.laverca.jaxb.mss package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MSSProfileReqGetAutoActivation_QNAME = new QName("http://uri.etsi.org/TS102204/v1.1.2#", "GetAutoActivation");
    private final static QName _MSSProfileReqGetServerSideSignature_QNAME = new QName("http://uri.etsi.org/TS102204/v1.1.2#", "GetServerSideSignature");
    private final static QName _MSSProfileReqGetRecoveryCodeCreated_QNAME = new QName("http://uri.etsi.org/TS102204/v1.1.2#", "GetRecoveryCodeCreated");
    private final static QName _MSSProfileReqGetCertificates_QNAME = new QName("http://uri.etsi.org/TS102204/v1.1.2#", "GetCertificates");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fi.laverca.jaxb.mss
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MSSSignatureReq }
     * 
     */
    public MSSSignatureReq createMSSSignatureReq() {
        return new MSSSignatureReq();
    }

    /**
     * Create an instance of {@link MSSHandshakeReq }
     * 
     */
    public MSSHandshakeReq createMSSHandshakeReq() {
        return new MSSHandshakeReq();
    }

    /**
     * Create an instance of {@link MSSHandshakeResp }
     * 
     */
    public MSSHandshakeResp createMSSHandshakeResp() {
        return new MSSHandshakeResp();
    }

    /**
     * Create an instance of {@link fi.laverca.jaxb.mss.MessageAbstractType.APInfo }
     * 
     */
    public fi.laverca.jaxb.mss.MessageAbstractType.APInfo createMessageAbstractTypeAPInfo() {
        return new fi.laverca.jaxb.mss.MessageAbstractType.APInfo();
    }

    /**
     * Create an instance of {@link fi.laverca.jaxb.mss.MessageAbstractType.MSSPInfo }
     * 
     */
    public fi.laverca.jaxb.mss.MessageAbstractType.MSSPInfo createMessageAbstractTypeMSSPInfo() {
        return new fi.laverca.jaxb.mss.MessageAbstractType.MSSPInfo();
    }

    /**
     * Create an instance of {@link MobileUserType }
     * 
     */
    public MobileUserType createMobileUserType() {
        return new MobileUserType();
    }

    /**
     * Create an instance of {@link DataType }
     * 
     */
    public DataType createDataType() {
        return new DataType();
    }

    /**
     * Create an instance of {@link MssURIType }
     * 
     */
    public MssURIType createMssURIType() {
        return new MssURIType();
    }

    /**
     * Create an instance of {@link MSSSignatureReq.AdditionalServices }
     * 
     */
    public MSSSignatureReq.AdditionalServices createMSSSignatureReqAdditionalServices() {
        return new MSSSignatureReq.AdditionalServices();
    }

    /**
     * Create an instance of {@link KeyReferenceType }
     * 
     */
    public KeyReferenceType createKeyReferenceType() {
        return new KeyReferenceType();
    }

    /**
     * Create an instance of {@link MSSSignatureResp }
     * 
     */
    public MSSSignatureResp createMSSSignatureResp() {
        return new MSSSignatureResp();
    }

    /**
     * Create an instance of {@link SignatureType }
     * 
     */
    public SignatureType createSignatureType() {
        return new SignatureType();
    }

    /**
     * Create an instance of {@link StatusType }
     * 
     */
    public StatusType createStatusType() {
        return new StatusType();
    }

    /**
     * Create an instance of {@link MSSStatusReq }
     * 
     */
    public MSSStatusReq createMSSStatusReq() {
        return new MSSStatusReq();
    }

    /**
     * Create an instance of {@link MSSStatusResp }
     * 
     */
    public MSSStatusResp createMSSStatusResp() {
        return new MSSStatusResp();
    }

    /**
     * Create an instance of {@link MSSRegistrationReq }
     * 
     */
    public MSSRegistrationReq createMSSRegistrationReq() {
        return new MSSRegistrationReq();
    }

    /**
     * Create an instance of {@link MSSRegistrationResp }
     * 
     */
    public MSSRegistrationResp createMSSRegistrationResp() {
        return new MSSRegistrationResp();
    }

    /**
     * Create an instance of {@link MSSProfileReq }
     * 
     */
    public MSSProfileReq createMSSProfileReq() {
        return new MSSProfileReq();
    }

    /**
     * Create an instance of {@link MSSProfileResp }
     * 
     */
    public MSSProfileResp createMSSProfileResp() {
        return new MSSProfileResp();
    }

    /**
     * Create an instance of {@link MSSReceiptReq }
     * 
     */
    public MSSReceiptReq createMSSReceiptReq() {
        return new MSSReceiptReq();
    }

    /**
     * Create an instance of {@link MSSReceiptResp }
     * 
     */
    public MSSReceiptResp createMSSReceiptResp() {
        return new MSSReceiptResp();
    }

    /**
     * Create an instance of {@link MSSHandshakeReq.SecureMethods }
     * 
     */
    public MSSHandshakeReq.SecureMethods createMSSHandshakeReqSecureMethods() {
        return new MSSHandshakeReq.SecureMethods();
    }

    /**
     * Create an instance of {@link MSSHandshakeReq.Certificates }
     * 
     */
    public MSSHandshakeReq.Certificates createMSSHandshakeReqCertificates() {
        return new MSSHandshakeReq.Certificates();
    }

    /**
     * Create an instance of {@link MSSHandshakeReq.RootCAs }
     * 
     */
    public MSSHandshakeReq.RootCAs createMSSHandshakeReqRootCAs() {
        return new MSSHandshakeReq.RootCAs();
    }

    /**
     * Create an instance of {@link MSSHandshakeReq.SignatureAlgList }
     * 
     */
    public MSSHandshakeReq.SignatureAlgList createMSSHandshakeReqSignatureAlgList() {
        return new MSSHandshakeReq.SignatureAlgList();
    }

    /**
     * Create an instance of {@link MSSHandshakeResp.SecureMethods }
     * 
     */
    public MSSHandshakeResp.SecureMethods createMSSHandshakeRespSecureMethods() {
        return new MSSHandshakeResp.SecureMethods();
    }

    /**
     * Create an instance of {@link MSSHandshakeResp.MatchingMSSPCertificates }
     * 
     */
    public MSSHandshakeResp.MatchingMSSPCertificates createMSSHandshakeRespMatchingMSSPCertificates() {
        return new MSSHandshakeResp.MatchingMSSPCertificates();
    }

    /**
     * Create an instance of {@link MSSHandshakeResp.MatchingAPCertificates }
     * 
     */
    public MSSHandshakeResp.MatchingAPCertificates createMSSHandshakeRespMatchingAPCertificates() {
        return new MSSHandshakeResp.MatchingAPCertificates();
    }

    /**
     * Create an instance of {@link MSSHandshakeResp.MatchingSigAlgList }
     * 
     */
    public MSSHandshakeResp.MatchingSigAlgList createMSSHandshakeRespMatchingSigAlgList() {
        return new MSSHandshakeResp.MatchingSigAlgList();
    }

    /**
     * Create an instance of {@link MSSMessageSignature }
     * 
     */
    public MSSMessageSignature createMSSMessageSignature() {
        return new MSSMessageSignature();
    }

    /**
     * Create an instance of {@link DigestAlgAndValueType }
     * 
     */
    public DigestAlgAndValueType createDigestAlgAndValueType() {
        return new DigestAlgAndValueType();
    }

    /**
     * Create an instance of {@link MeshMemberType }
     * 
     */
    public MeshMemberType createMeshMemberType() {
        return new MeshMemberType();
    }

    /**
     * Create an instance of {@link AdditionalServiceType }
     * 
     */
    public AdditionalServiceType createAdditionalServiceType() {
        return new AdditionalServiceType();
    }

    /**
     * Create an instance of {@link StatusCodeType }
     * 
     */
    public StatusCodeType createStatusCodeType() {
        return new StatusCodeType();
    }

    /**
     * Create an instance of {@link StatusDetailType }
     * 
     */
    public StatusDetailType createStatusDetailType() {
        return new StatusDetailType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://uri.etsi.org/TS102204/v1.1.2#", name = "GetAutoActivation", scope = MSSProfileReq.class)
    public JAXBElement<Boolean> createMSSProfileReqGetAutoActivation(Boolean value) {
        return new JAXBElement<Boolean>(_MSSProfileReqGetAutoActivation_QNAME, Boolean.class, MSSProfileReq.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://uri.etsi.org/TS102204/v1.1.2#", name = "GetServerSideSignature", scope = MSSProfileReq.class)
    public JAXBElement<Boolean> createMSSProfileReqGetServerSideSignature(Boolean value) {
        return new JAXBElement<Boolean>(_MSSProfileReqGetServerSideSignature_QNAME, Boolean.class, MSSProfileReq.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://uri.etsi.org/TS102204/v1.1.2#", name = "GetRecoveryCodeCreated", scope = MSSProfileReq.class)
    public JAXBElement<Boolean> createMSSProfileReqGetRecoveryCodeCreated(Boolean value) {
        return new JAXBElement<Boolean>(_MSSProfileReqGetRecoveryCodeCreated_QNAME, Boolean.class, MSSProfileReq.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://uri.etsi.org/TS102204/v1.1.2#", name = "GetCertificates", scope = MSSProfileReq.class)
    public JAXBElement<Boolean> createMSSProfileReqGetCertificates(Boolean value) {
        return new JAXBElement<Boolean>(_MSSProfileReqGetCertificates_QNAME, Boolean.class, MSSProfileReq.class, value);
    }

}
