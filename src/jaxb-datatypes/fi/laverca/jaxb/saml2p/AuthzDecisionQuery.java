
package fi.laverca.jaxb.saml2p;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import fi.laverca.jaxb.saml2a.Action;
import fi.laverca.jaxb.saml2a.Evidence;


/**
 * <p>Java class for AuthzDecisionQueryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthzDecisionQueryType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:protocol}SubjectQueryAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Action" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Evidence" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Resource" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthzDecisionQueryType", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", propOrder = {
    "actions",
    "evidence"
})
@XmlRootElement(name = "AuthzDecisionQuery", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
public class AuthzDecisionQuery
    extends SubjectQueryAbstractType
{

    @XmlElement(name = "Action", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", required = true, type = Action.class)
    protected List<Action> actions;
    @XmlElement(name = "Evidence", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected Evidence evidence;
    @XmlAttribute(name = "Resource", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String resource;

    /**
     * Gets the value of the actions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Action }
     * 
     * 
     */
    public List<Action> getActions() {
        if (actions == null) {
            actions = new ArrayList<Action>();
        }
        return this.actions;
    }

    /**
     * Gets the value of the evidence property.
     * 
     * @return
     *     possible object is
     *     {@link Evidence }
     *     
     */
    public Evidence getEvidence() {
        return evidence;
    }

    /**
     * Sets the value of the evidence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Evidence }
     *     
     */
    public void setEvidence(Evidence value) {
        this.evidence = value;
    }

    /**
     * Gets the value of the resource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResource() {
        return resource;
    }

    /**
     * Sets the value of the resource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResource(String value) {
        this.resource = value;
    }

}
