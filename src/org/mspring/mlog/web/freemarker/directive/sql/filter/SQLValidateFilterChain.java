/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.sql.filter;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author Gao Youbo
 * @since 2012-12-28
 * @Description
 * @TODO
 */
public class SQLValidateFilterChain {
    private static final Logger log = Logger.getLogger(SQLValidateFilterChain.class);

    private Map params;

    /**
     * 第一个filter
     */
    private SQLValidateFilter firstFilter = null;

    /**
     * 最后一个filter
     */
    private SQLValidateFilter lastFilter = null;
    
    public SQLValidateFilterChain(Map params){
        firstFilter = lastFilter;
        this.params = params;
    }

    /**
     * 
     */
    public SQLValidateFilterChain() {
        // TODO Auto-generated constructor stub
        firstFilter = lastFilter;
        this.params = new HashMap();
    }

    /**
     * @return the params
     */
    public Map getParams() {
        return params;
    }

    /**
     * @param params
     *            the params to set 设置队列执行过程中所需要的参数
     */
    public void setParams(Map params) {
        this.params = params;
    }

    /**
     * 启动filter链
     */
    public void doFilter() {
        try {
            if (firstFilter != null) {
                firstFilter.doFilter(params);
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage(), e);
            firstFilter.getNextFilter().doFilter(params);
        }
    }

    /**
     * 向filterchain中添加一个filter
     * 
     * @param filter
     */
    public void addFilter(SQLValidateFilter filter) {
        if (firstFilter == null) {
            firstFilter = filter;
        }
        else {
            lastFilter.setNextFilter(filter);
        }
        lastFilter = filter;
    }

}
