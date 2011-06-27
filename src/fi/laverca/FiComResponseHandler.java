package fi.laverca;

import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;

public interface FiComResponseHandler {

    void onError(FiComRequest req, Throwable throwable);
    
    void onResponse(FiComRequest req, FiComResponse resp, MSS_SignatureResp fiSignResp);
    
    void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate, MSS_SignatureResp fiSignResp);
    
}
