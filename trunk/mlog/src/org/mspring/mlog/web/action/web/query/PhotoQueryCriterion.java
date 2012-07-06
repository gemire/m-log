/**
 * 
 */
package org.mspring.mlog.web.action.web.query;

import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since May 26, 2012
 */
public class PhotoQueryCriterion extends AbstractQueryCriterion {
    
    private String whereString;
    private String queryString;
    private String countString;
    
    
    /**
     * 
     */
    public PhotoQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        
        QueryBuilder queryBuilder = new QueryBuilder(queryParams);
        queryBuilder.startBuild();
        queryBuilder.buildEqual("photo.album.id", "albumId", Long.class);
        whereString = queryBuilder.endBuild();
        
        queryString = "select photo from Photo photo " + whereString;
        countString = "select count(*) from Photo photo " + whereString;
        
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
