/**
 * 
 */
package org.mspring.mlog.web.action.manage.menu.query;

import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since Mar 22, 2012
 */
public class MenuQueryCriterion extends AbstractQueryCriterion {

    private String queryString;
    private String countString;
    private String whereString;

    /**
     * 
     */
    public MenuQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        QueryBuilder queryBuilder = new QueryBuilder(queryParams);
        whereString = queryBuilder.startBuild().endBuild();

        countString = "select count(*) from Menu menu " + whereString;
        queryString = "select menu from Menu menu " + whereString;

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
