/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2018 Laverca Project
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

package fi.laverca.mss;

import java.util.ArrayList;
import java.util.List;

import fi.laverca.jaxb.mcs204ext1.ProfileQueryExtension;
import fi.laverca.jaxb.mss.MSSProfileResp;
import fi.laverca.jaxb.mss.MssURIType;
import fi.laverca.util.X509CertificateChain;

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
     * @deprecated Use {@link #getCertificate(String)} instead
     */
    @Deprecated
    public X509CertificateChain getCertificates() {
        
        X509CertificateChain certs = new X509CertificateChain();
        
        if (this.resp             == null) return certs;
        if (this.resp.getStatus() == null) return certs;
        if (this.resp.getStatus().getStatusDetail() == null) return certs;

        for (Object o : this.resp.getStatus().getStatusDetail().getAniesAndServiceResponsesAndReceiptRequestExtensions()) {
            if (o instanceof ProfileQueryExtension) {
                ProfileQueryExtension ext = (ProfileQueryExtension)o;
                certs = new X509CertificateChain(ext.getMobileUserCertificates());
                if (certs.isNonRepudiation()) {
                    // Found nonRepudiation
                    return certs;
                }
            }
        }
        
        return certs;
    }
    
    /**
     * If the MSSP supports ProfileQueryExtension,
     * return the Mobile User's certificates.
     * @param mssSigProf SignatureProfile the certificate is related to
     * @return Mobile User certificates in X509Data elements. Each element contains a whole certificate chain.
     */
    public X509CertificateChain getCertificate(String mssSigProf) {

        if (this.resp             == null) return new X509CertificateChain();
        if (this.resp.getStatus() == null) return new X509CertificateChain();
        if (this.resp.getStatus().getStatusDetail() == null) return new X509CertificateChain();

        for (Object o : this.resp.getStatus().getStatusDetail().getAniesAndServiceResponsesAndReceiptRequestExtensions()) {
            if (o instanceof ProfileQueryExtension) {
                ProfileQueryExtension ext = (ProfileQueryExtension)o;
                X509CertificateChain cert = new X509CertificateChain(ext.getMobileUserCertificates());
                if (cert.getSignatureProfiles() != null && cert.getSignatureProfiles().contains(mssSigProf)) {
                    return cert;
                }
            }
        }
        return new X509CertificateChain();
    }
        
}
