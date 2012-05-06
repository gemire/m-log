/**
 * 
 */
package org.mspring.mlog.web.widget.factory;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.widget.SystemWidgetContext;
import org.mspring.mlog.web.widget.WidgetInterface;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since Apr 26, 2012
 */
public class WidgetFactory {
    private static final Logger log = Logger.getLogger(WidgetFactory.class);

    public static WidgetInterface createWidget(String className) {
        WidgetInterface widget = null;
        if (!StringUtils.isBlank(className)) {
            SystemWidgetContext systemWidgetContext = SystemWidgetContext.getInstance();
            if (systemWidgetContext.isSystemWidget(className)) {
                widget = systemWidgetContext.getSystemWidget(className);
            } else {
                try {
                    Class clazz = Class.forName(className);
                    Object w = clazz.newInstance();
                    if (w instanceof WidgetInterface) {
                        widget = (WidgetInterface) w;
                    } else {
                        log.warn("could not create widget [class=" + className + "], class must be extend AbstractWidget");
                    }
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return widget;
    }
}
