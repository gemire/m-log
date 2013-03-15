/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.utils.PostUrlUtils;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2013-2-28
 * @description
 * @TODO
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PostUrlDirectiveModel extends AbstractDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        String base = DirectiveUtils.getItem(env, FreemarkerVariableNames.BASE).toString();

        Long id = DirectiveUtils.getLong("id", params);
        if (id != null) {
            String postUrl = PostUrlUtils.getPostUrl(new Post(id));
            env.getOut().append(base + postUrl);
        } else {
            String postParamName = DirectiveUtils.getString("post", params);
            if (StringUtils.isBlank(postParamName)) {
                postParamName = "post";
            }
            Object postObj = DirectiveUtils.getItem(env, postParamName);
            if (postObj != null && (postObj instanceof Post)) {
                Post post = (Post) postObj;
                String postUrl = PostUrlUtils.getPostUrl(post);
                env.getOut().append(base + postUrl);
            }
        }
    }

}
