/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig;

/**
 * Class SPKIDataTypeItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class SPKIDataTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _SPKISexp.
     */
    private byte[] _SPKISexp;

    /**
     * Field _anyObject.
     */
    private java.lang.Object _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public SPKIDataTypeItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'SPKISexp'.
     * 
     * @return the value of field 'SPKISexp'.
     */
    public byte[] getSPKISexp(
    ) {
        return this._SPKISexp;
    }

    /**
     * Sets the value of field 'anyObject'.
     * 
     * @param anyObject the value of field 'anyObject'.
     */
    public void setAnyObject(
            final java.lang.Object anyObject) {
        this._anyObject = anyObject;
    }

    /**
     * Sets the value of field 'SPKISexp'.
     * 
     * @param SPKISexp the value of field 'SPKISexp'.
     */
    public void setSPKISexp(
            final byte[] SPKISexp) {
        this._SPKISexp = SPKISexp;
    }

}
