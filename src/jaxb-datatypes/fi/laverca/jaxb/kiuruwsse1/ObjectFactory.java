
package fi.laverca.jaxb.kiuruwsse1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import fi.laverca.jaxb.wsssecext.UsernameTokenType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fi.laverca.jaxb.kiuruwsse1 package. 
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

    private final static QName _OriginatingPersonToken_QNAME = new QName("http://www.methics.fi/Kiuru-wssecurity/v1.0.0#", "OriginatingPersonToken");
    private final static QName _ClientToken_QNAME = new QName("http://www.methics.fi/Kiuru-wssecurity/v1.0.0#", "ClientToken");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fi.laverca.jaxb.kiuruwsse1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsernameTokenType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsernameTokenType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.methics.fi/Kiuru-wssecurity/v1.0.0#", name = "OriginatingPersonToken")
    public JAXBElement<UsernameTokenType> createOriginatingPersonToken(UsernameTokenType value) {
        return new JAXBElement<UsernameTokenType>(_OriginatingPersonToken_QNAME, UsernameTokenType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsernameTokenType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsernameTokenType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.methics.fi/Kiuru-wssecurity/v1.0.0#", name = "ClientToken")
    public JAXBElement<UsernameTokenType> createClientToken(UsernameTokenType value) {
        return new JAXBElement<UsernameTokenType>(_ClientToken_QNAME, UsernameTokenType.class, null, value);
    }

}
