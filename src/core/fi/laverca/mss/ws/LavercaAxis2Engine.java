/* ==========================================
 * Laverca Project
 * https://sourceforge.net/projects/laverca/
 * ==========================================
 * Copyright 2026 Laverca Project
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

import java.io.IOException;
import java.io.InputStream;

import org.apache.axis2.client.Options;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.deployment.AxisConfigBuilder;
import org.apache.axis2.engine.AxisConfiguration;

/**
 * The Axis2 client engine shared by all Laverca clients, the counterpart of Laverca 3's laverca-client-config.wsdd.
 * <p>
 * Built once, from a {@value #CLIENT_CONFIG_RESOURCE} on the classpath if there is one, else from Laverca's
 * built-in configuration. Every call sends with Laverca's own HttpClient 5 transport sender, so transport senders
 * declared in the file are not used.
 */
public final class LavercaAxis2Engine {

    /** Classpath resource of a user-supplied Axis2 client engine configuration (an axis2.xml). */
    public static final String CLIENT_CONFIG_RESOURCE = "laverca-axis2-client.xml";

    /** Laverca's built-in Axis2 client engine configuration, next to this class. */
    private static final String DEFAULT_CONFIG_RESOURCE = "laverca-axis2-default.xml";

    /** Axis1's default call timeout, 10 minutes; Axis2's own default is 30 seconds. */
    public static final long DEFAULT_TIMEOUT_MILLIS = 10 * 60 * 1000L;

    private static volatile ConfigurationContext configContext;

    private LavercaAxis2Engine() {
        // Static only
    }

    /**
     * Gets the shared client ConfigurationContext, building it on first use.
     *
     * @return the client ConfigurationContext
     * @throws IOException if the configuration can't be read or the engine can't be built
     */
    public static ConfigurationContext getConfigurationContext() throws IOException {
        ConfigurationContext ctx = LavercaAxis2Engine.configContext;
        if (ctx == null) {
            synchronized (LavercaAxis2Engine.class) {
                ctx = LavercaAxis2Engine.configContext;
                if (ctx == null) {
                    ctx = LavercaAxis2Engine.build();
                    LavercaAxis2Engine.configContext = ctx;
                }
            }
        }
        return ctx;
    }

    private static ConfigurationContext build() throws IOException {
        InputStream in = null;
        final ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        if (tccl != null) {
            in = tccl.getResourceAsStream(CLIENT_CONFIG_RESOURCE);
        }
        if (in == null) {
            in = LavercaAxis2Engine.class.getClassLoader().getResourceAsStream(CLIENT_CONFIG_RESOURCE);
        }
        if (in == null) {
            in = LavercaAxis2Engine.class.getResourceAsStream(DEFAULT_CONFIG_RESOURCE);
        }
        if (in == null) {
            throw new IOException("Axis2 client configuration " + DEFAULT_CONFIG_RESOURCE + " not found");
        }
        try (InputStream is = in) {
            final AxisConfiguration axisConfig = new AxisConfiguration();
            new AxisConfigBuilder(is, axisConfig, null).populateConfig();
            axisConfig.validateSystemPredefinedPhases();
            return new ConfigurationContext(axisConfig);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException("Unable to build the Axis2 client engine: " + e.getMessage(), e);
        }
    }

    /**
     * Creates new client Options with Axis1's 10-minute default call timeout instead of Axis2's 30 seconds.
     *
     * @return the Options
     */
    public static Options newOptions() {
        final Options opts = new Options();
        opts.setTimeOutInMilliSeconds(DEFAULT_TIMEOUT_MILLIS);
        return opts;
    }
}
