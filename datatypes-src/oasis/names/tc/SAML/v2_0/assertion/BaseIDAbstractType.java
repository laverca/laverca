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

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class BaseIDAbstractType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class BaseIDAbstractType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

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

    public BaseIDAbstractType() {
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

        if (obj instanceof BaseIDAbstractType) {

            BaseIDAbstractType temp = (BaseIDAbstractType)obj;
            boolean thcycle;
            boolean tmcycle;
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
