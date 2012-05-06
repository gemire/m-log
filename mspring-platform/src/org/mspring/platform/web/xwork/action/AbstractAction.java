/**
 * 
 */
package org.mspring.platform.web.xwork.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class AbstractAction extends ActionSupport {

    public Map getSession() {
        return ActionContext.getContext().getSession();
    }

    public void removeSessionAttribute(String name) {
        getSession().remove(name);
    }

    public void setSessionAttribute(String name, Object value) {
        getSession().put(name, value);
    }

    public Object getSessionAttribute(String name) {
        return getSession().get(name);
    }

    public String getSessionParameter(String name) {
        return (String) getSessionAttribute(name);
    }

    public Long getSessionParameterAsLong(String name) {
        return (Long) getSessionAttribute(name);
    }

    public Integer getSessionParameterAsInteger(String name) {
        return (Integer) getSessionAttribute(name);
    }

    public Map getRequest() {
        return ActionContext.getContext().getParameters();
    }

    /**
     * @param name
     *            a String specifying the name of the parameter
     * @return a String representing the single value of the parameter
     * @see javax.servlet.ServletRequest#getParameter
     */
    public String getRequestParameter(String name) {
        String[] values = getRequestParameterValues(name);
        if (values == null || values.length == 0) {
            return null;
        } else {
            return values[0];
        }
    }

    public Long getRequestParameterAsLong(String name) {
        String value = getRequestParameter(name);
        return new Long(value);
    }

    public Integer getRequestParameterAsInteger(String name) {
        String value = getRequestParameter(name);
        return new Integer(value);
    }

    /**
     * @param name
     *            a String containing the name of the parameter whose value is
     *            requested
     * @return an array of String objects containing the parameter's values
     * @see javax.servlet.ServletRequest#getParameterValues
     */
    public String[] getRequestParameterValues(String name) {
        Map requestParams = ActionContext.getContext().getParameters();
        return (String[]) requestParams.get(name);
    }

    public void setRequestAttribute(String paramKey, Object value) {
        ServletActionContext.getRequest().setAttribute(paramKey, value);
    }

    public Object getRequestAttribute(String paramKey) {
        return ServletActionContext.getRequest().getAttribute(paramKey);
    }
}
