
package fi.laverca.jaxb.saml2a;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.xmldsigcore.Signature;
import fi.laverca.jaxb.xmlschema.Adapter1;
import org.xml.sax.Locator;


/**
 * <p>Java class for AssertionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssertionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Issuer"/&gt;
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Subject" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Conditions" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Advice" minOccurs="0"/&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Statement"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthnStatement"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AuthzDecisionStatement"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AttributeStatement"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="IssueInstant" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssertionType", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", propOrder = {
    "issuer",
    "signature",
    "subject",
    "conditions",
    "advice",
    "statementsAndAuthnStatementsAndAuthzDecisionStatements"
})
@XmlRootElement(name = "Assertion", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
public class Assertion implements Locatable
{

    @XmlElement(name = "Issuer", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", required = true)
    protected NameIDType issuer;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected Signature signature;
    @XmlElement(name = "Subject", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected Subject subject;
    @XmlElement(name = "Conditions", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected Conditions conditions;
    @XmlElement(name = "Advice", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected Advice advice;
    @XmlElements({
        @XmlElement(name = "Statement", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = StatementAbstractType.class),
        @XmlElement(name = "AuthnStatement", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = AuthnStatement.class),
        @XmlElement(name = "AuthzDecisionStatement", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = AuthzDecisionStatement.class),
        @XmlElement(name = "AttributeStatement", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = AttributeStatement.class)
    })
    protected List<StatementAbstractType> statementsAndAuthnStatementsAndAuthzDecisionStatements;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "ID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "IssueInstant", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar issueInstant;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the issuer property.
     * 
     * @return
     *     possible object is
     *     {@link NameIDType }
     *     
     */
    public NameIDType getIssuer() {
        return issuer;
    }

    /**
     * Sets the value of the issuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIDType }
     *     
     */
    public void setIssuer(NameIDType value) {
        this.issuer = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link Signature }
     *     
     */
    public Signature getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link Signature }
     *     
     */
    public void setSignature(Signature value) {
        this.signature = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link Subject }
     *     
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subject }
     *     
     */
    public void setSubject(Subject value) {
        this.subject = value;
    }

    /**
     * Gets the value of the conditions property.
     * 
     * @return
     *     possible object is
     *     {@link Conditions }
     *     
     */
    public Conditions getConditions() {
        return conditions;
    }

    /**
     * Sets the value of the conditions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Conditions }
     *     
     */
    public void setConditions(Conditions value) {
        this.conditions = value;
    }

    /**
     * Gets the value of the advice property.
     * 
     * @return
     *     possible object is
     *     {@link Advice }
     *     
     */
    public Advice getAdvice() {
        return advice;
    }

    /**
     * Sets the value of the advice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Advice }
     *     
     */
    public void setAdvice(Advice value) {
        this.advice = value;
    }

    /**
     * Gets the value of the statementsAndAuthnStatementsAndAuthzDecisionStatements property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statementsAndAuthnStatementsAndAuthzDecisionStatements property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatementsAndAuthnStatementsAndAuthzDecisionStatements().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatementAbstractType }
     * {@link AuthnStatement }
     * {@link AuthzDecisionStatement }
     * {@link AttributeStatement }
     * 
     * 
     */
    public List<StatementAbstractType> getStatementsAndAuthnStatementsAndAuthzDecisionStatements() {
        if (statementsAndAuthnStatementsAndAuthzDecisionStatements == null) {
            statementsAndAuthnStatementsAndAuthzDecisionStatements = new ArrayList<StatementAbstractType>();
        }
        return this.statementsAndAuthnStatementsAndAuthzDecisionStatements;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the issueInstant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getIssueInstant() {
        return issueInstant;
    }

    /**
     * Sets the value of the issueInstant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueInstant(Calendar value) {
        this.issueInstant = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
