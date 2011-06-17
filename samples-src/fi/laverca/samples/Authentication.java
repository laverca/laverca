package fi.laverca.samples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.laverca.DTBS;
import fi.laverca.FiComAdditionalServices;
import fi.laverca.FiComAdditionalServices.PersonIdAttribute;
import fi.laverca.FiComClient;
import fi.laverca.FiComException;
import fi.laverca.FiComRequest;
import fi.laverca.FiComResponse;
import fi.laverca.FiComResponseHandler;
import fi.laverca.JvmSsl;

public class Authentication {
	
	private static final Log log = LogFactory.getLog(Authentication.class);
	private static FiComRequest req;
	protected static AuthenticationProggerssBarUpdater callStateProgressBarUpdater = new AuthenticationProggerssBarUpdater();
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
 
        attributeNames.add(FiComAdditionalServices.PERSON_ID_VALIDUNTIL);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_ADDRESS);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_AGE);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_SUBJECT);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_SURNAME);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_GIVENNAME);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_HETU);
        attributeNames.add(FiComAdditionalServices.PERSON_ID_SATU);
        Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        additionalServices.add(personIdService);
        
        try {
            log.info("calling authenticate");
            req = 
	            fiComClient.authenticate(apTransId, 
	            		authnChallenge, 
	            		phoneNumber, 
	            		noSpamService, 
	            		additionalServices, 
	            		new FiComResponseHandler() {
			            	@Override
			            	public void onResponse(FiComRequest req, FiComResponse resp) {
			            		log.info("got resp");
			            		amountOfCalls--;
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
			            	}
			
			            	@Override
			            	public void onError(FiComRequest req, Throwable throwable) {
			            		log.info("got error", throwable);
			            		amountOfCalls--;
			            		responseBox.setText("ERROR, " + phoneNumber + "\n\n" + responseBox.getText());
			            	}
			            });
            
        }
        catch (IOException e) {
            log.info("error establishing connection", e);
        }

        fiComClient.shutdown();
	}
	
	/**
	 * Authenticates user
	 * @param args
	 */
	public static void main(String[] args) {
		initComponents();
		callStateProgressBarUpdater.start();
	}
	
    private static void initComponents() {
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
				amountOfCalls++;
				estamblishConnection(number.getText());
			}
		});
		
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				req.cancel();
				amountOfCalls--;
				responseBox.setText("Canceled\n" + responseBox.getText());
			}
		});
        
        responseBox.setColumns(20);
        responseBox.setRows(5);
        scrollPane.setViewportView(responseBox);

        javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(number, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addComponent(lblNumber, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(85, 85, 85))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(callStateProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addComponent(lblNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(callStateProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(sendButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private static javax.swing.JScrollPane scrollPane;
    private static javax.swing.JLabel lblNumber;
    private static javax.swing.JTextField number;
    private static javax.swing.JTextArea responseBox;
    private static javax.swing.JButton sendButton;
}

class AuthenticationProggerssBarUpdater extends Thread {
	
	AuthenticationProggerssBarUpdater() {}
	
	public void run() {
		while (true) {
			if (Authentication.amountOfCalls > 0) {
				int value = Authentication.callStateProgressBar.getValue() > 90 ? 10 : Authentication.callStateProgressBar.getValue()+10;
				Authentication.callStateProgressBar.setValue(value);
			} else {
				Authentication.callStateProgressBar.setValue(0);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}