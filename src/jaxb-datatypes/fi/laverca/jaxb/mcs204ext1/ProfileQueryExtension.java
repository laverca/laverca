
package fi.laverca.jaxb.mcs204ext1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.w3c.dom.Element;
import org.xml.sax.Locator;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MobileUserCertificate" type="{http://www.methics.fi/TS102204/ext/v1.0.0}CertificateType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AutoActivation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ServerSideSignature" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="RecoveryCodeCreated" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="PreferredSigningMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MobileUser" type="{http://www.methics.fi/TS102204/ext/v1.0.0}MobileUserType" minOccurs="0"/&gt;
 *         &lt;element name="Sscds" type="{http://www.methics.fi/TS102204/ext/v1.0.0}SscdListType" minOccurs="0"/&gt;
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mobileUserCertificates",
    "autoActivation",
    "serverSideSignature",
    "recoveryCodeCreated",
    "preferredSigningMethod",
    "mobileUser",
    "sscds",
    "anies"
})
@XmlRootElement(name = "ProfileQueryExtension", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
public class ProfileQueryExtension implements Locatable
{

    @XmlElement(name = "MobileUserCertificate", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0", type = CertificateType.class)
    protected List<CertificateType> mobileUserCertificates;
    @XmlElement(name = "AutoActivation", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
    protected Boolean autoActivation;
    @XmlElement(name = "ServerSideSignature", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
    protected Boolean serverSideSignature;
    @XmlElement(name = "RecoveryCodeCreated", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
    protected Boolean recoveryCodeCreated;
    @XmlElement(name = "PreferredSigningMethod", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
    protected String preferredSigningMethod;
    @XmlElement(name = "MobileUser", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
    protected MobileUserType mobileUser;
    @XmlElement(name = "Sscds", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
    protected SscdListType sscds;
    @XmlAnyElement
    protected List<Element> anies;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the mobileUserCertificates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mobileUserCertificates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMobileUserCertificates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CertificateType }
     * 
     * 
     */
    public List<CertificateType> getMobileUserCertificates() {
        if (mobileUserCertificates == null) {
            mobileUserCertificates = new ArrayList<CertificateType>();
        }
        return this.mobileUserCertificates;
    }

    /**
     * Gets the value of the autoActivation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoActivation() {
        return autoActivation;
    }

    /**
     * Sets the value of the autoActivation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoActivation(Boolean value) {
        this.autoActivation = value;
    }

    /**
     * Gets the value of the serverSideSignature property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isServerSideSignature() {
        return serverSideSignature;
    }

    /**
     * Sets the value of the serverSideSignature property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setServerSideSignature(Boolean value) {
        this.serverSideSignature = value;
    }

    /**
     * Gets the value of the recoveryCodeCreated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecoveryCodeCreated() {
        return recoveryCodeCreated;
    }

    /**
     * Sets the value of the recoveryCodeCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecoveryCodeCreated(Boolean value) {
        this.recoveryCodeCreated = value;
    }

    /**
     * Gets the value of the preferredSigningMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredSigningMethod() {
        return preferredSigningMethod;
    }

    /**
     * Sets the value of the preferredSigningMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredSigningMethod(String value) {
        this.preferredSigningMethod = value;
    }

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
     * Gets the value of the sscds property.
     * 
     * @return
     *     possible object is
     *     {@link SscdListType }
     *     
     */
    public SscdListType getSscds() {
        return sscds;
    }

    /**
     * Sets the value of the sscds property.
     * 
     * @param value
     *     allowed object is
     *     {@link SscdListType }
     *     
     */
    public void setSscds(SscdListType value) {
        this.sscds = value;
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

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
