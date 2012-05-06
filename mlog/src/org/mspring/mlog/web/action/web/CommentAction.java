/**
 * 
 */
package org.mspring.mlog.web.action.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.entity.Comment;

/**
 * @author Gao Youbo
 * @since Jan 25, 2012
 */
public class CommentAction extends CommonWebActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -319626647627164559L;
    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String postcomment() {
        // Message message = new Message();
        // message.setFrom("慕春博客");
        // message.addTo("330721072@qq.com", "高尤波");
        // message.addCc("398308747@qq.com", "方慧平");
        // message.setSubject("邮件测试");
        // message.setContent("邮件测试内容");
        // mailService.sendMail(message);
        HttpServletRequest request = ServletActionContext.getRequest();
        String ip = request.getRemoteAddr();
        String agent = request.getHeader("user-agent");

        comment.setIp(ip);
        comment.setAgent(agent);
        comment.setCreateTime(new Date());
        commentService.createComment(comment);
        return SUCCESS;
    }
}
