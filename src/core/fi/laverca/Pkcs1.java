/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2017 Laverca Project
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
import java.util.List;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;

import fi.laverca.jaxb.mssfi.PKCS1;
import fi.laverca.util.X509Util;


/** 
 * A FiCom PKCS1 signature wrapper.
 */ 
public class Pkcs1 implements Signature {
    private static final Log log = LogFactory.getLog(Pkcs1.class);

    private PKCS1 pkcs1;

	/** 
     * @param pkcs1 In general, you get this from an MSS_SignatureResp.getMSS_Signature() call.
     * @throws IllegalArgumentException if the input is null
     */
    public Pkcs1(final PKCS1 pkcs1) throws IllegalArgumentException {
    	
        if (pkcs1 == null) {
            throw new IllegalArgumentException("Can't construct a PKCS1 SignedData element from null input.");
        }
        this.pkcs1 = pkcs1;
    }
    
    /**
     * Get the MSS Signature value
     * @return MSS Signature as a String
     * @deprecated Use {@link #getBase64Signature()} instead
     */
    @Deprecated
    public String getMssSignatureValue() {
        return this.getBase64Signature();
    }

    /**
     * Look up the Certificate of the signer of this signature. 
     * <p>Note that this only looks up the first signer. In MSSP signatures,
     * there is only one, but in a general Pkcs1 case, there can be several.
     * 
     * @return Signer certificate
     */
    @Override
    public X509Certificate getSignerCert() {
    	return(X509Util.DERtoX509Certificate(this.pkcs1.getX509Certificate()));
    }

    /**
     * Get the signer CN. 
     * <p>Equivalent to calling getSignerCert and
     * then parsing out the CN from the certificate's Subject field.
     * @return Signer's CN or null if there's a problem.
     */
    @Override
    public String getSignerCn() {
        try {
            X509Certificate signerCert = this.getSignerCert();
            String dn = signerCert.getSubjectX500Principal().getName();

            String cn = null;
            try {
                LdapName ldapDn = new LdapName(dn);
                List<Rdn> rdns = ldapDn.getRdns();
                for (Rdn r : rdns) {
                    if("CN".equals(r.getType())) {
                        cn = r.getValue().toString();
                    }
                }
            } catch(InvalidNameException e) {
                log.warn("Invalid name", e);
            }

            return cn;
        } catch (Throwable t) {
            log.error("Failed to get Signer cert " + t.getMessage());
            return null;
        }
    }

    /**
     * Get PKCS1 raw signature.
     */
    @Override
    public byte[] getRawSignature() {
        return this.pkcs1.getSignatureValue();
    }

    @Override
    public String getBase64Signature() {
        return Base64.toBase64String(this.getRawSignature());
    }
    
}
