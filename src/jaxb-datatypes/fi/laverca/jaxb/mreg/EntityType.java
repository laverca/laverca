
package fi.laverca.jaxb.mreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.mss.MeshMemberType;
import org.xml.sax.Locator;


/**
 * <p>Java class for EntityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="ApID" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *           &lt;element name="CustomerID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *           &lt;element name="MsspID" type="{http://uri.etsi.org/TS102204/v1.1.2#}MeshMemberType"/&gt;
 *           &lt;element name="SpID" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *           &lt;element name="NasID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *           &lt;element name="CA_URI" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "EntityType", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", propOrder = {
    "cauri",
    "nasID",
    "spID",
    "msspID",
    "customerID",
    "apID"
})
@XmlSeeAlso({
    EntityUserType.class
})
public class EntityType
    implements Locatable
{

    @XmlElement(name = "CA_URI", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected String cauri;
    @XmlElement(name = "NasID", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected String nasID;
    @XmlElement(name = "SpID", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    @XmlSchemaType(name = "anyURI")
    protected String spID;
    @XmlElement(name = "MsspID", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected MeshMemberType msspID;
    @XmlElement(name = "CustomerID", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected String customerID;
    @XmlElement(name = "ApID", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    @XmlSchemaType(name = "anyURI")
    protected String apID;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the cauri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCAURI() {
        return cauri;
    }

    /**
     * Sets the value of the cauri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCAURI(String value) {
        this.cauri = value;
    }

    /**
     * Gets the value of the nasID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNasID() {
        return nasID;
    }

    /**
     * Sets the value of the nasID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNasID(String value) {
        this.nasID = value;
    }

    /**
     * Gets the value of the spID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpID() {
        return spID;
    }

    /**
     * Sets the value of the spID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpID(String value) {
        this.spID = value;
    }

    /**
     * Gets the value of the msspID property.
     * 
     * @return
     *     possible object is
     *     {@link MeshMemberType }
     *     
     */
    public MeshMemberType getMsspID() {
        return msspID;
    }

    /**
     * Sets the value of the msspID property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeshMemberType }
     *     
     */
    public void setMsspID(MeshMemberType value) {
        this.msspID = value;
    }

    /**
     * Gets the value of the customerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Sets the value of the customerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerID(String value) {
        this.customerID = value;
    }

    /**
     * Gets the value of the apID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApID() {
        return apID;
    }

    /**
     * Sets the value of the apID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApID(String value) {
        this.apID = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
