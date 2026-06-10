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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.axis.MessageContext;

/**
 * A wrapper container for passing stuff around (similar to Axis MessageContext)
 */
public class LavercaContext {

    private Map<String, Object> contents = new HashMap<>();
    private Map<String, String> headers  = new HashMap<>();
    
    public void setMessageContext(final MessageContext messageContext) {
        Iterator<String> iter = messageContext.getPropertyNames();

        while (iter.hasNext()) {
            String s = (String) iter.next();
            this.put(s, messageContext.getProperty(s));
        }
    }

    /**
     * Put a generic key to the context
     * @param key   Key
     * @param value Value
     */
    public void put(final String key, final Object value) {
        this.contents.put(key, value);
    }
    
    /**
     * Get a generic value from the context
     * @param key Key
     * @return value
     */
    public Object get(final String key) {
        return this.contents.get(key);
    }
    
    /**
     * Put a new header to the context. This shall be added to the SOAP request as HTTP header.
     * @param name  Header name
     * @param value Header value
     */
    public void putHeader(final String name, final String value) {
        this.headers.put(name, value);
    }
    
    /**
     * Get the raw map of HTTP headers
     * @return HTTP headers
     */
    public Map<String, String> getHeaders() {
        return this.headers;
    }
    
    /**
     * Get raw XML request from the context
     * @return XML request
     */
    public String getRawXmlRequest() {
        return (String)this.get(ComponentsHTTPSender.RAW_REQUEST_XML);
    }
    
    /**
     * Set raw XML response to the context
     * @return raw XML response
     */
    public String getRawXmlResponse() {
        return (String)this.get(ComponentsHTTPSender.RAW_RESPONSE_XML);
    }
    
}
