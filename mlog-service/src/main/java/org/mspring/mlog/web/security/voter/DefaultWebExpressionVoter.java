/**
 * 
 */
package org.mspring.mlog.web.security.voter;

import java.util.Collection;

import org.springframework.expression.EvaluationContext;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @author Gao Youbo
 * @since 2013-2-18
 * @description
 * @TODO
 */
public class DefaultWebExpressionVoter implements AccessDecisionVoter<FilterInvocation> {

    private SecurityExpressionHandler<FilterInvocation> expressionHandler = new DefaultWebSecurityExpressionHandler();

    public SecurityExpressionHandler<FilterInvocation> getExpressionHandler() {
        return expressionHandler;
    }

    public void setExpressionHandler(SecurityExpressionHandler<FilterInvocation> expressionHandler) {
        this.expressionHandler = expressionHandler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.access.AccessDecisionVoter#vote(org.
     * springframework.security.core.Authentication, java.lang.Object,
     * java.util.Collection)
     */
    @Override
    public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {
        // TODO Auto-generated method stub
        assert authentication != null;
        assert fi != null;
        assert attributes != null;

        DefaultWebExpressionConfigAttribute weca = findConfigAttribute(attributes);

        if (weca == null) {
            return ACCESS_ABSTAIN;
        }

        EvaluationContext ctx = expressionHandler.createEvaluationContext(authentication, fi);

        return ExpressionUtils.evaluateAsBoolean(weca.getAuthorizeExpression(), ctx) ? ACCESS_GRANTED : ACCESS_DENIED;
    }

    private DefaultWebExpressionConfigAttribute findConfigAttribute(Collection<ConfigAttribute> attributes) {
        for (ConfigAttribute attribute : attributes) {
            if (attribute instanceof DefaultWebExpressionConfigAttribute) {
                return (DefaultWebExpressionConfigAttribute) attribute;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.access.AccessDecisionVoter#supports(org.
     * springframework.security.access.ConfigAttribute)
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return attribute instanceof DefaultWebExpressionConfigAttribute;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.access.AccessDecisionVoter#supports(java
     * .lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return clazz.isAssignableFrom(FilterInvocation.class);
    }

}
