/**
 * 
 */
package org.mspring.platform.web.servlet.renderer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Gao Youbo
 * @since 2013-2-6
 * @description 
 * @TODO
 */
public class ScriptRenderer extends AbstractResponseRenderer {
    
    private String script;
    
    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    /**
     * 
     */
    public ScriptRenderer() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param content
     */
    public ScriptRenderer(String script) {
        super();
        this.script = script;
    }
    /* (non-Javadoc)
     * @see org.mspring.platform.web.servlet.renderer.AbstractResponseRenderer#render(javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void render(HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            response.setContentType("text/javascript");
            response.setCharacterEncoding("UTF-8");
            
            PrintWriter writer = response.getWriter();

            writer.write(this.script);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

}
