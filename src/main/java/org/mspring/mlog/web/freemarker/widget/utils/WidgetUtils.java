/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.utils;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.web.WebContext;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.mlog.web.freemarker.widget.WidgetTokens;
import org.mspring.mlog.web.freemarker.widget.config.WidgetConfig;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidget;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse;
import org.mspring.mlog.web.freemarker.widget.http.impl.DefaultHttpWidgetRequest;
import org.mspring.mlog.web.freemarker.widget.http.impl.DefaultHttpWidgetResponse;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-12-5
 * @Description
 * @TODO
 */
public class WidgetUtils {
    public static final Long DEFAULT_CACHE_IDLE = new Long(60 * 1000);

    public static final String PARAM_WIDGET_PATH = "path";
    public static final String PARAM_CACHE_ENABLED = "cache";
    public static final String PARAM_CACHE_IDLE = "idle";

    /**
     * 获取widget指向的路径
     * 
     * @param params
     * @return
     */
    public static String getWidgetPath(Map params) {
        if (params == null) {
            return "";
        }
        Object obj = params.get(PARAM_WIDGET_PATH);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 获取缓存的KEY
     * 
     * @param params
     * @return
     */
    public static String getHttpWidgetKey(WidgetConfig widgetConfig) {
        if (widgetConfig == null) {
            return "";
        }
        return widgetConfig.getName();
    }

    /**
     * 获取缓存key
     * 
     * @param params
     * @return
     */
    public static String getHttpWidgetKey(Map params) {
        if (params != null || params.size() < 1) {
            return "";
        }
        return WidgetTokens.WIDGET_CACHE_KEY + params.toString();
    }

    /**
     * 判断是否进行缓存
     * 
     * @param params
     * @return
     * @throws TemplateException
     */
    public static boolean isCacheEnabled(Map params) {
        try {
            Boolean cache = DirectiveUtils.getBoolean(PARAM_CACHE_ENABLED, params);
            if (cache == null) {
                return false;
            }
            return cache;
        }
        catch (TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取缓存时间，单位秒
     * 
     * @param params
     * @return
     */
    public static Long getCacheIdle(Map params) {
        try {
            Long idle = DirectiveUtils.getLong(PARAM_CACHE_IDLE, params);
            if (idle == null || idle == 0) {
                return DEFAULT_CACHE_IDLE;
            }
            return idle * 1000;
        }
        catch (TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return DEFAULT_CACHE_IDLE;
        }
    }

    /**
     * 
     * @param request
     * @param widgetConfig
     * @param widget
     */
    public static void setHttpWidget(ServletRequest request, WidgetConfig widgetConfig, HttpWidget widget) {
        request.setAttribute(getHttpWidgetKey(widgetConfig), widget);
    }

    public static HttpWidget getHttpWidget(ServletRequest request, WidgetConfig widgetConfig) {
        return (HttpWidget) request.getAttribute(getHttpWidgetKey(widgetConfig));
    }

    public static HttpWidget getHttpWidget(Environment env, WidgetConfig widgetConfig) throws TemplateModelException {
        String httpWidgetKey = WidgetUtils.getHttpWidgetKey(widgetConfig);
        BeanModel httpWidgetBeanModel = (BeanModel) env.getDataModel().get(httpWidgetKey);
        HttpWidget httpWidget = null;
        if (httpWidgetBeanModel != null) {
            httpWidget = (HttpWidget) httpWidgetBeanModel.getWrappedObject();
        }
        return httpWidget;
    }

    public static HttpWidgetRequest createHttpWidgetRequest(Environment env) {
        WebContext webContext = DirectiveUtils.getWebContext(env);
        return new DefaultHttpWidgetRequest(webContext.getRequest());
    }

    public static HttpWidgetResponse createHttpWidgetResponse(Environment env) {
        WebContext webContext = DirectiveUtils.getWebContext(env);
        return new DefaultHttpWidgetResponse(webContext.getResponse());
    }

    public static HttpWidgetRequest createHttpWidgetRequest(HttpServletRequest httpRequest) {
        return new DefaultHttpWidgetRequest(httpRequest);
    }

    public static HttpWidgetResponse createHttpWidgetResponse(HttpServletResponse httpResponse) {
        return new DefaultHttpWidgetResponse(httpResponse);
    }
}
