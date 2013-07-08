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

package fi.laverca.ws;

public interface MSS_SignaturePortType extends java.rmi.Remote {
    public org.etsi.uri.TS102204.v1_1_2.MSS_SignatureRespType MSS_Signature(org.etsi.uri.TS102204.v1_1_2.MSS_SignatureReqType MSS_SignatureReq) throws java.rmi.RemoteException;
}
