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

package oasis.names.tc.SAML.v2_0.assertion.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import oasis.names.tc.SAML.v2_0.assertion.EncryptedElementType;

/**
 * Class EncryptedElementTypeDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class EncryptedElementTypeDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _elementDefinition.
     */
    private boolean _elementDefinition;

    /**
     * Field _nsPrefix.
     */
    private java.lang.String _nsPrefix;

    /**
     * Field _nsURI.
     */
    private java.lang.String _nsURI;

    /**
     * Field _xmlName.
     */
    private java.lang.String _xmlName;

    /**
     * Field _identity.
     */
    private org.exolab.castor.xml.XMLFieldDescriptor _identity;


      //----------------/
     //- Constructors -/
    //----------------/

    public EncryptedElementTypeDescriptor() {
        super();
        _nsURI = "urn:oasis:names:tc:SAML:2.0:assertion";
        _xmlName = "EncryptedElementType";
        _elementDefinition = false;

        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors

        //-- initialize element descriptors

        //-- _encryptedData
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(org.w3.www._2001._04.xmlenc.EncryptedData.class, "_encryptedData", "EncryptedData", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EncryptedElementType target = (EncryptedElementType) object;
                return target.getEncryptedData();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EncryptedElementType target = (EncryptedElementType) object;
                    target.setEncryptedData( (org.w3.www._2001._04.xmlenc.EncryptedData) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("org.w3.www._2001._04.xmlenc.EncryptedData");
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.w3.org/2001/04/xmlenc#");
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _encryptedData
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _encryptedKeyList
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(org.w3.www._2001._04.xmlenc.EncryptedKey.class, "_encryptedKeyList", "EncryptedKey", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                EncryptedElementType target = (EncryptedElementType) object;
                return target.getEncryptedKey();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    EncryptedElementType target = (EncryptedElementType) object;
                    target.addEncryptedKey( (org.w3.www._2001._04.xmlenc.EncryptedKey) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
                try {
                    EncryptedElementType target = (EncryptedElementType) object;
                    target.removeAllEncryptedKey();
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("list");
        desc.setComponentType("org.w3.www._2001._04.xmlenc.EncryptedKey");
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.w3.org/2001/04/xmlenc#");
        desc.setMultivalued(true);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _encryptedKeyList
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(0);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method getAccessMode.
     * 
     * @return the access mode specified for this class.
     */
    @Override()
    public org.exolab.castor.mapping.AccessMode getAccessMode(
    ) {
        return null;
    }

    /**
     * Method getIdentity.
     * 
     * @return the identity field, null if this class has no
     * identity.
     */
    @Override()
    public org.exolab.castor.mapping.FieldDescriptor getIdentity(
    ) {
        return _identity;
    }

    /**
     * Method getJavaClass.
     * 
     * @return the Java class represented by this descriptor.
     */
    @Override()
    public java.lang.Class getJavaClass(
    ) {
        return oasis.names.tc.SAML.v2_0.assertion.EncryptedElementType.class;
    }

    /**
     * Method getNameSpacePrefix.
     * 
     * @return the namespace prefix to use when marshaling as XML.
     */
    @Override()
    public java.lang.String getNameSpacePrefix(
    ) {
        return _nsPrefix;
    }

    /**
     * Method getNameSpaceURI.
     * 
     * @return the namespace URI used when marshaling and
     * unmarshaling as XML.
     */
    @Override()
    public java.lang.String getNameSpaceURI(
    ) {
        return _nsURI;
    }

    /**
     * Method getValidator.
     * 
     * @return a specific validator for the class described by this
     * ClassDescriptor.
     */
    @Override()
    public org.exolab.castor.xml.TypeValidator getValidator(
    ) {
        return this;
    }

    /**
     * Method getXMLName.
     * 
     * @return the XML Name for the Class being described.
     */
    @Override()
    public java.lang.String getXMLName(
    ) {
        return _xmlName;
    }

    /**
     * Method isElementDefinition.
     * 
     * @return true if XML schema definition of this Class is that
     * of a global
     * element or element with anonymous type definition.
     */
    public boolean isElementDefinition(
    ) {
        return _elementDefinition;
    }

}
