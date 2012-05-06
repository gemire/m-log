/**
 * 
 */
package org.mspring.mlog.web.widget;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.ClassUtils;

/**
 * @author Gao Youbo
 * @since Apr 27, 2012
 * @TODO 用户插件
 */
public class WidgetContext {
    private static final Logger log = Logger.getLogger(WidgetContext.class);
    
    private static WidgetContext context = null;
    private static Map<String, AbstractWidget> widgets = null;
    
    private WidgetContext(){
        if (widgets == null) {
            widgets = new HashMap<String, AbstractWidget>();
        }
    }
    
    public static WidgetContext getInstance(){
        if (context == null) {
            context = new WidgetContext();
        }
        return context;
    }
    
    /**
     * 获取插件
     * @param name
     * @return
     */
    public static WidgetInterface getWidget(String name){
        return widgets.get(name);
    }
    
    /**
     * 安装插件
     * @param name 插件名称
     * @param className 插件完整类名
     */
    public static void installWidget(String name, String className){
        try {
            Class clazz = Class.forName(className);
            installWidget(name, clazz);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            log.warn("install widget failure, class [" + className + "] not found!");
        }
    }
    
    /**
     * 安装插件
     * @param name 插件名称
     * @param clazz 插件类
     */
    public static void installWidget(String name, Class clazz){
        try {
            widgets.put(name, (AbstractWidget) clazz.newInstance());
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 卸载插件
     * @param name
     */
    public static void uninstallWidget(String name){
        widgets.remove(name);
    }
    
    
}
