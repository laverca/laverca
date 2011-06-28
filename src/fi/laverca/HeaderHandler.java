//
//  (c) Copyright 2003-2008 Methics Oy. All rights reserved. 
//
package fi.laverca;

import java.util.Vector;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Mark all SOAP headers as understood, but do nothing. 
 * This needs to get done because some MSSP systems keep
 * sending XML signatures and Axis wants us to explicitly
 * mark all headers as "processed" due to mustunderstand 
 * -rules.
 *
 * @author Asko Saura (asko@methics.fi)
 */
@SuppressWarnings("serial")
public class HeaderHandler extends BasicHandler {

    static Log log = LogFactory.getLog(HeaderHandler.class);


    /**
     */
    public void invoke(MessageContext msgContext) throws AxisFault {

        SOAPEnvelope envelope = msgContext.getResponseMessage().getSOAPEnvelope();

        @SuppressWarnings("unchecked")
        Vector<SOAPHeaderElement> headers = (Vector<SOAPHeaderElement>)envelope.getHeaders(); 

        for(SOAPHeaderElement she : headers) {
            she.setProcessed(true);
        }

    }


}
