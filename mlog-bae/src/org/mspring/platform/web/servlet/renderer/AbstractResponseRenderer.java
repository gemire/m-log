/**
 * 
 */
package org.mspring.platform.web.servlet.renderer;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Gao Youbo
 * @since Apr 16, 2012
 */
public abstract class AbstractResponseRenderer {
    public abstract void render(HttpServletResponse response);
}
