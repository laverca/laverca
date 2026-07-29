
package fi.laverca.jaxb.saml2p;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;


/**
 * <p>Java class for IDPListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IDPListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}IDPEntry" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}GetComplete" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IDPListType", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", propOrder = {
    "idpEntries",
    "getComplete"
})
@XmlRootElement(name = "IDPList", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
public class IDPList
    implements Locatable
{

    @XmlElement(name = "IDPEntry", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", required = true, type = IDPEntry.class)
    protected List<IDPEntry> idpEntries;
    @XmlElement(name = "GetComplete", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
    @XmlSchemaType(name = "anyURI")
    protected String getComplete;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the idpEntries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idpEntries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIDPEntries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IDPEntry }
     * 
     * 
     */
    public List<IDPEntry> getIDPEntries() {
        if (idpEntries == null) {
            idpEntries = new ArrayList<IDPEntry>();
        }
        return this.idpEntries;
    }

    /**
     * Gets the value of the getComplete property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetComplete() {
        return getComplete;
    }

    /**
     * Sets the value of the getComplete property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetComplete(String value) {
        this.getComplete = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
