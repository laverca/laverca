
package fi.laverca.jaxb.mss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.w3c.dom.Element;
import org.xml.sax.Locator;


/**
 * <p>Java class for mssURIType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mssURIType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mssURI" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="DigestAlgAndValue" type="{http://uri.etsi.org/TS102204/v1.1.2#}DigestAlgAndValueType" minOccurs="0"/&gt;
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mssURIType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "mssURI",
    "digestAlgAndValue",
    "anies"
})
public class MssURIType
    implements Locatable
{

    @XmlElement(namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String mssURI;
    @XmlElement(name = "DigestAlgAndValue", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected DigestAlgAndValueType digestAlgAndValue;
    @XmlAnyElement
    protected List<Element> anies;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the mssURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMssURI() {
        return mssURI;
    }

    /**
     * Sets the value of the mssURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMssURI(String value) {
        this.mssURI = value;
    }

    /**
     * Gets the value of the digestAlgAndValue property.
     * 
     * @return
     *     possible object is
     *     {@link DigestAlgAndValueType }
     *     
     */
    public DigestAlgAndValueType getDigestAlgAndValue() {
        return digestAlgAndValue;
    }

    /**
     * Sets the value of the digestAlgAndValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link DigestAlgAndValueType }
     *     
     */
    public void setDigestAlgAndValue(DigestAlgAndValueType value) {
        this.digestAlgAndValue = value;
    }

    /**
     * Gets the value of the anies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the anies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * 
     * 
     */
    public List<Element> getAnies() {
        if (anies == null) {
            anies = new ArrayList<Element>();
        }
        return this.anies;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
