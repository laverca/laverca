
package fi.laverca.jaxb.mss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MSS_ProfileRespType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MSS_ProfileRespType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://uri.etsi.org/TS102204/v1.1.2#}MessageAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SignatureProfile" type="{http://uri.etsi.org/TS102204/v1.1.2#}mssURIType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Status" type="{http://uri.etsi.org/TS102204/v1.1.2#}StatusType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MSS_ProfileRespType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "signatureProfiles",
    "status"
})
@XmlRootElement(name = "MSS_ProfileResp", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
public class MSSProfileResp
    extends MessageAbstractType
{

    @XmlElement(name = "SignatureProfile", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = MssURIType.class)
    protected List<MssURIType> signatureProfiles;
    @XmlElement(name = "Status", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected StatusType status;

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
     * {@link MssURIType }
     * 
     * 
     */
    public List<MssURIType> getSignatureProfiles() {
        if (signatureProfiles == null) {
            signatureProfiles = new ArrayList<MssURIType>();
        }
        return this.signatureProfiles;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

}
