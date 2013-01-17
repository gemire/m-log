/**
 * 
 */
package org.mspring.mlog.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.OptionKeys;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.utils.SkinUtils;
import org.mspring.platform.web.Keys;
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
        // 处理通用参数
        request.setAttribute(OptionKeys.TEMPLATE_URL, SkinUtils.getTemplateUrl(request));
        request.setAttribute(Keys.REQUEST_KEY_BASE, request.getContextPath());
        return super.preHandle(request, response, handler);
    }

}
