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

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeaderElement;

/**
 * Mark all SOAP headers as understood, but do nothing. 
 * This needs to get done because some MSSP systems keep
 * sending XML signatures and Axis wants us to explicitly
 * mark all headers as "processed" due to mustunderstand 
 * -rules.
 */
@SuppressWarnings("serial")
public class HeaderHandler extends BasicHandler {

    @Override
    public void invoke(MessageContext msgContext) throws AxisFault {

        final SOAPEnvelope envelope = msgContext.getResponseMessage().getSOAPEnvelope();

        for (SOAPHeaderElement she : envelope.getHeaders()) {
            she.setProcessed(true);
        }
    }
}
