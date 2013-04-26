/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.render.impl;

import org.mspring.mlog.web.freemarker.widget.WidgetNotFoundException;
import org.mspring.mlog.web.freemarker.widget.config.WidgetConfig;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidget;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse;
import org.mspring.mlog.web.freemarker.widget.render.AbstractWidgetRender;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO
 */
public class StandardWidgetRender extends AbstractWidgetRender {

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.freemarker.widget.render.AbstractWidgetRender#
     * nativeWidgetRender
     * (org.mspring.mlog.web.freemarker.widget.config.WidgetConfig,
     * org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest,
     * org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse)
     */
    @Override
    protected void nativeWidgetRender(WidgetConfig widgetConfig, HttpWidgetRequest widgetRequest, HttpWidgetResponse widgetResponse) {
        // TODO Auto-generated method stub
        if (widgetConfig == null) {
            return;
        }

        HttpWidget widget = getHttpWidgetCache(widgetConfig, widgetRequest, widgetResponse);
        if (widget != null) {
            return;
        }

        try {
            String path = widgetConfig.getPath();
            if (StringUtils.isNotBlank(path)) {
                try {
                    widgetRequest.getRequestDispatcher(path).include(widgetRequest, widgetResponse);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new WidgetNotFoundException("widget render exception!", e);
        }
    }
}
