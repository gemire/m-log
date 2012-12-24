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
 * @TODO 文章点击统计
 */
public class PostClickStatCmd extends AbstractStatCmd {
    private static final Logger log = Logger.getLogger(PostClickStatCmd.class);

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
        Object object = request.getParameter("post_id");
        if (object == null) {
            log.warn("parameter post_id is null");
            return false;
        }
        try {
            ServiceFactory.getPostService().updatePostViewCount(new Long(object.toString()));
            return true;
        }
        catch (Exception e) {
            // TODO: handle exception
            log.error("stat post click error!", e);
            return false;
        }
    }

}
