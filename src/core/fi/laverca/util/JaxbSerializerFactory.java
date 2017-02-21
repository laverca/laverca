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
