/**
 * 
 */
package org.mspring.mlog.task.mail;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.entity.mail.Message;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.MailService;
import org.mspring.mlog.service.OptionService;
import org.mspring.platform.common.Validator;
import org.mspring.platform.task.AbstractTask;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since Apr 28, 2012 评论审核通过通知
 */
@Component
public class CommentAuditPassTask extends AbstractTask {
    private static final Logger log = Logger.getLogger(CommentAuditPassTask.class);

    private static final String TEMPLATE = "email/comment_audit_pass.ftl";

    public static final String PARAM_COMMENT_ID = "PARAM_COMMENT_ID";
    public static final String PARAM_COMMENT_STATUS = "PARAM_COMMENT_STATUS";

    private MailService mailService;
    private CommentService commentService;
    private OptionService optionService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Override
    protected void doTask(Map<Object, Object> paramMap) throws Exception {
        // TODO Auto-generated method stub
        Long[] ids = (Long[]) paramMap.get(PARAM_COMMENT_ID);
        if (ids != null) {
            for (Long id : ids) {
                Comment comment = commentService.getCommentById(id);
                if (comment != null && Validator.isEmailAddress(comment.getEmail())) {
                    Map<String, String> config = optionService.findBaseWebConfig();
                    
                    String blogname = config.get(ConfigTokens.mspring_config_base_blogname);
                    String blogurl = config.get(ConfigTokens.mspring_config_base_blogurl);

                    Message message = new Message();
                    message.setSubject("【" + blogname + "】评论审核通过通知");
                    message.setFrom(blogname);
                    message.addTo(comment.getEmail(), StringUtils.isBlank(comment.getAuthor()) ? comment.getEmail() : comment.getAuthor());

                    Map<Object, Object> context = new HashMap<Object, Object>();
                    context.put("comment", comment);
                    context.put("blogname", blogname);
                    context.put("blogurl", blogurl);
                    mailService.sendMail(message, TEMPLATE, context);
                    
                    log.info("send audit comment pass email to : " + comment.getEmail());
                }
            }
        }
    }
}
