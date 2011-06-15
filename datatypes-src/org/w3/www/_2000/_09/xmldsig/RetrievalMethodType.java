/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig;

/**
 * Class RetrievalMethodType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class RetrievalMethodType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _URI.
     */
    private java.lang.String _URI;

    /**
     * Field _type.
     */
    private java.lang.String _type;

    /**
     * Field _transforms.
     */
    private org.w3.www._2000._09.xmldsig.Transforms _transforms;


      //----------------/
     //- Constructors -/
    //----------------/

    public RetrievalMethodType() {
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

        if (obj instanceof RetrievalMethodType) {

            RetrievalMethodType temp = (RetrievalMethodType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._URI != null) {
                if (temp._URI == null) return false;
                if (this._URI != temp._URI) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._URI);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._URI);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._URI); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._URI); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._URI.equals(temp._URI)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._URI);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._URI);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._URI);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._URI);
                    }
                }
            } else if (temp._URI != null)
                return false;
            if (this._type != null) {
                if (temp._type == null) return false;
                if (this._type != temp._type) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._type);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._type);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._type); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._type); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._type.equals(temp._type)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._type);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._type);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._type);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._type);
                    }
                }
            } else if (temp._type != null)
                return false;
            if (this._transforms != null) {
                if (temp._transforms == null) return false;
                if (this._transforms != temp._transforms) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._transforms);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._transforms);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transforms); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transforms); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._transforms.equals(temp._transforms)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transforms);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transforms);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transforms);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transforms);
                    }
                }
            } else if (temp._transforms != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'transforms'.
     * 
     * @return the value of field 'Transforms'.
     */
    public org.w3.www._2000._09.xmldsig.Transforms getTransforms(
    ) {
        return this._transforms;
    }

    /**
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'Type'.
     */
    public java.lang.String getType(
    ) {
        return this._type;
    }

    /**
     * Returns the value of field 'URI'.
     * 
     * @return the value of field 'URI'.
     */
    public java.lang.String getURI(
    ) {
        return this._URI;
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
        if (_URI != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_URI)) {
           result = 37 * result + _URI.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_URI);
        }
        if (_type != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_type)) {
           result = 37 * result + _type.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_type);
        }
        if (_transforms != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_transforms)) {
           result = 37 * result + _transforms.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_transforms);
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
     * Sets the value of field 'transforms'.
     * 
     * @param transforms the value of field 'transforms'.
     */
    public void setTransforms(
            final org.w3.www._2000._09.xmldsig.Transforms transforms) {
        this._transforms = transforms;
    }

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(
            final java.lang.String type) {
        this._type = type;
    }

    /**
     * Sets the value of field 'URI'.
     * 
     * @param URI the value of field 'URI'.
     */
    public void setURI(
            final java.lang.String URI) {
        this._URI = URI;
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
