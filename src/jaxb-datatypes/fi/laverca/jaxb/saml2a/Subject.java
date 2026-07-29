
package fi.laverca.jaxb.saml2a;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;


/**
 * <p>Java class for SubjectType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubjectType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;sequence&gt;
 *           &lt;choice&gt;
 *             &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}BaseID"/&gt;
 *             &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}NameID"/&gt;
 *             &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}EncryptedID"/&gt;
 *           &lt;/choice&gt;
 *           &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}SubjectConfirmation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;/sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}SubjectConfirmation" maxOccurs="unbounded"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectType", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", propOrder = {
    "encryptedID",
    "nameID",
    "baseID",
    "subjectConfirmations"
})
@XmlRootElement(name = "Subject", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
public class Subject
    implements Locatable
{

    @XmlElement(name = "EncryptedID", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected EncryptedElementType encryptedID;
    @XmlElement(name = "NameID", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected NameIDType nameID;
    @XmlElement(name = "BaseID", namespace = "urn:oasis:names:tc:SAML:2.0:assertion")
    protected BaseID baseID;
    @XmlElement(name = "SubjectConfirmation", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = SubjectConfirmation.class)
    protected List<SubjectConfirmation> subjectConfirmations;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

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
     * Gets the value of the baseID property.
     * 
     * @return
     *     possible object is
     *     {@link BaseID }
     *     
     */
    public BaseID getBaseID() {
        return baseID;
    }

    /**
     * Sets the value of the baseID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseID }
     *     
     */
    public void setBaseID(BaseID value) {
        this.baseID = value;
    }

    /**
     * Gets the value of the subjectConfirmations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subjectConfirmations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubjectConfirmations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubjectConfirmation }
     * 
     * 
     */
    public List<SubjectConfirmation> getSubjectConfirmations() {
        if (subjectConfirmations == null) {
            subjectConfirmations = new ArrayList<SubjectConfirmation>();
        }
        return this.subjectConfirmations;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
