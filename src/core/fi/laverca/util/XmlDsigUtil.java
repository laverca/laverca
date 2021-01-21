package fi.laverca.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyException;
import java.security.PublicKey;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fi.laverca.ficom.FiComResponse;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.mss.MSSStatusResp;
import sun.security.x509.X509CertImpl;

public class XmlDsigUtil {

    private static Log log = LogFactory.getLog(XmlDsigUtil.class);

    /**
     * Validate XML signature of a message
     * @param xml String containing the XML document
     * @throws IOException if the parsing of the XML fails
     * @throws ValidationException if the validation failed
     */
    public static void validate(final String xml) throws IOException, ValidationException {

        try {
        
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
    
            // Find Signature
            NodeList nodelist = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
            if (nodelist.getLength() == 0) {
                throw new IllegalArgumentException("Given XML document contains no Signature");
            }
    
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
            DOMValidateContext  ctx = new DOMValidateContext(new SimpleKeySelector(), nodelist.item(0));
    
            // Unmarshal the XMLSignature
            XMLSignature signature = fac.unmarshalXMLSignature(ctx);
    
            // Find 1st Assertion
            Element assertion = (Element) doc.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion").item(0);        
            ctx.setIdAttributeNS(assertion, null, "ID");
            
            // Validate the XMLSignature
            boolean validity = signature.validate(ctx);
    
            // Check validation status
            if (validity == false) {
                log.error("Signature failed core validation");
                boolean sigValidity = signature.getSignatureValue().validate(ctx);
                
                log.info("Signature validation status: " + sigValidity);
                
                List<?> refs = signature.getSignedInfo().getReferences();
                
                for (int i = 0; i < refs.size(); i++) {
                    boolean refValid = ((Reference) refs.get(i)).validate(ctx);
                    log.info("Reference["+i+"] validity status: " + refValid);
                }
                throw new ValidationException("Signature failed core validation");
            } else {
                log.info("Signature passed core validation");
            }
        } catch (UnsupportedEncodingException | SAXException | ParserConfigurationException | MarshalException | XMLSignatureException e) {
            throw new IOException(e);
        }
    }
    
    /**
     * Validate XML signature of a FiComResponse
     * @param resp FiComResponse
     * @throws IOException if the parsing of the XML fails
     * @throws ValidationException if the validation failed
     * @throws JAXBException if the response marshalling failed
     */
    public static void validate(final FiComResponse resp) throws IOException, ValidationException, JAXBException {
        
        if (resp.getMSS_StatusResp() != null) {
            // Asynch client-server
            JAXBContext jc = JAXBContext.newInstance(MSSStatusResp.class);
            Marshaller m = jc.createMarshaller();
            StringWriter writer = new StringWriter();
            m.marshal(resp.getMSS_StatusResp(), writer);
            validate(writer.toString());
        } else if (resp.getMSS_SignatureResp() != null) {
            // Synch
            JAXBContext jc = JAXBContext.newInstance(MSSSignatureResp.class);
            Marshaller m = jc.createMarshaller();
            StringWriter writer = new StringWriter();
            m.marshal(resp.getMSS_SignatureResp(), writer);
            validate(writer.toString());
        }
    }

    
    @SuppressWarnings("serial")
    public static class ValidationException extends Exception {
        
        public ValidationException() {
            super();
        }
        
        public ValidationException(final Exception e) {
            super(e);
        }
        
        public ValidationException(final String s) {
            super(s);
        }
        
    }
    
    
    /**
     * KeySelector for retrieving the PublicKey
     */
    @SuppressWarnings("unchecked")
    private static class SimpleKeySelector extends KeySelector {
        
        public SimpleKeySelector() {}

        @Override
        public KeySelectorResult select(final KeyInfo             keyInfo,
                                        final KeySelector.Purpose purpose,
                                        final AlgorithmMethod     method,
                                        final XMLCryptoContext    context)
            throws KeySelectorException 
        {
            if (keyInfo == null) {
                throw new KeySelectorException("null KeyInfo");
            }
            List<?> list = keyInfo.getContent();

            for (int i = 0; i < list.size(); i++) {
                XMLStructure xmlStructure = (XMLStructure) list.get(i);
                PublicKey pk = null;
                if (xmlStructure instanceof KeyValue) {
                    try {
                        pk = ((KeyValue)xmlStructure).getPublicKey();
                    } catch (KeyException ke) {
                        throw new KeySelectorException(ke);
                    }
                } else if (xmlStructure instanceof X509Data) {
                    List<X509CertImpl> certs = (List<X509CertImpl>) ((X509Data)xmlStructure).getContent();
                    pk = certs.get(0).getPublicKey();
                } else  {
                    log.error(xmlStructure + " not supported");
                    continue;
                }
                return new SimpleKeySelectorResult(pk);
            }
            throw new KeySelectorException("No supported KeyValue element found");
        }
        
    }

    /**
     * Result containing the PublicKey
     */
    private static class SimpleKeySelectorResult implements KeySelectorResult {
        
        private PublicKey publicKey;
        
        public SimpleKeySelectorResult(final PublicKey pk) {
            this.publicKey = pk;
        }

        @Override
        public Key getKey() { 
            return this.publicKey; 
        }
    }

}
