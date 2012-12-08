/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.utils;

import java.util.Map;

import org.mspring.mlog.web.freemarker.widget.config.WidgetConfig;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO
 */
public class WidgetConfigUtils {

    public static WidgetConfig getWidgetConfig(Map params) {
        boolean iscache = WidgetUtils.isCacheEnabled(params);
        long idle = WidgetUtils.getCacheIdle(params);
        String key = WidgetUtils.getHttpWidgetKey(params);
        String widgetPath = WidgetUtils.getWidgetPath(params);

        WidgetConfig config = new WidgetConfig();
        config.setName(key);
        config.setPath(widgetPath);
        config.setCache(iscache);
        config.setIdle(idle);
        config.setPreferences(params);
        return config;
    }
}
