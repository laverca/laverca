package fi.laverca.samples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.laverca.DTBS;
import fi.laverca.FiComAdditionalServices;
import fi.laverca.FiComClient;
import fi.laverca.FiComException;
import fi.laverca.FiComMSS_Formats;
import fi.laverca.FiComRequest;
import fi.laverca.FiComResponse;
import fi.laverca.FiComResponseHandler;
import fi.laverca.FiComSignatureProfiles;
import fi.laverca.JvmSsl;
import fi.laverca.ProgressUpdate;


/**
 * An example for signing text that directly uses FiComClients call method.
 * This allows it to request PKCS1 signature instead of PKCS7.
 * 
 * Indirect calling means simply taking the default settings for a certain action.
 * 
 * @author Eemeli Miettinen
 * @author Jan Mikael Lindlöf
 *
 */

public class DirectCall {

		private static final Log log = LogFactory.getLog(DirectCall.class);
		private static FiComRequest req;
		
		/**
		 * Connects to MSSP using SSL and waits for response
		 * @param phoneNumber
		 * @param textToBeSigned
		 */
		
		private static void establishConnection(final String phoneNumber, final String textToBeSigned) {
			
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
	        final String eventId = "A"+ currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
	        
	        Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);
	        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
	        
	        DTBS dtbs = new DTBS(textToBeSigned, DTBS.ENCODING_UTF8, DTBS.MIME_TEXTPLAIN);
	        
	        try {
	        	log.info("calling call");
	        	req = 
		        	fiComClient.call(apTransId, 
		        			dtbs, 
		        			phoneNumber, 
		        			noSpamService,
		        			eventIdService,
		        			null, 
		        			FiComSignatureProfiles.SIGNATURE,
		        			FiComMSS_Formats.PKCS1,
		        			new FiComResponseHandler() {
				        		@Override
				        		public void onResponse(FiComRequest req, FiComResponse resp) {
				        			log.info("Got response");
				        			sendButton.setEnabled(true);
									callStateProgressBar.setIndeterminate(false);
									
									try {
										log.info("MSS signer cert: " + resp.getPkcs1Signature().getSignerCert());
									} catch (FiComException e) {
										log.error("unable to get cert" + e);
									}
									
				        			responseBox.setText("MSS signature: " + resp.getPkcs1Signature().getMssSignatureValue() + "\n" + responseBox.getText());
				        			responseBox.setText("MSS signer cn: " + resp.getPkcs1Signature().getSignerCn() + "\n" + responseBox.getText());
				        			
				        			responseBox.setText("Event ID: " + eventId + "\nSigned text: " + textToBeSigned + "\n" + responseBox.getText());
				        		}
				
				        		@Override
				        		public void onError(FiComRequest req, Throwable throwable) {
				        			log.info("got error", throwable);
									callStateProgressBar.setIndeterminate(false);
				        			responseBox.setText("ERROR, " + phoneNumber + "\n" +
				        					responseBox.getText());
				        		}

								@Override
								public void onOutstandingProgress(FiComRequest req,
										ProgressUpdate prgUpdate) {
								}
		        			});
	        }
	        catch (IOException e) {
	            log.info("error establishing connection", e);
	        }

	        fiComClient.shutdown();
		}

		
		public static void main(String[] args) {
			initComponents();
		}
		
		
		
	    private static void initComponents() {
	    	frame = new javax.swing.JFrame("Direct Call (Sign Text)");
	    	frame.setResizable(false);
	        pane = new javax.swing.JPanel();
	        lblTxtToBeSigned = new javax.swing.JLabel();
	        lblNumber = new javax.swing.JLabel();
	        number = new javax.swing.JTextField();
	        textToBeSigned = new javax.swing.JTextField();
	        sendButton = new javax.swing.JButton();
	        callStateProgressBar = new javax.swing.JProgressBar(0, 100);
	        cancelButton = new javax.swing.JButton();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        responseBox = new javax.swing.JTextArea();

	        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	        
	        lblNumber.setText("Phone number");

	        number.setText("+35847001001");

	        lblTxtToBeSigned.setText("Text to be signed");

	        textToBeSigned.setText("Some text to be signed");

	        sendButton.setText("Send");
	        sendButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sendButton.setEnabled(false);
					establishConnection(number.getText(), textToBeSigned.getText());
					callStateProgressBar.setIndeterminate(true);
				}
			});
	        
	        cancelButton.setText("Cancel");
	        cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sendButton.setEnabled(true);
					req.cancel();
					callStateProgressBar.setIndeterminate(false);
				}
			});
	        responseBox.setColumns(20);
	        responseBox.setRows(5);
	        jScrollPane1.setViewportView(responseBox);

	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pane);
	        pane.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
	                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addComponent(textToBeSigned, javax.swing.GroupLayout.Alignment.LEADING)
	                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
	                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                .addComponent(number, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
	                                .addComponent(lblNumber)
	                                .addComponent(lblTxtToBeSigned)
	                                .addGroup(jPanel1Layout.createSequentialGroup()
	                                    .addComponent(sendButton)
	                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                    .addComponent(callStateProgressBar, 0, 0, Short.MAX_VALUE)))
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                            .addComponent(cancelButton))))
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(cancelButton))
	                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
	                        .addComponent(lblNumber)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(lblTxtToBeSigned)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(textToBeSigned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(callStateProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
	                            .addComponent(sendButton))))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
	        frame.getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );

	        frame.pack();
	    }
	    
	    private static javax.swing.JFrame frame;
	    private static javax.swing.JButton sendButton;
	    private static javax.swing.JButton cancelButton;
	    private static javax.swing.JLabel lblNumber;
	    private static javax.swing.JLabel lblTxtToBeSigned;
	    private static javax.swing.JPanel pane;
	    protected static javax.swing.JProgressBar callStateProgressBar;
	    private static javax.swing.JScrollPane jScrollPane1;
	    private static javax.swing.JTextArea responseBox;
	    private static javax.swing.JTextField number;
	    private static javax.swing.JTextField textToBeSigned;
		
	}