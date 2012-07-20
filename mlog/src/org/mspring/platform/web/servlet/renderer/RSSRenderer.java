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
 * @since May 12, 2012
 */
public class RSSRenderer extends AbstractResponseRenderer {

    private static final Logger log = Logger.getLogger(RSSRenderer.class);

    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     */
    public RSSRenderer(String content) {
        // TODO Auto-generated constructor stub
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
            response.setContentType("application/rss+xml");
            response.setCharacterEncoding("UTF-8");

            PrintWriter writer = response.getWriter();
            writer.write(this.content);
            writer.close();
          } catch (IOException e) {
              log.warn("RSS Render failed");
          }
    }

}
