//
//  (c) Copyright 2003-2017 Methics Technologies Oy. All rights reserved. 
//

package fi.laverca.mss;

import org.w3c.dom.Element;

import fi.laverca.jaxb.mssfi.ServiceResponses.ServiceResponse;

/**
 * Wrapper class for FiCom {@link ServiceResponse}s
 */
public class AdditionalServiceResponse {

    private String          asUri;
    private ServiceResponse ficomServiceResp;
    
    /**
     * Create an {@link AdditionalServiceResponse} from a FiCom {@link ServiceResponse}
     * 
     * @param ficomServiceResp FiCom AdditionalService wrapper (not-null)
     */
    public AdditionalServiceResponse(final ServiceResponse ficomServiceResp) {
        this.ficomServiceResp = ficomServiceResp;
        
        if (ficomServiceResp.getDescription() != null) {
            this.asUri = ficomServiceResp.getDescription().getMssURI();
        }
    }
    
    /**
     * Get the URI of the AdditionalService ("Description")
     * @return {@link String} representation of the Description URI (may be null)
     */
    public String getDescription() {
        return this.asUri;
    }
    
    /**
     * Get a String representation of the AdditionalService 
     * @return String response or null if this AS does not have a response.
     */
    public String getResponse() {
        try {
            return this.ficomServiceResp.getAnies().stream()
                                                   .findFirst()
                                                   .get()
                                                   .getTextContent();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Get the content of an XML element within the AdditionalServiceResponse
     * @param tagName Name of the XML element
     * @return Contents of the element as a String, or null if nothing matching found
     */
    public String getContent(final String tagName) {
        for (Element e : this.ficomServiceResp.getAnies()) {
            if (e == null) continue;
            if (e.getTagName()     == null) continue;
            if (e.getTextContent() == null) continue;
            if (e.getTagName().endsWith(tagName)) {
                return e.getTextContent();
            }
        }
        return null;
    }
    
    /**
     * Get the raw {@link ServiceResponse} XML object
     * @return {@link ServiceResponse}
     */
    public ServiceResponse getServiceResponse() {
        return this.ficomServiceResp;
    }
    
}
