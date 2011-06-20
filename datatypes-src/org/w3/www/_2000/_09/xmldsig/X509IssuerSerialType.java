/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig;

/**
 * Class X509IssuerSerialType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class X509IssuerSerialType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _x509IssuerName.
     */
    private java.lang.String _x509IssuerName;

    /**
     * Field _x509SerialNumber.
     */
    private long _x509SerialNumber;

    /**
     * keeps track of state for field: _x509SerialNumber
     */
    private boolean _has_x509SerialNumber;


      //----------------/
     //- Constructors -/
    //----------------/

    public X509IssuerSerialType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteX509SerialNumber(
    ) {
        this._has_x509SerialNumber= false;
    }

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

        if (obj instanceof X509IssuerSerialType) {

            X509IssuerSerialType temp = (X509IssuerSerialType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._x509IssuerName != null) {
                if (temp._x509IssuerName == null) return false;
                if (this._x509IssuerName != temp._x509IssuerName) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._x509IssuerName);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._x509IssuerName);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509IssuerName); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509IssuerName); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._x509IssuerName.equals(temp._x509IssuerName)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509IssuerName);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509IssuerName);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._x509IssuerName);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._x509IssuerName);
                    }
                }
            } else if (temp._x509IssuerName != null)
                return false;
            if (this._x509SerialNumber != temp._x509SerialNumber)
                return false;
            if (this._has_x509SerialNumber != temp._has_x509SerialNumber)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'x509IssuerName'.
     * 
     * @return the value of field 'X509IssuerName'.
     */
    public java.lang.String getX509IssuerName(
    ) {
        return this._x509IssuerName;
    }

    /**
     * Returns the value of field 'x509SerialNumber'.
     * 
     * @return the value of field 'X509SerialNumber'.
     */
    public long getX509SerialNumber(
    ) {
        return this._x509SerialNumber;
    }

    /**
     * Method hasX509SerialNumber.
     * 
     * @return true if at least one X509SerialNumber has been added
     */
    public boolean hasX509SerialNumber(
    ) {
        return this._has_x509SerialNumber;
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
        if (_x509IssuerName != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_x509IssuerName)) {
           result = 37 * result + _x509IssuerName.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_x509IssuerName);
        }
        result = 37 * result + (int)(_x509SerialNumber^(_x509SerialNumber>>>32));

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
     * Sets the value of field 'x509IssuerName'.
     * 
     * @param x509IssuerName the value of field 'x509IssuerName'.
     */
    public void setX509IssuerName(
            final java.lang.String x509IssuerName) {
        this._x509IssuerName = x509IssuerName;
    }

    /**
     * Sets the value of field 'x509SerialNumber'.
     * 
     * @param x509SerialNumber the value of field 'x509SerialNumber'
     */
    public void setX509SerialNumber(
            final long x509SerialNumber) {
        this._x509SerialNumber = x509SerialNumber;
        this._has_x509SerialNumber = true;
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
