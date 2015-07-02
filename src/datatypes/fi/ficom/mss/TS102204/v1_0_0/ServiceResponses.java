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

package fi.ficom.mss.TS102204.v1_0_0;

/**
 * Class ServiceResponses.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ServiceResponses implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _serviceResponseList.
     */
    private java.util.List<fi.ficom.mss.TS102204.v1_0_0.ServiceResponse> _serviceResponseList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ServiceResponses() {
        super();
        this._serviceResponseList = new java.util.ArrayList<fi.ficom.mss.TS102204.v1_0_0.ServiceResponse>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vServiceResponse
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addServiceResponse(
            final fi.ficom.mss.TS102204.v1_0_0.ServiceResponse vServiceResponse)
    throws java.lang.IndexOutOfBoundsException {
        this._serviceResponseList.add(vServiceResponse);
    }

    /**
     * 
     * 
     * @param index
     * @param vServiceResponse
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addServiceResponse(
            final int index,
            final fi.ficom.mss.TS102204.v1_0_0.ServiceResponse vServiceResponse)
    throws java.lang.IndexOutOfBoundsException {
        this._serviceResponseList.add(index, vServiceResponse);
    }

    /**
     * Method enumerateServiceResponse.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends fi.ficom.mss.TS102204.v1_0_0.ServiceResponse> enumerateServiceResponse(
    ) {
        return java.util.Collections.enumeration(this._serviceResponseList);
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

        if (obj instanceof ServiceResponses) {

            ServiceResponses temp = (ServiceResponses)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._serviceResponseList != null) {
                if (temp._serviceResponseList == null) return false;
                if (this._serviceResponseList != temp._serviceResponseList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._serviceResponseList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._serviceResponseList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceResponseList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceResponseList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._serviceResponseList.equals(temp._serviceResponseList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceResponseList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceResponseList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceResponseList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceResponseList);
                    }
                }
            } else if (temp._serviceResponseList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Method getServiceResponse.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * fi.ficom.mss.TS102204.v1_0_0.ServiceResponse at the given
     * index
     */
    public fi.ficom.mss.TS102204.v1_0_0.ServiceResponse getServiceResponse(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._serviceResponseList.size()) {
            throw new IndexOutOfBoundsException("getServiceResponse: Index value '" + index + "' not in range [0.." + (this._serviceResponseList.size() - 1) + "]");
        }

        return (fi.ficom.mss.TS102204.v1_0_0.ServiceResponse) _serviceResponseList.get(index);
    }

    /**
     * Method getServiceResponse.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public fi.ficom.mss.TS102204.v1_0_0.ServiceResponse[] getServiceResponse(
    ) {
        fi.ficom.mss.TS102204.v1_0_0.ServiceResponse[] array = new fi.ficom.mss.TS102204.v1_0_0.ServiceResponse[0];
        return (fi.ficom.mss.TS102204.v1_0_0.ServiceResponse[]) this._serviceResponseList.toArray(array);
    }

    /**
     * Method getServiceResponseCount.
     * 
     * @return the size of this collection
     */
    public int getServiceResponseCount(
    ) {
        return this._serviceResponseList.size();
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
        if (_serviceResponseList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_serviceResponseList)) {
           result = 37 * result + _serviceResponseList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_serviceResponseList);
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
     * Method iterateServiceResponse.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends fi.ficom.mss.TS102204.v1_0_0.ServiceResponse> iterateServiceResponse(
    ) {
        return this._serviceResponseList.iterator();
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
     */
    public void removeAllServiceResponse(
    ) {
        this._serviceResponseList.clear();
    }

    /**
     * Method removeServiceResponse.
     * 
     * @param vServiceResponse
     * @return true if the object was removed from the collection.
     */
    public boolean removeServiceResponse(
            final fi.ficom.mss.TS102204.v1_0_0.ServiceResponse vServiceResponse) {
        boolean removed = _serviceResponseList.remove(vServiceResponse);
        return removed;
    }

    /**
     * Method removeServiceResponseAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public fi.ficom.mss.TS102204.v1_0_0.ServiceResponse removeServiceResponseAt(
            final int index) {
        java.lang.Object obj = this._serviceResponseList.remove(index);
        return (fi.ficom.mss.TS102204.v1_0_0.ServiceResponse) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vServiceResponse
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setServiceResponse(
            final int index,
            final fi.ficom.mss.TS102204.v1_0_0.ServiceResponse vServiceResponse)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._serviceResponseList.size()) {
            throw new IndexOutOfBoundsException("setServiceResponse: Index value '" + index + "' not in range [0.." + (this._serviceResponseList.size() - 1) + "]");
        }

        this._serviceResponseList.set(index, vServiceResponse);
    }

    /**
     * 
     * 
     * @param vServiceResponseArray
     */
    public void setServiceResponse(
            final fi.ficom.mss.TS102204.v1_0_0.ServiceResponse[] vServiceResponseArray) {
        //-- copy array
        _serviceResponseList.clear();

        for (int i = 0; i < vServiceResponseArray.length; i++) {
                this._serviceResponseList.add(vServiceResponseArray[i]);
        }
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
     * fi.ficom.mss.TS102204.v1_0_0.ServiceResponses
     */
    public static fi.ficom.mss.TS102204.v1_0_0.ServiceResponses unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (fi.ficom.mss.TS102204.v1_0_0.ServiceResponses) org.exolab.castor.xml.Unmarshaller.unmarshal(fi.ficom.mss.TS102204.v1_0_0.ServiceResponses.class, reader);
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
