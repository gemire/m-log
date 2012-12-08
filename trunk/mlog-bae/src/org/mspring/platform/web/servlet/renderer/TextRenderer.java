/**
 * 
 */
package org.mspring.platform.web.servlet.renderer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author Gao Youbo
 * @since Apr 20, 2012
 */
public class TextRenderer extends AbstractResponseRenderer {
    private static final Logger log = Logger.getLogger(TextRenderer.class);

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
    public TextRenderer() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param content
     */
    public TextRenderer(String content) {
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
