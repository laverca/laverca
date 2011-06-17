package fi.laverca.samples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

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

public class AnonAuthentication {

	private static final Log log = LogFactory.getLog(AnonAuthentication.class);
	private static FiComRequest req;
	protected static AnonAuthenticationProggerssBarUpdater callStateProgressBarUpdater = new AnonAuthenticationProggerssBarUpdater();
	protected static int amountOfCalls = 0;
	
	/**
	 * Connects to MSSP using SSL and waits for response.
	 * @param phoneNumber
	 */
	private static void estamblishConnection(final String phoneNumber) {
		
		log.info("setting up ssl");
		JvmSsl.setSSL("etc/laverca-truststore",
                "changeit",
                "etc/laverca-keystore",
                "changeit",
                "JKS");
		
		String apId  = "http://laverca-eval.fi";
        String apPwd = "pfkpfk";

        String aeMsspIdUri = "http://dev-ae.mssp.dna.fi";
        //TODO: TeliaSonera
        //TODO: Elisa

        String msspSignatureUrl    = "https://dev-ae.mssp.dna.fi/soap/services/MSS_SignaturePort";
        String msspStatusUrl       = "https://dev-ae.mssp.dna.fi/soap/services/MSS_StatusQueryPort";
        String msspReceiptUrl      = "https://dev-ae.mssp.dna.fi/soap/services/MSS_ReceiptPort";

        log.info("creating FiComClient");
        FiComClient fiComClient = new FiComClient(apId, 
                                                  apPwd, 
                                                  aeMsspIdUri, 
                                                  msspSignatureUrl, 
                                                  msspStatusUrl, 
                                                  msspReceiptUrl);
        
        String apTransId = "A"+System.currentTimeMillis();
        byte[] authnChallenge = new DTBS(apTransId, "UTF-8").toBytes();

        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        LinkedList<Service> additionalServices = new LinkedList<Service>();
        LinkedList<String> attributeNames = new LinkedList<String>();
        attributeNames.add(FiComAdditionalServices.PERSON_ID_AGE);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_GENDER);
        Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        additionalServices.add(personIdService);
        
        try {
            log.info("calling authenticateAnon");
            req = 
	            fiComClient.authenticateAnon(apTransId, 
	            		authnChallenge, 
	            		phoneNumber, 
	            		noSpamService, 
	            		additionalServices, 
	            		new FiComResponseHandler() {
			            	@Override
			            	public void onResponse(FiComRequest req, FiComResponse resp) {
			            		log.info("got resp");
			            		amountOfCalls--;
			            		responseBox.setText("\n" + responseBox.getText());
			            		
			            		for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
			            			log.info(a.getStringValue());
			            			responseBox.setText(a.getStringValue() + " " + responseBox.getText());
			            		}
			            	}
			
			            	@Override
			            	public void onError(FiComRequest req, Throwable throwable) {
			            		log.info("got error", throwable);
			            		amountOfCalls--;
			            		responseBox.setText("ERROR, " + phoneNumber + "\n" + responseBox.getText());
			            	}
			            });
        }
        catch (IOException e) {
            log.info("error establishing connection", e);
        }

        fiComClient.shutdown();
	}
	
	/**
	 * Authenticates user and keeps user anonymous. Only allows to request age and sex of the user.
	 * Eg. used to check maturity of the user.
	 * @param args
	 */
	public static void main(String[] args) {	
		initComponents();
		callStateProgressBarUpdater.start();
	}
	
	   private static void initComponents() {
		   
		   frame = new javax.swing.JFrame();
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
				   amountOfCalls++;
				   estamblishConnection(number.getText());
			   }
		   });
		   
		   cancelButton.setText("Cancel");
		   cancelButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   req.cancel();
				   amountOfCalls++;
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

class AnonAuthenticationProggerssBarUpdater extends Thread {
	
	public void run() {
		while (true) {
			if (AnonAuthentication.amountOfCalls > 0) {
				int value = AnonAuthentication.callStateProgressBar.getValue() > 90 ? 10 : AnonAuthentication.callStateProgressBar.getValue()+10;
				AnonAuthentication.callStateProgressBar.setValue(value);
			} else {
				AnonAuthentication.callStateProgressBar.setValue(0);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}