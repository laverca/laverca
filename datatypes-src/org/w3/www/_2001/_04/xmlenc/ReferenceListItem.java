/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2001._04.xmlenc;

/**
 * Class ReferenceListItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ReferenceListItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _dataReference.
     */
    private org.w3.www._2001._04.xmlenc.DataReference _dataReference;

    /**
     * Field _keyReference.
     */
    private org.w3.www._2001._04.xmlenc.KeyReference _keyReference;


      //----------------/
     //- Constructors -/
    //----------------/

    public ReferenceListItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'dataReference'.
     * 
     * @return the value of field 'DataReference'.
     */
    public org.w3.www._2001._04.xmlenc.DataReference getDataReference(
    ) {
        return this._dataReference;
    }

    /**
     * Returns the value of field 'keyReference'.
     * 
     * @return the value of field 'KeyReference'.
     */
    public org.w3.www._2001._04.xmlenc.KeyReference getKeyReference(
    ) {
        return this._keyReference;
    }

    /**
     * Sets the value of field 'dataReference'.
     * 
     * @param dataReference the value of field 'dataReference'.
     */
    public void setDataReference(
            final org.w3.www._2001._04.xmlenc.DataReference dataReference) {
        this._dataReference = dataReference;
        this._choiceValue = dataReference;
    }

    /**
     * Sets the value of field 'keyReference'.
     * 
     * @param keyReference the value of field 'keyReference'.
     */
    public void setKeyReference(
            final org.w3.www._2001._04.xmlenc.KeyReference keyReference) {
        this._keyReference = keyReference;
        this._choiceValue = keyReference;
    }

}
