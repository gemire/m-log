/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.List;

import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.service.TagService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
@Service
@Transactional
public class TagServiceImpl extends AbstractServiceSupport implements TagService {

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TagService#findLikeByName(java.lang.String)
     */
    @Override
    public List<Tag> findLikeByName(String name) {
        // TODO Auto-generated method stub
        String queryString = "select tag from Tag tag where tag.name like ?";
        return super.find(queryString, name + "%");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.TagService#findUniqueByName(java.lang.String)
     */
    @Override
    public List<Tag> findUniqueByName(String name) {
        // TODO Auto-generated method stub
        String queryString = "select tag from Tag tag where tag.name = ?";
        return (List<Tag>) super.findUnique(queryString, name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.TagService#createTag(org.mspring.mlog.entity
     * .Tag)
     */
    @Override
    public Tag createTag(Tag tag) {
        // TODO Auto-generated method stub
        Serializable id = super.save(tag);
        return (Tag) super.get(Tag.class, id);
    }

}
