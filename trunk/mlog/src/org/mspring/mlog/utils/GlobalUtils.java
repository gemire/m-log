/**
 * 
 */
package org.mspring.mlog.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mspring.mlog.entity.User;
import org.mspring.mlog.web.common.Keys;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
public class GlobalUtils {
    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    public static User getCurrentUser(HttpServletRequest request) {
        return getCurrentUser(request.getSession());
    }

    /**
     * 获取当前登录用户
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
