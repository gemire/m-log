/**
 * 
 */
package org.mspring.mlog.web.action.web.query;

import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since May 21, 2012
 */
public class AlbumQueryCriterion extends AbstractQueryCriterion {
    
    private String queryString;
    private String countString;
    private String whereString;
    
    /**
     * 
     */
    public AlbumQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        QueryBuilder queryBuilder = new QueryBuilder(queryParams);
        queryBuilder.startBuild();
        
        whereString = queryBuilder.endBuild();
        
        queryString = "select album from Album album " + whereString;
        countString = "select count(*) from Album album " + whereString;
        
        namedQueryParams = queryBuilder.getNamedQueryParams();
        queryParamsString = queryBuilder.getQueryParamsAsString();
    }

    /* (non-Javadoc)
     * @see org.mspring.platform.dao.query.QueryCriterion#getCountString()
     */
    @Override
    public String getCountString() {
        // TODO Auto-generated method stub
        return countString;
    }

    /* (non-Javadoc)
     * @see org.mspring.platform.dao.query.QueryCriterion#getQueryString()
     */
    @Override
    public String getQueryString() {
        // TODO Auto-generated method stub
        return queryString;
    }

}
