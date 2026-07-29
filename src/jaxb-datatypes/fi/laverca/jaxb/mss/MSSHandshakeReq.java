
package fi.laverca.jaxb.mss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;


/**
 * <p>Java class for MSS_HandshakeReqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MSS_HandshakeReqType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://uri.etsi.org/TS102204/v1.1.2#}MessageAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SecureMethods"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="MSS_Signature" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *                 &lt;attribute name="MSS_Registration" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *                 &lt;attribute name="MSS_Notification" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *                 &lt;attribute name="MSS_ProfileQuery" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *                 &lt;attribute name="MSS_Receipt" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *                 &lt;attribute name="MSS_Status" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Certificates"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RootCAs"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DN" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SignatureAlgList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Algorithm" type="{http://uri.etsi.org/TS102204/v1.1.2#}mssURIType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MSS_HandshakeReqType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "secureMethods",
    "certificates",
    "rootCAs",
    "signatureAlgList"
})
@XmlRootElement(name = "MSS_HandshakeReq", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
public class MSSHandshakeReq
    extends MessageAbstractType
{

    @XmlElement(name = "SecureMethods", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MSSHandshakeReq.SecureMethods secureMethods;
    @XmlElement(name = "Certificates", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MSSHandshakeReq.Certificates certificates;
    @XmlElement(name = "RootCAs", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MSSHandshakeReq.RootCAs rootCAs;
    @XmlElement(name = "SignatureAlgList", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MSSHandshakeReq.SignatureAlgList signatureAlgList;

    /**
     * Gets the value of the secureMethods property.
     * 
     * @return
     *     possible object is
     *     {@link MSSHandshakeReq.SecureMethods }
     *     
     */
    public MSSHandshakeReq.SecureMethods getSecureMethods() {
        return secureMethods;
    }

    /**
     * Sets the value of the secureMethods property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSSHandshakeReq.SecureMethods }
     *     
     */
    public void setSecureMethods(MSSHandshakeReq.SecureMethods value) {
        this.secureMethods = value;
    }

    /**
     * Gets the value of the certificates property.
     * 
     * @return
     *     possible object is
     *     {@link MSSHandshakeReq.Certificates }
     *     
     */
    public MSSHandshakeReq.Certificates getCertificates() {
        return certificates;
    }

    /**
     * Sets the value of the certificates property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSSHandshakeReq.Certificates }
     *     
     */
    public void setCertificates(MSSHandshakeReq.Certificates value) {
        this.certificates = value;
    }

    /**
     * Gets the value of the rootCAs property.
     * 
     * @return
     *     possible object is
     *     {@link MSSHandshakeReq.RootCAs }
     *     
     */
    public MSSHandshakeReq.RootCAs getRootCAs() {
        return rootCAs;
    }

    /**
     * Sets the value of the rootCAs property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSSHandshakeReq.RootCAs }
     *     
     */
    public void setRootCAs(MSSHandshakeReq.RootCAs value) {
        this.rootCAs = value;
    }

    /**
     * Gets the value of the signatureAlgList property.
     * 
     * @return
     *     possible object is
     *     {@link MSSHandshakeReq.SignatureAlgList }
     *     
     */
    public MSSHandshakeReq.SignatureAlgList getSignatureAlgList() {
        return signatureAlgList;
    }

    /**
     * Sets the value of the signatureAlgList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MSSHandshakeReq.SignatureAlgList }
     *     
     */
    public void setSignatureAlgList(MSSHandshakeReq.SignatureAlgList value) {
        this.signatureAlgList = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "certificates"
    })
    public static class Certificates
        implements Locatable
    {

        @XmlElement(name = "Certificate", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = byte[].class)
        protected List<byte[]> certificates;
        @XmlLocation
        @XmlTransient
        protected Locator locator;

        /**
         * Gets the value of the certificates property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the certificates property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCertificates().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * byte[]
         * 
         */
        public List<byte[]> getCertificates() {
            if (certificates == null) {
                certificates = new ArrayList<byte[]>();
            }
            return this.certificates;
        }

        public Locator sourceLocation() {
            return locator;
        }

        public void setSourceLocation(Locator newLocator) {
            locator = newLocator;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="DN" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "dns"
    })
    public static class RootCAs
        implements Locatable
    {

        @XmlElement(name = "DN", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = String.class)
        protected List<String> dns;
        @XmlLocation
        @XmlTransient
        protected Locator locator;

        /**
         * Gets the value of the dns property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dns property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDNS().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getDNS() {
            if (dns == null) {
                dns = new ArrayList<String>();
            }
            return this.dns;
        }

        public Locator sourceLocation() {
            return locator;
        }

        public void setSourceLocation(Locator newLocator) {
            locator = newLocator;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="MSS_Signature" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
     *       &lt;attribute name="MSS_Registration" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
     *       &lt;attribute name="MSS_Notification" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
     *       &lt;attribute name="MSS_ProfileQuery" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
     *       &lt;attribute name="MSS_Receipt" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
     *       &lt;attribute name="MSS_Status" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class SecureMethods
        implements Locatable
    {

        @XmlAttribute(name = "MSS_Signature", required = true)
        protected boolean mssSignature;
        @XmlAttribute(name = "MSS_Registration", required = true)
        protected boolean mssRegistration;
        @XmlAttribute(name = "MSS_Notification", required = true)
        protected boolean mssNotification;
        @XmlAttribute(name = "MSS_ProfileQuery", required = true)
        protected boolean mssProfileQuery;
        @XmlAttribute(name = "MSS_Receipt", required = true)
        protected boolean mssReceipt;
        @XmlAttribute(name = "MSS_Status", required = true)
        protected boolean mssStatus;
        @XmlLocation
        @XmlTransient
        protected Locator locator;

        /**
         * Gets the value of the mssSignature property.
         * 
         */
        public boolean isMSSSignature() {
            return mssSignature;
        }

        /**
         * Sets the value of the mssSignature property.
         * 
         */
        public void setMSSSignature(boolean value) {
            this.mssSignature = value;
        }

        /**
         * Gets the value of the mssRegistration property.
         * 
         */
        public boolean isMSSRegistration() {
            return mssRegistration;
        }

        /**
         * Sets the value of the mssRegistration property.
         * 
         */
        public void setMSSRegistration(boolean value) {
            this.mssRegistration = value;
        }

        /**
         * Gets the value of the mssNotification property.
         * 
         */
        public boolean isMSSNotification() {
            return mssNotification;
        }

        /**
         * Sets the value of the mssNotification property.
         * 
         */
        public void setMSSNotification(boolean value) {
            this.mssNotification = value;
        }

        /**
         * Gets the value of the mssProfileQuery property.
         * 
         */
        public boolean isMSSProfileQuery() {
            return mssProfileQuery;
        }

        /**
         * Sets the value of the mssProfileQuery property.
         * 
         */
        public void setMSSProfileQuery(boolean value) {
            this.mssProfileQuery = value;
        }

        /**
         * Gets the value of the mssReceipt property.
         * 
         */
        public boolean isMSSReceipt() {
            return mssReceipt;
        }

        /**
         * Sets the value of the mssReceipt property.
         * 
         */
        public void setMSSReceipt(boolean value) {
            this.mssReceipt = value;
        }

        /**
         * Gets the value of the mssStatus property.
         * 
         */
        public boolean isMSSStatus() {
            return mssStatus;
        }

        /**
         * Sets the value of the mssStatus property.
         * 
         */
        public void setMSSStatus(boolean value) {
            this.mssStatus = value;
        }

        public Locator sourceLocation() {
            return locator;
        }

        public void setSourceLocation(Locator newLocator) {
            locator = newLocator;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Algorithm" type="{http://uri.etsi.org/TS102204/v1.1.2#}mssURIType" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "algorithms"
    })
    public static class SignatureAlgList
        implements Locatable
    {

        @XmlElement(name = "Algorithm", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", type = MssURIType.class)
        protected List<MssURIType> algorithms;
        @XmlLocation
        @XmlTransient
        protected Locator locator;

        /**
         * Gets the value of the algorithms property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the algorithms property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAlgorithms().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MssURIType }
         * 
         * 
         */
        public List<MssURIType> getAlgorithms() {
            if (algorithms == null) {
                algorithms = new ArrayList<MssURIType>();
            }
            return this.algorithms;
        }

        public Locator sourceLocation() {
            return locator;
        }

        public void setSourceLocation(Locator newLocator) {
            locator = newLocator;
        }

    }

}
