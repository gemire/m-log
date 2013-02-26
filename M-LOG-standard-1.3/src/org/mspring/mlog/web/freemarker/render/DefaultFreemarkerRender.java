/**
 * 
 */
package org.mspring.mlog.web.freemarker.render;

import org.mspring.platform.utils.FreemarkerUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author Gao Youbo
 * @since 2013-2-22
 * @description
 * @TODO
 */
public class DefaultFreemarkerRender extends AbstractFreemarkerRender {

    @Override
    public String render(Object model, Configuration configuration, String templateName) {
        // TODO Auto-generated method stub
        Template template = getTemplate(configuration, templateName);
        return FreemarkerUtils.render(template, model);
    }

}
