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

package fi.laverca.examples.swisscom;

import javax.net.ssl.SSLSocketFactory;

import fi.laverca.MSS_Formats;
import fi.laverca.ProgressUpdate;
import fi.laverca.SignatureProfiles;
import fi.laverca.mss.MssClient;
import fi.laverca.mss.MssConf;
import fi.laverca.swisscom.SwisscomClient;
import fi.laverca.swisscom.SwisscomRequest;
import fi.laverca.swisscom.SwisscomResponse;
import fi.laverca.swisscom.SwisscomResponseHandler;
import fi.laverca.util.DTBS;


/**
 * Simple Swisscom signature request example
 * 
 * <ul>
 * <li>Uses SwisscomClient
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
            System.out.println("Keystore problem: " + e.getMessage());
            return;
        }

        // Create client
        SwisscomClient client = new SwisscomClient(conf.getApId(),
                                                   conf.getApPwd(),
                                                   conf.getSignatureUrl(),
                                                   conf.getStatusUrl(),
                                                   conf.getReceiptUrl());

        client.setSSLSocketFactory(ssf);
        
        // Create DataToBeSigned
        DTBS        dtbs = new DTBS("sign this", DTBS.ENCODING_UTF8);
        String apTransId = "A" + System.currentTimeMillis();
      
        SwisscomRequest req = client.createRequest(apTransId, // AP Transaction ID
                                                   dtbs,      // Data to be signed
                                                   msisdn,    // MSISDN
                                                   null,      // UserLang AdditionalService
                                                   null,      // AdditionalServices                
                                                   SignatureProfiles.SWISSCOM_AUTHENTICATION, // Signature profile
                                                   MSS_Formats.PKCS7);                        // MSS Format
                                                                            
        // Initialize response handler
        SwisscomResponseHandler handler = new SwisscomResponseHandler() {
            
            @Override
            public void onResponse(final SwisscomRequest _req, final SwisscomResponse resp) {
                System.out.println("Got a response");
                System.out.println("  StatusCode   : " + resp.getStatusCode());
                System.out.println("  StatusMessage: " + resp.getStatusMessage());
            }

            @Override
            public void onError(final SwisscomRequest _req, final Throwable t) {
                System.out.println("Got an error:");
                t.printStackTrace();
            }

            @Override
            public void onOutstandingProgress(final SwisscomRequest _req, final ProgressUpdate prgUpdate) {
                System.out.println("Got a progress update");
            }
        };
        
        client.send(req, handler);
        
        // Kill the thread pool - otherwise this example would wait 60 seconds for the thread to die
        client.shutdown();
    }

}