/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.post;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.platform.persistence.support.Page;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-8-9
 * @Description
 * @TODO 循环文章列表
 */
public class ListPostDirectiveModel extends AbstractDirectiveModel {

    private static final Logger log = Logger.getLogger(ListPostDirectiveModel.class);

    private static final String KEY = "list_post";

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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVar, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        Object postPageObj = env.__getitem__(FreemarkerVariableNames.POST_PAGE);
        if (postPageObj == null || !(postPageObj instanceof Page)) {
            log.warn("#####################count find postPage");
            return;
        }
        Page<Post> postPage = (Page<Post>) postPageObj;
        for (Post post : postPage.getResult()) {
            env.__setitem__(FreemarkerVariableNames.POST, post);
            body.render(env.getOut());
        }
    }

}
