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

import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.axis.MessageContext;
import org.apache.axis.encoding.SerializationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This ContentHandler delegates all serialization to an Axis SerializationContext
 * <p>
 * This is interface model compatible with SerializationContext methods.
 */
public class AxisContentHandler extends DefaultHandler {

    protected static final Log log = LogFactory.getLog(AxisContentHandler.class);

    // For passing the server request side context data to server response
    private static final ThreadLocal<MessageContext> tlCtx = new ThreadLocal<MessageContext>();

    public static MessageContext getMessageContext() {
        return AxisContentHandler.tlCtx.get();
    }

    public static void setMessageContext(final MessageContext ctx) {
        AxisContentHandler.tlCtx.set(ctx);
    }

    protected String elt2string( final String uri,
                                 final String localName,
                                 final String prefixedName,
                                 final Attributes ai )
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(prefixedName);
        sb.append("  {");
        sb.append(uri);
        sb.append("}");
        sb.append(localName);
        sb.append("  Attrs:");
        for (int i = 0; i < ai.getLength(); ++i) {
            sb.append("  ");
            sb.append(ai.getQName(i));
            sb.append("=\"");
            sb.append(ai.getValue(i));
            sb.append("\"");
        }
        return sb.toString();
    }

    /**
     * Serialization context to delegate to
     */
    protected SerializationContext context;

    protected MessageContext msgContext;
    protected boolean isClient;

    /**
     * Creates a contentHandler delegate
     *
     * @param context : axis context to delegate to
     */
    public AxisContentHandler(SerializationContext context) {
        super();
        this.msgContext = MessageContext.getCurrentContext();
        this.setContext(context);
        this.isClient = this.msgContext != null ? this.msgContext.isClient() : false;
        if (this.isClient) {
            log.trace(" .. msgContext[C]  = "+this.msgContext);
        } else {
            // Must get saved messageContext from server input time processing side
            // via ThreadLocal magic...
            this.msgContext = AxisContentHandler.getMessageContext();
            log.trace(" .. msgContext[S]  = "+this.msgContext);
        }
    }

    /**
     * Getter for property context.
     *
     * @return Value of property context.
     */
    public SerializationContext getContext() {
        return this.context;
    }

    /**
     * Setter for property context.
     *
     * @param context New value of property context.
     */
    public void setContext(SerializationContext context) {
        this.context = context;
    }

    @Override
    public void startElement( final String uri,
                              final String localName,
                              final String prefixedName,
                              final Attributes attributes )
        throws SAXException
    {
        final boolean trace = log.isTraceEnabled();
        try {
            if (trace) {
                log.trace("startElement " + elt2string(uri, localName, prefixedName, attributes));
            }

            int pfixlen = prefixedName.indexOf(':');
            if (pfixlen >= 0 && (!prefixedName.startsWith("ns"))) {

                // Use prefix that Castor fed to us.
                
                final String pfix = prefixedName.substring(0, pfixlen);
                this.context.startElement(new QName(uri, localName, pfix), attributes);
                
            } else {

                // Resolve prefix from Axis SerializationContext register of prefixes (or get dynamic NS*)
                final String pfix = this.context.getPrefixForURI(uri);
                if (trace) {
                    log.trace("  startElement pfix=\"" + pfix + "\"");
                }

                if (pfix == null || "".equals(pfix)) {
                    if (trace) {
                        log.trace("  --> startElement " + localName);
                    }
                    this.context.startElement(new QName(uri, localName), attributes);

                } else {
                    if (trace) {
                        log.trace("  --> startElement " + pfix+":"+localName);
                    }
                    this.context.startElement(new QName(uri, localName, pfix), attributes);
                }
            }

        } catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException
    {
        if (log.isTraceEnabled()) {
            log.trace("endElement "+ localName);
        }

        try {
            this.context.endElement();
        } catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }

    @Override
    public void startPrefixMapping (String prefix, String uri)
        throws SAXException
    {
        if (log.isTraceEnabled()) {
            log.trace("  startPrefixMapping '"+prefix+"' = "+uri);
        }
    }

    @Override
    public void endPrefixMapping (String prefix)
        throws SAXException
    {
        // no op
        if (log.isTraceEnabled()) {
            log.trace("  endPrefixMapping '"+prefix+"'");
        }        
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws org.xml.sax.SAXException
    {
        try {
            this.context.writeChars(ch, start, length);
        } catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }
}
