/**
 * 
 */
package org.mspring.mlog.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Gao Youbo
 * @since 2013-2-25
 * @description
 * @TODO
 */
public class SubStringTag extends TagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = -8025324812679299193L;

    protected String str;
    protected Integer beginIndex = 0;
    protected Integer endIndex;

    public void setStr(String str) {
        this.str = str;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    @Override
    public int doEndTag() throws JspException {
        // TODO Auto-generated method stub
        if (str == null) {
            return EVAL_PAGE;
        }
        if (beginIndex > endIndex) {
            return EVAL_PAGE;
        }
        
        String result = str;
        if (endIndex == null) {
            if (beginIndex < str.length()) {
                result = str.substring(beginIndex) + "...";
            }
        } else {
            // 123456789
            // 012345678
            if (endIndex < str.length()) {
                result = str.substring(beginIndex, endIndex) + "...";
            }
        }
        try {
            pageContext.getOut().append(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

}
