/**
 * 
 */
package org.mspring.mlog.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Gao Youbo
 * @since 2012-11-29
 * @Description
 * @TODO
 */
public class InstallInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = Logger.getLogger(InstallInterceptor.class);

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
        boolean hasInstalled = ServiceFactory.getInstallService().hasInstall();
        if (!hasInstalled) {
            if (!handler.getClass().getName().startsWith("org.mspring.mlog.web.module.install")) {
                log.debug("redirect to install page...");
                response.sendRedirect(request.getContextPath() + "/install/setup1");
                return false;
            }
        }
        return true;
    }

}
