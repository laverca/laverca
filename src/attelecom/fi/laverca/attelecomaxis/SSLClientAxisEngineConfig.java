/**
 * 
 */
package fi.laverca.attelecomaxis;

import java.io.File;
import java.util.Hashtable;

import org.apache.axis.AxisEngine;
import org.apache.axis.AxisProperties;
import org.apache.axis.ConfigurationException;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.Handler;
import org.apache.axis.SimpleChain;
import org.apache.axis.SimpleTargetedChain;
import org.apache.axis.client.HTTPTransport;
import org.apache.axis.configuration.SimpleProvider;
import org.apache.axis.encoding.TypeMappingRegistry;

import fi.laverca.util.ComponentsHTTPSender;

/**
 * <p>
 * Axis Client Configuration
 * </p>
 * @author Richard Unger
 */
public class SSLClientAxisEngineConfig extends SimpleProvider {

	/**
	 * Keystore filename
	 */
	private String keystore = null;
	/**
	 * Keystore type
	 */
	private String keystoretype = null;
	/**
	 * Keystore password
	 */
	private String keystorepin = null;
	/**
	 * Truststore filename
	 */
	private String truststore = null;
	/**
	 * Truststore PIN
	 */
	private String truststorepin = null;
	/**
	 * Truststore Type
	 */
	private String truststoreType = null;
	/**
	 * Log Handler object
	 */
	private AxisDebugLogHandler logHandler = null;
	/**
	 * Base dir for storing output files
	 */
	private File debugBaseDir = null; 
	/**
	 * true to disable XML formatting
	 */
	private boolean disablePrettyXML = true;
	/**
	 * true to enable namespace prefix optimization (see Axis docs)
	 */
	private boolean enableNamespacePrefixOptimization = false;

	/**
	 * Constructor
	 */
	public SSLClientAxisEngineConfig() {
		super();
	}

	/**
	 * @param arg0
	 */
	public SSLClientAxisEngineConfig(EngineConfiguration arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public SSLClientAxisEngineConfig(TypeMappingRegistry arg0) {
		super(arg0);
	}

	
	
	
	/**
	 * @see org.apache.axis.configuration.SimpleProvider#configureEngine(org.apache.axis.AxisEngine)
	 */
	@Override
	public void configureEngine(AxisEngine engine) throws ConfigurationException {
		super.configureEngine(engine);
		engine.refreshGlobalOptions();
	}

	
	

	/**
	 * @param keystore the keystore to set
	 */
	public void setKeystore(String keystore) {
		this.keystore = keystore;
	}

	/**
	 * @param keystorepin the keystorepin to set
	 */
	public void setKeystorePassword(String keystorepin) {
		this.keystorepin = keystorepin;
	}

	/**
	 * @param keystoretype the keystoretype to set
	 */
	public void setKeystoreType(String keystoretype) {
		this.keystoretype = keystoretype;
	}

	/**
	 * @param truststore the truststore to set
	 */
	public void setTruststore(String truststore) {
		this.truststore = truststore;
	}

	/**
	 * @param truststorepin the truststorepin to set
	 */
	public void setTruststorePassword(String truststorepin) {
		this.truststorepin = truststorepin;
	}

	/**
	 * @return the truststoreType
	 */
	public String getTruststoreType() {
		return this.truststoreType;
	}

	/**
	 * @param truststoreType the truststoreType to set
	 */
	public void setTruststoreType(String truststoreType) {
		this.truststoreType = truststoreType;
	}

	/**
	 * <p>
	 * Initialize, with logging off
	 * </p>
	 */
	public void initialize() {
		initialize(false);
	}
	
	
	/**
	 * <p>
	 * Initialize
	 * </p>
	 * @param logging true if logging is desired
	 */
	@SuppressWarnings("unchecked")
	public void initialize(boolean logging) {
	//	if (debugBaseDir==null)
	//		debugBaseDir = new File("./");
		AxisProperties.setProperty("axis.socketSecureFactory", "at.telekom.util.axis.SecureSocketFactory");
		AxisProperties.setProperty("axis.socketFactory","org.apache.axis.components.net.DefaultSocketFactory");
	//	AxisProperties.setProperty("axis.socketFactoryFactory", "at.telekom.weberv.util.SocketFactoryFactory");
		Hashtable opts = new Hashtable();
        opts.put(AxisEngine.PROP_DISABLE_PRETTY_XML, this.disablePrettyXML);
        opts.put(AxisEngine.PROP_ENABLE_NAMESPACE_PREFIX_OPTIMIZATION, this.enableNamespacePrefixOptimization);
        setGlobalOptions(opts);
        Handler pivot = new ComponentsHTTPSender();
        if (this.keystore!=null){
        	pivot.setOption("clientauth","true");
           	pivot.setOption("keystore", this.keystore);
            if (this.keystoretype!=null)
            	pivot.setOption("keystoreType", this.keystoretype);
            if (this.keystorepin!=null){
            	pivot.setOption("keypass",this.keystorepin);
            	pivot.setOption("keystorePass", this.keystorepin);        	
            }
        }
        if (this.truststore!=null){
        	pivot.setOption("truststore",this.truststore);
	        if (this.truststoreType!=null)
	        	pivot.setOption("truststoreType",this.truststoreType);
	        if (this.truststorepin!=null)
	        	pivot.setOption("truststorePass",this.truststorepin);
        }
        if (logging && this.debugBaseDir != null)
        	pivot.setOption("httplogdirectory", this.debugBaseDir.getAbsolutePath());
        Handler transport = null;
        // Logging, if desired
        if (logging){
        	SimpleChain reqHandler=new SimpleChain(); 
            SimpleChain respHandler=new SimpleChain();
            this.logHandler = new AxisDebugLogHandler();
            if (this.debugBaseDir!=null)
            	this.logHandler.setBaseDir(this.debugBaseDir);
            reqHandler.addHandler(this.logHandler);
            respHandler.addHandler(this.logHandler);
            transport = new SimpleTargetedChain(reqHandler,pivot,respHandler);
        }
        else
	        transport = new SimpleTargetedChain(pivot);
        deployTransport(HTTPTransport.DEFAULT_TRANSPORT_NAME, transport);
	}

	

	/**
	 * <p>
	 * Get the log handler
	 * </p>
	 * @return the log handler, or null if no logging
	 */
	public AxisDebugLogHandler getLogHandler() {
		return this.logHandler;
	}

	/**
	 * @return the debugBaseDir
	 */
	public File getDebugBaseDir() {
		return this.debugBaseDir;
	}

	/**
	 * @param debugBaseDir the debugBaseDir to set
	 */
	public void setDebugBaseDir(File debugBaseDir) {
		this.debugBaseDir = debugBaseDir;
	}

	/**
	 * @return the disablePrettyXML
	 */
	public boolean isDisablePrettyXML() {
		return this.disablePrettyXML;
	}

	/**
	 * @param disablePrettyXML the disablePrettyXML to set
	 */
	public void setDisablePrettyXML(boolean disablePrettyXML) {
		this.disablePrettyXML = disablePrettyXML;
	}

	/**
	 * @return the enableNamespacePrefixOptimization
	 */
	public boolean isEnableNamespacePrefixOptimization() {
		return this.enableNamespacePrefixOptimization;
	}

	/**
	 * @param enableNamespacePrefixOptimization the enableNamespacePrefixOptimization to set
	 */
	public void setEnableNamespacePrefixOptimization(
			boolean enableNamespacePrefixOptimization) {
		this.enableNamespacePrefixOptimization = enableNamespacePrefixOptimization;
	}

	/*
	 * HTTP Simple Authentication Example  

  String authorization = Base64Coder.encode(username + ":" + password);
  hd.addHeader("Authorization", "Basic " + authorization);


  Service service = new ServiceLocator();
  Impl impl = service.getImpl();
  javax.xml.rpc.Stub stub = (javax.xml.rpc.Stub) impl;
  stub._setProperty(Stub.USERNAME_PROPERTY, "user");
  stub._setProperty(Stub.PASSWORD_PROPERTY, "pwd");
  
	 */
}
