package fi.laverca.samples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.laverca.FiComAdditionalServices;
import fi.laverca.FiComClient;
import fi.laverca.FiComRequest;
import fi.laverca.FiComResponse;
import fi.laverca.FiComResponseHandler;
import fi.laverca.JvmSsl;

public class BankTransfer {


	private static final Log log = LogFactory.getLog(BankTransfer.class);
	private static FiComRequest req;
	protected static BankTransferProggerssBarUpdater callStateProgressBarUpdater = new BankTransferProggerssBarUpdater();
	protected static int amountOfCalls = 0;
	
	/**
	 * Connects to MSSP using SSL and waits for response.
	 * @param phoneNumber
	 * @param fromTxt
	 * @param toTxt
	 * @param amountTxt
	 */
	
	private static void estamblishConnection(String phoneNumber, final String fromTxt, final String toTxt, final String amountTxt) {
		
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
        String textToBeConsentedTo = "Do you allow transfer from " + fromTxt + " to " + toTxt + ", " + amountTxt;
        
        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        
        try {
           log.info("calling consent");
           req =
           fiComClient.consent(apTransId, 
            		textToBeConsentedTo, 
            		phoneNumber, 
            		noSpamService, 
            		null, 
            		new FiComResponseHandler() {
		            	@Override
		            	public void onResponse(FiComRequest req, FiComResponse resp) {
		            		log.info("got resp");
		            		amountOfCalls--;
		            		
		            		try {
		            			responseBox.setText("\nMSS Signature: " + 
		            					new String(Base64.encode(resp.getMSS_StatusResp().
		            					getMSS_Signature().getBase64Signature()), "ASCII") +
		            					"\n\n" + responseBox.getText());
		            		} catch (UnsupportedEncodingException e) {
		            			log.info("Unsupported encoding", e);
		            		}
		            		
		            		responseBox.setText("User allowed transfer from " + fromTxt + 
		            				" to\n" + toTxt + ", " + amountTxt + "\n" + responseBox.getText());
		            	}
		
		            	@Override
		            	public void onError(FiComRequest req, Throwable throwable) {
		            		amountOfCalls--;
		            		log.info("got error", throwable);
		            		responseBox.setText("User did not allow transfer from " + 
		            				fromTxt + " to\n" + toTxt + ", " + amountTxt + "\n" + 
		            				responseBox.getText());
		            	}
		            });
        }
        catch (IOException e) {
            log.info("error establishing connection", e);
        }

        fiComClient.shutdown();
	}
	
	/**
	 * Allows user to sign a bank transfer.
	 * @param args
	 */
	
	public static void main(String[] args) {
		initComponents();
		callStateProgressBarUpdater.start();
	}
	
    private static void initComponents() {
    	frame = new javax.swing.JFrame("Bank Transfer");
    	frame.setResizable(false);
        pane = new javax.swing.JPanel();
        lblNumber = new javax.swing.JLabel();
        number = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        callStateProgressBar = new javax.swing.JProgressBar();
        cancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        responseBox = new javax.swing.JTextArea();
        lblFromTxt = new javax.swing.JLabel();
        lblToTxt = new javax.swing.JLabel();
        fromTxt = new javax.swing.JTextField();
        toTxt = new javax.swing.JTextField();
        lblAmountTxt = new javax.swing.JLabel();
        amountTxt = new javax.swing.JTextField();

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        lblNumber.setText("Phone number");

        number.setText("+35847001001");

        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amountOfCalls++;
				estamblishConnection(number.getText(), fromTxt.getText(), toTxt.getText(), amountTxt.getText());
			}
		});
		
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				req.cancel();
				responseBox.setText("Canceled\n" + responseBox.getText());
				amountOfCalls--;
			}
		});
		
        responseBox.setColumns(20);
        responseBox.setRows(5);
        jScrollPane1.setViewportView(responseBox);

        lblFromTxt.setText("From account");

        lblToTxt.setText("To account");

        fromTxt.setText("Bank account 1");

        toTxt.setText("Bank account 2");

        lblAmountTxt.setText("Amount");

        amountTxt.setText("50 eur");

        javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(callStateProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(number, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addComponent(lblNumber, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblFromTxt)
                                    .addComponent(fromTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(amountTxt))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblToTxt)
                                    .addComponent(toTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(85, 85, 85))
                    .addComponent(lblAmountTxt))
                .addContainerGap())
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addComponent(lblNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFromTxt)
                    .addComponent(lblToTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAmountTxt)
                .addGap(3, 3, 3)
                .addComponent(amountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(callStateProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(sendButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private static javax.swing.JTextField amountTxt;
    protected static javax.swing.JProgressBar callStateProgressBar;
    private static javax.swing.JButton cancelButton;
    private static javax.swing.JTextField fromTxt;
    private static javax.swing.JPanel pane;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel lblAmountTxt;
    private static javax.swing.JLabel lblFromTxt;
    private static javax.swing.JLabel lblNumber;
    private static javax.swing.JLabel lblToTxt;
    private static javax.swing.JTextField number;
    private static javax.swing.JTextArea responseBox;
    private static javax.swing.JButton sendButton;
    private static javax.swing.JTextField toTxt;
    
}

class BankTransferProggerssBarUpdater extends Thread {
	
	public void run() {
		while (true) {
			if (BankTransfer.amountOfCalls > 0) {
				int value = BankTransfer.callStateProgressBar.getValue() > 90 ? 10 : BankTransfer.callStateProgressBar.getValue()+10;
				BankTransfer.callStateProgressBar.setValue(value);
			} else {
				BankTransfer.callStateProgressBar.setValue(0);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}