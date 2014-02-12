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

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class AuthzDecisionQueryType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class AuthzDecisionQueryType extends oasis.names.tc.SAML.v2_0.protocol.SubjectQueryAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _resource.
     */
    private java.lang.String _resource;

    /**
     * Field _actionList.
     */
    private java.util.List<oasis.names.tc.SAML.v2_0.assertion.Action> _actionList;

    /**
     * Field _evidence.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Evidence _evidence;


      //----------------/
     //- Constructors -/
    //----------------/

    public AuthzDecisionQueryType() {
        super();
        this._actionList = new java.util.ArrayList<oasis.names.tc.SAML.v2_0.assertion.Action>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAction
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAction(
            final oasis.names.tc.SAML.v2_0.assertion.Action vAction)
    throws java.lang.IndexOutOfBoundsException {
        this._actionList.add(vAction);
    }

    /**
     * 
     * 
     * @param index
     * @param vAction
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAction(
            final int index,
            final oasis.names.tc.SAML.v2_0.assertion.Action vAction)
    throws java.lang.IndexOutOfBoundsException {
        this._actionList.add(index, vAction);
    }

    /**
     * Method enumerateAction.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends oasis.names.tc.SAML.v2_0.assertion.Action> enumerateAction(
    ) {
        return java.util.Collections.enumeration(this._actionList);
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

        if (super.equals(obj)==false)
            return false;

        if (obj instanceof AuthzDecisionQueryType) {

            AuthzDecisionQueryType temp = (AuthzDecisionQueryType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._resource != null) {
                if (temp._resource == null) return false;
                if (this._resource != temp._resource) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._resource);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._resource);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._resource); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._resource); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._resource.equals(temp._resource)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._resource);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._resource);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._resource);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._resource);
                    }
                }
            } else if (temp._resource != null)
                return false;
            if (this._actionList != null) {
                if (temp._actionList == null) return false;
                if (this._actionList != temp._actionList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._actionList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._actionList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._actionList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._actionList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._actionList.equals(temp._actionList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._actionList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._actionList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._actionList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._actionList);
                    }
                }
            } else if (temp._actionList != null)
                return false;
            if (this._evidence != null) {
                if (temp._evidence == null) return false;
                if (this._evidence != temp._evidence) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._evidence);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._evidence);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._evidence); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._evidence); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._evidence.equals(temp._evidence)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._evidence);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._evidence);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._evidence);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._evidence);
                    }
                }
            } else if (temp._evidence != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getAction.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * oasis.names.tc.SAML.v2_0.assertion.Action at the given index
     */
    public oasis.names.tc.SAML.v2_0.assertion.Action getAction(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._actionList.size()) {
            throw new IndexOutOfBoundsException("getAction: Index value '" + index + "' not in range [0.." + (this._actionList.size() - 1) + "]");
        }

        return (oasis.names.tc.SAML.v2_0.assertion.Action) _actionList.get(index);
    }

    /**
     * Method getAction.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public oasis.names.tc.SAML.v2_0.assertion.Action[] getAction(
    ) {
        oasis.names.tc.SAML.v2_0.assertion.Action[] array = new oasis.names.tc.SAML.v2_0.assertion.Action[0];
        return (oasis.names.tc.SAML.v2_0.assertion.Action[]) this._actionList.toArray(array);
    }

    /**
     * Method getActionCount.
     * 
     * @return the size of this collection
     */
    public int getActionCount(
    ) {
        return this._actionList.size();
    }

    /**
     * Returns the value of field 'evidence'.
     * 
     * @return the value of field 'Evidence'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.Evidence getEvidence(
    ) {
        return this._evidence;
    }

    /**
     * Returns the value of field 'resource'.
     * 
     * @return the value of field 'Resource'.
     */
    public java.lang.String getResource(
    ) {
        return this._resource;
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
        if (_resource != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_resource)) {
           result = 37 * result + _resource.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_resource);
        }
        if (_actionList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_actionList)) {
           result = 37 * result + _actionList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_actionList);
        }
        if (_evidence != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_evidence)) {
           result = 37 * result + _evidence.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_evidence);
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
     * Method iterateAction.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends oasis.names.tc.SAML.v2_0.assertion.Action> iterateAction(
    ) {
        return this._actionList.iterator();
    }

    /**
     * Method removeAction.
     * 
     * @param vAction
     * @return true if the object was removed from the collection.
     */
    public boolean removeAction(
            final oasis.names.tc.SAML.v2_0.assertion.Action vAction) {
        boolean removed = _actionList.remove(vAction);
        return removed;
    }

    /**
     * Method removeActionAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public oasis.names.tc.SAML.v2_0.assertion.Action removeActionAt(
            final int index) {
        java.lang.Object obj = this._actionList.remove(index);
        return (oasis.names.tc.SAML.v2_0.assertion.Action) obj;
    }

    /**
     */
    public void removeAllAction(
    ) {
        this._actionList.clear();
    }

    /**
     * 
     * 
     * @param index
     * @param vAction
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAction(
            final int index,
            final oasis.names.tc.SAML.v2_0.assertion.Action vAction)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._actionList.size()) {
            throw new IndexOutOfBoundsException("setAction: Index value '" + index + "' not in range [0.." + (this._actionList.size() - 1) + "]");
        }

        this._actionList.set(index, vAction);
    }

    /**
     * 
     * 
     * @param vActionArray
     */
    public void setAction(
            final oasis.names.tc.SAML.v2_0.assertion.Action[] vActionArray) {
        //-- copy array
        _actionList.clear();

        for (int i = 0; i < vActionArray.length; i++) {
                this._actionList.add(vActionArray[i]);
        }
    }

    /**
     * Sets the value of field 'evidence'.
     * 
     * @param evidence the value of field 'evidence'.
     */
    public void setEvidence(
            final oasis.names.tc.SAML.v2_0.assertion.Evidence evidence) {
        this._evidence = evidence;
    }

    /**
     * Sets the value of field 'resource'.
     * 
     * @param resource the value of field 'resource'.
     */
    public void setResource(
            final java.lang.String resource) {
        this._resource = resource;
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
