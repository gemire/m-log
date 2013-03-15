/**
 * 
 */
package org.mspring.mlog.web.module.script.stat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;

/**
 * @author Gao Youbo
 * @since 2012-11-16
 * @Description
 * @TODO 博客点击量
 */
public class BlogClickStatCmd extends AbstractStatCmd {
    private static final Logger log = Logger.getLogger(BlogClickStatCmd.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.module.script.stat.AbstractStatCmd#execute(javax
     * .servlet.http.HttpServletRequest)
     */
    @Override
    public boolean execute(HttpServletRequest request) {
        // TODO Auto-generated method stub
        try {
            ServiceFactory.getStatService().updateClickCount();
            return true;
        }
        catch (Exception e) {
            // TODO: handle exception
            log.error("update blog click error.", e);
            return false;
        }
    }

}
