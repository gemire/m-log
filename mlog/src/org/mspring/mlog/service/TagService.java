/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Tag;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
public interface TagService {
    /**
     * 根据名字模糊查询
     * @param name
     * @return
     */
    List<Tag> findLikeByName(String name);

    /**
     * 根据名字获取唯一的tag对象
     * @param name
     * @return
     */
    List<Tag> findUniqueByName(String name);

    /**
     * 创建tag对象
     * @param tag
     * @return
     */
    Tag createTag(Tag tag);
}
