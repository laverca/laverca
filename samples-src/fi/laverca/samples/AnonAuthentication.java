package fi.laverca.samples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.laverca.DTBS;
import fi.laverca.FiComAdditionalServices;
import fi.laverca.FiComAdditionalServices.PersonIdAttribute;
import fi.laverca.FiComClient;
import fi.laverca.FiComRequest;
import fi.laverca.FiComResponse;
import fi.laverca.FiComResponseHandler;
import fi.laverca.JvmSsl;
import fi.laverca.ProgressUpdate;

/**
 * Sample for demonstrating anonymous authentication. 
 * 
 * @author Jan Mikael Lindlöf (mikael.lindlof@nbl.fi)
 * @author Eemeli Miettinen (eemeli.miettinen@methics.fi)
 */

public class AnonAuthentication {

	private static final Log log = LogFactory.getLog(AnonAuthentication.class);
	private static FiComRequest req;

	
	/**
	 * Connects to MSSP using SSL and waits for response.
	 * @param phoneNumber
	 */
	private static void connect(final String phoneNumber) {
		
	    Properties properties = SampleConf.getProperties();
	    
		log.info("setting up ssl");
		JvmSsl.setSSL(properties.getProperty(SampleConf.TRUSTSTORE_FILE),
		        properties.getProperty(SampleConf.TRUSTSTORE_PASSWORD),
		        properties.getProperty(SampleConf.KEYSTORE_FILE),
		        properties.getProperty(SampleConf.KEYSTORE_PASSWORD),
		        properties.getProperty(SampleConf.KEYSTORE_TYPE));
		
		String apId  = properties.getProperty(SampleConf.AP_ID);
        String apPwd = properties.getProperty(SampleConf.AP_PASSWORD);

        String msspSignatureUrl    = properties.getProperty(SampleConf.SIGNATURE_URL);
        String msspStatusUrl       = properties.getProperty(SampleConf.STATUS_URL);
        String msspReceiptUrl      = properties.getProperty(SampleConf.RECEIPT_URL);

        log.info("creating FiComClient");
        FiComClient fiComClient = new FiComClient(apId, 
                                                  apPwd, 
                                                  msspSignatureUrl, 
                                                  msspStatusUrl, 
                                                  msspReceiptUrl);
        
        Long currentTimeMillis = System.currentTimeMillis();
        String apTransId = "A"+currentTimeMillis;
        final String eventId = "A"+ currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
        
        byte[] authnChallenge = new DTBS(apTransId, DTBS.ENCODING_UTF8).toBytes();

        Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);
        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        LinkedList<Service> additionalServices = new LinkedList<Service>();
        LinkedList<String> attributeNames = new LinkedList<String>();
        attributeNames.add(FiComAdditionalServices.PERSON_ID_ADDRESS);
        //attributeNames.add(FiComAdditionalServices.PERSON_ID_GENDER);
        Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        additionalServices.add(personIdService);
        
        try {
            log.info("calling authenticateAnon");
            req = 
	            fiComClient.authenticateAnon(apTransId, 
	            		authnChallenge, 
	            		phoneNumber, 
	            		noSpamService, 
	            		eventIdService,
	            		additionalServices, 
	            		new FiComResponseHandler() {
			            	@Override
			            	public void onResponse(FiComRequest req, FiComResponse resp) {
			            		log.info("got resp");
			    				sendButton.setEnabled(true);
								callStateProgressBar.setIndeterminate(false);
			            		responseBox.setText("\n" + responseBox.getText());
			            		try{
				            		for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
				            			log.info(a.getStringValue());
				            			responseBox.setText(a.getStringValue() + " " + responseBox.getText());
				            		}
			            		} catch (NullPointerException e){
			            			log.warn("No Person ID Attributes found!");
			            		}
			            		
			            		responseBox.setText("Event ID: " + eventId + "\nAdditional attributes:\n" + responseBox.getText());
			            	}
			
			            	@Override
			            	public void onError(FiComRequest req, Throwable throwable) {
								callStateProgressBar.setIndeterminate(false);
			            		responseBox.setText(throwable.getMessage());
			            	}

							@Override
							public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate) {
								
							}
			            });
        }
        catch (IOException e) {
            log.info("error establishing connection", e);
        }

        fiComClient.shutdown();
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {	
		initComponents();
	}
	
	/**
	 * Initializes the swing GUI
	 */
	private static void initComponents() {

		frame = new javax.swing.JFrame("Anon authentication");
		pane = new javax.swing.JPanel();
		lblNumber = new javax.swing.JLabel();
		number = new javax.swing.JTextField();
		sendButton = new javax.swing.JButton();
		callStateProgressBar = new javax.swing.JProgressBar();
		cancelButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		responseBox = new javax.swing.JTextArea();

		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		lblNumber.setText("Phone number");

		number.setText("+35847001001");

		sendButton.setText("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendButton.setEnabled(false);
				callStateProgressBar.setIndeterminate(true);
				connect(number.getText());
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
								.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(number, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
												.addComponent(lblNumber, javax.swing.GroupLayout.Alignment.LEADING))
												.addGap(85, 85, 85))
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addComponent(sendButton)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(callStateProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(cancelButton)))
														.addContainerGap())
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(lblNumber)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(callStateProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
								.addComponent(sendButton))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
		frame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
	protected static javax.swing.JProgressBar callStateProgressBar;
	private static javax.swing.JButton cancelButton;
	private static javax.swing.JPanel pane;
	private static javax.swing.JScrollPane jScrollPane1;
	private static javax.swing.JLabel lblNumber;
	private static javax.swing.JTextField number;
	private static javax.swing.JTextArea responseBox;
	private static javax.swing.JButton sendButton;
}
