package fi.laverca.samples;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	
	private static JTextArea responseBox = new JTextArea();
	
	/**
	 * Connects to MSSP using SSL and waits for response.
	 * @param phoneNumber
	 * @param fromTxt
	 * @param toTxt
	 * @param amountTxt
	 */
	
	private void estamblishConnection(String phoneNumber, final String fromTxt, final String toTxt, final String amountTxt) {
		
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
            fiComClient.consent(apTransId, 
            		textToBeConsentedTo, 
            		phoneNumber, 
            		noSpamService, 
            		null, 
            		new FiComResponseHandler() {
		            	@Override
		            	public void onResponse(FiComRequest req, FiComResponse resp) {
		            		log.info("got resp");
		            		
		            		try {
		            			responseBox.setText("MSS Signature: " + 
		            					new String(Base64.encode(resp.getMSS_StatusResp().
		            					getMSS_Signature().getBase64Signature()), "ASCII") +
		            					"\n\n" + responseBox.getText());
		            		} catch (UnsupportedEncodingException e) {
		            			log.info("Unsupported encoding", e);
		            		}
		            		
		            		responseBox.setText("User allowed transfer from " + fromTxt + 
		            				" to " + toTxt + ", " + amountTxt + "\n" + responseBox.getText());
		            	}
		
		            	@Override
		            	public void onError(FiComRequest req, Throwable throwable) {
		            		log.info("got error", throwable);
		            		responseBox.setText("User did not allow transfer from " + 
		            				fromTxt + " to " + toTxt + ", " + amountTxt + "\n" + 
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
		new BankTransfer();
	}
	
	public BankTransfer() {
		
		JFrame frame = new JFrame("Bank Transfer");
		frame.setSize(600, 380);

		Container pane = frame.getContentPane();
		GroupLayout layout = new GroupLayout(pane);
		pane.setLayout(layout);
		layout.setAutoCreateGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		
		JLabel lblNumber = new JLabel("Phone number");
		final JTextField number = new JTextField("+35847001001");
		number.setPreferredSize(new Dimension(230, 10));
		pane.add(number, BorderLayout.CENTER);
		
		JLabel lblFromTxt = new JLabel("From account");
		final JTextField fromTxt = new JTextField();
		fromTxt.setPreferredSize(new Dimension(230, 10));
		pane.add(fromTxt, BorderLayout.CENTER);
		
		JLabel lblToTxt = new JLabel("To account");
		final JTextField toTxt = new JTextField();
		toTxt.setPreferredSize(new Dimension(230, 10));
		pane.add(toTxt, BorderLayout.CENTER);
		
		JLabel lblAmountTxt = new JLabel("Amount");
		final JTextField amountTxt = new JTextField();
		amountTxt.setPreferredSize(new Dimension(230, 10));
		pane.add(amountTxt, BorderLayout.CENTER);
		
		JButton send = new JButton("Send");
		send.setPreferredSize(new Dimension(70, 10));
		pane.add(send, BorderLayout.EAST);
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estamblishConnection(number.getText(), fromTxt.getText(), toTxt.getText(), amountTxt.getText());
			}
		});
		
		responseBox.setPreferredSize(new Dimension(200, 600));
		pane.add(responseBox, BorderLayout.PAGE_END);
		
		hGroup.addGroup(layout.createParallelGroup().addComponent(lblNumber).addComponent(number).
				addComponent(lblFromTxt).addComponent(fromTxt).addComponent(lblToTxt).addComponent(toTxt).
					addComponent(lblAmountTxt).addComponent(amountTxt).
				addComponent(send).addComponent(responseBox));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lblNumber));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(number));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lblFromTxt));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(fromTxt));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lblToTxt));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(toTxt));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lblAmountTxt));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(amountTxt));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(send));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(responseBox));
		
		layout.setHorizontalGroup(hGroup);
	    layout.setVerticalGroup(vGroup);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
