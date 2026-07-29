
package fi.laverca.jaxb.mss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for MSS_ProfileReqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MSS_ProfileReqType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://uri.etsi.org/TS102204/v1.1.2#}MessageAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MobileUser" type="{http://uri.etsi.org/TS102204/v1.1.2#}MobileUserType"/&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element name="GetAutoActivation" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *           &lt;element name="GetServerSideSignature" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *           &lt;element name="GetRecoveryCodeCreated" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *           &lt;element name="GetCertificates" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *           &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "MSS_ProfileReqType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "mobileUser",
    "getAutoActivationsAndGetServerSideSignaturesAndGetRecoveryCodeCreateds"
})
@XmlRootElement(name = "MSS_ProfileReq", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
public class MSSProfileReq
    extends MessageAbstractType
{

    @XmlElement(name = "MobileUser", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MobileUserType mobileUser;
    @XmlElementRefs({
        @XmlElementRef(name = "GetAutoActivation", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "GetServerSideSignature", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "GetRecoveryCodeCreated", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "GetCertificates", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = JAXBElement.class, required = false)
    })
    @XmlAnyElement
    protected List<Object> getAutoActivationsAndGetServerSideSignaturesAndGetRecoveryCodeCreateds;

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
     * Gets the value of the getAutoActivationsAndGetServerSideSignaturesAndGetRecoveryCodeCreateds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the getAutoActivationsAndGetServerSideSignaturesAndGetRecoveryCodeCreateds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGetAutoActivationsAndGetServerSideSignaturesAndGetRecoveryCodeCreateds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * {@link Element }
     * 
     * 
     */
    public List<Object> getGetAutoActivationsAndGetServerSideSignaturesAndGetRecoveryCodeCreateds() {
        if (getAutoActivationsAndGetServerSideSignaturesAndGetRecoveryCodeCreateds == null) {
            getAutoActivationsAndGetServerSideSignaturesAndGetRecoveryCodeCreateds = new ArrayList<Object>();
        }
        return this.getAutoActivationsAndGetServerSideSignaturesAndGetRecoveryCodeCreateds;
    }

}
