/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.tag.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.freemarker.widget.WidgetTokens;
import org.mspring.mlog.web.freemarker.widget.config.WidgetConfig;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidget;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse;
import org.mspring.mlog.web.freemarker.widget.render.WidgetRender;
import org.mspring.mlog.web.freemarker.widget.render.impl.StandardWidgetRender;
import org.mspring.mlog.web.freemarker.widget.tag.WidgetCallback;
import org.mspring.mlog.web.freemarker.widget.tag.WidgetProcessor;
import org.mspring.mlog.web.freemarker.widget.utils.WidgetUtils;
import org.mspring.platform.utils.StringUtils;

import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public class WidgetTag extends TagSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -3289603190424129610L;

    private static final Logger log = Logger.getLogger(WidgetTag.class);

    private String path;
    private boolean cache = WidgetUtils.DEFAULT_CACHE_ENABLE;
    // 缓存时间， 单位秒
    private Long idle;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public Long getIdle() {
        return idle;
    }

    public void setIdle(Long idle) {
        this.idle = idle;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag() {
        // TODO Auto-generated method stub
        if (StringUtils.isBlank(path)) {
            return EVAL_PAGE;
        }
        final HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        final HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

        WidgetProcessor wp = new WidgetProcessor();
        wp.setCallback(new WidgetCallback() {
            @Override
            public HttpWidget getHttpWidget(WidgetConfig widgetConfig) throws TemplateModelException {
                // TODO Auto-generated method stub
                HttpWidget httpWidget = WidgetUtils.getHttpWidget(request, widgetConfig);
                if (httpWidget == null) {
                    WidgetRender render = new StandardWidgetRender();
                    HttpWidgetRequest widgetRequest = WidgetUtils.createHttpWidgetRequest(request);
                    HttpWidgetResponse widgetResponse = WidgetUtils.createHttpWidgetResponse(response);
                    render.render(widgetConfig, widgetRequest, widgetResponse);
                    httpWidget = WidgetUtils.getHttpWidget(request, widgetConfig);
                }
                return httpWidget;
            }
        });
        WidgetConfig widgetConfig = new WidgetConfig();
        widgetConfig.setCache(cache);
        widgetConfig.setIdle(idle != null ? idle : WidgetUtils.DEFAULT_CACHE_IDLE);
        widgetConfig.setPath(path);
        widgetConfig.setName(WidgetTokens.WIDGET_CACHE_KEY + path);
        try {
            wp.process(pageContext.getOut(), widgetConfig);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    private String errorInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<div style='font-size:12px;color:blue;'>");
        buffer.append("path can't be null");
        buffer.append("</div>");
        return buffer.toString();
    }

}
