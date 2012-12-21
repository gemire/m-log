/**
 * 
 */
package org.mspring.mlog.web.module.admin.query;

import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since 2012-11-22
 * @Description
 * @TODO
 */
public class PhotoQueryCriterion extends AbstractQueryCriterion {
    private String queryString;
    private String countString;
    private String whereString;

    /**
     * 
     */
    @SuppressWarnings("rawtypes")
    public PhotoQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        QueryBuilder builder = new QueryBuilder(queryParams);
        builder.startBuild();
        builder.buildEqual("photo.album.id", "album.id", Long.class);
        whereString = builder.endBuild();

        namedQueryParams = builder.getNamedQueryParams();
        queryParamsString = builder.getQueryParamsAsString();

        queryString = "select photo from Photo photo ";
        countString = "select count(*) from Photo photo ";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.persistence.query.QueryCriterion#getQueryString()
     */
    @Override
    public String getQueryString() {
        // TODO Auto-generated method stub
        return queryString + whereString;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.persistence.query.QueryCriterion#getCountString()
     */
    @Override
    public String getCountString() {
        // TODO Auto-generated method stub
        return countString + whereString;
    }

}
