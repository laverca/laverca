package fi.laverca;

import org.etsi.uri.TS102204.v1_1_2.Description;
import org.etsi.uri.TS102204.v1_1_2.Service;

/**
 * @author Asko Saura (asko@methics.fi)
 */
public class EtsiAdditionalServices {
    
    // basic ETSI services
    public static Service createService(String uri) {
        Service s = new Service();
        Description d = new Description();
        d.setMssURI(uri);
        s.setDescription(d);
        
        return s;
    }

    
}
