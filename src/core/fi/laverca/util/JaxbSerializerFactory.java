//
//  (c) Copyright 2003-2017 Methics Oy. All rights reserved.
//

//package org.apache.axis.encoding.ser.castor;
package fi.laverca.util;

import org.apache.axis.encoding.ser.BaseSerializerFactory;
import org.apache.axis.encoding.SerializerFactory;

import javax.xml.namespace.QName;

/**
 * SerializerFactory for Jaxb objects
 *
 * Use our own serializer instead of the Axis version.
 */
@SuppressWarnings("serial")
public class JaxbSerializerFactory extends BaseSerializerFactory {
    public JaxbSerializerFactory(Class<?> javaType, QName xmlType) {
        super(JaxbSerializer.class, xmlType, javaType);
        // Initialize the JAXBContext map for this java type
        JMarshallerFactory.registerForJAXBContext(javaType);
    }
    public static SerializerFactory create(Class<?> javaType, QName xmlType) {
        // Initialize the JAXBContext map for this java type
        JMarshallerFactory.registerForJAXBContext(javaType);
        return new JaxbSerializerFactory(javaType, xmlType);
    }
}
