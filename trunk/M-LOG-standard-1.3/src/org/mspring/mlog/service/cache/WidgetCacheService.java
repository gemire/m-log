/**
 * 
 */
package org.mspring.mlog.service.cache;

/**
 * @author Gao Youbo
 * @since 2013-1-30
 * @description
 * @TODO
 */
public interface WidgetCacheService {
    static final String CACHE_NAME = CacheService.CacheName.WIDGET_CACHE_NAME;

    public void updateWidgetCacheValue(String key, Object value);

    public void updateWidgetCacheValue(String key, Object value, long expiry);

    public Object getWidgetCacheValue(String key);

    public void deleteWidgetCacheValue(String key);

    public void clearWidgetCache();
}
