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

package fi.laverca;

import java.security.cert.X509Certificate;

import fi.laverca.mss.MssException;

/**
 * Wrapper for an MSS Signature
 */
public interface Signature {

    /**
     * Get the raw signature byte[]
     * @return raw signature
     */
    public byte[] getRawSignature();
    
    /**
     * Get the signature as Base64 String
     * @return base64 signature
     */
    public String getBase64Signature();

    /**
     * Get the Signer CommonName if available
     * @return Signer common name
     */
    public String getSignerCn();
    
    /**
     * Get the signer Certificate if available
     * @return Signer certificate
     * @throws MssException if certificate parsing fails
     */
    public X509Certificate getSignerCert() throws MssException;
    
}
