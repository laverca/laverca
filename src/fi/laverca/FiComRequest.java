package fi.laverca;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureReq;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;

/** 
 * @author Asko Saura (asko@methics.fi)
 */ 
public class FiComRequest {

    MSS_SignatureReq           sigReq;
    MSS_SignatureResp          sigResp;
    FutureTask<FiComResponse>  ft;

    FiComRequest() {
        // fields are written by FiComClient during execution
    }

    /** Wait for a response. Block this thread. */
    public FiComResponse waitForResponse() throws InterruptedException, ExecutionException {
        return this.ft.get();
    }

    public void cancel() {
        this.ft.cancel(true);
    }
}
