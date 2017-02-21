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

import javax.xml.namespace.QName;

import org.apache.axis.AxisFault;

public interface FaultFactory {

    /**
     * Creates the Axis fault.
     * <p>
     *
     * @param code      Fault code "Receiver" or "Sender". Who is to blame?
     * @param subcode   Fault subcode, use ETSI102204 codes here
     * @param node      Provide information about which SOAP node on the SOAP message path caused the fault to happen.
     * @param role      Role of this server, MSSP identity URI
     * @param detailText is intended to provide a human-readable explanation of the fault
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    public AxisFault createFault(String label,
                                 String code,
                                 QName subcode,
                                 QName subsubcode,
                                 String reason,
                                 String node,
                                 String role,
                                 String detailText);

    /**
     * Creates the Axis fault.
     * <p>
     *
     * @param codeQName Fault code "Receiver" or "Sender". Who is to blame?
     * @param subcode   Fault subcode, use ETSI102204 codes here
     * @param node      Provide information about which SOAP node on the SOAP message path caused the fault to happen.
     * @param role      Role of this server, MSSP identity URI
     * @param detailText is intended to provide a human-readable explanation of the fault
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    public AxisFault createFault(String label,
                                 QName codeQName,
                                 QName subcode,
                                 QName subsubcode,
                                 String reason,
                                 String node,
                                 String role,
                                 String detailText);

    /**
     * Creates Axis fault when internal error occurs. Fault code is always this server eg "Receiver"
     *
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    public AxisFault createInternalError(String explanation);

    /**
     * Creates Axis fault when internal error occurs. Fault code is always this server eg "Receiver"
     * <p>
     * Use this method only when there is no way to define the error reason.
     *
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    public AxisFault createInternalError();

    /**
     * Creates AxisFault from supplied exception; typically for use at network protocol stacks.
     * 
     * @param e Received exception
     * @param didTimeout non-null value tells if a timeout happened
     * @param remoteURL non-null value pointing to resource URL
     */
    public AxisFault makeFault(java.lang.Exception e, Boolean didTimeout, String remoteURL);
}
