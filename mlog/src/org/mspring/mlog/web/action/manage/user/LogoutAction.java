/**
 * @since 2011-7-17
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.web.action.manage.user;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.common.Const;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.platform.utils.CookieUtils;

/**
 * 
 * @author Gao Youbo
 */
public class LogoutAction extends AbstractManageAction {
	/**
     * 
     */
    private static final long serialVersionUID = -5688839412645159656L;

    public String execute(){
        ServletActionContext.getRequest().getSession().invalidate();
        CookieUtils.setCookie(ServletActionContext.getResponse(), Const.MSPRING_COOKIE_REMEMBER, "false");
		return SUCCESS;
	}
}
