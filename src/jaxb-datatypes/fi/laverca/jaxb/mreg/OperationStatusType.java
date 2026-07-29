
package fi.laverca.jaxb.mreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.xmlschema.Adapter3;
import org.xml.sax.Locator;


/**
 * <p>Java class for OperationStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperationStatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OperationStatusCode" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="OperationStatusMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OperationStatusDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationStatusType", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", propOrder = {
    "operationStatusCode",
    "operationStatusMessage",
    "operationStatusDetail"
})
public class OperationStatusType
    implements Locatable
{

    @XmlElement(name = "OperationStatusCode", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter3 .class)
    @XmlSchemaType(name = "integer")
    protected Long operationStatusCode;
    @XmlElement(name = "OperationStatusMessage", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected String operationStatusMessage;
    @XmlElement(name = "OperationStatusDetail", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected String operationStatusDetail;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the operationStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getOperationStatusCode() {
        return operationStatusCode;
    }

    /**
     * Sets the value of the operationStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationStatusCode(Long value) {
        this.operationStatusCode = value;
    }

    /**
     * Gets the value of the operationStatusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationStatusMessage() {
        return operationStatusMessage;
    }

    /**
     * Sets the value of the operationStatusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationStatusMessage(String value) {
        this.operationStatusMessage = value;
    }

    /**
     * Gets the value of the operationStatusDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationStatusDetail() {
        return operationStatusDetail;
    }

    /**
     * Sets the value of the operationStatusDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationStatusDetail(String value) {
        this.operationStatusDetail = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
