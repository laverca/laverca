package fi.laverca.samples;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.laverca.DTBS;
import fi.laverca.FiComAdditionalServices;
import fi.laverca.FiComClient;
import fi.laverca.FiComRequest;
import fi.laverca.FiComResponse;
import fi.laverca.FiComResponseHandler;
import fi.laverca.JvmSsl;
import fi.laverca.FiComAdditionalServices.PersonIdAttribute;

public class AnonAuthentication {

	private static final Log log = LogFactory.getLog(AnonAuthentication.class);
	
	private static JTextArea responseBox = new JTextArea();
	
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
            fiComClient.authenticateAnon(apTransId, 
            		authnChallenge, 
            		phoneNumber, 
            		noSpamService, 
            		additionalServices, 
            		new FiComResponseHandler() {
		            	@Override
		            	public void onResponse(FiComRequest req, FiComResponse resp) {
		            		log.info("got resp");
		            		responseBox.setText("\n" + responseBox.getText());
		            		
		            		for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
		            			log.info(a.getStringValue());
		            			responseBox.setText(a.getStringValue() + " " + responseBox.getText());
		            		}
		            	}
		
		            	@Override
		            	public void onError(FiComRequest req, Throwable throwable) {
		            		log.info("got error", throwable);
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
		
		JFrame frame = new JFrame("Anonyme Authentication");
		frame.setSize(300, 380);
		
		Container pane = frame.getContentPane();
		
		pane.add(new JLabel("Phone number"), BorderLayout.PAGE_START);
		final JTextField number = new JTextField("+35847001001");
		number.setPreferredSize(new Dimension(230, 10));
		pane.add(number, BorderLayout.CENTER);
		
		JButton send = new JButton("Send");
		send.setPreferredSize(new Dimension(70, 10));
		pane.add(send, BorderLayout.EAST);
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estamblishConnection(number.getText());
			}
		});
		
		responseBox.setPreferredSize(new Dimension(200, 300));
		pane.add(responseBox, BorderLayout.PAGE_END);
		
		frame.setVisible(true);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
