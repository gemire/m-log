/**
 * 
 */
package org.mspring.platform.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Gao Youbo
 * @since Apr 7, 2012
 */
public class FreemarkerUtils {
    private static final Logger log = Logger.getLogger(FreemarkerUtils.class);

    /**
     * 获取模板
     * 
     * @param cfg
     * @param name
     * @return
     */
    public Template getTemplate(Configuration cfg, String name) {
        Template template = null;
        try {
            template = cfg.getTemplate(name, "UTF-8");
        } catch (IOException e) {
            log.warn("get template error,name:" + name + "." + e.getMessage());
        }
        return template;
    }

    /**
     * 渲染模板
     * 
     * @param cfg
     * @param name
     * @param model
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    public static String render(Configuration cfg, String name, Map<Object, Object> model) {
        try {
            Template template = cfg.getTemplate(name);
            return render(template, model);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 渲染模板
     * 
     * @param template
     * @param model
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    public static String render(Template template, Map<Object, Object> model) {
        Writer result = new StringWriter();
        try {
            template.process(model, result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result.toString();
    }

    public static boolean buildPage(Configuration cfg, String name, Map<Object, Object> model, String fileFullName) {
        Writer result;
        try {
            result = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileFullName), "UTF-8"));
            Template template = cfg.getTemplate(name);
            template.process(model, result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public static Configuration createConfiguration(String encoding, String... templateLoaderPaths) {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPaths(templateLoaderPaths);
        factory.setDefaultEncoding(encoding);
        Configuration cfg = null;
        try {
            cfg = factory.createConfiguration();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cfg;
    }
}
