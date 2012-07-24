/**
 * 
 */
package org.mspring.mlog.web.module.web;

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

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}
