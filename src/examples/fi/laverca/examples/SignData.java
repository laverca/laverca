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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
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
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;
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
 * Sample for demonstrating hash signing.
 * 
 * Generates SHA1 hash from a file and asks for a user to sign it.
 */
public class SignData {
    
    private static final Log log = LogFactory.getLog(SignData.class);    
    
    private static FiComRequest req;
        
    /**
     * Creates eventId and starts the UI
     * @param args not used
     */
    public static void main(String[] args) {        
        initUI();
    }

    /**
     * Connects to MSSP using SSL and waits for response.
     * @param msisdn
     * @param selectedFile
     */
    protected static void connect(String msisdn, String noSpam, String eventId, final File selectedFile) {
        
        // Read configuration
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

        // Create FiComClient
        log.info("creating FiComClient");
        FiComClient fiComClient = new FiComClient(apId, 
                                                  apPwd, 
                                                  msspSignatureUrl, 
                                                  msspStatusUrl, 
                                                  msspReceiptUrl);
        
        final byte[] output = SignData.generateSHA1(selectedFile);
        String apTransId = "A"+System.currentTimeMillis();
        
        // Initialize additional services
        LinkedList<Service> additionalServices = new LinkedList<Service>();
        
        // EventId and NoSpam
        Service eventIdService = FiComAdditionalServices.createEventIdService(eventId);
        Service noSpamService  = FiComAdditionalServices.createNoSpamService(noSpam, false);
        
        // PersonID
        LinkedList<String>  attributeNames     = new LinkedList<String>();
        attributeNames.add(FiComAdditionalServices.PERSON_ID_VALIDUNTIL);
        Service personIdService = FiComAdditionalServices.createPersonIdService(attributeNames);
        additionalServices.add(personIdService);
        
        try {
            log.info("Calling sign data"); 
            req = 
                fiComClient.signData(apTransId, 
                        output, 
                        msisdn, 
                        noSpamService, 
                        eventIdService,
                        additionalServices, 
                        new FiComResponseHandler() {
                    
                            @Override
                            public void onResponse(FiComRequest req, FiComResponse resp) {
                                log.info("Got resp");                                
                                respWindow.setResponse(resp, output);      
                            }
                            
                            @Override
                            public void onError(FiComRequest req, Throwable t) {
                                if (log.isTraceEnabled()) 
                                    log.trace("Got error", t);
                                else
                                    log.info("Got error " + t.getMessage() );
                                
                                respWindow.setError(t);
                            }
        
                            @Override
                            public void onOutstandingProgress(FiComRequest req, ProgressUpdate prgUpdate) {
                                log.trace("Got status update");
                            }
    
                        });
        }
        catch (IOException e) {
            log.info("error establishing connection", e);
        }

        fiComClient.shutdown();
    }   
    
    /**
     * Generates a new event id from System.currentTimeMillis()
     * @return new EventId
     */
    private static String generateEventId() {
        Long currentTimeMillis = System.currentTimeMillis();
        return "A" + currentTimeMillis.toString().substring(10);
    }
    
    /**
     * 
     * @param selectedFile
     * @return
     */
    public static byte[] generateSHA1(final File selectedFile) {
        byte[] output = null;
        InputStream is = null; 
        try {
            is = new FileInputStream(selectedFile);
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
            log.error("error finding algorithm", e);
        } catch (FileNotFoundException e) {
            log.error("error finding file", e);
        } catch (IOException e) {
            log.error("i/o error", e);
        } finally {
            try {
                if (is != null) is.close();
            } catch (Throwable t) {
                // Ignore
            }
        }
        
        return output;
    }
    
    /**
     * Creates a SHA1 String from the given byte array.
     * @param buf
     */
    public static String toSHA1String(byte[] buf) {
        Formatter formatter = new Formatter();
        for (byte b : buf)
            formatter.format("%02x", b);
        
        // Cuts first 8 characters of the hash to make the generated hash match the one in the phone.
        String sha1 = formatter.toString().substring(8).toUpperCase();
        String shaTmp = new String();
        String space = "";
        for (int i = 0; i < sha1.length()/4; i++) {
            shaTmp += space + sha1.substring(i*4, i*4+4);
            space = " ";
        }
        
        formatter.close();
        return shaTmp;
    }
    
    /*
     * SWING 
     */
    
    private static JButton        browseButton;    
    private static JFrame         dialFrame;    
    private static JPanel         dialPanel;   
    private static JTextArea      hashBox;   
    private static JScrollPane    scrollPane;
    private static JLabel         msisdnLabel;
    private static JLabel         noSpamLabel;
    private static JTextField     msisdnField;
    private static JTextField     noSpamField;
    private static JButton        sendButton;    
    private static ResponseWindow respWindow;
    
    /**
     * Initializes the initial UI that contains a Browse button for file selection.
     */
    private static void initUI() {
        
        // Change TextArea font to a monospaced one.
        UIManager.put("TextArea.font", new FontUIResource("Courier", Font.PLAIN, 12));

        dialFrame    = new JFrame("Sign Data");
        dialPanel    = new JPanel();
        msisdnLabel  = new JLabel();
        noSpamLabel  = new JLabel();
        msisdnField  = new JTextField();
        noSpamField  = new JTextField();
        sendButton   = new JButton();
        browseButton = new JButton();
        scrollPane   = new JScrollPane();
        hashBox      = new JTextArea();
        respWindow   = new ResponseWindow();

        final JFileChooser fileChooser = new JFileChooser();
        
        dialFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        dialFrame.setVisible(true);
        dialFrame.setResizable(false);        

        browseButton.setText("Browse...");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(dialFrame);
            }
        });

        dialFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        dialFrame.setVisible(true);
        
        msisdnLabel.setText("Phone number");
        msisdnField.setText("+35847001001");
        
        noSpamLabel.setText("Nospam Code");
        noSpamField.setText("A12");

        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = fileChooser.getSelectedFile();
                if (f == null) {
                    log.info("No file chosen");
                    hashBox.setText("Please choose a file");
                } else {
                    log.debug("Creating response window");
                    
                    String eventId = generateEventId();
                    respWindow.init(eventId);
                    connect(msisdnField.getText(), noSpamField.getText(), eventId, fileChooser.getSelectedFile());     
                } 
            }
        });
        
        hashBox.setColumns(20);
        hashBox.setRows(5);
        scrollPane.setViewportView(hashBox);
        
        GroupLayout dialLayout = new GroupLayout(dialPanel);
        dialPanel.setLayout(dialLayout);
        dialLayout.setHorizontalGroup(
            dialLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(dialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(msisdnLabel)
                    .addComponent(noSpamLabel)
                    .addGroup(dialLayout.createSequentialGroup()
                        .addComponent(sendButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
                    .addComponent(msisdnField, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(noSpamField, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        dialLayout.setVerticalGroup(
            dialLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(dialLayout.createSequentialGroup()
                .addComponent(msisdnLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msisdnField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dialLayout.createSequentialGroup())
                    .addComponent(noSpamLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(noSpamField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(dialLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(sendButton)
                            .addComponent(browseButton))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );

        GroupLayout contentLayout = new GroupLayout(dialFrame.getContentPane());
        dialFrame.getContentPane().setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dialPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addComponent(dialPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dialFrame.pack();
        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sha1 = toSHA1String(generateSHA1(fileChooser.getSelectedFile()));
                hashBox.setText("SHA1: " + sha1);
            }
        });
    }
    
    /**
     * Window for connection that includes a response box, 
     * a cancel button and a progress bar.
     */
    private static class ResponseWindow {
                
        private String       eventId;
        
        // Swing
        private JProgressBar progressBar;
        private JButton      cancelButton;
        private JScrollPane  scrollPane;
        private JTextArea    responseBox;
        private JFrame       responseFrame;

        /**
         * Creates the object. Call init() to create the actual window.
         */
        public ResponseWindow() {
            this.responseFrame = new JFrame(eventId);
            this.cancelButton  = new JButton();
            this.scrollPane    = new JScrollPane();
            this.responseBox   = new JTextArea();
            this.progressBar = new JProgressBar();            

            this.cancelButton.setText("Cancel");
            this.cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    log.info("Request canceled");
                    req.cancel();
                    progressBar.setIndeterminate(false);
                    responseFrame.dispose();
                }
            });
            
            this.responseBox.setColumns(20);
            this.responseBox.setRows(5);
            this.scrollPane.setViewportView(responseBox);

            GroupLayout layout = new GroupLayout(responseFrame.getContentPane());
            this.responseFrame.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(cancelButton)
                    .addContainerGap(47, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                        .addGap(14, 14, 14)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(progressBar, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(cancelButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(266, 266, 266))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(53, Short.MAX_VALUE)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
            );
            
        }       

        /**
         * Creates the window and initializes swing components
         */
        public void init(String eventId) {
            this.eventId = eventId;
            this.responseFrame.setTitle(this.eventId);
            
            this.responseBox.setText("");
            this.progressBar.setIndeterminate(true);
            this.responseFrame.setLocation(500, 0);
            this.responseFrame.pack();
            this.responseFrame.setVisible(true);
            this.responseFrame.setResizable(false);
        }

        /**
         * Stops the progress bar and outputs the error message to the window.
         * @param t
         */
        public void setError(Throwable t) {
            this.progressBar.setIndeterminate(false);
            this.responseBox.setText("Failed to get signature" +
                                     "\nError: " + t.getMessage());
        }

        /**
         * Fills the response area with the received FiComResponse.
         * @param resp Response to parse the MSS Signature and SHA1 text from
         * @param output byte array to converto to SHA1
         */
        public void setResponse(FiComResponse resp, byte[] output) {
            
            this.progressBar.setIndeterminate(false);
            String sha1 = SignData.toSHA1String(output);
            Map<String, String> respMap = new LinkedHashMap<String, String>();

            respMap.put("MSS_Signature", new String(Base64.encode(resp.getMSS_StatusResp().getMSS_Signature().getBase64Signature())));
            respMap.put("Signer", resp.getPkcs7Signature().getSignerCn());                   
            respMap.put("SHA1", sha1);
            
            // Get ValidUntil PersonID attribute
            if (resp.getPersonIdAttributes() != null) {
                for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
                    if (FiComAdditionalServices.PERSON_ID_VALIDUNTIL.equals(a.getName())) {
                        respMap.put("Valid Until", a.getStringValue());
                    }
                }
            } else {
                log.info("No ValidUntil found");
            }
            
            this.printResponse(respMap);           
        }

        /**
         * Appends the given response map to the response box.
         * @param respMap Map containing response attribute names and values.
         */
        private void printResponse(Map<String, String> respMap) {
            int longest = 0;
            for (String s : respMap.keySet()) {
                if (longest < s.length())
                    longest = s.length();
            }
            
            for (String s : respMap.keySet()) {
                StringBuilder tmp = new StringBuilder(s);
                while (tmp.length() < longest) {
                    tmp.append(" ");
                }
                this.responseBox.append(tmp.toString() + ": " + respMap.get(s) + "\n");
            }            
        }
        
        /**
         * Gets the eventId currently associated with the window
         * @return String eventId
         */
        public String getEventId() {
            return eventId;
        }
        
    }

}