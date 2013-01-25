/**
 * 
 */
package org.mspring.mlog.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.Keys;
import org.mspring.mlog.entity.security.User;
import org.springframework.security.core.context.SecurityContextHolder;

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
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;
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
}
