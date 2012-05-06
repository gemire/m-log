/**
 * 
 */
package org.mspring.mlog.web.action.manage.comment.query;

import java.util.Date;
import java.util.Map;

import org.mspring.platform.dao.query.AbstractQueryCriterion;
import org.mspring.platform.dao.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since Mar 22, 2012
 */
public class CommentQueryCriterion extends AbstractQueryCriterion {

    private String queryString;
    private String countString;
    private String whereString;

    /**
     * 
     */
    public CommentQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        QueryBuilder queryBuilder = new QueryBuilder(queryParams);
        whereString = queryBuilder.startBuild()
        .buildLike("comment.author", "comment.author")
        .buildLike("comment.content", "comment.content")
        .buildGE("comment.createTime", "comment.createTime.begVal")
        .buildLE("comment.createTime", "comment.createTime.endVal")
        .buildEqual("comment.status", "comment.status", Integer.class)
        .endBuild();

        countString = "select count(*) from Comment comment " + whereString;
        queryString = "select comment from Comment comment " + whereString;

        namedQueryParams = queryBuilder.getNamedQueryParams();
        queryParamsString = queryBuilder.getQueryParamsAsString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.query.QueryCriterion#getCountString()
     */
    @Override
    public String getCountString() {
        // TODO Auto-generated method stub
        return countString;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.dao.query.QueryCriterion#getQueryString()
     */
    @Override
    public String getQueryString() {
        // TODO Auto-generated method stub
        return queryString;
    }

}
