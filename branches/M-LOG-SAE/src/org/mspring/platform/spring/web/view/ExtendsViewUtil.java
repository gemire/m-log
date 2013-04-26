/**
 * 
 */
package org.mspring.platform.spring.web.view;

import javax.servlet.http.HttpServletRequest;

import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.Keys;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
public class ExtendsViewUtil {
    private String themePrefix;
    private String themeSuffix;
    private String skinfolder;

    /**
     * @param themePrefix
     * @param themeSuffix
     */
    public ExtendsViewUtil(String themePrefix, String themeSuffix, String skinfolder) {
        super();
        this.themePrefix = themePrefix;
        this.themeSuffix = themeSuffix;
        this.skinfolder = skinfolder;
    }

    public String getUrl(String beanName, HttpServletRequest request) {
        if (StringUtils.startsWith(beanName, Keys.SKIN_PREFIX)) {
            // Locale locale = RequestContextUtils.getLocale(request);
            String skin = getSkinFolder(beanName, request);
            String viewName = getViewName(beanName, request);
            
            StringBuffer url = new StringBuffer();
            url.append(this.skinfolder);
            if (!StringUtils.endsWith(this.skinfolder, "/")) {
                url.append("/");
            }
            url.append(skin);
            if (!StringUtils.startsWith(viewName, "/")) {
                url.append("/");
            }
            url.append(viewName);
            url.append(this.themeSuffix);
            return url.toString();
        }
        return null;
    }

    /**
     * 
     * @return
     */
    protected String getSkinFolder(String beanName, HttpServletRequest request) {
        String skin = (String) request.getAttribute("skin");
        if (StringUtils.isBlank(skin)) {
            skin = "default";
        }
        return skin;
    }

    /**
     * 获取当前view
     * 
     * @param beanName
     * @param request
     * @return
     */
    protected String getViewName(String beanName, HttpServletRequest request) {
        String viewName = StringUtils.substringAfter(beanName, Keys.SKIN_PREFIX);
        String domain = (String) request.getAttribute("domain");
        if (StringUtils.isNotBlank(domain)) {
            if (viewName.startsWith("/")) {
                viewName = domain + viewName;
            }
            else {
                viewName = domain + "/" + viewName;
            }
        }
        return viewName;
    }
}
