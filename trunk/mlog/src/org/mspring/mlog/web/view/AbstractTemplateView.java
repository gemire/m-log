package org.mspring.mlog.web.view;

import org.mspring.mlog.service.ArticleService;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTemplateView {
    protected CommentService commentService;
    protected ArticleService articleService;
    protected MenuService menuService;

    @Autowired
    public CommentService getCommentService() {
        return commentService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public abstract String json();
}
