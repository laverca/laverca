
package fi.laverca.jaxb.mss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import fi.laverca.jaxb.mcs204ext1.ProfileQueryExtension;
import fi.laverca.jaxb.mreg.RegistrationOutput;
import fi.laverca.jaxb.mssfi.ServiceResponses;
import fi.laverca.jaxb.sco204ext1.ReceiptExtensionType;
import org.w3c.dom.Element;
import org.xml.sax.Locator;


/**
 * <p>Java class for StatusDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusDetailType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element ref="{http://mss.ficom.fi/TS102204/v1.0.0#}ServiceResponses"/&gt;
 *           &lt;element ref="{http://www.swisscom.ch/TS102204/ext/v1.0.0}ReceiptRequestExtension"/&gt;
 *           &lt;element ref="{http://www.swisscom.ch/TS102204/ext/v1.0.0}ReceiptResponseExtension"/&gt;
 *           &lt;element ref="{http://www.methics.fi/TS102204/ext/v1.0.0}ProfileQueryExtension"/&gt;
 *           &lt;element ref="{http://www.methics.fi/MSSPRegistration/v1.0.0#}RegistrationOutput"/&gt;
 *           &lt;any processContents='lax' namespace='##other'/&gt;
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
@XmlType(name = "StatusDetailType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#", propOrder = {
    "aniesAndServiceResponsesAndReceiptRequestExtensions"
})
public class StatusDetailType
    implements Locatable
{

    @XmlElementRefs({
        @XmlElementRef(name = "ServiceResponses", namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", type = ServiceResponses.class, required = false),
        @XmlElementRef(name = "ReceiptRequestExtension", namespace = "http://www.swisscom.ch/TS102204/ext/v1.0.0", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ReceiptResponseExtension", namespace = "http://www.swisscom.ch/TS102204/ext/v1.0.0", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ProfileQueryExtension", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0", type = ProfileQueryExtension.class, required = false),
        @XmlElementRef(name = "RegistrationOutput", namespace = "http://www.methics.fi/MSSPRegistration/v1.0.0#", type = RegistrationOutput.class, required = false)
    })
    @XmlAnyElement
    protected List<Object> aniesAndServiceResponsesAndReceiptRequestExtensions;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the aniesAndServiceResponsesAndReceiptRequestExtensions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aniesAndServiceResponsesAndReceiptRequestExtensions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAniesAndServiceResponsesAndReceiptRequestExtensions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceResponses }
     * {@link JAXBElement }{@code <}{@link ReceiptExtensionType }{@code >}
     * {@link JAXBElement }{@code <}{@link ReceiptExtensionType }{@code >}
     * {@link ProfileQueryExtension }
     * {@link RegistrationOutput }
     * {@link Element }
     * 
     * 
     */
    public List<Object> getAniesAndServiceResponsesAndReceiptRequestExtensions() {
        if (aniesAndServiceResponsesAndReceiptRequestExtensions == null) {
            aniesAndServiceResponsesAndReceiptRequestExtensions = new ArrayList<Object>();
        }
        return this.aniesAndServiceResponsesAndReceiptRequestExtensions;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
