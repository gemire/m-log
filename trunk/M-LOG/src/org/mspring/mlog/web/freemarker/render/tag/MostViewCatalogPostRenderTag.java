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
 * @TODO 分类下最高点击文章
 */
public class MostViewCatalogPostRenderTag extends CacheRenderTag {

    /**
     * 
     */
    private static final long serialVersionUID = -6356726975864503000L;

    protected Integer maxResult = 10;
    protected Long catalog;

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    public void setCatalog(Long catalog) {
        this.catalog = catalog;
    }

    @Override
    protected String cachedProcess(SimpleHash model) {
        // TODO Auto-generated method stub
        if (catalog == null) {
            return null;
        }
        List<Post> posts = ServiceFactory.getPostService().getMostViewCatalogPost(catalog, maxResult);
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
