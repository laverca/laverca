package fi.laverca.registration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fi.laverca.jaxb.mreg.NameValueType;
import fi.laverca.jaxb.mreg.OperationOutput;
import fi.laverca.jaxb.mreg.OperationStatusType;
import fi.laverca.jaxb.mreg.RegistrationOutput;
import fi.laverca.jaxb.mss.MSSRegistrationResp;

/**
 * A wrapper class for an MSS_RegistrationResp with MReg extension
 *
 */
public class MregResponse {

    private static final String GROUP_PREFIX = "GROUP";
    
    protected MSSRegistrationResp resp;
    protected RegistrationOutput  output;
    protected List<MregParam>     params;
    
    /**
     * Default constructor - from a raw {@link MSSRegistrationResp}
     * @param resp {@link MSSRegistrationResp} received from an MSSP
     */
    public MregResponse(final MSSRegistrationResp resp) {
        this.resp = resp;
        
        if (this.resp == null) return;
        if (this.resp.getStatus() == null) return;
        if (this.resp.getStatus().getStatusDetail() == null) return;
        
        for (Object o : this.resp.getStatus().getStatusDetail().getAniesAndServiceResponsesAndReceiptRequestExtensions()) {
            if (o instanceof RegistrationOutput) {
                this.output = (RegistrationOutput)o;
            }
        }
        
        // Parse params
        this.params = new ArrayList<>();
        if (this.getOperationOutput().getParameters() == null) return;
        
        String groupName  = null;
        int    groupIndex = 0;
        
        for (NameValueType type : this.getOperationOutput().getParameters()) {
            
            MregParam param = new MregParam(type);
            if (param.isGroupStart()) {
                groupName = param.getValue();
                if (groupName == null) groupName = GROUP_PREFIX + groupIndex++;
            } else if (param.isGroupEnd()){
                groupName = null;
            } else {
                param.setGroup(groupName);
                this.params.add(param);
            }
        }
        
    }
    
    /**
     * Get the raw {@link MSSRegistrationResp}
     * @return {@link MSSRegistrationResp}
     */
    public MSSRegistrationResp getResp() {
        return this.resp;
    }
    
    /**
     * Get the raw {@link OperationOutput}
     * @return first {@link OperationOutput}
     */
    public OperationOutput getOperationOutput() {
        if (this.output == null) return null;
        if (this.output.getMregResponses().size() <= 0) return null;
        if (this.output.getMregResponses().get(0) == null) return null;
        return this.output.getMregResponses().get(0).getOperationOutput();
    }
    
    /**
     * Get the list of output parameters as {@link List&lt;MregParam&gt;}
     * @return Output parameters from the MReg response
     */
    public List<MregParam> getParameters() {
        return this.params;
    }

    /**
     * Get parameter group names
     * @return names of output param groups (may be empty)
     */
    public List<String> getGroupNames() {        
        return this.getParameters().stream()
                                   .map(p -> p.groupName)
                                   .distinct()
                                   .collect(Collectors.toList());
    }
    
    /**
     * Get the list of output parameters that belong to the given group as {@link List&lt;MregParam&gt;}
     * @param groupName Name of the group
     * @return Output parameters from the MReg response
     */
    public List<MregParam> getGroup(final String groupName) {
        return this.getParameters().stream()
                                   .filter(p -> p.groupName.equals(groupName))
                                   .collect(Collectors.toList());
    }

    /**
     * Get the Operation status message.
     * <p>Common responses:
     * <ul>
     * <li>100 - OPERATION_OK
     * <li>201 - UNAUTHORIZED
     * <li>202 - MISSING_PARAMETER
     * <li>204 - ILLEGAL_PARAMETER
     * <li>300 - UNSUPPORTED_OPERATION
     * <li>900 - INTERNAL_ERROR
     * </ul>
     * @return Status Message describing what went wrong
     */
    public String getStatusMessage() {
        if (this.getStatus() == null) return null;
        return this.getStatus().getOperationStatusMessage();
    }
    
    /**
     * Get the Operation status code.
     * <p>Common responses:
     * <ul>
     * <li>100 - OPERATION_OK
     * <li>201 - UNAUTHORIZED
     * <li>202 - MISSING_PARAMETER
     * <li>204 - ILLEGAL_PARAMETER
     * <li>300 - UNSUPPORTED_OPERATION
     * <li>900 - INTERNAL_ERROR
     * </ul>
     * @return Status code describing what went wrong
     *         Returns -1 if no code is available in the response.
     */
    public long getStatusCode() {
        if (this.getStatus() == null) return -1;
        return this.getStatus().getOperationStatusCode();
    }
    
    /**
     * Was the request successful?
     * @return true if operation status code is 100
     */
    public boolean isSuccessful() {
        return this.getStatusCode() == 100;
    }
    
    /**
     * Get the raw {@link OperationStatusType} 
     * @return {@link OperationStatusType}
     */
    protected OperationStatusType getStatus() {
        if (this.getOperationOutput() == null) return null;
        return this.getOperationOutput().getStatus();
    }
    
}
