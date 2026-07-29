
package fi.laverca.jaxb.saml2p;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.xmlschema.Adapter3;
import org.xml.sax.Locator;


/**
 * <p>Java class for ScopingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ScopingType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}IDPList" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}RequesterID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="ProxyCount" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScopingType", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", propOrder = {
    "idpList",
    "requesterIDs"
})
@XmlRootElement(name = "Scoping", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
public class Scoping
    implements Locatable
{

    @XmlElement(name = "IDPList", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
    protected IDPList idpList;
    @XmlElement(name = "RequesterID", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", type = String.class)
    @XmlSchemaType(name = "anyURI")
    protected List<String> requesterIDs;
    @XmlAttribute(name = "ProxyCount")
    @XmlJavaTypeAdapter(Adapter3 .class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Long proxyCount;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the idpList property.
     * 
     * @return
     *     possible object is
     *     {@link IDPList }
     *     
     */
    public IDPList getIDPList() {
        return idpList;
    }

    /**
     * Sets the value of the idpList property.
     * 
     * @param value
     *     allowed object is
     *     {@link IDPList }
     *     
     */
    public void setIDPList(IDPList value) {
        this.idpList = value;
    }

    /**
     * Gets the value of the requesterIDs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requesterIDs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequesterIDs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRequesterIDs() {
        if (requesterIDs == null) {
            requesterIDs = new ArrayList<String>();
        }
        return this.requesterIDs;
    }

    /**
     * Gets the value of the proxyCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getProxyCount() {
        return proxyCount;
    }

    /**
     * Sets the value of the proxyCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProxyCount(Long value) {
        this.proxyCount = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
