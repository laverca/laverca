
package fi.laverca.jaxb.wsssecext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.w3c.dom.Element;
import org.xml.sax.Locator;


/**
 * This complexType defines header block to use for
 *       security-relevant data directed at a specific SOAP actor.
 * 
 * <p>Java class for SecurityHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SecurityHeaderType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.methics.fi/Kiuru-wssecurity/v1.0.0#}OriginatingPersonToken" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.methics.fi/Kiuru-wssecurity/v1.0.0#}ClientToken" minOccurs="0"/&gt;
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecurityHeaderType", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", propOrder = {
    "originatingPersonToken",
    "clientToken",
    "anies"
})
@XmlRootElement(name = "Security", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd")
public class Security
    implements Locatable
{

    @XmlElement(name = "OriginatingPersonToken", namespace = "http://www.methics.fi/Kiuru-wssecurity/v1.0.0#")
    protected UsernameTokenType originatingPersonToken;
    @XmlElement(name = "ClientToken", namespace = "http://www.methics.fi/Kiuru-wssecurity/v1.0.0#")
    protected UsernameTokenType clientToken;
    @XmlAnyElement
    protected List<Element> anies;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the originatingPersonToken property.
     * 
     * @return
     *     possible object is
     *     {@link UsernameTokenType }
     *     
     */
    public UsernameTokenType getOriginatingPersonToken() {
        return originatingPersonToken;
    }

    /**
     * Sets the value of the originatingPersonToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsernameTokenType }
     *     
     */
    public void setOriginatingPersonToken(UsernameTokenType value) {
        this.originatingPersonToken = value;
    }

    /**
     * Gets the value of the clientToken property.
     * 
     * @return
     *     possible object is
     *     {@link UsernameTokenType }
     *     
     */
    public UsernameTokenType getClientToken() {
        return clientToken;
    }

    /**
     * Sets the value of the clientToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsernameTokenType }
     *     
     */
    public void setClientToken(UsernameTokenType value) {
        this.clientToken = value;
    }

    /**
     * Gets the value of the anies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the anies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * 
     * 
     */
    public List<Element> getAnies() {
        if (anies == null) {
            anies = new ArrayList<Element>();
        }
        return this.anies;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
