/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.post;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.platform.utils.HTMLUtils;
import org.mspring.platform.utils.ValidatorUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-7-26
 * @Description
 * @TODO 获取文章内容
 */
public class PostContentDirectiveModel extends AbstractDirectiveModel {
    private static final Logger log = Logger.getLogger(PostContentDirectiveModel.class);

    public static final String KEY = "post_content";

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
        //获取当前模板变量中的文章对象
        Object postObj = env.__getitem__(FreemarkerVariableNames.POST);
        if (postObj == null || !(postObj instanceof Post)) {
            log.warn("################post can't be found");
            return;
        }
        
        Post post = (Post) postObj;
//        //判断当前文章是否需要密码
//        if (StringUtils.isNotBlank(post.getPassword())) {
//            env.getOut().append("该文章需要密码访问");
//            return;
//        }
        
        
        Integer max_length = 0; // 文章内容显示的最大长度，“0”表示不限制
        if (params != null) {
            if (params.get("max_length") != null && StringUtils.isNotBlank(params.get("max_length").toString()) && ValidatorUtils.isNumber(params.get("max_length").toString())) {
                max_length = new Integer(params.get("max_length").toString());
            }
        }
        StringBuffer result = new StringBuffer();
        String content = post.getContent();
        if (max_length != 0) {
            content = HTMLUtils.preview(content, max_length);
        }
        
        result.append(content);
        env.getOut().append(result.toString());
    }

}
