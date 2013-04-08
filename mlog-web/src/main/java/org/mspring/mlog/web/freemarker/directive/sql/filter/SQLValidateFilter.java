/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.sql.filter;

import java.util.Map;

/**
 * @author Gao Youbo
 * @since 2012-12-28
 * @Description 
 * @TODO 
 */
public interface SQLValidateFilter {
    /**
     * 执行
     * @param params
     */
    public void doFilter(Map params);
    
    /**
     * 后续执行filter
     * @param filter
     */
    public void setNextFilter(SQLValidateFilter filter);
    
    /**
     * 获得后续filter
     * @return
     */
    public SQLValidateFilter getNextFilter();
}
