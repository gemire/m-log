/**
 * 
 */
package org.mspring.platform.web.widget.tag.jsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public class WidgetTag extends TagSupport {

    private String path;

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag() throws JspException {
        // TODO Auto-generated method stub
        // 验证path信息是否为空
        if (StringUtils.isBlank(path)) {
            try {
                pageContext.getOut().write(errorInfo());
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return EVAL_PAGE;
        }

        StringBuffer buffer = new StringBuffer();
        Object value = null;
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        final HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
        try {
            // pageContext.getRequest().getRequestDispatcher(path).include(request,
            // new GenericResponseWrapper(response, baos));
            pageContext.getRequest().getRequestDispatcher(path).include(request, response);
            value = (baos.toString("UTF-8"));
        }
        catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank((String) value)) {
            buffer.append((String) value);
        }
        if (StringUtils.isNotBlank(buffer.toString())) {
            try {
                pageContext.getOut().write(buffer.toString());
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return EVAL_PAGE;
    }

    private String errorInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<div style='font-size:12px;color:blue;'>");
        buffer.append("path can't be null");
        buffer.append("</div>");
        return buffer.toString();
    }

}
