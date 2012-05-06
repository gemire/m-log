/**
 * @since Jul 23, 2011
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Article;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.entity.Comment;

/**
 * 
 * @author Gao Youbo
 */
public interface CustomService {
	/**
	 * 所有分类
	 * @return
	 */
	List<Category> getCategories();
	
	/**
	 * 最近发表文章
	 * 
	 * @return
	 */
	List<Article> getRecentEntries();
	
	/**
	 * 最近评论
	 * 
	 * @return
	 */
	List<Comment> getRecentComments();
}
