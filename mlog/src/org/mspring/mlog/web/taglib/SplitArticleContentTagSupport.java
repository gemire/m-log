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
public class SplitArticleContentTagSupport extends BaseTagSupport {
    private String content;
    private String intro;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag() throws JspException {
        // TODO Auto-generated method stub
        String ret = "";
        if (!StringUtils.isBlank(intro)) {
            ret = intro;
        } else {
            ret = HTMLUtils.preview(content, 500);
        }

        JspWriter out = pageContext.getOut();
        try {
            out.print(ret);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
