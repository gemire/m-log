/**
 * 
 */
package org.mspring.mlog.web.action.web;

import java.io.UnsupportedEncodingException;

import org.mspring.mlog.entity.Article;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since Apr 1, 2012
 */
public class SearchAction extends CommonWebActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 7582539429058927011L;

    private Page<Article> articlePage = new Page<Article>();

    private String queryString;

    public Page<Article> getArticlePage() {
        return articlePage;
    }

    public void setArticlePage(Page<Article> articlePage) {
        this.articlePage = articlePage;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        if (!StringUtils.isBlank(queryString)) {
            try {
                this.queryString = new String(queryString.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            this.queryString = "";
        }
    }

    public String execute() throws UnsupportedEncodingException {
        if (!StringUtils.isBlank(queryString)) {
            articlePage = articleSearchService.search(articlePage, queryString);
        }
        return SUCCESS;
    }

}
