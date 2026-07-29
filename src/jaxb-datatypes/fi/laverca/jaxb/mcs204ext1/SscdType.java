
package fi.laverca.jaxb.mcs204ext1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;


/**
 * <p>Java class for SscdType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SscdType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Activation" type="{http://www.methics.fi/TS102204/ext/v1.0.0}ActivationType" minOccurs="0"/&gt;
 *         &lt;element name="PinStatus" type="{http://www.methics.fi/TS102204/ext/v1.0.0}PinStatusType" minOccurs="0"/&gt;
 *         &lt;element name="MobileUserCertificate" type="{http://www.methics.fi/TS102204/ext/v1.0.0}CertificateType" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "SscdType", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0", propOrder = {
    "state",
    "activation",
    "pinStatus",
    "mobileUserCertificates"
})
public class SscdType
    implements Locatable
{

    @XmlElement(name = "State", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
    protected String state;
    @XmlElement(name = "Activation", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
    protected ActivationType activation;
    @XmlElement(name = "PinStatus", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
    protected PinStatusType pinStatus;
    @XmlElement(name = "MobileUserCertificate", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0", type = CertificateType.class)
    protected List<CertificateType> mobileUserCertificates;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the activation property.
     * 
     * @return
     *     possible object is
     *     {@link ActivationType }
     *     
     */
    public ActivationType getActivation() {
        return activation;
    }

    /**
     * Sets the value of the activation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivationType }
     *     
     */
    public void setActivation(ActivationType value) {
        this.activation = value;
    }

    /**
     * Gets the value of the pinStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PinStatusType }
     *     
     */
    public PinStatusType getPinStatus() {
        return pinStatus;
    }

    /**
     * Sets the value of the pinStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PinStatusType }
     *     
     */
    public void setPinStatus(PinStatusType value) {
        this.pinStatus = value;
    }

    /**
     * Gets the value of the mobileUserCertificates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mobileUserCertificates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMobileUserCertificates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CertificateType }
     * 
     * 
     */
    public List<CertificateType> getMobileUserCertificates() {
        if (mobileUserCertificates == null) {
            mobileUserCertificates = new ArrayList<CertificateType>();
        }
        return this.mobileUserCertificates;
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
