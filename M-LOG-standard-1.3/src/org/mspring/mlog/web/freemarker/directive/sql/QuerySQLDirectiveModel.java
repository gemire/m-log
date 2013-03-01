/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.sql;

import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.mlog.web.freemarker.directive.sql.filter.SQLValidateUtils;
import org.mspring.platform.utils.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-12-31
 * @Description
 * @TODO
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class QuerySQLDirectiveModel extends AbstractSQLDirectiveModel {

    private static final Logger log = Logger.getLogger(QuerySQLDirectiveModel.class);

    @Override
    public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body) {
        // TODO Auto-generated method stub
        try {
            String var = ParamUtils.getVar(params);
            if (StringUtils.isBlank(var)) {
                log.warn("var name is blank, return.");
                return;
            }

            String sql = ParamUtils.getSQL(params);
            if (StringUtils.isBlank(sql)) {
                log.warn("sql is blank, return.");
                return;
            }
            boolean cache = ParamUtils.getCacheEnable(params);
            long expiry = ParamUtils.getExpiry(params);

            // 进行SQL验证
            Map validateResult = validateSQL(env, sql);
            boolean validateSuccess = SQLValidateUtils.getValidateResult(validateResult);
            if (!validateSuccess) {
                log.warn("sql validate failure.");
                return;
            }
            sql = SQLValidateUtils.getValidatedSQL(validateResult);

            // 获取CacheKey
            params.put(PARAM_NAME.SQL, sql);
            String cacheKey = getCacheKey(params);

            Object value = null;
            if (cache) {
                value = getCacheValue(cacheKey);
            }
            if (value == null) {
                Integer first = ParamUtils.getFirstResult(params);
                Integer max = ParamUtils.getMaxResult(params);
                value = ServiceFactory.getHQLExecuteService().query(sql, first, max);
                setCacheValue(cacheKey, value, expiry);
            }

            if (value == null) {
                value = ListUtils.EMPTY_LIST;
            }
            DirectiveUtils.setItem(env, var, value);
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());
        }
    }
}
