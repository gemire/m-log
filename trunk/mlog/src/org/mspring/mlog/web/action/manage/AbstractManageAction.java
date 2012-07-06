package org.mspring.mlog.web.action.manage;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.entity.security.Tree;
import org.mspring.mlog.web.action.CommonActionSupport;
import org.mspring.platform.model.field.ColumnField;
import org.mspring.platform.utils.StringUtils;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 5, 2011 org.mspring.mlog.web.action.manage
 */
public abstract class AbstractManageAction extends CommonActionSupport {
    protected List<ColumnField> fieldColumns;
    protected Integer entityId;

    public List<ColumnField> getFieldColumns() {
        return fieldColumns;
    }

    public void setFieldColumns(List<ColumnField> fieldColumns) {
        this.fieldColumns = fieldColumns;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    /**
     * 添加页面tab页签脚本
     * 
     * @param tabId
     *            页签编号
     */
    protected void addCreateTabScript(String tabId) {
        if (!StringUtils.isBlank(tabId)) {
            Tree item = treeService.findTreeItemById(tabId, getCurrentUser());
            addScript("window.top.loadPanel('" + item.getId() + "', '" + item.getUrl() + "', '" + item.getText() + "', '" + item.getQtip() + "');");
        }
    }

    /**
     * 刷新Tab页面
     * 
     * @param tabId
     */
    protected void addRebuildTabScript(String tabId) {
        if (!StringUtils.isBlank(tabId)) {
            Tree item = treeService.findTreeItemById(tabId, getCurrentUser());
            //addScript("window.top.rebuildPanel('" + item.getId() + "', '" + item.getUrl() + "', '" + item.getText() + "', '" + item.getQtip() + "');");
            addScript("window.top.MSpring.reloadTab('" + item.getId() + "', '" + item.getText() + "', '" + item.getUrl() + "');");
        }

    }
}
