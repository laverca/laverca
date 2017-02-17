//
//  (c) Copyright 2003-2017 Methics Oy. All rights reserved.
//

//package org.apache.axis.encoding.ser.castor;
package fi.laverca.util;

import org.apache.axis.encoding.ser.BaseDeserializerFactory;
import org.apache.axis.encoding.DeserializerFactory;

import javax.xml.namespace.QName;


/**
 * A JaxbDeserializer Factory
 */
@SuppressWarnings("serial")
public class JaxbDeserializerFactory extends BaseDeserializerFactory {

    public JaxbDeserializerFactory(Class<?> javaType, QName xmlType) {
        super(JaxbDeserializer.class, xmlType, javaType);
        // Initialize the JAXBContext map for this java type
        JMarshallerFactory.registerForJAXBContext(javaType);
    }
    public static DeserializerFactory create(Class<?> javaType, QName xmlType) {
        // Initialize the JAXBContext map for this java type
        JMarshallerFactory.registerForJAXBContext(javaType);
        return new JaxbDeserializerFactory(javaType, xmlType);
    }
}
