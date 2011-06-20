/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig;

/**
 * Class X509DataTypeItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class X509DataTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _x509DataTypeChoice.
     */
    private org.w3.www._2000._09.xmldsig.X509DataTypeChoice _x509DataTypeChoice;


      //----------------/
     //- Constructors -/
    //----------------/

    public X509DataTypeItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'x509DataTypeChoice'.
     * 
     * @return the value of field 'X509DataTypeChoice'.
     */
    public org.w3.www._2000._09.xmldsig.X509DataTypeChoice getX509DataTypeChoice(
    ) {
        return this._x509DataTypeChoice;
    }

    /**
     * Sets the value of field 'x509DataTypeChoice'.
     * 
     * @param x509DataTypeChoice the value of field
     * 'x509DataTypeChoice'.
     */
    public void setX509DataTypeChoice(
            final org.w3.www._2000._09.xmldsig.X509DataTypeChoice x509DataTypeChoice) {
        this._x509DataTypeChoice = x509DataTypeChoice;
    }

}
