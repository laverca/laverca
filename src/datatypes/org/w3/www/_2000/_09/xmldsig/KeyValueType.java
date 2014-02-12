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
 * Class KeyValueType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class KeyValueType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _DSAKeyValue.
     */
    private org.w3.www._2000._09.xmldsig.DSAKeyValue _DSAKeyValue;

    /**
     * Field _RSAKeyValue.
     */
    private org.w3.www._2000._09.xmldsig.RSAKeyValue _RSAKeyValue;

    /**
     * Field _anyObject.
     */
    private java.lang.Object _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public KeyValueType() {
        super();
        setContent("");
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

        if (obj instanceof KeyValueType) {

            KeyValueType temp = (KeyValueType)obj;
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
            if (this._choiceValue != null) {
                if (temp._choiceValue == null) return false;
                if (this._choiceValue != temp._choiceValue) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._choiceValue);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._choiceValue);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._choiceValue.equals(temp._choiceValue)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._choiceValue);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._choiceValue);
                    }
                }
            } else if (temp._choiceValue != null)
                return false;
            if (this._DSAKeyValue != null) {
                if (temp._DSAKeyValue == null) return false;
                if (this._DSAKeyValue != temp._DSAKeyValue) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._DSAKeyValue);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._DSAKeyValue);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DSAKeyValue); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DSAKeyValue); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._DSAKeyValue.equals(temp._DSAKeyValue)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DSAKeyValue);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DSAKeyValue);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._DSAKeyValue);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._DSAKeyValue);
                    }
                }
            } else if (temp._DSAKeyValue != null)
                return false;
            if (this._RSAKeyValue != null) {
                if (temp._RSAKeyValue == null) return false;
                if (this._RSAKeyValue != temp._RSAKeyValue) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._RSAKeyValue);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._RSAKeyValue);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._RSAKeyValue); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._RSAKeyValue); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._RSAKeyValue.equals(temp._RSAKeyValue)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._RSAKeyValue);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._RSAKeyValue);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._RSAKeyValue);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._RSAKeyValue);
                    }
                }
            } else if (temp._RSAKeyValue != null)
                return false;
            if (this._anyObject != null) {
                if (temp._anyObject == null) return false;
                if (this._anyObject != temp._anyObject) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._anyObject);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._anyObject);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._anyObject.equals(temp._anyObject)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._anyObject);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._anyObject);
                    }
                }
            } else if (temp._anyObject != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'anyObject'.
     * 
     * @return the value of field 'AnyObject'.
     */
    public java.lang.Object getAnyObject(
    ) {
        return this._anyObject;
    }

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return the value of field 'ChoiceValue'.
     */
    public java.lang.Object getChoiceValue(
    ) {
        return this._choiceValue;
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
     * Returns the value of field 'DSAKeyValue'.
     * 
     * @return the value of field 'DSAKeyValue'.
     */
    public org.w3.www._2000._09.xmldsig.DSAKeyValue getDSAKeyValue(
    ) {
        return this._DSAKeyValue;
    }

    /**
     * Returns the value of field 'RSAKeyValue'.
     * 
     * @return the value of field 'RSAKeyValue'.
     */
    public org.w3.www._2000._09.xmldsig.RSAKeyValue getRSAKeyValue(
    ) {
        return this._RSAKeyValue;
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
        if (_choiceValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_choiceValue)) {
           result = 37 * result + _choiceValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_choiceValue);
        }
        if (_DSAKeyValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_DSAKeyValue)) {
           result = 37 * result + _DSAKeyValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_DSAKeyValue);
        }
        if (_RSAKeyValue != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_RSAKeyValue)) {
           result = 37 * result + _RSAKeyValue.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_RSAKeyValue);
        }
        if (_anyObject != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_anyObject)) {
           result = 37 * result + _anyObject.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_anyObject);
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
     * Sets the value of field 'anyObject'.
     * 
     * @param anyObject the value of field 'anyObject'.
     */
    public void setAnyObject(
            final java.lang.Object anyObject) {
        this._anyObject = anyObject;
        this._choiceValue = anyObject;
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
     * Sets the value of field 'DSAKeyValue'.
     * 
     * @param DSAKeyValue the value of field 'DSAKeyValue'.
     */
    public void setDSAKeyValue(
            final org.w3.www._2000._09.xmldsig.DSAKeyValue DSAKeyValue) {
        this._DSAKeyValue = DSAKeyValue;
        this._choiceValue = DSAKeyValue;
    }

    /**
     * Sets the value of field 'RSAKeyValue'.
     * 
     * @param RSAKeyValue the value of field 'RSAKeyValue'.
     */
    public void setRSAKeyValue(
            final org.w3.www._2000._09.xmldsig.RSAKeyValue RSAKeyValue) {
        this._RSAKeyValue = RSAKeyValue;
        this._choiceValue = RSAKeyValue;
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
