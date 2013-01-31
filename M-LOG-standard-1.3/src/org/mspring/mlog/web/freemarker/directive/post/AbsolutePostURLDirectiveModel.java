/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.post;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.mlog.web.rulrewrite.PostRewriteRule;

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
    private static final Logger log = Logger.getLogger(AbsolutePostURLDirectiveModel.class);

    private static final String KEY = "absolute_post_url";

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
            postUrl = PostRewriteRule.getPostUrl(new Post(id));
        }
        else {
            Object postObj = env.__getitem__(FreemarkerVariableNames.POST);
            if (postObj != null && (postObj instanceof Post)) {
                Post post = (Post) postObj;
                postUrl = PostRewriteRule.getPostUrl(post);
            }    
        }
        if (blogurl.endsWith("/") || blogurl.endsWith("\\")) {
            blogurl = blogurl.substring(0, blogurl.length() - 1);
        }
        String absoluteUrl = blogurl + postUrl;
        env.getOut().append(absoluteUrl);
    }

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

}
