/**
 * 
 */
package org.mspring.mlog.inf.bae;

import java.util.ArrayList;
import java.util.List;

import org.mspring.mlog.inf.bae.cache.support.Element;
import org.mspring.mlog.service.CacheService;
import org.mspring.mlog.service.OptionService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.bae.api.memcache.BaeMemcachedClient;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
@Service
public class BaeCacheServiceImpl implements CacheService {

    @Autowired
    private OptionService optionService;

    private static BaeMemcachedClient mc = null;

    public static BaeMemcachedClient getMemcachedClient() {
        if (mc == null) {
            mc = new BaeMemcachedClient();
        }
        return mc;
    }

    /**
     * 获取应用的Cache前缀
     * 
     * @return
     */
    public String getAppCachePrefix() {
        String prefix = optionService.getPropertiesOption("cache_prefix");
        if (StringUtils.isBlank(prefix)) {
            prefix = "MLOG_";
        }
        return prefix;
    }

    /**
     * 获取cache key
     * 
     * @param key
     * @return
     */
    public String getKey(String key) {
        // 获取缓存的前缀，前缀可以在后台进行设置，精良要和bae上其他应用的前缀区分开，避免cachekey相同照成缓存错乱的bug
        return getAppCachePrefix() + key;
    }

    /**
     * 将key-value存储在cache中，如果key已经存在，则旧的value将会被当前value所替代；如果key不存在，
     * 则将value存入cache
     * 
     * @param key
     * @param value
     */
    public void updateCacheValue(String key, Object value) {
        updateCacheValue(getKey(key), value, DEFAULT_EXPIRY);
    }

    /**
     * 将key-value存储在cache中，如果key已经存在，则旧的value将会被当前value所替代；如果key不存在，
     * 则将value存入cache
     * 
     * @param key
     * @param value
     * @param expiry
     *            缓存时间（单位：毫秒）
     */
    public void updateCacheValue(String key, Object value, long expiry) {
        BaeMemcachedClient mc = getMemcachedClient();
        if (mc == null) {
            return;
        }
        if (expiry == 0) {
            expiry = DEFAULT_EXPIRY;
        }
        Element element = new Element(value);
        mc.set(getKey(key), element, expiry);
        mc.set(getKey(key), value, expiry);
        addCacheKeys(getKey(key));
    }

    /**
     * 获取缓存
     * 
     * @param key
     * @return
     */
    public Object getCacheValue(String key) {
        BaeMemcachedClient mc = getMemcachedClient();
        if (mc == null) {
            return null;
        }
        key = getKey(key);
        if (!mc.keyExists(key)) {
            return null;
        }
        Object obj = mc.get(key);
        if (obj == null) {
            return null;
        }
        if (obj instanceof Element) {
            Element ele = (Element) obj;
            return ele.get();
        }
        return obj;
    }

    /**
     * 删除缓存
     * 
     * @param key
     */
    public void deleteCache(String key) {
        BaeMemcachedClient mc = getMemcachedClient();
        if (mc == null) {
            return;
        }
        mc.delete(key);
        removeCahceKeys(key);
    }

    public List<String> getCacheKeys() {
        Object obj = getCacheValue(KEYS_CACHE);
        if (obj != null && obj instanceof List) {
            return (List<String>) obj;
        }
        return null;
    }

    /************************************************************/
    // 私有方法
    /************************************************************/
    /**
     * 将应用中的所有的CACHE的KEY都放到这里CACHE里面
     * 
     * @param key
     */
    private void addCacheKeys(String key) {
        BaeMemcachedClient mc = getMemcachedClient();
        if (mc == null) {
            return;
        }
        String keys_key = getKey(KEYS_CACHE);
        Object obj = mc.get(keys_key);
        List<String> keys = null;
        if (obj != null && obj instanceof List) {
            keys = (List) obj;
        }
        else {
            keys = new ArrayList<String>();
        }
        if (!keys.contains(key)) {
            keys.add(key);
        }

        if (keys != null && keys.size() > 0) {
            // 这里的expiry要设置的足够大，这里设置的为一年
            mc.set(keys_key, keys, 86400 * 365);
        }
    }

    /**
     * 删除当前系统中所存在的Cache keys
     * 
     * @param keys
     */
    private void removeCahceKeys(String key) {
        BaeMemcachedClient mc = getMemcachedClient();
        if (mc == null) {
            return;
        }
        String keys_key = getKey(KEYS_CACHE);
        Object obj = mc.get(keys_key);
        List<String> keys = null;
        if (obj != null && obj instanceof List) {
            keys = (List) obj;
            keys.remove(key);
            mc.set(keys_key, keys, 86400 * 365);
        }
    }
}
