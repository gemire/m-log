package org.mspring.platform.security.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SecurityConfigFactory {
    private static Log log = LogFactory.getLog(SecurityConfigFactory.class);
    private static SecurityConfig instance;

    public static synchronized SecurityConfig getConfig() {
        if (instance == null) {
            loadInstance(SecurityConfig.SECURITY_CONFIG_FILE);
        }
        return instance;
    }

    public static SecurityConfig getConfig(String configFileLocation) {
        if (instance == null) {
            loadInstance(configFileLocation);
        }
        return instance;
    }

    private static void loadInstance(String configFileLocation) {
        try {
            instance = new SecurityConfigImpl(configFileLocation);
        } catch (ConfigurationException e) {
            log.error("Couldn't load config file " + configFileLocation, e);
            throw e;
        }
    }
}