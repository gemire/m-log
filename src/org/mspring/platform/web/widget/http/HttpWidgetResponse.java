/**
 * 
 */
package org.mspring.platform.web.widget.http;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public interface HttpWidgetResponse extends HttpServletResponse {
    String getResponseAsString();
}