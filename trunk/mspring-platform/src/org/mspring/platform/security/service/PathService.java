package org.mspring.platform.security.service;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mspring.platform.security.SecurityService;
import org.mspring.platform.security.config.ConfigurationException;
import org.mspring.platform.security.config.SecurityConfig;
import org.mspring.platform.security.support.PathMapper;
import org.mspring.platform.security.support.XMLUtils;
import org.mspring.platform.utils.ClassLoaderUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PathService implements SecurityService {
    protected static Log log = LogFactory.getLog(PathService.class);

    private String configFileLocation = "sentry-paths.xml";
    private SecurityConfig securityConfig;
    private PathMapper pathMapper = new PathMapper();

    private Map paths = new HashMap();

    public void init(Map params, SecurityConfig config) {
        this.securityConfig = config;

        configurePathMapper();
    }

    public void destroy() {
    }

    public Set getRequiredRoles(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        Set requiredRoles = new HashSet();

        Collection constraintMatches = this.pathMapper.getAll(servletPath);

        for (Iterator it = constraintMatches.iterator(); it.hasNext();) {
            String constraint = (String) it.next();

            if (!requiredRoles.contains((String) this.paths.get(constraint))) {
                requiredRoles.add((String) this.paths.get(constraint));
            }
        }

        return requiredRoles;
    }

    private void configurePathMapper() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        URL fileUrl = ClassLoaderUtils.getResource(this.configFileLocation, getClass());

        if (fileUrl == null) {
            return;
        }

        Document doc = null;
        try {
            doc = factory.newDocumentBuilder().parse(fileUrl.toExternalForm());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ConfigurationException("Config " + this.configFileLocation + " error.", e);
        }

        Element root = doc.getDocumentElement();
        NodeList pathNodes = root.getElementsByTagName("path");

        for (int i = 0; i < pathNodes.getLength(); i++) {
            Element path = (Element) pathNodes.item(i);

            String pathName = path.getAttribute("name");
            String roleName = XMLUtils.getContainedText(path, "role-name");
            String urlPattern = XMLUtils.getContainedText(path, "url-pattern");

            if ((roleName != null) && (urlPattern != null)) {
                this.paths.put(pathName, roleName);
                this.pathMapper.put(pathName, urlPattern);
            }
        }
    }
}