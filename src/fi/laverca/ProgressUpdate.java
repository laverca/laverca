package fi.laverca;

public class ProgressUpdate {
	
	long initTime;
	long timeout;
	
	public ProgressUpdate(long timeout) {
		setInitTime();
		setTimeout(timeout);
	}
	
	public void setInitTime() {
		initTime = System.currentTimeMillis();
	}
	
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
	public long getPastTime() {
		return System.currentTimeMillis() - initTime;
	}
	
	public long getTimeLeft() {
		return timeout + initTime - System.currentTimeMillis();
	}
}
