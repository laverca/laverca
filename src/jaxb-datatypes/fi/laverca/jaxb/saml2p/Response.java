
package fi.laverca.jaxb.saml2p;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import fi.laverca.jaxb.saml2a.Assertion;
import fi.laverca.jaxb.saml2a.EncryptedElementType;


/**
 * <p>Java class for ResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:protocol}StatusResponseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Assertion"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}EncryptedAssertion"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseType", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", propOrder = {
    "assertionsAndEncryptedAssertions"
})
@XmlRootElement(name = "Response", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
public class Response
    extends StatusResponseType
{

    @XmlElements({
        @XmlElement(name = "Assertion", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = Assertion.class),
        @XmlElement(name = "EncryptedAssertion", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = EncryptedElementType.class)
    })
    protected List<Object> assertionsAndEncryptedAssertions;

    /**
     * Gets the value of the assertionsAndEncryptedAssertions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assertionsAndEncryptedAssertions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssertionsAndEncryptedAssertions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Assertion }
     * {@link EncryptedElementType }
     * 
     * 
     */
    public List<Object> getAssertionsAndEncryptedAssertions() {
        if (assertionsAndEncryptedAssertions == null) {
            assertionsAndEncryptedAssertions = new ArrayList<Object>();
        }
        return this.assertionsAndEncryptedAssertions;
    }

}
