
package fi.methics.ts102204.ext.v1_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.mss.DataType;
import fi.laverca.jaxb.mss.KeyReferenceType;
import fi.laverca.jaxb.mss.MssURIType;
import fi.laverca.jaxb.mss.SignatureProfileComparisonType;
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
 *         &lt;element name="DataToBeSigned" type="{http://uri.etsi.org/TS102204/v1.1.2#}DataType"/&gt;
 *         &lt;element name="DataToBeDisplayed" type="{http://uri.etsi.org/TS102204/v1.1.2#}DataType" minOccurs="0"/&gt;
 *         &lt;element name="SignatureProfile" type="{http://uri.etsi.org/TS102204/v1.1.2#}mssURIType" minOccurs="0"/&gt;
 *         &lt;element name="MSS_Format" type="{http://uri.etsi.org/TS102204/v1.1.2#}mssURIType" minOccurs="0"/&gt;
 *         &lt;element name="KeyReference" type="{http://uri.etsi.org/TS102204/v1.1.2#}KeyReferenceType" minOccurs="0"/&gt;
 *         &lt;element name="SignatureProfileComparison" type="{http://uri.etsi.org/TS102204/v1.1.2#}SignatureProfileComparisonType" minOccurs="0"/&gt;
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="DocumentRef" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dataToBeSigned",
    "dataToBeDisplayed",
    "signatureProfile",
    "mssFormat",
    "keyReference",
    "signatureProfileComparison",
    "anies"
})
@XmlRootElement(name = "AdditionalSignatureRequest", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign")
public class AdditionalSignatureRequest implements Locatable
{

    @XmlElement(name = "DataToBeSigned", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign", required = true)
    protected DataType dataToBeSigned;
    @XmlElement(name = "DataToBeDisplayed", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign")
    protected DataType dataToBeDisplayed;
    @XmlElement(name = "SignatureProfile", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign")
    protected MssURIType signatureProfile;
    @XmlElement(name = "MSS_Format", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign")
    protected MssURIType mssFormat;
    @XmlElement(name = "KeyReference", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign")
    protected KeyReferenceType keyReference;
    @XmlElement(name = "SignatureProfileComparison", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#MultiDocSign")
    @XmlSchemaType(name = "string")
    protected SignatureProfileComparisonType signatureProfileComparison;
    @XmlAnyElement
    protected List<Element> anies;
    @XmlAttribute(name = "DocumentRef")
    protected String documentRef;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the dataToBeSigned property.
     * 
     * @return
     *     possible object is
     *     {@link DataType }
     *     
     */
    public DataType getDataToBeSigned() {
        return dataToBeSigned;
    }

    /**
     * Sets the value of the dataToBeSigned property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataType }
     *     
     */
    public void setDataToBeSigned(DataType value) {
        this.dataToBeSigned = value;
    }

    /**
     * Gets the value of the dataToBeDisplayed property.
     * 
     * @return
     *     possible object is
     *     {@link DataType }
     *     
     */
    public DataType getDataToBeDisplayed() {
        return dataToBeDisplayed;
    }

    /**
     * Sets the value of the dataToBeDisplayed property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataType }
     *     
     */
    public void setDataToBeDisplayed(DataType value) {
        this.dataToBeDisplayed = value;
    }

    /**
     * Gets the value of the signatureProfile property.
     * 
     * @return
     *     possible object is
     *     {@link MssURIType }
     *     
     */
    public MssURIType getSignatureProfile() {
        return signatureProfile;
    }

    /**
     * Sets the value of the signatureProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link MssURIType }
     *     
     */
    public void setSignatureProfile(MssURIType value) {
        this.signatureProfile = value;
    }

    /**
     * Gets the value of the mssFormat property.
     * 
     * @return
     *     possible object is
     *     {@link MssURIType }
     *     
     */
    public MssURIType getMSSFormat() {
        return mssFormat;
    }

    /**
     * Sets the value of the mssFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link MssURIType }
     *     
     */
    public void setMSSFormat(MssURIType value) {
        this.mssFormat = value;
    }

    /**
     * Gets the value of the keyReference property.
     * 
     * @return
     *     possible object is
     *     {@link KeyReferenceType }
     *     
     */
    public KeyReferenceType getKeyReference() {
        return keyReference;
    }

    /**
     * Sets the value of the keyReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyReferenceType }
     *     
     */
    public void setKeyReference(KeyReferenceType value) {
        this.keyReference = value;
    }

    /**
     * Gets the value of the signatureProfileComparison property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureProfileComparisonType }
     *     
     */
    public SignatureProfileComparisonType getSignatureProfileComparison() {
        return signatureProfileComparison;
    }

    /**
     * Sets the value of the signatureProfileComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureProfileComparisonType }
     *     
     */
    public void setSignatureProfileComparison(SignatureProfileComparisonType value) {
        this.signatureProfileComparison = value;
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

    /**
     * Gets the value of the documentRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentRef() {
        return documentRef;
    }

    /**
     * Sets the value of the documentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentRef(String value) {
        this.documentRef = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
