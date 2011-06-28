package fi.laverca;

/**
 * Information about outstanding transaction.
 * Sent periodically.
 * 
 * @author Jan Mikael Lindlöf (mikael.lindlof@nbl.fi)
 * @author Eemeli Miettinen (eemeli.miettinen@methics.fi) 
 */
public class ProgressUpdate {
	
	long initTime;
	long timeout;
	
	/**
	 * 
	 * @param timeout Maximum time of the progress in milliseconds.
	 * @param initTime Progress starting time in System.currentTimeMillis() format.
	 */
	public ProgressUpdate(long timeout, long initTime) {
		this.initTime = initTime;
		setTimeout(timeout);
	}
	
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
	public long getElapsedTime() {
		return System.currentTimeMillis() - initTime;
	}
	
	public long getTimeLeft() {
		return timeout + initTime - System.currentTimeMillis();
	}
}
