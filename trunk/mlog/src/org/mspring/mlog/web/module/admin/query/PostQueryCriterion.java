/**
 * 
 */
package org.mspring.mlog.web.module.admin.query;

import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since 2012-8-8
 * @Description
 * @TODO
 */
public class PostQueryCriterion extends AbstractQueryCriterion {

    private String queryString;
    private String countString;
    private String whereString;

    /**
     * 
     */
    @SuppressWarnings("rawtypes")
    public PostQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        QueryBuilder builder = new QueryBuilder(queryParams);
        builder.startBuild().buildEqual("post.status", "status");
        builder.buildLike("post.title", "title");
        builder.buildLike("post.author.alias", "author.alias");
        whereString = builder.endBuild();

        namedQueryParams = builder.getNamedQueryParams();
        queryParamsString = builder.getQueryParamsAsString();

        queryString = "select post from Post post ";
        countString = "select count(*) from Post post ";
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
