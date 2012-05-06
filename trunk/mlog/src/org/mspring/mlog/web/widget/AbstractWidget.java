/**
 * 
 */
package org.mspring.mlog.web.widget;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.service.OptionService;

/**
 * @author Gao Youbo
 * @since Apr 26, 2012
 */
public abstract class AbstractWidget implements WidgetInterface {
    protected OptionService optionService = ServiceFactory.getOptionService();
    
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected void setSessionAttrbute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public Object getSessionAttribute(String key) {
        return getSession().getAttribute(key);
    }
}
