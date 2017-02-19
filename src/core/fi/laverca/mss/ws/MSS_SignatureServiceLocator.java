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

package fi.laverca.mss.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Stub;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings({"serial", "rawtypes"})
public class MSS_SignatureServiceLocator extends org.apache.axis.client.Service implements MSS_SignatureService {

    private static Log log = LogFactory.getLog(MSS_SignatureServiceLocator.class);

    public MSS_SignatureServiceLocator() {
        super();
        log.debug("MSS_SignatureServiceLocator()");
    }

    public MSS_SignatureServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
        log.debug("MSS_SignatureServiceLocator(EngineConfiguration)");
    }

    // Use to get a proxy class for MSS_SignaturePort
    private String MSS_SignaturePort_address = "https://127.0.0.1:8443";

    @Override
    public String getMSS_SignaturePortAddress() {
        return this.MSS_SignaturePort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_SignaturePortWSDDServiceName = "MSS_SignaturePort";

    public String getMSS_SignaturePortWSDDServiceName() {
        return this.MSS_SignaturePortWSDDServiceName;
    }

    public void setMSS_SignaturePortWSDDServiceName(String name) {
        this.MSS_SignaturePortWSDDServiceName = name;
    }

    @Override
    public MSS_SignaturePortType getMSS_SignaturePort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(this.MSS_SignaturePort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_SignaturePort(endpoint);
    }

    @Override
    public MSS_SignaturePortType getMSS_SignaturePort(URL portAddress) throws ServiceException {
        MSS_SignatureBindingStub _stub = new MSS_SignatureBindingStub(portAddress, this);
        _stub.setPortName(getMSS_SignaturePortWSDDServiceName());
        return _stub;
    }

    public void setMSS_SignaturePortEndpointAddress(String address) {
        this.MSS_SignaturePort_address = address;
    }


    // Use to get a proxy class for MSS_StatusQueryPort
    private String MSS_StatusQueryPort_address = "https://127.0.0.1:8443";

    @Override
    public String getMSS_StatusQueryPortAddress() {
        return this.MSS_StatusQueryPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_StatusQueryPortWSDDServiceName = "MSS_StatusQueryPort";

    public String getMSS_StatusQueryPortWSDDServiceName() {
        return this.MSS_StatusQueryPortWSDDServiceName;
    }

    public void setMSS_StatusQueryPortWSDDServiceName(String name) {
        this.MSS_StatusQueryPortWSDDServiceName = name;
    }

    @Override
    public MSS_StatusQueryType getMSS_StatusQueryPort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(this.MSS_StatusQueryPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_StatusQueryPort(endpoint);
    }

    @Override
    public MSS_StatusQueryType getMSS_StatusQueryPort(URL portAddress) throws ServiceException {
        MSS_StatusQueryBindingStub _stub = new MSS_StatusQueryBindingStub(portAddress, this);
        _stub.setPortName(getMSS_StatusQueryPortWSDDServiceName());
        return _stub;
    }

    public void setMSS_StatusQueryPortEndpointAddress(String address) {
        this.MSS_StatusQueryPort_address = address;
    }


    // Use to get a proxy class for MSS_ReceiptPort
    private String MSS_ReceiptPort_address = "https://127.0.0.1:8443";

    @Override
    public String getMSS_ReceiptPortAddress() {
        return this.MSS_ReceiptPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_ReceiptPortWSDDServiceName = "MSS_ReceiptPort";

    public String getMSS_ReceiptPortWSDDServiceName() {
        return this.MSS_ReceiptPortWSDDServiceName;
    }

    public void setMSS_ReceiptPortWSDDServiceName(String name) {
        this.MSS_ReceiptPortWSDDServiceName = name;
    }

    @Override
    public MSS_ReceiptType getMSS_ReceiptPort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(this.MSS_ReceiptPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_ReceiptPort(endpoint);
    }

    @Override
    public MSS_ReceiptType getMSS_ReceiptPort(URL portAddress) throws ServiceException {
        MSS_ReceiptBindingStub _stub = new MSS_ReceiptBindingStub(portAddress, this);
        _stub.setPortName(getMSS_ReceiptPortWSDDServiceName());
        return _stub;
    }

    public void setMSS_ReceiptPortEndpointAddress(String address) {
        this.MSS_ReceiptPort_address = address;
    }


    // Use to get a proxy class for MSS_NotificationPort
    private String MSS_NotificationPort_address = "https://127.0.0.1:8443";

    @Override
    public String getMSS_NotificationPortAddress() {
        return this.MSS_NotificationPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_NotificationPortWSDDServiceName = "MSS_NotificationPort";

    public String getMSS_NotificationPortWSDDServiceName() {
        return this.MSS_NotificationPortWSDDServiceName;
    }

    public void setMSS_NotificationPortWSDDServiceName(String name) {
        this.MSS_NotificationPortWSDDServiceName = name;
    }

    @Override
    public MSS_NotificationPortType getMSS_NotificationPort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(this.MSS_NotificationPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_NotificationPort(endpoint);
    }

    @Override
    public MSS_NotificationPortType getMSS_NotificationPort(URL portAddress) throws ServiceException {
        MSS_NotificationBindingStub _stub = new MSS_NotificationBindingStub(portAddress, this);
        _stub.setPortName(getMSS_NotificationPortWSDDServiceName());
        return _stub;
    }

    public void setMSS_NotificationPortEndpointAddress(String address) {
        this.MSS_NotificationPort_address = address;
    }


    // Use to get a proxy class for MSS_RegistrationPort
    private String MSS_RegistrationPort_address = "https://127.0.0.1:8443";

    @Override
    public String getMSS_RegistrationPortAddress() {
        return this.MSS_RegistrationPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_RegistrationPortWSDDServiceName = "MSS_RegistrationPort";

    public String getMSS_RegistrationPortWSDDServiceName() {
        return this.MSS_RegistrationPortWSDDServiceName;
    }

    public void setMSS_RegistrationPortWSDDServiceName(String name) {
        this.MSS_RegistrationPortWSDDServiceName = name;
    }

    @Override
    public MSS_RegistrationType getMSS_RegistrationPort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(this.MSS_RegistrationPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_RegistrationPort(endpoint);
    }

    @Override
    public MSS_RegistrationType getMSS_RegistrationPort(URL portAddress) throws ServiceException {
        MSS_RegistrationBindingStub _stub = new MSS_RegistrationBindingStub(portAddress, this);
        _stub.setPortName(getMSS_RegistrationPortWSDDServiceName());
        return _stub;
    }

    public void setMSS_RegistrationPortEndpointAddress(String address) {
        this.MSS_RegistrationPort_address = address;
    }


    // Use to get a proxy class for MSS_ProfileQueryPort
    private String MSS_ProfileQueryPort_address = "https://127.0.0.1:8443";

    @Override
    public String getMSS_ProfileQueryPortAddress() {
        return this.MSS_ProfileQueryPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_ProfileQueryPortWSDDServiceName = "MSS_ProfileQueryPort";

    public String getMSS_ProfileQueryPortWSDDServiceName() {
        return this.MSS_ProfileQueryPortWSDDServiceName;
    }

    public void setMSS_ProfileQueryPortWSDDServiceName(String name) {
        this.MSS_ProfileQueryPortWSDDServiceName = name;
    }

    @Override
    public MSS_ProfileQueryType getMSS_ProfileQueryPort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(this.MSS_ProfileQueryPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_ProfileQueryPort(endpoint);
    }

    @Override
    public MSS_ProfileQueryType getMSS_ProfileQueryPort(URL portAddress) throws ServiceException {
        MSS_ProfileQueryBindingStub _stub = new MSS_ProfileQueryBindingStub(portAddress, this);
        _stub.setPortName(getMSS_ProfileQueryPortWSDDServiceName());
        return _stub;
    }

    public void setMSS_ProfileQueryPortEndpointAddress(String address) {
        this.MSS_ProfileQueryPort_address = address;
    }


    // Use to get a proxy class for MSS_HandshakePort
    private String MSS_HandshakePort_address = "https://127.0.0.1:8443";

    @Override
    public String getMSS_HandshakePortAddress() {
        return this.MSS_HandshakePort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_HandshakePortWSDDServiceName = "MSS_HandshakePort";

    public String getMSS_HandshakePortWSDDServiceName() {
        return this.MSS_HandshakePortWSDDServiceName;
    }

    public void setMSS_HandshakePortWSDDServiceName(String name) {
        this.MSS_HandshakePortWSDDServiceName = name;
    }

    @Override
    public MSS_HandshakePortType getMSS_HandshakePort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(this.MSS_HandshakePort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_HandshakePort(endpoint);
    }

    @Override
    public MSS_HandshakePortType getMSS_HandshakePort(URL portAddress) throws ServiceException {
        MSS_HandshakeBindingStub _stub = new MSS_HandshakeBindingStub(portAddress, this);
        _stub.setPortName(getMSS_HandshakePortWSDDServiceName());
        return _stub;
    }

    /**
     * Set the MSS_Handshake port address
     * @param address Address to set
     */
    public void setMSS_HandshakePortEndpointAddress(final String address) {
        this.MSS_HandshakePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * @param serviceEndpointInterface Interface class reference
     */
    @Override
    public Remote getPort(Class serviceEndpointInterface) throws ServiceException {
        try {
            if (MSS_SignaturePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_SignatureBindingStub _stub = new MSS_SignatureBindingStub(new URL(this.MSS_SignaturePort_address), this);
                _stub.setPortName(getMSS_SignaturePortWSDDServiceName());
                return _stub;
            }
            if (MSS_StatusQueryType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_StatusQueryBindingStub _stub = new MSS_StatusQueryBindingStub(new URL(this.MSS_StatusQueryPort_address), this);
                _stub.setPortName(getMSS_StatusQueryPortWSDDServiceName());
                return _stub;
            }
            if (MSS_ReceiptType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_ReceiptBindingStub _stub = new MSS_ReceiptBindingStub(new URL(this.MSS_ReceiptPort_address), this);
                _stub.setPortName(getMSS_ReceiptPortWSDDServiceName());
                return _stub;
            }
            if (MSS_NotificationPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_NotificationBindingStub _stub = new MSS_NotificationBindingStub(new URL(this.MSS_NotificationPort_address), this);
                _stub.setPortName(getMSS_NotificationPortWSDDServiceName());
                return _stub;
            }
            if (MSS_RegistrationType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_RegistrationBindingStub _stub = new MSS_RegistrationBindingStub(new URL(this.MSS_RegistrationPort_address), this);
                _stub.setPortName(getMSS_RegistrationPortWSDDServiceName());
                return _stub;
            }
            if (MSS_ProfileQueryType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_ProfileQueryBindingStub _stub = new MSS_ProfileQueryBindingStub(new URL(this.MSS_ProfileQueryPort_address), this);
                _stub.setPortName(getMSS_ProfileQueryPortWSDDServiceName());
                return _stub;
            }
            if (MSS_HandshakePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_HandshakeBindingStub _stub = new MSS_HandshakeBindingStub(new URL(this.MSS_HandshakePort_address), this);
                _stub.setPortName(getMSS_HandshakePortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new ServiceException(t);
        }
        throw new ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * @param portName Name of the port
     * @param serviceEndpointInterface Interface class reference
     */
    @Override
    public Remote getPort(QName portName, Class serviceEndpointInterface) throws ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("MSS_SignaturePort".equals(inputPortName)) {
            return getMSS_SignaturePort();
        }
        else if ("MSS_StatusQueryPort".equals(inputPortName)) {
            return getMSS_StatusQueryPort();
        }
        else if ("MSS_ReceiptPort".equals(inputPortName)) {
            return getMSS_ReceiptPort();
        }
        else if ("MSS_NotificationPort".equals(inputPortName)) {
            return getMSS_NotificationPort();
        }
        else if ("MSS_RegistrationPort".equals(inputPortName)) {
            return getMSS_RegistrationPort();
        }
        else if ("MSS_ProfileQueryPort".equals(inputPortName)) {
            return getMSS_ProfileQueryPort();
        }
        else if ("MSS_HandshakePort".equals(inputPortName)) {
            return getMSS_HandshakePort();
        }
        else  {
            Remote _stub = getPort(serviceEndpointInterface);
            ((Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    @Override
    public QName getServiceName() {
        return new QName("http://laverca.fi/mssp", "MSS_SignatureService");
    }

    private java.util.List<QName> ports = null;

    @Override
    public List<QName> getPorts() {
        if (this.ports == null) {
            this.ports = new java.util.ArrayList<QName>();
            this.ports.add(new QName("http://laverca.fi/mssp", "MSS_SignaturePort"));
            this.ports.add(new QName("http://laverca.fi/mssp", "MSS_StatusQueryPort"));
            this.ports.add(new QName("http://laverca.fi/mssp", "MSS_ReceiptPort"));
            this.ports.add(new QName("http://laverca.fi/mssp", "MSS_NotificationPort"));
            this.ports.add(new QName("http://laverca.fi/mssp", "MSS_RegistrationPort"));
            this.ports.add(new QName("http://laverca.fi/mssp", "MSS_ProfileQueryPort"));
            this.ports.add(new QName("http://laverca.fi/mssp", "MSS_HandshakePort"));
        }
        return this.ports;
    }

    /**
    * Set the endpoint address for the specified port name.
    * @param portName Name of the port
    * @param address Address to set
    * @throws ServiceException if the port name is not recognized
    */
    public void setEndpointAddress(final String portName, final String address) throws ServiceException {
        
        if ("MSS_SignaturePort".equals(portName)) {
            this.setMSS_SignaturePortEndpointAddress(address);
        }
        else if ("MSS_StatusQueryPort".equals(portName)) {
            this.setMSS_StatusQueryPortEndpointAddress(address);
        }
        else if ("MSS_ReceiptPort".equals(portName)) {
            this.setMSS_ReceiptPortEndpointAddress(address);
        }
        else if ("MSS_NotificationPort".equals(portName)) {
            this.setMSS_NotificationPortEndpointAddress(address);
        }
        else if ("MSS_RegistrationPort".equals(portName)) {
            this.setMSS_RegistrationPortEndpointAddress(address);
        }
        else if ("MSS_ProfileQueryPort".equals(portName)) {
            this.setMSS_ProfileQueryPortEndpointAddress(address);
        }
        else if ("MSS_HandshakePort".equals(portName)) {
            this.setMSS_HandshakePortEndpointAddress(address);
        }
        else { // Unknown Port Name
            throw new ServiceException("Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    * @param portName Name of the port
    * @param address Address to set
    * @throws ServiceException if the port name is not recognized
    */
    public void setEndpointAddress(final QName portName, final String address) throws ServiceException {
        this.setEndpointAddress(portName.getLocalPart(), address);
    }
}
