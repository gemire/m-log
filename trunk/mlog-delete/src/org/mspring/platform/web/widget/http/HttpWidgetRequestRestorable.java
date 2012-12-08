/**
 * 
 */
package org.mspring.platform.web.widget.http;

import java.util.Map;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public interface HttpWidgetRequestRestorable extends HttpWidgetRequest {
    void restoreWidgetRequest(Map<String, String[]> parameters, Map<String, ?> attributes);
}
