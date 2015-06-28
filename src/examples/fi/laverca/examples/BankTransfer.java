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
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.laverca.JvmSsl;
import fi.laverca.ProgressUpdate;
import fi.laverca.examples.util.ExampleConf;
import fi.laverca.ficom.FiComAdditionalServices;
import fi.laverca.ficom.FiComClient;
import fi.laverca.ficom.FiComRequest;
import fi.laverca.ficom.FiComResponse;
import fi.laverca.ficom.FiComResponseHandler;

/**
 * Sample for demonstrating a bank transfer and sending a receipt.
 */
public class BankTransfer {


    private static final Log log = LogFactory.getLog(BankTransfer.class);
    private static FiComRequest req;
    private static final String CONFIG_LOCATION = "fi/laverca/samples/configuration.xml";
    private static FiComClient fiComClient;
    
    /**
     * Connects to MSSP using SSL and waits for response.
     * @param phoneNumber
     * @param fromTxt
     * @param toTxt
     * @param amountTxt
     */
    private static void connect(final String phoneNumber, final String fromTxt, final String toTxt, final String amountTxt) {
        
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

        log.info("creating FiComClient");
        fiComClient = new FiComClient(apId, 
                                        apPwd, 
                                        msspSignatureUrl, 
                                        msspStatusUrl, 
                                        msspReceiptUrl);
        
        Long currentTimeMillis = System.currentTimeMillis();
        String apTransId = "A"+currentTimeMillis;
        final String eventId = "A"+ currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
        
        String textToBeConsentedTo = "Do you allow a transfer from " + fromTxt + " to " + toTxt + ", " + amountTxt;
 
        Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);        
        Service noSpamService = FiComAdditionalServices.createNoSpamService("A12", false);
        
        try {
           log.info("calling consent");
           req =
               fiComClient.consent(apTransId, 
                    textToBeConsentedTo, 
                    phoneNumber, 
                    noSpamService,
                    eventIdService,
                    null, 
                    new FiComResponseHandler() {
                        @Override
                        public void onResponse(FiComRequest req, FiComResponse resp) {
                            log.info("got resp");
                            sendButton.setEnabled(true);
                            callStateProgressBar.setIndeterminate(false);
                            
                            String numberResponding = resp.getMSS_StatusResp().getMobileUser().getMSISDN();
                            if (numberResponding.equals(phoneNumber)) {
                                fiComClient.sendReceiptReq(resp, phoneNumber + " successfully authenticated the bank transfer.");
                                try {
                                    responseBox.setText("\nMSS Signature: " + 
                                            new String(Base64.encode(resp.getMSS_StatusResp().
                                            getMSS_Signature().getBase64Signature()), "ASCII") +
                                            "\n\n" + responseBox.getText());
                                } catch (UnsupportedEncodingException e) {
                                    log.info("Unsupported encoding", e);
                                }
                                
                                responseBox.setText("User allowed the transfer from " + fromTxt + 
                                        " to\n" + toTxt + ", " + amountTxt + "\n" + responseBox.getText());
                                responseBox.setText("Event ID: " + eventId + "\n" + responseBox.getText());    
                            } else {
                                responseBox.setText("The transfer failed because " + numberResponding + 
                                        " tried to authenticate the transfer of " + phoneNumber);
                            }
                            
                        }
        
                        @Override
                        public void onError(FiComRequest req, Throwable throwable) {
                            log.info("got error", throwable);
                            responseBox.setText(throwable.getMessage());
                            callStateProgressBar.setIndeterminate(false);

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
     * Allows user to sign a bank transfer.
     * @param args
     */
    
    public static void main(String[] args) {
        initComponents();
    }
    
    private static void initComponents() {
        frame = new javax.swing.JFrame("Bank Transfer");
        frame.setResizable(false);
        pane = new javax.swing.JPanel();
        lblNumber = new javax.swing.JLabel();
        number = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        callStateProgressBar = new javax.swing.JProgressBar();
        cancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        responseBox = new javax.swing.JTextArea();
        lblFromTxt = new javax.swing.JLabel();
        lblToTxt = new javax.swing.JLabel();
        fromTxt = new javax.swing.JTextField();
        toTxt = new javax.swing.JTextField();
        lblAmountTxt = new javax.swing.JLabel();
        amountTxt = new javax.swing.JTextField();

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        lblNumber.setText("Phone number");

        number.setText("+35847001001");

        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendButton.setEnabled(false);
                connect(number.getText(), fromTxt.getText(), toTxt.getText(), amountTxt.getText());
                callStateProgressBar.setIndeterminate(true);
            }
        });
        
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                req.cancel();
                sendButton.setEnabled(true);
                responseBox.setText("Canceled\n" + responseBox.getText());
                callStateProgressBar.setIndeterminate(false);
            }
        });
        
        responseBox.setColumns(20);
        responseBox.setRows(5);
        jScrollPane1.setViewportView(responseBox);

        lblFromTxt.setText("From account");

        lblToTxt.setText("To account");

        fromTxt.setText("Bank account 1");

        toTxt.setText("Bank account 2");

        lblAmountTxt.setText("Amount");

        amountTxt.setText("50 eur");

        javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                    .addGroup(paneLayout.createSequentialGroup()
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(callStateProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(paneLayout.createSequentialGroup()
                        .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(number, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addComponent(lblNumber, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneLayout.createSequentialGroup()
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblFromTxt)
                                    .addComponent(fromTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(amountTxt))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblToTxt)
                                    .addComponent(toTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(85, 85, 85))
                    .addComponent(lblAmountTxt))
                .addContainerGap())
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addComponent(lblNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFromTxt)
                    .addComponent(lblToTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAmountTxt)
                .addGap(3, 3, 3)
                .addComponent(amountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(callStateProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(sendButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frame.pack();
    }
    
    private static javax.swing.JFrame frame;
    private static javax.swing.JTextField amountTxt;
    protected static javax.swing.JProgressBar callStateProgressBar;
    private static javax.swing.JButton cancelButton;
    private static javax.swing.JTextField fromTxt;
    private static javax.swing.JPanel pane;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel lblAmountTxt;
    private static javax.swing.JLabel lblFromTxt;
    private static javax.swing.JLabel lblNumber;
    private static javax.swing.JLabel lblToTxt;
    private static javax.swing.JTextField number;
    private static javax.swing.JTextArea responseBox;
    private static javax.swing.JButton sendButton;
    private static javax.swing.JTextField toTxt;
    
}