package fi.laverca.ws;

public interface MSS_SignatureService extends javax.xml.rpc.Service {
    public java.lang.String getMSS_SignaturePortAddress();

    public MSS_SignaturePortType getMSS_SignaturePort() throws javax.xml.rpc.ServiceException;

    public MSS_SignaturePortType getMSS_SignaturePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_StatusQueryPortAddress();

    public MSS_StatusQueryType getMSS_StatusQueryPort() throws javax.xml.rpc.ServiceException;

    public MSS_StatusQueryType getMSS_StatusQueryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_ReceiptPortAddress();

    public MSS_ReceiptType getMSS_ReceiptPort() throws javax.xml.rpc.ServiceException;

    public MSS_ReceiptType getMSS_ReceiptPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_NotificationPortAddress();

    public MSS_NotificationPortType getMSS_NotificationPort() throws javax.xml.rpc.ServiceException;

    public MSS_NotificationPortType getMSS_NotificationPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_RegistrationPortAddress();

    public MSS_RegistrationType getMSS_RegistrationPort() throws javax.xml.rpc.ServiceException;

    public MSS_RegistrationType getMSS_RegistrationPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_ProfileQueryPortAddress();

    public MSS_ProfileQueryType getMSS_ProfileQueryPort() throws javax.xml.rpc.ServiceException;

    public MSS_ProfileQueryType getMSS_ProfileQueryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getMSS_HandshakePortAddress();

    public MSS_HandshakePortType getMSS_HandshakePort() throws javax.xml.rpc.ServiceException;

    public MSS_HandshakePortType getMSS_HandshakePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
