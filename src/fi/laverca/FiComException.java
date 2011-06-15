package fi.laverca;

@SuppressWarnings("serial")
public class FiComException extends Exception {

    public FiComException(String message) {
        super(message);
    }
    
    public FiComException(Throwable cause) {
        super(cause);
    }
    
}
