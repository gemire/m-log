/**
 * 
 */
package org.mspring.platform.persistence.query;

import java.util.Map;

/**
 * @author Gao Youbo
 * @since Mar 21, 2012
 */
public abstract interface QueryCriterion {
    public static final String QUERY_PARAMS_KEY = "encodedQueryParams";

    public abstract String getQueryString();

    public abstract String getCountString();

    public abstract Map getQueryParams();

    public abstract String getQueryParamsAsString();
}