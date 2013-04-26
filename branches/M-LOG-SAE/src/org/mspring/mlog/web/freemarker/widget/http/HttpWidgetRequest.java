/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.http;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public interface HttpWidgetRequest extends HttpServletRequest {
    Map<String, ?> getWidgetModel();

    void bindWidgetModel(Map<String, ?> widgetModel);
}