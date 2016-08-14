package fi.laverca;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.axis.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.MSS_ReceiptReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_ReceiptResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_Signature;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_StatusResp;
import org.etsi.uri.TS102204.v1_1_2.Status;
import org.etsi.uri.TS102204.v1_1_2.StatusCode;

import fi.laverca.etsi.EtsiClient;
import fi.laverca.ficom.FiComException;
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
     * @param apId
     * @param apPwd
     * @param msspSignatureUrl
     * @param msspStatusUrl
     * @param msspReceiptUrl
     * @throws IllegalArgumentException
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
        
        if(handler == null) {
            throw new IllegalArgumentException("Null response handler not allowed.");
        }

        MSS_SignatureResp _sigResp = null;
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
        
        final MSS_SignatureResp fSigResp = _sigResp;
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

                    MSS_StatusResp statResp = null;
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
                        MSS_StatusReq  statReq = null;
                        try {
                            statReq = mssClient.createStatusRequest(fSigResp, req.sigReq.getAP_Info().getAP_TransID());
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

                            if(StatusCodes.OUTSTANDING_TRANSACTION.getValue() == statusCode) {
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
     * @param message 
     * @throws IOException if the receipt sending fails
     * @throws IllegalArgumentException if the given Response is null
     */
    public MSS_ReceiptResp sendReceiptReq(final Resp fiResp, 
                                          final String message) 
        throws IOException
    {    
        if(fiResp == null) {
            throw new IllegalArgumentException("Can't send receipt wihtout a proper response");
        }
        
        log.debug("Sending receipt");
        MSS_ReceiptReq receiptReq = this.mssClient.createReceiptRequest(fiResp.originalSigResp, 
                                                                    fiResp.originalSigReq.getAP_Info().getAP_TransID(), 
                                                                    message);
        receiptReq.setMobileUser(fiResp.originalSigReq.getMobileUser());
        return this.mssClient.send(receiptReq);
    }

    private static final long NO_STATUS = -1; // -1 is not used by ETSI or by FiCom
    protected long parseStatus(final Status status) {
        if(status == null)
            return NO_STATUS;
        StatusCode sc = status.getStatusCode();
        if(sc == null)
            return NO_STATUS;
        return sc.getValue();
    }

    /**
     * Checks whether the given StatusResp contains an MSS_Signature
     * @param statResp
     * @return true if signature was found, false otherwise
     */
    protected static boolean isDone(MSS_StatusResp statResp) {
        if(statResp == null) {
            return false;
        }
        MSS_Signature sig = statResp.getMSS_Signature();
        return sig != null;
    }

    /**
     * Shuts down all started threads.
     */
    public void shutdown() {
        try {
            this.threadExecutor.shutdown();
        } catch (Throwable t) {
            log.trace("FiComClient failed to shut down properly", t);
        }
    }
    
    /**
     * Create a Resp class from the given params
     * @param sigReq MSS_SignatureReq
     * @param sigResp MSS_SignatureResp
     * @param statResp MSS_StatusResp
     * @return created Resp instance
     */
    protected abstract Resp createResp(final MSS_SignatureReq  sigReq,
                                       final MSS_SignatureResp sigResp,
                                       final MSS_StatusResp    statResp);

    
}
