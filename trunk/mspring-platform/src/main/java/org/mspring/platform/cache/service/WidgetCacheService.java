/**
 * 
 */
package org.mspring.platform.cache.service;

import java.util.List;

import org.mspring.platform.cache.service.CacheService;

import net.sf.ehcache.Ehcache;

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

    public Ehcache getCache();

    public List getCacheKeys();
}
