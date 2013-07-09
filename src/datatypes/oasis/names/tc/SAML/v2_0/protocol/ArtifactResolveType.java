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

package oasis.names.tc.SAML.v2_0.protocol;

/**
 * Class ArtifactResolveType.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public abstract class ArtifactResolveType extends oasis.names.tc.SAML.v2_0.protocol.RequestAbstractType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _artifact.
     */
    private java.lang.String _artifact;


      //----------------/
     //- Constructors -/
    //----------------/

    public ArtifactResolveType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

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

        if (obj instanceof ArtifactResolveType) {

            ArtifactResolveType temp = (ArtifactResolveType)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._artifact != null) {
                if (temp._artifact == null) return false;
                if (this._artifact != temp._artifact) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._artifact);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._artifact);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._artifact); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._artifact); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._artifact.equals(temp._artifact)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._artifact);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._artifact);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._artifact);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._artifact);
                    }
                }
            } else if (temp._artifact != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'artifact'.
     * 
     * @return the value of field 'Artifact'.
     */
    public java.lang.String getArtifact(
    ) {
        return this._artifact;
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
        if (_artifact != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_artifact)) {
           result = 37 * result + _artifact.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_artifact);
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
     * Sets the value of field 'artifact'.
     * 
     * @param artifact the value of field 'artifact'.
     */
    public void setArtifact(
            final java.lang.String artifact) {
        this._artifact = artifact;
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
