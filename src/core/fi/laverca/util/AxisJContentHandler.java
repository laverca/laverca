//
//  (c) Copyright 2003-2017 Methics Oy. All rights reserved.
//

package fi.laverca.util;

import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.SerializationContext;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * This ContentHandler delegates all serialization to an Axis SerializationContext
 * <p>
 * This is interface model compatible with SerializationContext methods.
 */
public class AxisJContentHandler extends AxisContentHandler {

    /**
     * Creates a contentHandler delegate
     *
     * @param context : axis context to delegate to
     */
    public AxisJContentHandler(final SerializationContext context) {
        super(context);
    }
    
    /**
     * delegates to the serialization context
     */
    @Override
    public void startElement( final String uri,
                              final String localName,
                              final String prefixedName,
                              final Attributes attributes )
        throws SAXException
    {
        try {
            if (log.isTraceEnabled()) {
                log.trace("startElement " + super.elt2string(uri, localName, prefixedName, attributes)); //, new Throwable());
            }

            int pfixlen = prefixedName.indexOf(':');
            if (pfixlen >= 0 && (!prefixedName.startsWith("ns"))) {

                // Use prefix that JAXB fed to us.
                
                final String pfix = prefixedName.substring(0, pfixlen);
                this.context.startElement(new QName(uri, localName, pfix), attributes);
                
            } else {
                
                // Resolve prefix from Axis SerializationContext register of prefixes (or get dynamic NS*)
                final String pfix = super.context.getPrefixForURI(uri);
                if (log.isTraceEnabled()) {
                    log.trace("  startElement pfix=\"" + pfix + "\"");
                }

                if (pfix == null || "".equals(pfix)) {
                    this.context.startElement(new QName(uri, localName), attributes);
                    if (log.isTraceEnabled()) {
                        log.trace("  --> startElement " + localName);
                    }
                
                } else {
                    this.context.startElement(new QName(uri, localName, pfix), attributes);
                    if (log.isTraceEnabled()) {
                        log.trace("  --> startElement " + pfix+":"+localName);
                    }
                }
            }

        } catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }


    /**
     * delegates to the serialization context
     */
    @Override
    public void endElement( final String uri,
                            final String localName,
                            final String prefixedName )
            throws SAXException
    {
        if (log.isTraceEnabled()) {
            log.trace("endElement "+ prefixedName);
        }

        try {
            this.context.endElement();
        } catch (IOException ioe) {
            throw new SAXException(ioe);
        }
    }

    /**
     * delegates to the serialization context
     */
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
