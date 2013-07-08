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

package fi.laverca.samples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Properties;

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
 * Sample for demonstrating text signing.
 */
public class SignText {

	private static final Log log = LogFactory.getLog(SignText.class);
	private static FiComRequest req;
	private static final String CONFIG_LOCATION = "fi/laverca/samples/configuration.xml";
	
	/**
	 * Connects to MSSP using SSL and waits for response
	 * @param phoneNumber
	 * @param textToBeSigned
	 */
	private static void connect(final String phoneNumber, final String textToBeSigned) {
        
        Properties properties = SampleConf.getProperties();
        
        log.info("setting up ssl");
        JvmSsl.setSSL(properties.getProperty(SampleConf.TRUSTSTORE_FILE),
                properties.getProperty(SampleConf.TRUSTSTORE_PASSWORD),
                properties.getProperty(SampleConf.KEYSTORE_FILE),
                properties.getProperty(SampleConf.KEYSTORE_PASSWORD),
                properties.getProperty(SampleConf.KEYSTORE_TYPE));
        
        String apId  = properties.getProperty(SampleConf.AP_ID);
        String apPwd = properties.getProperty(SampleConf.AP_PASSWORD);

        String msspSignatureUrl    = properties.getProperty(SampleConf.SIGNATURE_URL);
        String msspStatusUrl       = properties.getProperty(SampleConf.STATUS_URL);
        String msspReceiptUrl      = properties.getProperty(SampleConf.RECEIPT_URL);

        log.info("creating FiComClient");
        FiComClient fiComClient = new FiComClient(apId, 
                                                  apPwd, 
                                                  msspSignatureUrl, 
                                                  msspStatusUrl, 
                                                  msspReceiptUrl);
        
        Long currentTimeMillis = System.currentTimeMillis();
        String apTransId = "A"+currentTimeMillis;
        final String eventId = "A"+ currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
        Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);
        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        LinkedList<Service> additionalServices = new LinkedList<Service>();
        LinkedList<String> attributeNames = new LinkedList<String>();
        attributeNames.add(FiComAdditionalServices.PERSON_ID_VALIDUNTIL);
        Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        additionalServices.add(personIdService);
        
        try {
        	log.info("calling signText");
        	req = 
	        	fiComClient.signText(apTransId, 
	        			textToBeSigned, 
	        			phoneNumber, 
	        			noSpamService,
	        			eventIdService,
	        			additionalServices, 
	        			new FiComResponseHandler() {
			        		@Override
			        		public void onResponse(FiComRequest req, FiComResponse resp) {
			        			log.info("got resp");
			        			sendButton.setEnabled(true);
								callStateProgressBar.setIndeterminate(false);
			        			try {
			        				responseBox.setText("MSS Signature: " + 
			        						new String(Base64.encode(resp.getMSS_StatusResp().
			        						getMSS_Signature().getBase64Signature()), "ASCII") +
			        						"\n\n" + responseBox.getText());
			        			} catch (UnsupportedEncodingException e) {
			        				log.info("Unsupported encoding", e);
			        			}
			        			try {
				        			for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
				        				log.info(a.getName() + " " + a.getStringValue());
				        				responseBox.setText(a.getStringValue() + "\n" + responseBox.getText());
				        			}
			            		} catch (NullPointerException e){
			            			log.warn("No Person ID Attributes found!");
			            		}
			        			responseBox.setText("Event ID: " + eventId + "\nSigned text: " + textToBeSigned + "\n" + responseBox.getText());
			        		}
			
			        		@Override
			        		public void onError(FiComRequest req, Throwable throwable) {
			        			log.info("got error", throwable);
								callStateProgressBar.setIndeterminate(false);
			        			responseBox.setText("ERROR, " + phoneNumber + "\n" +
			        					responseBox.getText());
			        		}

							@Override
							public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate) {
								
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
		initComponents();
	}
	
    private static void initComponents() {
    	frame = new javax.swing.JFrame("Sign Text");
    	frame.setResizable(false);
        pane = new javax.swing.JPanel();
        lblTxtToBeSigned = new javax.swing.JLabel();
        lblNumber = new javax.swing.JLabel();
        number = new javax.swing.JTextField();
        textToBeSigned = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        callStateProgressBar = new javax.swing.JProgressBar(0, 100);
        cancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        responseBox = new javax.swing.JTextArea();

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        lblNumber.setText("Phone number");

        number.setText("+35847001001");

        lblTxtToBeSigned.setText("Text to be signed");

        textToBeSigned.setText("Sample text");

        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendButton.setEnabled(false);
				connect(number.getText(), textToBeSigned.getText());
				callStateProgressBar.setIndeterminate(true);
			}
		});
        
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendButton.setEnabled(true);
				req.cancel();
				callStateProgressBar.setIndeterminate(false);
			}
		});
        responseBox.setColumns(20);
        responseBox.setRows(5);
        jScrollPane1.setViewportView(responseBox);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pane);
        pane.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(textToBeSigned, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(number, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(lblNumber)
                                .addComponent(lblTxtToBeSigned)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(sendButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(callStateProgressBar, 0, 0, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cancelButton))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cancelButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTxtToBeSigned)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textToBeSigned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(callStateProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(sendButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
    private static javax.swing.JLabel lblNumber;
    private static javax.swing.JLabel lblTxtToBeSigned;
    private static javax.swing.JPanel pane;
    protected static javax.swing.JProgressBar callStateProgressBar;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea responseBox;
    private static javax.swing.JTextField number;
    private static javax.swing.JTextField textToBeSigned;
	
}

