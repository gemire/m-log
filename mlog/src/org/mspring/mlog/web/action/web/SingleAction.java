/**
 * 
 */
package org.mspring.mlog.web.action.web;

import java.util.List;

import org.mspring.mlog.entity.Article;
import org.mspring.mlog.entity.Comment;

/**
 * @author Gao Youbo
 * @since Jan 17, 2012 http://www.mspring.org
 */
public class SingleAction extends CommonWebActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -8300271599924542677L;
    private Long id;
    private Article article;
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String execute() {
        try {
            article = articleService.findArticleById(id);
            comments = commentService.findAuditPassCommentsByArticle(id);
        } catch (Exception e) {
            // TODO: handle exception
            return EXCEPTION;
        }
        return SUCCESS;
    }
}
