package fi.laverca;

public interface FiComResponseHandler {

    void onError(FiComRequest req, Throwable throwable);
    
    void onResponse(FiComRequest req, FiComResponse resp);
    
    void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate);
    
}
