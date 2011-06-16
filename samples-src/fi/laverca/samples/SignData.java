package fi.laverca.samples;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import fi.laverca.FiComAdditionalServices.PersonIdAttribute;

public class SignData {
	
	private static final Log log = LogFactory.getLog(SignData.class);
	private static FiComRequest req;
	private static JTextArea responseBox = new JTextArea();
	
	/**
	 * Connects to MSSP using SSL and waits for response.
	 * @param phoneNumber
	 * @param selectedFile
	 */
	private static void estamblishConnection(String phoneNumber, final File selectedFile) {
		
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
        
        final byte[] output = generateSHA1(selectedFile);
        
        String apTransId = "A"+System.currentTimeMillis();
        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        LinkedList<Service> additionalServices = new LinkedList<Service>();
        LinkedList<String> attributeNames = new LinkedList<String>();
        attributeNames.add(FiComAdditionalServices.PERSON_ID_VALIDUNTIL);
        Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        additionalServices.add(personIdService);
        
        try {
        	log.info("calling signData");
        	req = 
        		fiComClient.signData(apTransId, 
        				output, 
        				phoneNumber, 
        				noSpamService, 
        				additionalServices, 
        				new FiComResponseHandler() {
		        			@Override
		        			public void onResponse(FiComRequest req, FiComResponse resp) {
		        				log.info("got resp");
		        				printSHA1(output);
		        				
		        				responseBox.setText("File path: " + selectedFile.getAbsolutePath() 
		        						+ "\n" + responseBox.getText());
		        				
		        				try {
		        					responseBox.setText("MSS Signature: " + 
		        							new String(Base64.encode(resp.getMSS_StatusResp().
		        							getMSS_Signature().getBase64Signature()), "ASCII") +
		        							"\nSigner: " + resp.getPkcs7Signature().getSignerCn() +
		        							"\n" + responseBox.getText());
		        					for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
		        						log.info(a.getName() + " " + a.getStringValue());
		        						responseBox.setText(a.getStringValue() + "\n" + responseBox.getText());
		        					}
		        				} catch (UnsupportedEncodingException e) {
		        					log.info("Unsupported encoding", e);
		        				} catch (NullPointerException e){
		        					log.info("PersonIDAttributes = null", e);
		        				}
		
		        			}
		
		        			@Override
		        			public void onError(FiComRequest req, Throwable throwable) {
		        				log.info("got error", throwable);
		        			}
		        		});
        }
        catch (IOException e) {
            log.info("error establishing connection", e);
        }

        fiComClient.shutdown();
	}
	
	/**
	 * Generates SHA1 hash from a file and asks for a user to sign it.
	 * @param args
	 */
	public static void main(String[] args) {
		
		final JFrame frame = new JFrame("Sign data");
		Container pane = frame.getContentPane();
		frame.setSize(380, 380);
		
		final JFileChooser fc = new JFileChooser();
		fc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printSHA1(generateSHA1(fc.getSelectedFile()));
			}
		});
		fc.showOpenDialog(frame);
		
		/*JButton browse = new JButton("Browse");
		browse.setPreferredSize(new Dimension(80, 10));
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.showOpenDialog(frame);
			}
		});
		pane.add(browse, BorderLayout.WEST);*/
		
		JButton cancel = new JButton("Cancel");
		cancel.setPreferredSize(new Dimension(80, 10));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				req.cancel();
				responseBox.setText("Canceled\n" + responseBox.getText());
			}
		});
		pane.add(cancel, BorderLayout.WEST);
		
		pane.add(new JLabel("Phone number"), BorderLayout.PAGE_START);
		final JTextField number = new JTextField("+35847001001");
		number.setPreferredSize(new Dimension(230, 10));
		pane.add(number, BorderLayout.CENTER);
		JButton send = new JButton("Send");
		send.setPreferredSize(new Dimension(70, 10));
		pane.add(send, BorderLayout.EAST);
		responseBox.setPreferredSize(new Dimension(200, 300));
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estamblishConnection(number.getText(), fc.getSelectedFile());
			}
		});
		pane.add(responseBox, BorderLayout.PAGE_END);
		frame.setVisible(true);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 
	 * @param selectedFile
	 * @return
	 */
	public static byte[] generateSHA1(final File selectedFile) {
		byte[] output = null;
		try {
			InputStream is = new FileInputStream(selectedFile);
			byte[] buffer = new byte[1024];
			MessageDigest md = MessageDigest.getInstance("SHA1");
			int numRead;
			do {
				numRead = is.read(buffer);
				if (numRead > 0) {
					md.update(buffer, 0, numRead);
				}
			} while (numRead != -1);
			output = md.digest();
		} catch (NoSuchAlgorithmException e) {
			log.info("error finding algorithm", e);
		} catch (FileNotFoundException e) {
			log.info("error finding file", e);
		} catch (IOException e) {
			log.info("i/o error", e);
		}
        
        return output;
	}
	
    /**
     * Prints SHA1 to the <code>responseBox</code>.
     * @param buf
     */
	public static void printSHA1(byte[] buf) {
        Formatter formatter = new Formatter();
        for (byte b : buf)
            formatter.format("%02x", b);
        
        // Cuts first 8 characters of a hash because first 8 characters should be cut from a receiver.
        String sha1 = formatter.toString().substring(8).toUpperCase();
        String shaTmp = new String();
        for (int i = 0; i < sha1.length()/4; i++){
        	shaTmp += " " + sha1.substring(i*4, i*4+4);
        }
        responseBox.setText("SHA1: " + shaTmp + "\n\n" + responseBox.getText());
    }
}
