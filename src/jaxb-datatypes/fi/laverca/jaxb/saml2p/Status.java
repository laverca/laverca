
package fi.laverca.jaxb.saml2p;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;


/**
 * <p>Java class for StatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}StatusCode"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}StatusMessage" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}StatusDetail" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusType", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", propOrder = {
    "statusCode",
    "statusMessage",
    "statusDetail"
})
@XmlRootElement(name = "Status", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
public class Status
    implements Locatable
{

    @XmlElement(name = "StatusCode", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", required = true)
    protected StatusCode statusCode;
    @XmlElement(name = "StatusMessage", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
    protected String statusMessage;
    @XmlElement(name = "StatusDetail", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
    protected StatusDetail statusDetail;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link StatusCode }
     *     
     */
    public StatusCode getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusCode }
     *     
     */
    public void setStatusCode(StatusCode value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

    /**
     * Gets the value of the statusDetail property.
     * 
     * @return
     *     possible object is
     *     {@link StatusDetail }
     *     
     */
    public StatusDetail getStatusDetail() {
        return statusDetail;
    }

    /**
     * Sets the value of the statusDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusDetail }
     *     
     */
    public void setStatusDetail(StatusDetail value) {
        this.statusDetail = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
