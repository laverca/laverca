//
//  (c) Copyright 2003-2017 Methics Oy. All rights reserved.
//

package fi.laverca.util;

import javax.xml.bind.MarshalException;
import javax.xml.bind.ValidationException;
import javax.xml.namespace.QName;

import org.apache.axis.AxisFault;
import org.apache.axis.Constants;
import org.apache.axis.MessageContext;
import org.apache.axis.encoding.DeserializationContext;
import org.apache.axis.encoding.DeserializerImpl;
import org.apache.axis.message.MessageElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


/**
 * Jaxb deserializer
 *
 * Functionally, this is similar Jaxb deserializer that is in Axis.
 *
 * We've added the code to handle error conversion to nicer SOAP Faults.
 */
@SuppressWarnings("serial")
public class JaxbDeserializer extends DeserializerImpl {

    protected static final Log log = LogFactory.getLog(JaxbDeserializer.class);

    protected AbstractSoapFaultFactory faultfactory = new DefaultFaultFactory();
    public void setFaultFactory(AbstractSoapFaultFactory ff) {
        this.faultfactory = ff;
    }

    private static class DefaultFaultFactory extends AbstractSoapFaultFactory {
        public DefaultFaultFactory() {
            super(JaxbDeserializer.log);
        }
        @Override
        public String mapSubcodeToReason(final QName subcode) {
            return null;
        }
    }

    
    final public QName xmlType;
    final public Class<?> javaType;

    public JaxbDeserializer(Class<?> javaType, QName xmlType) {
        this.xmlType = xmlType;
        this.javaType = javaType;
    }

    /**
     * Return something even if no characters were found.
     */
    @Override
    public void onEndElement( final String namespace,
                              final String localName,
                              final DeserializationContext context )
        throws SAXException
    {
        final boolean trace = log.isTraceEnabled();
        if (trace) {
            log.trace("onEndElement ns={"+namespace+ "}"+localName+" javatype="+this.javaType);
        }
        final MessageContext messageContext = MessageContext.getCurrentContext();
        try {
            final MessageElement msgElem = context.getCurElement();
            if (msgElem != null) {

                // Default case: Original MessageElement data as is.
                final Element elt = msgElem; // msgElem.getAsDOM();

                // Unmarshal the nested XML element into a JAXB object of type 'javaType'
                this.value = JMarshallerFactory.unmarshal( this.javaType, elt ); 

                if (trace) {
                    log.trace("Unmarshal result = "+this.value);
                }
            }
        } catch (final MarshalException | ValidationException | IllegalArgumentException e) {
            log.debug("Unable to unmarshal from XML to Jaxb Object (localName="+localName+
                      ", javatype="+this.javaType+"): "+e.getMessage());
            log.trace(e);
            
            if (messageContext != null) { // TODO: What's this?!?
                messageContext.setProperty("DESERIALIZATION_ERROR", e.getLocalizedMessage());
            }

            final String detailStr = this.getDetail(e);
            String label = "";
            
            if (detailStr != null) {
                if ((detailStr.indexOf("missing, required") > 0) ||
                    (detailStr.indexOf("is a required field") > 0) ||
                    (detailStr.indexOf("are required for class") > 0)) {
                    
                    label = "MISSING_PARAM";
                }
            }
            
            final AxisFault af =
                this.faultfactory
                .createFault(label,
                             Constants.FAULT_SOAP12_SENDER,
                             null, // subcode
                             null, // subsubcode
                             null, // reason
                             null, // node
                             null, // role
                             detailStr);
            throw new SAXException("Unable to unmarshal from XML to Jaxb Object: "
                                   + detailStr, af);
        } catch (Exception e) {
            log.debug("Unable to unmarshal from XML to Jaxb Object (localName="+localName+", javatype="+this.javaType+")");
            log.trace(e);
            
            if (messageContext != null)
                messageContext.setProperty("DESERIALIZATION_ERROR", e.getLocalizedMessage());
            throw new SAXException("Unmarshal from XML to Jaxb Object"
                                   + e.getLocalizedMessage(), e);
        } catch (Throwable t) {
            log.error("Unexpected throwable");
            log.trace(t);
            
            SAXException se = new SAXException("Unexpected throwable");
            se.initCause(t);
            throw se;
        }
    }
    
    /**
     * Attempt to get the most informative detail from the given exception
     * @param e Exception to dig
     * @return Detail String
     */
    protected String getDetail(final Exception e) {
        
        if (e == null) return null;
        
        if (e instanceof ValidationException || e instanceof MarshalException) {
            
            Throwable t = e.getCause();
            
            if (t != null) {
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                return t.getMessage();
            }
        }
        
        if (e instanceof IllegalArgumentException) {
            return "Unable to parse XML: " + e.getMessage();
        }
        
        return e.getMessage();
    }
}
