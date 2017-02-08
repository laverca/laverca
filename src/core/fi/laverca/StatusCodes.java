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
 * FiCom MSS Status Codes
 * <p>As per MSS FiCom Implementation Guideline v2.1
 */
public class StatusCodes {

    public final static int NO_SECONDARY_CODE = -1;

    public final static StatusCode      REQUEST_OK                       = new StatusCode(100);
    public final static StatusCode      SIGNATURE                        = new StatusCode(500);
    public final static StatusCode      REVOKED_CERTIFICATE              = new StatusCode(501);
    public final static StatusCode      VALID_SIGNATURE                  = new StatusCode(502);
    public final static StatusCode      INVALID_SIGNATURE                = new StatusCode(503);
    public final static StatusCode      OUTSTANDING_TRANSACTION          = new StatusCode(504);
    public final static StatusCode      CONSTRAINT_MISMATCH              = new StatusCode(505);
    
    public static class StatusCode {
        private final int primaryCode;
        
        public StatusCode(int primary) {
            this.primaryCode = primary;
        }
        
        public int getValue(){
        	return primaryCode;
        }
    }

}
