
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
import fi.laverca.jaxb.xmldsigcore.Signature;


/**
 * <p>Java class for MSS_ReceiptReqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MSS_ReceiptReqType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://uri.etsi.org/TS102204/v1.1.2#}MessageAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MobileUser" type="{http://uri.etsi.org/TS102204/v1.1.2#}MobileUserType"/&gt;
 *         &lt;element name="Status" type="{http://uri.etsi.org/TS102204/v1.1.2#}StatusType" minOccurs="0"/&gt;
 *         &lt;element name="Message" type="{http://uri.etsi.org/TS102204/v1.1.2#}DataType" minOccurs="0"/&gt;
 *         &lt;element name="SignedReceipt" type="{http://www.w3.org/2000/09/xmldsig#}SignatureType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="MSSP_TransID" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MSS_ReceiptReqType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "mobileUser",
    "status",
    "message",
    "signedReceipt"
})
@XmlRootElement(name = "MSS_ReceiptReq", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
public class MSSReceiptReq
    extends MessageAbstractType
{

    @XmlElement(name = "MobileUser", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MobileUserType mobileUser;
    @XmlElement(name = "Status", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected StatusType status;
    @XmlElement(name = "Message", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected DataType message;
    @XmlElement(name = "SignedReceipt", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
    protected Signature signedReceipt;
    @XmlAttribute(name = "MSSP_TransID", required = true)
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
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link DataType }
     *     
     */
    public DataType getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataType }
     *     
     */
    public void setMessage(DataType value) {
        this.message = value;
    }

    /**
     * Gets the value of the signedReceipt property.
     * 
     * @return
     *     possible object is
     *     {@link Signature }
     *     
     */
    public Signature getSignedReceipt() {
        return signedReceipt;
    }

    /**
     * Sets the value of the signedReceipt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Signature }
     *     
     */
    public void setSignedReceipt(Signature value) {
        this.signedReceipt = value;
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
