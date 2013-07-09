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

package org.w3.www._2000._09.xmldsig;

/**
 * Class DSAKeyValueTypeSequence2.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class DSAKeyValueTypeSequence2 implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _seed.
     */
    private byte[] _seed;

    /**
     * Field _pgenCounter.
     */
    private byte[] _pgenCounter;


      //----------------/
     //- Constructors -/
    //----------------/

    public DSAKeyValueTypeSequence2() {
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

        if (obj instanceof DSAKeyValueTypeSequence2) {

            DSAKeyValueTypeSequence2 temp = (DSAKeyValueTypeSequence2)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._seed != null) {
                if (temp._seed == null) return false;
                if (this._seed != temp._seed) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._seed);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._seed);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._seed); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._seed); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._seed, temp._seed)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._seed);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._seed);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._seed);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._seed);
                    }
                }
            } else if (temp._seed != null)
                return false;
            if (this._pgenCounter != null) {
                if (temp._pgenCounter == null) return false;
                if (this._pgenCounter != temp._pgenCounter) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._pgenCounter);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._pgenCounter);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pgenCounter); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pgenCounter); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._pgenCounter, temp._pgenCounter)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pgenCounter);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pgenCounter);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pgenCounter);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pgenCounter);
                    }
                }
            } else if (temp._pgenCounter != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'pgenCounter'.
     * 
     * @return the value of field 'PgenCounter'.
     */
    public byte[] getPgenCounter(
    ) {
        return this._pgenCounter;
    }

    /**
     * Returns the value of field 'seed'.
     * 
     * @return the value of field 'Seed'.
     */
    public byte[] getSeed(
    ) {
        return this._seed;
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
        if (_seed != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_seed)) {
           result = 37 * result + _seed.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_seed);
        }
        if (_pgenCounter != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_pgenCounter)) {
           result = 37 * result + _pgenCounter.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_pgenCounter);
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
     * Sets the value of field 'pgenCounter'.
     * 
     * @param pgenCounter the value of field 'pgenCounter'.
     */
    public void setPgenCounter(
            final byte[] pgenCounter) {
        this._pgenCounter = pgenCounter;
    }

    /**
     * Sets the value of field 'seed'.
     * 
     * @param seed the value of field 'seed'.
     */
    public void setSeed(
            final byte[] seed) {
        this._seed = seed;
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
     * org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2
     */
    public static org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2 unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2) org.exolab.castor.xml.Unmarshaller.unmarshal(org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2.class, reader);
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
