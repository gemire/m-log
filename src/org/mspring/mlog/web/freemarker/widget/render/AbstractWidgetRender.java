/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.render;

import java.io.IOException;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.web.freemarker.widget.config.WidgetConfig;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidget;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse;
import org.mspring.mlog.web.freemarker.widget.http.impl.DefaultHttpWidget;
import org.mspring.mlog.web.freemarker.widget.utils.WidgetUtils;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO
 */
public abstract class AbstractWidgetRender implements WidgetRender {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.freemarker.widget.render.WidgetRender#render(org
     * .mspring.mlog.web.freemarker.widget.config.WidgetConfig,
     * org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest,
     * org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse)
     */
    @Override
    public void render(WidgetConfig widgetConfig, HttpWidgetRequest widgetRequest, HttpWidgetResponse widgetResponse) {
        // TODO Auto-generated method stub
        preWidgetRender(widgetConfig, widgetRequest, widgetResponse);
        nativeWidgetRender(widgetConfig, widgetRequest, widgetResponse);
        postWidgetRender(widgetConfig, widgetRequest, widgetResponse);
    }

    /**
     * 执行render
     * 
     * @param widgetConfig
     * @param httpRequest
     * @param httpResponse
     */
    protected abstract void nativeWidgetRender(WidgetConfig widgetConfig, HttpWidgetRequest widgetRequest, HttpWidgetResponse widgetResponse);

    /**
     * render之前执行的操作
     * 
     * @param widgetConfig
     * @param httpRequest
     * @param httpResponse
     */
    protected void preWidgetRender(WidgetConfig widgetConfig, HttpWidgetRequest widgetRequest, HttpWidgetResponse widgetResponse) {
    }

    /**
     * render之后执行的操作
     * 
     * @param widgetConfig
     * @param httpRequest
     * @param httpResponse
     */
    protected void postWidgetRender(WidgetConfig widgetConfig, HttpWidgetRequest widgetRequest, HttpWidgetResponse widgetResponse) {
        HttpWidget httpWidget = new DefaultHttpWidget(widgetRequest, widgetResponse);
        WidgetUtils.setHttpWidget(widgetRequest, widgetConfig, httpWidget);

        if (widgetConfig.isCache()) {
            String value = widgetResponse.getResponseAsString();
            ServiceFactory.getWidgetCacheService().updateWidgetCacheValue(widgetConfig.getName(), value, widgetConfig.getIdle());
        }
    }

    protected HttpWidget getHttpWidgetCache(WidgetConfig widgetConfig, HttpWidgetRequest widgetRequest, HttpWidgetResponse widgetResponse) {
        HttpWidget httpWidget = null;
        if (widgetConfig.isCache()) {
            Object obj = ServiceFactory.getWidgetCacheService().getWidgetCacheValue(widgetConfig.getName());
            if (obj != null) {
                try {
                    widgetResponse.setResponseContent(obj.toString());
                    httpWidget = new DefaultHttpWidget(widgetRequest, widgetResponse);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return httpWidget;
    }

}
