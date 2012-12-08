/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.tag;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Tag;
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
 * @TODO 获取Tag链接
 */
public class TagUrlDirectiveModel extends AbstractDirectiveModel {
    private static final Logger log = Logger.getLogger(TagUrlDirectiveModel.class);

    public static final String KEY = "tag_url";

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
        Object tagObj = env.__getitem__(FreemarkerVariableNames.TAG);
        if (tagObj == null || !(tagObj instanceof Tag)) {
            log.warn("################tag can't be found");
            return;
        }
        String base = env.__getitem__(FreemarkerVariableNames.BASE).toString();
        Tag tag = (Tag) tagObj;
        env.getOut().append(base + "/tag/" + tag.getName());
    }

}
