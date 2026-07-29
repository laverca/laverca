
package fi.laverca.jaxb.mreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntityUserType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityUserType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.methics.fi/MSSPRegistration/v1.0.0#}EntityType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EntityUserID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityUserType", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", propOrder = {
    "entityUserID"
})
public class EntityUserType
    extends EntityType
{

    @XmlElement(name = "EntityUserID", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected String entityUserID;

    /**
     * Gets the value of the entityUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityUserID() {
        return entityUserID;
    }

    /**
     * Sets the value of the entityUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityUserID(String value) {
        this.entityUserID = value;
    }

}
