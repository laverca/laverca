
package fi.laverca.jaxb.mss;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.xmlschema.Adapter1;
import fi.laverca.jaxb.xmlschema.Adapter3;
import org.xml.sax.Locator;


/**
 * <p>Java class for MessageAbstractType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageAbstractType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AP_Info"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="AP_ID" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *                 &lt;attribute name="AP_TransID" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *                 &lt;attribute name="AP_PWD" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="Instant" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *                 &lt;attribute name="AP_URL" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MSSP_Info"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="MSSP_ID" type="{http://uri.etsi.org/TS102204/v1.1.2#}MeshMemberType"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="Instant" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="MajorVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="MinorVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageAbstractType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "apInfo",
    "msspInfo"
})
@XmlSeeAlso({
    MSSHandshakeResp.class,
    MSSHandshakeReq.class,
    MSSReceiptResp.class,
    MSSReceiptReq.class,
    MSSProfileResp.class,
    MSSProfileReq.class,
    MSSRegistrationResp.class,
    MSSRegistrationReq.class,
    MSSStatusResp.class,
    MSSStatusReq.class,
    MSSSignatureResp.class,
    MSSSignatureReq.class
})
public abstract class MessageAbstractType
    implements Locatable
{

    @XmlElement(name = "AP_Info", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MessageAbstractType.APInfo apInfo;
    @XmlElement(name = "MSSP_Info", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
    protected MessageAbstractType.MSSPInfo msspInfo;
    @XmlAttribute(name = "MajorVersion", required = true)
    @XmlJavaTypeAdapter(Adapter3 .class)
    @XmlSchemaType(name = "integer")
    protected Long majorVersion;
    @XmlAttribute(name = "MinorVersion", required = true)
    @XmlJavaTypeAdapter(Adapter3 .class)
    @XmlSchemaType(name = "integer")
    protected Long minorVersion;
    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the apInfo property.
     * 
     * @return
     *     possible object is
     *     {@link MessageAbstractType.APInfo }
     *     
     */
    public MessageAbstractType.APInfo getAPInfo() {
        return apInfo;
    }

    /**
     * Sets the value of the apInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageAbstractType.APInfo }
     *     
     */
    public void setAPInfo(MessageAbstractType.APInfo value) {
        this.apInfo = value;
    }

    /**
     * Gets the value of the msspInfo property.
     * 
     * @return
     *     possible object is
     *     {@link MessageAbstractType.MSSPInfo }
     *     
     */
    public MessageAbstractType.MSSPInfo getMSSPInfo() {
        return msspInfo;
    }

    /**
     * Sets the value of the msspInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageAbstractType.MSSPInfo }
     *     
     */
    public void setMSSPInfo(MessageAbstractType.MSSPInfo value) {
        this.msspInfo = value;
    }

    /**
     * Gets the value of the majorVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getMajorVersion() {
        return majorVersion;
    }

    /**
     * Sets the value of the majorVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMajorVersion(Long value) {
        this.majorVersion = value;
    }

    /**
     * Gets the value of the minorVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getMinorVersion() {
        return minorVersion;
    }

    /**
     * Sets the value of the minorVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinorVersion(Long value) {
        this.minorVersion = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
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
     *       &lt;attribute name="AP_ID" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
     *       &lt;attribute name="AP_TransID" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
     *       &lt;attribute name="AP_PWD" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="Instant" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
     *       &lt;attribute name="AP_URL" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class APInfo
        implements Locatable
    {

        @XmlAttribute(name = "AP_ID", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String apid;
        @XmlAttribute(name = "AP_TransID", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "NCName")
        protected String apTransID;
        @XmlAttribute(name = "AP_PWD", required = true)
        protected String appwd;
        @XmlAttribute(name = "Instant", required = true)
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "dateTime")
        protected Calendar instant;
        @XmlAttribute(name = "AP_URL")
        @XmlSchemaType(name = "anyURI")
        protected String apurl;
        @XmlLocation
        @XmlTransient
        protected Locator locator;

        /**
         * Gets the value of the apid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAPID() {
            return apid;
        }

        /**
         * Sets the value of the apid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAPID(String value) {
            this.apid = value;
        }

        /**
         * Gets the value of the apTransID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAPTransID() {
            return apTransID;
        }

        /**
         * Sets the value of the apTransID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAPTransID(String value) {
            this.apTransID = value;
        }

        /**
         * Gets the value of the appwd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAPPWD() {
            return appwd;
        }

        /**
         * Sets the value of the appwd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAPPWD(String value) {
            this.appwd = value;
        }

        /**
         * Gets the value of the instant property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Calendar getInstant() {
            return instant;
        }

        /**
         * Sets the value of the instant property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInstant(Calendar value) {
            this.instant = value;
        }

        /**
         * Gets the value of the apurl property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAPURL() {
            return apurl;
        }

        /**
         * Sets the value of the apurl property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAPURL(String value) {
            this.apurl = value;
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
     *         &lt;element name="MSSP_ID" type="{http://uri.etsi.org/TS102204/v1.1.2#}MeshMemberType"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="Instant" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "msspid"
    })
    public static class MSSPInfo
        implements Locatable
    {

        @XmlElement(name = "MSSP_ID", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", required = true)
        protected MeshMemberType msspid;
        @XmlAttribute(name = "Instant")
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "dateTime")
        protected Calendar instant;
        @XmlLocation
        @XmlTransient
        protected Locator locator;

        /**
         * Gets the value of the msspid property.
         * 
         * @return
         *     possible object is
         *     {@link MeshMemberType }
         *     
         */
        public MeshMemberType getMSSPID() {
            return msspid;
        }

        /**
         * Sets the value of the msspid property.
         * 
         * @param value
         *     allowed object is
         *     {@link MeshMemberType }
         *     
         */
        public void setMSSPID(MeshMemberType value) {
            this.msspid = value;
        }

        /**
         * Gets the value of the instant property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Calendar getInstant() {
            return instant;
        }

        /**
         * Sets the value of the instant property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInstant(Calendar value) {
            this.instant = value;
        }

        public Locator sourceLocation() {
            return locator;
        }

        public void setSourceLocation(Locator newLocator) {
            locator = newLocator;
        }

    }

}
