/**
 * 
 */
package org.mspring.mlog.web.action.manage.theme;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * @since Feb 9, 2012 
 * 
 * 使用当前主题
 */
public class UseThisThemeAction extends AbstractManageAction {
    private String folderName;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String execute(){
        try {
            themeService.useThisTheme(folderName);
            addActionMessage(getText("message.success"));
        } catch (Exception e) {
            // TODO: handle exception
            addActionError(getText("message.failure"));
        }
        return SUCCESS;
    }
}
