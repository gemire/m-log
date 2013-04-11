/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.utils.PostUrlUtils;
import org.mspring.mlog.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-11-21
 * @Description
 * @TODO 文章绝对路径
 */
public class AbsolutePostURLDirectiveModel extends AbstractDirectiveModel {

    /*
     * (non-Javadoc)
     * 
     * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
     * Environment, java.util.Map, freemarker.template.TemplateModel[],
     * freemarker.template.TemplateDirectiveBody)
     */
    @Override
    public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        String blogurl = ServiceFactory.getOptionService().getOption("blogurl");

        String postUrl = "";
        Long id = DirectiveUtils.getLong("id", params);
        if (id != null) {
            postUrl = PostUrlUtils.getPostUrl(new Post(id));
        } else {
            String postParamName = DirectiveUtils.getString("post", params);
            if (StringUtils.isBlank(postParamName)) {
                postParamName = "post";
            }
            Object postObj = DirectiveUtils.getItem(env, postParamName);
            if (postObj != null && (postObj instanceof Post)) {
                Post post = (Post) postObj;
                postUrl = PostUrlUtils.getPostUrl(post);
            }
        }
        if (blogurl.endsWith("/") || blogurl.endsWith("\\")) {
            blogurl = blogurl.substring(0, blogurl.length() - 1);
        }
        String absoluteUrl = blogurl + postUrl;
        env.getOut().append(absoluteUrl);
    }
}
