/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2014 Laverca Project
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.laverca.ficom.FiComAdditionalServices;
import fi.laverca.ficom.FiComClient;
import fi.laverca.ficom.FiComRequest;
import fi.laverca.ficom.FiComResponse;
import fi.laverca.ficom.FiComResponseHandler;
import fi.laverca.ficom.FiComAdditionalServices.PersonIdAttribute;
import fi.laverca.JvmSsl;
import fi.laverca.ProgressUpdate;

/**
 * Sample for demonstrating text signing.
 */
public class SignText {

    private static final Log log = LogFactory.getLog(SignText.class);
    private static FiComRequest req;
    
    /**
     * Connects to MSSP using SSL and waits for response
     * @param phoneNumber
     * @param textToBeSigned
     */
    private static void connect(final String phoneNumber, final String textToBeSigned) {
        
        Properties properties = ExampleConf.getProperties();
        
        log.info("Setting up SSL");
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

        log.info("Creating FiComClient");
        FiComClient fiComClient = new FiComClient(apId, 
                                                  apPwd, 
                                                  msspSignatureUrl, 
                                                  msspStatusUrl, 
                                                  msspReceiptUrl);
        
        Long currentTimeMillis = System.currentTimeMillis();
        final String apTransId = "A"+currentTimeMillis;
        final String eventId   = "A"+ currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
        
        Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);
        Service noSpamService  = FiComAdditionalServices.createNoSpamService("A12", false);
        
        // Create additional services
        Service personIdService = FiComAdditionalServices.createPersonIdService(FiComAdditionalServices.PERSON_ID_VALIDUNTIL);
        
        List<Service> additionalServices = new ArrayList<Service>();                      
        additionalServices.add(personIdService);
        
        responseBox.setText("Event ID: " + eventId);
        
        try {
            log.info("Calling signText");            
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
                                log.info("Got response");
                                sendButton.setEnabled(true);
                                callStateProgressBar.setIndeterminate(false);

                                responseBox.setText("\nSigned text: " + textToBeSigned + "\n" + responseBox.getText());
                                
                                // Write received PersonId attributes to the response textbox
                                if (resp.getPersonIdAttributes() != null) {
                                    for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
                                        if (a != null) {
                                            log.info(a.getName() + ": " + a.getStringValue());
                                            responseBox.setText(a.getName() + ": " + a.getStringValue() + "\n" + responseBox.getText());
                                        }
                                    }
                                } else {
                                    log.info("No Person ID Attributes found!");
                                }
                            }
            
                            @Override
                            public void onError(FiComRequest req, Throwable t) {
                                log.info("Received error.", t);
                                callStateProgressBar.setIndeterminate(false);
                                responseBox.setText("SignText failed (" + eventId + "): " + ( t != null ? t.getMessage() : null));
                            }

                            @Override
                            public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate) {
                                // Do nothing
                            }
                        });
        }
        catch (IOException e) {
            log.info("error establishing connection", e);
        }

        fiComClient.shutdown();
    }

    /**
     * Main method
     * 
     */
    public static void main(String[] args) {
        initComponents();
    }
    
    /*
     * Swing UI
     */
    
    private static javax.swing.JFrame frame;
    private static javax.swing.JButton sendButton;
    private static javax.swing.JButton cancelButton;
    private static javax.swing.JLabel numberLabel;
    private static javax.swing.JLabel textToBeSignedLabel;
    private static javax.swing.JPanel panel;
    private static javax.swing.JProgressBar callStateProgressBar;
    private static javax.swing.JScrollPane scrollPanel;
    private static javax.swing.JTextArea responseBox;
    private static javax.swing.JTextField numberField;
    private static javax.swing.JTextField textToBeSignedField;
    
    /**
     * Initializes the UI components.
     */
    private static void initComponents() {
        frame = new javax.swing.JFrame("Sign Text");
        frame.setResizable(false);
        
        panel                = new javax.swing.JPanel();
        textToBeSignedLabel  = new javax.swing.JLabel();
        numberLabel          = new javax.swing.JLabel();
        textToBeSignedField  = new javax.swing.JTextField();
        numberField          = new javax.swing.JTextField();
        sendButton           = new javax.swing.JButton();
        cancelButton         = new javax.swing.JButton();
        scrollPanel          = new javax.swing.JScrollPane();
        responseBox          = new javax.swing.JTextArea();
        callStateProgressBar = new javax.swing.JProgressBar(0, 100);

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        numberLabel.setText("Phone number");
        numberField.setText("+35847001001");

        textToBeSignedLabel.setText("Text to be signed");
        textToBeSignedField.setText("Sample text");

        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButton.setEnabled(false);
                connect(numberField.getText(), textToBeSignedField.getText());
                callStateProgressBar.setIndeterminate(true);
            }
        });
        
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButton.setEnabled(true);
                req.cancel();
                callStateProgressBar.setIndeterminate(false);
            }
        });
        responseBox.setColumns(20);
        responseBox.setRows(5);
        scrollPanel.setViewportView(responseBox);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(textToBeSignedField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(numberField, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(numberLabel)
                                .addComponent(textToBeSignedLabel)
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
                        .addComponent(numberLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textToBeSignedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textToBeSignedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(callStateProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(sendButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frame.pack();
    }
    
}

