//
// (c) Copyright 2003-2017 Methics Oy. All rights reserved.
//
package fi.laverca.util;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.AxisFault;
import org.apache.axis.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * Error instances represent the "errors" declared in kiuru.conf,
 * e.g.
 * <pre>
 * router.rule.error.label.3=UNKNOWN_MESSAGE_TYPE
 * router.rule.error.code.3=Receiver
 * router.rule.error.subcode.3=900
 * router.rule.error.subsubcode.3=901
 * router.rule.error.role.3=HMSSP
 * router.rule.error.detail.3=Unknown message type
 * <pre>
 */
public class SoapError  {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(SoapError.class);

    protected AxisFault fault;
    private final String label;

    // Base type constructor
    public SoapError(final String label) {
        this.label = label;
        this.fault = new AxisFault((String)null);
    }

    public String getLabel() {
        return this.label;
    }
    
    /**
     * Get a clone of configured AxisFault ready to be extended.
     * @param errmsg text that will be available from AxisFault exception's getMessage() method
     * @return
     */
    public AxisFault getAxisFault(final String errmsg) {
        return this.fault.copy(errmsg);
    }

    /** Sets error code to "Sender" or "Receiver". "Receiver" is this server.
     *  Default is "Receiver".
     *
     *  @param newCode can be only either "Receiver" or "Sender".
     */
    public void setCode(String newCode) {
        if (newCode == null) {
            this.fault.setFaultCode(AxisFault.soap12receiver);
        }
        else if (newCode.trim().equalsIgnoreCase("Receiver")) {
            this.fault.setFaultCode(AxisFault.soap12receiver);
        }
        else if (newCode.trim().equalsIgnoreCase("Sender")) {
            this.fault.setFaultCode(AxisFault.soap12sender);
        }
        else {
            this.fault.setFaultCode(AxisFault.soap12receiver);
        }
    }

    /**
     *  @return Error code i.e. "Receiver" or "Sender"
     */
    public String getCode() { return this.fault.getFaultCode().getLocalPart(); }


    public void addSubcode(QName code) {
        this.fault.addFaultSubCode(code);
    }

    /**
     * Get first error subcode
     * @return first subcode, or null if not defined
     */
    public QName getSubcode() {
        if (this.fault == null)
            return null;
        final List<QName> scs = this.fault.getFaultSubCodes();
        if (scs == null)
            return null;
        if (scs.size() < 1)
            return null;
        return scs.get(0);
    }

    /**
     * Get second subcode
     * @return second subcode, or null if not defined
     */
    public QName getSubSubcode() {
        if (this.fault == null)
            return null;
        final List<QName> scs = this.fault.getFaultSubCodes();
        if (scs == null)
            return null;
        if (scs.size() < 2)
            return null;
        return scs.get(1); // second elt.
    }


    private String defaultSubcodeNS;

    public void setDefaultSubcodeNS(final String ns) {
        this.defaultSubcodeNS = ns;
    }

    public String getDefaultSubcodeNS() {
        return this.defaultSubcodeNS;
    }

    /**
     * This is not used in other services than MSSP.
     *
     * @param newRole  Server role, one of "ae", "hmssp", "re" or one of ETSITS102207 roles
     */
    public void setRole(String newRole) {
        this.fault.setFaultRole(newRole);
    }

    /**
     * This is not used in other services than MSSP.
     */
    public String getRole() {
        return this.fault.getFaultRole();
    }

    /**
     * Set SOAP Fault Reason
     * @param reason Fault Reason text
     */
    public void setReason(String reason) {
        this.fault.setFaultString(reason);
    }

    /**
     * Get SOAP Fault Reason
     * @return Fault Reason
     */
    public String getReason() {
        return this.fault.getFaultString();
    }

    /**
     * @param detail is any human readable string
     */
    public void addDetail(String detail) {
        this.fault.addFaultDetail(Constants.QNAME_FAULTDETAIL_FAULTMSG, detail);
    }

    @Override
    public String toString() {
       // return "CODE='"+ this.code + "' SUBCODE='" + this.subcode + "' ROLE='" + this.role + "' detail='" + this.detail + "'";
        return this.fault.dumpToString();
    }
}
