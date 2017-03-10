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

//package org.apache.axis.encoding.ser.castor;
package fi.laverca.util;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.ValidationException;
import javax.xml.namespace.QName;

import org.apache.axis.Constants;
import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.wsdl.fromJava.Types;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;

/**
 * Jaxb serializer
 * This is a placeholder for interoperability changes.
 * So far, everything we do differently is in AxisContentHandler.
 *
 * On serialize we MAY at times choose to serialize original XML
 * request (or response) from DOM data instead of Jaxb object.
 *
 */
@SuppressWarnings("serial")
public class JaxbSerializer implements Serializer {

    protected static Log log = LogFactory.getLog(JaxbSerializer.class);

    /**
     * Serialize a Jaxb object.
     *
     * @param value      this must be a Jaxb object for marshalling
     * @throws IOException for XML schema noncompliance, bad object type, and any IO
     *                     trouble.
     */
    @Override
    public void serialize(final QName name,
                          final Attributes attributes,
                          final Object value,
                          final SerializationContext context)
            throws IOException
    {
        final boolean trace = log.isTraceEnabled();
        try {
            if (trace) {
                log.trace("JaxbSerializer.serialize() called name="+name+" attributes="+attributes+" value="+value);
            }

            // If DOM serialization was not done, fall back to original Jaxb serialize
            log.trace("No DOM serialize, using our JaxbSerializer.");

            // Normal Jaxb type serialization
            final AxisJContentHandler hand = new AxisJContentHandler(context);
            JMarshallerFactory.marshal(value, hand);

        } catch (final MarshalException e) {
            log.error("Unable to marshall between XML and Jaxb Objects:",e);
            throw new IOException("Unable to marshall between XML and Jaxb Objects: "
                                  + e.getMessage());
        } catch (final ValidationException e) {
            log.error("Message does not comply with the associated XML schema:", e);
            throw new IOException("Message does not comply with the associated XML schema: "
                                  + e.getMessage());
        } catch (JAXBException e) {
            log.error("Message does not comply with the associated XML schema:", e);
            throw new IOException("Message does not comply with the associated XML schema: "
                                   + e.getMessage());
        } finally {
            if (trace)
                log.trace("serialize() done. name="+name);
        }
    }

    @Override
    public String getMechanismType() {
        return Constants.AXIS_SAX;
    }

    /**
     * Return XML schema for the specified type, suitable for insertion into
     * the &lt;types&gt; element of a WSDL document, or underneath an
     * &lt;element&gt; or &lt;attribute&gt; declaration.
     *
     * @param javaType the Java Class we're writing out schema for
     * @param types    the Java2WSDL Types object which holds the context
     *                 for the WSDL being generated.
     * @return a type element containing a schema simpleType/complexType
     * @see org.apache.axis.wsdl.fromJava.Types
     */
    @Override
    @SuppressWarnings("rawtypes")
    public Element writeSchema(final Class javaType, final Types types)
        throws Exception
    {
        return null;
    }
}
