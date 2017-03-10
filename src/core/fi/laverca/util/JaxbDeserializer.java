/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2015 Laverca Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package fi.laverca.util;

import javax.xml.bind.MarshalException;
import javax.xml.bind.ValidationException;
import javax.xml.namespace.QName;

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
        if (log.isTraceEnabled()) {
            log.trace("onEndElement ns={" + namespace + "}" + localName + " javatype=" + this.javaType);
        }
        try {
            final MessageElement msgElem = context.getCurElement();
            if (msgElem != null) {

                // Default case: Original MessageElement data as is.
                final Element elt = msgElem; // msgElem.getAsDOM();

                // Unmarshal the nested XML element into a JAXB object of type 'javaType'
                this.value = JMarshallerFactory.unmarshal( this.javaType, elt ); 

                if (log.isTraceEnabled()) {
                    log.trace("Unmarshal result = "+this.value);
                }
            }
        } catch (final MarshalException | ValidationException | IllegalArgumentException e) {
            log.debug("Unable to unmarshal from XML to Jaxb Object (localName=" + localName + ", javatype="+this.javaType + "): " + e.getMessage());
            log.trace(e);

            final String detailStr = this.getDetail(e);

            throw new SAXException("Unable to unmarshal from XML to Jaxb Object: " + detailStr);
        } catch (Exception e) {
            log.debug("Unable to unmarshal from XML to Jaxb Object (localName=" + localName + ", javatype=" + this.javaType + ")");
            log.trace(e);
            throw new SAXException("Unmarshal from XML to Jaxb Object" + e.getLocalizedMessage(), e);
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
