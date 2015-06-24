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

    Java 7
        Java 6 can also be used by building the source code with JDK 6. 

    JDK SE
        http://www.oracle.com/technetwork/java/javase/downloads/index.html

    Laverca requires the libraries found under libs directory in the release root.


  Examples
  
    Building of examples also require the following.
  
    Ant (Tested with v1.8.1)

    Download Ant
    http://ant.apache.org/bindownload.cgi

    Ant manual
    http://ant.apache.org/manual/index.html


## Example

    Laverca ships with example implementations. The examples are included 
    in the examples directory.


  Building

    Compiled examples are shipped for your convenience. Build and distribute the 
    examples jar over the shipped one yourself by running the following command:

    $ ant dist


  Usage
    
    Most samples require a GUI. Run the examples by using the scripts in 
    examples/script directory. For example:
    
    $ sh examples/script/SignText.sh
     OR
    $ examples/script/SignText.bat
    

  Configuration

    Examples configuration file is located in examples/conf/.

    By default the examples are configured to use a demo AE server which is 
    hosted by Methics Ltd. Find additional information about the demo service
    in: http://demo.methics.fi/demo-environment/application-providers/

        
## Features

    1. Supports synchronic and asynchronic client-server.
         (FiComClient for C/S asynch, EtsiClient for synchronic)
    2. Strong mutual identification and encryption between all message routing entities.
         (JvmSsl)
    3. Strong mutual identification and encryption between AP and AE including a password (AP_PWD).
         (JvmSsl)
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

