package fi.laverca;

/** 
 * A PKCS7 SignedData element.
 * 
 * @author Asko Saura (asko@methics.fi)
 * @author Jan Mikael Lindlöf (mikael.lindlof@nbl.fi)
 * @author Eemeli Miettinen (eemeli.miettinen@methics.fi) 
 */ 
public interface FiComResponseHandler {

    void onError(FiComRequest req, Throwable throwable);
    
    void onResponse(FiComRequest req, FiComResponse resp);
    
    void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate);
    
}
