/**
 * 
 */
package org.mspring.mlog.web.module.stat.factory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gao Youbo
 * @since 2012-11-16
 * @Description
 * @TODO
 */
public abstract class AbstractStatCmd {
    public abstract boolean execute(HttpServletRequest request);
}
