
package fi.laverca.jaxb.sco204ext1;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.xmlschema.Adapter3;
import org.xml.sax.Locator;


/**
 * <p>Java class for ReceiptExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReceiptExtensionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReceiptProfile" type="{http://www.swisscom.ch/TS102204/ext/v1.0.0}ReceiptProfileType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="ReceiptMessagingMode" use="required" type="{http://www.swisscom.ch/TS102204/ext/v1.0.0}ReceiptMessagingModeType" /&gt;
 *       &lt;attribute name="TimeOut" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" /&gt;
 *       &lt;attribute name="RetryTimeOut" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" /&gt;
 *       &lt;attribute name="NextRetry" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" /&gt;
 *       &lt;attribute name="NetworkAck" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="ClientAck" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="UserAck" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="UserResponse" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="FaultMessage" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReceiptExtensionType", namespace = "http://www.swisscom.ch/TS102204/ext/v1.0.0", propOrder = {
    "receiptProfile"
})
public class ReceiptExtensionType
    implements Locatable
{

    @XmlElement(name = "ReceiptProfile", namespace = "http://www.swisscom.ch/TS102204/ext/v1.0.0")
    protected ReceiptProfileType receiptProfile;
    @XmlAttribute(name = "ReceiptMessagingMode", required = true)
    protected ReceiptMessagingModeType receiptMessagingMode;
    @XmlAttribute(name = "TimeOut")
    @XmlJavaTypeAdapter(Adapter3 .class)
    @XmlSchemaType(name = "positiveInteger")
    protected Long timeOut;
    @XmlAttribute(name = "RetryTimeOut")
    @XmlJavaTypeAdapter(Adapter3 .class)
    @XmlSchemaType(name = "positiveInteger")
    protected Long retryTimeOut;
    @XmlAttribute(name = "NextRetry")
    @XmlJavaTypeAdapter(Adapter3 .class)
    @XmlSchemaType(name = "positiveInteger")
    protected Long nextRetry;
    @XmlAttribute(name = "NetworkAck")
    protected Boolean networkAck;
    @XmlAttribute(name = "ClientAck")
    protected Boolean clientAck;
    @XmlAttribute(name = "UserAck")
    protected Boolean userAck;
    @XmlAttribute(name = "UserResponse")
    protected String userResponse;
    @XmlAttribute(name = "FaultMessage")
    protected String faultMessage;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the receiptProfile property.
     * 
     * @return
     *     possible object is
     *     {@link ReceiptProfileType }
     *     
     */
    public ReceiptProfileType getReceiptProfile() {
        return receiptProfile;
    }

    /**
     * Sets the value of the receiptProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceiptProfileType }
     *     
     */
    public void setReceiptProfile(ReceiptProfileType value) {
        this.receiptProfile = value;
    }

    /**
     * Gets the value of the receiptMessagingMode property.
     * 
     * @return
     *     possible object is
     *     {@link ReceiptMessagingModeType }
     *     
     */
    public ReceiptMessagingModeType getReceiptMessagingMode() {
        return receiptMessagingMode;
    }

    /**
     * Sets the value of the receiptMessagingMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceiptMessagingModeType }
     *     
     */
    public void setReceiptMessagingMode(ReceiptMessagingModeType value) {
        this.receiptMessagingMode = value;
    }

    /**
     * Gets the value of the timeOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getTimeOut() {
        return timeOut;
    }

    /**
     * Sets the value of the timeOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeOut(Long value) {
        this.timeOut = value;
    }

    /**
     * Gets the value of the retryTimeOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getRetryTimeOut() {
        return retryTimeOut;
    }

    /**
     * Sets the value of the retryTimeOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetryTimeOut(Long value) {
        this.retryTimeOut = value;
    }

    /**
     * Gets the value of the nextRetry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getNextRetry() {
        return nextRetry;
    }

    /**
     * Sets the value of the nextRetry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextRetry(Long value) {
        this.nextRetry = value;
    }

    /**
     * Gets the value of the networkAck property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNetworkAck() {
        return networkAck;
    }

    /**
     * Sets the value of the networkAck property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNetworkAck(Boolean value) {
        this.networkAck = value;
    }

    /**
     * Gets the value of the clientAck property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isClientAck() {
        return clientAck;
    }

    /**
     * Sets the value of the clientAck property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setClientAck(Boolean value) {
        this.clientAck = value;
    }

    /**
     * Gets the value of the userAck property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUserAck() {
        return userAck;
    }

    /**
     * Sets the value of the userAck property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUserAck(Boolean value) {
        this.userAck = value;
    }

    /**
     * Gets the value of the userResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserResponse() {
        return userResponse;
    }

    /**
     * Sets the value of the userResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserResponse(String value) {
        this.userResponse = value;
    }

    /**
     * Gets the value of the faultMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultMessage() {
        return faultMessage;
    }

    /**
     * Sets the value of the faultMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultMessage(String value) {
        this.faultMessage = value;
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
