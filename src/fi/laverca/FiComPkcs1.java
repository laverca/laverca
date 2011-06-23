package fi.laverca;

import java.io.UnsupportedEncodingException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;

import fi.ficom.mss.TS102204.v1_0_0.PKCS1;


/** 
 * A PKCS1 SignedData element.
 */ 
public class FiComPkcs1 {
    private static final Log log = LogFactory.getLog(FiComPkcs1.class);

    private PKCS1 pkcs1;

	/** 
     * @param bytes In general, you get this from an MSS_SignatureResp.getSignature() call.
     * @throws IllegalArgumentException
     */
    public FiComPkcs1(PKCS1 pkcs1) throws IllegalArgumentException {
    	
        if(pkcs1 == null) {
            throw new IllegalArgumentException("can't construct a PKCS1 SignedData element from null input.");
        }
        
        this.pkcs1 = pkcs1;
		
    }
    
    public String getMssSignatureValue() {
    	String signature = null;
    	try {
        	signature = new String(Base64.encode(pkcs1.getSignatureValue()), "ASCII");
		} catch (UnsupportedEncodingException e) {
			log.error("unable to decode signature" + e);
		}
		return signature;
    }

    /**
     * Look up the Certificate of the signer of this signature. 
     * Note that this only looks up the first signer. In MSSP signatures,
     * there is only one, but in a general Pkcs1 case, there can be several.
     * 
     */
    public X509Certificate getSignerCert() throws FiComException {
    	return(X509Util.DERtoX509Certificate(pkcs1.getX509Certificate()));
    }

    /**
     * Convenience method. Equivalent to calling getSignerCert and
     * then parsing out the CN from the certificate's Subject field.
     * @return null if there's a problem.
     */
    public String getSignerCn() {
        try {
            X509Certificate signerCert = this.getSignerCert();
            String dn = signerCert.getSubjectX500Principal().getName();

            //System.out.println("getSignerCn");
            //System.out.println(dn);
            String cn = null;
            try {
                LdapName ldapDn = new LdapName(dn);
                List<Rdn> rdns = ldapDn.getRdns();
                for(Rdn r : rdns) {
                    //System.out.println(r.getType());
                    //System.out.println(r.getValue());
                    if("CN".equals(r.getType())) {
                        cn = r.getValue().toString();
                    }
                }
            }
            catch(InvalidNameException e) {
                // TODO
            }

            return cn;
        }
        catch(Throwable t) {
            log.error("",t);
            return null;
        }
    }

}
