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

package fi.laverca.mss;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.configuration.FileProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fi.laverca.jaxb.mss.DataType;
import fi.laverca.jaxb.mss.MSSHandshakeReq;
import fi.laverca.jaxb.mss.MSSHandshakeResp;
import fi.laverca.jaxb.mss.MSSProfileReq;
import fi.laverca.jaxb.mss.MSSProfileResp;
import fi.laverca.jaxb.mss.MSSReceiptReq;
import fi.laverca.jaxb.mss.MSSReceiptResp;
import fi.laverca.jaxb.mss.MSSRegistrationReq;
import fi.laverca.jaxb.mss.MSSRegistrationResp;
import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.mss.MSSStatusReq;
import fi.laverca.jaxb.mss.MSSStatusResp;
import fi.laverca.jaxb.mss.MeshMemberType;
import fi.laverca.jaxb.mss.MessageAbstractType;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.jaxb.mss.MobileUserType;
import fi.laverca.jaxb.mss.MssURIType;
import fi.laverca.jaxb.mss.ObjectFactory;
import fi.laverca.jaxb.wsssecext.Security;
import fi.laverca.mss.ws.MSS_HandshakeBindingStub;
import fi.laverca.mss.ws.MSS_ProfileQueryBindingStub;
import fi.laverca.mss.ws.MSS_ReceiptBindingStub;
import fi.laverca.mss.ws.MSS_RegistrationBindingStub;
import fi.laverca.mss.ws.MSS_SignatureBindingStub;
import fi.laverca.mss.ws.MSS_SignatureServiceLocator;
import fi.laverca.mss.ws.MSS_StatusQueryBindingStub;
import fi.laverca.util.AbstractSoapBindingStub;
import fi.laverca.util.ComponentsHTTPSender;
import fi.laverca.util.DTBS;
import fi.laverca.util.JMarshallerFactory;
import fi.laverca.util.LavercaContext;
import fi.laverca.util.LavercaHttpClient;
import fi.laverca.util.LavercaSSLTrustManager;
import fi.laverca.util.ProxySettings;

/**
 * A raw ETSI TS 102 204 client object.
 */ 
public class MssClient {
    private static Log log = LogFactory.getLog(MssClient.class);

    public static ObjectFactory mssObjFactory = new ObjectFactory();
    
    public static final String CLIENT_CONFIG_WSDD_FILENAME = "laverca-client-config.wsdd";
    
    private static boolean marshallerInitDone;
    // AP settings
    private String apId  = null;
    private String apPwd = null;
    
    // MSSP AE connection settings
    private final MSS_SignatureServiceLocator mssService;
    private URL MSSP_SI_URL = null;
    private URL MSSP_RC_URL = null;
    private URL MSSP_HS_URL = null;
    private URL MSSP_ST_URL = null;
    private URL MSSP_PR_URL = null;

    private URL MSSP_RG_URL = null;

    private int poolSize = 50;
    
    private int newConnTimeout;
    private int newSoTimeout;
    
    private String newUsername;
    private String newPassword;
    
    private ProxySettings     proxySettings;
    private SSLSocketFactory  sslSocketFactory;

    private LavercaHttpClient httpClient;
    
    /**
     * <b>NOTE:</b> 
     * <br>if any of the URLs require SSL, you must
     * call {@link fi.laverca.util.JvmSsl#setSSL(String,String,String,String,String)} OR set the engine configuration before sending any requests.
     *
     * <p>If the configuration contains keystore and/or truststore parameters,
     * {@link #createSSLFactory(String, String, String, String, String, String)} and {@link #setSSLSocketFactory(SSLSocketFactory)}
     * are automatically run. Ignores any keystore loading problems.
     *
     * @param conf MSS Configuration object (not null)
     */
    public MssClient(final MssConf conf) {
        this(conf.getApId(),
             conf.getApPwd(),
             conf.getSignatureUrl(),
             conf.getStatusUrl(),
             conf.getReceiptUrl(),
             conf.getRegistrationUrl(),
             conf.getProfileUrl(),
             conf.getHandshakeUrl());
        
        if (conf.getKeystore() != null) {
            try {
                SSLSocketFactory ssf = MssClient.createSSLFactory(conf.getKeystore(),   conf.getKeystorePwd(),   conf.getKeystoreType(), 
                                                                  conf.getTruststore(), conf.getTruststorePwd(), conf.getTruststoreType());
                this.setSSLSocketFactory(ssf);
            } catch (Exception e) {
                log.error("Failed to load keystore and/or truststore", e);
            }
        }
    }

    /**
     * <b>NOTE:</b> 
     * <br>if any of the URLs require SSL, you must
     * call {@link fi.laverca.util.JvmSsl#setSSL(String,String,String,String,String)} OR set the engine configuration before sending any requests.
     *
     * @param apId Your identifier; MessageAbstractType/AP_Info/AP_ID. Not null.
     * @param apPwd Your password; MessageAbstractType/AP_Info/AP_PWD. Not null.
     * @param msspSignatureUrl    Connection URL to the AE for signature requests. 
     * @param msspStatusUrl       Connection URL to the AE for status query requests. 
     * @param msspReceiptUrl      Connection URL to the AE for receipt requests. 
     */
    public MssClient(final String apId,
                     final String apPwd,
                     final String msspSignatureUrl,
                     final String msspStatusUrl,
                     final String msspReceiptUrl) {
        this(apId, apPwd, msspSignatureUrl, msspStatusUrl, msspReceiptUrl, null, null, null);
    }
    
    /** 
     * <b>NOTE:</b> 
     * <br>if any of the URLs require SSL, you must
     * call {@link fi.laverca.util.JvmSsl#setSSL(String,String,String,String,String)} OR set the engine configuration before sending any requests.
     * 
     * @param apId Your identifier; MessageAbstractType/AP_Info/AP_ID. Not null.
     * @param apPwd Your password; MessageAbstractType/AP_Info/AP_PWD. Not null.
     * @param msspSignatureUrl    Connection URL to the AE for signature requests. 
     * @param msspStatusUrl       Connection URL to the AE for status query requests. 
     * @param msspReceiptUrl      Connection URL to the AE for receipt requests. 
     * @param msspRegistrationUrl Connection URL to the AE for registration requests. 
     * @param msspProfileUrl      Connection URL to the AE for profile query requests. 
     * @param msspHandshakeUrl    Connection URL to the AE for handshake requests.
     * 
     * @throws IllegalArgumentException if AP ID or AP PWD is missing or invalid.
     */
    public MssClient(final String apId,             // AP settings
                     final String apPwd, 
                     final String msspSignatureUrl, // AE connection settings
                     final String msspStatusUrl,
                     final String msspReceiptUrl,
                     final String msspRegistrationUrl,
                     final String msspProfileUrl,
                     final String msspHandshakeUrl)
        throws IllegalArgumentException
    {
        if (apId == null) {
            throw new IllegalArgumentException("Unable to construct MssClient without an AP identifier");
        }
        if (apPwd == null) {
            throw new IllegalArgumentException("Unable to construct MssClient without an AP Password");
        }
        
        this.apId  = apId;
        this.apPwd = apPwd;

        log.info("Initialized MssClient");
        
        this.setAeAddress(msspSignatureUrl,
                          msspStatusUrl,
                          msspReceiptUrl,
                          msspRegistrationUrl,
                          msspProfileUrl,
                          msspHandshakeUrl);

        MssClient.marshallerInit();

        // Load laverca-client-config.wsdd
        InputStream wsdd = Thread.currentThread().getContextClassLoader().getResourceAsStream(CLIENT_CONFIG_WSDD_FILENAME);
        if (wsdd != null) {
            this.mssService = new MSS_SignatureServiceLocator(new FileProvider(wsdd));
        } else {
            log.warn("Could not find " + CLIENT_CONFIG_WSDD_FILENAME + " in classpath");
            this.mssService = new MSS_SignatureServiceLocator();
        }
        
    }
    
    /**
     * Create an SSLSocketFactory
     * @param ksFile Keystore filename
     * @param ksPwd  Keystore password
     * @param ksType Keystore type
     * @return Created SSLSocketFactory
     * @throws IOException if the keystore or truststore cannot be read
     * @throws GeneralSecurityException if there is any security exception related to accessing the keystore or truststore
     */
    public static SSLSocketFactory createSSLFactory(final String ksFile, final String ksPwd, final String ksType)
        throws GeneralSecurityException, IOException 
    {
        return createSSLFactory(ksFile, ksPwd, ksType, null, null, null);
    }

    /**
     * Create an SSLSocketFactory
     * @param ksFile Keystore filename
     * @param ksPwd  Keystore password
     * @param ksType Keystore type
     * @param tsFile Truststore filename
     * @param tsPwd  Truststore password
     * @param tsType Truststore type
     * @return Created SSLSocketFactory
     * @throws IOException if the keystore or truststore cannot be read
     * @throws GeneralSecurityException if there is any security exception related to accessing the keystore or truststore
     */
    public static SSLSocketFactory createSSLFactory(final String ksFile, final String ksPwd, final String ksType,
                                                    final String tsFile, final String tsPwd, final String tsType) 
        throws GeneralSecurityException, IOException 
    {
        KeyStore    ks = KeyStore.getInstance(ksType);
        KeyStore    ts = null;
        InputStream kis = new FileInputStream(ksFile);
        InputStream tis = null;

        try {
            KeyManagerFactory   kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            
            ks.load(kis, ksPwd.toCharArray());
            kmf.init(ks, ksPwd.toCharArray());

            SSLContext ctx = SSLContext.getInstance("TLSv1.2");
            
            if (tsFile != null) {
                ts = KeyStore.getInstance(tsType);
                ts.load(new FileInputStream(tsFile), tsPwd.toCharArray());
                
                List<byte[]> certs = new ArrayList<>();
                
                for (Enumeration<String> aliases = ts.aliases(); aliases.hasMoreElements();) {
                    String alias = aliases.nextElement();
                    if (ts.isKeyEntry(alias)) {
                        X509Certificate cert = (X509Certificate)ts.getCertificate(alias);
                        if (cert != null) certs.add(cert.getEncoded());
                    }
                }

                LavercaSSLTrustManager.getInstance().setExpectedServerCerts(certs);
            }
            
            TrustManager[] tms = new TrustManager[] {LavercaSSLTrustManager.getInstance()};
            ctx.init(kmf.getKeyManagers(), tms, null);
            
            return ctx.getSocketFactory();
        } finally {
            if (tis != null) tis.close();
            kis.close();
        }
    }
  
    /**
     * Return whether s is a valid xs:NCName String.
     * 
     * @param s String to test
     * @return true if s is a valid xs:NCName
     */
    public static boolean isNCName(final String s) {
        if (s == null) {
            return false;
        } else {
            return org.apache.axis.types.NCName.isValid(s);
        }
    }
    
    private static synchronized void marshallerInit() {
        
        if (marshallerInitDone) return;

        // Record global package names of generated JAXB classes
        for (final Class<?> c : new Class[] {
                fi.laverca.jaxb.kiurumssp5.ObjectFactory.class,
                fi.laverca.jaxb.mcs204ext1.ObjectFactory.class,
                fi.laverca.jaxb.mid204as1.ObjectFactory.class,
                fi.laverca.jaxb.mreg.ObjectFactory.class,
                fi.laverca.jaxb.mss.ObjectFactory.class,
                fi.laverca.jaxb.mssfi.ObjectFactory.class,
                fi.laverca.jaxb.saml2a.ObjectFactory.class,
                fi.laverca.jaxb.saml2p.ObjectFactory.class,
                fi.laverca.jaxb.sco204ext1.ObjectFactory.class,
                fi.laverca.jaxb.soap12env.ObjectFactory.class,
                fi.laverca.jaxb.xmldsigcore.ObjectFactory.class,
                fi.laverca.jaxb.xmlenc.ObjectFactory.class,
                fi.laverca.jaxb.wsssecext.ObjectFactory.class,
                fi.laverca.jaxb.wsssecutil.ObjectFactory.class,
                fi.laverca.jaxb.kiuruwsse1.ObjectFactory.class}) {

            final String p = c.getPackage().getName();
            JMarshallerFactory.addJAXBPath(p);

        }

        marshallerInitDone = true;
    }
    
    /**
     * Create a profile query request for given MSISDN
     * @param msisdn MSISDN of the mobile user
     * @param apTransId AP_TransID 
     * @return Created MSS_ProfileReq
     * @throws IllegalArgumentException if the given MSISDN is null
     */
    public MSSProfileReq createProfileRequest(final String msisdn, final String apTransId) {
        
        if (msisdn == null) throw new IllegalArgumentException("Invalid MSISDN (null)");
        if (apTransId == null) throw new IllegalArgumentException("Invalid AP_TransID (null)");

        
        MSSProfileReq req = mssObjFactory.createMSSProfileReq();
        
        this.initializeRequestMessage(req, apTransId);
        
        MobileUserType mu = mssObjFactory.createMobileUserType();
        mu.setMSISDN(msisdn);
        req.setMobileUser(mu);        
        
        return req;
    }
    
    /**
     * Create a MSS_ReceiptRequest based on received MSS_SignatureResponse
     * 
     * @param sigResp MSS_SignatureResponse on which the receipt request is constructed
     * @param apTransId each new MSS request needs a new apTransID
     * @param message Message to display
     * @return Created MSS_ReceiptReq
     */
    public MSSReceiptReq createReceiptRequest(final MSSSignatureResp sigResp, 
                                              final String apTransId,
                                              final String message) 
    {
        if (sigResp               == null) throw new IllegalArgumentException("Invalid Signature Response (null)");
        if (sigResp.getMSSPInfo() == null) throw new IllegalArgumentException("Invalid Signature Response MSSP Info (null)");
        
        final MeshMemberType msspId = sigResp.getMSSPInfo().getMSSPID();
        if (msspId == null) throw new IllegalArgumentException("Invalid Signature Response MSSP ID");
        
        final MSSReceiptReq req = mssObjFactory.createMSSReceiptReq();
        this.initializeRequestMessage(req, apTransId);

        req.getMSSPInfo().setMSSPID(msspId);
        req.setMSSPTransID(sigResp.getMSSPTransID());
        
        if (message != null) {
            final DataType meObject = mssObjFactory.createDataType();
            meObject.setValue(message);
            req.setMessage(meObject);
        }
        
        return req;
    }
    
    /**
     * Create a MSS_RegistrationReq
     * 
     * @param apTransId
     * @return
     */
    public MSSRegistrationReq createRegistrationReq(final String apTransId) {
        
        MSSRegistrationReq req = mssObjFactory.createMSSRegistrationReq();
        this.initializeRequestMessage(req, apTransId);
        req.setMobileUser(mssObjFactory.createMobileUserType());

        return req;
    }
    
    /**
     * Creates a signature request. 
     * 
     * @param apTransId AP Transaction ID - not null.
     * @param msisdn MSISDN of the mobile user - not null.
     * @param dtbs Data to be Signed - not null.
     * @param dataToBeDisplayed Data to be displayed - may be null. 
     * @param signatureProfile Signature profile to use - not null.
     * @param mss_format MSS_Format to use - not null.
     * @param messagingMode Messaging mode to use - not null.
     * @return Created signature request
     */
    public MSSSignatureReq createSignatureRequest(final String apTransId,
                                                  final String msisdn,
                                                  final DTBS dtbs,
                                                  final String dataToBeDisplayed,
                                                  final String signatureProfile,
                                                  final String mss_format,
                                                  final MessagingModeType messagingMode) {
        
        if (msisdn           == null) throw new IllegalArgumentException("Invalid MSISDN (null)");
        if (dtbs             == null) throw new IllegalArgumentException("Invalid DTBS (null)");
        if (signatureProfile == null) throw new IllegalArgumentException("Invalid SignatureProfile (null)");
        if (messagingMode    == null) throw new IllegalArgumentException("Invalid MessagingMode (null)");
        
        final MSSSignatureReq req = mssObjFactory.createMSSSignatureReq();
        this.initializeRequestMessage(req, apTransId);
        
        MobileUserType muObject = mssObjFactory.createMobileUserType();
        muObject.setMSISDN(msisdn);
        req.setMobileUser(muObject);

        final DataType dsObject = dtbs.toDataToBeSigned();
        req.setDataToBeSigned(dsObject);
        
        if (dataToBeDisplayed != null) {
            final DataType ddObject = mssObjFactory.createDataType();
            ddObject.setValue(dataToBeDisplayed);
            req.setDataToBeDisplayed(ddObject);
        }
        
        final MssURIType spObject = mssObjFactory.createMssURIType();
        spObject.setMssURI(signatureProfile);
        req.setSignatureProfile(spObject);

        if (mss_format != null) {
            MssURIType mfObject = mssObjFactory.createMssURIType();
            mfObject.setMssURI(mss_format);
            req.setMSSFormat(mfObject);
        }

        req.setMessagingMode(messagingMode);
        req.setAdditionalServices(MssClient.mssObjFactory.createMSSSignatureReqAdditionalServices());
        
        return req;
    }
    
    /**
     * Create a status request for a signature response.
     * 
     * @param apTransId new AP transaction id
     * @param sigResp Original MSS_SignatureResp
     * @return Created MSS_StatusReq
     * @throws IllegalArgumentException if the given signature response does not contain all the necessary data to create an MSS_StatusReq
     */
    public MSSStatusReq createStatusRequest(final MSSSignatureResp sigResp,
                                            final String apTransId) 
        throws IllegalArgumentException
    {
        if (sigResp               == null) throw new IllegalArgumentException("Invalid Signature Response (null)");
        if (sigResp.getMSSPInfo() == null) throw new IllegalArgumentException("Invalid Signature Response MSSP Info (null)");
        
        final MeshMemberType msspId = sigResp.getMSSPInfo().getMSSPID();
        if (msspId == null) throw new IllegalArgumentException("Invalid Signature Response MSSP ID");

        MSSStatusReq req = mssObjFactory.createMSSStatusReq();
        this.initializeRequestMessage(req, apTransId);
        
        req.getMSSPInfo().setMSSPID(msspId);
        req.setMSSPTransID(sigResp.getMSSPTransID());
        
        return req;
    }

    /**
     * Send the MSS_HandshakeRequest to MSS system receiving answer
     * @param req the MSS_HandshakeReq
     * @return received MSS_HandshakeResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSHandshakeResp send(final MSSHandshakeReq req) throws IOException {
        return this.send(req, null);
    }
    
    /**
     * Send the MSS_HandshakeRequest to MSS system receiving answer
     * @param req the MSS_HandshakeReq
     * @param context LavercaContext
     * @return received MSS_HandshakeResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSHandshakeResp send(final MSSHandshakeReq req, final LavercaContext context) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null HandshakeReq");
        return (MSSHandshakeResp)this.sendMat(req, context);
    }
    
    
    /**
     * Send the MSS_ProfileRequest to MSS system receiving answer
     * @param req the MSS_ProfileReq
     * @return received MSS_ProfileResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSProfileResp send(final MSSProfileReq req) throws IOException {
        return this.send(req, null);
    }

    /**
     * Send the MSS_ProfileRequest to MSS system receiving answer
     * @param req the MSS_ProfileReq
     * @param context LavercaContext
     * @return received MSS_ProfileResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSProfileResp send(final MSSProfileReq req, final LavercaContext context) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null ProfileReq");
        return (MSSProfileResp)this.sendMat(req, context);
    }
    
    /**
     * Send the MSS_ReceiptRequest to MSS system receiving answer
     * @param req the MSS_ReceiptReq
     * @return received MSS_ReceiptResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSReceiptResp send(final MSSReceiptReq req) throws IOException {
        return this.send(req, null);
    }
    
    /**
     * Send the MSS_ReceiptRequest to MSS system receiving answer
     * @param req the MSS_ReceiptReq
     * @param context LavercaContext
     * @return received MSS_ReceiptResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSReceiptResp send(final MSSReceiptReq req, final LavercaContext context) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null ReceiptReq");
        return (MSSReceiptResp)this.sendMat(req, context);
    }
    

    /**
     * Send the MSS_RegistrationRequest to MSS system receiving answer
     * @param req the MSS_RegistrationReq
     * @return received MSS_RegistrationResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSRegistrationResp send(final MSSRegistrationReq req) throws IOException {
        return this.send(req, null);
    }

    /**
     * Send the MSS_RegistrationRequest to MSS system receiving answer
     * @param req the MSS_RegistrationReq
     * @param context LavercaContext
     * @return received MSS_RegistrationResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSRegistrationResp send(final MSSRegistrationReq req, final LavercaContext context) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null RegistrationReq");
        return (MSSRegistrationResp)this.sendMat(req, context);
    }

    /**
     * Send the MSS_RegistrationRequest to MSS system receiving answer
     * @param req the MSS_RegistrationReq
     * @param context LavercaContext
     * @param security WSSE security header
     * @return received MSS_RegistrationResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSRegistrationResp send(final MSSRegistrationReq req, final LavercaContext context, final Security security) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null RegistrationReq");
        return (MSSRegistrationResp)this.sendMat(req, context, security);
    }
    
    /**
     * Send the MSS_SignatureRequest to MSS system receiving answer
     * @param req the MSS_SignatureReq
     * @return received MSS_SignatureResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSSignatureResp send(final MSSSignatureReq req) throws IOException {
        return this.send(req, null);
    }

    /**
     * Send the MSS_SignatureRequest to MSS system receiving answer
     * @param req the MSS_SignatureReq
     * @param context LavercaContext
     * @return received MSS_SignatureResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSSignatureResp send(final MSSSignatureReq req, final LavercaContext context) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null SignatureReq");
        
        if (req.getAdditionalServices() != null) {
            if (req.getAdditionalServices().getServices().size() == 0) {
                req.setAdditionalServices(null);
            }
        }
        return (MSSSignatureResp)this.sendMat(req, context);
    }

    /**
     * Send the MSS_StatusRequest to MSS system receiving answer
     * @param req the MSS_StatusReq
     * @return received MSS_StatusResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSStatusResp send(final MSSStatusReq req) throws IOException {
        return this.send(req, null);
    }

    /**
     * Send the MSS_StatusRequest to MSS system receiving answer
     * @param req the MSS_StatusReq
     * @param context LavercaContext
     * @return received MSS_StatusResp
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if req is null
     */
    public MSSStatusResp send(final MSSStatusReq req, final LavercaContext context) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null StatusReq");
        return (MSSStatusResp)this.sendMat(req, context);
    }

    /**
     * Sets the AE connection URLs.
     * @param msspSignatureUrl    Connection URL to the AE for signature requests. 
     * @param msspStatusUrl       Connection URL to the AE for status query requests. 
     * @param msspReceiptUrl      Connection URL to the AE for receipt requests. 
     * @param msspRegistrationUrl Connection URL to the AE for registration requests. 
     * @param msspProfileUrl      Connection URL to the AE for profile query requests. 
     * @param msspHandshakeUrl    Connection URL to the AE for handshake requests.
     */
    public void setAeAddress(final String msspSignatureUrl,
                             final String msspStatusUrl,
                             final String msspReceiptUrl,
                             final String msspRegistrationUrl,
                             final String msspProfileUrl,
                             final String msspHandshakeUrl)
    throws IllegalArgumentException
    {
        try {
            if (msspSignatureUrl != null) {
                this.MSSP_SI_URL = new URL (msspSignatureUrl);
            }
            if (msspStatusUrl != null) {
                this.MSSP_ST_URL = new URL (msspStatusUrl);
            }
            if (msspReceiptUrl != null) {
                this.MSSP_RC_URL = new URL (msspReceiptUrl);
            }
            if (msspRegistrationUrl != null) {
                this.MSSP_RG_URL = new URL (msspRegistrationUrl);
            }
            if (msspProfileUrl != null) {
                this.MSSP_PR_URL = new URL (msspProfileUrl);
            }
            if (msspHandshakeUrl != null) {
                this.MSSP_HS_URL = new URL (msspHandshakeUrl);
            }
        } catch (MalformedURLException mue) {
            throw new IllegalArgumentException(mue.getMessage());
        }
    }

    
    /**
     * Set connection timeout for HTTP Client
     * @param connTimeout Timeout in milliseconds
     */
    public void setConnectionTimeout(final int connTimeout) {
        this.newConnTimeout = connTimeout;
    }

    /**
     * Set a custom Axis EngineConfiguration
     * @param conf Axis EngineConfiguration
     */
    public void setEngineConfiguration(final EngineConfiguration conf) {
        this.mssService.setEngineConfiguration(conf);
    }

    /**
     * Set HTTP Client pool size
     * @param poolSize pool size
     */
    public void setPoolSize(final int poolSize) {
        this.poolSize = poolSize;
    }

    /**
     * Set socket timeout for HTTP Client
     * @param socketTimeout Timeout in milliseconds
     */
    public void setSocketTimeout(final int socketTimeout) {
        this.newSoTimeout = socketTimeout;
    }

    /**
     * Set this socket factory before calling MSS operations,
     * if you want to e.g. inclusion of your client certificate
     * on the outgoing calls.
     *
     * @param ssf Define a SSL SocketFactory with a client side key
     */
    public void setSSLSocketFactory(final SSLSocketFactory ssf) {
        this.sslSocketFactory = ssf;
    }

    private LavercaHttpClient getHttpClient() {
        synchronized (this) {
            if (this.httpClient == null) {
                // If SSLSocketFactory is set, use it, otherwise use system default
                SSLSocketFactory ssf = this.sslSocketFactory;
                if (ssf == null) {
                    ssf = (SSLSocketFactory)SSLSocketFactory.getDefault();
                }
                this.httpClient = new LavercaHttpClient("mssClientPool",
                                                        this.poolSize,
                                                        this.newConnTimeout,
                                                        this.newSoTimeout,
                                                        this.newUsername,
                                                        this.newPassword,
                                                        this.proxySettings,
                                                        ssf);
            }
        }
        return this.httpClient;
    }

    /**
     * Fills Minorversion, Majorversion, AP_Info and MSS_Info to the given message.
     * @param mat Message to fill
     * @param apTransId AP Transaction ID
     */
    private void initializeRequestMessage(final MessageAbstractType mat, final String apTransId) {
        
        if (mat == null) throw new IllegalArgumentException("Invalid request (null)");
    
        // Set the interface versions. 1 for both, as per ETSI TS 102 204.
        mat.setMajorVersion(Long.valueOf(1));
        mat.setMinorVersion(Long.valueOf(1));

        if (apTransId != null) {
            // Create the AP_Info.
            final MessageAbstractType.APInfo aiObject = mssObjFactory.createMessageAbstractTypeAPInfo();
            aiObject.setAPID(this.apId);
            aiObject.setAPPWD(this.apPwd);
            aiObject.setAPTransID(apTransId);
            aiObject.setInstant(new GregorianCalendar());
            mat.setAPInfo(aiObject);
        }
        
        final MessageAbstractType.MSSPInfo miObject = mssObjFactory.createMessageAbstractTypeMSSPInfo();
        miObject.setMSSPID(mssObjFactory.createMeshMemberType()); 

        mat.setMSSPInfo(miObject);
    }

    /**
     * Sends an MSS request.
     *
     * @param req Abstract request type
     * @param context Context
     * @throws IOException if a HTTP communication error occurred i.e. a SOAP fault was generated by the <i>local</i> SOAP client stub.
     */
    private MessageAbstractType sendMat(final MessageAbstractType req, final LavercaContext context)
        throws AxisFault, IOException
    {
        return this.sendMat(req, context, null);
    }

    /**
     * Sends an MSS request.
     *
     * @param req Abstract request type
     * @param context Context
     * @param security WSSE security headers
     * @throws IOException if a HTTP communication error occurred i.e. a SOAP fault was generated by the <i>local</i> SOAP client stub.
     */
    private MessageAbstractType sendMat(final MessageAbstractType req, final LavercaContext context, final Security security)
        throws AxisFault, IOException
    {
        AbstractSoapBindingStub port = null;
        try {
            Long timeout = null;

            if (req instanceof MSSSignatureReq) {
                timeout = ((MSSSignatureReq)req).getTimeOut();
                port = (MSS_SignatureBindingStub)this.mssService.getMSS_SignaturePort(this.MSSP_SI_URL);
            } else if (req instanceof MSSReceiptReq) {
                port = (MSS_ReceiptBindingStub)this.mssService.getMSS_ReceiptPort(this.MSSP_RC_URL);
            } else if (req instanceof MSSHandshakeReq) {
                port = (MSS_HandshakeBindingStub)this.mssService.getMSS_HandshakePort(this.MSSP_HS_URL);
            } else if (req instanceof MSSStatusReq) {
                port = (MSS_StatusQueryBindingStub)this.mssService.getMSS_StatusQueryPort(this.MSSP_ST_URL);
            } else if (req instanceof MSSProfileReq) {
                port = (MSS_ProfileQueryBindingStub)this.mssService.getMSS_ProfileQueryPort(this.MSSP_PR_URL);
            } else if (req instanceof MSSRegistrationReq) {
                port = (MSS_RegistrationBindingStub)this.mssService.getMSS_RegistrationPort(this.MSSP_RG_URL);
            }

            if (port == null) {
                throw new IOException("Invalid request type");
            }
            if (timeout != null) {
                // ETSI TS 102 204 defines TimeOut in seconds instead of milliseconds
                port.setTimeout(timeout.intValue()*1000);
            }
        } catch (ServiceException se) {
            log.error("Failed to get port: " + se.getMessage());
            throw new IOException(se.getMessage());
        }
        try {
            if (port._getCall() == null) {
                port._createCall();
            }
        } catch (Exception e) {
            log.fatal("Could not do port._createCall()", e);
        }

        // Set tools for each context.
        port.setProperty(ComponentsHTTPSender.HTTPCLIENT_INSTANCE, this.getHttpClient());

        MessageAbstractType resp = null;
        
        if (port instanceof MSS_SignatureBindingStub) {
            resp = ((MSS_SignatureBindingStub)port).MSS_Signature((MSSSignatureReq)req);
        } else if (port instanceof MSS_StatusQueryBindingStub) {
            resp = ((MSS_StatusQueryBindingStub)port).MSS_StatusQuery((MSSStatusReq)req);
        } else if (port instanceof MSS_ReceiptBindingStub) {
            resp = ((MSS_ReceiptBindingStub)port).MSS_Receipt((MSSReceiptReq)req);
        } else if (port instanceof MSS_HandshakeBindingStub) {
            resp = ((MSS_HandshakeBindingStub)port).MSS_Handshake((MSSHandshakeReq)req);
        } else if (port instanceof MSS_ProfileQueryBindingStub) {
            resp = ((MSS_ProfileQueryBindingStub)port).MSS_ProfileQuery((MSSProfileReq)req);
        } else if (port instanceof MSS_RegistrationBindingStub) {
            resp = ((MSS_RegistrationBindingStub)port).MSS_Registration((MSSRegistrationReq)req, security);
        } else {
            throw new IOException("Invalid call parameters");
        }
        
        if (context != null) {
            try {
                context.setMessageContext(port.getMessageContext());
            } catch (ServiceException e) {
                log.warn("Unable to pass context", e);
            }
        }
        
        return resp;
    }

}
