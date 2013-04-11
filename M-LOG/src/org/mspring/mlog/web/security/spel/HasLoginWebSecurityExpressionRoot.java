/**
 * 
 */
package org.mspring.mlog.web.security.spel;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

/**
 * @author Gao Youbo
 * @since 2013-2-17
 * @description
 * @TODO 判断用户是否登录
 */
public class HasLoginWebSecurityExpressionRoot extends WebSecurityExpressionRoot {

    /**
     * @param a
     * @param fi
     */
    public HasLoginWebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
        super(a, fi);
        // TODO Auto-generated constructor stub
    }

    public boolean isHasLogin() {
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return false;
        }
        return true;
    }

}
