/**
 * 
 */
package org.mspring.mlog.web.security.voter;

import org.springframework.expression.Expression;
import org.springframework.security.access.ConfigAttribute;

/**
 * @author Gao Youbo
 * @since 2013-2-18
 * @description
 * @TODO
 */
public class DefaultWebExpressionConfigAttribute implements ConfigAttribute {

    /**
     * 
     */
    private static final long serialVersionUID = 6152664268766654132L;

    private final Expression authorizeExpression;

    public DefaultWebExpressionConfigAttribute(Expression authorizeExpression) {
        this.authorizeExpression = authorizeExpression;
    }

    Expression getAuthorizeExpression() {
        return authorizeExpression;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.access.ConfigAttribute#getAttribute()
     */
    @Override
    public String getAttribute() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return authorizeExpression.getExpressionString();
    }

}
