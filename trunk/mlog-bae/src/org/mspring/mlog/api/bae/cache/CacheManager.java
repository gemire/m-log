/**
 * 
 */
package org.mspring.mlog.api.bae.cache;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.mspring.mlog.api.bae.cache.support.CacheBean;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.mspring.platform.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description
 * @TODO
 */
public class CacheManager {
    private static final Logger log = Logger.getLogger(CacheManager.class);
    private static final String DEFAULT_CACHE_NAME = "defaultCache";
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static CacheManager singleton;

    protected final Map<String, CacheBean> caches = new HashMap<String, CacheBean>();

    /**
     * 
     */
    public CacheManager() {
        // TODO Auto-generated constructor stub
        try {
            init(null, null);
        }
        catch (Exception e) {
            // TODO: handle exception
        }
    }

    public CacheManager(URL configURL) {
        try {
            init(configURL, null);
        }
        catch (Exception e) {
            // TODO: handle exception
        }
    }

    public CacheManager(InputStream configurationInputStream) {
        try {
            init(null, configurationInputStream);
        }
        catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void init(URL configURL, InputStream configurationInputStream) throws NullPointerException, XPathExpressionException, RuntimeException {
        Document doc = null;
        if (configURL != null) {
            doc = XMLUtils.parse(configURL);
        }
        else if (configurationInputStream != null) {
            doc = XMLUtils.parse(configurationInputStream);
        }
        else {
            URL url = ClassLoader.getSystemResource("baecache-hibernate.xml");
            doc = XMLUtils.parse(url);
        }
        List<Node> nodes = XMLUtils.parseForNodeList(doc, "baecache/cache");
        CacheBean bean = null;
        for (Node node : nodes) {
            String name = XMLUtils.parseForString(node, "@name");
            if (StringUtils.isNotBlank(name)) {
                String timeToIdleSeconds = XMLUtils.parseForString(node, "@timeToIdleSeconds");
                bean = new CacheBean(name, timeToIdleSeconds);
                caches.put(name, bean);
            }
        }

        // default cache
        CacheBean defaultCache = new CacheBean();
        defaultCache.setName(DEFAULT_CACHE_NAME);
        long defaultCacheTimeToIdleSeconds = CacheBean.DEFAULT_CACHE_TIME_SECONDS;
        Node defaultCacheNode = XMLUtils.parseForNode(doc, DEFAULT_CACHE_NAME);
        if (defaultCacheNode != null) {
            String str = XMLUtils.parseForString(defaultCacheNode, "@timeToIdleSeconds");
            if (StringUtils.isNotBlank(str) && ValidatorUtils.isNumber(str.trim())) {
                defaultCacheTimeToIdleSeconds = new Long(str.trim());
            }
        }
        defaultCache.setTimeToIdleSeconds(defaultCacheTimeToIdleSeconds);
        caches.put(DEFAULT_CACHE_NAME, defaultCache);
    }

    public static CacheManager create(InputStream inputStream) {
        synchronized (CacheManager.class) {
            if (singleton == null) {
                log.info("Creating new CacheManager with InputStream");
                singleton = new CacheManager(inputStream);
            }
            return singleton;
        }
    }

    public static CacheManager create(URL configLocation) {
        synchronized (CacheManager.class) {
            if (singleton == null) {
                log.info("Creating new CacheManager with config URL: " + configLocation);
                singleton = new CacheManager(configLocation);
            }
            return singleton;
        }
    }

    /**
     * 获取cache
     * 
     * @param name
     * @return
     */
    public synchronized CacheBean getCache(String name) {
        CacheBean cache = this.caches.get(name);
        if (cache == null) {
            log.info("Cache named " + name + " is null, get default cache.");
            cache = getDefaultCache();
        }
        return cache;
    }

    /**
     * 获取默认cache
     * 
     * @return
     */
    public CacheBean getDefaultCache() {
        return this.caches.get(DEFAULT_CACHE_NAME);
    }

    /**
     * 获取所有的CacheName
     * 
     * @return
     */
    public synchronized String[] getCacheNames() {
        String[] list = new String[this.caches.size()];
        return this.caches.keySet().toArray(list);
    }
}
