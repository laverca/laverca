/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class MobileUserType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MobileUserType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _identityIssuer.
     */
    private org.etsi.uri.TS102204.v1_1_2.IdentityIssuer _identityIssuer;

    /**
     * Field _userIdentifier.
     */
    private java.lang.String _userIdentifier;

    /**
     * Field _homeMSSP.
     */
    private org.etsi.uri.TS102204.v1_1_2.HomeMSSP _homeMSSP;

    /**
     * Field _MSISDN.
     */
    private java.lang.String _MSISDN;


      //----------------/
     //- Constructors -/
    //----------------/

    public MobileUserType() {
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

        if (obj instanceof MobileUserType) {

            MobileUserType temp = (MobileUserType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._identityIssuer != null) {
                if (temp._identityIssuer == null) return false;
                if (this._identityIssuer != temp._identityIssuer) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._identityIssuer);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._identityIssuer);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._identityIssuer); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._identityIssuer); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._identityIssuer.equals(temp._identityIssuer)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._identityIssuer);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._identityIssuer);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._identityIssuer);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._identityIssuer);
                    }
                }
            } else if (temp._identityIssuer != null)
                return false;
            if (this._userIdentifier != null) {
                if (temp._userIdentifier == null) return false;
                if (this._userIdentifier != temp._userIdentifier) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._userIdentifier);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._userIdentifier);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userIdentifier); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userIdentifier); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._userIdentifier.equals(temp._userIdentifier)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userIdentifier);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userIdentifier);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userIdentifier);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userIdentifier);
                    }
                }
            } else if (temp._userIdentifier != null)
                return false;
            if (this._homeMSSP != null) {
                if (temp._homeMSSP == null) return false;
                if (this._homeMSSP != temp._homeMSSP) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._homeMSSP);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._homeMSSP);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._homeMSSP); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._homeMSSP); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._homeMSSP.equals(temp._homeMSSP)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._homeMSSP);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._homeMSSP);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._homeMSSP);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._homeMSSP);
                    }
                }
            } else if (temp._homeMSSP != null)
                return false;
            if (this._MSISDN != null) {
                if (temp._MSISDN == null) return false;
                if (this._MSISDN != temp._MSISDN) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._MSISDN);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._MSISDN);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSISDN); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSISDN); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._MSISDN.equals(temp._MSISDN)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSISDN);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSISDN);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSISDN);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSISDN);
                    }
                }
            } else if (temp._MSISDN != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'homeMSSP'.
     * 
     * @return the value of field 'HomeMSSP'.
     */
    public org.etsi.uri.TS102204.v1_1_2.HomeMSSP getHomeMSSP(
    ) {
        return this._homeMSSP;
    }

    /**
     * Returns the value of field 'identityIssuer'.
     * 
     * @return the value of field 'IdentityIssuer'.
     */
    public org.etsi.uri.TS102204.v1_1_2.IdentityIssuer getIdentityIssuer(
    ) {
        return this._identityIssuer;
    }

    /**
     * Returns the value of field 'MSISDN'.
     * 
     * @return the value of field 'MSISDN'.
     */
    public java.lang.String getMSISDN(
    ) {
        return this._MSISDN;
    }

    /**
     * Returns the value of field 'userIdentifier'.
     * 
     * @return the value of field 'UserIdentifier'.
     */
    public java.lang.String getUserIdentifier(
    ) {
        return this._userIdentifier;
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
        if (_identityIssuer != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_identityIssuer)) {
           result = 37 * result + _identityIssuer.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_identityIssuer);
        }
        if (_userIdentifier != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_userIdentifier)) {
           result = 37 * result + _userIdentifier.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_userIdentifier);
        }
        if (_homeMSSP != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_homeMSSP)) {
           result = 37 * result + _homeMSSP.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_homeMSSP);
        }
        if (_MSISDN != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_MSISDN)) {
           result = 37 * result + _MSISDN.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_MSISDN);
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
     * Sets the value of field 'homeMSSP'.
     * 
     * @param homeMSSP the value of field 'homeMSSP'.
     */
    public void setHomeMSSP(
            final org.etsi.uri.TS102204.v1_1_2.HomeMSSP homeMSSP) {
        this._homeMSSP = homeMSSP;
    }

    /**
     * Sets the value of field 'identityIssuer'.
     * 
     * @param identityIssuer the value of field 'identityIssuer'.
     */
    public void setIdentityIssuer(
            final org.etsi.uri.TS102204.v1_1_2.IdentityIssuer identityIssuer) {
        this._identityIssuer = identityIssuer;
    }

    /**
     * Sets the value of field 'MSISDN'.
     * 
     * @param MSISDN the value of field 'MSISDN'.
     */
    public void setMSISDN(
            final java.lang.String MSISDN) {
        this._MSISDN = MSISDN;
    }

    /**
     * Sets the value of field 'userIdentifier'.
     * 
     * @param userIdentifier the value of field 'userIdentifier'.
     */
    public void setUserIdentifier(
            final java.lang.String userIdentifier) {
        this._userIdentifier = userIdentifier;
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
