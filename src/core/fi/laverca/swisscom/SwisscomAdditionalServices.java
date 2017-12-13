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

package fi.laverca.swisscom;

import javax.xml.bind.JAXBElement;

import fi.laverca.jaxb.mss.AdditionalServiceType;
import fi.laverca.mss.AdditionalServices;

/**
 * Swisscom specific AdditionalServices
 */
public class SwisscomAdditionalServices extends AdditionalServices {
    
    public static final String SUBSCRIBER_INFO_URI = "http://mid.swisscom.ch/as#subscriberInfo";
    public static final String USER_LANG_URI       = "http://mss.ficom.fi/TS102204/v1.0.0#userLang";

    /**
     * Create an additional service for selecting the user language
     * @param language User language (ISO 639-1)
     * @return AdditionalService element
     */
    public static AdditionalServiceType createUserLangService(final String language) {
        final AdditionalServiceType s = createService(USER_LANG_URI);
        final JAXBElement<String>   u = SwisscomClient.factory.createUserLang(language);
        s.getSessionIDsAndEventIDsAndNoSpamCodes().add(u);
        return s;
    }
    
}
