/**
 * 
 */
package org.mspring.mlog.web.action.web;

import java.util.List;
import java.util.Map;

import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.Article;
import org.mspring.mlog.event.ping.PingEvent;
import org.mspring.mlog.event.ping.PingEventType;
import org.mspring.mlog.web.action.manage.article.query.ArticleQueryCriterion;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.event.Event;
import org.mspring.platform.event.EventManager;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

/**
 * @author Gao Youbo
 * @since Mar 24, 2012
 */
public class IndexAction extends CommonWebActionSupport implements QueryParameterAware {
    /**
     * 
     */
    private static final long serialVersionUID = -5925120058563881866L;
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
    private Article article;
    private List<Article> articles;

    public Page<Article> getArticlePage() {
        return articlePage;
    }

    public void setArticlePage(Page<Article> articlePage) {
        this.articlePage = articlePage;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String execute() throws Exception {
        // TODO Auto-generated method stub
        if (articlePage.getSort() == null) {
            Sort sort = new Sort("article.id", Sort.DESC);
            articlePage.setSort(sort);
        }

        queryCriterion = new ArticleQueryCriterion(queryParameters);
        articlePage = articleService.queryArticle(articlePage, queryCriterion);
        articles = articlePage.getResult();
        Event event = new PingEvent("Source", PingEventType.PING);
        EventManager eventManager = ServiceFactory.getDefaultEventManager();
        eventManager.publishEvent(event);
        return SUCCESS;
    }
}
