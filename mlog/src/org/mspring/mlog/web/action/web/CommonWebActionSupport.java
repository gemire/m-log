/**
 * 
 */
package org.mspring.mlog.web.action.web;

import org.mspring.mlog.web.action.CommonActionSupport;
import org.mspring.mlog.web.theme.ThemeUtils;

/**
 * @author Gao Youbo
 * @since Mar 17, 2012
 */
public class CommonWebActionSupport extends CommonActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 758663884880111589L;

    private String theme;

    public String getTheme() {
        this.theme = ThemeUtils.getTheme();
        return this.theme;
    }

}
