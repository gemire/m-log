/**
 * @since Jul 23, 2011
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.service.impl;

import java.util.List;

import org.mspring.mlog.entity.Article;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.entity.Comment;
import org.mspring.mlog.service.CustomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Gao Youbo
 */
@Service
@Transactional
public class CustomServiceImpl implements CustomService {
    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CustomService#getCategories()
     */
    public List<Category> getCategories() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CustomService#getRecentComments()
     */
    public List<Comment> getRecentComments() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CustomService#getRecentEntries()
     */
    public List<Article> getRecentEntries() {
        // TODO Auto-generated method stub
        return null;
    }
}
