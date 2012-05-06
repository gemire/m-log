/**
 * 
 */
package org.mspring.mlog.web.action.manage.theme;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.entity.Theme;

/**
 * @author Gao Youbo
 * @since Feb 8, 2012
 */
public class QueryThemeAction extends AbstractManageAction {
    private List<Theme> themes;

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }
    
    public String execute(){
        themes = themeService._findAllTheme(ServletActionContext.getServletContext().getRealPath(""));
        return SUCCESS;
    }
}
