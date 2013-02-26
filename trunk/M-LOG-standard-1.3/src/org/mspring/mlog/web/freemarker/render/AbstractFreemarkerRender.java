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
public abstract class AbstractFreemarkerRender implements FreemarkerRender {

    public Template getTemplate(Configuration cfg, String templateName) {
        return FreemarkerUtils.getTemplate(cfg, templateName);
    }
}
