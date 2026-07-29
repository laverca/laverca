
package fi.laverca.jaxb.sco204ext1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReceiptMessagingModeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReceiptMessagingModeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="asynch"/&gt;
 *     &lt;enumeration value="synch"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ReceiptMessagingModeType", namespace = "http://www.swisscom.ch/TS102204/ext/v1.0.0")
@XmlEnum
public enum ReceiptMessagingModeType {

    @XmlEnumValue("asynch")
    ASYNCH("asynch"),
    @XmlEnumValue("synch")
    SYNCH("synch");
    private final String value;

    ReceiptMessagingModeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReceiptMessagingModeType fromValue(String v) {
        for (ReceiptMessagingModeType c: ReceiptMessagingModeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
