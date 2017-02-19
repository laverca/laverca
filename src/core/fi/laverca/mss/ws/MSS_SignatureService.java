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

package fi.laverca.mss.ws;

public interface MSS_SignatureService extends javax.xml.rpc.Service {
    public java.lang.String getMSS_SignaturePortAddress();

    public MSS_SignaturePortType getMSS_SignaturePort() throws javax.xml.rpc.ServiceException;

    public MSS_SignaturePortType getMSS_SignaturePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_StatusQueryPortAddress();

    public MSS_StatusQueryType getMSS_StatusQueryPort() throws javax.xml.rpc.ServiceException;

    public MSS_StatusQueryType getMSS_StatusQueryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_ReceiptPortAddress();

    public MSS_ReceiptType getMSS_ReceiptPort() throws javax.xml.rpc.ServiceException;

    public MSS_ReceiptType getMSS_ReceiptPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_NotificationPortAddress();

    public MSS_NotificationPortType getMSS_NotificationPort() throws javax.xml.rpc.ServiceException;

    public MSS_NotificationPortType getMSS_NotificationPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_RegistrationPortAddress();

    public MSS_RegistrationType getMSS_RegistrationPort() throws javax.xml.rpc.ServiceException;

    public MSS_RegistrationType getMSS_RegistrationPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_ProfileQueryPortAddress();

    public MSS_ProfileQueryType getMSS_ProfileQueryPort() throws javax.xml.rpc.ServiceException;

    public MSS_ProfileQueryType getMSS_ProfileQueryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_HandshakePortAddress();

    public MSS_HandshakePortType getMSS_HandshakePort() throws javax.xml.rpc.ServiceException;

    public MSS_HandshakePortType getMSS_HandshakePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
