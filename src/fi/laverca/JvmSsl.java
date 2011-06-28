package fi.laverca;

/**
 * @author Asko Saura (asko@methics.fi)
 */
public class JvmSsl {

    /**
     * Sets JVM global SSL settings. This needs to be called only
     * once, before any EtsiClient or FiComClient objects are
     * used. The method tries to open the stores in order to
     * validate the parameters.
     *<p>
     * Note: This is STATIC, and uses global JVM settings.
     *       This can conflict with settings done by some other
     *       code, or by runtime settings.
     *
     * @param trustStore the keystore file containing trusted SSL server certificates.
     * @param trustStorePassword the password to the truststore.
     * @param keyStore the keystore containing your SSL client certificate.
     * @param keyStorePassword the password to the SSL client keystore.
     * @param keyStoreType either "JKS" (Java native) or "PKCS12". 
     * @exception IllegalArgumentException if the stores can not be opened.
     *
     */
    public static void setSSL(String trustStore,
                              String trustStorePassword, 
                              String keyStore,
                              String keyStorePassword,
                              String keyStoreType) 
    throws IllegalArgumentException
    {
        System.setProperty("javax.net.ssl.keyStoreType",       keyStoreType);
        System.setProperty("javax.net.ssl.keyStore",           keyStore);
        System.setProperty("javax.net.ssl.keyStorePassword",   keyStorePassword);

        if (trustStore != null) {
            System.setProperty("javax.net.ssl.trustStore",         trustStore);
            System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
        }
    }
}
