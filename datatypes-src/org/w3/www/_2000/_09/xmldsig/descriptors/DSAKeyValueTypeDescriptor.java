/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package org.w3.www._2000._09.xmldsig.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.w3.www._2000._09.xmldsig.DSAKeyValueType;

/**
 * Class DSAKeyValueTypeDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class DSAKeyValueTypeDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


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

    public DSAKeyValueTypeDescriptor() {
        super();
        _nsURI = "http://www.w3.org/2000/09/xmldsig#";
        _xmlName = "DSAKeyValueType";
        _elementDefinition = false;

        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors

        //-- initialize element descriptors

        //-- _DSAKeyValueTypeSequence
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence.class, "_DSAKeyValueTypeSequence", "-error-if-this-is-used-", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                DSAKeyValueType target = (DSAKeyValueType) object;
                return target.getDSAKeyValueTypeSequence();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    DSAKeyValueType target = (DSAKeyValueType) object;
                    target.setDSAKeyValueTypeSequence( (org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence();
            }
        };
        desc.setSchemaType("org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence");
        desc.setHandler(handler);
        desc.setContainer(true);
        desc.setClassDescriptor(new org.w3.www._2000._09.xmldsig.descriptors.DSAKeyValueTypeSequenceDescriptor());
        desc.setNameSpaceURI("http://www.w3.org/2000/09/xmldsig#");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _DSAKeyValueTypeSequence
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _g
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(byte[].class, "_g", "G", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                DSAKeyValueType target = (DSAKeyValueType) object;
                return target.getG();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    DSAKeyValueType target = (DSAKeyValueType) object;
                    target.setG( (byte[]) value);
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
        desc.setSchemaType("base64Binary");
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.w3.org/2000/09/xmldsig#");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _g
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _y
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(byte[].class, "_y", "Y", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                DSAKeyValueType target = (DSAKeyValueType) object;
                return target.getY();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    DSAKeyValueType target = (DSAKeyValueType) object;
                    target.setY( (byte[]) value);
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
        desc.setSchemaType("base64Binary");
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.w3.org/2000/09/xmldsig#");
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _y
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _j
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(byte[].class, "_j", "J", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                DSAKeyValueType target = (DSAKeyValueType) object;
                return target.getJ();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    DSAKeyValueType target = (DSAKeyValueType) object;
                    target.setJ( (byte[]) value);
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
        desc.setSchemaType("base64Binary");
        desc.setHandler(handler);
        desc.setNameSpaceURI("http://www.w3.org/2000/09/xmldsig#");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _j
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _DSAKeyValueTypeSequence2
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2.class, "_DSAKeyValueTypeSequence2", "-error-if-this-is-used-", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                DSAKeyValueType target = (DSAKeyValueType) object;
                return target.getDSAKeyValueTypeSequence2();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    DSAKeyValueType target = (DSAKeyValueType) object;
                    target.setDSAKeyValueTypeSequence2( (org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2();
            }
        };
        desc.setSchemaType("org.w3.www._2000._09.xmldsig.DSAKeyValueTypeSequence2");
        desc.setHandler(handler);
        desc.setContainer(true);
        desc.setClassDescriptor(new org.w3.www._2000._09.xmldsig.descriptors.DSAKeyValueTypeSequence2Descriptor());
        desc.setNameSpaceURI("http://www.w3.org/2000/09/xmldsig#");
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _DSAKeyValueTypeSequence2
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
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
        return org.w3.www._2000._09.xmldsig.DSAKeyValueType.class;
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
