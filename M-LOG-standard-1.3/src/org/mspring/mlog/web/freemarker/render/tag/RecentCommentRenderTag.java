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
 * @since 2013-2-28
 * @description
 * @TODO
 */
public class RecentCommentRenderTag extends CacheRenderTag {

    /**
     * 
     */
    private static final long serialVersionUID = -1634387801491015398L;

    protected Integer maxResult = 10;

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    @Override
    protected String cachedProcess(SimpleHash model) {
        // TODO Auto-generated method stub
        List<Comment> comments = ServiceFactory.getCommentService().getRecentComment(maxResult.intValue());
        model.put("comments", comments);
        return getTemplateString(model);
    }

    @Override
    protected String getCacheKey(SimpleHash model) {
        // TODO Auto-generated method stub
        StringBuffer key = new StringBuffer();
        key.append(this.getClass().getName()).append(template + "_").append("max:" + maxResult);
        return key.toString();
    }

}
