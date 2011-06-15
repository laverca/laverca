/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class DataType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class DataType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _mimeType.
     */
    private java.lang.String _mimeType;

    /**
     * Field _encoding.
     */
    private java.lang.String _encoding;


      //----------------/
     //- Constructors -/
    //----------------/

    public DataType() {
        super();
        setContent("");
    }

    public DataType(final java.lang.String defaultValue) {
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

        if (obj instanceof DataType) {

            DataType temp = (DataType)obj;
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
            if (this._mimeType != null) {
                if (temp._mimeType == null) return false;
                if (this._mimeType != temp._mimeType) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._mimeType);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._mimeType);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mimeType); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mimeType); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._mimeType.equals(temp._mimeType)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mimeType);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mimeType);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._mimeType);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._mimeType);
                    }
                }
            } else if (temp._mimeType != null)
                return false;
            if (this._encoding != null) {
                if (temp._encoding == null) return false;
                if (this._encoding != temp._encoding) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._encoding);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._encoding);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encoding); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encoding); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._encoding.equals(temp._encoding)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encoding);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encoding);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._encoding);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._encoding);
                    }
                }
            } else if (temp._encoding != null)
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
     * Returns the value of field 'encoding'.
     * 
     * @return the value of field 'Encoding'.
     */
    public java.lang.String getEncoding(
    ) {
        return this._encoding;
    }

    /**
     * Returns the value of field 'mimeType'.
     * 
     * @return the value of field 'MimeType'.
     */
    public java.lang.String getMimeType(
    ) {
        return this._mimeType;
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
        if (_mimeType != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_mimeType)) {
           result = 37 * result + _mimeType.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_mimeType);
        }
        if (_encoding != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_encoding)) {
           result = 37 * result + _encoding.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_encoding);
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
     * Sets the value of field 'encoding'.
     * 
     * @param encoding the value of field 'encoding'.
     */
    public void setEncoding(
            final java.lang.String encoding) {
        this._encoding = encoding;
    }

    /**
     * Sets the value of field 'mimeType'.
     * 
     * @param mimeType the value of field 'mimeType'.
     */
    public void setMimeType(
            final java.lang.String mimeType) {
        this._mimeType = mimeType;
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
