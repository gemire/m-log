/**
 * 
 */
package org.mspring.mlog.dao;

import java.util.List;

import org.mspring.mlog.entity.Tag;
import org.mspring.platform.dao.BaseDao;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
public abstract interface TagDao extends BaseDao<Tag> {
    String findTagStringByArticle(Long articleId);

    List<Tag> findTagByArticle(Long articleId);

    Tag getTagByName(String tagName);
}
