
package fi.laverca.jaxb.mss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.xmldsigcore.DigestMethod;
import org.xml.sax.Locator;


/**
 * <p>Java class for DigestAlgAndValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DigestAlgAndValueType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DigestMethod" type="{http://www.w3.org/2000/09/xmldsig#}DigestMethodType" minOccurs="0"/&gt;
 *         &lt;element name="DigestValue" type="{http://www.w3.org/2000/09/xmldsig#}DigestValueType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DigestAlgAndValueType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "digestMethod",
    "digestValue"
})
public class DigestAlgAndValueType
    implements Locatable
{

    @XmlElement(name = "DigestMethod", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected DigestMethod digestMethod;
    @XmlElement(name = "DigestValue", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected byte[] digestValue;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the digestMethod property.
     * 
     * @return
     *     possible object is
     *     {@link DigestMethod }
     *     
     */
    public DigestMethod getDigestMethod() {
        return digestMethod;
    }

    /**
     * Sets the value of the digestMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link DigestMethod }
     *     
     */
    public void setDigestMethod(DigestMethod value) {
        this.digestMethod = value;
    }

    /**
     * Gets the value of the digestValue property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDigestValue() {
        return digestValue;
    }

    /**
     * Sets the value of the digestValue property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDigestValue(byte[] value) {
        this.digestValue = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
