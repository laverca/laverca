/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2013 Laverca Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class MSSP_Info.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class MSSP_Info implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _instant.
     */
    private java.util.Date _instant;

    /**
     * Field _MSSP_ID.
     */
    private org.etsi.uri.TS102204.v1_1_2.MSSP_ID _MSSP_ID;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSSP_Info() {
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

        if (obj instanceof MSSP_Info) {

            MSSP_Info temp = (MSSP_Info)obj;
            boolean thcycle;
            boolean tmcycle;
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
            if (this._MSSP_ID != null) {
                if (temp._MSSP_ID == null) return false;
                if (this._MSSP_ID != temp._MSSP_ID) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._MSSP_ID);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._MSSP_ID);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSSP_ID); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSSP_ID); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._MSSP_ID.equals(temp._MSSP_ID)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSSP_ID);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSSP_ID);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._MSSP_ID);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._MSSP_ID);
                    }
                }
            } else if (temp._MSSP_ID != null)
                return false;
            return true;
        }
        return false;
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
     * Returns the value of field 'MSSP_ID'.
     * 
     * @return the value of field 'MSSP_ID'.
     */
    public org.etsi.uri.TS102204.v1_1_2.MSSP_ID getMSSP_ID(
    ) {
        return this._MSSP_ID;
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
        if (_instant != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_instant)) {
           result = 37 * result + _instant.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_instant);
        }
        if (_MSSP_ID != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_MSSP_ID)) {
           result = 37 * result + _MSSP_ID.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_MSSP_ID);
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
     * Sets the value of field 'instant'.
     * 
     * @param instant the value of field 'instant'.
     */
    public void setInstant(
            final java.util.Date instant) {
        this._instant = instant;
    }

    /**
     * Sets the value of field 'MSSP_ID'.
     * 
     * @param MSSP_ID the value of field 'MSSP_ID'.
     */
    public void setMSSP_ID(
            final org.etsi.uri.TS102204.v1_1_2.MSSP_ID MSSP_ID) {
        this._MSSP_ID = MSSP_ID;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled org.etsi.uri.TS102204.v1_1_2.MSSP_Inf
     */
    public static org.etsi.uri.TS102204.v1_1_2.MSSP_Info unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.etsi.uri.TS102204.v1_1_2.MSSP_Info) org.exolab.castor.xml.Unmarshaller.unmarshal(org.etsi.uri.TS102204.v1_1_2.MSSP_Info.class, reader);
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
