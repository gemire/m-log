/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.post;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Post;
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
 * @TODO 获取文章作者别名
 */
public class PostAuthorAliasDirectiveModel extends AbstractDirectiveModel {
    private static final Logger log = Logger.getLogger(PostAuthorAliasDirectiveModel.class);

    public static final String KEY = "post_author_alias";

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
        // 获取当前模板变量中的文章对象
        Object postObj = env.__getitem__(FreemarkerVariableNames.POST);
        if (postObj == null || !(postObj instanceof Post)) {
            log.warn("################post can't be found");
            return;
        }
        Post post = (Post) postObj;
        String aliasName = "";
        if (post.getAuthor() != null) {
            if (StringUtils.isNotBlank(post.getAuthor().getAlias())) {
                aliasName = post.getAuthor().getAlias();
            }
            else {
                aliasName = post.getAuthor().getName();
            }
        }
        env.getOut().append(aliasName);
    }

}
