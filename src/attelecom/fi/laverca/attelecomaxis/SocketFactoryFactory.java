/*
 * Copyright 2002-2004 The Apache Software Foundation.
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



/*
 * This file modified from its original form by Richard Unger, Telekom Austria
 * 
 * Modifications are intended to make the SocketFactoryFactory consider that for
 * SSL, different client certificates might be used at runtime.
 * Currently, the SocketFactory of a given type is initialized only once. The parameters
 * for SSL are set during initialization. Thus without this modification, there
 * is no way to change the SSL parameters at runtime.
 * 
 * The modification considers the SSL parameters requested, and creates a new factory if the
 * params are different.
 * 
 */

package org.apache.axis.components.net;

import org.apache.axis.AxisProperties;
import org.apache.axis.components.logger.LogFactory;
import org.apache.commons.logging.Log;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Class SocketFactoryFactory
 *
 * @author
 * @version %I%, %G%
 */
public class SocketFactoryFactory {

    /** Field log **/
    protected static Log log = LogFactory.getLog(SocketFactoryFactory.class.getName());

    /** socket factory **/
    private static HashMap<String,SocketFactory> factories = new HashMap<String,SocketFactory>();
    private static HashMap<String,SocketFactory> sslFactories = new HashMap<String,SocketFactory>();

    
    private static final Class classes[] = new Class[] { Hashtable.class };


    static {
        AxisProperties.setClassOverrideProperty(SocketFactory.class,
                                       "axis.socketFactory");

        AxisProperties.setClassDefault(SocketFactory.class,
                                       "org.apache.axis.components.net.DefaultSocketFactory");

        AxisProperties.setClassOverrideProperty(SecureSocketFactory.class,
                                       "axis.socketSecureFactory");

        AxisProperties.setClassDefault(SecureSocketFactory.class,
                                       "org.apache.axis.components.net.JSSESocketFactory");
    }
    
    /**
     * Returns a copy of the environment's default socket factory.
     * 
     * @param protocol Today this only supports "http" & "https".
     * @param attributes
     *
     * @return
     */
    public static synchronized SocketFactory getFactory(String protocol, Hashtable attributes) {
        SocketFactory theFactory = null;
        String sslAttributeString = null;
        
        log.debug("Getting socketfactory for protocol: "+protocol);
        
        if (protocol.equalsIgnoreCase("https")) {
        	sslAttributeString = attributes.get("keystore") + ";;" + (String)attributes.get("truststore");
        	theFactory = sslFactories.get(sslAttributeString);
        }
        else
        	theFactory = factories.get(protocol.toLowerCase());
        
        
        if (theFactory == null) {
            Object objects[] = new Object[] { attributes };
    
            log.debug("New socketfactory created for protocol: "+protocol);
            
            if (protocol.equalsIgnoreCase("http")) {
                theFactory = (SocketFactory) AxisProperties.newInstance(SocketFactory.class, classes, objects);
                if (theFactory!=null) factories.put("http", theFactory);
            }
            else if (protocol.equalsIgnoreCase("https")){
                theFactory = (SecureSocketFactory) AxisProperties.newInstance(SecureSocketFactory.class, classes, objects);
                if (theFactory!=null) sslFactories.put(sslAttributeString, theFactory);
            }
            else
            	throw new IllegalArgumentException("Unknown Protocol type: "+protocol);
        }
        
        return theFactory;
    }
}