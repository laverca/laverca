
package fi.laverca.jaxb.saml2a;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.xmlschema.Adapter1;
import org.xml.sax.Locator;


/**
 * <p>Java class for ConditionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConditionsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Condition"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AudienceRestriction"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}OneTimeUse"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}ProxyRestriction"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="NotBefore" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="NotOnOrAfter" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConditionsType", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", propOrder = {
    "conditionsAndAudienceRestrictionsAndOneTimeUses"
})
@XmlRootElement(name = "Conditions", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
public class Conditions
    implements Locatable
{

    @XmlElements({
        @XmlElement(name = "Condition", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = ConditionAbstractType.class),
        @XmlElement(name = "AudienceRestriction", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = AudienceRestriction.class),
        @XmlElement(name = "OneTimeUse", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = OneTimeUse.class),
        @XmlElement(name = "ProxyRestriction", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = ProxyRestriction.class)
    })
    protected List<ConditionAbstractType> conditionsAndAudienceRestrictionsAndOneTimeUses;
    @XmlAttribute(name = "NotBefore")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar notBefore;
    @XmlAttribute(name = "NotOnOrAfter")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar notOnOrAfter;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the conditionsAndAudienceRestrictionsAndOneTimeUses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conditionsAndAudienceRestrictionsAndOneTimeUses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConditionsAndAudienceRestrictionsAndOneTimeUses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConditionAbstractType }
     * {@link AudienceRestriction }
     * {@link OneTimeUse }
     * {@link ProxyRestriction }
     * 
     * 
     */
    public List<ConditionAbstractType> getConditionsAndAudienceRestrictionsAndOneTimeUses() {
        if (conditionsAndAudienceRestrictionsAndOneTimeUses == null) {
            conditionsAndAudienceRestrictionsAndOneTimeUses = new ArrayList<ConditionAbstractType>();
        }
        return this.conditionsAndAudienceRestrictionsAndOneTimeUses;
    }

    /**
     * Gets the value of the notBefore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getNotBefore() {
        return notBefore;
    }

    /**
     * Sets the value of the notBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotBefore(Calendar value) {
        this.notBefore = value;
    }

    /**
     * Gets the value of the notOnOrAfter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getNotOnOrAfter() {
        return notOnOrAfter;
    }

    /**
     * Sets the value of the notOnOrAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotOnOrAfter(Calendar value) {
        this.notOnOrAfter = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
