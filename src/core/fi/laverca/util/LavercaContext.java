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
import java.util.Map;

/**
 * Per-call context of an MSS request: extra HTTP headers to send, and what the call left behind, such as the raw
 * SOAP request and response and the HTTP status of the answer.
 */
public class LavercaContext {

    /** Context key of the raw SOAP request XML (String). */
    public static final String RAW_REQUEST_XML  = "laverca.rawreq";
    /** Context key of the raw SOAP response XML (String), or the HTTP response body if it was no SOAP message. */
    public static final String RAW_RESPONSE_XML = "laverca.rawresp";
    /** Context key of the HTTP status code of the answer (Integer). */
    public static final String HTTP_STATUS_CODE    = "laverca.http.statuscode";
    /** Context key of the HTTP reason phrase of the answer (String). */
    public static final String HTTP_STATUS_MESSAGE = "laverca.http.statusmessage";
    /** Context key of the Content-Type of the answer (String). */
    public static final String HTTP_CONTENT_TYPE   = "laverca.http.contenttype";
    /** Context key of the HTTP headers of the answer (Map of String to String; the last value of a repeated header). */
    public static final String HTTP_RESPONSE_HEADERS = "laverca.http.responseheaders";

    private Map<String, Object> contents = new HashMap<>();
    private Map<String, String> headers  = new HashMap<>();

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
     * <p>
     * "Expect: 100-continue" is not sent as such but turns on HTTP expect-continue handshaking.
     *
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
        return (String)this.get(RAW_REQUEST_XML);
    }
    
    /**
     * Get raw XML response from the context
     * @return raw XML response
     */
    public String getRawXmlResponse() {
        return (String)this.get(RAW_RESPONSE_XML);
    }

    /**
     * Get the HTTP status code of the answer.
     *
     * @return HTTP status code, or null if no answer was received
     */
    public Integer getHttpStatusCode() {
        return (Integer)this.get(HTTP_STATUS_CODE);
    }

}
