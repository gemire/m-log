/**
 * 
 */
package org.mspring.mlog.inf.bae.cache.provider;

import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description
 * @TODO
 */
public class BaeCacheProviderUtils {
    /**
     * HIBERNATE_CACHE_KEY前缀
     */
    public static final String HIBERNATE_CACHE_KEY_PREFIX = "HIBERNATE_";

    /**
     * 获取cache key
     * 
     * @param key
     * @return
     */
    public static final String getHibernateCacheKey(Object key) {
        if (key == null || StringUtils.isBlank(key.toString())) {
            return "";
        }
        return HIBERNATE_CACHE_KEY_PREFIX + key;
    }

}
