/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.comment;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.platform.utils.DateUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-8-9
 * @Description
 * @TODO
 */
public class CommentTimeDirectiveModel extends AbstractDirectiveModel {
    private static final Logger log = Logger.getLogger(CommentTimeDirectiveModel.class);

    public static final String KEY = "comment_time";

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
        Object commentObj = env.__getitem__(FreemarkerVariableNames.COMMENT);
        if (commentObj == null || !(commentObj instanceof Comment)) {
            log.warn("################comment can't be found");
            return;
        }
        Comment comment = (Comment) commentObj;
        Date date = new Date();
        if (comment.getCreateTime() != null) {
            date = comment.getCreateTime();
        }
        String dateString = DateUtils.format(date, DateUtils.YYYY_MM_DD_HH_MM_SS);
        env.getOut().append(dateString);
    }

}
