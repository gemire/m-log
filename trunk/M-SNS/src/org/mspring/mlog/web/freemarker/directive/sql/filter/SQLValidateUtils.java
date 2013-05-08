/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.sql.filter;

import java.util.Map;

/**
 * @author Gao Youbo
 * @since 2013-1-4
 * @Description 
 * @TODO 
 */
public class SQLValidateUtils {
    /**
     * 获得验证结果(成功、失败)
     * @param params
     * @return
     */
    public final static boolean getValidateResult(Map params){
        if (params == null) {
            return false;
        }
        Object object = params.get(SQLValidateParamNames.VALIDATE_RESULT);
        if (object == null) {
            return false;
        }
        return (Boolean) object;
    }
    
    /**
     * 获取验证完成之后的SQL
     * @param params
     * @return
     */
    public final static String getValidatedSQL(Map params) {
        if (params == null) {
            return "";
        }
        Object object = params.get(SQLValidateParamNames.PARAM_SQL);
        if (object == null) {
            return "";
        }
        return object.toString();
    }
}
