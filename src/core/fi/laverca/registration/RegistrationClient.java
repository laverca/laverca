package fi.laverca.registration;

import java.io.IOException;
import java.util.List;

import fi.laverca.jaxb.mreg.MregRequestType;
import fi.laverca.jaxb.mreg.RegistrationInput;
import fi.laverca.jaxb.mss.MSSRegistrationReq;
import fi.laverca.jaxb.mss.MSSRegistrationResp;
import fi.laverca.mss.MssClient;
import fi.laverca.mss.MssConf;

/**
 * Client specifically for handling MSS_Registration operations
 * 
 */
public class RegistrationClient {

    private MssClient client;
   
    /**
     * Default constructor with {@link MssConf}
     * <p>The configuration should contain at least the following options:
     * 
     * <ul>
     * <li>{@link MssConf#REGISTRATION_URL} - where to send the MSS_RegistrationRequests
     * <li>{@link MssConf#AP_ID}            - what AP_ID to use in the requests
     * <li>{@link MssConf#KEYSTORE_FILE}    - keystore to use
     * <li>{@link MssConf#KEYSTORE_PWD}     - keystore password
     * </ul>
     * @param conf
     */
    public RegistrationClient(final MssConf conf) {
        this.client = new MssClient(conf);
    }
    
    /**
     * Send an MSS_RegistrationRequest (with MReg extension)
     * 
     * @param req MReg Request
     * @return parsed MReg response
     * @throws IOException
     */
    public MregResponse send(final MregRequest req) throws IOException {
        MSSRegistrationReq  _req  = req.toMSSReq(this.client);
        MSSRegistrationResp _resp = this.client.send(_req, req.context);
        return new MregResponse(_resp);
    }
    
    /**
     * Send a multi-operation MSS_RegistrationRequest (with MReg extension)
     * <p>Reads the target and AP_TransID from the first request in the list.
     * @param req MReg Request
     * @param multiReq Additional requests (only operation content is fetched from these - target is ignored
     * @return parsed MReg response
     * @throws IOException
     */
    public MregResponse send(final MregRequest req, 
                             final List<MregRequest> multiReq) throws IOException {
        
        MSSRegistrationReq _final = req.toMSSReq(this.client);
        
        int i = 100;
        for (MregRequest data : multiReq) {
            MSSRegistrationReq _data = data.toMSSReq(this.client);
            RegistrationInput _input = _data.getRegistrationInputs().get(0);
            _input.setInputId("_" + i++);
            
            _final.getRegistrationInputs().add(_input);
        }
        MSSRegistrationResp _resp = this.client.send(_final, req.context);

        return new MregResponse(_resp);
    }
    
    /**
     * Get the raw {@link MssClient}
     * @return {@link MssClient} instance
     */
    public MssClient getMssClient() {
        return this.client;
    }
    
}