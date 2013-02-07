/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.http;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public interface HttpWidgetResponse extends HttpServletResponse {
    String getResponseAsString();
    void setResponseContent(String content) throws IOException;
}