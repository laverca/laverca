//
//  (c) Copyright 2003-2008 Methics Oy. All rights reserved. 
//
package fi.laverca;

import java.io.UnsupportedEncodingException;

import org.apache.axis.encoding.Base64;
import org.etsi.uri.TS102204.v1_1_2.DataToBeSigned;

/**
 *  Dual-mode mapper class for "DataToBeSigned"
 * This thing would benefit from a near-total rewrite.
 */
public class DTBS {

    String encoding = null;
    String text = null;
    byte[] data = null;
    String mimeType = null;
    
    final static public String MIME_STREAM = "application/octet-stream";
    final static public String MIME_SHA1 = "application/x-sha1";
    final static public String MIME_SHA256 = "application/x-sha256";
    final static public String MIME_UCS2 = "text/plain;ucs2";
    final static public String MIME_GSM = "text/plain;gsm";
    final static public String MIME_UTF8 = "text/plain;UTF-8";

    /*
    public DTBS(String newText) {
        this(newText, "UTF-8");
    }
    */

    /**
     * Alternate constructor, incoming data is String with encoding code value.
     *
     * @param newText
     * @param newEncoding
     */
    public DTBS( final String newText, final String newEncoding )
    {
        this.text     = newText;
        this.encoding = newEncoding;
    }

    /**
     * Alternate constructor, incoming data is byte-array
     * @param newData
     */
    public DTBS( final byte[] newData )
    {
        this.data = newData;
        this.mimeType = MIME_STREAM;
    }
    
    /**
     * Alternate constructor, incoming data is byte-array
     * @param newData
     * @param mimeType
     */
    public DTBS( final byte[] newData, final String mimeType )
    {
        this.data = newData;
        this.mimeType = mimeType;
    }

    public String  getText() { return this.text; }
    public byte[]  getData() { return this.data; }


    /**
     * Converter of incoming DTBS to byte-array, if the incoming
     * form happened to be a String, otherwise returning it as is.
     * 
     * @return byte-array
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
     * Retrieve DTBS as a string - if it was a String at creation time.
     * 
     * @return String
     */
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

    public DataToBeSigned toDataToBeSigned() {
        DataToBeSigned rv = new DataToBeSigned();
        rv.setContent(this.text); //TODO: data
        
        if(this.text == null) {
            rv.setContent(Base64.encode(this.data));
            rv.setEncoding("base64");
            rv.setMimeType(this.mimeType);
        }
        
        return rv;
    }
    
    /**
     * Length of DTBS data, either the string, or the byte-array
     * 
     * @return int - length of data
     */
    public int length() {
        if (this.text != null) {
	    return this.text.length();
        }
        else if (this.data != null) {
            return this.data.length; 
        }
        else {
            throw new RuntimeException("illegal dtbs");
        }
    }
}
