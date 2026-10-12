[![Build Status](https://api.travis-ci.org/laverca/laverca.png)](https://travis-ci.org/laverca/laverca)

Laverca is an open-source Java API for requesting signatures using mobile 
signature services.

Mobile signature service (MSS) allows users to sign messages securely using 
mobile devices. For instance, Laverca API can be used for document signing
or strong user authentication in a login system.

Laverca implements the ETSI TS 102 204 SOAP interface for application providers 
(APs). It means that Laverca can communicate with ETSI 204 conforming MSS 
systems. Laverca supports synchronous and asynchronous signature requests. 
Status of an asynchronous signature request can be polled.

In addition to the ETSI features, Laverca includes the FiCom 2.0 features 
specified in Finland.


## Requirements

Laverca API

    Java 21+

    JDK SE
        http://www.oracle.com/technetwork/java/javase/downloads/index.html

    Laverca requires the libraries found under the libs directory in the release root:

      SOAP        axis2-kernel 2.0.1, axiom-api, axiom-impl and
                  axiom-legacy-attachments 2.0.0, neethi 3.2.4, wsdl4j 1.6.3,
                  xmlschema-core 2.3.3, commons-logging 1.2, commons-io 2.19.0
      HTTP        httpclient5 5.6.4, httpcore5 and httpcore5-h2 5.4.4,
                  slf4j-api 2.0.16
      Crypto      bcprov-jdk18on 1.85.2, bcpkix-jdk18on and bcutil-jdk18on 1.85
      JAXB        jakarta.xml.bind-api 4.0.5, jaxb-runtime and jaxb-core 4.0.9,
                  istack-commons-runtime 4.2.0, jakarta.activation-api 2.1.4
                  (in libs/jaxb-ri-409; the build copies these to the release's libs)

    The other jars in libs/jaxb-ri-409 (jaxb-xjc and its helpers) are needed only to
    build Laverca, for generating the JAXB datatypes. Java 21 provides the XML APIs
    (no xml-apis jar). All libraries should be possible to update to newer versions of
    the same major release number.

    With Maven, pom.xml builds the core library (fi.laverca:laverca-core) and lists the
    same runtime dependencies.

    Laverca 4 no longer uses Axis 1.4. SOAP faults are thrown as
    fi.laverca.LavercaSoapFaultException (an IOException); use
    ErrorCodes.<CODE>.is(fault) to check the ETSI status code.
    An Axis2 client engine configuration can be supplied as
    laverca-axis2-client.xml on the classpath; by default a built-in one is used.

    Source branch "preJAXB" is possible to use on Java6.
    It is our old version.

    Source branch "preJava11" is before Java11+ work

    Laverca does not use Servlet API (callbacks), that layer the user must implement.

Examples
  
    Building the examples requires the following tools:
  
    Ant (Tested with v1.8.1, and v1.9.6)

    Download Ant
    http://ant.apache.org/bindownload.cgi

    Ant manual
    http://ant.apache.org/manual/index.html


## Examples

    Laverca ships with a couple of example implementations. The examples are included 
    in the examples directory.


Building

    Compiled examples are shipped for your convenience. Build and distribute the 
    examples jar over the shipped one yourself by running the following command:

    $ ant dist


Usage
    
    Run the examples by using the scripts in the examples/script directory:
    
    $ sh examples/bin/etsi-sign.sh
    

Configuration

    Example configuration file is located in examples/conf/.

    By default the examples are configured to use a demo MSSP server which is 
    hosted by Methics Ltd. Find additional information about the demo service
    in: http://demo.methics.fi/demo-environment/application-providers/


## Features

    1. Synchronous and asynchronous client-server communication.
    2. Strong mutual identification and encryption between all message routing entities.
         (JvmSsl, MssClient.setSSLSocketFactory(..))
    3. Strong mutual identification and encryption between AP and AE including a password (AP_PWD).
         (JvmSsl, MssClient.setSSLSocketFactory(..))
    4. Supported message formats are: MSS_SignatureReq, MSS_SignatureResp, MSS_StatusReq, MSS_StatusResp, MSS_ReceiptReq and MSS_ReceiptResp.
         (FiComClient, EtsiClient)
    5. Supported character maps in service requests are UTF-8, GSM and UCS2.
         (FiComCharsetLimiter)
    6. Supported character maps in client device are GSM 03.38 and UCS2.
         (FiComCharsetLimiter)
    7. Provides all 6 different signature provides defined in FiCom 2.0.
         (FiComClient, FiComSignatureProfiles)
    8. The signature profiles are used for choosing a specified service.
         (FiComClient, FiComSignatureProfiles)
    9. The user can deny usage of any signature profile on the client device.
         (FiComErrorCodes)
    10. Supports all of the FiCom 2.0 additional services.
         (FiComClient)
    11. The format of the digital signature is base64-encoding and PKCS#7 or PKCS#1 with the users certificate.
         (FiComResponse)


## Copyright and License

See the [LICENSE](https://github.com/laverca/laverca/blob/master/LICENSE) file
