/**
 * 
 */
package org.mspring.platform.web.render;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author Gao Youbo
 * @since Apr 20, 2012
 */
public class TextRender extends AbstractResponseRender {
    private static final Logger log = Logger.getLogger(TextRender.class);

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     */
    public TextRender() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param content
     */
    public TextRender(String content) {
        super();
        this.content = content;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.platform.web.servlet.renderer.AbstractResponseRenderer#render(javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void render(HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();

            writer.write(this.content);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.debug("Text Render failed", e);
        }

    }

}
