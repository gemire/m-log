/**
 * Mar 17, 20111:41:36 PM
 * www.mspring.org
 * @author (gaoyb)mspring
 */
package org.mspring.mlog.web.action.manage.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mspring.mlog.entity.Article;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.web.action.manage.article.query.ArticleQueryCriterion;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.model.field.ColumnField;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

/**
 * @author Gao Youbo
 * 
 */
public class QueryArticleAction extends AbstractManageAction implements QueryParameterAware {
    /**
     * 
     */
    private static final long serialVersionUID = 7700858438174253057L;

    private Map queryParameters;
    private QueryCriterion queryCriterion;

    public String getEncodedQueryParams() {
        if (queryCriterion != null) {
            return queryCriterion.getQueryParamsAsString();
        }
        return null;
    }

    public void setQueryParameters(Map queryParameters) {
        this.queryParameters = queryParameters;
    }

    private Page<Article> articlePage = new Page<Article>();
    private List<Category> categories;
    private Article article;

    public Page<Article> getArticlePage() {
        return articlePage;
    }

    public void setArticlePage(Page<Article> articlePage) {
        this.articlePage = articlePage;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String execute() {
        categories = categoryService.findAll();

        fieldColumns = new ArrayList<ColumnField>();
        fieldColumns.add(new ColumnField("id", getText("article.field.id")));
        fieldColumns.add(new ColumnField("category", getText("article.field.category")));
        fieldColumns.add(new ColumnField("title", getText("article.field.title")));
        fieldColumns.add(new ColumnField("createTime", getText("article.field.createtime")));
        fieldColumns.add(new ColumnField("viewNums", getText("article.field.click")));
        fieldColumns.add(new ColumnField("isTop", getText("article.field.istop")));

        if (articlePage.getSort() == null) {
            Sort sort = new Sort("article.id", Sort.DESC);
            articlePage.setSort(sort);
        }

        queryCriterion = new ArticleQueryCriterion(queryParameters);
        articlePage = articleService.queryArticle(articlePage, queryCriterion);
        return SUCCESS;
    }
}
