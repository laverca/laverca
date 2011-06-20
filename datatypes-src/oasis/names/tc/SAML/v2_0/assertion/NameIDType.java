/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class NameIDType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class NameIDType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _format.
     */
    private java.lang.String _format;

    /**
     * Field _SPProvidedID.
     */
    private java.lang.String _SPProvidedID;

    /**
     * Field _nameQualifier.
     */
    private java.lang.String _nameQualifier;

    /**
     * Field _SPNameQualifier.
     */
    private java.lang.String _SPNameQualifier;


      //----------------/
     //- Constructors -/
    //----------------/

    public NameIDType() {
        super();
        setContent("");
    }

    public NameIDType(final java.lang.String defaultValue) {
        try {
            setContent( new java.lang.String(defaultValue));
         } catch(Exception e) {
            throw new RuntimeException("Unable to cast default value for simple content!");
         } 
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

        if (obj instanceof NameIDType) {

            NameIDType temp = (NameIDType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._content != null) {
                if (temp._content == null) return false;
                if (this._content != temp._content) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._content);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._content);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._content.equals(temp._content)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content);
                    }
                }
            } else if (temp._content != null)
                return false;
            if (this._format != null) {
                if (temp._format == null) return false;
                if (this._format != temp._format) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._format);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._format);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._format); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._format); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._format.equals(temp._format)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._format);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._format);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._format);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._format);
                    }
                }
            } else if (temp._format != null)
                return false;
            if (this._SPProvidedID != null) {
                if (temp._SPProvidedID == null) return false;
                if (this._SPProvidedID != temp._SPProvidedID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._SPProvidedID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._SPProvidedID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._SPProvidedID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._SPProvidedID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._SPProvidedID.equals(temp._SPProvidedID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._SPProvidedID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._SPProvidedID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._SPProvidedID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._SPProvidedID);
                    }
                }
            } else if (temp._SPProvidedID != null)
                return false;
            if (this._nameQualifier != null) {
                if (temp._nameQualifier == null) return false;
                if (this._nameQualifier != temp._nameQualifier) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._nameQualifier);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._nameQualifier);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameQualifier); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameQualifier); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._nameQualifier.equals(temp._nameQualifier)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameQualifier);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameQualifier);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._nameQualifier);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._nameQualifier);
                    }
                }
            } else if (temp._nameQualifier != null)
                return false;
            if (this._SPNameQualifier != null) {
                if (temp._SPNameQualifier == null) return false;
                if (this._SPNameQualifier != temp._SPNameQualifier) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._SPNameQualifier);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._SPNameQualifier);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._SPNameQualifier); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._SPNameQualifier); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._SPNameQualifier.equals(temp._SPNameQualifier)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._SPNameQualifier);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._SPNameQualifier);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._SPNameQualifier);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._SPNameQualifier);
                    }
                }
            } else if (temp._SPNameQualifier != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return the value of field 'Content'.
     */
    public java.lang.String getContent(
    ) {
        return this._content;
    }

    /**
     * Returns the value of field 'format'.
     * 
     * @return the value of field 'Format'.
     */
    public java.lang.String getFormat(
    ) {
        return this._format;
    }

    /**
     * Returns the value of field 'nameQualifier'.
     * 
     * @return the value of field 'NameQualifier'.
     */
    public java.lang.String getNameQualifier(
    ) {
        return this._nameQualifier;
    }

    /**
     * Returns the value of field 'SPNameQualifier'.
     * 
     * @return the value of field 'SPNameQualifier'.
     */
    public java.lang.String getSPNameQualifier(
    ) {
        return this._SPNameQualifier;
    }

    /**
     * Returns the value of field 'SPProvidedID'.
     * 
     * @return the value of field 'SPProvidedID'.
     */
    public java.lang.String getSPProvidedID(
    ) {
        return this._SPProvidedID;
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
        if (_content != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_content)) {
           result = 37 * result + _content.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_content);
        }
        if (_format != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_format)) {
           result = 37 * result + _format.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_format);
        }
        if (_SPProvidedID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_SPProvidedID)) {
           result = 37 * result + _SPProvidedID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_SPProvidedID);
        }
        if (_nameQualifier != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_nameQualifier)) {
           result = 37 * result + _nameQualifier.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_nameQualifier);
        }
        if (_SPNameQualifier != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_SPNameQualifier)) {
           result = 37 * result + _SPNameQualifier.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_SPNameQualifier);
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
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(
            final java.lang.String content) {
        this._content = content;
    }

    /**
     * Sets the value of field 'format'.
     * 
     * @param format the value of field 'format'.
     */
    public void setFormat(
            final java.lang.String format) {
        this._format = format;
    }

    /**
     * Sets the value of field 'nameQualifier'.
     * 
     * @param nameQualifier the value of field 'nameQualifier'.
     */
    public void setNameQualifier(
            final java.lang.String nameQualifier) {
        this._nameQualifier = nameQualifier;
    }

    /**
     * Sets the value of field 'SPNameQualifier'.
     * 
     * @param SPNameQualifier the value of field 'SPNameQualifier'.
     */
    public void setSPNameQualifier(
            final java.lang.String SPNameQualifier) {
        this._SPNameQualifier = SPNameQualifier;
    }

    /**
     * Sets the value of field 'SPProvidedID'.
     * 
     * @param SPProvidedID the value of field 'SPProvidedID'.
     */
    public void setSPProvidedID(
            final java.lang.String SPProvidedID) {
        this._SPProvidedID = SPProvidedID;
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
