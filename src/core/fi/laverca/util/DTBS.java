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

import java.io.UnsupportedEncodingException;

import org.apache.axis.encoding.Base64;
import org.etsi.uri.TS102204.v1_1_2.DataToBeSigned;

/**
 * Dual-mode mapper class for "DataToBeSigned"
 * This thing would benefit from a near-total rewrite.
 * 
 */
public class DTBS {

    private String encoding = null;
    private String text = null;
    private byte[] data = null;
    private String mimeType = null;
    
    final static public String ENCODING_UTF8 = "UTF-8";
    final static public String ENCODING_BASE64 = "base64";
    
    final static public String MIME_STREAM    = "application/octet-stream";
    final static public String MIME_SHA1      = "application/x-sha1";
    final static public String MIME_SHA256    = "application/x-sha256";
    final static public String MIME_UCS2      = "text/plain;ucs2";
    final static public String MIME_GSM       = "text/plain;gsm";
    final static public String MIME_UTF8      = "text/plain;UTF-8";
    final static public String MIME_TEXTPLAIN = "text/plain";

    
    /**
     * Initialize a DTBS with text.
     * 
     * @param text Text data
     * @param encoding Text encoding
     * @param mimeType Mime-type
     */
    public DTBS(final String text, final String encoding, final String mimeType) {
        this.text     = text;
        this.encoding = encoding;
        this.mimeType = mimeType;
    }
    
    /**
     * Initialize a DTBS with data.
     * 
     * @param data Binary data
     * @param encoding Text encoding
     * @param mimeType Mime-type
     */
    public DTBS(final byte[] data, final String encoding, final String mimeType) {
        this.data = data;
        this.encoding = encoding;
        this.mimeType = mimeType;
    }
    
    /**
     * Initialize a DTBS without a mime type for <code>toBytes()</code>
     * 
     * @param text Text data
     * @param encoding Text encoding
     */
    public DTBS(final String text, final String encoding) {
    	this(text, encoding, null);
    }

    /**
     * Initialize a DTBS without a mime type for <code>toBytes()</code>
     * This defaults encoding to UTF-8.
     * @param text Text data
     */
    public DTBS(final String text) {
    	this(text, ENCODING_UTF8);
    }

    public String  getText() { return this.text; }
    public byte[]  getData() { return this.data; }


    /**
     * Converter of incoming DTBS to byte-array, if the incoming
     * form happened to be a String, otherwise returning it as is.
     * 
     * @return byte-array
     * @throws RunTimeException when no text or data is found
     */
    public byte[] toBytes() {
        if (this.data != null) {
            return data;
        }
        if (this.text != null) {
            try {
                return this.text.getBytes(this.encoding);
            }
            catch (UnsupportedEncodingException uee) {
                throw new RuntimeException(uee);
            }
        }
        throw new RuntimeException("Illegal DTBS");
    }

    /**
     * Retrieve DTBS as a String - if it was a String at creation time.
     * If the DTBS was not a String, return either 
     * "-binary DTBS-" or 
     * "-llegal DTBS-" 
     * depending on whether the DTBS contains binary data.
     * 
     * @return String
     */
    @Override
    public String toString() {
        if (this.text != null) {
            return this.text;
        }
        else if (this.data != null) {
            return "-binary DTBS-";
        }
        else {
            return "-illegal DTBS-";
        }
    }

    /**
     * Convert to a DataToBeSigned object.
     * @return this object as org.etsi.uri.TS102204.v1_1_2.DataToBeSigned
     */
    public DataToBeSigned toDataToBeSigned() {
        DataToBeSigned rv = new DataToBeSigned();
        rv.setEncoding(this.encoding);
        rv.setMimeType(this.mimeType);
        
        if(this.text == null) {
            rv.setContent(Base64.encode(this.data));
        } else {
        	rv.setContent(this.text);
        }
        
        return rv;
    }
    
    /**
     * Length of DTBS data, either the string, or the byte-array
     * 
     * @return int - length of data
     * @throws RunTimeException when no text or data is found
     */
    public int length() {
        if (this.text != null) {
            return this.text.length();
        }
        else if (this.data != null) {
            return this.data.length; 
        }
        else {
            throw new RuntimeException("Illegal DTBS");
        }
    }
}
