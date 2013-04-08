/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.tag.ftl;

import java.io.IOException;
import java.util.Map;

import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.mlog.web.freemarker.widget.config.WidgetConfig;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidget;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetRequest;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidgetResponse;
import org.mspring.mlog.web.freemarker.widget.render.WidgetRender;
import org.mspring.mlog.web.freemarker.widget.render.impl.StandardWidgetRender;
import org.mspring.mlog.web.freemarker.widget.tag.WidgetCallback;
import org.mspring.mlog.web.freemarker.widget.tag.WidgetProcessor;
import org.mspring.mlog.web.freemarker.widget.utils.WidgetConfigUtils;
import org.mspring.mlog.web.freemarker.widget.utils.WidgetUtils;
import org.mspring.platform.utils.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
@SuppressWarnings("rawtypes")
public class WidgetDirectiveModel extends AbstractDirectiveModel {
    /*
     * (non-Javadoc)
     * 
     * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
     * Environment, java.util.Map, freemarker.template.TemplateModel[],
     * freemarker.template.TemplateDirectiveBody)
     */
    @Override
    public void execute(final Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String widgetPath = WidgetUtils.getWidgetPath(params);
        if (StringUtils.isBlank(widgetPath)) {
            return;
        }

        WidgetProcessor wp = new WidgetProcessor();
        wp.setCallback(new WidgetCallback() {
            @Override
            public HttpWidget getHttpWidget(WidgetConfig widgetConfig) throws TemplateModelException {
                // TODO Auto-generated method stub
                HttpWidget httpWidget = WidgetUtils.getHttpWidget(env, widgetConfig);
                if (httpWidget == null) {
                    WidgetRender render = new StandardWidgetRender();
                    HttpWidgetRequest widgetRequest = WidgetUtils.createHttpWidgetRequest(env);
                    HttpWidgetResponse widgetResponse = WidgetUtils.createHttpWidgetResponse(env);
                    render.render(widgetConfig, widgetRequest, widgetResponse);
                    httpWidget = WidgetUtils.getHttpWidget(env, widgetConfig);
                }
                return httpWidget;
            }
        });
        WidgetConfig widgetConfig = WidgetConfigUtils.getWidgetConfig(params);
        wp.process(env.getOut(), widgetConfig);
    }
}
