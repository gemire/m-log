/**
 * Mar 17, 201111:08:11 AM
 * www.mspring.org
 * @author (gaoyb)mspring
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Tag;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author gaoyb
 * 
 */
public interface TagService {
    String findTagStringByArticle(Long articleId);

    List<Tag> findTagByArticle(Long articleId);

    Tag getTagByName(String tagName);

    List<Tag> findAllTag();

    Tag createTag(Tag tag);

    void deleteTag(Long... ids);

    Page<Tag> queryTag(Page<Tag> page, QueryCriterion queryCriterion);
    
    /**
     * 更新TAG使用量
     */
    void updateTagCount();
}
