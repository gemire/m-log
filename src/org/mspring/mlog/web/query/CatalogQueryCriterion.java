/**
 * 
 */
package org.mspring.mlog.web.query;

import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since 2012-8-8
 * @Description
 * @TODO
 */
public class CatalogQueryCriterion extends AbstractQueryCriterion {

    private String queryString;
    private String countString;
    private String whereString;

    /**
     * 
     */
    @SuppressWarnings("rawtypes")
    public CatalogQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        QueryBuilder builder = new QueryBuilder(queryParams);
        builder.startBuild();
        builder.buildLike("catalog.name", "name");
        
        Object parent = queryParams.get("parent.id");
        if (parent != null && "0".equals(parent.toString())) {
            builder.buildString("and catalog.parent is null");
        }
        else {
            builder.buildEqual("catalog.parent.id", "parent.id", Long.class);
        }
        whereString = builder.endBuild();

        namedQueryParams = builder.getNamedQueryParams();
        queryParamsString = builder.getQueryParamsAsString();

        queryString = "select catalog from Catalog catalog ";
        countString = "select count(*) from Catalog catalog ";
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
