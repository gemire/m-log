/**
 * 
 */
package org.mspring.platform.core;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Gao Youbo
 * @since Mar 17, 2012
 */
public class ContextManager {
    public static WebApplicationContext getApplicationContext() {
        return ContextLoader.getCurrentWebApplicationContext();
    }
}
