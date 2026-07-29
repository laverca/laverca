
package fi.laverca.jaxb.mcs204ext1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import fi.laverca.jaxb.xmldsigcore.X509DataType;


/**
 * <p>Java class for CertificateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertificateType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.w3.org/2000/09/xmldsig#}X509DataType"&gt;
 *       &lt;attribute name="State" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Algorithm" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="SignatureProfiles" type="{http://www.methics.fi/TS102204/ext/v1.0.0}SignatureProfileType" /&gt;
 *       &lt;attribute name="SigningMethod" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateType", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
public class CertificateType
    extends X509DataType
{

    @XmlAttribute(name = "State")
    protected String state;
    @XmlAttribute(name = "Algorithm")
    protected String algorithm;
    @XmlAttribute(name = "SignatureProfiles")
    protected List<String> signatureProfiles;
    @XmlAttribute(name = "SigningMethod")
    protected String signingMethod;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the algorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Sets the value of the algorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

    /**
     * Gets the value of the signatureProfiles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signatureProfiles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignatureProfiles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSignatureProfiles() {
        if (signatureProfiles == null) {
            signatureProfiles = new ArrayList<String>();
        }
        return this.signatureProfiles;
    }

    /**
     * Gets the value of the signingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigningMethod() {
        return signingMethod;
    }

    /**
     * Sets the value of the signingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigningMethod(String value) {
        this.signingMethod = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
