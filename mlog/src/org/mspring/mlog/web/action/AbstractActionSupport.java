package org.mspring.mlog.web.action;

import java.util.ArrayList;
import java.util.Collection;

import org.mspring.mlog.common.Const;
import org.mspring.mlog.entity.security.User;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.web.xwork.action.AbstractAction;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 12, 2011 org.mspring.mlog.core
 */
public class AbstractActionSupport extends AbstractAction {
    /**
     * 
     */
    private static final long serialVersionUID = 608216843343138289L;

    public static final String PROMPT = "prompt";
    public static final String EXCEPTION = "exception";

    protected User getCurrentUser() {
        return (User) getSessionAttribute(Const.SESSION_LOGINUSER);
    }

    /**
     * @return String of encodedQueryParams
     * @see QueryCriterion
     */
    public String getEncodedQueryParamsName() {
        return QueryCriterion.QUERY_PARAMS_KEY;
    }

    private Collection<String> scripts;

    public synchronized Collection<String> getScripts() {
        return scripts;
    }

    public synchronized void setScripts(Collection<String> scripts) {
        this.scripts = scripts;
    }

    public synchronized void addScript(String script) {
        internalGetScripts().add(script);
    }

    public synchronized boolean hasScripts() {
        boolean flag = ((this.scripts != null) && (!(this.scripts.isEmpty())));
        return flag;
    }

    public synchronized void clearScripts() {
        internalGetScripts().clear();
    }

    private Collection<String> internalGetScripts() {
        if (this.scripts == null) {
            this.scripts = new ArrayList<String>();
        }
        return this.scripts;
    }
}
