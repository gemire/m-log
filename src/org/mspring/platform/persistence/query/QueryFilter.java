/**
 * 
 */
package org.mspring.platform.persistence.query;

/**
 * @author Gao Youbo
 * @since Mar 21, 2012
 */
public abstract interface QueryFilter {
    public abstract boolean isQueryRequired(QueryContext paramQueryContext);
}