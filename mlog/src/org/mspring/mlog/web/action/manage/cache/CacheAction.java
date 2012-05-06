/**
 * 
 */
package org.mspring.mlog.web.action.manage.cache;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.entity.cache.CacheEntity;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.web.cache.CacheTokens;
import org.mspring.platform.dao.support.Page;
import org.mspring.platform.utils.ListUtils;

/**
 * @author Gao Youbo
 * @since Apr 15, 2012
 */
public class CacheAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = -1628303449411774563L;
    
    private Page<CacheEntity> elementPage = new Page<CacheEntity>();
    private String[] keys;
    private String enablePageCache;
    private String enableDBQueryCache;

    public Page<CacheEntity> getElementPage() {
        return elementPage;
    }

    public void setElementPage(Page<CacheEntity> elementPage) {
        this.elementPage = elementPage;
    }

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public String getEnablePageCache() {
        return enablePageCache;
    }

    public void setEnablePageCache(String enablePageCache) {
        this.enablePageCache = enablePageCache;
    }

    public String getEnableDBQueryCache() {
        return enableDBQueryCache;
    }

    public void setEnableDBQueryCache(String enableDBQueryCache) {
        this.enableDBQueryCache = enableDBQueryCache;
    }

    /**
     * 显示页面缓存列表
     * 
     * @return
     */
    public String listPageCache() {
        Ehcache cache = cacheService.getEhcache(CacheTokens.PAGE_CACHE_NAME);
        List<Object> keys = cache.getKeys();

        elementPage.setTotalCount(keys.size());

        List<CacheEntity> cacheEntities = new ArrayList<CacheEntity>();
        int start = (elementPage.getPageNo() - 1) * elementPage.getPageSize();
        int end = (elementPage.getPageNo() - 1) * elementPage.getPageSize() + elementPage.getPageSize();
        for (int i = start; i < end; i++) {
            if (keys.size() > i) {
                Object key = keys.get(i);
                Element element = cache.getQuiet(key);
                CacheEntity entity = CacheEntity.elementToCacheEntity(element);
                if (entity != null) {
                    cacheEntities.add(entity);
                }
            } else {
                break;
            }
        }
        elementPage.setResult(cacheEntities);
        enablePageCache = optionService.getOption(ConfigTokens.mspring_cache_enable_page_cache);
        enableDBQueryCache = optionService.getOption(ConfigTokens.mspring_cache_enable_dbquery_cache);
        return SUCCESS;
    }

    /**
     * 删除缓存
     * 
     * @return
     */
    public String removePageCache() {
        cacheService.removeCaches(CacheTokens.PAGE_CACHE_NAME, ListUtils.arrayToList(keys));
        return SUCCESS;
    }

    /**
     * 清空页面缓存
     * 
     * @return
     */
    public String clearPageCache() {
        Ehcache cache = cacheService.getEhcache(CacheTokens.PAGE_CACHE_NAME);
        cache.removeAll();
        return SUCCESS;
    }

    /**
     * 是否启用页面缓存
     * 
     * @return
     */
    public String enablePageCache() {
        optionService.setOption(ConfigTokens.mspring_cache_enable_page_cache, enablePageCache);
        // 如果关闭页面缓存，清空所有页面缓存
        if ("0".equals(enablePageCache)) {
            cacheService.removeAllCache(CacheTokens.PAGE_CACHE_NAME);
        }
        return SUCCESS;
    }

    /**
     * 是否启用查询缓存
     * 
     * @return
     */
    public String enableDBQueryCache() {
        optionService.setOption(ConfigTokens.mspring_cache_enable_dbquery_cache, enableDBQueryCache);
        // 如果关闭查询缓存，清空所有查询缓存
        if ("0".equals(enableDBQueryCache)) {
            cacheService.removeAllCache(CacheTokens.DB_QUERY_CACHE_NAME);
        }
        return SUCCESS;
    }
}
