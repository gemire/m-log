/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.comment;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Comment;
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
 * @TODO 循环文章评论
 */
public class ListCommentDirectiveModel extends AbstractDirectiveModel {

    private static final Logger log = Logger.getLogger(ListCommentDirectiveModel.class);

    private static final String KEY = "list_comment";

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
        // 获取当前模板变量中的文章对象
        Object commentListObj = env.__getitem__(FreemarkerVariableNames.COMMENTS);
        if (commentListObj == null || !(commentListObj instanceof List)) {
            log.warn("################post comments can't be found");
            return;
        }
        List<Comment> comments = (List<Comment>) commentListObj;
        int i = 0;
        for (Comment comment : comments) {
            env.__setitem__("list_index", i);
            env.__setitem__(FreemarkerVariableNames.COMMENT, comment);
            body.render(env.getOut());
            i++;
        }
    }

}
