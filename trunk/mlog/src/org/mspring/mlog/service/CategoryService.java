package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Category;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 6, 2011 org.mspring.mlog.service
 */
public interface CategoryService {
    
    List<Category> findAll();

    Category createCategory(Category category);

    void deleteCategory(Long[] categories);

    Category findCategoryById(Long id);
    
    Category findCategoryByName(String name);

    void updateCategory(Category category);

    Page<Category> queryCategory(Page<Category> page, QueryCriterion queryCriterion);

    Page<Category> queryCategory(Page<Category> page, String queryString);
    
    List<Category> findCategoryByArticle(Long articleId);
}
