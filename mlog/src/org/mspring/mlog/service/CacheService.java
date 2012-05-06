/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

/**
 * @author Gao Youbo
 * @since Jan 17, 2012 http://www.mspring.org
 */
public interface CacheService {
    /**
     * 删除
     * 
     * @param cacheName
     * @param key
     */
    public void removeCache(String cacheName, String key);

    /**
     * 删除文章缓存
     * 
     * @param cacheName
     * @param keyList
     */
    public void removeCaches(String cacheName, List<String> keyList);
    
    /**
     * 删除所有缓存
     * @param cacheName
     */
    public void removeAllCache(String cacheName);

    /**
     * 获取CacheManager对象
     * 
     * @return
     */
    public CacheManager getCacheManager();

    /**
     * 获取Cache对象
     * 
     * @param cacheName
     * @return
     */
    public Ehcache getEhcache(String cacheName);

    /**
     * 更新Cache
     * 
     * @param cacheName
     * @param key
     * @param value
     */
    public void updateValue(String cacheName, String key, Object value);

    /**
     * 更新Cache
     * 
     * @param cacheName
     * @param key
     * @param value
     * @param timeToLive
     */
    public void updateValue(String cacheName, String key, Object value, int timeToLive);

}
