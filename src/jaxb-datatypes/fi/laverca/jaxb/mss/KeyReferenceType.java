
package fi.laverca.jaxb.mss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.w3c.dom.Element;
import org.xml.sax.Locator;


/**
 * <p>Java class for KeyReferenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KeyReferenceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CertificateURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CertificateIssuerDN" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="HashOfUsersPublicKey" type="{http://uri.etsi.org/TS102204/v1.1.2#}DigestAlgAndValueType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="HashOfCAPublicKey" type="{http://uri.etsi.org/TS102204/v1.1.2#}DigestAlgAndValueType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;any processContents='lax' namespace='##other'/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyReferenceType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "certificateURLs",
    "certificateIssuerDNs",
    "hashOfUsersPublicKeies",
    "hashOfCAPublicKeies",
    "any"
})
public class KeyReferenceType
    implements Locatable
{

    @XmlElement(name = "CertificateURL", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = String.class)
    @XmlSchemaType(name = "anyURI")
    protected List<String> certificateURLs;
    @XmlElement(name = "CertificateIssuerDN", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = String.class)
    protected List<String> certificateIssuerDNs;
    @XmlElement(name = "HashOfUsersPublicKey", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = DigestAlgAndValueType.class)
    protected List<DigestAlgAndValueType> hashOfUsersPublicKeies;
    @XmlElement(name = "HashOfCAPublicKey", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = DigestAlgAndValueType.class)
    protected List<DigestAlgAndValueType> hashOfCAPublicKeies;
    @XmlAnyElement
    protected Element any;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the certificateURLs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificateURLs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificateURLs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCertificateURLs() {
        if (certificateURLs == null) {
            certificateURLs = new ArrayList<String>();
        }
        return this.certificateURLs;
    }

    /**
     * Gets the value of the certificateIssuerDNs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificateIssuerDNs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificateIssuerDNs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCertificateIssuerDNs() {
        if (certificateIssuerDNs == null) {
            certificateIssuerDNs = new ArrayList<String>();
        }
        return this.certificateIssuerDNs;
    }

    /**
     * Gets the value of the hashOfUsersPublicKeies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hashOfUsersPublicKeies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHashOfUsersPublicKeies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DigestAlgAndValueType }
     * 
     * 
     */
    public List<DigestAlgAndValueType> getHashOfUsersPublicKeies() {
        if (hashOfUsersPublicKeies == null) {
            hashOfUsersPublicKeies = new ArrayList<DigestAlgAndValueType>();
        }
        return this.hashOfUsersPublicKeies;
    }

    /**
     * Gets the value of the hashOfCAPublicKeies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hashOfCAPublicKeies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHashOfCAPublicKeies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DigestAlgAndValueType }
     * 
     * 
     */
    public List<DigestAlgAndValueType> getHashOfCAPublicKeies() {
        if (hashOfCAPublicKeies == null) {
            hashOfCAPublicKeies = new ArrayList<DigestAlgAndValueType>();
        }
        return this.hashOfCAPublicKeies;
    }

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Element }
     *     
     */
    public Element getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     
     */
    public void setAny(Element value) {
        this.any = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
