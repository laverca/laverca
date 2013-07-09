/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2013 Laverca Project
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
 * Information about outstanding transaction.
 * Sent periodically.
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
