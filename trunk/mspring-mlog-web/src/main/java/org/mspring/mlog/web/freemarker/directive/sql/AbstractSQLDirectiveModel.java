/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.sql;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.SetUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.service.CacheService;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;

import freemarker.template.TemplateException;

/**
 * @author Gao Youbo
 * @since 2012-12-31
 * @Description
 * @TODO
 */
public abstract class AbstractSQLDirectiveModel extends AbstractDirectiveModel {
    private static final Logger log = Logger.getLogger(AbstractSQLDirectiveModel.class);

    protected static class PARAM_NAME {
        public static final String SQL = "sql";
        public static final String CACHE = "cache";
        public static final String EXPIRY = "expiry";
        public static final String VAR = "var";
    }

    protected static class DEFAULT_PARAM_VALUE {
        public static final boolean DEFAULT_CACHE = true;
        public static final long DEFAULT_EXPIRY = CacheService.ONE_MINUTE;
    }

    protected String getSQL(Map params) {
        String sql = "";
        try {
            sql = DirectiveUtils.getString(PARAM_NAME.SQL, params);
        }
        catch (TemplateException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            log.debug("parameter " + PARAM_NAME.SQL + " is blank");
        }
        return sql;
    }

    protected boolean getCacheEnable(Map params) {
        boolean cacheEnable = DEFAULT_PARAM_VALUE.DEFAULT_CACHE;
        try {
            cacheEnable = DirectiveUtils.getBoolean(PARAM_NAME.CACHE, params);
        }
        catch (TemplateException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            log.debug("parameter " + PARAM_NAME.CACHE + " is blank, use default");
        }
        return cacheEnable;
    }

    protected long getExpiry(Map params) {
        long expiry = DEFAULT_PARAM_VALUE.DEFAULT_EXPIRY;
        try {
            expiry = DirectiveUtils.getLong(PARAM_NAME.EXPIRY, params);
        }
        catch (TemplateException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            log.debug("parameter " + PARAM_NAME.EXPIRY + " is blank, use default");
        }
        return expiry;
    }
    
    protected String getVar(Map params){
        String var = "";
        try {
            var = DirectiveUtils.getString(PARAM_NAME.VAR, params);
        }
        catch (TemplateException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            log.debug("parameter " + PARAM_NAME.VAR + " is blank");
        }
        return var;
    }

    protected Object getCacheValue(String cacheKey) {
        return ServiceFactory.getCacheService().getCacheValue(cacheKey);
    }

    protected Object getCacheValue(String directiveModelKey, Map params) {
        String cacheKey = getCacheKey(directiveModelKey, params);
        return ServiceFactory.getCacheService().getCacheValue(cacheKey);
    }

    protected void setCacheValue(String cacheKey, Object cacheValue, long expiry) {
        ServiceFactory.getCacheService().updateCacheValue(cacheKey, cacheValue, expiry);
    }

    protected void SetCacheValue(String directiveModelKey, Object cacheValue, Map params) {
        String cacheKey = getCacheKey(directiveModelKey, params);
        long expiry = getExpiry(params);
        ServiceFactory.getCacheService().updateCacheValue(cacheKey, cacheValue, expiry);
    }

    protected String getCacheKey(String directiveModelKey, Map params) {
        String key = directiveModelKey;
        Set keySet = SetUtils.orderedSet(params.keySet());
        Iterator it = keySet.iterator();
        while (it.hasNext()) {
            Object k = it.next();
            Object v = params.get(k);

            key += k + ":" + v;
        }
        return key;
    }
}
