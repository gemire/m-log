/**
 * @since 2011-7-16
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.web.view.factory;

import java.util.List;

import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.web.view.AbstractTemplateView;

import com.google.gson.Gson;

/**
 * 
 * @author Gao Youbo
 */
public class TopCommentTemplateView extends AbstractTemplateView {
    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.view.AbstractTemplateView#json()
     */
    @Override
    public String json() {
        // TODO Auto-generated method stub
        List<Comment> list = commentService.getRecentComments(10);
        return new Gson().toJson(list);
    }
}
