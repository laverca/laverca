/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig;

/**
 * Class RSAKeyValueType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class RSAKeyValueType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _modulus.
     */
    private byte[] _modulus;

    /**
     * Field _exponent.
     */
    private byte[] _exponent;


      //----------------/
     //- Constructors -/
    //----------------/

    public RSAKeyValueType() {
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

        if (obj instanceof RSAKeyValueType) {

            RSAKeyValueType temp = (RSAKeyValueType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._modulus != null) {
                if (temp._modulus == null) return false;
                if (this._modulus != temp._modulus) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._modulus);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._modulus);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._modulus); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._modulus); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._modulus, temp._modulus)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._modulus);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._modulus);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._modulus);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._modulus);
                    }
                }
            } else if (temp._modulus != null)
                return false;
            if (this._exponent != null) {
                if (temp._exponent == null) return false;
                if (this._exponent != temp._exponent) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._exponent);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._exponent);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._exponent); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._exponent); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._exponent, temp._exponent)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._exponent);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._exponent);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._exponent);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._exponent);
                    }
                }
            } else if (temp._exponent != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'exponent'.
     * 
     * @return the value of field 'Exponent'.
     */
    public byte[] getExponent(
    ) {
        return this._exponent;
    }

    /**
     * Returns the value of field 'modulus'.
     * 
     * @return the value of field 'Modulus'.
     */
    public byte[] getModulus(
    ) {
        return this._modulus;
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
        if (_modulus != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_modulus)) {
           result = 37 * result + _modulus.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_modulus);
        }
        if (_exponent != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_exponent)) {
           result = 37 * result + _exponent.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_exponent);
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
     * Sets the value of field 'exponent'.
     * 
     * @param exponent the value of field 'exponent'.
     */
    public void setExponent(
            final byte[] exponent) {
        this._exponent = exponent;
    }

    /**
     * Sets the value of field 'modulus'.
     * 
     * @param modulus the value of field 'modulus'.
     */
    public void setModulus(
            final byte[] modulus) {
        this._modulus = modulus;
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
