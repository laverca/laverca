/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2014 Laverca Project
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
 * Class DSAKeyValueType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class DSAKeyValueType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _DSAKeyValueTypeSequence.
     */
    private org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence _DSAKeyValueTypeSequence;

    /**
     * Field _g.
     */
    private byte[] _g;

    /**
     * Field _y.
     */
    private byte[] _y;

    /**
     * Field _j.
     */
    private byte[] _j;

    /**
     * Field _DSAKeyValueTypeSequence2.
     */
    private org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2 _DSAKeyValueTypeSequence2;


      //----------------/
     //- Constructors -/
    //----------------/

    public DSAKeyValueType() {
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

        if (obj instanceof DSAKeyValueType) {

            DSAKeyValueType temp = (DSAKeyValueType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._DSAKeyValueTypeSequence != null) {
                if (temp._DSAKeyValueTypeSequence == null) return false;
                if (this._DSAKeyValueTypeSequence != temp._DSAKeyValueTypeSequence) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._DSAKeyValueTypeSequence);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._DSAKeyValueTypeSequence);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DSAKeyValueTypeSequence); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DSAKeyValueTypeSequence); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._DSAKeyValueTypeSequence.equals(temp._DSAKeyValueTypeSequence)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DSAKeyValueTypeSequence);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DSAKeyValueTypeSequence);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DSAKeyValueTypeSequence);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DSAKeyValueTypeSequence);
                    }
                }
            } else if (temp._DSAKeyValueTypeSequence != null)
                return false;
            if (this._g != null) {
                if (temp._g == null) return false;
                if (this._g != temp._g) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._g);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._g);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._g); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._g); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._g, temp._g)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._g);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._g);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._g);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._g);
                    }
                }
            } else if (temp._g != null)
                return false;
            if (this._y != null) {
                if (temp._y == null) return false;
                if (this._y != temp._y) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._y);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._y);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._y); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._y); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._y, temp._y)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._y);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._y);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._y);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._y);
                    }
                }
            } else if (temp._y != null)
                return false;
            if (this._j != null) {
                if (temp._j == null) return false;
                if (this._j != temp._j) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._j);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._j);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._j); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._j); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!java.util.Arrays.equals(this._j, temp._j)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._j);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._j);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._j);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._j);
                    }
                }
            } else if (temp._j != null)
                return false;
            if (this._DSAKeyValueTypeSequence2 != null) {
                if (temp._DSAKeyValueTypeSequence2 == null) return false;
                if (this._DSAKeyValueTypeSequence2 != temp._DSAKeyValueTypeSequence2) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._DSAKeyValueTypeSequence2);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._DSAKeyValueTypeSequence2);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DSAKeyValueTypeSequence2); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DSAKeyValueTypeSequence2); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._DSAKeyValueTypeSequence2.equals(temp._DSAKeyValueTypeSequence2)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DSAKeyValueTypeSequence2);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DSAKeyValueTypeSequence2);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DSAKeyValueTypeSequence2);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DSAKeyValueTypeSequence2);
                    }
                }
            } else if (temp._DSAKeyValueTypeSequence2 != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'DSAKeyValueTypeSequence'.
     * 
     * @return the value of field 'DSAKeyValueTypeSequence'.
     */
    public org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence getDSAKeyValueTypeSequence(
    ) {
        return this._DSAKeyValueTypeSequence;
    }

    /**
     * Returns the value of field 'DSAKeyValueTypeSequence2'.
     * 
     * @return the value of field 'DSAKeyValueTypeSequence2'.
     */
    public org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2 getDSAKeyValueTypeSequence2(
    ) {
        return this._DSAKeyValueTypeSequence2;
    }

    /**
     * Returns the value of field 'g'.
     * 
     * @return the value of field 'G'.
     */
    public byte[] getG(
    ) {
        return this._g;
    }

    /**
     * Returns the value of field 'j'.
     * 
     * @return the value of field 'J'.
     */
    public byte[] getJ(
    ) {
        return this._j;
    }

    /**
     * Returns the value of field 'y'.
     * 
     * @return the value of field 'Y'.
     */
    public byte[] getY(
    ) {
        return this._y;
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
        if (_DSAKeyValueTypeSequence != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_DSAKeyValueTypeSequence)) {
           result = 37 * result + _DSAKeyValueTypeSequence.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_DSAKeyValueTypeSequence);
        }
        if (_g != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_g)) {
           result = 37 * result + _g.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_g);
        }
        if (_y != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_y)) {
           result = 37 * result + _y.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_y);
        }
        if (_j != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_j)) {
           result = 37 * result + _j.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_j);
        }
        if (_DSAKeyValueTypeSequence2 != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_DSAKeyValueTypeSequence2)) {
           result = 37 * result + _DSAKeyValueTypeSequence2.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_DSAKeyValueTypeSequence2);
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
     * Sets the value of field 'DSAKeyValueTypeSequence'.
     * 
     * @param DSAKeyValueTypeSequence the value of field
     * 'DSAKeyValueTypeSequence'.
     */
    public void setDSAKeyValueTypeSequence(
            final org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence DSAKeyValueTypeSequence) {
        this._DSAKeyValueTypeSequence = DSAKeyValueTypeSequence;
    }

    /**
     * Sets the value of field 'DSAKeyValueTypeSequence2'.
     * 
     * @param DSAKeyValueTypeSequence2 the value of field
     * 'DSAKeyValueTypeSequence2'.
     */
    public void setDSAKeyValueTypeSequence2(
            final org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2 DSAKeyValueTypeSequence2) {
        this._DSAKeyValueTypeSequence2 = DSAKeyValueTypeSequence2;
    }

    /**
     * Sets the value of field 'g'.
     * 
     * @param g the value of field 'g'.
     */
    public void setG(
            final byte[] g) {
        this._g = g;
    }

    /**
     * Sets the value of field 'j'.
     * 
     * @param j the value of field 'j'.
     */
    public void setJ(
            final byte[] j) {
        this._j = j;
    }

    /**
     * Sets the value of field 'y'.
     * 
     * @param y the value of field 'y'.
     */
    public void setY(
            final byte[] y) {
        this._y = y;
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
