
package fi.laverca.jaxb.saml2p;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthnQueryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthnQueryType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:protocol}SubjectQueryAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}RequestedAuthnContext" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="SessionIndex" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthnQueryType", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", propOrder = {
    "requestedAuthnContext"
})
@XmlRootElement(name = "AuthnQuery", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
public class AuthnQuery
    extends SubjectQueryAbstractType
{

    @XmlElement(name = "RequestedAuthnContext", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
    protected RequestedAuthnContext requestedAuthnContext;
    @XmlAttribute(name = "SessionIndex")
    protected String sessionIndex;

    /**
     * Gets the value of the requestedAuthnContext property.
     * 
     * @return
     *     possible object is
     *     {@link RequestedAuthnContext }
     *     
     */
    public RequestedAuthnContext getRequestedAuthnContext() {
        return requestedAuthnContext;
    }

    /**
     * Sets the value of the requestedAuthnContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestedAuthnContext }
     *     
     */
    public void setRequestedAuthnContext(RequestedAuthnContext value) {
        this.requestedAuthnContext = value;
    }

    /**
     * Gets the value of the sessionIndex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionIndex() {
        return sessionIndex;
    }

    /**
     * Sets the value of the sessionIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionIndex(String value) {
        this.sessionIndex = value;
    }

}
