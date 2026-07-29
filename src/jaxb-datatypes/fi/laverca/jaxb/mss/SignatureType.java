
package fi.laverca.jaxb.mss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.mssfi.PKCS1;
import fi.laverca.jaxb.xmldsigcore.Signature;
import org.w3c.dom.Element;
import org.xml.sax.Locator;


/**
 * <p>Java class for SignatureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SignatureType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="XMLSignature" type="{http://www.w3.org/2000/09/xmldsig#}SignatureType"/&gt;
 *         &lt;element name="Base64Signature" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element ref="{http://mss.ficom.fi/TS102204/v1.0.0#}PKCS1"/&gt;
 *         &lt;any processContents='lax' namespace='##other'/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignatureType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "any",
    "pkcs1",
    "base64Signature",
    "xmlSignature"
})
public class SignatureType
    implements Locatable
{

    @XmlAnyElement
    protected Element any;
    @XmlElement(name = "PKCS1", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#")
    protected PKCS1 pkcs1;
    @XmlElement(name = "Base64Signature", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected byte[] base64Signature;
    @XmlElement(name = "XMLSignature", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected Signature xmlSignature;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Element }
     *     
     */
    public Element getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     
     */
    public void setAny(Element value) {
        this.any = value;
    }

    /**
     * Gets the value of the pkcs1 property.
     * 
     * @return
     *     possible object is
     *     {@link PKCS1 }
     *     
     */
    public PKCS1 getPKCS1() {
        return pkcs1;
    }

    /**
     * Sets the value of the pkcs1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link PKCS1 }
     *     
     */
    public void setPKCS1(PKCS1 value) {
        this.pkcs1 = value;
    }

    /**
     * Gets the value of the base64Signature property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBase64Signature() {
        return base64Signature;
    }

    /**
     * Sets the value of the base64Signature property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBase64Signature(byte[] value) {
        this.base64Signature = value;
    }

    /**
     * Gets the value of the xmlSignature property.
     * 
     * @return
     *     possible object is
     *     {@link Signature }
     *     
     */
    public Signature getXMLSignature() {
        return xmlSignature;
    }

    /**
     * Sets the value of the xmlSignature property.
     * 
     * @param value
     *     allowed object is
     *     {@link Signature }
     *     
     */
    public void setXMLSignature(Signature value) {
        this.xmlSignature = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
