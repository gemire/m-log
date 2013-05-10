/**
 * 
 */
package org.mspring.msns.web.query;

import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * @author Administrator
 * @since Gao Youbo
 * @description
 * @TODO
 */
public class RuleQueryCriterion extends AbstractQueryCriterion {

    private String queryString;
    private String countString;
    private String whereString;

    /**
     * 
     */
    public RuleQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        QueryBuilder builder = new QueryBuilder(queryParams);
        builder.startBuild();
        whereString = builder.endBuild();

        namedQueryParams = builder.getNamedQueryParams();
        queryParamsString = builder.getQueryParamsAsString();

        queryString = "select rule from Rule rule ";
        countString = "select count(*) from Rule rule ";
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
