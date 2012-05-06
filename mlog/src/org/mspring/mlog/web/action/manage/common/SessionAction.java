/**
 * 
 */
package org.mspring.mlog.web.action.manage.common;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-7
 */
public class SessionAction extends AbstractManageAction {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String setSessionAttribute() {
        setSessionAttribute(key, value);
        return NONE;
    }
    
    public String getSessionAttribute() throws IOException {
        Object obj = getSessionAttribute(key);
        if (obj != null) {
            value = obj.toString();
        }
        else {
            value = "";
        }
        ServletActionContext.getResponse().getWriter().print(value);
        return NONE;
    }
}
