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

import org.bouncycastle.util.encoders.Base64;


/** 
 * A MobileConnect signature wrapper.
 */ 
public class MobileConnectSignature implements Signature {
    
    private byte[] signature;

	/** 
     * @param bytes Signature byte[]
     * @throws IllegalArgumentException if the input is null
     */
    public MobileConnectSignature(final byte[] bytes) throws IllegalArgumentException {
    	
        if (bytes == null) {
            throw new IllegalArgumentException("Can't create MobileConnect Signature from null input.");
        }
        this.signature = bytes;
    }

    @Override
    public X509Certificate getSignerCert() {
        // MobileConnect does not use a CA
    	return null;
    }

    @Override
    public String getSignerCn() {
        // MobileConnect does not use a CA
        return null;
    }

    /**
     * Get PKCS1 raw signature.
     */
    @Override
    public byte[] getRawSignature() {
        return this.signature;
    }

    @Override
    public String getBase64Signature() {
        return Base64.toBase64String(this.getRawSignature());
    }
    
}
