
package fi.laverca.jaxb.saml2p;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;


/**
 * <p>Java class for RequestedAuthnContextType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestedAuthnContextType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthnContextClassRef" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthnContextDeclRef" maxOccurs="unbounded"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="Comparison" type="{urn:oasis:names:tc:SAML:2.0:protocol}AuthnContextComparisonType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestedAuthnContextType", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", propOrder = {
    "authnContextDeclReves",
    "authnContextClassReves"
})
@XmlRootElement(name = "RequestedAuthnContext", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
public class RequestedAuthnContext
    implements Locatable
{

    @XmlElement(name = "AuthnContextDeclRef", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = String.class)
    @XmlSchemaType(name = "anyURI")
    protected List<String> authnContextDeclReves;
    @XmlElement(name = "AuthnContextClassRef", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = String.class)
    @XmlSchemaType(name = "anyURI")
    protected List<String> authnContextClassReves;
    @XmlAttribute(name = "Comparison")
    protected AuthnContextComparisonType comparison;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the authnContextDeclReves property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authnContextDeclReves property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthnContextDeclReves().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAuthnContextDeclReves() {
        if (authnContextDeclReves == null) {
            authnContextDeclReves = new ArrayList<String>();
        }
        return this.authnContextDeclReves;
    }

    /**
     * Gets the value of the authnContextClassReves property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authnContextClassReves property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthnContextClassReves().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAuthnContextClassReves() {
        if (authnContextClassReves == null) {
            authnContextClassReves = new ArrayList<String>();
        }
        return this.authnContextClassReves;
    }

    /**
     * Gets the value of the comparison property.
     * 
     * @return
     *     possible object is
     *     {@link AuthnContextComparisonType }
     *     
     */
    public AuthnContextComparisonType getComparison() {
        return comparison;
    }

    /**
     * Sets the value of the comparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthnContextComparisonType }
     *     
     */
    public void setComparison(AuthnContextComparisonType value) {
        this.comparison = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
