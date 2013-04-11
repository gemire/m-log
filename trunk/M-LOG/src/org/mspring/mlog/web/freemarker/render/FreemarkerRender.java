/**
 * 
 */
package org.mspring.mlog.web.freemarker.render;

import freemarker.template.Configuration;

/**
 * @author Gao Youbo
 * @since 2013-2-22
 * @description
 * @TODO
 */
public interface FreemarkerRender {
    String render(Object model, Configuration configuration, String templateName);
}
