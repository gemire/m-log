/**
 * 
 */
package org.mspring.mlog.web.freemarker.render.tag;

import java.util.List;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Link;

import freemarker.template.SimpleHash;

/**
 * @author Gao Youbo
 * @since 2013-2-26
 * @description
 * @TODO
 */
public class LinkRenderTag extends CacheRenderTag {

    /**
     * 
     */
    private static final long serialVersionUID = 4784049062111512288L;

    @Override
    protected String cachedProcess(SimpleHash model) {
        // TODO Auto-generated method stub
        List<Link> links = ServiceFactory.getLinkService().findVisableLinks();
        model.put("links", links);
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
