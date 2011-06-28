//
// Copyright Laverca project. 
// The contents of this file are subject to the Laverca License.
//

package fi.laverca;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.client.Stub;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.AP_Info;
import org.etsi.uri.TS102204.v1_1_2.AdditionalServices;
import org.etsi.uri.TS102204.v1_1_2.DataToBeDisplayed;
import org.etsi.uri.TS102204.v1_1_2.DataToBeSigned;
import org.etsi.uri.TS102204.v1_1_2.MSSP_ID;
import org.etsi.uri.TS102204.v1_1_2.MSSP_Info;
import org.etsi.uri.TS102204.v1_1_2.MSS_Format;
import org.etsi.uri.TS102204.v1_1_2.MSS_HandshakeReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_HandshakeResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_ProfileReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_ProfileResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_ReceiptReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_ReceiptResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_RegistrationReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_RegistrationResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusResp;
import org.etsi.uri.TS102204.v1_1_2.Message;
import org.etsi.uri.TS102204.v1_1_2.MessageAbstractType;
import org.etsi.uri.TS102204.v1_1_2.MobileUser;
import org.etsi.uri.TS102204.v1_1_2.SignatureProfile;
import org.etsi.uri.TS102204.v1_1_2.types.MessagingModeType;

import fi.laverca.ws.MSS_HandshakeBindingStub;
import fi.laverca.ws.MSS_ProfileQueryBindingStub;
import fi.laverca.ws.MSS_ReceiptBindingStub;
import fi.laverca.ws.MSS_RegistrationBindingStub;
import fi.laverca.ws.MSS_SignatureBindingStub;
import fi.laverca.ws.MSS_SignatureServiceLocator;
import fi.laverca.ws.MSS_StatusQueryBindingStub;

/**
 * A raw ETSI TS 102 204 client object.
 *
 * @author Asko Saura (asko@methics.fi)
 */ 
public class EtsiClient {
    private static Log log = LogFactory.getLog(EtsiClient.class);

    //protected HttpConnectionManager cm = null;

    // AP settings
    String apId = null;
    String apPwd = null;

    // MSSP AE connection settings
    final MSS_SignatureServiceLocator mssService = new MSS_SignatureServiceLocator();
    URL MSSP_SI_URL = null;
    URL MSSP_RC_URL = null;
    URL MSSP_HS_URL = null;
    URL MSSP_ST_URL = null;
    URL MSSP_PR_URL = null;
    URL MSSP_RG_URL = null;
    org.etsi.uri.TS102204.v1_1_2.MSSP_ID aeMsspId = null;

    /** 
     * NOTE that if any of the URLs require SSL, you must
     * call JvmSsl.setSSL() before sending any requests. TODO: DO SOMETHING ABOUT IT
     *
     * @param apIdentifier Your identifier; MessageAbstractType/AP_Info/AP_ID. Not null.
     * @param apPassword Your password; MessageAbstractType/AP_Info/AP_PWD. Not null.
     * @exception IllegalArgumentException if a parameter value is
     * missing or invalid.
     */
    public EtsiClient( String apId,             // AP settings
                       String apPwd, 
                       String msspSignatureUrl, // AE connection settings
                       String msspStatusUrl,
                       String msspReceiptUrl,
                       String msspRegistrationUrl,
                       String msspProfileUrl,
                       String msspHandshakeUrl
    )
    throws IllegalArgumentException
    {
        if (apId != null)
            this.apId = apId; 
        else 
            throw new IllegalArgumentException("null apId not allowed.");
        if (apPwd != null) 
            this.apPwd = apPwd;
        else 
            throw new IllegalArgumentException("null apPwd not allowed.");

        this.setAeAddress(msspSignatureUrl,
                          msspStatusUrl,
                          msspReceiptUrl,
                          msspRegistrationUrl,
                          msspProfileUrl,
                          msspHandshakeUrl);

    }

    /**
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

    void fillMatStuff(MessageAbstractType mat, String apTransId) {
        if(mat == null)
            throw new IllegalArgumentException("can't fill a null mat");
    
        // Set the interface versions. 1 for both, as per ETSI TS 102 204.
        mat.setMajorVersion(1);
        mat.setMinorVersion(1);

        // Create the AP_Info.
        AP_Info aiObject = new AP_Info();
        aiObject.setAP_ID(this.apId);
        aiObject.setAP_PWD(this.apPwd);
        if(apTransId == null) 
            throw new IllegalArgumentException("null apTransId not allowed.");
        aiObject.setAP_TransID(apTransId);
        aiObject.setInstant(new Date());
        mat.setAP_Info(aiObject);
        
        MSSP_Info miObject = new MSSP_Info();
        miObject.setMSSP_ID(new MSSP_ID()); 
        mat.setMSSP_Info(miObject);
    }
    
    
    /**
     * Creates a signature request. 
     * 
     * @param msisdn  not null.
     * @param messagingMode not null. 
     * @param dataToBeSigned  not null.
     * @param dataToBeDisplayed 
     * @param apTransId not null.
     * @param signatureProfile  not null.
     */
    public MSS_SignatureReq createSignatureRequest(String apTransId,
                                                   String msisdn,
                                                   DTBS dtbs,
                                                   String dataToBeDisplayed,
                                                   String signatureProfile,
                                                   String mss_format,
                                                   MessagingModeType messagingMode
    ) {
        MSS_SignatureReq req = new MSS_SignatureReq();
        
        fillMatStuff(req, apTransId);
        
        if(msisdn == null)
            throw new IllegalArgumentException("null msisdn is not allowed.");
        MobileUser muObject = new MobileUser();
        muObject.setMSISDN(msisdn);
        req.setMobileUser(muObject);

        if(dtbs == null)
            throw new IllegalArgumentException("null dataToBeSigned is not allowed.");
        DataToBeSigned dsObject = dtbs.toDataToBeSigned();
        req.setDataToBeSigned(dsObject);
        
        if(dataToBeDisplayed != null) {
            DataToBeDisplayed ddObject = new DataToBeDisplayed();
            ddObject.setContent(dataToBeDisplayed);
            req.setDataToBeDisplayed(ddObject);
        }
        
        if(signatureProfile == null)
            throw new IllegalArgumentException("null signatureProfile is not allowed.");
        SignatureProfile spObject = new SignatureProfile();
        spObject.setMssURI(signatureProfile);
        req.setSignatureProfile(spObject);

        if(mss_format != null) {
            MSS_Format mfObject = new MSS_Format();
            mfObject.setMssURI(mss_format);
            req.setMSS_Format(mfObject);
        }
        
        if(messagingMode == null)
            throw new IllegalArgumentException("null messagingMode is not allowed.");
        req.setMessagingMode(messagingMode);
        
        AdditionalServices additionalServices = new AdditionalServices();
        req.setAdditionalServices(additionalServices);
        
        return req;
    }

    /**
     * Create a MSS_ReceiptRequest based on received MSS_SignatureResponse
     * 
     * @param sigResp MSS_SignatureResponse on which the receipt request is constructed
     * @param apTransId each new MSS request needs a new apTransID
     * @param message 
     */
    public MSS_ReceiptReq createReceiptRequest( MSS_SignatureResp sigResp, 
                                                String apTransId,
                                                String message
                                                ) {
        MSS_ReceiptReq req = new MSS_ReceiptReq();
        
        fillMatStuff(req, apTransId);
        
        if(sigResp == null) {
            throw new IllegalArgumentException("null sigResp not allowed.");
        }
        
        if(sigResp.getMSSP_Info() == null) {
            throw new IllegalArgumentException("null sigResp.MSSP_Info not allowed.");
        }
        MSSP_ID msspId = sigResp.getMSSP_Info().getMSSP_ID();
        if(msspId == null) {
            throw new IllegalArgumentException("null sigResp.MSSP_Info.MSSP_ID not allowed.");
        }
        req.getMSSP_Info().setMSSP_ID(msspId); // fillMatStuff creates an empty MSSP_Info

        String msspTransId = sigResp.getMSSP_TransID();
        req.setMSSP_TransID(msspTransId);
        
        if(message != null) {
            Message meObject = new Message();
            meObject.setContent(message);
            req.setMessage(meObject);
        }
        
        return req;
    }
    /**
     * Create a status request for a signature response.
     * 
     * @param apTransID new AP transaction id
     * @param sigResp 
     */
    public MSS_StatusReq createStatusRequest(MSS_SignatureResp sigResp,
                                             String apTransId
                                             ) {
        MSS_StatusReq req = new MSS_StatusReq();
        
        fillMatStuff(req, apTransId);
        
        if(sigResp == null) {
            throw new IllegalArgumentException("null sigResp not allowed.");
        }
        
        if(sigResp.getMSSP_Info() == null) {
            throw new IllegalArgumentException("null sigResp.MSSP_Info not allowed.");
        }
        MSSP_ID msspId = sigResp.getMSSP_Info().getMSSP_ID();
        if(msspId == null) {
            throw new IllegalArgumentException("null sigResp.MSSP_Info.MSSP_ID not allowed.");
        }
        req.getMSSP_Info().setMSSP_ID(msspId); // fillMatStuff creates an empty MSSP_Info

        String msspTransId = sigResp.getMSSP_TransID();
        req.setMSSP_TransID(msspTransId);

        return req;
    }

    /**
     * Send the MSS_SignatureRequest to MSS system receiving answer
     * @param req the MSS_SignatureReq
     * @exception IOException if a HTTP communication error
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSS_SignatureResp send(MSS_SignatureReq req) throws IOException {
    	if(req.getAdditionalServices().getServiceCount() == 0)
    		req.setAdditionalServices(null);
        return (MSS_SignatureResp)send((MessageAbstractType)req);
    }

    /**
     * Send the MSS_ReceiptRequest to MSS system receiving answer
     * @param req the MSS_ReceiptReq
     * @exception IOException if a HTTP communication error
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSS_ReceiptResp send(MSS_ReceiptReq req) throws IOException {
        return (MSS_ReceiptResp)send((MessageAbstractType)req);
    }

    /**
     * Send the MSS_HandshakeRequest to MSS system receiving answer
     * @param req the MSS_HandshakeReq
     * @exception IOException if a HTTP communication error
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSS_HandshakeResp send(MSS_HandshakeReq req) throws IOException {
        return (MSS_HandshakeResp)send((MessageAbstractType)req);
    }

    /**
     * Send the MSS_StatusRequest to MSS system receiving answer
     * @param req the MSS_StatusReq
     * @exception IOException if a HTTP communication error
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSS_StatusResp send(MSS_StatusReq req) throws IOException {
        return (MSS_StatusResp)send((MessageAbstractType)req);
    }

    /**
     * Send the MSS_ProfileRequest to MSS system receiving answer
     * @param req the MSS_ProfileReq
     * @exception IOException if a HTTP communication error
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSS_ProfileResp send(MSS_ProfileReq req) throws IOException {
        return (MSS_ProfileResp)send((MessageAbstractType)req);
    }

    /**
     * Send the MSS_RegistrationRequest to MSS system receiving answer
     * @param req the MSS_RegistrationReq
     * @exception IOException if a HTTP communication error
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    public MSS_RegistrationResp send(MSS_RegistrationReq req) throws IOException {
        return (MSS_RegistrationResp)send((MessageAbstractType)req);
    }

    /**
     * Sends a signature request.
     *
     * @exception IOException if a HTTP communication error
     * occurred i.e. a SOAP fault was generated by the <i>local</i>
     * SOAP client stub.
     */
    private MessageAbstractType send(MessageAbstractType req)
    throws AxisFault, IOException
    {
        Stub port = null;
        try {
            long timeout = 0;

            if (req instanceof MSS_SignatureReq) {
                timeout = ((MSS_SignatureReq)req).getTimeOut();
                port = (MSS_SignatureBindingStub)mssService.getMSS_SignaturePort(MSSP_SI_URL);
            } else if (req instanceof MSS_ReceiptReq) {
                port = (MSS_ReceiptBindingStub)mssService.getMSS_ReceiptPort(MSSP_RC_URL);
                // debug..
            //} else if (req instanceof MSS_HandshakeReq) {
            //    port = (MSS_HandshakeBindingStub)mssService.getMSS_HandshakePort(MSSP_HS_URL);
            //    // debug..
            } else if (req instanceof MSS_StatusReq) {
                port = (MSS_StatusQueryBindingStub)mssService.getMSS_StatusQueryPort(MSSP_ST_URL);
                // debug..
            //} else if (req instanceof MSS_ProfileReq) {
            //    port = (MSS_ProfileQueryBindingStub)mssService.getMSS_ProfileQueryPort(MSSP_PR_URL);
            //    // debug..
            //} else if (req instanceof MSS_RegistrationReq) {
            //    port = (MSS_RegistrationBindingStub)mssService.getMSS_RegistrationPort(MSSP_RG_URL);
            }
            if (timeout > 0)
                port.setTimeout((int)(timeout*1000) + 1000);
        }
        catch (ServiceException se) {
            log.debug("ServiceException");
            throw new IOException(se.getMessage());
        }
        try {
            if (port._getCall() == null) {
                port._createCall();
            }
        } catch (Exception e) {
            // huh?  should never happen..
            log.error("Can not do port._createCall(), SHOULD NEVER HAPPEN",e);
        }

        MessageContext clientContext = port._getCall().getMessageContext();

        try {
            if (port instanceof MSS_SignatureBindingStub) {
                return ((MSS_SignatureBindingStub)port).   MSS_Signature((MSS_SignatureReq)req);
            } else if (port instanceof MSS_StatusQueryBindingStub) {
                return ((MSS_StatusQueryBindingStub)port). MSS_StatusQuery((MSS_StatusReq)req);
            } else if (port instanceof MSS_ReceiptBindingStub) {
                return ((MSS_ReceiptBindingStub)port).     MSS_Receipt((MSS_ReceiptReq)req);
            } else if (port instanceof MSS_HandshakeBindingStub) {
                return ((MSS_HandshakeBindingStub)port).   MSS_Handshake((MSS_HandshakeReq)req);
            } else if (port instanceof MSS_ProfileQueryBindingStub) {
                return ((MSS_ProfileQueryBindingStub)port).MSS_ProfileQuery((MSS_ProfileReq)req);
            } else if (port instanceof MSS_RegistrationBindingStub) {
                return ((MSS_RegistrationBindingStub)port).MSS_Registration((MSS_RegistrationReq)req);
            }
        } catch (AxisFault af) {
            log.error("AxisFault", af);
            throw af;
        } catch (RemoteException re) {
            log.error("RemoteException", re);
            throw re;
        }
        throw new IOException("Invalid call parameters");
    }



    /**
     * Return whether s is a valid xs:NCName String.
     */
    public static boolean isNCName(String s) {
        if (s == null) {
            return false;
        }
        else {
            return org.apache.axis.types.NCName.isValid(s);
        }
    }

}
