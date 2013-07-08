/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2013 Laverca Project
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

package fi.laverca.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Stub;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class MSS_SignatureServiceLocator extends org.apache.axis.client.Service implements MSS_SignatureService {

    private static Log log = LogFactory.getLog(MSS_SignatureServiceLocator.class);
/*
    public MSS_SignatureServiceLocator() {
        super();
        log.debug("MSS_SignatureServiceLocator()");
        setEngineConfiguration((String)null); // default client-config.wsdd file
    }

    public MSS_SignatureServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
        log.debug("MSS_SignatureServiceLocator(EngineConfiguration)");
        _config = config; // ready to use config!
    }

    public MSS_SignatureServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
        super(wsdlLoc, sName);
        log.debug("MSS_SignatureServiceLocator(wsdlLoc='"+wsdlLoc+"', sName='"+sName+"')");
        setEngineConfiguration((String)null); // default client-config.wsdd file
    }

    public MSS_SignatureServiceLocator(String fname) {
        super();
        log.debug("MSS_SignatureServiceLocator()");
        setEngineConfiguration(fname);
    }

    static private EngineConfiguration _config;
*/
    /**
     * Pick runtime Axis Client Engine Configuration from set value.
     *<p>
     * We support only one instance of client configuration at any
     * time.
     */
/*
    public EngineConfiguration getEngineConfiguration() {
        if (_config != null)
            return _config;

        return super.getEngineConfiguration();
    }
*/

    /**
     * Axis client engine configuration setter.
     *<p>
     * Tells name of the file, and depending on class-loader
     * may even pick it up from jar-file.
     *<p>
     * Default file is <code>client-config.wsdd</code>.
     */
/*
    public void setEngineConfiguration(String fname) {
        if (fname == null) fname="server-config.wsdd";
        InputStream is = null;
        try {
            // Load-location depends on class-loader
            is = MSS_SignatureServiceLocator.class.getResourceAsStream(fname);
            setEngineConfiguration(is);

        } catch (Exception e) {
            // ignore
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    // ignore..
                }
            }
        }
    }
*/

    /**
     * Axis client engine configuration setter.
     *<p>
     * Has generic InputStream type input, into which
     * the caller may choose to inject anything - including
     * 
     */
/*
    public void setEngineConfiguration(InputStream is) {
        _config = new FileProvider(is);
    }
*/


    // Use to get a proxy class for MSS_SignaturePort
    private String MSS_SignaturePort_address = "https://127.0.0.1:8443";

    public String getMSS_SignaturePortAddress() {
        return MSS_SignaturePort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_SignaturePortWSDDServiceName = "MSS_SignaturePort";

    public String getMSS_SignaturePortWSDDServiceName() {
        return MSS_SignaturePortWSDDServiceName;
    }

    public void setMSS_SignaturePortWSDDServiceName(String name) {
        MSS_SignaturePortWSDDServiceName = name;
    }

    public MSS_SignaturePortType getMSS_SignaturePort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(MSS_SignaturePort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_SignaturePort(endpoint);
    }

    public MSS_SignaturePortType getMSS_SignaturePort(URL portAddress) throws ServiceException {
        try {
            MSS_SignatureBindingStub _stub = new MSS_SignatureBindingStub(portAddress, this);
            _stub.setPortName(getMSS_SignaturePortWSDDServiceName());
            return _stub;
        }
        catch (AxisFault e) {
            return null;
        }
    }

    public void setMSS_SignaturePortEndpointAddress(String address) {
        MSS_SignaturePort_address = address;
    }


    // Use to get a proxy class for MSS_StatusQueryPort
    private String MSS_StatusQueryPort_address = "https://127.0.0.1:8443";

    public String getMSS_StatusQueryPortAddress() {
        return MSS_StatusQueryPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_StatusQueryPortWSDDServiceName = "MSS_StatusQueryPort";

    public String getMSS_StatusQueryPortWSDDServiceName() {
        return MSS_StatusQueryPortWSDDServiceName;
    }

    public void setMSS_StatusQueryPortWSDDServiceName(String name) {
        MSS_StatusQueryPortWSDDServiceName = name;
    }

    public MSS_StatusQueryType getMSS_StatusQueryPort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(MSS_StatusQueryPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_StatusQueryPort(endpoint);
    }

    public MSS_StatusQueryType getMSS_StatusQueryPort(URL portAddress) throws ServiceException {
        try {
            MSS_StatusQueryBindingStub _stub = new MSS_StatusQueryBindingStub(portAddress, this);
            _stub.setPortName(getMSS_StatusQueryPortWSDDServiceName());
            return _stub;
        }
        catch (AxisFault e) {
            return null;
        }
    }

    public void setMSS_StatusQueryPortEndpointAddress(String address) {
        MSS_StatusQueryPort_address = address;
    }


    // Use to get a proxy class for MSS_ReceiptPort
    private String MSS_ReceiptPort_address = "https://127.0.0.1:8443";

    public String getMSS_ReceiptPortAddress() {
        return MSS_ReceiptPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_ReceiptPortWSDDServiceName = "MSS_ReceiptPort";

    public String getMSS_ReceiptPortWSDDServiceName() {
        return MSS_ReceiptPortWSDDServiceName;
    }

    public void setMSS_ReceiptPortWSDDServiceName(String name) {
        MSS_ReceiptPortWSDDServiceName = name;
    }

    public MSS_ReceiptType getMSS_ReceiptPort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(MSS_ReceiptPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_ReceiptPort(endpoint);
    }

    public MSS_ReceiptType getMSS_ReceiptPort(URL portAddress) throws ServiceException {
        try {
            MSS_ReceiptBindingStub _stub = new MSS_ReceiptBindingStub(portAddress, this);
            _stub.setPortName(getMSS_ReceiptPortWSDDServiceName());
            return _stub;
        }
        catch (AxisFault e) {
            return null;
        }
    }

    public void setMSS_ReceiptPortEndpointAddress(String address) {
        MSS_ReceiptPort_address = address;
    }


    // Use to get a proxy class for MSS_NotificationPort
    private String MSS_NotificationPort_address = "https://127.0.0.1:8443";

    public String getMSS_NotificationPortAddress() {
        return MSS_NotificationPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_NotificationPortWSDDServiceName = "MSS_NotificationPort";

    public String getMSS_NotificationPortWSDDServiceName() {
        return MSS_NotificationPortWSDDServiceName;
    }

    public void setMSS_NotificationPortWSDDServiceName(String name) {
        MSS_NotificationPortWSDDServiceName = name;
    }

    public MSS_NotificationPortType getMSS_NotificationPort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(MSS_NotificationPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_NotificationPort(endpoint);
    }

    public MSS_NotificationPortType getMSS_NotificationPort(URL portAddress) throws ServiceException {
        try {
            MSS_NotificationBindingStub _stub = new MSS_NotificationBindingStub(portAddress, this);
            _stub.setPortName(getMSS_NotificationPortWSDDServiceName());
            return _stub;
        }
        catch (AxisFault e) {
            return null;
        }
    }

    public void setMSS_NotificationPortEndpointAddress(String address) {
        MSS_NotificationPort_address = address;
    }


    // Use to get a proxy class for MSS_RegistrationPort
    private String MSS_RegistrationPort_address = "https://127.0.0.1:8443";

    public String getMSS_RegistrationPortAddress() {
        return MSS_RegistrationPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_RegistrationPortWSDDServiceName = "MSS_RegistrationPort";

    public String getMSS_RegistrationPortWSDDServiceName() {
        return MSS_RegistrationPortWSDDServiceName;
    }

    public void setMSS_RegistrationPortWSDDServiceName(String name) {
        MSS_RegistrationPortWSDDServiceName = name;
    }

    public MSS_RegistrationType getMSS_RegistrationPort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(MSS_RegistrationPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_RegistrationPort(endpoint);
    }

    public MSS_RegistrationType getMSS_RegistrationPort(URL portAddress) throws ServiceException {
        try {
            MSS_RegistrationBindingStub _stub = new MSS_RegistrationBindingStub(portAddress, this);
            _stub.setPortName(getMSS_RegistrationPortWSDDServiceName());
            return _stub;
        }
        catch (AxisFault e) {
            return null;
        }
    }

    public void setMSS_RegistrationPortEndpointAddress(String address) {
        MSS_RegistrationPort_address = address;
    }


    // Use to get a proxy class for MSS_ProfileQueryPort
    private String MSS_ProfileQueryPort_address = "https://127.0.0.1:8443";

    public String getMSS_ProfileQueryPortAddress() {
        return MSS_ProfileQueryPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_ProfileQueryPortWSDDServiceName = "MSS_ProfileQueryPort";

    public String getMSS_ProfileQueryPortWSDDServiceName() {
        return MSS_ProfileQueryPortWSDDServiceName;
    }

    public void setMSS_ProfileQueryPortWSDDServiceName(String name) {
        MSS_ProfileQueryPortWSDDServiceName = name;
    }

    public MSS_ProfileQueryType getMSS_ProfileQueryPort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(MSS_ProfileQueryPort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_ProfileQueryPort(endpoint);
    }

    public MSS_ProfileQueryType getMSS_ProfileQueryPort(URL portAddress) throws ServiceException {
        try {
            MSS_ProfileQueryBindingStub _stub = new MSS_ProfileQueryBindingStub(portAddress, this);
            _stub.setPortName(getMSS_ProfileQueryPortWSDDServiceName());
            return _stub;
        }
        catch (AxisFault e) {
            return null;
        }
    }

    public void setMSS_ProfileQueryPortEndpointAddress(String address) {
        MSS_ProfileQueryPort_address = address;
    }


    // Use to get a proxy class for MSS_HandshakePort
    private String MSS_HandshakePort_address = "https://127.0.0.1:8443";

    public String getMSS_HandshakePortAddress() {
        return MSS_HandshakePort_address;
    }

    // The WSDD service name defaults to the port name.
    private String MSS_HandshakePortWSDDServiceName = "MSS_HandshakePort";

    public String getMSS_HandshakePortWSDDServiceName() {
        return MSS_HandshakePortWSDDServiceName;
    }

    public void setMSS_HandshakePortWSDDServiceName(String name) {
        MSS_HandshakePortWSDDServiceName = name;
    }

    public MSS_HandshakePortType getMSS_HandshakePort() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(MSS_HandshakePort_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getMSS_HandshakePort(endpoint);
    }

    public MSS_HandshakePortType getMSS_HandshakePort(URL portAddress) throws ServiceException {
        try {
            MSS_HandshakeBindingStub _stub = new MSS_HandshakeBindingStub(portAddress, this);
            _stub.setPortName(getMSS_HandshakePortWSDDServiceName());
            return _stub;
        }
        catch (AxisFault e) {
            return null;
        }
    }

    public void setMSS_HandshakePortEndpointAddress(String address) {
        MSS_HandshakePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public Remote getPort(Class serviceEndpointInterface) throws ServiceException {
        try {
            if (MSS_SignaturePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_SignatureBindingStub _stub = new MSS_SignatureBindingStub(new URL(MSS_SignaturePort_address), this);
                _stub.setPortName(getMSS_SignaturePortWSDDServiceName());
                return _stub;
            }
            if (MSS_StatusQueryType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_StatusQueryBindingStub _stub = new MSS_StatusQueryBindingStub(new URL(MSS_StatusQueryPort_address), this);
                _stub.setPortName(getMSS_StatusQueryPortWSDDServiceName());
                return _stub;
            }
            if (MSS_ReceiptType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_ReceiptBindingStub _stub = new MSS_ReceiptBindingStub(new URL(MSS_ReceiptPort_address), this);
                _stub.setPortName(getMSS_ReceiptPortWSDDServiceName());
                return _stub;
            }
            if (MSS_NotificationPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_NotificationBindingStub _stub = new MSS_NotificationBindingStub(new URL(MSS_NotificationPort_address), this);
                _stub.setPortName(getMSS_NotificationPortWSDDServiceName());
                return _stub;
            }
            if (MSS_RegistrationType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_RegistrationBindingStub _stub = new MSS_RegistrationBindingStub(new URL(MSS_RegistrationPort_address), this);
                _stub.setPortName(getMSS_RegistrationPortWSDDServiceName());
                return _stub;
            }
            if (MSS_ProfileQueryType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_ProfileQueryBindingStub _stub = new MSS_ProfileQueryBindingStub(new URL(MSS_ProfileQueryPort_address), this);
                _stub.setPortName(getMSS_ProfileQueryPortWSDDServiceName());
                return _stub;
            }
            if (MSS_HandshakePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                MSS_HandshakeBindingStub _stub = new MSS_HandshakeBindingStub(new URL(MSS_HandshakePort_address), this);
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
     */
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

    public QName getServiceName() {
        return new QName("http://laverca.fi/mssp", "MSS_SignatureService");
    }

    private java.util.HashSet<QName> ports = null;

    public java.util.Iterator<QName> getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet<QName>();
            ports.add(new QName("http://laverca.fi/mssp", "MSS_SignaturePort"));
            ports.add(new QName("http://laverca.fi/mssp", "MSS_StatusQueryPort"));
            ports.add(new QName("http://laverca.fi/mssp", "MSS_ReceiptPort"));
            ports.add(new QName("http://laverca.fi/mssp", "MSS_NotificationPort"));
            ports.add(new QName("http://laverca.fi/mssp", "MSS_RegistrationPort"));
            ports.add(new QName("http://laverca.fi/mssp", "MSS_ProfileQueryPort"));
            ports.add(new QName("http://laverca.fi/mssp", "MSS_HandshakePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws ServiceException {
        
        if ("MSS_SignaturePort".equals(portName)) {
            setMSS_SignaturePortEndpointAddress(address);
        }
        else if ("MSS_StatusQueryPort".equals(portName)) {
            setMSS_StatusQueryPortEndpointAddress(address);
        }
        else if ("MSS_ReceiptPort".equals(portName)) {
            setMSS_ReceiptPortEndpointAddress(address);
        }
        else if ("MSS_NotificationPort".equals(portName)) {
            setMSS_NotificationPortEndpointAddress(address);
        }
        else if ("MSS_RegistrationPort".equals(portName)) {
            setMSS_RegistrationPortEndpointAddress(address);
        }
        else if ("MSS_ProfileQueryPort".equals(portName)) {
            setMSS_ProfileQueryPortEndpointAddress(address);
        }
        else if ("MSS_HandshakePort".equals(portName)) {
            setMSS_HandshakePortEndpointAddress(address);
        }
        else { // Unknown Port Name
            throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(QName portName, String address) throws ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }
}
