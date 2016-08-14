package fi.laverca.mss;

public class MssException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private String statusCode;
    private String statusMessage;
    
    public MssException(final String msg) {
        super(msg);
    }
    
    public MssException(final Throwable t) {
        super(t);
    }
    
    public void setStatusCode(final String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMessage(final String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }
    
}
