/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.service.TagService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
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
    public Tag findUniqueByName(String name) {
        // TODO Auto-generated method stub
        String queryString = "select tag from Tag tag where tag.name = ?";
        return (Tag) super.findUnique(queryString, name);
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
        Serializable id = super.create(tag);
        return getTagById((Long) id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TagService#getTagById(java.lang.Long)
     */
    @Override
    public Tag getTagById(Long id) {
        // TODO Auto-generated method stub
        return (Tag) getById(Tag.class, id);
    }

	public Page<Tag> findTag(Page<Tag> page, QueryCriterion queryCriterion) {

		return super.findPage(queryCriterion, page);
	}

	public void deleteTag(Long... idArray) {
		super.remove(Tag.class, idArray);
	}

	public boolean checkTagNameExists(String name, Long id) {
		//System.out.println(name+""+id);
		 int count = 0;
	        if (id == null) {
	            count = count("select count(*) from Tag tag where tag.name = ?", name);
	        } else {
	            count = count("select count(*) from Tag tag where tag.name = ? and tag.id <> ?", new Object[] { name, id });
	        }
	      //  System.out.println(count);
	     return count > 0;
	}

	public void modifyTag(Tag tag) {
		if(tag.getCreateTime() == null){
			tag.setCreateTime(new Date());
		}
		update(tag);
	}
	
	

}
