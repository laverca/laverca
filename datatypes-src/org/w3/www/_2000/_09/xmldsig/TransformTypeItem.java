/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig;

/**
 * Class TransformTypeItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class TransformTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _anyObject.
     */
    private java.lang.Object _anyObject;

    /**
     * Field _XPath.
     */
    private java.lang.String _XPath;


      //----------------/
     //- Constructors -/
    //----------------/

    public TransformTypeItem() {
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
     * Returns the value of field 'XPath'.
     * 
     * @return the value of field 'XPath'.
     */
    public java.lang.String getXPath(
    ) {
        return this._XPath;
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
     * Sets the value of field 'XPath'.
     * 
     * @param XPath the value of field 'XPath'.
     */
    public void setXPath(
            final java.lang.String XPath) {
        this._XPath = XPath;
        this._choiceValue = XPath;
    }

}
