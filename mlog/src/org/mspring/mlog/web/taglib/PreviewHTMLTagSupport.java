/**
 * 
 */
package org.mspring.mlog.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.mspring.platform.utils.HTMLUtils;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since Dec 17, 2011
 */
public class PreviewHTMLTagSupport extends BaseTagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = -5246708403899195591L;

    public String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag() throws JspException {
        // TODO Auto-generated method stub
        String ret_content = "";
        if (!StringUtils.isBlank(content))
            ret_content = HTMLUtils.preview(content, 500);
        JspWriter out = pageContext.getOut();
        try {
            out.print(ret_content);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

}
