/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class LogoutRequestType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class LogoutRequestType extends oasis.names.tc.SAML.v2_0.protocol.RequestAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _reason.
     */
    private java.lang.String _reason;

    /**
     * Field _notOnOrAfter.
     */
    private java.util.Date _notOnOrAfter;

    /**
     * Field _logoutRequestTypeChoice.
     */
    private oasis.names.tc.SAML.v2_0.protocol.LogoutRequestTypeChoice _logoutRequestTypeChoice;

    /**
     * Field _sessionIndexList.
     */
    private java.util.List<java.lang.String> _sessionIndexList;


      //----------------/
     //- Constructors -/
    //----------------/

    public LogoutRequestType() {
        super();
        this._sessionIndexList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSessionIndex
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSessionIndex(
            final java.lang.String vSessionIndex)
    throws java.lang.IndexOutOfBoundsException {
        this._sessionIndexList.add(vSessionIndex);
    }

    /**
     * 
     * 
     * @param index
     * @param vSessionIndex
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSessionIndex(
            final int index,
            final java.lang.String vSessionIndex)
    throws java.lang.IndexOutOfBoundsException {
        this._sessionIndexList.add(index, vSessionIndex);
    }

    /**
     * Method enumerateSessionIndex.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateSessionIndex(
    ) {
        return java.util.Collections.enumeration(this._sessionIndexList);
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

        if (obj instanceof LogoutRequestType) {

            LogoutRequestType temp = (LogoutRequestType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._reason != null) {
                if (temp._reason == null) return false;
                if (this._reason != temp._reason) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._reason);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._reason);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reason); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reason); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._reason.equals(temp._reason)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reason);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reason);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reason);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reason);
                    }
                }
            } else if (temp._reason != null)
                return false;
            if (this._notOnOrAfter != null) {
                if (temp._notOnOrAfter == null) return false;
                if (this._notOnOrAfter != temp._notOnOrAfter) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._notOnOrAfter);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._notOnOrAfter);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._notOnOrAfter); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._notOnOrAfter); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._notOnOrAfter.equals(temp._notOnOrAfter)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._notOnOrAfter);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._notOnOrAfter);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._notOnOrAfter);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._notOnOrAfter);
                    }
                }
            } else if (temp._notOnOrAfter != null)
                return false;
            if (this._logoutRequestTypeChoice != null) {
                if (temp._logoutRequestTypeChoice == null) return false;
                if (this._logoutRequestTypeChoice != temp._logoutRequestTypeChoice) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._logoutRequestTypeChoice);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._logoutRequestTypeChoice);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._logoutRequestTypeChoice); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._logoutRequestTypeChoice); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._logoutRequestTypeChoice.equals(temp._logoutRequestTypeChoice)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._logoutRequestTypeChoice);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._logoutRequestTypeChoice);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._logoutRequestTypeChoice);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._logoutRequestTypeChoice);
                    }
                }
            } else if (temp._logoutRequestTypeChoice != null)
                return false;
            if (this._sessionIndexList != null) {
                if (temp._sessionIndexList == null) return false;
                if (this._sessionIndexList != temp._sessionIndexList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._sessionIndexList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._sessionIndexList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionIndexList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionIndexList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._sessionIndexList.equals(temp._sessionIndexList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionIndexList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionIndexList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionIndexList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionIndexList);
                    }
                }
            } else if (temp._sessionIndexList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'logoutRequestTypeChoice'.
     * 
     * @return the value of field 'LogoutRequestTypeChoice'.
     */
    public oasis.names.tc.SAML.v2_0.protocol.LogoutRequestTypeChoice getLogoutRequestTypeChoice(
    ) {
        return this._logoutRequestTypeChoice;
    }

    /**
     * Returns the value of field 'notOnOrAfter'.
     * 
     * @return the value of field 'NotOnOrAfter'.
     */
    public java.util.Date getNotOnOrAfter(
    ) {
        return this._notOnOrAfter;
    }

    /**
     * Returns the value of field 'reason'.
     * 
     * @return the value of field 'Reason'.
     */
    public java.lang.String getReason(
    ) {
        return this._reason;
    }

    /**
     * Method getSessionIndex.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getSessionIndex(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._sessionIndexList.size()) {
            throw new IndexOutOfBoundsException("getSessionIndex: Index value '" + index + "' not in range [0.." + (this._sessionIndexList.size() - 1) + "]");
        }

        return (java.lang.String) _sessionIndexList.get(index);
    }

    /**
     * Method getSessionIndex.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getSessionIndex(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._sessionIndexList.toArray(array);
    }

    /**
     * Method getSessionIndexCount.
     * 
     * @return the size of this collection
     */
    public int getSessionIndexCount(
    ) {
        return this._sessionIndexList.size();
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
        if (_reason != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_reason)) {
           result = 37 * result + _reason.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_reason);
        }
        if (_notOnOrAfter != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_notOnOrAfter)) {
           result = 37 * result + _notOnOrAfter.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_notOnOrAfter);
        }
        if (_logoutRequestTypeChoice != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_logoutRequestTypeChoice)) {
           result = 37 * result + _logoutRequestTypeChoice.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_logoutRequestTypeChoice);
        }
        if (_sessionIndexList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_sessionIndexList)) {
           result = 37 * result + _sessionIndexList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_sessionIndexList);
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
     * Method iterateSessionIndex.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateSessionIndex(
    ) {
        return this._sessionIndexList.iterator();
    }

    /**
     */
    public void removeAllSessionIndex(
    ) {
        this._sessionIndexList.clear();
    }

    /**
     * Method removeSessionIndex.
     * 
     * @param vSessionIndex
     * @return true if the object was removed from the collection.
     */
    public boolean removeSessionIndex(
            final java.lang.String vSessionIndex) {
        boolean removed = _sessionIndexList.remove(vSessionIndex);
        return removed;
    }

    /**
     * Method removeSessionIndexAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeSessionIndexAt(
            final int index) {
        java.lang.Object obj = this._sessionIndexList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Sets the value of field 'logoutRequestTypeChoice'.
     * 
     * @param logoutRequestTypeChoice the value of field
     * 'logoutRequestTypeChoice'.
     */
    public void setLogoutRequestTypeChoice(
            final oasis.names.tc.SAML.v2_0.protocol.LogoutRequestTypeChoice logoutRequestTypeChoice) {
        this._logoutRequestTypeChoice = logoutRequestTypeChoice;
    }

    /**
     * Sets the value of field 'notOnOrAfter'.
     * 
     * @param notOnOrAfter the value of field 'notOnOrAfter'.
     */
    public void setNotOnOrAfter(
            final java.util.Date notOnOrAfter) {
        this._notOnOrAfter = notOnOrAfter;
    }

    /**
     * Sets the value of field 'reason'.
     * 
     * @param reason the value of field 'reason'.
     */
    public void setReason(
            final java.lang.String reason) {
        this._reason = reason;
    }

    /**
     * 
     * 
     * @param index
     * @param vSessionIndex
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSessionIndex(
            final int index,
            final java.lang.String vSessionIndex)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._sessionIndexList.size()) {
            throw new IndexOutOfBoundsException("setSessionIndex: Index value '" + index + "' not in range [0.." + (this._sessionIndexList.size() - 1) + "]");
        }

        this._sessionIndexList.set(index, vSessionIndex);
    }

    /**
     * 
     * 
     * @param vSessionIndexArray
     */
    public void setSessionIndex(
            final java.lang.String[] vSessionIndexArray) {
        //-- copy array
        _sessionIndexList.clear();

        for (int i = 0; i < vSessionIndexArray.length; i++) {
                this._sessionIndexList.add(vSessionIndexArray[i]);
        }
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
