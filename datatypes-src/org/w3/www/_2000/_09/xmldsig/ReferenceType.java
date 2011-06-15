/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig;

/**
 * Class ReferenceType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ReferenceType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private java.lang.String _id;

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

    /**
     * Field _digestMethod.
     */
    private org.w3.www._2000._09.xmldsig.DigestMethod _digestMethod;

    /**
     * Field _digestValue.
     */
    private byte[] _digestValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public ReferenceType() {
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

        if (obj instanceof ReferenceType) {

            ReferenceType temp = (ReferenceType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._id != null) {
                if (temp._id == null) return false;
                if (this._id != temp._id) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._id);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._id);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._id); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._id); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._id.equals(temp._id)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._id);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._id);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._id);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._id);
                    }
                }
            } else if (temp._id != null)
                return false;
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
    public org.w3.www._2000._09.xmldsig.DigestMethod getDigestMethod(
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
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public java.lang.String getId(
    ) {
        return this._id;
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
        if (_id != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_id)) {
           result = 37 * result + _id.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_id);
        }
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
            final org.w3.www._2000._09.xmldsig.DigestMethod digestMethod) {
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
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(
            final java.lang.String id) {
        this._id = id;
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
