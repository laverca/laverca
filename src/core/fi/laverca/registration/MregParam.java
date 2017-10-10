package fi.laverca.registration;

import fi.laverca.jaxb.mreg.NameValueType;

/**
 * Wraps a single MReg input or output parameter.
 * 
 */
public class MregParam {

    protected static final String GROUP_START = "BeginGroup";
    protected static final String GROUP_END   = "EndGroup";
    
    protected NameValueType type;
    protected String groupName;
    
    /**
     * Construct from a raw {@link NameValueType}
     * @param type {@link NameValueType}
     */
    public MregParam(final NameValueType type) {
        if (type != null) {
            this.type  = type;
        } else {
            this.type = new NameValueType();
        }
    }
    
    /**
     * Construct an MregParam with unset mimetype/encoding
     * <p>This is fine in most cases, when the parameter value is a String.
     * <p>In case of binary data, use {@link #MregParam(String, String, String, String)} instead,
     * and set the encoding accordingly (e.g. base64)
     * 
     * @param name  Name of the parameter
     * @param value Value of the parameter
     */
    public MregParam(final String name, final String value) {
        this(name, value, null, null);
    }
    
    /**
     * Construct an MregParam
     * 
     * @param name  Name of the parameter
     * @param value Value of the parameter
     * @param mimeType MimeType of the parameter (may be null)
     * @param encoding Encoding of the parameter (may be null)
     */
    public MregParam(final String name,
                     final String value,
                     final String mimeType,
                     final String encoding) 
    {
        this.type = new NameValueType();
        this.type.setName(name);
        this.type.setValue(value);
        this.type.setMimeType(mimeType);
        this.type.setEncoding(encoding);
    }
    
    /**
     * Get the parameter value
     * @return value
     */
    public String getValue() {
        return this.getStringValue();
    }
    
    /**
     * Get the parameter value as String
     * @return String value
     */
    public String getStringValue() {
        return this.type.getValue();
    }
    
    /**
     * Get the parameter value as integer
     * @return parameter value or -1 if integer conversion is not possible
     */
    public int getIntValue() {
        try {
            return Integer.parseInt(this.getStringValue());
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }
    
    /**
     * Get the parameter value as boolean
     * @return true if the String value of this parameter is "true"
     */
    public boolean getBooleanValue() {
        return Boolean.parseBoolean(this.getStringValue());
    }
    
    /**
     * Get the name of this parameter
     * @return Name as String
     */
    public String getName() {
        return this.type.getName();
    }
    
    /**
     * Get the raw {@link NameValueType}
     * @return {@link NameValueType}
     */
    public NameValueType getType() {
        return this.type;
    }
    
    /**
     * Set the group which this param belongs to
     * <p>This is used when the Mreg Response contains multiple sets of parameters.
     * <br>e.g. ListMobileUsers may contain multiple MobileUsers with repeating parameters.
     * @param groupName Name of the group
     */
    public void setGroup(final String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * Get the group which this param belongs to
     */
    public String getGroup() {
        return this.groupName;
    }
    
    /**
     * Is this a BEGIN_GROUP parameter?
     * @return true if param name is {@link GROUP_START}
     */
    protected boolean isGroupStart() {
        return GROUP_START.equals(this.getName());
    }
    
    /**
     * Is this an END_GROUP parameter?
     * @return true if param name is {@link GROUP_END}
     */
    protected boolean isGroupEnd() {
        return GROUP_END.equals(this.getName());
    }
    
    @Override
    public String toString() {
        return this.getValue();
    }

}
