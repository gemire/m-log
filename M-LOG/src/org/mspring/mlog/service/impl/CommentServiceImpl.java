/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.Synchronize;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.MailService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.utils.PostUrlUtils;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.utils.FreemarkerUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import freemarker.template.Configuration;

/**
 * @author Gao Youbo
 * @since 2012-7-27
 * @Description
 * @TODO
 */
@Service
@Transactional
public class CommentServiceImpl extends AbstractServiceSupport implements CommentService {

    private static final Logger log = Logger.getLogger(CommentServiceImpl.class);

    @Autowired
    private PostService postService;
    @Autowired
    private MailService mailService;
    @Autowired
    private Configuration configuration;
    @Autowired
    private OptionService optionService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#createComment(org.mspring.mlog
     * .entity.Comment)
     */
    @Override
    public Comment createComment(Comment comment) {
        // TODO Auto-generated method stub
        Serializable s = super.create(comment);
        return (Comment) super.getById(Comment.class, s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#deleteComment(java.lang.Long[])
     */
    @Override
    public void deleteComment(Long... idArray) {
        // TODO Auto-generated method stub
        super.remove(Comment.class, idArray);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#findCommentsByPost(java.lang.
     * Long)
     */
    @Override
    public List<Comment> findCommentsByPost(Long postId) {
        // TODO Auto-generated method stub
        String queryString = "select comment from Comment comment where comment.post.id = ? and comment.status = ?";
        return find(queryString, new Object[] { postId, Comment.Status.APPROVED });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#getCommentById(java.lang.Long)
     */
    @Override
    public Comment getCommentById(Long id) {
        // TODO Auto-generated method stub
        return (Comment) getById(Comment.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#findComment(java.lang.String,
     * java.lang.Object[])
     */
    @Override
    public Page<Comment> findComment(Page<Comment> page, String queryString, Object... params) {
        // TODO Auto-generated method stub
        return findPage(queryString, page, params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#getRecentComment(int)
     */
    @Override
    public List<Comment> getRecentComment(final int num) {
        // TODO Auto-generated method stub
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Comment>>() {

            @Override
            public List<Comment> doInHibernate(Session session) throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                Query query = session.createQuery("select comment from Comment comment where comment.status = :status order by comment.createTime desc");
                query.setMaxResults(num);
                query.setString("status", Comment.Status.APPROVED);
                return query.list();
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#findComment(org.mspring.platform
     * .persistence.support.Page,
     * org.mspring.platform.persistence.query.QueryCriterion)
     */
    @Override
    public Page<Comment> findComment(Page<Comment> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#approved(java.lang.Long[])
     */
    @Override
    public void approved(Long... ids) {
        // TODO Auto-generated method stub
        for (Long id : ids) {
            updateCommentStatus(id, Comment.Status.APPROVED);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#spam(java.lang.Long[])
     */
    @Override
    public void spam(Long... ids) {
        // TODO Auto-generated method stub
        for (Long id : ids) {
            updateCommentStatus(id, Comment.Status.SPAM);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#recycle(java.lang.Long[])
     */
    @Override
    public void recycle(Long... ids) {
        // TODO Auto-generated method stub
        for (Long id : ids) {
            updateCommentStatus(id, Comment.Status.RECYCLE);
        }
    }

    /**
     * 更新评论状态
     * 
     * @param status
     */
    private void updateCommentStatus(Long id, String status) {
        // 更新评论状态
        String queryString = "update Comment comment set comment.status = ? where comment.id = ?";
        executeUpdate(queryString, new Object[] { status, id });

        // 更新文章数量
        Object postId = findUnique("select comment.post.id from Comment comment where comment.id = ?", id);
        if (postId != null && StringUtils.isNotBlank(postId.toString())) {
            postService.updatePostCommentCount(new Long(postId.toString()));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#commentReplyNotice(java.lang.
     * Long)
     */
    @Override
    public void commentReplyNotice(Comment comment) {
        // TODO Auto-generated method stub
        comment.getParentEager();
        if (comment != null && comment.getParent() != null && StringUtils.isNotBlank(comment.getParent().getEmail())) {
            comment.getPostEager();

            String blogurl = optionService.getOption("blogurl");
            String commentUrl = blogurl + PostUrlUtils.getPostUrl(comment.getPost());

            Map<Object, Object> model = new HashMap<Object, Object>();
            model.put("commentUrl", commentUrl);
            model.put("comment", comment);
            model.put("post", comment.getPost());

            String content = FreemarkerUtils.render(configuration, "mail/comment_reply_notice.ftl", model);
            String to = comment.getParent().getEmail();
            String personal = comment.getParent().getAuthor();
            String subject = optionService.getOption("blogname") + " - 评论回复通知";
            mailService.sendMail(to, personal, subject, content);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CommentService#commentNotice(org.mspring.mlog
     * .entity.Comment)
     */
    @Override
    public void commentNotice(Comment comment) {
        // TODO Auto-generated method stub
        Map<Object, Object> model = new HashMap<Object, Object>();
        if (comment != null) {
            comment.getPostEager();

            String blogurl = optionService.getOption("blogurl");
            String commentUrl = blogurl + PostUrlUtils.getPostUrl(comment.getPost());

            model.put("commentUrl", commentUrl);
            model.put("comment", comment);
            model.put("post", comment.getPost());

            String content = FreemarkerUtils.render(configuration, "mail/new_comment_notice.ftl", model);
            String to = comment.getPost().getAuthor().getEmail();
            String personal = comment.getPost().getAuthor().getAlias();
            String subject = optionService.getOption("blogname") + " - 文章评论通知";
            mailService.sendMail(to, personal, subject, content);
        }
    }
}
