/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2015 Laverca Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fi.laverca;

/**
 * Object containing information about an outstanding transaction.
 * (Status code 504)
 */
public class ProgressUpdate {
	
	private long initTime;
	private long timeout;
	
	/**
	 * Create a new ProgressUpdate
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
	
	/**
	 * @return time elapsed in milliseconds
	 */
	public long getElapsedTime() {
		return System.currentTimeMillis() - this.initTime;
	}
	
	/**
	 * @return time left before time out in milliseconds.
	 */
	public long getTimeLeft() {
		return this.timeout + this.initTime - System.currentTimeMillis();
	}
}
