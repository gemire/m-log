/**
 * 
 */
package org.mspring.mlog.service.cache;

import java.util.List;

import net.sf.ehcache.Ehcache;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description
 * @TODO
 */
public interface CacheService {
    public static final long ONE_SECOND = 1;

    public static final long ONE_MINUTE = 60 * ONE_SECOND;

    public static final long ONE_HOUR = ONE_MINUTE * 60;

    public static final Long ONE_DAY = ONE_HOUR * 24;

    /**
     * 默认缓存时长为1分钟
     */
    public static final long DEFAULT_EXPIRY = ONE_MINUTE;

    /**
     * 将key-value存储在cache中，如果key已经存在，则旧的value将会被当前value所替代；如果key不存在，
     * 则将value存入cache
     * 
     * @param cacheName
     * @param key
     * @param value
     */
    public void updateCacheValue(String cacheName, String key, Object value);

    /**
     * 将key-value存储在cache中，如果key已经存在，则旧的value将会被当前value所替代；如果key不存在，
     * 则将value存入cache
     * 
     * @param cacheName
     * @param key
     * @param value
     * @param expiry
     *            缓存时间（单位：毫秒）
     */
    public void updateCacheValue(String cacheName, String key, Object value, long expiry);

    /**
     * 获取缓存
     * 
     * @param cacheName
     * @param key
     * @return
     */
    public Object getCacheValue(String cacheName, String key);

    /**
     * 删除缓存
     * 
     * @param cacheName
     */
    public void deleteCacheValue(String cacheName, String key);

    /**
     * 清空缓存
     * 
     * @param cacheName
     */
    public void clearCache(String cacheName);

    /**
     * 获取所有的CacheName
     * 
     * @return
     */
    public String[] getCacheNames();

    /**
     * 清空错误缓存
     */
    public void clearAllCache();

    /**
     * 获取Cache对象
     * 
     * @param cacheName
     * @return
     */
    public Ehcache getCache(String cacheName);

    /**
     * 获取Cache中缓存的所有对象的Key
     * 
     * @param cacheName
     * @return
     */
    public List getCacheKeys(String cacheName);

    public static class CacheName {
        public static final String DEFAULT_CACHE_NAME = "DEFAULT_CACHE";
        public static final String WIDGET_CACHE_NAME = "WIDGET_CACHE";
        public static final String OPTION_CACHE_NAME = "OPTION_CACHE";
        public static final String POST_PAGE_CACHE_NAME = "POST_PAGE_CACHE";
        public static final String LAZY_CACHE_NAME = "LazyCache";
    }
}
