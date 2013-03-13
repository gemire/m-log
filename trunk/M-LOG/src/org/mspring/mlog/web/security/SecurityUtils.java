/**
 * 
 */
package org.mspring.mlog.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.Keys;
import org.mspring.mlog.entity.security.User;
import org.mspring.platform.core.ContextManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * @author Administrator
 * @since Gao Youbo
 * @description
 * @TODO
 */
public class SecurityUtils {
    private static final Logger log = Logger.getLogger(SecurityUtils.class);

    /**
     * 获取当前登录用户的UserDetails
     * 
     * @return
     */
    public static UserDetailsImpl getUserDetailsImpl() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj != null && obj instanceof UserDetailsImpl) {
            return (UserDetailsImpl) obj;
        }
        return null;
    }

    /**
     * 获取当前登录的User对象
     * 
     * @return
     */
    public static User getCurrentUser() {
        UserDetailsImpl userDetailsImpl = getUserDetailsImpl();
        if (userDetailsImpl == null) {
            log.error("can't get logined user.");
            return null;
        }
        return userDetailsImpl.getUserEntity();
    }

    /**
     * 获取当前登录用户
     * 
     * @param request
     * @return
     */
    public static User getCurrentUser(HttpServletRequest request) {
        return getCurrentUser(request.getSession());
    }

    /**
     * 获取当前登录用户
     * 
     * @param session
     * @return
     */
    public static User getCurrentUser(HttpSession session) {
        Object obj = session.getAttribute(Keys.CURRENT_USER);
        if (obj != null && obj instanceof User) {
            return (User) obj;
        }
        return null;
    }

    /**
     * 重新加载UserDetails
     * 
     * @param username
     * @param request
     */
    public static void reloadUserDetails(String username, HttpServletRequest request) {
        UserDetailServiceImpl userDetailServiceImpl = ContextManager.getApplicationContext().getBean(UserDetailServiceImpl.class);
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);
        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        if (request != null) {
            authentication.setDetails(new WebAuthenticationDetails(request));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
