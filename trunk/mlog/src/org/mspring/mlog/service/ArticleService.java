package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Article;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 13, 2011 org.mspring.mlog.service
 */
public interface ArticleService {
    
    public List<Article> findAll();
    /**
     * 根据ID查找
     * 
     * @param id
     * @return
     */
    public Article findArticleById(Long id);

    /**
     * 查询
     * 
     * @param page
     * @param queryBuilder
     * @return
     */
    public Page<Article> queryArticle(Page<Article> page, QueryCriterion queryCriterion);

    /**
     * 保存文章
     * 
     * @param article
     * @return
     */
    public Article createArticle(Article article);

    /**
     * 创建文章
     * 
     * @param article
     *            文章
     * @param categories
     *            分类
     * @param tags
     *            标签
     * @return
     */
    public Article createArticle(Article article, String categories, String tags);

    /**
     * 更新文章
     * 
     * @param article
     */
    public void updateArticle(Article article);

    /**
     * 更新文章
     * 
     * @param article
     * @param categories
     * @param tags
     * @return
     */
    public void updateArticle(Article article, String categories, String tags);
    
    /**
     * 更新文章点击率, 每次加一
     * @param articleId 文章编号
     */
    public void updateArticleClick(Long articleId);

    /**
     * 删除文章
     * 
     * @param ids
     */
    public void deleteArticle(Long... ids);

    /**
     * 查找最新发布的文章 显示数量config.articleListCount
     * 
     * @param numposts
     *            显示数量
     * @return
     */
    public List<Article> getRecentArticles(int numposts);
    
    /**
     * 更新文章评论数量
     */
    public void updateCommentNum();

    /**
     * 删除文章缓存
     * 
     * @param articleId
     */
    public void _removeArticleCache(Long articleId);

    /**
     * 删除所有文章缓存
     */
    public void _removeAllArticleCache();

}
