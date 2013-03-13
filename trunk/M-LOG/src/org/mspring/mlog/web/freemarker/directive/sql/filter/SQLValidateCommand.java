/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.sql.filter;

import java.util.HashMap;
import java.util.Map;

import freemarker.core.Environment;

/**
 * @author Gao Youbo
 * @since 2013-1-4
 * @Description
 * @TODO
 */
public class SQLValidateCommand {
    private Environment env;
    private String sql;

    /**
     * 
     */
    public SQLValidateCommand(Environment env, String sql) {
        // TODO Auto-generated constructor stub
        this.env = env;
        this.sql = sql;
    }

    public Map execute() {
        Map<Object, Object> params = new HashMap<Object, Object>();
        params.put(SQLValidateParamNames.ENVIROMENT_NAME, env);
        params.put(SQLValidateParamNames.VALIDATE_RESULT, true);
        params.put(SQLValidateParamNames.PARAM_SQL, sql);
        
        SQLValidateFilterChain chain = new SQLValidateFilterChain(params);
        chain.addFilter(new DeleteSQLValidateFilter());
        chain.addFilter(new ParameterValidateFilter());
        chain.doFilter();
        
        return params;
    }
}
