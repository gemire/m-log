/**
 * 
 */
package org.mspring.mlog.web.freemarker.render.tag;

import java.util.List;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Post;

import freemarker.template.SimpleHash;

/**
 * @author Gao Youbo
 * @since 2013-2-26
 * @description
 * @TODO 最新发表文章列表
 */
public class RecentPostRenderTag extends CacheRenderTag {

    /**
     * 
     */
    private static final long serialVersionUID = -7490562021292665595L;

    protected Integer maxResult = 10;

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    @Override
    protected String cachedProcess(SimpleHash model) {
        // TODO Auto-generated method stub
        List<Post> posts = ServiceFactory.getPostService().getRecentPost(maxResult.intValue());
        model.put("posts", posts);
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
