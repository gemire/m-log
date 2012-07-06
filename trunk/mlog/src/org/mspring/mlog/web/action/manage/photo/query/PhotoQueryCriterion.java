/**
 * 
 */
package org.mspring.mlog.web.action.manage.photo.query;

import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since Jan 9, 2012
 */
public class PhotoQueryCriterion extends AbstractQueryCriterion {

    private String queryString;
    private String countString;
    private String whereString;

    /**
     * 
     */
    public PhotoQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        QueryBuilder queryBuilder = new QueryBuilder(queryParams);
        whereString = queryBuilder.startBuild()
        .buildEqual("photo.album.id", "photo.album.id", Long.class)
        .buildLike("photo.name", "photo.name")
        .endBuild();

        queryString = "select photo from Photo as photo";
        countString = "select count(*) from Photo as photo";

        namedQueryParams = queryBuilder.getNamedQueryParams();
        queryParamsString = queryBuilder.getQueryParamsAsString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.model.query.QueryCriterion#getQueryString()
     */
    @Override
    public String getQueryString() {
        // TODO Auto-generated method stub
        return queryString + whereString;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.model.query.AbstractQueryCriterion#getCountString()
     */
    @Override
    public String getCountString() {
        // TODO Auto-generated method stub
        return countString + whereString;
    }
}
