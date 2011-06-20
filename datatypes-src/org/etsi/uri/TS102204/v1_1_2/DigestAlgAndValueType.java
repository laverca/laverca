/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class DigestAlgAndValueType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class DigestAlgAndValueType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _digestMethod.
     */
    private org.etsi.uri.TS102204.v1_1_2.DigestMethod _digestMethod;

    /**
     * Field _digestValue.
     */
    private byte[] _digestValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public DigestAlgAndValueType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Overrides the java.lang.Object.equals method.
     * 
     * @param obj
     * @return true if the objects are equal.
     */
    @Override()
    public boolean equals(
            final java.lang.Object obj) {
        if ( this == obj )
            return true;

        if (obj instanceof DigestAlgAndValueType) {

            DigestAlgAndValueType temp = (DigestAlgAndValueType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._digestMethod != null) {
                if (temp._digestMethod == null) return false;
                if (this._digestMethod != temp._digestMethod) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._digestMethod);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._digestMethod);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._digestMethod); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._digestMethod); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._digestMethod.equals(temp._digestMethod)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._digestMethod);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._digestMethod);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._digestMethod);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._digestMethod);
                    }
                }
            } else if (temp._digestMethod != null)
                return false;
            if (this._digestValue != null) {
                if (temp._digestValue == null) return false;
                if (this._digestValue != temp._digestValue) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._digestValue);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._digestValue);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._digestValue); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._digestValue); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._digestValue, temp._digestValue)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._digestValue);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._digestValue);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._digestValue);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._digestValue);
                    }
                }
            } else if (temp._digestValue != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'digestMethod'.
     * 
     * @return the value of field 'DigestMethod'.
     */
    public org.etsi.uri.TS102204.v1_1_2.DigestMethod getDigestMethod(
    ) {
        return this._digestMethod;
    }

    /**
     * Returns the value of field 'digestValue'.
     * 
     * @return the value of field 'DigestValue'.
     */
    public byte[] getDigestValue(
    ) {
        return this._digestValue;
    }

    /**
     * Overrides the java.lang.Object.hashCode method.
     * <p>
     * The following steps came from <b>Effective Java Programming
     * Language Guide</b> by Joshua Bloch, Chapter 3
     * 
     * @return a hash code value for the object.
     */
    public int hashCode(
    ) {
        int result = 17;

        long tmp;
        if (_digestMethod != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_digestMethod)) {
           result = 37 * result + _digestMethod.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_digestMethod);
        }
        if (_digestValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_digestValue)) {
           result = 37 * result + _digestValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_digestValue);
        }

        return result;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * Sets the value of field 'digestMethod'.
     * 
     * @param digestMethod the value of field 'digestMethod'.
     */
    public void setDigestMethod(
            final org.etsi.uri.TS102204.v1_1_2.DigestMethod digestMethod) {
        this._digestMethod = digestMethod;
    }

    /**
     * Sets the value of field 'digestValue'.
     * 
     * @param digestValue the value of field 'digestValue'.
     */
    public void setDigestValue(
            final byte[] digestValue) {
        this._digestValue = digestValue;
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
