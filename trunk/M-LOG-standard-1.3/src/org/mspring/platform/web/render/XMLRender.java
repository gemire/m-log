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
 * @since Apr 16, 2012
 */
public class XMLRender extends AbstractResponseRender {

    private static final Logger log = Logger.getLogger(XMLRender.class);

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
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
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");

            PrintWriter writer = response.getWriter();
            writer.write(this.content);
            writer.close();
        } catch (IOException e) {
            log.debug("XML Render failed", e);
        }
    }

}
