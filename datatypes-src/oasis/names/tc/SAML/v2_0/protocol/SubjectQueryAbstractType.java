/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class SubjectQueryAbstractType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class SubjectQueryAbstractType extends oasis.names.tc.SAML.v2_0.protocol.RequestAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _subject.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Subject _subject;


      //----------------/
     //- Constructors -/
    //----------------/

    public SubjectQueryAbstractType() {
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

        if (obj instanceof SubjectQueryAbstractType) {

            SubjectQueryAbstractType temp = (SubjectQueryAbstractType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._subject != null) {
                if (temp._subject == null) return false;
                if (this._subject != temp._subject) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._subject);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._subject);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subject); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subject); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._subject.equals(temp._subject)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subject);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subject);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._subject);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._subject);
                    }
                }
            } else if (temp._subject != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'subject'.
     * 
     * @return the value of field 'Subject'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.Subject getSubject(
    ) {
        return this._subject;
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
        if (_subject != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_subject)) {
           result = 37 * result + _subject.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_subject);
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
     * Sets the value of field 'subject'.
     * 
     * @param subject the value of field 'subject'.
     */
    public void setSubject(
            final oasis.names.tc.SAML.v2_0.assertion.Subject subject) {
        this._subject = subject;
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
