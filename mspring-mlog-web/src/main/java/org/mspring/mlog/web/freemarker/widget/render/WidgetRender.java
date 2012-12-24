/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.render;

import org.mspring.mlog.web.freemarker.widget.config.WidgetConfig;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO
 */
public interface WidgetRender {
    void render(WidgetConfig widgetConfig, HttpWidgetRequest widgetRequest, HttpWidgetResponse widgetResponse);
}
