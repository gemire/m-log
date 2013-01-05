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
public class DeleteSQLValidateFilter extends AbstractSQLValidateFilter {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.freemarker.directive.sql.filter.SQLValidateFilter
     * #doFilter(java.util.Map)
     */
    @Override
    public void doFilter(Map params) {
        // TODO Auto-generated method stub
        doNextFilter(params);
    }

}
