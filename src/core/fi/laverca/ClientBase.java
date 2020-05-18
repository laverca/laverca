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

package fi.laverca;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.net.ssl.SSLSocketFactory;

import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fi.laverca.jaxb.mss.MSSProfileReq;
import fi.laverca.jaxb.mss.MSSReceiptReq;
import fi.laverca.jaxb.mss.MSSReceiptResp;
import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.mss.MSSStatusReq;
import fi.laverca.jaxb.mss.MSSStatusResp;
import fi.laverca.jaxb.mss.SignatureType;
import fi.laverca.jaxb.mss.StatusCodeType;
import fi.laverca.jaxb.mss.StatusType;
import fi.laverca.mss.MssClient;
import fi.laverca.mss.MssConf;
import fi.laverca.mss.MssException;
import fi.laverca.mss.MssRequest;
import fi.laverca.mss.MssResponse;
import fi.laverca.mss.ProfileQueryResponse;
import fi.laverca.util.LavercaContext;
import fi.laverca.util.SynchHandler;
import fi.laverca.util.SynchHandler.SynchHandlerException;

/**
 * Abstract base class for ETSI TS 102 204 Signature operations
 *
 */
public abstract class ClientBase<Req extends MssRequest<Resp>, Resp extends MssResponse> {

    protected static final Log log = LogFactory.getLog(ClientBase.class);

    protected MssClient       mssClient;
    protected ExecutorService threadExecutor; 

    private static final long DEFAULT_INITIAL_WAIT    = 2 * 1000;      // Initial wait 2s   
    private static final long DEFAULT_SUBSEQUENT_WAIT = 2 * 1000;      // Subsequent wait 2s
    private static final long DEFAULT_TIMEOUT         = 3 * 1000 * 60; // Timeout 3 min

    /**
     * Time to wait before sending the first Status request (in asynch client-server mode)
     */
    protected long initialWait    = DEFAULT_INITIAL_WAIT;
    /**
     * Time to wait between subsequent Status requests (in asynch client-server mode)
     */
    protected long subsequentWait = DEFAULT_SUBSEQUENT_WAIT;
    /**
     * Timeout after which the polling will be stopped (in asynch client-server mode)
     */
    protected long timeout        = DEFAULT_TIMEOUT;
    
    /**
     * 
     * @param apId AP_ID
     * @param apPwd AP_PWD
     * @param msspSignatureUrl URL for MSS_Signature service
     * @param msspStatusUrl URL for MSS_Status service
     * @param msspReceiptUrl URL for MSS_Receipt service
     * @throws IllegalArgumentException if apId or apPwd is null
     */
    public ClientBase(final String apId,             // AP settings
                      final String apPwd, 
                      final String msspSignatureUrl, // AE connection settings
                      final String msspStatusUrl,
                      final String msspReceiptUrl)
        throws IllegalArgumentException
    {
        this(apId, apPwd, msspSignatureUrl, msspStatusUrl, msspReceiptUrl, null, null, null);
    }
    
    /**
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
     * @throws IllegalArgumentException if apId or apPwd is null
     */
    public ClientBase(final String apId,             // AP settings
                      final String apPwd, 
                      final String msspSignatureUrl, // AE connection settings
                      final String msspStatusUrl,
                      final String msspReceiptUrl,
                      final String msspRegistrationUrl,
                      final String msspProfileUrl,
                      final String msspHandshakeUrl)
        throws IllegalArgumentException
    {
        this.mssClient = new MssClient(apId, 
                                       apPwd, 
                                       msspSignatureUrl, 
                                       msspStatusUrl, 
                                       msspReceiptUrl,
                                       msspRegistrationUrl,
                                       msspProfileUrl,
                                       msspHandshakeUrl
                                       );

        this.threadExecutor = Executors.newCachedThreadPool();
    }
    
    public ClientBase(final MssConf conf) {
        this.mssClient = new MssClient(conf);
        this.threadExecutor = Executors.newCachedThreadPool();
    }

    /**
     * Sends a request and waits for the response.
     * Polls the server with status requests while waiting.
     * 
     * <p>Response and exceptions are mainly delivered to the given response handler.
     * May also throw SOAP faults also as IOException, which is why using 
     * {@link #send(MssRequest, ResponseHandler) or #callSynch(MssRequest)} instead is preferred.
     * 
     * <p>Usage example: 
     * <pre>
     *   try {
     *       Req req = client.call(req, new ResponseHandler&#60;Req, Resp&#62;() {
     *           &#64;Override
     *           public void onError(Req req, Throwable throwable) {
     *               // Handle error
     *           }
     *           &#64;Override
     *           public void onResponse(Req req, Resp resp) {
     *               // Handle response
     *           }
     *           &#64;Override
     *           public void onOutstandingProgress(Req req, ProgressUpdate prgUpdate) {
     *               // Handle outstanding request
     *           }
     *       });
     *   } catch (IOException e) {
     *       // Handle error
     *   }
     * </pre>
     * 
     * @param req The request object to send
     * @param handler A response handler for receiving asynch responses.
     * 
     * @return The request object being sent. This can be used to cancel the polling.
     * 
     * @throws IllegalArgumentException if handler is null 
     * @throws IOException if an IOException was caught when sending the request.
     * @deprecated Use {@link #send(MssRequest, ResponseHandler) or #callSynch(MssRequest)} instead
     */
    @Deprecated
    public Req call(final Req req, 
                    final ResponseHandler<Req, Resp> handler) 
        throws IOException
    {
        
        if (handler == null) {
            throw new IllegalArgumentException("Null response handler not allowed.");
        }

        LavercaContext   _context = new LavercaContext();
        MSSSignatureResp _sigResp = null;
        try {
            log.debug("Sending sigReq");
            _sigResp = this.mssClient.send(req.sigReq, _context);
            log.debug("Got resp");
            req.sigResp = _sigResp;
            req.context = _context;
        } catch (AxisFault af) {
            log.error("Got SOAP fault", af);
            handler.onError(req, af);
            return req;
        } catch (IOException ioe) {
            log.error("Got IOException ", ioe);
            throw ioe;
        }
        
        // Init the task and add it to the Req object
        req.ft = this.initializeTask(req, _sigResp, handler);

        log.debug("Starting calling");
        this.threadExecutor.execute(req.ft);
        
        return req;
    }
    
    /**
     * Sends a request without waiting for a response. {@link ResponseHandler} is used to deliver the response and possible errors.
     * 
     * <p>Usage example: 
     * <pre>
     *     Req req = client.send(req, ResponseHandler&#60;Req, Resp&#62;() {
     *         &#64;Override
     *         public void onError(Req req, Throwable throwable) {
     *             // Handle error
     *         }
     *         &#64;Override
     *         public void onResponse(Req req, Resp resp) {
     *             // Handle response
     *         }
     *         &#64;Override
     *         public void onOutstandingProgress(Req req, ProgressUpdate prgUpdate) {
     *             // Handle outstanding request
     *         }
     *     });
     * </pre>
     * 
     * @param req The request object to send
     * @param handler A response handler for receiving asynch responses.
     * 
     * @return The request object being sent. This can be used to cancel the polling.
     */
    public Req send(final Req req,
                    final ResponseHandler<Req, Resp> handler) 
    {

        if (handler == null) {
            throw new IllegalArgumentException("Null response handler not allowed.");
        }

        LavercaContext   _context = new LavercaContext();
        MSSSignatureResp _sigResp = null;
        try {
            log.debug("Sending sigReq");
            _sigResp = this.mssClient.send(req.sigReq, _context);
            log.debug("Got resp");
            req.sigResp = _sigResp;
            req.context = _context;

            // Init the task and add it to the Req object
            req.ft = this.initializeTask(req, _sigResp, handler);
            
        } catch (IOException ioe) {
            handler.onError(req, ioe);
            return req;
        }

        log.debug("Starting calling");
        this.threadExecutor.execute(req.ft);

        return req;
    }
    
    /**
     * Sends a request and waits for the response.
     * 
     * <p>Usage example: 
     * <pre>
     *   try {
     *       Response resp = client.send(req);
     *       // Handle response
     *   } catch (IOException ioe) {
     *       // Handle error
     *   }
     * </pre>
     * 
     * @param req The request object to send
     * @return The request object being sent. This can be used to cancel the polling.
     * 
     * @throws IOException if an HTTP communication error occurs or if the service returns a SOAP Fault
     */
    public Resp send(final Req req) 
        throws IOException 
    {
        
        LavercaContext   _context = new LavercaContext();
        MSSSignatureResp _sigResp = null;
        
        log.debug("Sending sigReq");
        _sigResp = this.mssClient.send(req.sigReq, _context);
        log.debug("Got resp");
        req.sigResp = _sigResp;
        req.context = _context;

        // Init the task and add it to the Req object
        try {
            req.ft = this.initializeTask(req, _sigResp, new SynchHandler<Req, Resp>());
        } catch (SynchHandlerException e) {
            if (e.getCause() instanceof IOException) throw e;
            else throw new IOException(e.getCause());
        }

        log.debug("Starting calling");
        this.threadExecutor.execute(req.ft);
        
        try {
            return req.waitForResponse();
        } catch (InterruptedException | ExecutionException e) {
            throw new IOException(e);
        }
    }
    
    /**
     * Initializes a FutureTask for polling for the signature via StatusRequests.
     * 
     * This is used by {@link #call(MssRequest, ResponseHandler)}.
     * 
     * @param req     The request object to send
     * @param sigResp A response to the original signature request
     * @param handler A response handler for receiving asynch responses.
     * @return A FutureTask wrapping the StatusRequest poll logic
     * 
     * @throws IOException if an HTTP communication error occurs or if the service returns a SOAP Fault
     */
    protected FutureTask<Resp> initializeTask(final Req req,
                                              final MSSSignatureResp sigResp,
                                              final ResponseHandler<Req, Resp> handler) 
        throws IOException
    {
        Callable<Resp> callable = new Callable<Resp>() {
            @Override
            public Resp call() throws Exception {
                
                long timeout = ClientBase.this.timeout;
                long now     = System.currentTimeMillis();
                
                // Note that the transaction generally times out at the server at 180 s 
                long deadline = now + timeout;
                
                Resp resp = null;
                ProgressUpdate update = new ProgressUpdate(timeout, now);

                MSSStatusResp statResp = null;
                long waitPeriod = ClientBase.this.initialWait;
                
                while (true) {

                    // Sleep for the rest of the interval
                    long timeToWait = waitPeriod - (System.currentTimeMillis()-now);
                    if  (timeToWait > 0) Thread.sleep(timeToWait);

                    now        = System.currentTimeMillis();
                    waitPeriod = ClientBase.this.subsequentWait; 
                    
                    if (now > deadline) {
                        log.trace("Timed out");
                        handler.onError(req, new MssException("Timed out"));
                        break;
                    }
                    MSSStatusReq statReq = null;
                    try {
                        statReq = ClientBase.this.mssClient.createStatusRequest(sigResp, req.sigReq.getAPInfo().getAPTransID());
                    } catch (Throwable t){
                        log.trace("Failed creating status request", t);
                        handler.onError(req, t);
                        break;
                    }
                    try {
                        log.trace("Sending statReq");
                        statResp = ClientBase.this.mssClient.send(statReq);
                        log.trace("Got statResp");

                        boolean done = isDone(statResp);
                        long statusCode = parseStatus(statResp.getStatus());

                        if (StatusCodes.OUTSTANDING_TRANSACTION.getValue() == statusCode) {
                            log.trace("Got an outstanding Status Response. Continuing to wait for a final answer.");
                            handler.onOutstandingProgress(req, update);
                            continue;
                        } else if (done) {
                            log.info("Got a final Status Response. Ending the wait.");
                            resp = ClientBase.this.createResp(req.sigReq, sigResp, statResp);

                            handler.onResponse(req, resp);
                            break;                              
                        } else {
                            log.warn("Got an abnormal Status Response. (" + statusCode  + ") Ending the wait.");
                            MssException fe = new MssException("Abnormal status code " + statusCode);
                            handler.onError(req, fe);
                            break;
                        }

                    } catch (AxisFault af) {
                        log.trace("Got SOAP fault", af);
                        handler.onError(req, af);
                        break;
                    } catch (IOException ioe) {
                        log.trace("Got IOException", ioe);
                        throw ioe;
                    }
                }
                return resp;
            }
        };

        return new FutureTask<Resp>(callable);
    }
    
    /**
     * Sends a receipt request.
     * 
     * @param fiResp The receipt request to send
     * @param message Message to display 
     * @return received receipt response
     * 
     * @throws IOException if the receipt sending fails
     * @throws IllegalArgumentException if the given Response is null
     */
    public MSSReceiptResp sendReceiptReq(final Resp fiResp, 
                                         final String message) 
        throws IOException
    {    
        if (fiResp == null) {
            throw new IllegalArgumentException("Can't send receipt without a proper response");
        }
        
        log.debug("Sending receipt");
        final MSSReceiptReq receiptReq = this.mssClient.createReceiptRequest(fiResp.getMSS_SignatureResp(), 
                                                                             fiResp.getMSS_SignatureReq().getAPInfo().getAPTransID(), 
                                                                             message);
        receiptReq.setMobileUser(fiResp.getMSS_SignatureReq().getMobileUser());
        return this.mssClient.send(receiptReq);
    }
    
    /**
     * Sends a profile query.
     * 
     * @param msisdn MSISDN of the user whose profile is to be queried 
     * @param apTransId Value for the AP_TransID element of the request. 
     * @return received receipt response
     * 
     * @throws IOException if a HTTP communication error occurs or if the service returns a SOAP Fault
     * @throws IllegalArgumentException if the given MSISDN is null
     */
    public ProfileQueryResponse sendProfileQuery(final String msisdn, String apTransId) 
        throws IOException 
    {
        MSSProfileReq profileReq = this.mssClient.createProfileRequest(msisdn, apTransId);
        return new ProfileQueryResponse(this.mssClient.send(profileReq));
    }

    private static final long NO_STATUS = -1; // -1 is not used by ETSI or by FiCom
    
    protected long parseStatus(final StatusType status) {
        if (status == null) return NO_STATUS;
        
        final StatusCodeType sc = status.getStatusCode();
        if (sc == null) return NO_STATUS;
        
        return sc.getValue().longValue();
    }

    /**
     * Checks whether the given StatusResp contains an MSS_Signature
     * @param statResp StatusResponse to check
     * @return true if signature was found, false otherwise
     */
    protected static boolean isDone(final MSSStatusResp statResp) {
        if (statResp == null) {
            return false;
        }
        final SignatureType sig = statResp.getMSSSignature();
        return sig != null;
    }

    /**
     * Shuts down all started threads.
     */
    public void shutdown() {
        try {
            this.threadExecutor.shutdown();
        } catch (Throwable t) {
            log.trace(this.getClass().getSimpleName() + " failed to shut down properly", t);
        }
    }
    
    /**
     * Create a Resp class from the given params
     * @param sigReq MSS_SignatureReq
     * @param sigResp MSS_SignatureResp
     * @param statResp MSS_StatusResp
     * @return created Resp instance
     */
    protected abstract Resp createResp(final MSSSignatureReq  sigReq,
                                       final MSSSignatureResp sigResp,
                                       final MSSStatusResp    statResp);


    /**
     * Set this socket factory before calling MSS operations,
     * if you want to e.g. inclusion of your client certificate on the outgoing calls.
     * 
     * @see fi.laverca.mss.MssClient#setSSLSocketFactory(SSLSocketFactory)
     *
     * @param ssf Define a SSL SocketFactory with a client side key
     */
    public void setSSLSocketFactory(final SSLSocketFactory ssf) {
        this.mssClient.setSSLSocketFactory(ssf);
    }
    
    /**
     * Set a custom Axis EngineConfiguration
     * @param conf Axis EngineConfiguration
     */
    public void setEngineConfiguration(final EngineConfiguration conf) {
        this.mssClient.setEngineConfiguration(conf);
    }
    
    /**
     * Get the raw {@link MssClient}
     * @return {@link MssClient} instance
     */
    public MssClient getMssClient() {
        return this.mssClient;
    }

}
