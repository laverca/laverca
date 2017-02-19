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

package fi.laverca.mss;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MSSSignatureResp;

public abstract class MssRequest<Resp> {

    public MSSSignatureReq          sigReq;
    public MSSSignatureResp         sigResp;
    public FutureTask<Resp>  ft;

    public MssRequest() {
        // fields are written by the client class during execution
    }

    /** 
     * Wait for a response. 
     * <p>Blocks current thread. 
     * @return Response
     * @throws InterruptedException if the wait was interrupted
     * @throws ExecutionException if the wait was aborted
     */
    public Resp waitForResponse() throws InterruptedException, ExecutionException {
        return this.ft.get();
    }

    /**
     * Cancel the sent request.
     */
    public void cancel() {
        this.ft.cancel(true);
    }
    
}
