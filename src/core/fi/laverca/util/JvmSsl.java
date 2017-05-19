/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2015 Laverca Project
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

package fi.laverca.util;

import javax.net.ssl.SSLSocketFactory;

/**
 * This has old style static setter of JVM global SSL settings.
 * <p>
 * Instead of this setting keystore data, you can also use
 * MssClient/EtsiClient/FicomClient's setSSLSocketFactory()
 * methods before calling the service the first time.
 * <p>
 * The connections are always trusted, and in case you want
 * to specify what certificates are expected at the server side,
 * you can use LavercaSSLTrustManager.setExpectedServerCerts()
 * to set a list of possible certificates.
 * @deprecated Use {@link fi.laverca.mss.MssClient#createSSLFactory(String, String, String, String, String, String)} and 
 * {@link #setSSLSocketFactory(SSLSocketFactory)} instead
 */
@Deprecated
public class JvmSsl {

    /**
     * Sets JVM global SSL settings. This needs to be called only
     * once, before any EtsiClient or FiComClient objects are
     * used.
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
     * @deprecated Use {@link #setSSL(String, String, String, String, String, String)} instead
     */
    @Deprecated
    public static void setSSL(final String trustStore,
                              final String trustStorePassword, 
                              final String keyStore,
                              final String keyStorePassword,
                              final String keyStoreType) 
    throws IllegalArgumentException
    {
        setSSL(trustStore, trustStorePassword, null, keyStore, keyStorePassword, keyStoreType);
    }
    
    /**
     * Sets JVM global TrustStore settings. This needs to be called only
     * once, before any EtsiClient or FiComClient objects are
     * used.
     *<p>
     * Note: This is STATIC, and uses global JVM settings.
     *       This can conflict with settings done by some other
     *       code, or by runtime settings.
     *
     * @param trustStore the keystore file containing trusted SSL server certificates.
     * @param trustStorePassword the password to the truststore.
     * @param trustStoreType either "JKS" (Java native) or "PKCS12".
     * @exception IllegalArgumentException if the store can not be opened.
     *
     */
    public static void setTrustStore(final String trustStore,
                                     final String trustStorePassword,
                                     final String trustStoreType) {
        setSSL(trustStore, trustStorePassword, trustStoreType, null, null, null);
    }
    
    /**
     * Sets JVM global SSL settings. This needs to be called only
     * once, before any EtsiClient or FiComClient objects are
     * used.
     * 
     *<p>
     * Note: This is STATIC, and uses global JVM settings.
     *       This can conflict with settings done by some other
     *       code, or by runtime settings.
     *
     * @param trustStore the keystore file containing trusted SSL server certificates.
     * @param trustStorePassword the password to the truststore.
     * @param trustStoreType either "JKS" (Java native) or "PKCS12".
     * @param keyStore the keystore containing your SSL client certificate.
     * @param keyStorePassword the password to the SSL client keystore.
     * @param keyStoreType either "JKS" (Java native) or "PKCS12". 
     * @exception IllegalArgumentException if the stores can not be opened.
     *
     */
    public static void setSSL(final String trustStore,
                              final String trustStorePassword,
                              final String trustStoreType,
                              final String keyStore,
                              final String keyStorePassword,
                              final String keyStoreType) 
    throws IllegalArgumentException
    {
        if (keyStore         != null) System.setProperty("javax.net.ssl.keyStore",         keyStore);
        if (keyStorePassword != null) System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
        if (keyStoreType     != null) System.setProperty("javax.net.ssl.keyStoreType",     keyStoreType);

        if (trustStore         != null) System.setProperty("javax.net.ssl.trustStore",         trustStore);
        if (trustStorePassword != null) System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
        if (trustStoreType     != null) System.setProperty("javax.net.ssl.trustStoreType",     trustStoreType);
    }
    
}
