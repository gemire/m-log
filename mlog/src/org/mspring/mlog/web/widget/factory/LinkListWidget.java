/**
 * 
 */
package org.mspring.mlog.web.widget.factory;

import java.util.List;

import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.Link;
import org.mspring.mlog.web.widget.AbstractWidget;

/**
 * @author Gao Youbo
 * @since Apr 26, 2012
 */
public class LinkListWidget extends AbstractWidget {

    /* (non-Javadoc)
     * @see org.mspring.mlog.web.widget.WidgetInterface#render(java.util.List)
     */
    public String render(List args) {
        // TODO Auto-generated method stub
        StringBuilder result = new StringBuilder();
        List<Link> links = ServiceFactory.getLinkService().findAllDisplayLinks();
        for (Link link : links) {
            result.append("<li><a href='" + link.getUrl() + "' target='" + link.getTarget() + "'>" + link.getName() + "</a></li>\n");
        }
        return result.toString();
    }

   
}
