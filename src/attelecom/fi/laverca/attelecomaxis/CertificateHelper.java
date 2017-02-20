/**
 * 
 */
package fi.laverca.attelecomaxis;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import sun.misc.BASE64Encoder;
import java.security.cert.Certificate;


/**
 * <p>
 * Helper class for dealing with X.509 certificates
 * </p>
 * @author Richard Unger
 */
public class CertificateHelper {

	/**
	 * factory for loading certs
	 */
	CertificateFactory factory = null;
	
	/**
	 * Constructor
	 * @throws CertificateException if there is a problem
	 */
	public CertificateHelper() throws CertificateException {
		factory = CertificateFactory.getInstance("X.509");
	}

	
	/**
	 * <p>
	 * Read a certificate from an inputstream
	 * </p>
	 * @param inStream the input
	 * @return the certificate
	 * @throws CertificateException if tehre is a problem
	 */
	public X509Certificate getCertificate(InputStream inStream) throws CertificateException{
		return (X509Certificate) factory.generateCertificate(inStream);
	}
	
	
	/**
	 * <p>
	 * Read the given certificate from its encoded bytes
	 * </p>
	 * @param inBytes the encoded certificate
	 * @return the certificate object
	 * @throws CertificateException if there is a problem
	 * @throws IOException 
	 */
	public X509Certificate getCertificate(byte[] inBytes) throws CertificateException, IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(inBytes);
		X509Certificate cert = getCertificate(bis);
		bis.close();
		return cert;
	}


	/**
	 * <p>
	 * Read the certificate from the given file
	 * </p>
	 * @param file the file containing the certificate
	 * @return return the certificate
	 * @throws FileNotFoundException the file was not found
	 * @throws CertificateException if there is a problem with the certificate
	 */
	public X509Certificate getCertificate(File file) throws FileNotFoundException, CertificateException {
		FileInputStream fis = new FileInputStream(file);
		X509Certificate cert = getCertificate(fis);
		return cert;
	}
	
	
	/**
	 * <p>
	 * Get the encoded certificate, for storing to the database...
	 * </p>
	 * @param cert the certificate
	 * @return the encoded form
	 * @throws CertificateEncodingException
	 */
	public byte[] getEncodedCertificate(X509Certificate cert) throws CertificateEncodingException{
		return cert.getEncoded();
	}
	
	
	/**
	 * Compares the byte[] of two given X509Certificates
	 * @param cert1
	 * @param cert2
	 * @return true if they are equal, false otherwise
	 * @throws CertificateEncodingException
	 */
	public boolean compareX509Certificates(X509Certificate cert1, X509Certificate cert2) throws CertificateEncodingException {

		byte[] data1 = this.getEncodedCertificate(cert1);
		byte[] data2 = this.getEncodedCertificate(cert2);
		
		if (data1.length != data2.length)
			return false;
		
		return Arrays.equals(data1, data2);
	}
	
	
	
	
	
	
//	From http://javaalmanac.com/egs/java.security/GetKeyFromKs.html
	public KeyPair getPrivateKey(KeyStore keystore, String alias, char[] password) {
	     try {
	         // Get private key
	         Key key = keystore.getKey(alias, password);
	         if (key instanceof PrivateKey) {
	             // Get certificate of public key
	             Certificate cert = keystore.getCertificate(alias);
	 
	             // Get public key
	             PublicKey publicKey = cert.getPublicKey();
	 
	             // Return a key pair
	             return new KeyPair(publicKey, (PrivateKey)key);
	         }
	     } catch (UnrecoverableKeyException e) {
	     } catch (NoSuchAlgorithmException e) {
	     } catch (KeyStoreException e) {
	     }
	     return null;
	 }
	
	
	
	 public void printPrivateKey(String jksKeyStoreName, String aliasName, String pass) throws Exception{
			KeyStore ks = KeyStore.getInstance("JKS");
			char[] passPhrase = pass.toCharArray();
			BASE64Encoder myB64 = new BASE64Encoder();
			File certificateFile = new File(jksKeyStoreName);
			ks.load(new FileInputStream(certificateFile), passPhrase);
			KeyPair kp = getPrivateKey(ks, aliasName, passPhrase);
			PrivateKey privKey = kp.getPrivate();
			String b64 = myB64.encode(privKey.getEncoded());
			System.out.println("-----BEGIN PRIVATE KEY-----");
			System.out.println(b64);
			System.out.println("-----END PRIVATE KEY-----");
	 }
	
	 
	 public void convertJKS2PKCS12(String jksKeyStoreFile,String pkcs12KeyStoreFile, String alias, String password) throws NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, KeyStoreException, UnrecoverableEntryException{
		   // define 2 keystore formats
	       KeyStore ks = KeyStore.getInstance("jks");
	       KeyStore nks = KeyStore.getInstance("pkcs12");
	 
	       // Load the jks keystore and open the pkcs12 store
	       ks.load(new FileInputStream(jksKeyStoreFile), password.toCharArray());
	       nks.load(null, password.toCharArray());
	 
	       // Get the private key entry and store it in the new keystore
	       KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(password.toCharArray()));
	       nks.setEntry(alias, pkEntry, new KeyStore.PasswordProtection(password.toCharArray()));
	 
	       // Store the new keystore
	       FileOutputStream fos = new FileOutputStream(pkcs12KeyStoreFile);
	       nks.store(fos, password.toCharArray() );
	       fos.close();
	 }
	 
	 
	 
	 public static void main(String[] args) throws NoSuchAlgorithmException, CertificateException, FileNotFoundException, KeyStoreException, UnrecoverableEntryException, IOException{
		 
		 CertificateHelper helper = new CertificateHelper();
		 helper.convertJKS2PKCS12("dist/certs/clientkey.jks", "dist/certs/clientkey.p12", "clientkey", "****");
		 System.out.println("Conversion completed without exception.");

	 }
}
