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

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;
import org.etsi.uri.TS102204.v1_1_2.MSS_SignatureResp;
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
		new SignData().initUI();
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
	public static void printSHA1(byte[] buf, JTextArea textArea) {
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
    
    private void initUI() {

    	dialFrame = new JFrame("Sign Data");
        dialPanel = new JPanel();
        lblNumber = new JLabel();
        number = new JTextField();
        sendButton = new JButton();
        browseButton = new JButton();
        jScrollPane1 = new JScrollPane();
        hashBox = new JTextArea();

        dialFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        dialFrame.setVisible(true);
        dialFrame.setResizable(false);
        
		final JFileChooser fc = new JFileChooser();

        browseButton.setText("Browse...");
        browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.showOpenDialog(dialFrame);
			}
		});

        dialFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        GroupLayout jPanel1Layout = new GroupLayout(dialPanel);
        dialPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNumber)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sendButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
                    .addComponent(number, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblNumber)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(number, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(browseButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(dialFrame.getContentPane());
        dialFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dialPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dialPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    
    private static JFrame dialFrame;
    private static JButton sendButton;
    private static JButton browseButton;
    private static JLabel lblNumber;
    private static JPanel dialPanel;
    private static JScrollPane jScrollPane1;
    private static JTextArea hashBox;
    private static JTextField number;
    
    
    /**
     * Generates a new window for connection that includes 
     * a response box, a cancel button and a progress bar.
     * 
	 * @author Jan Mikael Lindlöf
	 * @author Eemeli Miettinen
     */
    private class ResponseWindow {
    	
    	private FiComRequest req;
    	private JTextArea responseBox;
    	private JFrame responseFrame;
        private JButton cancelButton;
        private JProgressBar callStateProgressBar;
        private JScrollPane jScrollPane1;
        private String eventId;
    	
        /**
         * Generates a new window for response and calls <code>estamblishConnection</code>
         * to start the authentication process.
         * 
    	 * @author Jan Mikael Lindlöf
    	 * @author Eemeli Miettinen
         */
    	public ResponseWindow(String number, File selectedFile) {
    		Long currentTimeMillis = System.currentTimeMillis();
    		eventId = "A" + currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
    		initResponse();
    		establishConnection(number, selectedFile);
    	}

    	private void initResponse() {
        	responseFrame = new JFrame(eventId);
            cancelButton = new JButton();
            jScrollPane1 = new JScrollPane();
            responseBox = new JTextArea();
            callStateProgressBar = new JProgressBar();

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

            GroupLayout layout = new GroupLayout(responseFrame.getContentPane());
            responseFrame.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(callStateProgressBar, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(cancelButton)
                    .addContainerGap(47, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                        .addGap(14, 14, 14)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(callStateProgressBar, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(cancelButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(266, 266, 266))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(53, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
            );
            
            responseFrame.setLocation(500, 0);

            responseFrame.pack();
            responseFrame.setVisible(true);
            responseFrame.setResizable(false);
        }
    	
    	/**
    	 * Connects to MSSP using SSL and waits for response.
    	 * @param phoneNumber
    	 * @param selectedFile
    	 */
    	protected void establishConnection(String phoneNumber, final File selectedFile) {
    		
    		XMLConfiguration config = null;
    		try {
    		    config = new XMLConfiguration("fi/laverca/samples/configuration.xml");
    		} catch(ConfigurationException e) {
    		    log.info("configuration file not found", e);
    		}
    		
    		log.info("setting up ssl");
    		JvmSsl.setSSL(config.getString("ssl.trustStore"),
    				config.getString("ssl.trustStorePassword"),
    				config.getString("ssl.keyStore"),
    				config.getString("ssl.keyStorePassword"),
    				config.getString("ssl.keyStoreType"));
    		
    		String apId  = config.getString("ap.apId");
            String apPwd = config.getString("ap.apPwd");

            String msspSignatureUrl    = config.getString("mssp.msspSignatureUrl");
            String msspStatusUrl       = config.getString("mssp.msspStatusUrl");
            String msspReceiptUrl      = config.getString("mssp.msspReceiptUrl");

            SignData.log.info("creating FiComClient");
            FiComClient fiComClient = new FiComClient(apId, 
                                                      apPwd, 
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
    		        			public void onResponse(FiComRequest req, FiComResponse resp, MSS_SignatureResp sigResp) {
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
    							public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate, MSS_SignatureResp sigResp) {
    								
    							}
    		        		});
            }
            catch (IOException e) {
            	SignData.log.info("error establishing connection", e);
            }

            fiComClient.shutdown();
    	}
    }
}