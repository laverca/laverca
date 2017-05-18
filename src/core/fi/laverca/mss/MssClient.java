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
import java.util.GregorianCalendar;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;
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
import fi.laverca.util.LavercaHttpClient;
import fi.laverca.util.ProxySettings;

/**
 * A raw ETSI TS 102 204 client object.
 */ 
public class MssClient {
    private static Log log = LogFactory.getLog(MssClient.class);

    public static ObjectFactory mssObjFact = new ObjectFactory();
    
    // AP settings
    private String apId = null;
    private String apPwd = null;

    // MSSP AE connection settings
    private final MSS_SignatureServiceLocator mssService = new MSS_SignatureServiceLocator();
    private URL MSSP_SI_URL = null;
    private URL MSSP_RC_URL = null;
    private URL MSSP_HS_URL = null;
    private URL MSSP_ST_URL = null;
    private URL MSSP_PR_URL = null;
    private URL MSSP_RG_URL = null;

    private int poolSize = 16;
    /** New connection timeout: milliseconds, 0 &leq; not set */
    private int newConnTimeout;
    /** New SO read timeout: milliseconds, 0 &leq; not set */
    private int newSoTimeout;

    private String newUsername;
    private String newPassword;

    private ProxySettings     proxySettings;
    private SSLSocketFactory  sslSocketFactory;
    private LavercaHttpClient httpClient;

    private static boolean marshallerInitDone;
    
    private static void marshallerInit() {
        
        if (marshallerInitDone) return;

        // Record global package names of generated JAXB classes
        for (final Class<?> c : new Class[] {
                fi.laverca.jaxb.mss.ObjectFactory.class,
                fi.laverca.jaxb.mssfi.ObjectFactory.class,
                fi.laverca.jaxb.mid204as1.ObjectFactory.class,
                fi.laverca.jaxb.saml2a.ObjectFactory.class,
                fi.laverca.jaxb.saml2p.ObjectFactory.class,
                fi.laverca.jaxb.sco204ext1.ObjectFactory.class,
                fi.laverca.jaxb.soap12env.ObjectFactory.class,
                fi.laverca.jaxb.kiurumssp5.ObjectFactory.class }) {

            final String p = c.getPackage().getName();
            JMarshallerFactory.addJAXBPath(p);

        }

        marshallerInitDone = true;
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
     * 
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
        if (apId != null) {
            this.apId = apId;
        } else {
            throw new IllegalArgumentException("null apId not allowed.");
        }
        if (apPwd != null) {
            this.apPwd = apPwd;
        } else {
            throw new IllegalArgumentException("null apPwd not allowed.");
        }

        this.setAeAddress(msspSignatureUrl,
                          msspStatusUrl,
                          msspReceiptUrl,
                          msspRegistrationUrl,
                          msspProfileUrl,
                          msspHandshakeUrl);

        marshallerInit();
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
     * Set this socket factory before calling MSS operations,
     * if you want to e.g. inclusion of your client certificate
     * on the outgoing calls.
     *
     * @param ssf Define a SSL SocketFactory with a client side key
     */
    public void setSSLSocketFactory(SSLSocketFactory ssf) {
        this.sslSocketFactory = ssf;
    }
    
    /**
     * Create an SSLSocketFactory
     * @param ksFile Keystore filename
     * @param ksPwd  Keystore password
     * @param ksType Keystore type
     * @return Created SSLSocketFactory
     */
    public static SSLSocketFactory createSSLFactory(final String ksFile, final String ksPwd, final String ksType) 
        throws GeneralSecurityException, IOException 
    {
        KeyStore    ks = KeyStore.getInstance(ksType);
        InputStream is = new FileInputStream(ksFile);
        try {
            SSLContext ctx = SSLContext.getInstance("TLSv1");
    
            ks.load(is, ksPwd.toCharArray());
            
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks);
            ctx.init(null, tmf.getTrustManagers(), null);
            return ctx.getSocketFactory();
            
        } finally {
            is.close();
        }
    }
    
    /**
     * Set a custom Axis EngineConfiguration
     * @param conf Axis EngineConfiguration
     */
    public void setEngineConfiguration(final EngineConfiguration conf) {
        this.mssService.setEngineConfiguration(conf);
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
    public void setAeAddress( String msspSignatureUrl,
                              String msspStatusUrl,
                              String msspReceiptUrl,
                              String msspRegistrationUrl,
                              String msspProfileUrl,
                              String msspHandshakeUrl )
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
        if (mat == null)
            throw new IllegalArgumentException("can't fill a null mat");
        if (apTransId == null) 
            throw new IllegalArgumentException("null apTransId not allowed.");
    
        // Set the interface versions. 1 for both, as per ETSI TS 102 204.
        mat.setMajorVersion(Long.valueOf(1));
        mat.setMinorVersion(Long.valueOf(1));

        // Create the AP_Info.
        final MessageAbstractType.APInfo aiObject = mssObjFact.createMessageAbstractTypeAPInfo();
        aiObject.setAPID(this.apId);
        aiObject.setAPPWD(this.apPwd);
        aiObject.setAPTransID(apTransId);
        aiObject.setInstant(new GregorianCalendar());
        mat.setAPInfo(aiObject);
        
        final MessageAbstractType.MSSPInfo miObject = mssObjFact.createMessageAbstractTypeMSSPInfo();
        miObject.setMSSPID(mssObjFact.createMeshMemberType()); 

        mat.setMSSPInfo(miObject);
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
        final MSSSignatureReq req = mssObjFact.createMSSSignatureReq();
        
        this.initializeRequestMessage(req, apTransId);
        
        if (msisdn == null)
            throw new IllegalArgumentException("null msisdn is not allowed.");
        MobileUserType muObject = mssObjFact.createMobileUserType();
        muObject.setMSISDN(msisdn);
        req.setMobileUser(muObject);

        if (dtbs == null)
            throw new IllegalArgumentException("null dataToBeSigned is not allowed.");
        final DataType dsObject = dtbs.toDataToBeSigned();
        req.setDataToBeSigned(dsObject);
        
        if (dataToBeDisplayed != null) {
            final DataType ddObject = mssObjFact.createDataType();
            ddObject.setValue(dataToBeDisplayed);
            req.setDataToBeDisplayed(ddObject);
        }
        
        if (signatureProfile == null)
            throw new IllegalArgumentException("null signatureProfile is not allowed.");
        final MssURIType spObject = mssObjFact.createMssURIType();
        spObject.setMssURI(signatureProfile);
        req.setSignatureProfile(spObject);

        if(mss_format != null) {
            MssURIType mfObject = mssObjFact.createMssURIType();
            mfObject.setMssURI(mss_format);
            req.setMSSFormat(mfObject);
        }
        
        if (messagingMode == null)
            throw new IllegalArgumentException("null messagingMode is not allowed.");
        req.setMessagingMode(messagingMode);

        req.setAdditionalServices(MssClient.mssObjFact.createMSSSignatureReqAdditionalServices());
        
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
        final MSSReceiptReq req = mssObjFact.createMSSReceiptReq();
        
        this.initializeRequestMessage(req, apTransId);
        
        if (sigResp == null) {
            throw new IllegalArgumentException("null sigResp not allowed.");
        }
        
        if (sigResp.getMSSPInfo() == null) {
            throw new IllegalArgumentException("null sigResp.MSSP_Info not allowed.");
        }
        final MeshMemberType msspId = sigResp.getMSSPInfo().getMSSPID();
        if (msspId == null) {
            throw new IllegalArgumentException("null sigResp.MSSP_Info.MSSP_ID not allowed.");
        }
        req.getMSSPInfo().setMSSPID(msspId); // fillMatStuff creates an empty MSSP_Info

        final String msspTransId = sigResp.getMSSPTransID();
        req.setMSSPTransID(msspTransId);
        
        if (message != null) {
            final DataType meObject = mssObjFact.createDataType();
            meObject.setValue(message);
            req.setMessage(meObject);
        }
        
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
        MSSStatusReq req = new MSSStatusReq();
        
        this.initializeRequestMessage(req, apTransId);
        
        if (sigResp == null) {
            throw new IllegalArgumentException("null sigResp not allowed.");
        }
        
        if (sigResp.getMSSPInfo() == null) {
            throw new IllegalArgumentException("null sigResp.MSSP_Info not allowed.");
        }
        final MeshMemberType msspId = sigResp.getMSSPInfo().getMSSPID();
        if (msspId == null) {
            throw new IllegalArgumentException("null sigResp.MSSP_Info.MSSP_ID not allowed.");
        }
        req.getMSSPInfo().setMSSPID(msspId); // fillMatStuff creates an empty MSSP_Info

        final String msspTransId = sigResp.getMSSPTransID();
        req.setMSSPTransID(msspTransId);
        
        return req;
    }

    /**
     * Send the MSS_SignatureRequest to MSS system receiving answer
     * @param req the MSS_SignatureReq
     * @return received MSS_SignatureResp
     * @throws IOException if a HTTP communication error occurs
     * @throws IllegalArgumentException if req is null
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSSSignatureResp send(final MSSSignatureReq req) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null SignatureReq");
        
        if (req.getAdditionalServices() != null) {
            if (req.getAdditionalServices().getServices().size() == 0) {
                req.setAdditionalServices(null);
            }
        }
        return (MSSSignatureResp)this.sendMat(req);
    }

    /**
     * Send the MSS_ReceiptRequest to MSS system receiving answer
     * @param req the MSS_ReceiptReq
     * @return received MSS_ReceiptResp
     * @throws IOException if a HTTP communication error occurs
     * @throws IllegalArgumentException if req is null
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSSReceiptResp send(final MSSReceiptReq req) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null ReceiptReq");
        return (MSSReceiptResp)this.sendMat(req);
    }

    /**
     * Send the MSS_HandshakeRequest to MSS system receiving answer
     * @param req the MSS_HandshakeReq
     * @return received MSS_HandshakeResp
     * @throws IOException if a HTTP communication error occurs
     * @throws IllegalArgumentException if req is null
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSSHandshakeResp send(final MSSHandshakeReq req) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null HandshakeReq");
        return (MSSHandshakeResp)this.sendMat(req);
    }

    /**
     * Send the MSS_StatusRequest to MSS system receiving answer
     * @param req the MSS_StatusReq
     * @return received MSS_StatusResp
     * @throws IOException if a HTTP communication error occurs
     * @throws IllegalArgumentException if req is null
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSSStatusResp send(final MSSStatusReq req) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null StatusReq");
        return (MSSStatusResp)this.sendMat(req);
    }

    /**
     * Send the MSS_ProfileRequest to MSS system receiving answer
     * @param req the MSS_ProfileReq
     * @return received MSS_ProfileResp
     * @throws IOException if a HTTP communication error occurs
     * @throws IllegalArgumentException if req is null
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSSProfileResp send(final MSSProfileReq req) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null ProfileReq");
        return (MSSProfileResp)this.sendMat(req);
    }

    /**
     * Send the MSS_RegistrationRequest to MSS system receiving answer
     * @param req the MSS_RegistrationReq
     * @return received MSS_RegistrationResp
     * @throws IOException if a HTTP communication error occurs
     * @throws IllegalArgumentException if req is null
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSSRegistrationResp send(final MSSRegistrationReq req) throws IOException {
        if (req == null) throw new IllegalArgumentException ("Unable to send null RegistrationReq");
        return (MSSRegistrationResp)this.sendMat(req);
    }

    /**
     * Sends an MSS request.
     *
     * @param req Abstract request type
     * @throws IOException if a HTTP communication error occurred i.e. a SOAP fault was generated by the <i>local</i> SOAP client stub.
     */
    private MessageAbstractType sendMat(final MessageAbstractType req)
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
        // port.setProperty(CommonsHTTPSender.FAULTFACTORY_INSTANCE, MssClientFaultFactory.getInstance());

        if (port instanceof MSS_SignatureBindingStub) {
            return ((MSS_SignatureBindingStub)port).MSS_Signature((MSSSignatureReq)req);
        } else if (port instanceof MSS_StatusQueryBindingStub) {
            return ((MSS_StatusQueryBindingStub)port).MSS_StatusQuery((MSSStatusReq)req);
        } else if (port instanceof MSS_ReceiptBindingStub) {
            return ((MSS_ReceiptBindingStub)port).MSS_Receipt((MSSReceiptReq)req);
        } else if (port instanceof MSS_HandshakeBindingStub) {
            return ((MSS_HandshakeBindingStub)port).MSS_Handshake((MSSHandshakeReq)req);
        } else if (port instanceof MSS_ProfileQueryBindingStub) {
            return ((MSS_ProfileQueryBindingStub)port).MSS_ProfileQuery((MSSProfileReq)req);
        } else if (port instanceof MSS_RegistrationBindingStub) {
            return ((MSS_RegistrationBindingStub)port).MSS_Registration((MSSRegistrationReq)req);
        }

        throw new IOException("Invalid call parameters");
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
}
