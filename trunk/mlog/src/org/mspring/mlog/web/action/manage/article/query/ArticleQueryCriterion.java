/**
 * 
 */
package org.mspring.mlog.web.action.manage.article.query;

import java.util.Map;

import org.mspring.platform.dao.query.AbstractQueryCriterion;
import org.mspring.platform.dao.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since Mar 21, 2012
 */
public class ArticleQueryCriterion extends AbstractQueryCriterion {

    private String queryString;
    private String countString;
    private String whereString;

    /**
     * 
     */
    public ArticleQueryCriterion(Map queryParams) {
        QueryBuilder queryBuilder = new QueryBuilder(queryParams);

        // queryBuilder.startBuild();
        // queryBuilder.buildString(" and article.id = ac.PK.article.id ");
        // queryBuilder.buildLike("article.title", "article.title");
        // queryBuilder.buildEqual("ac.PK.category.id", "article.category.id",
        // Long.class);
        // queryBuilder.buildGE("article.createTime",
        // "article.createTime.begVal");
        // queryBuilder.buildLE("article.createTime",
        // "article.createTime.endVal");
        // whereString = queryBuilder.endBuild();
        //
        // countString = "select count(distinct article) from Article article,
        // ArticleCategory ac " + whereString;
        // queryString = "select distinct article from Article article,
        // ArticleCategory
        // ac " + whereString;

        queryBuilder.startBuild();
        if (queryParams.get("article.category.id") != null && "0".equals(queryParams.get("article.category.id").toString().trim())) {
            //处理未分类文章
            queryBuilder.buildString(" and categories.id is null ");
        }
        else {
            queryBuilder.buildEqual("categories.id", "article.category.id", Long.class);
        }
        queryBuilder.buildEqual("tags.id", "article.tag.id", Long.class);
        queryBuilder.buildLike("article.title", "article.title");
        queryBuilder.buildGE("article.createTime", "article.createTime.begVal");
        queryBuilder.buildLE("article.createTime", "article.createTime.endVal");
        whereString = queryBuilder.endBuild();

        countString = "select count(distinct article) from Article article LEFT OUTER JOIN article.categories categories LEFT OUTER JOIN article.tags tags " + whereString;
        queryString = "select distinct article from Article article LEFT OUTER JOIN article.categories categories LEFT OUTER JOIN article.tags tags " + whereString;

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
