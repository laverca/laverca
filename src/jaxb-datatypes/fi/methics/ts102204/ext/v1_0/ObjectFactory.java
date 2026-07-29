
package fi.methics.ts102204.ext.v1_0;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fi.methics.ts102204.ext.v1_0 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fi.methics.ts102204.ext.v1_0
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AdditionalSignatureRequest }
     * 
     */
    public AdditionalSignatureRequest createAdditionalSignatureRequest() {
        return new AdditionalSignatureRequest();
    }

    /**
     * Create an instance of {@link AdditionalSignatureResponse }
     * 
     */
    public AdditionalSignatureResponse createAdditionalSignatureResponse() {
        return new AdditionalSignatureResponse();
    }

    /**
     * Create an instance of {@link BatchSignatureRequest }
     * 
     */
    public BatchSignatureRequest createBatchSignatureRequest() {
        return new BatchSignatureRequest();
    }

    /**
     * Create an instance of {@link BatchSignatureResponse }
     * 
     */
    public BatchSignatureResponse createBatchSignatureResponse() {
        return new BatchSignatureResponse();
    }

}
