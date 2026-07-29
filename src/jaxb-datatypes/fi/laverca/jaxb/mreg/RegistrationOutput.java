
package fi.laverca.jaxb.mreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
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
 *         &lt;element name="MregResponse" type="{http://www.methics.fi/MSSPRegistration/v1.0.0#}MregResponseType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="InputId" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="DefaultNameSpace" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="MSSP_TransID" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mregResponses"
})
@XmlRootElement(name = "RegistrationOutput", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
public class RegistrationOutput implements Locatable
{

    @XmlElement(name = "MregResponse", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", type = MregResponseType.class)
    protected List<MregResponseType> mregResponses;
    @XmlAttribute(name = "InputId")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String inputId;
    @XmlAttribute(name = "DefaultNameSpace")
    protected String defaultNameSpace;
    @XmlAttribute(name = "MSSP_TransID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String msspTransID;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the mregResponses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mregResponses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMregResponses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MregResponseType }
     * 
     * 
     */
    public List<MregResponseType> getMregResponses() {
        if (mregResponses == null) {
            mregResponses = new ArrayList<MregResponseType>();
        }
        return this.mregResponses;
    }

    /**
     * Gets the value of the inputId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputId() {
        return inputId;
    }

    /**
     * Sets the value of the inputId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputId(String value) {
        this.inputId = value;
    }

    /**
     * Gets the value of the defaultNameSpace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultNameSpace() {
        return defaultNameSpace;
    }

    /**
     * Sets the value of the defaultNameSpace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultNameSpace(String value) {
        this.defaultNameSpace = value;
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

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
