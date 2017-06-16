//
//  (c) Copyright 2003-2017 Methics Technologies Oy. All rights reserved. 
//

package fi.laverca;

import java.security.cert.X509Certificate;

import fi.laverca.mss.MssException;

public interface Signature {

    public byte[] getRawSignature();
    public String getBase64Signature();
    public String getSignerCn();
    public X509Certificate getSignerCert() throws MssException;
    
}
