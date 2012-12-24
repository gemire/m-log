/**
 * 
 */
package org.mspring.mlog.web.module.script.stat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * @author Gao Youbo
 * @since 2012-11-16
 * @Description
 * @TODO 空统计命令
 */
public class NullStatCmd extends AbstractStatCmd {
    private static final Logger log = Logger.getLogger(NullStatCmd.class);

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
        log.warn("null stat cmd!");
        return false;
    }

}
