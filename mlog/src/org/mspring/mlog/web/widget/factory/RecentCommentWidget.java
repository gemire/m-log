/**
 * 
 */
package org.mspring.mlog.web.widget.factory;

import java.util.List;

import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.web.widget.AbstractWidget;

/**
 * @author Gao Youbo
 * @since Apr 26, 2012
 */
public class RecentCommentWidget extends AbstractWidget {

    /* (non-Javadoc)
     * @see org.mspring.mlog.web.widget.WidgetInterface#render(java.util.List)
     */
    @Override
    public String render(List args) {
        // TODO Auto-generated method stub
        StringBuilder result = new StringBuilder();
        int numOfPosts = 20;
        if (args.size() > 0) {
            numOfPosts = new Integer(args.get(0).toString());
        }
        List<Comment> recentComments = ServiceFactory.getCommentService().getRecentComments(numOfPosts);
        for (Comment comment : recentComments) {
            result.append("<li><a href='#'>" + comment.getContent() + "</a></li>\n");
        }
        return result.toString();
    }

}
