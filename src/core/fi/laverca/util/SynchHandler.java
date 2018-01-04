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

package fi.laverca.util;

import fi.laverca.ProgressUpdate;
import fi.laverca.ResponseHandler;

/** 
 * Interface for MSS response handling
 * 
 */ 
public class SynchHandler<Req, Resp> implements ResponseHandler<Req, Resp> {

    @Override
    public void onError(final Req req, final Throwable t) {
        throw new SynchHandlerException(t);
    }
    
    @Override
    public void onResponse(final Req req, final Resp resp) {
        // Ignore
    }
    
    @Override
    public void onOutstandingProgress(final Req req, final ProgressUpdate prgUpdate) {
        // Ignore
    }
    
    public static class SynchHandlerException extends RuntimeException {
        public SynchHandlerException(final Throwable t) {
            super(t);
        }
    }
    
}
