/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2021 Laverca Project
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.net.ssl.SSLSocketFactory;

import fi.laverca.LavercaException;
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
import fi.laverca.mss.ws.LavercaAxis2Client;
import fi.laverca.util.DTBS;
import fi.laverca.util.IssuerTrustManager;
import fi.laverca.util.JMarshallerFactory;
import fi.laverca.util.LavercaContext;
import fi.laverca.util.LavercaHttpClient;
import fi.laverca.util.LavercaSSLTrustManager;
import fi.laverca.util.ProxySettings;
import fi.laverca.util.ReloadingKeyManager;
import fi.laverca.util.ReloadingSSLSocketFactory;
import fi.laverca.util.ReloadingTrustManager;

/**
 * A raw ETSI TS 102 204 client object.
 */ 
public class MssClient {

    public static ObjectFactory mssObjFactory = new ObjectFactory();

    /** XML 1.0 (5th edition) NameStartChar without ':'. */
    private static final String NCNAME_START =
        "A-Z_a-z\\u00C0-\\u00D6\\u00D8-\\u00F6\\u00F8-\\u02FF\\u0370-\\u037D\\u037F-\\u1FFF\\u200C-\\u200D"
        + "\\u2070-\\u218F\\u2C00-\\u2FEF\\u3001-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFFD\\x{10000}-\\x{EFFFF}";
    /** xs:NCName: an XML Name without ':'. */
    private static final Pattern NCNAME =
        Pattern.compile("[" + NCNAME_START + "][" + NCNAME_START + "\\-.0-9\\u00B7\\u0300-\\u036F\\u203F-\\u2040]*");
    
    /**
     * Note: TLSv1.2 is obsolete by 2020..  but it depends upon your Java runtime what you can use.
     * See {@link #setTLSContextName(String)} for a method to set it <i>before</i> calling
     * the constructor {@link #MssClient(MssConf)}.
     */
    private static String TLSContextName = "TLSv1.2";
    
    private static boolean marshallerInitDone;
    // AP settings
    private String apId  = null;
    private String apPwd = null;
    
    // MSSP AE connection settings
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

    /** The current connection pool, built on the first call; null until then and after a setter changed its settings. */
    private PoolUse pool;

    private List<byte[]> expectedServerCerts;
    private List<String> msspUri = new ArrayList<>();
    
    /**
     * Initialize the MssClient instance with supplied configuration.
     * <p>
     * <b>NOTE:</b> {@link #setSSLSocketFactory(SSLSocketFactory)} is automatically run with {@link MssConf#createSSLFactory()}:
     * the keystore's key, if any, is the client certificate, and the server is checked against the truststore, if any;
     * without a truststore any server certificate is accepted. 
     *
     * @param conf MSS Configuration object (not null)
     * @throws LavercaException if SSL keystore fails to load
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
        
        // Also without a keystore and a truststore: then no client certificate is sent and any server certificate is accepted
        try {
            this.setSSLSocketFactory(conf.createSSLFactory());
            this.expectedServerCerts = LavercaSSLTrustManager.getInstance().getExpectedServerCerts();
        } catch (Exception e) {
            throw new LavercaException("Failed to load keystore and/or truststore", e);
        }
        this.msspUri = conf.getMsspUri();
    }

    /**
     * Initialize the MssClient instance with supplied configuration.
     * <p>
     * <b>NOTE:</b> This does not auto-initialize the keystore/truststore settings
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
     * Initialize the MssClient instance with supplied configuration.
     * <p>
     * <b>NOTE:</b> This does not auto-initialize the keystore/truststore settings
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

        this.setAeAddress(msspSignatureUrl,
                          msspStatusUrl,
                          msspReceiptUrl,
                          msspRegistrationUrl,
                          msspProfileUrl,
                          msspHandshakeUrl);

        MssClient.marshallerInit();
    }
    
    /**
     * Create an SSLSocketFactory
     * 
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
     * Create an SSLSocketFactory.
     * <p>
     * The server certificate must be one of the truststore's certificates; without a truststore any server certificate
     * is accepted. The keystore and the truststore are reloaded when their files change.
     * 
     * @param ksFile Keystore filename
     * @param ksPwd  Keystore password
     * @param ksType Keystore type
     * @param tsFile Truststore filename
     * @param tsPwd  Truststore password
     * @param tsType Truststore type
     * @return Created SSLSocketFactory
     * @throws IOException if the keystore or truststore cannot be read
     * @throws GeneralSecurityException if there is any security exception related to accessing the keystore or truststore
     * @deprecated Use {@link MssConf#createSSLFactory()} instead
     */
    public static SSLSocketFactory createSSLFactory(final String ksFile, final String ksPwd, final String ksType,
                                                    final String tsFile, final String tsPwd, final String tsType) 
        throws GeneralSecurityException, IOException 
    {
        final ReloadingKeyManager km = (ksFile != null)
            ? new ReloadingKeyManager(ksFile, ksPwd, ksType, ReloadingKeyManager.DEFAULT_CHECK_INTERVAL)
            : null;
        final ReloadingTrustManager tm = (tsFile != null)
            ? new ReloadingTrustManager(tsFile, tsPwd, tsType, ReloadingTrustManager.DEFAULT_CHECK_INTERVAL, false, null)
            : null;
        if (tm != null) {
            // For callers of the global trust manager, as before 4.0.0; MssClient no longer uses it with this factory
            final List<byte[]> certs = new ArrayList<>();
            for (final X509Certificate c : tm.getCertificates()) {
                certs.add(c.getEncoded());
            }
            LavercaSSLTrustManager.getInstance().setExpectedServerCerts(certs);
        }
        return ReloadingSSLSocketFactory.create(TLSContextName, km, tm);
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
        }
        return NCNAME.matcher(s).matches();
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
        this.rebuildHttpClient();
    }

    /**
     * Set HTTP Client pool size
     * @param poolSize pool size
     */
    public void setPoolSize(final int poolSize) {
        this.poolSize = poolSize;
        this.rebuildHttpClient();
    }

    /**
     * Set socket timeout for HTTP Client
     * @param socketTimeout Timeout in milliseconds
     */
    public void setSocketTimeout(final int socketTimeout) {
        this.newSoTimeout = socketTimeout;
        this.rebuildHttpClient();
    }

    /**
     * Set the TLS socket factory for the MSS calls, e.g. to send your client certificate.
     *
     * @param ssf Define a SSL SocketFactory with a client side key
     */
    public void setSSLSocketFactory(final SSLSocketFactory ssf) {
        this.sslSocketFactory = ssf;
        this.rebuildHttpClient();
    }

    /**
     * Set an HTTP proxy for the MSS calls.
     *
     * @param proxySettings proxy host, port and optional credentials; null for none
     */
    public void setProxySettings(final ProxySettings proxySettings) {
        this.proxySettings = proxySettings;
        this.rebuildHttpClient();
    }

    /**
     * Set HTTP Basic authentication credentials for the MSS calls, sent preemptively.
     * Without them, credentials in the AE URL's user info part are used.
     *
     * @param username user name; null for none
     * @param password password
     */
    public void setHttpCredentials(final String username, final String password) {
        this.newUsername = username;
        this.newPassword = password;
        this.rebuildHttpClient();
    }

    /**
     * A connection pool and the calls in progress on it.
     */
    private static final class PoolUse {
        final LavercaHttpClient client;
        /** The client key generation the pool was built with. */
        final long keyGeneration;
        int     calls;
        boolean retired;

        PoolUse(final LavercaHttpClient client, final long keyGeneration) {
            this.client        = client;
            this.keyGeneration = keyGeneration;
        }
    }

    /**
     * Gets the current connection pool for a call, building it with the current settings if needed.
     * A pool whose connections were made with an older client key is retired first.
     * Each call must be followed by {@link #releaseHttpClient(PoolUse)}.
     *
     * @return the pool, with this call counted on it
     */
    private PoolUse borrowHttpClient() {
        final long generation = (this.sslSocketFactory instanceof ReloadingSSLSocketFactory r) ? r.refresh() : 0;

        final PoolUse use;
        PoolUse toClose = null;
        synchronized (this) {
            if (this.pool != null && this.pool.keyGeneration != generation) {
                toClose = this.retirePool();
            }
            if (this.pool == null) {
                // If SSLSocketFactory is set, use it, otherwise use system default
                SSLSocketFactory ssf = this.sslSocketFactory;
                if (ssf == null) {
                    ssf = (SSLSocketFactory)SSLSocketFactory.getDefault();
                }
                this.pool = new PoolUse(new LavercaHttpClient("mssClientPool",
                                                                    this.poolSize,
                                                                    this.newConnTimeout,
                                                                    this.newSoTimeout,
                                                                    this.newUsername,
                                                                    this.newPassword,
                                                                    this.proxySettings,
                                                                    ssf),
                                        generation);
            }
            this.pool.calls++;
            use = this.pool;
        }
        if (toClose != null) {
            closeQuietly(toClose.client);
        }
        return use;
    }

    /**
     * Ends a call on a pool from {@link #borrowHttpClient()}, closing the pool if it was retired and this was its last call.
     *
     * @param use the pool the call used
     */
    private void releaseHttpClient(final PoolUse use) {
        final boolean close;
        synchronized (this) {
            use.calls--;
            close = use.retired && use.calls == 0;
        }
        if (close) {
            closeQuietly(use.client);
        }
    }

    /**
     * Retires the current connection pool after a settings change: the next call builds a new one.
     * The old pool is closed now, or when its last call in progress ends.
     */
    private void rebuildHttpClient() {
        final PoolUse toClose;
        synchronized (this) {
            toClose = this.retirePool();
        }
        if (toClose != null) {
            closeQuietly(toClose.client);
        }
    }

    /**
     * Retires the current connection pool; the caller holds this object's lock.
     *
     * @return the old pool if it has no calls in progress and should be closed now, else null
     */
    private PoolUse retirePool() {
        final PoolUse old = this.pool;
        if (old == null) {
            return null;
        }
        this.pool = null;
        old.retired = true;
        return (old.calls == 0) ? old : null;
    }

    /**
     * Closes a retired connection pool.
     *
     * @param client the pool's client
     */
    private static void closeQuietly(final LavercaHttpClient client) {
        try {
            client.close();
        } catch (IOException e) {
            // Nothing to do: the pool is no longer used
        }
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
     * @param context Context, may be null
     * @return the response
     * @throws IOException if the call fails; a {@link fi.laverca.LavercaSoapFaultException} for a SOAP fault or a failed connection
     */
    private MessageAbstractType sendMat(final MessageAbstractType req, final LavercaContext context)
        throws IOException
    {
        return this.sendMat(req, context, null);
    }

    /**
     * Sends an MSS request.
     *
     * @param req Abstract request type
     * @param context Context, may be null
     * @param security WSSE security header, may be null
     * @return the response
     * @throws IOException if the call fails; a {@link fi.laverca.LavercaSoapFaultException} for a SOAP fault or a failed connection
     */
    private MessageAbstractType sendMat(final MessageAbstractType req, final LavercaContext context, final Security security)
        throws IOException
    {
        final String operation;
        final URL url;
        final Class<? extends MessageAbstractType> respClass;
        long timeout = 0;

        if (req instanceof MSSSignatureReq sigReq) {
            operation = "MSS_Signature";
            url       = this.MSSP_SI_URL;
            respClass = MSSSignatureResp.class;
            if (sigReq.getTimeOut() != null) {
                // ETSI TS 102 204 defines TimeOut in seconds; add 5 seconds to let the backend time out first
                timeout = sigReq.getTimeOut().longValue() * 1000 + 5000;
            }
        } else if (req instanceof MSSReceiptReq) {
            operation = "MSS_Receipt";
            url       = this.MSSP_RC_URL;
            respClass = MSSReceiptResp.class;
        } else if (req instanceof MSSHandshakeReq) {
            operation = "MSS_Handshake";
            url       = this.MSSP_HS_URL;
            respClass = MSSHandshakeResp.class;
        } else if (req instanceof MSSStatusReq) {
            operation = "MSS_StatusQuery";
            url       = this.MSSP_ST_URL;
            respClass = MSSStatusResp.class;
        } else if (req instanceof MSSProfileReq) {
            operation = "MSS_ProfileQuery";
            url       = this.MSSP_PR_URL;
            respClass = MSSProfileResp.class;
        } else if (req instanceof MSSRegistrationReq) {
            operation = "MSS_Registration";
            url       = this.MSSP_RG_URL;
            respClass = MSSRegistrationResp.class;
        } else {
            throw new IOException("Invalid request type");
        }
        if (timeout == 0 && this.newSoTimeout > 0) {
            timeout = this.newSoTimeout;
        }

        LavercaSSLTrustManager.getInstance().setExpectedServerCerts(this.expectedServerCerts);
        // Add MSSP URI as expected SAN
        IssuerTrustManager.getInstance().addExpectedMsspURI(this.msspUri);
        if (req.getMSSPInfo() != null && req.getMSSPInfo().getMSSPID() != null) { 
            IssuerTrustManager.getInstance().addExpectedMsspURI(req.getMSSPInfo().getMSSPID().getURI());
        }
        final PoolUse pool = this.borrowHttpClient();
        try {
            final Object header = (req instanceof MSSRegistrationReq) ? security : null;
            return LavercaAxis2Client.send(operation, req, respClass, url, pool.client, timeout, header, context);
        } finally {
            this.releaseHttpClient(pool);
            // Clear the expected server certificate set from this thread
            LavercaSSLTrustManager.getInstance().setExpectedServerCerts(null);
        }
    }

    /**
     * Set specific TLS protocol version name, if necessary.
     * @param name  Use "TLSv1.3"  or later
     */
    public static void setTLSContextName(String name) {
        TLSContextName = name;
    }
    
    /**
     * Get the specific TLS protocol version name
     * @return TLS protocol version name
     */
    public static String getTLSContextName() {
        return TLSContextName;
    }
    
}
