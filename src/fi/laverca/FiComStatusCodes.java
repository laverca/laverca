package fi.laverca;

/**
 * As per FiCom Soveltamisohje v2.0
 *
 * @author Asko Saura (asko@methics.fi)
 */
public class FiComStatusCodes {

    public final static int NO_SECONDARY_CODE = -1;

    public final static StatusCode      REQUEST_OK                       = new StatusCode(100);
    public final static StatusCode      SIGNATURE                        = new StatusCode(501);
    public final static StatusCode      REVOKED_CERTIFICATE              = new StatusCode(501);
    public final static StatusCode      VALID_SIGNATURE                  = new StatusCode(502);
    public final static StatusCode      INVALID_SIGNATURE                = new StatusCode(503);
    public final static StatusCode      OUTSTANDING_TRANSACTION          = new StatusCode(504);
    public final static StatusCode      CONSTRAINT_MISMATCH              = new StatusCode(505);
    
    static class StatusCode {
        private final int primaryCode;
        
        public StatusCode(int primary) {
            this.primaryCode = primary;
        }
        
        public int getValue(){
        	return primaryCode;
        }
    }

}
