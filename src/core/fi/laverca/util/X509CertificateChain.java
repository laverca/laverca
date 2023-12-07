package fi.laverca.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fi.laverca.jaxb.mcs204ext1.CertificateType;

/**
 * Wrapper for a list of certificates that represents a certificate chain. 
 * Null object cannot be added to this. 
 */
public class X509CertificateChain implements Collection<X509Certificate>, Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<X509Certificate> certChain = new ArrayList<>();
    private List<String>          sigProfs  = new ArrayList<>();
    private String                signingMethod;
    private String                state;
    
    private Map<QName, String> attributes;
    
    /**
     * Construct an {@link X509CertificateChain} from SOAP {@link CertificateType} data.
     * @param certData SOAP data
     */
    public X509CertificateChain(CertificateType certData) {
        CertificateFactory certFactory = null;
        try {
            certFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            return;
        }

        this.attributes    = certData.getOtherAttributes();
        this.signingMethod = certData.getSigningMethod();
        this.state         = certData.getState();
        this.sigProfs.addAll(certData.getSignatureProfiles());
        
        for (Object o: certData.getX509IssuerSerialsAndX509SKISAndX509SubjectNames()) {
            if (o instanceof JAXBElement<?>) {
                JAXBElement<?> element = (JAXBElement<?>) o;
                Object val = element.getValue();
                
                if (val instanceof byte[]) {
                    try (InputStream in = new ByteArrayInputStream((byte[]) val)) {
                        final X509Certificate x509cert = (X509Certificate) certFactory.generateCertificate(in);
                        this.add(x509cert);                                
                    } catch (Exception ioe) {
                         continue;
                    }
                }
            }
        }
    }

    /**
     * Empty default constructor
     */
    public X509CertificateChain() {
        // Do nothing. 
    }
    
    /**
     * Get SignatureProfiles for this certificate chain
     * @return SignatureProfiles (String list)
     */
    public List<String> getSignatureProfiles() {
        return this.sigProfs;
    }
    
    /**
     * Get certificate SigningMethod
     * @return e.g. APP or SIM
     */
    public String getSigningMethod() {
        return this.signingMethod;
    }
    
    /**
     * Get certificate state
     * @return one of: ACTIVE, INACTIVE
     */
    public String getCertState() {
        return this.state;
    }

    /**
     * Get nth certificate from this certificate chain
     * @param n
     * @return certificate if found
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public X509Certificate get(int n) {
        return this.certChain.get(n);
    }
    
    /**
     * Get an attribute from the SOAP certificate element
     * @param qname QName of the attribute
     * @return attribute or null if not found
     */
    public String getAttribute(final QName qname) {
        return this.attributes.get(qname);
    }

    @Override
    public int size() {
        return this.certChain.size();
    }

    @Override
    public boolean isEmpty() {
        return this.certChain.isEmpty();
    }
    
    /**
     * Does the signing certificate have KeyUsage nonRepudiaiton?
     * @return true if the signing certificate have KeyUsage nonRepudiaiton
     */
    public boolean isNonRepudiation() {
        if (this.isEmpty()) return false;
        return this.certChain.get(0).getKeyUsage()[1];
    }

    /**
     * Does the signing certificate have KeyUsage digitalSignature?
     * @return true if the signing certificate have KeyUsage digitalSignature
     */
    public boolean isDigitalSignature() {
        if (this.isEmpty()) return false;
        return this.certChain.get(0).getKeyUsage()[0];
    }
    
    @Override
    public boolean contains(Object arg0) {
        return this.certChain.contains(arg0);
    }

    @Override
    public Iterator<X509Certificate> iterator() {
        return this.certChain.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.certChain.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        return this.certChain.toArray(arg0);
    }

    @Override
    public boolean add(X509Certificate arg0) {
        if (arg0 != null) {
            return this.certChain.add(arg0);            
        }
        return false;
    }

    @Override
    public boolean remove(Object arg0) {
        return this.certChain.remove(arg0);
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        return this.certChain.containsAll(arg0);
    }

    @Override
    public boolean addAll(Collection<? extends X509Certificate> arg0) {
        for (X509Certificate cert: arg0) {
            if (!this.add(cert)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {
        return this.certChain.removeAll(arg0);
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        return this.certChain.retainAll(arg0);
    }

    @Override
    public void clear() {
        this.certChain.clear();
    }

    public X509Certificate getSigningCert() {
        if (this.size() > 0) {
            return this.get(0);            
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (X509Certificate cert: this.certChain) {
            sb.append("Cert " + i + "= " + cert.toString() +"\n");
            i++;
        }
        return sb.toString();
    }

}
