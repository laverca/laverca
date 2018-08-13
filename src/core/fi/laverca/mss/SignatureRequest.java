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

import fi.laverca.jaxb.mss.MSSSignatureReq;
import fi.laverca.jaxb.mss.MSSSignatureResp;
import fi.laverca.jaxb.mss.MessageAbstractType;
import fi.laverca.jaxb.mss.MessagingModeType;

public class SignatureRequest extends MssRequest<SignatureResponse> {

    private final MSSSignatureReq sigReq;
    
    public SignatureRequest(final MSSSignatureReq sigReq) {
        this.sigReq  = sigReq;
        this.type    = Type.SIGNATURE;
    }

    @Override
    public MSSSignatureReq getRawReq() {
        return this.sigReq;
    }
    
    public boolean isSynchronous() {
        if (this.sigReq == null) return false;
        return MessagingModeType.SYNCH == this.sigReq.getMessagingMode();
    }

    @Override
    public SignatureResponse createResp(final MessageAbstractType t) {
        if (t instanceof MSSSignatureResp) {
            return new SignatureResponse((MSSSignatureResp)t);
        } else {
            throw new IllegalArgumentException("Not a SignatureResponse");
        }
    }

}
