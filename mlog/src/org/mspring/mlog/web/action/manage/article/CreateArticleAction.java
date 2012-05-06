package org.mspring.mlog.web.action.manage.article;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.entity.Article;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Mar 13, 2011
 */
public class CreateArticleAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = -452902203061369078L;
    
    private Article article;
    private String categoryIds;
    private String tagIds;


    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String toCreateArticle() {
        return SUCCESS;
    }

    public String doCreateArticle() {
        article.setCreateTime(new Date());
        article.setIp(ServletActionContext.getRequest().getRemoteAddr());
        articleService.createArticle(article, categoryIds, tagIds);
        // addActionMessage(getText("message.success"));
        // addCreateTabScript("002002");
        addRebuildTabScript("200001002");
        return SUCCESS;
    }
}
