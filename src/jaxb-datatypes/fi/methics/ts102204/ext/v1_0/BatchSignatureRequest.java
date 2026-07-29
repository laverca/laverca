
package fi.methics.ts102204.ext.v1_0;

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
import fi.laverca.jaxb.mss.DataType;
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
    "dataToBeSigned",
    "dataToBeDisplayed",
    "anies"
})
@XmlRootElement(name = "BatchSignatureRequest", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#BatchSignature")
public class BatchSignatureRequest implements Locatable
{

    @XmlElement(name = "DataToBeSigned", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#BatchSignature", required = true)
    protected DataType dataToBeSigned;
    @XmlElement(name = "DataToBeDisplayed", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0#BatchSignature")
    protected DataType dataToBeDisplayed;
    @XmlAnyElement
    protected List<Element> anies;
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
