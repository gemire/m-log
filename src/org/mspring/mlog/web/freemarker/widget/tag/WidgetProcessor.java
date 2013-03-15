/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.tag;

import java.io.IOException;
import java.io.Writer;

import org.mspring.mlog.web.freemarker.widget.config.WidgetConfig;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidget;

import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public class WidgetProcessor {

    private WidgetCallback callback;

    /**
     * @param callback
     *            the callback to set
     */
    public void setCallback(WidgetCallback callback) {
        this.callback = callback;
    }

    public void process(Writer writer, WidgetConfig widgetConfig) throws IOException, TemplateModelException {
        HttpWidget widget = callback.getHttpWidget(widgetConfig);
        if (widget != null) {
            String htmlFragment = widget.getWidgetResponse().getResponseAsString();
            writer.append(htmlFragment);
        }
    }

}
