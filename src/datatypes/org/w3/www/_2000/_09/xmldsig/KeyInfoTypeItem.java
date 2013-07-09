/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2013 Laverca Project
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
 * Class KeyInfoTypeItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class KeyInfoTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _keyName.
     */
    private java.lang.String _keyName;

    /**
     * Field _keyValue.
     */
    private org.w3.www._2000._09.xmldsig.KeyValue _keyValue;

    /**
     * Field _retrievalMethod.
     */
    private org.w3.www._2000._09.xmldsig.RetrievalMethod _retrievalMethod;

    /**
     * Field _x509Data.
     */
    private org.w3.www._2000._09.xmldsig.X509Data _x509Data;

    /**
     * Field _PGPData.
     */
    private org.w3.www._2000._09.xmldsig.PGPData _PGPData;

    /**
     * Field _SPKIData.
     */
    private org.w3.www._2000._09.xmldsig.SPKIData _SPKIData;

    /**
     * Field _mgmtData.
     */
    private java.lang.String _mgmtData;

    /**
     * Field _anyObject.
     */
    private java.lang.Object _anyObject;


      //----------------/
     //- Constructors -/
    //----------------/

    public KeyInfoTypeItem() {
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
     * Returns the value of field 'keyName'.
     * 
     * @return the value of field 'KeyName'.
     */
    public java.lang.String getKeyName(
    ) {
        return this._keyName;
    }

    /**
     * Returns the value of field 'keyValue'.
     * 
     * @return the value of field 'KeyValue'.
     */
    public org.w3.www._2000._09.xmldsig.KeyValue getKeyValue(
    ) {
        return this._keyValue;
    }

    /**
     * Returns the value of field 'mgmtData'.
     * 
     * @return the value of field 'MgmtData'.
     */
    public java.lang.String getMgmtData(
    ) {
        return this._mgmtData;
    }

    /**
     * Returns the value of field 'PGPData'.
     * 
     * @return the value of field 'PGPData'.
     */
    public org.w3.www._2000._09.xmldsig.PGPData getPGPData(
    ) {
        return this._PGPData;
    }

    /**
     * Returns the value of field 'retrievalMethod'.
     * 
     * @return the value of field 'RetrievalMethod'.
     */
    public org.w3.www._2000._09.xmldsig.RetrievalMethod getRetrievalMethod(
    ) {
        return this._retrievalMethod;
    }

    /**
     * Returns the value of field 'SPKIData'.
     * 
     * @return the value of field 'SPKIData'.
     */
    public org.w3.www._2000._09.xmldsig.SPKIData getSPKIData(
    ) {
        return this._SPKIData;
    }

    /**
     * Returns the value of field 'x509Data'.
     * 
     * @return the value of field 'X509Data'.
     */
    public org.w3.www._2000._09.xmldsig.X509Data getX509Data(
    ) {
        return this._x509Data;
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
     * Sets the value of field 'keyName'.
     * 
     * @param keyName the value of field 'keyName'.
     */
    public void setKeyName(
            final java.lang.String keyName) {
        this._keyName = keyName;
        this._choiceValue = keyName;
    }

    /**
     * Sets the value of field 'keyValue'.
     * 
     * @param keyValue the value of field 'keyValue'.
     */
    public void setKeyValue(
            final org.w3.www._2000._09.xmldsig.KeyValue keyValue) {
        this._keyValue = keyValue;
        this._choiceValue = keyValue;
    }

    /**
     * Sets the value of field 'mgmtData'.
     * 
     * @param mgmtData the value of field 'mgmtData'.
     */
    public void setMgmtData(
            final java.lang.String mgmtData) {
        this._mgmtData = mgmtData;
        this._choiceValue = mgmtData;
    }

    /**
     * Sets the value of field 'PGPData'.
     * 
     * @param PGPData the value of field 'PGPData'.
     */
    public void setPGPData(
            final org.w3.www._2000._09.xmldsig.PGPData PGPData) {
        this._PGPData = PGPData;
        this._choiceValue = PGPData;
    }

    /**
     * Sets the value of field 'retrievalMethod'.
     * 
     * @param retrievalMethod the value of field 'retrievalMethod'.
     */
    public void setRetrievalMethod(
            final org.w3.www._2000._09.xmldsig.RetrievalMethod retrievalMethod) {
        this._retrievalMethod = retrievalMethod;
        this._choiceValue = retrievalMethod;
    }

    /**
     * Sets the value of field 'SPKIData'.
     * 
     * @param SPKIData the value of field 'SPKIData'.
     */
    public void setSPKIData(
            final org.w3.www._2000._09.xmldsig.SPKIData SPKIData) {
        this._SPKIData = SPKIData;
        this._choiceValue = SPKIData;
    }

    /**
     * Sets the value of field 'x509Data'.
     * 
     * @param x509Data the value of field 'x509Data'.
     */
    public void setX509Data(
            final org.w3.www._2000._09.xmldsig.X509Data x509Data) {
        this._x509Data = x509Data;
        this._choiceValue = x509Data;
    }

}
