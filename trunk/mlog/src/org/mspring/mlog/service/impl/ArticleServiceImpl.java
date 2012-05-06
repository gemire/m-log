package org.mspring.mlog.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.Ehcache;

import org.apache.log4j.Logger;
import org.mspring.mlog.dao.ArticleCategoryDao;
import org.mspring.mlog.dao.ArticleDao;
import org.mspring.mlog.dao.ArticleTagDao;
import org.mspring.mlog.dao.CategoryDao;
import org.mspring.mlog.dao.TagDao;
import org.mspring.mlog.entity.Article;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.service.ArticleService;
import org.mspring.mlog.service.CacheService;
import org.mspring.mlog.util.ParameterUtils;
import org.mspring.mlog.web.cache.CacheTokens;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 13, 2011 org.mspring.mlog.service.impl
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private static final Logger log = Logger.getLogger(ArticleServiceImpl.class);

    private ArticleDao articleDao;
    private CategoryDao categoryDao;
    private TagDao tagDao;
    private CacheService cacheService;
    private ArticleTagDao articleTagDao;
    private ArticleCategoryDao articleCategoryDao;

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Autowired
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Autowired
    public void setArticleTagDao(ArticleTagDao articleTagDao) {
        this.articleTagDao = articleTagDao;
    }

    @Autowired
    public void setArticleCategoryDao(ArticleCategoryDao articleCategoryDao) {
        this.articleCategoryDao = articleCategoryDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ArticleService#_removeAllArticleCache()
     */
    @Override
    public void _removeAllArticleCache() {
        // TODO Auto-generated method stub
        try {
            Ehcache cache = cacheService.getEhcache(CacheTokens.PAGE_CACHE_NAME);
            if (cache == null) {
                log.debug("not found cache named " + CacheTokens.PAGE_CACHE_NAME);
                return;
            }
            List<String> keyList = cache.getKeys();
            for (String key : keyList) {
                if (!StringUtils.isBlank(key)) {
                    if (key.startsWith(CacheTokens.ARTICLE_CACHE_PREFIX)) {
                        cache.remove(key);
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ArticleService#_removeArticleCache(java.lang.Long)
     */
    @Override
    public void _removeArticleCache(Long articleId) {
        // TODO Auto-generated method stub
        try {
            cacheService.removeCache(CacheTokens.PAGE_CACHE_NAME, CacheTokens.ARTICLE_CACHE_PREFIX + articleId);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ArticleService#findArticleById(java.lang.Long)
     */
    @Override
    public Article findArticleById(Long id) {
        // TODO Auto-generated method stub
        return articleDao.get(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ArticleService#deleteArticle(java.lang.Long[])
     */
    @Override
    public void deleteArticle(Long... ids) {
        // TODO Auto-generated method stub
        articleDao.delete(ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ArticleService#queryArticle(org.mspring.platform.dao.support.Page,
     *      org.mspring.platform.dao.query.QueryCriterion)
     */
    @Override
    public Page<Article> queryArticle(Page<Article> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return articleDao.findPage(page, queryCriterion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ArticleService#getRecentArticles(int)
     */
    @Override
    public List<Article> getRecentArticles(int numposts) {
        // TODO Auto-generated method stub
        return articleDao.getRecentArticles(numposts);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ArticleService#createArticle(org.mspring.mlog.entity.Article)
     */
    @Override
    public Article createArticle(Article article) {
        // TODO Auto-generated method stub
        Long articleId = (Long) articleDao.save(article);

        // // 处理article和category的关联
        // if (article.getCategories() != null && article.getCategories().size()
        // > 0) {
        // for (Category category : article.getCategories()) {
        // ArticleCategoryPK PK = new ArticleCategoryPK(new Article(articleId),
        // new Category(category.getId()));
        // ArticleCategory articleCategory = new ArticleCategory(PK);
        // articleCategoryDao.save(articleCategory);
        // }
        // }
        // // 处理article和tag的关联
        // if (article.getTags() != null && article.getTags().size() > 0) {
        // for (Tag tag : article.getTags()) {
        // ArticleTagPK PK = new ArticleTagPK(new Article(articleId), new
        // Tag(tag.getId()));
        // ArticleTag articleTag = new ArticleTag(PK);
        // articleTagDao.save(articleTag);
        // }
        // }
        return articleDao.get(articleId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ArticleService#createArticle(org.mspring.mlog.entity.Article,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public Article createArticle(Article article, String categories, String tags) {
        // TODO Auto-generated method stub
        Long[] category_arr = ParameterUtils.splitTOLongArray(categories);
        Long[] tag_arr = ParameterUtils.splitTOLongArray(tags);

        Set<Category> categorySet = new HashSet<Category>();
        if (category_arr != null && category_arr.length > 0) {
            for (Long catId : category_arr) {
                categorySet.add(new Category(catId));
            }
        }

        Set<Tag> tagSet = new HashSet<Tag>();
        if (tag_arr != null && tag_arr.length > 0) {
            for (Long tagId : tag_arr) {
                tagSet.add(new Tag(tagId));
            }
        }

        article.setCategories(categorySet);
        article.setTags(tagSet);

        return createArticle(article);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ArticleService#updateArticle(org.mspring.mlog.entity.Article)
     */
    @Override
    public void updateArticle(Article article) {
        // TODO Auto-generated method stub
        articleDao.update(article);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ArticleService#updateArticle(org.mspring.mlog.entity.Article,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public void updateArticle(Article article, String categories, String tags) {
        // TODO Auto-generated method stub
        Long[] category_arr = ParameterUtils.splitTOLongArray(categories);
        Long[] tag_arr = ParameterUtils.splitTOLongArray(tags);

        Set<Category> categorySet = new HashSet<Category>();

        if (category_arr != null && category_arr.length > 0) {
            for (Long catId : category_arr) {
                categorySet.add(new Category(catId));
            }
        }

        Set<Tag> tagSet = new HashSet<Tag>();
        if (tag_arr != null && tag_arr.length > 0) {
            for (Long tagId : tag_arr) {
                tagSet.add(new Tag(tagId));
            }
        }

        article.setCategories(categorySet);
        article.setTags(tagSet);

        updateArticle(article);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.ArticleService#findAll()
     */
    @Override
    public List<Article> findAll() {
        // TODO Auto-generated method stub
        return articleDao.findAll();
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.ArticleService#updateArticleClick(java.lang.Long)
     */
    @Override
    public void updateArticleClick(Long articleId) {
        // TODO Auto-generated method stub
        articleDao.executeUpdate("update Article a set a.viewNums = a.viewNums + 1 where a.id = ?", articleId);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.ArticleService#updateCommentNum()
     */
    @Override
    public void updateCommentNum() {
        // TODO Auto-generated method stub
        String hql = "update Article a set a.commentNums = (select count(*) from Comment c where c.article.id = a.id)";
        articleDao.executeUpdate(hql);
    }

}