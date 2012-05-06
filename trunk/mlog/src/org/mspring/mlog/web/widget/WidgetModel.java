/**
 * 
 */
package org.mspring.mlog.web.widget;

import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.widget.factory.WidgetFactory;
import org.mspring.platform.utils.StringUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since Apr 26, 2012
 */
public class WidgetModel implements TemplateMethodModel {
    private static final Logger log = Logger.getLogger(WidgetModel.class);

    /*
     * (non-Javadoc)
     * 
     * @see freemarker.template.TemplateMethodModel#exec(java.util.List)
     */
    @Override
    public Object exec(List args) throws TemplateModelException {
        // TODO Auto-generated method stub
        if (args == null) {
            log.warn("template method model args is null");
            return StringUtils.EMPTY;
        }
        if (args.size() == 0) {
            log.warn("template method model args size == 0");
            return StringUtils.EMPTY;
        }
        String widgetClass = args.get(0).toString();
        String result = "";
        try {
            if (args.size() > 1) {
                args.remove(0);
            }
            WidgetInterface widget = WidgetFactory.createWidget(widgetClass);
            result = widget.render(args);
        } catch (Exception e) {
            // TODO: handle exception
            result = "Widget name=[" + widgetClass + "] load error";
            log.warn(result, e);
        }
        return result;
    }

}
