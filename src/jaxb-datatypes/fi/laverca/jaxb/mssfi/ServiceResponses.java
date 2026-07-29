
package fi.laverca.jaxb.mssfi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.kiurumssp5.SigningCertificate;
import fi.laverca.jaxb.mid204as1.SubscriberInfo;
import fi.laverca.jaxb.mss.MeshMemberType;
import fi.laverca.jaxb.mss.MssURIType;
import fi.laverca.jaxb.mss.StatusType;
import fi.laverca.jaxb.saml2p.Response;
import fi.methics.ts102204.ext.v1_0.AdditionalSignatureResponse;
import fi.methics.ts102204.ext.v1_0.BatchSignatureResponse;
import org.w3c.dom.Element;
import org.xml.sax.Locator;


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
 *         &lt;element name="ServiceResponse" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Description" type="{http://uri.etsi.org/TS102204/v1.1.2#}mssURIType"/&gt;
 *                   &lt;element name="Entity" type="{http://uri.etsi.org/TS102204/v1.1.2#}MeshMemberType" minOccurs="0"/&gt;
 *                   &lt;element name="Status" type="{http://uri.etsi.org/TS102204/v1.1.2#}StatusType" minOccurs="0"/&gt;
 *                   &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}Response" minOccurs="0"/&gt;
 *                   &lt;element ref="{http://www.methics.fi/KiuruMSSP/v5.0.0#}signingCertificate" minOccurs="0"/&gt;
 *                   &lt;element ref="{http://mid.swisscom.ch/TS102204/as/v1.0}SubscriberInfo" minOccurs="0"/&gt;
 *                   &lt;element ref="{http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign}AdditionalSignatureResponse" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element ref="{http://www.methics.fi/TS102204/ext/v1.0.0#BatchSignature}BatchSignatureResponse" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "serviceResponses"
})
@XmlRootElement(name = "ServiceResponses", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#")
public class ServiceResponses implements Locatable
{

    @XmlElement(name = "ServiceResponse", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", type = ServiceResponses.ServiceResponse.class)
    protected List<ServiceResponses.ServiceResponse> serviceResponses;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the serviceResponses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceResponses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceResponses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceResponses.ServiceResponse }
     * 
     * 
     */
    public List<ServiceResponses.ServiceResponse> getServiceResponses() {
        if (serviceResponses == null) {
            serviceResponses = new ArrayList<ServiceResponses.ServiceResponse>();
        }
        return this.serviceResponses;
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
     *       &lt;sequence&gt;
     *         &lt;element name="Description" type="{http://uri.etsi.org/TS102204/v1.1.2#}mssURIType"/&gt;
     *         &lt;element name="Entity" type="{http://uri.etsi.org/TS102204/v1.1.2#}MeshMemberType" minOccurs="0"/&gt;
     *         &lt;element name="Status" type="{http://uri.etsi.org/TS102204/v1.1.2#}StatusType" minOccurs="0"/&gt;
     *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}Response" minOccurs="0"/&gt;
     *         &lt;element ref="{http://www.methics.fi/KiuruMSSP/v5.0.0#}signingCertificate" minOccurs="0"/&gt;
     *         &lt;element ref="{http://mid.swisscom.ch/TS102204/as/v1.0}SubscriberInfo" minOccurs="0"/&gt;
     *         &lt;element ref="{http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign}AdditionalSignatureResponse" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element ref="{http://www.methics.fi/TS102204/ext/v1.0.0#BatchSignature}BatchSignatureResponse" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "description",
        "entity",
        "status",
        "response",
        "signingCertificate",
        "subscriberInfo",
        "additionalSignatureResponses",
        "batchSignatureResponses",
        "anies"
    })
    public static class ServiceResponse
        implements Locatable
    {

        @XmlElement(name = "Description", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", required = true)
        protected MssURIType description;
        @XmlElement(name = "Entity", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#")
        protected MeshMemberType entity;
        @XmlElement(name = "Status", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#")
        protected StatusType status;
        @XmlElement(name = "Response", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
        protected Response response;
        @XmlElement(namespace = "http://www.methics.fi/KiuruMSSP/v5.0.0#")
        protected SigningCertificate signingCertificate;
        @XmlElement(name = "SubscriberInfo", namespace = "http://mid.swisscom.ch/TS102204/as/v1.0")
        protected SubscriberInfo subscriberInfo;
        @XmlElement(name = "AdditionalSignatureResponse", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign", type = AdditionalSignatureResponse.class)
        protected List<AdditionalSignatureResponse> additionalSignatureResponses;
        @XmlElement(name = "BatchSignatureResponse", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#BatchSignature", type = BatchSignatureResponse.class)
        protected List<BatchSignatureResponse> batchSignatureResponses;
        @XmlAnyElement
        protected List<Element> anies;
        @XmlLocation
        @XmlTransient
        protected Locator locator;

        /**
         * Gets the value of the description property.
         * 
         * @return
         *     possible object is
         *     {@link MssURIType }
         *     
         */
        public MssURIType getDescription() {
            return description;
        }

        /**
         * Sets the value of the description property.
         * 
         * @param value
         *     allowed object is
         *     {@link MssURIType }
         *     
         */
        public void setDescription(MssURIType value) {
            this.description = value;
        }

        /**
         * Gets the value of the entity property.
         * 
         * @return
         *     possible object is
         *     {@link MeshMemberType }
         *     
         */
        public MeshMemberType getEntity() {
            return entity;
        }

        /**
         * Sets the value of the entity property.
         * 
         * @param value
         *     allowed object is
         *     {@link MeshMemberType }
         *     
         */
        public void setEntity(MeshMemberType value) {
            this.entity = value;
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
         * Gets the value of the response property.
         * 
         * @return
         *     possible object is
         *     {@link Response }
         *     
         */
        public Response getResponse() {
            return response;
        }

        /**
         * Sets the value of the response property.
         * 
         * @param value
         *     allowed object is
         *     {@link Response }
         *     
         */
        public void setResponse(Response value) {
            this.response = value;
        }

        /**
         * Gets the value of the signingCertificate property.
         * 
         * @return
         *     possible object is
         *     {@link SigningCertificate }
         *     
         */
        public SigningCertificate getSigningCertificate() {
            return signingCertificate;
        }

        /**
         * Sets the value of the signingCertificate property.
         * 
         * @param value
         *     allowed object is
         *     {@link SigningCertificate }
         *     
         */
        public void setSigningCertificate(SigningCertificate value) {
            this.signingCertificate = value;
        }

        /**
         * Gets the value of the subscriberInfo property.
         * 
         * @return
         *     possible object is
         *     {@link SubscriberInfo }
         *     
         */
        public SubscriberInfo getSubscriberInfo() {
            return subscriberInfo;
        }

        /**
         * Sets the value of the subscriberInfo property.
         * 
         * @param value
         *     allowed object is
         *     {@link SubscriberInfo }
         *     
         */
        public void setSubscriberInfo(SubscriberInfo value) {
            this.subscriberInfo = value;
        }

        /**
         * Gets the value of the additionalSignatureResponses property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the additionalSignatureResponses property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAdditionalSignatureResponses().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AdditionalSignatureResponse }
         * 
         * 
         */
        public List<AdditionalSignatureResponse> getAdditionalSignatureResponses() {
            if (additionalSignatureResponses == null) {
                additionalSignatureResponses = new ArrayList<AdditionalSignatureResponse>();
            }
            return this.additionalSignatureResponses;
        }

        /**
         * Gets the value of the batchSignatureResponses property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the batchSignatureResponses property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBatchSignatureResponses().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BatchSignatureResponse }
         * 
         * 
         */
        public List<BatchSignatureResponse> getBatchSignatureResponses() {
            if (batchSignatureResponses == null) {
                batchSignatureResponses = new ArrayList<BatchSignatureResponse>();
            }
            return this.batchSignatureResponses;
        }

        /**
         * Gets the value of the anies property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the anies property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAnies().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Element }
         * 
         * 
         */
        public List<Element> getAnies() {
            if (anies == null) {
                anies = new ArrayList<Element>();
            }
            return this.anies;
        }

        public Locator sourceLocation() {
            return locator;
        }

        public void setSourceLocation(Locator newLocator) {
            locator = newLocator;
        }

    }

}
