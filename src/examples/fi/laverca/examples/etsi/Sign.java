/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2017 Laverca Project
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

package fi.laverca.examples.etsi;

import java.io.IOException;

import javax.net.ssl.SSLSocketFactory;

import org.apache.axis.AxisFault;

import fi.laverca.MSS_Formats;
import fi.laverca.SignatureProfiles;
import fi.laverca.etsi.EtsiClient;
import fi.laverca.etsi.EtsiRequest;
import fi.laverca.etsi.EtsiResponse;
import fi.laverca.jaxb.mss.MessagingModeType;
import fi.laverca.mss.MssClient;
import fi.laverca.mss.MssConf;
import fi.laverca.util.DTBS;


/**
 * Simple signature request example
 * 
 * <ul>
 * <li>Uses standard EtsiClient
 * <li>Sends a SignatureRequest with DTBS "sign this"
 * <li>Uses asynch-client-server messaging mode
 * <li>No AdditionalServices
 * </ul>
 *
 */
public class Sign {
    
    /**
     * The main method
     * @param args
     */
    public static void main(final String[] args) {
       
        String msisdn = "+35847001001";
        if (args.length > 0) {
            msisdn = args[0];
        }
        
        // Load config
        MssConf conf = MssConf.fromPropertyFile("conf/examples.conf");
        
        // Setup SSL
        SSLSocketFactory ssf = null;
        try {
            System.out.println("Setting up ssl");
            ssf = MssClient.createSSLFactory(conf.getKeystore(),
                                             conf.getKeystorePwd(),
                                             conf.getKeystoreType(),
                                             conf.getTruststore(),
                                             conf.getTruststorePwd(),
                                             conf.getTruststoreType());
        } catch (Exception e) {
            System.out.println("TLS initialization problem: " + e.getMessage());
            return;
        }

        // Create client
        EtsiClient client = new EtsiClient(conf.getApId(), 
                                           conf.getApPwd(), 
                                           conf.getSignatureUrl(), 
                                           conf.getStatusUrl(), 
                                           conf.getReceiptUrl());

        client.setSSLSocketFactory(ssf);
        
        // Create DataToBeSigned
        DTBS        dtbs = new DTBS("sign this", DTBS.ENCODING_UTF8);
        String apTransId = "A" + System.currentTimeMillis();
      
        EtsiRequest req = client.createRequest(apTransId, // AP Transaction ID
                                               msisdn,    // MSISDN
                                               dtbs,      // Data to be signed
                                               dtbs.toString(),                         // Data to be displayed
                                               null,                                    // Additional services
                                               SignatureProfiles.ALAUDA_AUTHENTICATION, // Signature profile
                                               MSS_Formats.PKCS7,                       // MSS Format
                                               MessagingModeType.ASYNCH_CLIENT_SERVER);
        
        try {
            EtsiResponse resp = client.send(req);
            
            System.out.println("Got a response");
            System.out.println("  StatusCode   : " + resp.getStatusCode());
            System.out.println("  StatusMessage: " + resp.getStatusMessage());
            if (resp.hasSignature()) {
                System.out.println("  Signature    : " + resp.getSignature().getBase64Signature());
            } else {
                System.out.println("  Signature    :");
            }
            
        } catch(AxisFault af) {
            System.out.println("Got a SOAP fault:");
            af.printStackTrace();
        } catch(IOException ioe) {
            System.out.println("Got an IOException:");
            ioe.printStackTrace();
        }
        
        // Kill the thread pool - otherwise this example would wait 60 seconds for the thread to die
        client.shutdown();
    }

}