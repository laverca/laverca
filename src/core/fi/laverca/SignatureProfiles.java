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

package fi.laverca;

/**
 * Static set of Signature Profile URIs
 */
public class SignatureProfiles {

    public static final String FICOM_AUTHENTICATION    = "http://mss.ficom.fi/TS102206/v1.0.0/authentication-profile.xml";
    public static final String FICOM_ANONYMOUS         = "http://mss.ficom.fi/TS102206/v1.0.0/anonymous-profile.xml";
    public static final String FICOM_SIGNATURE         = "http://mss.ficom.fi/TS102206/v1.0.0/signature-profile.xml";
    public static final String FICOM_DIGESTIVE         = "http://mss.ficom.fi/TS102206/v1.0.0/digestive-signature-profile.xml";
    public static final String FICOM_CONSENT           = "http://mss.ficom.fi/TS102206/v1.0.0/consent-profile.xml";
    
    public static final String SWISSCOM_AUTHENTICATION = "http://mid.swisscom.ch/MID/v1/AuthProfile1";
    public static final String SWISSCOM_APP_LOA4       = "http://mid.swisscom.ch/Device-LoA4";
    public static final String SWISSCOM_SIM_LOA4       = "http://mid.swisscom.ch/STK-LoA4";
    public static final String SWISSCOM_ANY_LOA4       = "http://mid.swisscom.ch/Any-LoA4";
    
    @Deprecated
    public static final String ALAUDA_SIGNATURE        = "http://alauda.mobi/digitalSignature";
    
    // See RFC 5280
    public static final String ALAUDA_AUTHENTICATION   = "http://alauda.mobi/digitalSignature";
    public static final String ALAUDA_SIGNING          = "http://alauda.mobi/nonRepudiation";
    
    public static final String MOBILECONNECT_LOA2      = "http://uri.gsma.com/mobileconnect/LoA2";
    public static final String MOBILECONNECT_LOA3      = "http://uri.gsma.com/mobileconnect/LoA3";
    
}
