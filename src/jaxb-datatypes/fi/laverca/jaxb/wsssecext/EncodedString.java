
package fi.laverca.jaxb.wsssecext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * This type is used for elements containing stringified binary data.
 * 
 * <p>Java class for EncodedString complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncodedString"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd&gt;AttributedString"&gt;
 *       &lt;attribute name="EncodingType" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncodedString", namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd")
@XmlSeeAlso({
    KeyIdentifier.class,
    BinarySecurityToken.class
})
public class EncodedString
    extends AttributedString
{

    @XmlAttribute(name = "EncodingType")
    @XmlSchemaType(name = "anyURI")
    protected String encodingType;

    /**
     * Gets the value of the encodingType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncodingType() {
        return encodingType;
    }

    /**
     * Sets the value of the encodingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncodingType(String value) {
        this.encodingType = value;
    }

}
