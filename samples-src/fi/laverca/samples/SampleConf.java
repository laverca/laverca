package fi.laverca.samples;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class for getting the sample configuration properties
 * @author Eemeli Miettinen (eemeli.miettinen@methics.fi)
 *
 */
public class SampleConf {

    private static final Log log = LogFactory.getLog(SampleConf.class);
    
    public static final String CONF_LOCATION       = "conf/samples.conf";
    
    public static final String TRUSTSTORE_FILE     = "ssl.truststore.file";
    public static final String TRUSTSTORE_PASSWORD = "ssl.truststore.password";
    public static final String KEYSTORE_FILE       = "ssl.keystore.file";
    public static final String KEYSTORE_PASSWORD   = "ssl.keystore.password";
    public static final String KEYSTORE_TYPE       = "ssl.keystore.type";
    
    public static final String AP_ID               = "ap.id";
    public static final String AP_PASSWORD         = "ap.password";
    public static final String SIGNATURE_URL       = "mssp.signature.url";
    public static final String STATUS_URL          = "mssp.status.url";
    public static final String RECEIPT_URL         = "mssp.receipt.url";
    
    public static Properties getProperties(String confPath){
        File f = new File(confPath);
        Properties properties = new Properties();
        FileInputStream is;
        try {
            is = new FileInputStream(f);
            properties.load(is);
            is.close();
        } catch (Exception e) {
            log.fatal("Could not load properties");
        }
        return properties;
    }

    public static Properties getProperties(){
        return getProperties(CONF_LOCATION);
    }
    
}
