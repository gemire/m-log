/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Comment;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-7-27
 * @Description
 * @TODO
 */
public interface CommentService {
    /**
     * 添加评论
     * 
     * @param comment
     * @return
     */
    public Comment createComment(Comment comment);

    /**
     * 根据文章编号查找该文章下的评论
     * 
     * @param postId
     * @return
     */
    public List<Comment> findCommentsByPost(Long postId);

    /**
     * 更具评论ID获取评论对象
     * 
     * @param id
     * @return
     */
    public Comment getCommentById(Long id);

    /**
     * 分页查找评论
     * 
     * @param page
     *            page对象
     * @param queryString
     *            hql语句
     * @param params
     *            查询参数
     * @return
     */
    public Page<Comment> findComment(Page<Comment> page, String queryString, Object... params);

    /**
     * 分页查找评论
     * 
     * @param page
     * @param queryCriterion
     * @return
     */
    public Page<Comment> findComment(Page<Comment> page, QueryCriterion queryCriterion);

    /**
     * 查找最新评论
     * 
     * @param num
     *            查询条数
     * @return
     */
    public List<Comment> getRecentComment(int num);

    /**
     * 彻底删除
     * 
     * @param ids
     */
    public void deleteComment(Long... ids);

    /**
     * 审核通过
     * 
     * @param ids
     */
    public void approved(Long... ids);

    /**
     * 编辑为垃圾评论
     * 
     * @param ids
     */
    public void spam(Long... ids);

    /**
     * 移到回收站
     * 
     * @param ids
     */
    public void recycle(Long... ids);

    /**
     * 评论回复邮件通知
     * 
     * @param comment
     *            当前评论编号
     */
    public void commentReplyNotice(Comment comment);

    /**
     * 新评论邮件通知文章作者
     * 
     * @param comment
     */
    public void commentNotice(Comment comment);
}
