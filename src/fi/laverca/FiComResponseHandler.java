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
 * A PKCS7 SignedData element.
 */ 
public interface FiComResponseHandler {

    void onError(FiComRequest req, Throwable throwable);
    
    void onResponse(FiComRequest req, FiComResponse resp);
    
    void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate);
    
}
