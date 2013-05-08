/**
 * 
 */
package org.mspring.mlog.web.freemarker.render.tag;

import org.mspring.mlog.core.ServiceFactory;

import freemarker.template.SimpleHash;

/**
 * @author Gao Youbo
 * @since 2013-3-4
 * @description
 * @TODO
 */
public class AllCatalogListRenderTag extends CacheRenderTag {

    /**
     * 
     */
    private static final long serialVersionUID = 7330268367058981859L;

    @Override
    protected String cachedProcess(SimpleHash model) {
        // TODO Auto-generated method stub
        model.put("catalogs", ServiceFactory.getCatalogService().findAllCatalog());
        return getTemplateString(model);
    }

    @Override
    protected String getCacheKey(SimpleHash model) {
        // TODO Auto-generated method stub
        StringBuffer key = new StringBuffer();
        key.append(this.getClass().getName()).append(template);
        return key.toString();
    }

}
