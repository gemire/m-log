/**
 * @since Jun 26, 2011
 * www.mspring.org
 * @author gaoyoubo
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.List;

import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.dao.CommentDao;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.service.ArticleService;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.OptionService;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;
    private ArticleService articleService;
    private OptionService optionService;

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#getCommentById(java.lang.Long)
     */
    @Override
    public Comment getCommentById(Long commentId) {
        // TODO Auto-generated method stub
        return commentDao.get(commentId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#getRecentComments(int)
     */
    @Override
    public List<Comment> getRecentComments(int count) {
        // TODO Auto-generated method stub
        return commentDao.getRecentComments(count);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#createComment(org.mspring.mlog
     *      .entity.Comment)
     */
    @Override
    public Comment createComment(Comment comment) {
        // TODO Auto-generated method stub
        // 判断审核功能是否开启
        String is_audit = optionService.getOption(ConfigTokens.mspring_config_base_auditcomment);
        if ("1".equals(is_audit)) // 开启审核
            comment.setStatus(Comment.STATUS_CHECK);
        else
            // 关闭审核
            comment.setStatus(Comment.STATUS_PASS);

        Serializable s = commentDao.save(comment);
        comment = commentDao.get(s);
        // 清空文章缓存，确保评论正常显示
        Long articleId = comment.getArticle().getId();
        articleService._removeArticleCache(articleId);

        return comment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#findCommentsByArticle(java.lang
     *      .Long)
     */
    @Override
    public List<Comment> findCommentsByArticle(Long articleId) {
        // TODO Auto-generated method stub
        return commentDao.findCommentsByArticle(articleId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#findAuditPassCommentsByArticle(java.lang.Long)
     */
    @Override
    public List<Comment> findAuditPassCommentsByArticle(Long articleId) {
        // TODO Auto-generated method stub
        return commentDao.findAuditPassCommentsByArticle(articleId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#deleteComment(java.lang.Long[])
     */
    @Override
    public void deleteComment(Long... ids) {
        // TODO Auto-generated method stub
        // 清空评论对应的文章缓存
        for (Long id : ids) {
            Comment comment = this.getCommentById(id);
            articleService._removeArticleCache(comment.getArticle().getId());
        }

        commentDao.delete(ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#queryComment(org.mspring.platform.dao.support.Page,
     *      org.mspring.platform.dao.query.QueryCriterion)
     */
    @Override
    public Page<Comment> queryComment(Page<Comment> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return commentDao.findPage(page, queryCriterion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CommentService#updateStatus(java.lang.Integer,
     *      java.lang.Long[])
     */
    @Override
    public void updateStatus(Integer status, Long... ids) {
        // TODO Auto-generated method stub
        for (Long id : ids) {
            Comment comment = this.getCommentById(id);
            comment.setStatus(status);
            commentDao.update(comment);
            
            //清除文章缓存
            articleService._removeArticleCache(comment.getArticle().getId());
        }
    }

}
