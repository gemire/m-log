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
public abstract class AbstractSQLValidateFilter implements SQLValidateFilter {
    
    /**
     * 后续执行的filter
     */
    private SQLValidateFilter nextFilter = null;

    /* (non-Javadoc)
     * @see org.mspring.mlog.web.freemarker.directive.sql.filter.SQLValidateFilter#setNextFilter(org.mspring.mlog.web.freemarker.directive.sql.filter.SQLValidateFilter)
     */
    @Override
    public void setNextFilter(SQLValidateFilter filter) {
        // TODO Auto-generated method stub
        this.nextFilter = filter;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.web.freemarker.directive.sql.filter.SQLValidateFilter#getNextFilter()
     */
    @Override
    public SQLValidateFilter getNextFilter() {
        // TODO Auto-generated method stub
        return this.nextFilter;
    }
    
    /**
     * 执行后续filter
     * @param params
     */
    public void doNextFilter(Map params){
        SQLValidateFilter filter = getNextFilter();
        if (filter != null) {
            filter.doFilter(params);
        }
    }

}
