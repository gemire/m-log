package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.List;

import org.mspring.mlog.dao.CategoryDao;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.service.CategoryService;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 6, 2011 org.mspring.mlog.service.impl
 */

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CategoryService#findAll()
     */
    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        return categoryDao.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CategoryService#createCategory(org.mspring.mlog
     *      .entity.Category)
     */
    @Override
    public Category createCategory(Category category) {
        // TODO Auto-generated method stub
        Serializable s = categoryDao.save(category);
        return categoryDao.get(s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CategoryService#deleteCategory(java.lang.Long[])
     */
    @Override
    public void deleteCategory(Long[] categories) {
        // TODO Auto-generated method stub
        categoryDao.delete(categories);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CategoryService#findCategoryById(java.lang.Long)
     */
    @Override
    public Category findCategoryById(Long id) {
        // TODO Auto-generated method stub
        return categoryDao.get(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CategoryService#updateCategory(org.mspring.mlog.entity.Category)
     */
    @Override
    public void updateCategory(Category category) {
        // TODO Auto-generated method stub
        categoryDao.update(category);
    }

    @Override
    public Page<Category> queryCategory(Page<Category> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return categoryDao.findPage(page, queryCriterion);
    }

    @Override
    public Page<Category> queryCategory(Page<Category> page, String queryString) {
        // TODO Auto-generated method stub
        return categoryDao.findPage(page, queryString);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.CategoryService#findCategoryByName(java.lang.String)
     */
    @Override
    public Category findCategoryByName(String name) {
        // TODO Auto-generated method stub
        return categoryDao.findUnique("select c from Category c where c.name = ?", name);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.CategoryService#findCategoryByArticle(java.lang.Long)
     */
    @Override
    public List<Category> findCategoryByArticle(Long articleId) {
        // TODO Auto-generated method stub
        String queryString = "select c from Category c, Article a where a.id = ? and a.category.id = c.id";
        return categoryDao.find(queryString, articleId);
    }

}
