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
 * Interface for MSS response handling
 * 
 */ 
public interface ResponseHandler<Req, Resp> {

    /**
     * Called when the response is a fault
     * @param req Sent request
     * @param throwable received throwable
     */
    void onError(Req req, Throwable throwable); 
    
    /**
     * Called when the non-fault response is received
     * @param req  Sent request
     * @param resp Received response
     */
    void onResponse(Req req, Resp resp);
    
    /**
     * Called when a Status response is received but the transaction is outstanding.
     * @param req Sent request
     * @param prgUpdate Received progress update containing details on the status response
     */
    void onOutstandingProgress(Req req, ProgressUpdate prgUpdate);
    
}
