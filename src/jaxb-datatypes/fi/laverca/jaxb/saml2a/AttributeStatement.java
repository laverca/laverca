
package fi.laverca.jaxb.saml2a;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AttributeStatementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AttributeStatementType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:assertion}StatementAbstractType"&gt;
 *       &lt;choice maxOccurs="unbounded"&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Attribute"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}EncryptedAttribute"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributeStatementType", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", propOrder = {
    "attributesAndEncryptedAttributes"
})
@XmlRootElement(name = "AttributeStatement", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
public class AttributeStatement
    extends StatementAbstractType
{

    @XmlElements({
        @XmlElement(name = "Attribute", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = Attribute.class),
        @XmlElement(name = "EncryptedAttribute", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = EncryptedElementType.class)
    })
    protected List<Object> attributesAndEncryptedAttributes;

    /**
     * Gets the value of the attributesAndEncryptedAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributesAndEncryptedAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributesAndEncryptedAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Attribute }
     * {@link EncryptedElementType }
     * 
     * 
     */
    public List<Object> getAttributesAndEncryptedAttributes() {
        if (attributesAndEncryptedAttributes == null) {
            attributesAndEncryptedAttributes = new ArrayList<Object>();
        }
        return this.attributesAndEncryptedAttributes;
    }

}
