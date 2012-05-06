/**
 * 
 */
package org.mspring.mlog.web.widget;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.widget.factory.AdminBarWidget;
import org.mspring.mlog.web.widget.factory.CategoryListWidget;
import org.mspring.mlog.web.widget.factory.FooterWidget;
import org.mspring.mlog.web.widget.factory.HeaderWidget;
import org.mspring.mlog.web.widget.factory.LinkListWidget;
import org.mspring.mlog.web.widget.factory.MenuWidget;
import org.mspring.mlog.web.widget.factory.RecentArticleWidget;
import org.mspring.mlog.web.widget.factory.RecentCommentWidget;
import org.mspring.mlog.web.widget.factory.SingleStatisticWidget;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since Apr 26, 2012
 * @TODO 系统插件
 */
public class SystemWidgetContext {
    private static final Logger log = Logger.getLogger(SystemWidgetContext.class);
    
    private static SystemWidgetContext context = null;
    private static Map<String, AbstractWidget> widgets = null;
    
    private SystemWidgetContext(){
        if (widgets == null) {
            widgets = new HashMap<String, AbstractWidget>();
        }
        
        //注册系统widget
        widgets.put("CategoryListWidget", new CategoryListWidget());
        widgets.put("RecentArticleWidget", new RecentArticleWidget());
        widgets.put("RecentCommentWidget", new RecentCommentWidget());
        widgets.put("LinkListWidget", new LinkListWidget());
        widgets.put("MenuWidget", new MenuWidget());
        widgets.put("HeaderWidget", new HeaderWidget());
        widgets.put("AdminBarWidget", new AdminBarWidget());
        widgets.put("FooterWidget", new FooterWidget());
        widgets.put("SingleStatisticWidget", new SingleStatisticWidget());
    }

    public static SystemWidgetContext getInstance() {
        if (context == null) {
            context = new SystemWidgetContext();
        }
        return context;
    }
    
    /**
     * 判断是否是系统widget
     * @return
     */
    public static boolean isSystemWidget(String name){
        if (widgets != null) {
            if (widgets.get(name) != null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 获取系统widget
     * @param name
     * @return
     */
    public static AbstractWidget getSystemWidget(String name){
        if (StringUtils.isBlank(name)) {
            log.warn("could not find system widget named null");
            return null;
        }
        if (!isSystemWidget(name)) {
            log.warn("could not find system widget named [" + name + "]");
            return null;
        }
        return widgets.get(name);
    }

}
