/**
 * 
 */
package org.mspring.mlog.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.mspring.mlog.dao.ArticleTagDao;
import org.mspring.mlog.dao.TagDao;
import org.mspring.mlog.entity.Tag;
import org.mspring.platform.dao.support.AbstractHibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
@Repository
public class TagDaoImpl extends AbstractHibernateDao<Tag> implements TagDao {

    /**
     * @param entityClass
     */
    protected TagDaoImpl() {
        super(Tag.class);
        // TODO Auto-generated constructor stub
    }

    private ArticleTagDao articleTagDao;

    @Autowired
    public void setArticleTagDao(ArticleTagDao articleTagDao) {
        this.articleTagDao = articleTagDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.dao.TagDao#findTagStringByArticle(java.lang.Long)
     */
    @Override
    public String findTagStringByArticle(Long articleId) {
        // TODO Auto-generated method stub
        List<Tag> list = findTagByArticle(articleId);
        String tagString = "";
        for (int i = 0; i < list.size(); i++) {
            tagString += list.get(i).getName();
            if (i != list.size() - 1) {
                tagString += ",";
            }
        }
        return tagString;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.dao.TagDao#findTagByArticle(java.lang.Long)
     */
    @Override
    public List<Tag> findTagByArticle(Long articleId) {
        // TODO Auto-generated method stub
        String queryString = " select t from Tag t,ArticleTag at where t.id = at.PK.tag.id and at.PK.article.id = ? ";
        return find(queryString, articleId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.dao.TagDao#getTagByName(java.lang.String)
     */
    @Override
    public Tag getTagByName(String tagName) {
        // TODO Auto-generated method stub
        List list = super.find("select t from Tag t where t.name = ?", tagName);
        if (list.size() > 0) {
            return (Tag) list.get(0);
        }
        return null;
    }

    private Tag createTag(String tagName) {
        Tag tag = getTagByName(tagName);
        // 如果存在,不添加
        if (tag != null) {
            return tag;
        }
        // 不存在,添加
        else {
            tag = new Tag(tagName);
            Serializable s = this.save(tag);
            tag.setId(new Long(s.toString()));
            return tag;
        }
    }

}
