/**
 * 
 */
package org.mspring.mlog.web.freemarker.widget.tag;

import org.mspring.mlog.web.freemarker.widget.config.WidgetConfig;
import org.mspring.mlog.web.freemarker.widget.http.HttpWidget;

import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description 
 * @TODO 
 */
public interface WidgetCallback {
    HttpWidget getHttpWidget(WidgetConfig widgetConfig) throws TemplateModelException;
}
