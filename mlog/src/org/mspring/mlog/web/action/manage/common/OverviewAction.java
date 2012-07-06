/**
 * 
 */
package org.mspring.mlog.web.action.manage.common;

import java.util.Map;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Jan 28, 2012
 */
public class OverviewAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 8144181848428077744L;

    private Map overviewMap;

    public Map getOverviewMap() {
        return overviewMap;
    }

    public void setOverviewMap(Map overviewMap) {
        this.overviewMap = overviewMap;
    }

    public String execute() {
        overviewMap = commonService.getOverviewInfo();
        return SUCCESS;
    }
}
