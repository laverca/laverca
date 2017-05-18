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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.net.ssl.SSLSocketFactory;

import org.apache.axis.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
import fi.laverca.mss.MssException;
import fi.laverca.mss.MssRequest;
import fi.laverca.mss.MssResponse;

/**
 * Abstract base class for ETSI TS 102 204 Signature operations
 *
 */
public abstract class ClientBase<Req extends MssRequest<Resp>, Resp extends MssResponse> {

    protected static final Log log = LogFactory.getLog(ClientBase.class);

    protected MssClient       mssClient;
    protected ExecutorService threadExecutor; 

    private static final long DEFAULT_INITIAL_WAIT    = 5 * 1000;      // Initial wait 5s   
    private static final long DEFAULT_SUBSEQUENT_WAIT = 2 * 1000;      // Subsequent wait 2s
    private static final long DEFAULT_TIMEOUT         = 3 * 1000 * 60; // Timeout 3 min

    protected long initialWait    = DEFAULT_INITIAL_WAIT;
    protected long subsequentWait = DEFAULT_SUBSEQUENT_WAIT;
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
        this.mssClient = new MssClient(apId, 
                                       apPwd, 
                                       msspSignatureUrl, 
                                       msspStatusUrl, 
                                       msspReceiptUrl);

        this.threadExecutor = Executors.newCachedThreadPool();
    }

    /**
     * Sends a request and waits for the response.
     * Polls the server with status requests while waiting.
     * 
     * All responses are delivered to the given response handler.
     * 
     * @param req The request object to send
     * @param handler A response handler for receiving asynch responses.
     * @return The request object being sent. This can be used to cancel the polling.
     * @throws IllegalArgumentException if handler is null 
     * @throws IOException if an IOException was caught when sending the request.
     */
    public Req call(final Req req, 
                    final ResponseHandler<Req, Resp> handler) 
        throws IOException
    {
        
        if (handler == null) {
            throw new IllegalArgumentException("Null response handler not allowed.");
        }

        MSSSignatureResp _sigResp = null;
        try {
            log.debug("Sending sigReq");
            _sigResp = this.mssClient.send(req.sigReq);
            log.debug("Got resp");
            req.sigResp = _sigResp;
        } catch (AxisFault af) {
            log.error("Got SOAP fault", af);
            handler.onError(req, af);
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
     * Initializes a FutureTask for polling for the signature via StatusRequests.
     * 
     * This is used by {@link #call(MssRequest, ResponseHandler)}.
     * 
     * @param req     The request object to send
     * @param sigResp A response to the original signature request
     * @param handler A response handler for receiving asynch responses.
     * @return A FutureTask wrapping the StatusRequest poll logic
     * @throws IOException
     */
    protected FutureTask<Resp> initializeTask(final Req req,
                                              final MSSSignatureResp sigResp,
                                              final ResponseHandler<Req, Resp> handler) 
        throws IOException
    {
        Callable<Resp> callable = new Callable<Resp>() {
            @SuppressWarnings("finally")
            @Override
            public Resp call() throws Exception {
                
                long timeout = ClientBase.this.timeout;
                long currentTimeMillis = System.currentTimeMillis();
                // Note that the transaction generally times out at the server at 180 s 
                long deadline = currentTimeMillis + timeout;
                
                Resp resp = null;
                ProgressUpdate update = new ProgressUpdate(timeout, currentTimeMillis);

                MSSStatusResp statResp = null;
                long waitPeriod = ClientBase.this.initialWait;
                long now        = System.currentTimeMillis();
                
                while (true) {

                    // Sleep for the rest of the interval
                    Thread.sleep(waitPeriod - (System.currentTimeMillis()-now));
                    now        = System.currentTimeMillis();
                    waitPeriod = ClientBase.this.subsequentWait; 
                    
                    if (now > deadline) {
                        log.trace("Timed out");
                        try {
                            handler.onError(req, new MssException("Timed out"));
                        } finally {
                            break;
                        }
                    }
                    MSSStatusReq statReq = null;
                    try {
                        statReq = ClientBase.this.mssClient.createStatusRequest(sigResp, req.sigReq.getAPInfo().getAPTransID());
                    } catch (Throwable t){
                        log.trace("Failed creating status request", t);
                        try {
                            handler.onError(req, t);
                        } 
                        finally {
                            break;
                        }
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

                            try {
                                handler.onResponse(req, resp);
                            } finally {
                                break;                              
                            }
                        } else {
                            log.warn("Got an abnormal Status Response. (" + statusCode  + ") Ending the wait.");
                            MssException fe = new MssException("Abnormal status code " + statusCode);
                            try {
                                handler.onError(req, fe);
                            } finally {
                                break;
                            }
                        }

                    } catch (AxisFault af) {
                        log.trace("Got SOAP fault", af);
                        try {
                            handler.onError(req, af);
                        } finally {
                            break;
                        }
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
     * @param fiResp The receipt request to send
     * @param message Message to display 
     * @return received receipt response
     * @throws IOException if the receipt sending fails
     * @throws IllegalArgumentException if the given Response is null
     */
    public MSSReceiptResp sendReceiptReq(final Resp fiResp, 
                                         final String message) 
        throws IOException
    {    
        if (fiResp == null) {
            throw new IllegalArgumentException("Can't send receipt wihtout a proper response");
        }
        
        log.debug("Sending receipt");
        final MSSReceiptReq receiptReq = 
            this.mssClient.createReceiptRequest(fiResp.originalSigResp, 
                                                fiResp.originalSigReq.getAPInfo().getAPTransID(), 
                                                message);
        receiptReq.setMobileUser(fiResp.originalSigReq.getMobileUser());
        return this.mssClient.send(receiptReq);
    }

    private static final long NO_STATUS = -1; // -1 is not used by ETSI or by FiCom
    
    protected long parseStatus(final StatusType status) {
        if (status == null) return NO_STATUS;
        
        final StatusCodeType sc = status.getStatusCode();
        if (sc == null) return NO_STATUS;
        
        return sc.getValue();
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

}
