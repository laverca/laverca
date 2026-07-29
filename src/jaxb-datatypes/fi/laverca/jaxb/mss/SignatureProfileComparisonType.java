
package fi.laverca.jaxb.mss;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SignatureProfileComparisonType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SignatureProfileComparisonType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="exact"/&gt;
 *     &lt;enumeration value="minimum"/&gt;
 *     &lt;enumeration value="better"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SignatureProfileComparisonType", namespace = "http://uri.etsi.org/TS102204/v1.1.2#")
@XmlEnum
public enum SignatureProfileComparisonType {

    @XmlEnumValue("exact")
    EXACT("exact"),
    @XmlEnumValue("minimum")
    MINIMUM("minimum"),
    @XmlEnumValue("better")
    BETTER("better");
    private final String value;

    SignatureProfileComparisonType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SignatureProfileComparisonType fromValue(String v) {
        for (SignatureProfileComparisonType c: SignatureProfileComparisonType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
