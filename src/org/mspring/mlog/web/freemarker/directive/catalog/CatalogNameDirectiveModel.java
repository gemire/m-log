/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.catalog;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-8-9
 * @Description
 * @TODO 获取分类名称
 */
public class CatalogNameDirectiveModel extends AbstractDirectiveModel {
    private static final Logger log = Logger.getLogger(CatalogNameDirectiveModel.class);

    public static final String KEY = "catalog_name";

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel#getKey()
     */
    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        return KEY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
     * Environment, java.util.Map, freemarker.template.TemplateModel[],
     * freemarker.template.TemplateDirectiveBody)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        // 获取当前模板变量中的catalog对象
        Object catalogObj = env.__getitem__(FreemarkerVariableNames.CATALOG);
        if (catalogObj == null || !(catalogObj instanceof Catalog)) {
            log.warn("################catalog can't be found");
            return;
        }
        Catalog catalog = (Catalog) catalogObj;
        env.getOut().append(catalog.getName());
    }

}
