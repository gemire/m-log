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
import org.mspring.mlog.service.cache.CacheService;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.mlog.web.freemarker.directive.sql.filter.SQLValidateCommand;

import freemarker.core.Environment;
import freemarker.template.TemplateException;

/**
 * @author Gao Youbo
 * @since 2012-12-31
 * @Description
 * @TODO
 */
public abstract class AbstractSQLDirectiveModel extends AbstractDirectiveModel {
    private static final Logger log = Logger.getLogger(AbstractSQLDirectiveModel.class);

    /**
     * 定义参数名字
     * 
     * @author Gao Youbo
     * @since 2013-1-5
     * @Description
     * @TODO
     */
    protected static class PARAM_NAME {
        public static final String SQL = "sql";
        public static final String CACHE = "cache";
        public static final String EXPIRY = "expiry";
        public static final String VAR = "var";
        public static final String MAX_RESULT = "max";
        public static final String FIRST_RESULT = "first";
    }

    /**
     * 定义参数默认值
     * 
     * @author Gao Youbo
     * @since 2013-1-5
     * @Description
     * @TODO
     */
    protected static class DEFAULT_PARAM_VALUE {
        public static final boolean DEFAULT_CACHE = true;
        public static final long DEFAULT_EXPIRY = CacheService.ONE_MINUTE;
    }

    /**
     * 参数处理相关的通用类
     * 
     * @author Gao Youbo
     * @since 2013-1-5
     * @Description
     * @TODO
     */
    protected static class ParamUtils {
        /**
         * 获取SQL
         * 
         * @param params
         * @return
         */
        protected static String getSQL(Map params) {
            String sql = "";
            try {
                sql = DirectiveUtils.getString(PARAM_NAME.SQL, params);
            } catch (TemplateException e) {
                // TODO Auto-generated catch block
                log.debug("parameter " + PARAM_NAME.SQL + " is blank");
            }
            return sql;
        }

        /**
         * 获取是否进行缓存
         * 
         * @param params
         * @return
         */
        protected static boolean getCacheEnable(Map params) {
            boolean cacheEnable = DEFAULT_PARAM_VALUE.DEFAULT_CACHE;
            try {
                Boolean flag = DirectiveUtils.getBoolean(PARAM_NAME.CACHE, params);
                if (flag != null) {
                    cacheEnable = flag;
                }
            } catch (TemplateException e) {
                // TODO Auto-generated catch block
                log.debug("parameter " + PARAM_NAME.CACHE + " is blank, use default");
            }
            return cacheEnable;
        }

        /**
         * 获取缓存时间
         * 
         * @param params
         * @return
         */
        protected static long getExpiry(Map params) {
            long expiry = DEFAULT_PARAM_VALUE.DEFAULT_EXPIRY;
            try {
                Long flag = DirectiveUtils.getLong(PARAM_NAME.EXPIRY, params);
                if (flag != null) {
                    expiry = flag;
                }
            } catch (TemplateException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                log.debug("parameter " + PARAM_NAME.EXPIRY + " is blank, use default");
            }
            return expiry;
        }

        /**
         * 获取结果保存参数名
         * 
         * @param params
         * @return
         */
        protected static String getVar(Map params) {
            String var = "";
            try {
                var = DirectiveUtils.getString(PARAM_NAME.VAR, params);
            } catch (TemplateException e) {
                // TODO Auto-generated catch block
                log.debug("parameter " + PARAM_NAME.VAR + " is blank");
            }
            return var;
        }

        /**
         * 获取最大查询条数
         * 
         * @param params
         * @return
         */
        protected static Integer getMaxResult(Map params) {
            Integer max = 0;
            try {
                max = DirectiveUtils.getInt(PARAM_NAME.MAX_RESULT, params);
            } catch (TemplateException e) {
                // TODO Auto-generated catch block
                log.debug("parameter " + PARAM_NAME.MAX_RESULT + " is blank");
            }
            return max;
        }

        /**
         * 获取第一条起始位置
         * 
         * @param params
         * @return
         */
        protected static Integer getFirstResult(Map params) {
            Integer first = 0;
            try {
                first = DirectiveUtils.getInt(PARAM_NAME.FIRST_RESULT, params);
            } catch (TemplateException e) {
                // TODO Auto-generated catch block
                log.debug("parameter " + PARAM_NAME.FIRST_RESULT + " is blank");
            }
            return first;
        }
    }

    /**
     * 进入SQL Filter进行SQL语句校验
     * 
     * @param env
     * @param sql
     * @return
     */
    protected Map validateSQL(Environment env, String sql) {
        SQLValidateCommand cmd = new SQLValidateCommand(env, sql);
        return cmd.execute();
    }

    /**
     * 获取缓存值
     * 
     * @param cacheKey
     * @return
     */
    protected Object getCacheValue(String cacheKey) {
        return ServiceFactory.getWidgetCacheService().getWidgetCacheValue(cacheKey);
    }

    /**
     * 设置缓存值
     * 
     * @param cacheKey
     * @param cacheValue
     * @param expiry
     */
    protected void setCacheValue(String cacheKey, Object cacheValue, long expiry) {
        ServiceFactory.getWidgetCacheService().updateWidgetCacheValue(cacheKey, cacheValue, expiry);
    }

    /**
     * 获取缓存的KEY
     * 
     * @param directiveModelKey
     * @param params
     * @return
     */
    protected String getCacheKey(Map params) {
        String key = "";
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
