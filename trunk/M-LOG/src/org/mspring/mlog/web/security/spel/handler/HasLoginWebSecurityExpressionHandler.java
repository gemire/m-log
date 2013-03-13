/**
 * 
 */
package org.mspring.mlog.web.security.spel.handler;

import org.mspring.mlog.web.security.spel.HasLoginWebSecurityExpressionRoot;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @author Gao Youbo
 * @since 2013-2-17
 * @description
 * @TODO
 */
public class HasLoginWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {

    @Override
    protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
        // TODO Auto-generated method stub
        HasLoginWebSecurityExpressionRoot root = new HasLoginWebSecurityExpressionRoot(authentication, fi);
        root.setPermissionEvaluator(getPermissionEvaluator());
        return root;
    }
}
