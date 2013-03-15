package org.mspring.platform.security.config;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mspring.platform.security.Initializable;
import org.mspring.platform.security.SecurityService;
import org.mspring.platform.security.auth.Authenticator;
import org.mspring.platform.security.controller.SecurityController;
import org.mspring.platform.security.interceptor.Interceptor;
import org.mspring.platform.security.support.IPMapper;
import org.mspring.platform.security.support.PathMapper;
import org.mspring.platform.security.support.XMLUtils;
import org.mspring.platform.utils.ClassLoaderUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SecurityConfigImpl implements SecurityConfig {
    private static final Log log = LogFactory.getLog(SecurityConfigImpl.class);
    // public static final String DEFAULT_CONFIG_LOCATION =
    // SecurityConfig.SECURITY_CONFIG_FILE;
    private String configFileLocation = SecurityConfig.SECURITY_CONFIG_FILE;

    private PathMapper excludeUrls = null;
    private List excludeIPs = null;
    private String loginURL;
    private String logoutURL;
    private String originalURLKey = "sentry_original_url";
    private String loggedInKey = "sentry_authenticator_user";
    private String loggedOutKey = "sentry_authenticator_logged_out_user";
    private String loginformUsernameKey = "sentry_username";
    private String loginformPasswordKey = "sentry_password";

    private Authenticator authenticator = null;
    private SecurityController controller;
    private List services = null;
    private List interceptors = null;

    private boolean useExternalAuthenticator = false;

    public SecurityConfigImpl(String configFileLocation) throws ConfigurationException {
        if (configFileLocation != null) {
            this.configFileLocation = configFileLocation;
            log.debug("Config file location passed. Location: " + this.configFileLocation);
        }
        else {
            log.debug("Initialising securityConfig using default configFile: " + this.configFileLocation);
        }

        init();
    }

    private void init() throws ConfigurationException {
        this.services = new ArrayList();
        this.interceptors = new ArrayList();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        URL fileURL = ClassLoaderUtils.getResource(this.configFileLocation, getClass());
        if (fileURL == null) {
            throw new IllegalArgumentException("No such xml file " + this.configFileLocation);
        }

        Document document = null;
        try {
            document = factory.newDocumentBuilder().parse(fileURL.toExternalForm());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ConfigurationException("Config " + this.configFileLocation + " error.", e);
        }

        Element root = document.getDocumentElement();

        configureParameters(root);
        configureAuthenticator(root);
        configureController(root);
        configureServices(root);
        configureInterceptors(root);
        configureExcludes(root);
    }

    public void destroy() {
        for (Iterator it = this.services.iterator(); it.hasNext();) {
            SecurityService securityService = (SecurityService) it.next();
            securityService.destroy();
        }
        for (Iterator it = this.interceptors.iterator(); it.hasNext();)
            ((Interceptor) it.next()).destroy();
    }

    private void configureParameters(Element root) {
        NodeList noteList = root.getElementsByTagName("parameters");
        Element parametersEl = (Element) noteList.item(0);
        Map globalParams = getInitParameters(parametersEl);

        this.loginURL = ((String) globalParams.get("login.url"));
        this.logoutURL = ((String) globalParams.get("logout.url"));

        if (globalParams.get("original.url.key") != null) {
            this.originalURLKey = ((String) globalParams.get("original.url.key"));
        }
        if (globalParams.get("logged.in.key") != null) {
            this.loggedInKey = ((String) globalParams.get("logged.in.key"));
        }
        if (globalParams.get("logged.out.key") != null) {
            this.loggedOutKey = ((String) globalParams.get("logged.out.key"));
        }
        if (globalParams.get("loginform.username.key") != null) {
            this.loginformUsernameKey = ((String) globalParams.get("loginform.username.key"));
        }
        if (globalParams.get("loginform.password.key") != null) this.loginformPasswordKey = ((String) globalParams.get("loginform.password.key"));
    }

    private void configureAuthenticator(Element root) {
        this.authenticator = ((Authenticator) configureClass(root, "authenticator"));
        if (this.authenticator == null) {
            log.info("No authenticator has been configured. Use external Authenticator.");
            this.useExternalAuthenticator = true;
        }
    }

    private void configureController(Element root) {
        this.controller = ((SecurityController) configureClass(root, "controller"));
        if (this.controller == null) try {
            this.controller = ((SecurityController) ClassLoaderUtils.instantiate(SecurityController.DEFAULT_CONTROLLER, getClass()));
        }
        catch (Exception e) {
            throw new ConfigurationException("Could not lookup class: " + SecurityController.DEFAULT_CONTROLLER + ":" + e);
        }
    }

    private void configureServices(Element rootEl) {
        NodeList nl = rootEl.getElementsByTagName("services");

        if ((nl != null) && (nl.getLength() > 0)) {
            Element servicesEl = (Element) nl.item(0);
            NodeList serviceList = servicesEl.getElementsByTagName("service");

            for (int i = 0; i < serviceList.getLength(); i++) {
                Element serviceEl = (Element) serviceList.item(i);
                String serviceClazz = serviceEl.getAttribute("class");

                if ((serviceClazz == null) || ("".equals(serviceClazz))) throw new ConfigurationException("Service element with bad class attribute");
                try {
                    log.debug("Adding seraph service of class: " + serviceClazz);
                    SecurityService service = (SecurityService) ClassLoaderUtils.instantiate(serviceClazz, getClass());
                    Map serviceParams = getInitParameters(serviceEl);
                    service.init(serviceParams, this);

                    this.services.add(service);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    throw new ConfigurationException("Could not getRequest service: " + serviceClazz + ". Exception: " + e);
                }
            }
        }
    }

    private void configureInterceptors(Element root) {
        NodeList nl = root.getElementsByTagName("interceptors");

        if ((nl != null) && (nl.getLength() > 0)) {
            Element interceptorsEl = (Element) nl.item(0);
            NodeList interceptorList = interceptorsEl.getElementsByTagName("interceptor");

            for (int i = 0; i < interceptorList.getLength(); i++) {
                Element interceptorEl = (Element) interceptorList.item(i);
                String interceptorClazz = interceptorEl.getAttribute("class");

                if ((interceptorClazz == null) || ("".equals(interceptorClazz))) throw new ConfigurationException("Interceptor element with bad class attribute");
                try {
                    log.debug("Adding interceptor of class: " + interceptorClazz);
                    Interceptor interceptor = (Interceptor) ClassLoaderUtils.instantiate(interceptorClazz, getClass());
                    Map interceptorParams = getInitParameters(interceptorEl);
                    interceptor.init(interceptorParams, this);

                    this.interceptors.add(interceptor);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    throw new ConfigurationException("Could not getRequest service: " + interceptorClazz + ". Exception: " + e);
                }
            }
        }
    }

    private Initializable configureClass(Element rootElement, String tagName) throws ConfigurationException {
        NodeList nl = rootElement.getElementsByTagName(tagName);
        String clazz = null;
        try {
            int i = 0;
            if (i < nl.getLength()) {
                Element el = (Element) nl.item(i);
                clazz = el.getAttribute("class");

                Initializable initializable = (Initializable) ClassLoaderUtils.loadClass(clazz, getClass()).newInstance();

                Map params = getInitParameters(el);
                initializable.init(params, this);

                return initializable;
            }
        }
        catch (Exception e) {
            throw new ConfigurationException("load class " + clazz + " error", e);
        }

        return null;
    }

    private Map getInitParameters(Element el) {
        Map params = new HashMap();

        NodeList nl = el.getElementsByTagName("init-param");
        for (int i = 0; i < nl.getLength(); i++) {
            Node initParam = nl.item(i);
            String paramName = XMLUtils.getContainedText(initParam, "param-name");
            String paramValue = XMLUtils.getContainedText(initParam, "param-value");
            params.put(paramName, paramValue);
        }

        return params;
    }

    private void configureExcludes(Element root) {
        clearExcludeUrls();

        NodeList nl = root.getElementsByTagName("excludes");
        if (nl == null) {
            return;
        }
        Element el = (Element) nl.item(0);

        Node child = el.getFirstChild();
        if (child == null) {
            return;
        }
        while (child != null) {
            String nodeName = child.getNodeName();
            if ("url-pattern".equals(nodeName)) {
                String pattern = XMLUtils.getElementText((Element) child);
                addExcludeUrl(pattern);
            }
            else if ("ip-pattern".equals(nodeName)) {
                addExcludeIp((Element) child);
            }
            child = child.getNextSibling();
        }
    }

    protected void addExcludeUrl(String path) {
        this.excludeUrls.put("", path);
    }

    protected void addExcludeIp(Element ipPatternElement) {
        String ipAddress = XMLUtils.getContainedText(ipPatternElement, "ip-address");

        if ((ipAddress != null) && (!ipAddress.equals(""))) {
            IPMapper ipMapper = new IPMapper();
            ipMapper.setIpAddress(ipAddress);

            NodeList nl = ipPatternElement.getElementsByTagName("url-pattern");
            if (nl != null) {
                Element el = null;
                for (int i = 0; i < nl.getLength(); i++) {
                    el = (Element) nl.item(i);
                    String pattern = XMLUtils.getElementText(el);
                    ipMapper.addExcludeUrl(pattern);
                }
            }

            this.excludeIPs.add(ipMapper);
        }
    }

    protected void clearExcludeUrls() {
        this.excludeUrls = new PathMapper();
        this.excludeIPs = new ArrayList();
    }

    public boolean isPathExcluded(String path, String remoteAddress) {
        String key = this.excludeUrls.get(path);
        if ("".equals(key)) {
            return true;
        }

        for (Iterator it = this.excludeIPs.iterator(); it.hasNext();) {
            IPMapper ipMapper = (IPMapper) it.next();
            if (ipMapper.isPathExcluded(path, remoteAddress)) {
                return true;
            }
        }

        return false;
    }

    public Authenticator getAuthenticator() {
        return this.authenticator;
    }

    public String getLoginURL() {
        return this.loginURL;
    }

    public String getLogoutURL() {
        return this.logoutURL;
    }

    public String getOriginalURLKey() {
        return this.originalURLKey;
    }

    public String getLoggedInKey() {
        return this.loggedInKey;
    }

    public String getLoggedOutKey() {
        return this.loggedOutKey;
    }

    public String getLoginformUsernameKey() {
        return this.loginformUsernameKey;
    }

    public String getLoginformPasswordKey() {
        return this.loginformPasswordKey;
    }

    public List getInterceptors(Class desiredInterceptorClass) {
        List result = new ArrayList();

        for (Iterator it = this.interceptors.iterator(); it.hasNext();) {
            Interceptor interceptor = (Interceptor) it.next();
            if (desiredInterceptorClass.isAssignableFrom(interceptor.getClass())) {
                result.add(interceptor);
            }
        }

        return result;
    }

    public List getServices() {
        return this.services;
    }

    public SecurityController getController() {
        return this.controller;
    }

    public boolean useExternalAuthenticator() {
        return this.useExternalAuthenticator;
    }
}