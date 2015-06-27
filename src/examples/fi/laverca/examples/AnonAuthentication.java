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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etsi.uri.TS102204.v1_1_2.Service;

import fi.laverca.DTBS;
import fi.laverca.JvmSsl;
import fi.laverca.ProgressUpdate;
import fi.laverca.ficom.FiComAdditionalServices;
import fi.laverca.ficom.FiComAdditionalServices.PersonIdAttribute;
import fi.laverca.ficom.FiComClient;
import fi.laverca.ficom.FiComRequest;
import fi.laverca.ficom.FiComResponse;
import fi.laverca.ficom.FiComResponseHandler;

/**
 * Example application for demonstrating anonymous authentication.
 * 
 * <ul>
 * <li>Creates a swing UI 
 * <li>Requests the user's gender
 * </ul>
 * 
 * <p>Uses the following FiCom AdditionalServices:
 * <ul>
 * <li>EventID
 * <li>NoSpamCode
 * <li>PersonID
 * </ul>
 * 
 */
public class AnonAuthentication {

    private static final Log log = LogFactory.getLog(AnonAuthentication.class);
    
    private static final String DEFAULT_MSISDN   = "+35847001001";
    private static final String FIXED_NOSPAMCODE = "A12";
    
    private FiComClient  client;
    private FiComRequest req;
    private Properties   conf;

    /**
     * Initializes this example
     */
    private void init() {
        
        this.conf = ExampleConf.getProperties();
        
        log.info("Setting up ssl");
        JvmSsl.setSSL(this.conf.getProperty(ExampleConf.TRUSTSTORE_FILE),
                      this.conf.getProperty(ExampleConf.TRUSTSTORE_PASSWORD),
                      this.conf.getProperty(ExampleConf.KEYSTORE_FILE),
                      this.conf.getProperty(ExampleConf.KEYSTORE_PASSWORD),
                      this.conf.getProperty(ExampleConf.KEYSTORE_TYPE));
        
        final String apId  = this.conf.getProperty(ExampleConf.AP_ID);
        final String apPwd = this.conf.getProperty(ExampleConf.AP_PASSWORD);

        final String msspSignatureUrl = this.conf.getProperty(ExampleConf.SIGNATURE_URL);
        final String msspStatusUrl    = this.conf.getProperty(ExampleConf.STATUS_URL);
        final String msspReceiptUrl   = this.conf.getProperty(ExampleConf.RECEIPT_URL);

        log.info("Creating FiComClient");
        this.client = new FiComClient(apId, 
                                      apPwd, 
                                      msspSignatureUrl, 
                                      msspStatusUrl, 
                                      msspReceiptUrl);
    }
    
    
    /**
     * Authenticates to the MSSP with the FiCom anonymous profile
     * 
     * @param phoneNumber MSISDN of the user
     */
    private void authenticate(final String phoneNumber) {
        
        // Generate IDs
        final Long currentTimeMillis = System.currentTimeMillis();
        final String apTransId = "A" + currentTimeMillis;
        final String eventId   = "A" + currentTimeMillis.toString().substring(currentTimeMillis.toString().length()-4);
        
        byte[] authnChallenge = new DTBS(apTransId, DTBS.ENCODING_UTF8).toBytes();

        // Add additional services
        LinkedList<Service> additionalServices = new LinkedList<Service>();        
        
        Service eventIdService  = FiComAdditionalServices.createEventIdService(eventId);
        Service noSpamService   = FiComAdditionalServices.createNoSpamService(FIXED_NOSPAMCODE, false);
        Service personIdService = FiComAdditionalServices.createPersonIdService(FiComAdditionalServices.PERSON_ID_GENDER);
        
        additionalServices.add(eventIdService);
        additionalServices.add(noSpamService);
        additionalServices.add(personIdService);
        
        // Create response handler
        FiComResponseHandler handler = new FiComResponseHandler() {
            @Override
            public void onResponse(FiComRequest req, FiComResponse resp) {
                log.info("Got response");
                displayResponse(eventId, resp, null);
            }

            @Override
            public void onError(FiComRequest req, Throwable throwable) {
                log.info("Got error");
                displayResponse(eventId, null, throwable);
            }

            @Override
            public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate) {
                // Ignore
            }
        };
        
        // Attempt to authenticate
        try {
            log.info("Authenticating");
            this.req = client.authenticateAnon(apTransId, 
                                               authnChallenge, 
                                               phoneNumber, 
                                               noSpamService, 
                                               eventIdService,
                                               additionalServices, 
                                               handler);
            
            // Display Event ID
            this.displayResponse(eventId, null, null);
        } catch (IOException ioe) {
            this.displayResponse(eventId, null, ioe);
            log.info("Error establishing connection: " + ioe.getMessage());
        }

    }
    
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        AnonAuthentication main = new AnonAuthentication();
        main.initGUI();
        main.init();
    }
    
    // GUI

    private JProgressBar callState;
    private JButton      sendButton;
    private JButton      cancelButton;
    private JTextArea    responseBox;
    
    /**
     * Initializes the swing GUI
     */
    private void initGUI() {
 
        // Init frame & panels
        final JFrame frame = new JFrame("Anon authentication");
        final JPanel panel = new JPanel();
        final JScrollPane jScrollPane = new JScrollPane();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Close client & exit
                client.shutdown();
                System.exit(0);
            }
        });

        // Init phone number field
        final JLabel  lblNumber = new JLabel();
        final JTextField number = new JTextField();
        lblNumber.setText("Phone number");
        number.setText(DEFAULT_MSISDN);

        // Init progress bar & response box
        this.callState    = new JProgressBar();
        this.responseBox  = new JTextArea();
        this.responseBox.setColumns(20);
        this.responseBox.setRows(5);
        jScrollPane.setViewportView(this.responseBox);
        
        // Init buttons
        this.cancelButton = new JButton();
        this.sendButton   = new JButton();

        this.sendButton.setText("Send");
        this.sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendButton.setEnabled(false);
                callState.setIndeterminate(true);
                authenticate(number.getText());
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendButton.setEnabled(true);
                req.cancel();
                callState.setIndeterminate(false);
            }
        });
        
        // Init layout
        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                                .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(number, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                                .addComponent(lblNumber, GroupLayout.Alignment.LEADING))
                                                .addGap(85, 85, 85))
                                                .addGroup(panelLayout.createSequentialGroup()
                                                        .addComponent(this.sendButton)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(this.callState, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(this.cancelButton)))
                                                        .addContainerGap())
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(lblNumber)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(number, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.cancelButton, GroupLayout.Alignment.TRAILING)
                                .addComponent(this.callState, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                                .addComponent(this.sendButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        
        GroupLayout frameLayout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
                frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
        frameLayout.setVerticalGroup(
                frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );

        frame.pack();
    }
    
    /**
     * Display the received response on the GUI
     * 
     * @param resp FiComResponse
     * @param eventId Sent EventID
     * @param t received error
     */
    private void displayResponse(final String        eventId,
                                 final FiComResponse resp,
                                 final Throwable     t) {
        
        StringBuilder responseMsg = new StringBuilder();
        responseMsg.append("Event ID: " + eventId + "\n");
        
        sendButton.setEnabled(true);
       
        if (resp != null) {
            callState.setIndeterminate(false);
            
            if (resp.getPersonIdAttributes() != null) {
                responseMsg.append("Additional attributes:\n");
                for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
                    String name  = a.getName();
                    String value = a.getStringValue();
                    
                    log.info(name + " " + value);
                    responseMsg.append(name + " " + value + "\n");
                }
            } else {
                log.warn("No Person ID Attributes found!");
            }
        }
        if (t != null) {
            callState.setIndeterminate(false);
            responseMsg.append("Error: " + t.getMessage());
        }
        
        this.responseBox.setText(responseMsg.toString());
    }

}
