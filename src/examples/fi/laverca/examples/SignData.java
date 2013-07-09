/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2013 Laverca Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fi.laverca.examples;

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
import java.util.Properties;

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
 */
public class SignData {
	
	private static final String CONFIG_LOCATION = "fi/laverca/samples/configuration.xml";
    private static final Log log = LogFactory.getLog(SignData.class);	
	
	/**
     * Generates a new window for connection that includes 
     * a response box, a cancel button and a progress bar.
     */
    private class ResponseWindow {
    	
    	private JProgressBar callStateProgressBar;
    	private JButton cancelButton;
    	private String eventId;
        private JScrollPane jScrollPane1;
        private FiComRequest req;
        private JTextArea responseBox;
        private JFrame responseFrame;
    	
        /**
         * Generates a new window for response and calls <code>connect</code>
         * to start the authentication process.
         */
    	public ResponseWindow(String number, File selectedFile) {
    		Long currentTimeMillis = System.currentTimeMillis();
    		eventId = "A" + currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
    		initResponse();
    		connect(number, selectedFile);
    	}

    	/**
    	 * Connects to MSSP using SSL and waits for response.
    	 * @param phoneNumber
    	 * @param selectedFile
    	 */
    	protected void connect(String phoneNumber, final File selectedFile) {
            
            Properties properties = ExampleConf.getProperties();
            
            log.info("setting up ssl");
            JvmSsl.setSSL(properties.getProperty(ExampleConf.TRUSTSTORE_FILE),
                    properties.getProperty(ExampleConf.TRUSTSTORE_PASSWORD),
                    properties.getProperty(ExampleConf.KEYSTORE_FILE),
                    properties.getProperty(ExampleConf.KEYSTORE_PASSWORD),
                    properties.getProperty(ExampleConf.KEYSTORE_TYPE));
            
            String apId  = properties.getProperty(ExampleConf.AP_ID);
            String apPwd = properties.getProperty(ExampleConf.AP_PASSWORD);

            String msspSignatureUrl    = properties.getProperty(ExampleConf.SIGNATURE_URL);
            String msspStatusUrl       = properties.getProperty(ExampleConf.STATUS_URL);
            String msspReceiptUrl      = properties.getProperty(ExampleConf.RECEIPT_URL);

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
    							public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate) {
    								
    							}

    		        		});
            }
            catch (IOException e) {
            	SignData.log.info("error establishing connection", e);
            }

            fiComClient.shutdown();
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
    }
	private static JButton browseButton;	
	private static JFrame dialFrame;	
    private static JPanel dialPanel;   
    private static JTextArea hashBox;   
    private static JScrollPane jScrollPane1;
    private static JLabel lblNumber;
    private static JTextField number;
    private static JButton sendButton;
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
	 * Generates SHA1 hash from a file and asks for a user to sign it.
	 * @param args
	 */
	public static void main(String[] args) {
		new SignData().initUI();
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
}