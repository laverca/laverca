//
//  (c) Copyright 2003-2017 Methics Technologies Oy. All rights reserved. 
//

package fi.laverca.mss;

import java.util.ArrayList;
import java.util.List;

import fi.laverca.jaxb.mcs204ext1.ProfileQueryExtension;
import fi.laverca.jaxb.mss.MSSProfileResp;
import fi.laverca.jaxb.mss.MssURIType;
import fi.laverca.jaxb.xmldsigcore.X509Data;

/**
 * Wrapper class for Profile Query Response
 */
public class ProfileQueryResponse {

    public MSSProfileResp resp;
    
    public ProfileQueryResponse(final MSSProfileResp resp) {
        this.resp = resp;
    }
    
    /**
     * Return the Mobile User's Signature Profiles
     * @return Signature Profiles as a String list
     */
    public List<String> getSignatureProfiles() {
        
        List<String> profiles = new ArrayList<>();
        if (this.resp == null) return profiles;

        for (MssURIType prof : this.resp.getSignatureProfiles()) {
            profiles.add(prof.getMssURI());
        }
        
        return profiles;
    }
    
    /**
     * If the MSSP supports ProfileQueryExtension,
     * return the Mobile User's certificates.
     * @return Mobile User certificates in X509Data elements. Each element contains a whole certificate chain.
     */
    public List<X509Data> getCertificates() {
        
        List<X509Data> certs = new ArrayList<>();
        
        if (this.resp             == null) return certs;
        if (this.resp.getStatus() == null) return certs;
        if (this.resp.getStatus().getStatusDetail() == null) return certs;

        for (Object o : this.resp.getStatus().getStatusDetail().getAniesAndServiceResponsesAndReceiptRequestExtensions()) {
            if (o instanceof ProfileQueryExtension) {
                ProfileQueryExtension ext = (ProfileQueryExtension)o;
                return ext.getMobileUserCertificates();
            }
        }
        
        return certs;
    }
    
}
