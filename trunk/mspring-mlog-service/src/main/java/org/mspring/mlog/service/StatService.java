/**
 * 
 */
package org.mspring.mlog.service;

/**
 * @author Gao Youbo
 * @since 2012-8-14
 * @Description 
 * @TODO 
 */
public interface StatService {
    /**
     * 获取文章总数
     * @return
     */
    public String getPostCount();
    
    /**
     * 获取文章总数量
     * @return
     */
    public String getCommentCount();
    
    /**
     * 获取网站点击率
     * @return
     */
    public String getClickCount();
    
    /**
     * 更新文章总数量
     */
    public void updatePostCount();
    
    /**
     * 
     * 更新评论总数量
     */
    public void updateCommentCount();
    
    /**
     * 更新点击率
     */
    public void updateClickCount();
}
