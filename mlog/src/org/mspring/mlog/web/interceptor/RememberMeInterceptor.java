/**
 * 
 */
package org.mspring.mlog.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.User;
import org.mspring.mlog.service.UserService;
import org.mspring.mlog.web.Keys;
import org.mspring.platform.utils.CookieUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Gao Youbo
 * @since 2012-7-18
 * @Description
 * @TODO 用于记住当前登录用户
 */
public class RememberMeInterceptor extends HandlerInterceptorAdapter {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            resolveRememberme(request, response);
        }
        return true;
    }

    public void resolveRememberme(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 如果session中不存在登录用户
        if (request.getSession().getAttribute(Keys.CURRENT_USER) == null) {
            String isRemember = CookieUtils.getCookie(request, Keys.IS_REMEMBER_USER_COOKIE);
            if (StringUtils.isNotBlank(isRemember) && "true".equals(isRemember)) {
                String username = CookieUtils.getCookie(request, Keys.USERNAME_COOKIE);
                String password = CookieUtils.getCookie(request, Keys.PASSWORD_COOKIE);
                if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                    User user = userService.login(username, password);
                    request.getSession().setAttribute(Keys.CURRENT_USER, user);
                }
            }
        }
    }

}
