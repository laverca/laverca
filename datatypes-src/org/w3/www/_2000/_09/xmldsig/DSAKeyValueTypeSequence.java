/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig;

/**
 * Class DSAKeyValueTypeSequence.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class DSAKeyValueTypeSequence implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _p.
     */
    private byte[] _p;

    /**
     * Field _q.
     */
    private byte[] _q;


      //----------------/
     //- Constructors -/
    //----------------/

    public DSAKeyValueTypeSequence() {
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

        if (obj instanceof DSAKeyValueTypeSequence) {

            DSAKeyValueTypeSequence temp = (DSAKeyValueTypeSequence)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._p != null) {
                if (temp._p == null) return false;
                if (this._p != temp._p) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._p);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._p);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._p); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._p); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._p, temp._p)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._p);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._p);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._p);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._p);
                    }
                }
            } else if (temp._p != null)
                return false;
            if (this._q != null) {
                if (temp._q == null) return false;
                if (this._q != temp._q) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._q);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._q);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._q); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._q); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._q, temp._q)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._q);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._q);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._q);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._q);
                    }
                }
            } else if (temp._q != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'p'.
     * 
     * @return the value of field 'P'.
     */
    public byte[] getP(
    ) {
        return this._p;
    }

    /**
     * Returns the value of field 'q'.
     * 
     * @return the value of field 'Q'.
     */
    public byte[] getQ(
    ) {
        return this._q;
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
        if (_p != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_p)) {
           result = 37 * result + _p.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_p);
        }
        if (_q != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_q)) {
           result = 37 * result + _q.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_q);
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
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'p'.
     * 
     * @param p the value of field 'p'.
     */
    public void setP(
            final byte[] p) {
        this._p = p;
    }

    /**
     * Sets the value of field 'q'.
     * 
     * @param q the value of field 'q'.
     */
    public void setQ(
            final byte[] q) {
        this._q = q;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence
     */
    public static org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence) org.exolab.castor.xml.Unmarshaller.unmarshal(org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence.class, reader);
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
