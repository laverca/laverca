/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class MSS_ProfileReqType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MSS_ProfileReqType extends org.etsi.uri.TS102204.v1_1_2.MessageAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _mobileUser.
     */
    private org.etsi.uri.TS102204.v1_1_2.MobileUser _mobileUser;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSS_ProfileReqType() {
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

        if (super.equals(obj)==false)
            return false;

        if (obj instanceof MSS_ProfileReqType) {

            MSS_ProfileReqType temp = (MSS_ProfileReqType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._mobileUser != null) {
                if (temp._mobileUser == null) return false;
                if (this._mobileUser != temp._mobileUser) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._mobileUser);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._mobileUser);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mobileUser); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mobileUser); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._mobileUser.equals(temp._mobileUser)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mobileUser);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mobileUser);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mobileUser);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mobileUser);
                    }
                }
            } else if (temp._mobileUser != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'mobileUser'.
     * 
     * @return the value of field 'MobileUser'.
     */
    public org.etsi.uri.TS102204.v1_1_2.MobileUser getMobileUser(
    ) {
        return this._mobileUser;
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
        int result = super.hashCode();

        long tmp;
        if (_mobileUser != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_mobileUser)) {
           result = 37 * result + _mobileUser.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_mobileUser);
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
     * Sets the value of field 'mobileUser'.
     * 
     * @param mobileUser the value of field 'mobileUser'.
     */
    public void setMobileUser(
            final org.etsi.uri.TS102204.v1_1_2.MobileUser mobileUser) {
        this._mobileUser = mobileUser;
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
