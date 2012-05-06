package org.mspring.mlog.web.action.manage.common;

import java.util.Map;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

public class ConfigAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 6842545389812782290L;

    private Map<String, String> config;

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    public String view() {
        config = optionService.findWebConfig();
        return SUCCESS;
    }

    public String save() {
        optionService.setOptions(config);
        return SUCCESS;
    }
}
