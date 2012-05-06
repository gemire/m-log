/**
 * 
 */
package org.mspring.platform.utils;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class CacheUtils {

    public static Ehcache getCache(CacheManager cacheManager, String cacheName) {
        return cacheManager.getEhcache(cacheName);
    }

    public static Element getElement(CacheManager cacheManager, String cacheName, String key) {
        Ehcache ehcache = getCache(cacheManager, cacheName);
        return ehcache.get(key);
    }

    public static Object getObjectValue(CacheManager cacheManager, String cacheName, String key) {
        Element element = getElement(cacheManager, cacheName, key);
        return element == null ? null : element.getObjectValue();
    }

    public static String getStringValue(CacheManager cacheManager, String cacheName, String key) {
        Element element = getElement(cacheManager, cacheName, key);
        return element == null ? null : String.valueOf(element.getValue());
    }

    public static void updateValue(CacheManager cacheManager, String cacheName, String key, Object value) {
        Ehcache cache = getCache(cacheManager, cacheName);
        if (value != null)
            cache.put(new Element(key, value));
        else
            invalidateValue(cacheManager, cacheName, key);
    }

    public static void updateValue(CacheManager cacheManager, String cacheName, String key, Object value, int timeToLive) {
        Ehcache cache = getCache(cacheManager, cacheName);
        if (value != null) {
            Element element = new Element(key, value);
            element.setTimeToLive(timeToLive);
            element.setTimeToIdle(timeToLive / 2);
            cache.put(element);
        } else {
            invalidateValue(cacheManager, cacheName, key);
        }
    }

    public static void invalidateValue(CacheManager cacheManager, String cacheName, String key) {
        Ehcache cache = getCache(cacheManager, cacheName);
        if (cache != null)
            cache.remove(key);
    }
}
