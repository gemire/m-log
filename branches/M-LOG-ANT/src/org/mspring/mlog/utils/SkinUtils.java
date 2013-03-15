/**
 * 
 */
package org.mspring.mlog.utils;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.common.OptionKeys;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.platform.spring.web.view.freemarker.ExtendsFreeMarkerViewResolver;
import org.mspring.platform.utils.StringUtils;

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

    /**
     * 获取主题中的widget对应的路径
     * 
     * @return
     */
    public static String getWidgetUrl(String template) {
        String skinFolder = ExtendsFreeMarkerViewResolver.getSkinfolder();
        StringBuffer widgetUrl = new StringBuffer();
        if (!StringUtils.startsWith(skinFolder, "/")) {
            widgetUrl.append("/");
        }
        widgetUrl.append(skinFolder);
        if (!StringUtils.endsWith(skinFolder, "/")) {
            widgetUrl.append("/");
        }
        widgetUrl.append(getSkin()).append(template);
        return widgetUrl.toString();
    }

    public static String getSkin() {
        String skin = ServiceFactory.getOptionService().getOption(OptionKeys.CURRENT_SKIN);
        if (StringUtils.isBlank(skin)) {
            skin = "default";
        }
        return skin;
    }
}
