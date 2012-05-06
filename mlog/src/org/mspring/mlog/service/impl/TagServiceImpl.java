/**
 * Mar 17, 201111:09:43 AM
 * www.mspring.org
 * @author (gaoyb)mspring
 */
package org.mspring.mlog.service.impl;

import java.util.List;

import org.mspring.mlog.dao.TagDao;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.service.TagService;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gaoyb
 * 
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    private TagDao tagDao;

    @Autowired
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TagService#createTag(org.mspring.mlog.entity.Tag)
     */
    @Override
    public Tag createTag(Tag tag) {
        // TODO Auto-generated method stub
        Long id = (Long) tagDao.save(tag);
        return tagDao.get(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TagService#deleteTag(java.lang.Long[])
     */
    @Override
    public void deleteTag(Long... ids) {
        // TODO Auto-generated method stub
        tagDao.delete(ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TagService#findAllTag()
     */
    @Override
    public List<Tag> findAllTag() {
        // TODO Auto-generated method stub
        return tagDao.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TagService#findTagByArticle(java.lang.Long)
     */
    @Override
    public List<Tag> findTagByArticle(Long articleId) {
        // TODO Auto-generated method stub
        return tagDao.findTagByArticle(articleId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TagService#findTagStringByArticle(java.lang.Long)
     */
    @Override
    public String findTagStringByArticle(Long articleId) {
        // TODO Auto-generated method stub
        return tagDao.findTagStringByArticle(articleId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TagService#getTagByName(java.lang.String)
     */
    @Override
    public Tag getTagByName(String tagName) {
        // TODO Auto-generated method stub
        return tagDao.getTagByName(tagName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TagService#queryTag(org.mspring.platform.dao.support.Page,
     *      org.mspring.platform.dao.query.QueryCriterion)
     */
    @Override
    public Page<Tag> queryTag(Page<Tag> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return tagDao.findPage(page, queryCriterion);
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.TagService#updateTagCount()
     */
    @Override
    public void updateTagCount() {
        // TODO Auto-generated method stub
        String hql = "update Tag tag set tag.count = (select count(*) from ArticleTag at where at.PK.tag.id = tag.id)";
        tagDao.executeUpdate(hql);
    }

}
