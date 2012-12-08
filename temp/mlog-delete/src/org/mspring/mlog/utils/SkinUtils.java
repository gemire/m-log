/**
 * 
 */
package org.mspring.mlog.utils;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.web.common.OptionKeys;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.view.freemarker.ExtendsFreeMarkerViewResolver;

/**
 * @author Gao Youbo
 * @since 2012-8-16
 * @Description
 * @TODO
 */
public class SkinUtils {
    /**
     * 获取主题所在URL
     * 
     * @param skin
     * @param request
     * @return
     */
    public static String getTemplateUrl(HttpServletRequest request) {
        String skinFolder = ExtendsFreeMarkerViewResolver.getSkinfolder();
        StringBuffer template_url = new StringBuffer(request.getContextPath());
        if (!StringUtils.startsWith(skinFolder, "/")) {
            template_url.append("/");
        }
        template_url.append(skinFolder);
        if (!StringUtils.endsWith(skinFolder, "/")) {
            template_url.append("/");
        }
        template_url.append(getSkin());
        return template_url.toString();
    }

    public static String getSkin() {
        String skin = ServiceFactory.getOptionService().getOption(OptionKeys.CURRENT_SKIN);
        if (StringUtils.isBlank(skin)) {
            skin = "default";
        }
        return skin;
    }
}
