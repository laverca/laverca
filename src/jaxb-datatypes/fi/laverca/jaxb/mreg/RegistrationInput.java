
package fi.laverca.jaxb.mreg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.mss.MessagingModeType;
import org.xml.sax.Locator;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Target" type="{http://www.methics.fi/MSSPRegistration/v1.0.0#}TargetType"/&gt;
 *         &lt;element name="MregRequest" type="{http://www.methics.fi/MSSPRegistration/v1.0.0#}MregRequestType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="InputId" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="DefaultNameSpace" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="MessagingMode" type="{http://uri.etsi.org/TS102204/v1.1.2#}MessagingModeType" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "target",
    "mregRequests"
})
@XmlRootElement(name = "RegistrationInput", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
public class RegistrationInput
    implements Locatable
{

    @XmlElement(name = "Target", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", required = true)
    protected TargetType target;
    @XmlElement(name = "MregRequest", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", type = MregRequestType.class)
    protected List<MregRequestType> mregRequests;
    @XmlAttribute(name = "InputId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String inputId;
    @XmlAttribute(name = "DefaultNameSpace")
    protected String defaultNameSpace;
    @XmlAttribute(name = "MessagingMode")
    protected MessagingModeType messagingMode;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link TargetType }
     *     
     */
    public TargetType getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link TargetType }
     *     
     */
    public void setTarget(TargetType value) {
        this.target = value;
    }

    /**
     * Gets the value of the mregRequests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mregRequests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMregRequests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MregRequestType }
     * 
     * 
     */
    public List<MregRequestType> getMregRequests() {
        if (mregRequests == null) {
            mregRequests = new ArrayList<MregRequestType>();
        }
        return this.mregRequests;
    }

    /**
     * Gets the value of the inputId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputId() {
        return inputId;
    }

    /**
     * Sets the value of the inputId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputId(String value) {
        this.inputId = value;
    }

    /**
     * Gets the value of the defaultNameSpace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultNameSpace() {
        return defaultNameSpace;
    }

    /**
     * Sets the value of the defaultNameSpace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultNameSpace(String value) {
        this.defaultNameSpace = value;
    }

    /**
     * Gets the value of the messagingMode property.
     * 
     * @return
     *     possible object is
     *     {@link MessagingModeType }
     *     
     */
    public MessagingModeType getMessagingMode() {
        return messagingMode;
    }

    /**
     * Sets the value of the messagingMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessagingModeType }
     *     
     */
    public void setMessagingMode(MessagingModeType value) {
        this.messagingMode = value;
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
