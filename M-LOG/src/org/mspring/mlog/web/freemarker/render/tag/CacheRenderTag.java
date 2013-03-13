/**
 * 
 */
package org.mspring.mlog.web.freemarker.render.tag;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.service.cache.CacheService;

import freemarker.template.SimpleHash;

/**
 * @author Gao Youbo
 * @since 2013-2-25
 * @description
 * @TODO
 */
public abstract class CacheRenderTag extends AbstractRenderTag {

    /**
     * 
     */
    private static final long serialVersionUID = 6344201049998370026L;

    protected boolean cache = true;
    protected Long idle = CacheService.ONE_MINUTE;

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public void setIdle(Long idle) {
        this.idle = idle;
    }

    @Override
    protected String process(SimpleHash model) throws Exception {
        // TODO Auto-generated method stub
        String result = null;
        if (cache) {
            result = getCacheValue(model);
        }

        if (result != null) {
            return result;
        }

        result = cachedProcess(model);

        setCacheValue(result, model);

        return result;
    }

    protected abstract String cachedProcess(SimpleHash model);

    protected abstract String getCacheKey(SimpleHash model);

    protected String getCacheValue(SimpleHash model) {
        String key = getCacheKey(model);
        Object result = ServiceFactory.getWidgetCacheService().getWidgetCacheValue(key);
        return result == null ? null : result.toString();
    }

    protected void setCacheValue(String value, SimpleHash model) {
        String key = getCacheKey(model);
        ServiceFactory.getWidgetCacheService().updateWidgetCacheValue(key, value, idle);
    }
}
