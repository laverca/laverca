
package fi.laverca.jaxb.mss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for MSS_StatusRespType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MSS_StatusRespType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://uri.etsi.org/TS102204/v1.1.2#}MessageAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MobileUser" type="{http://uri.etsi.org/TS102204/v1.1.2#}MobileUserType"/&gt;
 *         &lt;element name="MSS_Signature" type="{http://uri.etsi.org/TS102204/v1.1.2#}SignatureType" minOccurs="0"/&gt;
 *         &lt;element name="Status" type="{http://uri.etsi.org/TS102204/v1.1.2#}StatusType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="MSSP_TransID" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MSS_StatusRespType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "mobileUser",
    "mssSignature",
    "status"
})
@XmlRootElement(name = "MSS_StatusResp", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
public class MSSStatusResp
    extends MessageAbstractType
{

    @XmlElement(name = "MobileUser", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MobileUserType mobileUser;
    @XmlElement(name = "MSS_Signature", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected SignatureType mssSignature;
    @XmlElement(name = "Status", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected StatusType status;
    @XmlAttribute(name = "MSSP_TransID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String msspTransID;

    /**
     * Gets the value of the mobileUser property.
     * 
     * @return
     *     possible object is
     *     {@link MobileUserType }
     *     
     */
    public MobileUserType getMobileUser() {
        return mobileUser;
    }

    /**
     * Sets the value of the mobileUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link MobileUserType }
     *     
     */
    public void setMobileUser(MobileUserType value) {
        this.mobileUser = value;
    }

    /**
     * Gets the value of the mssSignature property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getMSSSignature() {
        return mssSignature;
    }

    /**
     * Sets the value of the mssSignature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setMSSSignature(SignatureType value) {
        this.mssSignature = value;
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

    /**
     * Gets the value of the msspTransID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSSPTransID() {
        return msspTransID;
    }

    /**
     * Sets the value of the msspTransID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSSPTransID(String value) {
        this.msspTransID = value;
    }

}
