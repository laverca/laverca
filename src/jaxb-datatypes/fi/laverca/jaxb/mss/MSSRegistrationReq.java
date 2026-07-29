
package fi.laverca.jaxb.mss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import fi.laverca.jaxb.mreg.RegistrationInput;
import fi.laverca.jaxb.xmlenc.EncryptedType;
import org.w3c.dom.Element;


/**
 * <p>Java class for MSS_RegistrationReqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MSS_RegistrationReqType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://uri.etsi.org/TS102204/v1.1.2#}MessageAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MobileUser" type="{http://uri.etsi.org/TS102204/v1.1.2#}MobileUserType"/&gt;
 *         &lt;element name="EncryptedData" type="{http://www.w3.org/2001/04/xmlenc#}EncryptedType" minOccurs="0"/&gt;
 *         &lt;element name="EncryptResponseBy" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="CertificateURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="X509Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.methics.fi/MSSPRegistration/v1.0.0#}RegistrationInput" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MSS_RegistrationReqType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "mobileUser",
    "encryptedData",
    "encryptResponseBy",
    "certificateURI",
    "x509Certificate",
    "registrationInputs",
    "anies"
})
@XmlRootElement(name = "MSS_RegistrationReq", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
public class MSSRegistrationReq
    extends MessageAbstractType
{

    @XmlElement(name = "MobileUser", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MobileUserType mobileUser;
    @XmlElement(name = "EncryptedData", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected EncryptedType encryptedData;
    @XmlElement(name = "EncryptResponseBy", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    @XmlSchemaType(name = "anyURI")
    protected String encryptResponseBy;
    @XmlElement(name = "CertificateURI", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    @XmlSchemaType(name = "anyURI")
    protected String certificateURI;
    @XmlElement(name = "X509Certificate", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected byte[] x509Certificate;
    @XmlElement(name = "RegistrationInput", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", type = RegistrationInput.class)
    protected List<RegistrationInput> registrationInputs;
    @XmlAnyElement
    protected List<Element> anies;

    /**
     * Gets the value of the mobileUser property.
     * 
     * @return
     *     possible object is
     *     {@link MobileUserType }
     *     
     */
    public MobileUserType getMobileUser() {
        return mobileUser;
    }

    /**
     * Sets the value of the mobileUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link MobileUserType }
     *     
     */
    public void setMobileUser(MobileUserType value) {
        this.mobileUser = value;
    }

    /**
     * Gets the value of the encryptedData property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptedType }
     *     
     */
    public EncryptedType getEncryptedData() {
        return encryptedData;
    }

    /**
     * Sets the value of the encryptedData property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedType }
     *     
     */
    public void setEncryptedData(EncryptedType value) {
        this.encryptedData = value;
    }

    /**
     * Gets the value of the encryptResponseBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptResponseBy() {
        return encryptResponseBy;
    }

    /**
     * Sets the value of the encryptResponseBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptResponseBy(String value) {
        this.encryptResponseBy = value;
    }

    /**
     * Gets the value of the certificateURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificateURI() {
        return certificateURI;
    }

    /**
     * Sets the value of the certificateURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateURI(String value) {
        this.certificateURI = value;
    }

    /**
     * Gets the value of the x509Certificate property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getX509Certificate() {
        return x509Certificate;
    }

    /**
     * Sets the value of the x509Certificate property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setX509Certificate(byte[] value) {
        this.x509Certificate = value;
    }

    /**
     * Gets the value of the registrationInputs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the registrationInputs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegistrationInputs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegistrationInput }
     * 
     * 
     */
    public List<RegistrationInput> getRegistrationInputs() {
        if (registrationInputs == null) {
            registrationInputs = new ArrayList<RegistrationInput>();
        }
        return this.registrationInputs;
    }

    /**
     * Gets the value of the anies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the anies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * 
     * 
     */
    public List<Element> getAnies() {
        if (anies == null) {
            anies = new ArrayList<Element>();
        }
        return this.anies;
    }

}
