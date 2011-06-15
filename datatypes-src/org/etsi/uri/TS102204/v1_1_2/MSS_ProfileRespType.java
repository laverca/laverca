/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.etsi.uri.TS102204.v1_1_2;

/**
 * Class MSS_ProfileRespType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class MSS_ProfileRespType extends org.etsi.uri.TS102204.v1_1_2.MessageAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _signatureProfileList.
     */
    private java.util.List<org.etsi.uri.TS102204.v1_1_2.SignatureProfile> _signatureProfileList;

    /**
     * Field _status.
     */
    private org.etsi.uri.TS102204.v1_1_2.Status _status;


      //----------------/
     //- Constructors -/
    //----------------/

    public MSS_ProfileRespType() {
        super();
        this._signatureProfileList = new java.util.ArrayList<org.etsi.uri.TS102204.v1_1_2.SignatureProfile>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSignatureProfile
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSignatureProfile(
            final org.etsi.uri.TS102204.v1_1_2.SignatureProfile vSignatureProfile)
    throws java.lang.IndexOutOfBoundsException {
        this._signatureProfileList.add(vSignatureProfile);
    }

    /**
     * 
     * 
     * @param index
     * @param vSignatureProfile
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSignatureProfile(
            final int index,
            final org.etsi.uri.TS102204.v1_1_2.SignatureProfile vSignatureProfile)
    throws java.lang.IndexOutOfBoundsException {
        this._signatureProfileList.add(index, vSignatureProfile);
    }

    /**
     * Method enumerateSignatureProfile.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends org.etsi.uri.TS102204.v1_1_2.SignatureProfile> enumerateSignatureProfile(
    ) {
        return java.util.Collections.enumeration(this._signatureProfileList);
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

        if (obj instanceof MSS_ProfileRespType) {

            MSS_ProfileRespType temp = (MSS_ProfileRespType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._signatureProfileList != null) {
                if (temp._signatureProfileList == null) return false;
                if (this._signatureProfileList != temp._signatureProfileList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._signatureProfileList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._signatureProfileList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureProfileList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureProfileList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._signatureProfileList.equals(temp._signatureProfileList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureProfileList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureProfileList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._signatureProfileList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._signatureProfileList);
                    }
                }
            } else if (temp._signatureProfileList != null)
                return false;
            if (this._status != null) {
                if (temp._status == null) return false;
                if (this._status != temp._status) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._status);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._status);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._status.equals(temp._status)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._status);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._status);
                    }
                }
            } else if (temp._status != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getSignatureProfile.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.etsi.uri.TS102204.v1_1_2.SignatureProfile at the given
     * index
     */
    public org.etsi.uri.TS102204.v1_1_2.SignatureProfile getSignatureProfile(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._signatureProfileList.size()) {
            throw new IndexOutOfBoundsException("getSignatureProfile: Index value '" + index + "' not in range [0.." + (this._signatureProfileList.size() - 1) + "]");
        }

        return (org.etsi.uri.TS102204.v1_1_2.SignatureProfile) _signatureProfileList.get(index);
    }

    /**
     * Method getSignatureProfile.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.etsi.uri.TS102204.v1_1_2.SignatureProfile[] getSignatureProfile(
    ) {
        org.etsi.uri.TS102204.v1_1_2.SignatureProfile[] array = new org.etsi.uri.TS102204.v1_1_2.SignatureProfile[0];
        return (org.etsi.uri.TS102204.v1_1_2.SignatureProfile[]) this._signatureProfileList.toArray(array);
    }

    /**
     * Method getSignatureProfileCount.
     * 
     * @return the size of this collection
     */
    public int getSignatureProfileCount(
    ) {
        return this._signatureProfileList.size();
    }

    /**
     * Returns the value of field 'status'.
     * 
     * @return the value of field 'Status'.
     */
    public org.etsi.uri.TS102204.v1_1_2.Status getStatus(
    ) {
        return this._status;
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
        if (_signatureProfileList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_signatureProfileList)) {
           result = 37 * result + _signatureProfileList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_signatureProfileList);
        }
        if (_status != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_status)) {
           result = 37 * result + _status.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_status);
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
     * Method iterateSignatureProfile.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends org.etsi.uri.TS102204.v1_1_2.SignatureProfile> iterateSignatureProfile(
    ) {
        return this._signatureProfileList.iterator();
    }

    /**
     */
    public void removeAllSignatureProfile(
    ) {
        this._signatureProfileList.clear();
    }

    /**
     * Method removeSignatureProfile.
     * 
     * @param vSignatureProfile
     * @return true if the object was removed from the collection.
     */
    public boolean removeSignatureProfile(
            final org.etsi.uri.TS102204.v1_1_2.SignatureProfile vSignatureProfile) {
        boolean removed = _signatureProfileList.remove(vSignatureProfile);
        return removed;
    }

    /**
     * Method removeSignatureProfileAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.etsi.uri.TS102204.v1_1_2.SignatureProfile removeSignatureProfileAt(
            final int index) {
        java.lang.Object obj = this._signatureProfileList.remove(index);
        return (org.etsi.uri.TS102204.v1_1_2.SignatureProfile) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vSignatureProfile
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSignatureProfile(
            final int index,
            final org.etsi.uri.TS102204.v1_1_2.SignatureProfile vSignatureProfile)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._signatureProfileList.size()) {
            throw new IndexOutOfBoundsException("setSignatureProfile: Index value '" + index + "' not in range [0.." + (this._signatureProfileList.size() - 1) + "]");
        }

        this._signatureProfileList.set(index, vSignatureProfile);
    }

    /**
     * 
     * 
     * @param vSignatureProfileArray
     */
    public void setSignatureProfile(
            final org.etsi.uri.TS102204.v1_1_2.SignatureProfile[] vSignatureProfileArray) {
        //-- copy array
        _signatureProfileList.clear();

        for (int i = 0; i < vSignatureProfileArray.length; i++) {
                this._signatureProfileList.add(vSignatureProfileArray[i]);
        }
    }

    /**
     * Sets the value of field 'status'.
     * 
     * @param status the value of field 'status'.
     */
    public void setStatus(
            final org.etsi.uri.TS102204.v1_1_2.Status status) {
        this._status = status;
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
