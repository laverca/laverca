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

import org.apache.axis.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fi.laverca.ficom.FiComException;
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
import fi.laverca.mss.MssRequest;
import fi.laverca.mss.MssResponse;

/**
 * Abstract helper class for ETSI TS 102 204 Signature operations
 *
 */
public abstract class ClientHelper<Req extends MssRequest<Resp>, Resp extends MssResponse> {

    private static final Log log = LogFactory.getLog(ClientHelper.class);

    protected MssClient mssClient;
    protected ExecutorService threadExecutor; 

    protected int initialWait    = 5 * 1000;      // Initial wait 5s   
    protected int subsequentWait = 5 * 1000;      // Subsequent wait 5s
    protected int timeout        = 5 * 1000 * 60; // Timeout 5 min

    /**
     * 
     * @param apId AP_ID
     * @param apPwd AP_PWD
     * @param msspSignatureUrl URL for MSS_Signature service
     * @param msspStatusUrl URL for MSS_Status service
     * @param msspReceiptUrl URL for MSS_Receipt service
     * @throws IllegalArgumentException if apId or apPwd is null
     */
    public ClientHelper(final String apId,             // AP settings
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
     * @param handler FiComResponseHandler for receiving asynch responses.
     * @return req The request sent
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
        
        final MSSSignatureResp fSigResp = _sigResp;
        FutureTask<Resp> ft = 
            new FutureTask<Resp>(new Callable<Resp>() {
                @SuppressWarnings("finally")
                @Override
                public Resp call() throws Exception {
                    
                    long timeout = ClientHelper.this.timeout;
                    long currentTimeMillis = System.currentTimeMillis();
                    // Note that the transaction generally times out at the server at 180 s 
                    long deadline = currentTimeMillis + timeout;
                    
                    Resp resp = null;
                    ProgressUpdate prgUpdate = new ProgressUpdate(timeout, currentTimeMillis);

                    MSSStatusResp statResp = null;
                    int waitPeriod = initialWait;
                    long now = System.currentTimeMillis();
                    LOOP: while(true) {     
                        Thread.sleep(waitPeriod - (System.currentTimeMillis()-now));
                        now = System.currentTimeMillis();
                        waitPeriod = subsequentWait; 
                        
                        if (now > deadline) {
                            log.trace("Timed out");
                            try {
                                handler.onError(req, new FiComException("Timed out"));
                            } finally {
                                break LOOP;
                            }
                        }
                        MSSStatusReq  statReq = null;
                        try {
                            statReq = mssClient.createStatusRequest(fSigResp, req.sigReq.getAPInfo().getAPTransID());
                        } catch (Throwable t){
                            log.trace("Failed creating status request", t);
                            try {
                                handler.onError(req, t);
                            } 
                            finally {
                                break LOOP;
                            }
                        }
                        try {
                            log.trace("Sending statReq");
                            statResp = mssClient.send(statReq);
                            log.trace("Got statResp");

                            boolean done = isDone(statResp);
                            long statusCode = parseStatus(statResp.getStatus());

                            if (StatusCodes.OUTSTANDING_TRANSACTION.getValue() == statusCode) {
                                log.trace("Got an outstanding Status Response. Continuing to wait for a final answer.");
                                handler.onOutstandingProgress(req, prgUpdate);
                                continue LOOP;
                            } else if (done) {
                                log.info("Got a final Status Response. Ending the wait.");
                                resp = ClientHelper.this.createResp(req.sigReq, fSigResp, statResp);

                                try {
                                    handler.onResponse(req, resp);
                                } finally {
                                    break LOOP;                              
                                }
                            } else {
                                log.warn("Got an abnormal Status Response. (" + statusCode  + ") Ending the wait.");
                                FiComException fe = new FiComException("abnormal status code " + statusCode);
                                try {
                                    handler.onError(req, fe);
                                } finally {
                                    break LOOP;
                                }
                            }

                        } catch (AxisFault af) {
                            log.trace("Got SOAP fault", af);
                            try {
                                handler.onError(req, af);
                            } finally {
                                break LOOP;
                            }
                        } catch (IOException ioe) {
                            log.trace("Got IOException", ioe);
                            throw ioe;
                            //try {
                            //    handler.onError(fiReq, ioe);
                            //} finally {
                            //    break LOOP;
                            //}
                        }
                    }

                    return resp;
                }
            });
        req.ft = ft;

        log.debug("Starting calling");
        this.threadExecutor.execute(ft);
        
        return req;
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

    
}
