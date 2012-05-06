/**
 * @since 2011-7-23
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.mspring.mlog.web.view.AbstractTemplateView;
import org.mspring.mlog.web.view.TemplateViewFactory;

/**
 * 
 * @author Gao Youbo
 */
public class TemplateViewTagSupport extends BaseTagSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -455444882524075074L;
    private String view;

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public synchronized int doEndTag() throws JspException {
        // TODO Auto-generated method stub
        AbstractTemplateView templateView = TemplateViewFactory.createTemplateView(view);
        try {
            pageContext.getOut().print(templateView.json());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

}
