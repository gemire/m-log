/**
 * 
 */
package org.mspring.platform.web.render;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.platform.exception.BusinessException;
import org.mspring.platform.utils.JSONUtils;

/**
 * @author Gao Youbo
 * @since Apr 16, 2012
 */
public class JSONRender extends AbstractResponseRender {
    private static final Logger log = Logger.getLogger(JSONRender.class);

    private Object content;
    private boolean excludesFieldsWithoutExpose = false;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public boolean isExcludesFieldsWithoutExpose() {
        return excludesFieldsWithoutExpose;
    }

    public void setExcludesFieldsWithoutExpose(boolean excludesFieldsWithoutExpose) {
        this.excludesFieldsWithoutExpose = excludesFieldsWithoutExpose;
    }

    /**
     * 
     */
    public JSONRender() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param content
     */
    public JSONRender(Object content) {
        super();
        this.content = content;
    }

    /**
     * @param content
     * @param excludesFieldsWithoutExpose
     */
    public JSONRender(Object content, boolean excludesFieldsWithoutExpose) {
        super();
        this.content = content;
        this.excludesFieldsWithoutExpose = excludesFieldsWithoutExpose;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.web.servlet.renderer.AbstractResponseRenderer#render
     * (javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void render(HttpServletResponse response) {
        // TODO Auto-generated method stub
        if (this.content == null) {
            log.warn("JSON renders error, render content is null");
            throw new BusinessException("JSON renders error, render content is null");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String JSON = "";
            if (content instanceof String) {
                JSON = content.toString();
            }
            else {
                JSON = JSONUtils.toJson(content, excludesFieldsWithoutExpose);
            }
            PrintWriter writer = response.getWriter();
            writer.println(JSON);
        }
        catch (Exception e) {
            // TODO: handle exception
            log.warn("JSON renders error", e);
        }
    }

}
