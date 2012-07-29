/**
 * 
 */
package org.mspring.mlog.web.module.web;

import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
public abstract class AbstractWebWidget {
    protected PostService postService;
    protected CatalogService catalogService;
    protected CommentService commentService;
    protected OptionService optionService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

}
