/**
 * 
 */
package org.mspring.mlog.web.freemarker.render.tag;

import java.util.List;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.PostService;
import org.mspring.platform.persistence.support.Page;

import freemarker.template.SimpleHash;

/**
 * @author Gao Youbo
 * @since 2013-2-25
 * @description
 * @TODO 根据分类归档文章列表
 */
public class CatalogArchiveTag extends CacheRenderTag {
    /**
     * 
     */
    private static final long serialVersionUID = -2176550534653405856L;

    protected Long catalog;
    protected Integer maxResult = 20;

    public void setCatalog(Long catalog) {
        this.catalog = catalog;
    }

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    @Override
    protected String cachedProcess(SimpleHash model) {
        // TODO Auto-generated method stub
        if (catalog != null) {
            PostService postService = ServiceFactory.getPostService();
            Page<Post> page = new Page<Post>();
            page.setPageSize(maxResult);
            page = postService.findPostByCatalog(page, catalog);
            List<Post> posts = page.getResult();
            
            
            model.put("posts", posts);
            model.put("catalog", ServiceFactory.getCatalogService().getCatalogById(catalog));
            return getTemplateString(model);
        }
        return null;
    }

    @Override
    protected String getCacheKey(SimpleHash model) {
        // TODO Auto-generated method stub
        StringBuffer key = new StringBuffer();
        key.append(this.getClass().getName()).append(template + "_").append("catalog:" + catalog + "_").append("max:" + maxResult);
        return key.toString();
    }

}
