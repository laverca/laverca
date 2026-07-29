
package fi.laverca.jaxb.mcs204ext1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.w3c.dom.Element;
import org.xml.sax.Locator;


/**
 * <p>Java class for SscdListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SscdListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Sim" type="{http://www.methics.fi/TS102204/ext/v1.0.0}SscdType" minOccurs="0"/&gt;
 *         &lt;element name="App" type="{http://www.methics.fi/TS102204/ext/v1.0.0}SscdType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SscdListType", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0", propOrder = {
    "sim",
    "apps",
    "anies"
})
public class SscdListType
    implements Locatable
{

    @XmlElement(name = "Sim", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0")
    protected SscdType sim;
    @XmlElement(name = "App", namespace = "http://www.methics.fi/TS102204/ext/v1.0.0", type = SscdType.class)
    protected List<SscdType> apps;
    @XmlAnyElement
    protected List<Element> anies;
    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * Gets the value of the sim property.
     * 
     * @return
     *     possible object is
     *     {@link SscdType }
     *     
     */
    public SscdType getSim() {
        return sim;
    }

    /**
     * Sets the value of the sim property.
     * 
     * @param value
     *     allowed object is
     *     {@link SscdType }
     *     
     */
    public void setSim(SscdType value) {
        this.sim = value;
    }

    /**
     * Gets the value of the apps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SscdType }
     * 
     * 
     */
    public List<SscdType> getApps() {
        if (apps == null) {
            apps = new ArrayList<SscdType>();
        }
        return this.apps;
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
