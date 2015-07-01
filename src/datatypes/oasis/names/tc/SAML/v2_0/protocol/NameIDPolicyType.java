/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2015 Laverca Project
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

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class NameIDPolicyType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class NameIDPolicyType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _format.
     */
    private java.lang.String _format;

    /**
     * Field _SPNameQualifier.
     */
    private java.lang.String _SPNameQualifier;

    /**
     * Field _allowCreate.
     */
    private boolean _allowCreate;

    /**
     * keeps track of state for field: _allowCreate
     */
    private boolean _has_allowCreate;


      //----------------/
     //- Constructors -/
    //----------------/

    public NameIDPolicyType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteAllowCreate(
    ) {
        this._has_allowCreate= false;
    }

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

        if (obj instanceof NameIDPolicyType) {

            NameIDPolicyType temp = (NameIDPolicyType)obj;
            boolean thcycle;
            boolean tmcycle;
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
            if (this._allowCreate != temp._allowCreate)
                return false;
            if (this._has_allowCreate != temp._has_allowCreate)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'allowCreate'.
     * 
     * @return the value of field 'AllowCreate'.
     */
    public boolean getAllowCreate(
    ) {
        return this._allowCreate;
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
     * Returns the value of field 'SPNameQualifier'.
     * 
     * @return the value of field 'SPNameQualifier'.
     */
    public java.lang.String getSPNameQualifier(
    ) {
        return this._SPNameQualifier;
    }

    /**
     * Method hasAllowCreate.
     * 
     * @return true if at least one AllowCreate has been added
     */
    public boolean hasAllowCreate(
    ) {
        return this._has_allowCreate;
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
        if (_format != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_format)) {
           result = 37 * result + _format.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_format);
        }
        if (_SPNameQualifier != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_SPNameQualifier)) {
           result = 37 * result + _SPNameQualifier.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_SPNameQualifier);
        }
        result = 37 * result + (_allowCreate?0:1);

        return result;
    }

    /**
     * Returns the value of field 'allowCreate'.
     * 
     * @return the value of field 'AllowCreate'.
     */
    public boolean isAllowCreate(
    ) {
        return this._allowCreate;
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
     * Sets the value of field 'allowCreate'.
     * 
     * @param allowCreate the value of field 'allowCreate'.
     */
    public void setAllowCreate(
            final boolean allowCreate) {
        this._allowCreate = allowCreate;
        this._has_allowCreate = true;
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
