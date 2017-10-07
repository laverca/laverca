package fi.laverca.registration;

import java.util.ArrayList;
import java.util.List;

import fi.laverca.jaxb.mreg.EntityType;
import fi.laverca.jaxb.mreg.MregRequestType;
import fi.laverca.jaxb.mreg.NameValueType;
import fi.laverca.jaxb.mreg.ProvisioningOperation;
import fi.laverca.jaxb.mreg.RegistrationInput;
import fi.laverca.jaxb.mreg.SimCardType;
import fi.laverca.jaxb.mreg.TargetType;
import fi.laverca.jaxb.mss.MSSRegistrationReq;
import fi.laverca.jaxb.mss.MobileUserType;
import fi.laverca.mss.MssClient;
import fi.laverca.util.LavercaContext;

/**
 * A wrapper class for an MSS_RegistrationReq with MReg extension
 * <p>
 * 
 * Example use:
 * <pre>
 * MregRequest _req = new MregRequest();
 * _req.setOperation("GetMobileUser");
 * _req.setTargetMsisdn("35847001001");
 * _req.addParam("Certs", "true");
 * 
 * MSSRegistrationReq regReq = _req.toMSSReq(mssClient);
 * </pre>
 */
public class MregRequest {

    public enum Target {
        MOBILEUSER,
        SIMCARD,
        ENTITY
    }
    
    protected LavercaContext context;
    protected Target         targetType = Target.MOBILEUSER;
    protected String         apTransId = "A" + System.currentTimeMillis();
    protected String         operation;
    protected String         namespace;
    
    protected List<MregParam> params = new ArrayList<>();
    
    // Targets
    protected String msisdn;
    protected String imsi;
    protected String iccid;
    protected String apId;

    /**
     * Create a new MReg request wrapper
     * 
     * @param operation Name of the operation
     */
    public MregRequest(final String operation) {
        this.operation = operation;
    }

    /**
     * Construct a raw {@link MSSRegistrationReq} object to be sent via {@link MssClient}
     * @param client MSS Client reference used for building the {@link MSSRegistrationReq}
     * @return a {@link MSSRegistrationReq} built from this {@link MregRequest}
     */
    public MSSRegistrationReq toMSSReq(final MssClient client) {
        
        MSSRegistrationReq req = client.createRegistrationReq(this.apTransId);
        RegistrationInput input = new RegistrationInput();
        input.setTarget(this.buildTarget());
        
        MregRequestType       mreq = new MregRequestType();
        ProvisioningOperation prop = new ProvisioningOperation();
        for (MregParam param : this.params) {
            prop.getParameters().add(param.getType());
        }
        prop.setName(this.operation);
        prop.setNameSpace(this.namespace);
        mreq.setProvisioningOperation(prop);
        input.getMregRequests().add(mreq);
        input.setInputId("_1");
        
        req.setRegistrationInput(input);
        
        return req;
    }    
    
    /**
     * If the target is a Mobile User, set the IMSI identifying it
     * @param apId
     */    
    public void setTargetMsisdn(final String msisdn) {
        this.targetType = Target.MOBILEUSER;
        this.msisdn = msisdn;
    }

    /**
     * If the target is a SIM Card, set the IMSI identifying it
     * @param apId
     */
    public void setTargetImsi(final String imsi) {
        this.targetType = Target.SIMCARD;
        this.imsi = imsi;
    }
    
    /**
     * If the target is a SIM Card, set the ICCID identifying it
     * @param apId
     */
    public void setTargetIccid(final String iccid) {
        this.targetType = Target.SIMCARD;
        this.iccid = iccid;
    }
    
    /**
     * If the target is an AP, set the AP_ID identifying it
     * @param apId
     */
    public void setTargetApId(final String apId) {
        this.targetType = Target.ENTITY;
        this.apId = apId;
    }
    
    /**
     * Force the target type to a specific value.
     * <p>Not recommended, as the {@link #setTargetMsisdn(String)}, etc methods already set this.
     * @param targetType {@link Target} specifying what kind of a target this operation is modifying/querying.
     *                   null values ignored.
     */
    public void setTargetType(final Target targetType) {
        if (targetType != null) {
            this.targetType = targetType;
        }
    }
    
    /**
     * Set the operation name. (e.g. ListMobileUsers)
     * <p>To get a list of supported operations, contact your MSSP vendor
     * @param operation
     */
    public void setOperation(final String operation) {
        this.operation = operation;
    }

    /**
     * Set the AP Transaction ID
     * <p>If this is not called, a default is used.
     * The default is constructed like this:
     * <pre>
     * "A" + System.currentTimeMillis();
     * </pre>
     * @param apTransId new AP Transaction ID
     */
    public void setApTransId(final String apTransId) {
        this.apTransId = apTransId;
    }
    
    /**
     * Set a namespace for this operation.
     * <p>This can often be left empty
     * @param namespace
     */
    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }
    
    /**
     * Add a new request parameter
     * 
     * @param name  Name of the parameter
     * @param value Value of the parameter
     */
    public void addParam(final String name, final String value) {
        this.addParam(name, value, null, null);
    }
    
    /**
     * Add a new request parameter
     * 
     * @param name     Name of the parameter
     * @param value    Value of the parameter
     * @param mimeType MimeType 
     * @param encoding Encoding (e.g. UTF-8 or BASE64)
     */
    public void addParam(final String name,
                         final String value,
                         final String mimeType,
                         final String encoding) {
        MregParam param = new MregParam(name, value, mimeType, encoding);
        this.addParam(param);
    }
    
    /**
     * Add a new request parameter
     * @param param raw {@link NameValueType} object
     */
    public void addParam(final MregParam param) {
        this.params.add(param);
    }
    
    /**
     * Build the XML TargetType element from the data we have
     * @return {@link TargetType} element
     */
    protected TargetType buildTarget() {
        TargetType target = new TargetType();
        
        switch (this.targetType) {
            case MOBILEUSER:
                MobileUserType mu = new MobileUserType();
                mu.setMSISDN(this.msisdn);
                target.setMobileUser(mu);
                break;
            case SIMCARD:
                SimCardType sc = new SimCardType();
                sc.setIMSI(this.imsi);
                sc.setICCID(this.iccid);
                sc.setMSISDN(this.msisdn);
                target.setSimCard(sc);
                break;
            case ENTITY:
                EntityType ent = new EntityType();
                ent.setApID(this.apId);
                target.setEntity(ent);
                break;
            default:
                break;
        }

        return target;
    }

}
