
package fi.laverca.jaxb.saml2a;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;


/**
 * <p>Java class for SubjectLocalityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubjectLocalityType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Address" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="DNSName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectLocalityType", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
@XmlRootElement(name = "SubjectLocality", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
public class SubjectLocality
    implements Locatable
{

    @XmlAttribute(name = "Address")
    protected String address;
    @XmlAttribute(name = "DNSName")
    protected String dnsName;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the dnsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDNSName() {
        return dnsName;
    }

    /**
     * Sets the value of the dnsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDNSName(String value) {
        this.dnsName = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
