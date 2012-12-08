/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.PostMeta;
import org.mspring.mlog.service.PostMetaService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-9-7
 * @Description
 * @TODO
 */
@Service
@Transactional
public class PostMetaServiceImpl extends AbstractServiceSupport implements PostMetaService {
    private static final Logger log = Logger.getLogger(PostMetaServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.PostMetaService#insertPostMeta(java.lang.Long,
     * java.lang.String, java.lang.String)
     */
    @Override
    public void insertPostMeta(Long postId, String key, String value) {
        // TODO Auto-generated method stub
        if (postId == null && StringUtils.isBlank(key)) {
            log.debug(String.format("insert postmeta failure, postId=%s,key=%s,value=%s", postId, key, value));
            return;
        }
        PostMeta meta = new PostMeta();
        meta.setPostId(postId);
        meta.setKey(key);
        meta.setValue(value);
        create(meta);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.PostMetaService#getPostMeta(java.lang.Long,
     * java.lang.String)
     */
    @Override
    public List<PostMeta> getPostMeta(Long postId, String key) {
        // TODO Auto-generated method stub
        String queryString = "select pm from PostMeta pm where pm.postId = ? and pm.key = ?";
        return find(queryString, postId, key);
    }

}
