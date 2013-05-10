/**
 * 
 */
package org.mspring.msns.api.spider.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.mspring.msns.api.spider.service.SpiderPostService;
import org.mspring.msns.api.spider.vo.SpiderPost;
import org.mspring.msns.entity.Catalog;
import org.mspring.msns.entity.Post;
import org.mspring.msns.service.PostService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO
 */
@Service
@Transactional
public class SpiderPostServiceImpl extends AbstractServiceSupport implements SpiderPostService {
    @Autowired
    private PostService postService;

    @Override
    public SpiderPost getSpiderPostById(Long id) {
        // TODO Auto-generated method stub
        if (id == null) {
            return null;
        }
        return (SpiderPost) getById(SpiderPost.class, id);
    }

    @Override
    public void createSpiderPost(SpiderPost spiderPost) {
        // TODO Auto-generated method stub
        spiderPost.setCreateTime(new Date());
        spiderPost.setPosted(false);
        create(spiderPost);
    }

    @Override
    public Page<SpiderPost> findSpiderPostPage(QueryCriterion queryCriterion, Page<SpiderPost> spiderPostPage) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, spiderPostPage);
    }

    @Override
    public void deleteSpiderPost(Long... id) {
        // TODO Auto-generated method stub
        remove(SpiderPost.class, id);
    }

    @Override
    public void changePosted(Boolean posted, Long... id) {
        // TODO Auto-generated method stub
        if (posted == null || id == null) {
            return;
        }
        for (Long i : id) {
            executeUpdate("update SpiderPost sp set sp.posted = ? where sp.id = ?", new Object[] { posted, i });
        }
    }

    @Override
    public void publishPost(Long[] catalogs, Long... id) {
        // TODO Auto-generated method stub
        if (id == null) {
            return;
        }
        SpiderPost sp = null;
        Post post = null;
        for (Long i : id) {
            sp = getSpiderPostById(i);
            if (sp == null) {
                continue;
            }
            post = new Post();
            post.setTitle(sp.getTitle());
            post.setContent(sp.getContent());
            if (catalogs != null && catalogs.length > 0) {
                Set<Catalog> cset = new HashSet<Catalog>();
                for (Long catalog : catalogs) {
                    cset.add(new Catalog(catalog));
                }
                post.setCatalogs(cset);
            }
            postService.createPost(post);
            changePosted(true, i);
        }
    }
}
