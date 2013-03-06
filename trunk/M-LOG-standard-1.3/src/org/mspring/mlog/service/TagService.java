/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Tag;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
public interface TagService {
    /**
     * 根据名字模糊查询
     * 
     * @param name
     * @return
     */
    List<Tag> findLikeByName(String name);

    /**
     * 根据名字获取唯一的tag对象
     * 
     * @param name
     * @return
     */
    Tag findUniqueByName(String name);

    /**
     * 创建tag对象
     * 
     * @param tag
     * @return
     */
    Tag createTag(Tag tag);

    /**
     * 根据ID获取标签
     * 
     * @param id
     * @return
     */
    Tag getTagById(Long id);
    
    /**
     * 查找tag
     * @param page
     * @param queryCriterion
     * @return
     */
    Page<Tag> findTag(Page<Tag> page ,QueryCriterion queryCriterion);
    
    /**
     * 删除tag
     * @param idArray
     */
	public void deleteTag(Long... idArray);
	
	/**
	 * 验证改tag是否存在
	 * @param name
	 * @param id
	 * @return
	 */
	public boolean checkTagNameExists(String name, Long id);

	/**
	 * 修改tag
	 * @param tag
	 */
	public void modifyTag(Tag tag);
}
