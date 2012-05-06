/**
 * 
 */
package org.mspring.mlog.web.cache;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo (http://www.mspring.org)
 * @since 2011-12-3
 * @see 数据库查询缓存
 */
@Aspect
@Component
public class DBQueryCacheAspect {
    private static final Logger logger = Logger.getLogger(DBQueryCacheAspect.class);
    
    private OptionService optionService;

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    public Cache getDefaultCache() {
        return CacheManager.getInstance().getCache(CacheTokens.DB_QUERY_CACHE_NAME);
    }
    
    @Around("execution(* org.mspring.mlog.service.*.find*(..)) || execution(* org.mspring.mlog.service.*.get*(..)) || execution(* org.mspring.mlog.service.*.query*(..)) || execution(* org.mspring.mlog.service.*.load*(..))")
    public Object findAndCreateCache(ProceedingJoinPoint jp) throws Throwable {
        Object objectValue = null;
        String enableDBQueryCache = optionService._getOption(ConfigTokens.mspring_cache_enable_dbquery_cache);
        //如果启用查询缓存
        if ("1".equals(enableDBQueryCache)) {
            String cacheKey = getCacheKey(jp.getTarget().getClass().getName(), jp.getArgs());
            Element element = getDefaultCache().get(cacheKey);
            if (element == null) {
                logger.debug("Hold up method , Get method result and create cache........!");
                try {
                    Object cacheValue = jp.proceed();
                    element = new Element(cacheKey, cacheValue);
                    getDefaultCache().put(element);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            objectValue = element.getObjectValue();
        }
        else {
            objectValue = jp.proceed();
        }
        return objectValue;
    }

    @SuppressWarnings("unchecked")
    @AfterReturning(("execution(* org.mspring.mlog.service.*.create*(..)) || execution(* org.mspring.mlog.service.*.update*(..)) || execution(* org.mspring.mlog.service.*.delete*(..)) || execution(* org.mspring.mlog.service.*.remove*(..)) || execution(* org.mspring.mlog.service.*.upload*(..)) || execution(* org.mspring.mlog.service.*.merge*(..)) || execution(* org.mspring.mlog.service.*.set*(..))"))
    public void flushCache(JoinPoint jp) {
        String enableDBQueryCache = optionService.getOption(ConfigTokens.mspring_cache_enable_dbquery_cache);
        //如果启用查询缓存
        if ("1".equals(enableDBQueryCache)) {
            String className = jp.getTarget().getClass().getName();
            List<String> list = getDefaultCache().getKeys();
            for (int i = 0; i < list.size(); i++) {
                String cacheKey = String.valueOf(list.get(i));
                if (cacheKey.startsWith(className)) {
                    getDefaultCache().remove(cacheKey);
                    logger.debug("remove cache " + cacheKey);
                }
            }
        }
    }

    /**
     * 获得 cache key 的方法，cache key 是 Cache 中一个 Element 的唯一标识 cache key 包括
     * 包名+类名+方法名，如 com.co.cache.service.UserServiceImpl.getAllUser
     * 
     * @param targetName
     * @param methodName
     * @param arguments
     * @return
     */
    private String getCacheKey(String targetName, Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append(".").append(arguments[i]);
            }
        }
        return sb.toString();
    }
}
