package fi.laverca;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A collection of helper methods for commonplace X509 tasks.
 * 
 * @author Asko Saura (asko@methics.fi)
 */
public class X509Util {
    private static Log log = LogFactory.getLog(X509Util.class);

    public static X509Certificate DERtoX509Certificate(byte[] der) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(der);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return (X509Certificate)cf.generateCertificate(bis);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    public static byte[] X509CertificateToDER(X509Certificate cert) {
        try {
            return cert.getEncoded();
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    
    /** return SHA-1 hash of the cert. */
    public static byte[] certHash(byte[] cert) {
        if(cert == null) {
            return null;
        }
        
        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            hash = md.digest(cert);
        } catch (Throwable t) {
            // never happens
        }
        
        return hash;
    }
    
    public static String parseSubjectCn(X509Certificate cert) {
        return parseSubjectName(cert, "CN");
    }

    public static String parseSubjectName(X509Certificate cert, String rdnType) {
        String dn = cert.getSubjectX500Principal().getName();
    
        String name = null;
        try {
            LdapName ldapDn = new LdapName(dn);
            List<Rdn> rdns = ldapDn.getRdns();
            for(Rdn r : rdns) {
                if(rdnType.equals(r.getType())) {
                    name = r.getValue().toString();
                }
            }
        }
        catch(InvalidNameException e) {
            log.error(e);
        }
        
        return name;
    }

}
