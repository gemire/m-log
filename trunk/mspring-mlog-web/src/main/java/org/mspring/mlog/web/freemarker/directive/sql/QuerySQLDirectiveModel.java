/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.sql;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.platform.utils.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-12-31
 * @Description 
 * @TODO 
 */
public class QuerySQLDirectiveModel extends AbstractSQLDirectiveModel {
    
    private static final Logger log = Logger.getLogger(QuerySQLDirectiveModel.class);
    
    public static final String KEY = "sql_query";

    /* (non-Javadoc)
     * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
     */
    @Override
    public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        String var = getVar(params);
        if (StringUtils.isBlank(var)) {
            log.warn("var name is blank, return.");
            return;
        }
        
        String sql = getSQL(params);
        if (StringUtils.isBlank(sql)) {
            log.warn("sql is blank, return.");
            return;
        }
        
        boolean cache = getCacheEnable(params);
        long expiry = getExpiry(params);
        
        Object value = null;
        if (cache) {
            value = getCacheValue(KEY, params);
        }
        
        if (value == null) {
            value = ServiceFactory.getHQLExecuteService().query(sql);
            SetCacheValue(KEY, value, params);
        }
        
        if (value == null) {
            value = ListUtils.EMPTY_LIST;
        }
        DirectiveUtils.setItem(env, var, value);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel#getKey()
     */
    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        return KEY;
    }

}
