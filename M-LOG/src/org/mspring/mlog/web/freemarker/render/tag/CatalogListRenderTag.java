/**
 * 
 */
package org.mspring.mlog.web.freemarker.render.tag;

import java.util.List;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Catalog;

import freemarker.template.SimpleHash;

/**
 * @author Gao Youbo
 * @since 2013-2-28
 * @description
 * @TODO 文章分类列表
 */
public class CatalogListRenderTag extends CacheRenderTag {

    /**
     * 
     */
    private static final long serialVersionUID = 1790603102008918439L;

    protected Long parentId;

    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    protected String cachedProcess(SimpleHash model) {
        // TODO Auto-generated method stub
        if (parentId == null) {
            parentId = new Long(0);
        }
        if (parentId != null && !parentId.equals(new Long(0))) {
            Catalog parentCatalog = ServiceFactory.getCatalogService().getCatalogById(parentId);
            model.put("parent", parentCatalog);
        }
        List<Catalog> catalogs = ServiceFactory.getCatalogService().findChildCatalogs(parentId);
        model.put("catalogs", catalogs);
        return getTemplateString(model);
    }

    @Override
    protected String getCacheKey(SimpleHash model) {
        // TODO Auto-generated method stub
        StringBuffer key = new StringBuffer();
        key.append(this.getClass().getName()).append(template + "_").append("parentId:" + parentId);
        return key.toString();
    }

}
