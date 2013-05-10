/**
 * 
 */
package org.mspring.msns.web.query;

import java.util.Date;
import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO
 */
public class SpiderPostQueryCriterion extends AbstractQueryCriterion {

    private String queryString;
    private String countString;
    private String whereString;

    /**
     * 
     */
    public SpiderPostQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        QueryBuilder builder = new QueryBuilder(queryParams);
        builder.startBuild();
        builder.buildLike("spiderPost.title", "title");
        builder.buildEqual("spiderPost.posted", "posted", Boolean.class);
        builder.buildEqual("spiderPost.rule.id", "rule.id", Long.class);
        builder.buildBetween("spiderPost.createTime", "createTimeBeg", "createTimeEnd", Date.class);
        whereString = builder.endBuild();

        namedQueryParams = builder.getNamedQueryParams();
        queryParamsString = builder.getQueryParamsAsString();

        queryString = "select spiderPost from SpiderPost spiderPost ";
        countString = "select count(*) from SpiderPost spiderPost ";
    }

    @Override
    public String getQueryString() {
        // TODO Auto-generated method stub
        return queryString + whereString;
    }

    @Override
    public String getCountString() {
        // TODO Auto-generated method stub
        return countString + whereString;
    }

}
