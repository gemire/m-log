/**
 * 
 */
package org.mspring.mlog.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.WebContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO 将request、response加入到context变量中
 */
public class WebContextInterceptor extends HandlerInterceptorAdapter {
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
        WebContext.getInstance().setRequest(request);
        WebContext.getInstance().setResponse(response);
        return super.preHandle(request, response, handler);
    }
}
