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
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import fi.laverca.jaxb.mcs204ext1.CertificateType;
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
     * If the MSSP supports ProfileQueryExtension, return the Mobile User's nonRepudiation certificate.
     * @return Mobile User certificate chain for nonRepudiation certificate.
     * @deprecated Use {@link #getCertificate(String)} or {@link #getNonRepudiationCertificate()} instead
     */
    @Deprecated
    public X509CertificateChain getCertificates() {
        return this.getNonRepudiationCertificate();
    }
    
    /**
     * If the MSSP supports ProfileQueryExtension, return the Mobile User's nonRepudiation certificate.
     * @return Mobile User certificate chain for nonRepudiation certificate.
     */
    public X509CertificateChain getNonRepudiationCertificate() {
        for (X509CertificateChain chain : this.getAllCertificates()) {
            if (chain.isNonRepudiation()) return chain;
        }
        return new X509CertificateChain();
    }
    
    
    /**
     * If the MSSP supports ProfileQueryExtension, return the Mobile User's digitalSignature certificate.
     * @return Mobile User certificate chain for nonRepudiation certificate.
     */
    public X509CertificateChain getDigitalSignatureCertificate() {
        for (X509CertificateChain chain : this.getAllCertificates()) {
            if (chain.isDigitalSignature()) return chain;
        }
        return new X509CertificateChain();
    }
    
    /**
     * If the MSSP supports ProfileQueryExtension, return all of the the Mobile User's certificates.
     * @return Mobile User certificate chains.
     */
    public List<X509CertificateChain> getAllCertificates() {
        
        List<X509CertificateChain> certs = new ArrayList<>();
        
        if (this.resp             == null) return certs;
        if (this.resp.getStatus() == null) return certs;
        if (this.resp.getStatus().getStatusDetail() == null) return certs;

        ProfileQueryExtension ext = this.getProfileQueryExtension();
        for (CertificateType certType : ext.getMobileUserCertificates()) {
            certs.add(new X509CertificateChain(certType));
        }
        return certs;
    }
    
    /**
     * If the MSSP supports ProfileQueryExtension, return the Mobile User's certificates.
     * @param mssSigProf SignatureProfile the certificate is related to
     * @return Mobile User certificate chains.
     */
    public X509CertificateChain getCertificate(final String mssSigProf) {

        if (this.resp             == null) return new X509CertificateChain();
        if (this.resp.getStatus() == null) return new X509CertificateChain();
        if (this.resp.getStatus().getStatusDetail() == null) return new X509CertificateChain();

        // Filter certs by signature profile
        ProfileQueryExtension    extension = this.getProfileQueryExtension();
        List<X509CertificateChain> certs = extension.getMobileUserCertificates().stream()
                                                                                .map(X509CertificateChain::new)
                                                                                .filter(c -> c.getSignatureProfiles() != null)
                                                                                .filter(c -> c.getSignatureProfiles().contains(mssSigProf))
                                                                                .collect(Collectors.toList());
        
        // Find first acceptable cert
        Predicate<X509CertificateChain> wantedSigningMethod = (c) -> Objects.equals(c.getSigningMethod(), extension.getPreferredSigningMethod());
        return certs.stream()
                    .filter(wantedSigningMethod).findFirst() // find first cert matching our wanted signing method
                    .orElse(certs.stream().findFirst()       // or if none found, find any other cert
                    .orElse(new X509CertificateChain()));    // or if none found, return empty cert chain
    }
    
    /**
     * Get a ProfileQueryExtension from the response
     * @return ProfileQueryExtension or null if not found
     */
    private ProfileQueryExtension getProfileQueryExtension() {
        for (Object o : this.resp.getStatus().getStatusDetail().getAniesAndServiceResponsesAndReceiptRequestExtensions()) {
            if (o instanceof ProfileQueryExtension) {
                return (ProfileQueryExtension)o;
            }
        }
        return null;
    }
        
}
