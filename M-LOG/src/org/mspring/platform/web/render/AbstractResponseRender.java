/**
 * 
 */
package org.mspring.platform.web.render;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Gao Youbo
 * @since Apr 16, 2012
 */
public abstract class AbstractResponseRender {
    public abstract void render(HttpServletResponse response);
}
