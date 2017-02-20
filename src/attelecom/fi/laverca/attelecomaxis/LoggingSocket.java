/**
 * 
 */
package fi.laverca.attelecomaxis;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p>
 * </p>
 * <p>
 * </p>
 * @author Richard Unger
 */
public class LoggingSocket extends Socket {

	/**
	 * the wrapped socket
	 */
	Socket wrappedSocket = null;
	/**
	 * baseDirectory where logfiles are stored
	 */
	File baseDir = null;
	/**
	 * dateformat for filename
	 */
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
	/**
	 * outgoing stream copy
	 */
	ByteArrayOutputStream outgoing = null;
	/**
	 * incoming stream copy
	 */
	ByteArrayOutputStream incoming = null;
	
	
	/**
	 * Constructor
	 * @param sock the socket to wrap
	 * @param httpLogDir the logging dir
	 */
	public LoggingSocket(Socket sock, String httpLogDir) {
		wrappedSocket = sock;
		baseDir = new File(httpLogDir);
		if (!baseDir.exists() || !baseDir.isDirectory() || !baseDir.canWrite())
			throw new IllegalArgumentException("The Logdirectory configured ("+httpLogDir+") is not a writeable directory.");
		resetBuffers();
	}
	
	
	
	
	
	/**
	 * <p>
	 * Reset the buffers, new buffers are created
	 * </p>
	 */
	private void resetBuffers() {
		incoming = new ByteArrayOutputStream();
		outgoing = new ByteArrayOutputStream();
	}



	/**
	 * <p>
	 * Wrap the output stream
	 * </p>
	 * @param outputStream
	 * @return a wrapper object
	 */
	private OutputStream wrapOutput(OutputStream outputStream) {
		LoggingOutputStream los = new LoggingOutputStream(outputStream,outgoing);
		return los;
	}


	/**
	 * <p>
	 * Wrap the input stream
	 * </p>
	 * @param inputStream
	 * @return a wrapper object
	 */
	private InputStream wrapInput(InputStream inputStream) {
		LoggingInputStream lis = new LoggingInputStream(inputStream,incoming);
		return lis;
	}





	/**
	 * <p>
	 * dump the logs at thios point, resetting the buffers
	 * </p>
	 */
	public void dumpLogs(){
		Date now = new Date();
		String fName = sdf.format(now);
		
		dumpToFile(fName+"_A_outgoing", outgoing.toByteArray());
		dumpToFile(fName+"_B_incoming", incoming.toByteArray());
		
		resetBuffers();
	}
	
	
	
	
	/**
	 * <p>
	 * Write the file
	 * </p>
	 * @param fName
	 * @param partAsBytes
	 */
	private void dumpToFile(String fName, byte[] partAsBytes) {
		if (baseDir == null)
			throw new IllegalArgumentException("Basedir not set in AxisDebugLogHandler!");
		File f = new File(baseDir,fName);
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
			bos.write(partAsBytes);
			bos.flush();
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("Problem writing debug file.",e);
		}
	}

	
	
	


	/**
	 * @param bindpoint
	 * @throws IOException
	 * @see java.net.Socket#bind(java.net.SocketAddress)
	 */
	@Override
	public void bind(SocketAddress bindpoint) throws IOException {
		wrappedSocket.bind(bindpoint);
	}


	/**
	 * @throws IOException
	 * @see java.net.Socket#close()
	 */
	@Override
	public void close() throws IOException {
		wrappedSocket.close();
		dumpLogs();
	}


	/**
	 * @param endpoint
	 * @param timeout
	 * @throws IOException
	 * @see java.net.Socket#connect(java.net.SocketAddress, int)
	 */
	@Override
	public void connect(SocketAddress endpoint, int timeout) throws IOException {
		wrappedSocket.connect(endpoint, timeout);
	}


	/**
	 * @param endpoint
	 * @throws IOException
	 * @see java.net.Socket#connect(java.net.SocketAddress)
	 */
	@Override
	public void connect(SocketAddress endpoint) throws IOException {
		wrappedSocket.connect(endpoint);
	}


	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return wrappedSocket.equals(obj);
	}


	/**
	 * @see java.net.Socket#getChannel()
	 */
	@Override
	public SocketChannel getChannel() {
		return wrappedSocket.getChannel();
	}


	/**
	 * @see java.net.Socket#getInetAddress()
	 */
	@Override
	public InetAddress getInetAddress() {
		return wrappedSocket.getInetAddress();
	}


	/**
	 * @see java.net.Socket#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return wrapInput(wrappedSocket.getInputStream());
	}


	/**
	 * @see java.net.Socket#getKeepAlive()
	 */
	@Override
	public boolean getKeepAlive() throws SocketException {
		return wrappedSocket.getKeepAlive();
	}


	/**
	 * @see java.net.Socket#getLocalAddress()
	 */
	@Override
	public InetAddress getLocalAddress() {
		return wrappedSocket.getLocalAddress();
	}


	/**
	 * @see java.net.Socket#getLocalPort()
	 */
	@Override
	public int getLocalPort() {
		return wrappedSocket.getLocalPort();
	}


	/**
	 * @see java.net.Socket#getLocalSocketAddress()
	 */
	@Override
	public SocketAddress getLocalSocketAddress() {
		return wrappedSocket.getLocalSocketAddress();
	}


	/**
	 * @see java.net.Socket#getOOBInline()
	 */
	@Override
	public boolean getOOBInline() throws SocketException {
		return wrappedSocket.getOOBInline();
	}


	/**
	 * @see java.net.Socket#getOutputStream()
	 */
	@Override
	public OutputStream getOutputStream() throws IOException {
		return wrapOutput(wrappedSocket.getOutputStream());
	}




	/**
	 * @see java.net.Socket#getPort()
	 */
	@Override
	public int getPort() {
		return wrappedSocket.getPort();
	}


	/**
	 * @see java.net.Socket#getReceiveBufferSize()
	 */
	@Override
	public int getReceiveBufferSize() throws SocketException {
		return wrappedSocket.getReceiveBufferSize();
	}


	/**
	 * @see java.net.Socket#getRemoteSocketAddress()
	 */
	@Override
	public SocketAddress getRemoteSocketAddress() {
		return wrappedSocket.getRemoteSocketAddress();
	}


	/**
	 * @see java.net.Socket#getReuseAddress()
	 */
	@Override
	public boolean getReuseAddress() throws SocketException {
		return wrappedSocket.getReuseAddress();
	}


	/**
	 * @see java.net.Socket#getSendBufferSize()
	 */
	@Override
	public int getSendBufferSize() throws SocketException {
		return wrappedSocket.getSendBufferSize();
	}


	/**
	 * @see java.net.Socket#getSoLinger()
	 */
	@Override
	public int getSoLinger() throws SocketException {
		return wrappedSocket.getSoLinger();
	}


	/**
	 * @see java.net.Socket#getSoTimeout()
	 */
	@Override
	public int getSoTimeout() throws SocketException {
		return wrappedSocket.getSoTimeout();
	}


	/**
	 * @see java.net.Socket#getTcpNoDelay()
	 */
	@Override
	public boolean getTcpNoDelay() throws SocketException {
		return wrappedSocket.getTcpNoDelay();
	}


	/**
	 * @see java.net.Socket#getTrafficClass()
	 */
	@Override
	public int getTrafficClass() throws SocketException {
		return wrappedSocket.getTrafficClass();
	}


	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return wrappedSocket.hashCode();
	}


	/**
	 * @see java.net.Socket#isBound()
	 */
	@Override
	public boolean isBound() {
		return wrappedSocket.isBound();
	}


	/**
	 * @see java.net.Socket#isClosed()
	 */
	@Override
	public boolean isClosed() {
		return wrappedSocket.isClosed();
	}


	/**
	 * @see java.net.Socket#isConnected()
	 */
	@Override
	public boolean isConnected() {
		return wrappedSocket.isConnected();
	}


	/**
	 * @see java.net.Socket#isInputShutdown()
	 */
	@Override
	public boolean isInputShutdown() {
		return wrappedSocket.isInputShutdown();
	}


	/**
	 * @see java.net.Socket#isOutputShutdown()
	 */
	@Override
	public boolean isOutputShutdown() {
		return wrappedSocket.isOutputShutdown();
	}


	/**
	 * @param data
	 * @throws IOException
	 * @see java.net.Socket#sendUrgentData(int)
	 */
	@Override
	public void sendUrgentData(int data) throws IOException {
		wrappedSocket.sendUrgentData(data);
	}


	/**
	 * @param on
	 * @throws SocketException
	 * @see java.net.Socket#setKeepAlive(boolean)
	 */
	@Override
	public void setKeepAlive(boolean on) throws SocketException {
		wrappedSocket.setKeepAlive(on);
	}


	/**
	 * @param on
	 * @throws SocketException
	 * @see java.net.Socket#setOOBInline(boolean)
	 */
	@Override
	public void setOOBInline(boolean on) throws SocketException {
		wrappedSocket.setOOBInline(on);
	}


	/**
	 * @param connectionTime
	 * @param latency
	 * @param bandwidth
	 * @see java.net.Socket#setPerformancePreferences(int, int, int)
	 */
	@Override
	public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
		wrappedSocket.setPerformancePreferences(connectionTime, latency, bandwidth);
	}


	/**
	 * @param size
	 * @throws SocketException
	 * @see java.net.Socket#setReceiveBufferSize(int)
	 */
	@Override
	public void setReceiveBufferSize(int size) throws SocketException {
		wrappedSocket.setReceiveBufferSize(size);
	}


	/**
	 * @param on
	 * @throws SocketException
	 * @see java.net.Socket#setReuseAddress(boolean)
	 */
	@Override
	public void setReuseAddress(boolean on) throws SocketException {
		wrappedSocket.setReuseAddress(on);
	}


	/**
	 * @param size
	 * @throws SocketException
	 * @see java.net.Socket#setSendBufferSize(int)
	 */
	@Override
	public void setSendBufferSize(int size) throws SocketException {
		wrappedSocket.setSendBufferSize(size);
	}


	/**
	 * @param on
	 * @param linger
	 * @throws SocketException
	 * @see java.net.Socket#setSoLinger(boolean, int)
	 */
	@Override
	public void setSoLinger(boolean on, int linger) throws SocketException {
		wrappedSocket.setSoLinger(on, linger);
	}


	/**
	 * @param timeout
	 * @throws SocketException
	 * @see java.net.Socket#setSoTimeout(int)
	 */
	@Override
	public void setSoTimeout(int timeout) throws SocketException {
		wrappedSocket.setSoTimeout(timeout);
	}


	/**
	 * @param on
	 * @throws SocketException
	 * @see java.net.Socket#setTcpNoDelay(boolean)
	 */
	@Override
	public void setTcpNoDelay(boolean on) throws SocketException {
		wrappedSocket.setTcpNoDelay(on);
	}


	/**
	 * @param tc
	 * @throws SocketException
	 * @see java.net.Socket#setTrafficClass(int)
	 */
	@Override
	public void setTrafficClass(int tc) throws SocketException {
		wrappedSocket.setTrafficClass(tc);
	}


	/**
	 * @throws IOException
	 * @see java.net.Socket#shutdownInput()
	 */
	@Override
	public void shutdownInput() throws IOException {
		wrappedSocket.shutdownInput();
	}


	/**
	 * @throws IOException
	 * @see java.net.Socket#shutdownOutput()
	 */
	@Override
	public void shutdownOutput() throws IOException {
		wrappedSocket.shutdownOutput();
	}


	/**
	 * @see java.net.Socket#toString()
	 */
	@Override
	public String toString() {
		return wrappedSocket.toString();
	}

	
	
	
	
	

}
