/**
 * 
 */
package org.mspring.platform.persistence.query.support;

import org.mspring.platform.persistence.query.QueryContext;
import org.mspring.platform.persistence.query.QueryFilter;
import org.mspring.platform.web.query.QueryParameterAware;

/**
 * @author Gao Youbo
 * @since Mar 21, 2012
 */
public class SimpleQueryFilter implements QueryFilter {

    private boolean requried = true;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.dao.query.QueryFilter#isQueryRequired(org.mspring
     * .platform.dao.query.QueryContext)
     */
    public boolean isQueryRequired(QueryContext queryContext) {
        // TODO Auto-generated method stub
        QueryParameterAware action = queryContext.getAction();
        if ((action instanceof QueryFilter)) {
            this.requried = ((QueryFilter) action).isQueryRequired(queryContext);
        }
        return (this.requried) && (new ParameterQueryFilter().isQueryRequired(queryContext));
    }

}
