//
//  (c) Copyright 2003-2017 Methics Oy. All rights reserved.
//
package fi.laverca.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.namespace.QName;

import org.apache.axis.AxisFault;
import org.apache.axis.Constants;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.soap.SOAPConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

/**
 * AxisFault factory.
 * <p>
 * SOAP 1.2 Faults look like this:
 * <dl>
 * <dt>Code</dt><dd> mandatory  QName. SOAP 1.2 specifies five textual values,
 *                   of which we use two: env:Sender and env:Receiver.</dd>
 * <dt>Subcode</dt><dd>optional QName. ETSI 204 specifies couple dozen of these.
 *                       See mssp.ETSITS102204 for values. E.g. _101</dd>
 * <dt>SubSubcode</dt><dd>optional QNames. FICOM expanded the ETSI set a lot.
 *                       See mssp.FICOM20 for values. E.g. _1011</dd>
 * <dt>Reason</dt><dd>Optional env:Text with mandatory xml:lang attribute.
 *                       Specified by ETSI 204 (wrongly) to map directly to subcode.
 *                       SOAP 1.2 spec says this is NOT machine processable data.</dd>
 * <dt>Node</dt><dd>optional in SOAP 1.2, mandatory in ETSI 207. Contains URI.
 *                       URI of the SOAP node where the fault occurred.</dd>
 * <dt>Role</dt><dd>optional in SOAP 1.2, mandatory in ETSI 207. Contains URI.
 *                  Values specified in 207, see router.ETSITS102207 for values.</dd>
 * <dt>Detail</dt><dd>optional. defined as xs:any data; We use this to display
 *                    short error messages.</dd>
 * </dl>
 */
public abstract class AbstractSoapFaultFactory implements FaultFactory {

    protected Log log = LogFactory.getLog(AbstractSoapFaultFactory.class);

    protected static final QName  LEGACY_FAULTDETAIL = new QName("http://kiuru.methics.fi/mssp", "detail");
    protected static final String SOAP_RPC_NAMESPACE = "http://www.w3.org/2003/05/soap-rpc";

    // These are set in MsspInit
    public String  defaultNode;
    public String  defaultRole;
    public boolean enableFaultNode    = true;
    public boolean enableFaultRole    = true;
    public boolean enableFaultDetails = true;
    public boolean legacyFaultMsg     = false;

    public boolean produceFicomFaults = false;

    public String  faultNamespace;

    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";
    public static final String COMEXCEPTION   = "COMEXCEPTION";
    public static final String COMTIMEOUT     = "COMTIMEOUT";
    
    protected AbstractSoapFaultFactory(final Log log) {
        this.log = log;
    }

    /**
     * Convert the Fault Code "Receiver" or "Sender" to QName format
     * per current message context, default to SOAP 1.2 style.
     * <p>
     * Null input code produces null output QName.
     */
    protected QName findFaultCodeQN(final String code) {
        if (code == null) {
            return null;
        }

        final SOAPConstants soapConstants =
            (MessageContext.getCurrentContext() == null ?
             SOAPConstants.SOAP12_CONSTANTS :
             MessageContext.getCurrentContext().getSOAPConstants());

        return new QName(soapConstants.getEnvelopeURI(), code);
    }

    /**
     * Creates the Axis fault.
     * <p>This is the actual workhorse
     *
     * @param label     Label used to match to a configured Fault
     * @param codeQName Fault code "Receiver" or "Sender". Who is to blame?
     * @param subcode   Fault subcode, use ETSI102204 codes here
     * @param subsubcode Fault subsubcode
     * @param reasonIn  Fault reason
     * @param node      Provide information about which SOAP node on the SOAP message path caused the fault to happen.
     * @param role      Role of this server, MSSP identity URI
     * @param detailText is intended to provide a human-readable explanation of the fault
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    @Override
    public AxisFault createFault( final String label,
                                  final QName  codeQName,
                                  final QName  subcode,
                                  final QName  subsubcode,
                                  final String reasonIn,
                                  final String node,
                                  final String role,
                                  final String detailText )
    {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Constructing a fault:");
            this.log.debug("  label      = "+label);
            this.log.debug("  code       = "+codeQName);
            this.log.debug("  subcode    = "+subcode);
            this.log.debug("  subsubcode = "+subsubcode);
            this.log.debug("  node       = "+node);
            this.log.debug("  role       = "+role);
            this.log.debug("  detailText = "+detailText);
        }

        final SoapError soapErr = this.getErrorByLabel(label);
        if (soapErr == null) {
            // No pre-canned fault, do direct generation
            this.log.debug("  No predefined fault for label: "+label);
            final String reason = (reasonIn != null ? reasonIn : this.mapSubcodeToReason(subcode));
            return this.buildFault(codeQName, subcode, subsubcode, reason, node, role, detailText);
        }

        // Use canned message as a base.
        // But do not override (sub(sub))code
        final AxisFault af = soapErr.getAxisFault(detailText != null ? detailText : label);
        if (codeQName != null && af.getFaultCode() == null) {
            af.setFaultCode(codeQName);
        }
        if (subcode != null) {
            final List<QName> subcodes = af.getFaultSubCodes();
            if (subcodes == null || subcodes.size() < 1) {
                af.addFaultSubCode(subcode);
            } else {
                // Do not override template defined subcode
                // subcodes.set(0, subcode);
            }
        }

        final List<QName> subcodes = af.getFaultSubCodes();
        final QName newSubcode = subcodes.size() > 0 ? subcodes.get(0) : null;
        final String reason = (reasonIn != null ? reasonIn : this.mapSubcodeToReason(newSubcode));
        if (af.getFaultReasonCount() == 0) {
            this.log.debug("  reason     = "+reason);
            if (reason != null) {
                af.addFaultReasonText("en", reason);
            }
        }


        // Set reason to fault string only if template did not define anything
        /* -- SOAP 1.1 FaultString is now SOAP 1.2 FaultReasonText
        final String fr = af.getFaultString();
        if (fr == null || fr.equals("")) {
            af.setFaultString(reason);
        }
        */

        // Set fault node
        if (node != null) {
            af.setFaultNode(node);
        }
        if (role != null) {
            af.setFaultRole(role);
        } else if (this.defaultRole != null) {
            af.setFaultRole(this.defaultRole);
        } else {
            // Nothing??? Hopefully template defined something.
        }
        // Add detail text
        if (detailText != null) {
            if (af.lookupFaultDetail(Constants.QNAME_FAULTDETAIL_FAULTMSG) == null) {
                af.addFaultDetailString(detailText);
            }
        }
        return this.processFault(af);
    }


    /**
     * Creates an Axis fault.
     * <p>AxisFault helper
     *
     * @param label     Label used to match to a configured Fault
     * @param code      Fault code "Receiver" or "Sender". Who is to blame?
     * @param subcode   Fault subcode, use ETSI102204 codes here
     * @param subsubcode Fault subsubcode
     * @param reasonIn  Fault reason
     * @param node      Provide information about which SOAP node on the SOAP message path caused the fault to happen.
     * @param role      Role of this server, MSSP identity URI
     * @param detailText is intended to provide a human-readable explanation of the fault
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    @Override
    public AxisFault createFault( final String label,
                                  final String code,
                                  final QName  subcode,
                                  final QName  subsubcode,
                                  final String reasonIn,
                                  final String node,
                                  final String role,
                                  final String detailText )
    {
        return this.createFault(label,
                                this.findFaultCodeQN(code),
                                subcode,
                                subsubcode,
                                reasonIn,
                                node,
                                role,
                                detailText);
    }

    /**
     * Creates an Axis fault.
     * <p>AxisFault helper
     *
     * @param label     Label used to match to a configured Fault
     * @param code      Fault code "Receiver" or "Sender". Who is to blame?
     * @param detailText is intended to provide a human-readable explanation of the fault
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    public AxisFault createFault( final String label,
                                  final String code,
                                  final String detailText) {
        return this.createFault(label,
                                code,
                                null, //subcode
                                null, //subsubcode
                                null, //reason
                                null, //node
                                null, //role
                                detailText);
    }

    /**
     * Creates an Axis fault.
     * <p>AxisFault helper
     *
     * @param label     Label used to match to a configured Fault
     * @param code      Fault code "Receiver" or "Sender". Who is to blame?
     * @param detailText is intended to provide a human-readable explanation of the fault
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    public AxisFault createFault( final String label,
                                  final QName  code,
                                  final String detailText) {
        return this.createFault(label,
                                code,
                                null, //subcode
                                null, //subsubcode
                                null, //reason
                                null, //node
                                null, //role
                                detailText);
    }

    /**
     * Parses a raw AxisFault thrown by Axis or SAX parser.
     * Produces a new fault based on the label and the given
     * Fault.
     * @param label Label used to match to a configured Fault
     * @param fault Raw AxisFault
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    public AxisFault createFault( final String    label,
                                  final AxisFault fault) {

        // Take the blame by default
        String code = "Receiver";

        if (fault.getFaultCode() != null) {
            String faultCode = fault.getFaultCode().getLocalPart();

            switch (faultCode) {
                case "Sender":
                case "Receiver":
                    code = faultCode;
                    break;
                case Constants.FAULT_SERVER_USER:
                    code = "Sender";
                    break;
                default:
                    break;
            }
        }

        // Try to find a Fault message
        String msg = fault.getMessage();
        if (msg == null && fault.getCause() != null) {
            msg = fault.getCause().getMessage();
        }

        return this.createFault(label, code, msg);
    }

    /**
     * Build AxisFault from source parameters.
     * <p>Internal AxisFault helper
     *
     * @param code      Fault code "Receiver" or "Sender". Who is to blame?
     * @param subcode   Fault subcode, use ETSI102204 codes here
     * @param subsubcode Fault subsubcode
     * @param reasonIn  Fault reason
     * @param node      Provide information about which SOAP node on the SOAP message path caused the fault to happen.
     * @param role      Role of this server, MSSP identity URI
     * @param detailText is intended to provide a human-readable explanation of the fault
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    private AxisFault buildFault( final QName  codeQName,
                                  final QName  subcode,
                                  final QName  subsubcode,
                                  final String reasonIn,
                                  final String node,
                                  final String role,
                                  final String detailText )
    {

        final String reason = (reasonIn != null ? reasonIn : mapSubcodeToReason(subcode));

        final QName[] subCodes;
        if (subsubcode != null) {
            subCodes = new QName[2];
            subCodes[0] = subcode;
            subCodes[1] = subsubcode;
        }
        else {
            subCodes = new QName[1];
            subCodes[0] = subcode;
        }

        final AxisFault af = new AxisFault( codeQName,
                                            subCodes,
                                            reason,
                                            role,
                                            node,
                                            null);
        if (detailText != null) {
            af.addFaultDetailString(detailText);
        }

        if (this.log.isDebugEnabled()) {
            this.log.debug("Constructed a fault: " + af.dumpToString());
            this.log.trace(af);
        }

        return this.processFault(af);
    }

    /**
     * Create an INTERNAL_ERROR fault
     * <p>AxisFault helper
     *
     * @param explanation FaultMsg value
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    @Override
    public AxisFault createInternalError(final String explanation) {
        return this.createFault( INTERNAL_ERROR,
                                 AxisFault.soap12receiver,
                                 new QName(Constants.NS_URI_KIURU, "_900"),
                                 null,
                                 "INTERNAL_ERROR", // reason
                                 null, //node
                                 null, //role
                                 explanation );
    }

    /**
     * Create an INTERNAL_ERROR fault
     * <p>AxisFault helper
     *
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    @Override
    public AxisFault createInternalError() {
        return this.createInternalError((String)null);
    }

    /**
     * Create an INTERNAL_ERROR fault
     * <p>AxisFault helper
     *
     * @param exception Exception
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    public AxisFault createInternalError(final Exception exception) {
        return this.createInternalError(exception.getMessage());
    }

    /**
     * Make an AxisFault from an Exception
     * <p>Attempts to determine the fault cause and produces a Fault
     * with one of the following labels:
     *
     * <ul>
     * <li>COMEXCEPTION
     * <li>COMTIMEOUT
     * <li>INTERNAL_ERROR
     * </ul>
     *
     * @param e Exception
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    public AxisFault makeFault( final Exception e) {
        return this.makeFault(e, Boolean.FALSE, null);
    }

    /**
     * Make an AxisFault from an Exception
     * <p>Attempts to determine the fault cause and produces a Fault
     * with one of the following labels:
     *
     * <ul>
     * <li>COMEXCEPTION
     * <li>COMTIMEOUT
     * <li>INTERNAL_ERROR
     * </ul>
     *
     * @param e Exception
     * @param didTimeout True if the request timed out
     * @param remoteURL  The URL where the connection was attempted
     * @return AxisFault Maps cleanly to a SOAP fault.
     */
    @Override
    public AxisFault makeFault( final Exception e,
                                final Boolean didTimeout,
                                final String  remoteURL )
    {
        this.log.debug("Creating Fault");

        AxisFault fault = null;

        if (e instanceof AxisFault) {
            fault = (AxisFault)e;
        } else if (e instanceof org.apache.http.conn.HttpHostConnectException) {
            fault = this.createFault( COMEXCEPTION,
                                      AxisFault.soap12receiver,
                                      "Http Connection Exception: " + e.getMessage());
        } else if (e instanceof java.io.InterruptedIOException) {
            if (e instanceof java.net.SocketTimeoutException) {
                // Timeout on socket read
                if (didTimeout != null && didTimeout.booleanValue())
                    fault = this.createFault(COMTIMEOUT,
                                             AxisFault.soap12receiver,
                                             "Communication Timeout Exception: " + e.getMessage());
            } else if (e instanceof org.apache.http.conn.ConnectTimeoutException) {
                fault = this.createFault(COMTIMEOUT,
                                         AxisFault.soap12receiver,
                                         "Communication Timeout Exception: " + e.getMessage());
            } else {
                // general timeout exception
                fault = this.createFault(COMEXCEPTION,
                                         AxisFault.soap12receiver,
                                         "InterruptedIOException: "+e.getMessage());
            }
        } else if (e instanceof java.io.IOException) {
            fault = this.createFault(COMEXCEPTION,
                                     AxisFault.soap12receiver,
                                     "IOException: "+e.getMessage());
        }

        if (fault == null) {
            // Default maker..
            fault = this.createInternalError(e);
        }

        return this.processFault(fault);
    }

    /**
     * Get pre-registered SoapError by reference label.
     */
    //@Override
    public SoapError getErrorByLabel(final String label) {
        return this.faultRegistry.get(label);
    }

    /**
     * Map given subcode to a reason.
     * @param subcode
     * @return null is no mapping is known, a text string otherwise.
     */
    public abstract String mapSubcodeToReason(final QName subcode);


    /**
     * Process the given AxisFault:
     * <ul>
     * <li>Fill the default ROLE and NODE into the AxisFault.
     * <li>Insert a FaultNode Detail element with the default NODE
     * <li>Remove unwanted elements
     * </ul>
     *
     * @param af AxisFault to be filled
     * @return processed AxisFault
     */
    public AxisFault processFault(final AxisFault af) {

        final String role = af.getFaultRole();
        final String node = af.getFaultNode();

        if (role == null || role.isEmpty()) {
            af.setFaultRole(this.defaultRole);
        }
        if (node == null || node.isEmpty()) {
            af.setFaultNode(this.defaultNode);
        }
        
        // Clean up Faults thrown by third-party libraries
        boolean isNonMssFault = false;
        if (af.getFaultSubCodes() != null) {
            isNonMssFault = af.getFaultSubCodes().stream()
                                                 .filter(Objects::nonNull)
                                                 .anyMatch(c -> c.getNamespaceURI().equals(SOAP_RPC_NAMESPACE));
        }
        if (isNonMssFault) {
            // TODO: Store the SubCodes as details?
            af.clearFaultSubCodes();
            af.addFaultSubCode(new QName(Constants.NS_ETSI_204_MSS, "_900"));
            af.addFaultDetailString(af.getFaultReasonText("en"));
            af.setFaultString("INTERNAL_ERROR");
        }
        
        // Force namespace for fault subcodes
        if (this.faultNamespace != null) {
            List<QName> subcodes = af.getFaultSubCodes();
            af.clearFaultSubCodes();

            for (QName q : subcodes) {
                af.addFaultSubCode(new QName(this.faultNamespace, q.getLocalPart(), q.getPrefix()));
            }
        }

        // Clear unwanted elements

        if (!this.enableFaultNode) {
            af.setFaultNode(null);
            // Remove Hostname
            if (this.enableFaultDetails) {
                if (af.lookupFaultDetail(Constants.QNAME_FAULTDETAIL_HOSTNAME) != null) {
                    final List<Node> faultElts = af.getFaultDetails();
                    af.clearFaultDetails();

                    for (final Node elt : faultElts) {
                        QName elQn = new QName(elt.getNamespaceURI(), elt.getLocalName());
                        // Skip Hostname
                        if (elQn.equals(Constants.QNAME_FAULTDETAIL_HOSTNAME)) continue;
                        af.addFaultDetail(elt);
                    }
                }
            }
        } else {
            // Add FaultNode
            if (this.enableFaultDetails) {
                if (af.lookupFaultDetail(Constants.QNAME_FAULTDETAIL_FAULTNODE) == null) {

                    final List<Node> faultElts = af.getFaultDetails();
                    af.clearFaultDetails();
                    if (this.defaultNode != null) {
                        af.addFaultDetail(Constants.QNAME_FAULTDETAIL_FAULTNODE, this.defaultNode);
                    }
                    for (final Node elt : faultElts) {
                        af.addFaultDetail(elt);
                    }
                }
            }
        }

        if (!this.enableFaultRole) {
            af.setFaultRole(null);
        }

        if (!this.enableFaultDetails) {
            af.clearFaultDetails();
        }

        // Convert "FaultMsg" to lecacy "details"
        if (this.legacyFaultMsg) {
            if (this.enableFaultDetails) {
                if (af.lookupFaultDetail(Constants.QNAME_FAULTDETAIL_FAULTMSG) != null) {
                    final List<Node> faultElts = af.getFaultDetails();
                    String origMsg = af.getFaultDetailString(Constants.QNAME_FAULTDETAIL_FAULTMSG);
                    af.clearFaultDetails();

                    af.addFaultDetail(LEGACY_FAULTDETAIL, origMsg);

                    for (final Node elt : faultElts) {
                        QName elQn = new QName(elt.getNamespaceURI(), elt.getLocalName());
                        // Skip FaultMsg
                        if (elQn.equals(Constants.QNAME_FAULTDETAIL_FAULTMSG)) continue;
                        af.addFaultDetail(elt);
                    }
                }
            }
        }

        return af;
    }


    /**
     * Pre-recorded errors
     */
    private final Map<String,SoapError> faultRegistry = new HashMap<String,SoapError>();

    /**
     * Pre-record canned errors at router.
     */
    public void record(final String label,
                       final SoapError error) {
        this.faultRegistry.put(label, error);
    }

    public String getDefaultNode() {
        return this.defaultNode;
    }

    public String getDefaultRole() {
        return this.defaultRole;
    }
}
