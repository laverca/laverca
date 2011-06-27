package fi.laverca.samples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.ficom.mss.TS102204.v1_0_0.Status;
import fi.laverca.DTBS;
import fi.laverca.FiComAdditionalServices;
import fi.laverca.FiComAdditionalServices.PersonIdAttribute;
import fi.laverca.FiComClient;
import fi.laverca.FiComException;
import fi.laverca.FiComRequest;
import fi.laverca.FiComResponse;
import fi.laverca.FiComResponseHandler;
import fi.laverca.JvmSsl;
import fi.laverca.ProgressUpdate;

/**
 * Sample for demonstrating authentication. 
 * 
 * @author Jan Mikael Lindlöf
 * @author Eemeli Miettinen
 *
 */

public class Authentication {
	
	private static final Log log = LogFactory.getLog(Authentication.class);
	private static FiComRequest req;
	
	public static void main(String[] args) {
		new Authentication().initComponents();
	}

	/**
	 * Initializes the UI
	 */

    private void initComponents() {
    	frame = new javax.swing.JFrame("Authentication");
        pane = new javax.swing.JPanel();
        lblNumber = new javax.swing.JLabel();
        number = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        callStateProgressBar = new javax.swing.JProgressBar();
        cancelButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        responseBox = new javax.swing.JTextArea();

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        lblNumber.setText("Phone number");

        number.setText("+35847001001");

        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ResponseWindow(number.getText());
				callStateProgressBar.setIndeterminate(true);
			}
		});
		
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				req.cancel();
				responseBox.setText("Canceled\n" + responseBox.getText());
			}
		});
        
        responseBox.setColumns(20);
        responseBox.setRows(5);
        scrollPane.setViewportView(responseBox);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNumber)
                    .addComponent(sendButton)
                    .addComponent(number, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frame.pack();
    }
    
    private static javax.swing.JFrame frame;
    public static javax.swing.JProgressBar callStateProgressBar;
    private static javax.swing.JButton cancelButton;
    private static javax.swing.JPanel pane;
    private static javax.swing.JScrollPane scrollPane;
    private static javax.swing.JLabel lblNumber;
    private static javax.swing.JTextField number;
    private static javax.swing.JTextArea responseBox;
    private static javax.swing.JButton sendButton;
    
    /**
     * Creates a response window.
     * 
     * @author Jan Mikael Lindlöf
     * @author Eemeli Miettinen
     * 
     */
    
    private class ResponseWindow {
    	
    	private FiComRequest req;
    	private javax.swing.JTextArea responseBox;
    	private javax.swing.JFrame responseFrame;
        private javax.swing.JButton cancelButton;
        private javax.swing.JProgressBar callStateProgressBar;
        private javax.swing.JScrollPane jScrollPane1;
        private String eventId;
    	
        /**
         * Generates a new window for responses and calls <code>estamblishConnection</code> 
         * to start the authentication process.
         * @param number
         */
        
    	public ResponseWindow(String number) {
    		Long currentTimeMillis = System.currentTimeMillis();
    		eventId = "A" + currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
    		initResponse();
    		establishConnection(number);
    	}
    	
    	/**
    	 * Initializes the response box
    	 */
    	private void initResponse() {
        	responseFrame = new javax.swing.JFrame(eventId);
            cancelButton = new javax.swing.JButton();
            jScrollPane1 = new javax.swing.JScrollPane();
            responseBox = new javax.swing.JTextArea();
            callStateProgressBar = new javax.swing.JProgressBar();

            cancelButton.setText("Cancel");
            cancelButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				req.cancel();
    				callStateProgressBar.setIndeterminate(false);
    				responseFrame.dispose();
    			}
    		});
            
            responseBox.setColumns(20);
            responseBox.setRows(5);
            jScrollPane1.setViewportView(responseBox);
            callStateProgressBar.setIndeterminate(true);
            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(responseFrame.getContentPane());
            responseFrame.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(callStateProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(cancelButton)
                    .addContainerGap(47, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                        .addGap(14, 14, 14)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(callStateProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(266, 266, 266))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(53, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
            );

            responseFrame.pack();
            responseFrame.setVisible(true);
            responseFrame.setResizable(false);
        }
    	
    	/**
    	 * Connects to MSSP using SSL and waits for response.
    	 * @param phoneNumber
    	 */
    	private void establishConnection(final String phoneNumber) {
    		
    		XMLConfiguration config = null;
    		try {
    		    config = new XMLConfiguration("fi/laverca/samples/configuration.xml");
    		} catch(ConfigurationException e) {
    		    log.info("configuration file not found", e);
    		}
    		
    		log.info("setting up ssl");
    		JvmSsl.setSSL(config.getString("ssl.trustStore"),
    				config.getString("ssl.trustStorePassword"),
    				config.getString("ssl.keyStore"),
    				config.getString("ssl.keyStorePassword"),
    				config.getString("ssl.keyStoreType"));
    		
    		String apId  = config.getString("ap.apId");
            String apPwd = config.getString("ap.apPwd");

            String msspSignatureUrl    = config.getString("mssp.msspSignatureUrl");
            String msspStatusUrl       = config.getString("mssp.msspStatusUrl");
            String msspReceiptUrl      = config.getString("mssp.msspReceiptUrl");

            log.info("creating FiComClient");
            FiComClient fiComClient = new FiComClient(apId, 
                                                      apPwd, 
                                                      msspSignatureUrl, 
                                                      msspStatusUrl, 
                                                      msspReceiptUrl);
            
            Long currentTimeMillis = System.currentTimeMillis();
            String apTransId = "A"+currentTimeMillis;
            
            byte[] authnChallenge = new DTBS(apTransId, DTBS.ENCODING_UTF8).toBytes();

            Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);
            Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);

            LinkedList<Service> additionalServices = new LinkedList<Service>();
            LinkedList<String> attributeNames = new LinkedList<String>(); 
     
            attributeNames.add(FiComAdditionalServices.PERSON_ID_VALIDUNTIL);
            attributeNames.add(FiComAdditionalServices.PERSON_ID_ADDRESS);
            attributeNames.add(FiComAdditionalServices.PERSON_ID_AGE);
            attributeNames.add(FiComAdditionalServices.PERSON_ID_SUBJECT);
            attributeNames.add(FiComAdditionalServices.PERSON_ID_SURNAME);
            attributeNames.add(FiComAdditionalServices.PERSON_ID_GIVENNAME);
            attributeNames.add(FiComAdditionalServices.PERSON_ID_HETU);
            attributeNames.add(FiComAdditionalServices.PERSON_ID_SATU);
            Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
            Service validateService = FiComAdditionalServices.createValidateService();
            additionalServices.add(personIdService);
            additionalServices.add(validateService);
            
            try {
                log.info("calling authenticate");
                req = 
    	            fiComClient.authenticate(apTransId, 
    	            		authnChallenge, 
    	            		phoneNumber, 
    	            		noSpamService, 
    	            		eventIdService,
    	            		additionalServices, 
    	            		new FiComResponseHandler() {
    			            	@Override
    			            	public void onResponse(FiComRequest req, FiComResponse resp, MSS_SignatureResp sigResp) {
    			            		log.info("got resp");
    								callStateProgressBar.setIndeterminate(false);
    								
    			            		log.info(resp.getPkcs7Signature().getSignerCn());
    			            		
    			            		try {
    			            			responseBox.setText("MSS Signature: " + 
    			            					new String(Base64.encode(resp.getMSS_StatusResp().
    			            					getMSS_Signature().getBase64Signature()), "ASCII") +
    			            					"\n\n");
    			            		} catch (UnsupportedEncodingException e) {
    			            			log.info("Unsupported encoding", e);
    			            		}
    			            		responseBox.setText("AP   id: " + resp.getMSS_StatusResp().getAP_Info().getAP_ID()
    			            				+ "   PWD: " + resp.getMSS_StatusResp().getAP_Info().getAP_PWD()
    			            				+ "   TransID: " + resp.getMSS_StatusResp().getAP_Info().getAP_TransID() + "\n" + responseBox.getText());
    			            		
    	//		            		responseBox.setText("MSS   StatusMessage: " + resp.getMSS_StatusResp().getStatus().getStatusMessage() 
    	//		            				+ "   MajorVersion: " + resp.getMSS_StatusResp().getMajorVersion()
    	//		            				+ "   MinorVersion: " + resp.getMSS_StatusResp().getMinorVersion()
    	//		            				+ "   URI: " + resp.getMSS_StatusResp().getMSSP_Info().getMSSP_ID().getURI()
    	//		            				+ "\n" + responseBox.getText());
    			            		
    			            		responseBox.setText("MobileUser   MSISDN: " + resp.getMSS_StatusResp().getMobileUser().getMSISDN()
    			            				+ "\n" + responseBox.getText());
    			            		
    			            		try {
    			            			responseBox.setText("Pkcs7" + "\n"
    			            					+ "   Serial: " + resp.getPkcs7Signature().getSignerCert().getSerialNumber()
    			            					+ "   Type: " + resp.getPkcs7Signature().getSignerCert().getType()
    			            					+ "   SigAlgName: " + resp.getPkcs7Signature().getSignerCert().getSigAlgName()
    			            					+ "   SerialNumber: " + resp.getPkcs7Signature().getSignerCert().getSerialNumber()
    			            					+ "   SigAlgOID: " + resp.getPkcs7Signature().getSignerCert().getSigAlgOID() + "\n"
    			            					+ "   IssuerX500Principal: " + resp.getPkcs7Signature().getSignerCert().getIssuerX500Principal() + "\n"
    			            					+ "   SubjectX500Principal: " + resp.getPkcs7Signature().getSignerCert().getSubjectX500Principal() + "\n"
    	//		            					+ "   PublicKey: " + resp.getPkcs7Signature().getSignerCert().getPublicKey()
    			            					+ "\n" + responseBox.getText());
    	
    			            			for (String oid : resp.getPkcs7Signature().getSignerCert().getNonCriticalExtensionOIDs()) {
    			            				responseBox.setText("   " + oid + "\n" + responseBox.getText());
    			            			}
    			            			responseBox.setText("NonCriticalExtensionOIDs:" + "\n" + responseBox.getText());
    			            			
    			            			for (String oid : resp.getPkcs7Signature().getSignerCert().getCriticalExtensionOIDs()) {
    			            				responseBox.setText("   " + oid + "\n" + responseBox.getText());
    			            			}
    			            			responseBox.setText("CriticalExtensionOIDs:" + "\n" + responseBox.getText());
    	
    			            			responseBox.setText(resp.getPkcs7Signature().getSignerCert().getIssuerX500Principal() + "\n" + responseBox.getText());	
    			            		} catch (FiComException e1) {
    									e1.printStackTrace();
    								}
    			            		for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
    			            			log.info(a.getName() + " " + a.getStringValue());
    			            			responseBox.setText(a.getName().substring(a.getName().indexOf('#')+1) + ": " + a.getStringValue() 
    			            					+ "\n" + responseBox.getText());
    			            		}
    			            		
    			            		Status validationStatus = resp.getAeValidationStatus();
    			            		responseBox.setText("AE validation status code: " + validationStatus.getStatusCode().getValue() + 
    			            				" (" + validationStatus.getStatusMessage() + ")\n" + responseBox.getText());
    			            		
    		            			responseBox.setText("Event ID: " + eventId + "\n" + responseBox.getText());	
    			            	}
    			
    			            	@Override
    			            	public void onError(FiComRequest req, Throwable throwable) {
    			            		log.info("got error", throwable);
    			            		responseBox.setText("ERROR, " + phoneNumber + "\n\n" + responseBox.getText());
    								callStateProgressBar.setIndeterminate(false);
    			            	}

    							@Override
    							public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate, MSS_SignatureResp sigResp) {
    								log.info("got progress update");
    								long timePast = prgUpdate.getElapsedTime();
                					log.info("Time past: " + String.format("%d min, %d sec", 
    									    TimeUnit.MILLISECONDS.toMinutes(timePast),
    									    TimeUnit.MILLISECONDS.toSeconds(timePast) - 
    									    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timePast))
    								));
    								long timeLeft = prgUpdate.getTimeLeft();
                					log.info("Time left: " + String.format("%d min, %d sec", 
    									    TimeUnit.MILLISECONDS.toMinutes(timeLeft),
    									    TimeUnit.MILLISECONDS.toSeconds(timeLeft) - 
    									    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeLeft))
    								));
                					log.info("AP: " + sigResp.getAP_Info().getAP_ID());
                					log.info("MSSP: " + sigResp.getMSSP_Info().getMSSP_ID().getURI());
    							}
    			            });
                
            }
            catch (IOException e) {
                log.info("error establishing connection", e);
            }

            fiComClient.shutdown();
    	}
    }
    
}

