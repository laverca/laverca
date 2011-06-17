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
import fi.laverca.ProgressUpdate;

/**
 * Sample for demonstrating hash signing. 
 * 
 * @author Jan Mikael Lindlöf
 * @author Eemeli Miettinen
 *
 */

public class SignData {
	
	protected final static Log log = LogFactory.getLog(SignData.class);
	
	/**
	 * Generates SHA1 hash from a file and asks for a user to sign it.
	 * @param args
	 */
	public static void main(String[] args) {		
		initUI();
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
			SignData.log.info("error finding algorithm", e);
		} catch (FileNotFoundException e) {
			SignData.log.info("error finding file", e);
		} catch (IOException e) {
			SignData.log.info("i/o error", e);
		}
        
        return output;
	}
	
    /**
     * Prints SHA1 to the <code>responseBox</code>.
     * @param buf
     */
	public static void printSHA1(byte[] buf, javax.swing.JTextArea textArea) {
        Formatter formatter = new Formatter();
        for (byte b : buf)
            formatter.format("%02x", b);
        
        // Cuts first 8 characters of the hash to make the generated hash match the one in the phone.
        String sha1 = formatter.toString().substring(8).toUpperCase();
        String shaTmp = new String();
        for (int i = 0; i < sha1.length()/4; i++){
        	shaTmp += " " + sha1.substring(i*4, i*4+4);
        }
        textArea.setText("SHA1: " + shaTmp + "\n" + textArea.getText());
    }
    
    private static void initUI() {

    	dialFrame = new javax.swing.JFrame("Sign Data");
        dialPanel = new javax.swing.JPanel();
        lblNumber = new javax.swing.JLabel();
        number = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        browseButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        hashBox = new javax.swing.JTextArea();

        dialFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        dialFrame.setVisible(true);
        dialFrame.setResizable(false);
        
		final JFileChooser fc = new JFileChooser();

        browseButton.setText("Browse...");
        browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.showOpenDialog(dialFrame);
			}
		});

        dialFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        dialFrame.setVisible(true);
        lblNumber.setText("Phone number");

        number.setText("+35847001001");

        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ResponseWindow(number.getText(), fc.getSelectedFile());
				
			}
		});
        
        hashBox.setColumns(20);
        hashBox.setRows(5);
        jScrollPane1.setViewportView(hashBox);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(dialPanel);
        dialPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNumber)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
                    .addComponent(number, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(dialFrame.getContentPane());
        dialFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dialPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dialPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dialFrame.pack();
		fc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printSHA1(generateSHA1(fc.getSelectedFile()), hashBox);
			}
		});
		fc.showOpenDialog(dialFrame);
    }
    
    private static javax.swing.JFrame dialFrame;
    private static javax.swing.JButton sendButton;
    private static javax.swing.JButton browseButton;
    private static javax.swing.JLabel lblNumber;
    private static javax.swing.JPanel dialPanel;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea hashBox;
    private static javax.swing.JTextField number;
}

class ResponseWindow {
	
	private FiComRequest req;
	private javax.swing.JTextArea responseBox;
	private javax.swing.JFrame responseFrame;
    private javax.swing.JButton cancelButton;
    private javax.swing.JProgressBar callStateProgressBar;
    private javax.swing.JScrollPane jScrollPane1;
    private String eventId;
	
	public ResponseWindow(String number, File selectedFile) {
		Long currentTimeMillis = System.currentTimeMillis();
		eventId = "A" + currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
		initResponse();
		estamblishConnection(number, selectedFile);
	}

	public void setCallStateProgressBar(
			javax.swing.JProgressBar callStateProgressBar) {
		this.callStateProgressBar = callStateProgressBar;
	}

	private void initResponse() {
    	responseFrame = new javax.swing.JFrame(eventId);
        cancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        responseBox = new javax.swing.JTextArea();
        callStateProgressBar = new javax.swing.JProgressBar();

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				req.cancel();
				callStateProgressBar.setIndeterminate(false);
				responseFrame.dispose();
			}
		});
        
        responseBox.setColumns(20);
        responseBox.setRows(5);
        jScrollPane1.setViewportView(responseBox);
        callStateProgressBar.setIndeterminate(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(responseFrame.getContentPane());
        responseFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(callStateProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancelButton)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                    .addGap(14, 14, 14)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(callStateProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(266, 266, 266))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(53, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)))
        );

        responseFrame.pack();
        responseFrame.setVisible(true);
        responseFrame.setResizable(false);
    }
	
	/**
	 * Connects to MSSP using SSL and waits for response.
	 * @param phoneNumber
	 * @param selectedFile
	 */
	protected void estamblishConnection(String phoneNumber, final File selectedFile) {
		
		SignData.log.info("setting up ssl");
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

        SignData.log.info("creating FiComClient");
        FiComClient fiComClient = new FiComClient(apId, 
                                                  apPwd, 
                                                  aeMsspIdUri, 
                                                  msspSignatureUrl, 
                                                  msspStatusUrl, 
                                                  msspReceiptUrl);
        
        final byte[] output = SignData.generateSHA1(selectedFile);
        String apTransId = "A"+System.currentTimeMillis();
        
        Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);
        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        LinkedList<Service> additionalServices = new LinkedList<Service>();
        LinkedList<String> attributeNames = new LinkedList<String>();
        attributeNames.add(FiComAdditionalServices.PERSON_ID_VALIDUNTIL);
        Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        additionalServices.add(personIdService);
        
        try {
        	SignData.log.info("calling signData");
        	req = 
        		fiComClient.signData(apTransId, 
        				output, 
        				phoneNumber, 
        				noSpamService, 
        				eventIdService,
        				additionalServices, 
        				new FiComResponseHandler() {
		        			@Override
		        			public void onResponse(FiComRequest req, FiComResponse resp) {
		        				SignData.log.info("got resp");
		        				callStateProgressBar.setIndeterminate(false);
		        				SignData.printSHA1(output, responseBox);
		        				
		        				responseBox.setText("File path: " + selectedFile.getAbsolutePath() 
		        						+ "\n" + responseBox.getText());
		        				
		        				try {
		        					responseBox.setText("MSS Signature: " + 
		        							new String(Base64.encode(resp.getMSS_StatusResp().
		        							getMSS_Signature().getBase64Signature()), "ASCII") +
		        							"\nSigner: " + resp.getPkcs7Signature().getSignerCn() +
		        							"\n" + responseBox.getText());
		        					for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
		        						SignData.log.info(a.getName() + " " + a.getStringValue());
		        						responseBox.setText(a.getStringValue() + "\n" + responseBox.getText());
		        					}
		        				} catch (UnsupportedEncodingException e) {
		        					SignData.log.info("Unsupported encoding", e);
		        				} catch (NullPointerException e){
		        					SignData.log.info("PersonIDAttributes = null", e);
		        				}
		
		        			}
		
		        			@Override
		        			public void onError(FiComRequest req, Throwable throwable) {
		        				callStateProgressBar.setIndeterminate(false);
		        				SignData.log.info("got error", throwable);
		        			}

							@Override
							public void onOutstandingProgress(FiComRequest req,
									ProgressUpdate prgUpdate) {
								// TODO Auto-generated method stub
								
							}
		        		});
        }
        catch (IOException e) {
        	SignData.log.info("error establishing connection", e);
        }

        fiComClient.shutdown();
	}
}