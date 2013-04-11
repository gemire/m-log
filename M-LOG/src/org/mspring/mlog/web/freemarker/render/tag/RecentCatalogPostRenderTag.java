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
 * @TODO
 */
public class RecentCatalogPostRenderTag extends CacheRenderTag {

    /**
     * 
     */
    private static final long serialVersionUID = 4066812244594604362L;

    protected Long catalog;
    protected Integer maxResult = 10;

    public void setCatalog(Long catalog) {
        this.catalog = catalog;
    }

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    @Override
    protected String cachedProcess(SimpleHash model) {
        // TODO Auto-generated method stub
        if (catalog == null) {
            return null;
        }
        List<Post> posts = ServiceFactory.getPostService().getRecentCatalogPost(catalog, maxResult.intValue());
        model.put("posts", posts);
        return getTemplateString(model);
    }

    @Override
    protected String getCacheKey(SimpleHash model) {
        // TODO Auto-generated method stub
        StringBuffer key = new StringBuffer();
        key.append(this.getClass().getName()).append(template + "_").append("catalog:" + catalog + "_").append("max:" + maxResult);
        return key.toString();
    }

}
