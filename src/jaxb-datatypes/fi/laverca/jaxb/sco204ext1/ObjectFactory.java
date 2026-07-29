
package fi.laverca.jaxb.sco204ext1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fi.laverca.jaxb.sco204ext1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReceiptRequestExtension_QNAME = new QName("http://www.swisscom.ch/TS102204/ext/v1.0.0", "ReceiptRequestExtension");
    private final static QName _ReceiptResponseExtension_QNAME = new QName("http://www.swisscom.ch/TS102204/ext/v1.0.0", "ReceiptResponseExtension");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fi.laverca.jaxb.sco204ext1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReceiptExtensionType }
     * 
     */
    public ReceiptExtensionType createReceiptExtensionType() {
        return new ReceiptExtensionType();
    }

    /**
     * Create an instance of {@link ReceiptProfileType }
     * 
     */
    public ReceiptProfileType createReceiptProfileType() {
        return new ReceiptProfileType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiptExtensionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReceiptExtensionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.swisscom.ch/TS102204/ext/v1.0.0", name = "ReceiptRequestExtension")
    public JAXBElement<ReceiptExtensionType> createReceiptRequestExtension(ReceiptExtensionType value) {
        return new JAXBElement<ReceiptExtensionType>(_ReceiptRequestExtension_QNAME, ReceiptExtensionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiptExtensionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReceiptExtensionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.swisscom.ch/TS102204/ext/v1.0.0", name = "ReceiptResponseExtension")
    public JAXBElement<ReceiptExtensionType> createReceiptResponseExtension(ReceiptExtensionType value) {
        return new JAXBElement<ReceiptExtensionType>(_ReceiptResponseExtension_QNAME, ReceiptExtensionType.class, null, value);
    }

}
