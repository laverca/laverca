/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2015 Laverca Project
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
import fi.laverca.mss.MssClient;

/**
 * ETSI specific AdditionalServices
 *
 */
public class EtsiAdditionalServices {
    
    /**
     * Create a basic ETSI Service element
     * @param uri AdditionalService URI
     * @return Created Service
     */
    public static AdditionalServiceType createService(final String uri) {
        final AdditionalServiceType s = MssClient.mssObjFact.createAdditionalServiceType();
        final MssURIType d = MssClient.mssObjFact.createMssURIType();
        d.setMssURI(uri);
        s.setDescription(d);
        return s;
    }
}
