
package fi.laverca.jaxb.mreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;


/**
 * <p>Java class for MregResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MregResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element ref="{http://www.methics.fi/MSSPRegistration/v1.0.0#}OperationOutput"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MregResponseType", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", propOrder = {
    "operationOutput"
})
public class MregResponseType
    implements Locatable
{

    @XmlElement(name = "OperationOutput", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#")
    protected OperationOutput operationOutput;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the operationOutput property.
     * 
     * @return
     *     possible object is
     *     {@link OperationOutput }
     *     
     */
    public OperationOutput getOperationOutput() {
        return operationOutput;
    }

    /**
     * Sets the value of the operationOutput property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationOutput }
     *     
     */
    public void setOperationOutput(OperationOutput value) {
        this.operationOutput = value;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
