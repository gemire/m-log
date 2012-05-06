/**
 * 
 */
package org.mspring.mlog.web.widget.factory;

import java.util.List;

import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.Menu;
import org.mspring.mlog.web.widget.AbstractWidget;

/**
 * @author Gao Youbo
 * @since Apr 26, 2012
 */
public class MenuWidget extends AbstractWidget {

    /* (non-Javadoc)
     * @see org.mspring.mlog.web.widget.WidgetInterface#render(java.util.List)
     */
    @Override
    public String render(List args) {
        // TODO Auto-generated method stub
        StringBuilder result = new StringBuilder();
        List<Menu> menus = ServiceFactory.getMenuService().findAll();
        String path = getRequest().getContextPath();
        for (Menu menu : menus) {
            switch (menu.getType()) {
            case 1:
                result.append("<li><a href='" + path + "/category/" + menu.getCategory() + "'>" + menu.getName() + "</a></li>\n");
                break;
            case 2:
                result.append("<li><a href='" + menu.getUrl() + "'>" + menu.getName() + "</a></li>\n");
                break;
            default:
                result.append("<li><a href='" + path + "/" + menu.getUrl() + "'>" + menu.getName() + "</a></li>\n");
                break;
            }
        }
        return result.toString();
    }

}
