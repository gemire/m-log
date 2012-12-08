/**
 * 
 */
package org.mspring.platform.persistence.query;

import java.util.Map;

import org.apache.commons.lang.ClassUtils;

/**
 * @author Gao Youbo
 * @since Mar 21, 2012
 */
public abstract class AbstractQueryCriterion implements QueryCriterion {

    protected Map namedQueryParams;
    protected String queryParamsString;

    @Override
    public Map getQueryParams() {
        // TODO Auto-generated method stub
        return this.namedQueryParams;
    }

    @Override
    public String getQueryParamsAsString() {
        // TODO Auto-generated method stub
        return this.queryParamsString;
    }

    public String toString() {
        return ClassUtils.getShortClassName(getClass()) + "[QueryString=" + getQueryString() + ", " + "CountString=" + getCountString() + ", " + "QueryParams=" + queryParamsString + "]";
    }

}
