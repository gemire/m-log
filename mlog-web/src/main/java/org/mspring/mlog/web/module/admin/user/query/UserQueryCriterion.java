/**
 * 
 */
package org.mspring.mlog.web.module.admin.user.query;

import java.util.Date;
import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2013-1-15
 * @Description
 * @TODO
 */
public class UserQueryCriterion extends AbstractQueryCriterion {

    private String queryString;
    private String countString;
    private String whereString;

    /**
     * 
     */
    public UserQueryCriterion(Map queryParams) {
        // TODO Auto-generated constructor stub
        QueryBuilder builder = new QueryBuilder(queryParams);

        builder.startBuild();
        if (queryParams.get("PK.role.id") == null || StringUtils.isBlank(queryParams.get("PK.role.id").toString())) {
            queryString = "select user from User user";
            countString = "select count(*) from User user";

            builder.buildLike("user.name", "PK.user.name");
            builder.buildLike("user.alias", "PK.user.alias");
            builder.buildLike("user.email", "PK.user.email");
            builder.buildBetween("user.createTime", "createTimeBeg", "createTimeEnd", Date.class);
        } else {
            queryString = "select userRole.PK.user from UserRole userRole";
            countString = "select count(userRole.PK.user) from UserRole userRole";

            builder.buildLike("userRole.PK.user.name", "PK.user.name");
            builder.buildLike("userRole.PK.user.alias", "PK.user.alias");
            builder.buildLike("userRole.PK.user.email", "PK.user.email");
            builder.buildBetween("userRole.PK.user.createTime", "createTimeBeg", "createTimeEnd", Date.class);

            builder.buildEqual("userRole.PK.role.id", "PK.role.id", Long.class);
        }
        whereString = builder.endBuild();

        namedQueryParams = builder.getNamedQueryParams();
        queryParamsString = builder.getQueryParamsAsString();

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
