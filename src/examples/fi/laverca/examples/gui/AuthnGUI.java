package fi.laverca.examples.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

import fi.laverca.ficom.FiComAdditionalServices.PersonIdAttribute;
import fi.laverca.ficom.FiComClient;
import fi.laverca.ficom.FiComRequest;
import fi.laverca.ficom.FiComResponse;

public abstract class AuthnGUI {

    private static final Log log = LogFactory.getLog(AuthnGUI.class);
    
    public static final String DEFAULT_MSISDN   = "+35847001001";
    public static final String FIXED_NOSPAMCODE = "A12";

    
    private JProgressBar callState;
    private JButton      sendButton;
    private JButton      cancelButton;
    private JTextArea    responseBox;
    
    public abstract void authenticate(String number);
    
    /**
     * Initializes the swing GUI
     * @param client FiComClient implementation
     * @param req    FiComRequest 
     * @param windowName name of the GUI window
     */
    protected void initGUI(final FiComClient client,
                           final FiComRequest req,
                           final String windowName) {
 
        // Init frame & panels
        final JFrame frame = new JFrame(windowName);
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
    protected void displayResponse(final String        eventId,
                                   final FiComResponse resp,
                                   final Throwable     t) {
        
        StringBuilder responseMsg = new StringBuilder();
        responseMsg.append("Event ID: " + eventId + "\n");
        
        sendButton.setEnabled(true);
       
        if (resp != null) {
            callState.setIndeterminate(false);

            String code = "" + resp.getMSS_StatusResp().getStatus().getStatusCode().getValue();
            String msg  =      resp.getMSS_StatusResp().getStatus().getStatusMessage();
            
            responseMsg.append("Status: " + code + " - " + msg + "\n");
            
            if (resp.getPersonIdAttributes() != null) {
                responseMsg.append("Additional attributes:\n");
                for(PersonIdAttribute a : resp.getPersonIdAttributes()) {
                    String name  = a.getName();
                    String value = a.getStringValue();
                    if (value != null && !value.isEmpty()) {
                        responseMsg.append(name + " " + value + "\n");
                    }
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
