package fi.laverca.samples;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

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
import fi.laverca.FiComAdditionalServices.PersonIdAttribute;
import fi.laverca.FiComClient;
import fi.laverca.FiComRequest;
import fi.laverca.FiComResponse;
import fi.laverca.FiComResponseHandler;
import fi.laverca.JvmSsl;

public class SignText {

	private static final Log log = LogFactory.getLog(SignText.class);
	
	private static JTextArea responseBox = new JTextArea();
	
	/**
	 * Connects to MSSP using SSL and waits for response
	 * @param phoneNumber
	 * @param textToBeSigned
	 */
	
	private static void estamblishConnection(final String phoneNumber, final String textToBeSigned) {
		
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

        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        LinkedList<Service> additionalServices = new LinkedList<Service>();
        LinkedList<String> attributeNames = new LinkedList<String>();
        attributeNames.add(FiComAdditionalServices.PERSON_ID_VALIDUNTIL);
        Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        additionalServices.add(personIdService);
        
        try {
        	log.info("calling signText");
        	fiComClient.signText(apTransId, 
        			textToBeSigned, 
        			phoneNumber, 
        			noSpamService, 
        			additionalServices, 
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
		        			
		        			for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
		        				log.info(a.getName() + " " + a.getStringValue());
		        				responseBox.setText(a.getStringValue() + "\n" + responseBox.getText());
		        			}
		        			
		        			responseBox.setText(textToBeSigned + "\n" + responseBox.getText());
		        		}
		
		        		@Override
		        		public void onError(FiComRequest req, Throwable throwable) {
		        			log.info("got error", throwable);
		        			responseBox.setText("ERROR, " + phoneNumber + "\n" +
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
	 * Asks a user for text to sign.
	 * @param args
	 */	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Sign Text");
		frame.setSize(800, 380);
		
		Container pane = frame.getContentPane();
		GroupLayout layout = new GroupLayout(pane);
		pane.setLayout(layout);
		layout.setAutoCreateGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		
		JLabel lblNumber = new JLabel("Phone number");
		final JTextField number = new JTextField("+35847001001");
		number.setPreferredSize(new Dimension(800, 10));
		
		JLabel lblTxtToBeSigned = new JLabel("Text to be signed");
		final JTextArea textToBeSigned = new JTextArea();
		textToBeSigned.setPreferredSize(new Dimension(800, 10));
		
		JButton send = new JButton("Send");
		send.setPreferredSize(new Dimension(70, 10));
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estamblishConnection(number.getText(), textToBeSigned.getText());
			}
		});
		
		responseBox.setPreferredSize(new Dimension(800, 600));

		hGroup.addGroup(layout.createParallelGroup().addComponent(lblNumber).addComponent(number).
				addComponent(lblTxtToBeSigned).addComponent(textToBeSigned).addComponent(send).addComponent(responseBox));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lblNumber));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(number));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(lblTxtToBeSigned));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
				addComponent(textToBeSigned));
		
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
