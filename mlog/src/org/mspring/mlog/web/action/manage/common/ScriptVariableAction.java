/**
 * 
 */
package org.mspring.mlog.web.action.manage.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Jun 13, 2012
 */
public class ScriptVariableAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = -8032281650395081834L;
    
    private Map<String, String> variables;

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    public String execute() {
        HttpServletRequest request = ServletActionContext.getRequest();
        variables = new HashMap<String, String>();
        variables.put("path", request.getContextPath());
        return SUCCESS;
    }
}
