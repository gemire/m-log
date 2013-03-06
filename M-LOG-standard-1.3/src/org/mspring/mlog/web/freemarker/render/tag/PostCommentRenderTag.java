/**
 * 
 */
package org.mspring.mlog.web.freemarker.render.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mspring.mlog.common.Keys;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Comment;
import org.mspring.platform.utils.CookieUtils;

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
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        
        model.put("comments", comments);
        model.put("postId", post);
        model.put("author", CookieUtils.getCookie(request, Keys.COMMENT_AUTHOR_COOKIE));
        model.put("email", CookieUtils.getCookie(request, Keys.COMMENT_EMAIL_COOKIE));
        model.put("url", CookieUtils.getCookie(request, Keys.COMMENT_URL_COOKIE));
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
