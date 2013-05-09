/**
 * 
 */
package org.mspring.platform.web.freemarker.render;

import org.mspring.platform.core.ContextManager;
import org.mspring.platform.utils.FreemarkerUtils;
import org.mspring.platform.web.freemarker.ExtendsFreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author Gao Youbo
 * @since 2013-3-15
 * @description
 * @TODO
 */
public class TagUtils {

    /**
     * 获取freemarker配置
     * 
     * @return
     */
    public static Configuration getConfiguration() {
        return ContextManager.getApplicationContext().getBean(ExtendsFreeMarkerConfigurer.class).getConfiguration();
    }

    /**
     * 渲染
     * 
     * @param template
     * @param Model
     * @return
     */
    public static String render(String template, Object Model) {
        Configuration configuration = getConfiguration();
        Template temp = FreemarkerUtils.getTemplate(configuration, template);
        return FreemarkerUtils.render(temp, Model);
    }
}
