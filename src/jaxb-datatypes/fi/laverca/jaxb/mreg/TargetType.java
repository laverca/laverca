
package fi.laverca.jaxb.mreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.mss.MobileUserType;
import org.xml.sax.Locator;


/**
 * <p>Java class for TargetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TargetType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EndPointID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="MobileUser" type="{http://uri.etsi.org/TS102204/v1.1.2#}MobileUserType"/&gt;
 *           &lt;element name="SimCard" type="{http://www.methics.fi/MSSPRegistration/v1.0.0#}SimCardType"/&gt;
 *           &lt;element name="Entity" type="{http://www.methics.fi/MSSPRegistration/v1.0.0#}EntityType"/&gt;
 *           &lt;element name="EntityUser" type="{http://www.methics.fi/MSSPRegistration/v1.0.0#}EntityUserType"/&gt;
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
@XmlType(name = "TargetType", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", propOrder = {
    "endPointID",
    "entityUser",
    "entity",
    "simCard",
    "mobileUser"
})
public class TargetType
    implements Locatable
{

    @XmlElement(name = "EndPointID", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected String endPointID;
    @XmlElement(name = "EntityUser", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected EntityUserType entityUser;
    @XmlElement(name = "Entity", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected EntityType entity;
    @XmlElement(name = "SimCard", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected SimCardType simCard;
    @XmlElement(name = "MobileUser", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected MobileUserType mobileUser;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the endPointID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndPointID() {
        return endPointID;
    }

    /**
     * Sets the value of the endPointID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndPointID(String value) {
        this.endPointID = value;
    }

    /**
     * Gets the value of the entityUser property.
     * 
     * @return
     *     possible object is
     *     {@link EntityUserType }
     *     
     */
    public EntityUserType getEntityUser() {
        return entityUser;
    }

    /**
     * Sets the value of the entityUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityUserType }
     *     
     */
    public void setEntityUser(EntityUserType value) {
        this.entityUser = value;
    }

    /**
     * Gets the value of the entity property.
     * 
     * @return
     *     possible object is
     *     {@link EntityType }
     *     
     */
    public EntityType getEntity() {
        return entity;
    }

    /**
     * Sets the value of the entity property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityType }
     *     
     */
    public void setEntity(EntityType value) {
        this.entity = value;
    }

    /**
     * Gets the value of the simCard property.
     * 
     * @return
     *     possible object is
     *     {@link SimCardType }
     *     
     */
    public SimCardType getSimCard() {
        return simCard;
    }

    /**
     * Sets the value of the simCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimCardType }
     *     
     */
    public void setSimCard(SimCardType value) {
        this.simCard = value;
    }

    /**
     * Gets the value of the mobileUser property.
     * 
     * @return
     *     possible object is
     *     {@link MobileUserType }
     *     
     */
    public MobileUserType getMobileUser() {
        return mobileUser;
    }

    /**
     * Sets the value of the mobileUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link MobileUserType }
     *     
     */
    public void setMobileUser(MobileUserType value) {
        this.mobileUser = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
