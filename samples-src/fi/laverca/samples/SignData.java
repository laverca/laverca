package fi.laverca.samples;

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

import javax.swing.JFileChooser;

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

public class SignData {
	
	private static final Log log = LogFactory.getLog(SignData.class);
	private static FiComRequest req;
	protected static SignDataProggerssBarUpdater callStateProgressBarUpdater = new SignDataProggerssBarUpdater();
	protected static int amountOfCalls = 0;
	
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
		        				amountOfCalls--;
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
		        				amountOfCalls--;
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
		initComponents();
		callStateProgressBarUpdater.start();
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
	
    private static void initComponents() {
    	frame = new javax.swing.JFrame("Sign Data");
        pane = new javax.swing.JPanel();
        lblNumber = new javax.swing.JLabel();
        number = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        callStateProgressBar = new javax.swing.JProgressBar();
        cancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        responseBox = new javax.swing.JTextArea();
        
		final JFileChooser fc = new JFileChooser();
		fc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printSHA1(generateSHA1(fc.getSelectedFile()));
			}
		});
		fc.showOpenDialog(frame);
        
        
        browseButton = new javax.swing.JButton();
        browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.showOpenDialog(frame);
			}
		});

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        lblNumber.setText("Phone number");

        number.setText("+35847001001");

        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amountOfCalls++;
				estamblishConnection(number.getText(), fc.getSelectedFile());
			}
		});

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				req.cancel();
				amountOfCalls--;
			}
		});
        
        responseBox.setColumns(20);
        responseBox.setRows(5);
        jScrollPane1.setViewportView(responseBox);

        browseButton.setText("Browse...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(number, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(lblNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(callStateProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(sendButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
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
    private static javax.swing.JButton browseButton;
    private static javax.swing.JLabel lblNumber;
    private static javax.swing.JPanel pane;
    protected static javax.swing.JProgressBar callStateProgressBar;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea responseBox;
    private static javax.swing.JTextField number;
}

class SignDataProggerssBarUpdater extends Thread {
	
	public void run() {
		while (true) {
			if (SignData.amountOfCalls > 0) {
				int value = SignData.callStateProgressBar.getValue() > 90 ? 10 : SignData.callStateProgressBar.getValue()+10;
				SignData.callStateProgressBar.setValue(value);
			} else {
				SignData.callStateProgressBar.setValue(0);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}