/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.assertion.types;

/**
 * Enumeration DecisionType.
 * 
 * @version $Revision$ $Date$
 */
public enum DecisionType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant PERMIT
     */
    PERMIT("Permit"),
    /**
     * Constant DENY
     */
    DENY("Deny"),
    /**
     * Constant INDETERMINATE
     */
    INDETERMINATE("Indeterminate");

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field value.
     */
    private final java.lang.String value;

    /**
     * Field enumConstants.
     */
    private static final java.util.Map<java.lang.String, DecisionType> enumConstants = new java.util.HashMap<java.lang.String, DecisionType>();


    static {
        for (DecisionType c: DecisionType.values()) {
            DecisionType.enumConstants.put(c.value, c);
        }

    };


      //----------------/
     //- Constructors -/
    //----------------/

    private DecisionType(final java.lang.String value) {
        this.value = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static oasis.names.tc.SAML.v2_0.assertion.types.DecisionType fromValue(
            final java.lang.String value) {
        DecisionType c = DecisionType.enumConstants.get(value);
        if (c != null) {
            return c;
        }
        throw new IllegalArgumentException(value);
    }

    /**
     * 
     * 
     * @param value
     */
    public void setValue(
            final java.lang.String value) {
    }

    /**
     * Method toString.
     * 
     * @return the value of this constant
     */
    public java.lang.String toString(
    ) {
        return this.value;
    }

    /**
     * Method value.
     * 
     * @return the value of this constant
     */
    public java.lang.String value(
    ) {
        return this.value;
    }

}
