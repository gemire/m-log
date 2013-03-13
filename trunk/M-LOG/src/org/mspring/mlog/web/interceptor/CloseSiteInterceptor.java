/**
 * 
 */
package org.mspring.mlog.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Gao Youbo
 * @since 2013-2-17
 * @description
 * @TODO 判断是否关闭站点
 */
public class CloseSiteInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private OptionService optionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        if ("false".equals(optionService.getOption("site_close"))) {
            return true;
        }
        if (handler.getClass().getName().startsWith("org.mspring.mlog.web.module.admin")) {
            return true;
        }
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/script")) {
            return true;
        } else if (requestURI.startsWith("/post")) {
            return true;
        } else if (requestURI.startsWith("/errors")) {
            return true;
        } else if (requestURI.startsWith("/common")) {
            return true;
        } else if (requestURI.startsWith("/metaweblog.do")) {
            return true;
        } else {
            request.getRequestDispatcher("/errors/site_close").forward(request, response);
            return false;
        }
    }

}
