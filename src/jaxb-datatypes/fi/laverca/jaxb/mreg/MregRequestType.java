
package fi.laverca.jaxb.mreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;


/**
 * <p>Java class for MregRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MregRequestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element ref="{http://www.methics.fi/MSSPRegistration/v1.0.0#}WirelessOperation"/&gt;
 *           &lt;element ref="{http://www.methics.fi/MSSPRegistration/v1.0.0#}CertificationOperation"/&gt;
 *           &lt;element ref="{http://www.methics.fi/MSSPRegistration/v1.0.0#}ProvisioningOperation"/&gt;
 *           &lt;element ref="{http://www.methics.fi/MSSPRegistration/v1.0.0#}EntityOperation"/&gt;
 *           &lt;element ref="{http://www.methics.fi/MSSPRegistration/v1.0.0#}EntityUserOperation"/&gt;
 *           &lt;element ref="{http://www.methics.fi/MSSPRegistration/v1.0.0#}CompoundOperation"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MregRequestType", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", propOrder = {
    "compoundOperation",
    "entityUserOperation",
    "entityOperation",
    "provisioningOperation",
    "certificationOperation",
    "wirelessOperation"
})
public class MregRequestType
    implements Locatable
{

    @XmlElement(name = "CompoundOperation", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected CompoundOperation compoundOperation;
    @XmlElement(name = "EntityUserOperation", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected EntityUserOperation entityUserOperation;
    @XmlElement(name = "EntityOperation", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected EntityOperation entityOperation;
    @XmlElement(name = "ProvisioningOperation", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected ProvisioningOperation provisioningOperation;
    @XmlElement(name = "CertificationOperation", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected CertificationOperation certificationOperation;
    @XmlElement(name = "WirelessOperation", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected WirelessOperation wirelessOperation;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the compoundOperation property.
     * 
     * @return
     *     possible object is
     *     {@link CompoundOperation }
     *     
     */
    public CompoundOperation getCompoundOperation() {
        return compoundOperation;
    }

    /**
     * Sets the value of the compoundOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompoundOperation }
     *     
     */
    public void setCompoundOperation(CompoundOperation value) {
        this.compoundOperation = value;
    }

    /**
     * Gets the value of the entityUserOperation property.
     * 
     * @return
     *     possible object is
     *     {@link EntityUserOperation }
     *     
     */
    public EntityUserOperation getEntityUserOperation() {
        return entityUserOperation;
    }

    /**
     * Sets the value of the entityUserOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityUserOperation }
     *     
     */
    public void setEntityUserOperation(EntityUserOperation value) {
        this.entityUserOperation = value;
    }

    /**
     * Gets the value of the entityOperation property.
     * 
     * @return
     *     possible object is
     *     {@link EntityOperation }
     *     
     */
    public EntityOperation getEntityOperation() {
        return entityOperation;
    }

    /**
     * Sets the value of the entityOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityOperation }
     *     
     */
    public void setEntityOperation(EntityOperation value) {
        this.entityOperation = value;
    }

    /**
     * Gets the value of the provisioningOperation property.
     * 
     * @return
     *     possible object is
     *     {@link ProvisioningOperation }
     *     
     */
    public ProvisioningOperation getProvisioningOperation() {
        return provisioningOperation;
    }

    /**
     * Sets the value of the provisioningOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProvisioningOperation }
     *     
     */
    public void setProvisioningOperation(ProvisioningOperation value) {
        this.provisioningOperation = value;
    }

    /**
     * Gets the value of the certificationOperation property.
     * 
     * @return
     *     possible object is
     *     {@link CertificationOperation }
     *     
     */
    public CertificationOperation getCertificationOperation() {
        return certificationOperation;
    }

    /**
     * Sets the value of the certificationOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificationOperation }
     *     
     */
    public void setCertificationOperation(CertificationOperation value) {
        this.certificationOperation = value;
    }

    /**
     * Gets the value of the wirelessOperation property.
     * 
     * @return
     *     possible object is
     *     {@link WirelessOperation }
     *     
     */
    public WirelessOperation getWirelessOperation() {
        return wirelessOperation;
    }

    /**
     * Sets the value of the wirelessOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelessOperation }
     *     
     */
    public void setWirelessOperation(WirelessOperation value) {
        this.wirelessOperation = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
