package fi.laverca.examples.registration;

import java.io.IOException;

import org.apache.axis.AxisFault;

import fi.laverca.mss.MssConf;
import fi.laverca.registration.MregParam;
import fi.laverca.registration.MregRequest;
import fi.laverca.registration.MregResponse;
import fi.laverca.registration.RegistrationClient;

/**
 * MSS Registration example for getting details of a single MobileUser
 * 
 * <p>Please note that the MSS Registration extension used here is currently only supported by Kiuru MSSP.
 * <p>This example runs the MReg operation "GetMobileUser", parses the output and prints it to stdout.
 */
public class GetMobileUser {

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

        // Create client
        RegistrationClient client = new RegistrationClient(conf);
        MregRequest       request = new MregRequest("GetMobileUser");
        request.setTargetMsisdn(msisdn);
 
        // Send MReg request
        try {
            MregResponse response = client.send(request);
            
            System.out.println("CODE: " + response.getStatusCode());
            System.out.println("MSG : " + response.getStatusMessage());
            System.out.println();
            
            for (MregParam param : response.getParameters()) {
                System.out.println("  " + param.getName() + ": " + param.getValue());
            }
            
        } catch (AxisFault af) {
            System.out.println("Got a SOAP fault:");
            af.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Got an IOException:");
            ioe.printStackTrace();
        }

    }
    
}
