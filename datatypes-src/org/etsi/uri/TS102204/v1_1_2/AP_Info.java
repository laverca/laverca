/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class AP_Info.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AP_Info implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _AP_ID.
     */
    private java.lang.String _AP_ID;

    /**
     * Field _AP_TransID.
     */
    private java.lang.String _AP_TransID;

    /**
     * Field _AP_PWD.
     */
    private java.lang.String _AP_PWD;

    /**
     * Field _instant.
     */
    private java.util.Date _instant;

    /**
     * Field _AP_URL.
     */
    private java.lang.String _AP_URL;


      //----------------/
     //- Constructors -/
    //----------------/

    public AP_Info() {
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

        if (obj instanceof AP_Info) {

            AP_Info temp = (AP_Info)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._AP_ID != null) {
                if (temp._AP_ID == null) return false;
                if (this._AP_ID != temp._AP_ID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._AP_ID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._AP_ID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_ID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_ID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._AP_ID.equals(temp._AP_ID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_ID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_ID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_ID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_ID);
                    }
                }
            } else if (temp._AP_ID != null)
                return false;
            if (this._AP_TransID != null) {
                if (temp._AP_TransID == null) return false;
                if (this._AP_TransID != temp._AP_TransID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._AP_TransID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._AP_TransID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_TransID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_TransID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._AP_TransID.equals(temp._AP_TransID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_TransID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_TransID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_TransID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_TransID);
                    }
                }
            } else if (temp._AP_TransID != null)
                return false;
            if (this._AP_PWD != null) {
                if (temp._AP_PWD == null) return false;
                if (this._AP_PWD != temp._AP_PWD) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._AP_PWD);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._AP_PWD);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_PWD); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_PWD); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._AP_PWD.equals(temp._AP_PWD)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_PWD);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_PWD);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_PWD);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_PWD);
                    }
                }
            } else if (temp._AP_PWD != null)
                return false;
            if (this._instant != null) {
                if (temp._instant == null) return false;
                if (this._instant != temp._instant) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._instant);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._instant);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._instant); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._instant); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._instant.equals(temp._instant)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._instant);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._instant);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._instant);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._instant);
                    }
                }
            } else if (temp._instant != null)
                return false;
            if (this._AP_URL != null) {
                if (temp._AP_URL == null) return false;
                if (this._AP_URL != temp._AP_URL) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._AP_URL);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._AP_URL);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_URL); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_URL); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._AP_URL.equals(temp._AP_URL)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_URL);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_URL);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._AP_URL);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._AP_URL);
                    }
                }
            } else if (temp._AP_URL != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'AP_ID'.
     * 
     * @return the value of field 'AP_ID'.
     */
    public java.lang.String getAP_ID(
    ) {
        return this._AP_ID;
    }

    /**
     * Returns the value of field 'AP_PWD'.
     * 
     * @return the value of field 'AP_PWD'.
     */
    public java.lang.String getAP_PWD(
    ) {
        return this._AP_PWD;
    }

    /**
     * Returns the value of field 'AP_TransID'.
     * 
     * @return the value of field 'AP_TransID'.
     */
    public java.lang.String getAP_TransID(
    ) {
        return this._AP_TransID;
    }

    /**
     * Returns the value of field 'AP_URL'.
     * 
     * @return the value of field 'AP_URL'.
     */
    public java.lang.String getAP_URL(
    ) {
        return this._AP_URL;
    }

    /**
     * Returns the value of field 'instant'.
     * 
     * @return the value of field 'Instant'.
     */
    public java.util.Date getInstant(
    ) {
        return this._instant;
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
        if (_AP_ID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_AP_ID)) {
           result = 37 * result + _AP_ID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_AP_ID);
        }
        if (_AP_TransID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_AP_TransID)) {
           result = 37 * result + _AP_TransID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_AP_TransID);
        }
        if (_AP_PWD != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_AP_PWD)) {
           result = 37 * result + _AP_PWD.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_AP_PWD);
        }
        if (_instant != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_instant)) {
           result = 37 * result + _instant.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_instant);
        }
        if (_AP_URL != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_AP_URL)) {
           result = 37 * result + _AP_URL.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_AP_URL);
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
     * Sets the value of field 'AP_ID'.
     * 
     * @param AP_ID the value of field 'AP_ID'.
     */
    public void setAP_ID(
            final java.lang.String AP_ID) {
        this._AP_ID = AP_ID;
    }

    /**
     * Sets the value of field 'AP_PWD'.
     * 
     * @param AP_PWD the value of field 'AP_PWD'.
     */
    public void setAP_PWD(
            final java.lang.String AP_PWD) {
        this._AP_PWD = AP_PWD;
    }

    /**
     * Sets the value of field 'AP_TransID'.
     * 
     * @param AP_TransID the value of field 'AP_TransID'.
     */
    public void setAP_TransID(
            final java.lang.String AP_TransID) {
        this._AP_TransID = AP_TransID;
    }

    /**
     * Sets the value of field 'AP_URL'.
     * 
     * @param AP_URL the value of field 'AP_URL'.
     */
    public void setAP_URL(
            final java.lang.String AP_URL) {
        this._AP_URL = AP_URL;
    }

    /**
     * Sets the value of field 'instant'.
     * 
     * @param instant the value of field 'instant'.
     */
    public void setInstant(
            final java.util.Date instant) {
        this._instant = instant;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.etsi.uri.TS102204.v1_1_2.AP_Info
     */
    public static org.etsi.uri.TS102204.v1_1_2.AP_Info unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.etsi.uri.TS102204.v1_1_2.AP_Info) org.exolab.castor.xml.Unmarshaller.unmarshal(org.etsi.uri.TS102204.v1_1_2.AP_Info.class, reader);
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
