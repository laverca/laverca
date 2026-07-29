
package fi.laverca.jaxb.saml2a;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AudienceRestrictionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AudienceRestrictionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:assertion}ConditionAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Audience" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AudienceRestrictionType", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", propOrder = {
    "audiences"
})
@XmlRootElement(name = "AudienceRestriction", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
public class AudienceRestriction
    extends ConditionAbstractType
{

    @XmlElement(name = "Audience", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", required = true, type = String.class)
    @XmlSchemaType(name = "anyURI")
    protected List<String> audiences;

    /**
     * Gets the value of the audiences property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the audiences property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAudiences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAudiences() {
        if (audiences == null) {
            audiences = new ArrayList<String>();
        }
        return this.audiences;
    }

}
