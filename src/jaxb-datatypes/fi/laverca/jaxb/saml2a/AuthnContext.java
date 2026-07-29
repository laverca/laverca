
package fi.laverca.jaxb.saml2a;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;


/**
 * <p>Java class for AuthnContextType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthnContextType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;sequence&gt;
 *             &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthnContextClassRef"/&gt;
 *             &lt;choice minOccurs="0"&gt;
 *               &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthnContextDecl"/&gt;
 *               &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthnContextDeclRef"/&gt;
 *             &lt;/choice&gt;
 *           &lt;/sequence&gt;
 *           &lt;choice&gt;
 *             &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthnContextDecl"/&gt;
 *             &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthnContextDeclRef"/&gt;
 *           &lt;/choice&gt;
 *         &lt;/choice&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthenticatingAuthority" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthnContextType", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", propOrder = {
    "authnContextClassRef",
    "authnContextDeclRef",
    "authnContextDecl",
    "authenticatingAuthorities"
})
@XmlRootElement(name = "AuthnContext", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
public class AuthnContext
    implements Locatable
{

    @XmlElement(name = "AuthnContextClassRef", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    @XmlSchemaType(name = "anyURI")
    protected String authnContextClassRef;
    @XmlElement(name = "AuthnContextDeclRef", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    @XmlSchemaType(name = "anyURI")
    protected String authnContextDeclRef;
    @XmlElement(name = "AuthnContextDecl", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected Object authnContextDecl;
    @XmlElement(name = "AuthenticatingAuthority", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = String.class)
    @XmlSchemaType(name = "anyURI")
    protected List<String> authenticatingAuthorities;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the authnContextClassRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthnContextClassRef() {
        return authnContextClassRef;
    }

    /**
     * Sets the value of the authnContextClassRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthnContextClassRef(String value) {
        this.authnContextClassRef = value;
    }

    /**
     * Gets the value of the authnContextDeclRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthnContextDeclRef() {
        return authnContextDeclRef;
    }

    /**
     * Sets the value of the authnContextDeclRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthnContextDeclRef(String value) {
        this.authnContextDeclRef = value;
    }

    /**
     * Gets the value of the authnContextDecl property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAuthnContextDecl() {
        return authnContextDecl;
    }

    /**
     * Sets the value of the authnContextDecl property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAuthnContextDecl(Object value) {
        this.authnContextDecl = value;
    }

    /**
     * Gets the value of the authenticatingAuthorities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authenticatingAuthorities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthenticatingAuthorities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAuthenticatingAuthorities() {
        if (authenticatingAuthorities == null) {
            authenticatingAuthorities = new ArrayList<String>();
        }
        return this.authenticatingAuthorities;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
