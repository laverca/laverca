
package fi.laverca.jaxb.mssfi;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fi.laverca.jaxb.mssfi package. 
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

    private final static QName _EventID_QNAME = new QName("http://mss.ficom.fi/TS102204/v1.0.0#", "EventID");
    private final static QName _SessionID_QNAME = new QName("http://mss.ficom.fi/TS102204/v1.0.0#", "SessionID");
    private final static QName _UserLang_QNAME = new QName("http://mss.ficom.fi/TS102204/v1.0.0#", "UserLang");
    private final static QName _DisplayName_QNAME = new QName("http://mss.ficom.fi/TS102204/v1.0.0#", "DisplayName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fi.laverca.jaxb.mssfi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceResponses }
     * 
     */
    public ServiceResponses createServiceResponses() {
        return new ServiceResponses();
    }

    /**
     * Create an instance of {@link NoSpamCode }
     * 
     */
    public NoSpamCode createNoSpamCode() {
        return new NoSpamCode();
    }

    /**
     * Create an instance of {@link PostalAddress }
     * 
     */
    public PostalAddress createPostalAddress() {
        return new PostalAddress();
    }

    /**
     * Create an instance of {@link ServiceResponses.ServiceResponse }
     * 
     */
    public ServiceResponses.ServiceResponse createServiceResponsesServiceResponse() {
        return new ServiceResponses.ServiceResponse();
    }

    /**
     * Create an instance of {@link PKCS1 }
     * 
     */
    public PKCS1 createPKCS1() {
        return new PKCS1();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", name = "EventID")
    public JAXBElement<String> createEventID(String value) {
        return new JAXBElement<String>(_EventID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", name = "SessionID")
    public JAXBElement<String> createSessionID(String value) {
        return new JAXBElement<String>(_SessionID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", name = "UserLang")
    public JAXBElement<String> createUserLang(String value) {
        return new JAXBElement<String>(_UserLang_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://mss.ficom.fi/TS102204/v1.0.0#", name = "DisplayName")
    public JAXBElement<String> createDisplayName(String value) {
        return new JAXBElement<String>(_DisplayName_QNAME, String.class, null, value);
    }

}
