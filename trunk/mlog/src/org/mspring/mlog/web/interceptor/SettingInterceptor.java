/**
 * 
 */
package org.mspring.mlog.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.web.OptionKeys;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.view.freemarker.ExtendsFreeMarkerViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
public class SettingInterceptor extends HandlerInterceptorAdapter {
    private OptionService optionService;

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
     * (javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        Map<String, String> options = optionService.getOptions();
        for (Map.Entry<String, String> entry : options.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        // 处理皮肤
        String skin = options.get(OptionKeys.CURRENT_SKIN);
        if (StringUtils.isBlank(skin)) {
            skin = "default";
        }
        //request.setAttribute(OptionKeys.CURRENT_SKIN, "default");

        // 处理通用参数
        request.setAttribute(OptionKeys.TEMPLATE_URL, getTemplateUrl(skin, request));
        return super.preHandle(request, response, handler);
    }

    /**
     * 获取主题所在URL
     * 
     * @param skin
     * @param request
     * @return
     */
    private String getTemplateUrl(String skin, HttpServletRequest request) {
        String skinFolder = ExtendsFreeMarkerViewResolver.getSkinfolder();
        StringBuffer template_url = new StringBuffer(request.getContextPath());
        if (!StringUtils.startsWith(skinFolder, "/")) {
            template_url.append("/");
        }
        template_url.append(skinFolder);
        if (!StringUtils.endsWith(skinFolder, "/")) {
            template_url.append("/");
        }
        template_url.append(skin);
        return template_url.toString();
    }

}
