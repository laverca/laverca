
package fi.laverca.jaxb.saml2a;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import fi.laverca.jaxb.xmlschema.Adapter1;


/**
 * <p>Java class for AuthnStatementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthnStatementType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:assertion}StatementAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}SubjectLocality" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthnContext"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="AuthnInstant" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="SessionIndex" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="SessionNotOnOrAfter" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthnStatementType", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", propOrder = {
    "subjectLocality",
    "authnContext"
})
@XmlRootElement(name = "AuthnStatement", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
public class AuthnStatement
    extends StatementAbstractType
{

    @XmlElement(name = "SubjectLocality", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected SubjectLocality subjectLocality;
    @XmlElement(name = "AuthnContext", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", required = true)
    protected AuthnContext authnContext;
    @XmlAttribute(name = "AuthnInstant", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar authnInstant;
    @XmlAttribute(name = "SessionIndex")
    protected String sessionIndex;
    @XmlAttribute(name = "SessionNotOnOrAfter")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar sessionNotOnOrAfter;

    /**
     * Gets the value of the subjectLocality property.
     * 
     * @return
     *     possible object is
     *     {@link SubjectLocality }
     *     
     */
    public SubjectLocality getSubjectLocality() {
        return subjectLocality;
    }

    /**
     * Sets the value of the subjectLocality property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubjectLocality }
     *     
     */
    public void setSubjectLocality(SubjectLocality value) {
        this.subjectLocality = value;
    }

    /**
     * Gets the value of the authnContext property.
     * 
     * @return
     *     possible object is
     *     {@link AuthnContext }
     *     
     */
    public AuthnContext getAuthnContext() {
        return authnContext;
    }

    /**
     * Sets the value of the authnContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthnContext }
     *     
     */
    public void setAuthnContext(AuthnContext value) {
        this.authnContext = value;
    }

    /**
     * Gets the value of the authnInstant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getAuthnInstant() {
        return authnInstant;
    }

    /**
     * Sets the value of the authnInstant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthnInstant(Calendar value) {
        this.authnInstant = value;
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

    /**
     * Gets the value of the sessionNotOnOrAfter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getSessionNotOnOrAfter() {
        return sessionNotOnOrAfter;
    }

    /**
     * Sets the value of the sessionNotOnOrAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionNotOnOrAfter(Calendar value) {
        this.sessionNotOnOrAfter = value;
    }

}
