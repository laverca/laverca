
package fi.laverca.jaxb.saml2p;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import fi.laverca.jaxb.saml2a.EncryptedElementType;
import fi.laverca.jaxb.saml2a.NameIDType;


/**
 * <p>Java class for ManageNameIDRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ManageNameIDRequestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:protocol}RequestAbstractType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}NameID"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}EncryptedID"/&gt;
 *         &lt;/choice&gt;
 *         &lt;choice&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}NewID"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}NewEncryptedID"/&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:protocol}Terminate"/&gt;
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
@XmlType(name = "ManageNameIDRequestType", namespace = "urn:oasis:names:tc:SAML:2.0:protocol", propOrder = {
    "encryptedID",
    "nameID",
    "terminate",
    "newEncryptedID",
    "newID"
})
@XmlRootElement(name = "ManageNameIDRequest", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
public class ManageNameIDRequest
    extends RequestAbstractType
{

    @XmlElement(name = "EncryptedID", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected EncryptedElementType encryptedID;
    @XmlElement(name = "NameID", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected NameIDType nameID;
    @XmlElement(name = "Terminate", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
    protected Terminate terminate;
    @XmlElement(name = "NewEncryptedID", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
    protected EncryptedElementType newEncryptedID;
    @XmlElement(name = "NewID", namespace = "urn:oasis:names:tc:SAML:2.0:protocol")
    protected String newID;

    /**
     * Gets the value of the encryptedID property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptedElementType }
     *     
     */
    public EncryptedElementType getEncryptedID() {
        return encryptedID;
    }

    /**
     * Sets the value of the encryptedID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedElementType }
     *     
     */
    public void setEncryptedID(EncryptedElementType value) {
        this.encryptedID = value;
    }

    /**
     * Gets the value of the nameID property.
     * 
     * @return
     *     possible object is
     *     {@link NameIDType }
     *     
     */
    public NameIDType getNameID() {
        return nameID;
    }

    /**
     * Sets the value of the nameID property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIDType }
     *     
     */
    public void setNameID(NameIDType value) {
        this.nameID = value;
    }

    /**
     * Gets the value of the terminate property.
     * 
     * @return
     *     possible object is
     *     {@link Terminate }
     *     
     */
    public Terminate getTerminate() {
        return terminate;
    }

    /**
     * Sets the value of the terminate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Terminate }
     *     
     */
    public void setTerminate(Terminate value) {
        this.terminate = value;
    }

    /**
     * Gets the value of the newEncryptedID property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptedElementType }
     *     
     */
    public EncryptedElementType getNewEncryptedID() {
        return newEncryptedID;
    }

    /**
     * Sets the value of the newEncryptedID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedElementType }
     *     
     */
    public void setNewEncryptedID(EncryptedElementType value) {
        this.newEncryptedID = value;
    }

    /**
     * Gets the value of the newID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewID() {
        return newID;
    }

    /**
     * Sets the value of the newID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewID(String value) {
        this.newID = value;
    }

}
