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

package fi.laverca.registration;

import java.util.ArrayList;
import java.util.List;

import fi.laverca.jaxb.mreg.EntityType;
import fi.laverca.jaxb.mreg.EntityUserType;
import fi.laverca.jaxb.mreg.MregRequestType;
import fi.laverca.jaxb.mreg.OperationInputType;
import fi.laverca.jaxb.mreg.ProvisioningOperation;
import fi.laverca.jaxb.mreg.RegistrationInput;
import fi.laverca.jaxb.mreg.SimCardType;
import fi.laverca.jaxb.mreg.TargetType;
import fi.laverca.jaxb.mreg.WirelessOperation;
import fi.laverca.jaxb.mss.MSSRegistrationReq;
import fi.laverca.jaxb.mss.MeshMemberType;
import fi.laverca.jaxb.mss.MobileUserType;
import fi.laverca.jaxb.wsssecext.Security;
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

    private static final String SIMCARD_OP    = "simcard";
    
    public static enum Target {
        MOBILEUSER,
        SIMCARD,
        ENTITY,
        ENTITYUSER
    }
    
    public static enum TargetName {
        MSISDN,
        IMSI,
        ICCID,
        APID,
        EUID,
        CUSTOMERID,
        SPID,
        NASID,
        MSSPURI,
        CAURI,
        USERID
    }
    
    // WSSE security headers
    public Security security;
    public LavercaContext context;
    
    protected Target  targetType = Target.MOBILEUSER;
    protected String  apTransId = "A" + System.currentTimeMillis();
    protected String  operation;
    protected String  namespace;
    protected boolean wireless  = false;

    protected List<MregParam> params = new ArrayList<>();
    
    // Targets
    protected String msisdn;
    protected String imsi;
    protected String iccid;
    protected String apId;
    
    protected String entityUserId;
    protected String customerId;
    protected String spId;
    protected String nasId;
    protected String msspUri;
    protected String caUri;
    protected String userIdentifier;
    
    // These override any client specific AP ID and AP PWD when sending this req. 
    private String senderApId;
    private String senderApPwd;
    
    
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
        
        MregRequestType    mreq = new MregRequestType();
        OperationInputType prop;
        if (this.wireless) {
            prop = new WirelessOperation();
            mreq.setWirelessOperation((WirelessOperation)prop);
        } else {
            prop = new ProvisioningOperation();
            mreq.setProvisioningOperation((ProvisioningOperation)prop);
        }
        
        for (MregParam param : this.params) {
            prop.getParameters().add(param.getType());
        }
        prop.setName(this.operation);
        prop.setNameSpace(this.namespace);
        input.getMregRequests().add(mreq);
        input.setInputId("_1");
        
        req.getRegistrationInputs().add(input);
        
        // Override AP ID and AP PWD if they are set for this req. 
        if (this.senderApId  != null) req.getAPInfo().setAPID(this.senderApId);
        if (this.senderApPwd != null) req.getAPInfo().setAPPWD(this.senderApPwd);            

        return req;
    }
    
    /**
     * Get the operation name
     * @return operation name
     */
    public String getOperation() {
        return this.operation;
    }
    
    /**
     * Get the operation namespace
     * @return operation namespace
     */
    public String getNamespace() {
        return this.namespace;
    }
    
    /**
     * Set a target for the request
     * @param name {@link TargetName} specifying target name
     * @param value String value of target
     * @throws IllegalArgumentException
     */
    public void setTarget(final TargetName name, final String value) 
        throws IllegalArgumentException
    {
        if (name == null) throw new IllegalArgumentException("Target must have a name");
        switch (name) {
            case APID:
                this.apId       = value;
                this.targetType = Target.ENTITY;
                break;
            case CUSTOMERID:
                this.customerId = value;
                this.targetType = Target.ENTITY;
                break;
            case EUID:
                this.entityUserId = value;
                this.targetType   = Target.ENTITYUSER;
                break;
            case ICCID:
                this.iccid      = value;
                this.targetType = Target.SIMCARD;
                break;
            case IMSI:
                this.imsi       = value;
                this.targetType = Target.SIMCARD;
                break;
            case MSISDN: 
                this.msisdn = value;
                if (this.operation.toLowerCase().endsWith(SIMCARD_OP)) {
                    this.targetType = Target.SIMCARD;
                } else {
                    this.targetType = Target.MOBILEUSER;
                }
              break;
            case MSSPURI:
                this.msspUri    = value;
                this.targetType = Target.ENTITY;
                break;
            case NASID:
                this.nasId      = value;
                this.targetType = Target.ENTITY;
                break;
            case SPID:
                this.spId       = value;
                this.targetType = Target.ENTITY;
                break;
            case CAURI:
                this.caUri      = value;
                this.targetType = Target.ENTITY;
                break;
            case USERID:
                this.userIdentifier = value;
                this.targetType = Target.MOBILEUSER;
                break;
        }
    }

    /**
     * If the target is a Mobile User, set the MSISDN identifying it
     * @param msisdn Target MSISDN
     */    
    public void setTargetMsisdn(final String msisdn) {
        this.setTarget(TargetName.MSISDN, msisdn);
    }

    /**
     * If the target is a SIM Card, set the IMSI identifying it
     * @param imsi Target IMSI
     */
    public void setTargetImsi(final String imsi) {
        this.setTarget(TargetName.IMSI, imsi);
    }
    
    /**
     * If the target is a SIM Card, set the ICCID identifying it
     * @param iccid Target ICCID
     */
    public void setTargetIccid(final String iccid) {
        this.setTarget(TargetName.ICCID, iccid);
    }
    
    /**
     * If the target is an AP, set the AP_ID identifying it
     * @param apId Target AP_ID
     */
    public void setTargetApId(final String apId) {
        this.setTarget(TargetName.APID, apId);
    }
    
    /**
     * If the target is an EntityUser, set the EntityUserID identifying it
     * @param euId Target EntityUserID
     */
    public void setTargetEuId(final String euId) {
        this.setTarget(TargetName.EUID, euId);
    }
    
    /**
     * If the target is a CA, set the CA_URI identifying it
     * @param caUri Target CA URI
     */
    public void setTargetCaUri(final String caUri) {
        this.setTarget(TargetName.CAURI, caUri);
    }
    
    /**
     * If the target is an MSSP, set the MSSP URI identifying it
     * @param value Target MSSP URI
     */
    public void setTargetMsspUri(String value) {
        this.setTarget(TargetName.MSSPURI, value);
    }

    /**
     * If the target is an Customer, set the CUSTOMERID identifying it
     * @param value Target CUSTOMERID
     */
    public void setTargetCustomerID(String value) {
        this.setTarget(TargetName.CUSTOMERID, value);
    }

    public void setTargetUserIdentifier(final String uid) {
        this.setTarget(TargetName.USERID, uid);
    }

    public void setTargetNasId(String value) {
        this.setTarget(TargetName.NASID, value);
    }

    public void setTargetSpId(String value) {
        this.setTarget(TargetName.SPID, value);
    }
    
    /**
     * Force the target type to a specific value.
     * <p>Not recommended, as the {@link #setTarget(TargetName, String)}, method already sets this.
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
     * Mark this request as wireless (going through OTA/etc)
     * <p>If this flag is set, {@link #toMSSReq(MssClient)} uses {@link WirelessOperation} instead of {@link ProvisioningOperation}
     * @param wireless
     */
    public void setWireless(final boolean wireless) {
        this.wireless = wireless;
    }
    
    /**
     * Set WSSE security header to be used in the request
     * @param security WSSE security header
     */
    public void setWSSESecurity(final Security security) {
        this.security = security;
    }
    
    /**
     * Add a new request parameter
     * 
     * @param name  Name of the parameter
     * @param value Value of the parameter
     */
    public void addParameter(final String name, final String value) {
        this.addParameter(name, value, null, null);
    }
    
    /**
     * Add a new request parameter
     * 
     * @param name     Name of the parameter
     * @param value    Value of the parameter
     * @param mimeType MimeType 
     * @param encoding Encoding (e.g. UTF-8 or BASE64)
     */
    public void addParameter(final String name,
                             final String value,
                             final String mimeType,
                             final String encoding) {
        MregParam param = new MregParam(name, value, mimeType, encoding);
        this.addParameter(param);
    }
    
    /**
     * Add a new request parameter
     * @param param raw {@link MregParam} object
     */
    public void addParameter(final MregParam param) {
        this.params.add(param);
    }
    
    /**
     * Add a new request parameter
     * 
     * @param name  Name of the parameter
     * @param value Value of the parameter
     * @deprecated Use {@link #addParameter(String, String)} instead
     */
    @Deprecated
    public void addParam(final String name, final String value) {
        this.addParameter(name, value, null, null);
    }
    
    /**
     * Add a new request parameter
     * 
     * @param name     Name of the parameter
     * @param value    Value of the parameter
     * @param mimeType MimeType 
     * @param encoding Encoding (e.g. UTF-8 or BASE64)
     * @deprecated Use {@link #addParameter(String, String, String, String)} instead
     */
    @Deprecated
    public void addParam(final String name,
                         final String value,
                         final String mimeType,
                         final String encoding) {
        this.addParameter(name, value, mimeType, encoding);
    }
    
    /**
     * Add a new request parameter
     * @param param raw {@link MregParam} object
     * @deprecated Use {@link #addParameter(MregParam)} instead
     */
    @Deprecated
    public void addParam(final MregParam param) {
        this.addParameter(param);
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
                mu.setUserIdentifier(this.userIdentifier);
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
                ent.setCustomerID(this.customerId);
                if (this.msspUri != null) {
                    MeshMemberType mm = new MeshMemberType();
                    mm.setURI(this.msspUri);
                    ent.setMsspID(mm);
                }
                ent.setNasID(this.nasId);
                ent.setSpID(this.spId);
                ent.setCAURI(this.caUri);
                target.setEntity(ent);
                break;
            case ENTITYUSER:
                EntityUserType eu = new EntityUserType();
                eu.setEntityUserID(this.entityUserId);
                target.setEntityUser(eu);
                break;
            default:
                break;
        }

        return target;
    }
    
    /**
     * Set AP_ID for this request. 
     * This overrides client specific AP_ID. 
     * @param apId AP ID. 
     */
    public void setApId(String apId) {
        this.senderApId = apId;
    }
    
    /**
     * Set AP_PWD for this request. 
     * This overrides client specific AP_ID. 
     * @param apPwd AP Password. 
     */
    public void setApPwd(String apPwd) {
        this.senderApPwd = apPwd;
    }
    
}
