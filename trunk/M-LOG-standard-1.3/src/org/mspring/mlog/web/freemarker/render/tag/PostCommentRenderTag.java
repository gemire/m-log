/**
 * 
 */
package org.mspring.mlog.web.freemarker.render.tag;

import java.util.List;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Comment;

import freemarker.template.SimpleHash;

/**
 * @author Gao Youbo
 * @since 2013-3-1
 * @description
 * @TODO
 */
public class PostCommentRenderTag extends CacheRenderTag {

    /**
     * 
     */
    private static final long serialVersionUID = -8679684665697439912L;

    protected Long post;

    public void setPost(Long post) {
        this.post = post;
    }

    @Override
    protected String cachedProcess(SimpleHash model) {
        // TODO Auto-generated method stub
        if (post == null) {
            return "";
        }
        List<Comment> comments = ServiceFactory.getCommentService().findCommentsByPost(post);
        model.put("postId", post);
        model.put("comments", comments);
        return getTemplateString(model);
    }

    @Override
    protected String getCacheKey(SimpleHash model) {
        // TODO Auto-generated method stub
        StringBuffer key = new StringBuffer();
        key.append(this.getClass().getName()).append(template + "_").append("post:" + post);
        return key.toString();
    }

}
