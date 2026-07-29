
package fi.laverca.jaxb.soap12env;

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
 * <p>Java class for UpgradeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpgradeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SupportedEnvelope" type="{http://www.w3.org/2003/05/soap-envelope}SupportedEnvType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpgradeType", namespace = "http://www.w3.org/2003/05/soap-envelope", propOrder = {
    "supportedEnvelopes"
})
@XmlRootElement(name = "Upgrade", namespace = "http://www.w3.org/2003/05/soap-envelope")
public class Upgrade
    implements Locatable
{

    @XmlElement(name = "SupportedEnvelope", namespace = "http://www.w3.org/2003/05/soap-envelope", required = true, type = SupportedEnvType.class)
    protected List<SupportedEnvType> supportedEnvelopes;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the supportedEnvelopes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedEnvelopes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedEnvelopes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupportedEnvType }
     * 
     * 
     */
    public List<SupportedEnvType> getSupportedEnvelopes() {
        if (supportedEnvelopes == null) {
            supportedEnvelopes = new ArrayList<SupportedEnvType>();
        }
        return this.supportedEnvelopes;
    }

    public Locator sourceLocation() {
        return locator;
    }

    public void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
