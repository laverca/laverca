/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2026 Laverca Project
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

package fi.laverca;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A SOAP fault from an MSS call: either a fault the MSSP answered with, or a local one for a failed connection.
 * <p>
 * A connection that can't be made, times out, or gets no SOAP answer gives a local fault, as Laverca 3 did: code
 * {@code env:Receiver}, subcode {@code msrs:_780} ({@link ErrorCodes#UNABLE_TO_PROVIDE_SERVICES}), reason
 * "Unable to provide services." and the failure as a detail string. {@link #isReceived()} tells the two apart.
 * <p>
 * Use {@link ErrorCodes.StatusCode#is(Throwable)} to check for a specific ETSI status code, e.g.
 * {@code ErrorCodes.UNAUTHORIZED_ACCESS.is(fault)}.
 */
public class LavercaSoapFaultException extends IOException {

    private static final long serialVersionUID = 1L;

    /** SOAP 1.2 envelope namespace. */
    public static final String SOAP12_ENV_NS = "http://www.w3.org/2003/05/soap-envelope";

    /** SOAP 1.2 Sender fault code. */
    public static final QName SOAP12_SENDER   = new QName(SOAP12_ENV_NS, "Sender",   "env");
    /** SOAP 1.2 Receiver fault code. */
    public static final QName SOAP12_RECEIVER = new QName(SOAP12_ENV_NS, "Receiver", "env");

    /** Detail element of a local fault's failure text; the same name as Laverca 3 (Axis 1.4) used. */
    public static final QName DETAIL_STRING         = new QName("http://xml.apache.org/axis/", "string");
    /** Detail element of a local fault's HTTP status code; the same name as Laverca 3 (Axis 1.4) used. */
    public static final QName DETAIL_HTTPERRORCODE  = new QName("http://xml.apache.org/axis/", "HttpErrorCode");

    private final QName         faultCode;
    private final List<QName>   faultSubCodes;
    private final String        faultReason;
    private final String        faultRole;
    private final String        faultNode;
    private final List<Element> faultDetails;
    private final boolean       received;

    /**
     * Creates a fault.
     *
     * @param faultCode     fault code, e.g. {@link #SOAP12_SENDER}; may be null
     * @param faultSubCodes fault subcodes, outermost first; may be null
     * @param faultReason   fault reason text; may be null
     * @param faultRole     SOAP 1.2 Role (SOAP 1.1 faultactor); may be null
     * @param faultNode     SOAP 1.2 Node; may be null
     * @param faultDetails  detail elements; may be null
     * @param received      true if the fault was received from the MSSP, false for a local fault
     * @param cause         the local failure behind the fault; may be null
     */
    public LavercaSoapFaultException(final QName faultCode,
                                     final List<QName> faultSubCodes,
                                     final String faultReason,
                                     final String faultRole,
                                     final String faultNode,
                                     final List<Element> faultDetails,
                                     final boolean received,
                                     final Throwable cause)
    {
        super(faultReason, cause);
        this.faultCode     = faultCode;
        this.faultSubCodes = (faultSubCodes == null) ? Collections.<QName>emptyList()
                                                     : Collections.unmodifiableList(new ArrayList<>(faultSubCodes));
        this.faultReason   = faultReason;
        this.faultRole     = faultRole;
        this.faultNode     = faultNode;
        this.faultDetails  = (faultDetails == null) ? Collections.<Element>emptyList()
                                                    : Collections.unmodifiableList(new ArrayList<>(faultDetails));
        this.received      = received;
    }

    /**
     * Creates a local "Unable to provide services" fault ({@code env:Receiver}, {@code msrs:_780}) for a failed call.
     *
     * @param detail         the failure as text, put into a detail element
     * @param httpStatusCode HTTP status code of the answer, or -1 for none
     * @param cause          the failure; may be null
     * @return the fault
     */
    public static LavercaSoapFaultException unableToProvideServices(final String detail,
                                                                    final int httpStatusCode,
                                                                    final Throwable cause)
    {
        final List<QName>   subcodes = Collections.singletonList(new QName(ErrorCodes.ETSI_207_NS_URI, "_780", "msrs"));
        final List<Element> details  = new ArrayList<>();
        final Element detailString = createDetail(DETAIL_STRING, detail);
        if (detailString != null) {
            details.add(detailString);
        }
        if (httpStatusCode >= 0) {
            final Element httpCode = createDetail(DETAIL_HTTPERRORCODE, Integer.toString(httpStatusCode));
            if (httpCode != null) {
                details.add(httpCode);
            }
        }
        return new LavercaSoapFaultException(SOAP12_RECEIVER, subcodes, "Unable to provide services.",
                                             null, null, details, false, cause);
    }

    /**
     * Creates a DOM element with the given name and text.
     *
     * @param name element name
     * @param text element text
     * @return the element, or null if no DOM document can be created
     */
    private static Element createDetail(final QName name, final String text) {
        try {
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            final Document doc = dbf.newDocumentBuilder().newDocument();
            final Element el = doc.createElementNS(name.getNamespaceURI(), name.getLocalPart());
            el.setTextContent(text);
            doc.appendChild(el);
            return el;
        } catch (ParserConfigurationException e) {
            return null;
        }
    }

    /**
     * Gets the fault code, e.g. {@code env:Sender} or {@code env:Receiver}.
     *
     * @return the fault code, or null if the fault had none
     */
    public QName getFaultCode() {
        return this.faultCode;
    }

    /**
     * Gets the fault subcodes, outermost first. For an ETSI TS 102 204 fault the first one is the status code,
     * e.g. {@code mss:_101}, and a second one, if any, the more specific code.
     *
     * @return the subcodes, never null
     */
    public List<QName> getFaultSubCodes() {
        return this.faultSubCodes;
    }

    /**
     * Gets the fault reason text.
     *
     * @return the reason, or null if the fault had none
     */
    public String getFaultReason() {
        return this.faultReason;
    }

    /**
     * Gets the SOAP 1.2 fault Role (SOAP 1.1 faultactor).
     *
     * @return the role, or null if the fault had none
     */
    public String getFaultRole() {
        return this.faultRole;
    }

    /**
     * Gets the SOAP 1.2 fault Node.
     *
     * @return the node, or null if the fault had none
     */
    public String getFaultNode() {
        return this.faultNode;
    }

    /**
     * Gets the fault detail elements as DOM.
     *
     * @return the detail elements, never null
     */
    public List<Element> getFaultDetails() {
        return this.faultDetails;
    }

    /**
     * Gets the text content of the fault detail elements, one per line.
     *
     * @return the detail text, or null if the fault has no details
     */
    public String getFaultDetailString() {
        if (this.faultDetails.isEmpty()) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (final Element e : this.faultDetails) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append(e.getTextContent());
        }
        return sb.toString();
    }

    /**
     * Gets the fault detail elements serialized as XML, one per line.
     *
     * @return the detail XML, or null if the fault has no details
     */
    public String getFaultDetailXml() {
        if (this.faultDetails.isEmpty()) {
            return null;
        }
        final StringWriter sw = new StringWriter();
        try {
            final Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            for (final Element e : this.faultDetails) {
                if (sw.getBuffer().length() > 0) {
                    sw.append('\n');
                }
                t.transform(new DOMSource(e), new StreamResult(sw));
            }
        } catch (TransformerException e) {
            return this.getFaultDetailString();
        }
        return sw.toString();
    }

    /**
     * Tells whether the fault was received from the MSSP or made locally for a failed call.
     *
     * @return true if received from the MSSP
     */
    public boolean isReceived() {
        return this.received;
    }

    /**
     * Describes the fault: its code, subcodes and reason.
     *
     * @return the description
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getClass().getName());
        sb.append(": ").append(this.faultCode);
        for (final QName sc : this.faultSubCodes) {
            sb.append(" / ").append(sc);
        }
        if (this.faultReason != null) {
            sb.append(": ").append(this.faultReason);
        }
        return sb.toString();
    }
}
