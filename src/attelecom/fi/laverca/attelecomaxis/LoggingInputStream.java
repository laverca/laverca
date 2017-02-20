/**
 * 
 */
package fi.laverca.attelecomaxis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * Implements a delegate inputstream, which copies the content of its delegate as it is being read
 * </p>
 * @author Richard Unger
 */
public class LoggingInputStream extends InputStream {

	
	
	/**
	 * the delegate stream
	 */
	private InputStream wrapped = null;

	/**
	 * the buffer for the copied data
	 */
	private ByteArrayOutputStream buffer = null;

	/**
	 * @param inputStream
	 * @param incoming
	 */
	public LoggingInputStream(InputStream inputStream, ByteArrayOutputStream incoming) {
		this.wrapped = inputStream;
		this.buffer = incoming;
	}

	/**
	 * @see java.io.InputStream#available()
	 */
	@Override
	public int available() throws IOException {
		return wrapped.available();
	}

	/**
	 * @throws IOException
	 * @see java.io.InputStream#close()
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return wrapped.hashCode();
	}

	/**
	 * @param readlimit
	 * @see java.io.InputStream#mark(int)
	 */
	@Override
	public void mark(int readlimit) {
		wrapped.mark(readlimit);
	}

	/**
	 * @see java.io.InputStream#markSupported()
	 */
	@Override
	public boolean markSupported() {
		return wrapped.markSupported();
	}

	/**
	 * @see java.io.InputStream#read()
	 */
	@Override
	public int read() throws IOException {
		int b = wrapped.read();
		if (b>=0)
			buffer.write(b);
		return b;
	}

	/**
	 * @see java.io.InputStream#read(byte[], int, int)
	 */
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int actlen =  wrapped.read(b, off, len);
		if (actlen>0)
			buffer.write(b,off,actlen);
		return actlen;
	}

	/**
	 * @see java.io.InputStream#read(byte[])
	 */
	@Override
	public int read(byte[] b) throws IOException {
		int actlen = wrapped.read(b);
		if (actlen>0)
			buffer.write(b,0,actlen);
		return actlen;
	}

	/**
	 * @throws IOException
	 * @see java.io.InputStream#reset()
	 */
	@Override
	public void reset() throws IOException {
		wrapped.reset();
	}

	/**
	 * @see java.io.InputStream#skip(long)
	 */
	@Override
	public long skip(long n) throws IOException {
		return wrapped.skip(n);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return wrapped.toString();
	}

}
