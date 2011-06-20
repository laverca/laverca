/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package oasis.names.tc.SAML.v2_0.assertion;

/**
 * Class AssertionTypeChoiceItem.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AssertionTypeChoiceItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _statement.
     */
    private oasis.names.tc.SAML.v2_0.assertion.Statement _statement;

    /**
     * Field _authnStatement.
     */
    private oasis.names.tc.SAML.v2_0.assertion.AuthnStatement _authnStatement;

    /**
     * Field _authzDecisionStatement.
     */
    private oasis.names.tc.SAML.v2_0.assertion.AuthzDecisionStatement _authzDecisionStatement;

    /**
     * Field _attributeStatement.
     */
    private oasis.names.tc.SAML.v2_0.assertion.AttributeStatement _attributeStatement;


      //----------------/
     //- Constructors -/
    //----------------/

    public AssertionTypeChoiceItem() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'attributeStatement'.
     * 
     * @return the value of field 'AttributeStatement'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.AttributeStatement getAttributeStatement(
    ) {
        return this._attributeStatement;
    }

    /**
     * Returns the value of field 'authnStatement'.
     * 
     * @return the value of field 'AuthnStatement'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.AuthnStatement getAuthnStatement(
    ) {
        return this._authnStatement;
    }

    /**
     * Returns the value of field 'authzDecisionStatement'.
     * 
     * @return the value of field 'AuthzDecisionStatement'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.AuthzDecisionStatement getAuthzDecisionStatement(
    ) {
        return this._authzDecisionStatement;
    }

    /**
     * Returns the value of field 'statement'.
     * 
     * @return the value of field 'Statement'.
     */
    public oasis.names.tc.SAML.v2_0.assertion.Statement getStatement(
    ) {
        return this._statement;
    }

    /**
     * Sets the value of field 'attributeStatement'.
     * 
     * @param attributeStatement the value of field
     * 'attributeStatement'.
     */
    public void setAttributeStatement(
            final oasis.names.tc.SAML.v2_0.assertion.AttributeStatement attributeStatement) {
        this._attributeStatement = attributeStatement;
    }

    /**
     * Sets the value of field 'authnStatement'.
     * 
     * @param authnStatement the value of field 'authnStatement'.
     */
    public void setAuthnStatement(
            final oasis.names.tc.SAML.v2_0.assertion.AuthnStatement authnStatement) {
        this._authnStatement = authnStatement;
    }

    /**
     * Sets the value of field 'authzDecisionStatement'.
     * 
     * @param authzDecisionStatement the value of field
     * 'authzDecisionStatement'.
     */
    public void setAuthzDecisionStatement(
            final oasis.names.tc.SAML.v2_0.assertion.AuthzDecisionStatement authzDecisionStatement) {
        this._authzDecisionStatement = authzDecisionStatement;
    }

    /**
     * Sets the value of field 'statement'.
     * 
     * @param statement the value of field 'statement'.
     */
    public void setStatement(
            final oasis.names.tc.SAML.v2_0.assertion.Statement statement) {
        this._statement = statement;
    }

}
