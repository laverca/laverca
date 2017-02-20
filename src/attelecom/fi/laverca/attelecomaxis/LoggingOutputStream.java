/**
 * 
 */
package fi.laverca.attelecomaxis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 * Delegate output stream which keeps a copy of the data written to it
 * </p>
 * @author Richard Unger
 */
public class LoggingOutputStream extends OutputStream {


	
	/**
	 * wrapped
	 */
	private OutputStream wrapped = null;

	/**
	 * the buffer where the copied data is stored
	 */
	private ByteArrayOutputStream buffer = null;

	/**
	 * Constructor
	 * @param outputStream stream to wrap
	 * @param outgoing stores copied data
	 */
	public LoggingOutputStream(OutputStream outputStream, ByteArrayOutputStream outgoing) {
		this.wrapped = outputStream;
		this.buffer = outgoing;
	}


	/**
	 * @throws IOException
	 * @see java.io.OutputStream#close()
	 */
	@Override
	public void close() throws IOException {
		wrapped.close();
	}


	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return wrapped.equals(obj);
	}


	/**
	 * @see java.io.OutputStream#flush()
	 */
	@Override
	public void flush() throws IOException {
		wrapped.flush();
	}


	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return wrapped.hashCode();
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return wrapped.toString();
	}


	/**
	 * @param b
	 * @param off
	 * @param len
	 * @throws IOException
	 * @see java.io.OutputStream#write(byte[], int, int)
	 */
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		buffer.write(b,off,len);
		wrapped.write(b, off, len);
	}


	/**
	 * @param b
	 * @throws IOException
	 * @see java.io.OutputStream#write(byte[])
	 */
	@Override
	public void write(byte[] b) throws IOException {
		buffer.write(b);
		wrapped.write(b);
	}


	/**
	 * @param b
	 * @throws IOException
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) throws IOException {
		buffer.write(b);
		wrapped.write(b);
	}
	
	
	
	
}
