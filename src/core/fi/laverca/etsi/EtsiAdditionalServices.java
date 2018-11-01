/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2018 Laverca Project
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

package fi.laverca.etsi;

import fi.laverca.jaxb.mss.AdditionalServiceType;
import fi.laverca.jaxb.mss.MssURIType;
import fi.laverca.mss.AdditionalServices;
import fi.laverca.mss.MssClient;

/**
 * ETSI specific AdditionalServices
 *
 */
public class EtsiAdditionalServices extends AdditionalServices {
    
    public static final String URI_VALIDATE  = "http://uri.etsi.org/TS102204/v1.1.2#validate";
    public static final String URI_TIMESTAMP = "http://uri.etsi.org/TS102204/v1.1.2#timestamp";
    public static final String URI_ARCHIVE   = "http://uri.etsi.org/TS102204/v1.1.2#archive";
    
    /**
     * Creates an AdditionalService for validation service
     * @return ETSI Validation AdditionalService
     */
    public static AdditionalServiceType createValidateService() {
        return AdditionalServices.createService(URI_VALIDATE);
    }
    
    /**
     * Creates an AdditionalService for timestamp service
     * @return ETSI Timestamp AdditionalService
     */
    public static AdditionalServiceType createTimestampService() {
        return AdditionalServices.createService(URI_TIMESTAMP);
    }
    
    /**
     * Creates an AdditionalService for archive service
     * @return ETSI Archive AdditionalService
     */
    public static AdditionalServiceType createArchiveService() {
        return AdditionalServices.createService(URI_ARCHIVE);
    }
    
    /**
     * Create a basic ETSI Service element
     * @param uri AdditionalService URI
     * @return Created AdditionalService element
     * @deprecated Use {@link AdditionalServices#createService(String)} instead
     */
    @Deprecated
    public static AdditionalServiceType createService(final String uri) {
        final AdditionalServiceType s = MssClient.mssObjFactory.createAdditionalServiceType();
        final MssURIType            d = MssClient.mssObjFactory.createMssURIType();
        d.setMssURI(uri);
        s.setDescription(d);
        return s;
    }
}
