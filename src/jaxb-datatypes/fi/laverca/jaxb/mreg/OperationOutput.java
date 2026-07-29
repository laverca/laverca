
package fi.laverca.jaxb.mreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperationOutputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperationOutputType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.methics.fi/MSSPRegistration/v1.0.0#}OperationBaseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Status" type="{http://www.methics.fi/MSSPRegistration/v1.0.0#}OperationStatusType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationOutputType", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", propOrder = {
    "status"
})
@XmlRootElement(name = "OperationOutput", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
public class OperationOutput
    extends OperationBaseType
{

    @XmlElement(name = "Status", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", required = true)
    protected OperationStatusType status;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link OperationStatusType }
     *     
     */
    public OperationStatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationStatusType }
     *     
     */
    public void setStatus(OperationStatusType value) {
        this.status = value;
    }

}
