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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import fi.laverca.jaxb.mreg.NameValueType;
import fi.laverca.jaxb.mreg.OperationOutput;
import fi.laverca.jaxb.mreg.OperationStatusType;
import fi.laverca.jaxb.mreg.RegistrationOutput;
import fi.laverca.jaxb.mss.MSSRegistrationResp;
import fi.laverca.util.LavercaContext;

/**
 * A wrapper class for an MSS_RegistrationResp with MReg extension
 *
 */
public class MregResponse {

    private static final String GROUP_PREFIX = "GROUP";
    
    public LavercaContext context;
    
    protected MSSRegistrationResp       resp;
    protected List<RegistrationOutput>  output  = new ArrayList<>();
    protected List<MregParam>           params  = new ArrayList<>();
    protected List<String>              opNames = new ArrayList<>();
    
    /**
     * Default constructor - from a raw {@link MSSRegistrationResp} and {@link LavercaContext}
     * @param resp {@link MSSRegistrationResp} received from an MSSP
     * @param context {@link LavercaContext}
     */
    public MregResponse(final MSSRegistrationResp resp, final LavercaContext context) {
        this.resp    = resp;
        this.context = context;
        
        if (this.resp == null) return;
        if (this.resp.getStatus() == null) return;
        if (this.resp.getStatus().getStatusDetail() == null) return;
        
        for (Object o : this.resp.getStatus().getStatusDetail().getAniesAndServiceResponsesAndReceiptRequestExtensions()) {
            if (o instanceof RegistrationOutput) {
                this.output.add((RegistrationOutput)o);
            }
        }
        
        // Parse operation name
        for (OperationOutput o : this.getOperationOutputs()) {
            if (o == null) continue;
            this.opNames.add(o.getName());
        }
        
        // Parse params
        if (this.getOperationOutput().getParameters() == null) return;
        
        String groupName  = null;
        int    groupIndex = 0;
        
        for (NameValueType type : this.getOperationOutput().getParameters()) {
            
            MregParam param = new MregParam(type);
            if (param.isGroupStart()) {
                groupName = param.getValue();
                if (groupName == null) groupName = GROUP_PREFIX + "_" + groupIndex++;
            } else if (param.isGroupEnd()){
                groupName = null;
            } else {
                param.setGroup(groupName);
                this.params.add(param);
            }
        }
    }
    
    /**
     * Constructor - from a raw {@link MSSRegistrationResp}
     * @param resp {@link MSSRegistrationResp} received from an MSSP
     */
    public MregResponse(final MSSRegistrationResp resp) {
        this(resp, null);
    }
    
    /**
     * Get the raw {@link MSSRegistrationResp}
     * @return {@link MSSRegistrationResp}
     */
    public MSSRegistrationResp getResp() {
        return this.resp;
    }
    
    /**
     * Get the (first) operation name
     * @return first operation name (may be null)
     */
    public String getName() {
        if (this.opNames.size() == 0) return null;
        return this.opNames.get(0);
    }
    
    /**
     * Get the names of the operations this response contains. (normally only 1)
     * @return operation names
     */
    public List<String> getNames() {
        return this.opNames;
    }
    
    /**
     * Get the (first) raw {@link OperationOutput}
     * @return first {@link OperationOutput}
     */
    public OperationOutput getOperationOutput() {
        return this.getOperationOutput(0);
    }
    
    /**
     * Get the raw {@link OperationOutput}
     * @param index Index of the output (if there are multiple)
     * @return {@link OperationOutput} corresponding to the given index
     * @throws IndexOutOfBoundsException if the given index is out of bounds
     */
    public OperationOutput getOperationOutput(final int index) {
        RegistrationOutput output = this.output.get(index);
        if (output == null) return null;
        if (output.getMregResponses().size() <= 0) return null;
        if (output.getMregResponses().get(0) == null) return null;
        return output.getMregResponses().get(0).getOperationOutput();
    }
    
    /**
     * Get the raw {@code List<OperationOutput>}
     * @return {@code List<OperationOutput>}
     * @throws IndexOutOfBoundsException if the output is malformed
     */
    public List<OperationOutput> getOperationOutputs() {
        return this.output.stream()
                          .map(o -> o.getMregResponses()
                          .get(0).getOperationOutput())
                          .collect(Collectors.toList());
    }
    
    /**
     * Get the list of output parameters as {@code List<MregParam>}
     * @return Output parameters from the MReg response
     */
    public List<MregParam> getParameters() {
        return this.params;
    }
    
    /**
     * Get a parameter with the given name.
     * <p>If the parameter is not found, returns an empty {@link MregParam}
     * @param name Name of the output parameter
     * @return Output parameter, if found, an empty {@link MregParam} if not
     */
    public MregParam getParameter(final String name) {
        if (name == null) return new MregParam(null);
        return this.params.stream()
                          .filter(p -> name.equalsIgnoreCase(p.getName()))
                          .findFirst()
                          .orElse(new MregParam(null));
    }
    
    /**
     * Get a parameter with the given name.
     * <p>If the parameter is not found, returns an empty {@link MregParam}
     * @param name Name of the output parameter
     * @param group Name of the group this parameter belongs to. If null, calls {@link #getParameter(String)}.
     * @return Output parameter, if found, an empty {@link MregParam} if not
     */
    public MregParam getParameter(final String name,
                                  final String group) {
        if (name  == null) return new MregParam(null);
        if (group == null) return this.getParameter(name);
        return this.getGroup(group).stream()
                                   .filter(p -> name.equalsIgnoreCase(p.getName()))
                                   .findFirst()
                                   .orElse(new MregParam(null));
    }

    /**
     * Get parameter group names
     * @return names of output param groups (may be empty)
     */
    public List<String> getGroupNames() {        
        return this.getParameters().stream()
                                   .filter(p -> p.groupName != null)
                                   .map(p -> p.groupName)
                                   .distinct()
                                   .collect(Collectors.toList());
    }
    
    /**
     * Get the list of output parameters that belong to the given group as {@code List<MregParam>}
     * @param groupName Name of the group
     * @return Output parameters from the MReg response
     */
    public List<MregParam> getGroup(final String groupName) {
        if (groupName == null) return Collections.emptyList();
        return this.getParameters().stream()
                                   .filter(p -> groupName.equals(p.groupName))
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
