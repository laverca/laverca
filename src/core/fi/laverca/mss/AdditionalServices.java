/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2021 Laverca Project
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

package fi.laverca.mss;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fi.laverca.jaxb.mss.AdditionalServiceType;
import fi.laverca.jaxb.mss.DataType;
import fi.laverca.jaxb.mss.MssURIType;
import fi.laverca.jaxb.mss.StatusDetailType;
import fi.laverca.jaxb.mssfi.ServiceResponses;
import fi.laverca.jaxb.mssfi.ServiceResponses.ServiceResponse;
import fi.methics.ts102204.ext.v1_0.AdditionalSignatureRequest;
import fi.methics.ts102204.ext.v1_0.BatchSignatureRequest;

public class AdditionalServices {

    private static final Log log = LogFactory.getLog(AdditionalServices.class);
    
    // MISC AdditionalService URIs
    public static final String POP_URI             = "http://www.methics.fi/KiuruMSSP/v3.0.0#POP";
    public static final String ROLE_URI            = "http://www.methics.fi/KiuruMSSP/v5.0.0#role";
    public static final String SIGNING_CERT_URI    = "http://www.methics.fi/KiuruMSSP/v5.0.0#signingCertificate";
    public static final String BATCH_SIGNATURE_URI = "http://www.methics.fi/KiuruMSSP/v5.0.0#batchsign";
    public static final String MULTIDOC_URI        = "http://www.methics.fi/KiuruMSSP/v5.0.0#docsign";
    
    /**
     * Create a basic AdditionalService element
     * @param uri AdditionalService URI
     * @return Created AdditionalService element
     */
    public static AdditionalServiceType createService(final String uri) {
        final AdditionalServiceType s = MssClient.mssObjFactory.createAdditionalServiceType();
        final MssURIType            d = MssClient.mssObjFactory.createMssURIType();
        d.setMssURI(uri);
        s.setDescription(d);
        return s;
    }
    
    /**
     * Create a Multi-doc AdditionalService element
     * @param docref Document/Hash reference
     * @parma dtbs   Data to be signed
     * @return Created AdditionalService element
     */
    public static AdditionalServiceType createMultidocService(final String docref, final DataType dtbs) {
        final AdditionalServiceType s = MssClient.mssObjFactory.createAdditionalServiceType();
        final MssURIType            d = MssClient.mssObjFactory.createMssURIType();
        d.setMssURI(MULTIDOC_URI);
        s.setDescription(d);
        
        AdditionalSignatureRequest req = new AdditionalSignatureRequest();
        req.setDocumentRef(docref);
        req.setDataToBeSigned(dtbs);
        s.getSessionIDsAndEventIDsAndNoSpamCodes().add(req);
        
        return s;
    }
    
    /**
     * Create a Multi-doc AdditionalService element
     * @param dtbd Data to be displayed
     * @parma dtbs Data to be signed
     * @return Created AdditionalService element
     */
    public static AdditionalServiceType createBatchSignatureService(final DataType dtbd, final DataType dtbs) {
        final AdditionalServiceType s = MssClient.mssObjFactory.createAdditionalServiceType();
        final MssURIType            d = MssClient.mssObjFactory.createMssURIType();
        d.setMssURI(BATCH_SIGNATURE_URI);
        s.setDescription(d);
        
        BatchSignatureRequest req = new BatchSignatureRequest();
        req.setDataToBeDisplayed(dtbd);
        req.setDataToBeSigned(dtbs);
        s.getSessionIDsAndEventIDsAndNoSpamCodes().add(req);
        
        return s;
    }
    
    /**
     * Read ServiceResponses from a StatusDetail element
     * @param sd StatusDetail
     * @return ServiceResponses or null
     */
    public static ServiceResponses readServiceResponses(final StatusDetailType sd)
    {
        if (sd == null) {
            return null;
        }
        
        final List<Object> le = sd.getAniesAndServiceResponsesAndReceiptRequestExtensions();
        ServiceResponses ret = null;
        for (final Object o : le) {
            if (!(o instanceof ServiceResponses)) {
                continue;
            }
            // Check that there is only one...
            if (ret != null) {
                log.warn("mss:StatusDetail contains multiple ServiceResponses instances. Returning the first.");
                break;
            }
            ret = (ServiceResponses) o;
        }
        return ret;
    }

    /**
     * Read a specific ServiceResponse from a StatusDetail element
     *
     * @param sd StatusDetail
     * @param serviceUri URI of the ServiceResponse
     * @return single ServiceResponse or null
     */
    public static ServiceResponse readServiceResponse(final StatusDetailType sd, 
                                                      final String   serviceUri)
    {
        if (sd == null) {
            return null;
        }
        final ServiceResponses sr = readServiceResponses(sd);
        // Note that this assumes only one service response for a given URI
        // It is an ok assumption in FiCom 2.0.
        for (final ServiceResponse sResp : sr.getServiceResponses()) {
            final MssURIType d = sResp.getDescription();
            final String   dUri = d.getMssURI();
            if (serviceUri.equals(dUri)) {
                return sResp;
            }
        }
        return null;
    }
    
}
