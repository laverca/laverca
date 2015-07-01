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
 * Class ManageNameIDRequestType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ManageNameIDRequestType extends oasis.names.tc.SAML.v2_0.protocol.RequestAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _manageNameIDRequestTypeChoice.
     */
    private oasis.names.tc.SAML.v2_0.protocol.ManageNameIDRequestTypeChoice _manageNameIDRequestTypeChoice;

    /**
     * Field _manageNameIDRequestTypeChoice2.
     */
    private oasis.names.tc.SAML.v2_0.protocol.ManageNameIDRequestTypeChoice2 _manageNameIDRequestTypeChoice2;


      //----------------/
     //- Constructors -/
    //----------------/

    public ManageNameIDRequestType() {
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

        if (super.equals(obj)==false)
            return false;

        if (obj instanceof ManageNameIDRequestType) {

            ManageNameIDRequestType temp = (ManageNameIDRequestType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._manageNameIDRequestTypeChoice != null) {
                if (temp._manageNameIDRequestTypeChoice == null) return false;
                if (this._manageNameIDRequestTypeChoice != temp._manageNameIDRequestTypeChoice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._manageNameIDRequestTypeChoice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._manageNameIDRequestTypeChoice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._manageNameIDRequestTypeChoice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._manageNameIDRequestTypeChoice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._manageNameIDRequestTypeChoice.equals(temp._manageNameIDRequestTypeChoice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._manageNameIDRequestTypeChoice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._manageNameIDRequestTypeChoice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._manageNameIDRequestTypeChoice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._manageNameIDRequestTypeChoice);
                    }
                }
            } else if (temp._manageNameIDRequestTypeChoice != null)
                return false;
            if (this._manageNameIDRequestTypeChoice2 != null) {
                if (temp._manageNameIDRequestTypeChoice2 == null) return false;
                if (this._manageNameIDRequestTypeChoice2 != temp._manageNameIDRequestTypeChoice2) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._manageNameIDRequestTypeChoice2);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._manageNameIDRequestTypeChoice2);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._manageNameIDRequestTypeChoice2); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._manageNameIDRequestTypeChoice2); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._manageNameIDRequestTypeChoice2.equals(temp._manageNameIDRequestTypeChoice2)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._manageNameIDRequestTypeChoice2);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._manageNameIDRequestTypeChoice2);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._manageNameIDRequestTypeChoice2);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._manageNameIDRequestTypeChoice2);
                    }
                }
            } else if (temp._manageNameIDRequestTypeChoice2 != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'manageNameIDRequestTypeChoice'.
     * 
     * @return the value of field 'ManageNameIDRequestTypeChoice'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.ManageNameIDRequestTypeChoice getManageNameIDRequestTypeChoice(
    ) {
        return this._manageNameIDRequestTypeChoice;
    }

    /**
     * Returns the value of field 'manageNameIDRequestTypeChoice2'.
     * 
     * @return the value of field 'ManageNameIDRequestTypeChoice2'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.ManageNameIDRequestTypeChoice2 getManageNameIDRequestTypeChoice2(
    ) {
        return this._manageNameIDRequestTypeChoice2;
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
        int result = super.hashCode();

        long tmp;
        if (_manageNameIDRequestTypeChoice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_manageNameIDRequestTypeChoice)) {
           result = 37 * result + _manageNameIDRequestTypeChoice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_manageNameIDRequestTypeChoice);
        }
        if (_manageNameIDRequestTypeChoice2 != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_manageNameIDRequestTypeChoice2)) {
           result = 37 * result + _manageNameIDRequestTypeChoice2.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_manageNameIDRequestTypeChoice2);
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
     * Sets the value of field 'manageNameIDRequestTypeChoice'.
     * 
     * @param manageNameIDRequestTypeChoice the value of field
     * 'manageNameIDRequestTypeChoice'.
     */
    public void setManageNameIDRequestTypeChoice(
            final oasis.names.tc.SAML.v2_0.protocol.ManageNameIDRequestTypeChoice manageNameIDRequestTypeChoice) {
        this._manageNameIDRequestTypeChoice = manageNameIDRequestTypeChoice;
    }

    /**
     * Sets the value of field 'manageNameIDRequestTypeChoice2'.
     * 
     * @param manageNameIDRequestTypeChoice2 the value of field
     * 'manageNameIDRequestTypeChoice2'.
     */
    public void setManageNameIDRequestTypeChoice2(
            final oasis.names.tc.SAML.v2_0.protocol.ManageNameIDRequestTypeChoice2 manageNameIDRequestTypeChoice2) {
        this._manageNameIDRequestTypeChoice2 = manageNameIDRequestTypeChoice2;
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
