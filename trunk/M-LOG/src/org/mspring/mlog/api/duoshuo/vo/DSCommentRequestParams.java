/**
 * 
 */
package org.mspring.mlog.api.duoshuo.vo;

import java.util.List;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO
 */
public class DSCommentRequestParams extends DSRequestParams {
    private List<DSComment> posts;

    public List<DSComment> getPosts() {
        return posts;
    }

    public void setPosts(List<DSComment> posts) {
        this.posts = posts;
    }

}
