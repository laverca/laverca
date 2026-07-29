
package fi.laverca.jaxb.mss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.mssfi.NoSpamCode;
import fi.laverca.jaxb.saml2p.AttributeQuery;
import fi.methics.ts102204.ext.v1_0.AdditionalSignatureRequest;
import fi.methics.ts102204.ext.v1_0.BatchSignatureRequest;
import org.w3c.dom.Element;
import org.xml.sax.Locator;


/**
 * <p>Java class for AdditionalServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdditionalServiceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Description" type="{http://uri.etsi.org/TS102204/v1.1.2#}mssURIType"/&gt;
 *         &lt;element name="Entity" type="{http://uri.etsi.org/TS102204/v1.1.2#}MeshMemberType" minOccurs="0"/&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element ref="{http://mss.ficom.fi/TS102204/v1.0.0#}SessionID"/&gt;
 *           &lt;element ref="{http://mss.ficom.fi/TS102204/v1.0.0#}EventID"/&gt;
 *           &lt;element ref="{http://mss.ficom.fi/TS102204/v1.0.0#}NoSpamCode"/&gt;
 *           &lt;element ref="{http://mss.ficom.fi/TS102204/v1.0.0#}UserLang"/&gt;
 *           &lt;element ref="{http://mss.ficom.fi/TS102204/v1.0.0#}DisplayName"/&gt;
 *           &lt;element ref="{http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign}AdditionalSignatureRequest"/&gt;
 *           &lt;element ref="{http://www.methics.fi/TS102204/ext/v1.0.0#BatchSignature}BatchSignatureRequest"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}AttributeQuery"/&gt;
 *           &lt;any processContents='lax'/&gt;
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
@XmlType(name = "AdditionalServiceType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "description",
    "entity",
    "sessionIDsAndEventIDsAndNoSpamCodes"
})
public class AdditionalServiceType
    implements Locatable
{

    @XmlElement(name = "Description", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MssURIType description;
    @XmlElement(name = "Entity", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected MeshMemberType entity;
    @XmlElementRefs({
        @XmlElementRef(name = "SessionID", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "EventID", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "NoSpamCode", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", type = NoSpamCode.class, required = false),
        @XmlElementRef(name = "UserLang", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "DisplayName", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "AdditionalSignatureRequest", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign", type = AdditionalSignatureRequest.class, required = false),
        @XmlElementRef(name = "BatchSignatureRequest", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#BatchSignature", type = BatchSignatureRequest.class, required = false),
        @XmlElementRef(name = "AttributeQuery", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", type = AttributeQuery.class, required = false)
    })
    @XmlAnyElement
    protected List<Object> sessionIDsAndEventIDsAndNoSpamCodes;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link MssURIType }
     *     
     */
    public MssURIType getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link MssURIType }
     *     
     */
    public void setDescription(MssURIType value) {
        this.description = value;
    }

    /**
     * Gets the value of the entity property.
     * 
     * @return
     *     possible object is
     *     {@link MeshMemberType }
     *     
     */
    public MeshMemberType getEntity() {
        return entity;
    }

    /**
     * Sets the value of the entity property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeshMemberType }
     *     
     */
    public void setEntity(MeshMemberType value) {
        this.entity = value;
    }

    /**
     * Gets the value of the sessionIDsAndEventIDsAndNoSpamCodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sessionIDsAndEventIDsAndNoSpamCodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSessionIDsAndEventIDsAndNoSpamCodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link NoSpamCode }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link AdditionalSignatureRequest }
     * {@link BatchSignatureRequest }
     * {@link AttributeQuery }
     * {@link Element }
     * 
     * 
     */
    public List<Object> getSessionIDsAndEventIDsAndNoSpamCodes() {
        if (sessionIDsAndEventIDsAndNoSpamCodes == null) {
            sessionIDsAndEventIDsAndNoSpamCodes = new ArrayList<Object>();
        }
        return this.sessionIDsAndEventIDsAndNoSpamCodes;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
